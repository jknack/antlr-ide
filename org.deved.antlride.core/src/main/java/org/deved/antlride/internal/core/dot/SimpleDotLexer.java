// $ANTLR 3.1.3 Mar 17, 2009 19:23:44 /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g 2009-06-16 20:35:21

package org.deved.antlride.internal.core.dot;

import org.antlr.runtime.BaseRecognizer;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.DFA;
import org.antlr.runtime.EarlyExitException;
import org.antlr.runtime.IntStream;
import org.antlr.runtime.Lexer;
import org.antlr.runtime.MismatchedSetException;
import org.antlr.runtime.NoViableAltException;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.RecognizerSharedState;

public class SimpleDotLexer extends Lexer {
	public static final int T__28 = 28;
	public static final int HTMLSTR = 10;
	public static final int T__23 = 23;
	public static final int T__20 = 20;
	public static final int WS = 14;
	public static final int NUMBER = 8;
	public static final int ALPHACHAR = 11;
	public static final int T__21 = 21;
	public static final int T__19 = 19;
	public static final int T__22 = 22;
	public static final int ESC = 13;
	public static final int EDGEOP_LITERAL = 5;
	public static final int T__29 = 29;
	public static final int T__30 = 30;
	public static final int T__17 = 17;
	public static final int EOF = -1;
	public static final int T__27 = 27;
	public static final int STRING_LITERAL = 9;
	public static final int T__24 = 24;
	public static final int VALIDSTR = 7;
	public static final int T__26 = 26;
	public static final int COMPASS_PT = 6;
	public static final int T__25 = 25;
	public static final int ML_COMMENT = 16;
	public static final int SL_COMMENT = 15;
	public static final int LITERAL_CHAR = 12;
	public static final int T__18 = 18;
	public static final int ID = 4;

	// delegates
	// delegators

	public SimpleDotLexer() {
		;
	}

	public SimpleDotLexer(CharStream input) {
		this(input, new RecognizerSharedState());
	}

	public SimpleDotLexer(CharStream input, RecognizerSharedState state) {
		super(input, state);

	}

	public String getGrammarFileName() {
		return "/media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g";
	}

