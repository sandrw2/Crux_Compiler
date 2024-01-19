package crux.ir;

import crux.ast.SymbolTable.Symbol;
import crux.ast.*;
import crux.ast.OpExpr.Operation;
import crux.ast.traversal.NodeVisitor;
import crux.ast.types.*;
import crux.ir.insts.*;

import javax.print.attribute.standard.JobHoldUntil;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;



class InstPair {
  //TODO
  private Instruction start;
  private Instruction end;
  private Value value;
  public InstPair(Instruction start, Instruction end, Value value){
    this.start = start;
    this.end = end;
    this.value = value;
  }

  Instruction getStart(){
    return start;
  }
  Instruction getEnd(){
    return end;
  }
  Value getVal(){
    return value;
  }
}


/**
 * Convert AST to IR and build the CFG
 */
public final class ASTLower implements NodeVisitor<InstPair> {
  //global stack to account for break statements in nested loops
  Stack<Instruction> loopStack = new Stack<>();
  private Program mCurrentProgram = null;
  private Function mCurrentFunction = null;

  private Map<Symbol, LocalVar> mCurrentLocalVarMap = null;

  /**
   * A constructor to initialize member variables
   */
  public ASTLower() {}

  public Program lower(DeclarationList ast) {
    visit(ast);
    return mCurrentProgram;
  }

  @Override
  public InstPair visit(DeclarationList declarationList) {
    mCurrentProgram = new Program();
    for (Node declaration : declarationList.getChildren()){
      declaration.accept(this);
    }
    return null;
  }

  /**
   * This visitor should create a Function instance for the functionDefinition node, add parameters
   * to the localVarMap, add the function to the program, and init the function start Instruction.
   */
  @Override
  public InstPair visit(FunctionDefinition functionDefinition) {
    // create function instance (and set mCurrentFunction equal to it??)
    String func_name = functionDefinition.getSymbol().getName();
    FuncType func_type = (FuncType) (functionDefinition.getSymbol().getType());
    Function func_instance  = new Function(func_name, func_type);
    mCurrentFunction = func_instance;

    // create new hashmap<Symbol, Variable> for mCurrentLocalVarMap
    mCurrentLocalVarMap = new HashMap<Symbol, LocalVar>();

    // create list to set mCurrentFunction argument list
    ArrayList<LocalVar> args = new ArrayList<LocalVar>();

    for(Symbol argument : functionDefinition.getParameters()){
      // create LocalVar using mCurrentFunction.getTempVar() and put them in a list
      LocalVar temp = mCurrentFunction.getTempVar(argument.getType());
      args.add(temp);
      // put the variable (↑) to mCurrentLocalVarMap with correct symbol
      mCurrentLocalVarMap.put(argument, temp);
    }

    // set arguments for mCurrentFunction
    mCurrentFunction.setArguments(args);

    // add mCurrentFunction to the function list in mCurrentProgram
    mCurrentProgram.addFunction(mCurrentFunction);

    // visit function body
    InstPair statements_inst  = functionDefinition.getStatements().accept(this);

    // set the start node of mCurrentFunction
    mCurrentFunction.setStart(statements_inst.getStart());

    // dump mCurrentFunction and mCurrentLocalVarMap (Set back to null)
    mCurrentFunction = null;
    mCurrentLocalVarMap = null;

    return null;

  }

  @Override
  public InstPair visit(StatementList statementList) {
    //there might be an issue here;
    Instruction currentInstruction = new NopInst();
    Instruction begin =  currentInstruction;
    for (Node statement :statementList.getChildren()){
      //visit statement
      InstPair visited = statement.accept(this);
      currentInstruction.setNext(0, visited.getStart());
      currentInstruction = visited.getEnd();
    }
    return new InstPair(begin, currentInstruction, null);
  }

