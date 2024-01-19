// Generated from crux/pt/Crux.g4 by ANTLR 4.7.2
package crux.pt;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CruxParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		SemiColon=1, Integer=2, True=3, False=4, And=5, Or=6, Not=7, If=8, Else=9, 
		For=10, Break=11, Return=12, OpenParen=13, CloseParen=14, OpenBrace=15, 
		CloseBrace=16, OpenBracket=17, CloseBracket=18, Add=19, Sub=20, Mul=21, 
		Div=22, GreaterEqual=23, LesserEqual=24, NotEqual=25, Equal=26, GreaterThan=27, 
		LessThan=28, Assign=29, Comma=30, Identifier=31, WhiteSpaces=32, Comment=33;
	public static final int
		RULE_program = 0, RULE_declarationList = 1, RULE_declaration = 2, RULE_variableDeclaration = 3, 
		RULE_type = 4, RULE_literal = 5, RULE_designator = 6, RULE_op0 = 7, RULE_op1 = 8, 
		RULE_op2 = 9, RULE_expression0 = 10, RULE_expression1 = 11, RULE_expression2 = 12, 
		RULE_expression3 = 13, RULE_callExpression = 14, RULE_expressionList = 15, 
		RULE_parameter = 16, RULE_parameterList = 17, RULE_arrayDeclaration = 18, 
		RULE_functionDefinition = 19, RULE_assignmentStatement = 20, RULE_assignmentStatementNoSemi = 21, 
		RULE_callStatement = 22, RULE_ifStatement = 23, RULE_forStatement = 24, 
		RULE_breakStatement = 25, RULE_returnStatement = 26, RULE_statement = 27, 
		RULE_statementList = 28, RULE_statementBlock = 29;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "declarationList", "declaration", "variableDeclaration", "type", 
			"literal", "designator", "op0", "op1", "op2", "expression0", "expression1", 
			"expression2", "expression3", "callExpression", "expressionList", "parameter", 
			"parameterList", "arrayDeclaration", "functionDefinition", "assignmentStatement", 
			"assignmentStatementNoSemi", "callStatement", "ifStatement", "forStatement", 
			"breakStatement", "returnStatement", "statement", "statementList", "statementBlock"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "';'", null, "'true'", "'false'", "'&&'", "'||'", "'!'", "'if'", 
			"'else'", "'for'", "'break'", "'return'", "'('", "')'", "'{'", "'}'", 
			"'['", "']'", "'+'", "'-'", "'*'", "'/'", "'>='", "'<='", "'!='", "'=='", 
			"'>'", "'<'", "'='", "','"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "SemiColon", "Integer", "True", "False", "And", "Or", "Not", "If", 
			"Else", "For", "Break", "Return", "OpenParen", "CloseParen", "OpenBrace", 
			"CloseBrace", "OpenBracket", "CloseBracket", "Add", "Sub", "Mul", "Div", 
			"GreaterEqual", "LesserEqual", "NotEqual", "Equal", "GreaterThan", "LessThan", 
			"Assign", "Comma", "Identifier", "WhiteSpaces", "Comment"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Crux.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public CruxParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ProgramContext extends ParserRuleContext {
		public DeclarationListContext declarationList() {
			return getRuleContext(DeclarationListContext.class,0);
		}
		public TerminalNode EOF() { return getToken(CruxParser.EOF, 0); }
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).exitProgram(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CruxVisitor ) return ((CruxVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(60);
			declarationList();
			setState(61);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclarationListContext extends ParserRuleContext {
		public List<DeclarationContext> declaration() {
			return getRuleContexts(DeclarationContext.class);
		}
		public DeclarationContext declaration(int i) {
			return getRuleContext(DeclarationContext.class,i);
		}
		public DeclarationListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declarationList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).enterDeclarationList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).exitDeclarationList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CruxVisitor ) return ((CruxVisitor<? extends T>)visitor).visitDeclarationList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeclarationListContext declarationList() throws RecognitionException {
		DeclarationListContext _localctx = new DeclarationListContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_declarationList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(66);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Identifier) {
				{
				{
				setState(63);
				declaration();
				}
				}
				setState(68);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclarationContext extends ParserRuleContext {
		public VariableDeclarationContext variableDeclaration() {
			return getRuleContext(VariableDeclarationContext.class,0);
		}
		public ArrayDeclarationContext arrayDeclaration() {
			return getRuleContext(ArrayDeclarationContext.class,0);
		}
		public FunctionDefinitionContext functionDefinition() {
			return getRuleContext(FunctionDefinitionContext.class,0);
		}
		public DeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).enterDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).exitDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CruxVisitor ) return ((CruxVisitor<? extends T>)visitor).visitDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeclarationContext declaration() throws RecognitionException {
		DeclarationContext _localctx = new DeclarationContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_declaration);
		try {
			setState(72);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(69);
				variableDeclaration();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(70);
				arrayDeclaration();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(71);
				functionDefinition();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VariableDeclarationContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(CruxParser.Identifier, 0); }
		public TerminalNode SemiColon() { return getToken(CruxParser.SemiColon, 0); }
		public VariableDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).enterVariableDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).exitVariableDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CruxVisitor ) return ((CruxVisitor<? extends T>)visitor).visitVariableDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableDeclarationContext variableDeclaration() throws RecognitionException {
		VariableDeclarationContext _localctx = new VariableDeclarationContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_variableDeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(74);
			type();
			setState(75);
			match(Identifier);
			setState(76);
			match(SemiColon);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(CruxParser.Identifier, 0); }
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).exitType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CruxVisitor ) return ((CruxVisitor<? extends T>)visitor).visitType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_type);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(78);
			match(Identifier);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LiteralContext extends ParserRuleContext {
		public TerminalNode Integer() { return getToken(CruxParser.Integer, 0); }
		public TerminalNode True() { return getToken(CruxParser.True, 0); }
		public TerminalNode False() { return getToken(CruxParser.False, 0); }
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).enterLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).exitLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CruxVisitor ) return ((CruxVisitor<? extends T>)visitor).visitLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_literal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(80);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Integer) | (1L << True) | (1L << False))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DesignatorContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(CruxParser.Identifier, 0); }
		public TerminalNode OpenBracket() { return getToken(CruxParser.OpenBracket, 0); }
		public Expression0Context expression0() {
			return getRuleContext(Expression0Context.class,0);
		}
		public TerminalNode CloseBracket() { return getToken(CruxParser.CloseBracket, 0); }
		public DesignatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_designator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).enterDesignator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).exitDesignator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CruxVisitor ) return ((CruxVisitor<? extends T>)visitor).visitDesignator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DesignatorContext designator() throws RecognitionException {
		DesignatorContext _localctx = new DesignatorContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_designator);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(82);
			match(Identifier);
			setState(87);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				{
				setState(83);
				match(OpenBracket);
				setState(84);
				expression0();
				setState(85);
				match(CloseBracket);
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Op0Context extends ParserRuleContext {
		public TerminalNode GreaterEqual() { return getToken(CruxParser.GreaterEqual, 0); }
		public TerminalNode LesserEqual() { return getToken(CruxParser.LesserEqual, 0); }
		public TerminalNode NotEqual() { return getToken(CruxParser.NotEqual, 0); }
		public TerminalNode Equal() { return getToken(CruxParser.Equal, 0); }
		public TerminalNode GreaterThan() { return getToken(CruxParser.GreaterThan, 0); }
		public TerminalNode LessThan() { return getToken(CruxParser.LessThan, 0); }
		public Op0Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_op0; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).enterOp0(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).exitOp0(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CruxVisitor ) return ((CruxVisitor<? extends T>)visitor).visitOp0(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Op0Context op0() throws RecognitionException {
		Op0Context _localctx = new Op0Context(_ctx, getState());
		enterRule(_localctx, 14, RULE_op0);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(89);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << GreaterEqual) | (1L << LesserEqual) | (1L << NotEqual) | (1L << Equal) | (1L << GreaterThan) | (1L << LessThan))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Op1Context extends ParserRuleContext {
		public TerminalNode Add() { return getToken(CruxParser.Add, 0); }
		public TerminalNode Sub() { return getToken(CruxParser.Sub, 0); }
		public TerminalNode Or() { return getToken(CruxParser.Or, 0); }
		public Op1Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_op1; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).enterOp1(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).exitOp1(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CruxVisitor ) return ((CruxVisitor<? extends T>)visitor).visitOp1(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Op1Context op1() throws RecognitionException {
		Op1Context _localctx = new Op1Context(_ctx, getState());
		enterRule(_localctx, 16, RULE_op1);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(91);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Or) | (1L << Add) | (1L << Sub))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Op2Context extends ParserRuleContext {
		public TerminalNode Mul() { return getToken(CruxParser.Mul, 0); }
		public TerminalNode Div() { return getToken(CruxParser.Div, 0); }
		public TerminalNode And() { return getToken(CruxParser.And, 0); }
		public Op2Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_op2; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).enterOp2(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).exitOp2(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CruxVisitor ) return ((CruxVisitor<? extends T>)visitor).visitOp2(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Op2Context op2() throws RecognitionException {
		Op2Context _localctx = new Op2Context(_ctx, getState());
		enterRule(_localctx, 18, RULE_op2);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(93);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << And) | (1L << Mul) | (1L << Div))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Expression0Context extends ParserRuleContext {
		public List<Expression1Context> expression1() {
			return getRuleContexts(Expression1Context.class);
		}
		public Expression1Context expression1(int i) {
			return getRuleContext(Expression1Context.class,i);
		}
		public Op0Context op0() {
			return getRuleContext(Op0Context.class,0);
		}
		public Expression0Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression0; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).enterExpression0(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).exitExpression0(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CruxVisitor ) return ((CruxVisitor<? extends T>)visitor).visitExpression0(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Expression0Context expression0() throws RecognitionException {
		Expression0Context _localctx = new Expression0Context(_ctx, getState());
		enterRule(_localctx, 20, RULE_expression0);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(95);
			expression1(0);
			setState(99);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << GreaterEqual) | (1L << LesserEqual) | (1L << NotEqual) | (1L << Equal) | (1L << GreaterThan) | (1L << LessThan))) != 0)) {
				{
				setState(96);
				op0();
				setState(97);
				expression1(0);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Expression1Context extends ParserRuleContext {
		public Expression2Context expression2() {
			return getRuleContext(Expression2Context.class,0);
		}
		public Expression1Context expression1() {
			return getRuleContext(Expression1Context.class,0);
		}
		public Op1Context op1() {
			return getRuleContext(Op1Context.class,0);
		}
		public Expression1Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression1; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).enterExpression1(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).exitExpression1(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CruxVisitor ) return ((CruxVisitor<? extends T>)visitor).visitExpression1(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Expression1Context expression1() throws RecognitionException {
		return expression1(0);
	}

	private Expression1Context expression1(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		Expression1Context _localctx = new Expression1Context(_ctx, _parentState);
		Expression1Context _prevctx = _localctx;
		int _startState = 22;
		enterRecursionRule(_localctx, 22, RULE_expression1, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(102);
			expression2(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(110);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new Expression1Context(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_expression1);
					setState(104);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(105);
					op1();
					setState(106);
					expression2(0);
					}
					} 
				}
				setState(112);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class Expression2Context extends ParserRuleContext {
		public Expression3Context expression3() {
			return getRuleContext(Expression3Context.class,0);
		}
		public Expression2Context expression2() {
			return getRuleContext(Expression2Context.class,0);
		}
		public Op2Context op2() {
			return getRuleContext(Op2Context.class,0);
		}
		public Expression2Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression2; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).enterExpression2(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).exitExpression2(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CruxVisitor ) return ((CruxVisitor<? extends T>)visitor).visitExpression2(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Expression2Context expression2() throws RecognitionException {
		return expression2(0);
	}

	private Expression2Context expression2(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		Expression2Context _localctx = new Expression2Context(_ctx, _parentState);
		Expression2Context _prevctx = _localctx;
		int _startState = 24;
		enterRecursionRule(_localctx, 24, RULE_expression2, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(114);
			expression3();
			}
			_ctx.stop = _input.LT(-1);
			setState(122);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new Expression2Context(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_expression2);
					setState(116);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(117);
					op2();
					setState(118);
					expression3();
					}
					} 
				}
				setState(124);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class Expression3Context extends ParserRuleContext {
		public TerminalNode Not() { return getToken(CruxParser.Not, 0); }
		public Expression3Context expression3() {
			return getRuleContext(Expression3Context.class,0);
		}
		public TerminalNode OpenParen() { return getToken(CruxParser.OpenParen, 0); }
		public Expression0Context expression0() {
			return getRuleContext(Expression0Context.class,0);
		}
		public TerminalNode CloseParen() { return getToken(CruxParser.CloseParen, 0); }
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public DesignatorContext designator() {
			return getRuleContext(DesignatorContext.class,0);
		}
		public CallExpressionContext callExpression() {
			return getRuleContext(CallExpressionContext.class,0);
		}
		public Expression3Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression3; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).enterExpression3(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).exitExpression3(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CruxVisitor ) return ((CruxVisitor<? extends T>)visitor).visitExpression3(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Expression3Context expression3() throws RecognitionException {
		Expression3Context _localctx = new Expression3Context(_ctx, getState());
		enterRule(_localctx, 26, RULE_expression3);
		try {
			setState(134);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(125);
				match(Not);
				setState(126);
				expression3();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(127);
				match(OpenParen);
				setState(128);
				expression0();
				setState(129);
				match(CloseParen);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(131);
				literal();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(132);
				designator();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(133);
				callExpression();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CallExpressionContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(CruxParser.Identifier, 0); }
		public TerminalNode OpenParen() { return getToken(CruxParser.OpenParen, 0); }
		public ExpressionListContext expressionList() {
			return getRuleContext(ExpressionListContext.class,0);
		}
		public TerminalNode CloseParen() { return getToken(CruxParser.CloseParen, 0); }
		public CallExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_callExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).enterCallExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).exitCallExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CruxVisitor ) return ((CruxVisitor<? extends T>)visitor).visitCallExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CallExpressionContext callExpression() throws RecognitionException {
		CallExpressionContext _localctx = new CallExpressionContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_callExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(136);
			match(Identifier);
			setState(137);
			match(OpenParen);
			setState(138);
			expressionList();
			setState(139);
			match(CloseParen);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionListContext extends ParserRuleContext {
		public List<Expression0Context> expression0() {
			return getRuleContexts(Expression0Context.class);
		}
		public Expression0Context expression0(int i) {
			return getRuleContext(Expression0Context.class,i);
		}
		public List<TerminalNode> Comma() { return getTokens(CruxParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(CruxParser.Comma, i);
		}
		public ExpressionListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressionList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).enterExpressionList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).exitExpressionList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CruxVisitor ) return ((CruxVisitor<? extends T>)visitor).visitExpressionList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionListContext expressionList() throws RecognitionException {
		ExpressionListContext _localctx = new ExpressionListContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_expressionList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(149);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Integer) | (1L << True) | (1L << False) | (1L << Not) | (1L << OpenParen) | (1L << Identifier))) != 0)) {
				{
				setState(141);
				expression0();
				setState(146);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Comma) {
					{
					{
					setState(142);
					match(Comma);
					setState(143);
					expression0();
					}
					}
					setState(148);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParameterContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(CruxParser.Identifier, 0); }
		public ParameterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).enterParameter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).exitParameter(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CruxVisitor ) return ((CruxVisitor<? extends T>)visitor).visitParameter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParameterContext parameter() throws RecognitionException {
		ParameterContext _localctx = new ParameterContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_parameter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(151);
			type();
			setState(152);
			match(Identifier);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParameterListContext extends ParserRuleContext {
		public List<ParameterContext> parameter() {
			return getRuleContexts(ParameterContext.class);
		}
		public ParameterContext parameter(int i) {
			return getRuleContext(ParameterContext.class,i);
		}
		public List<TerminalNode> Comma() { return getTokens(CruxParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(CruxParser.Comma, i);
		}
		public ParameterListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameterList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).enterParameterList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).exitParameterList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CruxVisitor ) return ((CruxVisitor<? extends T>)visitor).visitParameterList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParameterListContext parameterList() throws RecognitionException {
		ParameterListContext _localctx = new ParameterListContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_parameterList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(162);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Identifier) {
				{
				setState(154);
				parameter();
				setState(159);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Comma) {
					{
					{
					setState(155);
					match(Comma);
					setState(156);
					parameter();
					}
					}
					setState(161);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArrayDeclarationContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(CruxParser.Identifier, 0); }
		public TerminalNode OpenBracket() { return getToken(CruxParser.OpenBracket, 0); }
		public TerminalNode Integer() { return getToken(CruxParser.Integer, 0); }
		public TerminalNode CloseBracket() { return getToken(CruxParser.CloseBracket, 0); }
		public TerminalNode SemiColon() { return getToken(CruxParser.SemiColon, 0); }
		public ArrayDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).enterArrayDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).exitArrayDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CruxVisitor ) return ((CruxVisitor<? extends T>)visitor).visitArrayDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayDeclarationContext arrayDeclaration() throws RecognitionException {
		ArrayDeclarationContext _localctx = new ArrayDeclarationContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_arrayDeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(164);
			type();
			setState(165);
			match(Identifier);
			setState(166);
			match(OpenBracket);
			setState(167);
			match(Integer);
			setState(168);
			match(CloseBracket);
			setState(169);
			match(SemiColon);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionDefinitionContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(CruxParser.Identifier, 0); }
		public TerminalNode OpenParen() { return getToken(CruxParser.OpenParen, 0); }
		public ParameterListContext parameterList() {
			return getRuleContext(ParameterListContext.class,0);
		}
		public TerminalNode CloseParen() { return getToken(CruxParser.CloseParen, 0); }
		public StatementBlockContext statementBlock() {
			return getRuleContext(StatementBlockContext.class,0);
		}
		public FunctionDefinitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionDefinition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).enterFunctionDefinition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).exitFunctionDefinition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CruxVisitor ) return ((CruxVisitor<? extends T>)visitor).visitFunctionDefinition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionDefinitionContext functionDefinition() throws RecognitionException {
		FunctionDefinitionContext _localctx = new FunctionDefinitionContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_functionDefinition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(171);
			type();
			setState(172);
			match(Identifier);
			setState(173);
			match(OpenParen);
			setState(174);
			parameterList();
			setState(175);
			match(CloseParen);
			setState(176);
			statementBlock();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AssignmentStatementContext extends ParserRuleContext {
		public DesignatorContext designator() {
			return getRuleContext(DesignatorContext.class,0);
		}
		public TerminalNode Assign() { return getToken(CruxParser.Assign, 0); }
		public Expression0Context expression0() {
			return getRuleContext(Expression0Context.class,0);
		}
		public TerminalNode SemiColon() { return getToken(CruxParser.SemiColon, 0); }
		public AssignmentStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignmentStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).enterAssignmentStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).exitAssignmentStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CruxVisitor ) return ((CruxVisitor<? extends T>)visitor).visitAssignmentStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignmentStatementContext assignmentStatement() throws RecognitionException {
		AssignmentStatementContext _localctx = new AssignmentStatementContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_assignmentStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(178);
			designator();
			setState(179);
			match(Assign);
			setState(180);
			expression0();
			setState(181);
			match(SemiColon);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AssignmentStatementNoSemiContext extends ParserRuleContext {
		public DesignatorContext designator() {
			return getRuleContext(DesignatorContext.class,0);
		}
		public TerminalNode Assign() { return getToken(CruxParser.Assign, 0); }
		public Expression0Context expression0() {
			return getRuleContext(Expression0Context.class,0);
		}
		public AssignmentStatementNoSemiContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignmentStatementNoSemi; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).enterAssignmentStatementNoSemi(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).exitAssignmentStatementNoSemi(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CruxVisitor ) return ((CruxVisitor<? extends T>)visitor).visitAssignmentStatementNoSemi(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignmentStatementNoSemiContext assignmentStatementNoSemi() throws RecognitionException {
		AssignmentStatementNoSemiContext _localctx = new AssignmentStatementNoSemiContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_assignmentStatementNoSemi);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(183);
			designator();
			setState(184);
			match(Assign);
			setState(185);
			expression0();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CallStatementContext extends ParserRuleContext {
		public CallExpressionContext callExpression() {
			return getRuleContext(CallExpressionContext.class,0);
		}
		public TerminalNode SemiColon() { return getToken(CruxParser.SemiColon, 0); }
		public CallStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_callStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).enterCallStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).exitCallStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CruxVisitor ) return ((CruxVisitor<? extends T>)visitor).visitCallStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CallStatementContext callStatement() throws RecognitionException {
		CallStatementContext _localctx = new CallStatementContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_callStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(187);
			callExpression();
			setState(188);
			match(SemiColon);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IfStatementContext extends ParserRuleContext {
		public TerminalNode If() { return getToken(CruxParser.If, 0); }
		public Expression0Context expression0() {
			return getRuleContext(Expression0Context.class,0);
		}
		public List<StatementBlockContext> statementBlock() {
			return getRuleContexts(StatementBlockContext.class);
		}
		public StatementBlockContext statementBlock(int i) {
			return getRuleContext(StatementBlockContext.class,i);
		}
		public TerminalNode Else() { return getToken(CruxParser.Else, 0); }
		public IfStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).enterIfStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).exitIfStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CruxVisitor ) return ((CruxVisitor<? extends T>)visitor).visitIfStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IfStatementContext ifStatement() throws RecognitionException {
		IfStatementContext _localctx = new IfStatementContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_ifStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(190);
			match(If);
			setState(191);
			expression0();
			setState(192);
			statementBlock();
			setState(195);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Else) {
				{
				setState(193);
				match(Else);
				setState(194);
				statementBlock();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ForStatementContext extends ParserRuleContext {
		public TerminalNode For() { return getToken(CruxParser.For, 0); }
		public TerminalNode OpenParen() { return getToken(CruxParser.OpenParen, 0); }
		public AssignmentStatementContext assignmentStatement() {
			return getRuleContext(AssignmentStatementContext.class,0);
		}
		public Expression0Context expression0() {
			return getRuleContext(Expression0Context.class,0);
		}
		public TerminalNode SemiColon() { return getToken(CruxParser.SemiColon, 0); }
		public AssignmentStatementNoSemiContext assignmentStatementNoSemi() {
			return getRuleContext(AssignmentStatementNoSemiContext.class,0);
		}
		public TerminalNode CloseParen() { return getToken(CruxParser.CloseParen, 0); }
		public StatementBlockContext statementBlock() {
			return getRuleContext(StatementBlockContext.class,0);
		}
		public ForStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).enterForStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).exitForStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CruxVisitor ) return ((CruxVisitor<? extends T>)visitor).visitForStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ForStatementContext forStatement() throws RecognitionException {
		ForStatementContext _localctx = new ForStatementContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_forStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(197);
			match(For);
			setState(198);
			match(OpenParen);
			setState(199);
			assignmentStatement();
			setState(200);
			expression0();
			setState(201);
			match(SemiColon);
			setState(202);
			assignmentStatementNoSemi();
			setState(203);
			match(CloseParen);
			setState(204);
			statementBlock();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BreakStatementContext extends ParserRuleContext {
		public TerminalNode Break() { return getToken(CruxParser.Break, 0); }
		public TerminalNode SemiColon() { return getToken(CruxParser.SemiColon, 0); }
		public BreakStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_breakStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).enterBreakStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).exitBreakStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CruxVisitor ) return ((CruxVisitor<? extends T>)visitor).visitBreakStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BreakStatementContext breakStatement() throws RecognitionException {
		BreakStatementContext _localctx = new BreakStatementContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_breakStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(206);
			match(Break);
			setState(207);
			match(SemiColon);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ReturnStatementContext extends ParserRuleContext {
		public TerminalNode Return() { return getToken(CruxParser.Return, 0); }
		public Expression0Context expression0() {
			return getRuleContext(Expression0Context.class,0);
		}
		public TerminalNode SemiColon() { return getToken(CruxParser.SemiColon, 0); }
		public ReturnStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_returnStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).enterReturnStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).exitReturnStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CruxVisitor ) return ((CruxVisitor<? extends T>)visitor).visitReturnStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReturnStatementContext returnStatement() throws RecognitionException {
		ReturnStatementContext _localctx = new ReturnStatementContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_returnStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(209);
			match(Return);
			setState(210);
			expression0();
			setState(211);
			match(SemiColon);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementContext extends ParserRuleContext {
		public VariableDeclarationContext variableDeclaration() {
			return getRuleContext(VariableDeclarationContext.class,0);
		}
		public CallStatementContext callStatement() {
			return getRuleContext(CallStatementContext.class,0);
		}
		public AssignmentStatementContext assignmentStatement() {
			return getRuleContext(AssignmentStatementContext.class,0);
		}
		public IfStatementContext ifStatement() {
			return getRuleContext(IfStatementContext.class,0);
		}
		public ForStatementContext forStatement() {
			return getRuleContext(ForStatementContext.class,0);
		}
		public BreakStatementContext breakStatement() {
			return getRuleContext(BreakStatementContext.class,0);
		}
		public ReturnStatementContext returnStatement() {
			return getRuleContext(ReturnStatementContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).exitStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CruxVisitor ) return ((CruxVisitor<? extends T>)visitor).visitStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_statement);
		try {
			setState(220);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(213);
				variableDeclaration();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(214);
				callStatement();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(215);
				assignmentStatement();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(216);
				ifStatement();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(217);
				forStatement();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(218);
				breakStatement();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(219);
				returnStatement();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementListContext extends ParserRuleContext {
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public StatementListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statementList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).enterStatementList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).exitStatementList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CruxVisitor ) return ((CruxVisitor<? extends T>)visitor).visitStatementList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementListContext statementList() throws RecognitionException {
		StatementListContext _localctx = new StatementListContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_statementList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(225);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << If) | (1L << For) | (1L << Break) | (1L << Return) | (1L << Identifier))) != 0)) {
				{
				{
				setState(222);
				statement();
				}
				}
				setState(227);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementBlockContext extends ParserRuleContext {
		public TerminalNode OpenBrace() { return getToken(CruxParser.OpenBrace, 0); }
		public StatementListContext statementList() {
			return getRuleContext(StatementListContext.class,0);
		}
		public TerminalNode CloseBrace() { return getToken(CruxParser.CloseBrace, 0); }
		public StatementBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statementBlock; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).enterStatementBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).exitStatementBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CruxVisitor ) return ((CruxVisitor<? extends T>)visitor).visitStatementBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementBlockContext statementBlock() throws RecognitionException {
		StatementBlockContext _localctx = new StatementBlockContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_statementBlock);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(228);
			match(OpenBrace);
			setState(229);
			statementList();
			setState(230);
			match(CloseBrace);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 11:
			return expression1_sempred((Expression1Context)_localctx, predIndex);
		case 12:
			return expression2_sempred((Expression2Context)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression1_sempred(Expression1Context _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean expression2_sempred(Expression2Context _localctx, int predIndex) {
		switch (predIndex) {
		case 1:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3#\u00eb\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\3\2\3\2\3"+
		"\2\3\3\7\3C\n\3\f\3\16\3F\13\3\3\4\3\4\3\4\5\4K\n\4\3\5\3\5\3\5\3\5\3"+
		"\6\3\6\3\7\3\7\3\b\3\b\3\b\3\b\3\b\5\bZ\n\b\3\t\3\t\3\n\3\n\3\13\3\13"+
		"\3\f\3\f\3\f\3\f\5\ff\n\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\7\ro\n\r\f\r\16"+
		"\rr\13\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\7\16{\n\16\f\16\16\16~\13"+
		"\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\5\17\u0089\n\17\3\20"+
		"\3\20\3\20\3\20\3\20\3\21\3\21\3\21\7\21\u0093\n\21\f\21\16\21\u0096\13"+
		"\21\5\21\u0098\n\21\3\22\3\22\3\22\3\23\3\23\3\23\7\23\u00a0\n\23\f\23"+
		"\16\23\u00a3\13\23\5\23\u00a5\n\23\3\24\3\24\3\24\3\24\3\24\3\24\3\24"+
		"\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3\27\3\27"+
		"\3\27\3\27\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31\5\31\u00c6\n\31\3\32"+
		"\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\34\3\34\3\34"+
		"\3\34\3\35\3\35\3\35\3\35\3\35\3\35\3\35\5\35\u00df\n\35\3\36\7\36\u00e2"+
		"\n\36\f\36\16\36\u00e5\13\36\3\37\3\37\3\37\3\37\3\37\2\4\30\32 \2\4\6"+
		"\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:<\2\6\3\2\4\6"+
		"\3\2\31\36\4\2\b\b\25\26\4\2\7\7\27\30\2\u00e3\2>\3\2\2\2\4D\3\2\2\2\6"+
		"J\3\2\2\2\bL\3\2\2\2\nP\3\2\2\2\fR\3\2\2\2\16T\3\2\2\2\20[\3\2\2\2\22"+
		"]\3\2\2\2\24_\3\2\2\2\26a\3\2\2\2\30g\3\2\2\2\32s\3\2\2\2\34\u0088\3\2"+
		"\2\2\36\u008a\3\2\2\2 \u0097\3\2\2\2\"\u0099\3\2\2\2$\u00a4\3\2\2\2&\u00a6"+
		"\3\2\2\2(\u00ad\3\2\2\2*\u00b4\3\2\2\2,\u00b9\3\2\2\2.\u00bd\3\2\2\2\60"+
		"\u00c0\3\2\2\2\62\u00c7\3\2\2\2\64\u00d0\3\2\2\2\66\u00d3\3\2\2\28\u00de"+
		"\3\2\2\2:\u00e3\3\2\2\2<\u00e6\3\2\2\2>?\5\4\3\2?@\7\2\2\3@\3\3\2\2\2"+
		"AC\5\6\4\2BA\3\2\2\2CF\3\2\2\2DB\3\2\2\2DE\3\2\2\2E\5\3\2\2\2FD\3\2\2"+
		"\2GK\5\b\5\2HK\5&\24\2IK\5(\25\2JG\3\2\2\2JH\3\2\2\2JI\3\2\2\2K\7\3\2"+
		"\2\2LM\5\n\6\2MN\7!\2\2NO\7\3\2\2O\t\3\2\2\2PQ\7!\2\2Q\13\3\2\2\2RS\t"+
		"\2\2\2S\r\3\2\2\2TY\7!\2\2UV\7\23\2\2VW\5\26\f\2WX\7\24\2\2XZ\3\2\2\2"+
		"YU\3\2\2\2YZ\3\2\2\2Z\17\3\2\2\2[\\\t\3\2\2\\\21\3\2\2\2]^\t\4\2\2^\23"+
		"\3\2\2\2_`\t\5\2\2`\25\3\2\2\2ae\5\30\r\2bc\5\20\t\2cd\5\30\r\2df\3\2"+
		"\2\2eb\3\2\2\2ef\3\2\2\2f\27\3\2\2\2gh\b\r\1\2hi\5\32\16\2ip\3\2\2\2j"+
		"k\f\3\2\2kl\5\22\n\2lm\5\32\16\2mo\3\2\2\2nj\3\2\2\2or\3\2\2\2pn\3\2\2"+
		"\2pq\3\2\2\2q\31\3\2\2\2rp\3\2\2\2st\b\16\1\2tu\5\34\17\2u|\3\2\2\2vw"+
		"\f\3\2\2wx\5\24\13\2xy\5\34\17\2y{\3\2\2\2zv\3\2\2\2{~\3\2\2\2|z\3\2\2"+
		"\2|}\3\2\2\2}\33\3\2\2\2~|\3\2\2\2\177\u0080\7\t\2\2\u0080\u0089\5\34"+
		"\17\2\u0081\u0082\7\17\2\2\u0082\u0083\5\26\f\2\u0083\u0084\7\20\2\2\u0084"+
		"\u0089\3\2\2\2\u0085\u0089\5\f\7\2\u0086\u0089\5\16\b\2\u0087\u0089\5"+
		"\36\20\2\u0088\177\3\2\2\2\u0088\u0081\3\2\2\2\u0088\u0085\3\2\2\2\u0088"+
		"\u0086\3\2\2\2\u0088\u0087\3\2\2\2\u0089\35\3\2\2\2\u008a\u008b\7!\2\2"+
		"\u008b\u008c\7\17\2\2\u008c\u008d\5 \21\2\u008d\u008e\7\20\2\2\u008e\37"+
		"\3\2\2\2\u008f\u0094\5\26\f\2\u0090\u0091\7 \2\2\u0091\u0093\5\26\f\2"+
		"\u0092\u0090\3\2\2\2\u0093\u0096\3\2\2\2\u0094\u0092\3\2\2\2\u0094\u0095"+
		"\3\2\2\2\u0095\u0098\3\2\2\2\u0096\u0094\3\2\2\2\u0097\u008f\3\2\2\2\u0097"+
		"\u0098\3\2\2\2\u0098!\3\2\2\2\u0099\u009a\5\n\6\2\u009a\u009b\7!\2\2\u009b"+
		"#\3\2\2\2\u009c\u00a1\5\"\22\2\u009d\u009e\7 \2\2\u009e\u00a0\5\"\22\2"+
		"\u009f\u009d\3\2\2\2\u00a0\u00a3\3\2\2\2\u00a1\u009f\3\2\2\2\u00a1\u00a2"+
		"\3\2\2\2\u00a2\u00a5\3\2\2\2\u00a3\u00a1\3\2\2\2\u00a4\u009c\3\2\2\2\u00a4"+
		"\u00a5\3\2\2\2\u00a5%\3\2\2\2\u00a6\u00a7\5\n\6\2\u00a7\u00a8\7!\2\2\u00a8"+
		"\u00a9\7\23\2\2\u00a9\u00aa\7\4\2\2\u00aa\u00ab\7\24\2\2\u00ab\u00ac\7"+
		"\3\2\2\u00ac\'\3\2\2\2\u00ad\u00ae\5\n\6\2\u00ae\u00af\7!\2\2\u00af\u00b0"+
		"\7\17\2\2\u00b0\u00b1\5$\23\2\u00b1\u00b2\7\20\2\2\u00b2\u00b3\5<\37\2"+
		"\u00b3)\3\2\2\2\u00b4\u00b5\5\16\b\2\u00b5\u00b6\7\37\2\2\u00b6\u00b7"+
		"\5\26\f\2\u00b7\u00b8\7\3\2\2\u00b8+\3\2\2\2\u00b9\u00ba\5\16\b\2\u00ba"+
		"\u00bb\7\37\2\2\u00bb\u00bc\5\26\f\2\u00bc-\3\2\2\2\u00bd\u00be\5\36\20"+
		"\2\u00be\u00bf\7\3\2\2\u00bf/\3\2\2\2\u00c0\u00c1\7\n\2\2\u00c1\u00c2"+
		"\5\26\f\2\u00c2\u00c5\5<\37\2\u00c3\u00c4\7\13\2\2\u00c4\u00c6\5<\37\2"+
		"\u00c5\u00c3\3\2\2\2\u00c5\u00c6\3\2\2\2\u00c6\61\3\2\2\2\u00c7\u00c8"+
		"\7\f\2\2\u00c8\u00c9\7\17\2\2\u00c9\u00ca\5*\26\2\u00ca\u00cb\5\26\f\2"+
		"\u00cb\u00cc\7\3\2\2\u00cc\u00cd\5,\27\2\u00cd\u00ce\7\20\2\2\u00ce\u00cf"+
		"\5<\37\2\u00cf\63\3\2\2\2\u00d0\u00d1\7\r\2\2\u00d1\u00d2\7\3\2\2\u00d2"+
		"\65\3\2\2\2\u00d3\u00d4\7\16\2\2\u00d4\u00d5\5\26\f\2\u00d5\u00d6\7\3"+
		"\2\2\u00d6\67\3\2\2\2\u00d7\u00df\5\b\5\2\u00d8\u00df\5.\30\2\u00d9\u00df"+
		"\5*\26\2\u00da\u00df\5\60\31\2\u00db\u00df\5\62\32\2\u00dc\u00df\5\64"+
		"\33\2\u00dd\u00df\5\66\34\2\u00de\u00d7\3\2\2\2\u00de\u00d8\3\2\2\2\u00de"+
		"\u00d9\3\2\2\2\u00de\u00da\3\2\2\2\u00de\u00db\3\2\2\2\u00de\u00dc\3\2"+
		"\2\2\u00de\u00dd\3\2\2\2\u00df9\3\2\2\2\u00e0\u00e2\58\35\2\u00e1\u00e0"+
		"\3\2\2\2\u00e2\u00e5\3\2\2\2\u00e3\u00e1\3\2\2\2\u00e3\u00e4\3\2\2\2\u00e4"+
		";\3\2\2\2\u00e5\u00e3\3\2\2\2\u00e6\u00e7\7\21\2\2\u00e7\u00e8\5:\36\2"+
		"\u00e8\u00e9\7\22\2\2\u00e9=\3\2\2\2\20DJYep|\u0088\u0094\u0097\u00a1"+
		"\u00a4\u00c5\u00de\u00e3";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}