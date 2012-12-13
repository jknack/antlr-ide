// $ANTLR 3.2 Sep 23, 2009 12:02:23 Ebnf.g 2010-03-06 12:36:09

/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *******************************************************************************/
package net.hydromatic.clapham.parser.ebnf;

import net.hydromatic.clapham.parser.*;
import java.util.Arrays;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *******************************************************************************/
public class EbnfParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "ID", "STRING_LITERAL", "LITERAL_CHAR", "ESC", "XDIGIT", "WS", "'::='", "'|'", "'+'", "'?'", "'*'", "'.'", "'('", "')'", "'~'", "'??'"
    };
    public static final int ESC=7;
    public static final int LITERAL_CHAR=6;
    public static final int ID=4;
    public static final int EOF=-1;
    public static final int T__19=19;
    public static final int T__16=16;
    public static final int STRING_LITERAL=5;
    public static final int WS=9;
    public static final int T__15=15;
    public static final int T__18=18;
    public static final int T__17=17;
    public static final int T__12=12;
    public static final int T__11=11;
    public static final int T__14=14;
    public static final int T__13=13;
    public static final int XDIGIT=8;
    public static final int T__10=10;

    // delegates
    // delegators


        public EbnfParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public EbnfParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return EbnfParser.tokenNames; }
    public String getGrammarFileName() { return "Ebnf.g"; }


    protected void mismatch(IntStream input, int ttype, BitSet follow)
        throws RecognitionException
    {
        throw new MismatchedTokenException(ttype, input);
    }
    public Object recoverFromMismatchedSet(IntStream input,
                                         RecognitionException e,
                                         BitSet follow)
        throws RecognitionException
    {
        throw e;
    }

    public void emitErrorMessage(String msg) {
     //do not print anything, handle by the exception mechanism
    }

    private String removeQuotes(String text) {
      return text.substring(1, text.length()-1).replace("\\'", "'");
    }




    // $ANTLR start "rule"
    // Ebnf.g:74:1: rule returns [List<ProductionNode> productionNodes = new ArrayList<ProductionNode>()] : (node= production )* EOF ;
    public final List<ProductionNode> rule() throws RecognitionException {
        List<ProductionNode> productionNodes =  new ArrayList<ProductionNode>();

        ProductionNode node = null;


        try {
            // Ebnf.g:75:3: ( (node= production )* EOF )
            // Ebnf.g:76:3: (node= production )* EOF
            {
            // Ebnf.g:76:3: (node= production )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==ID) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // Ebnf.g:76:4: node= production
            	    {
            	    pushFollow(FOLLOW_production_in_rule66);
            	    node=production();

            	    state._fsp--;

            	    productionNodes.add(node);

            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);

            match(input,EOF,FOLLOW_EOF_in_rule72); 

            }

        }

        catch (RecognitionException rexc) {
            throw rexc;
        }
        finally {
        }
        return productionNodes;
    }
    // $ANTLR end "rule"


    // $ANTLR start "production"
    // Ebnf.g:79:1: production returns [ProductionNode productionNode] : ID '::=' expr= expression ;
    public final ProductionNode production() throws RecognitionException {
        ProductionNode productionNode = null;

        Token ID1=null;
        EbnfNode expr = null;


        try {
            // Ebnf.g:80:3: ( ID '::=' expr= expression )
            // Ebnf.g:81:3: ID '::=' expr= expression
            {
            ID1=(Token)match(input,ID,FOLLOW_ID_in_production91); 
            match(input,10,FOLLOW_10_in_production93); 
            pushFollow(FOLLOW_expression_in_production97);
            expr=expression();

            state._fsp--;


                productionNode = new ProductionNode(new IdentifierNode((ID1!=null?ID1.getText():null)), expr);
              

            }

        }

        catch (RecognitionException rexc) {
            throw rexc;
        }
        finally {
        }
        return productionNode;
    }
    // $ANTLR end "production"


    // $ANTLR start "expression"
    // Ebnf.g:86:1: expression returns [EbnfNode node] : t= term ( '|' t= term )* ;
    public final EbnfNode expression() throws RecognitionException {
        EbnfNode node = null;

        EbnfNode t = null;



          List<EbnfNode> list = new ArrayList<EbnfNode>();

        try {
            // Ebnf.g:90:3: (t= term ( '|' t= term )* )
            // Ebnf.g:91:3: t= term ( '|' t= term )*
            {
            pushFollow(FOLLOW_term_in_expression125);
            t=term();

            state._fsp--;

             list.add(t); 
            // Ebnf.g:91:27: ( '|' t= term )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==11) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // Ebnf.g:91:28: '|' t= term
            	    {
            	    match(input,11,FOLLOW_11_in_expression130); 
            	    pushFollow(FOLLOW_term_in_expression134);
            	    t=term();

            	    state._fsp--;

            	     list.add(t);

            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);


                if (list.size() == 1) {
                 node = list.get(0);
                } else {
                 node = new AlternateNode(list);
                }
              

            }

        }

        catch (RecognitionException rexc) {
            throw rexc;
        }
        finally {
        }
        return node;
    }
    // $ANTLR end "expression"


    // $ANTLR start "term"
    // Ebnf.g:100:1: term returns [EbnfNode node] : (e= ebnf )* ;
    public final EbnfNode term() throws RecognitionException {
        EbnfNode node = null;

        EbnfNode e = null;



          List<EbnfNode> list = new ArrayList<EbnfNode>();

        try {
            // Ebnf.g:104:3: ( (e= ebnf )* )
            // Ebnf.g:105:3: (e= ebnf )*
            {
            // Ebnf.g:105:3: (e= ebnf )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==ID) ) {
                    int LA3_2 = input.LA(2);

                    if ( (LA3_2==EOF||(LA3_2>=ID && LA3_2<=STRING_LITERAL)||(LA3_2>=11 && LA3_2<=19)) ) {
                        alt3=1;
                    }


                }
                else if ( (LA3_0==STRING_LITERAL||(LA3_0>=15 && LA3_0<=16)||(LA3_0>=18 && LA3_0<=19)) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // Ebnf.g:105:4: e= ebnf
            	    {
            	    pushFollow(FOLLOW_ebnf_in_term167);
            	    e=ebnf();

            	    state._fsp--;

            	    list.add(e);

            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);


              switch (list.size()) {
                    case 0:
                        node = new EmptyNode();
                        break;
                    case 1:
                        node = list.get(0);
                        break;
                    default:
                        node = new SequenceNode(list);
                        break;
                    }
              

            }

        }

        catch (RecognitionException rexc) {
            throw rexc;
        }
        finally {
        }
        return node;
    }
    // $ANTLR end "term"


    // $ANTLR start "ebnf"
    // Ebnf.g:120:1: ebnf returns [EbnfNode node] : n= factor ( '+' | '?' | '*' | ) ;
    public final EbnfNode ebnf() throws RecognitionException {
        EbnfNode node = null;

        EbnfNode n = null;


        try {
            // Ebnf.g:121:3: (n= factor ( '+' | '?' | '*' | ) )
            // Ebnf.g:122:3: n= factor ( '+' | '?' | '*' | )
            {
            pushFollow(FOLLOW_factor_in_ebnf194);
            n=factor();

            state._fsp--;

            // Ebnf.g:123:3: ( '+' | '?' | '*' | )
            int alt4=4;
            switch ( input.LA(1) ) {
            case 12:
                {
                alt4=1;
                }
                break;
            case 13:
                {
                alt4=2;
                }
                break;
            case 14:
                {
                alt4=3;
                }
                break;
            case EOF:
            case ID:
            case STRING_LITERAL:
            case 11:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
                {
                alt4=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }

            switch (alt4) {
                case 1 :
                    // Ebnf.g:124:5: '+'
                    {
                    match(input,12,FOLLOW_12_in_ebnf204); 
                    node = new SequenceNode(Arrays.asList(n, new MandatoryRepeatNode(n)));

                    }
                    break;
                case 2 :
                    // Ebnf.g:125:7: '?'
                    {
                    match(input,13,FOLLOW_13_in_ebnf214); 
                    node = new OptionNode(n);

                    }
                    break;
                case 3 :
                    // Ebnf.g:126:7: '*'
                    {
                    match(input,14,FOLLOW_14_in_ebnf224); 
                    node = new RepeatNode(n);

                    }
                    break;
                case 4 :
                    // Ebnf.g:127:7: 
                    {
                    node = n;

                    }
                    break;

            }


            }

        }

        catch (RecognitionException rexc) {
            throw rexc;
        }
        finally {
        }
        return node;
    }
    // $ANTLR end "ebnf"


    // $ANTLR start "factor"
    // Ebnf.g:131:1: factor returns [EbnfNode node] : ( ID | STRING_LITERAL | any= '.' | '(' expr= expression ')' | '~' '(' expr= expression ')' | '??' STRING_LITERAL '(' expr= expression ')' );
    public final EbnfNode factor() throws RecognitionException {
        EbnfNode node = null;

        Token any=null;
        Token ID2=null;
        Token STRING_LITERAL3=null;
        Token STRING_LITERAL4=null;
        EbnfNode expr = null;


        try {
            // Ebnf.g:132:3: ( ID | STRING_LITERAL | any= '.' | '(' expr= expression ')' | '~' '(' expr= expression ')' | '??' STRING_LITERAL '(' expr= expression ')' )
            int alt5=6;
            switch ( input.LA(1) ) {
            case ID:
                {
                alt5=1;
                }
                break;
            case STRING_LITERAL:
                {
                alt5=2;
                }
                break;
            case 15:
                {
                alt5=3;
                }
                break;
            case 16:
                {
                alt5=4;
                }
                break;
            case 18:
                {
                alt5=5;
                }
                break;
            case 19:
                {
                alt5=6;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;
            }

            switch (alt5) {
                case 1 :
                    // Ebnf.g:133:5: ID
                    {
                    ID2=(Token)match(input,ID,FOLLOW_ID_in_factor262); 
                    node = new IdentifierNode((ID2!=null?ID2.getText():null));

                    }
                    break;
                case 2 :
                    // Ebnf.g:134:5: STRING_LITERAL
                    {
                    STRING_LITERAL3=(Token)match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_factor270); 
                    node = new LiteralNode((STRING_LITERAL3!=null?STRING_LITERAL3.getText():null));

                    }
                    break;
                case 3 :
                    // Ebnf.g:135:5: any= '.'
                    {
                    any=(Token)match(input,15,FOLLOW_15_in_factor280); 
                    node = new AnyCharacterNode((any!=null?any.getText():null));

                    }
                    break;
                case 4 :
                    // Ebnf.g:136:5: '(' expr= expression ')'
                    {
                    match(input,16,FOLLOW_16_in_factor288); 
                    pushFollow(FOLLOW_expression_in_factor292);
                    expr=expression();

                    state._fsp--;

                    match(input,17,FOLLOW_17_in_factor294); 
                    node = expr;

                    }
                    break;
                case 5 :
                    // Ebnf.g:137:6: '~' '(' expr= expression ')'
                    {
                    match(input,18,FOLLOW_18_in_factor303); 
                    match(input,16,FOLLOW_16_in_factor305); 
                    pushFollow(FOLLOW_expression_in_factor309);
                    expr=expression();

                    state._fsp--;

                    match(input,17,FOLLOW_17_in_factor311); 
                    node = new ExceptionNode(expr, "~");

                    }
                    break;
                case 6 :
                    // Ebnf.g:138:5: '??' STRING_LITERAL '(' expr= expression ')'
                    {
                    match(input,19,FOLLOW_19_in_factor319); 
                    STRING_LITERAL4=(Token)match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_factor321); 
                    match(input,16,FOLLOW_16_in_factor323); 
                    pushFollow(FOLLOW_expression_in_factor327);
                    expr=expression();

                    state._fsp--;

                    match(input,17,FOLLOW_17_in_factor329); 
                    node = new PredicateNode(removeQuotes((STRING_LITERAL4!=null?STRING_LITERAL4.getText():null)), expr);

                    }
                    break;

            }
        }

        catch (RecognitionException rexc) {
            throw rexc;
        }
        finally {
        }
        return node;
    }
    // $ANTLR end "factor"

    // Delegated rules


 

    public static final BitSet FOLLOW_production_in_rule66 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_EOF_in_rule72 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_production91 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_production93 = new BitSet(new long[]{0x00000000000D8830L});
    public static final BitSet FOLLOW_expression_in_production97 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_term_in_expression125 = new BitSet(new long[]{0x0000000000000802L});
    public static final BitSet FOLLOW_11_in_expression130 = new BitSet(new long[]{0x00000000000D8830L});
    public static final BitSet FOLLOW_term_in_expression134 = new BitSet(new long[]{0x0000000000000802L});
    public static final BitSet FOLLOW_ebnf_in_term167 = new BitSet(new long[]{0x00000000000D8032L});
    public static final BitSet FOLLOW_factor_in_ebnf194 = new BitSet(new long[]{0x0000000000007002L});
    public static final BitSet FOLLOW_12_in_ebnf204 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_ebnf214 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_14_in_ebnf224 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_factor262 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_LITERAL_in_factor270 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_factor280 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_factor288 = new BitSet(new long[]{0x00000000000D8830L});
    public static final BitSet FOLLOW_expression_in_factor292 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_factor294 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_factor303 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_factor305 = new BitSet(new long[]{0x00000000000D8830L});
    public static final BitSet FOLLOW_expression_in_factor309 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_factor311 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_factor319 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_STRING_LITERAL_in_factor321 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_factor323 = new BitSet(new long[]{0x00000000000D8830L});
    public static final BitSet FOLLOW_expression_in_factor327 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_factor329 = new BitSet(new long[]{0x0000000000000002L});

}