// $ANTLR 3.1.3 Mar 17, 2009 19:23:44 /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g 2009-06-16 20:35:20

package org.deved.antlride.internal.core.dot;

import org.antlr.runtime.BaseRecognizer;
import org.antlr.runtime.BitSet;
import org.antlr.runtime.DFA;
import org.antlr.runtime.EarlyExitException;
import org.antlr.runtime.MismatchedSetException;
import org.antlr.runtime.NoViableAltException;
import org.antlr.runtime.Parser;
import org.antlr.runtime.ParserRuleReturnScope;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.RecognizerSharedState;
import org.antlr.runtime.Token;
import org.antlr.runtime.TokenStream;
import org.deved.antlride.core.dot.DotAttributesBuilder;
import org.deved.antlride.core.dot.DotGraph;

public class SimpleDotParser extends Parser {
	public static final String[] tokenNames = new String[] { "<invalid>",
			"<EOR>", "<DOWN>", "<UP>", "ID", "EDGEOP_LITERAL", "COMPASS_PT",
			"VALIDSTR", "NUMBER", "STRING_LITERAL", "HTMLSTR", "ALPHACHAR",
			"LITERAL_CHAR", "ESC", "WS", "SL_COMMENT", "ML_COMMENT",
			"'strict'", "'graph'", "'digraph'", "'{'", "'}'", "';'", "'='",
			"'node'", "'edge'", "'['", "']'", "','", "':'", "'subgraph'" };
	public static final int HTMLSTR = 10;
	public static final int T__28 = 28;
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
	public static final int STRING_LITERAL = 9;
	public static final int T__27 = 27;
	public static final int VALIDSTR = 7;
	public static final int T__24 = 24;
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

	public SimpleDotParser(TokenStream input) {
		this(input, new RecognizerSharedState());
	}

	public SimpleDotParser(TokenStream input, RecognizerSharedState state) {
		super(input, state);

	}

	public String[] getTokenNames() {
		return SimpleDotParser.tokenNames;
	}

	public String getGrammarFileName() {
		return "/media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g";
	}

	DotGraph graph = null;
	DotAttributesBuilder attrBuilder;

	// $ANTLR start "graph"
	// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:22:1:
	// graph[DotGraph graph] : ( 'strict' )? ( 'graph' | 'digraph' ) ( ID )?
	// body ;
	public final void graph(DotGraph graph) throws RecognitionException {

		this.graph = graph;

		try {
			// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:26:3:
			// ( ( 'strict' )? ( 'graph' | 'digraph' ) ( ID )? body )
			// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:27:5:
			// ( 'strict' )? ( 'graph' | 'digraph' ) ( ID )? body
			{
				// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:27:5:
				// ( 'strict' )?
				int alt1 = 2;
				int LA1_0 = input.LA(1);

				if ((LA1_0 == 17)) {
					alt1 = 1;
				}
				switch (alt1) {
				case 1:
					// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:27:5:
					// 'strict'
				{
					match(input, 17, FOLLOW_17_in_graph57);

				}
					break;

				}

				if ((input.LA(1) >= 18 && input.LA(1) <= 19)) {
					input.consume();
					state.errorRecovery = false;
				} else {
					MismatchedSetException mse = new MismatchedSetException(
							null, input);
					throw mse;
				}

				// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:29:5:
				// ( ID )?
				int alt2 = 2;
				int LA2_0 = input.LA(1);

				if ((LA2_0 == ID)) {
					alt2 = 1;
				}
				switch (alt2) {
				case 1:
					// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:29:5:
					// ID
				{
					match(input, ID, FOLLOW_ID_in_graph78);

				}
					break;

				}

				pushFollow(FOLLOW_body_in_graph86);
				body();

				state._fsp--;

			}

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
		}
		return;
	}

	// $ANTLR end "graph"

