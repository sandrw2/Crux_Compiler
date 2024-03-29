package crux.ast;

import crux.ast.*;
import crux.ast.OpExpr.Operation;
import crux.ir.insts.BinaryOperator;
import crux.pt.CruxBaseVisitor;
import crux.pt.CruxParser;
import crux.ast.types.*;
import crux.ast.SymbolTable.Symbol;
import org.antlr.v4.runtime.ParserRuleContext;

import javax.swing.plaf.nimbus.State;
import java.io.PrintStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class will convert the parse tree generated by ANTLR to AST It follows the visitor pattern
 * where declarations will be by DeclarationVisitor Class Statements will be resolved by
 * StatementVisitor Class Expressions will be resolved by ExpressionVisitor Class
 */

public final class ParseTreeLower {
  private final DeclarationVisitor declarationVisitor = new DeclarationVisitor();
  private final StatementVisitor statementVisitor = new StatementVisitor();
  private final ExpressionVisitor expressionVisitor = new ExpressionVisitor();

  private final SymbolTable symTab;

  public ParseTreeLower(PrintStream err) {
    symTab = new SymbolTable(err);
  }

  private static Position makePosition(ParserRuleContext ctx) {
    var start = ctx.start;
    return new Position(start.getLine());
  }

  /**
   *
   * @return True if any errors
   */
  public boolean hasEncounteredError() {
    return symTab.hasEncounteredError();
  }


  /**
   * Lower top-level parse tree to AST
   * 
   * @return a {@link DeclarationList} object representing the top-level AST.
   */

  public DeclarationList lower(CruxParser.ProgramContext program) {
    // DeclarationList(position, List<Declarations>)
    Position pos = makePosition(program);
    ArrayList<Declaration> list  = new ArrayList<Declaration>();
    //program.declarationList() --> DeclarationListContext
    // DeclarationListContext.declaration() --> List<declaration Context>
    // CHECK for variable, array, and function declaration
    for(CruxParser.DeclarationContext context : program.declarationList().declaration()){
      if(context.variableDeclaration() != null){
        list.add(context.variableDeclaration().accept(declarationVisitor));
      }else if(context.arrayDeclaration()!= null){
        list.add(context.arrayDeclaration().accept(declarationVisitor));
      }else{
        list.add(context.functionDefinition().accept(declarationVisitor));
      }
    }
    return new DeclarationList(pos, list);
  }

  /**
   * Lower statement list by lower individual statement into AST.
   * 
   * @return a {@link StatementList} AST object.
   */

   private StatementList lower(CruxParser.StatementListContext statementList) {
     //statementListContext --> StatementList(Position position, list<Statement> statements)
     Position pos = makePosition(statementList);
     ArrayList<Statement> list = new ArrayList<Statement>();
     for(CruxParser.StatementContext statementCtx : statementList.statement()){
       if(statementCtx.variableDeclaration() != null){
         list.add(statementCtx.variableDeclaration().accept(statementVisitor));
       }else if(statementCtx.callStatement()!=null){
         list.add(statementCtx.callStatement().accept(statementVisitor));
       }else if(statementCtx.assignmentStatement() != null){
         list.add(statementCtx.assignmentStatement().accept(statementVisitor));
       }else if(statementCtx.ifStatement() != null){
         list.add(statementCtx.ifStatement().accept(statementVisitor));
       }else if(statementCtx.forStatement() != null){
         list.add(statementCtx.forStatement().accept(statementVisitor));
       }else if(statementCtx.breakStatement() != null){
         list.add(statementCtx.breakStatement().accept(statementVisitor));
       }else{
         list.add(statementCtx.returnStatement().accept(statementVisitor));
       }
     }
     return new StatementList(pos, list);
   }

  /**
   * Similar to {@link #lower(CruxParser.StatementListContext)}, but handles symbol table as well.
   * 
   * @return a {@link StatementList} AST object.
   */

