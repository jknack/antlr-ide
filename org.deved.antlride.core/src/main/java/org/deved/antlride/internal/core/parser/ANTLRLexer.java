// $ANTLR 3.2 Sep 23, 2009 12:02:23 /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g 2010-02-24 17:42:59

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

public class ANTLRLexer extends Lexer {
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

    public ANTLRLexer() {;} 
    public ANTLRLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public ANTLRLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "/home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g"; }

    // $ANTLR start "SCOPE"
    public final void mSCOPE() throws RecognitionException {
        try {
            int _type = SCOPE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:19:7: ( 'scope' )
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:19:9: 'scope'
            {
            match("scope"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SCOPE"

    // $ANTLR start "FRAGMENT"
    public final void mFRAGMENT() throws RecognitionException {
        try {
            int _type = FRAGMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:20:10: ( 'fragment' )
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:20:12: 'fragment'
            {
            match("fragment"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "FRAGMENT"

    // $ANTLR start "TREE_BEGIN"
    public final void mTREE_BEGIN() throws RecognitionException {
        try {
            int _type = TREE_BEGIN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:21:12: ( '^(' )
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:21:14: '^('
            {
            match("^("); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "TREE_BEGIN"

    // $ANTLR start "ROOT"
    public final void mROOT() throws RecognitionException {
        try {
            int _type = ROOT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:22:6: ( '^' )
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:22:8: '^'
            {
            match('^'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ROOT"

    // $ANTLR start "BANG"
    public final void mBANG() throws RecognitionException {
        try {
            int _type = BANG;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:23:6: ( '!' )
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:23:8: '!'
            {
            match('!'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "BANG"

    // $ANTLR start "RANGE"
    public final void mRANGE() throws RecognitionException {
        try {
            int _type = RANGE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:24:7: ( '..' )
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:24:9: '..'
            {
            match(".."); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RANGE"

    // $ANTLR start "REWRITE"
    public final void mREWRITE() throws RecognitionException {
        try {
            int _type = REWRITE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:25:9: ( '->' )
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:25:11: '->'
            {
            match("->"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "REWRITE"

    // $ANTLR start "SEMI"
    public final void mSEMI() throws RecognitionException {
        try {
            int _type = SEMI;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:26:6: ( ';' )
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:26:8: ';'
            {
            match(';'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SEMI"

    // $ANTLR start "COLON"
    public final void mCOLON() throws RecognitionException {
        try {
            int _type = COLON;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:27:7: ( ':' )
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:27:9: ':'
            {
            match(':'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "COLON"

    // $ANTLR start "DOT"
    public final void mDOT() throws RecognitionException {
        try {
            int _type = DOT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:28:5: ( '.' )
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:28:7: '.'
            {
            match('.'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DOT"

    // $ANTLR start "ASSIGN"
    public final void mASSIGN() throws RecognitionException {
        try {
            int _type = ASSIGN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:29:8: ( '=' )
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:29:10: '='
            {
            match('='); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ASSIGN"

    // $ANTLR start "T__69"
    public final void mT__69() throws RecognitionException {
        try {
            int _type = T__69;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:30:7: ( 'lexer' )
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:30:9: 'lexer'
            {
            match("lexer"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__69"

    // $ANTLR start "T__70"
    public final void mT__70() throws RecognitionException {
        try {
            int _type = T__70;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:31:7: ( 'parser' )
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:31:9: 'parser'
            {
            match("parser"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__70"

    // $ANTLR start "T__71"
    public final void mT__71() throws RecognitionException {
        try {
            int _type = T__71;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:32:7: ( 'tree' )
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:32:9: 'tree'
            {
            match("tree"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__71"

    // $ANTLR start "T__72"
    public final void mT__72() throws RecognitionException {
        try {
            int _type = T__72;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:33:7: ( 'grammar' )
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:33:9: 'grammar'
            {
            match("grammar"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__72"

    // $ANTLR start "T__73"
    public final void mT__73() throws RecognitionException {
        try {
            int _type = T__73;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:34:7: ( 'import' )
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:34:9: 'import'
            {
            match("import"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__73"

    // $ANTLR start "T__74"
    public final void mT__74() throws RecognitionException {
        try {
            int _type = T__74;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:35:7: ( ',' )
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:35:9: ','
            {
            match(','); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__74"

    // $ANTLR start "T__75"
    public final void mT__75() throws RecognitionException {
        try {
            int _type = T__75;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:36:7: ( '}' )
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:36:9: '}'
            {
            match('}'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__75"

    // $ANTLR start "T__76"
    public final void mT__76() throws RecognitionException {
        try {
            int _type = T__76;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:37:7: ( '@' )
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:37:9: '@'
            {
            match('@'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__76"

    // $ANTLR start "T__77"
    public final void mT__77() throws RecognitionException {
        try {
            int _type = T__77;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:38:7: ( '::' )
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:38:9: '::'
            {
            match("::"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__77"

    // $ANTLR start "T__78"
    public final void mT__78() throws RecognitionException {
        try {
            int _type = T__78;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:39:7: ( '*' )
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:39:9: '*'
            {
            match('*'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__78"

    // $ANTLR start "T__79"
    public final void mT__79() throws RecognitionException {
        try {
            int _type = T__79;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:40:7: ( 'returns' )
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:40:9: 'returns'
            {
            match("returns"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__79"

    // $ANTLR start "T__80"
    public final void mT__80() throws RecognitionException {
        try {
            int _type = T__80;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:41:7: ( 'protected' )
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:41:9: 'protected'
            {
            match("protected"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__80"

    // $ANTLR start "T__81"
    public final void mT__81() throws RecognitionException {
        try {
            int _type = T__81;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:42:7: ( 'public' )
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:42:9: 'public'
            {
            match("public"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__81"

    // $ANTLR start "T__82"
    public final void mT__82() throws RecognitionException {
        try {
            int _type = T__82;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:43:7: ( 'private' )
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:43:9: 'private'
            {
            match("private"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__82"

    // $ANTLR start "T__83"
    public final void mT__83() throws RecognitionException {
        try {
            int _type = T__83;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:44:7: ( 'throws' )
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:44:9: 'throws'
            {
            match("throws"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__83"

    // $ANTLR start "T__84"
    public final void mT__84() throws RecognitionException {
        try {
            int _type = T__84;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:45:7: ( '(' )
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:45:9: '('
            {
            match('('); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__84"

    // $ANTLR start "T__85"
    public final void mT__85() throws RecognitionException {
        try {
            int _type = T__85;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:46:7: ( '|' )
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:46:9: '|'
            {
            match('|'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__85"

    // $ANTLR start "T__86"
    public final void mT__86() throws RecognitionException {
        try {
            int _type = T__86;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:47:7: ( ')' )
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:47:9: ')'
            {
            match(')'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__86"

    // $ANTLR start "T__87"
    public final void mT__87() throws RecognitionException {
        try {
            int _type = T__87;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:48:7: ( 'catch' )
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:48:9: 'catch'
            {
            match("catch"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__87"

    // $ANTLR start "T__88"
    public final void mT__88() throws RecognitionException {
        try {
            int _type = T__88;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:49:7: ( 'finally' )
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:49:9: 'finally'
            {
            match("finally"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__88"

    // $ANTLR start "T__89"
    public final void mT__89() throws RecognitionException {
        try {
            int _type = T__89;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:50:7: ( '+=' )
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:50:9: '+='
            {
            match("+="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__89"

    // $ANTLR start "T__90"
    public final void mT__90() throws RecognitionException {
        try {
            int _type = T__90;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:51:7: ( '=>' )
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:51:9: '=>'
            {
            match("=>"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__90"

    // $ANTLR start "T__91"
    public final void mT__91() throws RecognitionException {
        try {
            int _type = T__91;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:52:7: ( '~' )
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:52:9: '~'
            {
            match('~'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__91"

    // $ANTLR start "T__92"
    public final void mT__92() throws RecognitionException {
        try {
            int _type = T__92;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:53:7: ( '?' )
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:53:9: '?'
            {
            match('?'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__92"

    // $ANTLR start "T__93"
    public final void mT__93() throws RecognitionException {
        try {
            int _type = T__93;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:54:7: ( '+' )
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:54:9: '+'
            {
            match('+'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__93"

    // $ANTLR start "T__94"
    public final void mT__94() throws RecognitionException {
        try {
            int _type = T__94;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:55:7: ( '<' )
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:55:9: '<'
            {
            match('<'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__94"

    // $ANTLR start "T__95"
    public final void mT__95() throws RecognitionException {
        try {
            int _type = T__95;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:56:7: ( '>' )
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:56:9: '>'
            {
            match('>'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__95"

    // $ANTLR start "T__96"
    public final void mT__96() throws RecognitionException {
        try {
            int _type = T__96;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:57:7: ( '$' )
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:57:9: '$'
            {
            match('$'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__96"

    // $ANTLR start "SL_COMMENT"
    public final void mSL_COMMENT() throws RecognitionException {
        try {
            int _type = SL_COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:805:3: ( '//' ( ' $ANTLR ' SRC | (~ ( '\\r' | '\\n' ) )* ) ( '\\r' )? '\\n' )
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:806:3: '//' ( ' $ANTLR ' SRC | (~ ( '\\r' | '\\n' ) )* ) ( '\\r' )? '\\n'
            {
            match("//"); 

            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:807:3: ( ' $ANTLR ' SRC | (~ ( '\\r' | '\\n' ) )* )
            int alt2=2;
            alt2 = dfa2.predict(input);
            switch (alt2) {
                case 1 :
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:808:5: ' $ANTLR ' SRC
                    {
                    match(" $ANTLR "); 

                    mSRC(); 

                    }
                    break;
                case 2 :
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:810:5: (~ ( '\\r' | '\\n' ) )*
                    {
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:810:5: (~ ( '\\r' | '\\n' ) )*
                    loop1:
                    do {
                        int alt1=2;
                        int LA1_0 = input.LA(1);

                        if ( ((LA1_0>='\u0000' && LA1_0<='\t')||(LA1_0>='\u000B' && LA1_0<='\f')||(LA1_0>='\u000E' && LA1_0<='\uFFFF')) ) {
                            alt1=1;
                        }


                        switch (alt1) {
                    	case 1 :
                    	    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:810:5: ~ ( '\\r' | '\\n' )
                    	    {
                    	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='\t')||(input.LA(1)>='\u000B' && input.LA(1)<='\f')||(input.LA(1)>='\u000E' && input.LA(1)<='\uFFFF') ) {
                    	        input.consume();

                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;}


                    	    }
                    	    break;

                    	default :
                    	    break loop1;
                        }
                    } while (true);


                    }
                    break;

            }

            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:815:3: ( '\\r' )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0=='\r') ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:815:3: '\\r'
                    {
                    match('\r'); 

                    }
                    break;

            }

            match('\n'); 
            _channel=HIDDEN;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SL_COMMENT"

    // $ANTLR start "ML_COMMENT"
    public final void mML_COMMENT() throws RecognitionException {
        try {
            int _type = ML_COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:819:3: ( '/*' ( . )* '*/' )
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:820:3: '/*' ( . )* '*/'
            {
            match("/*"); 

            if (input.LA(1)=='*') _type=DOC_COMMENT; else _channel=HIDDEN;
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:820:73: ( . )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0=='*') ) {
                    int LA4_1 = input.LA(2);

                    if ( (LA4_1=='/') ) {
                        alt4=2;
                    }
                    else if ( ((LA4_1>='\u0000' && LA4_1<='.')||(LA4_1>='0' && LA4_1<='\uFFFF')) ) {
                        alt4=1;
                    }


                }
                else if ( ((LA4_0>='\u0000' && LA4_0<=')')||(LA4_0>='+' && LA4_0<='\uFFFF')) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:820:73: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);

            match("*/"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ML_COMMENT"

    // $ANTLR start "STRING_LITERAL"
    public final void mSTRING_LITERAL() throws RecognitionException {
        try {
            int _type = STRING_LITERAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:824:3: ( '\\'' ( LITERAL_CHAR )* '\\'' )
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:825:3: '\\'' ( LITERAL_CHAR )* '\\''
            {
            match('\''); 
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:825:8: ( LITERAL_CHAR )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( ((LA5_0>='\u0000' && LA5_0<='&')||(LA5_0>='(' && LA5_0<='\uFFFF')) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:825:8: LITERAL_CHAR
            	    {
            	    mLITERAL_CHAR(); 

            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);

            match('\''); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "STRING_LITERAL"

    // $ANTLR start "LITERAL_CHAR"
    public final void mLITERAL_CHAR() throws RecognitionException {
        try {
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:830:3: ( ESC | ~ ( '\\'' | '\\\\' ) )
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0=='\\') ) {
                alt6=1;
            }
            else if ( ((LA6_0>='\u0000' && LA6_0<='&')||(LA6_0>='(' && LA6_0<='[')||(LA6_0>=']' && LA6_0<='\uFFFF')) ) {
                alt6=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;
            }
            switch (alt6) {
                case 1 :
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:831:3: ESC
                    {
                    mESC(); 

                    }
                    break;
                case 2 :
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:833:3: ~ ( '\\'' | '\\\\' )
                    {
                    if ( (input.LA(1)>='\u0000' && input.LA(1)<='&')||(input.LA(1)>='(' && input.LA(1)<='[')||(input.LA(1)>=']' && input.LA(1)<='\uFFFF') ) {
                        input.consume();

                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;}


                    }
                    break;

            }
        }
        finally {
        }
    }
    // $ANTLR end "LITERAL_CHAR"

    // $ANTLR start "DOUBLE_QUOTE_STRING_LITERAL"
    public final void mDOUBLE_QUOTE_STRING_LITERAL() throws RecognitionException {
        try {
            int _type = DOUBLE_QUOTE_STRING_LITERAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:840:3: ( '\"' ( ESC | ~ ( '\\\\' | '\"' ) )* '\"' )
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:841:3: '\"' ( ESC | ~ ( '\\\\' | '\"' ) )* '\"'
            {
            match('\"'); 
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:842:3: ( ESC | ~ ( '\\\\' | '\"' ) )*
            loop7:
            do {
                int alt7=3;
                int LA7_0 = input.LA(1);

                if ( (LA7_0=='\\') ) {
                    alt7=1;
                }
                else if ( ((LA7_0>='\u0000' && LA7_0<='!')||(LA7_0>='#' && LA7_0<='[')||(LA7_0>=']' && LA7_0<='\uFFFF')) ) {
                    alt7=2;
                }


                switch (alt7) {
            	case 1 :
            	    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:843:5: ESC
            	    {
            	    mESC(); 

            	    }
            	    break;
            	case 2 :
            	    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:845:5: ~ ( '\\\\' | '\"' )
            	    {
            	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='!')||(input.LA(1)>='#' && input.LA(1)<='[')||(input.LA(1)>=']' && input.LA(1)<='\uFFFF') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop7;
                }
            } while (true);

            match('\"'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DOUBLE_QUOTE_STRING_LITERAL"

    // $ANTLR start "DOUBLE_ANGLE_STRING_LITERAL"
    public final void mDOUBLE_ANGLE_STRING_LITERAL() throws RecognitionException {
        try {
            int _type = DOUBLE_ANGLE_STRING_LITERAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:854:3: ( '<<' ( . )* '>>' )
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:855:3: '<<' ( . )* '>>'
            {
            match("<<"); 

            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:855:8: ( . )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0=='>') ) {
                    int LA8_1 = input.LA(2);

                    if ( (LA8_1=='>') ) {
                        alt8=2;
                    }
                    else if ( ((LA8_1>='\u0000' && LA8_1<='=')||(LA8_1>='?' && LA8_1<='\uFFFF')) ) {
                        alt8=1;
                    }


                }
                else if ( ((LA8_0>='\u0000' && LA8_0<='=')||(LA8_0>='?' && LA8_0<='\uFFFF')) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:855:8: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop8;
                }
            } while (true);

            match(">>"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DOUBLE_ANGLE_STRING_LITERAL"

    // $ANTLR start "ESC"
    public final void mESC() throws RecognitionException {
        try {
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:860:3: ( '\\\\' ( 'n' | 'r' | 't' | 'b' | 'f' | '\"' | '\\'' | '\\\\' | '>' | 'u' XDIGIT XDIGIT XDIGIT XDIGIT | . ) )
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:861:3: '\\\\' ( 'n' | 'r' | 't' | 'b' | 'f' | '\"' | '\\'' | '\\\\' | '>' | 'u' XDIGIT XDIGIT XDIGIT XDIGIT | . )
            {
            match('\\'); 
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:862:3: ( 'n' | 'r' | 't' | 'b' | 'f' | '\"' | '\\'' | '\\\\' | '>' | 'u' XDIGIT XDIGIT XDIGIT XDIGIT | . )
            int alt9=11;
            alt9 = dfa9.predict(input);
            switch (alt9) {
                case 1 :
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:863:5: 'n'
                    {
                    match('n'); 

                    }
                    break;
                case 2 :
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:864:7: 'r'
                    {
                    match('r'); 

                    }
                    break;
                case 3 :
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:865:7: 't'
                    {
                    match('t'); 

                    }
                    break;
                case 4 :
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:866:7: 'b'
                    {
                    match('b'); 

                    }
                    break;
                case 5 :
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:867:7: 'f'
                    {
                    match('f'); 

                    }
                    break;
                case 6 :
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:868:7: '\"'
                    {
                    match('\"'); 

                    }
                    break;
                case 7 :
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:869:7: '\\''
                    {
                    match('\''); 

                    }
                    break;
                case 8 :
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:870:7: '\\\\'
                    {
                    match('\\'); 

                    }
                    break;
                case 9 :
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:871:7: '>'
                    {
                    match('>'); 

                    }
                    break;
                case 10 :
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:872:7: 'u' XDIGIT XDIGIT XDIGIT XDIGIT
                    {
                    match('u'); 
                    mXDIGIT(); 
                    mXDIGIT(); 
                    mXDIGIT(); 
                    mXDIGIT(); 

                    }
                    break;
                case 11 :
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:873:7: .
                    {
                    matchAny(); 

                    }
                    break;

            }


            }

        }
        finally {
        }
    }
    // $ANTLR end "ESC"

    // $ANTLR start "XDIGIT"
    public final void mXDIGIT() throws RecognitionException {
        try {
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:879:3: ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' )
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:
            {
            if ( (input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='F')||(input.LA(1)>='a' && input.LA(1)<='f') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "XDIGIT"

    // $ANTLR start "INT"
    public final void mINT() throws RecognitionException {
        try {
            int _type = INT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:886:3: ( ( '0' .. '9' )+ )
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:887:3: ( '0' .. '9' )+
            {
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:887:3: ( '0' .. '9' )+
            int cnt10=0;
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( ((LA10_0>='0' && LA10_0<='9')) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:887:3: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }
            	    break;

            	default :
            	    if ( cnt10 >= 1 ) break loop10;
                        EarlyExitException eee =
                            new EarlyExitException(10, input);
                        throw eee;
                }
                cnt10++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "INT"

    // $ANTLR start "ARG_ACTION"
    public final void mARG_ACTION() throws RecognitionException {
        try {
            int _type = ARG_ACTION;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:891:3: ( NESTED_ARG_ACTION )
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:892:3: NESTED_ARG_ACTION
            {
            mNESTED_ARG_ACTION(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ARG_ACTION"

    // $ANTLR start "NESTED_ARG_ACTION"
    public final void mNESTED_ARG_ACTION() throws RecognitionException {
        try {
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:897:3: ( '[' ( options {greedy=false; k=1; } : NESTED_ARG_ACTION | ACTION_STRING_LITERAL | ACTION_CHAR_LITERAL | . )* ']' )
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:898:3: '[' ( options {greedy=false; k=1; } : NESTED_ARG_ACTION | ACTION_STRING_LITERAL | ACTION_CHAR_LITERAL | . )* ']'
            {
            match('['); 
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:899:3: ( options {greedy=false; k=1; } : NESTED_ARG_ACTION | ACTION_STRING_LITERAL | ACTION_CHAR_LITERAL | . )*
            loop11:
            do {
                int alt11=5;
                int LA11_0 = input.LA(1);

                if ( (LA11_0==']') ) {
                    alt11=5;
                }
                else if ( (LA11_0=='[') ) {
                    alt11=1;
                }
                else if ( (LA11_0=='\"') ) {
                    alt11=2;
                }
                else if ( (LA11_0=='\'') ) {
                    alt11=3;
                }
                else if ( ((LA11_0>='\u0000' && LA11_0<='!')||(LA11_0>='#' && LA11_0<='&')||(LA11_0>='(' && LA11_0<='Z')||LA11_0=='\\'||(LA11_0>='^' && LA11_0<='\uFFFF')) ) {
                    alt11=4;
                }


                switch (alt11) {
            	case 1 :
            	    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:899:35: NESTED_ARG_ACTION
            	    {
            	    mNESTED_ARG_ACTION(); 

            	    }
            	    break;
            	case 2 :
            	    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:900:7: ACTION_STRING_LITERAL
            	    {
            	    mACTION_STRING_LITERAL(); 

            	    }
            	    break;
            	case 3 :
            	    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:901:7: ACTION_CHAR_LITERAL
            	    {
            	    mACTION_CHAR_LITERAL(); 

            	    }
            	    break;
            	case 4 :
            	    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:902:7: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop11;
                }
            } while (true);

            match(']'); 

            }

        }
        finally {
        }
    }
    // $ANTLR end "NESTED_ARG_ACTION"

    // $ANTLR start "ACTION"
    public final void mACTION() throws RecognitionException {
        try {
            int _type = ACTION;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:909:3: ( NESTED_ACTION ( '?' )? )
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:910:3: NESTED_ACTION ( '?' )?
            {
            mNESTED_ACTION(); 
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:910:17: ( '?' )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0=='?') ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:910:18: '?'
                    {
                    match('?'); 
                    _type = SEMPRED;

                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ACTION"

    // $ANTLR start "NESTED_ACTION"
    public final void mNESTED_ACTION() throws RecognitionException {
        try {
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:915:3: ( '{' ( options {greedy=false; k=2; } : NESTED_ACTION | SL_COMMENT | ML_COMMENT | ACTION_STRING_LITERAL | ACTION_CHAR_LITERAL | . )* '}' )
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:916:3: '{' ( options {greedy=false; k=2; } : NESTED_ACTION | SL_COMMENT | ML_COMMENT | ACTION_STRING_LITERAL | ACTION_CHAR_LITERAL | . )* '}'
            {
            match('{'); 
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:917:3: ( options {greedy=false; k=2; } : NESTED_ACTION | SL_COMMENT | ML_COMMENT | ACTION_STRING_LITERAL | ACTION_CHAR_LITERAL | . )*
            loop13:
            do {
                int alt13=7;
                alt13 = dfa13.predict(input);
                switch (alt13) {
            	case 1 :
            	    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:917:35: NESTED_ACTION
            	    {
            	    mNESTED_ACTION(); 

            	    }
            	    break;
            	case 2 :
            	    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:918:7: SL_COMMENT
            	    {
            	    mSL_COMMENT(); 

            	    }
            	    break;
            	case 3 :
            	    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:919:7: ML_COMMENT
            	    {
            	    mML_COMMENT(); 

            	    }
            	    break;
            	case 4 :
            	    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:920:7: ACTION_STRING_LITERAL
            	    {
            	    mACTION_STRING_LITERAL(); 

            	    }
            	    break;
            	case 5 :
            	    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:921:7: ACTION_CHAR_LITERAL
            	    {
            	    mACTION_CHAR_LITERAL(); 

            	    }
            	    break;
            	case 6 :
            	    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:922:7: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop13;
                }
            } while (true);

            match('}'); 

            }

        }
        finally {
        }
    }
    // $ANTLR end "NESTED_ACTION"

    // $ANTLR start "ACTION_CHAR_LITERAL"
    public final void mACTION_CHAR_LITERAL() throws RecognitionException {
        try {
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:929:3: ( '\\'' ( ACTION_ESC | ~ ( '\\\\' | '\\'' ) ) '\\'' )
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:930:3: '\\'' ( ACTION_ESC | ~ ( '\\\\' | '\\'' ) ) '\\''
            {
            match('\''); 
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:931:3: ( ACTION_ESC | ~ ( '\\\\' | '\\'' ) )
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0=='\\') ) {
                alt14=1;
            }
            else if ( ((LA14_0>='\u0000' && LA14_0<='&')||(LA14_0>='(' && LA14_0<='[')||(LA14_0>=']' && LA14_0<='\uFFFF')) ) {
                alt14=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 14, 0, input);

                throw nvae;
            }
            switch (alt14) {
                case 1 :
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:932:5: ACTION_ESC
                    {
                    mACTION_ESC(); 

                    }
                    break;
                case 2 :
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:934:5: ~ ( '\\\\' | '\\'' )
                    {
                    if ( (input.LA(1)>='\u0000' && input.LA(1)<='&')||(input.LA(1)>='(' && input.LA(1)<='[')||(input.LA(1)>=']' && input.LA(1)<='\uFFFF') ) {
                        input.consume();

                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;}


                    }
                    break;

            }

            match('\''); 

            }

        }
        finally {
        }
    }
    // $ANTLR end "ACTION_CHAR_LITERAL"

    // $ANTLR start "ACTION_STRING_LITERAL"
    public final void mACTION_STRING_LITERAL() throws RecognitionException {
        try {
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:944:3: ( '\"' ( ACTION_ESC | ~ ( '\\\\' | '\"' ) )* '\"' )
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:945:3: '\"' ( ACTION_ESC | ~ ( '\\\\' | '\"' ) )* '\"'
            {
            match('\"'); 
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:946:3: ( ACTION_ESC | ~ ( '\\\\' | '\"' ) )*
            loop15:
            do {
                int alt15=3;
                int LA15_0 = input.LA(1);

                if ( (LA15_0=='\\') ) {
                    alt15=1;
                }
                else if ( ((LA15_0>='\u0000' && LA15_0<='!')||(LA15_0>='#' && LA15_0<='[')||(LA15_0>=']' && LA15_0<='\uFFFF')) ) {
                    alt15=2;
                }


                switch (alt15) {
            	case 1 :
            	    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:947:5: ACTION_ESC
            	    {
            	    mACTION_ESC(); 

            	    }
            	    break;
            	case 2 :
            	    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:949:5: ~ ( '\\\\' | '\"' )
            	    {
            	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='!')||(input.LA(1)>='#' && input.LA(1)<='[')||(input.LA(1)>=']' && input.LA(1)<='\uFFFF') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop15;
                }
            } while (true);

            match('\"'); 

            }

        }
        finally {
        }
    }
    // $ANTLR end "ACTION_STRING_LITERAL"

    // $ANTLR start "ACTION_ESC"
    public final void mACTION_ESC() throws RecognitionException {
        try {
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:959:3: ( '\\\\\\'' | '\\\\' '\"' | '\\\\' ~ ( '\\'' | '\"' ) )
            int alt16=3;
            int LA16_0 = input.LA(1);

            if ( (LA16_0=='\\') ) {
                int LA16_1 = input.LA(2);

                if ( (LA16_1=='\'') ) {
                    alt16=1;
                }
                else if ( (LA16_1=='\"') ) {
                    alt16=2;
                }
                else if ( ((LA16_1>='\u0000' && LA16_1<='!')||(LA16_1>='#' && LA16_1<='&')||(LA16_1>='(' && LA16_1<='\uFFFF')) ) {
                    alt16=3;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 16, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 16, 0, input);

                throw nvae;
            }
            switch (alt16) {
                case 1 :
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:960:3: '\\\\\\''
                    {
                    match("\\'"); 


                    }
                    break;
                case 2 :
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:961:5: '\\\\' '\"'
                    {
                    match('\\'); 
                    match('\"'); 

                    }
                    break;
                case 3 :
                    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:962:5: '\\\\' ~ ( '\\'' | '\"' )
                    {
                    match('\\'); 
                    if ( (input.LA(1)>='\u0000' && input.LA(1)<='!')||(input.LA(1)>='#' && input.LA(1)<='&')||(input.LA(1)>='(' && input.LA(1)<='\uFFFF') ) {
                        input.consume();

                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;}


                    }
                    break;

            }
        }
        finally {
        }
    }
    // $ANTLR end "ACTION_ESC"

    // $ANTLR start "TOKEN_REF"
    public final void mTOKEN_REF() throws RecognitionException {
        try {
            int _type = TOKEN_REF;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:970:3: ( 'A' .. 'Z' ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )* )
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:971:3: 'A' .. 'Z' ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
            {
            matchRange('A','Z'); 
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:972:3: ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
            loop17:
            do {
                int alt17=2;
                int LA17_0 = input.LA(1);

                if ( ((LA17_0>='0' && LA17_0<='9')||(LA17_0>='A' && LA17_0<='Z')||LA17_0=='_'||(LA17_0>='a' && LA17_0<='z')) ) {
                    alt17=1;
                }


                switch (alt17) {
            	case 1 :
            	    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:
            	    {
            	    if ( (input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop17;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "TOKEN_REF"

    // $ANTLR start "RULE_REF"
    public final void mRULE_REF() throws RecognitionException {
        try {
            int _type = RULE_REF;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:981:3: ( 'a' .. 'z' ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )* )
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:982:3: 'a' .. 'z' ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
            {
            matchRange('a','z'); 
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:983:3: ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
            loop18:
            do {
                int alt18=2;
                int LA18_0 = input.LA(1);

                if ( ((LA18_0>='0' && LA18_0<='9')||(LA18_0>='A' && LA18_0<='Z')||LA18_0=='_'||(LA18_0>='a' && LA18_0<='z')) ) {
                    alt18=1;
                }


                switch (alt18) {
            	case 1 :
            	    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:
            	    {
            	    if ( (input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop18;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_REF"

    // $ANTLR start "OPTIONS"
    public final void mOPTIONS() throws RecognitionException {
        try {
            int _type = OPTIONS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:995:3: ( 'options' WS_LOOP '{' )
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:996:3: 'options' WS_LOOP '{'
            {
            match("options"); 

            mWS_LOOP(); 
            match('{'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "OPTIONS"

    // $ANTLR start "TOKENS"
    public final void mTOKENS() throws RecognitionException {
        try {
            int _type = TOKENS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:1000:3: ( 'tokens' WS_LOOP '{' )
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:1001:3: 'tokens' WS_LOOP '{'
            {
            match("tokens"); 

            mWS_LOOP(); 
            match('{'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "TOKENS"

    // $ANTLR start "SRC"
    public final void mSRC() throws RecognitionException {
        try {
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:1010:3: ( 'src' ' ' ACTION_STRING_LITERAL ' ' INT )
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:1011:3: 'src' ' ' ACTION_STRING_LITERAL ' ' INT
            {
            match("src"); 

            match(' '); 
            mACTION_STRING_LITERAL(); 
            match(' '); 
            mINT(); 

            }

        }
        finally {
        }
    }
    // $ANTLR end "SRC"

    // $ANTLR start "WS"
    public final void mWS() throws RecognitionException {
        try {
            int _type = WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:1015:3: ( ( ' ' | '\\t' | ( '\\r' )? '\\n' )+ )
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:1016:3: ( ' ' | '\\t' | ( '\\r' )? '\\n' )+
            {
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:1016:3: ( ' ' | '\\t' | ( '\\r' )? '\\n' )+
            int cnt20=0;
            loop20:
            do {
                int alt20=4;
                switch ( input.LA(1) ) {
                case ' ':
                    {
                    alt20=1;
                    }
                    break;
                case '\t':
                    {
                    alt20=2;
                    }
                    break;
                case '\n':
                case '\r':
                    {
                    alt20=3;
                    }
                    break;

                }

                switch (alt20) {
            	case 1 :
            	    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:1017:5: ' '
            	    {
            	    match(' '); 

            	    }
            	    break;
            	case 2 :
            	    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:1018:7: '\\t'
            	    {
            	    match('\t'); 

            	    }
            	    break;
            	case 3 :
            	    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:1019:7: ( '\\r' )? '\\n'
            	    {
            	    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:1019:7: ( '\\r' )?
            	    int alt19=2;
            	    int LA19_0 = input.LA(1);

            	    if ( (LA19_0=='\r') ) {
            	        alt19=1;
            	    }
            	    switch (alt19) {
            	        case 1 :
            	            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:1019:7: '\\r'
            	            {
            	            match('\r'); 

            	            }
            	            break;

            	    }

            	    match('\n'); 

            	    }
            	    break;

            	default :
            	    if ( cnt20 >= 1 ) break loop20;
                        EarlyExitException eee =
                            new EarlyExitException(20, input);
                        throw eee;
                }
                cnt20++;
            } while (true);

            _channel=HIDDEN;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "WS"

    // $ANTLR start "WS_LOOP"
    public final void mWS_LOOP() throws RecognitionException {
        try {
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:1026:3: ( ( WS | SL_COMMENT | ML_COMMENT )* )
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:1027:3: ( WS | SL_COMMENT | ML_COMMENT )*
            {
            // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:1027:3: ( WS | SL_COMMENT | ML_COMMENT )*
            loop21:
            do {
                int alt21=4;
                int LA21_0 = input.LA(1);

                if ( ((LA21_0>='\t' && LA21_0<='\n')||LA21_0=='\r'||LA21_0==' ') ) {
                    alt21=1;
                }
                else if ( (LA21_0=='/') ) {
                    int LA21_3 = input.LA(2);

                    if ( (LA21_3=='/') ) {
                        alt21=2;
                    }
                    else if ( (LA21_3=='*') ) {
                        alt21=3;
                    }


                }


                switch (alt21) {
            	case 1 :
            	    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:1028:5: WS
            	    {
            	    mWS(); 

            	    }
            	    break;
            	case 2 :
            	    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:1029:7: SL_COMMENT
            	    {
            	    mSL_COMMENT(); 

            	    }
            	    break;
            	case 3 :
            	    // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:1030:7: ML_COMMENT
            	    {
            	    mML_COMMENT(); 

            	    }
            	    break;

            	default :
            	    break loop21;
                }
            } while (true);


            }

        }
        finally {
        }
    }
    // $ANTLR end "WS_LOOP"

    public void mTokens() throws RecognitionException {
        // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:1:8: ( SCOPE | FRAGMENT | TREE_BEGIN | ROOT | BANG | RANGE | REWRITE | SEMI | COLON | DOT | ASSIGN | T__69 | T__70 | T__71 | T__72 | T__73 | T__74 | T__75 | T__76 | T__77 | T__78 | T__79 | T__80 | T__81 | T__82 | T__83 | T__84 | T__85 | T__86 | T__87 | T__88 | T__89 | T__90 | T__91 | T__92 | T__93 | T__94 | T__95 | T__96 | SL_COMMENT | ML_COMMENT | STRING_LITERAL | DOUBLE_QUOTE_STRING_LITERAL | DOUBLE_ANGLE_STRING_LITERAL | INT | ARG_ACTION | ACTION | TOKEN_REF | RULE_REF | OPTIONS | TOKENS | WS )
        int alt22=52;
        alt22 = dfa22.predict(input);
        switch (alt22) {
            case 1 :
                // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:1:10: SCOPE
                {
                mSCOPE(); 

                }
                break;
            case 2 :
                // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:1:16: FRAGMENT
                {
                mFRAGMENT(); 

                }
                break;
            case 3 :
                // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:1:25: TREE_BEGIN
                {
                mTREE_BEGIN(); 

                }
                break;
            case 4 :
                // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:1:36: ROOT
                {
                mROOT(); 

                }
                break;
            case 5 :
                // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:1:41: BANG
                {
                mBANG(); 

                }
                break;
            case 6 :
                // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:1:46: RANGE
                {
                mRANGE(); 

                }
                break;
            case 7 :
                // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:1:52: REWRITE
                {
                mREWRITE(); 

                }
                break;
            case 8 :
                // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:1:60: SEMI
                {
                mSEMI(); 

                }
                break;
            case 9 :
                // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:1:65: COLON
                {
                mCOLON(); 

                }
                break;
            case 10 :
                // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:1:71: DOT
                {
                mDOT(); 

                }
                break;
            case 11 :
                // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:1:75: ASSIGN
                {
                mASSIGN(); 

                }
                break;
            case 12 :
                // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:1:82: T__69
                {
                mT__69(); 

                }
                break;
            case 13 :
                // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:1:88: T__70
                {
                mT__70(); 

                }
                break;
            case 14 :
                // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:1:94: T__71
                {
                mT__71(); 

                }
                break;
            case 15 :
                // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:1:100: T__72
                {
                mT__72(); 

                }
                break;
            case 16 :
                // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:1:106: T__73
                {
                mT__73(); 

                }
                break;
            case 17 :
                // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:1:112: T__74
                {
                mT__74(); 

                }
                break;
            case 18 :
                // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:1:118: T__75
                {
                mT__75(); 

                }
                break;
            case 19 :
                // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:1:124: T__76
                {
                mT__76(); 

                }
                break;
            case 20 :
                // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:1:130: T__77
                {
                mT__77(); 

                }
                break;
            case 21 :
                // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:1:136: T__78
                {
                mT__78(); 

                }
                break;
            case 22 :
                // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:1:142: T__79
                {
                mT__79(); 

                }
                break;
            case 23 :
                // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:1:148: T__80
                {
                mT__80(); 

                }
                break;
            case 24 :
                // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:1:154: T__81
                {
                mT__81(); 

                }
                break;
            case 25 :
                // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:1:160: T__82
                {
                mT__82(); 

                }
                break;
            case 26 :
                // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:1:166: T__83
                {
                mT__83(); 

                }
                break;
            case 27 :
                // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:1:172: T__84
                {
                mT__84(); 

                }
                break;
            case 28 :
                // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:1:178: T__85
                {
                mT__85(); 

                }
                break;
            case 29 :
                // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:1:184: T__86
                {
                mT__86(); 

                }
                break;
            case 30 :
                // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:1:190: T__87
                {
                mT__87(); 

                }
                break;
            case 31 :
                // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:1:196: T__88
                {
                mT__88(); 

                }
                break;
            case 32 :
                // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:1:202: T__89
                {
                mT__89(); 

                }
                break;
            case 33 :
                // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:1:208: T__90
                {
                mT__90(); 

                }
                break;
            case 34 :
                // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:1:214: T__91
                {
                mT__91(); 

                }
                break;
            case 35 :
                // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:1:220: T__92
                {
                mT__92(); 

                }
                break;
            case 36 :
                // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:1:226: T__93
                {
                mT__93(); 

                }
                break;
            case 37 :
                // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:1:232: T__94
                {
                mT__94(); 

                }
                break;
            case 38 :
                // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:1:238: T__95
                {
                mT__95(); 

                }
                break;
            case 39 :
                // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:1:244: T__96
                {
                mT__96(); 

                }
                break;
            case 40 :
                // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:1:250: SL_COMMENT
                {
                mSL_COMMENT(); 

                }
                break;
            case 41 :
                // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:1:261: ML_COMMENT
                {
                mML_COMMENT(); 

                }
                break;
            case 42 :
                // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:1:272: STRING_LITERAL
                {
                mSTRING_LITERAL(); 

                }
                break;
            case 43 :
                // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:1:287: DOUBLE_QUOTE_STRING_LITERAL
                {
                mDOUBLE_QUOTE_STRING_LITERAL(); 

                }
                break;
            case 44 :
                // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:1:315: DOUBLE_ANGLE_STRING_LITERAL
                {
                mDOUBLE_ANGLE_STRING_LITERAL(); 

                }
                break;
            case 45 :
                // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:1:343: INT
                {
                mINT(); 

                }
                break;
            case 46 :
                // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:1:347: ARG_ACTION
                {
                mARG_ACTION(); 

                }
                break;
            case 47 :
                // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:1:358: ACTION
                {
                mACTION(); 

                }
                break;
            case 48 :
                // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:1:365: TOKEN_REF
                {
                mTOKEN_REF(); 

                }
                break;
            case 49 :
                // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:1:375: RULE_REF
                {
                mRULE_REF(); 

                }
                break;
            case 50 :
                // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:1:384: OPTIONS
                {
                mOPTIONS(); 

                }
                break;
            case 51 :
                // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:1:392: TOKENS
                {
                mTOKENS(); 

                }
                break;
            case 52 :
                // /home/arexer/workspaces/antlride/org.deved.antlride.core/src/main/java/org/deved/antlride/internal/core/parser/ANTLR.g:1:399: WS
                {
                mWS(); 

                }
                break;

        }

    }


    protected DFA2 dfa2 = new DFA2(this);
    protected DFA9 dfa9 = new DFA9(this);
    protected DFA13 dfa13 = new DFA13(this);
    protected DFA22 dfa22 = new DFA22(this);
    static final String DFA2_eotS =
        "\20\uffff\1\2\7\uffff\1\2\3\uffff";
    static final String DFA2_eofS =
        "\34\uffff";
    static final String DFA2_minS =
        "\2\0\1\uffff\21\0\1\uffff\6\0\1\uffff";
    static final String DFA2_maxS =
        "\2\uffff\1\uffff\21\uffff\1\uffff\6\uffff\1\uffff";
    static final String DFA2_acceptS =
        "\2\uffff\1\2\21\uffff\1\1\6\uffff\1\1";
    static final String DFA2_specialS =
        "\1\22\1\4\1\uffff\1\2\1\1\1\0\1\12\1\10\1\6\1\30\1\17\1\7\1\13\1"+
        "\20\1\21\1\15\1\25\1\27\1\14\1\23\1\uffff\1\3\1\11\1\16\1\26\1\24"+
        "\1\5\1\uffff}>";
    static final String[] DFA2_transitionS = {
            "\40\2\1\1\uffdf\2",
            "\44\2\1\3\uffdb\2",
            "",
            "\101\2\1\4\uffbe\2",
            "\116\2\1\5\uffb1\2",
            "\124\2\1\6\uffab\2",
            "\114\2\1\7\uffb3\2",
            "\122\2\1\10\uffad\2",
            "\40\2\1\11\uffdf\2",
            "\163\2\1\12\uff8c\2",
            "\162\2\1\13\uff8d\2",
            "\143\2\1\14\uff9c\2",
            "\40\2\1\15\uffdf\2",
            "\42\2\1\16\uffdd\2",
            "\12\22\1\20\2\22\1\17\24\22\1\23\71\22\1\21\uffa3\22",
            "\12\24\1\20\ufff5\24",
            "\0\24",
            "\12\31\1\30\2\31\1\27\24\31\1\26\4\31\1\25\uffd8\31",
            "\12\22\1\20\2\22\1\17\24\22\1\23\71\22\1\21\uffa3\22",
            "\40\2\1\32\uffdf\2",
            "",
            "\12\22\1\20\2\22\1\17\24\22\1\23\71\22\1\21\uffa3\22",
            "\12\22\1\20\2\22\1\17\24\22\1\23\71\22\1\21\uffa3\22",
            "\12\24\1\20\ufff5\24",
            "\0\24",
            "\12\22\1\20\2\22\1\17\24\22\1\23\71\22\1\21\uffa3\22",
            "\60\2\12\33\uffc6\2",
            ""
    };

    static final short[] DFA2_eot = DFA.unpackEncodedString(DFA2_eotS);
    static final short[] DFA2_eof = DFA.unpackEncodedString(DFA2_eofS);
    static final char[] DFA2_min = DFA.unpackEncodedStringToUnsignedChars(DFA2_minS);
    static final char[] DFA2_max = DFA.unpackEncodedStringToUnsignedChars(DFA2_maxS);
    static final short[] DFA2_accept = DFA.unpackEncodedString(DFA2_acceptS);
    static final short[] DFA2_special = DFA.unpackEncodedString(DFA2_specialS);
    static final short[][] DFA2_transition;

    static {
        int numStates = DFA2_transitionS.length;
        DFA2_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA2_transition[i] = DFA.unpackEncodedString(DFA2_transitionS[i]);
        }
    }

    class DFA2 extends DFA {

        public DFA2(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 2;
            this.eot = DFA2_eot;
            this.eof = DFA2_eof;
            this.min = DFA2_min;
            this.max = DFA2_max;
            this.accept = DFA2_accept;
            this.special = DFA2_special;
            this.transition = DFA2_transition;
        }
        public String getDescription() {
            return "807:3: ( ' $ANTLR ' SRC | (~ ( '\\r' | '\\n' ) )* )";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            IntStream input = _input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA2_5 = input.LA(1);

                        s = -1;
                        if ( (LA2_5=='T') ) {s = 6;}

                        else if ( ((LA2_5>='\u0000' && LA2_5<='S')||(LA2_5>='U' && LA2_5<='\uFFFF')) ) {s = 2;}

                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA2_4 = input.LA(1);

                        s = -1;
                        if ( (LA2_4=='N') ) {s = 5;}

                        else if ( ((LA2_4>='\u0000' && LA2_4<='M')||(LA2_4>='O' && LA2_4<='\uFFFF')) ) {s = 2;}

                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA2_3 = input.LA(1);

                        s = -1;
                        if ( (LA2_3=='A') ) {s = 4;}

                        else if ( ((LA2_3>='\u0000' && LA2_3<='@')||(LA2_3>='B' && LA2_3<='\uFFFF')) ) {s = 2;}

                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA2_21 = input.LA(1);

                        s = -1;
                        if ( (LA2_21=='\r') ) {s = 15;}

                        else if ( (LA2_21=='\n') ) {s = 16;}

                        else if ( (LA2_21=='\"') ) {s = 19;}

                        else if ( (LA2_21=='\\') ) {s = 17;}

                        else if ( ((LA2_21>='\u0000' && LA2_21<='\t')||(LA2_21>='\u000B' && LA2_21<='\f')||(LA2_21>='\u000E' && LA2_21<='!')||(LA2_21>='#' && LA2_21<='[')||(LA2_21>=']' && LA2_21<='\uFFFF')) ) {s = 18;}

                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA2_1 = input.LA(1);

                        s = -1;
                        if ( (LA2_1=='$') ) {s = 3;}

                        else if ( ((LA2_1>='\u0000' && LA2_1<='#')||(LA2_1>='%' && LA2_1<='\uFFFF')) ) {s = 2;}

                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA2_26 = input.LA(1);

                        s = -1;
                        if ( ((LA2_26>='\u0000' && LA2_26<='/')||(LA2_26>=':' && LA2_26<='\uFFFF')) ) {s = 2;}

                        else if ( ((LA2_26>='0' && LA2_26<='9')) ) {s = 27;}

                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA2_8 = input.LA(1);

                        s = -1;
                        if ( (LA2_8==' ') ) {s = 9;}

                        else if ( ((LA2_8>='\u0000' && LA2_8<='\u001F')||(LA2_8>='!' && LA2_8<='\uFFFF')) ) {s = 2;}

                        if ( s>=0 ) return s;
                        break;
                    case 7 : 
                        int LA2_11 = input.LA(1);

                        s = -1;
                        if ( (LA2_11=='c') ) {s = 12;}

                        else if ( ((LA2_11>='\u0000' && LA2_11<='b')||(LA2_11>='d' && LA2_11<='\uFFFF')) ) {s = 2;}

                        if ( s>=0 ) return s;
                        break;
                    case 8 : 
                        int LA2_7 = input.LA(1);

                        s = -1;
                        if ( (LA2_7=='R') ) {s = 8;}

                        else if ( ((LA2_7>='\u0000' && LA2_7<='Q')||(LA2_7>='S' && LA2_7<='\uFFFF')) ) {s = 2;}

                        if ( s>=0 ) return s;
                        break;
                    case 9 : 
                        int LA2_22 = input.LA(1);

                        s = -1;
                        if ( (LA2_22=='\r') ) {s = 15;}

                        else if ( (LA2_22=='\n') ) {s = 16;}

                        else if ( (LA2_22=='\"') ) {s = 19;}

                        else if ( (LA2_22=='\\') ) {s = 17;}

                        else if ( ((LA2_22>='\u0000' && LA2_22<='\t')||(LA2_22>='\u000B' && LA2_22<='\f')||(LA2_22>='\u000E' && LA2_22<='!')||(LA2_22>='#' && LA2_22<='[')||(LA2_22>=']' && LA2_22<='\uFFFF')) ) {s = 18;}

                        if ( s>=0 ) return s;
                        break;
                    case 10 : 
                        int LA2_6 = input.LA(1);

                        s = -1;
                        if ( (LA2_6=='L') ) {s = 7;}

                        else if ( ((LA2_6>='\u0000' && LA2_6<='K')||(LA2_6>='M' && LA2_6<='\uFFFF')) ) {s = 2;}

                        if ( s>=0 ) return s;
                        break;
                    case 11 : 
                        int LA2_12 = input.LA(1);

                        s = -1;
                        if ( (LA2_12==' ') ) {s = 13;}

                        else if ( ((LA2_12>='\u0000' && LA2_12<='\u001F')||(LA2_12>='!' && LA2_12<='\uFFFF')) ) {s = 2;}

                        if ( s>=0 ) return s;
                        break;
                    case 12 : 
                        int LA2_18 = input.LA(1);

                        s = -1;
                        if ( (LA2_18=='\r') ) {s = 15;}

                        else if ( (LA2_18=='\n') ) {s = 16;}

                        else if ( (LA2_18=='\"') ) {s = 19;}

                        else if ( (LA2_18=='\\') ) {s = 17;}

                        else if ( ((LA2_18>='\u0000' && LA2_18<='\t')||(LA2_18>='\u000B' && LA2_18<='\f')||(LA2_18>='\u000E' && LA2_18<='!')||(LA2_18>='#' && LA2_18<='[')||(LA2_18>=']' && LA2_18<='\uFFFF')) ) {s = 18;}

                        if ( s>=0 ) return s;
                        break;
                    case 13 : 
                        int LA2_15 = input.LA(1);

                        s = -1;
                        if ( (LA2_15=='\n') ) {s = 16;}

                        else if ( ((LA2_15>='\u0000' && LA2_15<='\t')||(LA2_15>='\u000B' && LA2_15<='\uFFFF')) ) {s = 20;}

                        if ( s>=0 ) return s;
                        break;
                    case 14 : 
                        int LA2_23 = input.LA(1);

                        s = -1;
                        if ( (LA2_23=='\n') ) {s = 16;}

                        else if ( ((LA2_23>='\u0000' && LA2_23<='\t')||(LA2_23>='\u000B' && LA2_23<='\uFFFF')) ) {s = 20;}

                        if ( s>=0 ) return s;
                        break;
                    case 15 : 
                        int LA2_10 = input.LA(1);

                        s = -1;
                        if ( (LA2_10=='r') ) {s = 11;}

                        else if ( ((LA2_10>='\u0000' && LA2_10<='q')||(LA2_10>='s' && LA2_10<='\uFFFF')) ) {s = 2;}

                        if ( s>=0 ) return s;
                        break;
                    case 16 : 
                        int LA2_13 = input.LA(1);

                        s = -1;
                        if ( ((LA2_13>='\u0000' && LA2_13<='!')||(LA2_13>='#' && LA2_13<='\uFFFF')) ) {s = 2;}

                        else if ( (LA2_13=='\"') ) {s = 14;}

                        if ( s>=0 ) return s;
                        break;
                    case 17 : 
                        int LA2_14 = input.LA(1);

                        s = -1;
                        if ( (LA2_14=='\r') ) {s = 15;}

                        else if ( (LA2_14=='\n') ) {s = 16;}

                        else if ( (LA2_14=='\\') ) {s = 17;}

                        else if ( ((LA2_14>='\u0000' && LA2_14<='\t')||(LA2_14>='\u000B' && LA2_14<='\f')||(LA2_14>='\u000E' && LA2_14<='!')||(LA2_14>='#' && LA2_14<='[')||(LA2_14>=']' && LA2_14<='\uFFFF')) ) {s = 18;}

                        else if ( (LA2_14=='\"') ) {s = 19;}

                        if ( s>=0 ) return s;
                        break;
                    case 18 : 
                        int LA2_0 = input.LA(1);

                        s = -1;
                        if ( (LA2_0==' ') ) {s = 1;}

                        else if ( ((LA2_0>='\u0000' && LA2_0<='\u001F')||(LA2_0>='!' && LA2_0<='\uFFFF')) ) {s = 2;}

                        if ( s>=0 ) return s;
                        break;
                    case 19 : 
                        int LA2_19 = input.LA(1);

                        s = -1;
                        if ( ((LA2_19>='\u0000' && LA2_19<='\u001F')||(LA2_19>='!' && LA2_19<='\uFFFF')) ) {s = 2;}

                        else if ( (LA2_19==' ') ) {s = 26;}

                        if ( s>=0 ) return s;
                        break;
                    case 20 : 
                        int LA2_25 = input.LA(1);

                        s = -1;
                        if ( (LA2_25=='\r') ) {s = 15;}

                        else if ( (LA2_25=='\n') ) {s = 16;}

                        else if ( (LA2_25=='\"') ) {s = 19;}

                        else if ( (LA2_25=='\\') ) {s = 17;}

                        else if ( ((LA2_25>='\u0000' && LA2_25<='\t')||(LA2_25>='\u000B' && LA2_25<='\f')||(LA2_25>='\u000E' && LA2_25<='!')||(LA2_25>='#' && LA2_25<='[')||(LA2_25>=']' && LA2_25<='\uFFFF')) ) {s = 18;}

                        if ( s>=0 ) return s;
                        break;
                    case 21 : 
                        int LA2_16 = input.LA(1);

                        s = -1;
                        if ( ((LA2_16>='\u0000' && LA2_16<='\uFFFF')) ) {s = 20;}

                        else s = 2;

                        if ( s>=0 ) return s;
                        break;
                    case 22 : 
                        int LA2_24 = input.LA(1);

                        s = -1;
                        if ( ((LA2_24>='\u0000' && LA2_24<='\uFFFF')) ) {s = 20;}

                        else s = 2;

                        if ( s>=0 ) return s;
                        break;
                    case 23 : 
                        int LA2_17 = input.LA(1);

                        s = -1;
                        if ( (LA2_17=='\'') ) {s = 21;}

                        else if ( (LA2_17=='\"') ) {s = 22;}

                        else if ( (LA2_17=='\r') ) {s = 23;}

                        else if ( (LA2_17=='\n') ) {s = 24;}

                        else if ( ((LA2_17>='\u0000' && LA2_17<='\t')||(LA2_17>='\u000B' && LA2_17<='\f')||(LA2_17>='\u000E' && LA2_17<='!')||(LA2_17>='#' && LA2_17<='&')||(LA2_17>='(' && LA2_17<='\uFFFF')) ) {s = 25;}

                        if ( s>=0 ) return s;
                        break;
                    case 24 : 
                        int LA2_9 = input.LA(1);

                        s = -1;
                        if ( ((LA2_9>='\u0000' && LA2_9<='r')||(LA2_9>='t' && LA2_9<='\uFFFF')) ) {s = 2;}

                        else if ( (LA2_9=='s') ) {s = 10;}

                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 2, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA9_eotS =
        "\12\uffff\1\13\2\uffff";
    static final String DFA9_eofS =
        "\15\uffff";
    static final String DFA9_minS =
        "\1\0\11\uffff\1\60\2\uffff";
    static final String DFA9_maxS =
        "\1\uffff\11\uffff\1\146\2\uffff";
    static final String DFA9_acceptS =
        "\1\uffff\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\uffff\1\13\1\12";
    static final String DFA9_specialS =
        "\1\0\14\uffff}>";
    static final String[] DFA9_transitionS = {
            "\42\13\1\6\4\13\1\7\26\13\1\11\35\13\1\10\5\13\1\4\3\13\1\5"+
            "\7\13\1\1\3\13\1\2\1\13\1\3\1\12\uff8a\13",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\12\14\7\uffff\6\14\32\uffff\6\14",
            "",
            ""
    };

    static final short[] DFA9_eot = DFA.unpackEncodedString(DFA9_eotS);
    static final short[] DFA9_eof = DFA.unpackEncodedString(DFA9_eofS);
    static final char[] DFA9_min = DFA.unpackEncodedStringToUnsignedChars(DFA9_minS);
    static final char[] DFA9_max = DFA.unpackEncodedStringToUnsignedChars(DFA9_maxS);
    static final short[] DFA9_accept = DFA.unpackEncodedString(DFA9_acceptS);
    static final short[] DFA9_special = DFA.unpackEncodedString(DFA9_specialS);
    static final short[][] DFA9_transition;

    static {
        int numStates = DFA9_transitionS.length;
        DFA9_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA9_transition[i] = DFA.unpackEncodedString(DFA9_transitionS[i]);
        }
    }

    class DFA9 extends DFA {

        public DFA9(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 9;
            this.eot = DFA9_eot;
            this.eof = DFA9_eof;
            this.min = DFA9_min;
            this.max = DFA9_max;
            this.accept = DFA9_accept;
            this.special = DFA9_special;
            this.transition = DFA9_transition;
        }
        public String getDescription() {
            return "862:3: ( 'n' | 'r' | 't' | 'b' | 'f' | '\"' | '\\'' | '\\\\' | '>' | 'u' XDIGIT XDIGIT XDIGIT XDIGIT | . )";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            IntStream input = _input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA9_0 = input.LA(1);

                        s = -1;
                        if ( (LA9_0=='n') ) {s = 1;}

                        else if ( (LA9_0=='r') ) {s = 2;}

                        else if ( (LA9_0=='t') ) {s = 3;}

                        else if ( (LA9_0=='b') ) {s = 4;}

                        else if ( (LA9_0=='f') ) {s = 5;}

                        else if ( (LA9_0=='\"') ) {s = 6;}

                        else if ( (LA9_0=='\'') ) {s = 7;}

                        else if ( (LA9_0=='\\') ) {s = 8;}

                        else if ( (LA9_0=='>') ) {s = 9;}

                        else if ( (LA9_0=='u') ) {s = 10;}

                        else if ( ((LA9_0>='\u0000' && LA9_0<='!')||(LA9_0>='#' && LA9_0<='&')||(LA9_0>='(' && LA9_0<='=')||(LA9_0>='?' && LA9_0<='[')||(LA9_0>=']' && LA9_0<='a')||(LA9_0>='c' && LA9_0<='e')||(LA9_0>='g' && LA9_0<='m')||(LA9_0>='o' && LA9_0<='q')||LA9_0=='s'||(LA9_0>='v' && LA9_0<='\uFFFF')) ) {s = 11;}

                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 9, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA13_eotS =
        "\34\uffff";
    static final String DFA13_eofS =
        "\34\uffff";
    static final String DFA13_minS =
        "\1\0\2\uffff\3\0\26\uffff";
    static final String DFA13_maxS =
        "\1\uffff\2\uffff\3\uffff\26\uffff";
    static final String DFA13_acceptS =
        "\1\uffff\1\7\1\1\3\uffff\1\6\1\2\1\3\5\uffff\7\4\6\5\1\uffff";
    static final String DFA13_specialS =
        "\1\0\2\uffff\1\1\1\2\1\3\26\uffff}>";
    static final String[] DFA13_transitionS = {
            "\42\6\1\4\4\6\1\5\7\6\1\3\113\6\1\2\1\6\1\1\uff82\6",
            "",
            "",
            "\52\6\1\10\4\6\1\7\uffd0\6",
            "\42\24\1\21\4\24\1\22\7\24\1\20\54\24\1\23\36\24\1\17\1\24"+
            "\1\16\uff82\24",
            "\42\32\1\31\4\32\1\6\7\32\1\30\54\32\1\25\36\32\1\27\1\32\1"+
            "\26\uff82\32",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA13_eot = DFA.unpackEncodedString(DFA13_eotS);
    static final short[] DFA13_eof = DFA.unpackEncodedString(DFA13_eofS);
    static final char[] DFA13_min = DFA.unpackEncodedStringToUnsignedChars(DFA13_minS);
    static final char[] DFA13_max = DFA.unpackEncodedStringToUnsignedChars(DFA13_maxS);
    static final short[] DFA13_accept = DFA.unpackEncodedString(DFA13_acceptS);
    static final short[] DFA13_special = DFA.unpackEncodedString(DFA13_specialS);
    static final short[][] DFA13_transition;

    static {
        int numStates = DFA13_transitionS.length;
        DFA13_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA13_transition[i] = DFA.unpackEncodedString(DFA13_transitionS[i]);
        }
    }

    class DFA13 extends DFA {

        public DFA13(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 13;
            this.eot = DFA13_eot;
            this.eof = DFA13_eof;
            this.min = DFA13_min;
            this.max = DFA13_max;
            this.accept = DFA13_accept;
            this.special = DFA13_special;
            this.transition = DFA13_transition;
        }
        public String getDescription() {
            return "()* loopback of 917:3: ( options {greedy=false; k=2; } : NESTED_ACTION | SL_COMMENT | ML_COMMENT | ACTION_STRING_LITERAL | ACTION_CHAR_LITERAL | . )*";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            IntStream input = _input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA13_0 = input.LA(1);

                        s = -1;
                        if ( (LA13_0=='}') ) {s = 1;}

                        else if ( (LA13_0=='{') ) {s = 2;}

                        else if ( (LA13_0=='/') ) {s = 3;}

                        else if ( (LA13_0=='\"') ) {s = 4;}

                        else if ( (LA13_0=='\'') ) {s = 5;}

                        else if ( ((LA13_0>='\u0000' && LA13_0<='!')||(LA13_0>='#' && LA13_0<='&')||(LA13_0>='(' && LA13_0<='.')||(LA13_0>='0' && LA13_0<='z')||LA13_0=='|'||(LA13_0>='~' && LA13_0<='\uFFFF')) ) {s = 6;}

                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA13_3 = input.LA(1);

                        s = -1;
                        if ( (LA13_3=='/') ) {s = 7;}

                        else if ( (LA13_3=='*') ) {s = 8;}

                        else if ( ((LA13_3>='\u0000' && LA13_3<=')')||(LA13_3>='+' && LA13_3<='.')||(LA13_3>='0' && LA13_3<='\uFFFF')) ) {s = 6;}

                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA13_4 = input.LA(1);

                        s = -1;
                        if ( (LA13_4=='}') ) {s = 14;}

                        else if ( (LA13_4=='{') ) {s = 15;}

                        else if ( (LA13_4=='/') ) {s = 16;}

                        else if ( (LA13_4=='\"') ) {s = 17;}

                        else if ( (LA13_4=='\'') ) {s = 18;}

                        else if ( (LA13_4=='\\') ) {s = 19;}

                        else if ( ((LA13_4>='\u0000' && LA13_4<='!')||(LA13_4>='#' && LA13_4<='&')||(LA13_4>='(' && LA13_4<='.')||(LA13_4>='0' && LA13_4<='[')||(LA13_4>=']' && LA13_4<='z')||LA13_4=='|'||(LA13_4>='~' && LA13_4<='\uFFFF')) ) {s = 20;}

                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA13_5 = input.LA(1);

                        s = -1;
                        if ( (LA13_5=='\\') ) {s = 21;}

                        else if ( (LA13_5=='}') ) {s = 22;}

                        else if ( (LA13_5=='{') ) {s = 23;}

                        else if ( (LA13_5=='/') ) {s = 24;}

                        else if ( (LA13_5=='\"') ) {s = 25;}

                        else if ( ((LA13_5>='\u0000' && LA13_5<='!')||(LA13_5>='#' && LA13_5<='&')||(LA13_5>='(' && LA13_5<='.')||(LA13_5>='0' && LA13_5<='[')||(LA13_5>=']' && LA13_5<='z')||LA13_5=='|'||(LA13_5>='~' && LA13_5<='\uFFFF')) ) {s = 26;}

                        else if ( (LA13_5=='\'') ) {s = 6;}

                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 13, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA22_eotS =
        "\1\uffff\2\46\1\54\1\uffff\1\56\2\uffff\1\60\1\62\5\46\4\uffff\1"+
        "\46\3\uffff\1\46\1\77\2\uffff\1\101\11\uffff\1\46\2\uffff\3\46\10"+
        "\uffff\13\46\6\uffff\31\46\1\155\7\46\1\165\2\46\1\170\4\46\1\uffff"+
        "\5\46\1\u0082\1\46\1\uffff\2\46\1\uffff\1\u0086\2\46\1\u0089\1\u008a"+
        "\2\46\1\u008d\1\46\1\uffff\2\46\1\u0091\1\uffff\1\46\1\u0093\3\uffff"+
        "\1\u0094\1\uffff\1\u0095\1\46\1\u0097\1\uffff\1\46\5\uffff\1\u0099"+
        "\1\uffff";
    static final String DFA22_eofS =
        "\u009a\uffff";
    static final String DFA22_minS =
        "\1\11\1\143\1\151\1\50\1\uffff\1\56\2\uffff\1\72\1\76\1\145\1\141"+
        "\1\150\1\162\1\155\4\uffff\1\145\3\uffff\1\141\1\75\2\uffff\1\74"+
        "\2\uffff\1\52\6\uffff\1\160\2\uffff\1\157\1\141\1\156\10\uffff\1"+
        "\170\1\162\1\151\1\142\1\145\1\162\1\153\1\141\1\160\2\164\6\uffff"+
        "\1\164\1\160\1\147\1\141\1\145\1\163\1\164\1\166\1\154\1\145\1\157"+
        "\1\145\1\155\1\157\1\165\1\143\1\151\1\145\1\155\1\154\1\162\2\145"+
        "\1\141\1\151\1\60\1\167\1\156\1\155\2\162\1\150\1\157\1\60\1\145"+
        "\1\154\1\60\1\162\1\143\1\164\1\143\1\uffff\2\163\1\141\1\164\1"+
        "\156\1\60\1\156\1\uffff\1\156\1\171\1\uffff\1\60\1\164\1\145\2\60"+
        "\1\11\1\162\1\60\1\163\1\uffff\1\163\1\164\1\60\1\uffff\1\145\1"+
        "\60\3\uffff\1\60\1\uffff\1\60\1\11\1\60\1\uffff\1\144\5\uffff\1"+
        "\60\1\uffff";
    static final String DFA22_maxS =
        "\1\176\1\143\1\162\1\50\1\uffff\1\56\2\uffff\1\72\1\76\1\145\1\165"+
        "\2\162\1\155\4\uffff\1\145\3\uffff\1\141\1\75\2\uffff\1\74\2\uffff"+
        "\1\57\6\uffff\1\160\2\uffff\1\157\1\141\1\156\10\uffff\1\170\1\162"+
        "\1\157\1\142\1\145\1\162\1\153\1\141\1\160\2\164\6\uffff\1\164\1"+
        "\160\1\147\1\141\1\145\1\163\1\164\1\166\1\154\1\145\1\157\1\145"+
        "\1\155\1\157\1\165\1\143\1\151\1\145\1\155\1\154\1\162\2\145\1\141"+
        "\1\151\1\172\1\167\1\156\1\155\2\162\1\150\1\157\1\172\1\145\1\154"+
        "\1\172\1\162\1\143\1\164\1\143\1\uffff\2\163\1\141\1\164\1\156\1"+
        "\172\1\156\1\uffff\1\156\1\171\1\uffff\1\172\1\164\1\145\2\172\1"+
        "\173\1\162\1\172\1\163\1\uffff\1\163\1\164\1\172\1\uffff\1\145\1"+
        "\172\3\uffff\1\172\1\uffff\1\172\1\173\1\172\1\uffff\1\144\5\uffff"+
        "\1\172\1\uffff";
    static final String DFA22_acceptS =
        "\4\uffff\1\5\1\uffff\1\7\1\10\7\uffff\1\21\1\22\1\23\1\25\1\uffff"+
        "\1\33\1\34\1\35\2\uffff\1\42\1\43\1\uffff\1\46\1\47\1\uffff\1\52"+
        "\1\53\1\55\1\56\1\57\1\60\1\uffff\1\61\1\64\3\uffff\1\3\1\4\1\6"+
        "\1\12\1\24\1\11\1\41\1\13\13\uffff\1\40\1\44\1\54\1\45\1\50\1\51"+
        "\51\uffff\1\16\7\uffff\1\1\2\uffff\1\14\11\uffff\1\36\3\uffff\1"+
        "\15\2\uffff\1\30\1\32\1\63\1\uffff\1\20\3\uffff\1\37\1\uffff\1\31"+
        "\1\17\1\26\1\62\1\2\1\uffff\1\27";
    static final String DFA22_specialS =
        "\u009a\uffff}>";
    static final String[] DFA22_transitionS = {
            "\2\47\2\uffff\1\47\22\uffff\1\47\1\4\1\40\1\uffff\1\35\2\uffff"+
            "\1\37\1\24\1\26\1\22\1\30\1\17\1\6\1\5\1\36\12\41\1\10\1\7\1"+
            "\33\1\11\1\34\1\32\1\21\32\44\1\42\2\uffff\1\3\2\uffff\2\46"+
            "\1\27\2\46\1\2\1\15\1\46\1\16\2\46\1\12\2\46\1\45\1\13\1\46"+
            "\1\23\1\1\1\14\6\46\1\43\1\25\1\20\1\31",
            "\1\50",
            "\1\52\10\uffff\1\51",
            "\1\53",
            "",
            "\1\55",
            "",
            "",
            "\1\57",
            "\1\61",
            "\1\63",
            "\1\64\20\uffff\1\65\2\uffff\1\66",
            "\1\70\6\uffff\1\71\2\uffff\1\67",
            "\1\72",
            "\1\73",
            "",
            "",
            "",
            "",
            "\1\74",
            "",
            "",
            "",
            "\1\75",
            "\1\76",
            "",
            "",
            "\1\100",
            "",
            "",
            "\1\103\4\uffff\1\102",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\104",
            "",
            "",
            "\1\105",
            "\1\106",
            "\1\107",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\110",
            "\1\111",
            "\1\113\5\uffff\1\112",
            "\1\114",
            "\1\115",
            "\1\116",
            "\1\117",
            "\1\120",
            "\1\121",
            "\1\122",
            "\1\123",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\124",
            "\1\125",
            "\1\126",
            "\1\127",
            "\1\130",
            "\1\131",
            "\1\132",
            "\1\133",
            "\1\134",
            "\1\135",
            "\1\136",
            "\1\137",
            "\1\140",
            "\1\141",
            "\1\142",
            "\1\143",
            "\1\144",
            "\1\145",
            "\1\146",
            "\1\147",
            "\1\150",
            "\1\151",
            "\1\152",
            "\1\153",
            "\1\154",
            "\12\46\7\uffff\32\46\4\uffff\1\46\1\uffff\32\46",
            "\1\156",
            "\1\157",
            "\1\160",
            "\1\161",
            "\1\162",
            "\1\163",
            "\1\164",
            "\12\46\7\uffff\32\46\4\uffff\1\46\1\uffff\32\46",
            "\1\166",
            "\1\167",
            "\12\46\7\uffff\32\46\4\uffff\1\46\1\uffff\32\46",
            "\1\171",
            "\1\172",
            "\1\173",
            "\1\174",
            "",
            "\1\175",
            "\1\176",
            "\1\177",
            "\1\u0080",
            "\1\u0081",
            "\12\46\7\uffff\32\46\4\uffff\1\46\1\uffff\32\46",
            "\1\u0083",
            "",
            "\1\u0084",
            "\1\u0085",
            "",
            "\12\46\7\uffff\32\46\4\uffff\1\46\1\uffff\32\46",
            "\1\u0087",
            "\1\u0088",
            "\12\46\7\uffff\32\46\4\uffff\1\46\1\uffff\32\46",
            "\12\46\7\uffff\32\46\4\uffff\1\46\1\uffff\32\46",
            "\2\u008b\2\uffff\1\u008b\22\uffff\1\u008b\16\uffff\1\u008b"+
            "\113\uffff\1\u008b",
            "\1\u008c",
            "\12\46\7\uffff\32\46\4\uffff\1\46\1\uffff\32\46",
            "\1\u008e",
            "",
            "\1\u008f",
            "\1\u0090",
            "\12\46\7\uffff\32\46\4\uffff\1\46\1\uffff\32\46",
            "",
            "\1\u0092",
            "\12\46\7\uffff\32\46\4\uffff\1\46\1\uffff\32\46",
            "",
            "",
            "",
            "\12\46\7\uffff\32\46\4\uffff\1\46\1\uffff\32\46",
            "",
            "\12\46\7\uffff\32\46\4\uffff\1\46\1\uffff\32\46",
            "\2\u0096\2\uffff\1\u0096\22\uffff\1\u0096\16\uffff\1\u0096"+
            "\113\uffff\1\u0096",
            "\12\46\7\uffff\32\46\4\uffff\1\46\1\uffff\32\46",
            "",
            "\1\u0098",
            "",
            "",
            "",
            "",
            "",
            "\12\46\7\uffff\32\46\4\uffff\1\46\1\uffff\32\46",
            ""
    };

    static final short[] DFA22_eot = DFA.unpackEncodedString(DFA22_eotS);
    static final short[] DFA22_eof = DFA.unpackEncodedString(DFA22_eofS);
    static final char[] DFA22_min = DFA.unpackEncodedStringToUnsignedChars(DFA22_minS);
    static final char[] DFA22_max = DFA.unpackEncodedStringToUnsignedChars(DFA22_maxS);
    static final short[] DFA22_accept = DFA.unpackEncodedString(DFA22_acceptS);
    static final short[] DFA22_special = DFA.unpackEncodedString(DFA22_specialS);
    static final short[][] DFA22_transition;

    static {
        int numStates = DFA22_transitionS.length;
        DFA22_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA22_transition[i] = DFA.unpackEncodedString(DFA22_transitionS[i]);
        }
    }

    class DFA22 extends DFA {

        public DFA22(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 22;
            this.eot = DFA22_eot;
            this.eof = DFA22_eof;
            this.min = DFA22_min;
            this.max = DFA22_max;
            this.accept = DFA22_accept;
            this.special = DFA22_special;
            this.transition = DFA22_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( SCOPE | FRAGMENT | TREE_BEGIN | ROOT | BANG | RANGE | REWRITE | SEMI | COLON | DOT | ASSIGN | T__69 | T__70 | T__71 | T__72 | T__73 | T__74 | T__75 | T__76 | T__77 | T__78 | T__79 | T__80 | T__81 | T__82 | T__83 | T__84 | T__85 | T__86 | T__87 | T__88 | T__89 | T__90 | T__91 | T__92 | T__93 | T__94 | T__95 | T__96 | SL_COMMENT | ML_COMMENT | STRING_LITERAL | DOUBLE_QUOTE_STRING_LITERAL | DOUBLE_ANGLE_STRING_LITERAL | INT | ARG_ACTION | ACTION | TOKEN_REF | RULE_REF | OPTIONS | TOKENS | WS );";
        }
    }
 

}