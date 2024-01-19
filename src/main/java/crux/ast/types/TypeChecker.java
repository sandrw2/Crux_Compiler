package crux.ast.types;

import crux.ast.SymbolTable.Symbol;
import crux.ast.*;
import crux.ast.traversal.NullNodeVisitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This class will associate types with the AST nodes from Stage 2
 */
public final class TypeChecker {
  private final ArrayList<String> errors = new ArrayList<>();

  public ArrayList<String> getErrors() {
    return errors;
  }

  public void check(DeclarationList ast) {
    var inferenceVisitor = new TypeInferenceVisitor();
    inferenceVisitor.visit(ast);
  }

  /**
   * Helper function, should be used to add error into the errors array
   */
  private void addTypeError(Node n, String message) {
    errors.add(String.format("TypeError%s[%s]", n.getPosition(), message));
  }

  /**
   * Helper function, should be used to record Types if the Type is an ErrorType then it will call
   * addTypeError
   */
  private void setNodeType(Node n, Type ty) {
    ((BaseNode) n).setType(ty);
    if (ty.getClass() == ErrorType.class) {
      var error = (ErrorType) ty;
      addTypeError(n, error.getMessage());
    }
  }

  /**
   * Helper to retrieve Type from the map
   */
  public Type getType(Node n) {
    return ((BaseNode) n).getType();
  }


  /**
   * This calls will visit each AST node and try to resolve it's type with the help of the
   * symbolTable.
   */
  private final class TypeInferenceVisitor extends NullNodeVisitor<Void> {
    boolean lastStatementReturns = false;

    Symbol currentFunctionSymbol;
    Type currentFunctionReturnType;
    @Override
    public Void visit(VarAccess vaccess) {
      //TODO
      //possible error here
      setNodeType(vaccess, vaccess.getSymbol().getType());
      return null;
    }

    @Override
    //Not sure what is wrong here
    public Void visit(ArrayDeclaration arrayDeclaration) {
      //TODO
      //might be an error here, base wording in slides
      if(((ArrayType)arrayDeclaration.getSymbol().getType()).getBase().getClass() != IntType.class && ((ArrayType)(arrayDeclaration.getSymbol().getType())).getBase().getClass() != BoolType.class){
        setNodeType(arrayDeclaration, new ErrorType("Array can only be declared as int or bool"));
      }
      //setNodeType(arrayDeclaration, arrayDeclaration.getSymbol().getType());
      lastStatementReturns = false;
      return null;
    }

    @Override
    public Void visit(Assignment assignment) {
      //TODO
      //assignment.getValue() type is null
      for(Node child : assignment.getChildren()){
        child.accept(this);
      }
      setNodeType(assignment, getType(assignment.getLocation()).assign(getType(assignment.getValue())));
      lastStatementReturns = false;
      return null;
    }

    @Override
    public Void visit(Break brk) {
      return null;
    }

    @Override
    public Void visit(Call call) {
      TypeList argTypes = new TypeList();
      for(Expression arg : call.getArguments()){
        arg.accept(this);
        argTypes.append(getType(arg));
      }
      setNodeType(call, call.getCallee().getType().call(argTypes));
      lastStatementReturns = false;
      return null;
    }

    @Override
    public Void visit(DeclarationList declarationList) {
      for(Node declaration_node : declarationList.getChildren()){
        declaration_node.accept(this);
      }
      return null;
    }

    @Override
    public Void visit(FunctionDefinition functionDefinition) {
      currentFunctionSymbol = functionDefinition.getSymbol();
      currentFunctionReturnType = ((FuncType)(currentFunctionSymbol.getType())).getRet();
      if(currentFunctionSymbol.getName().equals("main")){
        if(currentFunctionReturnType.getClass() != VoidType.class){
          setNodeType(functionDefinition, new ErrorType("main return type is non void"));
        } else if (functionDefinition.getParameters().size() != 0){
          setNodeType(functionDefinition, new ErrorType("main return type is non void"));
        }
      }
      //check if parameters are int type or bool type
      for(Symbol parameter : functionDefinition.getParameters()) {
        if(parameter.getType().getClass() != BoolType.class && parameter.getType().getClass() != IntType.class){
          setNodeType(functionDefinition, new ErrorType("main return type is non void"));
        }
      }
      functionDefinition.getStatements().accept(this);
      if(currentFunctionReturnType.getClass() == VoidType.class && lastStatementReturns){
        setNodeType(functionDefinition, new ErrorType("main return type is non void"));
      }
      //i feel like you would setNodeType here
      return null;
    }

