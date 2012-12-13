// $ANTLR 3.2 Sep 23, 2009 12:02:23 /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g 2010-02-24 17:42:58

/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *******************************************************************************/ 
package org.deved.antlride.internal.core.parser;


import org.antlr.runtime.*;
import java.util.List;
import java.util.Stack;
import java.util.ArrayList;

import org.antlr.runtime.tree.*;

/** ANTLR v3 grammar written in ANTLR v3 with AST construction */
@SuppressWarnings({"unused"})
public class ANTLRParser extends Parser {
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
    public static final int RULE=7;
    public static final int T__82=82;
    public static final int ACTION_ESC=66;
    public static final int T__83=83;
    public static final int PARSER_GRAMMAR=25;
    public static final int SRC=56;
    public static final int CHAR_RANGE=14;
    public static final int INT=51;
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


        public ANTLRParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public ANTLRParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        
    protected TreeAdaptor adaptor = new CommonTreeAdaptor();

    public void setTreeAdaptor(TreeAdaptor adaptor) {
        this.adaptor = adaptor;
    }
    public TreeAdaptor getTreeAdaptor() {
        return adaptor;
    }

    public String[] getTokenNames() { return ANTLRParser.tokenNames; }
    public String getGrammarFileName() { return "/home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g"; }


      int gtype;
      boolean templateOutput=false;
      
      public void reportError(Exception ex) {
      	emitErrorMessage(ex.getClass().getName() + ": " + ex.getMessage());
      }