   private StatementList lower(CruxParser.StatementBlockContext statementBlock) {
     //StatementBlockContext --> StatementList(Position position, List<Statement> statements)
     symTab.enter();
     Position pos = makePosition(statementBlock);
     ArrayList<Statement> list = new ArrayList<Statement>();
     CruxParser.StatementListContext statementList = statementBlock.statementList();
     for(CruxParser.StatementContext statementCtx : statementList.statement()){
       if(statementCtx.variableDeclaration() != null){
         list.add(statementCtx.variableDeclaration().accept(statementVisitor));
       }else if(statementCtx.callStatement()!=null){
         list.add(statementCtx.callStatement().accept(statementVisitor));
       }else if(statementCtx.assignmentStatement() != null){
         list.add(statementCtx.assignmentStatement().accept(statementVisitor));
       }else if(statementCtx.ifStatement() != null){
         list.add(statementCtx.ifStatement().accept(statementVisitor));
       }else if(statementCtx.forStatement() != null){
         list.add(statementCtx.forStatement().accept(statementVisitor));
       }else if(statementCtx.breakStatement() != null){
         list.add(statementCtx.breakStatement().accept(statementVisitor));
       }else{
         list.add(statementCtx.returnStatement().accept(statementVisitor));
       }
     }
     symTab.exit();
     return new StatementList(pos, list);
   }

  /**
   * A parse tree visitor to create AST nodes derived from {@link Declaration}
   */
  private final class DeclarationVisitor extends CruxBaseVisitor<Declaration> {
    /**
     * Visit a parse tree variable declaration and create an AST {@link VariableDeclaration}
     * 
     * @return an AST {@link VariableDeclaration}
     */

    @Override
    public VariableDeclaration visitVariableDeclaration(CruxParser.VariableDeclarationContext ctx) {
      // MADE NEW SYMBOL
      // ADD SYMBOL IN SYMBOL TABLE
      // VariableDeclaration(Position, Symbol)
      // Symbol(Name, Type)
      Type typ = typeCtxToType(ctx.type());
      Symbol sym = symTab.add(makePosition(ctx), ctx.Identifier().getText(), typ);
      return new VariableDeclaration(makePosition(ctx), sym);
    }

    //***HELPER***
    public Type typeCtxToType(CruxParser.TypeContext typ){
      String typeStr = typ.getText();
      if(typeStr.equals("bool")){
        return new BoolType();
      } else if(typeStr.equals("void")) {
        return new VoidType();
      } else {
        return new IntType();
      }
    }

    /**
     * Visit a parse tree array declaration and creates an AST {@link ArrayDeclaration}
     * 
     * @return an AST {@link ArrayDeclaration}
     */

     @Override
     public Declaration visitArrayDeclaration(CruxParser.ArrayDeclarationContext ctx) {
       // ArrayDeclarationContext --> ArrayDeclaration(position, symbol)
       Type typ = typeCtxToType(ctx.type());
       int sz = Integer.parseInt(ctx.Integer().toString());
       Type arrTyp = new ArrayType(sz ,typ);
       Symbol sym = symTab.add(makePosition(ctx), ctx.Identifier().getText(), arrTyp);
       return new ArrayDeclaration(makePosition(ctx), sym);
     }


    /**
     * Visit a parse tree function definition and create an AST {@link FunctionDefinition}
     * 
     * @return an AST {@link FunctionDefinition}
     */
    @Override
    public Declaration visitFunctionDefinition(CruxParser.FunctionDefinitionContext ctx) {
      //get the type of the function
      CruxParser.TypeContext funcType = ctx.type();
      //get position of function for debug
      Position funcPos = makePosition(ctx);
      //get list of parameters in context form
      List<CruxParser.ParameterContext> funcParamCtxList = ctx.parameterList().parameter();
      //get list of statements in context form
      CruxParser.StatementListContext funcStatementListCtx = ctx.statementBlock().statementList();
      //get pos of statementList needed for debug
      Position statementListPosition = makePosition(ctx.statementBlock().statementList());
      //declare parameter, needed for functiondefinition
      List<Symbol> parameters = new ArrayList<Symbol>();
      TypeList argTypes = new TypeList();

      for (CruxParser.ParameterContext paramCtx : funcParamCtxList) {
        argTypes.append(typeCtxToType(paramCtx.type()));
      }

      Symbol funcSymbol = symTab.add(funcPos, ctx.Identifier().getText(), new FuncType(argTypes, typeCtxToType(funcType)));

      //enter new scope MAY BE AN ISSUE DOWN THE LINE
      symTab.enter();
      if(funcParamCtxList != null) {
        for (CruxParser.ParameterContext paramCtx : funcParamCtxList) {
          Symbol parameterSymbol = symTab.add(
                  makePosition(paramCtx), paramCtx.Identifier().getText(), typeCtxToType(paramCtx.type()));
          parameters.add(parameterSymbol);
        }
      }
      //let lower handle statementList
      StatementList statementList = lower(funcStatementListCtx);
      //finished processing, exit scope
      symTab.exit();

      //function definition return, StatementList is created here as well
      return new FunctionDefinition(funcPos, funcSymbol, parameters, statementList);
    }

  }