    @Override
    public Void visit(IfElseBranch ifElseBranch) {
      boolean lastStatementReturnsThen = false;
      boolean lastStatementReturnsElse = false;
      boolean elseNotNull = false;
      ifElseBranch.getCondition().accept(this);
      if (getType(ifElseBranch.getCondition()).getClass() != BoolType.class){
        setNodeType(ifElseBranch, new ErrorType("condition statement does not resolve to bool type"));
      }
      ifElseBranch.getThenBlock().accept(this);
      lastStatementReturnsThen = lastStatementReturns;
      if(ifElseBranch.getElseBlock() != null){
        elseNotNull = true;
        ifElseBranch.getElseBlock().accept(this);
        lastStatementReturnsElse = lastStatementReturns;
      }
      if(lastStatementReturnsThen && lastStatementReturnsElse){
          lastStatementReturns = true;
      }
      return null;
    }

    @Override
    public Void visit(ArrayAccess access) {
      //TODO
      //possible error here
      access.getIndex().accept(this);
      setNodeType(access, ((ArrayType)(access.getBase().getType()))
              .index(getType(access.getIndex())));
      return null;
    }

    @Override
    public Void visit(LiteralBool literalBool) {
      setNodeType(literalBool, new BoolType());
      return null;
    }

    @Override
    public Void visit(LiteralInt literalInt) {
      setNodeType(literalInt, new IntType());
      return null;
    }

    @Override
    public Void visit(For forloop) {
      forloop.getInit().accept(this);
      forloop.getCond().accept(this);
      forloop.getIncrement().accept(this);
      forloop.getBody().accept(this);
      return null;
    }

    @Override
    public Void visit(OpExpr op) {
      if (op.getLeft() != null && op.getRight() != null) {
        op.getLeft().accept(this);
        op.getRight().accept(this);
        Type expressionType;
        if (op.getOp() == OpExpr.Operation.ADD) {
          expressionType = getType(op.getLeft()).add(getType(op.getRight()));
          setNodeType(op, expressionType);
        } else if (op.getOp() == OpExpr.Operation.DIV) {
          expressionType = getType(op.getLeft()).div(getType(op.getRight()));
          setNodeType(op, expressionType);
        } else if (op.getOp() == OpExpr.Operation.SUB) {
          expressionType = getType(op.getLeft()).sub(getType(op.getRight()));
          setNodeType(op, expressionType);
        } else if (op.getOp() == OpExpr.Operation.MULT) {
          expressionType = getType(op.getLeft()).mul(getType(op.getRight()));
          setNodeType(op, expressionType);
        } else if (op.getOp() == OpExpr.Operation.GE || op.getOp() == OpExpr.Operation.GT || op.getOp() == OpExpr.Operation.LT || op.getOp() == OpExpr.Operation.LE) {
          expressionType = getType(op.getLeft()).compare(getType(op.getRight()));
          setNodeType(op, expressionType);
        } else if (op.getOp() == OpExpr.Operation.LOGIC_AND) {
          expressionType = getType(op.getLeft()).and(getType(op.getRight()));
          setNodeType(op, expressionType);
        } else if (op.getOp() == OpExpr.Operation.LOGIC_OR) {
          expressionType = getType(op.getLeft()).or(getType(op.getRight()));
          setNodeType(op, expressionType);
        }else if (op.getOp() == OpExpr.Operation.EQ || op.getOp() == OpExpr.Operation.NE) {
          if(getType(op.getLeft()).equivalent(getType(op.getRight()))){
            expressionType = new BoolType();
          } else {
            expressionType = new ErrorType("invalid Expression");
          }
          setNodeType(op, expressionType);
        }
      } else if (op.getLeft() != null && op.getRight() == null) {
        op.getLeft().accept(this);
        Type expressionType = getType(op.getLeft()).not();
        setNodeType(op, expressionType);
      } else {
        setNodeType(op, new ErrorType("invalid Expression"));
      }
      return null;
    }

    @Override
    public Void visit(Return ret) {
      ret.getValue().accept(this);
      if(!(getType(ret.getValue())).equivalent(currentFunctionReturnType)){
        setNodeType(ret, new ErrorType("return statement does not match function return type"));
      }
      lastStatementReturns = true;
      return null;
    }

    @Override
    public Void visit(StatementList statementList) {
      for(Node statementList_node : statementList.getChildren()){
//        if(lastStatementReturns == true){
//          setNodeType(statementList_node, new ErrorType("Unreachable statement"));
//        }
        statementList_node.accept(this);
      }
      return null;
    }

    @Override
    public Void visit(VariableDeclaration variableDeclaration) {
      //TODO
      //possible error here
      Symbol sym = variableDeclaration.getSymbol();
      Type typ = sym.getType();
      if(!(typ.getClass() == IntType.class) && !(typ.getClass() == BoolType.class)){
        setNodeType(variableDeclaration, new ErrorType("Variable can only be declared as int or bool"));
      }
      //setNodeType(variableDeclaration, variableDeclaration.getSymbol().getType());
      lastStatementReturns = false;
      return null;
    }
  }
}
