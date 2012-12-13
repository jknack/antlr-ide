// $ANTLR 3.1.3 Mar 17, 2009 19:23:44 /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.gunit.core/src/main/java/org/deved/antlride/gunit/internal/core/parser/gUnit.g 2009-07-18 13:51:09

package org.deved.antlride.gunit.internal.core.parser;

import java.util.List;

import org.antlr.runtime.BitSet;
import org.antlr.runtime.CommonToken;
import org.antlr.runtime.EarlyExitException;
import org.antlr.runtime.MismatchedSetException;
import org.antlr.runtime.NoViableAltException;
import org.antlr.runtime.Parser;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.RecognizerSharedState;
import org.antlr.runtime.Token;
import org.antlr.runtime.TokenStream;
import org.deved.antlride.gunit.core.model.dltk.ast.DASTGUnitTest;

public class gUnitParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "OK", "FAIL", "DOC_COMMENT", "ACTION", "RULE_REF", "TOKEN_REF", "STRING", "ML_STRING", "RETVAL", "AST", "EXT", "SL_COMMENT", "ML_COMMENT", "ESC", "NESTED_RETVAL", "NESTED_AST", "NESTED_ACTION", "STRING_LITERAL", "CHAR_LITERAL", "XDIGIT", "WS", "'gunit'", "'walks'", "';'", "'@header'", "':'", "'returns'", "'->'"
    };
    public static final int RETVAL=12;
    public static final int NESTED_ACTION=20;
    public static final int AST=13;
    public static final int T__28=28;
    public static final int EXT=14;
    public static final int FAIL=5;
    public static final int WS=24;
    public static final int STRING=10;
    public static final int OK=4;
    public static final int ACTION=7;
    public static final int TOKEN_REF=9;
    public static final int ESC=17;
    public static final int XDIGIT=23;
    public static final int RULE_REF=8;
    public static final int T__29=29;
    public static final int NESTED_AST=19;
    public static final int T__30=30;
    public static final int CHAR_LITERAL=22;
    public static final int T__31=31;
    public static final int EOF=-1;
    public static final int STRING_LITERAL=21;
    public static final int T__27=27;
    public static final int T__26=26;
    public static final int T__25=25;
    public static final int ML_STRING=11;
    public static final int ML_COMMENT=16;
    public static final int SL_COMMENT=15;
    public static final int DOC_COMMENT=6;
    public static final int NESTED_RETVAL=18;

    // delegates
    // delegators


        public gUnitParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public gUnitParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return gUnitParser.tokenNames; }
    public String getGrammarFileName() { return "/media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.gunit.core/src/main/java/org/deved/antlride/gunit/internal/core/parser/gUnit.g"; }


     List<DASTGUnitTest> statements = null;
     
     protected String text(Token token) {
    	if (token == null)
    		return "";
    	String text = token.getText();
    	return text == null ? "" : text;
     }

     protected int start(Token token) {
     	return start((CommonToken)token);
     }
     
     protected int start(CommonToken token) {
    	return token.getStartIndex();
     }
     
     protected int end(Token token) {
     	return end((CommonToken)token);
     }
     
     protected int end(CommonToken token) {
    	return token.getStopIndex() + 1;
     }
     



    // $ANTLR start "gUnitDef"
    // /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.gunit.core/src/main/java/org/deved/antlride/gunit/internal/core/parser/gUnit.g:75:1: gUnitDef[List<DASTGUnitTest> statements] : 'gunit' id ( 'walks' id )? ';' ( header )? ( testsuite )* ;
    public final void gUnitDef(List<DASTGUnitTest> statements) throws RecognitionException {

        this.statements = statements;

        try {
            // /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.gunit.core/src/main/java/org/deved/antlride/gunit/internal/core/parser/gUnit.g:79:3: ( 'gunit' id ( 'walks' id )? ';' ( header )? ( testsuite )* )
            // /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.gunit.core/src/main/java/org/deved/antlride/gunit/internal/core/parser/gUnit.g:80:3: 'gunit' id ( 'walks' id )? ';' ( header )? ( testsuite )*
            {
            match(input,25,FOLLOW_25_in_gUnitDef77); 
            pushFollow(FOLLOW_id_in_gUnitDef79);
            id();

            state._fsp--;

            // /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.gunit.core/src/main/java/org/deved/antlride/gunit/internal/core/parser/gUnit.g:80:14: ( 'walks' id )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==26) ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.gunit.core/src/main/java/org/deved/antlride/gunit/internal/core/parser/gUnit.g:80:15: 'walks' id
                    {
                    match(input,26,FOLLOW_26_in_gUnitDef82); 
                    pushFollow(FOLLOW_id_in_gUnitDef84);
                    id();

                    state._fsp--;


                    }
                    break;

            }

            match(input,27,FOLLOW_27_in_gUnitDef88); 
            // /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.gunit.core/src/main/java/org/deved/antlride/gunit/internal/core/parser/gUnit.g:81:3: ( header )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==28) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.gunit.core/src/main/java/org/deved/antlride/gunit/internal/core/parser/gUnit.g:81:3: header
                    {
                    pushFollow(FOLLOW_header_in_gUnitDef93);
                    header();

                    state._fsp--;


                    }
                    break;

            }

            // /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.gunit.core/src/main/java/org/deved/antlride/gunit/internal/core/parser/gUnit.g:81:11: ( testsuite )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( ((LA3_0>=RULE_REF && LA3_0<=TOKEN_REF)) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.gunit.core/src/main/java/org/deved/antlride/gunit/internal/core/parser/gUnit.g:81:11: testsuite
            	    {
            	    pushFollow(FOLLOW_testsuite_in_gUnitDef96);
            	    testsuite();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);


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
    // $ANTLR end "gUnitDef"


    // $ANTLR start "header"
    // /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.gunit.core/src/main/java/org/deved/antlride/gunit/internal/core/parser/gUnit.g:84:1: header : '@header' ACTION ;
    public final void header() throws RecognitionException {
        try {
            // /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.gunit.core/src/main/java/org/deved/antlride/gunit/internal/core/parser/gUnit.g:84:8: ( '@header' ACTION )
            // /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.gunit.core/src/main/java/org/deved/antlride/gunit/internal/core/parser/gUnit.g:84:10: '@header' ACTION
            {
            match(input,28,FOLLOW_28_in_header107); 
            match(input,ACTION,FOLLOW_ACTION_in_header109); 

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
    // $ANTLR end "header"


    // $ANTLR start "testsuite"
    // /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.gunit.core/src/main/java/org/deved/antlride/gunit/internal/core/parser/gUnit.g:87:1: testsuite : (r= RULE_REF ( 'walks' RULE_REF )? | r= TOKEN_REF ) ':' ( testcase )+ ;
    public final void testsuite() throws RecognitionException {
        Token r=null;

        try {
            // /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.gunit.core/src/main/java/org/deved/antlride/gunit/internal/core/parser/gUnit.g:88:2: ( (r= RULE_REF ( 'walks' RULE_REF )? | r= TOKEN_REF ) ':' ( testcase )+ )
            // /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.gunit.core/src/main/java/org/deved/antlride/gunit/internal/core/parser/gUnit.g:88:4: (r= RULE_REF ( 'walks' RULE_REF )? | r= TOKEN_REF ) ':' ( testcase )+
            {
            // /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.gunit.core/src/main/java/org/deved/antlride/gunit/internal/core/parser/gUnit.g:88:4: (r= RULE_REF ( 'walks' RULE_REF )? | r= TOKEN_REF )
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==RULE_REF) ) {
                alt5=1;
            }
            else if ( (LA5_0==TOKEN_REF) ) {
                alt5=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;
            }
            switch (alt5) {
                case 1 :
                    // /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.gunit.core/src/main/java/org/deved/antlride/gunit/internal/core/parser/gUnit.g:88:6: r= RULE_REF ( 'walks' RULE_REF )?
                    {
                    r=(Token)match(input,RULE_REF,FOLLOW_RULE_REF_in_testsuite126); 
                    // /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.gunit.core/src/main/java/org/deved/antlride/gunit/internal/core/parser/gUnit.g:88:17: ( 'walks' RULE_REF )?
                    int alt4=2;
                    int LA4_0 = input.LA(1);

                    if ( (LA4_0==26) ) {
                        alt4=1;
                    }
                    switch (alt4) {
                        case 1 :
                            // /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.gunit.core/src/main/java/org/deved/antlride/gunit/internal/core/parser/gUnit.g:88:18: 'walks' RULE_REF
                            {
                            match(input,26,FOLLOW_26_in_testsuite129); 
                            match(input,RULE_REF,FOLLOW_RULE_REF_in_testsuite131); 

                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.gunit.core/src/main/java/org/deved/antlride/gunit/internal/core/parser/gUnit.g:89:5: r= TOKEN_REF
                    {
                    r=(Token)match(input,TOKEN_REF,FOLLOW_TOKEN_REF_in_testsuite142); 

                    }
                    break;

            }

            statements.add(new DASTGUnitTest(text(r), start(r), end(r)));
            match(input,29,FOLLOW_29_in_testsuite154); 
            // /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.gunit.core/src/main/java/org/deved/antlride/gunit/internal/core/parser/gUnit.g:93:3: ( testcase )+
            int cnt6=0;
            loop6:
            do {
                int alt6=2;
                switch ( input.LA(1) ) {
                case RULE_REF:
                    {
                    int LA6_2 = input.LA(2);

                    if ( ((LA6_2>=OK && LA6_2<=FAIL)||LA6_2==EXT||(LA6_2>=30 && LA6_2<=31)) ) {
                        alt6=1;
                    }


                    }
                    break;
                case TOKEN_REF:
                    {
                    int LA6_3 = input.LA(2);

                    if ( ((LA6_3>=OK && LA6_3<=FAIL)||LA6_3==EXT||(LA6_3>=30 && LA6_3<=31)) ) {
                        alt6=1;
                    }


                    }
                    break;
                case STRING:
                case ML_STRING:
                    {
                    alt6=1;
                    }
                    break;

                }

                switch (alt6) {
            	case 1 :
            	    // /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.gunit.core/src/main/java/org/deved/antlride/gunit/internal/core/parser/gUnit.g:93:3: testcase
            	    {
            	    pushFollow(FOLLOW_testcase_in_testsuite159);
            	    testcase();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    if ( cnt6 >= 1 ) break loop6;
                        EarlyExitException eee =
                            new EarlyExitException(6, input);
                        throw eee;
                }
                cnt6++;
            } while (true);


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
    // $ANTLR end "testsuite"


    // $ANTLR start "testcase"
    // /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.gunit.core/src/main/java/org/deved/antlride/gunit/internal/core/parser/gUnit.g:96:1: testcase : input expect ;
    public final void testcase() throws RecognitionException {
        try {
            // /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.gunit.core/src/main/java/org/deved/antlride/gunit/internal/core/parser/gUnit.g:97:2: ( input expect )
            // /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.gunit.core/src/main/java/org/deved/antlride/gunit/internal/core/parser/gUnit.g:97:4: input expect
            {
            pushFollow(FOLLOW_input_in_testcase171);
            input();

            state._fsp--;

            pushFollow(FOLLOW_expect_in_testcase173);
            expect();

            state._fsp--;


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
    // $ANTLR end "testcase"


    // $ANTLR start "input"
    // /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.gunit.core/src/main/java/org/deved/antlride/gunit/internal/core/parser/gUnit.g:100:1: input : ( STRING | ML_STRING | file );
    public final void input() throws RecognitionException {
        try {
            // /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.gunit.core/src/main/java/org/deved/antlride/gunit/internal/core/parser/gUnit.g:101:2: ( STRING | ML_STRING | file )
            int alt7=3;
            switch ( input.LA(1) ) {
            case STRING:
                {
                alt7=1;
                }
                break;
            case ML_STRING:
                {
                alt7=2;
                }
                break;
            case RULE_REF:
            case TOKEN_REF:
                {
                alt7=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;
            }

            switch (alt7) {
                case 1 :
                    // /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.gunit.core/src/main/java/org/deved/antlride/gunit/internal/core/parser/gUnit.g:101:4: STRING
                    {
                    match(input,STRING,FOLLOW_STRING_in_input184); 

                    }
                    break;
                case 2 :
                    // /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.gunit.core/src/main/java/org/deved/antlride/gunit/internal/core/parser/gUnit.g:102:4: ML_STRING
                    {
                    match(input,ML_STRING,FOLLOW_ML_STRING_in_input190); 

                    }
                    break;
                case 3 :
                    // /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.gunit.core/src/main/java/org/deved/antlride/gunit/internal/core/parser/gUnit.g:103:4: file
                    {
                    pushFollow(FOLLOW_file_in_input195);
                    file();

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
    // $ANTLR end "input"


    // $ANTLR start "expect"
    // /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.gunit.core/src/main/java/org/deved/antlride/gunit/internal/core/parser/gUnit.g:106:1: expect : ( OK | FAIL | 'returns' RETVAL | '->' output );
    public final void expect() throws RecognitionException {
        try {
            // /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.gunit.core/src/main/java/org/deved/antlride/gunit/internal/core/parser/gUnit.g:107:2: ( OK | FAIL | 'returns' RETVAL | '->' output )
            int alt8=4;
            switch ( input.LA(1) ) {
            case OK:
                {
                alt8=1;
                }
                break;
            case FAIL:
                {
                alt8=2;
                }
                break;
            case 30:
                {
                alt8=3;
                }
                break;
            case 31:
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
                    // /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.gunit.core/src/main/java/org/deved/antlride/gunit/internal/core/parser/gUnit.g:107:4: OK
                    {
                    match(input,OK,FOLLOW_OK_in_expect207); 

                    }
                    break;
                case 2 :
                    // /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.gunit.core/src/main/java/org/deved/antlride/gunit/internal/core/parser/gUnit.g:108:4: FAIL
                    {
                    match(input,FAIL,FOLLOW_FAIL_in_expect212); 

                    }
                    break;
                case 3 :
                    // /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.gunit.core/src/main/java/org/deved/antlride/gunit/internal/core/parser/gUnit.g:109:4: 'returns' RETVAL
                    {
                    match(input,30,FOLLOW_30_in_expect217); 
                    match(input,RETVAL,FOLLOW_RETVAL_in_expect219); 

                    }
                    break;
                case 4 :
                    // /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.gunit.core/src/main/java/org/deved/antlride/gunit/internal/core/parser/gUnit.g:110:4: '->' output
                    {
                    match(input,31,FOLLOW_31_in_expect224); 
                    pushFollow(FOLLOW_output_in_expect226);
                    output();

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
    // $ANTLR end "expect"


    // $ANTLR start "output"
    // /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.gunit.core/src/main/java/org/deved/antlride/gunit/internal/core/parser/gUnit.g:113:1: output : ( STRING | ML_STRING | AST | ACTION );
    public final void output() throws RecognitionException {
        try {
            // /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.gunit.core/src/main/java/org/deved/antlride/gunit/internal/core/parser/gUnit.g:114:2: ( STRING | ML_STRING | AST | ACTION )
            // /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.gunit.core/src/main/java/org/deved/antlride/gunit/internal/core/parser/gUnit.g:
            {
            if ( input.LA(1)==ACTION||(input.LA(1)>=STRING && input.LA(1)<=ML_STRING)||input.LA(1)==AST ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


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
    // $ANTLR end "output"


    // $ANTLR start "file"
    // /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.gunit.core/src/main/java/org/deved/antlride/gunit/internal/core/parser/gUnit.g:120:1: file : id ( EXT )? ;
    public final void file() throws RecognitionException {
        try {
            // /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.gunit.core/src/main/java/org/deved/antlride/gunit/internal/core/parser/gUnit.g:121:2: ( id ( EXT )? )
            // /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.gunit.core/src/main/java/org/deved/antlride/gunit/internal/core/parser/gUnit.g:121:4: id ( EXT )?
            {
            pushFollow(FOLLOW_id_in_file265);
            id();

            state._fsp--;

            // /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.gunit.core/src/main/java/org/deved/antlride/gunit/internal/core/parser/gUnit.g:121:7: ( EXT )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==EXT) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.gunit.core/src/main/java/org/deved/antlride/gunit/internal/core/parser/gUnit.g:121:7: EXT
                    {
                    match(input,EXT,FOLLOW_EXT_in_file267); 

                    }
                    break;

            }


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
    // $ANTLR end "file"


    // $ANTLR start "id"
    // /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.gunit.core/src/main/java/org/deved/antlride/gunit/internal/core/parser/gUnit.g:124:1: id : ( TOKEN_REF | RULE_REF );
    public final void id() throws RecognitionException {
        try {
            // /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.gunit.core/src/main/java/org/deved/antlride/gunit/internal/core/parser/gUnit.g:125:2: ( TOKEN_REF | RULE_REF )
            // /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.gunit.core/src/main/java/org/deved/antlride/gunit/internal/core/parser/gUnit.g:
            {
            if ( (input.LA(1)>=RULE_REF && input.LA(1)<=TOKEN_REF) ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


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
    // $ANTLR end "id"

    // Delegated rules


 

    public static final BitSet FOLLOW_25_in_gUnitDef77 = new BitSet(new long[]{0x0000000000000300L});
    public static final BitSet FOLLOW_id_in_gUnitDef79 = new BitSet(new long[]{0x000000000C000000L});
    public static final BitSet FOLLOW_26_in_gUnitDef82 = new BitSet(new long[]{0x0000000000000300L});
    public static final BitSet FOLLOW_id_in_gUnitDef84 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_27_in_gUnitDef88 = new BitSet(new long[]{0x0000000010000302L});
    public static final BitSet FOLLOW_header_in_gUnitDef93 = new BitSet(new long[]{0x0000000000000302L});
    public static final BitSet FOLLOW_testsuite_in_gUnitDef96 = new BitSet(new long[]{0x0000000000000302L});
    public static final BitSet FOLLOW_28_in_header107 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_ACTION_in_header109 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_REF_in_testsuite126 = new BitSet(new long[]{0x0000000024000000L});
    public static final BitSet FOLLOW_26_in_testsuite129 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_RULE_REF_in_testsuite131 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_TOKEN_REF_in_testsuite142 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_29_in_testsuite154 = new BitSet(new long[]{0x0000000000000F00L});
    public static final BitSet FOLLOW_testcase_in_testsuite159 = new BitSet(new long[]{0x0000000000000F02L});
    public static final BitSet FOLLOW_input_in_testcase171 = new BitSet(new long[]{0x00000000C0000030L});
    public static final BitSet FOLLOW_expect_in_testcase173 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_in_input184 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ML_STRING_in_input190 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_file_in_input195 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_OK_in_expect207 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FAIL_in_expect212 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_30_in_expect217 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_RETVAL_in_expect219 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_31_in_expect224 = new BitSet(new long[]{0x0000000000002C80L});
    public static final BitSet FOLLOW_output_in_expect226 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_output0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_id_in_file265 = new BitSet(new long[]{0x0000000000004002L});
    public static final BitSet FOLLOW_EXT_in_file267 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_id0 = new BitSet(new long[]{0x0000000000000002L});

}