  /**
   * A parse tree visitor to create AST nodes derived from {@link Statement}
   */

  private final class StatementVisitor extends CruxBaseVisitor<Statement> {
    /**
     * Visit a parse tree variable declaration and create an AST {@link VariableDeclaration}. Since
     * {@link VariableDeclaration} is both {@link Declaration} and {@link Statement}, we simply
     * delegate this to
     * {@link DeclarationVisitor#visitArrayDeclaration(CruxParser.ArrayDeclarationContext)} which we
     * implement earlier.
     * 
     * @return an AST {@link VariableDeclaration}
     */

     @Override
     public Statement visitVariableDeclaration(CruxParser.VariableDeclarationContext ctx) {
       //VariableDeclarationContext --> variableDeclaration
       //We already did VariableDeclaration in DeclarationVisitor
       //VariableDeclaration visitVariableDeclaration(CruxParser.VariableDeclarationContext ctx)
       return (Statement) ctx.accept(declarationVisitor);

     }
    
    /**
     * Visit a parse tree assignment statement and create an AST {@link Assignment}
     * 
     * @return an AST {@link Assignment}
     */

     @Override
     public Statement visitAssignmentStatement(CruxParser.AssignmentStatementContext ctx) {
       //AssignmentStatementContext --> Assignment(Position position, Expression location, Expression value)
       Position pos  = makePosition(ctx);
       Expression lhs = ctx.designator().accept(expressionVisitor);
       Expression rhs = ctx.expression0().accept(expressionVisitor);
       return new Assignment(pos, lhs, rhs);
     }

    /**
     * Visit a parse tree assignment nosemi statement and create an AST {@link Assignment}
     * 
     * @return an AST {@link Assignment}
     */


     @Override
     public Statement visitAssignmentStatementNoSemi(CruxParser.AssignmentStatementNoSemiContext ctx) {
       //AssignmentStatementNoSemiContext --> Assignment(Position position, Expression location, Expression value)
       Position pos  = makePosition(ctx);
       Expression lhs = ctx.designator().accept(expressionVisitor);
       Expression rhs = ctx.expression0().accept(expressionVisitor);
       return new Assignment(pos, lhs, rhs);
     }


    /**
     * Visit a parse tree call statement and create an AST {@link Call}. Since {@link Call} is both
     * {@link Expression} and {@link Statement}, we simply delegate this to
     * {@link ExpressionVisitor#visitCallExpression(CruxParser.CallExpressionContext)} that we will
     * implement later.
     * 
     * @return an AST {@link Call}
     */


     @Override
     public Statement visitCallStatement(CruxParser.CallStatementContext ctx) {
       //CallStatementContext --> call
       return (Statement) ctx.callExpression().accept(expressionVisitor);
     }

    /**
     * Visit a parse tree if-else branch and create an AST {@link IfElseBranch}. The template code
     * shows partial implementations that visit the then block and else block recursively before
     * using those returned AST nodes to construct {@link IfElseBranch} object.
     * 
     * @return an AST {@link IfElseBranch}
     */

     @Override
     public Statement visitIfStatement(CruxParser.IfStatementContext ctx) {
       //IfStatementContext -->
       // IfElseBranch(Position pos, Expression cond, StatementList thenBlock, StatementList elseBlock)
       Position pos = makePosition(ctx);
       Expression cond = ctx.expression0().accept(expressionVisitor);
       StatementList thenBlock =  lower(ctx.statementBlock(0));

       if(ctx.Else() != null){
         StatementList elseBlock =  lower(ctx.statementBlock(1));
         return new IfElseBranch(pos, cond, thenBlock, elseBlock);

       }else{
         //Empty Else Block
         StatementList elseBlock = new StatementList(makePosition(ctx.statementBlock(0)),
                 new ArrayList<Statement>());

         return new IfElseBranch(pos, cond, thenBlock, elseBlock);
       }
     }

