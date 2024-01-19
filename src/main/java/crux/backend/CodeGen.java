package crux.backend;

import crux.ast.OpExpr;
import crux.ast.SymbolTable.Symbol;
import crux.ast.types.*;
import crux.ir.*;
import crux.ir.insts.*;
import crux.printing.IRValueFormatter;

import java.util.*;

/**
 * Convert the CFG into Assembly Instructions
 */
public final class CodeGen extends InstVisitor {
  private final Program p;
  private final CodePrinter out;
  private HashMap<Variable, String> varIndexMap = new HashMap<Variable, String>();
  HashMap<Instruction, String> labelMap;
  private int varIndex = 0;

  //change this toggle when submitting
  private static boolean mac = false;

  private IRValueFormatter irFormat = new IRValueFormatter();

  public CodeGen(Program p) {
    this.p = p;
    // Do not change the file name that is outputted or it will
    // break the grader!

    out = new CodePrinter("a.s");
  }

  /**
   * It should allocate space for globals call genCode for each Function
   */
  public void genCode() {
    //TODO
    for(Iterator<GlobalDecl> glob_it = p.getGlobals(); glob_it.hasNext();){
      GlobalDecl g = glob_it.next();
      //2 types of globals, array and int; array have extent, int only have 1 for size believe
      if(g.getSymbol().getType().getClass() == ArrayType.class){
        if(mac) {
          out.printCode(".comm _" + g.getSymbol().getName() + ", " + (((ArrayType) (g.getSymbol().getType())).getExtent()) * 8 + ", 8");
        } else {
          out.printCode(".comm " + g.getSymbol().getName() + ", " + (((ArrayType) (g.getSymbol().getType())).getExtent()) * 8 + ", 8");
        }
      }else{
        if(mac) {
          out.printCode(".comm _" + g.getSymbol().getName() + ", 8, 8");
        } else {
          out.printCode(".comm " + g.getSymbol().getName() + ", 8, 8");
        }
      }
    }

    int count[] = new int[1];
    for(Iterator<Function> func_it = p.getFunctions(); func_it.hasNext();){
      //refresh Hashmap
      varIndexMap.clear();
      varIndex = 0;
      Function f = func_it.next();
      genCode(f,count);
    }

    out.close();
  }


  private void genCode(Function f, int count[]){
    //Assign labels to jump targets
    labelMap = f.assignLabels(count);
    //declare function and label
    if(mac) {
      out.printCode(".globl _" + f.getName());
      out.printLabel("_" + f.getName() + ":");
    } else {
      out.printCode(".globl " + f.getName());
      out.printLabel(f.getName() + ":");
    }
    //enter to make space for local and temp local variables
    int slots = f.getNumTempAddressVars() + f.getNumTempVars();
    slots = (slots+1) & ~1;
    out.printCode("enter $(8 * " + slots + "), $0");
    //move arguments from registers and stack to local variable
    int argNum = 1;
    for(LocalVar arg : f.getArguments()){
      //make space to store current arg into a local var
      varIndex++;

      int destOffset  = -varIndex * 8;
      if(argNum > 6){
        int sourceOffset = (8*(argNum-7))+16;
        out.printCode("movq " +sourceOffset+ "(%rbp), %r10");
        out.printCode("movq %r10, " + destOffset + "(%rbp)");
        varIndexMap.put(arg, destOffset+ "(%rbp)");
        argNum++;
        continue;
      }else{
        switch(argNum){
          case(1):
            out.printCode("movq %rdi, " + destOffset + "(%rbp)" );
            varIndexMap.put(arg, destOffset + "(%rbp)");
            break;
          case(2):
            out.printCode("movq %rsi, " + destOffset + "(%rbp)");
            varIndexMap.put(arg, destOffset + "(%rbp)");
            break;
          case(3):
            out.printCode("movq %rdx, " + destOffset + "(%rbp)");
            varIndexMap.put(arg, destOffset + "(%rbp)");
            break;
          case(4):
            out.printCode("movq %rcx, " + destOffset + "(%rbp)");
            varIndexMap.put(arg, destOffset + "(%rbp)");
            break;
          case(5):
            out.printCode("movq %r8, " + destOffset + "(%rbp)");
            varIndexMap.put(arg, destOffset + "(%rbp)");
            break;
          case(6):
            out.printCode("movq %r9, " + destOffset + "(%rbp)");
            varIndexMap.put(arg, destOffset + "(%rbp)");
            break;
        }
      }
      argNum++;
    }

    //create a stack to keep track of instructions
    //create a set of visited instructions
    //if current instruction is in visited, jump using its label
    //Visit Current instruction on stack and push its children
    //stop when stack is empty

    //Use DFS Traversal to call visit instructions
    Stack<Instruction> instStack = new Stack<>();
    Set<Instruction> visited = new HashSet<Instruction>();

    instStack.push(f.getStart());
    Instruction current = f.getStart();
    while(!instStack.isEmpty()){
      current = instStack.pop();
      if(labelMap.containsKey(current) && !visited.contains(current)){
        String name = labelMap.get(current);
        if(mac) {
          out.printLabel("_" + name + ":");
        } else {
          out.printLabel(name + ":");
        }
      }
      if(visited.contains(current) && labelMap.containsKey(current)){
        //jmp to it using label
        String labelName = labelMap.get(current);
        if(mac) {
          out.printCode("jmp _" + labelName);
        } else {
          out.printCode("jmp " + labelName);
        }
      }
      if(!visited.contains(current)){
        //visit instructions
        current.accept(this);
        //place current instruction in visited set
        visited.add(current);

        //False cases are last because it should always be popped before
        //if numnext is 0, that means the function/program ends there so we leave and return
        //true cases
        if(current.numNext() == 2){
          instStack.push(current.getNext(1));
          instStack.push(current.getNext(0));
        } else if (current.numNext() == 1){
          instStack.push(current.getNext(0));
        } else if (current.numNext() == 0) {
          out.printCode("leave");
          out.printCode("ret");
        }
      }
    }

  }