	// $ANTLR start "T__17"
	public final void mT__17() throws RecognitionException {
		try {
			int _type = T__17;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:11:7:
			// ( 'strict' )
			// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:11:9:
			// 'strict'
			{
				match("strict");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__17"

	// $ANTLR start "T__18"
	public final void mT__18() throws RecognitionException {
		try {
			int _type = T__18;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:12:7:
			// ( 'graph' )
			// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:12:9:
			// 'graph'
			{
				match("graph");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__18"

	// $ANTLR start "T__19"
	public final void mT__19() throws RecognitionException {
		try {
			int _type = T__19;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:13:7:
			// ( 'digraph' )
			// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:13:9:
			// 'digraph'
			{
				match("digraph");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__19"

	// $ANTLR start "T__20"
	public final void mT__20() throws RecognitionException {
		try {
			int _type = T__20;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:14:7:
			// ( '{' )
			// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:14:9:
			// '{'
			{
				match('{');

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__20"

	// $ANTLR start "T__21"
	public final void mT__21() throws RecognitionException {
		try {
			int _type = T__21;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:15:7:
			// ( '}' )
			// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:15:9:
			// '}'
			{
				match('}');

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__21"

	// $ANTLR start "T__22"
	public final void mT__22() throws RecognitionException {
		try {
			int _type = T__22;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:16:7:
			// ( ';' )
			// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:16:9:
			// ';'
			{
				match(';');

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__22"

	// $ANTLR start "T__23"
	public final void mT__23() throws RecognitionException {
		try {
			int _type = T__23;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:17:7:
			// ( '=' )
			// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:17:9:
			// '='
			{
				match('=');

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__23"

	// $ANTLR start "T__24"
	public final void mT__24() throws RecognitionException {
		try {
			int _type = T__24;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:18:7:
			// ( 'node' )
			// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:18:9:
			// 'node'
			{
				match("node");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__24"

	// $ANTLR start "T__25"
	public final void mT__25() throws RecognitionException {
		try {
			int _type = T__25;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:19:7:
			// ( 'edge' )
			// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:19:9:
			// 'edge'
			{
				match("edge");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__25"

	// $ANTLR start "T__26"
	public final void mT__26() throws RecognitionException {
		try {
			int _type = T__26;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:20:7:
			// ( '[' )
			// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:20:9:
			// '['
			{
				match('[');

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__26"

	// $ANTLR start "T__27"
	public final void mT__27() throws RecognitionException {
		try {
			int _type = T__27;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:21:7:
			// ( ']' )
			// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:21:9:
			// ']'
			{
				match(']');

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__27"

	// $ANTLR start "T__28"
	public final void mT__28() throws RecognitionException {
		try {
			int _type = T__28;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:22:7:
			// ( ',' )
			// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:22:9:
			// ','
			{
				match(',');

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__28"

	// $ANTLR start "T__29"
	public final void mT__29() throws RecognitionException {
		try {
			int _type = T__29;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:23:7:
			// ( ':' )
			// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:23:9:
			// ':'
			{
				match(':');

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__29"

	// $ANTLR start "T__30"
	public final void mT__30() throws RecognitionException {
		try {
			int _type = T__30;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:24:7:
			// ( 'subgraph' )
			// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:24:9:
			// 'subgraph'
			{
				match("subgraph");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__30"

	// $ANTLR start "EDGEOP_LITERAL"
	public final void mEDGEOP_LITERAL() throws RecognitionException {
		try {
			int _type = EDGEOP_LITERAL;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:104:5:
			// ( ( '->' | '--' ) )
			// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:104:8:
			// ( '->' | '--' )
			{
				// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:104:8:
				// ( '->' | '--' )
				int alt1 = 2;
				int LA1_0 = input.LA(1);

				if ((LA1_0 == '-')) {
					int LA1_1 = input.LA(2);

					if ((LA1_1 == '>')) {
						alt1 = 1;
					} else if ((LA1_1 == '-')) {
						alt1 = 2;
					} else {
						NoViableAltException nvae = new NoViableAltException(
								"", 1, 1, input);

						throw nvae;
					}
				} else {
					NoViableAltException nvae = new NoViableAltException("", 1,
							0, input);

					throw nvae;
				}
				switch (alt1) {
				case 1:
					// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:104:11:
					// '->'
				{
					match("->");

				}
					break;
				case 2:
					// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:105:11:
					// '--'
				{
					match("--");

				}
					break;

				}

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "EDGEOP_LITERAL"

	// $ANTLR start "ID"
	public final void mID() throws RecognitionException {
		try {
			int _type = ID;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:110:5:
			// ( ( VALIDSTR | NUMBER | STRING_LITERAL | HTMLSTR ) )
			// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:110:8:
			// ( VALIDSTR | NUMBER | STRING_LITERAL | HTMLSTR )
			{
				// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:110:8:
				// ( VALIDSTR | NUMBER | STRING_LITERAL | HTMLSTR )
				int alt2 = 4;
				switch (input.LA(1)) {
				case 'A':
				case 'B':
				case 'C':
				case 'D':
				case 'E':
				case 'F':
				case 'G':
				case 'H':
				case 'I':
				case 'J':
				case 'K':
				case 'L':
				case 'M':
				case 'N':
				case 'O':
				case 'P':
				case 'Q':
				case 'R':
				case 'S':
				case 'T':
				case 'U':
				case 'V':
				case 'W':
				case 'X':
				case 'Y':
				case 'Z':
				case '_':
				case 'a':
				case 'b':
				case 'c':
				case 'd':
				case 'e':
				case 'f':
				case 'g':
				case 'h':
				case 'i':
				case 'j':
				case 'k':
				case 'l':
				case 'm':
				case 'n':
				case 'o':
				case 'p':
				case 'q':
				case 'r':
				case 's':
				case 't':
				case 'u':
				case 'v':
				case 'w':
				case 'x':
				case 'y':
				case 'z': {
					alt2 = 1;
				}
					break;
				case '-':
				case '.':
				case '0':
				case '1':
				case '2':
				case '3':
				case '4':
				case '5':
				case '6':
				case '7':
				case '8':
				case '9': {
					alt2 = 2;
				}
					break;
				case '\"': {
					alt2 = 3;
				}
					break;
				case '<': {
					alt2 = 4;
				}
					break;
				default:
					NoViableAltException nvae = new NoViableAltException("", 2,
							0, input);

					throw nvae;
				}

				switch (alt2) {
				case 1:
					// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:110:11:
					// VALIDSTR
				{
					mVALIDSTR();

				}
					break;
				case 2:
					// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:111:11:
					// NUMBER
				{
					mNUMBER();

				}
					break;
				case 3:
					// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:112:11:
					// STRING_LITERAL
				{
					mSTRING_LITERAL();

				}
					break;
				case 4:
					// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:113:11:
					// HTMLSTR
				{
					mHTMLSTR();

				}
					break;

				}

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "ID"

	// $ANTLR start "COMPASS_PT"
	public final void mCOMPASS_PT() throws RecognitionException {
		try {
			// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:117:5:
			// ( ( 'ne' | 'nw' | 'node' | 'n' | 'e' | 'se' | 'sw' | 's' | 'w' )
			// )
			// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:117:8:
			// ( 'ne' | 'nw' | 'node' | 'n' | 'e' | 'se' | 'sw' | 's' | 'w' )
			{
				// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:117:8:
				// ( 'ne' | 'nw' | 'node' | 'n' | 'e' | 'se' | 'sw' | 's' | 'w'
				// )
				int alt3 = 9;
				alt3 = dfa3.predict(input);
				switch (alt3) {
				case 1:
					// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:117:11:
					// 'ne'
				{
					match("ne");

				}
					break;
				case 2:
					// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:118:11:
					// 'nw'
				{
					match("nw");

				}
					break;
				case 3:
					// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:119:11:
					// 'node'
				{
					match("node");

				}
					break;
				case 4:
					// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:120:11:
					// 'n'
				{
					match('n');

				}
					break;
				case 5:
					// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:121:11:
					// 'e'
				{
					match('e');

				}
					break;
				case 6:
					// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:122:11:
					// 'se'
				{
					match("se");

				}
					break;
				case 7:
					// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:123:11:
					// 'sw'
				{
					match("sw");

				}
					break;
				case 8:
					// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:124:11:
					// 's'
				{
					match('s');

				}
					break;
				case 9:
					// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:125:11:
					// 'w'
				{
					match('w');

				}
					break;

				}

			}

		} finally {
		}
	}

	// $ANTLR end "COMPASS_PT"

	// $ANTLR start "ALPHACHAR"
	public final void mALPHACHAR() throws RecognitionException {
		try {
			// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:129:5:
			// ( ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) )
			// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:129:8:
			// ( 'a' .. 'z' | 'A' .. 'Z' | '_' )
			{
				if ((input.LA(1) >= 'A' && input.LA(1) <= 'Z')
						|| input.LA(1) == '_'
						|| (input.LA(1) >= 'a' && input.LA(1) <= 'z')) {
					input.consume();

				} else {
					MismatchedSetException mse = new MismatchedSetException(
							null, input);
					recover(mse);
					throw mse;
				}

			}

		} finally {
		}
	}

	// $ANTLR end "ALPHACHAR"

	// $ANTLR start "VALIDSTR"
	public final void mVALIDSTR() throws RecognitionException {
		try {
			// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:135:5:
			// ( ALPHACHAR ( ALPHACHAR | '0' .. '9' )* )
			// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:135:8:
			// ALPHACHAR ( ALPHACHAR | '0' .. '9' )*
			{
				mALPHACHAR();
				// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:136:9:
				// ( ALPHACHAR | '0' .. '9' )*
				loop4: do {
					int alt4 = 2;
					int LA4_0 = input.LA(1);

					if (((LA4_0 >= '0' && LA4_0 <= '9')
							|| (LA4_0 >= 'A' && LA4_0 <= 'Z') || LA4_0 == '_' || (LA4_0 >= 'a' && LA4_0 <= 'z'))) {
						alt4 = 1;
					}

					switch (alt4) {
					case 1:
						// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:
					{
						if ((input.LA(1) >= '0' && input.LA(1) <= '9')
								|| (input.LA(1) >= 'A' && input.LA(1) <= 'Z')
								|| input.LA(1) == '_'
								|| (input.LA(1) >= 'a' && input.LA(1) <= 'z')) {
							input.consume();

						} else {
							MismatchedSetException mse = new MismatchedSetException(
									null, input);
							recover(mse);
							throw mse;
						}

					}
						break;

					default:
						break loop4;
					}
				} while (true);

			}

		} finally {
		}
	}

	// $ANTLR end "VALIDSTR"

	// $ANTLR start "NUMBER"
	public final void mNUMBER() throws RecognitionException {
		try {
			// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:142:5:
			// ( ( '-' | '.' )? ( '0' .. '9' )+ ( '.' ( '0' .. '9' )+ )? )
			// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:142:8:
			// ( '-' | '.' )? ( '0' .. '9' )+ ( '.' ( '0' .. '9' )+ )?
			{
				// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:142:8:
				// ( '-' | '.' )?
				int alt5 = 2;
				int LA5_0 = input.LA(1);

				if (((LA5_0 >= '-' && LA5_0 <= '.'))) {
					alt5 = 1;
				}
				switch (alt5) {
				case 1:
					// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:
				{
					if ((input.LA(1) >= '-' && input.LA(1) <= '.')) {
						input.consume();

					} else {
						MismatchedSetException mse = new MismatchedSetException(
								null, input);
						recover(mse);
						throw mse;
					}

				}
					break;

				}

				// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:142:21:
				// ( '0' .. '9' )+
				int cnt6 = 0;
				loop6: do {
					int alt6 = 2;
					int LA6_0 = input.LA(1);

					if (((LA6_0 >= '0' && LA6_0 <= '9'))) {
						alt6 = 1;
					}

					switch (alt6) {
					case 1:
						// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:142:22:
						// '0' .. '9'
					{
						matchRange('0', '9');

					}
						break;

					default:
						if (cnt6 >= 1)
							break loop6;
						EarlyExitException eee = new EarlyExitException(6,
								input);
						throw eee;
					}
					cnt6++;
				} while (true);

				// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:142:33:
				// ( '.' ( '0' .. '9' )+ )?
				int alt8 = 2;
				int LA8_0 = input.LA(1);

				if ((LA8_0 == '.')) {
					alt8 = 1;
				}
				switch (alt8) {
				case 1:
					// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:142:34:
					// '.' ( '0' .. '9' )+
				{
					match('.');
					// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:142:38:
					// ( '0' .. '9' )+
					int cnt7 = 0;
					loop7: do {
						int alt7 = 2;
						int LA7_0 = input.LA(1);

						if (((LA7_0 >= '0' && LA7_0 <= '9'))) {
							alt7 = 1;
						}

						switch (alt7) {
						case 1:
							// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:142:39:
							// '0' .. '9'
						{
							matchRange('0', '9');

						}
							break;

						default:
							if (cnt7 >= 1)
								break loop7;
							EarlyExitException eee = new EarlyExitException(7,
									input);
							throw eee;
						}
						cnt7++;
					} while (true);

				}
					break;

				}

			}

		} finally {
		}
	}

	// $ANTLR end "NUMBER"

	// $ANTLR start "STRING_LITERAL"
	public final void mSTRING_LITERAL() throws RecognitionException {
		try {
			// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:147:3:
			// ( '\"' LITERAL_CHAR ( LITERAL_CHAR )* '\"' )
			// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:147:5:
			// '\"' LITERAL_CHAR ( LITERAL_CHAR )* '\"'
			{
				match('\"');
				mLITERAL_CHAR();
				// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:147:22:
				// ( LITERAL_CHAR )*
				loop9: do {
					int alt9 = 2;
					int LA9_0 = input.LA(1);

					if (((LA9_0 >= '\u0000' && LA9_0 <= '!') || (LA9_0 >= '#' && LA9_0 <= '\uFFFF'))) {
						alt9 = 1;
					}

					switch (alt9) {
					case 1:
						// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:147:22:
						// LITERAL_CHAR
					{
						mLITERAL_CHAR();

					}
						break;

					default:
						break loop9;
					}
				} while (true);

				match('\"');

			}

		} finally {
		}
	}

	// $ANTLR end "STRING_LITERAL"

	// $ANTLR start "LITERAL_CHAR"
	public final void mLITERAL_CHAR() throws RecognitionException {
		try {
			// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:152:3:
			// ( ESC | ~ ( '\"' | '\\\\' ) )
			int alt10 = 2;
			int LA10_0 = input.LA(1);

			if ((LA10_0 == '\\')) {
				alt10 = 1;
			} else if (((LA10_0 >= '\u0000' && LA10_0 <= '!')
					|| (LA10_0 >= '#' && LA10_0 <= '[') || (LA10_0 >= ']' && LA10_0 <= '\uFFFF'))) {
				alt10 = 2;
			} else {
				NoViableAltException nvae = new NoViableAltException("", 10, 0,
						input);

				throw nvae;
			}
			switch (alt10) {
			case 1:
				// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:152:5:
				// ESC
			{
				mESC();

			}
				break;
			case 2:
				// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:153:5:
				// ~ ( '\"' | '\\\\' )
			{
				if ((input.LA(1) >= '\u0000' && input.LA(1) <= '!')
						|| (input.LA(1) >= '#' && input.LA(1) <= '[')
						|| (input.LA(1) >= ']' && input.LA(1) <= '\uFFFF')) {
					input.consume();

				} else {
					MismatchedSetException mse = new MismatchedSetException(
							null, input);
					recover(mse);
					throw mse;
				}

			}
				break;

			}
		} finally {
		}
	}

	// $ANTLR end "LITERAL_CHAR"

	// $ANTLR start "ESC"
	public final void mESC() throws RecognitionException {
		try {
			// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:157:5:
			// ( '\\\\' ( 'n' | 'r' | 't' | 'b' | 'f' | '\"' | '\\'' | '\\\\' |
			// '>' ) )
			// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:157:7:
			// '\\\\' ( 'n' | 'r' | 't' | 'b' | 'f' | '\"' | '\\'' | '\\\\' |
			// '>' )
			{
				match('\\');
				if (input.LA(1) == '\"' || input.LA(1) == '\''
						|| input.LA(1) == '>' || input.LA(1) == '\\'
						|| input.LA(1) == 'b' || input.LA(1) == 'f'
						|| input.LA(1) == 'n' || input.LA(1) == 'r'
						|| input.LA(1) == 't') {
					input.consume();

				} else {
					MismatchedSetException mse = new MismatchedSetException(
							null, input);
					recover(mse);
					throw mse;
				}

			}

		} finally {
		}
	}

	// $ANTLR end "ESC"

	// $ANTLR start "HTMLSTR"
	public final void mHTMLSTR() throws RecognitionException {
		try {
			// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:172:5:
			// ( '<' (~ '>' )* '>' )
			// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:172:8:
			// '<' (~ '>' )* '>'
			{
				match('<');
				// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:172:12:
				// (~ '>' )*
				loop11: do {
					int alt11 = 2;
					int LA11_0 = input.LA(1);

					if (((LA11_0 >= '\u0000' && LA11_0 <= '=') || (LA11_0 >= '?' && LA11_0 <= '\uFFFF'))) {
						alt11 = 1;
					}

					switch (alt11) {
					case 1:
						// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:172:13:
						// ~ '>'
					{
						if ((input.LA(1) >= '\u0000' && input.LA(1) <= '=')
								|| (input.LA(1) >= '?' && input.LA(1) <= '\uFFFF')) {
							input.consume();

						} else {
							MismatchedSetException mse = new MismatchedSetException(
									null, input);
							recover(mse);
							throw mse;
						}

					}
						break;

					default:
						break loop11;
					}
				} while (true);

				match('>');

			}

		} finally {
		}
	}

	// $ANTLR end "HTMLSTR"

	// $ANTLR start "WS"
	public final void mWS() throws RecognitionException {
		try {
			int _type = WS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:175:5:
			// ( ( ' ' | '\\t' | ( '\\r' )? '\\n' )+ )
			// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:175:7:
			// ( ' ' | '\\t' | ( '\\r' )? '\\n' )+
			{
				// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:175:7:
				// ( ' ' | '\\t' | ( '\\r' )? '\\n' )+
				int cnt13 = 0;
				loop13: do {
					int alt13 = 4;
					switch (input.LA(1)) {
					case ' ': {
						alt13 = 1;
					}
						break;
					case '\t': {
						alt13 = 2;
					}
						break;
					case '\n':
					case '\r': {
						alt13 = 3;
					}
						break;

					}

					switch (alt13) {
					case 1:
						// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:175:9:
						// ' '
					{
						match(' ');

					}
						break;
					case 2:
						// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:176:7:
						// '\\t'
					{
						match('\t');

					}
						break;
					case 3:
						// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:177:7:
						// ( '\\r' )? '\\n'
					{
						// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:177:7:
						// ( '\\r' )?
						int alt12 = 2;
						int LA12_0 = input.LA(1);

						if ((LA12_0 == '\r')) {
							alt12 = 1;
						}
						switch (alt12) {
						case 1:
							// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:177:7:
							// '\\r'
						{
							match('\r');

						}
							break;

						}

						match('\n');

					}
						break;

					default:
						if (cnt13 >= 1)
							break loop13;
						EarlyExitException eee = new EarlyExitException(13,
								input);
						throw eee;
					}
					cnt13++;
				} while (true);

				_channel = HIDDEN;

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "WS"

	// $ANTLR start "SL_COMMENT"
	public final void mSL_COMMENT() throws RecognitionException {
		try {
			int _type = SL_COMMENT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:184:3:
			// ( ( '/*' | '//' ) (~ ( '\\r' | '\\n' ) )* ( '\\r' )? '\\n' )
			// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:184:5:
			// ( '/*' | '//' ) (~ ( '\\r' | '\\n' ) )* ( '\\r' )? '\\n'
			{
				// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:184:5:
				// ( '/*' | '//' )
				int alt14 = 2;
				int LA14_0 = input.LA(1);

				if ((LA14_0 == '/')) {
					int LA14_1 = input.LA(2);

					if ((LA14_1 == '*')) {
						alt14 = 1;
					} else if ((LA14_1 == '/')) {
						alt14 = 2;
					} else {
						NoViableAltException nvae = new NoViableAltException(
								"", 14, 1, input);

						throw nvae;
					}
				} else {
					NoViableAltException nvae = new NoViableAltException("",
							14, 0, input);

					throw nvae;
				}
				switch (alt14) {
				case 1:
					// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:184:8:
					// '/*'
				{
					match("/*");

				}
					break;
				case 2:
					// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:185:8:
					// '//'
				{
					match("//");

				}
					break;

				}

				// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:187:5:
				// (~ ( '\\r' | '\\n' ) )*
				loop15: do {
					int alt15 = 2;
					int LA15_0 = input.LA(1);

					if (((LA15_0 >= '\u0000' && LA15_0 <= '\t')
							|| (LA15_0 >= '\u000B' && LA15_0 <= '\f') || (LA15_0 >= '\u000E' && LA15_0 <= '\uFFFF'))) {
						alt15 = 1;
					}

					switch (alt15) {
					case 1:
						// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:187:5:
						// ~ ( '\\r' | '\\n' )
					{
						if ((input.LA(1) >= '\u0000' && input.LA(1) <= '\t')
								|| (input.LA(1) >= '\u000B' && input.LA(1) <= '\f')
								|| (input.LA(1) >= '\u000E' && input.LA(1) <= '\uFFFF')) {
							input.consume();

						} else {
							MismatchedSetException mse = new MismatchedSetException(
									null, input);
							recover(mse);
							throw mse;
						}

					}
						break;

					default:
						break loop15;
					}
				} while (true);

				// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:188:5:
				// ( '\\r' )?
				int alt16 = 2;
				int LA16_0 = input.LA(1);

				if ((LA16_0 == '\r')) {
					alt16 = 1;
				}
				switch (alt16) {
				case 1:
					// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:188:5:
					// '\\r'
				{
					match('\r');

				}
					break;

				}

				match('\n');
				_channel = HIDDEN;

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "SL_COMMENT"

	// $ANTLR start "ML_COMMENT"
	public final void mML_COMMENT() throws RecognitionException {
		try {
			int _type = ML_COMMENT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:194:3:
			// ( '/*' ( . )* '*/' )
			// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:194:5:
			// '/*' ( . )* '*/'
			{
				match("/*");

				_channel = HIDDEN;
				// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:194:29:
				// ( . )*
				loop17: do {
					int alt17 = 2;
					int LA17_0 = input.LA(1);

					if ((LA17_0 == '*')) {
						int LA17_1 = input.LA(2);

						if ((LA17_1 == '/')) {
							alt17 = 2;
						} else if (((LA17_1 >= '\u0000' && LA17_1 <= '.') || (LA17_1 >= '0' && LA17_1 <= '\uFFFF'))) {
							alt17 = 1;
						}

					} else if (((LA17_0 >= '\u0000' && LA17_0 <= ')') || (LA17_0 >= '+' && LA17_0 <= '\uFFFF'))) {
						alt17 = 1;
					}

					switch (alt17) {
					case 1:
						// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:194:29:
						// .
					{
						matchAny();

					}
						break;

					default:
						break loop17;
					}
				} while (true);

				match("*/");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "ML_COMMENT"

	public void mTokens() throws RecognitionException {
		// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:1:8:
		// ( T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 |
		// T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | EDGEOP_LITERAL | ID |
		// WS | SL_COMMENT | ML_COMMENT )
		int alt18 = 19;
		alt18 = dfa18.predict(input);
		switch (alt18) {
		case 1:
			// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:1:10:
			// T__17
		{
			mT__17();

		}
			break;
		case 2:
			// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:1:16:
			// T__18
		{
			mT__18();

		}
			break;
		case 3:
			// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:1:22:
			// T__19
		{
			mT__19();

		}
			break;
		case 4:
			// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:1:28:
			// T__20
		{
			mT__20();

		}
			break;
		case 5:
			// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:1:34:
			// T__21
		{
			mT__21();

		}
			break;
		case 6:
			// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:1:40:
			// T__22
		{
			mT__22();

		}
			break;
		case 7:
			// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:1:46:
			// T__23
		{
			mT__23();

		}
			break;
		case 8:
			// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:1:52:
			// T__24
		{
			mT__24();

		}
			break;
		case 9:
			// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:1:58:
			// T__25
		{
			mT__25();

		}
			break;
		case 10:
			// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:1:64:
			// T__26
		{
			mT__26();

		}
			break;
		case 11:
			// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:1:70:
			// T__27
		{
			mT__27();

		}
			break;
		case 12:
			// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:1:76:
			// T__28
		{
			mT__28();

		}
			break;
		case 13:
			// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:1:82:
			// T__29
		{
			mT__29();

		}
			break;
		case 14:
			// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:1:88:
			// T__30
		{
			mT__30();

		}
			break;
		case 15:
			// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:1:94:
			// EDGEOP_LITERAL
		{
			mEDGEOP_LITERAL();

		}
			break;
		case 16:
			// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:1:109:
			// ID
		{
			mID();

		}
			break;
		case 17:
			// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:1:112:
			// WS
		{
			mWS();

		}
			break;
		case 18:
			// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:1:115:
			// SL_COMMENT
		{
			mSL_COMMENT();

		}
			break;
		case 19:
			// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:1:126:
			// ML_COMMENT
		{
			mML_COMMENT();

		}
			break;

		}

	}

	protected DFA3 dfa3 = new DFA3(this);
	protected DFA18 dfa18 = new DFA18(this);
	static final String DFA3_eotS = "\1\uffff\1\10\1\uffff\1\13\10\uffff";
	static final String DFA3_eofS = "\14\uffff";
	static final String DFA3_minS = "\2\145\1\uffff\1\145\10\uffff";
	static final String DFA3_maxS = "\2\167\1\uffff\1\167\10\uffff";
	static final String DFA3_acceptS = "\2\uffff\1\5\1\uffff\1\11\1\1\1\2\1\3\1\4\1\6\1\7\1\10";
	static final String DFA3_specialS = "\14\uffff}>";
	static final String[] DFA3_transitionS = {
			"\1\2\10\uffff\1\1\4\uffff\1\3\3\uffff\1\4",
			"\1\5\11\uffff\1\7\7\uffff\1\6", "", "\1\11\21\uffff\1\12", "", "",
			"", "", "", "", "", "" };

	static final short[] DFA3_eot = DFA.unpackEncodedString(DFA3_eotS);
	static final short[] DFA3_eof = DFA.unpackEncodedString(DFA3_eofS);
	static final char[] DFA3_min = DFA
			.unpackEncodedStringToUnsignedChars(DFA3_minS);
	static final char[] DFA3_max = DFA
			.unpackEncodedStringToUnsignedChars(DFA3_maxS);
	static final short[] DFA3_accept = DFA.unpackEncodedString(DFA3_acceptS);
	static final short[] DFA3_special = DFA.unpackEncodedString(DFA3_specialS);
	static final short[][] DFA3_transition;

	static {
		int numStates = DFA3_transitionS.length;
		DFA3_transition = new short[numStates][];
		for (int i = 0; i < numStates; i++) {
			DFA3_transition[i] = DFA.unpackEncodedString(DFA3_transitionS[i]);
		}
	}

	class DFA3 extends DFA {

		public DFA3(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 3;
			this.eot = DFA3_eot;
			this.eof = DFA3_eof;
			this.min = DFA3_min;
			this.max = DFA3_max;
			this.accept = DFA3_accept;
			this.special = DFA3_special;
			this.transition = DFA3_transition;
		}

		public String getDescription() {
			return "117:8: ( 'ne' | 'nw' | 'node' | 'n' | 'e' | 'se' | 'sw' | 's' | 'w' )";
		}
	}

	static final String DFA18_eotS = "\1\uffff\3\17\4\uffff\2\17\10\uffff\6\17\3\uffff\6\17\2\uffff\1"
			+ "\32\1\uffff\4\17\1\61\1\62\1\54\1\uffff\2\17\1\65\1\17\2\uffff\1"
			+ "\67\1\17\1\uffff\1\17\1\uffff\1\17\1\73\1\74\2\uffff";
	static final String DFA18_eofS = "\75\uffff";
	static final String DFA18_minS = "\1\11\1\164\1\162\1\151\4\uffff\1\157\1\144\4\uffff\1\55\2\uffff"
			+ "\1\52\1\162\1\142\1\141\1\147\1\144\1\147\1\uffff\1\0\1\uffff\1"
			+ "\151\1\147\1\160\1\162\2\145\4\0\1\143\1\162\1\150\1\141\2\60\1"
			+ "\0\1\uffff\1\164\1\141\1\60\1\160\2\uffff\1\60\1\160\1\uffff\1\150"
			+ "\1\uffff\1\150\2\60\2\uffff";
	static final String DFA18_maxS = "\1\175\1\165\1\162\1\151\4\uffff\1\157\1\144\4\uffff\1\76\2\uffff"
			+ "\1\57\1\162\1\142\1\141\1\147\1\144\1\147\1\uffff\1\uffff\1\uffff"
			+ "\1\151\1\147\1\160\1\162\2\145\4\uffff\1\143\1\162\1\150\1\141\2"
			+ "\172\1\uffff\1\uffff\1\164\1\141\1\172\1\160\2\uffff\1\172\1\160"
			+ "\1\uffff\1\150\1\uffff\1\150\2\172\2\uffff";
	static final String DFA18_acceptS = "\4\uffff\1\4\1\5\1\6\1\7\2\uffff\1\12\1\13\1\14\1\15\1\uffff\1\20"
			+ "\1\21\7\uffff\1\17\1\uffff\1\22\21\uffff\1\23\4\uffff\1\10\1\11"
			+ "\2\uffff\1\2\1\uffff\1\1\3\uffff\1\3\1\16";
	static final String DFA18_specialS = "\31\uffff\1\4\7\uffff\1\2\1\0\1\3\1\1\6\uffff\1\5\21\uffff}>";
	static final String[] DFA18_transitionS = {
			"\2\20\2\uffff\1\20\22\uffff\1\20\1\uffff\1\17\11\uffff\1\14"
					+ "\1\16\1\17\1\21\12\17\1\15\1\6\1\17\1\7\3\uffff\32\17\1\12\1"
					+ "\uffff\1\13\1\uffff\1\17\1\uffff\3\17\1\3\1\11\1\17\1\2\6\17"
					+ "\1\10\4\17\1\1\7\17\1\4\1\uffff\1\5", "\1\22\1\23",
			"\1\24", "\1\25", "", "", "", "", "\1\26", "\1\27", "", "", "", "",
			"\1\30\2\uffff\12\17\4\uffff\1\30", "", "", "\1\31\4\uffff\1\32",
			"\1\33", "\1\34", "\1\35", "\1\36", "\1\37", "\1\40", "",
			"\12\44\1\43\2\44\1\42\34\44\1\41\uffd5\44", "", "\1\45", "\1\46",
			"\1\47", "\1\50", "\1\51", "\1\52",
			"\12\44\1\43\2\44\1\42\34\44\1\41\4\44\1\53\uffd0\44",
			"\12\54\1\43\ufff5\54", "\0\54",
			"\12\44\1\43\2\44\1\42\34\44\1\41\uffd5\44", "\1\55", "\1\56",
			"\1\57", "\1\60",
			"\12\17\7\uffff\32\17\4\uffff\1\17\1\uffff\32\17",
			"\12\17\7\uffff\32\17\4\uffff\1\17\1\uffff\32\17",
			"\12\44\1\43\2\44\1\42\34\44\1\41\uffd5\44", "", "\1\63", "\1\64",
			"\12\17\7\uffff\32\17\4\uffff\1\17\1\uffff\32\17", "\1\66", "", "",
			"\12\17\7\uffff\32\17\4\uffff\1\17\1\uffff\32\17", "\1\70", "",
			"\1\71", "", "\1\72",
			"\12\17\7\uffff\32\17\4\uffff\1\17\1\uffff\32\17",
			"\12\17\7\uffff\32\17\4\uffff\1\17\1\uffff\32\17", "", "" };

	static final short[] DFA18_eot = DFA.unpackEncodedString(DFA18_eotS);
	static final short[] DFA18_eof = DFA.unpackEncodedString(DFA18_eofS);
	static final char[] DFA18_min = DFA
			.unpackEncodedStringToUnsignedChars(DFA18_minS);
	static final char[] DFA18_max = DFA
			.unpackEncodedStringToUnsignedChars(DFA18_maxS);
	static final short[] DFA18_accept = DFA.unpackEncodedString(DFA18_acceptS);
	static final short[] DFA18_special = DFA
			.unpackEncodedString(DFA18_specialS);
	static final short[][] DFA18_transition;

	static {
		int numStates = DFA18_transitionS.length;
		DFA18_transition = new short[numStates][];
		for (int i = 0; i < numStates; i++) {
			DFA18_transition[i] = DFA.unpackEncodedString(DFA18_transitionS[i]);
		}
	}

	class DFA18 extends DFA {

		public DFA18(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 18;
			this.eot = DFA18_eot;
			this.eof = DFA18_eof;
			this.min = DFA18_min;
			this.max = DFA18_max;
			this.accept = DFA18_accept;
			this.special = DFA18_special;
			this.transition = DFA18_transition;
		}

		public String getDescription() {
			return "1:1: Tokens : ( T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | EDGEOP_LITERAL | ID | WS | SL_COMMENT | ML_COMMENT );";
		}

		public int specialStateTransition(int s, IntStream _input)
				throws NoViableAltException {
			IntStream input = _input;
			int _s = s;
			switch (s) {
			case 0:
				int LA18_34 = input.LA(1);

				s = -1;
				if (((LA18_34 >= '\u0000' && LA18_34 <= '\t') || (LA18_34 >= '\u000B' && LA18_34 <= '\uFFFF'))) {
					s = 44;
				}

				else if ((LA18_34 == '\n')) {
					s = 35;
				}

				if (s >= 0)
					return s;
				break;
			case 1:
				int LA18_36 = input.LA(1);

				s = -1;
				if ((LA18_36 == '\r')) {
					s = 34;
				}

				else if ((LA18_36 == '\n')) {
					s = 35;
				}

				else if ((LA18_36 == '*')) {
					s = 33;
				}

				else if (((LA18_36 >= '\u0000' && LA18_36 <= '\t')
						|| (LA18_36 >= '\u000B' && LA18_36 <= '\f')
						|| (LA18_36 >= '\u000E' && LA18_36 <= ')') || (LA18_36 >= '+' && LA18_36 <= '\uFFFF'))) {
					s = 36;
				}

				if (s >= 0)
					return s;
				break;
			case 2:
				int LA18_33 = input.LA(1);

				s = -1;
				if ((LA18_33 == '/')) {
					s = 43;
				}

				else if ((LA18_33 == '\r')) {
					s = 34;
				}

				else if ((LA18_33 == '\n')) {
					s = 35;
				}

				else if ((LA18_33 == '*')) {
					s = 33;
				}

				else if (((LA18_33 >= '\u0000' && LA18_33 <= '\t')
						|| (LA18_33 >= '\u000B' && LA18_33 <= '\f')
						|| (LA18_33 >= '\u000E' && LA18_33 <= ')')
						|| (LA18_33 >= '+' && LA18_33 <= '.') || (LA18_33 >= '0' && LA18_33 <= '\uFFFF'))) {
					s = 36;
				}

				if (s >= 0)
					return s;
				break;
			case 3:
				int LA18_35 = input.LA(1);

				s = -1;
				if (((LA18_35 >= '\u0000' && LA18_35 <= '\uFFFF'))) {
					s = 44;
				}

				else
					s = 26;

				if (s >= 0)
					return s;
				break;
			case 4:
				int LA18_25 = input.LA(1);

				s = -1;
				if ((LA18_25 == '*')) {
					s = 33;
				}

				else if ((LA18_25 == '\r')) {
					s = 34;
				}

				else if ((LA18_25 == '\n')) {
					s = 35;
				}

				else if (((LA18_25 >= '\u0000' && LA18_25 <= '\t')
						|| (LA18_25 >= '\u000B' && LA18_25 <= '\f')
						|| (LA18_25 >= '\u000E' && LA18_25 <= ')') || (LA18_25 >= '+' && LA18_25 <= '\uFFFF'))) {
					s = 36;
				}

				if (s >= 0)
					return s;
				break;
			case 5:
				int LA18_43 = input.LA(1);

				s = -1;
				if ((LA18_43 == '\r')) {
					s = 34;
				}

				else if ((LA18_43 == '\n')) {
					s = 35;
				}

				else if ((LA18_43 == '*')) {
					s = 33;
				}

				else if (((LA18_43 >= '\u0000' && LA18_43 <= '\t')
						|| (LA18_43 >= '\u000B' && LA18_43 <= '\f')
						|| (LA18_43 >= '\u000E' && LA18_43 <= ')') || (LA18_43 >= '+' && LA18_43 <= '\uFFFF'))) {
					s = 36;
				}

				else
					s = 44;

				if (s >= 0)
					return s;
				break;
			}
			NoViableAltException nvae = new NoViableAltException(
					getDescription(), 18, _s, input);
			error(nvae);
			throw nvae;
		}
	}

}