    /**
     * Visit a parse tree for loop and create an AST {@link For}. You'll going to use a similar
     * techniques as {@link #visitIfStatement(CruxParser.IfStatementContext)} to decompose this
     * construction.
     * 
     * @return an AST {@link Loop}
     */


     @Override
     public Statement visitForStatement(CruxParser.ForStatementContext ctx) {
       //** Used the same pos for all assignment statements here because they are on the same line
       //ForStatementCtx --> For(Position pos, Assignment init, Expression cond, Assignment increment,
       //StatementList body)
       Position pos = makePosition(ctx);
       Expression lhs= ctx.assignmentStatement().designator().accept(expressionVisitor);
       Expression rhs = ctx.assignmentStatement().expression0().accept(expressionVisitor);
       Assignment init = new Assignment(pos, lhs, rhs);

       Expression cond = ctx.expression0().accept(expressionVisitor);

       Expression lhsNoSemi = ctx.assignmentStatementNoSemi().designator().accept(expressionVisitor);;
       Expression rhsNoSemi = ctx.assignmentStatementNoSemi().expression0().accept(expressionVisitor);
       Assignment increment = new Assignment(pos, lhsNoSemi, rhsNoSemi);
       
       StatementList body =  lower(ctx.statementBlock());
       return new For(pos, init, cond, increment, body);
     }


    /**
     * Visit a parse tree return statement and create an AST {@link Return}. Here we show a simple
     * example of how to lower a simple parse tree construction.
     * 
     * @return an AST {@link Return}
     */

     @Override
     public Statement visitReturnStatement(CruxParser.ReturnStatementContext ctx) {
       //ReturnStatementContext --> Return(Position pos, Expression val)
       Position pos = makePosition(ctx);
       Expression val = ctx.expression0().accept(expressionVisitor);
       return new Return(pos, val);
     }

    /**
     * Creates a Break node
     */

     @Override
     public Statement visitBreakStatement(CruxParser.BreakStatementContext ctx) {
       Position pos = makePosition(ctx);
       return new Break(pos);
     }

  }

  private final class ExpressionVisitor extends CruxBaseVisitor<Expression> {
    /*
    *helper for determining expression enum
    */

    private Operation op0Converter(CruxParser.Op0Context ctx){
      if(ctx.GreaterEqual() != null){
        return Operation.GE;
      } else if (ctx.LesserEqual() != null) {
          return Operation.LE;
      }else if (ctx.NotEqual() != null){
        return  Operation.NE;
      } else if (ctx.Equal() != null){
        return Operation.EQ;
      } else if (ctx.GreaterThan() != null){
        return Operation.GT;
      } else {
        return Operation.LT;
      }
    }

    private Operation op1Converter(CruxParser.Op1Context ctx){
      if(ctx.Add()!= null){
        return Operation.ADD;
      } else if (ctx.Sub() != null) {
        return Operation.SUB;
      }else{
        return Operation.LOGIC_OR;
      }
    }

    private Operation op2Converter(CruxParser.Op2Context ctx){
      if(ctx.Mul()!= null){
        return Operation.MULT;
      } else if (ctx.Div() != null) {
        return Operation.DIV;
      }else{
        return Operation.LOGIC_AND;
      }
    }

    /**
     * Parse Expression0 to OpExpr Node Parsing the expression should be exactly as described in the
     * grammer
     */
     @Override
     public Expression visitExpression0(CruxParser.Expression0Context ctx) {
        if (ctx.op0() == null){
          //(op0 expression1 is not there) so only 1 expression0
          return ctx.expression1(0).accept(expressionVisitor);
        } else {
          //2 expression0s
          Expression lhs = ctx.expression1(0).accept(expressionVisitor);
          Expression rhs = ctx.expression1(1).accept(expressionVisitor);
          Operation op = op0Converter(ctx.op0());
          return new OpExpr(makePosition(ctx), op, lhs, rhs);
        }
     }


