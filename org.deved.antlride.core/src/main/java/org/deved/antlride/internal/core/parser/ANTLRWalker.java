// $ANTLR 3.2 Sep 23, 2009 12:02:23 ANTLRWalker.g 2010-02-24 23:23:34

/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 *******************************************************************************/
package org.deved.antlride.internal.core.parser;

import org.deved.antlride.core.model.*;
import org.deved.antlride.internal.core.model.*;
import java.util.List;
import java.util.ArrayList;


import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;
/** ANTLR v3 tree grammar to walk trees created by ANTLRv3.g */
@SuppressWarnings({"unused"})
public class ANTLRWalker extends BaseTreeParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "DOC_COMMENT", "PARSER", "LEXER", "RULE", "BLOCK", "OPTIONAL", "CLOSURE", "POSITIVE_CLOSURE", "SYNPRED", "RANGE", "CHAR_RANGE", "EPSILON", "ALT", "EOR", "EOB", "EOA", "ID", "ARG", "ARGLIST", "RET", "LEXER_GRAMMAR", "PARSER_GRAMMAR", "TREE_GRAMMAR", "COMBINED_GRAMMAR", "INITACTION", "LABEL", "TEMPLATE", "SCOPE", "SEMPRED", "GATED_SEMPRED", "SYN_SEMPRED", "BACKTRACK_SEMPRED", "FRAGMENT", "TREE_BEGIN", "ROOT", "BANG", "REWRITE", "SEMI", "COLON", "DOT", "ASSIGN", "ELEMENT_OPTIONS", "TOKENS", "TOKEN_REF", "STRING_LITERAL", "ACTION", "OPTIONS", "INT", "ARG_ACTION", "RULE_REF", "DOUBLE_QUOTE_STRING_LITERAL", "DOUBLE_ANGLE_STRING_LITERAL", "SRC", "SL_COMMENT", "ML_COMMENT", "LITERAL_CHAR", "ESC", "XDIGIT", "NESTED_ARG_ACTION", "ACTION_STRING_LITERAL", "ACTION_CHAR_LITERAL", "NESTED_ACTION", "ACTION_ESC", "WS_LOOP", "WS", "'lexer'", "'parser'", "'tree'", "'grammar'", "'import'", "','", "'}'", "'@'", "'::'", "'*'", "'returns'", "'protected'", "'public'", "'private'", "'throws'", "'('", "'|'", "')'", "'catch'", "'finally'", "'+='", "'=>'", "'~'", "'?'", "'+'", "'<'", "'>'", "'$'"
    };
    public static final int BACKTRACK_SEMPRED=35;
    public static final int DOUBLE_ANGLE_STRING_LITERAL=55;
    public static final int LEXER_GRAMMAR=24;
    public static final int EOA=19;
    public static final int ARGLIST=22;
    public static final int EOF=-1;
    public static final int SEMPRED=32;
    public static final int ACTION=49;
    public static final int EOB=18;
    public static final int TOKEN_REF=47;
    public static final int T__93=93;
    public static final int T__94=94;
    public static final int T__91=91;
    public static final int RET=23;
    public static final int T__92=92;
    public static final int STRING_LITERAL=48;
    public static final int T__90=90;
    public static final int ARG=21;
    public static final int EOR=17;
    public static final int ARG_ACTION=52;
    public static final int DOUBLE_QUOTE_STRING_LITERAL=54;
    public static final int NESTED_ARG_ACTION=62;
    public static final int ACTION_CHAR_LITERAL=64;
    public static final int T__96=96;
    public static final int T__95=95;
    public static final int INITACTION=28;
    public static final int T__80=80;
    public static final int T__81=81;
    public static final int T__82=82;
    public static final int RULE=7;
    public static final int T__83=83;
    public static final int ACTION_ESC=66;
    public static final int PARSER_GRAMMAR=25;
    public static final int SRC=56;
    public static final int INT=51;
    public static final int CHAR_RANGE=14;
    public static final int EPSILON=15;
    public static final int T__85=85;
    public static final int T__84=84;
    public static final int T__87=87;
    public static final int T__86=86;
    public static final int T__89=89;
    public static final int REWRITE=40;
    public static final int T__88=88;
    public static final int WS=68;
    public static final int T__71=71;
    public static final int T__72=72;
    public static final int COMBINED_GRAMMAR=27;
    public static final int T__70=70;
    public static final int LEXER=6;
    public static final int SL_COMMENT=57;
    public static final int TREE_GRAMMAR=26;
    public static final int T__76=76;
    public static final int CLOSURE=10;
    public static final int T__75=75;
    public static final int PARSER=5;
    public static final int T__74=74;
    public static final int T__73=73;
    public static final int T__79=79;
    public static final int T__78=78;
    public static final int T__77=77;
    public static final int T__69=69;
    public static final int ELEMENT_OPTIONS=45;
    public static final int NESTED_ACTION=65;
    public static final int ESC=60;
    public static final int FRAGMENT=36;
    public static final int ID=20;
    public static final int TREE_BEGIN=37;
    public static final int ML_COMMENT=58;
    public static final int ALT=16;
    public static final int SCOPE=31;
    public static final int DOC_COMMENT=4;
    public static final int DOT=43;
    public static final int WS_LOOP=67;
    public static final int RANGE=13;
    public static final int TOKENS=46;
    public static final int GATED_SEMPRED=33;
    public static final int LITERAL_CHAR=59;
    public static final int BANG=39;
    public static final int ACTION_STRING_LITERAL=63;
    public static final int ROOT=38;
    public static final int SEMI=41;
    public static final int RULE_REF=53;
    public static final int SYNPRED=12;
    public static final int OPTIONAL=9;
    public static final int COLON=42;
    public static final int LABEL=29;
    public static final int TEMPLATE=30;
    public static final int SYN_SEMPRED=34;
    public static final int XDIGIT=61;
    public static final int BLOCK=8;
    public static final int ASSIGN=44;
    public static final int POSITIVE_CLOSURE=11;
    public static final int OPTIONS=50;

    // delegates
    // delegators


        public ANTLRWalker(TreeNodeStream input) {
            this(input, new RecognizerSharedState());
        }
        public ANTLRWalker(TreeNodeStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return ANTLRWalker.tokenNames; }
    public String getGrammarFileName() { return "ANTLRWalker.g"; }


    	private GrammarBuilder builder;	
    	private GrammarBuilderFactory factory;
    	
     	public void reportError(Exception ex) {
      		emitErrorMessage(ex.getClass().getName() + ": " + ex.getMessage());
      	}



    // $ANTLR start "grammarDef"
    // ANTLRWalker.g:64:1: grammarDef[GrammarBuilderFactory factory] : ^( grammarType ID ( DOC_COMMENT )? ( optionsSpec[builder] )? ( delegateGrammars )? ( tokensSpec )? ( attrScope )* ( action )* ( rule )+ ) ;
    public final void grammarDef(GrammarBuilderFactory factory) throws RecognitionException {
        CommonTree ID1=null;
        CommonTree DOC_COMMENT2=null;


        	this.factory = factory;
        	builder = factory.newGrammarBuilder();

        try {
            // ANTLRWalker.g:69:3: ( ^( grammarType ID ( DOC_COMMENT )? ( optionsSpec[builder] )? ( delegateGrammars )? ( tokensSpec )? ( attrScope )* ( action )* ( rule )+ ) )
            // ANTLRWalker.g:70:3: ^( grammarType ID ( DOC_COMMENT )? ( optionsSpec[builder] )? ( delegateGrammars )? ( tokensSpec )? ( attrScope )* ( action )* ( rule )+ )
            {
            pushFollow(FOLLOW_grammarType_in_grammarDef93);
            grammarType();

            state._fsp--;


            match(input, Token.DOWN, null); 
            ID1=(CommonTree)match(input,ID,FOLLOW_ID_in_grammarDef95); 
            builder.name(ID1);
            // ANTLRWalker.g:72:5: ( DOC_COMMENT )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==DOC_COMMENT) ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // ANTLRWalker.g:73:7: DOC_COMMENT
                    {
                    DOC_COMMENT2=(CommonTree)match(input,DOC_COMMENT,FOLLOW_DOC_COMMENT_in_grammarDef111); 
                    builder.documentation(DOC_COMMENT2);

                    }
                    break;

            }

            // ANTLRWalker.g:75:5: ( optionsSpec[builder] )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==OPTIONS) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // ANTLRWalker.g:75:5: optionsSpec[builder]
                    {
                    pushFollow(FOLLOW_optionsSpec_in_grammarDef126);
                    optionsSpec(builder);

                    state._fsp--;


                    }
                    break;

            }

            // ANTLRWalker.g:75:27: ( delegateGrammars )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==73) ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // ANTLRWalker.g:75:27: delegateGrammars
                    {
                    pushFollow(FOLLOW_delegateGrammars_in_grammarDef130);
                    delegateGrammars();

                    state._fsp--;


                    }
                    break;

            }

            // ANTLRWalker.g:75:45: ( tokensSpec )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==TOKENS) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // ANTLRWalker.g:75:45: tokensSpec
                    {
                    pushFollow(FOLLOW_tokensSpec_in_grammarDef133);
                    tokensSpec();

                    state._fsp--;


                    }
                    break;

            }

            // ANTLRWalker.g:75:57: ( attrScope )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0==SCOPE) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // ANTLRWalker.g:75:57: attrScope
            	    {
            	    pushFollow(FOLLOW_attrScope_in_grammarDef136);
            	    attrScope();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);

            builder.scopes();
            // ANTLRWalker.g:75:88: ( action )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0==76) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // ANTLRWalker.g:75:88: action
            	    {
            	    pushFollow(FOLLOW_action_in_grammarDef141);
            	    action();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop6;
                }
            } while (true);

            builder.actions();
            // ANTLRWalker.g:75:117: ( rule )+
            int cnt7=0;
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==RULE) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // ANTLRWalker.g:75:117: rule
            	    {
            	    pushFollow(FOLLOW_rule_in_grammarDef146);
            	    rule();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    if ( cnt7 >= 1 ) break loop7;
                        EarlyExitException eee =
                            new EarlyExitException(7, input);
                        throw eee;
                }
                cnt7++;
            } while (true);

            builder.rules();

            match(input, Token.UP, null); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "grammarDef"


    // $ANTLR start "grammarType"
    // ANTLRWalker.g:79:1: grammarType : ( LEXER_GRAMMAR | PARSER_GRAMMAR | TREE_GRAMMAR | COMBINED_GRAMMAR );
    public final void grammarType() throws RecognitionException {
        try {
            // ANTLRWalker.g:80:3: ( LEXER_GRAMMAR | PARSER_GRAMMAR | TREE_GRAMMAR | COMBINED_GRAMMAR )
            int alt8=4;
            switch ( input.LA(1) ) {
            case LEXER_GRAMMAR:
                {
                alt8=1;
                }
                break;
            case PARSER_GRAMMAR:
                {
                alt8=2;
                }
                break;
            case TREE_GRAMMAR:
                {
                alt8=3;
                }
                break;
            case COMBINED_GRAMMAR:
                {
                alt8=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;
            }

            switch (alt8) {
                case 1 :
                    // ANTLRWalker.g:81:3: LEXER_GRAMMAR
                    {
                    match(input,LEXER_GRAMMAR,FOLLOW_LEXER_GRAMMAR_in_grammarType169); 
                    builder.type(GrammarType.LEXER);

                    }
                    break;
                case 2 :
                    // ANTLRWalker.g:82:5: PARSER_GRAMMAR
                    {
                    match(input,PARSER_GRAMMAR,FOLLOW_PARSER_GRAMMAR_in_grammarType177); 
                    builder.type(GrammarType.PARSER);

                    }
                    break;
                case 3 :
                    // ANTLRWalker.g:83:5: TREE_GRAMMAR
                    {
                    match(input,TREE_GRAMMAR,FOLLOW_TREE_GRAMMAR_in_grammarType185); 
                    builder.type(GrammarType.TREE_PARSER);

                    }
                    break;
                case 4 :
                    // ANTLRWalker.g:84:5: COMBINED_GRAMMAR
                    {
                    match(input,COMBINED_GRAMMAR,FOLLOW_COMBINED_GRAMMAR_in_grammarType193); 
                    builder.type(GrammarType.COMBINED);

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "grammarType"


    // $ANTLR start "delegateGrammars"
    // ANTLRWalker.g:87:1: delegateGrammars : ^(impStart= 'import' ( delegateGrammar )+ impEnd= ';' ) ;
    public final void delegateGrammars() throws RecognitionException {
        CommonTree impStart=null;
        CommonTree impEnd=null;

        try {
            // ANTLRWalker.g:88:3: ( ^(impStart= 'import' ( delegateGrammar )+ impEnd= ';' ) )
            // ANTLRWalker.g:89:3: ^(impStart= 'import' ( delegateGrammar )+ impEnd= ';' )
            {
            impStart=(CommonTree)match(input,73,FOLLOW_73_in_delegateGrammars213); 

            match(input, Token.DOWN, null); 
            // ANTLRWalker.g:89:23: ( delegateGrammar )+
            int cnt9=0;
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0==ID||LA9_0==ASSIGN) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // ANTLRWalker.g:89:23: delegateGrammar
            	    {
            	    pushFollow(FOLLOW_delegateGrammar_in_delegateGrammars215);
            	    delegateGrammar();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    if ( cnt9 >= 1 ) break loop9;
                        EarlyExitException eee =
                            new EarlyExitException(9, input);
                        throw eee;
                }
                cnt9++;
            } while (true);

            impEnd=(CommonTree)match(input,SEMI,FOLLOW_SEMI_in_delegateGrammars220); 

            match(input, Token.UP, null); 

              			builder.imports(impStart, impEnd);
              		

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "delegateGrammars"


    // $ANTLR start "delegateGrammar"
    // ANTLRWalker.g:95:1: delegateGrammar : ( ^( '=' label= ID dg= ID ) | ID );
    public final void delegateGrammar() throws RecognitionException {
        CommonTree label=null;
        CommonTree dg=null;
        CommonTree ID3=null;

        try {
            // ANTLRWalker.g:96:3: ( ^( '=' label= ID dg= ID ) | ID )
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==ASSIGN) ) {
                alt10=1;
            }
            else if ( (LA10_0==ID) ) {
                alt10=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 10, 0, input);

                throw nvae;
            }
            switch (alt10) {
                case 1 :
                    // ANTLRWalker.g:97:3: ^( '=' label= ID dg= ID )
                    {
                    match(input,ASSIGN,FOLLOW_ASSIGN_in_delegateGrammar241); 

                    match(input, Token.DOWN, null); 
                    label=(CommonTree)match(input,ID,FOLLOW_ID_in_delegateGrammar245); 
                    dg=(CommonTree)match(input,ID,FOLLOW_ID_in_delegateGrammar249); 

                    match(input, Token.UP, null); 
                    builder.importGrammar(dg, label);

                    }
                    break;
                case 2 :
                    // ANTLRWalker.g:99:5: ID
                    {
                    ID3=(CommonTree)match(input,ID,FOLLOW_ID_in_delegateGrammar260); 
                    builder.importGrammar(ID3);

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "delegateGrammar"


    // $ANTLR start "tokensSpec"
    // ANTLRWalker.g:102:1: tokensSpec : ^( TOKENS ( tokenSpec )* toksEnd= '}' ) ;
    public final void tokensSpec() throws RecognitionException {
        CommonTree toksEnd=null;
        CommonTree TOKENS4=null;

        try {
            // ANTLRWalker.g:103:3: ( ^( TOKENS ( tokenSpec )* toksEnd= '}' ) )
            // ANTLRWalker.g:104:3: ^( TOKENS ( tokenSpec )* toksEnd= '}' )
            {
            TOKENS4=(CommonTree)match(input,TOKENS,FOLLOW_TOKENS_in_tokensSpec278); 

            match(input, Token.DOWN, null); 
            // ANTLRWalker.g:104:12: ( tokenSpec )*
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( (LA11_0==ASSIGN||LA11_0==TOKEN_REF) ) {
                    alt11=1;
                }


                switch (alt11) {
            	case 1 :
            	    // ANTLRWalker.g:104:12: tokenSpec
            	    {
            	    pushFollow(FOLLOW_tokenSpec_in_tokensSpec280);
            	    tokenSpec();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop11;
                }
            } while (true);

            toksEnd=(CommonTree)match(input,75,FOLLOW_75_in_tokensSpec285); 

            match(input, Token.UP, null); 
            builder.tokens(TOKENS4, toksEnd);

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "tokensSpec"


    // $ANTLR start "tokenSpec"
    // ANTLRWalker.g:108:1: tokenSpec : ( ^( '=' TOKEN_REF STRING_LITERAL ) | TOKEN_REF );
    public final void tokenSpec() throws RecognitionException {
        CommonTree TOKEN_REF5=null;
        CommonTree STRING_LITERAL6=null;
        CommonTree TOKEN_REF7=null;

        try {
            // ANTLRWalker.g:109:3: ( ^( '=' TOKEN_REF STRING_LITERAL ) | TOKEN_REF )
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==ASSIGN) ) {
                alt12=1;
            }
            else if ( (LA12_0==TOKEN_REF) ) {
                alt12=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 12, 0, input);

                throw nvae;
            }
            switch (alt12) {
                case 1 :
                    // ANTLRWalker.g:110:3: ^( '=' TOKEN_REF STRING_LITERAL )
                    {
                    match(input,ASSIGN,FOLLOW_ASSIGN_in_tokenSpec306); 

                    match(input, Token.DOWN, null); 
                    TOKEN_REF5=(CommonTree)match(input,TOKEN_REF,FOLLOW_TOKEN_REF_in_tokenSpec308); 
                    STRING_LITERAL6=(CommonTree)match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_tokenSpec310); 

                    match(input, Token.UP, null); 
                    builder.token(TOKEN_REF5, STRING_LITERAL6);

                    }
                    break;
                case 2 :
                    // ANTLRWalker.g:112:5: TOKEN_REF
                    {
                    TOKEN_REF7=(CommonTree)match(input,TOKEN_REF,FOLLOW_TOKEN_REF_in_tokenSpec321); 
                    builder.token(TOKEN_REF7);

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "tokenSpec"


    // $ANTLR start "attrScope"
    // ANTLRWalker.g:115:1: attrScope : ^( 'scope' ID ACTION ) ;
    public final void attrScope() throws RecognitionException {
        CommonTree ID8=null;
        CommonTree ACTION9=null;

        try {
            // ANTLRWalker.g:116:3: ( ^( 'scope' ID ACTION ) )
            // ANTLRWalker.g:117:3: ^( 'scope' ID ACTION )
            {
            match(input,SCOPE,FOLLOW_SCOPE_in_attrScope339); 

            match(input, Token.DOWN, null); 
            ID8=(CommonTree)match(input,ID,FOLLOW_ID_in_attrScope341); 
            ACTION9=(CommonTree)match(input,ACTION,FOLLOW_ACTION_in_attrScope343); 

            match(input, Token.UP, null); 
            builder.scope(ID8, ACTION9);

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "attrScope"


    // $ANTLR start "action"
    // ANTLRWalker.g:121:1: action : ( ^(amp= '@' scp= ID name= ID act= ACTION ) | ^(amp= '@' name= ID act= ACTION ) );
    public final void action() throws RecognitionException {
        CommonTree amp=null;
        CommonTree scp=null;
        CommonTree name=null;
        CommonTree act=null;

        try {
            // ANTLRWalker.g:122:3: ( ^(amp= '@' scp= ID name= ID act= ACTION ) | ^(amp= '@' name= ID act= ACTION ) )
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==76) ) {
                int LA13_1 = input.LA(2);

                if ( (LA13_1==DOWN) ) {
                    int LA13_2 = input.LA(3);

                    if ( (LA13_2==ID) ) {
                        int LA13_3 = input.LA(4);

                        if ( (LA13_3==ID) ) {
                            alt13=1;
                        }
                        else if ( (LA13_3==ACTION) ) {
                            alt13=2;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("", 13, 3, input);

                            throw nvae;
                        }
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 13, 2, input);

                        throw nvae;
                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 13, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 13, 0, input);

                throw nvae;
            }
            switch (alt13) {
                case 1 :
                    // ANTLRWalker.g:123:3: ^(amp= '@' scp= ID name= ID act= ACTION )
                    {
                    amp=(CommonTree)match(input,76,FOLLOW_76_in_action366); 

                    match(input, Token.DOWN, null); 
                    scp=(CommonTree)match(input,ID,FOLLOW_ID_in_action370); 
                    name=(CommonTree)match(input,ID,FOLLOW_ID_in_action374); 
                    act=(CommonTree)match(input,ACTION,FOLLOW_ACTION_in_action378); 

                    match(input, Token.UP, null); 
                    builder.action(amp, scp, name, act);

                    }
                    break;
                case 2 :
                    // ANTLRWalker.g:126:3: ^(amp= '@' name= ID act= ACTION )
                    {
                    amp=(CommonTree)match(input,76,FOLLOW_76_in_action394); 

                    match(input, Token.DOWN, null); 
                    name=(CommonTree)match(input,ID,FOLLOW_ID_in_action398); 
                    act=(CommonTree)match(input,ACTION,FOLLOW_ACTION_in_action402); 

                    match(input, Token.UP, null); 
                    builder.action(amp, name, act);

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "action"


    // $ANTLR start "optionsSpec"
    // ANTLRWalker.g:130:1: optionsSpec[OptionsAware options] : ^( OPTIONS ( option[options] )* optEnd= '}' ) ;
    public final void optionsSpec(OptionsAware options) throws RecognitionException {
        CommonTree optEnd=null;
        CommonTree OPTIONS10=null;

        try {
            // ANTLRWalker.g:131:3: ( ^( OPTIONS ( option[options] )* optEnd= '}' ) )
            // ANTLRWalker.g:132:3: ^( OPTIONS ( option[options] )* optEnd= '}' )
            {
            OPTIONS10=(CommonTree)match(input,OPTIONS,FOLLOW_OPTIONS_in_optionsSpec424); 

            match(input, Token.DOWN, null); 
            // ANTLRWalker.g:132:13: ( option[options] )*
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( (LA14_0==ASSIGN) ) {
                    alt14=1;
                }


                switch (alt14) {
            	case 1 :
            	    // ANTLRWalker.g:132:13: option[options]
            	    {
            	    pushFollow(FOLLOW_option_in_optionsSpec426);
            	    option(options);

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop14;
                }
            } while (true);

            optEnd=(CommonTree)match(input,75,FOLLOW_75_in_optionsSpec432); 

            match(input, Token.UP, null); 
            options.options(OPTIONS10, optEnd);

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "optionsSpec"


    // $ANTLR start "option"
    // ANTLRWalker.g:136:1: option[OptionsAware options] : ^( '=' ID optval= optionValue ) ;
    public final void option(OptionsAware options) throws RecognitionException {
        CommonTree ID11=null;
        CommonTree optval = null;


        try {
            // ANTLRWalker.g:137:3: ( ^( '=' ID optval= optionValue ) )
            // ANTLRWalker.g:138:3: ^( '=' ID optval= optionValue )
            {
            match(input,ASSIGN,FOLLOW_ASSIGN_in_option454); 

            match(input, Token.DOWN, null); 
            ID11=(CommonTree)match(input,ID,FOLLOW_ID_in_option456); 
            pushFollow(FOLLOW_optionValue_in_option460);
            optval=optionValue();

            state._fsp--;

            options.option(ID11, optval);

            match(input, Token.UP, null); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "option"


    // $ANTLR start "optionValue"
    // ANTLRWalker.g:141:1: optionValue returns [CommonTree token = null;] : ( ID | STRING_LITERAL | INT );
    public final CommonTree optionValue() throws RecognitionException {
        CommonTree token =  null;;

        CommonTree ID12=null;
        CommonTree STRING_LITERAL13=null;
        CommonTree INT14=null;

        try {
            // ANTLRWalker.g:142:3: ( ID | STRING_LITERAL | INT )
            int alt15=3;
            switch ( input.LA(1) ) {
            case ID:
                {
                alt15=1;
                }
                break;
            case STRING_LITERAL:
                {
                alt15=2;
                }
                break;
            case INT:
                {
                alt15=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 15, 0, input);

                throw nvae;
            }

            switch (alt15) {
                case 1 :
                    // ANTLRWalker.g:143:3: ID
                    {
                    ID12=(CommonTree)match(input,ID,FOLLOW_ID_in_optionValue482); 
                    token = ID12;

                    }
                    break;
                case 2 :
                    // ANTLRWalker.g:144:5: STRING_LITERAL
                    {
                    STRING_LITERAL13=(CommonTree)match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_optionValue490); 
                    token = STRING_LITERAL13;

                    }
                    break;
                case 3 :
                    // ANTLRWalker.g:145:5: INT
                    {
                    INT14=(CommonTree)match(input,INT,FOLLOW_INT_in_optionValue498); 
                    token = INT14;

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return token;
    }
    // $ANTLR end "optionValue"


    // $ANTLR start "rule"
    // ANTLRWalker.g:148:1: rule : ^( RULE (doc= DOC_COMMENT )? ID (mod= modifier )? ( '!' )? ( ^( ARG params= ARG_ACTION ) )? ( ^( RET rets= ARG_ACTION ) )? ( throwsSpec[builder.rule()] )? ( optionsSpec[builder.rule()] )? ( ruleScopeSpec )? ( ruleAction )* COLON body= altList ( exceptionGroup[builder.rule()] )? EOR ) ;
    public final void rule() throws RecognitionException {
        CommonTree doc=null;
        CommonTree params=null;
        CommonTree rets=null;
        CommonTree ID15=null;
        CommonTree COLON16=null;
        CommonTree EOR17=null;
        IRule.RuleAccessModifier mod = null;

        BlockBuilder body = null;



        	builder.beginRule();

        try {
            // ANTLRWalker.g:155:3: ( ^( RULE (doc= DOC_COMMENT )? ID (mod= modifier )? ( '!' )? ( ^( ARG params= ARG_ACTION ) )? ( ^( RET rets= ARG_ACTION ) )? ( throwsSpec[builder.rule()] )? ( optionsSpec[builder.rule()] )? ( ruleScopeSpec )? ( ruleAction )* COLON body= altList ( exceptionGroup[builder.rule()] )? EOR ) )
            // ANTLRWalker.g:156:3: ^( RULE (doc= DOC_COMMENT )? ID (mod= modifier )? ( '!' )? ( ^( ARG params= ARG_ACTION ) )? ( ^( RET rets= ARG_ACTION ) )? ( throwsSpec[builder.rule()] )? ( optionsSpec[builder.rule()] )? ( ruleScopeSpec )? ( ruleAction )* COLON body= altList ( exceptionGroup[builder.rule()] )? EOR )
            {
            match(input,RULE,FOLLOW_RULE_in_rule531); 

            match(input, Token.DOWN, null); 
            // ANTLRWalker.g:158:5: (doc= DOC_COMMENT )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==DOC_COMMENT) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // ANTLRWalker.g:159:7: doc= DOC_COMMENT
                    {
                    doc=(CommonTree)match(input,DOC_COMMENT,FOLLOW_DOC_COMMENT_in_rule547); 
                    builder.rule().documentation(doc);

                    }
                    break;

            }

            ID15=(CommonTree)match(input,ID,FOLLOW_ID_in_rule562); 
            builder.rule().name(ID15).ruleStart(ID15);
            // ANTLRWalker.g:162:5: (mod= modifier )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==FRAGMENT||(LA17_0>=80 && LA17_0<=82)) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // ANTLRWalker.g:163:7: mod= modifier
                    {
                    pushFollow(FOLLOW_modifier_in_rule580);
                    mod=modifier();

                    state._fsp--;

                    builder.rule().accessModifier(mod).ruleStart(ID15);

                    }
                    break;

            }

            // ANTLRWalker.g:165:5: ( '!' )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==BANG) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // ANTLRWalker.g:166:7: '!'
                    {
                    match(input,BANG,FOLLOW_BANG_in_rule603); 
                    builder.rule().astSuffix(ASTSuffix.BANG);

                    }
                    break;

            }

            // ANTLRWalker.g:168:5: ( ^( ARG params= ARG_ACTION ) )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==ARG) ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // ANTLRWalker.g:169:7: ^( ARG params= ARG_ACTION )
                    {
                    match(input,ARG,FOLLOW_ARG_in_rule627); 

                    match(input, Token.DOWN, null); 
                    params=(CommonTree)match(input,ARG_ACTION,FOLLOW_ARG_ACTION_in_rule631); 
                    builder.rule().parameters(params);

                    match(input, Token.UP, null); 

                    }
                    break;

            }

            // ANTLRWalker.g:171:5: ( ^( RET rets= ARG_ACTION ) )?
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==RET) ) {
                alt20=1;
            }
            switch (alt20) {
                case 1 :
                    // ANTLRWalker.g:172:7: ^( RET rets= ARG_ACTION )
                    {
                    match(input,RET,FOLLOW_RET_in_rule656); 

                    match(input, Token.DOWN, null); 
                    rets=(CommonTree)match(input,ARG_ACTION,FOLLOW_ARG_ACTION_in_rule660); 
                    builder.rule().returns(rets);

                    match(input, Token.UP, null); 

                    }
                    break;

            }

            // ANTLRWalker.g:174:5: ( throwsSpec[builder.rule()] )?
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==83) ) {
                alt21=1;
            }
            switch (alt21) {
                case 1 :
                    // ANTLRWalker.g:174:5: throwsSpec[builder.rule()]
                    {
                    pushFollow(FOLLOW_throwsSpec_in_rule676);
                    throwsSpec(builder.rule());

                    state._fsp--;


                    }
                    break;

            }

            // ANTLRWalker.g:174:33: ( optionsSpec[builder.rule()] )?
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( (LA22_0==OPTIONS) ) {
                alt22=1;
            }
            switch (alt22) {
                case 1 :
                    // ANTLRWalker.g:174:33: optionsSpec[builder.rule()]
                    {
                    pushFollow(FOLLOW_optionsSpec_in_rule680);
                    optionsSpec(builder.rule());

                    state._fsp--;


                    }
                    break;

            }

            // ANTLRWalker.g:174:62: ( ruleScopeSpec )?
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( (LA23_0==SCOPE) ) {
                alt23=1;
            }
            switch (alt23) {
                case 1 :
                    // ANTLRWalker.g:174:62: ruleScopeSpec
                    {
                    pushFollow(FOLLOW_ruleScopeSpec_in_rule684);
                    ruleScopeSpec();

                    state._fsp--;


                    }
                    break;

            }

            // ANTLRWalker.g:174:77: ( ruleAction )*
            loop24:
            do {
                int alt24=2;
                int LA24_0 = input.LA(1);

                if ( (LA24_0==76) ) {
                    alt24=1;
                }


                switch (alt24) {
            	case 1 :
            	    // ANTLRWalker.g:174:77: ruleAction
            	    {
            	    pushFollow(FOLLOW_ruleAction_in_rule687);
            	    ruleAction();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop24;
                }
            } while (true);

            COLON16=(CommonTree)match(input,COLON,FOLLOW_COLON_in_rule690); 
            pushFollow(FOLLOW_altList_in_rule694);
            body=altList();

            state._fsp--;

            builder.rule().body(body).bodyStart(COLON16);
            // ANTLRWalker.g:174:155: ( exceptionGroup[builder.rule()] )?
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( ((LA25_0>=87 && LA25_0<=88)) ) {
                alt25=1;
            }
            switch (alt25) {
                case 1 :
                    // ANTLRWalker.g:174:155: exceptionGroup[builder.rule()]
                    {
                    pushFollow(FOLLOW_exceptionGroup_in_rule698);
                    exceptionGroup(builder.rule());

                    state._fsp--;


                    }
                    break;

            }

            EOR17=(CommonTree)match(input,EOR,FOLLOW_EOR_in_rule702); 
            builder.rule().bodyEnd(EOR17).ruleEnd(EOR17);

            match(input, Token.UP, null); 

            }


            	builder.endRule();

        }
        catch (RecognitionException re) {

                reportError(re);
                consumeUntil(input, SEMI); // throw away all until ';'
                input.consume(); // eat the ';'

        }
        catch (Exception ex) {

                reportError(ex);
                consumeUntil(input, SEMI); // throw away all until ';'
                input.consume(); // eat the ';'

        }
        finally {
        }
        return ;
    }
    // $ANTLR end "rule"


    // $ANTLR start "modifier"
    // ANTLRWalker.g:188:1: modifier returns [IRule.RuleAccessModifier mod = IRule.RuleAccessModifier.PUBLIC;] : ( 'protected' | 'public' | 'private' | 'fragment' );
    public final IRule.RuleAccessModifier modifier() throws RecognitionException {
        IRule.RuleAccessModifier mod =  IRule.RuleAccessModifier.PUBLIC;;

        try {
            // ANTLRWalker.g:189:3: ( 'protected' | 'public' | 'private' | 'fragment' )
            int alt26=4;
            switch ( input.LA(1) ) {
            case 80:
                {
                alt26=1;
                }
                break;
            case 81:
                {
                alt26=2;
                }
                break;
            case 82:
                {
                alt26=3;
                }
                break;
            case FRAGMENT:
                {
                alt26=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 26, 0, input);

                throw nvae;
            }

            switch (alt26) {
                case 1 :
                    // ANTLRWalker.g:190:3: 'protected'
                    {
                    match(input,80,FOLLOW_80_in_modifier740); 
                    mod = IRule.RuleAccessModifier.PROTECTED;

                    }
                    break;
                case 2 :
                    // ANTLRWalker.g:191:5: 'public'
                    {
                    match(input,81,FOLLOW_81_in_modifier748); 
                    mod = IRule.RuleAccessModifier.PUBLIC;

                    }
                    break;
                case 3 :
                    // ANTLRWalker.g:192:5: 'private'
                    {
                    match(input,82,FOLLOW_82_in_modifier756); 
                    mod = IRule.RuleAccessModifier.PRIVATE;

                    }
                    break;
                case 4 :
                    // ANTLRWalker.g:193:5: 'fragment'
                    {
                    match(input,FRAGMENT,FOLLOW_FRAGMENT_in_modifier764); 
                    mod = IRule.RuleAccessModifier.FRAGMENT;

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return mod;
    }
    // $ANTLR end "modifier"


    // $ANTLR start "ruleAction"
    // ANTLRWalker.g:196:1: ruleAction : ^( '@' ID ACTION ) ;
    public final void ruleAction() throws RecognitionException {
        CommonTree ID18=null;
        CommonTree ACTION19=null;

        try {
            // ANTLRWalker.g:198:3: ( ^( '@' ID ACTION ) )
            // ANTLRWalker.g:199:3: ^( '@' ID ACTION )
            {
            match(input,76,FOLLOW_76_in_ruleAction784); 

            match(input, Token.DOWN, null); 
            ID18=(CommonTree)match(input,ID,FOLLOW_ID_in_ruleAction786); 
            ACTION19=(CommonTree)match(input,ACTION,FOLLOW_ACTION_in_ruleAction788); 

            match(input, Token.UP, null); 
            builder.rule().action(ID18, ACTION19);

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "ruleAction"


    // $ANTLR start "throwsSpec"
    // ANTLRWalker.g:203:1: throwsSpec[RuleBuilder rule] : ^( 'throws' (exception+= ID )+ ) ;
    public final void throwsSpec(RuleBuilder rule) throws RecognitionException {
        CommonTree exception=null;
        List<CommonTree> list_exception=null;

        try {
            // ANTLRWalker.g:204:3: ( ^( 'throws' (exception+= ID )+ ) )
            // ANTLRWalker.g:205:3: ^( 'throws' (exception+= ID )+ )
            {
            match(input,83,FOLLOW_83_in_throwsSpec810); 

            match(input, Token.DOWN, null); 
            // ANTLRWalker.g:205:23: (exception+= ID )+
            int cnt27=0;
            loop27:
            do {
                int alt27=2;
                int LA27_0 = input.LA(1);

                if ( (LA27_0==ID) ) {
                    alt27=1;
                }


                switch (alt27) {
            	case 1 :
            	    // ANTLRWalker.g:205:23: exception+= ID
            	    {
            	    exception=(CommonTree)match(input,ID,FOLLOW_ID_in_throwsSpec814); 
            	    if (list_exception==null) list_exception=new ArrayList<CommonTree>();
            	    list_exception.add(exception);


            	    }
            	    break;

            	default :
            	    if ( cnt27 >= 1 ) break loop27;
                        EarlyExitException eee =
                            new EarlyExitException(27, input);
                        throw eee;
                }
                cnt27++;
            } while (true);


            match(input, Token.UP, null); 
            rule.ruleThrows(list_exception);

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "throwsSpec"


    // $ANTLR start "ruleScopeSpec"
    // ANTLRWalker.g:209:1: ruleScopeSpec : ( ^(s= 'scope' ACTION ) | ^(s= 'scope' ACTION ( ID )+ ) | ^(s= 'scope' ( ID )+ ) );
    public final void ruleScopeSpec() throws RecognitionException {
        CommonTree s=null;
        CommonTree ACTION20=null;
        CommonTree ACTION21=null;
        CommonTree ID22=null;
        CommonTree ID23=null;

        try {
            // ANTLRWalker.g:210:3: ( ^(s= 'scope' ACTION ) | ^(s= 'scope' ACTION ( ID )+ ) | ^(s= 'scope' ( ID )+ ) )
            int alt30=3;
            int LA30_0 = input.LA(1);

            if ( (LA30_0==SCOPE) ) {
                int LA30_1 = input.LA(2);

                if ( (LA30_1==DOWN) ) {
                    int LA30_2 = input.LA(3);

                    if ( (LA30_2==ACTION) ) {
                        int LA30_3 = input.LA(4);

                        if ( (LA30_3==UP) ) {
                            alt30=1;
                        }
                        else if ( (LA30_3==ID) ) {
                            alt30=2;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("", 30, 3, input);

                            throw nvae;
                        }
                    }
                    else if ( (LA30_2==ID) ) {
                        alt30=3;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 30, 2, input);

                        throw nvae;
                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 30, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 30, 0, input);

                throw nvae;
            }
            switch (alt30) {
                case 1 :
                    // ANTLRWalker.g:211:3: ^(s= 'scope' ACTION )
                    {
                    s=(CommonTree)match(input,SCOPE,FOLLOW_SCOPE_in_ruleScopeSpec838); 

                    match(input, Token.DOWN, null); 
                    ACTION20=(CommonTree)match(input,ACTION,FOLLOW_ACTION_in_ruleScopeSpec840); 

                    match(input, Token.UP, null); 
                    builder.rule().scope(s, ACTION20);

                    }
                    break;
                case 2 :
                    // ANTLRWalker.g:214:3: ^(s= 'scope' ACTION ( ID )+ )
                    {
                    s=(CommonTree)match(input,SCOPE,FOLLOW_SCOPE_in_ruleScopeSpec861); 

                    match(input, Token.DOWN, null); 
                    ACTION21=(CommonTree)match(input,ACTION,FOLLOW_ACTION_in_ruleScopeSpec863); 
                    builder.rule().scope(s, ACTION21);
                    // ANTLRWalker.g:216:5: ( ID )+
                    int cnt28=0;
                    loop28:
                    do {
                        int alt28=2;
                        int LA28_0 = input.LA(1);

                        if ( (LA28_0==ID) ) {
                            alt28=1;
                        }


                        switch (alt28) {
                    	case 1 :
                    	    // ANTLRWalker.g:217:7: ID
                    	    {
                    	    ID22=(CommonTree)match(input,ID,FOLLOW_ID_in_ruleScopeSpec879); 
                    	    builder.rule().scopeReference(ID22);

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt28 >= 1 ) break loop28;
                                EarlyExitException eee =
                                    new EarlyExitException(28, input);
                                throw eee;
                        }
                        cnt28++;
                    } while (true);


                    match(input, Token.UP, null); 

                    }
                    break;
                case 3 :
                    // ANTLRWalker.g:221:3: ^(s= 'scope' ( ID )+ )
                    {
                    s=(CommonTree)match(input,SCOPE,FOLLOW_SCOPE_in_ruleScopeSpec909); 

                    match(input, Token.DOWN, null); 
                    // ANTLRWalker.g:223:5: ( ID )+
                    int cnt29=0;
                    loop29:
                    do {
                        int alt29=2;
                        int LA29_0 = input.LA(1);

                        if ( (LA29_0==ID) ) {
                            alt29=1;
                        }


                        switch (alt29) {
                    	case 1 :
                    	    // ANTLRWalker.g:224:7: ID
                    	    {
                    	    ID23=(CommonTree)match(input,ID,FOLLOW_ID_in_ruleScopeSpec923); 
                    	    builder.rule().scopeReference(ID23);

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt29 >= 1 ) break loop29;
                                EarlyExitException eee =
                                    new EarlyExitException(29, input);
                                throw eee;
                        }
                        cnt29++;
                    } while (true);


                    match(input, Token.UP, null); 

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "ruleScopeSpec"


    // $ANTLR start "block"
    // ANTLRWalker.g:229:1: block returns [BlockBuilder block = factory.newBlockBuilder();] : ^( BLOCK ( optionsSpec[block] )? (stt= alternative rwstt= rewrite )+ EOB ) ;
    public final BlockBuilder block() throws RecognitionException {
        BlockBuilder block =  factory.newBlockBuilder();;

        CommonTree BLOCK24=null;
        CommonTree EOB25=null;
        AlternativeBuilder stt = null;

        StatementBuilder rwstt = null;


        try {
            // ANTLRWalker.g:233:3: ( ^( BLOCK ( optionsSpec[block] )? (stt= alternative rwstt= rewrite )+ EOB ) )
            // ANTLRWalker.g:234:3: ^( BLOCK ( optionsSpec[block] )? (stt= alternative rwstt= rewrite )+ EOB )
            {
            BLOCK24=(CommonTree)match(input,BLOCK,FOLLOW_BLOCK_in_block964); 

            match(input, Token.DOWN, null); 
            // ANTLRWalker.g:235:11: ( optionsSpec[block] )?
            int alt31=2;
            int LA31_0 = input.LA(1);

            if ( (LA31_0==OPTIONS) ) {
                alt31=1;
            }
            switch (alt31) {
                case 1 :
                    // ANTLRWalker.g:235:11: optionsSpec[block]
                    {
                    pushFollow(FOLLOW_optionsSpec_in_block966);
                    optionsSpec(block);

                    state._fsp--;


                    }
                    break;

            }

            // ANTLRWalker.g:236:5: (stt= alternative rwstt= rewrite )+
            int cnt32=0;
            loop32:
            do {
                int alt32=2;
                int LA32_0 = input.LA(1);

                if ( (LA32_0==ALT) ) {
                    alt32=1;
                }


                switch (alt32) {
            	case 1 :
            	    // ANTLRWalker.g:237:7: stt= alternative rwstt= rewrite
            	    {
            	    pushFollow(FOLLOW_alternative_in_block984);
            	    stt=alternative();

            	    state._fsp--;

            	    block.statement(stt);
            	    pushFollow(FOLLOW_rewrite_in_block990);
            	    rwstt=rewrite();

            	    state._fsp--;

            	    if(rwstt!=null)stt.rewrite(rwstt);

            	    }
            	    break;

            	default :
            	    if ( cnt32 >= 1 ) break loop32;
                        EarlyExitException eee =
                            new EarlyExitException(32, input);
                        throw eee;
                }
                cnt32++;
            } while (true);

            EOB25=(CommonTree)match(input,EOB,FOLLOW_EOB_in_block1005); 

            match(input, Token.UP, null); 
            block.start(BLOCK24).lp(BLOCK24).end(EOB25).rp(EOB25);

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return block;
    }
    // $ANTLR end "block"


    // $ANTLR start "altList"
    // ANTLRWalker.g:244:1: altList returns [BlockBuilder block = factory.newBlockBuilder(true);] : ^( BLOCK (stt= alternative rwstt= rewrite )+ EOB ) ;
    public final BlockBuilder altList() throws RecognitionException {
        BlockBuilder block =  factory.newBlockBuilder(true);;

        CommonTree BLOCK26=null;
        CommonTree EOB27=null;
        AlternativeBuilder stt = null;

        StatementBuilder rwstt = null;


        try {
            // ANTLRWalker.g:245:3: ( ^( BLOCK (stt= alternative rwstt= rewrite )+ EOB ) )
            // ANTLRWalker.g:246:3: ^( BLOCK (stt= alternative rwstt= rewrite )+ EOB )
            {
            BLOCK26=(CommonTree)match(input,BLOCK,FOLLOW_BLOCK_in_altList1039); 

            match(input, Token.DOWN, null); 
            // ANTLRWalker.g:248:5: (stt= alternative rwstt= rewrite )+
            int cnt33=0;
            loop33:
            do {
                int alt33=2;
                int LA33_0 = input.LA(1);

                if ( (LA33_0==ALT) ) {
                    alt33=1;
                }


                switch (alt33) {
            	case 1 :
            	    // ANTLRWalker.g:249:7: stt= alternative rwstt= rewrite
            	    {
            	    pushFollow(FOLLOW_alternative_in_altList1055);
            	    stt=alternative();

            	    state._fsp--;

            	    block.start(BLOCK26).statement(stt);
            	    pushFollow(FOLLOW_rewrite_in_altList1061);
            	    rwstt=rewrite();

            	    state._fsp--;

            	    if(rwstt!=null)stt.rewrite(rwstt);

            	    }
            	    break;

            	default :
            	    if ( cnt33 >= 1 ) break loop33;
                        EarlyExitException eee =
                            new EarlyExitException(33, input);
                        throw eee;
                }
                cnt33++;
            } while (true);

            EOB27=(CommonTree)match(input,EOB,FOLLOW_EOB_in_altList1076); 
            block.end(EOB27);

            match(input, Token.UP, null); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return block;
    }
    // $ANTLR end "altList"


    // $ANTLR start "alternative"
    // ANTLRWalker.g:255:1: alternative returns [AlternativeBuilder alt = factory.newAlternativeBuilder();] : ( ^( ALT (stt= element )+ EOA ) | ^( ALT EPSILON EOA ) );
    public final AlternativeBuilder alternative() throws RecognitionException {
        AlternativeBuilder alt =  factory.newAlternativeBuilder();;

        CommonTree ALT28=null;
        CommonTree EOA29=null;
        CommonTree ALT30=null;
        CommonTree EOA31=null;
        StatementBuilder stt = null;


        try {
            // ANTLRWalker.g:256:3: ( ^( ALT (stt= element )+ EOA ) | ^( ALT EPSILON EOA ) )
            int alt35=2;
            int LA35_0 = input.LA(1);

            if ( (LA35_0==ALT) ) {
                int LA35_1 = input.LA(2);

                if ( (LA35_1==DOWN) ) {
                    int LA35_2 = input.LA(3);

                    if ( (LA35_2==EPSILON) ) {
                        alt35=2;
                    }
                    else if ( ((LA35_2>=BLOCK && LA35_2<=SYNPRED)||LA35_2==CHAR_RANGE||(LA35_2>=SEMPRED && LA35_2<=SYN_SEMPRED)||(LA35_2>=TREE_BEGIN && LA35_2<=BANG)||(LA35_2>=DOT && LA35_2<=ASSIGN)||(LA35_2>=TOKEN_REF && LA35_2<=ACTION)||LA35_2==RULE_REF||LA35_2==89||LA35_2==91) ) {
                        alt35=1;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 35, 2, input);

                        throw nvae;
                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 35, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 35, 0, input);

                throw nvae;
            }
            switch (alt35) {
                case 1 :
                    // ANTLRWalker.g:257:3: ^( ALT (stt= element )+ EOA )
                    {
                    ALT28=(CommonTree)match(input,ALT,FOLLOW_ALT_in_alternative1108); 

                    match(input, Token.DOWN, null); 
                    // ANTLRWalker.g:259:5: (stt= element )+
                    int cnt34=0;
                    loop34:
                    do {
                        int alt34=2;
                        int LA34_0 = input.LA(1);

                        if ( ((LA34_0>=BLOCK && LA34_0<=SYNPRED)||LA34_0==CHAR_RANGE||(LA34_0>=SEMPRED && LA34_0<=SYN_SEMPRED)||(LA34_0>=TREE_BEGIN && LA34_0<=BANG)||(LA34_0>=DOT && LA34_0<=ASSIGN)||(LA34_0>=TOKEN_REF && LA34_0<=ACTION)||LA34_0==RULE_REF||LA34_0==89||LA34_0==91) ) {
                            alt34=1;
                        }


                        switch (alt34) {
                    	case 1 :
                    	    // ANTLRWalker.g:260:7: stt= element
                    	    {
                    	    pushFollow(FOLLOW_element_in_alternative1124);
                    	    stt=element();

                    	    state._fsp--;

                    	    alt.statement(stt);

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt34 >= 1 ) break loop34;
                                EarlyExitException eee =
                                    new EarlyExitException(34, input);
                                throw eee;
                        }
                        cnt34++;
                    } while (true);

                    EOA29=(CommonTree)match(input,EOA,FOLLOW_EOA_in_alternative1139); 
                    alt.start(ALT28).end(EOA29);

                    match(input, Token.UP, null); 

                    }
                    break;
                case 2 :
                    // ANTLRWalker.g:265:3: ^( ALT EPSILON EOA )
                    {
                    ALT30=(CommonTree)match(input,ALT,FOLLOW_ALT_in_alternative1155); 

                    match(input, Token.DOWN, null); 
                    match(input,EPSILON,FOLLOW_EPSILON_in_alternative1157); 
                    EOA31=(CommonTree)match(input,EOA,FOLLOW_EOA_in_alternative1159); 
                    alt.start(ALT30).end(EOA31);

                    match(input, Token.UP, null); 

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return alt;
    }
    // $ANTLR end "alternative"


    // $ANTLR start "exceptionGroup"
    // ANTLRWalker.g:268:1: exceptionGroup[RuleBuilder rule] : ( ( exceptionHandler[rule] )+ ( finallyClause[rule] )? | finallyClause[rule] );
    public final void exceptionGroup(RuleBuilder rule) throws RecognitionException {
        try {
            // ANTLRWalker.g:269:3: ( ( exceptionHandler[rule] )+ ( finallyClause[rule] )? | finallyClause[rule] )
            int alt38=2;
            int LA38_0 = input.LA(1);

            if ( (LA38_0==87) ) {
                alt38=1;
            }
            else if ( (LA38_0==88) ) {
                alt38=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 38, 0, input);

                throw nvae;
            }
            switch (alt38) {
                case 1 :
                    // ANTLRWalker.g:270:3: ( exceptionHandler[rule] )+ ( finallyClause[rule] )?
                    {
                    // ANTLRWalker.g:270:3: ( exceptionHandler[rule] )+
                    int cnt36=0;
                    loop36:
                    do {
                        int alt36=2;
                        int LA36_0 = input.LA(1);

                        if ( (LA36_0==87) ) {
                            alt36=1;
                        }


                        switch (alt36) {
                    	case 1 :
                    	    // ANTLRWalker.g:270:3: exceptionHandler[rule]
                    	    {
                    	    pushFollow(FOLLOW_exceptionHandler_in_exceptionGroup1178);
                    	    exceptionHandler(rule);

                    	    state._fsp--;


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt36 >= 1 ) break loop36;
                                EarlyExitException eee =
                                    new EarlyExitException(36, input);
                                throw eee;
                        }
                        cnt36++;
                    } while (true);

                    // ANTLRWalker.g:270:27: ( finallyClause[rule] )?
                    int alt37=2;
                    int LA37_0 = input.LA(1);

                    if ( (LA37_0==88) ) {
                        alt37=1;
                    }
                    switch (alt37) {
                        case 1 :
                            // ANTLRWalker.g:270:27: finallyClause[rule]
                            {
                            pushFollow(FOLLOW_finallyClause_in_exceptionGroup1182);
                            finallyClause(rule);

                            state._fsp--;


                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // ANTLRWalker.g:271:5: finallyClause[rule]
                    {
                    pushFollow(FOLLOW_finallyClause_in_exceptionGroup1190);
                    finallyClause(rule);

                    state._fsp--;


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "exceptionGroup"


    // $ANTLR start "exceptionHandler"
    // ANTLRWalker.g:274:1: exceptionHandler[RuleBuilder rule] : ^(c= 'catch' ARG_ACTION ACTION ) ;
    public final void exceptionHandler(RuleBuilder rule) throws RecognitionException {
        CommonTree c=null;
        CommonTree ARG_ACTION32=null;
        CommonTree ACTION33=null;

        try {
            // ANTLRWalker.g:275:3: ( ^(c= 'catch' ARG_ACTION ACTION ) )
            // ANTLRWalker.g:276:3: ^(c= 'catch' ARG_ACTION ACTION )
            {
            c=(CommonTree)match(input,87,FOLLOW_87_in_exceptionHandler1210); 

            match(input, Token.DOWN, null); 
            ARG_ACTION32=(CommonTree)match(input,ARG_ACTION,FOLLOW_ARG_ACTION_in_exceptionHandler1212); 
            ACTION33=(CommonTree)match(input,ACTION,FOLLOW_ACTION_in_exceptionHandler1214); 

            match(input, Token.UP, null); 
            rule.ruleCatch(c, ARG_ACTION32, ACTION33);

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "exceptionHandler"


    // $ANTLR start "finallyClause"
    // ANTLRWalker.g:280:1: finallyClause[RuleBuilder rule] : ^(f= 'finally' ACTION ) ;
    public final void finallyClause(RuleBuilder rule) throws RecognitionException {
        CommonTree f=null;
        CommonTree ACTION34=null;

        try {
            // ANTLRWalker.g:281:3: ( ^(f= 'finally' ACTION ) )
            // ANTLRWalker.g:282:3: ^(f= 'finally' ACTION )
            {
            f=(CommonTree)match(input,88,FOLLOW_88_in_finallyClause1238); 

            match(input, Token.DOWN, null); 
            ACTION34=(CommonTree)match(input,ACTION,FOLLOW_ACTION_in_finallyClause1240); 

            match(input, Token.UP, null); 
            rule.ruleFinally(f, ACTION34);

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "finallyClause"


    // $ANTLR start "element"
    // ANTLRWalker.g:286:1: element returns [StatementBuilder statement = null;] : stt= elementNoOptionSpec ;
    public final StatementBuilder element() throws RecognitionException {
        StatementBuilder statement =  null;;

        StatementBuilder stt = null;


        try {
            // ANTLRWalker.g:287:3: (stt= elementNoOptionSpec )
            // ANTLRWalker.g:288:3: stt= elementNoOptionSpec
            {
            pushFollow(FOLLOW_elementNoOptionSpec_in_element1266);
            stt=elementNoOptionSpec();

            state._fsp--;

            statement=stt;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return statement;
    }
    // $ANTLR end "element"


    // $ANTLR start "elementNoOptionSpec"
    // ANTLRWalker.g:291:1: elementNoOptionSpec returns [StatementBuilder statement = null;] : ( ^( (op= '=' | op= '+=' ) ID stt= block ) | ^( (op= '=' | op= '+=' ) ID stt= atom ) | stt= atom | stt= ebnf | ACTION | SEMPRED | GATED_SEMPRED | stt1= treeSpec );
    public final StatementBuilder elementNoOptionSpec() throws RecognitionException {
        StatementBuilder statement =  null;;

        CommonTree op=null;
        CommonTree ID35=null;
        CommonTree ID36=null;
        CommonTree ACTION37=null;
        CommonTree SEMPRED38=null;
        CommonTree GATED_SEMPRED39=null;
        StatementBuilder stt = null;

        TreeBuilder stt1 = null;


        try {
            // ANTLRWalker.g:292:3: ( ^( (op= '=' | op= '+=' ) ID stt= block ) | ^( (op= '=' | op= '+=' ) ID stt= atom ) | stt= atom | stt= ebnf | ACTION | SEMPRED | GATED_SEMPRED | stt1= treeSpec )
            int alt41=8;
            alt41 = dfa41.predict(input);
            switch (alt41) {
                case 1 :
                    // ANTLRWalker.g:293:3: ^( (op= '=' | op= '+=' ) ID stt= block )
                    {
                    // ANTLRWalker.g:294:5: (op= '=' | op= '+=' )
                    int alt39=2;
                    int LA39_0 = input.LA(1);

                    if ( (LA39_0==ASSIGN) ) {
                        alt39=1;
                    }
                    else if ( (LA39_0==89) ) {
                        alt39=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 39, 0, input);

                        throw nvae;
                    }
                    switch (alt39) {
                        case 1 :
                            // ANTLRWalker.g:295:7: op= '='
                            {
                            op=(CommonTree)match(input,ASSIGN,FOLLOW_ASSIGN_in_elementNoOptionSpec1303); 

                            }
                            break;
                        case 2 :
                            // ANTLRWalker.g:296:9: op= '+='
                            {
                            op=(CommonTree)match(input,89,FOLLOW_89_in_elementNoOptionSpec1315); 

                            }
                            break;

                    }


                    match(input, Token.DOWN, null); 
                    ID35=(CommonTree)match(input,ID,FOLLOW_ID_in_elementNoOptionSpec1327); 
                    pushFollow(FOLLOW_block_in_elementNoOptionSpec1331);
                    stt=block();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    statement=factory.newAssignBuilder().assign(op, ID35, stt);

                    }
                    break;
                case 2 :
                    // ANTLRWalker.g:302:3: ^( (op= '=' | op= '+=' ) ID stt= atom )
                    {
                    // ANTLRWalker.g:303:5: (op= '=' | op= '+=' )
                    int alt40=2;
                    int LA40_0 = input.LA(1);

                    if ( (LA40_0==ASSIGN) ) {
                        alt40=1;
                    }
                    else if ( (LA40_0==89) ) {
                        alt40=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 40, 0, input);

                        throw nvae;
                    }
                    switch (alt40) {
                        case 1 :
                            // ANTLRWalker.g:304:7: op= '='
                            {
                            op=(CommonTree)match(input,ASSIGN,FOLLOW_ASSIGN_in_elementNoOptionSpec1364); 

                            }
                            break;
                        case 2 :
                            // ANTLRWalker.g:305:9: op= '+='
                            {
                            op=(CommonTree)match(input,89,FOLLOW_89_in_elementNoOptionSpec1376); 

                            }
                            break;

                    }


                    match(input, Token.DOWN, null); 
                    ID36=(CommonTree)match(input,ID,FOLLOW_ID_in_elementNoOptionSpec1388); 
                    pushFollow(FOLLOW_atom_in_elementNoOptionSpec1392);
                    stt=atom();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    statement=factory.newAssignBuilder().assign(op, ID36, stt);

                    }
                    break;
                case 3 :
                    // ANTLRWalker.g:310:5: stt= atom
                    {
                    pushFollow(FOLLOW_atom_in_elementNoOptionSpec1409);
                    stt=atom();

                    state._fsp--;

                    statement=stt;

                    }
                    break;
                case 4 :
                    // ANTLRWalker.g:311:5: stt= ebnf
                    {
                    pushFollow(FOLLOW_ebnf_in_elementNoOptionSpec1419);
                    stt=ebnf();

                    state._fsp--;

                    statement=stt;

                    }
                    break;
                case 5 :
                    // ANTLRWalker.g:312:5: ACTION
                    {
                    ACTION37=(CommonTree)match(input,ACTION,FOLLOW_ACTION_in_elementNoOptionSpec1427); 
                    statement=factory.newActionBuilder().action(ACTION37);

                    }
                    break;
                case 6 :
                    // ANTLRWalker.g:313:5: SEMPRED
                    {
                    SEMPRED38=(CommonTree)match(input,SEMPRED,FOLLOW_SEMPRED_in_elementNoOptionSpec1435); 
                    statement=factory.newSemPredBuilder().condition(SEMPRED38).type(ISemanticPredicate.PredicateType.SEMPRED);

                    }
                    break;
                case 7 :
                    // ANTLRWalker.g:314:5: GATED_SEMPRED
                    {
                    GATED_SEMPRED39=(CommonTree)match(input,GATED_SEMPRED,FOLLOW_GATED_SEMPRED_in_elementNoOptionSpec1443); 
                    statement=factory.newSemPredBuilder().condition(GATED_SEMPRED39).type(ISemanticPredicate.PredicateType.GATED_SEMPRED);

                    }
                    break;
                case 8 :
                    // ANTLRWalker.g:315:5: stt1= treeSpec
                    {
                    pushFollow(FOLLOW_treeSpec_in_elementNoOptionSpec1453);
                    stt1=treeSpec();

                    state._fsp--;

                    statement=stt1;

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return statement;
    }
    // $ANTLR end "elementNoOptionSpec"


    // $ANTLR start "atom"
    // ANTLRWalker.g:318:1: atom returns [StatementBuilder statement = null;] : ( ^( (treeOp= '^' | treeOp= '!' ) stt1= atom ) | stt2= range | stt3= notSet | stt4= call | stt5= terminal );
    public final StatementBuilder atom() throws RecognitionException {
        StatementBuilder statement =  null;;

        CommonTree treeOp=null;
        StatementBuilder stt1 = null;

        RangeBuilder stt2 = null;

        StatementBuilder stt3 = null;

        StatementBuilder stt4 = null;

        CallExpressionBuilder stt5 = null;


        try {
            // ANTLRWalker.g:319:3: ( ^( (treeOp= '^' | treeOp= '!' ) stt1= atom ) | stt2= range | stt3= notSet | stt4= call | stt5= terminal )
            int alt43=5;
            switch ( input.LA(1) ) {
            case ROOT:
            case BANG:
                {
                alt43=1;
                }
                break;
            case CHAR_RANGE:
                {
                alt43=2;
                }
                break;
            case 91:
                {
                alt43=3;
                }
                break;
            case RULE_REF:
                {
                alt43=4;
                }
                break;
            case DOT:
            case TOKEN_REF:
            case STRING_LITERAL:
                {
                alt43=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 43, 0, input);

                throw nvae;
            }

            switch (alt43) {
                case 1 :
                    // ANTLRWalker.g:320:3: ^( (treeOp= '^' | treeOp= '!' ) stt1= atom )
                    {
                    // ANTLRWalker.g:321:5: (treeOp= '^' | treeOp= '!' )
                    int alt42=2;
                    int LA42_0 = input.LA(1);

                    if ( (LA42_0==ROOT) ) {
                        alt42=1;
                    }
                    else if ( (LA42_0==BANG) ) {
                        alt42=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 42, 0, input);

                        throw nvae;
                    }
                    switch (alt42) {
                        case 1 :
                            // ANTLRWalker.g:322:7: treeOp= '^'
                            {
                            treeOp=(CommonTree)match(input,ROOT,FOLLOW_ROOT_in_atom1490); 

                            }
                            break;
                        case 2 :
                            // ANTLRWalker.g:323:9: treeOp= '!'
                            {
                            treeOp=(CommonTree)match(input,BANG,FOLLOW_BANG_in_atom1502); 

                            }
                            break;

                    }


                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_atom_in_atom1516);
                    stt1=atom();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    statement=stt1.treeOp(treeOp);

                    }
                    break;
                case 2 :
                    // ANTLRWalker.g:328:5: stt2= range
                    {
                    pushFollow(FOLLOW_range_in_atom1533);
                    stt2=range();

                    state._fsp--;

                    statement = stt2;

                    }
                    break;
                case 3 :
                    // ANTLRWalker.g:329:5: stt3= notSet
                    {
                    pushFollow(FOLLOW_notSet_in_atom1543);
                    stt3=notSet();

                    state._fsp--;

                    statement = stt3;

                    }
                    break;
                case 4 :
                    // ANTLRWalker.g:330:5: stt4= call
                    {
                    pushFollow(FOLLOW_call_in_atom1553);
                    stt4=call();

                    state._fsp--;

                    statement = stt4;

                    }
                    break;
                case 5 :
                    // ANTLRWalker.g:331:5: stt5= terminal
                    {
                    pushFollow(FOLLOW_terminal_in_atom1563);
                    stt5=terminal();

                    state._fsp--;

                    statement = stt5;

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return statement;
    }
    // $ANTLR end "atom"


    // $ANTLR start "call"
    // ANTLRWalker.g:334:1: call returns [StatementBuilder statement = null] : RULE_REF ( ARG_ACTION )? ;
    public final StatementBuilder call() throws RecognitionException {
        StatementBuilder statement =  null;

        CommonTree ARG_ACTION40=null;
        CommonTree RULE_REF41=null;

        try {
            // ANTLRWalker.g:335:3: ( RULE_REF ( ARG_ACTION )? )
            // ANTLRWalker.g:336:3: RULE_REF ( ARG_ACTION )?
            {
            RULE_REF41=(CommonTree)match(input,RULE_REF,FOLLOW_RULE_REF_in_call1584); 
            // ANTLRWalker.g:336:12: ( ARG_ACTION )?
            int alt44=2;
            int LA44_0 = input.LA(1);

            if ( (LA44_0==ARG_ACTION) ) {
                alt44=1;
            }
            switch (alt44) {
                case 1 :
                    // ANTLRWalker.g:336:12: ARG_ACTION
                    {
                    ARG_ACTION40=(CommonTree)match(input,ARG_ACTION,FOLLOW_ARG_ACTION_in_call1586); 

                    }
                    break;

            }


            		if(ARG_ACTION40!=null) {
                		statement = factory.newCallExpressionBuilder().name(RULE_REF41).parameters(ARG_ACTION40);
                	} else {
                		statement = factory.newCallExpressionBuilder().name(RULE_REF41);
                	}
                

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return statement;
    }
    // $ANTLR end "call"


    // $ANTLR start "notSet"
    // ANTLRWalker.g:345:1: notSet returns [StatementBuilder statement = null;] : ( ^(not= '~' stt= notTerminal ) | ^(not= '~' stt1= block ) );
    public final StatementBuilder notSet() throws RecognitionException {
        StatementBuilder statement =  null;;

        CommonTree not=null;
        CallExpressionBuilder stt = null;

        BlockBuilder stt1 = null;


        try {
            // ANTLRWalker.g:346:3: ( ^(not= '~' stt= notTerminal ) | ^(not= '~' stt1= block ) )
            int alt45=2;
            int LA45_0 = input.LA(1);

            if ( (LA45_0==91) ) {
                int LA45_1 = input.LA(2);

                if ( (LA45_1==DOWN) ) {
                    int LA45_2 = input.LA(3);

                    if ( ((LA45_2>=TOKEN_REF && LA45_2<=STRING_LITERAL)) ) {
                        alt45=1;
                    }
                    else if ( (LA45_2==BLOCK) ) {
                        alt45=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 45, 2, input);

                        throw nvae;
                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 45, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 45, 0, input);

                throw nvae;
            }
            switch (alt45) {
                case 1 :
                    // ANTLRWalker.g:347:3: ^(not= '~' stt= notTerminal )
                    {
                    not=(CommonTree)match(input,91,FOLLOW_91_in_notSet1611); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_notTerminal_in_notSet1615);
                    stt=notTerminal();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    statement=stt.not(not);

                    }
                    break;
                case 2 :
                    // ANTLRWalker.g:350:3: ^(not= '~' stt1= block )
                    {
                    not=(CommonTree)match(input,91,FOLLOW_91_in_notSet1631); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_block_in_notSet1635);
                    stt1=block();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    statement=stt1.not(not);

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return statement;
    }
    // $ANTLR end "notSet"


    // $ANTLR start "treeSpec"
    // ANTLRWalker.g:354:1: treeSpec returns [TreeBuilder statement = factory.newTreeBuilder();] : ^( TREE_BEGIN (elem= element )+ ) ;
    public final TreeBuilder treeSpec() throws RecognitionException {
        TreeBuilder statement =  factory.newTreeBuilder();;

        StatementBuilder elem = null;


        try {
            // ANTLRWalker.g:355:3: ( ^( TREE_BEGIN (elem= element )+ ) )
            // ANTLRWalker.g:356:3: ^( TREE_BEGIN (elem= element )+ )
            {
            match(input,TREE_BEGIN,FOLLOW_TREE_BEGIN_in_treeSpec1665); 

            match(input, Token.DOWN, null); 
            // ANTLRWalker.g:358:5: (elem= element )+
            int cnt46=0;
            loop46:
            do {
                int alt46=2;
                int LA46_0 = input.LA(1);

                if ( ((LA46_0>=BLOCK && LA46_0<=SYNPRED)||LA46_0==CHAR_RANGE||(LA46_0>=SEMPRED && LA46_0<=SYN_SEMPRED)||(LA46_0>=TREE_BEGIN && LA46_0<=BANG)||(LA46_0>=DOT && LA46_0<=ASSIGN)||(LA46_0>=TOKEN_REF && LA46_0<=ACTION)||LA46_0==RULE_REF||LA46_0==89||LA46_0==91) ) {
                    alt46=1;
                }


                switch (alt46) {
            	case 1 :
            	    // ANTLRWalker.g:359:7: elem= element
            	    {
            	    pushFollow(FOLLOW_element_in_treeSpec1681);
            	    elem=element();

            	    state._fsp--;

            	    statement.statement(elem);

            	    }
            	    break;

            	default :
            	    if ( cnt46 >= 1 ) break loop46;
                        EarlyExitException eee =
                            new EarlyExitException(46, input);
                        throw eee;
                }
                cnt46++;
            } while (true);


            match(input, Token.UP, null); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return statement;
    }
    // $ANTLR end "treeSpec"


    // $ANTLR start "ebnf"
    // ANTLRWalker.g:364:1: ebnf returns [StatementBuilder statement = null;] : ( ^( SYNPRED stt= block ) | ^( SYN_SEMPRED stt= block ) | ^(op= ebnfSuffix stt1= block ) | ^( ROOT stt1= block ) | ^( BANG stt1= block ) | stt= block );
    public final StatementBuilder ebnf() throws RecognitionException {
        StatementBuilder statement =  null;;

        CommonTree ROOT42=null;
        CommonTree BANG43=null;
        BlockBuilder stt = null;

        CommonTree op = null;

        BlockBuilder stt1 = null;


        try {
            // ANTLRWalker.g:366:3: ( ^( SYNPRED stt= block ) | ^( SYN_SEMPRED stt= block ) | ^(op= ebnfSuffix stt1= block ) | ^( ROOT stt1= block ) | ^( BANG stt1= block ) | stt= block )
            int alt47=6;
            switch ( input.LA(1) ) {
            case SYNPRED:
                {
                alt47=1;
                }
                break;
            case SYN_SEMPRED:
                {
                alt47=2;
                }
                break;
            case OPTIONAL:
            case CLOSURE:
            case POSITIVE_CLOSURE:
                {
                alt47=3;
                }
                break;
            case ROOT:
                {
                alt47=4;
                }
                break;
            case BANG:
                {
                alt47=5;
                }
                break;
            case BLOCK:
                {
                alt47=6;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 47, 0, input);

                throw nvae;
            }

            switch (alt47) {
                case 1 :
                    // ANTLRWalker.g:367:3: ^( SYNPRED stt= block )
                    {
                    match(input,SYNPRED,FOLLOW_SYNPRED_in_ebnf1717); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_block_in_ebnf1721);
                    stt=block();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    statement=factory.newSynPredBuilder().predicate(stt);

                    }
                    break;
                case 2 :
                    // ANTLRWalker.g:370:3: ^( SYN_SEMPRED stt= block )
                    {
                    match(input,SYN_SEMPRED,FOLLOW_SYN_SEMPRED_in_ebnf1735); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_block_in_ebnf1739);
                    stt=block();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    statement=factory.newSynPredBuilder().predicate(stt);

                    }
                    break;
                case 3 :
                    // ANTLRWalker.g:373:3: ^(op= ebnfSuffix stt1= block )
                    {
                    pushFollow(FOLLOW_ebnfSuffix_in_ebnf1755);
                    op=ebnfSuffix();

                    state._fsp--;


                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_block_in_ebnf1759);
                    stt1=block();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    statement=stt1.ebnf(op);

                    }
                    break;
                case 4 :
                    // ANTLRWalker.g:376:3: ^( ROOT stt1= block )
                    {
                    ROOT42=(CommonTree)match(input,ROOT,FOLLOW_ROOT_in_ebnf1773); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_block_in_ebnf1777);
                    stt1=block();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    statement=stt1.treeOp(ROOT42);

                    }
                    break;
                case 5 :
                    // ANTLRWalker.g:379:3: ^( BANG stt1= block )
                    {
                    BANG43=(CommonTree)match(input,BANG,FOLLOW_BANG_in_ebnf1791); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_block_in_ebnf1795);
                    stt1=block();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    statement=stt1.treeOp(BANG43);

                    }
                    break;
                case 6 :
                    // ANTLRWalker.g:381:5: stt= block
                    {
                    pushFollow(FOLLOW_block_in_ebnf1808);
                    stt=block();

                    state._fsp--;

                    statement=stt;

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return statement;
    }
    // $ANTLR end "ebnf"


    // $ANTLR start "range"
    // ANTLRWalker.g:384:1: range returns [RangeBuilder statement = factory.newRangeBuilder();] : ^(op= CHAR_RANGE from= STRING_LITERAL to= STRING_LITERAL ) ;
    public final RangeBuilder range() throws RecognitionException {
        RangeBuilder statement =  factory.newRangeBuilder();;

        CommonTree op=null;
        CommonTree from=null;
        CommonTree to=null;

        try {
            // ANTLRWalker.g:385:3: ( ^(op= CHAR_RANGE from= STRING_LITERAL to= STRING_LITERAL ) )
            // ANTLRWalker.g:386:3: ^(op= CHAR_RANGE from= STRING_LITERAL to= STRING_LITERAL )
            {
            op=(CommonTree)match(input,CHAR_RANGE,FOLLOW_CHAR_RANGE_in_range1832); 

            match(input, Token.DOWN, null); 
            from=(CommonTree)match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_range1836); 
            to=(CommonTree)match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_range1840); 

            match(input, Token.UP, null); 
            statement.operator(op).from(from).to(to);

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return statement;
    }
    // $ANTLR end "range"


    // $ANTLR start "elementOptions"
    // ANTLRWalker.g:390:1: elementOptions[CallExpressionBuilder parent] : ^( ELEMENT_OPTIONS ( elementOption[parent] )+ ) ;
    public final void elementOptions(CallExpressionBuilder parent) throws RecognitionException {
        try {
            // ANTLRWalker.g:391:3: ( ^( ELEMENT_OPTIONS ( elementOption[parent] )+ ) )
            // ANTLRWalker.g:392:3: ^( ELEMENT_OPTIONS ( elementOption[parent] )+ )
            {
            match(input,ELEMENT_OPTIONS,FOLLOW_ELEMENT_OPTIONS_in_elementOptions1862); 

            match(input, Token.DOWN, null); 
            // ANTLRWalker.g:392:21: ( elementOption[parent] )+
            int cnt48=0;
            loop48:
            do {
                int alt48=2;
                int LA48_0 = input.LA(1);

                if ( (LA48_0==ID||LA48_0==ASSIGN) ) {
                    alt48=1;
                }


                switch (alt48) {
            	case 1 :
            	    // ANTLRWalker.g:392:21: elementOption[parent]
            	    {
            	    pushFollow(FOLLOW_elementOption_in_elementOptions1864);
            	    elementOption(parent);

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    if ( cnt48 >= 1 ) break loop48;
                        EarlyExitException eee =
                            new EarlyExitException(48, input);
                        throw eee;
                }
                cnt48++;
            } while (true);


            match(input, Token.UP, null); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "elementOptions"


    // $ANTLR start "elementOption"
    // ANTLRWalker.g:395:1: elementOption[CallExpressionBuilder parent] : ( ID | ^( ASSIGN n= ID v= ID ) | ^( ASSIGN n= ID v= STRING_LITERAL ) );
    public final void elementOption(CallExpressionBuilder parent) throws RecognitionException {
        CommonTree n=null;
        CommonTree v=null;
        CommonTree ID44=null;

        try {
            // ANTLRWalker.g:396:3: ( ID | ^( ASSIGN n= ID v= ID ) | ^( ASSIGN n= ID v= STRING_LITERAL ) )
            int alt49=3;
            int LA49_0 = input.LA(1);

            if ( (LA49_0==ID) ) {
                alt49=1;
            }
            else if ( (LA49_0==ASSIGN) ) {
                int LA49_2 = input.LA(2);

                if ( (LA49_2==DOWN) ) {
                    int LA49_3 = input.LA(3);

                    if ( (LA49_3==ID) ) {
                        int LA49_4 = input.LA(4);

                        if ( (LA49_4==ID) ) {
                            alt49=2;
                        }
                        else if ( (LA49_4==STRING_LITERAL) ) {
                            alt49=3;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("", 49, 4, input);

                            throw nvae;
                        }
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 49, 3, input);

                        throw nvae;
                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 49, 2, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 49, 0, input);

                throw nvae;
            }
            switch (alt49) {
                case 1 :
                    // ANTLRWalker.g:397:3: ID
                    {
                    ID44=(CommonTree)match(input,ID,FOLLOW_ID_in_elementOption1883); 
                    parent.option(ID44);

                    }
                    break;
                case 2 :
                    // ANTLRWalker.g:399:3: ^( ASSIGN n= ID v= ID )
                    {
                    match(input,ASSIGN,FOLLOW_ASSIGN_in_elementOption1894); 

                    match(input, Token.DOWN, null); 
                    n=(CommonTree)match(input,ID,FOLLOW_ID_in_elementOption1898); 
                    v=(CommonTree)match(input,ID,FOLLOW_ID_in_elementOption1902); 

                    match(input, Token.UP, null); 
                    parent.option(n, v);

                    }
                    break;
                case 3 :
                    // ANTLRWalker.g:402:3: ^( ASSIGN n= ID v= STRING_LITERAL )
                    {
                    match(input,ASSIGN,FOLLOW_ASSIGN_in_elementOption1916); 

                    match(input, Token.DOWN, null); 
                    n=(CommonTree)match(input,ID,FOLLOW_ID_in_elementOption1920); 
                    v=(CommonTree)match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_elementOption1924); 

                    match(input, Token.UP, null); 
                    parent.option(n, v);

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "elementOption"


    // $ANTLR start "terminal"
    // ANTLRWalker.g:406:1: terminal returns [CallExpressionBuilder statement = factory.newCallExpressionBuilder();] : ( ^( TOKEN_REF elementOptions[statement] ARG_ACTION ) | ^( TOKEN_REF elementOptions[statement] ) | ^( TOKEN_REF ARG_ACTION ) | TOKEN_REF | ^( STRING_LITERAL elementOptions[statement] ) | STRING_LITERAL | any= '.' );
    public final CallExpressionBuilder terminal() throws RecognitionException {
        CallExpressionBuilder statement =  factory.newCallExpressionBuilder();;

        CommonTree any=null;
        CommonTree TOKEN_REF45=null;
        CommonTree TOKEN_REF46=null;
        CommonTree TOKEN_REF47=null;
        CommonTree ARG_ACTION48=null;
        CommonTree TOKEN_REF49=null;
        CommonTree STRING_LITERAL50=null;
        CommonTree STRING_LITERAL51=null;

        try {
            // ANTLRWalker.g:407:3: ( ^( TOKEN_REF elementOptions[statement] ARG_ACTION ) | ^( TOKEN_REF elementOptions[statement] ) | ^( TOKEN_REF ARG_ACTION ) | TOKEN_REF | ^( STRING_LITERAL elementOptions[statement] ) | STRING_LITERAL | any= '.' )
            int alt50=7;
            alt50 = dfa50.predict(input);
            switch (alt50) {
                case 1 :
                    // ANTLRWalker.g:408:3: ^( TOKEN_REF elementOptions[statement] ARG_ACTION )
                    {
                    TOKEN_REF45=(CommonTree)match(input,TOKEN_REF,FOLLOW_TOKEN_REF_in_terminal1949); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_elementOptions_in_terminal1951);
                    elementOptions(statement);

                    state._fsp--;

                    match(input,ARG_ACTION,FOLLOW_ARG_ACTION_in_terminal1954); 

                    match(input, Token.UP, null); 
                    statement.name(TOKEN_REF45);

                    }
                    break;
                case 2 :
                    // ANTLRWalker.g:411:3: ^( TOKEN_REF elementOptions[statement] )
                    {
                    TOKEN_REF46=(CommonTree)match(input,TOKEN_REF,FOLLOW_TOKEN_REF_in_terminal1968); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_elementOptions_in_terminal1970);
                    elementOptions(statement);

                    state._fsp--;


                    match(input, Token.UP, null); 
                    statement.name(TOKEN_REF46);

                    }
                    break;
                case 3 :
                    // ANTLRWalker.g:414:3: ^( TOKEN_REF ARG_ACTION )
                    {
                    TOKEN_REF47=(CommonTree)match(input,TOKEN_REF,FOLLOW_TOKEN_REF_in_terminal1985); 

                    match(input, Token.DOWN, null); 
                    ARG_ACTION48=(CommonTree)match(input,ARG_ACTION,FOLLOW_ARG_ACTION_in_terminal1987); 

                    match(input, Token.UP, null); 
                    statement.name(TOKEN_REF47).parameters(ARG_ACTION48);

                    }
                    break;
                case 4 :
                    // ANTLRWalker.g:416:5: TOKEN_REF
                    {
                    TOKEN_REF49=(CommonTree)match(input,TOKEN_REF,FOLLOW_TOKEN_REF_in_terminal1998); 
                    statement.name(TOKEN_REF49);

                    }
                    break;
                case 5 :
                    // ANTLRWalker.g:418:3: ^( STRING_LITERAL elementOptions[statement] )
                    {
                    STRING_LITERAL50=(CommonTree)match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_terminal2009); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_elementOptions_in_terminal2011);
                    elementOptions(statement);

                    state._fsp--;


                    match(input, Token.UP, null); 
                    statement.name(STRING_LITERAL50);

                    }
                    break;
                case 6 :
                    // ANTLRWalker.g:420:5: STRING_LITERAL
                    {
                    STRING_LITERAL51=(CommonTree)match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_terminal2023); 
                    statement.name(STRING_LITERAL51);

                    }
                    break;
                case 7 :
                    // ANTLRWalker.g:421:5: any= '.'
                    {
                    any=(CommonTree)match(input,DOT,FOLLOW_DOT_in_terminal2033); 
                    statement.name(any);

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return statement;
    }
    // $ANTLR end "terminal"


    // $ANTLR start "notTerminal"
    // ANTLRWalker.g:424:1: notTerminal returns [CallExpressionBuilder statement = factory.newCallExpressionBuilder();] : ( TOKEN_REF | STRING_LITERAL );
    public final CallExpressionBuilder notTerminal() throws RecognitionException {
        CallExpressionBuilder statement =  factory.newCallExpressionBuilder();;

        CommonTree TOKEN_REF52=null;
        CommonTree STRING_LITERAL53=null;

        try {
            // ANTLRWalker.g:425:3: ( TOKEN_REF | STRING_LITERAL )
            int alt51=2;
            int LA51_0 = input.LA(1);

            if ( (LA51_0==TOKEN_REF) ) {
                alt51=1;
            }
            else if ( (LA51_0==STRING_LITERAL) ) {
                alt51=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 51, 0, input);

                throw nvae;
            }
            switch (alt51) {
                case 1 :
                    // ANTLRWalker.g:426:3: TOKEN_REF
                    {
                    TOKEN_REF52=(CommonTree)match(input,TOKEN_REF,FOLLOW_TOKEN_REF_in_notTerminal2054); 
                    statement.name(TOKEN_REF52);

                    }
                    break;
                case 2 :
                    // ANTLRWalker.g:427:5: STRING_LITERAL
                    {
                    STRING_LITERAL53=(CommonTree)match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_notTerminal2062); 
                    statement.name(STRING_LITERAL53);

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return statement;
    }
    // $ANTLR end "notTerminal"


    // $ANTLR start "ebnfSuffix"
    // ANTLRWalker.g:430:1: ebnfSuffix returns [CommonTree node = null;] : ( OPTIONAL | CLOSURE | POSITIVE_CLOSURE );
    public final CommonTree ebnfSuffix() throws RecognitionException {
        CommonTree node =  null;;

        CommonTree OPTIONAL54=null;
        CommonTree CLOSURE55=null;
        CommonTree POSITIVE_CLOSURE56=null;

        try {
            // ANTLRWalker.g:431:3: ( OPTIONAL | CLOSURE | POSITIVE_CLOSURE )
            int alt52=3;
            switch ( input.LA(1) ) {
            case OPTIONAL:
                {
                alt52=1;
                }
                break;
            case CLOSURE:
                {
                alt52=2;
                }
                break;
            case POSITIVE_CLOSURE:
                {
                alt52=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 52, 0, input);

                throw nvae;
            }

            switch (alt52) {
                case 1 :
                    // ANTLRWalker.g:432:3: OPTIONAL
                    {
                    OPTIONAL54=(CommonTree)match(input,OPTIONAL,FOLLOW_OPTIONAL_in_ebnfSuffix2083); 
                    node = OPTIONAL54;

                    }
                    break;
                case 2 :
                    // ANTLRWalker.g:433:5: CLOSURE
                    {
                    CLOSURE55=(CommonTree)match(input,CLOSURE,FOLLOW_CLOSURE_in_ebnfSuffix2091); 
                    node = CLOSURE55;

                    }
                    break;
                case 3 :
                    // ANTLRWalker.g:434:5: POSITIVE_CLOSURE
                    {
                    POSITIVE_CLOSURE56=(CommonTree)match(input,POSITIVE_CLOSURE,FOLLOW_POSITIVE_CLOSURE_in_ebnfSuffix2099); 
                    node = POSITIVE_CLOSURE56;

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return node;
    }
    // $ANTLR end "ebnfSuffix"


    // $ANTLR start "rewrite"
    // ANTLRWalker.g:439:1: rewrite returns [StatementBuilder statement = null] : ( ( ^(blockStart= '->' SEMPRED alt= rewrite_alternative ) )* ^(blockStart= '->' alt= rewrite_alternative ) | );
    public final StatementBuilder rewrite() throws RecognitionException {
        StatementBuilder statement =  null;

        CommonTree blockStart=null;
        CommonTree SEMPRED57=null;
        AlternativeBuilder alt = null;



        	BlockBuilder blockBuilder = null;

        try {
            // ANTLRWalker.g:443:3: ( ( ^(blockStart= '->' SEMPRED alt= rewrite_alternative ) )* ^(blockStart= '->' alt= rewrite_alternative ) | )
            int alt54=2;
            int LA54_0 = input.LA(1);

            if ( (LA54_0==REWRITE) ) {
                alt54=1;
            }
            else if ( (LA54_0==ALT||LA54_0==EOB) ) {
                alt54=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 54, 0, input);

                throw nvae;
            }
            switch (alt54) {
                case 1 :
                    // ANTLRWalker.g:444:3: ( ^(blockStart= '->' SEMPRED alt= rewrite_alternative ) )* ^(blockStart= '->' alt= rewrite_alternative )
                    {
                    // ANTLRWalker.g:444:3: ( ^(blockStart= '->' SEMPRED alt= rewrite_alternative ) )*
                    loop53:
                    do {
                        int alt53=2;
                        int LA53_0 = input.LA(1);

                        if ( (LA53_0==REWRITE) ) {
                            int LA53_1 = input.LA(2);

                            if ( (LA53_1==DOWN) ) {
                                int LA53_2 = input.LA(3);

                                if ( (LA53_2==SEMPRED) ) {
                                    alt53=1;
                                }


                            }


                        }


                        switch (alt53) {
                    	case 1 :
                    	    // ANTLRWalker.g:445:5: ^(blockStart= '->' SEMPRED alt= rewrite_alternative )
                    	    {
                    	    blockStart=(CommonTree)match(input,REWRITE,FOLLOW_REWRITE_in_rewrite2136); 

                    	    match(input, Token.DOWN, null); 
                    	    SEMPRED57=(CommonTree)match(input,SEMPRED,FOLLOW_SEMPRED_in_rewrite2138); 
                    	    pushFollow(FOLLOW_rewrite_alternative_in_rewrite2142);
                    	    alt=rewrite_alternative();

                    	    state._fsp--;

                    	    	
                    	      				StatementBuilder semstt = factory.newSemPredBuilder()  													
                    	      													.type(ISemanticPredicate.PredicateType.SEMPRED)
                    	      													.condition(SEMPRED57)
                    	      													.predicate(alt);
                    	      				blockBuilder=(blockBuilder==null? factory.newBlockBuilder():blockBuilder);
                    	      				blockBuilder.start(blockStart);
                    	      				blockBuilder.statement( factory.newAlternativeBuilder().statement(semstt) );  				
                    	      			

                    	    match(input, Token.UP, null); 

                    	    }
                    	    break;

                    	default :
                    	    break loop53;
                        }
                    } while (true);

                    blockStart=(CommonTree)match(input,REWRITE,FOLLOW_REWRITE_in_rewrite2157); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_rewrite_alternative_in_rewrite2161);
                    alt=rewrite_alternative();

                    state._fsp--;

                      			
                      			blockBuilder=(blockBuilder==null? factory.newBlockBuilder():blockBuilder);
                      			blockBuilder.start(blockStart);
                      			blockBuilder.statement( alt );
                      		

                    match(input, Token.UP, null); 
                    statement=blockBuilder;

                    }
                    break;
                case 2 :
                    // ANTLRWalker.g:461:5: 
                    {
                    	
                      		//an alternative without rewrite rule, returns null
                      	

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return statement;
    }
    // $ANTLR end "rewrite"


    // $ANTLR start "rewrite_alternative"
    // ANTLRWalker.g:466:1: rewrite_alternative returns [AlternativeBuilder statement=null;] : (stt0= rewrite_template | stt1= rewrite_tree_alternative | ^( ALT EPSILON EOA ) );
    public final AlternativeBuilder rewrite_alternative() throws RecognitionException {
        AlternativeBuilder statement = null;;

        CommonTree ALT58=null;
        CommonTree EOA59=null;
        TemplateBuilder stt0 = null;

        AlternativeBuilder stt1 = null;


        try {
            // ANTLRWalker.g:467:3: (stt0= rewrite_template | stt1= rewrite_tree_alternative | ^( ALT EPSILON EOA ) )
            int alt55=3;
            int LA55_0 = input.LA(1);

            if ( (LA55_0==TEMPLATE||LA55_0==ACTION) ) {
                alt55=1;
            }
            else if ( (LA55_0==ALT) ) {
                int LA55_2 = input.LA(2);

                if ( (LA55_2==DOWN) ) {
                    int LA55_3 = input.LA(3);

                    if ( (LA55_3==EPSILON) ) {
                        alt55=3;
                    }
                    else if ( ((LA55_3>=BLOCK && LA55_3<=POSITIVE_CLOSURE)||LA55_3==LABEL||LA55_3==TREE_BEGIN||(LA55_3>=TOKEN_REF && LA55_3<=ACTION)||LA55_3==RULE_REF) ) {
                        alt55=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 55, 3, input);

                        throw nvae;
                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 55, 2, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 55, 0, input);

                throw nvae;
            }
            switch (alt55) {
                case 1 :
                    // ANTLRWalker.g:468:3: stt0= rewrite_template
                    {
                    pushFollow(FOLLOW_rewrite_template_in_rewrite_alternative2195);
                    stt0=rewrite_template();

                    state._fsp--;

                    statement=factory.newAlternativeBuilder().statement(stt0);

                    }
                    break;
                case 2 :
                    // ANTLRWalker.g:469:5: stt1= rewrite_tree_alternative
                    {
                    pushFollow(FOLLOW_rewrite_tree_alternative_in_rewrite_alternative2205);
                    stt1=rewrite_tree_alternative();

                    state._fsp--;

                    statement=stt1;

                    }
                    break;
                case 3 :
                    // ANTLRWalker.g:471:3: ^( ALT EPSILON EOA )
                    {
                    ALT58=(CommonTree)match(input,ALT,FOLLOW_ALT_in_rewrite_alternative2216); 

                    match(input, Token.DOWN, null); 
                    match(input,EPSILON,FOLLOW_EPSILON_in_rewrite_alternative2218); 
                    EOA59=(CommonTree)match(input,EOA,FOLLOW_EOA_in_rewrite_alternative2220); 

                    match(input, Token.UP, null); 
                    statement=factory.newAlternativeBuilder().start(ALT58).end(EOA59);

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return statement;
    }
    // $ANTLR end "rewrite_alternative"


    // $ANTLR start "rewrite_tree_block"
    // ANTLRWalker.g:475:1: rewrite_tree_block returns [BlockBuilder statement=factory.newBlockBuilder();] : ^( BLOCK stt= rewrite_tree_alternative EOB ) ;
    public final BlockBuilder rewrite_tree_block() throws RecognitionException {
        BlockBuilder statement = factory.newBlockBuilder();;

        CommonTree BLOCK60=null;
        CommonTree EOB61=null;
        AlternativeBuilder stt = null;


        try {
            // ANTLRWalker.g:476:3: ( ^( BLOCK stt= rewrite_tree_alternative EOB ) )
            // ANTLRWalker.g:477:3: ^( BLOCK stt= rewrite_tree_alternative EOB )
            {
            BLOCK60=(CommonTree)match(input,BLOCK,FOLLOW_BLOCK_in_rewrite_tree_block2245); 

            statement.start(BLOCK60).lp(BLOCK60);

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_rewrite_tree_alternative_in_rewrite_tree_block2251);
            stt=rewrite_tree_alternative();

            state._fsp--;

            statement.statement(stt);
            EOB61=(CommonTree)match(input,EOB,FOLLOW_EOB_in_rewrite_tree_block2255); 
            statement.end(EOB61).rp(EOB61);

            match(input, Token.UP, null); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return statement;
    }
    // $ANTLR end "rewrite_tree_block"


    // $ANTLR start "rewrite_tree_alternative"
    // ANTLRWalker.g:480:1: rewrite_tree_alternative returns [AlternativeBuilder statement=factory.newAlternativeBuilder();] : ^( ALT (stt= rewrite_tree_element )+ EOA ) ;
    public final AlternativeBuilder rewrite_tree_alternative() throws RecognitionException {
        AlternativeBuilder statement = factory.newAlternativeBuilder();;

        CommonTree ALT62=null;
        CommonTree EOA63=null;
        StatementBuilder stt = null;


        try {
            // ANTLRWalker.g:481:3: ( ^( ALT (stt= rewrite_tree_element )+ EOA ) )
            // ANTLRWalker.g:482:3: ^( ALT (stt= rewrite_tree_element )+ EOA )
            {
            ALT62=(CommonTree)match(input,ALT,FOLLOW_ALT_in_rewrite_tree_alternative2283); 

            match(input, Token.DOWN, null); 
            // ANTLRWalker.g:484:5: (stt= rewrite_tree_element )+
            int cnt56=0;
            loop56:
            do {
                int alt56=2;
                int LA56_0 = input.LA(1);

                if ( ((LA56_0>=BLOCK && LA56_0<=POSITIVE_CLOSURE)||LA56_0==LABEL||LA56_0==TREE_BEGIN||(LA56_0>=TOKEN_REF && LA56_0<=ACTION)||LA56_0==RULE_REF) ) {
                    alt56=1;
                }


                switch (alt56) {
            	case 1 :
            	    // ANTLRWalker.g:485:7: stt= rewrite_tree_element
            	    {
            	    pushFollow(FOLLOW_rewrite_tree_element_in_rewrite_tree_alternative2299);
            	    stt=rewrite_tree_element();

            	    state._fsp--;

            	    statement.statement(stt);

            	    }
            	    break;

            	default :
            	    if ( cnt56 >= 1 ) break loop56;
                        EarlyExitException eee =
                            new EarlyExitException(56, input);
                        throw eee;
                }
                cnt56++;
            } while (true);

            EOA63=(CommonTree)match(input,EOA,FOLLOW_EOA_in_rewrite_tree_alternative2314); 

            match(input, Token.UP, null); 
            statement.start(ALT62).end(EOA63);

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return statement;
    }
    // $ANTLR end "rewrite_tree_alternative"


    // $ANTLR start "rewrite_tree_element"
    // ANTLRWalker.g:492:1: rewrite_tree_element returns [StatementBuilder statement = null;] : (stt= rewrite_tree_atom | stt= rewrite_tree | stt1= rewrite_tree_block | stt= rewrite_tree_ebnf );
    public final StatementBuilder rewrite_tree_element() throws RecognitionException {
        StatementBuilder statement =  null;;

        StatementBuilder stt = null;

        BlockBuilder stt1 = null;


        try {
            // ANTLRWalker.g:493:3: (stt= rewrite_tree_atom | stt= rewrite_tree | stt1= rewrite_tree_block | stt= rewrite_tree_ebnf )
            int alt57=4;
            switch ( input.LA(1) ) {
            case LABEL:
            case TOKEN_REF:
            case STRING_LITERAL:
            case ACTION:
            case RULE_REF:
                {
                alt57=1;
                }
                break;
            case TREE_BEGIN:
                {
                alt57=2;
                }
                break;
            case BLOCK:
                {
                alt57=3;
                }
                break;
            case OPTIONAL:
            case CLOSURE:
            case POSITIVE_CLOSURE:
                {
                alt57=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 57, 0, input);

                throw nvae;
            }

            switch (alt57) {
                case 1 :
                    // ANTLRWalker.g:494:3: stt= rewrite_tree_atom
                    {
                    pushFollow(FOLLOW_rewrite_tree_atom_in_rewrite_tree_element2344);
                    stt=rewrite_tree_atom();

                    state._fsp--;

                    statement=stt;

                    }
                    break;
                case 2 :
                    // ANTLRWalker.g:495:5: stt= rewrite_tree
                    {
                    pushFollow(FOLLOW_rewrite_tree_in_rewrite_tree_element2354);
                    stt=rewrite_tree();

                    state._fsp--;

                    statement=stt;

                    }
                    break;
                case 3 :
                    // ANTLRWalker.g:496:5: stt1= rewrite_tree_block
                    {
                    pushFollow(FOLLOW_rewrite_tree_block_in_rewrite_tree_element2364);
                    stt1=rewrite_tree_block();

                    state._fsp--;

                    statement=stt1;

                    }
                    break;
                case 4 :
                    // ANTLRWalker.g:497:5: stt= rewrite_tree_ebnf
                    {
                    pushFollow(FOLLOW_rewrite_tree_ebnf_in_rewrite_tree_element2374);
                    stt=rewrite_tree_ebnf();

                    state._fsp--;

                    statement=stt;

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return statement;
    }
    // $ANTLR end "rewrite_tree_element"


    // $ANTLR start "rewrite_tree_atom"
    // ANTLRWalker.g:500:1: rewrite_tree_atom returns [StatementBuilder statement = null] : ( ^( TOKEN_REF elementOptions[(CallExpressionBuilder)statement] ARG_ACTION ) | ^( TOKEN_REF elementOptions[(CallExpressionBuilder)statement] ) | ^( TOKEN_REF ARG_ACTION ) | TOKEN_REF | RULE_REF | ^( STRING_LITERAL elementOptions[(CallExpressionBuilder)statement] ) | STRING_LITERAL | LABEL | ACTION );
    public final StatementBuilder rewrite_tree_atom() throws RecognitionException {
        StatementBuilder statement =  null;

        CommonTree TOKEN_REF64=null;
        CommonTree ARG_ACTION65=null;
        CommonTree TOKEN_REF66=null;
        CommonTree TOKEN_REF67=null;
        CommonTree ARG_ACTION68=null;
        CommonTree TOKEN_REF69=null;
        CommonTree RULE_REF70=null;
        CommonTree STRING_LITERAL71=null;
        CommonTree STRING_LITERAL72=null;
        CommonTree LABEL73=null;
        CommonTree ACTION74=null;

        try {
            // ANTLRWalker.g:501:3: ( ^( TOKEN_REF elementOptions[(CallExpressionBuilder)statement] ARG_ACTION ) | ^( TOKEN_REF elementOptions[(CallExpressionBuilder)statement] ) | ^( TOKEN_REF ARG_ACTION ) | TOKEN_REF | RULE_REF | ^( STRING_LITERAL elementOptions[(CallExpressionBuilder)statement] ) | STRING_LITERAL | LABEL | ACTION )
            int alt58=9;
            alt58 = dfa58.predict(input);
            switch (alt58) {
                case 1 :
                    // ANTLRWalker.g:502:3: ^( TOKEN_REF elementOptions[(CallExpressionBuilder)statement] ARG_ACTION )
                    {
                    TOKEN_REF64=(CommonTree)match(input,TOKEN_REF,FOLLOW_TOKEN_REF_in_rewrite_tree_atom2396); 

                    statement=factory.newCallExpressionBuilder().name(TOKEN_REF64);

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_elementOptions_in_rewrite_tree_atom2400);
                    elementOptions((CallExpressionBuilder)statement);

                    state._fsp--;

                    ARG_ACTION65=(CommonTree)match(input,ARG_ACTION,FOLLOW_ARG_ACTION_in_rewrite_tree_atom2403); 
                    ((CallExpressionBuilder)statement).parameters(ARG_ACTION65);

                    match(input, Token.UP, null); 

                    }
                    break;
                case 2 :
                    // ANTLRWalker.g:504:3: ^( TOKEN_REF elementOptions[(CallExpressionBuilder)statement] )
                    {
                    TOKEN_REF66=(CommonTree)match(input,TOKEN_REF,FOLLOW_TOKEN_REF_in_rewrite_tree_atom2416); 

                    statement=factory.newCallExpressionBuilder().name(TOKEN_REF66);

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_elementOptions_in_rewrite_tree_atom2420);
                    elementOptions((CallExpressionBuilder)statement);

                    state._fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 3 :
                    // ANTLRWalker.g:506:3: ^( TOKEN_REF ARG_ACTION )
                    {
                    TOKEN_REF67=(CommonTree)match(input,TOKEN_REF,FOLLOW_TOKEN_REF_in_rewrite_tree_atom2432); 

                    match(input, Token.DOWN, null); 
                    ARG_ACTION68=(CommonTree)match(input,ARG_ACTION,FOLLOW_ARG_ACTION_in_rewrite_tree_atom2434); 

                    match(input, Token.UP, null); 
                    statement=factory.newCallExpressionBuilder().name(TOKEN_REF67).parameters(ARG_ACTION68);

                    }
                    break;
                case 4 :
                    // ANTLRWalker.g:508:5: TOKEN_REF
                    {
                    TOKEN_REF69=(CommonTree)match(input,TOKEN_REF,FOLLOW_TOKEN_REF_in_rewrite_tree_atom2446); 
                    statement=factory.newCallExpressionBuilder().name(TOKEN_REF69);

                    }
                    break;
                case 5 :
                    // ANTLRWalker.g:509:5: RULE_REF
                    {
                    RULE_REF70=(CommonTree)match(input,RULE_REF,FOLLOW_RULE_REF_in_rewrite_tree_atom2454); 
                    statement=factory.newCallExpressionBuilder().name(RULE_REF70);

                    }
                    break;
                case 6 :
                    // ANTLRWalker.g:511:3: ^( STRING_LITERAL elementOptions[(CallExpressionBuilder)statement] )
                    {
                    STRING_LITERAL71=(CommonTree)match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_rewrite_tree_atom2465); 

                    statement=factory.newCallExpressionBuilder().name(STRING_LITERAL71);

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_elementOptions_in_rewrite_tree_atom2469);
                    elementOptions((CallExpressionBuilder)statement);

                    state._fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 7 :
                    // ANTLRWalker.g:512:5: STRING_LITERAL
                    {
                    STRING_LITERAL72=(CommonTree)match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_rewrite_tree_atom2477); 
                    statement=factory.newCallExpressionBuilder().name(STRING_LITERAL72);

                    }
                    break;
                case 8 :
                    // ANTLRWalker.g:513:5: LABEL
                    {
                    LABEL73=(CommonTree)match(input,LABEL,FOLLOW_LABEL_in_rewrite_tree_atom2485); 
                    statement=factory.newCallExpressionBuilder().name(LABEL73).labeled();

                    }
                    break;
                case 9 :
                    // ANTLRWalker.g:514:5: ACTION
                    {
                    ACTION74=(CommonTree)match(input,ACTION,FOLLOW_ACTION_in_rewrite_tree_atom2493); 
                    statement=factory.newActionBuilder().action(ACTION74);

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return statement;
    }
    // $ANTLR end "rewrite_tree_atom"


    // $ANTLR start "rewrite_tree_ebnf"
    // ANTLRWalker.g:517:1: rewrite_tree_ebnf returns [StatementBuilder statement=null] : ^(op= ebnfSuffix stt= rewrite_tree_block ) ;
    public final StatementBuilder rewrite_tree_ebnf() throws RecognitionException {
        StatementBuilder statement = null;

        CommonTree op = null;

        BlockBuilder stt = null;


        try {
            // ANTLRWalker.g:518:3: ( ^(op= ebnfSuffix stt= rewrite_tree_block ) )
            // ANTLRWalker.g:519:3: ^(op= ebnfSuffix stt= rewrite_tree_block )
            {
            pushFollow(FOLLOW_ebnfSuffix_in_rewrite_tree_ebnf2517);
            op=ebnfSuffix();

            state._fsp--;


            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_rewrite_tree_block_in_rewrite_tree_ebnf2521);
            stt=rewrite_tree_block();

            state._fsp--;

            statement=stt.ebnf(op);

            match(input, Token.UP, null); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return statement;
    }
    // $ANTLR end "rewrite_tree_ebnf"


    // $ANTLR start "rewrite_tree"
    // ANTLRWalker.g:522:1: rewrite_tree returns [TreeBuilder statement = factory.newTreeBuilder();] : ^( TREE_BEGIN tatom= rewrite_tree_atom (telem= rewrite_tree_element )* ) ;
    public final TreeBuilder rewrite_tree() throws RecognitionException {
        TreeBuilder statement =  factory.newTreeBuilder();;

        StatementBuilder tatom = null;

        StatementBuilder telem = null;


        try {
            // ANTLRWalker.g:523:3: ( ^( TREE_BEGIN tatom= rewrite_tree_atom (telem= rewrite_tree_element )* ) )
            // ANTLRWalker.g:524:3: ^( TREE_BEGIN tatom= rewrite_tree_atom (telem= rewrite_tree_element )* )
            {
            match(input,TREE_BEGIN,FOLLOW_TREE_BEGIN_in_rewrite_tree2549); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_rewrite_tree_atom_in_rewrite_tree2553);
            tatom=rewrite_tree_atom();

            state._fsp--;

            statement.statement(tatom);
            // ANTLRWalker.g:526:5: (telem= rewrite_tree_element )*
            loop59:
            do {
                int alt59=2;
                int LA59_0 = input.LA(1);

                if ( ((LA59_0>=BLOCK && LA59_0<=POSITIVE_CLOSURE)||LA59_0==LABEL||LA59_0==TREE_BEGIN||(LA59_0>=TOKEN_REF && LA59_0<=ACTION)||LA59_0==RULE_REF) ) {
                    alt59=1;
                }


                switch (alt59) {
            	case 1 :
            	    // ANTLRWalker.g:527:7: telem= rewrite_tree_element
            	    {
            	    pushFollow(FOLLOW_rewrite_tree_element_in_rewrite_tree2571);
            	    telem=rewrite_tree_element();

            	    state._fsp--;

            	    statement.statement(telem);

            	    }
            	    break;

            	default :
            	    break loop59;
                }
            } while (true);


            match(input, Token.UP, null); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return statement;
    }
    // $ANTLR end "rewrite_tree"


    // $ANTLR start "rewrite_template"
    // ANTLRWalker.g:532:1: rewrite_template returns [TemplateBuilder template=factory.newTemplateBuilder()] : ( ^( TEMPLATE ID rewrite_template_args[template] (inlineNode= DOUBLE_QUOTE_STRING_LITERAL | inlineNode= DOUBLE_ANGLE_STRING_LITERAL ) ) | rewrite_template_ref[template] | rewrite_indirect_template_head[template] | ACTION );
    public final TemplateBuilder rewrite_template() throws RecognitionException {
        TemplateBuilder template = factory.newTemplateBuilder();

        CommonTree inlineNode=null;
        CommonTree ID75=null;
        CommonTree ACTION76=null;

        try {
            // ANTLRWalker.g:533:3: ( ^( TEMPLATE ID rewrite_template_args[template] (inlineNode= DOUBLE_QUOTE_STRING_LITERAL | inlineNode= DOUBLE_ANGLE_STRING_LITERAL ) ) | rewrite_template_ref[template] | rewrite_indirect_template_head[template] | ACTION )
            int alt61=4;
            alt61 = dfa61.predict(input);
            switch (alt61) {
                case 1 :
                    // ANTLRWalker.g:534:3: ^( TEMPLATE ID rewrite_template_args[template] (inlineNode= DOUBLE_QUOTE_STRING_LITERAL | inlineNode= DOUBLE_ANGLE_STRING_LITERAL ) )
                    {
                    match(input,TEMPLATE,FOLLOW_TEMPLATE_in_rewrite_template2610); 

                    match(input, Token.DOWN, null); 
                    ID75=(CommonTree)match(input,ID,FOLLOW_ID_in_rewrite_template2612); 
                    template.name(ID75);
                    pushFollow(FOLLOW_rewrite_template_args_in_rewrite_template2616);
                    rewrite_template_args(template);

                    state._fsp--;

                    // ANTLRWalker.g:536:5: (inlineNode= DOUBLE_QUOTE_STRING_LITERAL | inlineNode= DOUBLE_ANGLE_STRING_LITERAL )
                    int alt60=2;
                    int LA60_0 = input.LA(1);

                    if ( (LA60_0==DOUBLE_QUOTE_STRING_LITERAL) ) {
                        alt60=1;
                    }
                    else if ( (LA60_0==DOUBLE_ANGLE_STRING_LITERAL) ) {
                        alt60=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 60, 0, input);

                        throw nvae;
                    }
                    switch (alt60) {
                        case 1 :
                            // ANTLRWalker.g:537:7: inlineNode= DOUBLE_QUOTE_STRING_LITERAL
                            {
                            inlineNode=(CommonTree)match(input,DOUBLE_QUOTE_STRING_LITERAL,FOLLOW_DOUBLE_QUOTE_STRING_LITERAL_in_rewrite_template2633); 

                            }
                            break;
                        case 2 :
                            // ANTLRWalker.g:538:9: inlineNode= DOUBLE_ANGLE_STRING_LITERAL
                            {
                            inlineNode=(CommonTree)match(input,DOUBLE_ANGLE_STRING_LITERAL,FOLLOW_DOUBLE_ANGLE_STRING_LITERAL_in_rewrite_template2645); 

                            }
                            break;

                    }

                    template.inlineTemplate(inlineNode);

                    match(input, Token.UP, null); 

                    }
                    break;
                case 2 :
                    // ANTLRWalker.g:542:5: rewrite_template_ref[template]
                    {
                    pushFollow(FOLLOW_rewrite_template_ref_in_rewrite_template2668);
                    rewrite_template_ref(template);

                    state._fsp--;


                    }
                    break;
                case 3 :
                    // ANTLRWalker.g:543:5: rewrite_indirect_template_head[template]
                    {
                    pushFollow(FOLLOW_rewrite_indirect_template_head_in_rewrite_template2675);
                    rewrite_indirect_template_head(template);

                    state._fsp--;


                    }
                    break;
                case 4 :
                    // ANTLRWalker.g:544:5: ACTION
                    {
                    ACTION76=(CommonTree)match(input,ACTION,FOLLOW_ACTION_in_rewrite_template2682); 
                    template.action(ACTION76).setSimpleActionTemplate(true);

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return template;
    }
    // $ANTLR end "rewrite_template"


    // $ANTLR start "rewrite_template_ref"
    // ANTLRWalker.g:547:1: rewrite_template_ref[TemplateBuilder template] : ^( TEMPLATE ID rewrite_template_args[template] ) ;
    public final void rewrite_template_ref(TemplateBuilder template) throws RecognitionException {
        CommonTree ID77=null;

        try {
            // ANTLRWalker.g:549:3: ( ^( TEMPLATE ID rewrite_template_args[template] ) )
            // ANTLRWalker.g:550:3: ^( TEMPLATE ID rewrite_template_args[template] )
            {
            match(input,TEMPLATE,FOLLOW_TEMPLATE_in_rewrite_template_ref2703); 

            match(input, Token.DOWN, null); 
            ID77=(CommonTree)match(input,ID,FOLLOW_ID_in_rewrite_template_ref2705); 
            template.name(ID77);
            pushFollow(FOLLOW_rewrite_template_args_in_rewrite_template_ref2709);
            rewrite_template_args(template);

            state._fsp--;


            match(input, Token.UP, null); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "rewrite_template_ref"


    // $ANTLR start "rewrite_indirect_template_head"
    // ANTLRWalker.g:553:1: rewrite_indirect_template_head[TemplateBuilder template] : ^( TEMPLATE ACTION rewrite_template_args[template] ) ;
    public final void rewrite_indirect_template_head(TemplateBuilder template) throws RecognitionException {
        CommonTree ACTION78=null;

        try {
            // ANTLRWalker.g:555:3: ( ^( TEMPLATE ACTION rewrite_template_args[template] ) )
            // ANTLRWalker.g:556:3: ^( TEMPLATE ACTION rewrite_template_args[template] )
            {
            match(input,TEMPLATE,FOLLOW_TEMPLATE_in_rewrite_indirect_template_head2730); 

            match(input, Token.DOWN, null); 
            ACTION78=(CommonTree)match(input,ACTION,FOLLOW_ACTION_in_rewrite_indirect_template_head2732); 
            template.action(ACTION78);
            pushFollow(FOLLOW_rewrite_template_args_in_rewrite_indirect_template_head2736);
            rewrite_template_args(template);

            state._fsp--;


            match(input, Token.UP, null); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "rewrite_indirect_template_head"


    // $ANTLR start "rewrite_template_args"
    // ANTLRWalker.g:559:1: rewrite_template_args[TemplateBuilder template] : ( ^( ARGLIST ( rewrite_template_arg[template] )+ ) | ARGLIST );
    public final void rewrite_template_args(TemplateBuilder template) throws RecognitionException {
        try {
            // ANTLRWalker.g:560:3: ( ^( ARGLIST ( rewrite_template_arg[template] )+ ) | ARGLIST )
            int alt63=2;
            int LA63_0 = input.LA(1);

            if ( (LA63_0==ARGLIST) ) {
                int LA63_1 = input.LA(2);

                if ( (LA63_1==DOWN) ) {
                    alt63=1;
                }
                else if ( (LA63_1==UP||(LA63_1>=DOUBLE_QUOTE_STRING_LITERAL && LA63_1<=DOUBLE_ANGLE_STRING_LITERAL)) ) {
                    alt63=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 63, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 63, 0, input);

                throw nvae;
            }
            switch (alt63) {
                case 1 :
                    // ANTLRWalker.g:561:3: ^( ARGLIST ( rewrite_template_arg[template] )+ )
                    {
                    match(input,ARGLIST,FOLLOW_ARGLIST_in_rewrite_template_args2755); 

                    match(input, Token.DOWN, null); 
                    // ANTLRWalker.g:561:13: ( rewrite_template_arg[template] )+
                    int cnt62=0;
                    loop62:
                    do {
                        int alt62=2;
                        int LA62_0 = input.LA(1);

                        if ( (LA62_0==ARG) ) {
                            alt62=1;
                        }


                        switch (alt62) {
                    	case 1 :
                    	    // ANTLRWalker.g:561:13: rewrite_template_arg[template]
                    	    {
                    	    pushFollow(FOLLOW_rewrite_template_arg_in_rewrite_template_args2757);
                    	    rewrite_template_arg(template);

                    	    state._fsp--;


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt62 >= 1 ) break loop62;
                                EarlyExitException eee =
                                    new EarlyExitException(62, input);
                                throw eee;
                        }
                        cnt62++;
                    } while (true);


                    match(input, Token.UP, null); 

                    }
                    break;
                case 2 :
                    // ANTLRWalker.g:562:5: ARGLIST
                    {
                    match(input,ARGLIST,FOLLOW_ARGLIST_in_rewrite_template_args2766); 

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "rewrite_template_args"


    // $ANTLR start "rewrite_template_arg"
    // ANTLRWalker.g:565:1: rewrite_template_arg[TemplateBuilder template] : ^( ARG ID ACTION ) ;
    public final void rewrite_template_arg(TemplateBuilder template) throws RecognitionException {
        CommonTree ID79=null;
        CommonTree ACTION80=null;

        try {
            // ANTLRWalker.g:566:3: ( ^( ARG ID ACTION ) )
            // ANTLRWalker.g:567:3: ^( ARG ID ACTION )
            {
            match(input,ARG,FOLLOW_ARG_in_rewrite_template_arg2783); 

            match(input, Token.DOWN, null); 
            ID79=(CommonTree)match(input,ID,FOLLOW_ID_in_rewrite_template_arg2785); 
            ACTION80=(CommonTree)match(input,ACTION,FOLLOW_ACTION_in_rewrite_template_arg2787); 
            template.parameter(ID79, ACTION80);

            match(input, Token.UP, null); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "rewrite_template_arg"

    // Delegated rules


    protected DFA41 dfa41 = new DFA41(this);
    protected DFA50 dfa50 = new DFA50(this);
    protected DFA58 dfa58 = new DFA58(this);
    protected DFA61 dfa61 = new DFA61(this);
    static final String DFA41_eotS =
        "\21\uffff";
    static final String DFA41_eofS =
        "\21\uffff";
    static final String DFA41_minS =
        "\1\10\4\2\6\uffff\1\24\3\10\2\uffff";
    static final String DFA41_maxS =
        "\1\133\4\2\6\uffff\1\24\3\133\2\uffff";
    static final String DFA41_acceptS =
        "\5\uffff\1\3\1\4\1\5\1\6\1\7\1\10\4\uffff\1\2\1\1";
    static final String DFA41_specialS =
        "\21\uffff}>";
    static final String[] DFA41_transitionS = {
            "\5\6\1\uffff\1\5\21\uffff\1\10\1\11\1\6\2\uffff\1\12\1\3\1\4"+
            "\3\uffff\1\5\1\1\2\uffff\2\5\1\7\3\uffff\1\5\43\uffff\1\2\1"+
            "\uffff\1\5",
            "\1\13",
            "\1\13",
            "\1\14",
            "\1\15",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\16",
            "\1\6\5\uffff\1\5\27\uffff\2\5\3\uffff\1\5\3\uffff\2\5\4\uffff"+
            "\1\5\45\uffff\1\5",
            "\1\6\5\uffff\1\5\27\uffff\2\5\3\uffff\1\5\3\uffff\2\5\4\uffff"+
            "\1\5\45\uffff\1\5",
            "\1\20\5\uffff\1\17\27\uffff\2\17\3\uffff\1\17\3\uffff\2\17"+
            "\4\uffff\1\17\45\uffff\1\17",
            "",
            ""
    };

    static final short[] DFA41_eot = DFA.unpackEncodedString(DFA41_eotS);
    static final short[] DFA41_eof = DFA.unpackEncodedString(DFA41_eofS);
    static final char[] DFA41_min = DFA.unpackEncodedStringToUnsignedChars(DFA41_minS);
    static final char[] DFA41_max = DFA.unpackEncodedStringToUnsignedChars(DFA41_maxS);
    static final short[] DFA41_accept = DFA.unpackEncodedString(DFA41_acceptS);
    static final short[] DFA41_special = DFA.unpackEncodedString(DFA41_specialS);
    static final short[][] DFA41_transition;

    static {
        int numStates = DFA41_transitionS.length;
        DFA41_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA41_transition[i] = DFA.unpackEncodedString(DFA41_transitionS[i]);
        }
    }

    class DFA41 extends DFA {

        public DFA41(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 41;
            this.eot = DFA41_eot;
            this.eof = DFA41_eof;
            this.min = DFA41_min;
            this.max = DFA41_max;
            this.accept = DFA41_accept;
            this.special = DFA41_special;
            this.transition = DFA41_transition;
        }
        public String getDescription() {
            return "291:1: elementNoOptionSpec returns [StatementBuilder statement = null;] : ( ^( (op= '=' | op= '+=' ) ID stt= block ) | ^( (op= '=' | op= '+=' ) ID stt= atom ) | stt= atom | stt= ebnf | ACTION | SEMPRED | GATED_SEMPRED | stt1= treeSpec );";
        }
    }
    static final String DFA50_eotS =
        "\26\uffff";
    static final String DFA50_eofS =
        "\26\uffff";
    static final String DFA50_minS =
        "\1\53\2\2\1\uffff\1\55\4\uffff\1\2\1\24\1\3\1\2\1\3\1\24\2\uffff"+
        "\1\24\4\3";
    static final String DFA50_maxS =
        "\1\60\2\133\1\uffff\1\64\4\uffff\1\2\2\54\1\2\1\64\1\24\2\uffff"+
        "\1\60\2\3\2\54";
    static final String DFA50_acceptS =
        "\3\uffff\1\7\1\uffff\1\4\1\5\1\6\1\3\6\uffff\1\2\1\1\5\uffff";
    static final String DFA50_specialS =
        "\26\uffff}>";
    static final String[] DFA50_transitionS = {
            "\1\3\3\uffff\1\1\1\2",
            "\1\4\1\5\4\uffff\5\5\1\uffff\1\5\4\uffff\1\5\14\uffff\3\5\2"+
            "\uffff\3\5\3\uffff\2\5\2\uffff\3\5\3\uffff\1\5\43\uffff\1\5"+
            "\1\uffff\1\5",
            "\1\6\1\7\4\uffff\5\7\1\uffff\1\7\4\uffff\1\7\14\uffff\3\7\2"+
            "\uffff\3\7\3\uffff\2\7\2\uffff\3\7\3\uffff\1\7\43\uffff\1\7"+
            "\1\uffff\1\7",
            "",
            "\1\11\6\uffff\1\10",
            "",
            "",
            "",
            "",
            "\1\12",
            "\1\13\27\uffff\1\14",
            "\1\15\20\uffff\1\13\27\uffff\1\14",
            "\1\16",
            "\1\17\60\uffff\1\20",
            "\1\21",
            "",
            "",
            "\1\22\33\uffff\1\23",
            "\1\24",
            "\1\25",
            "\1\15\20\uffff\1\13\27\uffff\1\14",
            "\1\15\20\uffff\1\13\27\uffff\1\14"
    };

    static final short[] DFA50_eot = DFA.unpackEncodedString(DFA50_eotS);
    static final short[] DFA50_eof = DFA.unpackEncodedString(DFA50_eofS);
    static final char[] DFA50_min = DFA.unpackEncodedStringToUnsignedChars(DFA50_minS);
    static final char[] DFA50_max = DFA.unpackEncodedStringToUnsignedChars(DFA50_maxS);
    static final short[] DFA50_accept = DFA.unpackEncodedString(DFA50_acceptS);
    static final short[] DFA50_special = DFA.unpackEncodedString(DFA50_specialS);
    static final short[][] DFA50_transition;

    static {
        int numStates = DFA50_transitionS.length;
        DFA50_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA50_transition[i] = DFA.unpackEncodedString(DFA50_transitionS[i]);
        }
    }

    class DFA50 extends DFA {

        public DFA50(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 50;
            this.eot = DFA50_eot;
            this.eof = DFA50_eof;
            this.min = DFA50_min;
            this.max = DFA50_max;
            this.accept = DFA50_accept;
            this.special = DFA50_special;
            this.transition = DFA50_transition;
        }
        public String getDescription() {
            return "406:1: terminal returns [CallExpressionBuilder statement = factory.newCallExpressionBuilder();] : ( ^( TOKEN_REF elementOptions[statement] ARG_ACTION ) | ^( TOKEN_REF elementOptions[statement] ) | ^( TOKEN_REF ARG_ACTION ) | TOKEN_REF | ^( STRING_LITERAL elementOptions[statement] ) | STRING_LITERAL | any= '.' );";
        }
    }
    static final String DFA58_eotS =
        "\30\uffff";
    static final String DFA58_eofS =
        "\30\uffff";
    static final String DFA58_minS =
        "\1\35\1\2\1\uffff\1\2\2\uffff\1\55\4\uffff\1\2\1\24\1\3\1\2\1\3"+
        "\1\24\2\uffff\1\24\4\3";
    static final String DFA58_maxS =
        "\2\65\1\uffff\1\65\2\uffff\1\64\4\uffff\1\2\2\54\1\2\1\64\1\24\2"+
        "\uffff\1\60\2\3\2\54";
    static final String DFA58_acceptS =
        "\2\uffff\1\5\1\uffff\1\10\1\11\1\uffff\1\4\1\6\1\7\1\3\6\uffff\1"+
        "\2\1\1\5\uffff";
    static final String DFA58_specialS =
        "\30\uffff}>";
    static final String[] DFA58_transitionS = {
            "\1\4\21\uffff\1\1\1\3\1\5\3\uffff\1\2",
            "\1\6\1\7\4\uffff\4\7\7\uffff\1\7\11\uffff\1\7\7\uffff\1\7\11"+
            "\uffff\3\7\3\uffff\1\7",
            "",
            "\1\10\1\11\4\uffff\4\11\7\uffff\1\11\11\uffff\1\11\7\uffff"+
            "\1\11\11\uffff\3\11\3\uffff\1\11",
            "",
            "",
            "\1\13\6\uffff\1\12",
            "",
            "",
            "",
            "",
            "\1\14",
            "\1\15\27\uffff\1\16",
            "\1\17\20\uffff\1\15\27\uffff\1\16",
            "\1\20",
            "\1\21\60\uffff\1\22",
            "\1\23",
            "",
            "",
            "\1\24\33\uffff\1\25",
            "\1\26",
            "\1\27",
            "\1\17\20\uffff\1\15\27\uffff\1\16",
            "\1\17\20\uffff\1\15\27\uffff\1\16"
    };

    static final short[] DFA58_eot = DFA.unpackEncodedString(DFA58_eotS);
    static final short[] DFA58_eof = DFA.unpackEncodedString(DFA58_eofS);
    static final char[] DFA58_min = DFA.unpackEncodedStringToUnsignedChars(DFA58_minS);
    static final char[] DFA58_max = DFA.unpackEncodedStringToUnsignedChars(DFA58_maxS);
    static final short[] DFA58_accept = DFA.unpackEncodedString(DFA58_acceptS);
    static final short[] DFA58_special = DFA.unpackEncodedString(DFA58_specialS);
    static final short[][] DFA58_transition;

    static {
        int numStates = DFA58_transitionS.length;
        DFA58_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA58_transition[i] = DFA.unpackEncodedString(DFA58_transitionS[i]);
        }
    }

    class DFA58 extends DFA {

        public DFA58(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 58;
            this.eot = DFA58_eot;
            this.eof = DFA58_eof;
            this.min = DFA58_min;
            this.max = DFA58_max;
            this.accept = DFA58_accept;
            this.special = DFA58_special;
            this.transition = DFA58_transition;
        }
        public String getDescription() {
            return "500:1: rewrite_tree_atom returns [StatementBuilder statement = null] : ( ^( TOKEN_REF elementOptions[(CallExpressionBuilder)statement] ARG_ACTION ) | ^( TOKEN_REF elementOptions[(CallExpressionBuilder)statement] ) | ^( TOKEN_REF ARG_ACTION ) | TOKEN_REF | RULE_REF | ^( STRING_LITERAL elementOptions[(CallExpressionBuilder)statement] ) | STRING_LITERAL | LABEL | ACTION );";
        }
    }
    static final String DFA61_eotS =
        "\20\uffff";
    static final String DFA61_eofS =
        "\20\uffff";
    static final String DFA61_minS =
        "\1\36\1\2\1\uffff\1\24\1\26\1\uffff\1\2\1\25\2\uffff\1\2\1\24\1"+
        "\61\3\3";
    static final String DFA61_maxS =
        "\1\61\1\2\1\uffff\1\61\1\26\1\uffff\1\67\1\25\2\uffff\1\2\1\24\1"+
        "\61\1\3\1\25\1\67";
    static final String DFA61_acceptS =
        "\2\uffff\1\4\2\uffff\1\3\2\uffff\1\2\1\1\6\uffff";
    static final String DFA61_specialS =
        "\20\uffff}>";
    static final String[] DFA61_transitionS = {
            "\1\1\22\uffff\1\2",
            "\1\3",
            "",
            "\1\4\34\uffff\1\5",
            "\1\6",
            "",
            "\1\7\1\10\62\uffff\2\11",
            "\1\12",
            "",
            "",
            "\1\13",
            "\1\14",
            "\1\15",
            "\1\16",
            "\1\17\21\uffff\1\12",
            "\1\10\62\uffff\2\11"
    };

    static final short[] DFA61_eot = DFA.unpackEncodedString(DFA61_eotS);
    static final short[] DFA61_eof = DFA.unpackEncodedString(DFA61_eofS);
    static final char[] DFA61_min = DFA.unpackEncodedStringToUnsignedChars(DFA61_minS);
    static final char[] DFA61_max = DFA.unpackEncodedStringToUnsignedChars(DFA61_maxS);
    static final short[] DFA61_accept = DFA.unpackEncodedString(DFA61_acceptS);
    static final short[] DFA61_special = DFA.unpackEncodedString(DFA61_specialS);
    static final short[][] DFA61_transition;

    static {
        int numStates = DFA61_transitionS.length;
        DFA61_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA61_transition[i] = DFA.unpackEncodedString(DFA61_transitionS[i]);
        }
    }

    class DFA61 extends DFA {

        public DFA61(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 61;
            this.eot = DFA61_eot;
            this.eof = DFA61_eof;
            this.min = DFA61_min;
            this.max = DFA61_max;
            this.accept = DFA61_accept;
            this.special = DFA61_special;
            this.transition = DFA61_transition;
        }
        public String getDescription() {
            return "532:1: rewrite_template returns [TemplateBuilder template=factory.newTemplateBuilder()] : ( ^( TEMPLATE ID rewrite_template_args[template] (inlineNode= DOUBLE_QUOTE_STRING_LITERAL | inlineNode= DOUBLE_ANGLE_STRING_LITERAL ) ) | rewrite_template_ref[template] | rewrite_indirect_template_head[template] | ACTION );";
        }
    }
 

    public static final BitSet FOLLOW_grammarType_in_grammarDef93 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_grammarDef95 = new BitSet(new long[]{0x0004400080000090L,0x0000000000001200L});
    public static final BitSet FOLLOW_DOC_COMMENT_in_grammarDef111 = new BitSet(new long[]{0x0004400080000090L,0x0000000000001200L});
    public static final BitSet FOLLOW_optionsSpec_in_grammarDef126 = new BitSet(new long[]{0x0004400080000090L,0x0000000000001200L});
    public static final BitSet FOLLOW_delegateGrammars_in_grammarDef130 = new BitSet(new long[]{0x0004400080000090L,0x0000000000001200L});
    public static final BitSet FOLLOW_tokensSpec_in_grammarDef133 = new BitSet(new long[]{0x0004400080000090L,0x0000000000001200L});
    public static final BitSet FOLLOW_attrScope_in_grammarDef136 = new BitSet(new long[]{0x0004400080000090L,0x0000000000001200L});
    public static final BitSet FOLLOW_action_in_grammarDef141 = new BitSet(new long[]{0x0004400080000090L,0x0000000000001200L});
    public static final BitSet FOLLOW_rule_in_grammarDef146 = new BitSet(new long[]{0x0004400080000098L,0x0000000000001200L});
    public static final BitSet FOLLOW_LEXER_GRAMMAR_in_grammarType169 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PARSER_GRAMMAR_in_grammarType177 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TREE_GRAMMAR_in_grammarType185 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COMBINED_GRAMMAR_in_grammarType193 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_73_in_delegateGrammars213 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_delegateGrammar_in_delegateGrammars215 = new BitSet(new long[]{0x0000120000100000L});
    public static final BitSet FOLLOW_SEMI_in_delegateGrammars220 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_ASSIGN_in_delegateGrammar241 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_delegateGrammar245 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_ID_in_delegateGrammar249 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_ID_in_delegateGrammar260 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TOKENS_in_tokensSpec278 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_tokenSpec_in_tokensSpec280 = new BitSet(new long[]{0x0000900000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_75_in_tokensSpec285 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_ASSIGN_in_tokenSpec306 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_TOKEN_REF_in_tokenSpec308 = new BitSet(new long[]{0x0001000000000000L});
    public static final BitSet FOLLOW_STRING_LITERAL_in_tokenSpec310 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_TOKEN_REF_in_tokenSpec321 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SCOPE_in_attrScope339 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_attrScope341 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_ACTION_in_attrScope343 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_76_in_action366 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_action370 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_ID_in_action374 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_ACTION_in_action378 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_76_in_action394 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_action398 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_ACTION_in_action402 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_OPTIONS_in_optionsSpec424 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_option_in_optionsSpec426 = new BitSet(new long[]{0x0000100000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_75_in_optionsSpec432 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_ASSIGN_in_option454 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_option456 = new BitSet(new long[]{0x0009000000100000L});
    public static final BitSet FOLLOW_optionValue_in_option460 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_ID_in_optionValue482 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_LITERAL_in_optionValue490 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INT_in_optionValue498 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_in_rule531 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_DOC_COMMENT_in_rule547 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_ID_in_rule562 = new BitSet(new long[]{0x0004049080A00000L,0x00000000000F1000L});
    public static final BitSet FOLLOW_modifier_in_rule580 = new BitSet(new long[]{0x0004048080A00000L,0x0000000000081000L});
    public static final BitSet FOLLOW_BANG_in_rule603 = new BitSet(new long[]{0x0004040080A00000L,0x0000000000081000L});
    public static final BitSet FOLLOW_ARG_in_rule627 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ARG_ACTION_in_rule631 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_RET_in_rule656 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ARG_ACTION_in_rule660 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_throwsSpec_in_rule676 = new BitSet(new long[]{0x0004040080000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_optionsSpec_in_rule680 = new BitSet(new long[]{0x0000040080000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_ruleScopeSpec_in_rule684 = new BitSet(new long[]{0x0000040000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_ruleAction_in_rule687 = new BitSet(new long[]{0x0000040000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_COLON_in_rule690 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_altList_in_rule694 = new BitSet(new long[]{0x0000000000020000L,0x0000000001800000L});
    public static final BitSet FOLLOW_exceptionGroup_in_rule698 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_EOR_in_rule702 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_80_in_modifier740 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_81_in_modifier748 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_82_in_modifier756 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FRAGMENT_in_modifier764 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_76_in_ruleAction784 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_ruleAction786 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_ACTION_in_ruleAction788 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_83_in_throwsSpec810 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_throwsSpec814 = new BitSet(new long[]{0x0000000000100008L});
    public static final BitSet FOLLOW_SCOPE_in_ruleScopeSpec838 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ACTION_in_ruleScopeSpec840 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_SCOPE_in_ruleScopeSpec861 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ACTION_in_ruleScopeSpec863 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_ID_in_ruleScopeSpec879 = new BitSet(new long[]{0x0000000000100008L});
    public static final BitSet FOLLOW_SCOPE_in_ruleScopeSpec909 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_ruleScopeSpec923 = new BitSet(new long[]{0x0000000000100008L});
    public static final BitSet FOLLOW_BLOCK_in_block964 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_optionsSpec_in_block966 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_alternative_in_block984 = new BitSet(new long[]{0x0000010000050000L});
    public static final BitSet FOLLOW_rewrite_in_block990 = new BitSet(new long[]{0x0000000000050000L});
    public static final BitSet FOLLOW_EOB_in_block1005 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_BLOCK_in_altList1039 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_alternative_in_altList1055 = new BitSet(new long[]{0x0000010000050000L});
    public static final BitSet FOLLOW_rewrite_in_altList1061 = new BitSet(new long[]{0x0000000000050000L});
    public static final BitSet FOLLOW_EOB_in_altList1076 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_ALT_in_alternative1108 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_element_in_alternative1124 = new BitSet(new long[]{0x002398E700085F00L,0x000000000A000000L});
    public static final BitSet FOLLOW_EOA_in_alternative1139 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_ALT_in_alternative1155 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_EPSILON_in_alternative1157 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_EOA_in_alternative1159 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_exceptionHandler_in_exceptionGroup1178 = new BitSet(new long[]{0x0000000000000002L,0x0000000001800000L});
    public static final BitSet FOLLOW_finallyClause_in_exceptionGroup1182 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_finallyClause_in_exceptionGroup1190 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_87_in_exceptionHandler1210 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ARG_ACTION_in_exceptionHandler1212 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_ACTION_in_exceptionHandler1214 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_88_in_finallyClause1238 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ACTION_in_finallyClause1240 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_elementNoOptionSpec_in_element1266 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ASSIGN_in_elementNoOptionSpec1303 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_89_in_elementNoOptionSpec1315 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_elementNoOptionSpec1327 = new BitSet(new long[]{0x000000C400001F00L});
    public static final BitSet FOLLOW_block_in_elementNoOptionSpec1331 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_ASSIGN_in_elementNoOptionSpec1364 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_89_in_elementNoOptionSpec1376 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_elementNoOptionSpec1388 = new BitSet(new long[]{0x002188C000004000L,0x0000000008000000L});
    public static final BitSet FOLLOW_atom_in_elementNoOptionSpec1392 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_atom_in_elementNoOptionSpec1409 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ebnf_in_elementNoOptionSpec1419 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ACTION_in_elementNoOptionSpec1427 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SEMPRED_in_elementNoOptionSpec1435 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GATED_SEMPRED_in_elementNoOptionSpec1443 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_treeSpec_in_elementNoOptionSpec1453 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ROOT_in_atom1490 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_BANG_in_atom1502 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_atom_in_atom1516 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_range_in_atom1533 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_notSet_in_atom1543 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_call_in_atom1553 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_terminal_in_atom1563 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_REF_in_call1584 = new BitSet(new long[]{0x0010000000000002L});
    public static final BitSet FOLLOW_ARG_ACTION_in_call1586 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_91_in_notSet1611 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_notTerminal_in_notSet1615 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_91_in_notSet1631 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_block_in_notSet1635 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_TREE_BEGIN_in_treeSpec1665 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_element_in_treeSpec1681 = new BitSet(new long[]{0x002398E700085F08L,0x000000000A000000L});
    public static final BitSet FOLLOW_SYNPRED_in_ebnf1717 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_block_in_ebnf1721 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_SYN_SEMPRED_in_ebnf1735 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_block_in_ebnf1739 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_ebnfSuffix_in_ebnf1755 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_block_in_ebnf1759 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_ROOT_in_ebnf1773 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_block_in_ebnf1777 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_BANG_in_ebnf1791 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_block_in_ebnf1795 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_block_in_ebnf1808 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CHAR_RANGE_in_range1832 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_STRING_LITERAL_in_range1836 = new BitSet(new long[]{0x0001000000000000L});
    public static final BitSet FOLLOW_STRING_LITERAL_in_range1840 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_ELEMENT_OPTIONS_in_elementOptions1862 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_elementOption_in_elementOptions1864 = new BitSet(new long[]{0x0000100000100008L});
    public static final BitSet FOLLOW_ID_in_elementOption1883 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ASSIGN_in_elementOption1894 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_elementOption1898 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_ID_in_elementOption1902 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_ASSIGN_in_elementOption1916 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_elementOption1920 = new BitSet(new long[]{0x0001000000000000L});
    public static final BitSet FOLLOW_STRING_LITERAL_in_elementOption1924 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_TOKEN_REF_in_terminal1949 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_elementOptions_in_terminal1951 = new BitSet(new long[]{0x0010000000000000L});
    public static final BitSet FOLLOW_ARG_ACTION_in_terminal1954 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_TOKEN_REF_in_terminal1968 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_elementOptions_in_terminal1970 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_TOKEN_REF_in_terminal1985 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ARG_ACTION_in_terminal1987 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_TOKEN_REF_in_terminal1998 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_LITERAL_in_terminal2009 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_elementOptions_in_terminal2011 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_STRING_LITERAL_in_terminal2023 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOT_in_terminal2033 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TOKEN_REF_in_notTerminal2054 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_LITERAL_in_notTerminal2062 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_OPTIONAL_in_ebnfSuffix2083 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CLOSURE_in_ebnfSuffix2091 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_POSITIVE_CLOSURE_in_ebnfSuffix2099 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_REWRITE_in_rewrite2136 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_SEMPRED_in_rewrite2138 = new BitSet(new long[]{0x0002000040010000L});
    public static final BitSet FOLLOW_rewrite_alternative_in_rewrite2142 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_REWRITE_in_rewrite2157 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_rewrite_alternative_in_rewrite2161 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_rewrite_template_in_rewrite_alternative2195 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rewrite_tree_alternative_in_rewrite_alternative2205 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ALT_in_rewrite_alternative2216 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_EPSILON_in_rewrite_alternative2218 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_EOA_in_rewrite_alternative2220 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_BLOCK_in_rewrite_tree_block2245 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_rewrite_tree_alternative_in_rewrite_tree_block2251 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_EOB_in_rewrite_tree_block2255 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_ALT_in_rewrite_tree_alternative2283 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_rewrite_tree_element_in_rewrite_tree_alternative2299 = new BitSet(new long[]{0x0023802020080F00L});
    public static final BitSet FOLLOW_EOA_in_rewrite_tree_alternative2314 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_rewrite_tree_atom_in_rewrite_tree_element2344 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rewrite_tree_in_rewrite_tree_element2354 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rewrite_tree_block_in_rewrite_tree_element2364 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rewrite_tree_ebnf_in_rewrite_tree_element2374 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TOKEN_REF_in_rewrite_tree_atom2396 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_elementOptions_in_rewrite_tree_atom2400 = new BitSet(new long[]{0x0010000000000000L});
    public static final BitSet FOLLOW_ARG_ACTION_in_rewrite_tree_atom2403 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_TOKEN_REF_in_rewrite_tree_atom2416 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_elementOptions_in_rewrite_tree_atom2420 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_TOKEN_REF_in_rewrite_tree_atom2432 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ARG_ACTION_in_rewrite_tree_atom2434 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_TOKEN_REF_in_rewrite_tree_atom2446 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_REF_in_rewrite_tree_atom2454 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_LITERAL_in_rewrite_tree_atom2465 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_elementOptions_in_rewrite_tree_atom2469 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_STRING_LITERAL_in_rewrite_tree_atom2477 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LABEL_in_rewrite_tree_atom2485 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ACTION_in_rewrite_tree_atom2493 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ebnfSuffix_in_rewrite_tree_ebnf2517 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_rewrite_tree_block_in_rewrite_tree_ebnf2521 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_TREE_BEGIN_in_rewrite_tree2549 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_rewrite_tree_atom_in_rewrite_tree2553 = new BitSet(new long[]{0x0023802020080F08L});
    public static final BitSet FOLLOW_rewrite_tree_element_in_rewrite_tree2571 = new BitSet(new long[]{0x0023802020080F08L});
    public static final BitSet FOLLOW_TEMPLATE_in_rewrite_template2610 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_rewrite_template2612 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_rewrite_template_args_in_rewrite_template2616 = new BitSet(new long[]{0x00C0000000000000L});
    public static final BitSet FOLLOW_DOUBLE_QUOTE_STRING_LITERAL_in_rewrite_template2633 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_DOUBLE_ANGLE_STRING_LITERAL_in_rewrite_template2645 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_rewrite_template_ref_in_rewrite_template2668 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rewrite_indirect_template_head_in_rewrite_template2675 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ACTION_in_rewrite_template2682 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TEMPLATE_in_rewrite_template_ref2703 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_rewrite_template_ref2705 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_rewrite_template_args_in_rewrite_template_ref2709 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_TEMPLATE_in_rewrite_indirect_template_head2730 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ACTION_in_rewrite_indirect_template_head2732 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_rewrite_template_args_in_rewrite_indirect_template_head2736 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_ARGLIST_in_rewrite_template_args2755 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_rewrite_template_arg_in_rewrite_template_args2757 = new BitSet(new long[]{0x0000000000200008L});
    public static final BitSet FOLLOW_ARGLIST_in_rewrite_template_args2766 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ARG_in_rewrite_template_arg2783 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_rewrite_template_arg2785 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_ACTION_in_rewrite_template_arg2787 = new BitSet(new long[]{0x0000000000000008L});

}