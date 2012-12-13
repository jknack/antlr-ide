// $ANTLR 3.2 Sep 23, 2009 14:05:07 /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/grammar's/try-catch/TryCatch.g 2009-10-12 10:57:12

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class TryCatchParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "ID", "INT", "WS", "'method'", "'('", "')'", "'{'", "'}'", "'='", "';'", "'+'", "'*'"
    };
    public static final int WS=6;
    public static final int T__15=15;
    public static final int T__12=12;
    public static final int T__11=11;
    public static final int T__14=14;
    public static final int T__13=13;
    public static final int T__10=10;
    public static final int INT=5;
    public static final int ID=4;
    public static final int EOF=-1;
    public static final int T__9=9;
    public static final int T__8=8;
    public static final int T__7=7;

    // delegates
    // delegators


        public TryCatchParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public TryCatchParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return TryCatchParser.tokenNames; }
    public String getGrammarFileName() { return "/media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/grammar's/try-catch/TryCatch.g"; }



    // $ANTLR start "program"
    // /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/grammar's/try-catch/TryCatch.g:11:1: program : method ;
    public final void program() throws RecognitionException {
        try {
            // /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/grammar's/try-catch/TryCatch.g:12:2: ( method )
            // /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/grammar's/try-catch/TryCatch.g:12:4: method
            {
            pushFollow(FOLLOW_method_in_program23);
            method();

            state._fsp--;


            }

        }

        	catch (RecognitionException e) {
        	    throw e;
        	}
        finally {
        }
        return ;
    }
    // $ANTLR end "program"

    protected static class method_scope {
        /** name is visible to any rule called by method directly or indirectly.
           *  There is also a stack of these names, one slot for each nested
           *  invocation of method.  If you have a method nested within another
           *  method then you have name strings on the stack.  Referencing
           *  $method.name access the topmost always.  I have no way at the moment
           *  to access earlier elements on the stack.
           */
          String name;
    }
    protected Stack method_stack = new Stack();


    // $ANTLR start "method"
    // /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/grammar's/try-catch/TryCatch.g:14:1: method : 'method' ID '(' ')' body ;
    public final void method() throws RecognitionException {
        method_stack.push(new method_scope());
        Token ID1=null;

        try {
            // /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/grammar's/try-catch/TryCatch.g:25:5: ( 'method' ID '(' ')' body )
            // /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/grammar's/try-catch/TryCatch.g:25:9: 'method' ID '(' ')' body
            {
            match(input,7,FOLLOW_7_in_method42); 
            ID1=(Token)match(input,ID,FOLLOW_ID_in_method44); 
            match(input,8,FOLLOW_8_in_method46); 
            match(input,9,FOLLOW_9_in_method48); 
            ((method_scope)method_stack.peek()).name =(ID1!=null?ID1.getText():null);
            pushFollow(FOLLOW_body_in_method52);
            body();

            state._fsp--;


            }

        }

        	catch (RecognitionException e) {
        	    throw e;
        	}
        finally {
            method_stack.pop();
        }
        return ;
    }
    // $ANTLR end "method"


    // $ANTLR start "body"
    // /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/grammar's/try-catch/TryCatch.g:28:1: body : '{' ( stat )* '}' ;
    public final void body() throws RecognitionException {
        try {
            // /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/grammar's/try-catch/TryCatch.g:28:5: ( '{' ( stat )* '}' )
            // /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/grammar's/try-catch/TryCatch.g:28:9: '{' ( stat )* '}'
            {
            match(input,10,FOLLOW_10_in_body67); 
            // /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/grammar's/try-catch/TryCatch.g:28:13: ( stat )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==ID||LA1_0==7) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/grammar's/try-catch/TryCatch.g:28:13: stat
            	    {
            	    pushFollow(FOLLOW_stat_in_body69);
            	    stat();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);

            match(input,11,FOLLOW_11_in_body72); 

            }

        }
        catch (RecognitionException re) {

                reportError(re);
                consumeUntil(input, SEMI); // throw away all until ';'
                input.consume(); // eat the ';'

        }
        finally {
            System.out.println("finallyClause");
        }
        return ;
    }
    // $ANTLR end "body"


    // $ANTLR start "stat"
    // /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/grammar's/try-catch/TryCatch.g:37:1: stat : ( ID '=' expr ';' | method );
    public final void stat() throws RecognitionException {
        try {
            // /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/grammar's/try-catch/TryCatch.g:37:5: ( ID '=' expr ';' | method )
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==ID) ) {
                alt2=1;
            }
            else if ( (LA2_0==7) ) {
                alt2=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }
            switch (alt2) {
                case 1 :
                    // /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/grammar's/try-catch/TryCatch.g:37:9: ID '=' expr ';'
                    {
                    match(input,ID,FOLLOW_ID_in_stat99); 
                    match(input,12,FOLLOW_12_in_stat101); 
                    pushFollow(FOLLOW_expr_in_stat103);
                    expr();

                    state._fsp--;

                    match(input,13,FOLLOW_13_in_stat105); 

                    }
                    break;
                case 2 :
                    // /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/grammar's/try-catch/TryCatch.g:38:9: method
                    {
                    pushFollow(FOLLOW_method_in_stat115);
                    method();

                    state._fsp--;


                    }
                    break;

            }
        }

        	catch (RecognitionException e) {
        	    throw e;
        	}
        finally {
        }
        return ;
    }
    // $ANTLR end "stat"


    // $ANTLR start "expr"
    // /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/grammar's/try-catch/TryCatch.g:41:1: expr : mul ( '+' mul )* ;
    public final void expr() throws RecognitionException {
        try {
            // /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/grammar's/try-catch/TryCatch.g:41:5: ( mul ( '+' mul )* )
            // /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/grammar's/try-catch/TryCatch.g:41:9: mul ( '+' mul )*
            {
            pushFollow(FOLLOW_mul_in_expr130);
            mul();

            state._fsp--;

            // /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/grammar's/try-catch/TryCatch.g:41:13: ( '+' mul )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==14) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/grammar's/try-catch/TryCatch.g:41:14: '+' mul
            	    {
            	    match(input,14,FOLLOW_14_in_expr133); 
            	    pushFollow(FOLLOW_mul_in_expr135);
            	    mul();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);


            }

        }

        	catch (RecognitionException e) {
        	    throw e;
        	}
        finally {
        }
        return ;
    }
    // $ANTLR end "expr"


    // $ANTLR start "mul"
    // /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/grammar's/try-catch/TryCatch.g:44:1: mul : atom ( '*' atom )* ;
    public final void mul() throws RecognitionException {
        try {
            // /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/grammar's/try-catch/TryCatch.g:44:5: ( atom ( '*' atom )* )
            // /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/grammar's/try-catch/TryCatch.g:44:9: atom ( '*' atom )*
            {
            pushFollow(FOLLOW_atom_in_mul153);
            atom();

            state._fsp--;

            // /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/grammar's/try-catch/TryCatch.g:44:14: ( '*' atom )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0==15) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/grammar's/try-catch/TryCatch.g:44:15: '*' atom
            	    {
            	    match(input,15,FOLLOW_15_in_mul156); 
            	    pushFollow(FOLLOW_atom_in_mul158);
            	    atom();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);


            }

        }

        	catch (RecognitionException e) {
        	    throw e;
        	}
        finally {
        }
        return ;
    }
    // $ANTLR end "mul"


    // $ANTLR start "atom"
    // /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/grammar's/try-catch/TryCatch.g:47:1: atom : ( ID | INT );
    public final void atom() throws RecognitionException {
        Token ID2=null;
        Token INT3=null;

        try {
            // /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/grammar's/try-catch/TryCatch.g:52:5: ( ID | INT )
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==ID) ) {
                alt5=1;
            }
            else if ( (LA5_0==INT) ) {
                alt5=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;
            }
            switch (alt5) {
                case 1 :
                    // /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/grammar's/try-catch/TryCatch.g:52:9: ID
                    {
                    ID2=(Token)match(input,ID,FOLLOW_ID_in_atom176); 
                    System.out.println("ref "+(ID2!=null?ID2.getText():null)+" from method "+((method_scope)method_stack.peek()).name);

                    }
                    break;
                case 2 :
                    // /media/dev/desarrollo/@ws-antlrv3ide/org.deved.antlride.core/grammar's/try-catch/TryCatch.g:53:9: INT
                    {
                    INT3=(Token)match(input,INT,FOLLOW_INT_in_atom189); 
                    System.out.println("int "+(INT3!=null?INT3.getText():null)+" in method "+((method_scope)method_stack.peek()).name);

                    }
                    break;

            }
        }

        	catch (RecognitionException e) {
        	    throw e;
        	}
        finally {
        }
        return ;
    }
    // $ANTLR end "atom"

    // Delegated rules


 

    public static final BitSet FOLLOW_method_in_program23 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_7_in_method42 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ID_in_method44 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_8_in_method46 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_method48 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_body_in_method52 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_10_in_body67 = new BitSet(new long[]{0x0000000000000890L});
    public static final BitSet FOLLOW_stat_in_body69 = new BitSet(new long[]{0x0000000000000890L});
    public static final BitSet FOLLOW_11_in_body72 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_stat99 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_stat101 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_expr_in_stat103 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_stat105 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_method_in_stat115 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_mul_in_expr130 = new BitSet(new long[]{0x0000000000004002L});
    public static final BitSet FOLLOW_14_in_expr133 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_mul_in_expr135 = new BitSet(new long[]{0x0000000000004002L});
    public static final BitSet FOLLOW_atom_in_mul153 = new BitSet(new long[]{0x0000000000008002L});
    public static final BitSet FOLLOW_15_in_mul156 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_atom_in_mul158 = new BitSet(new long[]{0x0000000000008002L});
    public static final BitSet FOLLOW_ID_in_atom176 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INT_in_atom189 = new BitSet(new long[]{0x0000000000000002L});

}