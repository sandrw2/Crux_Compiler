// Generated from crux/pt/Crux.g4 by ANTLR 4.7.2
package crux.pt;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CruxLexer extends Lexer {
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
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"SemiColon", "Integer", "True", "False", "And", "Or", "Not", "If", "Else", 
			"For", "Break", "Return", "OpenParen", "CloseParen", "OpenBrace", "CloseBrace", 
			"OpenBracket", "CloseBracket", "Add", "Sub", "Mul", "Div", "GreaterEqual", 
			"LesserEqual", "NotEqual", "Equal", "GreaterThan", "LessThan", "Assign", 
			"Comma", "Identifier", "WhiteSpaces", "Comment"
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


	public CruxLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Crux.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2#\u00be\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\3\2\3\2\3\3\3\3\3\3\7\3K\n\3\f\3\16\3N\13\3\5\3P\n\3\3\4\3"+
		"\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\7\3\7\3\7\3\b\3\b"+
		"\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3"+
		"\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3\21"+
		"\3\21\3\22\3\22\3\23\3\23\3\24\3\24\3\25\3\25\3\26\3\26\3\27\3\27\3\30"+
		"\3\30\3\30\3\31\3\31\3\31\3\32\3\32\3\32\3\33\3\33\3\33\3\34\3\34\3\35"+
		"\3\35\3\36\3\36\3\37\3\37\3 \3 \7 \u00a8\n \f \16 \u00ab\13 \3!\6!\u00ae"+
		"\n!\r!\16!\u00af\3!\3!\3\"\3\"\3\"\3\"\7\"\u00b8\n\"\f\"\16\"\u00bb\13"+
		"\"\3\"\3\"\2\2#\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31"+
		"\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65"+
		"\34\67\359\36;\37= ?!A\"C#\3\2\b\3\2\63;\3\2\62;\4\2C\\c|\6\2\62;C\\a"+
		"ac|\5\2\13\f\17\17\"\"\4\2\f\f\17\17\2\u00c2\2\3\3\2\2\2\2\5\3\2\2\2\2"+
		"\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2"+
		"\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2"+
		"\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2"+
		"\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2"+
		"\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2"+
		"\2\2A\3\2\2\2\2C\3\2\2\2\3E\3\2\2\2\5O\3\2\2\2\7Q\3\2\2\2\tV\3\2\2\2\13"+
		"\\\3\2\2\2\r_\3\2\2\2\17b\3\2\2\2\21d\3\2\2\2\23g\3\2\2\2\25l\3\2\2\2"+
		"\27p\3\2\2\2\31v\3\2\2\2\33}\3\2\2\2\35\177\3\2\2\2\37\u0081\3\2\2\2!"+
		"\u0083\3\2\2\2#\u0085\3\2\2\2%\u0087\3\2\2\2\'\u0089\3\2\2\2)\u008b\3"+
		"\2\2\2+\u008d\3\2\2\2-\u008f\3\2\2\2/\u0091\3\2\2\2\61\u0094\3\2\2\2\63"+
		"\u0097\3\2\2\2\65\u009a\3\2\2\2\67\u009d\3\2\2\29\u009f\3\2\2\2;\u00a1"+
		"\3\2\2\2=\u00a3\3\2\2\2?\u00a5\3\2\2\2A\u00ad\3\2\2\2C\u00b3\3\2\2\2E"+
		"F\7=\2\2F\4\3\2\2\2GP\7\62\2\2HL\t\2\2\2IK\t\3\2\2JI\3\2\2\2KN\3\2\2\2"+
		"LJ\3\2\2\2LM\3\2\2\2MP\3\2\2\2NL\3\2\2\2OG\3\2\2\2OH\3\2\2\2P\6\3\2\2"+
		"\2QR\7v\2\2RS\7t\2\2ST\7w\2\2TU\7g\2\2U\b\3\2\2\2VW\7h\2\2WX\7c\2\2XY"+
		"\7n\2\2YZ\7u\2\2Z[\7g\2\2[\n\3\2\2\2\\]\7(\2\2]^\7(\2\2^\f\3\2\2\2_`\7"+
		"~\2\2`a\7~\2\2a\16\3\2\2\2bc\7#\2\2c\20\3\2\2\2de\7k\2\2ef\7h\2\2f\22"+
		"\3\2\2\2gh\7g\2\2hi\7n\2\2ij\7u\2\2jk\7g\2\2k\24\3\2\2\2lm\7h\2\2mn\7"+
		"q\2\2no\7t\2\2o\26\3\2\2\2pq\7d\2\2qr\7t\2\2rs\7g\2\2st\7c\2\2tu\7m\2"+
		"\2u\30\3\2\2\2vw\7t\2\2wx\7g\2\2xy\7v\2\2yz\7w\2\2z{\7t\2\2{|\7p\2\2|"+
		"\32\3\2\2\2}~\7*\2\2~\34\3\2\2\2\177\u0080\7+\2\2\u0080\36\3\2\2\2\u0081"+
		"\u0082\7}\2\2\u0082 \3\2\2\2\u0083\u0084\7\177\2\2\u0084\"\3\2\2\2\u0085"+
		"\u0086\7]\2\2\u0086$\3\2\2\2\u0087\u0088\7_\2\2\u0088&\3\2\2\2\u0089\u008a"+
		"\7-\2\2\u008a(\3\2\2\2\u008b\u008c\7/\2\2\u008c*\3\2\2\2\u008d\u008e\7"+
		",\2\2\u008e,\3\2\2\2\u008f\u0090\7\61\2\2\u0090.\3\2\2\2\u0091\u0092\7"+
		"@\2\2\u0092\u0093\7?\2\2\u0093\60\3\2\2\2\u0094\u0095\7>\2\2\u0095\u0096"+
		"\7?\2\2\u0096\62\3\2\2\2\u0097\u0098\7#\2\2\u0098\u0099\7?\2\2\u0099\64"+
		"\3\2\2\2\u009a\u009b\7?\2\2\u009b\u009c\7?\2\2\u009c\66\3\2\2\2\u009d"+
		"\u009e\7@\2\2\u009e8\3\2\2\2\u009f\u00a0\7>\2\2\u00a0:\3\2\2\2\u00a1\u00a2"+
		"\7?\2\2\u00a2<\3\2\2\2\u00a3\u00a4\7.\2\2\u00a4>\3\2\2\2\u00a5\u00a9\t"+
		"\4\2\2\u00a6\u00a8\t\5\2\2\u00a7\u00a6\3\2\2\2\u00a8\u00ab\3\2\2\2\u00a9"+
		"\u00a7\3\2\2\2\u00a9\u00aa\3\2\2\2\u00aa@\3\2\2\2\u00ab\u00a9\3\2\2\2"+
		"\u00ac\u00ae\t\6\2\2\u00ad\u00ac\3\2\2\2\u00ae\u00af\3\2\2\2\u00af\u00ad"+
		"\3\2\2\2\u00af\u00b0\3\2\2\2\u00b0\u00b1\3\2\2\2\u00b1\u00b2\b!\2\2\u00b2"+
		"B\3\2\2\2\u00b3\u00b4\7\61\2\2\u00b4\u00b5\7\61\2\2\u00b5\u00b9\3\2\2"+
		"\2\u00b6\u00b8\n\7\2\2\u00b7\u00b6\3\2\2\2\u00b8\u00bb\3\2\2\2\u00b9\u00b7"+
		"\3\2\2\2\u00b9\u00ba\3\2\2\2\u00ba\u00bc\3\2\2\2\u00bb\u00b9\3\2\2\2\u00bc"+
		"\u00bd\b\"\2\2\u00bdD\3\2\2\2\b\2LO\u00a9\u00af\u00b9\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}