    public static class grammarDef_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "grammarDef"
    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:116:1: grammarDef : ( DOC_COMMENT )? ( 'lexer' | 'parser' | 'tree' | ) g= 'grammar' id ';' ( optionsSpec )? ( delegateGrammars )? ( tokensSpec )? ( attrScope )* ( action )* ( rule )+ EOF -> ^( id ( DOC_COMMENT )? ( optionsSpec )? ( delegateGrammars )? ( tokensSpec )? ( attrScope )* ( action )* ( rule )+ ) ;
    public final ANTLRParser.grammarDef_return grammarDef() throws RecognitionException {
        ANTLRParser.grammarDef_return retval = new ANTLRParser.grammarDef_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token g=null;
        Token DOC_COMMENT1=null;
        Token string_literal2=null;
        Token string_literal3=null;
        Token string_literal4=null;
        Token char_literal6=null;
        Token EOF13=null;
        ANTLRParser.id_return id5 = null;

        ANTLRParser.optionsSpec_return optionsSpec7 = null;

        ANTLRParser.delegateGrammars_return delegateGrammars8 = null;

        ANTLRParser.tokensSpec_return tokensSpec9 = null;

        ANTLRParser.attrScope_return attrScope10 = null;

        ANTLRParser.action_return action11 = null;

        ANTLRParser.rule_return rule12 = null;


        CommonTree g_tree=null;
        CommonTree DOC_COMMENT1_tree=null;
        CommonTree string_literal2_tree=null;
        CommonTree string_literal3_tree=null;
        CommonTree string_literal4_tree=null;
        CommonTree char_literal6_tree=null;
        CommonTree EOF13_tree=null;
        RewriteRuleTokenStream stream_DOC_COMMENT=new RewriteRuleTokenStream(adaptor,"token DOC_COMMENT");
        RewriteRuleTokenStream stream_69=new RewriteRuleTokenStream(adaptor,"token 69");
        RewriteRuleTokenStream stream_70=new RewriteRuleTokenStream(adaptor,"token 70");
        RewriteRuleTokenStream stream_EOF=new RewriteRuleTokenStream(adaptor,"token EOF");
        RewriteRuleTokenStream stream_71=new RewriteRuleTokenStream(adaptor,"token 71");
        RewriteRuleTokenStream stream_SEMI=new RewriteRuleTokenStream(adaptor,"token SEMI");
        RewriteRuleTokenStream stream_72=new RewriteRuleTokenStream(adaptor,"token 72");
        RewriteRuleSubtreeStream stream_id=new RewriteRuleSubtreeStream(adaptor,"rule id");
        RewriteRuleSubtreeStream stream_tokensSpec=new RewriteRuleSubtreeStream(adaptor,"rule tokensSpec");
        RewriteRuleSubtreeStream stream_attrScope=new RewriteRuleSubtreeStream(adaptor,"rule attrScope");
        RewriteRuleSubtreeStream stream_rule=new RewriteRuleSubtreeStream(adaptor,"rule rule");
        RewriteRuleSubtreeStream stream_action=new RewriteRuleSubtreeStream(adaptor,"rule action");
        RewriteRuleSubtreeStream stream_optionsSpec=new RewriteRuleSubtreeStream(adaptor,"rule optionsSpec");
        RewriteRuleSubtreeStream stream_delegateGrammars=new RewriteRuleSubtreeStream(adaptor,"rule delegateGrammars");
        try {
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:117:3: ( ( DOC_COMMENT )? ( 'lexer' | 'parser' | 'tree' | ) g= 'grammar' id ';' ( optionsSpec )? ( delegateGrammars )? ( tokensSpec )? ( attrScope )* ( action )* ( rule )+ EOF -> ^( id ( DOC_COMMENT )? ( optionsSpec )? ( delegateGrammars )? ( tokensSpec )? ( attrScope )* ( action )* ( rule )+ ) )
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:118:3: ( DOC_COMMENT )? ( 'lexer' | 'parser' | 'tree' | ) g= 'grammar' id ';' ( optionsSpec )? ( delegateGrammars )? ( tokensSpec )? ( attrScope )* ( action )* ( rule )+ EOF
            {
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:118:3: ( DOC_COMMENT )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==DOC_COMMENT) ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:118:3: DOC_COMMENT
                    {
                    DOC_COMMENT1=(Token)match(input,DOC_COMMENT,FOLLOW_DOC_COMMENT_in_grammarDef400); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_DOC_COMMENT.add(DOC_COMMENT1);


                    }
                    break;

            }

            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:119:3: ( 'lexer' | 'parser' | 'tree' | )
            int alt2=4;
            switch ( input.LA(1) ) {
            case 69:
                {
                alt2=1;
                }
                break;
            case 70:
                {
                alt2=2;
                }
                break;
            case 71:
                {
                alt2=3;
                }
                break;
            case 72:
                {
                alt2=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }

            switch (alt2) {
                case 1 :
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:120:5: 'lexer'
                    {
                    string_literal2=(Token)match(input,69,FOLLOW_69_in_grammarDef411); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_69.add(string_literal2);

                    if ( state.backtracking==0 ) {
                      gtype=LEXER_GRAMMAR;
                    }

                    }
                    break;
                case 2 :
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:121:7: 'parser'
                    {
                    string_literal3=(Token)match(input,70,FOLLOW_70_in_grammarDef422); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_70.add(string_literal3);

                    if ( state.backtracking==0 ) {
                      gtype=PARSER_GRAMMAR;
                    }

                    }
                    break;
                case 3 :
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:122:7: 'tree'
                    {
                    string_literal4=(Token)match(input,71,FOLLOW_71_in_grammarDef433); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_71.add(string_literal4);

                    if ( state.backtracking==0 ) {
                      gtype=TREE_GRAMMAR;
                    }

                    }
                    break;
                case 4 :
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:123:7: 
                    {
                    if ( state.backtracking==0 ) {
                      gtype=COMBINED_GRAMMAR;
                    }

                    }
                    break;

            }

            g=(Token)match(input,72,FOLLOW_72_in_grammarDef455); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_72.add(g);

            pushFollow(FOLLOW_id_in_grammarDef457);
            id5=id();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_id.add(id5.getTree());
            char_literal6=(Token)match(input,SEMI,FOLLOW_SEMI_in_grammarDef459); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_SEMI.add(char_literal6);

            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:125:22: ( optionsSpec )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==OPTIONS) ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:125:22: optionsSpec
                    {
                    pushFollow(FOLLOW_optionsSpec_in_grammarDef461);
                    optionsSpec7=optionsSpec();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_optionsSpec.add(optionsSpec7.getTree());

                    }
                    break;

            }

            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:125:35: ( delegateGrammars )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==73) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:125:35: delegateGrammars
                    {
                    pushFollow(FOLLOW_delegateGrammars_in_grammarDef464);
                    delegateGrammars8=delegateGrammars();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_delegateGrammars.add(delegateGrammars8.getTree());

                    }
                    break;

            }

            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:125:53: ( tokensSpec )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==TOKENS) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:125:53: tokensSpec
                    {
                    pushFollow(FOLLOW_tokensSpec_in_grammarDef467);
                    tokensSpec9=tokensSpec();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_tokensSpec.add(tokensSpec9.getTree());

                    }
                    break;

            }

            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:125:65: ( attrScope )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0==SCOPE) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:125:65: attrScope
            	    {
            	    pushFollow(FOLLOW_attrScope_in_grammarDef470);
            	    attrScope10=attrScope();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_attrScope.add(attrScope10.getTree());

            	    }
            	    break;

            	default :
            	    break loop6;
                }
            } while (true);

            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:125:76: ( action )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==76) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:125:76: action
            	    {
            	    pushFollow(FOLLOW_action_in_grammarDef473);
            	    action11=action();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_action.add(action11.getTree());

            	    }
            	    break;

            	default :
            	    break loop7;
                }
            } while (true);

            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:125:84: ( rule )+
            int cnt8=0;
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0==DOC_COMMENT||LA8_0==FRAGMENT||LA8_0==TOKEN_REF||LA8_0==RULE_REF||(LA8_0>=80 && LA8_0<=82)) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:125:84: rule
            	    {
            	    pushFollow(FOLLOW_rule_in_grammarDef476);
            	    rule12=rule();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_rule.add(rule12.getTree());

            	    }
            	    break;

            	default :
            	    if ( cnt8 >= 1 ) break loop8;
            	    if (state.backtracking>0) {state.failed=true; return retval;}
                        EarlyExitException eee =
                            new EarlyExitException(8, input);
                        throw eee;
                }
                cnt8++;
            } while (true);

            EOF13=(Token)match(input,EOF,FOLLOW_EOF_in_grammarDef479); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_EOF.add(EOF13);



            // AST REWRITE
            // elements: tokensSpec, DOC_COMMENT, action, delegateGrammars, attrScope, optionsSpec, id, rule
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 126:5: -> ^( id ( DOC_COMMENT )? ( optionsSpec )? ( delegateGrammars )? ( tokensSpec )? ( attrScope )* ( action )* ( rule )+ )
            {
                // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:127:7: ^( id ( DOC_COMMENT )? ( optionsSpec )? ( delegateGrammars )? ( tokensSpec )? ( attrScope )* ( action )* ( rule )+ )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(adaptor.create(gtype,g), root_1);

                adaptor.addChild(root_1, stream_id.nextTree());
                // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:127:40: ( DOC_COMMENT )?
                if ( stream_DOC_COMMENT.hasNext() ) {
                    adaptor.addChild(root_1, stream_DOC_COMMENT.nextNode());

                }
                stream_DOC_COMMENT.reset();
                // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:127:53: ( optionsSpec )?
                if ( stream_optionsSpec.hasNext() ) {
                    adaptor.addChild(root_1, stream_optionsSpec.nextTree());

                }
                stream_optionsSpec.reset();
                // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:127:66: ( delegateGrammars )?
                if ( stream_delegateGrammars.hasNext() ) {
                    adaptor.addChild(root_1, stream_delegateGrammars.nextTree());

                }
                stream_delegateGrammars.reset();
                // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:127:84: ( tokensSpec )?
                if ( stream_tokensSpec.hasNext() ) {
                    adaptor.addChild(root_1, stream_tokensSpec.nextTree());

                }
                stream_tokensSpec.reset();
                // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:127:96: ( attrScope )*
                while ( stream_attrScope.hasNext() ) {
                    adaptor.addChild(root_1, stream_attrScope.nextTree());

                }
                stream_attrScope.reset();
                // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:127:107: ( action )*
                while ( stream_action.hasNext() ) {
                    adaptor.addChild(root_1, stream_action.nextTree());

                }
                stream_action.reset();
                if ( !(stream_rule.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_rule.hasNext() ) {
                    adaptor.addChild(root_1, stream_rule.nextTree());

                }
                stream_rule.reset();

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "grammarDef"

    public static class delegateGrammars_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "delegateGrammars"
    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:130:1: delegateGrammars : 'import' delegateGrammar ( ',' delegateGrammar )* ';' -> ^( 'import' ( delegateGrammar )+ ';' ) ;
    public final ANTLRParser.delegateGrammars_return delegateGrammars() throws RecognitionException {
        ANTLRParser.delegateGrammars_return retval = new ANTLRParser.delegateGrammars_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token string_literal14=null;
        Token char_literal16=null;
        Token char_literal18=null;
        ANTLRParser.delegateGrammar_return delegateGrammar15 = null;

        ANTLRParser.delegateGrammar_return delegateGrammar17 = null;


        CommonTree string_literal14_tree=null;
        CommonTree char_literal16_tree=null;
        CommonTree char_literal18_tree=null;
        RewriteRuleTokenStream stream_SEMI=new RewriteRuleTokenStream(adaptor,"token SEMI");
        RewriteRuleTokenStream stream_73=new RewriteRuleTokenStream(adaptor,"token 73");
        RewriteRuleTokenStream stream_74=new RewriteRuleTokenStream(adaptor,"token 74");
        RewriteRuleSubtreeStream stream_delegateGrammar=new RewriteRuleSubtreeStream(adaptor,"rule delegateGrammar");
        try {
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:131:3: ( 'import' delegateGrammar ( ',' delegateGrammar )* ';' -> ^( 'import' ( delegateGrammar )+ ';' ) )
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:132:3: 'import' delegateGrammar ( ',' delegateGrammar )* ';'
            {
            string_literal14=(Token)match(input,73,FOLLOW_73_in_delegateGrammars534); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_73.add(string_literal14);

            pushFollow(FOLLOW_delegateGrammar_in_delegateGrammars536);
            delegateGrammar15=delegateGrammar();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_delegateGrammar.add(delegateGrammar15.getTree());
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:132:28: ( ',' delegateGrammar )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0==74) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:132:29: ',' delegateGrammar
            	    {
            	    char_literal16=(Token)match(input,74,FOLLOW_74_in_delegateGrammars539); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_74.add(char_literal16);

            	    pushFollow(FOLLOW_delegateGrammar_in_delegateGrammars541);
            	    delegateGrammar17=delegateGrammar();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_delegateGrammar.add(delegateGrammar17.getTree());

            	    }
            	    break;

            	default :
            	    break loop9;
                }
            } while (true);

            char_literal18=(Token)match(input,SEMI,FOLLOW_SEMI_in_delegateGrammars545); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_SEMI.add(char_literal18);



            // AST REWRITE
            // elements: delegateGrammar, SEMI, 73
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 133:5: -> ^( 'import' ( delegateGrammar )+ ';' )
            {
                // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:134:7: ^( 'import' ( delegateGrammar )+ ';' )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(stream_73.nextNode(), root_1);

                if ( !(stream_delegateGrammar.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_delegateGrammar.hasNext() ) {
                    adaptor.addChild(root_1, stream_delegateGrammar.nextTree());

                }
                stream_delegateGrammar.reset();
                adaptor.addChild(root_1, stream_SEMI.nextNode());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "delegateGrammars"

    public static class delegateGrammar_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "delegateGrammar"
    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:137:1: delegateGrammar : (lab= id '=' dg1= id -> ^( '=' $lab $dg1) | dg2= id -> $dg2);
    public final ANTLRParser.delegateGrammar_return delegateGrammar() throws RecognitionException {
        ANTLRParser.delegateGrammar_return retval = new ANTLRParser.delegateGrammar_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token char_literal19=null;
        ANTLRParser.id_return lab = null;

        ANTLRParser.id_return dg1 = null;

        ANTLRParser.id_return dg2 = null;


        CommonTree char_literal19_tree=null;
        RewriteRuleTokenStream stream_ASSIGN=new RewriteRuleTokenStream(adaptor,"token ASSIGN");
        RewriteRuleSubtreeStream stream_id=new RewriteRuleSubtreeStream(adaptor,"rule id");
        try {
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:138:3: (lab= id '=' dg1= id -> ^( '=' $lab $dg1) | dg2= id -> $dg2)
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==TOKEN_REF) ) {
                int LA10_1 = input.LA(2);

                if ( (LA10_1==SEMI||LA10_1==74) ) {
                    alt10=2;
                }
                else if ( (LA10_1==ASSIGN) ) {
                    alt10=1;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 10, 1, input);

                    throw nvae;
                }
            }
            else if ( (LA10_0==RULE_REF) ) {
                int LA10_2 = input.LA(2);

                if ( (LA10_2==SEMI||LA10_2==74) ) {
                    alt10=2;
                }
                else if ( (LA10_2==ASSIGN) ) {
                    alt10=1;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 10, 2, input);

                    throw nvae;
                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 10, 0, input);

                throw nvae;
            }
            switch (alt10) {
                case 1 :
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:139:3: lab= id '=' dg1= id
                    {
                    pushFollow(FOLLOW_id_in_delegateGrammar583);
                    lab=id();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_id.add(lab.getTree());
                    char_literal19=(Token)match(input,ASSIGN,FOLLOW_ASSIGN_in_delegateGrammar585); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_ASSIGN.add(char_literal19);

                    pushFollow(FOLLOW_id_in_delegateGrammar589);
                    dg1=id();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_id.add(dg1.getTree());


                    // AST REWRITE
                    // elements: ASSIGN, dg1, lab
                    // token labels: 
                    // rule labels: retval, lab, dg1
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
                    RewriteRuleSubtreeStream stream_lab=new RewriteRuleSubtreeStream(adaptor,"rule lab",lab!=null?lab.tree:null);
                    RewriteRuleSubtreeStream stream_dg1=new RewriteRuleSubtreeStream(adaptor,"rule dg1",dg1!=null?dg1.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 140:5: -> ^( '=' $lab $dg1)
                    {
                        // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:141:7: ^( '=' $lab $dg1)
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(stream_ASSIGN.nextNode(), root_1);

                        adaptor.addChild(root_1, stream_lab.nextTree());
                        adaptor.addChild(root_1, stream_dg1.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 2 :
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:142:5: dg2= id
                    {
                    pushFollow(FOLLOW_id_in_delegateGrammar619);
                    dg2=id();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_id.add(dg2.getTree());


                    // AST REWRITE
                    // elements: dg2
                    // token labels: 
                    // rule labels: retval, dg2
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
                    RewriteRuleSubtreeStream stream_dg2=new RewriteRuleSubtreeStream(adaptor,"rule dg2",dg2!=null?dg2.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 143:5: -> $dg2
                    {
                        adaptor.addChild(root_0, stream_dg2.nextTree());

                    }

                    retval.tree = root_0;}
                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "delegateGrammar"

    public static class tokensSpec_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "tokensSpec"
    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:146:1: tokensSpec : TOKENS ( tokenSpec )* '}' -> ^( TOKENS ( tokenSpec )* '}' ) ;
    public final ANTLRParser.tokensSpec_return tokensSpec() throws RecognitionException {
        ANTLRParser.tokensSpec_return retval = new ANTLRParser.tokensSpec_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token TOKENS20=null;
        Token char_literal22=null;
        ANTLRParser.tokenSpec_return tokenSpec21 = null;


        CommonTree TOKENS20_tree=null;
        CommonTree char_literal22_tree=null;
        RewriteRuleTokenStream stream_TOKENS=new RewriteRuleTokenStream(adaptor,"token TOKENS");
        RewriteRuleTokenStream stream_75=new RewriteRuleTokenStream(adaptor,"token 75");
        RewriteRuleSubtreeStream stream_tokenSpec=new RewriteRuleSubtreeStream(adaptor,"rule tokenSpec");
        try {
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:147:3: ( TOKENS ( tokenSpec )* '}' -> ^( TOKENS ( tokenSpec )* '}' ) )
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:148:3: TOKENS ( tokenSpec )* '}'
            {
            TOKENS20=(Token)match(input,TOKENS,FOLLOW_TOKENS_in_tokensSpec643); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_TOKENS.add(TOKENS20);

            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:148:10: ( tokenSpec )*
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( (LA11_0==TOKEN_REF) ) {
                    alt11=1;
                }


                switch (alt11) {
            	case 1 :
            	    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:148:10: tokenSpec
            	    {
            	    pushFollow(FOLLOW_tokenSpec_in_tokensSpec645);
            	    tokenSpec21=tokenSpec();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_tokenSpec.add(tokenSpec21.getTree());

            	    }
            	    break;

            	default :
            	    break loop11;
                }
            } while (true);

            char_literal22=(Token)match(input,75,FOLLOW_75_in_tokensSpec648); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_75.add(char_literal22);



            // AST REWRITE
            // elements: 75, tokenSpec, TOKENS
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 149:5: -> ^( TOKENS ( tokenSpec )* '}' )
            {
                // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:150:7: ^( TOKENS ( tokenSpec )* '}' )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(stream_TOKENS.nextNode(), root_1);

                // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:150:16: ( tokenSpec )*
                while ( stream_tokenSpec.hasNext() ) {
                    adaptor.addChild(root_1, stream_tokenSpec.nextTree());

                }
                stream_tokenSpec.reset();
                adaptor.addChild(root_1, stream_75.nextNode());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "tokensSpec"

    public static class tokenSpec_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "tokenSpec"
    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:153:1: tokenSpec : TOKEN_REF ( '=' (lit= STRING_LITERAL ) -> ^( '=' TOKEN_REF $lit) | -> TOKEN_REF ) ';' ;
    public final ANTLRParser.tokenSpec_return tokenSpec() throws RecognitionException {
        ANTLRParser.tokenSpec_return retval = new ANTLRParser.tokenSpec_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token lit=null;
        Token TOKEN_REF23=null;
        Token char_literal24=null;
        Token char_literal25=null;

        CommonTree lit_tree=null;
        CommonTree TOKEN_REF23_tree=null;
        CommonTree char_literal24_tree=null;
        CommonTree char_literal25_tree=null;
        RewriteRuleTokenStream stream_STRING_LITERAL=new RewriteRuleTokenStream(adaptor,"token STRING_LITERAL");
        RewriteRuleTokenStream stream_SEMI=new RewriteRuleTokenStream(adaptor,"token SEMI");
        RewriteRuleTokenStream stream_TOKEN_REF=new RewriteRuleTokenStream(adaptor,"token TOKEN_REF");
        RewriteRuleTokenStream stream_ASSIGN=new RewriteRuleTokenStream(adaptor,"token ASSIGN");

        try {
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:154:3: ( TOKEN_REF ( '=' (lit= STRING_LITERAL ) -> ^( '=' TOKEN_REF $lit) | -> TOKEN_REF ) ';' )
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:155:3: TOKEN_REF ( '=' (lit= STRING_LITERAL ) -> ^( '=' TOKEN_REF $lit) | -> TOKEN_REF ) ';'
            {
            TOKEN_REF23=(Token)match(input,TOKEN_REF,FOLLOW_TOKEN_REF_in_tokenSpec684); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_TOKEN_REF.add(TOKEN_REF23);

            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:156:3: ( '=' (lit= STRING_LITERAL ) -> ^( '=' TOKEN_REF $lit) | -> TOKEN_REF )
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==ASSIGN) ) {
                alt12=1;
            }
            else if ( (LA12_0==SEMI) ) {
                alt12=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 12, 0, input);

                throw nvae;
            }
            switch (alt12) {
                case 1 :
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:157:5: '=' (lit= STRING_LITERAL )
                    {
                    char_literal24=(Token)match(input,ASSIGN,FOLLOW_ASSIGN_in_tokenSpec694); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_ASSIGN.add(char_literal24);

                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:157:9: (lit= STRING_LITERAL )
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:157:10: lit= STRING_LITERAL
                    {
                    lit=(Token)match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_tokenSpec699); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_STRING_LITERAL.add(lit);


                    }



                    // AST REWRITE
                    // elements: ASSIGN, lit, TOKEN_REF
                    // token labels: lit
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleTokenStream stream_lit=new RewriteRuleTokenStream(adaptor,"token lit",lit);
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 158:7: -> ^( '=' TOKEN_REF $lit)
                    {
                        // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:159:9: ^( '=' TOKEN_REF $lit)
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(stream_ASSIGN.nextNode(), root_1);

                        adaptor.addChild(root_1, stream_TOKEN_REF.nextNode());
                        adaptor.addChild(root_1, stream_lit.nextNode());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 2 :
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:161:7: 
                    {

                    // AST REWRITE
                    // elements: TOKEN_REF
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 161:7: -> TOKEN_REF
                    {
                        adaptor.addChild(root_0, stream_TOKEN_REF.nextNode());

                    }

                    retval.tree = root_0;}
                    }
                    break;

            }

            char_literal25=(Token)match(input,SEMI,FOLLOW_SEMI_in_tokenSpec749); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_SEMI.add(char_literal25);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "tokenSpec"

    public static class attrScope_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "attrScope"
    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:166:1: attrScope : 'scope' id ACTION -> ^( 'scope' id ACTION ) ;
    public final ANTLRParser.attrScope_return attrScope() throws RecognitionException {
        ANTLRParser.attrScope_return retval = new ANTLRParser.attrScope_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token string_literal26=null;
        Token ACTION28=null;
        ANTLRParser.id_return id27 = null;


        CommonTree string_literal26_tree=null;
        CommonTree ACTION28_tree=null;
        RewriteRuleTokenStream stream_SCOPE=new RewriteRuleTokenStream(adaptor,"token SCOPE");
        RewriteRuleTokenStream stream_ACTION=new RewriteRuleTokenStream(adaptor,"token ACTION");
        RewriteRuleSubtreeStream stream_id=new RewriteRuleSubtreeStream(adaptor,"rule id");
        try {
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:167:3: ( 'scope' id ACTION -> ^( 'scope' id ACTION ) )
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:168:3: 'scope' id ACTION
            {
            string_literal26=(Token)match(input,SCOPE,FOLLOW_SCOPE_in_attrScope764); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_SCOPE.add(string_literal26);

            pushFollow(FOLLOW_id_in_attrScope766);
            id27=id();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_id.add(id27.getTree());
            ACTION28=(Token)match(input,ACTION,FOLLOW_ACTION_in_attrScope768); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ACTION.add(ACTION28);



            // AST REWRITE
            // elements: ACTION, id, SCOPE
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 169:5: -> ^( 'scope' id ACTION )
            {
                // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:170:7: ^( 'scope' id ACTION )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(stream_SCOPE.nextNode(), root_1);

                adaptor.addChild(root_1, stream_id.nextTree());
                adaptor.addChild(root_1, stream_ACTION.nextNode());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "attrScope"

    public static class action_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "action"
    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:173:1: action : '@' ( actionScopeName '::' )? id ACTION -> ^( '@' ( actionScopeName )? id ACTION ) ;
    public final ANTLRParser.action_return action() throws RecognitionException {
        ANTLRParser.action_return retval = new ANTLRParser.action_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token char_literal29=null;
        Token string_literal31=null;
        Token ACTION33=null;
        ANTLRParser.actionScopeName_return actionScopeName30 = null;

        ANTLRParser.id_return id32 = null;


        CommonTree char_literal29_tree=null;
        CommonTree string_literal31_tree=null;
        CommonTree ACTION33_tree=null;
        RewriteRuleTokenStream stream_77=new RewriteRuleTokenStream(adaptor,"token 77");
        RewriteRuleTokenStream stream_ACTION=new RewriteRuleTokenStream(adaptor,"token ACTION");
        RewriteRuleTokenStream stream_76=new RewriteRuleTokenStream(adaptor,"token 76");
        RewriteRuleSubtreeStream stream_id=new RewriteRuleSubtreeStream(adaptor,"rule id");
        RewriteRuleSubtreeStream stream_actionScopeName=new RewriteRuleSubtreeStream(adaptor,"rule actionScopeName");
        try {
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:175:3: ( '@' ( actionScopeName '::' )? id ACTION -> ^( '@' ( actionScopeName )? id ACTION ) )
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:176:3: '@' ( actionScopeName '::' )? id ACTION
            {
            char_literal29=(Token)match(input,76,FOLLOW_76_in_action805); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_76.add(char_literal29);

            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:176:7: ( actionScopeName '::' )?
            int alt13=2;
            switch ( input.LA(1) ) {
                case TOKEN_REF:
                    {
                    int LA13_1 = input.LA(2);

                    if ( (LA13_1==77) ) {
                        alt13=1;
                    }
                    }
                    break;
                case RULE_REF:
                    {
                    int LA13_2 = input.LA(2);

                    if ( (LA13_2==77) ) {
                        alt13=1;
                    }
                    }
                    break;
                case 69:
                case 70:
                    {
                    alt13=1;
                    }
                    break;
            }

            switch (alt13) {
                case 1 :
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:176:8: actionScopeName '::'
                    {
                    pushFollow(FOLLOW_actionScopeName_in_action808);
                    actionScopeName30=actionScopeName();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_actionScopeName.add(actionScopeName30.getTree());
                    string_literal31=(Token)match(input,77,FOLLOW_77_in_action810); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_77.add(string_literal31);


                    }
                    break;

            }

            pushFollow(FOLLOW_id_in_action814);
            id32=id();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_id.add(id32.getTree());
            ACTION33=(Token)match(input,ACTION,FOLLOW_ACTION_in_action816); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ACTION.add(ACTION33);



            // AST REWRITE
            // elements: 76, ACTION, actionScopeName, id
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 177:5: -> ^( '@' ( actionScopeName )? id ACTION )
            {
                // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:178:7: ^( '@' ( actionScopeName )? id ACTION )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(stream_76.nextNode(), root_1);

                // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:178:13: ( actionScopeName )?
                if ( stream_actionScopeName.hasNext() ) {
                    adaptor.addChild(root_1, stream_actionScopeName.nextTree());

                }
                stream_actionScopeName.reset();
                adaptor.addChild(root_1, stream_id.nextTree());
                adaptor.addChild(root_1, stream_ACTION.nextNode());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "action"

    public static class actionScopeName_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "actionScopeName"
    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:181:1: actionScopeName : ( id | l= 'lexer' -> ID[$l] | p= 'parser' -> ID[$p] );
    public final ANTLRParser.actionScopeName_return actionScopeName() throws RecognitionException {
        ANTLRParser.actionScopeName_return retval = new ANTLRParser.actionScopeName_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token l=null;
        Token p=null;
        ANTLRParser.id_return id34 = null;


        CommonTree l_tree=null;
        CommonTree p_tree=null;
        RewriteRuleTokenStream stream_69=new RewriteRuleTokenStream(adaptor,"token 69");
        RewriteRuleTokenStream stream_70=new RewriteRuleTokenStream(adaptor,"token 70");

        try {
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:185:3: ( id | l= 'lexer' -> ID[$l] | p= 'parser' -> ID[$p] )
            int alt14=3;
            switch ( input.LA(1) ) {
            case TOKEN_REF:
            case RULE_REF:
                {
                alt14=1;
                }
                break;
            case 69:
                {
                alt14=2;
                }
                break;
            case 70:
                {
                alt14=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 14, 0, input);

                throw nvae;
            }

            switch (alt14) {
                case 1 :
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:186:3: id
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_id_in_actionScopeName856);
                    id34=id();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, id34.getTree());

                    }
                    break;
                case 2 :
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:187:5: l= 'lexer'
                    {
                    l=(Token)match(input,69,FOLLOW_69_in_actionScopeName864); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_69.add(l);



                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 188:5: -> ID[$l]
                    {
                        adaptor.addChild(root_0, (CommonTree)adaptor.create(ID, l));

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 3 :
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:189:5: p= 'parser'
                    {
                    p=(Token)match(input,70,FOLLOW_70_in_actionScopeName881); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_70.add(p);



                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 190:5: -> ID[$p]
                    {
                        adaptor.addChild(root_0, (CommonTree)adaptor.create(ID, p));

                    }

                    retval.tree = root_0;}
                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "actionScopeName"

    public static class optionsSpec_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "optionsSpec"
    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:193:1: optionsSpec : OPTIONS ( option ';' )* '}' -> ^( OPTIONS ( option )* '}' ) ;
    public final ANTLRParser.optionsSpec_return optionsSpec() throws RecognitionException {
        ANTLRParser.optionsSpec_return retval = new ANTLRParser.optionsSpec_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token OPTIONS35=null;
        Token char_literal37=null;
        Token char_literal38=null;
        ANTLRParser.option_return option36 = null;


        CommonTree OPTIONS35_tree=null;
        CommonTree char_literal37_tree=null;
        CommonTree char_literal38_tree=null;
        RewriteRuleTokenStream stream_SEMI=new RewriteRuleTokenStream(adaptor,"token SEMI");
        RewriteRuleTokenStream stream_OPTIONS=new RewriteRuleTokenStream(adaptor,"token OPTIONS");
        RewriteRuleTokenStream stream_75=new RewriteRuleTokenStream(adaptor,"token 75");
        RewriteRuleSubtreeStream stream_option=new RewriteRuleSubtreeStream(adaptor,"rule option");
        try {
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:194:3: ( OPTIONS ( option ';' )* '}' -> ^( OPTIONS ( option )* '}' ) )
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:195:3: OPTIONS ( option ';' )* '}'
            {
            OPTIONS35=(Token)match(input,OPTIONS,FOLLOW_OPTIONS_in_optionsSpec905); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_OPTIONS.add(OPTIONS35);

            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:195:11: ( option ';' )*
            loop15:
            do {
                int alt15=2;
                int LA15_0 = input.LA(1);

                if ( (LA15_0==TOKEN_REF||LA15_0==RULE_REF) ) {
                    alt15=1;
                }


                switch (alt15) {
            	case 1 :
            	    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:195:12: option ';'
            	    {
            	    pushFollow(FOLLOW_option_in_optionsSpec908);
            	    option36=option();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_option.add(option36.getTree());
            	    char_literal37=(Token)match(input,SEMI,FOLLOW_SEMI_in_optionsSpec910); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_SEMI.add(char_literal37);


            	    }
            	    break;

            	default :
            	    break loop15;
                }
            } while (true);

            char_literal38=(Token)match(input,75,FOLLOW_75_in_optionsSpec914); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_75.add(char_literal38);



            // AST REWRITE
            // elements: 75, option, OPTIONS
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 196:5: -> ^( OPTIONS ( option )* '}' )
            {
                // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:197:7: ^( OPTIONS ( option )* '}' )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(stream_OPTIONS.nextNode(), root_1);

                // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:197:17: ( option )*
                while ( stream_option.hasNext() ) {
                    adaptor.addChild(root_1, stream_option.nextTree());

                }
                stream_option.reset();
                adaptor.addChild(root_1, stream_75.nextNode());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "optionsSpec"

    public static class option_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "option"
    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:200:1: option : id '=' optionValue -> ^( '=' id optionValue ) ;
    public final ANTLRParser.option_return option() throws RecognitionException {
        ANTLRParser.option_return retval = new ANTLRParser.option_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token char_literal40=null;
        ANTLRParser.id_return id39 = null;

        ANTLRParser.optionValue_return optionValue41 = null;


        CommonTree char_literal40_tree=null;
        RewriteRuleTokenStream stream_ASSIGN=new RewriteRuleTokenStream(adaptor,"token ASSIGN");
        RewriteRuleSubtreeStream stream_id=new RewriteRuleSubtreeStream(adaptor,"rule id");
        RewriteRuleSubtreeStream stream_optionValue=new RewriteRuleSubtreeStream(adaptor,"rule optionValue");
        try {
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:201:3: ( id '=' optionValue -> ^( '=' id optionValue ) )
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:202:3: id '=' optionValue
            {
            pushFollow(FOLLOW_id_in_option950);
            id39=id();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_id.add(id39.getTree());
            char_literal40=(Token)match(input,ASSIGN,FOLLOW_ASSIGN_in_option952); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ASSIGN.add(char_literal40);

            pushFollow(FOLLOW_optionValue_in_option954);
            optionValue41=optionValue();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_optionValue.add(optionValue41.getTree());
            if ( state.backtracking==0 ) {

                  						if("output".equals((id39!=null?input.toString(id39.start,id39.stop):null))) {
              								templateOutput = "template".equals((optionValue41!=null?input.toString(optionValue41.start,optionValue41.stop):null));
              							}
              							
            }


            // AST REWRITE
            // elements: ASSIGN, id, optionValue
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 207:5: -> ^( '=' id optionValue )
            {
                // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:208:7: ^( '=' id optionValue )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(stream_ASSIGN.nextNode(), root_1);

                adaptor.addChild(root_1, stream_id.nextTree());
                adaptor.addChild(root_1, stream_optionValue.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "option"

    public static class optionValue_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "optionValue"
    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:211:1: optionValue : ( id | STRING_LITERAL | INT | s= '*' -> STRING_LITERAL[$s] );
    public final ANTLRParser.optionValue_return optionValue() throws RecognitionException {
        ANTLRParser.optionValue_return retval = new ANTLRParser.optionValue_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token s=null;
        Token STRING_LITERAL43=null;
        Token INT44=null;
        ANTLRParser.id_return id42 = null;


        CommonTree s_tree=null;
        CommonTree STRING_LITERAL43_tree=null;
        CommonTree INT44_tree=null;
        RewriteRuleTokenStream stream_78=new RewriteRuleTokenStream(adaptor,"token 78");

        try {
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:212:3: ( id | STRING_LITERAL | INT | s= '*' -> STRING_LITERAL[$s] )
            int alt16=4;
            switch ( input.LA(1) ) {
            case TOKEN_REF:
            case RULE_REF:
                {
                alt16=1;
                }
                break;
            case STRING_LITERAL:
                {
                alt16=2;
                }
                break;
            case INT:
                {
                alt16=3;
                }
                break;
            case 78:
                {
                alt16=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 16, 0, input);

                throw nvae;
            }

            switch (alt16) {
                case 1 :
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:213:3: id
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_id_in_optionValue991);
                    id42=id();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, id42.getTree());

                    }
                    break;
                case 2 :
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:214:5: STRING_LITERAL
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    STRING_LITERAL43=(Token)match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_optionValue997); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    STRING_LITERAL43_tree = (CommonTree)adaptor.create(STRING_LITERAL43);
                    adaptor.addChild(root_0, STRING_LITERAL43_tree);
                    }

                    }
                    break;
                case 3 :
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:215:5: INT
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    INT44=(Token)match(input,INT,FOLLOW_INT_in_optionValue1003); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    INT44_tree = (CommonTree)adaptor.create(INT44);
                    adaptor.addChild(root_0, INT44_tree);
                    }

                    }
                    break;
                case 4 :
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:216:5: s= '*'
                    {
                    s=(Token)match(input,78,FOLLOW_78_in_optionValue1011); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_78.add(s);



                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 217:5: -> STRING_LITERAL[$s]
                    {
                        adaptor.addChild(root_0, (CommonTree)adaptor.create(STRING_LITERAL, s));

                    }

                    retval.tree = root_0;}
                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "optionValue"

    protected static class rule_scope {
        String name;
    }
    protected Stack<rule_scope> rule_stack = new Stack<rule_scope>();

    public static class rule_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "rule"
    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:220:1: rule : ( DOC_COMMENT )? ( modifier )? id (bang= '!' )? (arg= ARG_ACTION )? ( 'returns' rt= ARG_ACTION )? ( throwsSpec )? ( optionsSpec )? ( ruleScopeSpec )? ( ruleAction )* colon= ':' altList (eor= ';' )? ( exceptionGroup )? -> ^( RULE ( DOC_COMMENT )? id ( modifier )? ( ^( ARG $arg) )? ( ^( RET $rt) )? ( throwsSpec )? ( optionsSpec )? ( ruleScopeSpec )? ( ruleAction )* COLON[$colon] altList ( exceptionGroup )? ) ;
    public final ANTLRParser.rule_return rule() throws RecognitionException {
        rule_stack.push(new rule_scope());
        ANTLRParser.rule_return retval = new ANTLRParser.rule_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token bang=null;
        Token arg=null;
        Token rt=null;
        Token colon=null;
        Token eor=null;
        Token DOC_COMMENT45=null;
        Token string_literal48=null;
        ANTLRParser.modifier_return modifier46 = null;

        ANTLRParser.id_return id47 = null;

        ANTLRParser.throwsSpec_return throwsSpec49 = null;

        ANTLRParser.optionsSpec_return optionsSpec50 = null;

        ANTLRParser.ruleScopeSpec_return ruleScopeSpec51 = null;

        ANTLRParser.ruleAction_return ruleAction52 = null;

        ANTLRParser.altList_return altList53 = null;

        ANTLRParser.exceptionGroup_return exceptionGroup54 = null;


        CommonTree bang_tree=null;
        CommonTree arg_tree=null;
        CommonTree rt_tree=null;
        CommonTree colon_tree=null;
        CommonTree eor_tree=null;
        CommonTree DOC_COMMENT45_tree=null;
        CommonTree string_literal48_tree=null;
        RewriteRuleTokenStream stream_COLON=new RewriteRuleTokenStream(adaptor,"token COLON");
        RewriteRuleTokenStream stream_DOC_COMMENT=new RewriteRuleTokenStream(adaptor,"token DOC_COMMENT");
        RewriteRuleTokenStream stream_79=new RewriteRuleTokenStream(adaptor,"token 79");
        RewriteRuleTokenStream stream_BANG=new RewriteRuleTokenStream(adaptor,"token BANG");
        RewriteRuleTokenStream stream_SEMI=new RewriteRuleTokenStream(adaptor,"token SEMI");
        RewriteRuleTokenStream stream_ARG_ACTION=new RewriteRuleTokenStream(adaptor,"token ARG_ACTION");
        RewriteRuleSubtreeStream stream_modifier=new RewriteRuleSubtreeStream(adaptor,"rule modifier");
        RewriteRuleSubtreeStream stream_id=new RewriteRuleSubtreeStream(adaptor,"rule id");
        RewriteRuleSubtreeStream stream_exceptionGroup=new RewriteRuleSubtreeStream(adaptor,"rule exceptionGroup");
        RewriteRuleSubtreeStream stream_throwsSpec=new RewriteRuleSubtreeStream(adaptor,"rule throwsSpec");
        RewriteRuleSubtreeStream stream_ruleScopeSpec=new RewriteRuleSubtreeStream(adaptor,"rule ruleScopeSpec");
        RewriteRuleSubtreeStream stream_optionsSpec=new RewriteRuleSubtreeStream(adaptor,"rule optionsSpec");
        RewriteRuleSubtreeStream stream_altList=new RewriteRuleSubtreeStream(adaptor,"rule altList");
        RewriteRuleSubtreeStream stream_ruleAction=new RewriteRuleSubtreeStream(adaptor,"rule ruleAction");
        try {
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:224:3: ( ( DOC_COMMENT )? ( modifier )? id (bang= '!' )? (arg= ARG_ACTION )? ( 'returns' rt= ARG_ACTION )? ( throwsSpec )? ( optionsSpec )? ( ruleScopeSpec )? ( ruleAction )* colon= ':' altList (eor= ';' )? ( exceptionGroup )? -> ^( RULE ( DOC_COMMENT )? id ( modifier )? ( ^( ARG $arg) )? ( ^( RET $rt) )? ( throwsSpec )? ( optionsSpec )? ( ruleScopeSpec )? ( ruleAction )* COLON[$colon] altList ( exceptionGroup )? ) )
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:225:3: ( DOC_COMMENT )? ( modifier )? id (bang= '!' )? (arg= ARG_ACTION )? ( 'returns' rt= ARG_ACTION )? ( throwsSpec )? ( optionsSpec )? ( ruleScopeSpec )? ( ruleAction )* colon= ':' altList (eor= ';' )? ( exceptionGroup )?
            {
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:225:3: ( DOC_COMMENT )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==DOC_COMMENT) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:225:3: DOC_COMMENT
                    {
                    DOC_COMMENT45=(Token)match(input,DOC_COMMENT,FOLLOW_DOC_COMMENT_in_rule1040); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_DOC_COMMENT.add(DOC_COMMENT45);


                    }
                    break;

            }

            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:225:16: ( modifier )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==FRAGMENT||(LA18_0>=80 && LA18_0<=82)) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:225:16: modifier
                    {
                    pushFollow(FOLLOW_modifier_in_rule1043);
                    modifier46=modifier();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_modifier.add(modifier46.getTree());

                    }
                    break;

            }

            pushFollow(FOLLOW_id_in_rule1046);
            id47=id();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_id.add(id47.getTree());
            if ( state.backtracking==0 ) {
              ((rule_scope)rule_stack.peek()).name = (id47!=null?input.toString(id47.start,id47.stop):null);
            }
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:225:59: (bang= '!' )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==BANG) ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:225:59: bang= '!'
                    {
                    bang=(Token)match(input,BANG,FOLLOW_BANG_in_rule1052); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_BANG.add(bang);


                    }
                    break;

            }

            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:225:65: (arg= ARG_ACTION )?
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==ARG_ACTION) ) {
                alt20=1;
            }
            switch (alt20) {
                case 1 :
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:225:66: arg= ARG_ACTION
                    {
                    arg=(Token)match(input,ARG_ACTION,FOLLOW_ARG_ACTION_in_rule1058); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_ARG_ACTION.add(arg);


                    }
                    break;

            }

            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:225:83: ( 'returns' rt= ARG_ACTION )?
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==79) ) {
                alt21=1;
            }
            switch (alt21) {
                case 1 :
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:225:84: 'returns' rt= ARG_ACTION
                    {
                    string_literal48=(Token)match(input,79,FOLLOW_79_in_rule1063); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_79.add(string_literal48);

                    rt=(Token)match(input,ARG_ACTION,FOLLOW_ARG_ACTION_in_rule1067); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_ARG_ACTION.add(rt);


                    }
                    break;

            }

            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:225:110: ( throwsSpec )?
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( (LA22_0==83) ) {
                alt22=1;
            }
            switch (alt22) {
                case 1 :
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:225:110: throwsSpec
                    {
                    pushFollow(FOLLOW_throwsSpec_in_rule1071);
                    throwsSpec49=throwsSpec();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_throwsSpec.add(throwsSpec49.getTree());

                    }
                    break;

            }

            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:225:122: ( optionsSpec )?
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( (LA23_0==OPTIONS) ) {
                alt23=1;
            }
            switch (alt23) {
                case 1 :
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:225:122: optionsSpec
                    {
                    pushFollow(FOLLOW_optionsSpec_in_rule1074);
                    optionsSpec50=optionsSpec();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_optionsSpec.add(optionsSpec50.getTree());

                    }
                    break;

            }

            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:225:135: ( ruleScopeSpec )?
            int alt24=2;
            int LA24_0 = input.LA(1);

            if ( (LA24_0==SCOPE) ) {
                alt24=1;
            }
            switch (alt24) {
                case 1 :
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:225:135: ruleScopeSpec
                    {
                    pushFollow(FOLLOW_ruleScopeSpec_in_rule1077);
                    ruleScopeSpec51=ruleScopeSpec();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_ruleScopeSpec.add(ruleScopeSpec51.getTree());

                    }
                    break;

            }

            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:225:150: ( ruleAction )*
            loop25:
            do {
                int alt25=2;
                int LA25_0 = input.LA(1);

                if ( (LA25_0==76) ) {
                    alt25=1;
                }


                switch (alt25) {
            	case 1 :
            	    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:225:150: ruleAction
            	    {
            	    pushFollow(FOLLOW_ruleAction_in_rule1080);
            	    ruleAction52=ruleAction();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_ruleAction.add(ruleAction52.getTree());

            	    }
            	    break;

            	default :
            	    break loop25;
                }
            } while (true);

            colon=(Token)match(input,COLON,FOLLOW_COLON_in_rule1085); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_COLON.add(colon);

            pushFollow(FOLLOW_altList_in_rule1087);
            altList53=altList();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_altList.add(altList53.getTree());
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:225:183: (eor= ';' )?
            int alt26=2;
            int LA26_0 = input.LA(1);

            if ( (LA26_0==SEMI) ) {
                alt26=1;
            }
            switch (alt26) {
                case 1 :
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:225:183: eor= ';'
                    {
                    eor=(Token)match(input,SEMI,FOLLOW_SEMI_in_rule1091); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_SEMI.add(eor);


                    }
                    break;

            }

            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:225:189: ( exceptionGroup )?
            int alt27=2;
            int LA27_0 = input.LA(1);

            if ( ((LA27_0>=87 && LA27_0<=88)) ) {
                alt27=1;
            }
            switch (alt27) {
                case 1 :
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:225:189: exceptionGroup
                    {
                    pushFollow(FOLLOW_exceptionGroup_in_rule1094);
                    exceptionGroup54=exceptionGroup();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_exceptionGroup.add(exceptionGroup54.getTree());

                    }
                    break;

            }



            // AST REWRITE
            // elements: exceptionGroup, altList, id, ruleAction, optionsSpec, rt, throwsSpec, arg, DOC_COMMENT, ruleScopeSpec, modifier
            // token labels: arg, rt
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleTokenStream stream_arg=new RewriteRuleTokenStream(adaptor,"token arg",arg);
            RewriteRuleTokenStream stream_rt=new RewriteRuleTokenStream(adaptor,"token rt",rt);
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 226:5: -> ^( RULE ( DOC_COMMENT )? id ( modifier )? ( ^( ARG $arg) )? ( ^( RET $rt) )? ( throwsSpec )? ( optionsSpec )? ( ruleScopeSpec )? ( ruleAction )* COLON[$colon] altList ( exceptionGroup )? )
            {
                // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:227:7: ^( RULE ( DOC_COMMENT )? id ( modifier )? ( ^( ARG $arg) )? ( ^( RET $rt) )? ( throwsSpec )? ( optionsSpec )? ( ruleScopeSpec )? ( ruleAction )* COLON[$colon] altList ( exceptionGroup )? )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(RULE, "RULE"), root_1);

                // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:228:14: ( DOC_COMMENT )?
                if ( stream_DOC_COMMENT.hasNext() ) {
                    adaptor.addChild(root_1, stream_DOC_COMMENT.nextNode());

                }
                stream_DOC_COMMENT.reset();
                adaptor.addChild(root_1, stream_id.nextTree());
                // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:228:30: ( modifier )?
                if ( stream_modifier.hasNext() ) {
                    adaptor.addChild(root_1, stream_modifier.nextTree());

                }
                stream_modifier.reset();
                adaptor.addChild(root_1, bang==null?null:adaptor.create(bang));
                // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:229:9: ( ^( ARG $arg) )?
                if ( stream_arg.hasNext() ) {
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:229:9: ^( ARG $arg)
                    {
                    CommonTree root_2 = (CommonTree)adaptor.nil();
                    root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ARG, "ARG"), root_2);

                    adaptor.addChild(root_2, stream_arg.nextNode());

                    adaptor.addChild(root_1, root_2);
                    }

                }
                stream_arg.reset();
                // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:230:9: ( ^( RET $rt) )?
                if ( stream_rt.hasNext() ) {
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:230:9: ^( RET $rt)
                    {
                    CommonTree root_2 = (CommonTree)adaptor.nil();
                    root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(RET, "RET"), root_2);

                    adaptor.addChild(root_2, stream_rt.nextNode());

                    adaptor.addChild(root_1, root_2);
                    }

                }
                stream_rt.reset();
                // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:231:9: ( throwsSpec )?
                if ( stream_throwsSpec.hasNext() ) {
                    adaptor.addChild(root_1, stream_throwsSpec.nextTree());

                }
                stream_throwsSpec.reset();
                // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:231:21: ( optionsSpec )?
                if ( stream_optionsSpec.hasNext() ) {
                    adaptor.addChild(root_1, stream_optionsSpec.nextTree());

                }
                stream_optionsSpec.reset();
                // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:231:34: ( ruleScopeSpec )?
                if ( stream_ruleScopeSpec.hasNext() ) {
                    adaptor.addChild(root_1, stream_ruleScopeSpec.nextTree());

                }
                stream_ruleScopeSpec.reset();
                // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:231:49: ( ruleAction )*
                while ( stream_ruleAction.hasNext() ) {
                    adaptor.addChild(root_1, stream_ruleAction.nextTree());

                }
                stream_ruleAction.reset();
                adaptor.addChild(root_1, (CommonTree)adaptor.create(COLON, colon));
                adaptor.addChild(root_1, stream_altList.nextTree());
                // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:231:83: ( exceptionGroup )?
                if ( stream_exceptionGroup.hasNext() ) {
                    adaptor.addChild(root_1, stream_exceptionGroup.nextTree());

                }
                stream_exceptionGroup.reset();
                adaptor.addChild(root_1, eor==null?adaptor.create(EOR, ";"):adaptor.create(EOR, eor));

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
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
            rule_stack.pop();
        }
        return retval;
    }
    // $ANTLR end "rule"

    public static class modifier_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "modifier"
    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:245:1: modifier : ( 'protected' | 'public' | 'private' | 'fragment' );
    public final ANTLRParser.modifier_return modifier() throws RecognitionException {
        ANTLRParser.modifier_return retval = new ANTLRParser.modifier_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token set55=null;

        CommonTree set55_tree=null;

        try {
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:246:3: ( 'protected' | 'public' | 'private' | 'fragment' )
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:
            {
            root_0 = (CommonTree)adaptor.nil();

            set55=(Token)input.LT(1);
            if ( input.LA(1)==FRAGMENT||(input.LA(1)>=80 && input.LA(1)<=82) ) {
                input.consume();
                if ( state.backtracking==0 ) adaptor.addChild(root_0, (CommonTree)adaptor.create(set55));
                state.errorRecovery=false;state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "modifier"

    public static class ruleAction_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "ruleAction"
    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:253:1: ruleAction : '@' id ACTION -> ^( '@' id ACTION ) ;
    public final ANTLRParser.ruleAction_return ruleAction() throws RecognitionException {
        ANTLRParser.ruleAction_return retval = new ANTLRParser.ruleAction_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token char_literal56=null;
        Token ACTION58=null;
        ANTLRParser.id_return id57 = null;


        CommonTree char_literal56_tree=null;
        CommonTree ACTION58_tree=null;
        RewriteRuleTokenStream stream_ACTION=new RewriteRuleTokenStream(adaptor,"token ACTION");
        RewriteRuleTokenStream stream_76=new RewriteRuleTokenStream(adaptor,"token 76");
        RewriteRuleSubtreeStream stream_id=new RewriteRuleSubtreeStream(adaptor,"rule id");
        try {
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:255:3: ( '@' id ACTION -> ^( '@' id ACTION ) )
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:256:3: '@' id ACTION
            {
            char_literal56=(Token)match(input,76,FOLLOW_76_in_ruleAction1262); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_76.add(char_literal56);

            pushFollow(FOLLOW_id_in_ruleAction1264);
            id57=id();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_id.add(id57.getTree());
            ACTION58=(Token)match(input,ACTION,FOLLOW_ACTION_in_ruleAction1266); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ACTION.add(ACTION58);



            // AST REWRITE
            // elements: ACTION, 76, id
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 257:5: -> ^( '@' id ACTION )
            {
                // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:258:7: ^( '@' id ACTION )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(stream_76.nextNode(), root_1);

                adaptor.addChild(root_1, stream_id.nextTree());
                adaptor.addChild(root_1, stream_ACTION.nextNode());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "ruleAction"

    public static class throwsSpec_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "throwsSpec"
    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:261:1: throwsSpec : 'throws' id ( ',' id )* -> ^( 'throws' ( id )+ ) ;
    public final ANTLRParser.throwsSpec_return throwsSpec() throws RecognitionException {
        ANTLRParser.throwsSpec_return retval = new ANTLRParser.throwsSpec_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token string_literal59=null;
        Token char_literal61=null;
        ANTLRParser.id_return id60 = null;

        ANTLRParser.id_return id62 = null;


        CommonTree string_literal59_tree=null;
        CommonTree char_literal61_tree=null;
        RewriteRuleTokenStream stream_83=new RewriteRuleTokenStream(adaptor,"token 83");
        RewriteRuleTokenStream stream_74=new RewriteRuleTokenStream(adaptor,"token 74");
        RewriteRuleSubtreeStream stream_id=new RewriteRuleSubtreeStream(adaptor,"rule id");
        try {
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:262:3: ( 'throws' id ( ',' id )* -> ^( 'throws' ( id )+ ) )
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:263:3: 'throws' id ( ',' id )*
            {
            string_literal59=(Token)match(input,83,FOLLOW_83_in_throwsSpec1301); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_83.add(string_literal59);

            pushFollow(FOLLOW_id_in_throwsSpec1303);
            id60=id();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_id.add(id60.getTree());
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:263:15: ( ',' id )*
            loop28:
            do {
                int alt28=2;
                int LA28_0 = input.LA(1);

                if ( (LA28_0==74) ) {
                    alt28=1;
                }


                switch (alt28) {
            	case 1 :
            	    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:263:16: ',' id
            	    {
            	    char_literal61=(Token)match(input,74,FOLLOW_74_in_throwsSpec1306); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_74.add(char_literal61);

            	    pushFollow(FOLLOW_id_in_throwsSpec1308);
            	    id62=id();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_id.add(id62.getTree());

            	    }
            	    break;

            	default :
            	    break loop28;
                }
            } while (true);



            // AST REWRITE
            // elements: id, 83
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 264:5: -> ^( 'throws' ( id )+ )
            {
                // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:265:7: ^( 'throws' ( id )+ )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(stream_83.nextNode(), root_1);

                if ( !(stream_id.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_id.hasNext() ) {
                    adaptor.addChild(root_1, stream_id.nextTree());

                }
                stream_id.reset();

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "throwsSpec"

    public static class ruleScopeSpec_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "ruleScopeSpec"
    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:268:1: ruleScopeSpec : ( 'scope' ACTION -> ^( 'scope' ACTION ) | 'scope' id ( ',' id )* ';' -> ^( 'scope' ( id )+ ) | 'scope' ACTION 'scope' id ( ',' id )* ';' -> ^( 'scope' ACTION ( id )+ ) );
    public final ANTLRParser.ruleScopeSpec_return ruleScopeSpec() throws RecognitionException {
        ANTLRParser.ruleScopeSpec_return retval = new ANTLRParser.ruleScopeSpec_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token string_literal63=null;
        Token ACTION64=null;
        Token string_literal65=null;
        Token char_literal67=null;
        Token char_literal69=null;
        Token string_literal70=null;
        Token ACTION71=null;
        Token string_literal72=null;
        Token char_literal74=null;
        Token char_literal76=null;
        ANTLRParser.id_return id66 = null;

        ANTLRParser.id_return id68 = null;

        ANTLRParser.id_return id73 = null;

        ANTLRParser.id_return id75 = null;


        CommonTree string_literal63_tree=null;
        CommonTree ACTION64_tree=null;
        CommonTree string_literal65_tree=null;
        CommonTree char_literal67_tree=null;
        CommonTree char_literal69_tree=null;
        CommonTree string_literal70_tree=null;
        CommonTree ACTION71_tree=null;
        CommonTree string_literal72_tree=null;
        CommonTree char_literal74_tree=null;
        CommonTree char_literal76_tree=null;
        RewriteRuleTokenStream stream_SCOPE=new RewriteRuleTokenStream(adaptor,"token SCOPE");
        RewriteRuleTokenStream stream_SEMI=new RewriteRuleTokenStream(adaptor,"token SEMI");
        RewriteRuleTokenStream stream_ACTION=new RewriteRuleTokenStream(adaptor,"token ACTION");
        RewriteRuleTokenStream stream_74=new RewriteRuleTokenStream(adaptor,"token 74");
        RewriteRuleSubtreeStream stream_id=new RewriteRuleSubtreeStream(adaptor,"rule id");
        try {
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:269:3: ( 'scope' ACTION -> ^( 'scope' ACTION ) | 'scope' id ( ',' id )* ';' -> ^( 'scope' ( id )+ ) | 'scope' ACTION 'scope' id ( ',' id )* ';' -> ^( 'scope' ACTION ( id )+ ) )
            int alt31=3;
            int LA31_0 = input.LA(1);

            if ( (LA31_0==SCOPE) ) {
                int LA31_1 = input.LA(2);

                if ( (LA31_1==ACTION) ) {
                    int LA31_2 = input.LA(3);

                    if ( (LA31_2==SCOPE) ) {
                        alt31=3;
                    }
                    else if ( (LA31_2==COLON||LA31_2==76) ) {
                        alt31=1;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 31, 2, input);

                        throw nvae;
                    }
                }
                else if ( (LA31_1==TOKEN_REF||LA31_1==RULE_REF) ) {
                    alt31=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 31, 1, input);

                    throw nvae;
                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 31, 0, input);

                throw nvae;
            }
            switch (alt31) {
                case 1 :
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:270:3: 'scope' ACTION
                    {
                    string_literal63=(Token)match(input,SCOPE,FOLLOW_SCOPE_in_ruleScopeSpec1344); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_SCOPE.add(string_literal63);

                    ACTION64=(Token)match(input,ACTION,FOLLOW_ACTION_in_ruleScopeSpec1346); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_ACTION.add(ACTION64);



                    // AST REWRITE
                    // elements: SCOPE, ACTION
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 271:5: -> ^( 'scope' ACTION )
                    {
                        // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:272:7: ^( 'scope' ACTION )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(stream_SCOPE.nextNode(), root_1);

                        adaptor.addChild(root_1, stream_ACTION.nextNode());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 2 :
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:273:5: 'scope' id ( ',' id )* ';'
                    {
                    string_literal65=(Token)match(input,SCOPE,FOLLOW_SCOPE_in_ruleScopeSpec1370); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_SCOPE.add(string_literal65);

                    pushFollow(FOLLOW_id_in_ruleScopeSpec1372);
                    id66=id();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_id.add(id66.getTree());
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:273:16: ( ',' id )*
                    loop29:
                    do {
                        int alt29=2;
                        int LA29_0 = input.LA(1);

                        if ( (LA29_0==74) ) {
                            alt29=1;
                        }


                        switch (alt29) {
                    	case 1 :
                    	    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:273:17: ',' id
                    	    {
                    	    char_literal67=(Token)match(input,74,FOLLOW_74_in_ruleScopeSpec1375); if (state.failed) return retval; 
                    	    if ( state.backtracking==0 ) stream_74.add(char_literal67);

                    	    pushFollow(FOLLOW_id_in_ruleScopeSpec1377);
                    	    id68=id();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) stream_id.add(id68.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop29;
                        }
                    } while (true);

                    char_literal69=(Token)match(input,SEMI,FOLLOW_SEMI_in_ruleScopeSpec1381); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_SEMI.add(char_literal69);



                    // AST REWRITE
                    // elements: id, SCOPE
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 274:5: -> ^( 'scope' ( id )+ )
                    {
                        // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:275:7: ^( 'scope' ( id )+ )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(stream_SCOPE.nextNode(), root_1);

                        if ( !(stream_id.hasNext()) ) {
                            throw new RewriteEarlyExitException();
                        }
                        while ( stream_id.hasNext() ) {
                            adaptor.addChild(root_1, stream_id.nextTree());

                        }
                        stream_id.reset();

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 3 :
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:276:5: 'scope' ACTION 'scope' id ( ',' id )* ';'
                    {
                    string_literal70=(Token)match(input,SCOPE,FOLLOW_SCOPE_in_ruleScopeSpec1406); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_SCOPE.add(string_literal70);

                    ACTION71=(Token)match(input,ACTION,FOLLOW_ACTION_in_ruleScopeSpec1408); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_ACTION.add(ACTION71);

                    string_literal72=(Token)match(input,SCOPE,FOLLOW_SCOPE_in_ruleScopeSpec1410); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_SCOPE.add(string_literal72);

                    pushFollow(FOLLOW_id_in_ruleScopeSpec1412);
                    id73=id();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_id.add(id73.getTree());
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:276:31: ( ',' id )*
                    loop30:
                    do {
                        int alt30=2;
                        int LA30_0 = input.LA(1);

                        if ( (LA30_0==74) ) {
                            alt30=1;
                        }


                        switch (alt30) {
                    	case 1 :
                    	    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:276:32: ',' id
                    	    {
                    	    char_literal74=(Token)match(input,74,FOLLOW_74_in_ruleScopeSpec1415); if (state.failed) return retval; 
                    	    if ( state.backtracking==0 ) stream_74.add(char_literal74);

                    	    pushFollow(FOLLOW_id_in_ruleScopeSpec1417);
                    	    id75=id();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) stream_id.add(id75.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop30;
                        }
                    } while (true);

                    char_literal76=(Token)match(input,SEMI,FOLLOW_SEMI_in_ruleScopeSpec1421); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_SEMI.add(char_literal76);



                    // AST REWRITE
                    // elements: ACTION, id, SCOPE
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 277:5: -> ^( 'scope' ACTION ( id )+ )
                    {
                        // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:278:7: ^( 'scope' ACTION ( id )+ )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(stream_SCOPE.nextNode(), root_1);

                        adaptor.addChild(root_1, stream_ACTION.nextNode());
                        if ( !(stream_id.hasNext()) ) {
                            throw new RewriteEarlyExitException();
                        }
                        while ( stream_id.hasNext() ) {
                            adaptor.addChild(root_1, stream_id.nextTree());

                        }
                        stream_id.reset();

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "ruleScopeSpec"

    public static class block_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "block"
    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:281:1: block : lp= '(' ( ( optionsSpec )? ':' )? altpair ( '|' altpair )* rp= ')' -> ^( BLOCK[$lp] ( optionsSpec )? ( altpair )+ EOB[$rp] ) ;
    public final ANTLRParser.block_return block() throws RecognitionException {
        ANTLRParser.block_return retval = new ANTLRParser.block_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token lp=null;
        Token rp=null;
        Token char_literal78=null;
        Token char_literal80=null;
        ANTLRParser.optionsSpec_return optionsSpec77 = null;

        ANTLRParser.altpair_return altpair79 = null;

        ANTLRParser.altpair_return altpair81 = null;


        CommonTree lp_tree=null;
        CommonTree rp_tree=null;
        CommonTree char_literal78_tree=null;
        CommonTree char_literal80_tree=null;
        RewriteRuleTokenStream stream_COLON=new RewriteRuleTokenStream(adaptor,"token COLON");
        RewriteRuleTokenStream stream_86=new RewriteRuleTokenStream(adaptor,"token 86");
        RewriteRuleTokenStream stream_84=new RewriteRuleTokenStream(adaptor,"token 84");
        RewriteRuleTokenStream stream_85=new RewriteRuleTokenStream(adaptor,"token 85");
        RewriteRuleSubtreeStream stream_altpair=new RewriteRuleSubtreeStream(adaptor,"rule altpair");
        RewriteRuleSubtreeStream stream_optionsSpec=new RewriteRuleSubtreeStream(adaptor,"rule optionsSpec");
        try {
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:282:3: (lp= '(' ( ( optionsSpec )? ':' )? altpair ( '|' altpair )* rp= ')' -> ^( BLOCK[$lp] ( optionsSpec )? ( altpair )+ EOB[$rp] ) )
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:283:3: lp= '(' ( ( optionsSpec )? ':' )? altpair ( '|' altpair )* rp= ')'
            {
            lp=(Token)match(input,84,FOLLOW_84_in_block1459); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_84.add(lp);

            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:283:10: ( ( optionsSpec )? ':' )?
            int alt33=2;
            int LA33_0 = input.LA(1);

            if ( (LA33_0==COLON||LA33_0==OPTIONS) ) {
                alt33=1;
            }
            switch (alt33) {
                case 1 :
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:283:12: ( optionsSpec )? ':'
                    {
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:283:12: ( optionsSpec )?
                    int alt32=2;
                    int LA32_0 = input.LA(1);

                    if ( (LA32_0==OPTIONS) ) {
                        alt32=1;
                    }
                    switch (alt32) {
                        case 1 :
                            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:283:13: optionsSpec
                            {
                            pushFollow(FOLLOW_optionsSpec_in_block1464);
                            optionsSpec77=optionsSpec();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_optionsSpec.add(optionsSpec77.getTree());

                            }
                            break;

                    }

                    char_literal78=(Token)match(input,COLON,FOLLOW_COLON_in_block1468); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_COLON.add(char_literal78);


                    }
                    break;

            }

            pushFollow(FOLLOW_altpair_in_block1472);
            altpair79=altpair();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_altpair.add(altpair79.getTree());
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:283:41: ( '|' altpair )*
            loop34:
            do {
                int alt34=2;
                int LA34_0 = input.LA(1);

                if ( (LA34_0==85) ) {
                    alt34=1;
                }


                switch (alt34) {
            	case 1 :
            	    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:283:42: '|' altpair
            	    {
            	    char_literal80=(Token)match(input,85,FOLLOW_85_in_block1475); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_85.add(char_literal80);

            	    pushFollow(FOLLOW_altpair_in_block1477);
            	    altpair81=altpair();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_altpair.add(altpair81.getTree());

            	    }
            	    break;

            	default :
            	    break loop34;
                }
            } while (true);

            rp=(Token)match(input,86,FOLLOW_86_in_block1483); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_86.add(rp);



            // AST REWRITE
            // elements: optionsSpec, altpair
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 284:5: -> ^( BLOCK[$lp] ( optionsSpec )? ( altpair )+ EOB[$rp] )
            {
                // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:285:7: ^( BLOCK[$lp] ( optionsSpec )? ( altpair )+ EOB[$rp] )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(BLOCK, lp), root_1);

                // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:285:20: ( optionsSpec )?
                if ( stream_optionsSpec.hasNext() ) {
                    adaptor.addChild(root_1, stream_optionsSpec.nextTree());

                }
                stream_optionsSpec.reset();
                if ( !(stream_altpair.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_altpair.hasNext() ) {
                    adaptor.addChild(root_1, stream_altpair.nextTree());

                }
                stream_altpair.reset();
                adaptor.addChild(root_1, (CommonTree)adaptor.create(EOB, rp));

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "block"

    public static class altpair_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "altpair"
    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:288:1: altpair : alternative rewrite ;
    public final ANTLRParser.altpair_return altpair() throws RecognitionException {
        ANTLRParser.altpair_return retval = new ANTLRParser.altpair_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        ANTLRParser.alternative_return alternative82 = null;

        ANTLRParser.rewrite_return rewrite83 = null;



        try {
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:289:3: ( alternative rewrite )
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:290:3: alternative rewrite
            {
            root_0 = (CommonTree)adaptor.nil();

            pushFollow(FOLLOW_alternative_in_altpair1524);
            alternative82=alternative();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, alternative82.getTree());
            pushFollow(FOLLOW_rewrite_in_altpair1526);
            rewrite83=rewrite();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, rewrite83.getTree());

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "altpair"

    public static class altList_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "altList"
    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:293:1: altList : altpair ( '|' altpair )* -> ^( ( altpair )+ ) ;
    public final ANTLRParser.altList_return altList() throws RecognitionException {
        ANTLRParser.altList_return retval = new ANTLRParser.altList_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token char_literal85=null;
        ANTLRParser.altpair_return altpair84 = null;

        ANTLRParser.altpair_return altpair86 = null;


        CommonTree char_literal85_tree=null;
        RewriteRuleTokenStream stream_85=new RewriteRuleTokenStream(adaptor,"token 85");
        RewriteRuleSubtreeStream stream_altpair=new RewriteRuleSubtreeStream(adaptor,"rule altpair");

          // must create root manually as it's used by invoked rules in real antlr tool.
          // leave here to demonstrate use of {...} in rewrite rule
          // it's really BLOCK[firstToken,"BLOCK"]; set line/col to previous ( or : token.
            CommonTree blkStart = (CommonTree)adaptor.create(BLOCK,input.LT(-1),"BLOCK");

        try {
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:300:3: ( altpair ( '|' altpair )* -> ^( ( altpair )+ ) )
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:301:3: altpair ( '|' altpair )*
            {
            pushFollow(FOLLOW_altpair_in_altList1546);
            altpair84=altpair();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_altpair.add(altpair84.getTree());
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:301:11: ( '|' altpair )*
            loop35:
            do {
                int alt35=2;
                int LA35_0 = input.LA(1);

                if ( (LA35_0==85) ) {
                    alt35=1;
                }


                switch (alt35) {
            	case 1 :
            	    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:301:12: '|' altpair
            	    {
            	    char_literal85=(Token)match(input,85,FOLLOW_85_in_altList1549); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_85.add(char_literal85);

            	    pushFollow(FOLLOW_altpair_in_altList1551);
            	    altpair86=altpair();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_altpair.add(altpair86.getTree());

            	    }
            	    break;

            	default :
            	    break loop35;
                }
            } while (true);



            // AST REWRITE
            // elements: altpair
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 302:5: -> ^( ( altpair )+ )
            {
                // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:303:7: ^( ( altpair )+ )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(blkStart, root_1);

                if ( !(stream_altpair.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_altpair.hasNext() ) {
                    adaptor.addChild(root_1, stream_altpair.nextTree());

                }
                stream_altpair.reset();
                adaptor.addChild(root_1, adaptor.create(EOB, input.LT(-1)));

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "altList"

    public static class alternative_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "alternative"
    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:306:1: alternative : ( ( element )+ -> ^( ALT[firstToken] ( element )+ ) | -> ^( ALT[prevToken] EPSILON[prevToken] EOA[prevToken] ) );
    public final ANTLRParser.alternative_return alternative() throws RecognitionException {
        ANTLRParser.alternative_return retval = new ANTLRParser.alternative_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        ANTLRParser.element_return element87 = null;


        RewriteRuleSubtreeStream stream_element=new RewriteRuleSubtreeStream(adaptor,"rule element");

          Token firstToken = input.LT(1);
          Token prevToken = input.LT(-1); // either : or | I think

        try {
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:311:3: ( ( element )+ -> ^( ALT[firstToken] ( element )+ ) | -> ^( ALT[prevToken] EPSILON[prevToken] EOA[prevToken] ) )
            int alt37=2;
            switch ( input.LA(1) ) {
            case TOKEN_REF:
                {
                switch ( input.LA(2) ) {
                case EOF:
                case DOC_COMMENT:
                case SEMPRED:
                case FRAGMENT:
                case TREE_BEGIN:
                case ROOT:
                case REWRITE:
                case SEMI:
                case DOT:
                case ASSIGN:
                case TOKEN_REF:
                case STRING_LITERAL:
                case ACTION:
                case RULE_REF:
                case 78:
                case 80:
                case 81:
                case 82:
                case 84:
                case 85:
                case 86:
                case 87:
                case 88:
                case 89:
                case 91:
                case 92:
                case 93:
                case 94:
                    {
                    alt37=1;
                    }
                    break;
                case BANG:
                    {
                    int LA37_5 = input.LA(3);

                    if ( (LA37_5==EOF||LA37_5==DOC_COMMENT||LA37_5==SEMPRED||(LA37_5>=FRAGMENT && LA37_5<=TREE_BEGIN)||(LA37_5>=REWRITE && LA37_5<=SEMI)||LA37_5==DOT||(LA37_5>=TOKEN_REF && LA37_5<=ACTION)||LA37_5==RULE_REF||LA37_5==78||(LA37_5>=80 && LA37_5<=82)||(LA37_5>=84 && LA37_5<=88)||(LA37_5>=91 && LA37_5<=93)) ) {
                        alt37=1;
                    }
                    else if ( (LA37_5==SCOPE||LA37_5==COLON||LA37_5==OPTIONS||LA37_5==ARG_ACTION||LA37_5==76||LA37_5==79||LA37_5==83) ) {
                        alt37=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 37, 5, input);

                        throw nvae;
                    }
                    }
                    break;
                case ARG_ACTION:
                    {
                    int LA37_6 = input.LA(3);

                    if ( (LA37_6==EOF||LA37_6==DOC_COMMENT||LA37_6==SEMPRED||(LA37_6>=FRAGMENT && LA37_6<=SEMI)||LA37_6==DOT||(LA37_6>=TOKEN_REF && LA37_6<=ACTION)||LA37_6==RULE_REF||LA37_6==78||(LA37_6>=80 && LA37_6<=82)||(LA37_6>=84 && LA37_6<=88)||(LA37_6>=91 && LA37_6<=93)) ) {
                        alt37=1;
                    }
                    else if ( (LA37_6==SCOPE||LA37_6==COLON||LA37_6==OPTIONS||LA37_6==76||LA37_6==79||LA37_6==83) ) {
                        alt37=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 37, 6, input);

                        throw nvae;
                    }
                    }
                    break;
                case SCOPE:
                case COLON:
                case OPTIONS:
                case 76:
                case 79:
                case 83:
                    {
                    alt37=2;
                    }
                    break;
                default:
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 37, 1, input);

                    throw nvae;
                }

                }
                break;
            case RULE_REF:
                {
                switch ( input.LA(2) ) {
                case EOF:
                case DOC_COMMENT:
                case SEMPRED:
                case FRAGMENT:
                case TREE_BEGIN:
                case ROOT:
                case REWRITE:
                case SEMI:
                case DOT:
                case ASSIGN:
                case TOKEN_REF:
                case STRING_LITERAL:
                case ACTION:
                case RULE_REF:
                case 78:
                case 80:
                case 81:
                case 82:
                case 84:
                case 85:
                case 86:
                case 87:
                case 88:
                case 89:
                case 91:
                case 92:
                case 93:
                    {
                    alt37=1;
                    }
                    break;
                case BANG:
                    {
                    int LA37_7 = input.LA(3);

                    if ( (LA37_7==SCOPE||LA37_7==COLON||LA37_7==OPTIONS||LA37_7==ARG_ACTION||LA37_7==76||LA37_7==79||LA37_7==83) ) {
                        alt37=2;
                    }
                    else if ( (LA37_7==EOF||LA37_7==DOC_COMMENT||LA37_7==SEMPRED||(LA37_7>=FRAGMENT && LA37_7<=TREE_BEGIN)||(LA37_7>=REWRITE && LA37_7<=SEMI)||LA37_7==DOT||(LA37_7>=TOKEN_REF && LA37_7<=ACTION)||LA37_7==RULE_REF||LA37_7==78||(LA37_7>=80 && LA37_7<=82)||(LA37_7>=84 && LA37_7<=88)||(LA37_7>=91 && LA37_7<=93)) ) {
                        alt37=1;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 37, 7, input);

                        throw nvae;
                    }
                    }
                    break;
                case ARG_ACTION:
                    {
                    int LA37_8 = input.LA(3);

                    if ( (LA37_8==EOF||LA37_8==DOC_COMMENT||LA37_8==SEMPRED||(LA37_8>=FRAGMENT && LA37_8<=SEMI)||LA37_8==DOT||(LA37_8>=TOKEN_REF && LA37_8<=ACTION)||LA37_8==RULE_REF||LA37_8==78||(LA37_8>=80 && LA37_8<=82)||(LA37_8>=84 && LA37_8<=88)||(LA37_8>=91 && LA37_8<=93)) ) {
                        alt37=1;
                    }
                    else if ( (LA37_8==SCOPE||LA37_8==COLON||LA37_8==OPTIONS||LA37_8==76||LA37_8==79||LA37_8==83) ) {
                        alt37=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 37, 8, input);

                        throw nvae;
                    }
                    }
                    break;
                case SCOPE:
                case COLON:
                case OPTIONS:
                case 76:
                case 79:
                case 83:
                    {
                    alt37=2;
                    }
                    break;
                default:
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 37, 2, input);

                    throw nvae;
                }

                }
                break;
            case SEMPRED:
            case TREE_BEGIN:
            case DOT:
            case STRING_LITERAL:
            case ACTION:
            case 84:
            case 91:
                {
                alt37=1;
                }
                break;
            case EOF:
            case DOC_COMMENT:
            case FRAGMENT:
            case REWRITE:
            case SEMI:
            case 80:
            case 81:
            case 82:
            case 85:
            case 86:
            case 87:
            case 88:
                {
                alt37=2;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 37, 0, input);

                throw nvae;
            }

            switch (alt37) {
                case 1 :
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:312:3: ( element )+
                    {
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:312:3: ( element )+
                    int cnt36=0;
                    loop36:
                    do {
                        int alt36=2;
                        switch ( input.LA(1) ) {
                        case TOKEN_REF:
                            {
                            switch ( input.LA(2) ) {
                            case EOF:
                            case DOC_COMMENT:
                            case SEMPRED:
                            case FRAGMENT:
                            case TREE_BEGIN:
                            case ROOT:
                            case REWRITE:
                            case SEMI:
                            case DOT:
                            case ASSIGN:
                            case TOKEN_REF:
                            case STRING_LITERAL:
                            case ACTION:
                            case RULE_REF:
                            case 78:
                            case 80:
                            case 81:
                            case 82:
                            case 84:
                            case 85:
                            case 86:
                            case 87:
                            case 88:
                            case 89:
                            case 91:
                            case 92:
                            case 93:
                            case 94:
                                {
                                alt36=1;
                                }
                                break;
                            case BANG:
                                {
                                int LA36_5 = input.LA(3);

                                if ( (LA36_5==EOF||LA36_5==DOC_COMMENT||LA36_5==SEMPRED||(LA36_5>=FRAGMENT && LA36_5<=TREE_BEGIN)||(LA36_5>=REWRITE && LA36_5<=SEMI)||LA36_5==DOT||(LA36_5>=TOKEN_REF && LA36_5<=ACTION)||LA36_5==RULE_REF||LA36_5==78||(LA36_5>=80 && LA36_5<=82)||(LA36_5>=84 && LA36_5<=88)||(LA36_5>=91 && LA36_5<=93)) ) {
                                    alt36=1;
                                }


                                }
                                break;
                            case ARG_ACTION:
                                {
                                int LA36_6 = input.LA(3);

                                if ( (LA36_6==EOF||LA36_6==DOC_COMMENT||LA36_6==SEMPRED||(LA36_6>=FRAGMENT && LA36_6<=SEMI)||LA36_6==DOT||(LA36_6>=TOKEN_REF && LA36_6<=ACTION)||LA36_6==RULE_REF||LA36_6==78||(LA36_6>=80 && LA36_6<=82)||(LA36_6>=84 && LA36_6<=88)||(LA36_6>=91 && LA36_6<=93)) ) {
                                    alt36=1;
                                }


                                }
                                break;

                            }

                            }
                            break;
                        case RULE_REF:
                            {
                            switch ( input.LA(2) ) {
                            case EOF:
                            case DOC_COMMENT:
                            case SEMPRED:
                            case FRAGMENT:
                            case TREE_BEGIN:
                            case ROOT:
                            case REWRITE:
                            case SEMI:
                            case DOT:
                            case ASSIGN:
                            case TOKEN_REF:
                            case STRING_LITERAL:
                            case ACTION:
                            case RULE_REF:
                            case 78:
                            case 80:
                            case 81:
                            case 82:
                            case 84:
                            case 85:
                            case 86:
                            case 87:
                            case 88:
                            case 89:
                            case 91:
                            case 92:
                            case 93:
                                {
                                alt36=1;
                                }
                                break;
                            case BANG:
                                {
                                int LA36_7 = input.LA(3);

                                if ( (LA36_7==EOF||LA36_7==DOC_COMMENT||LA36_7==SEMPRED||(LA36_7>=FRAGMENT && LA36_7<=TREE_BEGIN)||(LA36_7>=REWRITE && LA36_7<=SEMI)||LA36_7==DOT||(LA36_7>=TOKEN_REF && LA36_7<=ACTION)||LA36_7==RULE_REF||LA36_7==78||(LA36_7>=80 && LA36_7<=82)||(LA36_7>=84 && LA36_7<=88)||(LA36_7>=91 && LA36_7<=93)) ) {
                                    alt36=1;
                                }


                                }
                                break;
                            case ARG_ACTION:
                                {
                                int LA36_8 = input.LA(3);

                                if ( (LA36_8==EOF||LA36_8==DOC_COMMENT||LA36_8==SEMPRED||(LA36_8>=FRAGMENT && LA36_8<=SEMI)||LA36_8==DOT||(LA36_8>=TOKEN_REF && LA36_8<=ACTION)||LA36_8==RULE_REF||LA36_8==78||(LA36_8>=80 && LA36_8<=82)||(LA36_8>=84 && LA36_8<=88)||(LA36_8>=91 && LA36_8<=93)) ) {
                                    alt36=1;
                                }


                                }
                                break;

                            }

                            }
                            break;
                        case SEMPRED:
                        case TREE_BEGIN:
                        case DOT:
                        case STRING_LITERAL:
                        case ACTION:
                        case 84:
                        case 91:
                            {
                            alt36=1;
                            }
                            break;

                        }

                        switch (alt36) {
                    	case 1 :
                    	    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:312:4: element
                    	    {
                    	    pushFollow(FOLLOW_element_in_alternative1596);
                    	    element87=element();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) stream_element.add(element87.getTree());

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt36 >= 1 ) break loop36;
                    	    if (state.backtracking>0) {state.failed=true; return retval;}
                                EarlyExitException eee =
                                    new EarlyExitException(36, input);
                                throw eee;
                        }
                        cnt36++;
                    } while (true);



                    // AST REWRITE
                    // elements: element
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 313:5: -> ^( ALT[firstToken] ( element )+ )
                    {
                        // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:314:7: ^( ALT[firstToken] ( element )+ )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ALT, firstToken), root_1);

                        if ( !(stream_element.hasNext()) ) {
                            throw new RewriteEarlyExitException();
                        }
                        while ( stream_element.hasNext() ) {
                            adaptor.addChild(root_1, stream_element.nextTree());

                        }
                        stream_element.reset();
                        adaptor.addChild(root_1, adaptor.create(EOA, input.LT(-1)));

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 2 :
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:316:5: 
                    {

                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 316:5: -> ^( ALT[prevToken] EPSILON[prevToken] EOA[prevToken] )
                    {
                        // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:317:7: ^( ALT[prevToken] EPSILON[prevToken] EOA[prevToken] )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ALT, prevToken), root_1);

                        adaptor.addChild(root_1, (CommonTree)adaptor.create(EPSILON, prevToken));
                        adaptor.addChild(root_1, (CommonTree)adaptor.create(EOA, prevToken));

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "alternative"

    public static class exceptionGroup_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "exceptionGroup"
    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:320:1: exceptionGroup : ( ( exceptionHandler )+ ( finallyClause )? | finallyClause );
    public final ANTLRParser.exceptionGroup_return exceptionGroup() throws RecognitionException {
        ANTLRParser.exceptionGroup_return retval = new ANTLRParser.exceptionGroup_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        ANTLRParser.exceptionHandler_return exceptionHandler88 = null;

        ANTLRParser.finallyClause_return finallyClause89 = null;

        ANTLRParser.finallyClause_return finallyClause90 = null;



        try {
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:321:3: ( ( exceptionHandler )+ ( finallyClause )? | finallyClause )
            int alt40=2;
            int LA40_0 = input.LA(1);

            if ( (LA40_0==87) ) {
                alt40=1;
            }
            else if ( (LA40_0==88) ) {
                alt40=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 40, 0, input);

                throw nvae;
            }
            switch (alt40) {
                case 1 :
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:322:3: ( exceptionHandler )+ ( finallyClause )?
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:322:3: ( exceptionHandler )+
                    int cnt38=0;
                    loop38:
                    do {
                        int alt38=2;
                        int LA38_0 = input.LA(1);

                        if ( (LA38_0==87) ) {
                            alt38=1;
                        }


                        switch (alt38) {
                    	case 1 :
                    	    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:322:4: exceptionHandler
                    	    {
                    	    pushFollow(FOLLOW_exceptionHandler_in_exceptionGroup1663);
                    	    exceptionHandler88=exceptionHandler();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) adaptor.addChild(root_0, exceptionHandler88.getTree());

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt38 >= 1 ) break loop38;
                    	    if (state.backtracking>0) {state.failed=true; return retval;}
                                EarlyExitException eee =
                                    new EarlyExitException(38, input);
                                throw eee;
                        }
                        cnt38++;
                    } while (true);

                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:322:23: ( finallyClause )?
                    int alt39=2;
                    int LA39_0 = input.LA(1);

                    if ( (LA39_0==88) ) {
                        alt39=1;
                    }
                    switch (alt39) {
                        case 1 :
                            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:322:24: finallyClause
                            {
                            pushFollow(FOLLOW_finallyClause_in_exceptionGroup1668);
                            finallyClause89=finallyClause();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, finallyClause89.getTree());

                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:323:5: finallyClause
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_finallyClause_in_exceptionGroup1676);
                    finallyClause90=finallyClause();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, finallyClause90.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "exceptionGroup"

    public static class exceptionHandler_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "exceptionHandler"
    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:326:1: exceptionHandler : 'catch' ARG_ACTION ACTION -> ^( 'catch' ARG_ACTION ACTION ) ;
    public final ANTLRParser.exceptionHandler_return exceptionHandler() throws RecognitionException {
        ANTLRParser.exceptionHandler_return retval = new ANTLRParser.exceptionHandler_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token string_literal91=null;
        Token ARG_ACTION92=null;
        Token ACTION93=null;

        CommonTree string_literal91_tree=null;
        CommonTree ARG_ACTION92_tree=null;
        CommonTree ACTION93_tree=null;
        RewriteRuleTokenStream stream_ACTION=new RewriteRuleTokenStream(adaptor,"token ACTION");
        RewriteRuleTokenStream stream_87=new RewriteRuleTokenStream(adaptor,"token 87");
        RewriteRuleTokenStream stream_ARG_ACTION=new RewriteRuleTokenStream(adaptor,"token ARG_ACTION");

        try {
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:327:3: ( 'catch' ARG_ACTION ACTION -> ^( 'catch' ARG_ACTION ACTION ) )
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:328:3: 'catch' ARG_ACTION ACTION
            {
            string_literal91=(Token)match(input,87,FOLLOW_87_in_exceptionHandler1691); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_87.add(string_literal91);

            ARG_ACTION92=(Token)match(input,ARG_ACTION,FOLLOW_ARG_ACTION_in_exceptionHandler1693); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ARG_ACTION.add(ARG_ACTION92);

            ACTION93=(Token)match(input,ACTION,FOLLOW_ACTION_in_exceptionHandler1695); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ACTION.add(ACTION93);



            // AST REWRITE
            // elements: ACTION, ARG_ACTION, 87
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 329:5: -> ^( 'catch' ARG_ACTION ACTION )
            {
                // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:330:7: ^( 'catch' ARG_ACTION ACTION )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(stream_87.nextNode(), root_1);

                adaptor.addChild(root_1, stream_ARG_ACTION.nextNode());
                adaptor.addChild(root_1, stream_ACTION.nextNode());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "exceptionHandler"

    public static class finallyClause_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "finallyClause"
    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:333:1: finallyClause : 'finally' ACTION -> ^( 'finally' ACTION ) ;
    public final ANTLRParser.finallyClause_return finallyClause() throws RecognitionException {
        ANTLRParser.finallyClause_return retval = new ANTLRParser.finallyClause_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token string_literal94=null;
        Token ACTION95=null;

        CommonTree string_literal94_tree=null;
        CommonTree ACTION95_tree=null;
        RewriteRuleTokenStream stream_ACTION=new RewriteRuleTokenStream(adaptor,"token ACTION");
        RewriteRuleTokenStream stream_88=new RewriteRuleTokenStream(adaptor,"token 88");

        try {
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:334:3: ( 'finally' ACTION -> ^( 'finally' ACTION ) )
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:335:3: 'finally' ACTION
            {
            string_literal94=(Token)match(input,88,FOLLOW_88_in_finallyClause1730); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_88.add(string_literal94);

            ACTION95=(Token)match(input,ACTION,FOLLOW_ACTION_in_finallyClause1732); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ACTION.add(ACTION95);



            // AST REWRITE
            // elements: ACTION, 88
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 336:5: -> ^( 'finally' ACTION )
            {
                // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:337:7: ^( 'finally' ACTION )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(stream_88.nextNode(), root_1);

                adaptor.addChild(root_1, stream_ACTION.nextNode());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "finallyClause"

    public static class element_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "element"
    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:340:1: element : elementNoOptionSpec ;
    public final ANTLRParser.element_return element() throws RecognitionException {
        ANTLRParser.element_return retval = new ANTLRParser.element_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        ANTLRParser.elementNoOptionSpec_return elementNoOptionSpec96 = null;



        try {
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:341:3: ( elementNoOptionSpec )
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:342:3: elementNoOptionSpec
            {
            root_0 = (CommonTree)adaptor.nil();

            pushFollow(FOLLOW_elementNoOptionSpec_in_element1765);
            elementNoOptionSpec96=elementNoOptionSpec();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, elementNoOptionSpec96.getTree());

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "element"

    public static class elementNoOptionSpec_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "elementNoOptionSpec"
    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:345:1: elementNoOptionSpec : ( id (labelOp= '=' | labelOp= '+=' ) atom (startEndStt= ebnfSuffix -> ^( ebnfSuffix ^( BLOCK[$startEndStt.tree.getToken()] ^( ALT[$startEndStt.tree.getToken()] ^( $labelOp id atom ) EOA[$startEndStt.tree.getToken()] ) EOB[$startEndStt.tree.getToken()] ) ) | -> ^( $labelOp id atom ) ) | id (labelOp= '=' | labelOp= '+=' ) block (startEndStt= ebnfSuffix -> ^( ebnfSuffix ^( BLOCK[$startEndStt.tree.getToken()] ^( ALT[$startEndStt.tree.getToken()] ^( $labelOp id block ) EOA[$startEndStt.tree.getToken()] ) EOB[$startEndStt.tree.getToken()] ) ) | -> ^( $labelOp id block ) ) | atom (startEndStt= ebnfSuffix -> ^( ebnfSuffix ^( BLOCK[$startEndStt.tree.getToken()] ^( ALT[$startEndStt.tree.getToken()] atom EOA[$startEndStt.tree.getToken()] ) EOB[$startEndStt.tree.getToken()] ) ) | -> atom ) | ebnf | ACTION | s= SEMPRED ( '=>' -> GATED_SEMPRED[$s] | -> $s) | treeSpec (startEndStt= ebnfSuffix -> ^( ebnfSuffix ^( BLOCK[$startEndStt.tree.getToken()] ^( ALT[$startEndStt.tree.getToken()] treeSpec EOA[$startEndStt.tree.getToken()] ) EOB[$startEndStt.tree.getToken()] ) ) | -> treeSpec ) );
    public final ANTLRParser.elementNoOptionSpec_return elementNoOptionSpec() throws RecognitionException {
        ANTLRParser.elementNoOptionSpec_return retval = new ANTLRParser.elementNoOptionSpec_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token labelOp=null;
        Token s=null;
        Token ACTION103=null;
        Token string_literal104=null;
        ANTLRParser.ebnfSuffix_return startEndStt = null;

        ANTLRParser.id_return id97 = null;

        ANTLRParser.atom_return atom98 = null;

        ANTLRParser.id_return id99 = null;

        ANTLRParser.block_return block100 = null;

        ANTLRParser.atom_return atom101 = null;

        ANTLRParser.ebnf_return ebnf102 = null;

        ANTLRParser.treeSpec_return treeSpec105 = null;


        CommonTree labelOp_tree=null;
        CommonTree s_tree=null;
        CommonTree ACTION103_tree=null;
        CommonTree string_literal104_tree=null;
        RewriteRuleTokenStream stream_90=new RewriteRuleTokenStream(adaptor,"token 90");
        RewriteRuleTokenStream stream_SEMPRED=new RewriteRuleTokenStream(adaptor,"token SEMPRED");
        RewriteRuleTokenStream stream_89=new RewriteRuleTokenStream(adaptor,"token 89");
        RewriteRuleTokenStream stream_ASSIGN=new RewriteRuleTokenStream(adaptor,"token ASSIGN");
        RewriteRuleSubtreeStream stream_id=new RewriteRuleSubtreeStream(adaptor,"rule id");
        RewriteRuleSubtreeStream stream_atom=new RewriteRuleSubtreeStream(adaptor,"rule atom");
        RewriteRuleSubtreeStream stream_ebnfSuffix=new RewriteRuleSubtreeStream(adaptor,"rule ebnfSuffix");
        RewriteRuleSubtreeStream stream_block=new RewriteRuleSubtreeStream(adaptor,"rule block");
        RewriteRuleSubtreeStream stream_treeSpec=new RewriteRuleSubtreeStream(adaptor,"rule treeSpec");
        try {
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:346:3: ( id (labelOp= '=' | labelOp= '+=' ) atom (startEndStt= ebnfSuffix -> ^( ebnfSuffix ^( BLOCK[$startEndStt.tree.getToken()] ^( ALT[$startEndStt.tree.getToken()] ^( $labelOp id atom ) EOA[$startEndStt.tree.getToken()] ) EOB[$startEndStt.tree.getToken()] ) ) | -> ^( $labelOp id atom ) ) | id (labelOp= '=' | labelOp= '+=' ) block (startEndStt= ebnfSuffix -> ^( ebnfSuffix ^( BLOCK[$startEndStt.tree.getToken()] ^( ALT[$startEndStt.tree.getToken()] ^( $labelOp id block ) EOA[$startEndStt.tree.getToken()] ) EOB[$startEndStt.tree.getToken()] ) ) | -> ^( $labelOp id block ) ) | atom (startEndStt= ebnfSuffix -> ^( ebnfSuffix ^( BLOCK[$startEndStt.tree.getToken()] ^( ALT[$startEndStt.tree.getToken()] atom EOA[$startEndStt.tree.getToken()] ) EOB[$startEndStt.tree.getToken()] ) ) | -> atom ) | ebnf | ACTION | s= SEMPRED ( '=>' -> GATED_SEMPRED[$s] | -> $s) | treeSpec (startEndStt= ebnfSuffix -> ^( ebnfSuffix ^( BLOCK[$startEndStt.tree.getToken()] ^( ALT[$startEndStt.tree.getToken()] treeSpec EOA[$startEndStt.tree.getToken()] ) EOB[$startEndStt.tree.getToken()] ) ) | -> treeSpec ) )
            int alt48=7;
            alt48 = dfa48.predict(input);
            switch (alt48) {
                case 1 :
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:347:3: id (labelOp= '=' | labelOp= '+=' ) atom (startEndStt= ebnfSuffix -> ^( ebnfSuffix ^( BLOCK[$startEndStt.tree.getToken()] ^( ALT[$startEndStt.tree.getToken()] ^( $labelOp id atom ) EOA[$startEndStt.tree.getToken()] ) EOB[$startEndStt.tree.getToken()] ) ) | -> ^( $labelOp id atom ) )
                    {
                    pushFollow(FOLLOW_id_in_elementNoOptionSpec1780);
                    id97=id();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_id.add(id97.getTree());
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:348:3: (labelOp= '=' | labelOp= '+=' )
                    int alt41=2;
                    int LA41_0 = input.LA(1);

                    if ( (LA41_0==ASSIGN) ) {
                        alt41=1;
                    }
                    else if ( (LA41_0==89) ) {
                        alt41=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 41, 0, input);

                        throw nvae;
                    }
                    switch (alt41) {
                        case 1 :
                            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:349:5: labelOp= '='
                            {
                            labelOp=(Token)match(input,ASSIGN,FOLLOW_ASSIGN_in_elementNoOptionSpec1792); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_ASSIGN.add(labelOp);


                            }
                            break;
                        case 2 :
                            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:350:7: labelOp= '+='
                            {
                            labelOp=(Token)match(input,89,FOLLOW_89_in_elementNoOptionSpec1802); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_89.add(labelOp);


                            }
                            break;

                    }

                    pushFollow(FOLLOW_atom_in_elementNoOptionSpec1810);
                    atom98=atom();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_atom.add(atom98.getTree());
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:353:3: (startEndStt= ebnfSuffix -> ^( ebnfSuffix ^( BLOCK[$startEndStt.tree.getToken()] ^( ALT[$startEndStt.tree.getToken()] ^( $labelOp id atom ) EOA[$startEndStt.tree.getToken()] ) EOB[$startEndStt.tree.getToken()] ) ) | -> ^( $labelOp id atom ) )
                    int alt42=2;
                    int LA42_0 = input.LA(1);

                    if ( (LA42_0==78||(LA42_0>=92 && LA42_0<=93)) ) {
                        alt42=1;
                    }
                    else if ( (LA42_0==EOF||LA42_0==DOC_COMMENT||LA42_0==SEMPRED||(LA42_0>=FRAGMENT && LA42_0<=TREE_BEGIN)||(LA42_0>=REWRITE && LA42_0<=SEMI)||LA42_0==DOT||(LA42_0>=TOKEN_REF && LA42_0<=ACTION)||LA42_0==RULE_REF||(LA42_0>=80 && LA42_0<=82)||(LA42_0>=84 && LA42_0<=88)||LA42_0==91) ) {
                        alt42=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 42, 0, input);

                        throw nvae;
                    }
                    switch (alt42) {
                        case 1 :
                            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:354:5: startEndStt= ebnfSuffix
                            {
                            pushFollow(FOLLOW_ebnfSuffix_in_elementNoOptionSpec1822);
                            startEndStt=ebnfSuffix();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_ebnfSuffix.add(startEndStt.getTree());


                            // AST REWRITE
                            // elements: atom, ebnfSuffix, id, labelOp
                            // token labels: labelOp
                            // rule labels: retval
                            // token list labels: 
                            // rule list labels: 
                            // wildcard labels: 
                            if ( state.backtracking==0 ) {
                            retval.tree = root_0;
                            RewriteRuleTokenStream stream_labelOp=new RewriteRuleTokenStream(adaptor,"token labelOp",labelOp);
                            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                            root_0 = (CommonTree)adaptor.nil();
                            // 355:7: -> ^( ebnfSuffix ^( BLOCK[$startEndStt.tree.getToken()] ^( ALT[$startEndStt.tree.getToken()] ^( $labelOp id atom ) EOA[$startEndStt.tree.getToken()] ) EOB[$startEndStt.tree.getToken()] ) )
                            {
                                // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:356:9: ^( ebnfSuffix ^( BLOCK[$startEndStt.tree.getToken()] ^( ALT[$startEndStt.tree.getToken()] ^( $labelOp id atom ) EOA[$startEndStt.tree.getToken()] ) EOB[$startEndStt.tree.getToken()] ) )
                                {
                                CommonTree root_1 = (CommonTree)adaptor.nil();
                                root_1 = (CommonTree)adaptor.becomeRoot(stream_ebnfSuffix.nextNode(), root_1);

                                // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:358:11: ^( BLOCK[$startEndStt.tree.getToken()] ^( ALT[$startEndStt.tree.getToken()] ^( $labelOp id atom ) EOA[$startEndStt.tree.getToken()] ) EOB[$startEndStt.tree.getToken()] )
                                {
                                CommonTree root_2 = (CommonTree)adaptor.nil();
                                root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(BLOCK, (startEndStt!=null?((CommonTree)startEndStt.tree):null).getToken()), root_2);

                                // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:360:13: ^( ALT[$startEndStt.tree.getToken()] ^( $labelOp id atom ) EOA[$startEndStt.tree.getToken()] )
                                {
                                CommonTree root_3 = (CommonTree)adaptor.nil();
                                root_3 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ALT, (startEndStt!=null?((CommonTree)startEndStt.tree):null).getToken()), root_3);

                                // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:362:15: ^( $labelOp id atom )
                                {
                                CommonTree root_4 = (CommonTree)adaptor.nil();
                                root_4 = (CommonTree)adaptor.becomeRoot(stream_labelOp.nextNode(), root_4);

                                adaptor.addChild(root_4, stream_id.nextTree());
                                adaptor.addChild(root_4, stream_atom.nextTree());

                                adaptor.addChild(root_3, root_4);
                                }
                                adaptor.addChild(root_3, (CommonTree)adaptor.create(EOA, (startEndStt!=null?((CommonTree)startEndStt.tree):null).getToken()));

                                adaptor.addChild(root_2, root_3);
                                }
                                adaptor.addChild(root_2, (CommonTree)adaptor.create(EOB, (startEndStt!=null?((CommonTree)startEndStt.tree):null).getToken()));

                                adaptor.addChild(root_1, root_2);
                                }

                                adaptor.addChild(root_0, root_1);
                                }

                            }

                            retval.tree = root_0;}
                            }
                            break;
                        case 2 :
                            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:369:7: 
                            {

                            // AST REWRITE
                            // elements: labelOp, atom, id
                            // token labels: labelOp
                            // rule labels: retval
                            // token list labels: 
                            // rule list labels: 
                            // wildcard labels: 
                            if ( state.backtracking==0 ) {
                            retval.tree = root_0;
                            RewriteRuleTokenStream stream_labelOp=new RewriteRuleTokenStream(adaptor,"token labelOp",labelOp);
                            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                            root_0 = (CommonTree)adaptor.nil();
                            // 369:7: -> ^( $labelOp id atom )
                            {
                                // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:370:9: ^( $labelOp id atom )
                                {
                                CommonTree root_1 = (CommonTree)adaptor.nil();
                                root_1 = (CommonTree)adaptor.becomeRoot(stream_labelOp.nextNode(), root_1);

                                adaptor.addChild(root_1, stream_id.nextTree());
                                adaptor.addChild(root_1, stream_atom.nextTree());

                                adaptor.addChild(root_0, root_1);
                                }

                            }

                            retval.tree = root_0;}
                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:372:5: id (labelOp= '=' | labelOp= '+=' ) block (startEndStt= ebnfSuffix -> ^( ebnfSuffix ^( BLOCK[$startEndStt.tree.getToken()] ^( ALT[$startEndStt.tree.getToken()] ^( $labelOp id block ) EOA[$startEndStt.tree.getToken()] ) EOB[$startEndStt.tree.getToken()] ) ) | -> ^( $labelOp id block ) )
                    {
                    pushFollow(FOLLOW_id_in_elementNoOptionSpec2045);
                    id99=id();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_id.add(id99.getTree());
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:373:3: (labelOp= '=' | labelOp= '+=' )
                    int alt43=2;
                    int LA43_0 = input.LA(1);

                    if ( (LA43_0==ASSIGN) ) {
                        alt43=1;
                    }
                    else if ( (LA43_0==89) ) {
                        alt43=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 43, 0, input);

                        throw nvae;
                    }
                    switch (alt43) {
                        case 1 :
                            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:374:5: labelOp= '='
                            {
                            labelOp=(Token)match(input,ASSIGN,FOLLOW_ASSIGN_in_elementNoOptionSpec2057); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_ASSIGN.add(labelOp);


                            }
                            break;
                        case 2 :
                            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:375:7: labelOp= '+='
                            {
                            labelOp=(Token)match(input,89,FOLLOW_89_in_elementNoOptionSpec2067); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_89.add(labelOp);


                            }
                            break;

                    }

                    pushFollow(FOLLOW_block_in_elementNoOptionSpec2075);
                    block100=block();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_block.add(block100.getTree());
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:378:3: (startEndStt= ebnfSuffix -> ^( ebnfSuffix ^( BLOCK[$startEndStt.tree.getToken()] ^( ALT[$startEndStt.tree.getToken()] ^( $labelOp id block ) EOA[$startEndStt.tree.getToken()] ) EOB[$startEndStt.tree.getToken()] ) ) | -> ^( $labelOp id block ) )
                    int alt44=2;
                    int LA44_0 = input.LA(1);

                    if ( (LA44_0==78||(LA44_0>=92 && LA44_0<=93)) ) {
                        alt44=1;
                    }
                    else if ( (LA44_0==EOF||LA44_0==DOC_COMMENT||LA44_0==SEMPRED||(LA44_0>=FRAGMENT && LA44_0<=TREE_BEGIN)||(LA44_0>=REWRITE && LA44_0<=SEMI)||LA44_0==DOT||(LA44_0>=TOKEN_REF && LA44_0<=ACTION)||LA44_0==RULE_REF||(LA44_0>=80 && LA44_0<=82)||(LA44_0>=84 && LA44_0<=88)||LA44_0==91) ) {
                        alt44=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 44, 0, input);

                        throw nvae;
                    }
                    switch (alt44) {
                        case 1 :
                            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:379:5: startEndStt= ebnfSuffix
                            {
                            pushFollow(FOLLOW_ebnfSuffix_in_elementNoOptionSpec2087);
                            startEndStt=ebnfSuffix();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_ebnfSuffix.add(startEndStt.getTree());


                            // AST REWRITE
                            // elements: labelOp, block, ebnfSuffix, id
                            // token labels: labelOp
                            // rule labels: retval
                            // token list labels: 
                            // rule list labels: 
                            // wildcard labels: 
                            if ( state.backtracking==0 ) {
                            retval.tree = root_0;
                            RewriteRuleTokenStream stream_labelOp=new RewriteRuleTokenStream(adaptor,"token labelOp",labelOp);
                            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                            root_0 = (CommonTree)adaptor.nil();
                            // 380:7: -> ^( ebnfSuffix ^( BLOCK[$startEndStt.tree.getToken()] ^( ALT[$startEndStt.tree.getToken()] ^( $labelOp id block ) EOA[$startEndStt.tree.getToken()] ) EOB[$startEndStt.tree.getToken()] ) )
                            {
                                // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:381:9: ^( ebnfSuffix ^( BLOCK[$startEndStt.tree.getToken()] ^( ALT[$startEndStt.tree.getToken()] ^( $labelOp id block ) EOA[$startEndStt.tree.getToken()] ) EOB[$startEndStt.tree.getToken()] ) )
                                {
                                CommonTree root_1 = (CommonTree)adaptor.nil();
                                root_1 = (CommonTree)adaptor.becomeRoot(stream_ebnfSuffix.nextNode(), root_1);

                                // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:383:11: ^( BLOCK[$startEndStt.tree.getToken()] ^( ALT[$startEndStt.tree.getToken()] ^( $labelOp id block ) EOA[$startEndStt.tree.getToken()] ) EOB[$startEndStt.tree.getToken()] )
                                {
                                CommonTree root_2 = (CommonTree)adaptor.nil();
                                root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(BLOCK, (startEndStt!=null?((CommonTree)startEndStt.tree):null).getToken()), root_2);

                                // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:385:13: ^( ALT[$startEndStt.tree.getToken()] ^( $labelOp id block ) EOA[$startEndStt.tree.getToken()] )
                                {
                                CommonTree root_3 = (CommonTree)adaptor.nil();
                                root_3 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ALT, (startEndStt!=null?((CommonTree)startEndStt.tree):null).getToken()), root_3);

                                // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:387:15: ^( $labelOp id block )
                                {
                                CommonTree root_4 = (CommonTree)adaptor.nil();
                                root_4 = (CommonTree)adaptor.becomeRoot(stream_labelOp.nextNode(), root_4);

                                adaptor.addChild(root_4, stream_id.nextTree());
                                adaptor.addChild(root_4, stream_block.nextTree());

                                adaptor.addChild(root_3, root_4);
                                }
                                adaptor.addChild(root_3, (CommonTree)adaptor.create(EOA, (startEndStt!=null?((CommonTree)startEndStt.tree):null).getToken()));

                                adaptor.addChild(root_2, root_3);
                                }
                                adaptor.addChild(root_2, (CommonTree)adaptor.create(EOB, (startEndStt!=null?((CommonTree)startEndStt.tree):null).getToken()));

                                adaptor.addChild(root_1, root_2);
                                }

                                adaptor.addChild(root_0, root_1);
                                }

                            }

                            retval.tree = root_0;}
                            }
                            break;
                        case 2 :
                            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:394:7: 
                            {

                            // AST REWRITE
                            // elements: labelOp, block, id
                            // token labels: labelOp
                            // rule labels: retval
                            // token list labels: 
                            // rule list labels: 
                            // wildcard labels: 
                            if ( state.backtracking==0 ) {
                            retval.tree = root_0;
                            RewriteRuleTokenStream stream_labelOp=new RewriteRuleTokenStream(adaptor,"token labelOp",labelOp);
                            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                            root_0 = (CommonTree)adaptor.nil();
                            // 394:7: -> ^( $labelOp id block )
                            {
                                // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:395:9: ^( $labelOp id block )
                                {
                                CommonTree root_1 = (CommonTree)adaptor.nil();
                                root_1 = (CommonTree)adaptor.becomeRoot(stream_labelOp.nextNode(), root_1);

                                adaptor.addChild(root_1, stream_id.nextTree());
                                adaptor.addChild(root_1, stream_block.nextTree());

                                adaptor.addChild(root_0, root_1);
                                }

                            }

                            retval.tree = root_0;}
                            }
                            break;

                    }


                    }
                    break;
                case 3 :
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:397:5: atom (startEndStt= ebnfSuffix -> ^( ebnfSuffix ^( BLOCK[$startEndStt.tree.getToken()] ^( ALT[$startEndStt.tree.getToken()] atom EOA[$startEndStt.tree.getToken()] ) EOB[$startEndStt.tree.getToken()] ) ) | -> atom )
                    {
                    pushFollow(FOLLOW_atom_in_elementNoOptionSpec2310);
                    atom101=atom();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_atom.add(atom101.getTree());
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:398:3: (startEndStt= ebnfSuffix -> ^( ebnfSuffix ^( BLOCK[$startEndStt.tree.getToken()] ^( ALT[$startEndStt.tree.getToken()] atom EOA[$startEndStt.tree.getToken()] ) EOB[$startEndStt.tree.getToken()] ) ) | -> atom )
                    int alt45=2;
                    int LA45_0 = input.LA(1);

                    if ( (LA45_0==78||(LA45_0>=92 && LA45_0<=93)) ) {
                        alt45=1;
                    }
                    else if ( (LA45_0==EOF||LA45_0==DOC_COMMENT||LA45_0==SEMPRED||(LA45_0>=FRAGMENT && LA45_0<=TREE_BEGIN)||(LA45_0>=REWRITE && LA45_0<=SEMI)||LA45_0==DOT||(LA45_0>=TOKEN_REF && LA45_0<=ACTION)||LA45_0==RULE_REF||(LA45_0>=80 && LA45_0<=82)||(LA45_0>=84 && LA45_0<=88)||LA45_0==91) ) {
                        alt45=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 45, 0, input);

                        throw nvae;
                    }
                    switch (alt45) {
                        case 1 :
                            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:399:5: startEndStt= ebnfSuffix
                            {
                            pushFollow(FOLLOW_ebnfSuffix_in_elementNoOptionSpec2322);
                            startEndStt=ebnfSuffix();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_ebnfSuffix.add(startEndStt.getTree());


                            // AST REWRITE
                            // elements: ebnfSuffix, atom
                            // token labels: 
                            // rule labels: retval
                            // token list labels: 
                            // rule list labels: 
                            // wildcard labels: 
                            if ( state.backtracking==0 ) {
                            retval.tree = root_0;
                            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                            root_0 = (CommonTree)adaptor.nil();
                            // 400:7: -> ^( ebnfSuffix ^( BLOCK[$startEndStt.tree.getToken()] ^( ALT[$startEndStt.tree.getToken()] atom EOA[$startEndStt.tree.getToken()] ) EOB[$startEndStt.tree.getToken()] ) )
                            {
                                // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:401:9: ^( ebnfSuffix ^( BLOCK[$startEndStt.tree.getToken()] ^( ALT[$startEndStt.tree.getToken()] atom EOA[$startEndStt.tree.getToken()] ) EOB[$startEndStt.tree.getToken()] ) )
                                {
                                CommonTree root_1 = (CommonTree)adaptor.nil();
                                root_1 = (CommonTree)adaptor.becomeRoot(stream_ebnfSuffix.nextNode(), root_1);

                                // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:403:11: ^( BLOCK[$startEndStt.tree.getToken()] ^( ALT[$startEndStt.tree.getToken()] atom EOA[$startEndStt.tree.getToken()] ) EOB[$startEndStt.tree.getToken()] )
                                {
                                CommonTree root_2 = (CommonTree)adaptor.nil();
                                root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(BLOCK, (startEndStt!=null?((CommonTree)startEndStt.tree):null).getToken()), root_2);

                                // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:405:13: ^( ALT[$startEndStt.tree.getToken()] atom EOA[$startEndStt.tree.getToken()] )
                                {
                                CommonTree root_3 = (CommonTree)adaptor.nil();
                                root_3 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ALT, (startEndStt!=null?((CommonTree)startEndStt.tree):null).getToken()), root_3);

                                adaptor.addChild(root_3, stream_atom.nextTree());
                                adaptor.addChild(root_3, (CommonTree)adaptor.create(EOA, (startEndStt!=null?((CommonTree)startEndStt.tree):null).getToken()));

                                adaptor.addChild(root_2, root_3);
                                }
                                adaptor.addChild(root_2, (CommonTree)adaptor.create(EOB, (startEndStt!=null?((CommonTree)startEndStt.tree):null).getToken()));

                                adaptor.addChild(root_1, root_2);
                                }

                                adaptor.addChild(root_0, root_1);
                                }

                            }

                            retval.tree = root_0;}
                            }
                            break;
                        case 2 :
                            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:410:7: 
                            {

                            // AST REWRITE
                            // elements: atom
                            // token labels: 
                            // rule labels: retval
                            // token list labels: 
                            // rule list labels: 
                            // wildcard labels: 
                            if ( state.backtracking==0 ) {
                            retval.tree = root_0;
                            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                            root_0 = (CommonTree)adaptor.nil();
                            // 410:7: -> atom
                            {
                                adaptor.addChild(root_0, stream_atom.nextTree());

                            }

                            retval.tree = root_0;}
                            }
                            break;

                    }


                    }
                    break;
                case 4 :
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:412:5: ebnf
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_ebnf_in_elementNoOptionSpec2466);
                    ebnf102=ebnf();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, ebnf102.getTree());

                    }
                    break;
                case 5 :
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:413:5: ACTION
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    ACTION103=(Token)match(input,ACTION,FOLLOW_ACTION_in_elementNoOptionSpec2472); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    ACTION103_tree = (CommonTree)adaptor.create(ACTION103);
                    adaptor.addChild(root_0, ACTION103_tree);
                    }

                    }
                    break;
                case 6 :
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:414:5: s= SEMPRED ( '=>' -> GATED_SEMPRED[$s] | -> $s)
                    {
                    s=(Token)match(input,SEMPRED,FOLLOW_SEMPRED_in_elementNoOptionSpec2480); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_SEMPRED.add(s);

                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:415:3: ( '=>' -> GATED_SEMPRED[$s] | -> $s)
                    int alt46=2;
                    int LA46_0 = input.LA(1);

                    if ( (LA46_0==90) ) {
                        alt46=1;
                    }
                    else if ( (LA46_0==EOF||LA46_0==DOC_COMMENT||LA46_0==SEMPRED||(LA46_0>=FRAGMENT && LA46_0<=TREE_BEGIN)||(LA46_0>=REWRITE && LA46_0<=SEMI)||LA46_0==DOT||(LA46_0>=TOKEN_REF && LA46_0<=ACTION)||LA46_0==RULE_REF||(LA46_0>=80 && LA46_0<=82)||(LA46_0>=84 && LA46_0<=88)||LA46_0==91) ) {
                        alt46=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 46, 0, input);

                        throw nvae;
                    }
                    switch (alt46) {
                        case 1 :
                            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:416:5: '=>'
                            {
                            string_literal104=(Token)match(input,90,FOLLOW_90_in_elementNoOptionSpec2490); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_90.add(string_literal104);



                            // AST REWRITE
                            // elements: 
                            // token labels: 
                            // rule labels: retval
                            // token list labels: 
                            // rule list labels: 
                            // wildcard labels: 
                            if ( state.backtracking==0 ) {
                            retval.tree = root_0;
                            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                            root_0 = (CommonTree)adaptor.nil();
                            // 417:7: -> GATED_SEMPRED[$s]
                            {
                                adaptor.addChild(root_0, (CommonTree)adaptor.create(GATED_SEMPRED, s));

                            }

                            retval.tree = root_0;}
                            }
                            break;
                        case 2 :
                            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:419:7: 
                            {

                            // AST REWRITE
                            // elements: s
                            // token labels: s
                            // rule labels: retval
                            // token list labels: 
                            // rule list labels: 
                            // wildcard labels: 
                            if ( state.backtracking==0 ) {
                            retval.tree = root_0;
                            RewriteRuleTokenStream stream_s=new RewriteRuleTokenStream(adaptor,"token s",s);
                            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                            root_0 = (CommonTree)adaptor.nil();
                            // 419:7: -> $s
                            {
                                adaptor.addChild(root_0, stream_s.nextNode());

                            }

                            retval.tree = root_0;}
                            }
                            break;

                    }


                    }
                    break;
                case 7 :
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:421:5: treeSpec (startEndStt= ebnfSuffix -> ^( ebnfSuffix ^( BLOCK[$startEndStt.tree.getToken()] ^( ALT[$startEndStt.tree.getToken()] treeSpec EOA[$startEndStt.tree.getToken()] ) EOB[$startEndStt.tree.getToken()] ) ) | -> treeSpec )
                    {
                    pushFollow(FOLLOW_treeSpec_in_elementNoOptionSpec2528);
                    treeSpec105=treeSpec();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_treeSpec.add(treeSpec105.getTree());
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:422:3: (startEndStt= ebnfSuffix -> ^( ebnfSuffix ^( BLOCK[$startEndStt.tree.getToken()] ^( ALT[$startEndStt.tree.getToken()] treeSpec EOA[$startEndStt.tree.getToken()] ) EOB[$startEndStt.tree.getToken()] ) ) | -> treeSpec )
                    int alt47=2;
                    int LA47_0 = input.LA(1);

                    if ( (LA47_0==78||(LA47_0>=92 && LA47_0<=93)) ) {
                        alt47=1;
                    }
                    else if ( (LA47_0==EOF||LA47_0==DOC_COMMENT||LA47_0==SEMPRED||(LA47_0>=FRAGMENT && LA47_0<=TREE_BEGIN)||(LA47_0>=REWRITE && LA47_0<=SEMI)||LA47_0==DOT||(LA47_0>=TOKEN_REF && LA47_0<=ACTION)||LA47_0==RULE_REF||(LA47_0>=80 && LA47_0<=82)||(LA47_0>=84 && LA47_0<=88)||LA47_0==91) ) {
                        alt47=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 47, 0, input);

                        throw nvae;
                    }
                    switch (alt47) {
                        case 1 :
                            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:423:5: startEndStt= ebnfSuffix
                            {
                            pushFollow(FOLLOW_ebnfSuffix_in_elementNoOptionSpec2540);
                            startEndStt=ebnfSuffix();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_ebnfSuffix.add(startEndStt.getTree());


                            // AST REWRITE
                            // elements: treeSpec, ebnfSuffix
                            // token labels: 
                            // rule labels: retval
                            // token list labels: 
                            // rule list labels: 
                            // wildcard labels: 
                            if ( state.backtracking==0 ) {
                            retval.tree = root_0;
                            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                            root_0 = (CommonTree)adaptor.nil();
                            // 424:7: -> ^( ebnfSuffix ^( BLOCK[$startEndStt.tree.getToken()] ^( ALT[$startEndStt.tree.getToken()] treeSpec EOA[$startEndStt.tree.getToken()] ) EOB[$startEndStt.tree.getToken()] ) )
                            {
                                // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:425:9: ^( ebnfSuffix ^( BLOCK[$startEndStt.tree.getToken()] ^( ALT[$startEndStt.tree.getToken()] treeSpec EOA[$startEndStt.tree.getToken()] ) EOB[$startEndStt.tree.getToken()] ) )
                                {
                                CommonTree root_1 = (CommonTree)adaptor.nil();
                                root_1 = (CommonTree)adaptor.becomeRoot(stream_ebnfSuffix.nextNode(), root_1);

                                // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:427:11: ^( BLOCK[$startEndStt.tree.getToken()] ^( ALT[$startEndStt.tree.getToken()] treeSpec EOA[$startEndStt.tree.getToken()] ) EOB[$startEndStt.tree.getToken()] )
                                {
                                CommonTree root_2 = (CommonTree)adaptor.nil();
                                root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(BLOCK, (startEndStt!=null?((CommonTree)startEndStt.tree):null).getToken()), root_2);

                                // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:429:13: ^( ALT[$startEndStt.tree.getToken()] treeSpec EOA[$startEndStt.tree.getToken()] )
                                {
                                CommonTree root_3 = (CommonTree)adaptor.nil();
                                root_3 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ALT, (startEndStt!=null?((CommonTree)startEndStt.tree):null).getToken()), root_3);

                                adaptor.addChild(root_3, stream_treeSpec.nextTree());
                                adaptor.addChild(root_3, (CommonTree)adaptor.create(EOA, (startEndStt!=null?((CommonTree)startEndStt.tree):null).getToken()));

                                adaptor.addChild(root_2, root_3);
                                }
                                adaptor.addChild(root_2, (CommonTree)adaptor.create(EOB, (startEndStt!=null?((CommonTree)startEndStt.tree):null).getToken()));

                                adaptor.addChild(root_1, root_2);
                                }

                                adaptor.addChild(root_0, root_1);
                                }

                            }

                            retval.tree = root_0;}
                            }
                            break;
                        case 2 :
                            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:434:7: 
                            {

                            // AST REWRITE
                            // elements: treeSpec
                            // token labels: 
                            // rule labels: retval
                            // token list labels: 
                            // rule list labels: 
                            // wildcard labels: 
                            if ( state.backtracking==0 ) {
                            retval.tree = root_0;
                            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                            root_0 = (CommonTree)adaptor.nil();
                            // 434:7: -> treeSpec
                            {
                                adaptor.addChild(root_0, stream_treeSpec.nextTree());

                            }

                            retval.tree = root_0;}
                            }
                            break;

                    }


                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "elementNoOptionSpec"

    public static class atom_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "atom"
    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:438:1: atom : ( range ( (op= '^' | op= '!' ) -> ^( $op range ) | -> range ) | terminal | notSet ( (op= '^' | op= '!' ) -> ^( $op notSet ) | -> notSet ) | call (op= '^' | op= '!' )? -> {op!=null}? ^( $op call ) -> call );
    public final ANTLRParser.atom_return atom() throws RecognitionException {
        ANTLRParser.atom_return retval = new ANTLRParser.atom_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token op=null;
        ANTLRParser.range_return range106 = null;

        ANTLRParser.terminal_return terminal107 = null;

        ANTLRParser.notSet_return notSet108 = null;

        ANTLRParser.call_return call109 = null;


        CommonTree op_tree=null;
        RewriteRuleTokenStream stream_BANG=new RewriteRuleTokenStream(adaptor,"token BANG");
        RewriteRuleTokenStream stream_ROOT=new RewriteRuleTokenStream(adaptor,"token ROOT");
        RewriteRuleSubtreeStream stream_call=new RewriteRuleSubtreeStream(adaptor,"rule call");
        RewriteRuleSubtreeStream stream_range=new RewriteRuleSubtreeStream(adaptor,"rule range");
        RewriteRuleSubtreeStream stream_notSet=new RewriteRuleSubtreeStream(adaptor,"rule notSet");
        try {
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:439:3: ( range ( (op= '^' | op= '!' ) -> ^( $op range ) | -> range ) | terminal | notSet ( (op= '^' | op= '!' ) -> ^( $op notSet ) | -> notSet ) | call (op= '^' | op= '!' )? -> {op!=null}? ^( $op call ) -> call )
            int alt54=4;
            switch ( input.LA(1) ) {
            case STRING_LITERAL:
                {
                int LA54_1 = input.LA(2);

                if ( (LA54_1==RANGE) ) {
                    alt54=1;
                }
                else if ( (LA54_1==EOF||LA54_1==DOC_COMMENT||LA54_1==SEMPRED||(LA54_1>=FRAGMENT && LA54_1<=SEMI)||LA54_1==DOT||(LA54_1>=TOKEN_REF && LA54_1<=ACTION)||LA54_1==RULE_REF||LA54_1==78||(LA54_1>=80 && LA54_1<=82)||(LA54_1>=84 && LA54_1<=88)||(LA54_1>=91 && LA54_1<=94)) ) {
                    alt54=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 54, 1, input);

                    throw nvae;
                }
                }
                break;
            case DOT:
            case TOKEN_REF:
                {
                alt54=2;
                }
                break;
            case 91:
                {
                alt54=3;
                }
                break;
            case RULE_REF:
                {
                alt54=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 54, 0, input);

                throw nvae;
            }

            switch (alt54) {
                case 1 :
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:440:3: range ( (op= '^' | op= '!' ) -> ^( $op range ) | -> range )
                    {
                    pushFollow(FOLLOW_range_in_atom2693);
                    range106=range();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_range.add(range106.getTree());
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:441:3: ( (op= '^' | op= '!' ) -> ^( $op range ) | -> range )
                    int alt50=2;
                    int LA50_0 = input.LA(1);

                    if ( ((LA50_0>=ROOT && LA50_0<=BANG)) ) {
                        alt50=1;
                    }
                    else if ( (LA50_0==EOF||LA50_0==DOC_COMMENT||LA50_0==SEMPRED||(LA50_0>=FRAGMENT && LA50_0<=TREE_BEGIN)||(LA50_0>=REWRITE && LA50_0<=SEMI)||LA50_0==DOT||(LA50_0>=TOKEN_REF && LA50_0<=ACTION)||LA50_0==RULE_REF||LA50_0==78||(LA50_0>=80 && LA50_0<=82)||(LA50_0>=84 && LA50_0<=88)||(LA50_0>=91 && LA50_0<=93)) ) {
                        alt50=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 50, 0, input);

                        throw nvae;
                    }
                    switch (alt50) {
                        case 1 :
                            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:442:5: (op= '^' | op= '!' )
                            {
                            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:442:5: (op= '^' | op= '!' )
                            int alt49=2;
                            int LA49_0 = input.LA(1);

                            if ( (LA49_0==ROOT) ) {
                                alt49=1;
                            }
                            else if ( (LA49_0==BANG) ) {
                                alt49=2;
                            }
                            else {
                                if (state.backtracking>0) {state.failed=true; return retval;}
                                NoViableAltException nvae =
                                    new NoViableAltException("", 49, 0, input);

                                throw nvae;
                            }
                            switch (alt49) {
                                case 1 :
                                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:443:7: op= '^'
                                    {
                                    op=(Token)match(input,ROOT,FOLLOW_ROOT_in_atom2713); if (state.failed) return retval; 
                                    if ( state.backtracking==0 ) stream_ROOT.add(op);


                                    }
                                    break;
                                case 2 :
                                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:444:9: op= '!'
                                    {
                                    op=(Token)match(input,BANG,FOLLOW_BANG_in_atom2725); if (state.failed) return retval; 
                                    if ( state.backtracking==0 ) stream_BANG.add(op);


                                    }
                                    break;

                            }



                            // AST REWRITE
                            // elements: op, range
                            // token labels: op
                            // rule labels: retval
                            // token list labels: 
                            // rule list labels: 
                            // wildcard labels: 
                            if ( state.backtracking==0 ) {
                            retval.tree = root_0;
                            RewriteRuleTokenStream stream_op=new RewriteRuleTokenStream(adaptor,"token op",op);
                            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                            root_0 = (CommonTree)adaptor.nil();
                            // 446:7: -> ^( $op range )
                            {
                                // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:447:9: ^( $op range )
                                {
                                CommonTree root_1 = (CommonTree)adaptor.nil();
                                root_1 = (CommonTree)adaptor.becomeRoot(stream_op.nextNode(), root_1);

                                adaptor.addChild(root_1, stream_range.nextTree());

                                adaptor.addChild(root_0, root_1);
                                }

                            }

                            retval.tree = root_0;}
                            }
                            break;
                        case 2 :
                            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:449:7: 
                            {

                            // AST REWRITE
                            // elements: range
                            // token labels: 
                            // rule labels: retval
                            // token list labels: 
                            // rule list labels: 
                            // wildcard labels: 
                            if ( state.backtracking==0 ) {
                            retval.tree = root_0;
                            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                            root_0 = (CommonTree)adaptor.nil();
                            // 449:7: -> range
                            {
                                adaptor.addChild(root_0, stream_range.nextTree());

                            }

                            retval.tree = root_0;}
                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:451:5: terminal
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_terminal_in_atom2780);
                    terminal107=terminal();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, terminal107.getTree());

                    }
                    break;
                case 3 :
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:452:5: notSet ( (op= '^' | op= '!' ) -> ^( $op notSet ) | -> notSet )
                    {
                    pushFollow(FOLLOW_notSet_in_atom2786);
                    notSet108=notSet();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_notSet.add(notSet108.getTree());
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:453:3: ( (op= '^' | op= '!' ) -> ^( $op notSet ) | -> notSet )
                    int alt52=2;
                    int LA52_0 = input.LA(1);

                    if ( ((LA52_0>=ROOT && LA52_0<=BANG)) ) {
                        alt52=1;
                    }
                    else if ( (LA52_0==EOF||LA52_0==DOC_COMMENT||LA52_0==SEMPRED||(LA52_0>=FRAGMENT && LA52_0<=TREE_BEGIN)||(LA52_0>=REWRITE && LA52_0<=SEMI)||LA52_0==DOT||(LA52_0>=TOKEN_REF && LA52_0<=ACTION)||LA52_0==RULE_REF||LA52_0==78||(LA52_0>=80 && LA52_0<=82)||(LA52_0>=84 && LA52_0<=88)||(LA52_0>=91 && LA52_0<=93)) ) {
                        alt52=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 52, 0, input);

                        throw nvae;
                    }
                    switch (alt52) {
                        case 1 :
                            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:454:5: (op= '^' | op= '!' )
                            {
                            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:454:5: (op= '^' | op= '!' )
                            int alt51=2;
                            int LA51_0 = input.LA(1);

                            if ( (LA51_0==ROOT) ) {
                                alt51=1;
                            }
                            else if ( (LA51_0==BANG) ) {
                                alt51=2;
                            }
                            else {
                                if (state.backtracking>0) {state.failed=true; return retval;}
                                NoViableAltException nvae =
                                    new NoViableAltException("", 51, 0, input);

                                throw nvae;
                            }
                            switch (alt51) {
                                case 1 :
                                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:455:7: op= '^'
                                    {
                                    op=(Token)match(input,ROOT,FOLLOW_ROOT_in_atom2806); if (state.failed) return retval; 
                                    if ( state.backtracking==0 ) stream_ROOT.add(op);


                                    }
                                    break;
                                case 2 :
                                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:456:9: op= '!'
                                    {
                                    op=(Token)match(input,BANG,FOLLOW_BANG_in_atom2818); if (state.failed) return retval; 
                                    if ( state.backtracking==0 ) stream_BANG.add(op);


                                    }
                                    break;

                            }



                            // AST REWRITE
                            // elements: op, notSet
                            // token labels: op
                            // rule labels: retval
                            // token list labels: 
                            // rule list labels: 
                            // wildcard labels: 
                            if ( state.backtracking==0 ) {
                            retval.tree = root_0;
                            RewriteRuleTokenStream stream_op=new RewriteRuleTokenStream(adaptor,"token op",op);
                            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                            root_0 = (CommonTree)adaptor.nil();
                            // 458:7: -> ^( $op notSet )
                            {
                                // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:459:9: ^( $op notSet )
                                {
                                CommonTree root_1 = (CommonTree)adaptor.nil();
                                root_1 = (CommonTree)adaptor.becomeRoot(stream_op.nextNode(), root_1);

                                adaptor.addChild(root_1, stream_notSet.nextTree());

                                adaptor.addChild(root_0, root_1);
                                }

                            }

                            retval.tree = root_0;}
                            }
                            break;
                        case 2 :
                            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:461:7: 
                            {

                            // AST REWRITE
                            // elements: notSet
                            // token labels: 
                            // rule labels: retval
                            // token list labels: 
                            // rule list labels: 
                            // wildcard labels: 
                            if ( state.backtracking==0 ) {
                            retval.tree = root_0;
                            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                            root_0 = (CommonTree)adaptor.nil();
                            // 461:7: -> notSet
                            {
                                adaptor.addChild(root_0, stream_notSet.nextTree());

                            }

                            retval.tree = root_0;}
                            }
                            break;

                    }


                    }
                    break;
                case 4 :
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:463:5: call (op= '^' | op= '!' )?
                    {
                    pushFollow(FOLLOW_call_in_atom2873);
                    call109=call();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_call.add(call109.getTree());
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:464:3: (op= '^' | op= '!' )?
                    int alt53=3;
                    int LA53_0 = input.LA(1);

                    if ( (LA53_0==ROOT) ) {
                        alt53=1;
                    }
                    else if ( (LA53_0==BANG) ) {
                        alt53=2;
                    }
                    switch (alt53) {
                        case 1 :
                            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:465:5: op= '^'
                            {
                            op=(Token)match(input,ROOT,FOLLOW_ROOT_in_atom2885); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_ROOT.add(op);


                            }
                            break;
                        case 2 :
                            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:466:7: op= '!'
                            {
                            op=(Token)match(input,BANG,FOLLOW_BANG_in_atom2895); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_BANG.add(op);


                            }
                            break;

                    }



                    // AST REWRITE
                    // elements: op, call, call
                    // token labels: op
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleTokenStream stream_op=new RewriteRuleTokenStream(adaptor,"token op",op);
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 468:5: -> {op!=null}? ^( $op call )
                    if (op!=null) {
                        // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:469:7: ^( $op call )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(stream_op.nextNode(), root_1);

                        adaptor.addChild(root_1, stream_call.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }
                    else // 470:5: -> call
                    {
                        adaptor.addChild(root_0, stream_call.nextTree());

                    }

                    retval.tree = root_0;}
                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "atom"

    public static class call_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "call"
    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:473:1: call : RULE_REF ( ARG_ACTION )? ;
    public final ANTLRParser.call_return call() throws RecognitionException {
        ANTLRParser.call_return retval = new ANTLRParser.call_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token RULE_REF110=null;
        Token ARG_ACTION111=null;

        CommonTree RULE_REF110_tree=null;
        CommonTree ARG_ACTION111_tree=null;

        try {
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:474:3: ( RULE_REF ( ARG_ACTION )? )
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:475:3: RULE_REF ( ARG_ACTION )?
            {
            root_0 = (CommonTree)adaptor.nil();

            RULE_REF110=(Token)match(input,RULE_REF,FOLLOW_RULE_REF_in_call2944); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            RULE_REF110_tree = (CommonTree)adaptor.create(RULE_REF110);
            adaptor.addChild(root_0, RULE_REF110_tree);
            }
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:475:12: ( ARG_ACTION )?
            int alt55=2;
            int LA55_0 = input.LA(1);

            if ( (LA55_0==ARG_ACTION) ) {
                alt55=1;
            }
            switch (alt55) {
                case 1 :
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:475:12: ARG_ACTION
                    {
                    ARG_ACTION111=(Token)match(input,ARG_ACTION,FOLLOW_ARG_ACTION_in_call2946); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    ARG_ACTION111_tree = (CommonTree)adaptor.create(ARG_ACTION111);
                    adaptor.addChild(root_0, ARG_ACTION111_tree);
                    }

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "call"

    public static class notSet_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "notSet"
    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:478:1: notSet : '~' ( notTerminal -> ^( '~' notTerminal ) | block -> ^( '~' block ) ) ;
    public final ANTLRParser.notSet_return notSet() throws RecognitionException {
        ANTLRParser.notSet_return retval = new ANTLRParser.notSet_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token char_literal112=null;
        ANTLRParser.notTerminal_return notTerminal113 = null;

        ANTLRParser.block_return block114 = null;


        CommonTree char_literal112_tree=null;
        RewriteRuleTokenStream stream_91=new RewriteRuleTokenStream(adaptor,"token 91");
        RewriteRuleSubtreeStream stream_notTerminal=new RewriteRuleSubtreeStream(adaptor,"rule notTerminal");
        RewriteRuleSubtreeStream stream_block=new RewriteRuleSubtreeStream(adaptor,"rule block");
        try {
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:479:3: ( '~' ( notTerminal -> ^( '~' notTerminal ) | block -> ^( '~' block ) ) )
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:480:3: '~' ( notTerminal -> ^( '~' notTerminal ) | block -> ^( '~' block ) )
            {
            char_literal112=(Token)match(input,91,FOLLOW_91_in_notSet2962); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_91.add(char_literal112);

            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:481:3: ( notTerminal -> ^( '~' notTerminal ) | block -> ^( '~' block ) )
            int alt56=2;
            int LA56_0 = input.LA(1);

            if ( ((LA56_0>=TOKEN_REF && LA56_0<=STRING_LITERAL)) ) {
                alt56=1;
            }
            else if ( (LA56_0==84) ) {
                alt56=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 56, 0, input);

                throw nvae;
            }
            switch (alt56) {
                case 1 :
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:482:5: notTerminal
                    {
                    pushFollow(FOLLOW_notTerminal_in_notSet2972);
                    notTerminal113=notTerminal();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_notTerminal.add(notTerminal113.getTree());


                    // AST REWRITE
                    // elements: 91, notTerminal
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 483:7: -> ^( '~' notTerminal )
                    {
                        // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:484:9: ^( '~' notTerminal )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(stream_91.nextNode(), root_1);

                        adaptor.addChild(root_1, stream_notTerminal.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 2 :
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:485:7: block
                    {
                    pushFollow(FOLLOW_block_in_notSet3002);
                    block114=block();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_block.add(block114.getTree());


                    // AST REWRITE
                    // elements: block, 91
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 486:7: -> ^( '~' block )
                    {
                        // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:487:9: ^( '~' block )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(stream_91.nextNode(), root_1);

                        adaptor.addChild(root_1, stream_block.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "notSet"

    public static class treeSpec_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "treeSpec"
    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:491:1: treeSpec : tb= '^(' element ( element )+ ')' -> ^( TREE_BEGIN[tb] ( element )+ ) ;
    public final ANTLRParser.treeSpec_return treeSpec() throws RecognitionException {
        ANTLRParser.treeSpec_return retval = new ANTLRParser.treeSpec_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token tb=null;
        Token char_literal117=null;
        ANTLRParser.element_return element115 = null;

        ANTLRParser.element_return element116 = null;


        CommonTree tb_tree=null;
        CommonTree char_literal117_tree=null;
        RewriteRuleTokenStream stream_TREE_BEGIN=new RewriteRuleTokenStream(adaptor,"token TREE_BEGIN");
        RewriteRuleTokenStream stream_86=new RewriteRuleTokenStream(adaptor,"token 86");
        RewriteRuleSubtreeStream stream_element=new RewriteRuleSubtreeStream(adaptor,"rule element");
        try {
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:492:3: (tb= '^(' element ( element )+ ')' -> ^( TREE_BEGIN[tb] ( element )+ ) )
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:493:3: tb= '^(' element ( element )+ ')'
            {
            tb=(Token)match(input,TREE_BEGIN,FOLLOW_TREE_BEGIN_in_treeSpec3045); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_TREE_BEGIN.add(tb);

            pushFollow(FOLLOW_element_in_treeSpec3047);
            element115=element();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_element.add(element115.getTree());
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:493:19: ( element )+
            int cnt57=0;
            loop57:
            do {
                int alt57=2;
                int LA57_0 = input.LA(1);

                if ( (LA57_0==SEMPRED||LA57_0==TREE_BEGIN||LA57_0==DOT||(LA57_0>=TOKEN_REF && LA57_0<=ACTION)||LA57_0==RULE_REF||LA57_0==84||LA57_0==91) ) {
                    alt57=1;
                }


                switch (alt57) {
            	case 1 :
            	    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:493:20: element
            	    {
            	    pushFollow(FOLLOW_element_in_treeSpec3050);
            	    element116=element();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_element.add(element116.getTree());

            	    }
            	    break;

            	default :
            	    if ( cnt57 >= 1 ) break loop57;
            	    if (state.backtracking>0) {state.failed=true; return retval;}
                        EarlyExitException eee =
                            new EarlyExitException(57, input);
                        throw eee;
                }
                cnt57++;
            } while (true);

            char_literal117=(Token)match(input,86,FOLLOW_86_in_treeSpec3054); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_86.add(char_literal117);



            // AST REWRITE
            // elements: element
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 494:5: -> ^( TREE_BEGIN[tb] ( element )+ )
            {
                // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:495:7: ^( TREE_BEGIN[tb] ( element )+ )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TREE_BEGIN, tb), root_1);

                if ( !(stream_element.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_element.hasNext() ) {
                    adaptor.addChild(root_1, stream_element.nextTree());

                }
                stream_element.reset();

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "treeSpec"

    public static class ebnf_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "ebnf"
    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:498:1: ebnf : block (op= '?' -> ^( OPTIONAL[op] block ) | op= '*' -> ^( CLOSURE[op] block ) | op= '+' -> ^( POSITIVE_CLOSURE[op] block ) | op= '^' -> ^( ROOT[op] block ) | op= '!' -> ^( BANG[op] block ) | op= '=>' -> {gtype==COMBINED_GRAMMAR && Character.isUpperCase($rule::name.charAt(0))}? ^( SYNPRED[op] block ) -> ^( SYN_SEMPRED[op] block ) | -> block ) ;
    public final ANTLRParser.ebnf_return ebnf() throws RecognitionException {
        ANTLRParser.ebnf_return retval = new ANTLRParser.ebnf_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token op=null;
        ANTLRParser.block_return block118 = null;


        CommonTree op_tree=null;
        RewriteRuleTokenStream stream_78=new RewriteRuleTokenStream(adaptor,"token 78");
        RewriteRuleTokenStream stream_BANG=new RewriteRuleTokenStream(adaptor,"token BANG");
        RewriteRuleTokenStream stream_93=new RewriteRuleTokenStream(adaptor,"token 93");
        RewriteRuleTokenStream stream_92=new RewriteRuleTokenStream(adaptor,"token 92");
        RewriteRuleTokenStream stream_90=new RewriteRuleTokenStream(adaptor,"token 90");
        RewriteRuleTokenStream stream_ROOT=new RewriteRuleTokenStream(adaptor,"token ROOT");
        RewriteRuleSubtreeStream stream_block=new RewriteRuleSubtreeStream(adaptor,"rule block");

            Token firstToken = input.LT(1);

        try {
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:510:3: ( block (op= '?' -> ^( OPTIONAL[op] block ) | op= '*' -> ^( CLOSURE[op] block ) | op= '+' -> ^( POSITIVE_CLOSURE[op] block ) | op= '^' -> ^( ROOT[op] block ) | op= '!' -> ^( BANG[op] block ) | op= '=>' -> {gtype==COMBINED_GRAMMAR && Character.isUpperCase($rule::name.charAt(0))}? ^( SYNPRED[op] block ) -> ^( SYN_SEMPRED[op] block ) | -> block ) )
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:511:3: block (op= '?' -> ^( OPTIONAL[op] block ) | op= '*' -> ^( CLOSURE[op] block ) | op= '+' -> ^( POSITIVE_CLOSURE[op] block ) | op= '^' -> ^( ROOT[op] block ) | op= '!' -> ^( BANG[op] block ) | op= '=>' -> {gtype==COMBINED_GRAMMAR && Character.isUpperCase($rule::name.charAt(0))}? ^( SYNPRED[op] block ) -> ^( SYN_SEMPRED[op] block ) | -> block )
            {
            pushFollow(FOLLOW_block_in_ebnf3101);
            block118=block();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_block.add(block118.getTree());
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:512:3: (op= '?' -> ^( OPTIONAL[op] block ) | op= '*' -> ^( CLOSURE[op] block ) | op= '+' -> ^( POSITIVE_CLOSURE[op] block ) | op= '^' -> ^( ROOT[op] block ) | op= '!' -> ^( BANG[op] block ) | op= '=>' -> {gtype==COMBINED_GRAMMAR && Character.isUpperCase($rule::name.charAt(0))}? ^( SYNPRED[op] block ) -> ^( SYN_SEMPRED[op] block ) | -> block )
            int alt58=7;
            switch ( input.LA(1) ) {
            case 92:
                {
                alt58=1;
                }
                break;
            case 78:
                {
                alt58=2;
                }
                break;
            case 93:
                {
                alt58=3;
                }
                break;
            case ROOT:
                {
                alt58=4;
                }
                break;
            case BANG:
                {
                alt58=5;
                }
                break;
            case 90:
                {
                alt58=6;
                }
                break;
            case EOF:
            case DOC_COMMENT:
            case SEMPRED:
            case FRAGMENT:
            case TREE_BEGIN:
            case REWRITE:
            case SEMI:
            case DOT:
            case TOKEN_REF:
            case STRING_LITERAL:
            case ACTION:
            case RULE_REF:
            case 80:
            case 81:
            case 82:
            case 84:
            case 85:
            case 86:
            case 87:
            case 88:
            case 91:
                {
                alt58=7;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 58, 0, input);

                throw nvae;
            }

            switch (alt58) {
                case 1 :
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:513:5: op= '?'
                    {
                    op=(Token)match(input,92,FOLLOW_92_in_ebnf3113); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_92.add(op);



                    // AST REWRITE
                    // elements: block
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 514:7: -> ^( OPTIONAL[op] block )
                    {
                        // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:515:9: ^( OPTIONAL[op] block )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(OPTIONAL, op), root_1);

                        adaptor.addChild(root_1, stream_block.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 2 :
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:516:7: op= '*'
                    {
                    op=(Token)match(input,78,FOLLOW_78_in_ebnf3146); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_78.add(op);



                    // AST REWRITE
                    // elements: block
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 517:7: -> ^( CLOSURE[op] block )
                    {
                        // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:518:9: ^( CLOSURE[op] block )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CLOSURE, op), root_1);

                        adaptor.addChild(root_1, stream_block.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 3 :
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:519:7: op= '+'
                    {
                    op=(Token)match(input,93,FOLLOW_93_in_ebnf3179); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_93.add(op);



                    // AST REWRITE
                    // elements: block
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 520:7: -> ^( POSITIVE_CLOSURE[op] block )
                    {
                        // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:521:9: ^( POSITIVE_CLOSURE[op] block )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(POSITIVE_CLOSURE, op), root_1);

                        adaptor.addChild(root_1, stream_block.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 4 :
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:522:7: op= '^'
                    {
                    op=(Token)match(input,ROOT,FOLLOW_ROOT_in_ebnf3212); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_ROOT.add(op);



                    // AST REWRITE
                    // elements: block
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 523:7: -> ^( ROOT[op] block )
                    {
                        // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:524:9: ^( ROOT[op] block )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ROOT, op), root_1);

                        adaptor.addChild(root_1, stream_block.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 5 :
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:525:7: op= '!'
                    {
                    op=(Token)match(input,BANG,FOLLOW_BANG_in_ebnf3245); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_BANG.add(op);



                    // AST REWRITE
                    // elements: block
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 526:7: -> ^( BANG[op] block )
                    {
                        // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:527:9: ^( BANG[op] block )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(BANG, op), root_1);

                        adaptor.addChild(root_1, stream_block.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 6 :
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:528:7: op= '=>'
                    {
                    op=(Token)match(input,90,FOLLOW_90_in_ebnf3278); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_90.add(op);



                    // AST REWRITE
                    // elements: block, block
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 529:7: -> {gtype==COMBINED_GRAMMAR && Character.isUpperCase($rule::name.charAt(0))}? ^( SYNPRED[op] block )
                    if (gtype==COMBINED_GRAMMAR && Character.isUpperCase(((rule_scope)rule_stack.peek()).name.charAt(0))) {
                        // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:531:9: ^( SYNPRED[op] block )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(SYNPRED, op), root_1);

                        adaptor.addChild(root_1, stream_block.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }
                    else // 533:7: -> ^( SYN_SEMPRED[op] block )
                    {
                        // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:534:9: ^( SYN_SEMPRED[op] block )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(SYN_SEMPRED, op), root_1);

                        adaptor.addChild(root_1, stream_block.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 7 :
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:536:7: 
                    {

                    // AST REWRITE
                    // elements: block
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 536:7: -> block
                    {
                        adaptor.addChild(root_0, stream_block.nextTree());

                    }

                    retval.tree = root_0;}
                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

                Token token = ((CommonTree)retval.tree).getToken();
                if(token != null) {
              	  token.setLine(firstToken.getLine());
              	  token.setCharPositionInLine(firstToken.getCharPositionInLine());
                }

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "ebnf"

    public static class range_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "range"
    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:540:1: range : c1= STRING_LITERAL r= RANGE c2= STRING_LITERAL -> ^( CHAR_RANGE[$r] $c1 $c2) ;
    public final ANTLRParser.range_return range() throws RecognitionException {
        ANTLRParser.range_return retval = new ANTLRParser.range_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token c1=null;
        Token r=null;
        Token c2=null;

        CommonTree c1_tree=null;
        CommonTree r_tree=null;
        CommonTree c2_tree=null;
        RewriteRuleTokenStream stream_RANGE=new RewriteRuleTokenStream(adaptor,"token RANGE");
        RewriteRuleTokenStream stream_STRING_LITERAL=new RewriteRuleTokenStream(adaptor,"token STRING_LITERAL");

        try {
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:541:3: (c1= STRING_LITERAL r= RANGE c2= STRING_LITERAL -> ^( CHAR_RANGE[$r] $c1 $c2) )
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:542:3: c1= STRING_LITERAL r= RANGE c2= STRING_LITERAL
            {
            c1=(Token)match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_range3381); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_STRING_LITERAL.add(c1);

            r=(Token)match(input,RANGE,FOLLOW_RANGE_in_range3385); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_RANGE.add(r);

            c2=(Token)match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_range3389); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_STRING_LITERAL.add(c2);



            // AST REWRITE
            // elements: c1, c2
            // token labels: c1, c2
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleTokenStream stream_c1=new RewriteRuleTokenStream(adaptor,"token c1",c1);
            RewriteRuleTokenStream stream_c2=new RewriteRuleTokenStream(adaptor,"token c2",c2);
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 543:5: -> ^( CHAR_RANGE[$r] $c1 $c2)
            {
                // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:544:7: ^( CHAR_RANGE[$r] $c1 $c2)
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CHAR_RANGE, r), root_1);

                adaptor.addChild(root_1, stream_c1.nextNode());
                adaptor.addChild(root_1, stream_c2.nextNode());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "range"

    public static class elementOptions_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "elementOptions"
    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:555:1: elementOptions : '<' elementOption ( ',' elementOption )* '>' -> ^( ELEMENT_OPTIONS ( elementOption )+ ) ;
    public final ANTLRParser.elementOptions_return elementOptions() throws RecognitionException {
        ANTLRParser.elementOptions_return retval = new ANTLRParser.elementOptions_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token char_literal119=null;
        Token char_literal121=null;
        Token char_literal123=null;
        ANTLRParser.elementOption_return elementOption120 = null;

        ANTLRParser.elementOption_return elementOption122 = null;


        CommonTree char_literal119_tree=null;
        CommonTree char_literal121_tree=null;
        CommonTree char_literal123_tree=null;
        RewriteRuleTokenStream stream_95=new RewriteRuleTokenStream(adaptor,"token 95");
        RewriteRuleTokenStream stream_94=new RewriteRuleTokenStream(adaptor,"token 94");
        RewriteRuleTokenStream stream_74=new RewriteRuleTokenStream(adaptor,"token 74");
        RewriteRuleSubtreeStream stream_elementOption=new RewriteRuleSubtreeStream(adaptor,"rule elementOption");
        try {
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:556:3: ( '<' elementOption ( ',' elementOption )* '>' -> ^( ELEMENT_OPTIONS ( elementOption )+ ) )
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:558:3: '<' elementOption ( ',' elementOption )* '>'
            {
            char_literal119=(Token)match(input,94,FOLLOW_94_in_elementOptions3441); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_94.add(char_literal119);

            pushFollow(FOLLOW_elementOption_in_elementOptions3443);
            elementOption120=elementOption();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_elementOption.add(elementOption120.getTree());
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:558:21: ( ',' elementOption )*
            loop59:
            do {
                int alt59=2;
                int LA59_0 = input.LA(1);

                if ( (LA59_0==74) ) {
                    alt59=1;
                }


                switch (alt59) {
            	case 1 :
            	    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:558:22: ',' elementOption
            	    {
            	    char_literal121=(Token)match(input,74,FOLLOW_74_in_elementOptions3446); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_74.add(char_literal121);

            	    pushFollow(FOLLOW_elementOption_in_elementOptions3448);
            	    elementOption122=elementOption();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_elementOption.add(elementOption122.getTree());

            	    }
            	    break;

            	default :
            	    break loop59;
                }
            } while (true);

            char_literal123=(Token)match(input,95,FOLLOW_95_in_elementOptions3452); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_95.add(char_literal123);



            // AST REWRITE
            // elements: elementOption
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 559:5: -> ^( ELEMENT_OPTIONS ( elementOption )+ )
            {
                // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:560:7: ^( ELEMENT_OPTIONS ( elementOption )+ )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ELEMENT_OPTIONS, "ELEMENT_OPTIONS"), root_1);

                if ( !(stream_elementOption.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_elementOption.hasNext() ) {
                    adaptor.addChild(root_1, stream_elementOption.nextTree());

                }
                stream_elementOption.reset();

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "elementOptions"

    public static class elementOption_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "elementOption"
    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:566:1: elementOption : ( qid | id ASSIGN ( qid | STRING_LITERAL ) );
    public final ANTLRParser.elementOption_return elementOption() throws RecognitionException {
        ANTLRParser.elementOption_return retval = new ANTLRParser.elementOption_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token ASSIGN126=null;
        Token STRING_LITERAL128=null;
        ANTLRParser.qid_return qid124 = null;

        ANTLRParser.id_return id125 = null;

        ANTLRParser.qid_return qid127 = null;


        CommonTree ASSIGN126_tree=null;
        CommonTree STRING_LITERAL128_tree=null;

        try {
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:567:3: ( qid | id ASSIGN ( qid | STRING_LITERAL ) )
            int alt61=2;
            int LA61_0 = input.LA(1);

            if ( (LA61_0==TOKEN_REF) ) {
                int LA61_1 = input.LA(2);

                if ( (LA61_1==ASSIGN) ) {
                    alt61=2;
                }
                else if ( (LA61_1==DOT||LA61_1==74||LA61_1==95) ) {
                    alt61=1;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 61, 1, input);

                    throw nvae;
                }
            }
            else if ( (LA61_0==RULE_REF) ) {
                int LA61_2 = input.LA(2);

                if ( (LA61_2==DOT||LA61_2==74||LA61_2==95) ) {
                    alt61=1;
                }
                else if ( (LA61_2==ASSIGN) ) {
                    alt61=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 61, 2, input);

                    throw nvae;
                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 61, 0, input);

                throw nvae;
            }
            switch (alt61) {
                case 1 :
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:568:3: qid
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_qid_in_elementOption3490);
                    qid124=qid();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, qid124.getTree());

                    }
                    break;
                case 2 :
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:570:4: id ASSIGN ( qid | STRING_LITERAL )
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_id_in_elementOption3500);
                    id125=id();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, id125.getTree());
                    ASSIGN126=(Token)match(input,ASSIGN,FOLLOW_ASSIGN_in_elementOption3502); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    ASSIGN126_tree = (CommonTree)adaptor.create(ASSIGN126);
                    root_0 = (CommonTree)adaptor.becomeRoot(ASSIGN126_tree, root_0);
                    }
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:571:3: ( qid | STRING_LITERAL )
                    int alt60=2;
                    int LA60_0 = input.LA(1);

                    if ( (LA60_0==TOKEN_REF||LA60_0==RULE_REF) ) {
                        alt60=1;
                    }
                    else if ( (LA60_0==STRING_LITERAL) ) {
                        alt60=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 60, 0, input);

                        throw nvae;
                    }
                    switch (alt60) {
                        case 1 :
                            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:572:5: qid
                            {
                            pushFollow(FOLLOW_qid_in_elementOption3513);
                            qid127=qid();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, qid127.getTree());

                            }
                            break;
                        case 2 :
                            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:573:7: STRING_LITERAL
                            {
                            STRING_LITERAL128=(Token)match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_elementOption3521); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            STRING_LITERAL128_tree = (CommonTree)adaptor.create(STRING_LITERAL128);
                            adaptor.addChild(root_0, STRING_LITERAL128_tree);
                            }

                            }
                            break;

                    }


                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "elementOption"

    public static class qid_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "qid"
    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:577:1: qid : id ( DOT id )* -> ID[$qid.start, $text] ;
    public final ANTLRParser.qid_return qid() throws RecognitionException {
        ANTLRParser.qid_return retval = new ANTLRParser.qid_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token DOT130=null;
        ANTLRParser.id_return id129 = null;

        ANTLRParser.id_return id131 = null;


        CommonTree DOT130_tree=null;
        RewriteRuleTokenStream stream_DOT=new RewriteRuleTokenStream(adaptor,"token DOT");
        RewriteRuleSubtreeStream stream_id=new RewriteRuleSubtreeStream(adaptor,"rule id");
        try {
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:578:3: ( id ( DOT id )* -> ID[$qid.start, $text] )
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:579:3: id ( DOT id )*
            {
            pushFollow(FOLLOW_id_in_qid3540);
            id129=id();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_id.add(id129.getTree());
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:579:6: ( DOT id )*
            loop62:
            do {
                int alt62=2;
                int LA62_0 = input.LA(1);

                if ( (LA62_0==DOT) ) {
                    alt62=1;
                }


                switch (alt62) {
            	case 1 :
            	    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:579:7: DOT id
            	    {
            	    DOT130=(Token)match(input,DOT,FOLLOW_DOT_in_qid3543); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_DOT.add(DOT130);

            	    pushFollow(FOLLOW_id_in_qid3545);
            	    id131=id();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_id.add(id131.getTree());

            	    }
            	    break;

            	default :
            	    break loop62;
                }
            } while (true);



            // AST REWRITE
            // elements: 
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 580:5: -> ID[$qid.start, $text]
            {
                adaptor.addChild(root_0, (CommonTree)adaptor.create(ID, ((Token)retval.start), input.toString(retval.start,input.LT(-1))));

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "qid"

    public static class terminal_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "terminal"
    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:583:1: terminal : ( TOKEN_REF ( elementOptions )? ( ARG_ACTION )? -> ^( TOKEN_REF ( elementOptions )? ( ARG_ACTION )? ) | STRING_LITERAL ( elementOptions )? -> ^( STRING_LITERAL ( elementOptions )? ) | '.' -> '.' ) ( '^' -> ^( '^' $terminal) | '!' -> ^( '!' $terminal) )? ;
    public final ANTLRParser.terminal_return terminal() throws RecognitionException {
        ANTLRParser.terminal_return retval = new ANTLRParser.terminal_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token TOKEN_REF132=null;
        Token ARG_ACTION134=null;
        Token STRING_LITERAL135=null;
        Token char_literal137=null;
        Token char_literal138=null;
        Token char_literal139=null;
        ANTLRParser.elementOptions_return elementOptions133 = null;

        ANTLRParser.elementOptions_return elementOptions136 = null;


        CommonTree TOKEN_REF132_tree=null;
        CommonTree ARG_ACTION134_tree=null;
        CommonTree STRING_LITERAL135_tree=null;
        CommonTree char_literal137_tree=null;
        CommonTree char_literal138_tree=null;
        CommonTree char_literal139_tree=null;
        RewriteRuleTokenStream stream_STRING_LITERAL=new RewriteRuleTokenStream(adaptor,"token STRING_LITERAL");
        RewriteRuleTokenStream stream_BANG=new RewriteRuleTokenStream(adaptor,"token BANG");
        RewriteRuleTokenStream stream_DOT=new RewriteRuleTokenStream(adaptor,"token DOT");
        RewriteRuleTokenStream stream_ROOT=new RewriteRuleTokenStream(adaptor,"token ROOT");
        RewriteRuleTokenStream stream_TOKEN_REF=new RewriteRuleTokenStream(adaptor,"token TOKEN_REF");
        RewriteRuleTokenStream stream_ARG_ACTION=new RewriteRuleTokenStream(adaptor,"token ARG_ACTION");
        RewriteRuleSubtreeStream stream_elementOptions=new RewriteRuleSubtreeStream(adaptor,"rule elementOptions");
        try {
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:584:3: ( ( TOKEN_REF ( elementOptions )? ( ARG_ACTION )? -> ^( TOKEN_REF ( elementOptions )? ( ARG_ACTION )? ) | STRING_LITERAL ( elementOptions )? -> ^( STRING_LITERAL ( elementOptions )? ) | '.' -> '.' ) ( '^' -> ^( '^' $terminal) | '!' -> ^( '!' $terminal) )? )
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:585:3: ( TOKEN_REF ( elementOptions )? ( ARG_ACTION )? -> ^( TOKEN_REF ( elementOptions )? ( ARG_ACTION )? ) | STRING_LITERAL ( elementOptions )? -> ^( STRING_LITERAL ( elementOptions )? ) | '.' -> '.' ) ( '^' -> ^( '^' $terminal) | '!' -> ^( '!' $terminal) )?
            {
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:585:3: ( TOKEN_REF ( elementOptions )? ( ARG_ACTION )? -> ^( TOKEN_REF ( elementOptions )? ( ARG_ACTION )? ) | STRING_LITERAL ( elementOptions )? -> ^( STRING_LITERAL ( elementOptions )? ) | '.' -> '.' )
            int alt66=3;
            switch ( input.LA(1) ) {
            case TOKEN_REF:
                {
                alt66=1;
                }
                break;
            case STRING_LITERAL:
                {
                alt66=2;
                }
                break;
            case DOT:
                {
                alt66=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 66, 0, input);

                throw nvae;
            }

            switch (alt66) {
                case 1 :
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:587:5: TOKEN_REF ( elementOptions )? ( ARG_ACTION )?
                    {
                    TOKEN_REF132=(Token)match(input,TOKEN_REF,FOLLOW_TOKEN_REF_in_terminal3582); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_TOKEN_REF.add(TOKEN_REF132);

                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:587:15: ( elementOptions )?
                    int alt63=2;
                    int LA63_0 = input.LA(1);

                    if ( (LA63_0==94) ) {
                        alt63=1;
                    }
                    switch (alt63) {
                        case 1 :
                            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:587:15: elementOptions
                            {
                            pushFollow(FOLLOW_elementOptions_in_terminal3584);
                            elementOptions133=elementOptions();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_elementOptions.add(elementOptions133.getTree());

                            }
                            break;

                    }

                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:587:31: ( ARG_ACTION )?
                    int alt64=2;
                    int LA64_0 = input.LA(1);

                    if ( (LA64_0==ARG_ACTION) ) {
                        alt64=1;
                    }
                    switch (alt64) {
                        case 1 :
                            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:587:31: ARG_ACTION
                            {
                            ARG_ACTION134=(Token)match(input,ARG_ACTION,FOLLOW_ARG_ACTION_in_terminal3587); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_ARG_ACTION.add(ARG_ACTION134);


                            }
                            break;

                    }



                    // AST REWRITE
                    // elements: elementOptions, ARG_ACTION, TOKEN_REF
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 588:7: -> ^( TOKEN_REF ( elementOptions )? ( ARG_ACTION )? )
                    {
                        // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:589:9: ^( TOKEN_REF ( elementOptions )? ( ARG_ACTION )? )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(stream_TOKEN_REF.nextNode(), root_1);

                        // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:589:21: ( elementOptions )?
                        if ( stream_elementOptions.hasNext() ) {
                            adaptor.addChild(root_1, stream_elementOptions.nextTree());

                        }
                        stream_elementOptions.reset();
                        // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:589:37: ( ARG_ACTION )?
                        if ( stream_ARG_ACTION.hasNext() ) {
                            adaptor.addChild(root_1, stream_ARG_ACTION.nextNode());

                        }
                        stream_ARG_ACTION.reset();

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 2 :
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:590:7: STRING_LITERAL ( elementOptions )?
                    {
                    STRING_LITERAL135=(Token)match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_terminal3622); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_STRING_LITERAL.add(STRING_LITERAL135);

                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:590:22: ( elementOptions )?
                    int alt65=2;
                    int LA65_0 = input.LA(1);

                    if ( (LA65_0==94) ) {
                        alt65=1;
                    }
                    switch (alt65) {
                        case 1 :
                            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:590:22: elementOptions
                            {
                            pushFollow(FOLLOW_elementOptions_in_terminal3624);
                            elementOptions136=elementOptions();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_elementOptions.add(elementOptions136.getTree());

                            }
                            break;

                    }



                    // AST REWRITE
                    // elements: elementOptions, STRING_LITERAL
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 591:7: -> ^( STRING_LITERAL ( elementOptions )? )
                    {
                        // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:592:9: ^( STRING_LITERAL ( elementOptions )? )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(stream_STRING_LITERAL.nextNode(), root_1);

                        // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:592:26: ( elementOptions )?
                        if ( stream_elementOptions.hasNext() ) {
                            adaptor.addChild(root_1, stream_elementOptions.nextTree());

                        }
                        stream_elementOptions.reset();

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 3 :
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:593:7: '.'
                    {
                    char_literal137=(Token)match(input,DOT,FOLLOW_DOT_in_terminal3656); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_DOT.add(char_literal137);



                    // AST REWRITE
                    // elements: DOT
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 594:7: -> '.'
                    {
                        adaptor.addChild(root_0, stream_DOT.nextNode());

                    }

                    retval.tree = root_0;}
                    }
                    break;

            }

            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:596:3: ( '^' -> ^( '^' $terminal) | '!' -> ^( '!' $terminal) )?
            int alt67=3;
            int LA67_0 = input.LA(1);

            if ( (LA67_0==ROOT) ) {
                alt67=1;
            }
            else if ( (LA67_0==BANG) ) {
                alt67=2;
            }
            switch (alt67) {
                case 1 :
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:597:5: '^'
                    {
                    char_literal138=(Token)match(input,ROOT,FOLLOW_ROOT_in_terminal3680); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_ROOT.add(char_literal138);



                    // AST REWRITE
                    // elements: ROOT, terminal
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 598:7: -> ^( '^' $terminal)
                    {
                        // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:599:9: ^( '^' $terminal)
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(stream_ROOT.nextNode(), root_1);

                        adaptor.addChild(root_1, stream_retval.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 2 :
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:600:7: '!'
                    {
                    char_literal139=(Token)match(input,BANG,FOLLOW_BANG_in_terminal3711); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_BANG.add(char_literal139);



                    // AST REWRITE
                    // elements: terminal, BANG
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 601:7: -> ^( '!' $terminal)
                    {
                        // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:602:9: ^( '!' $terminal)
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(stream_BANG.nextNode(), root_1);

                        adaptor.addChild(root_1, stream_retval.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "terminal"

    public static class notTerminal_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "notTerminal"
    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:606:1: notTerminal : ( TOKEN_REF | STRING_LITERAL );
    public final ANTLRParser.notTerminal_return notTerminal() throws RecognitionException {
        ANTLRParser.notTerminal_return retval = new ANTLRParser.notTerminal_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token set140=null;

        CommonTree set140_tree=null;

        try {
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:607:3: ( TOKEN_REF | STRING_LITERAL )
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:
            {
            root_0 = (CommonTree)adaptor.nil();

            set140=(Token)input.LT(1);
            if ( (input.LA(1)>=TOKEN_REF && input.LA(1)<=STRING_LITERAL) ) {
                input.consume();
                if ( state.backtracking==0 ) adaptor.addChild(root_0, (CommonTree)adaptor.create(set140));
                state.errorRecovery=false;state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "notTerminal"

    public static class ebnfSuffix_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "ebnfSuffix"
    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:612:1: ebnfSuffix : ( '?' -> OPTIONAL[op] | '*' -> CLOSURE[op] | '+' -> POSITIVE_CLOSURE[op] );
    public final ANTLRParser.ebnfSuffix_return ebnfSuffix() throws RecognitionException {
        ANTLRParser.ebnfSuffix_return retval = new ANTLRParser.ebnfSuffix_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token char_literal141=null;
        Token char_literal142=null;
        Token char_literal143=null;

        CommonTree char_literal141_tree=null;
        CommonTree char_literal142_tree=null;
        CommonTree char_literal143_tree=null;
        RewriteRuleTokenStream stream_78=new RewriteRuleTokenStream(adaptor,"token 78");
        RewriteRuleTokenStream stream_93=new RewriteRuleTokenStream(adaptor,"token 93");
        RewriteRuleTokenStream stream_92=new RewriteRuleTokenStream(adaptor,"token 92");


          Token op = input.LT(1);

        try {
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:616:3: ( '?' -> OPTIONAL[op] | '*' -> CLOSURE[op] | '+' -> POSITIVE_CLOSURE[op] )
            int alt68=3;
            switch ( input.LA(1) ) {
            case 92:
                {
                alt68=1;
                }
                break;
            case 78:
                {
                alt68=2;
                }
                break;
            case 93:
                {
                alt68=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 68, 0, input);

                throw nvae;
            }

            switch (alt68) {
                case 1 :
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:617:3: '?'
                    {
                    char_literal141=(Token)match(input,92,FOLLOW_92_in_ebnfSuffix3780); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_92.add(char_literal141);



                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 618:5: -> OPTIONAL[op]
                    {
                        adaptor.addChild(root_0, (CommonTree)adaptor.create(OPTIONAL, op));

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 2 :
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:619:5: '*'
                    {
                    char_literal142=(Token)match(input,78,FOLLOW_78_in_ebnfSuffix3795); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_78.add(char_literal142);



                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 620:5: -> CLOSURE[op]
                    {
                        adaptor.addChild(root_0, (CommonTree)adaptor.create(CLOSURE, op));

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 3 :
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:621:5: '+'
                    {
                    char_literal143=(Token)match(input,93,FOLLOW_93_in_ebnfSuffix3810); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_93.add(char_literal143);



                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 622:5: -> POSITIVE_CLOSURE[op]
                    {
                        adaptor.addChild(root_0, (CommonTree)adaptor.create(POSITIVE_CLOSURE, op));

                    }

                    retval.tree = root_0;}
                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "ebnfSuffix"

    public static class rewrite_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "rewrite"
    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:627:1: rewrite : ( (rew+= '->' preds+= SEMPRED predicated+= rewrite_alternative )* rew2= '->' last= rewrite_alternative -> ( ^( $rew $preds $predicated) )* ^( $rew2 $last) | );
    public final ANTLRParser.rewrite_return rewrite() throws RecognitionException {
        ANTLRParser.rewrite_return retval = new ANTLRParser.rewrite_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token rew2=null;
        Token rew=null;
        Token preds=null;
        List<Token> list_rew=null;
        List<Token> list_preds=null;
        List<Object> list_predicated=null;
        ANTLRParser.rewrite_alternative_return last = null;

        RuleReturnScope predicated = null;
        CommonTree rew2_tree=null;
        CommonTree rew_tree=null;
        CommonTree preds_tree=null;
        RewriteRuleTokenStream stream_SEMPRED=new RewriteRuleTokenStream(adaptor,"token SEMPRED");
        RewriteRuleTokenStream stream_REWRITE=new RewriteRuleTokenStream(adaptor,"token REWRITE");
        RewriteRuleSubtreeStream stream_rewrite_alternative=new RewriteRuleSubtreeStream(adaptor,"rule rewrite_alternative");
        try {
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:628:3: ( (rew+= '->' preds+= SEMPRED predicated+= rewrite_alternative )* rew2= '->' last= rewrite_alternative -> ( ^( $rew $preds $predicated) )* ^( $rew2 $last) | )
            int alt70=2;
            int LA70_0 = input.LA(1);

            if ( (LA70_0==REWRITE) ) {
                alt70=1;
            }
            else if ( (LA70_0==EOF||LA70_0==DOC_COMMENT||LA70_0==FRAGMENT||LA70_0==SEMI||LA70_0==TOKEN_REF||LA70_0==RULE_REF||(LA70_0>=80 && LA70_0<=82)||(LA70_0>=85 && LA70_0<=88)) ) {
                alt70=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 70, 0, input);

                throw nvae;
            }
            switch (alt70) {
                case 1 :
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:629:3: (rew+= '->' preds+= SEMPRED predicated+= rewrite_alternative )* rew2= '->' last= rewrite_alternative
                    {
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:629:3: (rew+= '->' preds+= SEMPRED predicated+= rewrite_alternative )*
                    loop69:
                    do {
                        int alt69=2;
                        int LA69_0 = input.LA(1);

                        if ( (LA69_0==REWRITE) ) {
                            int LA69_1 = input.LA(2);

                            if ( (LA69_1==SEMPRED) ) {
                                alt69=1;
                            }


                        }


                        switch (alt69) {
                    	case 1 :
                    	    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:629:4: rew+= '->' preds+= SEMPRED predicated+= rewrite_alternative
                    	    {
                    	    rew=(Token)match(input,REWRITE,FOLLOW_REWRITE_in_rewrite3839); if (state.failed) return retval; 
                    	    if ( state.backtracking==0 ) stream_REWRITE.add(rew);

                    	    if (list_rew==null) list_rew=new ArrayList<Token>();
                    	    list_rew.add(rew);

                    	    preds=(Token)match(input,SEMPRED,FOLLOW_SEMPRED_in_rewrite3843); if (state.failed) return retval; 
                    	    if ( state.backtracking==0 ) stream_SEMPRED.add(preds);

                    	    if (list_preds==null) list_preds=new ArrayList<Token>();
                    	    list_preds.add(preds);

                    	    pushFollow(FOLLOW_rewrite_alternative_in_rewrite3847);
                    	    predicated=rewrite_alternative();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) stream_rewrite_alternative.add(predicated.getTree());
                    	    if (list_predicated==null) list_predicated=new ArrayList<Object>();
                    	    list_predicated.add(predicated.getTree());


                    	    }
                    	    break;

                    	default :
                    	    break loop69;
                        }
                    } while (true);

                    rew2=(Token)match(input,REWRITE,FOLLOW_REWRITE_in_rewrite3853); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_REWRITE.add(rew2);

                    pushFollow(FOLLOW_rewrite_alternative_in_rewrite3857);
                    last=rewrite_alternative();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_rewrite_alternative.add(last.getTree());


                    // AST REWRITE
                    // elements: predicated, rew, last, preds, rew2
                    // token labels: rew2
                    // rule labels: retval, last
                    // token list labels: rew, preds
                    // rule list labels: predicated
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleTokenStream stream_rew2=new RewriteRuleTokenStream(adaptor,"token rew2",rew2);
                    RewriteRuleTokenStream stream_rew=new RewriteRuleTokenStream(adaptor,"token rew", list_rew);
                    RewriteRuleTokenStream stream_preds=new RewriteRuleTokenStream(adaptor,"token preds", list_preds);
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
                    RewriteRuleSubtreeStream stream_last=new RewriteRuleSubtreeStream(adaptor,"rule last",last!=null?last.tree:null);
                    RewriteRuleSubtreeStream stream_predicated=new RewriteRuleSubtreeStream(adaptor,"token predicated",list_predicated);
                    root_0 = (CommonTree)adaptor.nil();
                    // 630:5: -> ( ^( $rew $preds $predicated) )* ^( $rew2 $last)
                    {
                        // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:631:7: ( ^( $rew $preds $predicated) )*
                        while ( stream_predicated.hasNext()||stream_rew.hasNext()||stream_preds.hasNext() ) {
                            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:631:7: ^( $rew $preds $predicated)
                            {
                            CommonTree root_1 = (CommonTree)adaptor.nil();
                            root_1 = (CommonTree)adaptor.becomeRoot(stream_rew.nextNode(), root_1);

                            adaptor.addChild(root_1, stream_preds.nextNode());
                            adaptor.addChild(root_1, stream_predicated.nextTree());

                            adaptor.addChild(root_0, root_1);
                            }

                        }
                        stream_predicated.reset();
                        stream_rew.reset();
                        stream_preds.reset();
                        // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:632:7: ^( $rew2 $last)
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(stream_rew2.nextNode(), root_1);

                        adaptor.addChild(root_1, stream_last.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 2 :
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:634:3: 
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "rewrite"

    public static class rewrite_alternative_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "rewrite_alternative"
    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:636:1: rewrite_alternative options {backtrack=true; } : ({...}? rewrite_template | rewrite_tree_alternative | -> ^( ALT[firstToken] EPSILON[firstToken] EOA[firstToken] ) );
    public final ANTLRParser.rewrite_alternative_return rewrite_alternative() throws RecognitionException {
        ANTLRParser.rewrite_alternative_return retval = new ANTLRParser.rewrite_alternative_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        ANTLRParser.rewrite_template_return rewrite_template144 = null;

        ANTLRParser.rewrite_tree_alternative_return rewrite_tree_alternative145 = null;




          Token firstToken = input.LT(-1);

        try {
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:643:3: ({...}? rewrite_template | rewrite_tree_alternative | -> ^( ALT[firstToken] EPSILON[firstToken] EOA[firstToken] ) )
            int alt71=3;
            alt71 = dfa71.predict(input);
            switch (alt71) {
                case 1 :
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:644:3: {...}? rewrite_template
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    if ( !((templateOutput)) ) {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        throw new FailedPredicateException(input, "rewrite_alternative", "templateOutput");
                    }
                    pushFollow(FOLLOW_rewrite_template_in_rewrite_alternative3934);
                    rewrite_template144=rewrite_template();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, rewrite_template144.getTree());

                    }
                    break;
                case 2 :
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:645:5: rewrite_tree_alternative
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_rewrite_tree_alternative_in_rewrite_alternative3940);
                    rewrite_tree_alternative145=rewrite_tree_alternative();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, rewrite_tree_alternative145.getTree());

                    }
                    break;
                case 3 :
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:647:5: 
                    {

                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 647:5: -> ^( ALT[firstToken] EPSILON[firstToken] EOA[firstToken] )
                    {
                        // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:648:7: ^( ALT[firstToken] EPSILON[firstToken] EOA[firstToken] )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ALT, firstToken), root_1);

                        adaptor.addChild(root_1, (CommonTree)adaptor.create(EPSILON, firstToken));
                        adaptor.addChild(root_1, (CommonTree)adaptor.create(EOA, firstToken));

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "rewrite_alternative"

    public static class rewrite_tree_block_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "rewrite_tree_block"
    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:651:1: rewrite_tree_block : lp= '(' rewrite_tree_alternative rp= ')' -> ^( BLOCK[$lp] rewrite_tree_alternative EOB[$rp] ) ;
    public final ANTLRParser.rewrite_tree_block_return rewrite_tree_block() throws RecognitionException {
        ANTLRParser.rewrite_tree_block_return retval = new ANTLRParser.rewrite_tree_block_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token lp=null;
        Token rp=null;
        ANTLRParser.rewrite_tree_alternative_return rewrite_tree_alternative146 = null;


        CommonTree lp_tree=null;
        CommonTree rp_tree=null;
        RewriteRuleTokenStream stream_86=new RewriteRuleTokenStream(adaptor,"token 86");
        RewriteRuleTokenStream stream_84=new RewriteRuleTokenStream(adaptor,"token 84");
        RewriteRuleSubtreeStream stream_rewrite_tree_alternative=new RewriteRuleSubtreeStream(adaptor,"rule rewrite_tree_alternative");
        try {
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:652:3: (lp= '(' rewrite_tree_alternative rp= ')' -> ^( BLOCK[$lp] rewrite_tree_alternative EOB[$rp] ) )
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:653:3: lp= '(' rewrite_tree_alternative rp= ')'
            {
            lp=(Token)match(input,84,FOLLOW_84_in_rewrite_tree_block3986); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_84.add(lp);

            pushFollow(FOLLOW_rewrite_tree_alternative_in_rewrite_tree_block3988);
            rewrite_tree_alternative146=rewrite_tree_alternative();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_rewrite_tree_alternative.add(rewrite_tree_alternative146.getTree());
            rp=(Token)match(input,86,FOLLOW_86_in_rewrite_tree_block3992); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_86.add(rp);



            // AST REWRITE
            // elements: rewrite_tree_alternative
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 654:5: -> ^( BLOCK[$lp] rewrite_tree_alternative EOB[$rp] )
            {
                // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:655:7: ^( BLOCK[$lp] rewrite_tree_alternative EOB[$rp] )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(BLOCK, lp), root_1);

                adaptor.addChild(root_1, stream_rewrite_tree_alternative.nextTree());
                adaptor.addChild(root_1, (CommonTree)adaptor.create(EOB, rp));

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "rewrite_tree_block"

    public static class rewrite_tree_alternative_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "rewrite_tree_alternative"
    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:658:1: rewrite_tree_alternative : ( rewrite_tree_element )+ -> ^( ALT[firstToken] ( rewrite_tree_element )+ ) ;
    public final ANTLRParser.rewrite_tree_alternative_return rewrite_tree_alternative() throws RecognitionException {
        ANTLRParser.rewrite_tree_alternative_return retval = new ANTLRParser.rewrite_tree_alternative_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        ANTLRParser.rewrite_tree_element_return rewrite_tree_element147 = null;


        RewriteRuleSubtreeStream stream_rewrite_tree_element=new RewriteRuleSubtreeStream(adaptor,"rule rewrite_tree_element");

          Token firstToken = input.LT(1);

        try {
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:662:3: ( ( rewrite_tree_element )+ -> ^( ALT[firstToken] ( rewrite_tree_element )+ ) )
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:663:3: ( rewrite_tree_element )+
            {
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:663:3: ( rewrite_tree_element )+
            int cnt72=0;
            loop72:
            do {
                int alt72=2;
                switch ( input.LA(1) ) {
                case TOKEN_REF:
                    {
                    int LA72_2 = input.LA(2);

                    if ( (LA72_2==ARG_ACTION) ) {
                        int LA72_5 = input.LA(3);

                        if ( (LA72_5==EOF||LA72_5==DOC_COMMENT||(LA72_5>=FRAGMENT && LA72_5<=TREE_BEGIN)||(LA72_5>=REWRITE && LA72_5<=SEMI)||(LA72_5>=TOKEN_REF && LA72_5<=ACTION)||LA72_5==RULE_REF||LA72_5==78||(LA72_5>=80 && LA72_5<=82)||(LA72_5>=84 && LA72_5<=88)||(LA72_5>=92 && LA72_5<=93)||LA72_5==96) ) {
                            alt72=1;
                        }


                    }
                    else if ( (LA72_2==EOF||LA72_2==DOC_COMMENT||(LA72_2>=FRAGMENT && LA72_2<=TREE_BEGIN)||(LA72_2>=REWRITE && LA72_2<=SEMI)||(LA72_2>=TOKEN_REF && LA72_2<=ACTION)||LA72_2==RULE_REF||LA72_2==78||(LA72_2>=80 && LA72_2<=82)||(LA72_2>=84 && LA72_2<=88)||(LA72_2>=92 && LA72_2<=94)||LA72_2==96) ) {
                        alt72=1;
                    }


                    }
                    break;
                case RULE_REF:
                    {
                    int LA72_3 = input.LA(2);

                    if ( (LA72_3==EOF||LA72_3==DOC_COMMENT||(LA72_3>=FRAGMENT && LA72_3<=TREE_BEGIN)||(LA72_3>=REWRITE && LA72_3<=SEMI)||(LA72_3>=TOKEN_REF && LA72_3<=ACTION)||LA72_3==RULE_REF||LA72_3==78||(LA72_3>=80 && LA72_3<=82)||(LA72_3>=84 && LA72_3<=88)||(LA72_3>=92 && LA72_3<=93)||LA72_3==96) ) {
                        alt72=1;
                    }


                    }
                    break;
                case TREE_BEGIN:
                case STRING_LITERAL:
                case ACTION:
                case 84:
                case 96:
                    {
                    alt72=1;
                    }
                    break;

                }

                switch (alt72) {
            	case 1 :
            	    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:663:3: rewrite_tree_element
            	    {
            	    pushFollow(FOLLOW_rewrite_tree_element_in_rewrite_tree_alternative4034);
            	    rewrite_tree_element147=rewrite_tree_element();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_rewrite_tree_element.add(rewrite_tree_element147.getTree());

            	    }
            	    break;

            	default :
            	    if ( cnt72 >= 1 ) break loop72;
            	    if (state.backtracking>0) {state.failed=true; return retval;}
                        EarlyExitException eee =
                            new EarlyExitException(72, input);
                        throw eee;
                }
                cnt72++;
            } while (true);



            // AST REWRITE
            // elements: rewrite_tree_element
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 664:5: -> ^( ALT[firstToken] ( rewrite_tree_element )+ )
            {
                // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:665:7: ^( ALT[firstToken] ( rewrite_tree_element )+ )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ALT, firstToken), root_1);

                if ( !(stream_rewrite_tree_element.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_rewrite_tree_element.hasNext() ) {
                    adaptor.addChild(root_1, stream_rewrite_tree_element.nextTree());

                }
                stream_rewrite_tree_element.reset();
                adaptor.addChild(root_1, adaptor.create(EOA, input.LT(-1)));

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "rewrite_tree_alternative"

    public static class rewrite_tree_element_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "rewrite_tree_element"
    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:668:1: rewrite_tree_element : ( rewrite_tree_atom | rewrite_tree_atom startEndStt= ebnfSuffix -> ^( ebnfSuffix ^( BLOCK[$startEndStt.tree.getToken()] ^( ALT[$startEndStt.tree.getToken()] rewrite_tree_atom EOA[$startEndStt.tree.getToken()] ) EOB[$startEndStt.tree.getToken()] ) ) | rewrite_tree (startEndStt= ebnfSuffix -> ^( ebnfSuffix ^( BLOCK[$startEndStt.tree.getToken()] ^( ALT[$startEndStt.tree.getToken()] rewrite_tree EOA[$startEndStt.tree.getToken()] ) EOB[$startEndStt.tree.getToken()] ) ) | -> rewrite_tree ) | rewrite_tree_ebnf );
    public final ANTLRParser.rewrite_tree_element_return rewrite_tree_element() throws RecognitionException {
        ANTLRParser.rewrite_tree_element_return retval = new ANTLRParser.rewrite_tree_element_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        ANTLRParser.ebnfSuffix_return startEndStt = null;

        ANTLRParser.rewrite_tree_atom_return rewrite_tree_atom148 = null;

        ANTLRParser.rewrite_tree_atom_return rewrite_tree_atom149 = null;

        ANTLRParser.rewrite_tree_return rewrite_tree150 = null;

        ANTLRParser.rewrite_tree_ebnf_return rewrite_tree_ebnf151 = null;


        RewriteRuleSubtreeStream stream_rewrite_tree=new RewriteRuleSubtreeStream(adaptor,"rule rewrite_tree");
        RewriteRuleSubtreeStream stream_ebnfSuffix=new RewriteRuleSubtreeStream(adaptor,"rule ebnfSuffix");
        RewriteRuleSubtreeStream stream_rewrite_tree_atom=new RewriteRuleSubtreeStream(adaptor,"rule rewrite_tree_atom");
        try {
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:669:3: ( rewrite_tree_atom | rewrite_tree_atom startEndStt= ebnfSuffix -> ^( ebnfSuffix ^( BLOCK[$startEndStt.tree.getToken()] ^( ALT[$startEndStt.tree.getToken()] rewrite_tree_atom EOA[$startEndStt.tree.getToken()] ) EOB[$startEndStt.tree.getToken()] ) ) | rewrite_tree (startEndStt= ebnfSuffix -> ^( ebnfSuffix ^( BLOCK[$startEndStt.tree.getToken()] ^( ALT[$startEndStt.tree.getToken()] rewrite_tree EOA[$startEndStt.tree.getToken()] ) EOB[$startEndStt.tree.getToken()] ) ) | -> rewrite_tree ) | rewrite_tree_ebnf )
            int alt74=4;
            alt74 = dfa74.predict(input);
            switch (alt74) {
                case 1 :
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:670:3: rewrite_tree_atom
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_rewrite_tree_atom_in_rewrite_tree_element4072);
                    rewrite_tree_atom148=rewrite_tree_atom();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, rewrite_tree_atom148.getTree());

                    }
                    break;
                case 2 :
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:671:5: rewrite_tree_atom startEndStt= ebnfSuffix
                    {
                    pushFollow(FOLLOW_rewrite_tree_atom_in_rewrite_tree_element4078);
                    rewrite_tree_atom149=rewrite_tree_atom();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_rewrite_tree_atom.add(rewrite_tree_atom149.getTree());
                    pushFollow(FOLLOW_ebnfSuffix_in_rewrite_tree_element4082);
                    startEndStt=ebnfSuffix();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_ebnfSuffix.add(startEndStt.getTree());


                    // AST REWRITE
                    // elements: ebnfSuffix, rewrite_tree_atom
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 672:5: -> ^( ebnfSuffix ^( BLOCK[$startEndStt.tree.getToken()] ^( ALT[$startEndStt.tree.getToken()] rewrite_tree_atom EOA[$startEndStt.tree.getToken()] ) EOB[$startEndStt.tree.getToken()] ) )
                    {
                        // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:673:7: ^( ebnfSuffix ^( BLOCK[$startEndStt.tree.getToken()] ^( ALT[$startEndStt.tree.getToken()] rewrite_tree_atom EOA[$startEndStt.tree.getToken()] ) EOB[$startEndStt.tree.getToken()] ) )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(stream_ebnfSuffix.nextNode(), root_1);

                        // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:675:9: ^( BLOCK[$startEndStt.tree.getToken()] ^( ALT[$startEndStt.tree.getToken()] rewrite_tree_atom EOA[$startEndStt.tree.getToken()] ) EOB[$startEndStt.tree.getToken()] )
                        {
                        CommonTree root_2 = (CommonTree)adaptor.nil();
                        root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(BLOCK, (startEndStt!=null?((CommonTree)startEndStt.tree):null).getToken()), root_2);

                        // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:677:11: ^( ALT[$startEndStt.tree.getToken()] rewrite_tree_atom EOA[$startEndStt.tree.getToken()] )
                        {
                        CommonTree root_3 = (CommonTree)adaptor.nil();
                        root_3 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ALT, (startEndStt!=null?((CommonTree)startEndStt.tree):null).getToken()), root_3);

                        adaptor.addChild(root_3, stream_rewrite_tree_atom.nextTree());
                        adaptor.addChild(root_3, (CommonTree)adaptor.create(EOA, (startEndStt!=null?((CommonTree)startEndStt.tree):null).getToken()));

                        adaptor.addChild(root_2, root_3);
                        }
                        adaptor.addChild(root_2, (CommonTree)adaptor.create(EOB, (startEndStt!=null?((CommonTree)startEndStt.tree):null).getToken()));

                        adaptor.addChild(root_1, root_2);
                        }

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 3 :
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:681:5: rewrite_tree (startEndStt= ebnfSuffix -> ^( ebnfSuffix ^( BLOCK[$startEndStt.tree.getToken()] ^( ALT[$startEndStt.tree.getToken()] rewrite_tree EOA[$startEndStt.tree.getToken()] ) EOB[$startEndStt.tree.getToken()] ) ) | -> rewrite_tree )
                    {
                    pushFollow(FOLLOW_rewrite_tree_in_rewrite_tree_element4188);
                    rewrite_tree150=rewrite_tree();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_rewrite_tree.add(rewrite_tree150.getTree());
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:682:3: (startEndStt= ebnfSuffix -> ^( ebnfSuffix ^( BLOCK[$startEndStt.tree.getToken()] ^( ALT[$startEndStt.tree.getToken()] rewrite_tree EOA[$startEndStt.tree.getToken()] ) EOB[$startEndStt.tree.getToken()] ) ) | -> rewrite_tree )
                    int alt73=2;
                    int LA73_0 = input.LA(1);

                    if ( (LA73_0==78||(LA73_0>=92 && LA73_0<=93)) ) {
                        alt73=1;
                    }
                    else if ( (LA73_0==EOF||LA73_0==DOC_COMMENT||(LA73_0>=FRAGMENT && LA73_0<=TREE_BEGIN)||(LA73_0>=REWRITE && LA73_0<=SEMI)||(LA73_0>=TOKEN_REF && LA73_0<=ACTION)||LA73_0==RULE_REF||(LA73_0>=80 && LA73_0<=82)||(LA73_0>=84 && LA73_0<=88)||LA73_0==96) ) {
                        alt73=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 73, 0, input);

                        throw nvae;
                    }
                    switch (alt73) {
                        case 1 :
                            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:683:5: startEndStt= ebnfSuffix
                            {
                            pushFollow(FOLLOW_ebnfSuffix_in_rewrite_tree_element4200);
                            startEndStt=ebnfSuffix();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_ebnfSuffix.add(startEndStt.getTree());


                            // AST REWRITE
                            // elements: ebnfSuffix, rewrite_tree
                            // token labels: 
                            // rule labels: retval
                            // token list labels: 
                            // rule list labels: 
                            // wildcard labels: 
                            if ( state.backtracking==0 ) {
                            retval.tree = root_0;
                            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                            root_0 = (CommonTree)adaptor.nil();
                            // 684:7: -> ^( ebnfSuffix ^( BLOCK[$startEndStt.tree.getToken()] ^( ALT[$startEndStt.tree.getToken()] rewrite_tree EOA[$startEndStt.tree.getToken()] ) EOB[$startEndStt.tree.getToken()] ) )
                            {
                                // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:685:9: ^( ebnfSuffix ^( BLOCK[$startEndStt.tree.getToken()] ^( ALT[$startEndStt.tree.getToken()] rewrite_tree EOA[$startEndStt.tree.getToken()] ) EOB[$startEndStt.tree.getToken()] ) )
                                {
                                CommonTree root_1 = (CommonTree)adaptor.nil();
                                root_1 = (CommonTree)adaptor.becomeRoot(stream_ebnfSuffix.nextNode(), root_1);

                                // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:687:11: ^( BLOCK[$startEndStt.tree.getToken()] ^( ALT[$startEndStt.tree.getToken()] rewrite_tree EOA[$startEndStt.tree.getToken()] ) EOB[$startEndStt.tree.getToken()] )
                                {
                                CommonTree root_2 = (CommonTree)adaptor.nil();
                                root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(BLOCK, (startEndStt!=null?((CommonTree)startEndStt.tree):null).getToken()), root_2);

                                // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:689:13: ^( ALT[$startEndStt.tree.getToken()] rewrite_tree EOA[$startEndStt.tree.getToken()] )
                                {
                                CommonTree root_3 = (CommonTree)adaptor.nil();
                                root_3 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ALT, (startEndStt!=null?((CommonTree)startEndStt.tree):null).getToken()), root_3);

                                adaptor.addChild(root_3, stream_rewrite_tree.nextTree());
                                adaptor.addChild(root_3, (CommonTree)adaptor.create(EOA, (startEndStt!=null?((CommonTree)startEndStt.tree):null).getToken()));

                                adaptor.addChild(root_2, root_3);
                                }
                                adaptor.addChild(root_2, (CommonTree)adaptor.create(EOB, (startEndStt!=null?((CommonTree)startEndStt.tree):null).getToken()));

                                adaptor.addChild(root_1, root_2);
                                }

                                adaptor.addChild(root_0, root_1);
                                }

                            }

                            retval.tree = root_0;}
                            }
                            break;
                        case 2 :
                            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:694:7: 
                            {

                            // AST REWRITE
                            // elements: rewrite_tree
                            // token labels: 
                            // rule labels: retval
                            // token list labels: 
                            // rule list labels: 
                            // wildcard labels: 
                            if ( state.backtracking==0 ) {
                            retval.tree = root_0;
                            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                            root_0 = (CommonTree)adaptor.nil();
                            // 694:7: -> rewrite_tree
                            {
                                adaptor.addChild(root_0, stream_rewrite_tree.nextTree());

                            }

                            retval.tree = root_0;}
                            }
                            break;

                    }


                    }
                    break;
                case 4 :
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:696:5: rewrite_tree_ebnf
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_rewrite_tree_ebnf_in_rewrite_tree_element4344);
                    rewrite_tree_ebnf151=rewrite_tree_ebnf();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, rewrite_tree_ebnf151.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "rewrite_tree_element"

    public static class rewrite_tree_atom_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "rewrite_tree_atom"
    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:699:1: rewrite_tree_atom : ( TOKEN_REF ( elementOptions )? ( ARG_ACTION )? -> ^( TOKEN_REF ( elementOptions )? ( ARG_ACTION )? ) | RULE_REF | STRING_LITERAL ( elementOptions )? -> ^( STRING_LITERAL ( elementOptions )? ) | '$' id -> LABEL[$id.start, $id.text] | ACTION );
    public final ANTLRParser.rewrite_tree_atom_return rewrite_tree_atom() throws RecognitionException {
        ANTLRParser.rewrite_tree_atom_return retval = new ANTLRParser.rewrite_tree_atom_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token TOKEN_REF152=null;
        Token ARG_ACTION154=null;
        Token RULE_REF155=null;
        Token STRING_LITERAL156=null;
        Token char_literal158=null;
        Token ACTION160=null;
        ANTLRParser.elementOptions_return elementOptions153 = null;

        ANTLRParser.elementOptions_return elementOptions157 = null;

        ANTLRParser.id_return id159 = null;


        CommonTree TOKEN_REF152_tree=null;
        CommonTree ARG_ACTION154_tree=null;
        CommonTree RULE_REF155_tree=null;
        CommonTree STRING_LITERAL156_tree=null;
        CommonTree char_literal158_tree=null;
        CommonTree ACTION160_tree=null;
        RewriteRuleTokenStream stream_96=new RewriteRuleTokenStream(adaptor,"token 96");
        RewriteRuleTokenStream stream_STRING_LITERAL=new RewriteRuleTokenStream(adaptor,"token STRING_LITERAL");
        RewriteRuleTokenStream stream_TOKEN_REF=new RewriteRuleTokenStream(adaptor,"token TOKEN_REF");
        RewriteRuleTokenStream stream_ARG_ACTION=new RewriteRuleTokenStream(adaptor,"token ARG_ACTION");
        RewriteRuleSubtreeStream stream_id=new RewriteRuleSubtreeStream(adaptor,"rule id");
        RewriteRuleSubtreeStream stream_elementOptions=new RewriteRuleSubtreeStream(adaptor,"rule elementOptions");
        try {
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:700:3: ( TOKEN_REF ( elementOptions )? ( ARG_ACTION )? -> ^( TOKEN_REF ( elementOptions )? ( ARG_ACTION )? ) | RULE_REF | STRING_LITERAL ( elementOptions )? -> ^( STRING_LITERAL ( elementOptions )? ) | '$' id -> LABEL[$id.start, $id.text] | ACTION )
            int alt78=5;
            switch ( input.LA(1) ) {
            case TOKEN_REF:
                {
                alt78=1;
                }
                break;
            case RULE_REF:
                {
                alt78=2;
                }
                break;
            case STRING_LITERAL:
                {
                alt78=3;
                }
                break;
            case 96:
                {
                alt78=4;
                }
                break;
            case ACTION:
                {
                alt78=5;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 78, 0, input);

                throw nvae;
            }

            switch (alt78) {
                case 1 :
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:701:3: TOKEN_REF ( elementOptions )? ( ARG_ACTION )?
                    {
                    TOKEN_REF152=(Token)match(input,TOKEN_REF,FOLLOW_TOKEN_REF_in_rewrite_tree_atom4359); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_TOKEN_REF.add(TOKEN_REF152);

                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:701:13: ( elementOptions )?
                    int alt75=2;
                    int LA75_0 = input.LA(1);

                    if ( (LA75_0==94) ) {
                        alt75=1;
                    }
                    switch (alt75) {
                        case 1 :
                            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:701:13: elementOptions
                            {
                            pushFollow(FOLLOW_elementOptions_in_rewrite_tree_atom4361);
                            elementOptions153=elementOptions();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_elementOptions.add(elementOptions153.getTree());

                            }
                            break;

                    }

                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:701:29: ( ARG_ACTION )?
                    int alt76=2;
                    int LA76_0 = input.LA(1);

                    if ( (LA76_0==ARG_ACTION) ) {
                        alt76=1;
                    }
                    switch (alt76) {
                        case 1 :
                            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:701:29: ARG_ACTION
                            {
                            ARG_ACTION154=(Token)match(input,ARG_ACTION,FOLLOW_ARG_ACTION_in_rewrite_tree_atom4364); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_ARG_ACTION.add(ARG_ACTION154);


                            }
                            break;

                    }



                    // AST REWRITE
                    // elements: ARG_ACTION, TOKEN_REF, elementOptions
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 702:5: -> ^( TOKEN_REF ( elementOptions )? ( ARG_ACTION )? )
                    {
                        // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:703:7: ^( TOKEN_REF ( elementOptions )? ( ARG_ACTION )? )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(stream_TOKEN_REF.nextNode(), root_1);

                        // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:703:19: ( elementOptions )?
                        if ( stream_elementOptions.hasNext() ) {
                            adaptor.addChild(root_1, stream_elementOptions.nextTree());

                        }
                        stream_elementOptions.reset();
                        // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:703:35: ( ARG_ACTION )?
                        if ( stream_ARG_ACTION.hasNext() ) {
                            adaptor.addChild(root_1, stream_ARG_ACTION.nextNode());

                        }
                        stream_ARG_ACTION.reset();

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 2 :
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:704:5: RULE_REF
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    RULE_REF155=(Token)match(input,RULE_REF,FOLLOW_RULE_REF_in_rewrite_tree_atom4394); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    RULE_REF155_tree = (CommonTree)adaptor.create(RULE_REF155);
                    adaptor.addChild(root_0, RULE_REF155_tree);
                    }

                    }
                    break;
                case 3 :
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:705:5: STRING_LITERAL ( elementOptions )?
                    {
                    STRING_LITERAL156=(Token)match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_rewrite_tree_atom4400); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_STRING_LITERAL.add(STRING_LITERAL156);

                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:705:20: ( elementOptions )?
                    int alt77=2;
                    int LA77_0 = input.LA(1);

                    if ( (LA77_0==94) ) {
                        alt77=1;
                    }
                    switch (alt77) {
                        case 1 :
                            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:705:20: elementOptions
                            {
                            pushFollow(FOLLOW_elementOptions_in_rewrite_tree_atom4402);
                            elementOptions157=elementOptions();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_elementOptions.add(elementOptions157.getTree());

                            }
                            break;

                    }



                    // AST REWRITE
                    // elements: STRING_LITERAL, elementOptions
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 706:5: -> ^( STRING_LITERAL ( elementOptions )? )
                    {
                        // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:707:7: ^( STRING_LITERAL ( elementOptions )? )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(stream_STRING_LITERAL.nextNode(), root_1);

                        // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:707:24: ( elementOptions )?
                        if ( stream_elementOptions.hasNext() ) {
                            adaptor.addChild(root_1, stream_elementOptions.nextTree());

                        }
                        stream_elementOptions.reset();

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 4 :
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:708:5: '$' id
                    {
                    char_literal158=(Token)match(input,96,FOLLOW_96_in_rewrite_tree_atom4428); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_96.add(char_literal158);

                    pushFollow(FOLLOW_id_in_rewrite_tree_atom4430);
                    id159=id();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_id.add(id159.getTree());


                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 709:5: -> LABEL[$id.start, $id.text]
                    {
                        adaptor.addChild(root_0, (CommonTree)adaptor.create(LABEL, (id159!=null?((Token)id159.start):null), (id159!=null?input.toString(id159.start,id159.stop):null)));

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 5 :
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:710:5: ACTION
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    ACTION160=(Token)match(input,ACTION,FOLLOW_ACTION_in_rewrite_tree_atom4446); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    ACTION160_tree = (CommonTree)adaptor.create(ACTION160);
                    adaptor.addChild(root_0, ACTION160_tree);
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "rewrite_tree_atom"

    public static class rewrite_tree_ebnf_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "rewrite_tree_ebnf"
    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:713:1: rewrite_tree_ebnf : rewrite_tree_block ebnfSuffix -> ^( ebnfSuffix rewrite_tree_block ) ;
    public final ANTLRParser.rewrite_tree_ebnf_return rewrite_tree_ebnf() throws RecognitionException {
        ANTLRParser.rewrite_tree_ebnf_return retval = new ANTLRParser.rewrite_tree_ebnf_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        ANTLRParser.rewrite_tree_block_return rewrite_tree_block161 = null;

        ANTLRParser.ebnfSuffix_return ebnfSuffix162 = null;


        RewriteRuleSubtreeStream stream_ebnfSuffix=new RewriteRuleSubtreeStream(adaptor,"rule ebnfSuffix");
        RewriteRuleSubtreeStream stream_rewrite_tree_block=new RewriteRuleSubtreeStream(adaptor,"rule rewrite_tree_block");

            Token firstToken = input.LT(1);

        try {
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:721:3: ( rewrite_tree_block ebnfSuffix -> ^( ebnfSuffix rewrite_tree_block ) )
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:722:3: rewrite_tree_block ebnfSuffix
            {
            pushFollow(FOLLOW_rewrite_tree_block_in_rewrite_tree_ebnf4471);
            rewrite_tree_block161=rewrite_tree_block();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_rewrite_tree_block.add(rewrite_tree_block161.getTree());
            pushFollow(FOLLOW_ebnfSuffix_in_rewrite_tree_ebnf4473);
            ebnfSuffix162=ebnfSuffix();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_ebnfSuffix.add(ebnfSuffix162.getTree());


            // AST REWRITE
            // elements: ebnfSuffix, rewrite_tree_block
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 723:5: -> ^( ebnfSuffix rewrite_tree_block )
            {
                // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:724:7: ^( ebnfSuffix rewrite_tree_block )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(stream_ebnfSuffix.nextNode(), root_1);

                adaptor.addChild(root_1, stream_rewrite_tree_block.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

                ((CommonTree)retval.tree).getToken().setLine(firstToken.getLine());
                ((CommonTree)retval.tree).getToken().setCharPositionInLine(firstToken.getCharPositionInLine());

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "rewrite_tree_ebnf"

    public static class rewrite_tree_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "rewrite_tree"
    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:727:1: rewrite_tree : '^(' rewrite_tree_atom ( rewrite_tree_element )* ')' -> ^( TREE_BEGIN rewrite_tree_atom ( rewrite_tree_element )* ) ;
    public final ANTLRParser.rewrite_tree_return rewrite_tree() throws RecognitionException {
        ANTLRParser.rewrite_tree_return retval = new ANTLRParser.rewrite_tree_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token string_literal163=null;
        Token char_literal166=null;
        ANTLRParser.rewrite_tree_atom_return rewrite_tree_atom164 = null;

        ANTLRParser.rewrite_tree_element_return rewrite_tree_element165 = null;


        CommonTree string_literal163_tree=null;
        CommonTree char_literal166_tree=null;
        RewriteRuleTokenStream stream_TREE_BEGIN=new RewriteRuleTokenStream(adaptor,"token TREE_BEGIN");
        RewriteRuleTokenStream stream_86=new RewriteRuleTokenStream(adaptor,"token 86");
        RewriteRuleSubtreeStream stream_rewrite_tree_element=new RewriteRuleSubtreeStream(adaptor,"rule rewrite_tree_element");
        RewriteRuleSubtreeStream stream_rewrite_tree_atom=new RewriteRuleSubtreeStream(adaptor,"rule rewrite_tree_atom");
        try {
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:728:3: ( '^(' rewrite_tree_atom ( rewrite_tree_element )* ')' -> ^( TREE_BEGIN rewrite_tree_atom ( rewrite_tree_element )* ) )
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:729:3: '^(' rewrite_tree_atom ( rewrite_tree_element )* ')'
            {
            string_literal163=(Token)match(input,TREE_BEGIN,FOLLOW_TREE_BEGIN_in_rewrite_tree4506); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_TREE_BEGIN.add(string_literal163);

            pushFollow(FOLLOW_rewrite_tree_atom_in_rewrite_tree4508);
            rewrite_tree_atom164=rewrite_tree_atom();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_rewrite_tree_atom.add(rewrite_tree_atom164.getTree());
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:729:26: ( rewrite_tree_element )*
            loop79:
            do {
                int alt79=2;
                int LA79_0 = input.LA(1);

                if ( (LA79_0==TREE_BEGIN||(LA79_0>=TOKEN_REF && LA79_0<=ACTION)||LA79_0==RULE_REF||LA79_0==84||LA79_0==96) ) {
                    alt79=1;
                }


                switch (alt79) {
            	case 1 :
            	    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:729:26: rewrite_tree_element
            	    {
            	    pushFollow(FOLLOW_rewrite_tree_element_in_rewrite_tree4510);
            	    rewrite_tree_element165=rewrite_tree_element();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_rewrite_tree_element.add(rewrite_tree_element165.getTree());

            	    }
            	    break;

            	default :
            	    break loop79;
                }
            } while (true);

            char_literal166=(Token)match(input,86,FOLLOW_86_in_rewrite_tree4513); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_86.add(char_literal166);



            // AST REWRITE
            // elements: rewrite_tree_element, rewrite_tree_atom
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 730:5: -> ^( TREE_BEGIN rewrite_tree_atom ( rewrite_tree_element )* )
            {
                // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:731:7: ^( TREE_BEGIN rewrite_tree_atom ( rewrite_tree_element )* )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TREE_BEGIN, "TREE_BEGIN"), root_1);

                adaptor.addChild(root_1, stream_rewrite_tree_atom.nextTree());
                // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:731:38: ( rewrite_tree_element )*
                while ( stream_rewrite_tree_element.hasNext() ) {
                    adaptor.addChild(root_1, stream_rewrite_tree_element.nextTree());

                }
                stream_rewrite_tree_element.reset();

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "rewrite_tree"

    public static class rewrite_template_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "rewrite_template"
    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:734:1: rewrite_template : ( id lp= '(' rewrite_template_args ')' (str= DOUBLE_QUOTE_STRING_LITERAL | str= DOUBLE_ANGLE_STRING_LITERAL ) -> ^( TEMPLATE[$lp,\"TEMPLATE\"] id rewrite_template_args $str) | rewrite_template_ref | rewrite_indirect_template_head | ACTION );
    public final ANTLRParser.rewrite_template_return rewrite_template() throws RecognitionException {
        ANTLRParser.rewrite_template_return retval = new ANTLRParser.rewrite_template_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token lp=null;
        Token str=null;
        Token char_literal169=null;
        Token ACTION172=null;
        ANTLRParser.id_return id167 = null;

        ANTLRParser.rewrite_template_args_return rewrite_template_args168 = null;

        ANTLRParser.rewrite_template_ref_return rewrite_template_ref170 = null;

        ANTLRParser.rewrite_indirect_template_head_return rewrite_indirect_template_head171 = null;


        CommonTree lp_tree=null;
        CommonTree str_tree=null;
        CommonTree char_literal169_tree=null;
        CommonTree ACTION172_tree=null;
        RewriteRuleTokenStream stream_DOUBLE_QUOTE_STRING_LITERAL=new RewriteRuleTokenStream(adaptor,"token DOUBLE_QUOTE_STRING_LITERAL");
        RewriteRuleTokenStream stream_DOUBLE_ANGLE_STRING_LITERAL=new RewriteRuleTokenStream(adaptor,"token DOUBLE_ANGLE_STRING_LITERAL");
        RewriteRuleTokenStream stream_86=new RewriteRuleTokenStream(adaptor,"token 86");
        RewriteRuleTokenStream stream_84=new RewriteRuleTokenStream(adaptor,"token 84");
        RewriteRuleSubtreeStream stream_id=new RewriteRuleSubtreeStream(adaptor,"rule id");
        RewriteRuleSubtreeStream stream_rewrite_template_args=new RewriteRuleSubtreeStream(adaptor,"rule rewrite_template_args");
        try {
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:746:3: ( id lp= '(' rewrite_template_args ')' (str= DOUBLE_QUOTE_STRING_LITERAL | str= DOUBLE_ANGLE_STRING_LITERAL ) -> ^( TEMPLATE[$lp,\"TEMPLATE\"] id rewrite_template_args $str) | rewrite_template_ref | rewrite_indirect_template_head | ACTION )
            int alt81=4;
            alt81 = dfa81.predict(input);
            switch (alt81) {
                case 1 :
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:747:3: id lp= '(' rewrite_template_args ')' (str= DOUBLE_QUOTE_STRING_LITERAL | str= DOUBLE_ANGLE_STRING_LITERAL )
                    {
                    pushFollow(FOLLOW_id_in_rewrite_template4552);
                    id167=id();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_id.add(id167.getTree());
                    lp=(Token)match(input,84,FOLLOW_84_in_rewrite_template4556); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_84.add(lp);

                    pushFollow(FOLLOW_rewrite_template_args_in_rewrite_template4558);
                    rewrite_template_args168=rewrite_template_args();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_rewrite_template_args.add(rewrite_template_args168.getTree());
                    char_literal169=(Token)match(input,86,FOLLOW_86_in_rewrite_template4560); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_86.add(char_literal169);

                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:748:3: (str= DOUBLE_QUOTE_STRING_LITERAL | str= DOUBLE_ANGLE_STRING_LITERAL )
                    int alt80=2;
                    int LA80_0 = input.LA(1);

                    if ( (LA80_0==DOUBLE_QUOTE_STRING_LITERAL) ) {
                        alt80=1;
                    }
                    else if ( (LA80_0==DOUBLE_ANGLE_STRING_LITERAL) ) {
                        alt80=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 80, 0, input);

                        throw nvae;
                    }
                    switch (alt80) {
                        case 1 :
                            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:749:5: str= DOUBLE_QUOTE_STRING_LITERAL
                            {
                            str=(Token)match(input,DOUBLE_QUOTE_STRING_LITERAL,FOLLOW_DOUBLE_QUOTE_STRING_LITERAL_in_rewrite_template4572); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_DOUBLE_QUOTE_STRING_LITERAL.add(str);


                            }
                            break;
                        case 2 :
                            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:750:7: str= DOUBLE_ANGLE_STRING_LITERAL
                            {
                            str=(Token)match(input,DOUBLE_ANGLE_STRING_LITERAL,FOLLOW_DOUBLE_ANGLE_STRING_LITERAL_in_rewrite_template4582); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_DOUBLE_ANGLE_STRING_LITERAL.add(str);


                            }
                            break;

                    }



                    // AST REWRITE
                    // elements: rewrite_template_args, id, str
                    // token labels: str
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleTokenStream stream_str=new RewriteRuleTokenStream(adaptor,"token str",str);
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 752:5: -> ^( TEMPLATE[$lp,\"TEMPLATE\"] id rewrite_template_args $str)
                    {
                        // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:753:7: ^( TEMPLATE[$lp,\"TEMPLATE\"] id rewrite_template_args $str)
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TEMPLATE, lp, "TEMPLATE"), root_1);

                        adaptor.addChild(root_1, stream_id.nextTree());
                        adaptor.addChild(root_1, stream_rewrite_template_args.nextTree());
                        adaptor.addChild(root_1, stream_str.nextNode());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 2 :
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:755:4: rewrite_template_ref
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_rewrite_template_ref_in_rewrite_template4620);
                    rewrite_template_ref170=rewrite_template_ref();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, rewrite_template_ref170.getTree());

                    }
                    break;
                case 3 :
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:757:4: rewrite_indirect_template_head
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_rewrite_indirect_template_head_in_rewrite_template4630);
                    rewrite_indirect_template_head171=rewrite_indirect_template_head();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, rewrite_indirect_template_head171.getTree());

                    }
                    break;
                case 4 :
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:759:4: ACTION
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    ACTION172=(Token)match(input,ACTION,FOLLOW_ACTION_in_rewrite_template4640); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    ACTION172_tree = (CommonTree)adaptor.create(ACTION172);
                    adaptor.addChild(root_0, ACTION172_tree);
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "rewrite_template"

    public static class rewrite_template_ref_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "rewrite_template_ref"
    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:762:1: rewrite_template_ref : id lp= '(' rewrite_template_args ')' -> ^( TEMPLATE[$lp,\"TEMPLATE\"] id rewrite_template_args ) ;
    public final ANTLRParser.rewrite_template_ref_return rewrite_template_ref() throws RecognitionException {
        ANTLRParser.rewrite_template_ref_return retval = new ANTLRParser.rewrite_template_ref_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token lp=null;
        Token char_literal175=null;
        ANTLRParser.id_return id173 = null;

        ANTLRParser.rewrite_template_args_return rewrite_template_args174 = null;


        CommonTree lp_tree=null;
        CommonTree char_literal175_tree=null;
        RewriteRuleTokenStream stream_86=new RewriteRuleTokenStream(adaptor,"token 86");
        RewriteRuleTokenStream stream_84=new RewriteRuleTokenStream(adaptor,"token 84");
        RewriteRuleSubtreeStream stream_id=new RewriteRuleSubtreeStream(adaptor,"rule id");
        RewriteRuleSubtreeStream stream_rewrite_template_args=new RewriteRuleSubtreeStream(adaptor,"rule rewrite_template_args");
        try {
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:764:3: ( id lp= '(' rewrite_template_args ')' -> ^( TEMPLATE[$lp,\"TEMPLATE\"] id rewrite_template_args ) )
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:765:3: id lp= '(' rewrite_template_args ')'
            {
            pushFollow(FOLLOW_id_in_rewrite_template_ref4657);
            id173=id();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_id.add(id173.getTree());
            lp=(Token)match(input,84,FOLLOW_84_in_rewrite_template_ref4661); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_84.add(lp);

            pushFollow(FOLLOW_rewrite_template_args_in_rewrite_template_ref4663);
            rewrite_template_args174=rewrite_template_args();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_rewrite_template_args.add(rewrite_template_args174.getTree());
            char_literal175=(Token)match(input,86,FOLLOW_86_in_rewrite_template_ref4665); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_86.add(char_literal175);



            // AST REWRITE
            // elements: id, rewrite_template_args
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 766:5: -> ^( TEMPLATE[$lp,\"TEMPLATE\"] id rewrite_template_args )
            {
                // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:767:7: ^( TEMPLATE[$lp,\"TEMPLATE\"] id rewrite_template_args )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TEMPLATE, lp, "TEMPLATE"), root_1);

                adaptor.addChild(root_1, stream_id.nextTree());
                adaptor.addChild(root_1, stream_rewrite_template_args.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "rewrite_template_ref"

    public static class rewrite_indirect_template_head_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "rewrite_indirect_template_head"
    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:770:1: rewrite_indirect_template_head : lp= '(' ACTION ')' '(' rewrite_template_args ')' -> ^( TEMPLATE[$lp,\"TEMPLATE\"] ACTION rewrite_template_args ) ;
    public final ANTLRParser.rewrite_indirect_template_head_return rewrite_indirect_template_head() throws RecognitionException {
        ANTLRParser.rewrite_indirect_template_head_return retval = new ANTLRParser.rewrite_indirect_template_head_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token lp=null;
        Token ACTION176=null;
        Token char_literal177=null;
        Token char_literal178=null;
        Token char_literal180=null;
        ANTLRParser.rewrite_template_args_return rewrite_template_args179 = null;


        CommonTree lp_tree=null;
        CommonTree ACTION176_tree=null;
        CommonTree char_literal177_tree=null;
        CommonTree char_literal178_tree=null;
        CommonTree char_literal180_tree=null;
        RewriteRuleTokenStream stream_ACTION=new RewriteRuleTokenStream(adaptor,"token ACTION");
        RewriteRuleTokenStream stream_86=new RewriteRuleTokenStream(adaptor,"token 86");
        RewriteRuleTokenStream stream_84=new RewriteRuleTokenStream(adaptor,"token 84");
        RewriteRuleSubtreeStream stream_rewrite_template_args=new RewriteRuleSubtreeStream(adaptor,"rule rewrite_template_args");
        try {
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:772:3: (lp= '(' ACTION ')' '(' rewrite_template_args ')' -> ^( TEMPLATE[$lp,\"TEMPLATE\"] ACTION rewrite_template_args ) )
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:773:3: lp= '(' ACTION ')' '(' rewrite_template_args ')'
            {
            lp=(Token)match(input,84,FOLLOW_84_in_rewrite_indirect_template_head4705); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_84.add(lp);

            ACTION176=(Token)match(input,ACTION,FOLLOW_ACTION_in_rewrite_indirect_template_head4707); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ACTION.add(ACTION176);

            char_literal177=(Token)match(input,86,FOLLOW_86_in_rewrite_indirect_template_head4709); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_86.add(char_literal177);

            char_literal178=(Token)match(input,84,FOLLOW_84_in_rewrite_indirect_template_head4711); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_84.add(char_literal178);

            pushFollow(FOLLOW_rewrite_template_args_in_rewrite_indirect_template_head4713);
            rewrite_template_args179=rewrite_template_args();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_rewrite_template_args.add(rewrite_template_args179.getTree());
            char_literal180=(Token)match(input,86,FOLLOW_86_in_rewrite_indirect_template_head4715); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_86.add(char_literal180);



            // AST REWRITE
            // elements: ACTION, rewrite_template_args
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 774:5: -> ^( TEMPLATE[$lp,\"TEMPLATE\"] ACTION rewrite_template_args )
            {
                // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:775:7: ^( TEMPLATE[$lp,\"TEMPLATE\"] ACTION rewrite_template_args )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TEMPLATE, lp, "TEMPLATE"), root_1);

                adaptor.addChild(root_1, stream_ACTION.nextNode());
                adaptor.addChild(root_1, stream_rewrite_template_args.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "rewrite_indirect_template_head"

    public static class rewrite_template_args_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "rewrite_template_args"
    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:778:1: rewrite_template_args : ( rewrite_template_arg ( ',' rewrite_template_arg )* -> ^( ARGLIST ( rewrite_template_arg )+ ) | -> ARGLIST );
    public final ANTLRParser.rewrite_template_args_return rewrite_template_args() throws RecognitionException {
        ANTLRParser.rewrite_template_args_return retval = new ANTLRParser.rewrite_template_args_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token char_literal182=null;
        ANTLRParser.rewrite_template_arg_return rewrite_template_arg181 = null;

        ANTLRParser.rewrite_template_arg_return rewrite_template_arg183 = null;


        CommonTree char_literal182_tree=null;
        RewriteRuleTokenStream stream_74=new RewriteRuleTokenStream(adaptor,"token 74");
        RewriteRuleSubtreeStream stream_rewrite_template_arg=new RewriteRuleSubtreeStream(adaptor,"rule rewrite_template_arg");
        try {
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:779:3: ( rewrite_template_arg ( ',' rewrite_template_arg )* -> ^( ARGLIST ( rewrite_template_arg )+ ) | -> ARGLIST )
            int alt83=2;
            int LA83_0 = input.LA(1);

            if ( (LA83_0==TOKEN_REF||LA83_0==RULE_REF) ) {
                alt83=1;
            }
            else if ( (LA83_0==86) ) {
                alt83=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 83, 0, input);

                throw nvae;
            }
            switch (alt83) {
                case 1 :
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:780:3: rewrite_template_arg ( ',' rewrite_template_arg )*
                    {
                    pushFollow(FOLLOW_rewrite_template_arg_in_rewrite_template_args4751);
                    rewrite_template_arg181=rewrite_template_arg();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_rewrite_template_arg.add(rewrite_template_arg181.getTree());
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:780:24: ( ',' rewrite_template_arg )*
                    loop82:
                    do {
                        int alt82=2;
                        int LA82_0 = input.LA(1);

                        if ( (LA82_0==74) ) {
                            alt82=1;
                        }


                        switch (alt82) {
                    	case 1 :
                    	    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:780:25: ',' rewrite_template_arg
                    	    {
                    	    char_literal182=(Token)match(input,74,FOLLOW_74_in_rewrite_template_args4754); if (state.failed) return retval; 
                    	    if ( state.backtracking==0 ) stream_74.add(char_literal182);

                    	    pushFollow(FOLLOW_rewrite_template_arg_in_rewrite_template_args4756);
                    	    rewrite_template_arg183=rewrite_template_arg();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) stream_rewrite_template_arg.add(rewrite_template_arg183.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop82;
                        }
                    } while (true);



                    // AST REWRITE
                    // elements: rewrite_template_arg
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 781:5: -> ^( ARGLIST ( rewrite_template_arg )+ )
                    {
                        // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:782:7: ^( ARGLIST ( rewrite_template_arg )+ )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ARGLIST, "ARGLIST"), root_1);

                        if ( !(stream_rewrite_template_arg.hasNext()) ) {
                            throw new RewriteEarlyExitException();
                        }
                        while ( stream_rewrite_template_arg.hasNext() ) {
                            adaptor.addChild(root_1, stream_rewrite_template_arg.nextTree());

                        }
                        stream_rewrite_template_arg.reset();

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 2 :
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:784:5: 
                    {

                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 784:5: -> ARGLIST
                    {
                        adaptor.addChild(root_0, (CommonTree)adaptor.create(ARGLIST, "ARGLIST"));

                    }

                    retval.tree = root_0;}
                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "rewrite_template_args"

    public static class rewrite_template_arg_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "rewrite_template_arg"
    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:787:1: rewrite_template_arg : id '=' ACTION -> ^( ARG[$id.start] id ACTION ) ;
    public final ANTLRParser.rewrite_template_arg_return rewrite_template_arg() throws RecognitionException {
        ANTLRParser.rewrite_template_arg_return retval = new ANTLRParser.rewrite_template_arg_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token char_literal185=null;
        Token ACTION186=null;
        ANTLRParser.id_return id184 = null;


        CommonTree char_literal185_tree=null;
        CommonTree ACTION186_tree=null;
        RewriteRuleTokenStream stream_ACTION=new RewriteRuleTokenStream(adaptor,"token ACTION");
        RewriteRuleTokenStream stream_ASSIGN=new RewriteRuleTokenStream(adaptor,"token ASSIGN");
        RewriteRuleSubtreeStream stream_id=new RewriteRuleSubtreeStream(adaptor,"rule id");
        try {
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:788:3: ( id '=' ACTION -> ^( ARG[$id.start] id ACTION ) )
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:789:3: id '=' ACTION
            {
            pushFollow(FOLLOW_id_in_rewrite_template_arg4804);
            id184=id();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_id.add(id184.getTree());
            char_literal185=(Token)match(input,ASSIGN,FOLLOW_ASSIGN_in_rewrite_template_arg4806); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ASSIGN.add(char_literal185);

            ACTION186=(Token)match(input,ACTION,FOLLOW_ACTION_in_rewrite_template_arg4808); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ACTION.add(ACTION186);



            // AST REWRITE
            // elements: id, ACTION
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 790:5: -> ^( ARG[$id.start] id ACTION )
            {
                // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:791:7: ^( ARG[$id.start] id ACTION )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ARG, (id184!=null?((Token)id184.start):null)), root_1);

                adaptor.addChild(root_1, stream_id.nextTree());
                adaptor.addChild(root_1, stream_ACTION.nextNode());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "rewrite_template_arg"

    public static class id_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "id"
    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:794:1: id : ( TOKEN_REF -> ID[$TOKEN_REF] | RULE_REF -> ID[$RULE_REF] );
    public final ANTLRParser.id_return id() throws RecognitionException {
        ANTLRParser.id_return retval = new ANTLRParser.id_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token TOKEN_REF187=null;
        Token RULE_REF188=null;

        CommonTree TOKEN_REF187_tree=null;
        CommonTree RULE_REF188_tree=null;
        RewriteRuleTokenStream stream_RULE_REF=new RewriteRuleTokenStream(adaptor,"token RULE_REF");
        RewriteRuleTokenStream stream_TOKEN_REF=new RewriteRuleTokenStream(adaptor,"token TOKEN_REF");

        try {
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:795:3: ( TOKEN_REF -> ID[$TOKEN_REF] | RULE_REF -> ID[$RULE_REF] )
            int alt84=2;
            int LA84_0 = input.LA(1);

            if ( (LA84_0==TOKEN_REF) ) {
                alt84=1;
            }
            else if ( (LA84_0==RULE_REF) ) {
                alt84=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 84, 0, input);

                throw nvae;
            }
            switch (alt84) {
                case 1 :
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:796:3: TOKEN_REF
                    {
                    TOKEN_REF187=(Token)match(input,TOKEN_REF,FOLLOW_TOKEN_REF_in_id4844); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_TOKEN_REF.add(TOKEN_REF187);



                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 797:5: -> ID[$TOKEN_REF]
                    {
                        adaptor.addChild(root_0, (CommonTree)adaptor.create(ID, TOKEN_REF187));

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 2 :
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:798:5: RULE_REF
                    {
                    RULE_REF188=(Token)match(input,RULE_REF,FOLLOW_RULE_REF_in_id4859); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RULE_REF.add(RULE_REF188);



                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 799:5: -> ID[$RULE_REF]
                    {
                        adaptor.addChild(root_0, (CommonTree)adaptor.create(ID, RULE_REF188));

                    }

                    retval.tree = root_0;}
                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "id"

    // $ANTLR start synpred1_ANTLR
    public final void synpred1_ANTLR_fragment() throws RecognitionException {   
        // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:644:3: ({...}? rewrite_template )
        // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:644:3: {...}? rewrite_template
        {
        if ( !((templateOutput)) ) {
            if (state.backtracking>0) {state.failed=true; return ;}
            throw new FailedPredicateException(input, "synpred1_ANTLR", "templateOutput");
        }
        pushFollow(FOLLOW_rewrite_template_in_synpred1_ANTLR3934);
        rewrite_template();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred1_ANTLR

    // $ANTLR start synpred2_ANTLR
    public final void synpred2_ANTLR_fragment() throws RecognitionException {   
        // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:645:5: ( rewrite_tree_alternative )
        // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:645:5: rewrite_tree_alternative
        {
        pushFollow(FOLLOW_rewrite_tree_alternative_in_synpred2_ANTLR3940);
        rewrite_tree_alternative();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred2_ANTLR

    // Delegated rules

    public final boolean synpred1_ANTLR() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred1_ANTLR_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred2_ANTLR() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred2_ANTLR_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }


    protected DFA48 dfa48 = new DFA48(this);
    protected DFA71 dfa71 = new DFA71(this);
    protected DFA74 dfa74 = new DFA74(this);
    protected DFA81 dfa81 = new DFA81(this);
    static final String DFA48_eotS =
        "\14\uffff";
    static final String DFA48_eofS =
        "\1\uffff\2\3\11\uffff";
    static final String DFA48_minS =
        "\1\40\2\4\5\uffff\2\53\2\uffff";
    static final String DFA48_maxS =
        "\1\133\1\136\1\135\5\uffff\2\133\2\uffff";
    static final String DFA48_acceptS =
        "\3\uffff\1\3\1\4\1\5\1\6\1\7\2\uffff\1\2\1\1";
    static final String DFA48_specialS =
        "\14\uffff}>";
    static final String[] DFA48_transitionS = {
            "\1\6\4\uffff\1\7\5\uffff\1\3\3\uffff\1\1\1\3\1\5\3\uffff\1\2"+
            "\36\uffff\1\4\6\uffff\1\3",
            "\1\3\33\uffff\1\3\3\uffff\6\3\1\uffff\1\3\1\10\2\uffff\3\3"+
            "\2\uffff\2\3\30\uffff\1\3\1\uffff\3\3\1\uffff\5\3\1\11\1\uffff"+
            "\4\3",
            "\1\3\33\uffff\1\3\3\uffff\6\3\1\uffff\1\3\1\10\2\uffff\3\3"+
            "\2\uffff\2\3\30\uffff\1\3\1\uffff\3\3\1\uffff\5\3\1\11\1\uffff"+
            "\3\3",
            "",
            "",
            "",
            "",
            "",
            "\1\13\3\uffff\2\13\4\uffff\1\13\36\uffff\1\12\6\uffff\1\13",
            "\1\13\3\uffff\2\13\4\uffff\1\13\36\uffff\1\12\6\uffff\1\13",
            "",
            ""
    };

    static final short[] DFA48_eot = DFA.unpackEncodedString(DFA48_eotS);
    static final short[] DFA48_eof = DFA.unpackEncodedString(DFA48_eofS);
    static final char[] DFA48_min = DFA.unpackEncodedStringToUnsignedChars(DFA48_minS);
    static final char[] DFA48_max = DFA.unpackEncodedStringToUnsignedChars(DFA48_maxS);
    static final short[] DFA48_accept = DFA.unpackEncodedString(DFA48_acceptS);
    static final short[] DFA48_special = DFA.unpackEncodedString(DFA48_specialS);
    static final short[][] DFA48_transition;

    static {
        int numStates = DFA48_transitionS.length;
        DFA48_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA48_transition[i] = DFA.unpackEncodedString(DFA48_transitionS[i]);
        }
    }

    class DFA48 extends DFA {

        public DFA48(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 48;
            this.eot = DFA48_eot;
            this.eof = DFA48_eof;
            this.min = DFA48_min;
            this.max = DFA48_max;
            this.accept = DFA48_accept;
            this.special = DFA48_special;
            this.transition = DFA48_transition;
        }
        public String getDescription() {
            return "345:1: elementNoOptionSpec : ( id (labelOp= '=' | labelOp= '+=' ) atom (startEndStt= ebnfSuffix -> ^( ebnfSuffix ^( BLOCK[$startEndStt.tree.getToken()] ^( ALT[$startEndStt.tree.getToken()] ^( $labelOp id atom ) EOA[$startEndStt.tree.getToken()] ) EOB[$startEndStt.tree.getToken()] ) ) | -> ^( $labelOp id atom ) ) | id (labelOp= '=' | labelOp= '+=' ) block (startEndStt= ebnfSuffix -> ^( ebnfSuffix ^( BLOCK[$startEndStt.tree.getToken()] ^( ALT[$startEndStt.tree.getToken()] ^( $labelOp id block ) EOA[$startEndStt.tree.getToken()] ) EOB[$startEndStt.tree.getToken()] ) ) | -> ^( $labelOp id block ) ) | atom (startEndStt= ebnfSuffix -> ^( ebnfSuffix ^( BLOCK[$startEndStt.tree.getToken()] ^( ALT[$startEndStt.tree.getToken()] atom EOA[$startEndStt.tree.getToken()] ) EOB[$startEndStt.tree.getToken()] ) ) | -> atom ) | ebnf | ACTION | s= SEMPRED ( '=>' -> GATED_SEMPRED[$s] | -> $s) | treeSpec (startEndStt= ebnfSuffix -> ^( ebnfSuffix ^( BLOCK[$startEndStt.tree.getToken()] ^( ALT[$startEndStt.tree.getToken()] treeSpec EOA[$startEndStt.tree.getToken()] ) EOB[$startEndStt.tree.getToken()] ) ) | -> treeSpec ) );";
        }
    }
    static final String DFA71_eotS =
        "\16\uffff";
    static final String DFA71_eofS =
        "\1\6\2\5\5\uffff\1\5\5\uffff";
    static final String DFA71_minS =
        "\3\4\1\45\1\0\2\uffff\1\45\1\4\1\45\1\uffff\2\45\1\116";
    static final String DFA71_maxS =
        "\4\140\1\0\2\uffff\3\140\1\uffff\2\140\1\135";
    static final String DFA71_acceptS =
        "\5\uffff\1\2\1\3\3\uffff\1\1\3\uffff";
    static final String DFA71_specialS =
        "\4\uffff\1\0\11\uffff}>";
    static final String[] DFA71_transitionS = {
            "\1\6\37\uffff\1\6\1\5\2\uffff\2\6\5\uffff\1\1\1\5\1\4\3\uffff"+
            "\1\2\32\uffff\3\6\1\uffff\1\3\4\6\7\uffff\1\5",
            "\1\5\32\uffff\1\6\4\uffff\2\5\1\uffff\1\6\2\5\1\6\4\uffff\3"+
            "\5\1\6\1\uffff\1\10\1\5\26\uffff\1\6\1\uffff\1\5\1\6\3\5\1\6"+
            "\1\7\4\5\3\uffff\3\5\1\uffff\1\5",
            "\1\5\32\uffff\1\6\4\uffff\2\5\1\uffff\1\6\2\5\1\6\4\uffff\3"+
            "\5\1\6\1\uffff\1\6\1\5\26\uffff\1\6\1\uffff\1\5\1\6\3\5\1\6"+
            "\1\7\4\5\3\uffff\2\5\2\uffff\1\5",
            "\1\5\11\uffff\2\5\1\11\3\uffff\1\5\36\uffff\1\5\13\uffff\1"+
            "\5",
            "\1\uffff",
            "",
            "",
            "\1\5\11\uffff\1\13\2\5\3\uffff\1\14\36\uffff\1\5\1\uffff\1"+
            "\12\11\uffff\1\5",
            "\1\5\32\uffff\1\6\4\uffff\2\5\2\uffff\2\5\1\6\4\uffff\3\5\1"+
            "\6\2\uffff\1\5\26\uffff\1\6\1\uffff\1\5\1\6\3\5\1\6\5\5\3\uffff"+
            "\2\5\2\uffff\1\5",
            "\1\5\11\uffff\3\5\3\uffff\1\5\30\uffff\1\5\5\uffff\1\5\1\uffff"+
            "\1\15\5\uffff\2\5\2\uffff\1\5",
            "",
            "\1\5\6\uffff\1\12\2\uffff\3\5\2\uffff\2\5\30\uffff\1\5\5\uffff"+
            "\1\5\1\uffff\1\5\5\uffff\3\5\1\uffff\1\5",
            "\1\5\6\uffff\1\12\2\uffff\3\5\3\uffff\1\5\30\uffff\1\5\5\uffff"+
            "\1\5\1\uffff\1\5\5\uffff\2\5\2\uffff\1\5",
            "\1\5\5\uffff\1\12\7\uffff\2\5"
    };

    static final short[] DFA71_eot = DFA.unpackEncodedString(DFA71_eotS);
    static final short[] DFA71_eof = DFA.unpackEncodedString(DFA71_eofS);
    static final char[] DFA71_min = DFA.unpackEncodedStringToUnsignedChars(DFA71_minS);
    static final char[] DFA71_max = DFA.unpackEncodedStringToUnsignedChars(DFA71_maxS);
    static final short[] DFA71_accept = DFA.unpackEncodedString(DFA71_acceptS);
    static final short[] DFA71_special = DFA.unpackEncodedString(DFA71_specialS);
    static final short[][] DFA71_transition;

    static {
        int numStates = DFA71_transitionS.length;
        DFA71_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA71_transition[i] = DFA.unpackEncodedString(DFA71_transitionS[i]);
        }
    }

    class DFA71 extends DFA {

        public DFA71(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 71;
            this.eot = DFA71_eot;
            this.eof = DFA71_eof;
            this.min = DFA71_min;
            this.max = DFA71_max;
            this.accept = DFA71_accept;
            this.special = DFA71_special;
            this.transition = DFA71_transition;
        }
        public String getDescription() {
            return "636:1: rewrite_alternative options {backtrack=true; } : ({...}? rewrite_template | rewrite_tree_alternative | -> ^( ALT[firstToken] EPSILON[firstToken] EOA[firstToken] ) );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA71_4 = input.LA(1);

                         
                        int index71_4 = input.index();
                        input.rewind();
                        s = -1;
                        if ( ((synpred1_ANTLR()&&(templateOutput))) ) {s = 10;}

                        else if ( (synpred2_ANTLR()) ) {s = 5;}

                         
                        input.seek(index71_4);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 71, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA74_eotS =
        "\103\uffff";
    static final String DFA74_eofS =
        "\1\uffff\3\12\1\uffff\1\12\3\uffff\1\12\3\uffff\2\12\6\uffff\1\12"+
        "\4\uffff\1\12\50\uffff";
    static final String DFA74_minS =
        "\1\45\3\4\1\57\1\4\2\uffff\1\57\1\4\2\uffff\1\57\2\4\4\53\2\57\1"+
        "\4\4\57\1\4\6\53\1\112\2\53\1\112\4\53\6\57\4\53\1\112\6\53\1\112"+
        "\2\53\2\57\4\53";
    static final String DFA74_maxS =
        "\4\140\1\65\1\140\2\uffff\1\65\1\140\2\uffff\1\65\2\140\4\137\2"+
        "\65\1\140\4\65\1\140\16\137\6\65\16\137\2\65\4\137";
    static final String DFA74_acceptS =
        "\6\uffff\1\3\1\4\2\uffff\1\1\1\2\67\uffff";
    static final String DFA74_specialS =
        "\103\uffff}>";
    static final String[] DFA74_transitionS = {
            "\1\6\11\uffff\1\1\1\3\1\5\3\uffff\1\2\36\uffff\1\7\13\uffff"+
            "\1\4",
            "\1\12\37\uffff\2\12\2\uffff\2\12\5\uffff\3\12\2\uffff\1\11"+
            "\1\12\30\uffff\1\13\1\uffff\3\12\1\uffff\5\12\3\uffff\2\13\1"+
            "\10\1\uffff\1\12",
            "\1\12\37\uffff\2\12\2\uffff\2\12\5\uffff\3\12\3\uffff\1\12"+
            "\30\uffff\1\13\1\uffff\3\12\1\uffff\5\12\3\uffff\2\13\2\uffff"+
            "\1\12",
            "\1\12\37\uffff\2\12\2\uffff\2\12\5\uffff\3\12\3\uffff\1\12"+
            "\30\uffff\1\13\1\uffff\3\12\1\uffff\5\12\3\uffff\2\13\1\14\1"+
            "\uffff\1\12",
            "\1\15\5\uffff\1\16",
            "\1\12\37\uffff\2\12\2\uffff\2\12\5\uffff\3\12\3\uffff\1\12"+
            "\30\uffff\1\13\1\uffff\3\12\1\uffff\5\12\3\uffff\2\13\2\uffff"+
            "\1\12",
            "",
            "",
            "\1\17\5\uffff\1\20",
            "\1\12\37\uffff\2\12\2\uffff\2\12\5\uffff\3\12\3\uffff\1\12"+
            "\30\uffff\1\13\1\uffff\3\12\1\uffff\5\12\3\uffff\2\13\2\uffff"+
            "\1\12",
            "",
            "",
            "\1\21\5\uffff\1\22",
            "\1\12\37\uffff\2\12\2\uffff\2\12\5\uffff\3\12\3\uffff\1\12"+
            "\30\uffff\1\13\1\uffff\3\12\1\uffff\5\12\3\uffff\2\13\2\uffff"+
            "\1\12",
            "\1\12\37\uffff\2\12\2\uffff\2\12\5\uffff\3\12\3\uffff\1\12"+
            "\30\uffff\1\13\1\uffff\3\12\1\uffff\5\12\3\uffff\2\13\2\uffff"+
            "\1\12",
            "\1\23\1\26\35\uffff\1\24\24\uffff\1\25",
            "\1\23\1\26\35\uffff\1\24\24\uffff\1\25",
            "\1\30\1\27\35\uffff\1\31\24\uffff\1\32",
            "\1\30\1\27\35\uffff\1\31\24\uffff\1\32",
            "\1\33\5\uffff\1\34",
            "\1\35\5\uffff\1\36",
            "\1\12\37\uffff\2\12\2\uffff\2\12\5\uffff\3\12\2\uffff\1\11"+
            "\1\12\30\uffff\1\13\1\uffff\3\12\1\uffff\5\12\3\uffff\2\13\2"+
            "\uffff\1\12",
            "\1\37\1\41\4\uffff\1\40",
            "\1\42\1\44\4\uffff\1\43",
            "\1\45\5\uffff\1\46",
            "\1\47\5\uffff\1\50",
            "\1\12\37\uffff\2\12\2\uffff\2\12\5\uffff\3\12\3\uffff\1\12"+
            "\30\uffff\1\13\1\uffff\3\12\1\uffff\5\12\3\uffff\2\13\2\uffff"+
            "\1\12",
            "\1\23\36\uffff\1\24\24\uffff\1\25",
            "\1\23\36\uffff\1\24\24\uffff\1\25",
            "\1\51\1\52\35\uffff\1\24\24\uffff\1\25",
            "\1\51\1\52\35\uffff\1\24\24\uffff\1\25",
            "\1\53\36\uffff\1\24\24\uffff\1\25",
            "\1\53\36\uffff\1\24\24\uffff\1\25",
            "\1\24\24\uffff\1\25",
            "\1\54\36\uffff\1\31\24\uffff\1\32",
            "\1\54\36\uffff\1\31\24\uffff\1\32",
            "\1\31\24\uffff\1\32",
            "\1\30\36\uffff\1\31\24\uffff\1\32",
            "\1\30\36\uffff\1\31\24\uffff\1\32",
            "\1\56\1\55\35\uffff\1\31\24\uffff\1\32",
            "\1\56\1\55\35\uffff\1\31\24\uffff\1\32",
            "\1\57\5\uffff\1\60",
            "\1\61\1\63\4\uffff\1\62",
            "\1\64\5\uffff\1\65",
            "\1\66\5\uffff\1\67",
            "\1\70\1\72\4\uffff\1\71",
            "\1\73\5\uffff\1\74",
            "\1\51\36\uffff\1\24\24\uffff\1\25",
            "\1\51\36\uffff\1\24\24\uffff\1\25",
            "\1\75\36\uffff\1\24\24\uffff\1\25",
            "\1\75\36\uffff\1\24\24\uffff\1\25",
            "\1\24\24\uffff\1\25",
            "\1\53\36\uffff\1\24\24\uffff\1\25",
            "\1\53\36\uffff\1\24\24\uffff\1\25",
            "\1\54\36\uffff\1\31\24\uffff\1\32",
            "\1\54\36\uffff\1\31\24\uffff\1\32",
            "\1\76\36\uffff\1\31\24\uffff\1\32",
            "\1\76\36\uffff\1\31\24\uffff\1\32",
            "\1\31\24\uffff\1\32",
            "\1\56\36\uffff\1\31\24\uffff\1\32",
            "\1\56\36\uffff\1\31\24\uffff\1\32",
            "\1\77\5\uffff\1\100",
            "\1\101\5\uffff\1\102",
            "\1\75\36\uffff\1\24\24\uffff\1\25",
            "\1\75\36\uffff\1\24\24\uffff\1\25",
            "\1\76\36\uffff\1\31\24\uffff\1\32",
            "\1\76\36\uffff\1\31\24\uffff\1\32"
    };

    static final short[] DFA74_eot = DFA.unpackEncodedString(DFA74_eotS);
    static final short[] DFA74_eof = DFA.unpackEncodedString(DFA74_eofS);
    static final char[] DFA74_min = DFA.unpackEncodedStringToUnsignedChars(DFA74_minS);
    static final char[] DFA74_max = DFA.unpackEncodedStringToUnsignedChars(DFA74_maxS);
    static final short[] DFA74_accept = DFA.unpackEncodedString(DFA74_acceptS);
    static final short[] DFA74_special = DFA.unpackEncodedString(DFA74_specialS);
    static final short[][] DFA74_transition;

    static {
        int numStates = DFA74_transitionS.length;
        DFA74_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA74_transition[i] = DFA.unpackEncodedString(DFA74_transitionS[i]);
        }
    }

    class DFA74 extends DFA {

        public DFA74(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 74;
            this.eot = DFA74_eot;
            this.eof = DFA74_eof;
            this.min = DFA74_min;
            this.max = DFA74_max;
            this.accept = DFA74_accept;
            this.special = DFA74_special;
            this.transition = DFA74_transition;
        }
        public String getDescription() {
            return "668:1: rewrite_tree_element : ( rewrite_tree_atom | rewrite_tree_atom startEndStt= ebnfSuffix -> ^( ebnfSuffix ^( BLOCK[$startEndStt.tree.getToken()] ^( ALT[$startEndStt.tree.getToken()] rewrite_tree_atom EOA[$startEndStt.tree.getToken()] ) EOB[$startEndStt.tree.getToken()] ) ) | rewrite_tree (startEndStt= ebnfSuffix -> ^( ebnfSuffix ^( BLOCK[$startEndStt.tree.getToken()] ^( ALT[$startEndStt.tree.getToken()] rewrite_tree EOA[$startEndStt.tree.getToken()] ) EOB[$startEndStt.tree.getToken()] ) ) | -> rewrite_tree ) | rewrite_tree_ebnf );";
        }
    }
    static final String DFA81_eotS =
        "\22\uffff";
    static final String DFA81_eofS =
        "\10\uffff\1\12\11\uffff";
    static final String DFA81_minS =
        "\1\57\2\124\2\uffff\1\57\2\54\1\4\1\61\2\uffff\1\112\1\57\2\54\1"+
        "\61\1\112";
    static final String DFA81_maxS =
        "\3\124\2\uffff\1\126\2\54\1\130\1\61\2\uffff\1\126\1\65\2\54\1\61"+
        "\1\126";
    static final String DFA81_acceptS =
        "\3\uffff\1\3\1\4\5\uffff\1\2\1\1\6\uffff";
    static final String DFA81_specialS =
        "\22\uffff}>";
    static final String[] DFA81_transitionS = {
            "\1\1\1\uffff\1\4\3\uffff\1\2\36\uffff\1\3",
            "\1\5",
            "\1\5",
            "",
            "",
            "\1\6\5\uffff\1\7\40\uffff\1\10",
            "\1\11",
            "\1\11",
            "\1\12\37\uffff\1\12\3\uffff\2\12\5\uffff\1\12\5\uffff\1\12"+
            "\2\13\30\uffff\3\12\2\uffff\4\12",
            "\1\14",
            "",
            "",
            "\1\15\13\uffff\1\10",
            "\1\16\5\uffff\1\17",
            "\1\20",
            "\1\20",
            "\1\21",
            "\1\15\13\uffff\1\10"
    };

    static final short[] DFA81_eot = DFA.unpackEncodedString(DFA81_eotS);
    static final short[] DFA81_eof = DFA.unpackEncodedString(DFA81_eofS);
    static final char[] DFA81_min = DFA.unpackEncodedStringToUnsignedChars(DFA81_minS);
    static final char[] DFA81_max = DFA.unpackEncodedStringToUnsignedChars(DFA81_maxS);
    static final short[] DFA81_accept = DFA.unpackEncodedString(DFA81_acceptS);
    static final short[] DFA81_special = DFA.unpackEncodedString(DFA81_specialS);
    static final short[][] DFA81_transition;

    static {
        int numStates = DFA81_transitionS.length;
        DFA81_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA81_transition[i] = DFA.unpackEncodedString(DFA81_transitionS[i]);
        }
    }

    class DFA81 extends DFA {

        public DFA81(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 81;
            this.eot = DFA81_eot;
            this.eof = DFA81_eof;
            this.min = DFA81_min;
            this.max = DFA81_max;
            this.accept = DFA81_accept;
            this.special = DFA81_special;
            this.transition = DFA81_transition;
        }
        public String getDescription() {
            return "734:1: rewrite_template : ( id lp= '(' rewrite_template_args ')' (str= DOUBLE_QUOTE_STRING_LITERAL | str= DOUBLE_ANGLE_STRING_LITERAL ) -> ^( TEMPLATE[$lp,\"TEMPLATE\"] id rewrite_template_args $str) | rewrite_template_ref | rewrite_indirect_template_head | ACTION );";
        }
    }
 

    public static final BitSet FOLLOW_DOC_COMMENT_in_grammarDef400 = new BitSet(new long[]{0x0000000000000000L,0x00000000000001E0L});
    public static final BitSet FOLLOW_69_in_grammarDef411 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_70_in_grammarDef422 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_71_in_grammarDef433 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_72_in_grammarDef455 = new BitSet(new long[]{0x0020800000000000L});
    public static final BitSet FOLLOW_id_in_grammarDef457 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_SEMI_in_grammarDef459 = new BitSet(new long[]{0x0024C01080000010L,0x0000000000071200L});
    public static final BitSet FOLLOW_optionsSpec_in_grammarDef461 = new BitSet(new long[]{0x0024C01080000010L,0x0000000000071200L});
    public static final BitSet FOLLOW_delegateGrammars_in_grammarDef464 = new BitSet(new long[]{0x0024C01080000010L,0x0000000000071200L});
    public static final BitSet FOLLOW_tokensSpec_in_grammarDef467 = new BitSet(new long[]{0x0024C01080000010L,0x0000000000071200L});
    public static final BitSet FOLLOW_attrScope_in_grammarDef470 = new BitSet(new long[]{0x0024C01080000010L,0x0000000000071200L});
    public static final BitSet FOLLOW_action_in_grammarDef473 = new BitSet(new long[]{0x0024C01080000010L,0x0000000000071200L});
    public static final BitSet FOLLOW_rule_in_grammarDef476 = new BitSet(new long[]{0x0024C01080000010L,0x0000000000071200L});
    public static final BitSet FOLLOW_EOF_in_grammarDef479 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_73_in_delegateGrammars534 = new BitSet(new long[]{0x0020800000000000L});
    public static final BitSet FOLLOW_delegateGrammar_in_delegateGrammars536 = new BitSet(new long[]{0x0000020000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_74_in_delegateGrammars539 = new BitSet(new long[]{0x0020800000000000L});
    public static final BitSet FOLLOW_delegateGrammar_in_delegateGrammars541 = new BitSet(new long[]{0x0000020000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_SEMI_in_delegateGrammars545 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_id_in_delegateGrammar583 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_ASSIGN_in_delegateGrammar585 = new BitSet(new long[]{0x0020800000000000L});
    public static final BitSet FOLLOW_id_in_delegateGrammar589 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_id_in_delegateGrammar619 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TOKENS_in_tokensSpec643 = new BitSet(new long[]{0x0000800000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_tokenSpec_in_tokensSpec645 = new BitSet(new long[]{0x0000800000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_75_in_tokensSpec648 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TOKEN_REF_in_tokenSpec684 = new BitSet(new long[]{0x0000120000000000L});
    public static final BitSet FOLLOW_ASSIGN_in_tokenSpec694 = new BitSet(new long[]{0x0001000000000000L});
    public static final BitSet FOLLOW_STRING_LITERAL_in_tokenSpec699 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_SEMI_in_tokenSpec749 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SCOPE_in_attrScope764 = new BitSet(new long[]{0x0020800000000000L});
    public static final BitSet FOLLOW_id_in_attrScope766 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_ACTION_in_attrScope768 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_76_in_action805 = new BitSet(new long[]{0x0020800000000000L,0x0000000000000060L});
    public static final BitSet FOLLOW_actionScopeName_in_action808 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_77_in_action810 = new BitSet(new long[]{0x0020800000000000L});
    public static final BitSet FOLLOW_id_in_action814 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_ACTION_in_action816 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_id_in_actionScopeName856 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_69_in_actionScopeName864 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_actionScopeName881 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_OPTIONS_in_optionsSpec905 = new BitSet(new long[]{0x0020800000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_option_in_optionsSpec908 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_SEMI_in_optionsSpec910 = new BitSet(new long[]{0x0020800000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_75_in_optionsSpec914 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_id_in_option950 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_ASSIGN_in_option952 = new BitSet(new long[]{0x0029800000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_optionValue_in_option954 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_id_in_optionValue991 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_LITERAL_in_optionValue997 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INT_in_optionValue1003 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_78_in_optionValue1011 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOC_COMMENT_in_rule1040 = new BitSet(new long[]{0x0020801000000000L,0x0000000000070000L});
    public static final BitSet FOLLOW_modifier_in_rule1043 = new BitSet(new long[]{0x0020800000000000L});
    public static final BitSet FOLLOW_id_in_rule1046 = new BitSet(new long[]{0x0014048080000000L,0x0000000000089000L});
    public static final BitSet FOLLOW_BANG_in_rule1052 = new BitSet(new long[]{0x0014040080000000L,0x0000000000089000L});
    public static final BitSet FOLLOW_ARG_ACTION_in_rule1058 = new BitSet(new long[]{0x0004040080000000L,0x0000000000089000L});
    public static final BitSet FOLLOW_79_in_rule1063 = new BitSet(new long[]{0x0010000000000000L});
    public static final BitSet FOLLOW_ARG_ACTION_in_rule1067 = new BitSet(new long[]{0x0004040080000000L,0x0000000000081000L});
    public static final BitSet FOLLOW_throwsSpec_in_rule1071 = new BitSet(new long[]{0x0004040080000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_optionsSpec_in_rule1074 = new BitSet(new long[]{0x0000040080000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_ruleScopeSpec_in_rule1077 = new BitSet(new long[]{0x0000040000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_ruleAction_in_rule1080 = new BitSet(new long[]{0x0000040000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_COLON_in_rule1085 = new BitSet(new long[]{0x0023892100000000L,0x0000000008100000L});
    public static final BitSet FOLLOW_altList_in_rule1087 = new BitSet(new long[]{0x0000020000000002L,0x0000000001800000L});
    public static final BitSet FOLLOW_SEMI_in_rule1091 = new BitSet(new long[]{0x0000000000000002L,0x0000000001800000L});
    public static final BitSet FOLLOW_exceptionGroup_in_rule1094 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_modifier0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_76_in_ruleAction1262 = new BitSet(new long[]{0x0020800000000000L});
    public static final BitSet FOLLOW_id_in_ruleAction1264 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_ACTION_in_ruleAction1266 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_83_in_throwsSpec1301 = new BitSet(new long[]{0x0020800000000000L});
    public static final BitSet FOLLOW_id_in_throwsSpec1303 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000400L});
    public static final BitSet FOLLOW_74_in_throwsSpec1306 = new BitSet(new long[]{0x0020800000000000L});
    public static final BitSet FOLLOW_id_in_throwsSpec1308 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000400L});
    public static final BitSet FOLLOW_SCOPE_in_ruleScopeSpec1344 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_ACTION_in_ruleScopeSpec1346 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SCOPE_in_ruleScopeSpec1370 = new BitSet(new long[]{0x0020800000000000L});
    public static final BitSet FOLLOW_id_in_ruleScopeSpec1372 = new BitSet(new long[]{0x0000020000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_74_in_ruleScopeSpec1375 = new BitSet(new long[]{0x0020800000000000L});
    public static final BitSet FOLLOW_id_in_ruleScopeSpec1377 = new BitSet(new long[]{0x0000020000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_SEMI_in_ruleScopeSpec1381 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SCOPE_in_ruleScopeSpec1406 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_ACTION_in_ruleScopeSpec1408 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_SCOPE_in_ruleScopeSpec1410 = new BitSet(new long[]{0x0020800000000000L});
    public static final BitSet FOLLOW_id_in_ruleScopeSpec1412 = new BitSet(new long[]{0x0000020000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_74_in_ruleScopeSpec1415 = new BitSet(new long[]{0x0020800000000000L});
    public static final BitSet FOLLOW_id_in_ruleScopeSpec1417 = new BitSet(new long[]{0x0000020000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_SEMI_in_ruleScopeSpec1421 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_84_in_block1459 = new BitSet(new long[]{0x00278D2100000000L,0x0000000008100000L});
    public static final BitSet FOLLOW_optionsSpec_in_block1464 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_COLON_in_block1468 = new BitSet(new long[]{0x0023892100000000L,0x0000000008100000L});
    public static final BitSet FOLLOW_altpair_in_block1472 = new BitSet(new long[]{0x0000000000000000L,0x0000000000600000L});
    public static final BitSet FOLLOW_85_in_block1475 = new BitSet(new long[]{0x0023892100000000L,0x0000000008100000L});
    public static final BitSet FOLLOW_altpair_in_block1477 = new BitSet(new long[]{0x0000000000000000L,0x0000000000600000L});
    public static final BitSet FOLLOW_86_in_block1483 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_alternative_in_altpair1524 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_rewrite_in_altpair1526 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_altpair_in_altList1546 = new BitSet(new long[]{0x0000000000000002L,0x0000000000200000L});
    public static final BitSet FOLLOW_85_in_altList1549 = new BitSet(new long[]{0x0023892100000000L,0x0000000008100000L});
    public static final BitSet FOLLOW_altpair_in_altList1551 = new BitSet(new long[]{0x0000000000000002L,0x0000000000200000L});
    public static final BitSet FOLLOW_element_in_alternative1596 = new BitSet(new long[]{0x0023882100000002L,0x0000000008100000L});
    public static final BitSet FOLLOW_exceptionHandler_in_exceptionGroup1663 = new BitSet(new long[]{0x0000000000000002L,0x0000000001800000L});
    public static final BitSet FOLLOW_finallyClause_in_exceptionGroup1668 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_finallyClause_in_exceptionGroup1676 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_87_in_exceptionHandler1691 = new BitSet(new long[]{0x0010000000000000L});
    public static final BitSet FOLLOW_ARG_ACTION_in_exceptionHandler1693 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_ACTION_in_exceptionHandler1695 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_88_in_finallyClause1730 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_ACTION_in_finallyClause1732 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_elementNoOptionSpec_in_element1765 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_id_in_elementNoOptionSpec1780 = new BitSet(new long[]{0x0000100000000000L,0x0000000002000000L});
    public static final BitSet FOLLOW_ASSIGN_in_elementNoOptionSpec1792 = new BitSet(new long[]{0x0021880000000000L,0x0000000008000000L});
    public static final BitSet FOLLOW_89_in_elementNoOptionSpec1802 = new BitSet(new long[]{0x0021880000000000L,0x0000000008000000L});
    public static final BitSet FOLLOW_atom_in_elementNoOptionSpec1810 = new BitSet(new long[]{0x0000000000000002L,0x0000000030004000L});
    public static final BitSet FOLLOW_ebnfSuffix_in_elementNoOptionSpec1822 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_id_in_elementNoOptionSpec2045 = new BitSet(new long[]{0x0000100000000000L,0x0000000002000000L});
    public static final BitSet FOLLOW_ASSIGN_in_elementNoOptionSpec2057 = new BitSet(new long[]{0x0000000000000000L,0x0000000000100000L});
    public static final BitSet FOLLOW_89_in_elementNoOptionSpec2067 = new BitSet(new long[]{0x0000000000000000L,0x0000000000100000L});
    public static final BitSet FOLLOW_block_in_elementNoOptionSpec2075 = new BitSet(new long[]{0x0000000000000002L,0x0000000030004000L});
    public static final BitSet FOLLOW_ebnfSuffix_in_elementNoOptionSpec2087 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_atom_in_elementNoOptionSpec2310 = new BitSet(new long[]{0x0000000000000002L,0x0000000030004000L});
    public static final BitSet FOLLOW_ebnfSuffix_in_elementNoOptionSpec2322 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ebnf_in_elementNoOptionSpec2466 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ACTION_in_elementNoOptionSpec2472 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SEMPRED_in_elementNoOptionSpec2480 = new BitSet(new long[]{0x0000000000000002L,0x0000000004000000L});
    public static final BitSet FOLLOW_90_in_elementNoOptionSpec2490 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_treeSpec_in_elementNoOptionSpec2528 = new BitSet(new long[]{0x0000000000000002L,0x0000000030004000L});
    public static final BitSet FOLLOW_ebnfSuffix_in_elementNoOptionSpec2540 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_range_in_atom2693 = new BitSet(new long[]{0x000000C000000002L});
    public static final BitSet FOLLOW_ROOT_in_atom2713 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BANG_in_atom2725 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_terminal_in_atom2780 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_notSet_in_atom2786 = new BitSet(new long[]{0x000000C000000002L});
    public static final BitSet FOLLOW_ROOT_in_atom2806 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BANG_in_atom2818 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_call_in_atom2873 = new BitSet(new long[]{0x000000C000000002L});
    public static final BitSet FOLLOW_ROOT_in_atom2885 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BANG_in_atom2895 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_REF_in_call2944 = new BitSet(new long[]{0x0010000000000002L});
    public static final BitSet FOLLOW_ARG_ACTION_in_call2946 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_91_in_notSet2962 = new BitSet(new long[]{0x0001800000000000L,0x0000000000100000L});
    public static final BitSet FOLLOW_notTerminal_in_notSet2972 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_block_in_notSet3002 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TREE_BEGIN_in_treeSpec3045 = new BitSet(new long[]{0x0023882100000000L,0x0000000008100000L});
    public static final BitSet FOLLOW_element_in_treeSpec3047 = new BitSet(new long[]{0x0023882100000000L,0x0000000008100000L});
    public static final BitSet FOLLOW_element_in_treeSpec3050 = new BitSet(new long[]{0x0023882100000000L,0x0000000008500000L});
    public static final BitSet FOLLOW_86_in_treeSpec3054 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_block_in_ebnf3101 = new BitSet(new long[]{0x000000C000000002L,0x0000000034004000L});
    public static final BitSet FOLLOW_92_in_ebnf3113 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_78_in_ebnf3146 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_93_in_ebnf3179 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ROOT_in_ebnf3212 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BANG_in_ebnf3245 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_90_in_ebnf3278 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_LITERAL_in_range3381 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_RANGE_in_range3385 = new BitSet(new long[]{0x0001000000000000L});
    public static final BitSet FOLLOW_STRING_LITERAL_in_range3389 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_94_in_elementOptions3441 = new BitSet(new long[]{0x0020800000000000L});
    public static final BitSet FOLLOW_elementOption_in_elementOptions3443 = new BitSet(new long[]{0x0000000000000000L,0x0000000080000400L});
    public static final BitSet FOLLOW_74_in_elementOptions3446 = new BitSet(new long[]{0x0020800000000000L});
    public static final BitSet FOLLOW_elementOption_in_elementOptions3448 = new BitSet(new long[]{0x0000000000000000L,0x0000000080000400L});
    public static final BitSet FOLLOW_95_in_elementOptions3452 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_qid_in_elementOption3490 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_id_in_elementOption3500 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_ASSIGN_in_elementOption3502 = new BitSet(new long[]{0x0021800000000000L});
    public static final BitSet FOLLOW_qid_in_elementOption3513 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_LITERAL_in_elementOption3521 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_id_in_qid3540 = new BitSet(new long[]{0x0000080000000002L});
    public static final BitSet FOLLOW_DOT_in_qid3543 = new BitSet(new long[]{0x0020800000000000L});
    public static final BitSet FOLLOW_id_in_qid3545 = new BitSet(new long[]{0x0000080000000002L});
    public static final BitSet FOLLOW_TOKEN_REF_in_terminal3582 = new BitSet(new long[]{0x001000C000000002L,0x0000000040000000L});
    public static final BitSet FOLLOW_elementOptions_in_terminal3584 = new BitSet(new long[]{0x001000C000000002L});
    public static final BitSet FOLLOW_ARG_ACTION_in_terminal3587 = new BitSet(new long[]{0x000000C000000002L});
    public static final BitSet FOLLOW_STRING_LITERAL_in_terminal3622 = new BitSet(new long[]{0x000000C000000002L,0x0000000040000000L});
    public static final BitSet FOLLOW_elementOptions_in_terminal3624 = new BitSet(new long[]{0x000000C000000002L});
    public static final BitSet FOLLOW_DOT_in_terminal3656 = new BitSet(new long[]{0x000000C000000002L});
    public static final BitSet FOLLOW_ROOT_in_terminal3680 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BANG_in_terminal3711 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_notTerminal0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_92_in_ebnfSuffix3780 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_78_in_ebnfSuffix3795 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_93_in_ebnfSuffix3810 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_REWRITE_in_rewrite3839 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_SEMPRED_in_rewrite3843 = new BitSet(new long[]{0x0023812000000000L,0x0000000100100000L});
    public static final BitSet FOLLOW_rewrite_alternative_in_rewrite3847 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_REWRITE_in_rewrite3853 = new BitSet(new long[]{0x0023802000000000L,0x0000000100100000L});
    public static final BitSet FOLLOW_rewrite_alternative_in_rewrite3857 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rewrite_template_in_rewrite_alternative3934 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rewrite_tree_alternative_in_rewrite_alternative3940 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_84_in_rewrite_tree_block3986 = new BitSet(new long[]{0x0023802000000000L,0x0000000100100000L});
    public static final BitSet FOLLOW_rewrite_tree_alternative_in_rewrite_tree_block3988 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_86_in_rewrite_tree_block3992 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rewrite_tree_element_in_rewrite_tree_alternative4034 = new BitSet(new long[]{0x0023802000000002L,0x0000000100100000L});
    public static final BitSet FOLLOW_rewrite_tree_atom_in_rewrite_tree_element4072 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rewrite_tree_atom_in_rewrite_tree_element4078 = new BitSet(new long[]{0x0000000000000000L,0x0000000030004000L});
    public static final BitSet FOLLOW_ebnfSuffix_in_rewrite_tree_element4082 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rewrite_tree_in_rewrite_tree_element4188 = new BitSet(new long[]{0x0000000000000002L,0x0000000030004000L});
    public static final BitSet FOLLOW_ebnfSuffix_in_rewrite_tree_element4200 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rewrite_tree_ebnf_in_rewrite_tree_element4344 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TOKEN_REF_in_rewrite_tree_atom4359 = new BitSet(new long[]{0x0010000000000002L,0x0000000040000000L});
    public static final BitSet FOLLOW_elementOptions_in_rewrite_tree_atom4361 = new BitSet(new long[]{0x0010000000000002L});
    public static final BitSet FOLLOW_ARG_ACTION_in_rewrite_tree_atom4364 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_REF_in_rewrite_tree_atom4394 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_LITERAL_in_rewrite_tree_atom4400 = new BitSet(new long[]{0x0000000000000002L,0x0000000040000000L});
    public static final BitSet FOLLOW_elementOptions_in_rewrite_tree_atom4402 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_96_in_rewrite_tree_atom4428 = new BitSet(new long[]{0x0020800000000000L});
    public static final BitSet FOLLOW_id_in_rewrite_tree_atom4430 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ACTION_in_rewrite_tree_atom4446 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rewrite_tree_block_in_rewrite_tree_ebnf4471 = new BitSet(new long[]{0x0000000000000000L,0x0000000030004000L});
    public static final BitSet FOLLOW_ebnfSuffix_in_rewrite_tree_ebnf4473 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TREE_BEGIN_in_rewrite_tree4506 = new BitSet(new long[]{0x0023800000000000L,0x0000000100000000L});
    public static final BitSet FOLLOW_rewrite_tree_atom_in_rewrite_tree4508 = new BitSet(new long[]{0x0023802000000000L,0x0000000100500000L});
    public static final BitSet FOLLOW_rewrite_tree_element_in_rewrite_tree4510 = new BitSet(new long[]{0x0023802000000000L,0x0000000100500000L});
    public static final BitSet FOLLOW_86_in_rewrite_tree4513 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_id_in_rewrite_template4552 = new BitSet(new long[]{0x0000000000000000L,0x0000000000100000L});
    public static final BitSet FOLLOW_84_in_rewrite_template4556 = new BitSet(new long[]{0x0020800000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_rewrite_template_args_in_rewrite_template4558 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_86_in_rewrite_template4560 = new BitSet(new long[]{0x00C0000000000000L});
    public static final BitSet FOLLOW_DOUBLE_QUOTE_STRING_LITERAL_in_rewrite_template4572 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOUBLE_ANGLE_STRING_LITERAL_in_rewrite_template4582 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rewrite_template_ref_in_rewrite_template4620 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rewrite_indirect_template_head_in_rewrite_template4630 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ACTION_in_rewrite_template4640 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_id_in_rewrite_template_ref4657 = new BitSet(new long[]{0x0000000000000000L,0x0000000000100000L});
    public static final BitSet FOLLOW_84_in_rewrite_template_ref4661 = new BitSet(new long[]{0x0020800000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_rewrite_template_args_in_rewrite_template_ref4663 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_86_in_rewrite_template_ref4665 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_84_in_rewrite_indirect_template_head4705 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_ACTION_in_rewrite_indirect_template_head4707 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_86_in_rewrite_indirect_template_head4709 = new BitSet(new long[]{0x0000000000000000L,0x0000000000100000L});
    public static final BitSet FOLLOW_84_in_rewrite_indirect_template_head4711 = new BitSet(new long[]{0x0020800000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_rewrite_template_args_in_rewrite_indirect_template_head4713 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_86_in_rewrite_indirect_template_head4715 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rewrite_template_arg_in_rewrite_template_args4751 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000400L});
    public static final BitSet FOLLOW_74_in_rewrite_template_args4754 = new BitSet(new long[]{0x0020800000000000L});
    public static final BitSet FOLLOW_rewrite_template_arg_in_rewrite_template_args4756 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000400L});
    public static final BitSet FOLLOW_id_in_rewrite_template_arg4804 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_ASSIGN_in_rewrite_template_arg4806 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_ACTION_in_rewrite_template_arg4808 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TOKEN_REF_in_id4844 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_REF_in_id4859 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rewrite_template_in_synpred1_ANTLR3934 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rewrite_tree_alternative_in_synpred2_ANTLR3940 = new BitSet(new long[]{0x0000000000000002L});

}