  //pushes LocalVars into varIndexMap if it does not already exist
  //returns address of LocalVar
  private String mapGet (Variable v){
    if(!varIndexMap.containsKey(v)){
      varIndex++;
      varIndexMap.put(v, (-varIndex*8) + "(%rbp)");
    }
    return varIndexMap.get(v);
  }
  private void printInstructionInfo(Instruction i){
    var info = String.format("/* %s */", i.format(irFormat));
    out.printCode(info);
  }

  public void visit(AddressAt i) {
    printInstructionInfo(i);
    //Get address, place into varIndexMap with AddressVar
    if(mac){
      out.printCode("movq _" + i.getBase().getName() + "@GOTPCREL(%rip), %r11");
    }else{
      out.printCode("movq " + i.getBase().getName() + "@GOTPCREL(%rip), %r11");
    }
    //load offset into r10
    //offset can be null or localVar
    //Put final address in AddressVar destVar
    String destAddress = mapGet(i.getDst());
    if(i.getOffset() != null){
      String offsetAddress = mapGet(i.getOffset());
      out.printCode("movq " + offsetAddress + ", %r10" );
      out.printCode("imulq $8, %r10");
      out.printCode("addq %r10, %r11");
    }
    out.printCode("movq %r11, " + destAddress);

  }

  public void visit(BinaryOperator i) {
    printInstructionInfo(i);
    //make space for destination
    String destAddress = mapGet(i.getDst());
    BinaryOperator.Op opr = i.getOperator();
    //get lhs, rhs, dest
    String lhsAddress = varIndexMap.get(i.getLeftOperand());
    String rhsAddress = varIndexMap.get(i.getRightOperand());
    if(opr == BinaryOperator.Op.Add){
      out.printCode("movq " + lhsAddress + ", %r10");
      out.printCode("addq " + rhsAddress + ", %r10");
      out.printCode("movq %r10," + destAddress);
    }else if(opr == BinaryOperator.Op.Sub){
      //subtract source from dest
      //subtract rhs from lhs
      out.printCode("movq " + lhsAddress + ", %r10");
      out.printCode("subq " + rhsAddress + ", %r10");
      out.printCode("movq %r10," + destAddress);
    }else if(opr == BinaryOperator.Op.Mul){
      //multiply destination by source
      out.printCode("movq " + lhsAddress + ", %r10");
      out.printCode("imul " + rhsAddress + ", %r10");
      out.printCode("movq %r10," + destAddress);
    }else{
      //idvq
      out.printCode("movq " + lhsAddress + ", %rax");
      out.printCode("cqto");
      out.printCode("idivq " + rhsAddress);
      out.printCode("movq %rax, " + destAddress);
    }
  }

  public void visit(CompareInst i) {
    printInstructionInfo(i);
    String destAddress = mapGet(i.getDst());
    String leftAddress = mapGet(i.getLeftOperand());
    String rightAddress = mapGet(i.getRightOperand());


    out.printCode("movq $0, %rax");
    out.printCode("movq $1, %r10");
    out.printCode("movq " + leftAddress +", %r11");
    out.printCode("cmp " + rightAddress + ", %r11");

    CompareInst.Predicate pred = i.getPredicate();
    if(pred == CompareInst.Predicate.GE){
      out.printCode("cmovge %r10, %rax");
    }else if(pred == CompareInst.Predicate.GT){
      out.printCode("cmovg %r10, %rax");
    }else if(pred == CompareInst.Predicate.LE){
      out.printCode("cmovle %r10, %rax");
    }else if(pred == CompareInst.Predicate.LT){
      out.printCode("cmovl %r10, %rax");
    }else if(pred == CompareInst.Predicate.EQ){
      out.printCode("cmove %r10, %rax");
    }else if(pred == CompareInst.Predicate.NE){
      out.printCode("cmovne %r10, %rax");
    }

    out.printCode("movq %rax, " + destAddress);
    

  }

