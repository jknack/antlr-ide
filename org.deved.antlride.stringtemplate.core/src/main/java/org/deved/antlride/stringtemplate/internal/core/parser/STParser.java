// $ANTLR 3.2 Sep 23, 2009 12:02:23 /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g 2010-05-30 17:34:53

package org.deved.antlride.stringtemplate.internal.core.parser;

import org.deved.antlride.stringtemplate.core.model.dltk.ast.DASTStringTemplate;
import java.util.Set;
import java.util.HashSet;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

/** Recognize a single StringTemplate template text, expressions, and conditionals */
public class STParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "IF", "ELSE", "ELSEIF", "ENDIF", "SUPER", "SEMI", "BANG", "ELLIPSIS", "EQUALS", "COLON", "LPAREN", "RPAREN", "LBRACK", "RBRACK", "COMMA", "DOT", "LCURLY", "RCURLY", "TEXT", "LDELIM", "RDELIM", "ID", "STRING", "WS", "PIPE", "OR", "AND", "INDENT", "NEWLINE", "AT", "END"
    };
    public static final int RBRACK=17;
    public static final int LBRACK=16;
    public static final int ELSE=5;
    public static final int ELLIPSIS=11;
    public static final int LCURLY=20;
    public static final int BANG=10;
    public static final int EQUALS=12;
    public static final int TEXT=22;
    public static final int AND=30;
    public static final int ID=25;
    public static final int EOF=-1;
    public static final int SEMI=9;
    public static final int INDENT=31;
    public static final int LPAREN=14;
    public static final int IF=4;
    public static final int ELSEIF=6;
    public static final int COLON=13;
    public static final int AT=33;
    public static final int RPAREN=15;
    public static final int WS=27;
    public static final int NEWLINE=32;
    public static final int COMMA=18;
    public static final int RCURLY=21;
    public static final int OR=29;
    public static final int ENDIF=7;
    public static final int RDELIM=24;
    public static final int PIPE=28;
    public static final int SUPER=8;
    public static final int DOT=19;
    public static final int END=34;
    public static final int LDELIM=23;
    public static final int STRING=26;

    // delegates
    // delegators


        public STParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public STParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return STParser.tokenNames; }
    public String getGrammarFileName() { return "/media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g"; }


    /** The name of the template enclosing a subtemplate or region. */
    String enclosingTemplateName;
    private Set<String> funcs = new HashSet<String>();
    List<DASTStringTemplate> statements = new ArrayList<DASTStringTemplate>();

    public List<DASTStringTemplate> getStatements() {
      return statements;
    }

    protected String text(Token token) {
      if (token == null)
        return "";
      String text = token.getText();
      return text == null ? "" : text;
    }

    protected int start(Token token) {
      return start((CommonToken) token);
    }

    protected int start(CommonToken token) {
      return token.getStartIndex();
    }

    protected int end(Token token) {
      return end((CommonToken) token);
    }

    protected int end(CommonToken token) {
      return token.getStopIndex() + 1;
    }




    // $ANTLR start "templateAndEOF"
    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:80:1: templateAndEOF : template EOF ;
    public final void templateAndEOF() throws RecognitionException {
        try {
            // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:81:2: ( template EOF )
            // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:81:4: template EOF
            {
            pushFollow(FOLLOW_template_in_templateAndEOF49);
            template();

            state._fsp--;

            match(input,EOF,FOLLOW_EOF_in_templateAndEOF51); 

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
    // $ANTLR end "templateAndEOF"


    // $ANTLR start "template"
    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:84:1: template : ( element )* ;
    public final void template() throws RecognitionException {
        try {
            // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:85:2: ( ( element )* )
            // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:85:4: ( element )*
            {
            // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:85:4: ( element )*
            loop1:
            do {
                int alt1=2;
                switch ( input.LA(1) ) {
                case LDELIM:
                    {
                    int LA1_2 = input.LA(2);

                    if ( (LA1_2==IF||LA1_2==SUPER||LA1_2==LPAREN||LA1_2==LBRACK||LA1_2==LCURLY||(LA1_2>=ID && LA1_2<=STRING)||LA1_2==AT) ) {
                        alt1=1;
                    }


                    }
                    break;
                case INDENT:
                    {
                    int LA1_3 = input.LA(2);

                    if ( (LA1_3==TEXT||LA1_3==NEWLINE) ) {
                        alt1=1;
                    }
                    else if ( (LA1_3==LDELIM) ) {
                        int LA1_5 = input.LA(3);

                        if ( (LA1_5==IF||LA1_5==SUPER||LA1_5==LPAREN||LA1_5==LBRACK||LA1_5==LCURLY||(LA1_5>=ID && LA1_5<=STRING)||LA1_5==AT) ) {
                            alt1=1;
                        }


                    }


                    }
                    break;
                case TEXT:
                case NEWLINE:
                    {
                    alt1=1;
                    }
                    break;

                }

                switch (alt1) {
            	case 1 :
            	    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:85:4: element
            	    {
            	    pushFollow(FOLLOW_element_in_template62);
            	    element();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop1;
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
    // $ANTLR end "template"


    // $ANTLR start "element"
    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:88:1: element : ( (i= INDENT )? ifstat ({...}? NEWLINE )? | i= INDENT exprTag | exprTag | i= INDENT text | text | (i= INDENT )? region | i= INDENT NEWLINE | NEWLINE );
    public final void element() throws RecognitionException {
        CommonToken i=null;
        STParser.ifstat_return ifstat1 = null;


        try {
            // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:89:2: ( (i= INDENT )? ifstat ({...}? NEWLINE )? | i= INDENT exprTag | exprTag | i= INDENT text | text | (i= INDENT )? region | i= INDENT NEWLINE | NEWLINE )
            int alt5=8;
            alt5 = dfa5.predict(input);
            switch (alt5) {
                case 1 :
                    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:89:4: (i= INDENT )? ifstat ({...}? NEWLINE )?
                    {
                    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:89:4: (i= INDENT )?
                    int alt2=2;
                    int LA2_0 = input.LA(1);

                    if ( (LA2_0==INDENT) ) {
                        alt2=1;
                    }
                    switch (alt2) {
                        case 1 :
                            // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:89:6: i= INDENT
                            {
                            i=(CommonToken)match(input,INDENT,FOLLOW_INDENT_in_element78); 

                            }
                            break;

                    }

                    pushFollow(FOLLOW_ifstat_in_element85);
                    ifstat1=ifstat();

                    state._fsp--;

                    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:92:3: ({...}? NEWLINE )?
                    int alt3=2;
                    int LA3_0 = input.LA(1);

                    if ( (LA3_0==NEWLINE) ) {
                        int LA3_1 = input.LA(2);

                        if ( (((ifstat1!=null?((CommonToken)ifstat1.start):null).getLine()!=input.LT(1).getLine())) ) {
                            alt3=1;
                        }
                    }
                    switch (alt3) {
                        case 1 :
                            // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:92:5: {...}? NEWLINE
                            {
                            if ( !(((ifstat1!=null?((CommonToken)ifstat1.start):null).getLine()!=input.LT(1).getLine())) ) {
                                throw new FailedPredicateException(input, "element", "$ifstat.start.getLine()!=input.LT(1).getLine()");
                            }
                            match(input,NEWLINE,FOLLOW_NEWLINE_in_element96); 

                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:93:4: i= INDENT exprTag
                    {
                    i=(CommonToken)match(input,INDENT,FOLLOW_INDENT_in_element106); 
                    pushFollow(FOLLOW_exprTag_in_element119);
                    exprTag();

                    state._fsp--;


                    }
                    break;
                case 3 :
                    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:95:4: exprTag
                    {
                    pushFollow(FOLLOW_exprTag_in_element134);
                    exprTag();

                    state._fsp--;


                    }
                    break;
                case 4 :
                    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:96:4: i= INDENT text
                    {
                    i=(CommonToken)match(input,INDENT,FOLLOW_INDENT_in_element141); 
                    pushFollow(FOLLOW_text_in_element154);
                    text();

                    state._fsp--;


                    }
                    break;
                case 5 :
                    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:98:4: text
                    {
                    pushFollow(FOLLOW_text_in_element172);
                    text();

                    state._fsp--;


                    }
                    break;
                case 6 :
                    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:99:6: (i= INDENT )? region
                    {
                    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:99:6: (i= INDENT )?
                    int alt4=2;
                    int LA4_0 = input.LA(1);

                    if ( (LA4_0==INDENT) ) {
                        alt4=1;
                    }
                    switch (alt4) {
                        case 1 :
                            // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:99:7: i= INDENT
                            {
                            i=(CommonToken)match(input,INDENT,FOLLOW_INDENT_in_element182); 

                            }
                            break;

                    }

                    pushFollow(FOLLOW_region_in_element186);
                    region();

                    state._fsp--;


                    }
                    break;
                case 7 :
                    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:100:4: i= INDENT NEWLINE
                    {
                    i=(CommonToken)match(input,INDENT,FOLLOW_INDENT_in_element200); 
                    match(input,NEWLINE,FOLLOW_NEWLINE_in_element214); 

                    }
                    break;
                case 8 :
                    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:102:4: NEWLINE
                    {
                    match(input,NEWLINE,FOLLOW_NEWLINE_in_element230); 

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
    // $ANTLR end "element"


    // $ANTLR start "text"
    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:105:1: text : TEXT ;
    public final void text() throws RecognitionException {
        try {
            // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:106:2: ( TEXT )
            // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:106:4: TEXT
            {
            match(input,TEXT,FOLLOW_TEXT_in_text251); 

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
    // $ANTLR end "text"


    // $ANTLR start "exprTag"
    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:109:1: exprTag : LDELIM expr ( ';' exprOptions | ) RDELIM ;
    public final void exprTag() throws RecognitionException {
        try {
            // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:110:2: ( LDELIM expr ( ';' exprOptions | ) RDELIM )
            // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:110:4: LDELIM expr ( ';' exprOptions | ) RDELIM
            {
            match(input,LDELIM,FOLLOW_LDELIM_in_exprTag262); 
            pushFollow(FOLLOW_expr_in_exprTag266);
            expr();

            state._fsp--;

            // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:112:3: ( ';' exprOptions | )
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==SEMI) ) {
                alt6=1;
            }
            else if ( (LA6_0==RDELIM) ) {
                alt6=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;
            }
            switch (alt6) {
                case 1 :
                    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:112:5: ';' exprOptions
                    {
                    match(input,SEMI,FOLLOW_SEMI_in_exprTag272); 
                    pushFollow(FOLLOW_exprOptions_in_exprTag274);
                    exprOptions();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:114:3: 
                    {
                    }
                    break;

            }

            match(input,RDELIM,FOLLOW_RDELIM_in_exprTag286); 

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
    // $ANTLR end "exprTag"


    // $ANTLR start "region"
    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:118:1: region : LDELIM '@' ID RDELIM LDELIM '@end' RDELIM ;
    public final void region() throws RecognitionException {
        try {
            // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:120:2: ( LDELIM '@' ID RDELIM LDELIM '@end' RDELIM )
            // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:120:4: LDELIM '@' ID RDELIM LDELIM '@end' RDELIM
            {
            match(input,LDELIM,FOLLOW_LDELIM_in_region299); 
            match(input,AT,FOLLOW_AT_in_region301); 
            match(input,ID,FOLLOW_ID_in_region303); 
            match(input,RDELIM,FOLLOW_RDELIM_in_region305); 
            match(input,LDELIM,FOLLOW_LDELIM_in_region309); 
            match(input,END,FOLLOW_END_in_region311); 
            match(input,RDELIM,FOLLOW_RDELIM_in_region313); 

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
    // $ANTLR end "region"


    // $ANTLR start "subtemplate"
    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:124:1: subtemplate : '{' (ids+= ID ( ',' ids+= ID )* '|' )? '}' ;
    public final void subtemplate() throws RecognitionException {
        CommonToken ids=null;
        List list_ids=null;

        try {
            // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:125:2: ( '{' (ids+= ID ( ',' ids+= ID )* '|' )? '}' )
            // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:125:4: '{' (ids+= ID ( ',' ids+= ID )* '|' )? '}'
            {
            match(input,LCURLY,FOLLOW_LCURLY_in_subtemplate325); 
            // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:125:8: (ids+= ID ( ',' ids+= ID )* '|' )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==ID) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:125:10: ids+= ID ( ',' ids+= ID )* '|'
                    {
                    ids=(CommonToken)match(input,ID,FOLLOW_ID_in_subtemplate331); 
                    if (list_ids==null) list_ids=new ArrayList();
                    list_ids.add(ids);

                    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:125:18: ( ',' ids+= ID )*
                    loop7:
                    do {
                        int alt7=2;
                        int LA7_0 = input.LA(1);

                        if ( (LA7_0==COMMA) ) {
                            alt7=1;
                        }


                        switch (alt7) {
                    	case 1 :
                    	    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:125:19: ',' ids+= ID
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_subtemplate334); 
                    	    ids=(CommonToken)match(input,ID,FOLLOW_ID_in_subtemplate338); 
                    	    if (list_ids==null) list_ids=new ArrayList();
                    	    list_ids.add(ids);


                    	    }
                    	    break;

                    	default :
                    	    break loop7;
                        }
                    } while (true);

                    match(input,PIPE,FOLLOW_PIPE_in_subtemplate342); 

                    }
                    break;

            }

            match(input,RCURLY,FOLLOW_RCURLY_in_subtemplate347); 

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
    // $ANTLR end "subtemplate"


    // $ANTLR start "addTemplateEndTokensToFollowOfTemplateRule"
    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:128:1: addTemplateEndTokensToFollowOfTemplateRule : template ( '}' | LDELIM '@end' ) ;
    public final void addTemplateEndTokensToFollowOfTemplateRule() throws RecognitionException {
        try {
            // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:133:44: ( template ( '}' | LDELIM '@end' ) )
            // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:133:46: template ( '}' | LDELIM '@end' )
            {
            pushFollow(FOLLOW_template_in_addTemplateEndTokensToFollowOfTemplateRule362);
            template();

            state._fsp--;

            // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:133:55: ( '}' | LDELIM '@end' )
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==RCURLY) ) {
                alt9=1;
            }
            else if ( (LA9_0==LDELIM) ) {
                alt9=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 9, 0, input);

                throw nvae;
            }
            switch (alt9) {
                case 1 :
                    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:133:56: '}'
                    {
                    match(input,RCURLY,FOLLOW_RCURLY_in_addTemplateEndTokensToFollowOfTemplateRule365); 

                    }
                    break;
                case 2 :
                    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:133:60: LDELIM '@end'
                    {
                    match(input,LDELIM,FOLLOW_LDELIM_in_addTemplateEndTokensToFollowOfTemplateRule367); 
                    match(input,END,FOLLOW_END_in_addTemplateEndTokensToFollowOfTemplateRule369); 

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
    // $ANTLR end "addTemplateEndTokensToFollowOfTemplateRule"

    public static class ifstat_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "ifstat"
    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:135:1: ifstat : LDELIM 'if' '(' conditional ')' RDELIM template ( ( INDENT )? LDELIM 'elseif' '(' conditional ')' RDELIM template )* ( ( INDENT )? LDELIM 'else' RDELIM template )? ( INDENT )? endif= LDELIM 'endif' RDELIM ;
    public final STParser.ifstat_return ifstat() throws RecognitionException {
        STParser.ifstat_return retval = new STParser.ifstat_return();
        retval.start = input.LT(1);

        CommonToken endif=null;


            /** Tracks address of branch operand (in code block).  It's how
             *  we backpatch forward references when generating code for IFs.
             */
            int prevBranchOperand = -1;
            /** Branch instruction operands that are forward refs to end of IF.
             *  We need to update them once we see the endif.
             */
            List<Integer> endRefs = new ArrayList<Integer>();

        try {
            // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:146:2: ( LDELIM 'if' '(' conditional ')' RDELIM template ( ( INDENT )? LDELIM 'elseif' '(' conditional ')' RDELIM template )* ( ( INDENT )? LDELIM 'else' RDELIM template )? ( INDENT )? endif= LDELIM 'endif' RDELIM )
            // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:146:4: LDELIM 'if' '(' conditional ')' RDELIM template ( ( INDENT )? LDELIM 'elseif' '(' conditional ')' RDELIM template )* ( ( INDENT )? LDELIM 'else' RDELIM template )? ( INDENT )? endif= LDELIM 'endif' RDELIM
            {
            match(input,LDELIM,FOLLOW_LDELIM_in_ifstat385); 
            match(input,IF,FOLLOW_IF_in_ifstat387); 
            match(input,LPAREN,FOLLOW_LPAREN_in_ifstat389); 
            pushFollow(FOLLOW_conditional_in_ifstat391);
            conditional();

            state._fsp--;

            match(input,RPAREN,FOLLOW_RPAREN_in_ifstat393); 
            match(input,RDELIM,FOLLOW_RDELIM_in_ifstat395); 
            pushFollow(FOLLOW_template_in_ifstat399);
            template();

            state._fsp--;

            // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:148:3: ( ( INDENT )? LDELIM 'elseif' '(' conditional ')' RDELIM template )*
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( (LA11_0==INDENT) ) {
                    int LA11_1 = input.LA(2);

                    if ( (LA11_1==LDELIM) ) {
                        int LA11_2 = input.LA(3);

                        if ( (LA11_2==ELSEIF) ) {
                            alt11=1;
                        }


                    }


                }
                else if ( (LA11_0==LDELIM) ) {
                    int LA11_2 = input.LA(2);

                    if ( (LA11_2==ELSEIF) ) {
                        alt11=1;
                    }


                }


                switch (alt11) {
            	case 1 :
            	    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:148:5: ( INDENT )? LDELIM 'elseif' '(' conditional ')' RDELIM template
            	    {
            	    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:148:5: ( INDENT )?
            	    int alt10=2;
            	    int LA10_0 = input.LA(1);

            	    if ( (LA10_0==INDENT) ) {
            	        alt10=1;
            	    }
            	    switch (alt10) {
            	        case 1 :
            	            // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:148:5: INDENT
            	            {
            	            match(input,INDENT,FOLLOW_INDENT_in_ifstat405); 

            	            }
            	            break;

            	    }

            	    match(input,LDELIM,FOLLOW_LDELIM_in_ifstat408); 
            	    match(input,ELSEIF,FOLLOW_ELSEIF_in_ifstat410); 
            	    match(input,LPAREN,FOLLOW_LPAREN_in_ifstat415); 
            	    pushFollow(FOLLOW_conditional_in_ifstat417);
            	    conditional();

            	    state._fsp--;

            	    match(input,RPAREN,FOLLOW_RPAREN_in_ifstat419); 
            	    match(input,RDELIM,FOLLOW_RDELIM_in_ifstat421); 
            	    pushFollow(FOLLOW_template_in_ifstat426);
            	    template();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop11;
                }
            } while (true);

            // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:152:3: ( ( INDENT )? LDELIM 'else' RDELIM template )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==INDENT) ) {
                int LA13_1 = input.LA(2);

                if ( (LA13_1==LDELIM) ) {
                    int LA13_2 = input.LA(3);

                    if ( (LA13_2==ELSE) ) {
                        alt13=1;
                    }
                }
            }
            else if ( (LA13_0==LDELIM) ) {
                int LA13_2 = input.LA(2);

                if ( (LA13_2==ELSE) ) {
                    alt13=1;
                }
            }
            switch (alt13) {
                case 1 :
                    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:152:5: ( INDENT )? LDELIM 'else' RDELIM template
                    {
                    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:152:5: ( INDENT )?
                    int alt12=2;
                    int LA12_0 = input.LA(1);

                    if ( (LA12_0==INDENT) ) {
                        alt12=1;
                    }
                    switch (alt12) {
                        case 1 :
                            // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:152:5: INDENT
                            {
                            match(input,INDENT,FOLLOW_INDENT_in_ifstat437); 

                            }
                            break;

                    }

                    match(input,LDELIM,FOLLOW_LDELIM_in_ifstat440); 
                    match(input,ELSE,FOLLOW_ELSE_in_ifstat442); 
                    match(input,RDELIM,FOLLOW_RDELIM_in_ifstat444); 
                    pushFollow(FOLLOW_template_in_ifstat449);
                    template();

                    state._fsp--;


                    }
                    break;

            }

            // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:155:3: ( INDENT )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==INDENT) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:155:3: INDENT
                    {
                    match(input,INDENT,FOLLOW_INDENT_in_ifstat458); 

                    }
                    break;

            }

            endif=(CommonToken)match(input,LDELIM,FOLLOW_LDELIM_in_ifstat463); 
            match(input,ENDIF,FOLLOW_ENDIF_in_ifstat465); 
            match(input,RDELIM,FOLLOW_RDELIM_in_ifstat467); 

            }

            retval.stop = input.LT(-1);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "ifstat"


    // $ANTLR start "conditional"
    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:159:1: conditional : andConditional ( '||' andConditional )* ;
    public final void conditional() throws RecognitionException {
        try {
            // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:160:2: ( andConditional ( '||' andConditional )* )
            // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:160:4: andConditional ( '||' andConditional )*
            {
            pushFollow(FOLLOW_andConditional_in_conditional483);
            andConditional();

            state._fsp--;

            // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:160:19: ( '||' andConditional )*
            loop15:
            do {
                int alt15=2;
                int LA15_0 = input.LA(1);

                if ( (LA15_0==OR) ) {
                    alt15=1;
                }


                switch (alt15) {
            	case 1 :
            	    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:160:20: '||' andConditional
            	    {
            	    match(input,OR,FOLLOW_OR_in_conditional486); 
            	    pushFollow(FOLLOW_andConditional_in_conditional488);
            	    andConditional();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop15;
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
    // $ANTLR end "conditional"


    // $ANTLR start "andConditional"
    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:163:1: andConditional : notConditional ( '&&' notConditional )* ;
    public final void andConditional() throws RecognitionException {
        try {
            // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:164:2: ( notConditional ( '&&' notConditional )* )
            // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:164:4: notConditional ( '&&' notConditional )*
            {
            pushFollow(FOLLOW_notConditional_in_andConditional502);
            notConditional();

            state._fsp--;

            // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:164:19: ( '&&' notConditional )*
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( (LA16_0==AND) ) {
                    alt16=1;
                }


                switch (alt16) {
            	case 1 :
            	    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:164:20: '&&' notConditional
            	    {
            	    match(input,AND,FOLLOW_AND_in_andConditional505); 
            	    pushFollow(FOLLOW_notConditional_in_andConditional507);
            	    notConditional();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop16;
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
    // $ANTLR end "andConditional"


    // $ANTLR start "notConditional"
    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:167:1: notConditional : ( '!' memberExpr | memberExpr );
    public final void notConditional() throws RecognitionException {
        try {
            // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:168:2: ( '!' memberExpr | memberExpr )
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==BANG) ) {
                alt17=1;
            }
            else if ( (LA17_0==SUPER||LA17_0==LPAREN||LA17_0==LBRACK||LA17_0==LCURLY||(LA17_0>=ID && LA17_0<=STRING)||LA17_0==AT) ) {
                alt17=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 17, 0, input);

                throw nvae;
            }
            switch (alt17) {
                case 1 :
                    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:168:4: '!' memberExpr
                    {
                    match(input,BANG,FOLLOW_BANG_in_notConditional520); 
                    pushFollow(FOLLOW_memberExpr_in_notConditional522);
                    memberExpr();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:169:4: memberExpr
                    {
                    pushFollow(FOLLOW_memberExpr_in_notConditional527);
                    memberExpr();

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
    // $ANTLR end "notConditional"


    // $ANTLR start "exprOptions"
    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:172:1: exprOptions : option ( ',' option )* ;
    public final void exprOptions() throws RecognitionException {
        try {
            // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:173:2: ( option ( ',' option )* )
            // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:173:4: option ( ',' option )*
            {
            pushFollow(FOLLOW_option_in_exprOptions539);
            option();

            state._fsp--;

            // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:173:11: ( ',' option )*
            loop18:
            do {
                int alt18=2;
                int LA18_0 = input.LA(1);

                if ( (LA18_0==COMMA) ) {
                    alt18=1;
                }


                switch (alt18) {
            	case 1 :
            	    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:173:12: ',' option
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_exprOptions542); 
            	    pushFollow(FOLLOW_option_in_exprOptions544);
            	    option();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop18;
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
    // $ANTLR end "exprOptions"


    // $ANTLR start "option"
    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:176:1: option : ID ( '=' exprNoComma | ) ;
    public final void option() throws RecognitionException {
        try {
            // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:177:2: ( ID ( '=' exprNoComma | ) )
            // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:177:4: ID ( '=' exprNoComma | )
            {
            match(input,ID,FOLLOW_ID_in_option557); 
            // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:177:7: ( '=' exprNoComma | )
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==EQUALS) ) {
                alt19=1;
            }
            else if ( (LA19_0==COMMA||LA19_0==RDELIM) ) {
                alt19=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 19, 0, input);

                throw nvae;
            }
            switch (alt19) {
                case 1 :
                    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:177:9: '=' exprNoComma
                    {
                    match(input,EQUALS,FOLLOW_EQUALS_in_option561); 
                    pushFollow(FOLLOW_exprNoComma_in_option563);
                    exprNoComma();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:177:27: 
                    {
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
    // $ANTLR end "option"


    // $ANTLR start "exprNoComma"
    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:180:1: exprNoComma : memberExpr ( ':' templateRef )? ;
    public final void exprNoComma() throws RecognitionException {
        try {
            // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:181:2: ( memberExpr ( ':' templateRef )? )
            // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:181:4: memberExpr ( ':' templateRef )?
            {
            pushFollow(FOLLOW_memberExpr_in_exprNoComma579);
            memberExpr();

            state._fsp--;

            // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:182:3: ( ':' templateRef )?
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==COLON) ) {
                alt20=1;
            }
            switch (alt20) {
                case 1 :
                    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:182:5: ':' templateRef
                    {
                    match(input,COLON,FOLLOW_COLON_in_exprNoComma585); 
                    pushFollow(FOLLOW_templateRef_in_exprNoComma587);
                    templateRef();

                    state._fsp--;


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
    // $ANTLR end "exprNoComma"


    // $ANTLR start "expr"
    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:185:1: expr : mapExpr ;
    public final void expr() throws RecognitionException {
        try {
            // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:185:6: ( mapExpr )
            // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:185:8: mapExpr
            {
            pushFollow(FOLLOW_mapExpr_in_expr600);
            mapExpr();

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
    // $ANTLR end "expr"

    public static class mapExpr_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "mapExpr"
    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:187:1: mapExpr : memberExpr (c= ',' memberExpr )* ( ':' templateRef ( ( ',' templateRef )+ | ) )* ;
    public final STParser.mapExpr_return mapExpr() throws RecognitionException {
        STParser.mapExpr_return retval = new STParser.mapExpr_return();
        retval.start = input.LT(1);

        CommonToken c=null;

        int nt=1, ne=1; int a=((CommonToken)retval.start).getStartIndex();
        try {
            // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:189:2: ( memberExpr (c= ',' memberExpr )* ( ':' templateRef ( ( ',' templateRef )+ | ) )* )
            // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:189:4: memberExpr (c= ',' memberExpr )* ( ':' templateRef ( ( ',' templateRef )+ | ) )*
            {
            pushFollow(FOLLOW_memberExpr_in_mapExpr615);
            memberExpr();

            state._fsp--;

            // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:189:15: (c= ',' memberExpr )*
            loop21:
            do {
                int alt21=2;
                int LA21_0 = input.LA(1);

                if ( (LA21_0==COMMA) ) {
                    alt21=1;
                }


                switch (alt21) {
            	case 1 :
            	    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:189:16: c= ',' memberExpr
            	    {
            	    c=(CommonToken)match(input,COMMA,FOLLOW_COMMA_in_mapExpr620); 
            	    pushFollow(FOLLOW_memberExpr_in_mapExpr622);
            	    memberExpr();

            	    state._fsp--;

            	    ne++;

            	    }
            	    break;

            	default :
            	    break loop21;
                }
            } while (true);

            // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:190:3: ( ':' templateRef ( ( ',' templateRef )+ | ) )*
            loop24:
            do {
                int alt24=2;
                int LA24_0 = input.LA(1);

                if ( (LA24_0==COLON) ) {
                    alt24=1;
                }


                switch (alt24) {
            	case 1 :
            	    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:190:5: ':' templateRef ( ( ',' templateRef )+ | )
            	    {
            	    match(input,COLON,FOLLOW_COLON_in_mapExpr633); 
            	    pushFollow(FOLLOW_templateRef_in_mapExpr635);
            	    templateRef();

            	    state._fsp--;

            	    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:191:4: ( ( ',' templateRef )+ | )
            	    int alt23=2;
            	    int LA23_0 = input.LA(1);

            	    if ( (LA23_0==COMMA) ) {
            	        alt23=1;
            	    }
            	    else if ( (LA23_0==SEMI||LA23_0==COLON||LA23_0==RPAREN||LA23_0==RDELIM) ) {
            	        alt23=2;
            	    }
            	    else {
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 23, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt23) {
            	        case 1 :
            	            // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:191:6: ( ',' templateRef )+
            	            {
            	            // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:191:6: ( ',' templateRef )+
            	            int cnt22=0;
            	            loop22:
            	            do {
            	                int alt22=2;
            	                int LA22_0 = input.LA(1);

            	                if ( (LA22_0==COMMA) ) {
            	                    alt22=1;
            	                }


            	                switch (alt22) {
            	            	case 1 :
            	            	    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:191:7: ',' templateRef
            	            	    {
            	            	    match(input,COMMA,FOLLOW_COMMA_in_mapExpr643); 
            	            	    pushFollow(FOLLOW_templateRef_in_mapExpr645);
            	            	    templateRef();

            	            	    state._fsp--;

            	            	    nt++;

            	            	    }
            	            	    break;

            	            	default :
            	            	    if ( cnt22 >= 1 ) break loop22;
            	                        EarlyExitException eee =
            	                            new EarlyExitException(22, input);
            	                        throw eee;
            	                }
            	                cnt22++;
            	            } while (true);


            	            }
            	            break;
            	        case 2 :
            	            // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:193:4: 
            	            {
            	            }
            	            break;

            	    }


            	    }
            	    break;

            	default :
            	    break loop24;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "mapExpr"


    // $ANTLR start "memberExpr"
    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:197:1: memberExpr : callExpr ( '.' ID | '.' lp= '(' mapExpr rp= ')' )* ;
    public final void memberExpr() throws RecognitionException {
        CommonToken lp=null;
        CommonToken rp=null;
        CommonToken ID2=null;

        try {
            // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:198:2: ( callExpr ( '.' ID | '.' lp= '(' mapExpr rp= ')' )* )
            // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:198:4: callExpr ( '.' ID | '.' lp= '(' mapExpr rp= ')' )*
            {
            pushFollow(FOLLOW_callExpr_in_memberExpr676);
            callExpr();

            state._fsp--;

            // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:199:3: ( '.' ID | '.' lp= '(' mapExpr rp= ')' )*
            loop25:
            do {
                int alt25=3;
                int LA25_0 = input.LA(1);

                if ( (LA25_0==DOT) ) {
                    int LA25_2 = input.LA(2);

                    if ( (LA25_2==ID) ) {
                        alt25=1;
                    }
                    else if ( (LA25_2==LPAREN) ) {
                        alt25=2;
                    }


                }


                switch (alt25) {
            	case 1 :
            	    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:199:5: '.' ID
            	    {
            	    match(input,DOT,FOLLOW_DOT_in_memberExpr682); 
            	    ID2=(CommonToken)match(input,ID,FOLLOW_ID_in_memberExpr684); 
            	    statements.get(statements.size()-1).appendName(text(ID2));

            	    }
            	    break;
            	case 2 :
            	    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:200:5: '.' lp= '(' mapExpr rp= ')'
            	    {
            	    match(input,DOT,FOLLOW_DOT_in_memberExpr692); 
            	    lp=(CommonToken)match(input,LPAREN,FOLLOW_LPAREN_in_memberExpr696); 
            	    pushFollow(FOLLOW_mapExpr_in_memberExpr698);
            	    mapExpr();

            	    state._fsp--;

            	    rp=(CommonToken)match(input,RPAREN,FOLLOW_RPAREN_in_memberExpr702); 

            	    }
            	    break;

            	default :
            	    break loop25;
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
    // $ANTLR end "memberExpr"


    // $ANTLR start "callExpr"
    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:204:1: callExpr options {k=2; } : ({...}? ID '(' expr ')' | (s= 'super' '.' )? ID '(' ( args )? ')' | '@' (s= 'super' '.' )? ID '(' rp= ')' | primary );
    public final void callExpr() throws RecognitionException {
        CommonToken s=null;
        CommonToken rp=null;
        CommonToken ID3=null;
        CommonToken ID4=null;
        CommonToken ID5=null;

        try {
            // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:206:2: ({...}? ID '(' expr ')' | (s= 'super' '.' )? ID '(' ( args )? ')' | '@' (s= 'super' '.' )? ID '(' rp= ')' | primary )
            int alt29=4;
            alt29 = dfa29.predict(input);
            switch (alt29) {
                case 1 :
                    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:206:4: {...}? ID '(' expr ')'
                    {
                    if ( !((funcs.contains(input.LT(1).getText()))) ) {
                        throw new FailedPredicateException(input, "callExpr", "funcs.contains(input.LT(1).getText())");
                    }
                    ID3=(CommonToken)match(input,ID,FOLLOW_ID_in_callExpr737); 
                    match(input,LPAREN,FOLLOW_LPAREN_in_callExpr739); 
                    pushFollow(FOLLOW_expr_in_callExpr741);
                    expr();

                    state._fsp--;

                    match(input,RPAREN,FOLLOW_RPAREN_in_callExpr743); 

                    		              String text = text(ID3);
                    		              int start = start(ID3);
                    		              int end = end(ID3);
                    		              int declStart = start;
                    		              int declEnd = end;
                    		              statements.add(new DASTStringTemplate(text, start, end, declStart, declEnd));
                    		              funcs.add(text);
                    		             

                    }
                    break;
                case 2 :
                    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:217:4: (s= 'super' '.' )? ID '(' ( args )? ')'
                    {
                    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:217:4: (s= 'super' '.' )?
                    int alt26=2;
                    int LA26_0 = input.LA(1);

                    if ( (LA26_0==SUPER) ) {
                        alt26=1;
                    }
                    switch (alt26) {
                        case 1 :
                            // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:217:5: s= 'super' '.'
                            {
                            s=(CommonToken)match(input,SUPER,FOLLOW_SUPER_in_callExpr769); 
                            match(input,DOT,FOLLOW_DOT_in_callExpr771); 

                            }
                            break;

                    }

                    ID4=(CommonToken)match(input,ID,FOLLOW_ID_in_callExpr775); 
                    match(input,LPAREN,FOLLOW_LPAREN_in_callExpr779); 
                    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:218:7: ( args )?
                    int alt27=2;
                    int LA27_0 = input.LA(1);

                    if ( (LA27_0==SUPER||LA27_0==ELLIPSIS||LA27_0==LPAREN||LA27_0==LBRACK||LA27_0==LCURLY||(LA27_0>=ID && LA27_0<=STRING)||LA27_0==AT) ) {
                        alt27=1;
                    }
                    switch (alt27) {
                        case 1 :
                            // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:218:7: args
                            {
                            pushFollow(FOLLOW_args_in_callExpr781);
                            args();

                            state._fsp--;


                            }
                            break;

                    }

                    match(input,RPAREN,FOLLOW_RPAREN_in_callExpr784); 

                                      String text = text(ID4);
                                      int start = start(ID4);
                                      int end = end(ID4);
                                      int declStart = start;
                                      int declEnd = end;
                                      statements.add(new DASTStringTemplate(text, start, end, declStart, declEnd));
                                     

                    }
                    break;
                case 3 :
                    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:227:4: '@' (s= 'super' '.' )? ID '(' rp= ')'
                    {
                    match(input,AT,FOLLOW_AT_in_callExpr805); 
                    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:227:8: (s= 'super' '.' )?
                    int alt28=2;
                    int LA28_0 = input.LA(1);

                    if ( (LA28_0==SUPER) ) {
                        alt28=1;
                    }
                    switch (alt28) {
                        case 1 :
                            // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:227:9: s= 'super' '.'
                            {
                            s=(CommonToken)match(input,SUPER,FOLLOW_SUPER_in_callExpr810); 
                            match(input,DOT,FOLLOW_DOT_in_callExpr812); 

                            }
                            break;

                    }

                    ID5=(CommonToken)match(input,ID,FOLLOW_ID_in_callExpr816); 
                    match(input,LPAREN,FOLLOW_LPAREN_in_callExpr818); 
                    rp=(CommonToken)match(input,RPAREN,FOLLOW_RPAREN_in_callExpr822); 

                                      String text =  text(ID5);
                                      int start = start(ID5);
                                      int end = end(ID5);
                                      int declStart = start;
                                      int declEnd = end;
                                      statements.add(new DASTStringTemplate(text, start, end, declStart, declEnd));
                                     

                    }
                    break;
                case 4 :
                    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:236:5: primary
                    {
                    pushFollow(FOLLOW_primary_in_callExpr845);
                    primary();

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
    // $ANTLR end "callExpr"


    // $ANTLR start "primary"
    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:239:1: primary : (o= ID | STRING | subtemplate | list | lp= '(' expr rp= ')' ( '(' ( args )? ')' )? );
    public final void primary() throws RecognitionException {
        CommonToken o=null;
        CommonToken lp=null;
        CommonToken rp=null;

        try {
            // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:240:2: (o= ID | STRING | subtemplate | list | lp= '(' expr rp= ')' ( '(' ( args )? ')' )? )
            int alt32=5;
            switch ( input.LA(1) ) {
            case ID:
                {
                alt32=1;
                }
                break;
            case STRING:
                {
                alt32=2;
                }
                break;
            case LCURLY:
                {
                alt32=3;
                }
                break;
            case LBRACK:
                {
                alt32=4;
                }
                break;
            case LPAREN:
                {
                alt32=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 32, 0, input);

                throw nvae;
            }

            switch (alt32) {
                case 1 :
                    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:240:4: o= ID
                    {
                    o=(CommonToken)match(input,ID,FOLLOW_ID_in_primary858); 

                                      String text =  text(o);
                                      int start = start(o);
                                      int end = end(o);
                                      int declStart = start;
                                      int declEnd = end;
                                      statements.add(new DASTStringTemplate(text, start, end, declStart, declEnd));
                                     

                    }
                    break;
                case 2 :
                    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:249:4: STRING
                    {
                    match(input,STRING,FOLLOW_STRING_in_primary878); 

                    }
                    break;
                case 3 :
                    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:250:4: subtemplate
                    {
                    pushFollow(FOLLOW_subtemplate_in_primary883);
                    subtemplate();

                    state._fsp--;


                    }
                    break;
                case 4 :
                    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:251:4: list
                    {
                    pushFollow(FOLLOW_list_in_primary888);
                    list();

                    state._fsp--;


                    }
                    break;
                case 5 :
                    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:252:4: lp= '(' expr rp= ')' ( '(' ( args )? ')' )?
                    {
                    lp=(CommonToken)match(input,LPAREN,FOLLOW_LPAREN_in_primary895); 
                    pushFollow(FOLLOW_expr_in_primary897);
                    expr();

                    state._fsp--;

                    rp=(CommonToken)match(input,RPAREN,FOLLOW_RPAREN_in_primary901); 
                    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:253:3: ( '(' ( args )? ')' )?
                    int alt31=2;
                    int LA31_0 = input.LA(1);

                    if ( (LA31_0==LPAREN) ) {
                        alt31=1;
                    }
                    switch (alt31) {
                        case 1 :
                            // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:254:4: '(' ( args )? ')'
                            {
                            match(input,LPAREN,FOLLOW_LPAREN_in_primary926); 
                            // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:254:8: ( args )?
                            int alt30=2;
                            int LA30_0 = input.LA(1);

                            if ( (LA30_0==SUPER||LA30_0==ELLIPSIS||LA30_0==LPAREN||LA30_0==LBRACK||LA30_0==LCURLY||(LA30_0>=ID && LA30_0<=STRING)||LA30_0==AT) ) {
                                alt30=1;
                            }
                            switch (alt30) {
                                case 1 :
                                    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:254:8: args
                                    {
                                    pushFollow(FOLLOW_args_in_primary928);
                                    args();

                                    state._fsp--;


                                    }
                                    break;

                            }

                            match(input,RPAREN,FOLLOW_RPAREN_in_primary931); 

                            }
                            break;

                    }


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
    // $ANTLR end "primary"


    // $ANTLR start "args"
    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:258:1: args : arg ( ',' arg )* ;
    public final void args() throws RecognitionException {
        try {
            // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:258:5: ( arg ( ',' arg )* )
            // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:258:7: arg ( ',' arg )*
            {
            pushFollow(FOLLOW_arg_in_args947);
            arg();

            state._fsp--;

            // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:258:11: ( ',' arg )*
            loop33:
            do {
                int alt33=2;
                int LA33_0 = input.LA(1);

                if ( (LA33_0==COMMA) ) {
                    alt33=1;
                }


                switch (alt33) {
            	case 1 :
            	    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:258:12: ',' arg
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_args950); 
            	    pushFollow(FOLLOW_arg_in_args952);
            	    arg();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop33;
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
    // $ANTLR end "args"


    // $ANTLR start "arg"
    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:260:1: arg : ( ID '=' exprNoComma | exprNoComma | elip= '...' );
    public final void arg() throws RecognitionException {
        CommonToken elip=null;

        try {
            // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:260:5: ( ID '=' exprNoComma | exprNoComma | elip= '...' )
            int alt34=3;
            switch ( input.LA(1) ) {
            case ID:
                {
                int LA34_1 = input.LA(2);

                if ( (LA34_1==EQUALS) ) {
                    alt34=1;
                }
                else if ( ((LA34_1>=COLON && LA34_1<=RPAREN)||(LA34_1>=COMMA && LA34_1<=DOT)) ) {
                    alt34=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 34, 1, input);

                    throw nvae;
                }
                }
                break;
            case SUPER:
            case LPAREN:
            case LBRACK:
            case LCURLY:
            case STRING:
            case AT:
                {
                alt34=2;
                }
                break;
            case ELLIPSIS:
                {
                alt34=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 34, 0, input);

                throw nvae;
            }

            switch (alt34) {
                case 1 :
                    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:260:7: ID '=' exprNoComma
                    {
                    match(input,ID,FOLLOW_ID_in_arg963); 
                    match(input,EQUALS,FOLLOW_EQUALS_in_arg965); 
                    pushFollow(FOLLOW_exprNoComma_in_arg967);
                    exprNoComma();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:261:4: exprNoComma
                    {
                    pushFollow(FOLLOW_exprNoComma_in_arg973);
                    exprNoComma();

                    state._fsp--;


                    }
                    break;
                case 3 :
                    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:262:4: elip= '...'
                    {
                    elip=(CommonToken)match(input,ELLIPSIS,FOLLOW_ELLIPSIS_in_arg988); 

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
    // $ANTLR end "arg"


    // $ANTLR start "templateRef"
    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:265:1: templateRef : ( ID '(' ')' | subtemplate | lp= '(' mapExpr rp= ')' '(' ')' );
    public final void templateRef() throws RecognitionException {
        CommonToken lp=null;
        CommonToken rp=null;

        try {
            // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:271:2: ( ID '(' ')' | subtemplate | lp= '(' mapExpr rp= ')' '(' ')' )
            int alt35=3;
            switch ( input.LA(1) ) {
            case ID:
                {
                alt35=1;
                }
                break;
            case LCURLY:
                {
                alt35=2;
                }
                break;
            case LPAREN:
                {
                alt35=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 35, 0, input);

                throw nvae;
            }

            switch (alt35) {
                case 1 :
                    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:271:4: ID '(' ')'
                    {
                    match(input,ID,FOLLOW_ID_in_templateRef1001); 
                    match(input,LPAREN,FOLLOW_LPAREN_in_templateRef1004); 
                    match(input,RPAREN,FOLLOW_RPAREN_in_templateRef1006); 

                    }
                    break;
                case 2 :
                    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:272:4: subtemplate
                    {
                    pushFollow(FOLLOW_subtemplate_in_templateRef1011);
                    subtemplate();

                    state._fsp--;


                    }
                    break;
                case 3 :
                    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:273:4: lp= '(' mapExpr rp= ')' '(' ')'
                    {
                    lp=(CommonToken)match(input,LPAREN,FOLLOW_LPAREN_in_templateRef1019); 
                    pushFollow(FOLLOW_mapExpr_in_templateRef1021);
                    mapExpr();

                    state._fsp--;

                    rp=(CommonToken)match(input,RPAREN,FOLLOW_RPAREN_in_templateRef1025); 
                    match(input,LPAREN,FOLLOW_LPAREN_in_templateRef1027); 
                    match(input,RPAREN,FOLLOW_RPAREN_in_templateRef1029); 

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
    // $ANTLR end "templateRef"


    // $ANTLR start "list"
    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:276:1: list : ( '[' listElement ( ',' listElement )* ']' | '[' ']' );
    public final void list() throws RecognitionException {
        try {
            // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:276:5: ( '[' listElement ( ',' listElement )* ']' | '[' ']' )
            int alt37=2;
            int LA37_0 = input.LA(1);

            if ( (LA37_0==LBRACK) ) {
                int LA37_1 = input.LA(2);

                if ( (LA37_1==RBRACK) ) {
                    alt37=2;
                }
                else if ( (LA37_1==SUPER||LA37_1==LPAREN||LA37_1==LBRACK||LA37_1==LCURLY||(LA37_1>=ID && LA37_1<=STRING)||LA37_1==AT) ) {
                    alt37=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 37, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 37, 0, input);

                throw nvae;
            }
            switch (alt37) {
                case 1 :
                    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:276:7: '[' listElement ( ',' listElement )* ']'
                    {
                    match(input,LBRACK,FOLLOW_LBRACK_in_list1039); 
                    pushFollow(FOLLOW_listElement_in_list1041);
                    listElement();

                    state._fsp--;

                    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:276:23: ( ',' listElement )*
                    loop36:
                    do {
                        int alt36=2;
                        int LA36_0 = input.LA(1);

                        if ( (LA36_0==COMMA) ) {
                            alt36=1;
                        }


                        switch (alt36) {
                    	case 1 :
                    	    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:276:24: ',' listElement
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_list1044); 
                    	    pushFollow(FOLLOW_listElement_in_list1046);
                    	    listElement();

                    	    state._fsp--;


                    	    }
                    	    break;

                    	default :
                    	    break loop36;
                        }
                    } while (true);

                    match(input,RBRACK,FOLLOW_RBRACK_in_list1050); 

                    }
                    break;
                case 2 :
                    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:277:4: '[' ']'
                    {
                    match(input,LBRACK,FOLLOW_LBRACK_in_list1055); 
                    match(input,RBRACK,FOLLOW_RBRACK_in_list1057); 

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
    // $ANTLR end "list"


    // $ANTLR start "listElement"
    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:280:1: listElement : exprNoComma ;
    public final void listElement() throws RecognitionException {
        try {
            // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:281:5: ( exprNoComma )
            // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/STParser.g:281:9: exprNoComma
            {
            pushFollow(FOLLOW_exprNoComma_in_listElement1073);
            exprNoComma();

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
    // $ANTLR end "listElement"

    // Delegated rules


    protected DFA5 dfa5 = new DFA5(this);
    protected DFA29 dfa29 = new DFA29(this);
    static final String DFA5_eotS =
        "\20\uffff";
    static final String DFA5_eofS =
        "\20\uffff";
    static final String DFA5_minS =
        "\2\26\1\4\3\uffff\1\4\2\uffff\1\10\1\uffff\1\10\1\uffff\2\16\1\uffff";
    static final String DFA5_maxS =
        "\2\40\1\41\3\uffff\1\41\2\uffff\1\31\1\uffff\1\31\1\uffff\2\30\1"+
        "\uffff";
    static final String DFA5_acceptS =
        "\3\uffff\1\5\1\10\1\7\1\uffff\1\4\1\1\1\uffff\1\3\1\uffff\1\2\2"+
        "\uffff\1\6";
    static final String DFA5_specialS =
        "\20\uffff}>";
    static final String[] DFA5_transitionS = {
            "\1\3\1\2\7\uffff\1\1\1\4",
            "\1\7\1\6\10\uffff\1\5",
            "\1\10\3\uffff\1\12\5\uffff\1\12\1\uffff\1\12\3\uffff\1\12\4"+
            "\uffff\2\12\6\uffff\1\11",
            "",
            "",
            "",
            "\1\10\3\uffff\1\14\5\uffff\1\14\1\uffff\1\14\3\uffff\1\14\4"+
            "\uffff\2\14\6\uffff\1\13",
            "",
            "",
            "\1\12\20\uffff\1\15",
            "",
            "\1\14\20\uffff\1\16",
            "",
            "\1\12\11\uffff\1\17",
            "\1\14\11\uffff\1\17",
            ""
    };

    static final short[] DFA5_eot = DFA.unpackEncodedString(DFA5_eotS);
    static final short[] DFA5_eof = DFA.unpackEncodedString(DFA5_eofS);
    static final char[] DFA5_min = DFA.unpackEncodedStringToUnsignedChars(DFA5_minS);
    static final char[] DFA5_max = DFA.unpackEncodedStringToUnsignedChars(DFA5_maxS);
    static final short[] DFA5_accept = DFA.unpackEncodedString(DFA5_acceptS);
    static final short[] DFA5_special = DFA.unpackEncodedString(DFA5_specialS);
    static final short[][] DFA5_transition;

    static {
        int numStates = DFA5_transitionS.length;
        DFA5_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
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
            return "88:1: element : ( (i= INDENT )? ifstat ({...}? NEWLINE )? | i= INDENT exprTag | exprTag | i= INDENT text | text | (i= INDENT )? region | i= INDENT NEWLINE | NEWLINE );";
        }
    }
    static final String DFA29_eotS =
        "\23\uffff";
    static final String DFA29_eofS =
        "\23\uffff";
    static final String DFA29_minS =
        "\1\10\1\11\6\uffff\1\0\12\uffff";
    static final String DFA29_maxS =
        "\1\41\1\36\6\uffff\1\0\12\uffff";
    static final String DFA29_acceptS =
        "\2\uffff\1\2\1\3\1\4\15\uffff\1\1";
    static final String DFA29_specialS =
        "\10\uffff\1\0\12\uffff}>";
    static final String[] DFA29_transitionS = {
            "\1\2\5\uffff\1\4\1\uffff\1\4\3\uffff\1\4\4\uffff\1\1\1\4\6\uffff"+
            "\1\3",
            "\1\4\3\uffff\1\4\1\10\1\4\1\uffff\3\4\4\uffff\1\4\4\uffff\2"+
            "\4",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\uffff",
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

    static final short[] DFA29_eot = DFA.unpackEncodedString(DFA29_eotS);
    static final short[] DFA29_eof = DFA.unpackEncodedString(DFA29_eofS);
    static final char[] DFA29_min = DFA.unpackEncodedStringToUnsignedChars(DFA29_minS);
    static final char[] DFA29_max = DFA.unpackEncodedStringToUnsignedChars(DFA29_maxS);
    static final short[] DFA29_accept = DFA.unpackEncodedString(DFA29_acceptS);
    static final short[] DFA29_special = DFA.unpackEncodedString(DFA29_specialS);
    static final short[][] DFA29_transition;

    static {
        int numStates = DFA29_transitionS.length;
        DFA29_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA29_transition[i] = DFA.unpackEncodedString(DFA29_transitionS[i]);
        }
    }

    class DFA29 extends DFA {

        public DFA29(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 29;
            this.eot = DFA29_eot;
            this.eof = DFA29_eof;
            this.min = DFA29_min;
            this.max = DFA29_max;
            this.accept = DFA29_accept;
            this.special = DFA29_special;
            this.transition = DFA29_transition;
        }
        public String getDescription() {
            return "204:1: callExpr options {k=2; } : ({...}? ID '(' expr ')' | (s= 'super' '.' )? ID '(' ( args )? ')' | '@' (s= 'super' '.' )? ID '(' rp= ')' | primary );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA29_8 = input.LA(1);

                         
                        int index29_8 = input.index();
                        input.rewind();
                        s = -1;
                        if ( ((funcs.contains(input.LT(1).getText()))) ) {s = 18;}

                        else if ( (true) ) {s = 2;}

                         
                        input.seek(index29_8);
                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 29, _s, input);
            error(nvae);
            throw nvae;
        }
    }
 

    public static final BitSet FOLLOW_template_in_templateAndEOF49 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_templateAndEOF51 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_element_in_template62 = new BitSet(new long[]{0x0000000180C00002L});
    public static final BitSet FOLLOW_INDENT_in_element78 = new BitSet(new long[]{0x0000000080800000L});
    public static final BitSet FOLLOW_ifstat_in_element85 = new BitSet(new long[]{0x0000000100000002L});
    public static final BitSet FOLLOW_NEWLINE_in_element96 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INDENT_in_element106 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_exprTag_in_element119 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_exprTag_in_element134 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INDENT_in_element141 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_text_in_element154 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_text_in_element172 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INDENT_in_element182 = new BitSet(new long[]{0x0000000080800000L});
    public static final BitSet FOLLOW_region_in_element186 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INDENT_in_element200 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_NEWLINE_in_element214 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NEWLINE_in_element230 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TEXT_in_text251 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LDELIM_in_exprTag262 = new BitSet(new long[]{0x0000000206114100L});
    public static final BitSet FOLLOW_expr_in_exprTag266 = new BitSet(new long[]{0x0000000001000200L});
    public static final BitSet FOLLOW_SEMI_in_exprTag272 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_exprOptions_in_exprTag274 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_RDELIM_in_exprTag286 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LDELIM_in_region299 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_AT_in_region301 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_ID_in_region303 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_RDELIM_in_region305 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_LDELIM_in_region309 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_END_in_region311 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_RDELIM_in_region313 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LCURLY_in_subtemplate325 = new BitSet(new long[]{0x0000000002200000L});
    public static final BitSet FOLLOW_ID_in_subtemplate331 = new BitSet(new long[]{0x0000000010040000L});
    public static final BitSet FOLLOW_COMMA_in_subtemplate334 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_ID_in_subtemplate338 = new BitSet(new long[]{0x0000000010040000L});
    public static final BitSet FOLLOW_PIPE_in_subtemplate342 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_RCURLY_in_subtemplate347 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_template_in_addTemplateEndTokensToFollowOfTemplateRule362 = new BitSet(new long[]{0x0000000000A00000L});
    public static final BitSet FOLLOW_RCURLY_in_addTemplateEndTokensToFollowOfTemplateRule365 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LDELIM_in_addTemplateEndTokensToFollowOfTemplateRule367 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_END_in_addTemplateEndTokensToFollowOfTemplateRule369 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LDELIM_in_ifstat385 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IF_in_ifstat387 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_LPAREN_in_ifstat389 = new BitSet(new long[]{0x0000000206114500L});
    public static final BitSet FOLLOW_conditional_in_ifstat391 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_RPAREN_in_ifstat393 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_RDELIM_in_ifstat395 = new BitSet(new long[]{0x0000000180C00000L});
    public static final BitSet FOLLOW_template_in_ifstat399 = new BitSet(new long[]{0x0000000080800000L});
    public static final BitSet FOLLOW_INDENT_in_ifstat405 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_LDELIM_in_ifstat408 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_ELSEIF_in_ifstat410 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_LPAREN_in_ifstat415 = new BitSet(new long[]{0x0000000206114500L});
    public static final BitSet FOLLOW_conditional_in_ifstat417 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_RPAREN_in_ifstat419 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_RDELIM_in_ifstat421 = new BitSet(new long[]{0x0000000180C00000L});
    public static final BitSet FOLLOW_template_in_ifstat426 = new BitSet(new long[]{0x0000000080800000L});
    public static final BitSet FOLLOW_INDENT_in_ifstat437 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_LDELIM_in_ifstat440 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ELSE_in_ifstat442 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_RDELIM_in_ifstat444 = new BitSet(new long[]{0x0000000180C00000L});
    public static final BitSet FOLLOW_template_in_ifstat449 = new BitSet(new long[]{0x0000000080800000L});
    public static final BitSet FOLLOW_INDENT_in_ifstat458 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_LDELIM_in_ifstat463 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_ENDIF_in_ifstat465 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_RDELIM_in_ifstat467 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_andConditional_in_conditional483 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_OR_in_conditional486 = new BitSet(new long[]{0x0000000206114500L});
    public static final BitSet FOLLOW_andConditional_in_conditional488 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_notConditional_in_andConditional502 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_AND_in_andConditional505 = new BitSet(new long[]{0x0000000206114500L});
    public static final BitSet FOLLOW_notConditional_in_andConditional507 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_BANG_in_notConditional520 = new BitSet(new long[]{0x0000000206114100L});
    public static final BitSet FOLLOW_memberExpr_in_notConditional522 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_memberExpr_in_notConditional527 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_option_in_exprOptions539 = new BitSet(new long[]{0x0000000000040002L});
    public static final BitSet FOLLOW_COMMA_in_exprOptions542 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_option_in_exprOptions544 = new BitSet(new long[]{0x0000000000040002L});
    public static final BitSet FOLLOW_ID_in_option557 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_EQUALS_in_option561 = new BitSet(new long[]{0x0000000206114100L});
    public static final BitSet FOLLOW_exprNoComma_in_option563 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_memberExpr_in_exprNoComma579 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_COLON_in_exprNoComma585 = new BitSet(new long[]{0x0000000002104000L});
    public static final BitSet FOLLOW_templateRef_in_exprNoComma587 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_mapExpr_in_expr600 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_memberExpr_in_mapExpr615 = new BitSet(new long[]{0x0000000000042002L});
    public static final BitSet FOLLOW_COMMA_in_mapExpr620 = new BitSet(new long[]{0x0000000206114100L});
    public static final BitSet FOLLOW_memberExpr_in_mapExpr622 = new BitSet(new long[]{0x0000000000042002L});
    public static final BitSet FOLLOW_COLON_in_mapExpr633 = new BitSet(new long[]{0x0000000002104000L});
    public static final BitSet FOLLOW_templateRef_in_mapExpr635 = new BitSet(new long[]{0x0000000000042002L});
    public static final BitSet FOLLOW_COMMA_in_mapExpr643 = new BitSet(new long[]{0x0000000002104000L});
    public static final BitSet FOLLOW_templateRef_in_mapExpr645 = new BitSet(new long[]{0x0000000000042002L});
    public static final BitSet FOLLOW_callExpr_in_memberExpr676 = new BitSet(new long[]{0x0000000000080002L});
    public static final BitSet FOLLOW_DOT_in_memberExpr682 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_ID_in_memberExpr684 = new BitSet(new long[]{0x0000000000080002L});
    public static final BitSet FOLLOW_DOT_in_memberExpr692 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_LPAREN_in_memberExpr696 = new BitSet(new long[]{0x0000000206114100L});
    public static final BitSet FOLLOW_mapExpr_in_memberExpr698 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_RPAREN_in_memberExpr702 = new BitSet(new long[]{0x0000000000080002L});
    public static final BitSet FOLLOW_ID_in_callExpr737 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_LPAREN_in_callExpr739 = new BitSet(new long[]{0x0000000206114100L});
    public static final BitSet FOLLOW_expr_in_callExpr741 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_RPAREN_in_callExpr743 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SUPER_in_callExpr769 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_DOT_in_callExpr771 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_ID_in_callExpr775 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_LPAREN_in_callExpr779 = new BitSet(new long[]{0x000000020611C900L});
    public static final BitSet FOLLOW_args_in_callExpr781 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_RPAREN_in_callExpr784 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_AT_in_callExpr805 = new BitSet(new long[]{0x0000000002000100L});
    public static final BitSet FOLLOW_SUPER_in_callExpr810 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_DOT_in_callExpr812 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_ID_in_callExpr816 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_LPAREN_in_callExpr818 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_RPAREN_in_callExpr822 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_primary_in_callExpr845 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_primary858 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_in_primary878 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_subtemplate_in_primary883 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_list_in_primary888 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_primary895 = new BitSet(new long[]{0x0000000206114100L});
    public static final BitSet FOLLOW_expr_in_primary897 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_RPAREN_in_primary901 = new BitSet(new long[]{0x0000000000004002L});
    public static final BitSet FOLLOW_LPAREN_in_primary926 = new BitSet(new long[]{0x000000020611C900L});
    public static final BitSet FOLLOW_args_in_primary928 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_RPAREN_in_primary931 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_arg_in_args947 = new BitSet(new long[]{0x0000000000040002L});
    public static final BitSet FOLLOW_COMMA_in_args950 = new BitSet(new long[]{0x0000000206114900L});
    public static final BitSet FOLLOW_arg_in_args952 = new BitSet(new long[]{0x0000000000040002L});
    public static final BitSet FOLLOW_ID_in_arg963 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_EQUALS_in_arg965 = new BitSet(new long[]{0x0000000206114100L});
    public static final BitSet FOLLOW_exprNoComma_in_arg967 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_exprNoComma_in_arg973 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ELLIPSIS_in_arg988 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_templateRef1001 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_LPAREN_in_templateRef1004 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_RPAREN_in_templateRef1006 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_subtemplate_in_templateRef1011 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_templateRef1019 = new BitSet(new long[]{0x0000000206114100L});
    public static final BitSet FOLLOW_mapExpr_in_templateRef1021 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_RPAREN_in_templateRef1025 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_LPAREN_in_templateRef1027 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_RPAREN_in_templateRef1029 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LBRACK_in_list1039 = new BitSet(new long[]{0x0000000206114100L});
    public static final BitSet FOLLOW_listElement_in_list1041 = new BitSet(new long[]{0x0000000000060000L});
    public static final BitSet FOLLOW_COMMA_in_list1044 = new BitSet(new long[]{0x0000000206114100L});
    public static final BitSet FOLLOW_listElement_in_list1046 = new BitSet(new long[]{0x0000000000060000L});
    public static final BitSet FOLLOW_RBRACK_in_list1050 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LBRACK_in_list1055 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_RBRACK_in_list1057 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_exprNoComma_in_listElement1073 = new BitSet(new long[]{0x0000000000000002L});

}