  /**
   * Declarations, could be either local or Global
   */
  @Override
  public InstPair visit(VariableDeclaration variableDeclaration) {
    if(mCurrentFunction == null){
      //apparently slides in disc7 says to use value: 1 for variables
      IntegerConstant global = IntegerConstant.get(mCurrentProgram, 1);
      GlobalDecl newGlob = new GlobalDecl(variableDeclaration.getSymbol(), global);
      mCurrentProgram.addGlobalVar(newGlob);
    } else {
      LocalVar newLocalVar = mCurrentFunction.getTempVar(variableDeclaration.getSymbol().getType());
      mCurrentLocalVarMap.put(variableDeclaration.getSymbol(), newLocalVar);
    }
    Instruction noOP = new NopInst();
    return new InstPair(noOP, noOP, null);
  }

  /**
   * Create a declaration for array and connected it to the CFG
   */
  @Override
  public InstPair visit(ArrayDeclaration arrayDeclaration) {
    //IntegerConstant value should be the extent of the array.
    IntegerConstant global = IntegerConstant.get(mCurrentProgram, ((ArrayType)(arrayDeclaration.getSymbol().getType())).getExtent());
    GlobalDecl newGlob = new GlobalDecl(arrayDeclaration.getSymbol(), global);
    mCurrentProgram.addGlobalVar(newGlob);
    Instruction noOP = new NopInst();
    return new InstPair(noOP, noOP, null);
  }

  /**
   * LookUp the name in the map(s). For globals, we should do a load to get the value to load into a
   * LocalVar.
   */
  @Override
  public InstPair visit(VarAccess name) {
    LocalVar access = mCurrentLocalVarMap.get(name.getSymbol());
    if(access != null){
      //this means that the variable is a local variable, return no op
      Instruction noOP = new NopInst();
      return new InstPair(noOP, noOP, access);
    } else {
      AddressVar tempVar = mCurrentFunction.getTempAddressVar(name.getSymbol().getType());
      AddressAt tempAddress = new AddressAt(tempVar, name.getSymbol());
      return new InstPair(tempAddress, tempAddress, tempVar);
    }
  }

  /**
   * If the location is a VarAccess to a LocalVar, copy the value to it. If the location is a
   * VarAccess to a global, store the value. If the location is ArrayAccess, store the value.
   */
  @Override
  public InstPair visit(Assignment assignment) {
    InstPair lhs = assignment.getLocation().accept(this);
    InstPair rhs = assignment.getValue().accept(this);
    lhs.getEnd().setNext(0, rhs.getStart());
    if(lhs.getVal().getClass() == LocalVar.class){
      if(rhs.getVal().getClass() == LocalVar.class) {
        CopyInst copyInst = new CopyInst((LocalVar) lhs.getVal(), rhs.getVal());
        rhs.getEnd().setNext(0, copyInst);
        return new InstPair(lhs.getStart(), copyInst, null);
      } else {
        LoadInst loadInst = new LoadInst((LocalVar) lhs.getVal(), (AddressVar) rhs.getVal());
        rhs.getEnd().setNext(0, loadInst);
        return new InstPair(lhs.getStart(), loadInst, null);
      }
    } else {
      //rhs can also be local or global
      if(rhs.getVal().getClass() == LocalVar.class) {
        //rhs is local
        StoreInst storeInst = new StoreInst((LocalVar) rhs.getVal(), (AddressVar) lhs.getVal());
        rhs.getEnd().setNext(0, storeInst);
        return new InstPair(lhs.getStart(), storeInst, null);
      } else {
        //rhs is global
        LocalVar tempVar = mCurrentFunction.getTempVar(assignment.getType());
        //load global to temp
        LoadInst loadInst = new LoadInst(tempVar, (AddressVar) rhs.getVal());
        //store temp value to lhs global
        StoreInst storeInst = new StoreInst(tempVar, (AddressVar) lhs.getVal());
        //link rhs to load
        rhs.getEnd().setNext(0, loadInst);
        //link load to store
        loadInst.setNext(0, storeInst);
        return new InstPair(lhs.getStart(), storeInst, null);
      }
    }
  }