    /**
     * Parse Expression1 to OpExpr Node Parsing the expression should be exactly as described in the
     * grammer
     */


     @Override
     public Expression visitExpression1(CruxParser.Expression1Context ctx) {
       if (ctx.op1() == null){
         //in expression2 case
         return ctx.expression2().accept(expressionVisitor);
       } else {
         // in expression1 op1 expression2 case
         Expression lhs = ctx.expression1().accept(expressionVisitor);
         Expression rhs = ctx.expression2().accept(expressionVisitor);
         Operation op = op1Converter(ctx.op1());
         return new OpExpr(makePosition(ctx), op, lhs, rhs);
       }
     }

    /**
     * Parse Expression2 to OpExpr Node Parsing the expression should be exactly as described in the
     * grammer
     */

     @Override
     public Expression visitExpression2(CruxParser.Expression2Context ctx) {
       if(ctx.op2() == null){
         //in expression3 case
         return ctx.expression3().accept(expressionVisitor);
       } else {
         //in expression2 op2 expression 3 case
         Expression lhs = ctx.expression2().accept(expressionVisitor);
         Expression rhs = ctx.expression3().accept(expressionVisitor);
         Operation op = op2Converter(ctx.op2());
         return new OpExpr(makePosition(ctx), op, lhs, rhs);
       }
     }

    /**
     * Parse Expression3 to OpExpr Node Parsing the expression should be exactly as described in the
     * grammer
     */

     @Override
     public Expression visitExpression3(CruxParser.Expression3Context ctx) {
       if(ctx.expression0() != null) {
         return ctx.expression0().accept(expressionVisitor);
       } else if(ctx.literal() != null){
         return ctx.literal().accept(expressionVisitor);
       } else if(ctx.designator() != null){
         return ctx.designator().accept(expressionVisitor);
       } else if(ctx.callExpression() != null){
         return ctx.callExpression().accept(expressionVisitor);
       } else {
         // in Not expression3 case
         Expression expr3 = ctx.expression3().accept(expressionVisitor);
         return new OpExpr(makePosition(ctx), Operation.LOGIC_NOT, expr3, null);
       }
     }


    /**
     * Create an Call Node
     */

     @Override
     public Call visitCallExpression(CruxParser.CallExpressionContext ctx) {
       String name = ctx.Identifier().getText();
       Position callPosition = makePosition(ctx);
       //get symbol from symbol table
       Symbol symbol = symTab.lookup(callPosition, name);
       List<Expression> argumentList = new ArrayList<Expression>();
       if (ctx.expressionList().expression0() != null) {
         List<CruxParser.Expression0Context> expression0ContextList = ctx.expressionList().expression0();
         //create an empty list to store arguments
         //loop through expression0ContextList list and visit each to convert to Expressions
           for (CruxParser.Expression0Context expression0Context : expression0ContextList) {
             argumentList.add(expression0Context.accept(expressionVisitor));
           }
       }
       return new Call(callPosition, symbol, argumentList);

     }


    /**
     * visitDesignator will check for a name or ArrayAccess FYI it should account for the case when
     * the designator was dereferenced
     */

    @Override
    public Expression visitDesignator(CruxParser.DesignatorContext ctx) {
      Position designatorPos = makePosition(ctx);
      String name = ctx.Identifier().getText();
      Symbol symbol = symTab.lookup(designatorPos, name);
      if(ctx.expression0() == null){
        //just identifier/ name
        return new VarAccess(designatorPos, symbol);
      } else {
        //expression0/ array access
        Expression expr0 = ctx.expression0().accept(expressionVisitor);
        return new ArrayAccess(designatorPos, symbol, expr0);
      }
    }


    /**
     * Create an Literal Node
     */

    @Override
    public Expression visitLiteral(CruxParser.LiteralContext ctx) {
      Position literalPos = makePosition(ctx);
      if(ctx.Integer() != null){
        String number = ctx.Integer().getText();
        Integer i = new Integer(number);
        return new LiteralInt(literalPos, i);
      } else if (ctx.True() != null){
        return  new LiteralBool(literalPos, true);
      } else {
        return new LiteralBool(literalPos, false);
      }
    }

  }
}