  public void visit(CopyInst i) {
    printInstructionInfo(i);
    //create a local temp var if var does not already exist
    String destAddress;
    destAddress = mapGet(i.getDstVar());
    // movq address to address is not allowed
    //therefore we use two movq
    String valString = "";
    //Values include constants and variables
    if(LocalVar.class == i.getSrcValue().getClass()){
      //variable
      String srcAddress = varIndexMap.get(i.getSrcValue());
      out.printCode("movq "+ srcAddress + ", %r10");
      out.printCode("movq %r10, "  + destAddress);
    }else{
      //constant
      if(i.getSrcValue().getType().getClass() == IntType.class){
        long val  = ((IntegerConstant)(i.getSrcValue())).getValue();
        valString = "$" + val;

      }else{
        boolean val  = ((BooleanConstant)(i.getSrcValue())).getValue();
        if(val == false){
          valString = "$0";
        }else{
          valString = "$1";
        }
      }
      out.printCode("movq " + valString + ", "+ destAddress);

    }

  }

  public void visit(JumpInst i) {
    printInstructionInfo(i);
    String predAddress = mapGet(i.getPredicate());
    out.printCode("movq " + predAddress + ", %r10");
    out.printCode("cmp $1, %r10");

    //get Label
    String thenLabel = labelMap.get(i.getNext(1));
    if(mac){
      out.printCode("je _" + thenLabel);
    }else{
      out.printCode("je " + thenLabel);
    }

    //accept else block
    i.getNext(0).accept(this);
    //accept then block
    i.getNext(1).accept(this);

  }

  public void visit(LoadInst i) {
    printInstructionInfo(i);
    String sourceAddress = mapGet(i.getSrcAddress());
    String destAddress = mapGet(i.getDst());
    out.printCode("movq " + sourceAddress + ", %r11");
    //load global value into r10
    out.printCode("movq 0(%r11), %r10");
    //place value in r10 into destination address
    out.printCode("movq %r10, " + destAddress);

  }

  public void visit(NopInst i) {}

  public void visit(StoreInst i) {
    //good thing to note, movq address -> reg : get value in address to reg
    // value in reg1 -> reg2 : replace reg2 with value in reg1
    // value in reg1 -> address in reg2 : replace reg2 with value in reg1
    printInstructionInfo(i);
    String sourceValueAddress = mapGet(i.getSrcValue());
    String destAddress = mapGet(i.getDestAddress());
    out.printCode("movq " + destAddress + ", %r11");
    //save destaddress into r10, the global address
    out.printCode("movq %r11, %r10");
    //source value is local so it looks like: -(offset)(%rbp), grab value and place into r11
    out.printCode("movq " + sourceValueAddress + ", %r11");
    //store value into global address
    out.printCode("movq %r11, 0(%r10)");
  }

  public void visit(ReturnInst i) {
    String localVarAddress = varIndexMap.get(i.getReturnValue());
    printInstructionInfo(i);
    out.printCode("movq " + localVarAddress + ", %rax" );
    out.printCode("leave");
    out.printCode("ret");
  }

  public void visit(CallInst i) {
    //print Call Instruction comment
    printInstructionInfo(i);
    //store parameters in designated registers or on stack
    int paramCount = 1;
    for(LocalVar param : i.getParams()){
      String varAddress = varIndexMap.get(param);
      if(paramCount > 6){
        //place argument on stack according to rsp
        //lookup address of local variables on stack
        int destOffset = (paramCount - 7)*8;
        out.printCode("movq " + varAddress + ", %r10");
        out.printCode("movq %r10, "+ destOffset + "(%rsp)");
      }else{
        switch(paramCount){
          case(1):
            out.printCode("movq " + varAddress + ", %rdi");
            break;
          case(2):
            out.printCode("movq " + varAddress + ", %rsi");
            break;
          case(3):
            out.printCode("movq " + varAddress + ", %rdx");
            break;
          case(4):
            out.printCode("movq " + varAddress + ", %rcx");
            break;
          case(5):
            out.printCode("movq " + varAddress + ", %r8");
            break;
          case(6):
            out.printCode("movq " + varAddress + ", %r9");
            break;
        }
      }
      paramCount++;
    }

    //Jump to function
    if(mac){
      out.printCode("call _" + i.getCallee().getName());
    }else{
      out.printCode("call " + i.getCallee().getName());
    }

//    //create new frame
//    out.printCode("movq 8(%rbp), %r10");
//    out.printCode("movq %r10, -8(%rsp)");
//    out.printCode("movq %rbp,-16(%rsp)");

    //Check if there is returned value
    Type ret = ((FuncType)(i.getCallee().getType())).getRet();
    if( ret.getClass() != VoidType.class){
      String destAddr;
      //grab return value from %rax and place into call destination
      destAddr = mapGet(i.getDst());
      out.printCode("movq %rax, " + destAddr);
    }


  }

  public void visit(UnaryNotInst i) {
    printInstructionInfo(i);
    String innerAddress = mapGet(i.getInner());
    String destAddress = mapGet(i.getDst());
    out.printCode("movq $1, %r11");
    out.printCode("movq " + innerAddress + ", %r10");
    out.printCode("subq %r10, %r11");
    out.printCode("movq %r10, " + destAddress);
  }
}