  /**
   * Lower a Call.
   */
  @Override
  public InstPair visit(Call call) {
    //Visit each argument and add their values
    //(copied/loaded to LocalVar if not already) to a list(params) for creating callInst.
    ArrayList<LocalVar> params = new ArrayList<LocalVar>();
    LocalVar tempVar = null;
    Instruction firstInst = new NopInst();
    Instruction lastInst =  firstInst;
    InstPair expressionInstPair = null;
    for(Expression arg : call.getArguments()){
      expressionInstPair = arg.accept(this);
      lastInst.setNext(0, expressionInstPair.getStart());
      lastInst = expressionInstPair.getEnd();

      //add parameter values into list
      //If LocalVariable then grab straight out of InstPair Value
      //Otherwise Load global Variable
      tempVar = mCurrentFunction.getTempVar(expressionInstPair.getVal().getType());
      if(expressionInstPair.getVal().getClass() != LocalVar.class){
        LoadInst load = new LoadInst(tempVar, (AddressVar) (expressionInstPair.getVal()));
        lastInst.setNext(0, load);
        lastInst = load;
        params.add(tempVar);
      }else{
        params.add((LocalVar) (expressionInstPair.getVal()));
      }
    }

    Type ret = ((FuncType)(call.getCallee().getType())).getRet();
    if(ret.getClass() != VoidType.class){
      LocalVar retTemp = mCurrentFunction.getTempVar(((FuncType)(call.getCallee().getType())).getRet());
      CallInst callInst = new CallInst(retTemp, call.getCallee(), params);
      lastInst.setNext(0, callInst);
      lastInst = callInst;
      return new InstPair(firstInst, lastInst, retTemp);
    }else{
      CallInst callInst = new CallInst(call.getCallee(), params);
      lastInst.setNext(0, callInst);
      lastInst = callInst;
      return new InstPair(firstInst, lastInst, null);
    }
  }