	// $ANTLR start "body"
	// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:33:1:
	// body : '{' ( stmt_list )* '}' ;
	public final void body() throws RecognitionException {
		try {
			// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:33:5:
			// ( '{' ( stmt_list )* '}' )
			// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:34:3:
			// '{' ( stmt_list )* '}'
			{
				match(input, 20, FOLLOW_20_in_body98);
				// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:34:7:
				// ( stmt_list )*
				loop3: do {
					int alt3 = 2;
					int LA3_0 = input.LA(1);

					if ((LA3_0 == ID || LA3_0 == 18 || LA3_0 == 20
							|| (LA3_0 >= 24 && LA3_0 <= 25) || LA3_0 == 30)) {
						alt3 = 1;
					}

					switch (alt3) {
					case 1:
						// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:34:7:
						// stmt_list
					{
						pushFollow(FOLLOW_stmt_list_in_body100);
						stmt_list();

						state._fsp--;

					}
						break;

					default:
						break loop3;
					}
				} while (true);

				match(input, 21, FOLLOW_21_in_body104);

			}

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
		}
		return;
	}

	// $ANTLR end "body"

	// $ANTLR start "stmt_list"
	// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:37:1:
	// stmt_list : stmt ( ';' )? ;
	public final void stmt_list() throws RecognitionException {
		try {
			// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:38:3:
			// ( stmt ( ';' )? )
			// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:39:5:
			// stmt ( ';' )?
			{
				pushFollow(FOLLOW_stmt_in_stmt_list124);
				stmt();

				state._fsp--;

				// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:39:10:
				// ( ';' )?
				int alt4 = 2;
				int LA4_0 = input.LA(1);

				if ((LA4_0 == 22)) {
					alt4 = 1;
				}
				switch (alt4) {
				case 1:
					// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:39:10:
					// ';'
				{
					match(input, 22, FOLLOW_22_in_stmt_list126);

				}
					break;

				}

			}

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
		}
		return;
	}

	// $ANTLR end "stmt_list"