  /**
   * Handle operations like arithmetics and comparisons. Also handle logical operations (and,
   * or, not).
   */
  @Override
  public InstPair visit(OpExpr operation) {


    //visit lhs
    InstPair lhs = operation.getLeft().accept(this);
    Instruction begin = lhs.getStart();
    Instruction current = lhs.getEnd();

    LocalVar lhsVar = mCurrentFunction.getTempVar(operation.getType());
    if(operation.getLeft().accept(this).getVal().getClass()!= LocalVar.class){
      LoadInst load = new LoadInst(lhsVar, (AddressVar) (lhs.getVal()));
      current.setNext(0, load);
      current = load;
    }else{
      lhsVar = (LocalVar) (lhs.getVal());
    }

    //create dest variable to store result of operation
    LocalVar dest = mCurrentFunction.getTempVar(operation.getType());

    if(operation.getOp() == Operation.LOGIC_NOT){
      UnaryNotInst unInst = new UnaryNotInst(dest, lhsVar);
      current.setNext(0, unInst);
      current = unInst;

    }else if(operation.getOp() == Operation.LOGIC_OR){
      JumpInst jmpInst = new JumpInst(lhsVar);
      current.setNext(0, jmpInst);
      current = jmpInst;
      Instruction branch1;
      Instruction branch2;

      //if lhsVal was true
      CopyInst cpyInst1 = new CopyInst(dest, lhs.getVal());
      branch1 = cpyInst1;
      NopInst nop = new NopInst();
      branch1.setNext(0, nop);

      //else visit rhs
      InstPair rhs  = operation.getRight().accept(this);
      branch2 = rhs.getEnd();
      CopyInst cpyInst0 = new CopyInst(dest, rhs.getVal());
      branch2.setNext(0,cpyInst0);
      branch2 = cpyInst0;
      branch2.setNext(0, nop);

      //connect branches
      current.setNext(0, rhs.getStart());
      current.setNext(1, branch1);

      return new InstPair(begin,nop,dest);

    }else if(operation.getOp() == Operation.LOGIC_AND){
      JumpInst jmpInst = new JumpInst(lhsVar);
      current.setNext(0, jmpInst);
      current = jmpInst;
      Instruction branch1;
      Instruction branch2;

      //if lhsVal was false
      CopyInst cpyInst0 = new CopyInst(dest, lhs.getVal());
      branch1 = cpyInst0;
      NopInst nop = new NopInst();
      branch1.setNext(0, nop);

      //else visit rhs
      InstPair rhs  = operation.getRight().accept(this);
      branch2 = rhs.getEnd();
      CopyInst cpyInst1 = new CopyInst(dest, rhs.getVal());
      branch2.setNext(0,cpyInst1);
      branch2 = cpyInst1;
      branch2.setNext(0, nop);

      //connect branches
      current.setNext(0, branch1);
      current.setNext(1, rhs.getStart());

      return new InstPair(begin,nop,dest);

    }else{
      // visit rhs
      // check for global --> load
      InstPair rhs  = operation.getRight().accept(this);
      current.setNext(0, rhs.getStart());
      current = rhs.getEnd();
      LocalVar rhsVar = mCurrentFunction.getTempVar(operation.getType());
      if(operation.getRight().accept(this).getVal().getClass()!= LocalVar.class){
        LoadInst load = new LoadInst(rhsVar, (AddressVar) (rhs.getVal()));
        current.setNext(0, load);
        current = load;
      }else{
        rhsVar = (LocalVar)(rhs.getVal());
      }

      Instruction opInst;
      if(operation.getOp() == Operation.ADD){
        opInst = new BinaryOperator(BinaryOperator.Op.Add,dest, lhsVar, rhsVar);
        current.setNext(0,opInst);
        current = opInst;
      }else if(operation.getOp() == Operation.SUB){
        opInst = new BinaryOperator(BinaryOperator.Op.Sub,dest, lhsVar,rhsVar);
        current.setNext(0,opInst);
        current = opInst;
      }else if(operation.getOp() == Operation.MULT){
        opInst = new BinaryOperator(BinaryOperator.Op.Mul,dest, lhsVar,rhsVar);
        current.setNext(0,opInst);
        current = opInst;
      }else if(operation.getOp() == Operation.DIV){
        opInst = new BinaryOperator(BinaryOperator.Op.Div,dest, lhsVar,rhsVar);
        current.setNext(0,opInst);
        current = opInst;
      }else if(operation.getOp() == Operation.GE){
        opInst = new CompareInst(dest, CompareInst.Predicate.GE, lhsVar, rhsVar);
        current.setNext(0,opInst);
        current = opInst;
      }else if(operation.getOp() == Operation.GT){
        opInst = new CompareInst(dest, CompareInst.Predicate.GT, lhsVar, rhsVar);
        current.setNext(0,opInst);
        current = opInst;
      }else if(operation.getOp() == Operation.LE){
        opInst = new CompareInst(dest, CompareInst.Predicate.LE, lhsVar, rhsVar);
        current.setNext(0,opInst);
        current = opInst;
      }else if(operation.getOp() == Operation.LT){
        opInst = new CompareInst(dest, CompareInst.Predicate.LT, lhsVar, rhsVar);
        current.setNext(0,opInst);
        current = opInst;
      }else if(operation.getOp() == Operation.EQ){
        opInst = new CompareInst(dest, CompareInst.Predicate.EQ, lhsVar, rhsVar);
        current.setNext(0,opInst);
        current = opInst;
      }else{
        //operation.NE
        opInst = new CompareInst(dest, CompareInst.Predicate.NE, lhsVar, rhsVar);
        current.setNext(0,opInst);
        current = opInst;
      }
    }
    return new InstPair(begin, current, dest);
  }


  //  private InstPair visit(Expression expression) {
//    return null;
//  }

  /**
   * It should compute the address into the array, do the load, and return the value in a LocalVar.
   */
  @Override
  public InstPair visit(ArrayAccess access) {
    InstPair visited = access.getIndex().accept(this);
    Symbol base = access.getBase();
    AddressVar tempAddressVar = mCurrentFunction.getTempAddressVar(access.getType());
    if(visited.getVal().getClass() != LocalVar.class){
      LocalVar tempVar = mCurrentFunction.getTempVar(access.getType());
      LoadInst loadVar = new LoadInst(tempVar, (AddressVar) visited.getVal());
      AddressAt tempAt = new AddressAt(tempAddressVar, base, tempVar);
      visited.getEnd().setNext(0, loadVar);
      loadVar.setNext(0, tempAt);
      return new InstPair(visited.getStart(), tempAt, tempAddressVar);
    } else {
      AddressAt tempAt = new AddressAt(tempAddressVar, base, (LocalVar) visited.getVal());
      visited.getEnd().setNext(0, tempAt);
      return new InstPair(visited.getStart(), tempAt, tempAddressVar);
    }
  }

  /**
   * Copy the literal into a tempVar
   */
  @Override
  public InstPair visit(LiteralBool literalBool) {
    LocalVar temp = mCurrentFunction.getTempVar(literalBool.getType());
    CopyInst test = new CopyInst(temp, BooleanConstant.get(mCurrentProgram, literalBool.getValue()));
    return new InstPair(test, test, temp);
  }

  /**
   * Copy the literal into a tempVar
   */
  @Override
  public InstPair visit(LiteralInt literalInt) {
    LocalVar temp = mCurrentFunction.getTempVar(literalInt.getType());
    CopyInst test = new CopyInst(temp, IntegerConstant.get(mCurrentProgram, literalInt.getValue()));
    return new InstPair(test, test, temp);
  }

  /**
   * Lower a Return.
   */
  @Override
  public InstPair visit(Return ret) {
    InstPair returnPair = ret.getValue().accept(this);
    Value debug = returnPair.getVal();
    ReturnInst test = new ReturnInst((LocalVar) (returnPair.getVal()));
    returnPair.getEnd().setNext(0, test);
    return new InstPair(returnPair.getStart(), test, test.getReturnValue());
  }

  /**
   * Break Node
   */
  @Override
  public InstPair visit(Break brk) {
    //pop off from global loop stack
    // use current loopEnd as InstPair start
    return new InstPair(loopStack.peek(), new NopInst(), null);
  }

  /**
   * Implement If Then Else statements.
   */
  @Override
  public InstPair visit(IfElseBranch ifElseBranch) {
    InstPair condInst = ifElseBranch.getCondition().accept(this);
    Instruction begin = condInst.getStart();
    Instruction current = condInst.getEnd();

    JumpInst jumpInst = new JumpInst((LocalVar)(condInst.getVal()));
    current.setNext(0, jumpInst);
    current = jumpInst;

    //visit else and then branch
    NopInst nop = new NopInst();
    InstPair elseInst = ifElseBranch.getElseBlock().accept(this);
    elseInst.getEnd().setNext(0, nop);

    InstPair thenInst = ifElseBranch.getThenBlock().accept(this);
    thenInst.getEnd().setNext(0, nop);

    current.setNext(0, elseInst.getStart());
    current.setNext(1, thenInst.getStart());

    return new InstPair(begin, nop, null);
  }

  /**
   * Implement for loops.
   */
  @Override
  public InstPair visit(For loop) {
    //create global stack to account for break in nested loops
    //place noOps for loop ends in loopStack
    NopInst loopEnd = new NopInst();
    loopStack.push(loopEnd);

    //visit header of loop
    InstPair initInst = loop.getInit().accept(this);
    InstPair condInst = loop.getCond().accept(this);

    //link init inst to cond
    initInst.getEnd().setNext(0,condInst.getStart());

    //link cond to jmp
    JumpInst jmpInst = new JumpInst((LocalVar)(condInst.getVal()));
    condInst.getEnd().setNext(0, jmpInst);

    //link jmp to loopEnd
    jmpInst.setNext(0, loopEnd);

    //link jmp to body
    InstPair bodyInst = loop.getBody().accept(this);
    jmpInst.setNext(1, bodyInst.getStart());

    //link body to incr
    InstPair incrInst = loop.getIncrement().accept(this);
    bodyInst.getEnd().setNext(0, incrInst.getStart());

    //link incr to cond
    incrInst.getEnd().setNext(0, condInst.getStart());

    loopStack.pop();

    //return InstPair
    return new InstPair(initInst.getStart(), loopEnd, null);

  }
}