	// $ANTLR start "stmt"
	// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:42:1:
	// stmt : ( node_stmt | edge_stmt | attr_stmt | ID '=' ID | subgraph );
	public final void stmt() throws RecognitionException {
		try {
			// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:43:3:
			// ( node_stmt | edge_stmt | attr_stmt | ID '=' ID | subgraph )
			int alt5 = 5;
			alt5 = dfa5.predict(input);
			switch (alt5) {
			case 1:
				// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:44:7:
				// node_stmt
			{
				pushFollow(FOLLOW_node_stmt_in_stmt146);
				node_stmt();

				state._fsp--;

			}
				break;
			case 2:
				// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:45:7:
				// edge_stmt
			{
				pushFollow(FOLLOW_edge_stmt_in_stmt154);
				edge_stmt();

				state._fsp--;

			}
				break;
			case 3:
				// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:46:7:
				// attr_stmt
			{
				pushFollow(FOLLOW_attr_stmt_in_stmt162);
				attr_stmt();

				state._fsp--;

			}
				break;
			case 4:
				// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:47:7:
				// ID '=' ID
			{
				match(input, ID, FOLLOW_ID_in_stmt170);
				match(input, 23, FOLLOW_23_in_stmt172);
				match(input, ID, FOLLOW_ID_in_stmt174);

			}
				break;
			case 5:
				// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:48:7:
				// subgraph
			{
				pushFollow(FOLLOW_subgraph_in_stmt182);
				subgraph();

				state._fsp--;

			}
				break;

			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
		}
		return;
	}

	// $ANTLR end "stmt"

	// $ANTLR start "attr_stmt"
	// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:51:1:
	// attr_stmt : ( 'graph' | 'node' | 'edge' ) attr_list ;
	public final void attr_stmt() throws RecognitionException {
		try {
			// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:52:3:
			// ( ( 'graph' | 'node' | 'edge' ) attr_list )
			// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:53:5:
			// ( 'graph' | 'node' | 'edge' ) attr_list
			{
				if (input.LA(1) == 18
						|| (input.LA(1) >= 24 && input.LA(1) <= 25)) {
					input.consume();
					state.errorRecovery = false;
				} else {
					MismatchedSetException mse = new MismatchedSetException(
							null, input);
					throw mse;
				}

				pushFollow(FOLLOW_attr_list_in_attr_stmt211);
				attr_list();

				state._fsp--;

			}

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
		}
		return;
	}

	// $ANTLR end "attr_stmt"

	// $ANTLR start "attr_list"
	// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:55:1:
	// attr_list : ( '[' ( a_list )? ']' )+ ;
	public final void attr_list() throws RecognitionException {

		attrBuilder = new DotAttributesBuilder();

		try {
			// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:59:3:
			// ( ( '[' ( a_list )? ']' )+ )
			// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:60:5:
			// ( '[' ( a_list )? ']' )+
			{
				// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:60:5:
				// ( '[' ( a_list )? ']' )+
				int cnt7 = 0;
				loop7: do {
					int alt7 = 2;
					int LA7_0 = input.LA(1);

					if ((LA7_0 == 26)) {
						alt7 = 1;
					}

					switch (alt7) {
					case 1:
						// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:60:6:
						// '[' ( a_list )? ']'
					{
						match(input, 26, FOLLOW_26_in_attr_list234);
						// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:60:10:
						// ( a_list )?
						int alt6 = 2;
						int LA6_0 = input.LA(1);

						if ((LA6_0 == ID)) {
							alt6 = 1;
						}
						switch (alt6) {
						case 1:
							// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:60:10:
							// a_list
						{
							pushFollow(FOLLOW_a_list_in_attr_list236);
							a_list();

							state._fsp--;

						}
							break;

						}

						match(input, 27, FOLLOW_27_in_attr_list239);

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

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
		}
		return;
	}

	// $ANTLR end "attr_list"

	// $ANTLR start "a_list"
	// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:63:1:
	// a_list : name= ID ( '=' value= ID )? ( ',' )? ( a_list )? ;
	public final void a_list() throws RecognitionException {
		Token name = null;
		Token value = null;

		try {
			// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:64:3:
			// (name= ID ( '=' value= ID )? ( ',' )? ( a_list )? )
			// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:65:5:
			// name= ID ( '=' value= ID )? ( ',' )? ( a_list )?
			{
				name = (Token) match(input, ID, FOLLOW_ID_in_a_list260);
				// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:65:13:
				// ( '=' value= ID )?
				int alt8 = 2;
				int LA8_0 = input.LA(1);

				if ((LA8_0 == 23)) {
					alt8 = 1;
				}
				switch (alt8) {
				case 1:
					// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:65:15:
					// '=' value= ID
				{
					match(input, 23, FOLLOW_23_in_a_list264);
					value = (Token) match(input, ID, FOLLOW_ID_in_a_list268);

				}
					break;

				}

				// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:65:31:
				// ( ',' )?
				int alt9 = 2;
				int LA9_0 = input.LA(1);

				if ((LA9_0 == 28)) {
					alt9 = 1;
				}
				switch (alt9) {
				case 1:
					// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:65:31:
					// ','
				{
					match(input, 28, FOLLOW_28_in_a_list273);

				}
					break;

				}

				// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:65:36:
				// ( a_list )?
				int alt10 = 2;
				int LA10_0 = input.LA(1);

				if ((LA10_0 == ID)) {
					alt10 = 1;
				}
				switch (alt10) {
				case 1:
					// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:65:36:
					// a_list
				{
					pushFollow(FOLLOW_a_list_in_a_list276);
					a_list();

					state._fsp--;

				}
					break;

				}

				attrBuilder.attribute((name != null ? name.getText() : null),
						(value != null ? value.getText() : null));

			}

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
		}
		return;
	}

	// $ANTLR end "a_list"

	// $ANTLR start "edge_stmt"
	// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:71:1:
	// edge_stmt : ( node_id ) to= edgeRHS ( attr_list )? ;
	public final void edge_stmt() throws RecognitionException {
		String to = null;

		SimpleDotParser.node_id_return node_id1 = null;

		try {
			// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:72:3:
			// ( ( node_id ) to= edgeRHS ( attr_list )? )
			// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:73:5:
			// ( node_id ) to= edgeRHS ( attr_list )?
			{
				// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:73:5:
				// ( node_id )
				// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:73:6:
				// node_id
				{
					pushFollow(FOLLOW_node_id_in_edge_stmt305);
					node_id1 = node_id();

					state._fsp--;

				}

				pushFollow(FOLLOW_edgeRHS_in_edge_stmt312);
				to = edgeRHS();

				state._fsp--;

				// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:73:41:
				// ( attr_list )?
				int alt11 = 2;
				int LA11_0 = input.LA(1);

				if ((LA11_0 == 26)) {
					alt11 = 1;
				}
				switch (alt11) {
				case 1:
					// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:73:41:
					// attr_list
				{
					pushFollow(FOLLOW_attr_list_in_edge_stmt314);
					attr_list();

					state._fsp--;

				}
					break;

				}

				if (to != null)
					graph.edge((node_id1 != null ? input.toString(
							node_id1.start, node_id1.stop) : null), to,
							attrBuilder);

			}

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
		}
		return;
	}

	// $ANTLR end "edge_stmt"

	// $ANTLR start "edgeRHS"
	// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:77:1:
	// edgeRHS returns [String nodeId] : EDGEOP_LITERAL ( node_id | subgraph ) ;
	public final String edgeRHS() throws RecognitionException {
		String nodeId = null;

		SimpleDotParser.node_id_return node_id2 = null;

		try {
			// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:78:3:
			// ( EDGEOP_LITERAL ( node_id | subgraph ) )
			// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:79:3:
			// EDGEOP_LITERAL ( node_id | subgraph )
			{
				match(input, EDGEOP_LITERAL,
						FOLLOW_EDGEOP_LITERAL_in_edgeRHS342);
				// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:79:19:
				// ( node_id | subgraph )
				int alt12 = 2;
				int LA12_0 = input.LA(1);

				if ((LA12_0 == ID)) {
					alt12 = 1;
				} else if ((LA12_0 == 20 || LA12_0 == 30)) {
					alt12 = 2;
				} else {
					NoViableAltException nvae = new NoViableAltException("",
							12, 0, input);

					throw nvae;
				}
				switch (alt12) {
				case 1:
					// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:79:20:
					// node_id
				{
					pushFollow(FOLLOW_node_id_in_edgeRHS346);
					node_id2 = node_id();

					state._fsp--;

					nodeId = (node_id2 != null ? input.toString(node_id2.start,
							node_id2.stop) : null);

				}
					break;
				case 2:
					// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:79:53:
					// subgraph
				{
					pushFollow(FOLLOW_subgraph_in_edgeRHS351);
					subgraph();

					state._fsp--;

				}
					break;

				}

			}

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
		}
		return nodeId;
	}

	// $ANTLR end "edgeRHS"

	// $ANTLR start "node_stmt"
	// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:82:1:
	// node_stmt : node_id ( attr_list )? ;
	public final void node_stmt() throws RecognitionException {
		SimpleDotParser.node_id_return node_id3 = null;

		try {
			// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:83:3:
			// ( node_id ( attr_list )? )
			// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:83:7:
			// node_id ( attr_list )?
			{
				pushFollow(FOLLOW_node_id_in_node_stmt370);
				node_id3 = node_id();

				state._fsp--;

				// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:83:15:
				// ( attr_list )?
				int alt13 = 2;
				int LA13_0 = input.LA(1);

				if ((LA13_0 == 26)) {
					alt13 = 1;
				}
				switch (alt13) {
				case 1:
					// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:83:15:
					// attr_list
				{
					pushFollow(FOLLOW_attr_list_in_node_stmt372);
					attr_list();

					state._fsp--;

				}
					break;

				}

				graph.node((node_id3 != null ? input.toString(node_id3.start,
						node_id3.stop) : null), attrBuilder);

			}

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
		}
		return;
	}

	// $ANTLR end "node_stmt"

	public static class node_id_return extends ParserRuleReturnScope {
	};

	// $ANTLR start "node_id"
	// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:87:1:
	// node_id : ID ( port )? ;
	public final SimpleDotParser.node_id_return node_id()
			throws RecognitionException {
		SimpleDotParser.node_id_return retval = new SimpleDotParser.node_id_return();
		retval.start = input.LT(1);

		try {
			// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:88:3:
			// ( ID ( port )? )
			// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:89:5:
			// ID ( port )?
			{
				match(input, ID, FOLLOW_ID_in_node_id396);
				// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:89:8:
				// ( port )?
				int alt14 = 2;
				int LA14_0 = input.LA(1);

				if ((LA14_0 == 29)) {
					alt14 = 1;
				}
				switch (alt14) {
				case 1:
					// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:89:8:
					// port
				{
					pushFollow(FOLLOW_port_in_node_id398);
					port();

					state._fsp--;

				}
					break;

				}

			}

			retval.stop = input.LT(-1);

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
		}
		return retval;
	}

	// $ANTLR end "node_id"

	// $ANTLR start "port"
	// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:92:1:
	// port : ( ':' ID ( ':' COMPASS_PT )? | ':' COMPASS_PT );
	public final void port() throws RecognitionException {
		try {
			// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:93:3:
			// ( ':' ID ( ':' COMPASS_PT )? | ':' COMPASS_PT )
			int alt16 = 2;
			int LA16_0 = input.LA(1);

			if ((LA16_0 == 29)) {
				int LA16_1 = input.LA(2);

				if ((LA16_1 == ID)) {
					alt16 = 1;
				} else if ((LA16_1 == COMPASS_PT)) {
					alt16 = 2;
				} else {
					NoViableAltException nvae = new NoViableAltException("",
							16, 1, input);

					throw nvae;
				}
			} else {
				NoViableAltException nvae = new NoViableAltException("", 16, 0,
						input);

				throw nvae;
			}
			switch (alt16) {
			case 1:
				// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:94:5:
				// ':' ID ( ':' COMPASS_PT )?
			{
				match(input, 29, FOLLOW_29_in_port419);
				match(input, ID, FOLLOW_ID_in_port421);
				// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:94:12:
				// ( ':' COMPASS_PT )?
				int alt15 = 2;
				int LA15_0 = input.LA(1);

				if ((LA15_0 == 29)) {
					alt15 = 1;
				}
				switch (alt15) {
				case 1:
					// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:94:14:
					// ':' COMPASS_PT
				{
					match(input, 29, FOLLOW_29_in_port425);
					match(input, COMPASS_PT, FOLLOW_COMPASS_PT_in_port427);

				}
					break;

				}

			}
				break;
			case 2:
				// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:95:5:
				// ':' COMPASS_PT
			{
				match(input, 29, FOLLOW_29_in_port436);
				match(input, COMPASS_PT, FOLLOW_COMPASS_PT_in_port438);

			}
				break;

			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
		}
		return;
	}

	// $ANTLR end "port"

	// $ANTLR start "subgraph"
	// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:98:1:
	// subgraph : ( 'subgraph' ( ID )? )? body ;
	public final void subgraph() throws RecognitionException {
		try {
			// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:99:3:
			// ( ( 'subgraph' ( ID )? )? body )
			// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:100:5:
			// ( 'subgraph' ( ID )? )? body
			{
				// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:100:5:
				// ( 'subgraph' ( ID )? )?
				int alt18 = 2;
				int LA18_0 = input.LA(1);

				if ((LA18_0 == 30)) {
					alt18 = 1;
				}
				switch (alt18) {
				case 1:
					// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:100:7:
					// 'subgraph' ( ID )?
				{
					match(input, 30, FOLLOW_30_in_subgraph457);
					// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:100:18:
					// ( ID )?
					int alt17 = 2;
					int LA17_0 = input.LA(1);

					if ((LA17_0 == ID)) {
						alt17 = 1;
					}
					switch (alt17) {
					case 1:
						// /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/dot/SimpleDot.g:100:18:
						// ID
					{
						match(input, ID, FOLLOW_ID_in_subgraph459);

					}
						break;

					}

				}
					break;

				}

				pushFollow(FOLLOW_body_in_subgraph465);
				body();

				state._fsp--;

			}

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
		}
		return;
	}

	// $ANTLR end "subgraph"

	// Delegated rules

	protected DFA5 dfa5 = new DFA5(this);
	static final String DFA5_eotS = "\14\uffff";
	static final String DFA5_eofS = "\14\uffff";
	static final String DFA5_minS = "\2\4\3\uffff\1\4\2\uffff\2\4\1\6\1\4";
	static final String DFA5_maxS = "\2\36\3\uffff\1\6\2\uffff\2\36\1\6\1\36";
	static final String DFA5_acceptS = "\2\uffff\1\3\1\5\1\4\1\uffff\1\2\1\1\4\uffff";
	static final String DFA5_specialS = "\14\uffff}>";
	static final String[] DFA5_transitionS = {
			"\1\1\15\uffff\1\2\1\uffff\1\3\3\uffff\2\2\4\uffff\1\3",
			"\1\7\1\6\14\uffff\1\7\1\uffff\3\7\1\4\3\7\2\uffff\1\5\1\7",
			"",
			"",
			"",
			"\1\10\1\uffff\1\11",
			"",
			"",
			"\1\7\1\6\14\uffff\1\7\1\uffff\3\7\1\uffff\3\7\2\uffff\1\12"
					+ "\1\7",
			"\1\7\1\6\14\uffff\1\7\1\uffff\3\7\1\uffff\3\7\3\uffff\1\7",
			"\1\13",
			"\1\7\1\6\14\uffff\1\7\1\uffff\3\7\1\uffff\3\7\3\uffff\1\7" };

	static final short[] DFA5_eot = DFA.unpackEncodedString(DFA5_eotS);
	static final short[] DFA5_eof = DFA.unpackEncodedString(DFA5_eofS);
	static final char[] DFA5_min = DFA
			.unpackEncodedStringToUnsignedChars(DFA5_minS);
	static final char[] DFA5_max = DFA
			.unpackEncodedStringToUnsignedChars(DFA5_maxS);
	static final short[] DFA5_accept = DFA.unpackEncodedString(DFA5_acceptS);
	static final short[] DFA5_special = DFA.unpackEncodedString(DFA5_specialS);
	static final short[][] DFA5_transition;

	static {
		int numStates = DFA5_transitionS.length;
		DFA5_transition = new short[numStates][];
		for (int i = 0; i < numStates; i++) {
			DFA5_transition[i] = DFA.unpackEncodedString(DFA5_transitionS[i]);
		}
	}

	class DFA5 extends DFA {

		public DFA5(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 5;
			this.eot = DFA5_eot;
			this.eof = DFA5_eof;
			this.min = DFA5_min;
			this.max = DFA5_max;
			this.accept = DFA5_accept;
			this.special = DFA5_special;
			this.transition = DFA5_transition;
		}

		public String getDescription() {
			return "42:1: stmt : ( node_stmt | edge_stmt | attr_stmt | ID '=' ID | subgraph );";
		}
	}

	public static final BitSet FOLLOW_17_in_graph57 = new BitSet(
			new long[] { 0x00000000000C0000L });
	public static final BitSet FOLLOW_set_in_graph65 = new BitSet(
			new long[] { 0x0000000000100010L });
	public static final BitSet FOLLOW_ID_in_graph78 = new BitSet(
			new long[] { 0x0000000000100010L });
	public static final BitSet FOLLOW_body_in_graph86 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_20_in_body98 = new BitSet(
			new long[] { 0x0000000043340010L });
	public static final BitSet FOLLOW_stmt_list_in_body100 = new BitSet(
			new long[] { 0x0000000043340010L });
	public static final BitSet FOLLOW_21_in_body104 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_stmt_in_stmt_list124 = new BitSet(
			new long[] { 0x0000000000400002L });
	public static final BitSet FOLLOW_22_in_stmt_list126 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_node_stmt_in_stmt146 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_edge_stmt_in_stmt154 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_attr_stmt_in_stmt162 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_ID_in_stmt170 = new BitSet(
			new long[] { 0x0000000000800000L });
	public static final BitSet FOLLOW_23_in_stmt172 = new BitSet(
			new long[] { 0x0000000000000010L });
	public static final BitSet FOLLOW_ID_in_stmt174 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_subgraph_in_stmt182 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_set_in_attr_stmt199 = new BitSet(
			new long[] { 0x0000000004000000L });
	public static final BitSet FOLLOW_attr_list_in_attr_stmt211 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_26_in_attr_list234 = new BitSet(
			new long[] { 0x0000000008000010L });
	public static final BitSet FOLLOW_a_list_in_attr_list236 = new BitSet(
			new long[] { 0x0000000008000000L });
	public static final BitSet FOLLOW_27_in_attr_list239 = new BitSet(
			new long[] { 0x0000000004000002L });
	public static final BitSet FOLLOW_ID_in_a_list260 = new BitSet(
			new long[] { 0x0000000010800012L });
	public static final BitSet FOLLOW_23_in_a_list264 = new BitSet(
			new long[] { 0x0000000000000010L });
	public static final BitSet FOLLOW_ID_in_a_list268 = new BitSet(
			new long[] { 0x0000000010000012L });
	public static final BitSet FOLLOW_28_in_a_list273 = new BitSet(
			new long[] { 0x0000000000000012L });
	public static final BitSet FOLLOW_a_list_in_a_list276 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_node_id_in_edge_stmt305 = new BitSet(
			new long[] { 0x0000000000000020L });
	public static final BitSet FOLLOW_edgeRHS_in_edge_stmt312 = new BitSet(
			new long[] { 0x0000000004000002L });
	public static final BitSet FOLLOW_attr_list_in_edge_stmt314 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_EDGEOP_LITERAL_in_edgeRHS342 = new BitSet(
			new long[] { 0x0000000043140010L });
	public static final BitSet FOLLOW_node_id_in_edgeRHS346 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_subgraph_in_edgeRHS351 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_node_id_in_node_stmt370 = new BitSet(
			new long[] { 0x0000000004000002L });
	public static final BitSet FOLLOW_attr_list_in_node_stmt372 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_ID_in_node_id396 = new BitSet(
			new long[] { 0x0000000020000002L });
	public static final BitSet FOLLOW_port_in_node_id398 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_29_in_port419 = new BitSet(
			new long[] { 0x0000000000000010L });
	public static final BitSet FOLLOW_ID_in_port421 = new BitSet(
			new long[] { 0x0000000020000002L });
	public static final BitSet FOLLOW_29_in_port425 = new BitSet(
			new long[] { 0x0000000000000040L });
	public static final BitSet FOLLOW_COMPASS_PT_in_port427 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_29_in_port436 = new BitSet(
			new long[] { 0x0000000000000040L });
	public static final BitSet FOLLOW_COMPASS_PT_in_port438 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_30_in_subgraph457 = new BitSet(
			new long[] { 0x0000000000100010L });
	public static final BitSet FOLLOW_ID_in_subgraph459 = new BitSet(
			new long[] { 0x0000000000100010L });
	public static final BitSet FOLLOW_body_in_subgraph465 = new BitSet(
			new long[] { 0x0000000000000002L });

}