// $ANTLR 3.2 Sep 23, 2009 12:02:23 /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/Group.g 2010-05-30 16:40:25

package org.deved.antlride.stringtemplate.internal.core.parser;

import org.deved.antlride.stringtemplate.core.model.dltk.ast.DASTStringTemplate;


import org.antlr.runtime.*;
import java.util.List;
import java.util.Stack;
import java.util.ArrayList;
@SuppressWarnings({"unused", "unchecked"})
public class GroupParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "ID", "STRING", "BIGSTRING", "ANONYMOUS_TEMPLATE", "COMMENT", "LINE_COMMENT", "WS", "'group'", "'implements'", "';'", "'@'", "'.'", "'('", "')'", "'::='", "','", "'='", "'['", "']'", "'default'", "':'"
    };
    public static final int LINE_COMMENT=9;
    public static final int T__24=24;
    public static final int T__23=23;
    public static final int T__22=22;
    public static final int T__21=21;
    public static final int T__20=20;
    public static final int ANONYMOUS_TEMPLATE=7;
    public static final int ID=4;
    public static final int EOF=-1;
    public static final int T__19=19;
    public static final int WS=10;
    public static final int T__16=16;
    public static final int T__15=15;
    public static final int T__18=18;
    public static final int T__17=17;
    public static final int T__12=12;
    public static final int T__11=11;
    public static final int T__14=14;
    public static final int T__13=13;
    public static final int BIGSTRING=6;
    public static final int COMMENT=8;
    public static final int STRING=5;

    // delegates
    // delegators


        public GroupParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public GroupParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return GroupParser.tokenNames; }
    public String getGrammarFileName() { return "/media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/Group.g"; }


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



    // $ANTLR start "group"
    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/Group.g:75:1: group : 'group' ID ( 'implements' ID )? ';' ( def )+ ;
    public final void group() throws RecognitionException {
        try {
            // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/Group.g:76:3: ( 'group' ID ( 'implements' ID )? ';' ( def )+ )
            // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/Group.g:77:3: 'group' ID ( 'implements' ID )? ';' ( def )+
            {
            match(input,11,FOLLOW_11_in_group51); 
            match(input,ID,FOLLOW_ID_in_group53); 
            // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/Group.g:77:14: ( 'implements' ID )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==12) ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/Group.g:77:15: 'implements' ID
                    {
                    match(input,12,FOLLOW_12_in_group56); 
                    match(input,ID,FOLLOW_ID_in_group58); 

                    }
                    break;

            }

            match(input,13,FOLLOW_13_in_group62); 
            // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/Group.g:77:37: ( def )+
            int cnt2=0;
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==ID||LA2_0==14) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/Group.g:77:37: def
            	    {
            	    pushFollow(FOLLOW_def_in_group64);
            	    def();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    if ( cnt2 >= 1 ) break loop2;
                        EarlyExitException eee =
                            new EarlyExitException(2, input);
                        throw eee;
                }
                cnt2++;
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
    // $ANTLR end "group"


    // $ANTLR start "def"
    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/Group.g:80:1: def : ( templateDef | dictDef );
    public final void def() throws RecognitionException {
        try {
            // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/Group.g:85:3: ( templateDef | dictDef )
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==14) ) {
                alt3=1;
            }
            else if ( (LA3_0==ID) ) {
                int LA3_2 = input.LA(2);

                if ( (LA3_2==16) ) {
                    alt3=1;
                }
                else if ( (LA3_2==18) ) {
                    int LA3_3 = input.LA(3);

                    if ( (LA3_3==ID) ) {
                        alt3=1;
                    }
                    else if ( (LA3_3==21) ) {
                        alt3=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 3, 3, input);

                        throw nvae;
                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 3, 2, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;
            }
            switch (alt3) {
                case 1 :
                    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/Group.g:86:3: templateDef
                    {
                    pushFollow(FOLLOW_templateDef_in_def82);
                    templateDef();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/Group.g:87:5: dictDef
                    {
                    pushFollow(FOLLOW_dictDef_in_def88);
                    dictDef();

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
    // $ANTLR end "def"


    // $ANTLR start "templateDef"
    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/Group.g:90:1: templateDef : ( ( '@' prefix= ID '.' name= ID '(' ')' | name= ID '(' ( formalArgs )? ')' ) '::=' (body= STRING | body= BIGSTRING | ) | name= ID '::=' body= ID ) ;
    public final void templateDef() throws RecognitionException {
        Token prefix=null;
        Token name=null;
        Token body=null;

        try {
            // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/Group.g:91:3: ( ( ( '@' prefix= ID '.' name= ID '(' ')' | name= ID '(' ( formalArgs )? ')' ) '::=' (body= STRING | body= BIGSTRING | ) | name= ID '::=' body= ID ) )
            // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/Group.g:92:3: ( ( '@' prefix= ID '.' name= ID '(' ')' | name= ID '(' ( formalArgs )? ')' ) '::=' (body= STRING | body= BIGSTRING | ) | name= ID '::=' body= ID )
            {
            // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/Group.g:92:3: ( ( '@' prefix= ID '.' name= ID '(' ')' | name= ID '(' ( formalArgs )? ')' ) '::=' (body= STRING | body= BIGSTRING | ) | name= ID '::=' body= ID )
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==14) ) {
                alt7=1;
            }
            else if ( (LA7_0==ID) ) {
                int LA7_2 = input.LA(2);

                if ( (LA7_2==16) ) {
                    alt7=1;
                }
                else if ( (LA7_2==18) ) {
                    alt7=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 7, 2, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;
            }
            switch (alt7) {
                case 1 :
                    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/Group.g:93:5: ( '@' prefix= ID '.' name= ID '(' ')' | name= ID '(' ( formalArgs )? ')' ) '::=' (body= STRING | body= BIGSTRING | )
                    {
                    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/Group.g:93:5: ( '@' prefix= ID '.' name= ID '(' ')' | name= ID '(' ( formalArgs )? ')' )
                    int alt5=2;
                    int LA5_0 = input.LA(1);

                    if ( (LA5_0==14) ) {
                        alt5=1;
                    }
                    else if ( (LA5_0==ID) ) {
                        alt5=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 5, 0, input);

                        throw nvae;
                    }
                    switch (alt5) {
                        case 1 :
                            // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/Group.g:94:7: '@' prefix= ID '.' name= ID '(' ')'
                            {
                            match(input,14,FOLLOW_14_in_templateDef117); 
                            prefix=(Token)match(input,ID,FOLLOW_ID_in_templateDef121); 
                            match(input,15,FOLLOW_15_in_templateDef123); 
                            name=(Token)match(input,ID,FOLLOW_ID_in_templateDef127); 
                            match(input,16,FOLLOW_16_in_templateDef129); 
                            match(input,17,FOLLOW_17_in_templateDef131); 

                            }
                            break;
                        case 2 :
                            // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/Group.g:95:9: name= ID '(' ( formalArgs )? ')'
                            {
                            name=(Token)match(input,ID,FOLLOW_ID_in_templateDef143); 
                            match(input,16,FOLLOW_16_in_templateDef145); 
                            // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/Group.g:95:21: ( formalArgs )?
                            int alt4=2;
                            int LA4_0 = input.LA(1);

                            if ( (LA4_0==ID) ) {
                                alt4=1;
                            }
                            switch (alt4) {
                                case 1 :
                                    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/Group.g:95:21: formalArgs
                                    {
                                    pushFollow(FOLLOW_formalArgs_in_templateDef147);
                                    formalArgs();

                                    state._fsp--;


                                    }
                                    break;

                            }

                            match(input,17,FOLLOW_17_in_templateDef150); 

                            }
                            break;

                    }

                    match(input,18,FOLLOW_18_in_templateDef162); 
                    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/Group.g:98:5: (body= STRING | body= BIGSTRING | )
                    int alt6=3;
                    switch ( input.LA(1) ) {
                    case STRING:
                        {
                        alt6=1;
                        }
                        break;
                    case BIGSTRING:
                        {
                        alt6=2;
                        }
                        break;
                    case EOF:
                    case ID:
                    case 14:
                        {
                        alt6=3;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 6, 0, input);

                        throw nvae;
                    }

                    switch (alt6) {
                        case 1 :
                            // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/Group.g:99:7: body= STRING
                            {
                            body=(Token)match(input,STRING,FOLLOW_STRING_in_templateDef178); 

                            }
                            break;
                        case 2 :
                            // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/Group.g:100:9: body= BIGSTRING
                            {
                            body=(Token)match(input,BIGSTRING,FOLLOW_BIGSTRING_in_templateDef190); 

                            }
                            break;
                        case 3 :
                            // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/Group.g:102:5: 
                            {
                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/Group.g:103:7: name= ID '::=' body= ID
                    {
                    name=(Token)match(input,ID,FOLLOW_ID_in_templateDef214); 
                    match(input,18,FOLLOW_18_in_templateDef216); 
                    body=(Token)match(input,ID,FOLLOW_ID_in_templateDef220); 

                    }
                    break;

            }


               String templateName = prefix == null ? "" : text(prefix) + ".";
               templateName += text(name);
               int start = prefix == null ? start(name) : start(prefix);
               int end = end(name);
               int declStart = body == null ? start : start(body);
               int declEnd = body == null ? end : end(body);
               statements.add(new DASTStringTemplate(templateName, start, end, declStart,
               		declEnd));
              

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
    // $ANTLR end "templateDef"


    // $ANTLR start "formalArgs"
    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/Group.g:118:1: formalArgs : formalArg ( ',' formalArg )* ;
    public final void formalArgs() throws RecognitionException {
        try {
            // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/Group.g:119:3: ( formalArg ( ',' formalArg )* )
            // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/Group.g:120:3: formalArg ( ',' formalArg )*
            {
            pushFollow(FOLLOW_formalArg_in_formalArgs246);
            formalArg();

            state._fsp--;

            // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/Group.g:120:13: ( ',' formalArg )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0==19) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/Group.g:120:14: ',' formalArg
            	    {
            	    match(input,19,FOLLOW_19_in_formalArgs249); 
            	    pushFollow(FOLLOW_formalArg_in_formalArgs251);
            	    formalArg();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop8;
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
    // $ANTLR end "formalArgs"


    // $ANTLR start "formalArg"
    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/Group.g:123:1: formalArg : ID ( '=' STRING | '=' ANONYMOUS_TEMPLATE )? ;
    public final void formalArg() throws RecognitionException {
        try {
            // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/Group.g:124:3: ( ID ( '=' STRING | '=' ANONYMOUS_TEMPLATE )? )
            // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/Group.g:125:3: ID ( '=' STRING | '=' ANONYMOUS_TEMPLATE )?
            {
            match(input,ID,FOLLOW_ID_in_formalArg268); 
            // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/Group.g:126:3: ( '=' STRING | '=' ANONYMOUS_TEMPLATE )?
            int alt9=3;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==20) ) {
                int LA9_1 = input.LA(2);

                if ( (LA9_1==STRING) ) {
                    alt9=1;
                }
                else if ( (LA9_1==ANONYMOUS_TEMPLATE) ) {
                    alt9=2;
                }
            }
            switch (alt9) {
                case 1 :
                    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/Group.g:127:5: '=' STRING
                    {
                    match(input,20,FOLLOW_20_in_formalArg278); 
                    match(input,STRING,FOLLOW_STRING_in_formalArg280); 

                    }
                    break;
                case 2 :
                    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/Group.g:128:7: '=' ANONYMOUS_TEMPLATE
                    {
                    match(input,20,FOLLOW_20_in_formalArg288); 
                    match(input,ANONYMOUS_TEMPLATE,FOLLOW_ANONYMOUS_TEMPLATE_in_formalArg290); 

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
    // $ANTLR end "formalArg"

    public static class dictDef_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "dictDef"
    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/Group.g:141:1: dictDef : id= ID assign= '::=' dict ;
    public final GroupParser.dictDef_return dictDef() throws RecognitionException {
        GroupParser.dictDef_return retval = new GroupParser.dictDef_return();
        retval.start = input.LT(1);

        Token id=null;
        Token assign=null;

        try {
            // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/Group.g:150:3: (id= ID assign= '::=' dict )
            // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/Group.g:151:3: id= ID assign= '::=' dict
            {
            id=(Token)match(input,ID,FOLLOW_ID_in_dictDef320); 
            assign=(Token)match(input,18,FOLLOW_18_in_dictDef324); 
            pushFollow(FOLLOW_dict_in_dictDef326);
            dict();

            state._fsp--;


            }

            retval.stop = input.LT(-1);


              String text = text(id);
              int start = start(id);
              int end = end(id);
              int declStart = end(assign);
              int declEnd = end(((Token)retval.stop));
              statements.add(new DASTStringTemplate(text, start, end, declStart, declEnd));

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "dictDef"


    // $ANTLR start "dict"
    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/Group.g:154:1: dict : '[' dictPairs ']' ;
    public final void dict() throws RecognitionException {
        try {
            // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/Group.g:155:3: ( '[' dictPairs ']' )
            // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/Group.g:156:3: '[' dictPairs ']'
            {
            match(input,21,FOLLOW_21_in_dict341); 
            pushFollow(FOLLOW_dictPairs_in_dict343);
            dictPairs();

            state._fsp--;

            match(input,22,FOLLOW_22_in_dict345); 

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
    // $ANTLR end "dict"


    // $ANTLR start "dictPairs"
    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/Group.g:159:1: dictPairs : ( keyValuePair ( ',' keyValuePair )* ( ',' defaultValuePair )? | defaultValuePair );
    public final void dictPairs() throws RecognitionException {
        try {
            // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/Group.g:160:3: ( keyValuePair ( ',' keyValuePair )* ( ',' defaultValuePair )? | defaultValuePair )
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==STRING) ) {
                alt12=1;
            }
            else if ( (LA12_0==23) ) {
                alt12=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 12, 0, input);

                throw nvae;
            }
            switch (alt12) {
                case 1 :
                    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/Group.g:161:3: keyValuePair ( ',' keyValuePair )* ( ',' defaultValuePair )?
                    {
                    pushFollow(FOLLOW_keyValuePair_in_dictPairs360);
                    keyValuePair();

                    state._fsp--;

                    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/Group.g:161:16: ( ',' keyValuePair )*
                    loop10:
                    do {
                        int alt10=2;
                        int LA10_0 = input.LA(1);

                        if ( (LA10_0==19) ) {
                            int LA10_1 = input.LA(2);

                            if ( (LA10_1==STRING) ) {
                                alt10=1;
                            }


                        }


                        switch (alt10) {
                    	case 1 :
                    	    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/Group.g:161:17: ',' keyValuePair
                    	    {
                    	    match(input,19,FOLLOW_19_in_dictPairs363); 
                    	    pushFollow(FOLLOW_keyValuePair_in_dictPairs365);
                    	    keyValuePair();

                    	    state._fsp--;


                    	    }
                    	    break;

                    	default :
                    	    break loop10;
                        }
                    } while (true);

                    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/Group.g:161:36: ( ',' defaultValuePair )?
                    int alt11=2;
                    int LA11_0 = input.LA(1);

                    if ( (LA11_0==19) ) {
                        alt11=1;
                    }
                    switch (alt11) {
                        case 1 :
                            // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/Group.g:161:37: ',' defaultValuePair
                            {
                            match(input,19,FOLLOW_19_in_dictPairs370); 
                            pushFollow(FOLLOW_defaultValuePair_in_dictPairs372);
                            defaultValuePair();

                            state._fsp--;


                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/Group.g:162:5: defaultValuePair
                    {
                    pushFollow(FOLLOW_defaultValuePair_in_dictPairs380);
                    defaultValuePair();

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
    // $ANTLR end "dictPairs"


    // $ANTLR start "defaultValuePair"
    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/Group.g:165:1: defaultValuePair : 'default' ':' keyValue ;
    public final void defaultValuePair() throws RecognitionException {
        try {
            // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/Group.g:166:3: ( 'default' ':' keyValue )
            // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/Group.g:167:3: 'default' ':' keyValue
            {
            match(input,23,FOLLOW_23_in_defaultValuePair395); 
            match(input,24,FOLLOW_24_in_defaultValuePair397); 
            pushFollow(FOLLOW_keyValue_in_defaultValuePair399);
            keyValue();

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
    // $ANTLR end "defaultValuePair"


    // $ANTLR start "keyValuePair"
    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/Group.g:170:1: keyValuePair : STRING ':' keyValue ;
    public final void keyValuePair() throws RecognitionException {
        try {
            // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/Group.g:171:3: ( STRING ':' keyValue )
            // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/Group.g:172:3: STRING ':' keyValue
            {
            match(input,STRING,FOLLOW_STRING_in_keyValuePair414); 
            match(input,24,FOLLOW_24_in_keyValuePair416); 
            pushFollow(FOLLOW_keyValue_in_keyValuePair418);
            keyValue();

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
    // $ANTLR end "keyValuePair"


    // $ANTLR start "keyValue"
    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/Group.g:175:1: keyValue : ( BIGSTRING | ANONYMOUS_TEMPLATE | STRING | {...}? => ID );
    public final void keyValue() throws RecognitionException {
        try {
            // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/Group.g:176:3: ( BIGSTRING | ANONYMOUS_TEMPLATE | STRING | {...}? => ID )
            int alt13=4;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==BIGSTRING) ) {
                alt13=1;
            }
            else if ( (LA13_0==ANONYMOUS_TEMPLATE) ) {
                alt13=2;
            }
            else if ( (LA13_0==STRING) ) {
                alt13=3;
            }
            else if ( (LA13_0==ID) && ((input.LT(1).getText().equals("key")))) {
                alt13=4;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 13, 0, input);

                throw nvae;
            }
            switch (alt13) {
                case 1 :
                    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/Group.g:177:3: BIGSTRING
                    {
                    match(input,BIGSTRING,FOLLOW_BIGSTRING_in_keyValue433); 

                    }
                    break;
                case 2 :
                    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/Group.g:178:5: ANONYMOUS_TEMPLATE
                    {
                    match(input,ANONYMOUS_TEMPLATE,FOLLOW_ANONYMOUS_TEMPLATE_in_keyValue439); 

                    }
                    break;
                case 3 :
                    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/Group.g:179:5: STRING
                    {
                    match(input,STRING,FOLLOW_STRING_in_keyValue445); 

                    }
                    break;
                case 4 :
                    // /media/dev/workspaces/antlride/org.deved.antlride.stringtemplate.core/src/main/java/org/deved/antlride/stringtemplate/internal/core/parser/Group.g:180:5: {...}? => ID
                    {
                    if ( !((input.LT(1).getText().equals("key"))) ) {
                        throw new FailedPredicateException(input, "keyValue", "input.LT(1).getText().equals(\"key\")");
                    }
                    match(input,ID,FOLLOW_ID_in_keyValue454); 

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
    // $ANTLR end "keyValue"

    // Delegated rules


 

    public static final BitSet FOLLOW_11_in_group51 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ID_in_group53 = new BitSet(new long[]{0x0000000000003000L});
    public static final BitSet FOLLOW_12_in_group56 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ID_in_group58 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_group62 = new BitSet(new long[]{0x0000000000004010L});
    public static final BitSet FOLLOW_def_in_group64 = new BitSet(new long[]{0x0000000000004012L});
    public static final BitSet FOLLOW_templateDef_in_def82 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_dictDef_in_def88 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_14_in_templateDef117 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ID_in_templateDef121 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_templateDef123 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ID_in_templateDef127 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_templateDef129 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_templateDef131 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_ID_in_templateDef143 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_templateDef145 = new BitSet(new long[]{0x0000000000020010L});
    public static final BitSet FOLLOW_formalArgs_in_templateDef147 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_templateDef150 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_18_in_templateDef162 = new BitSet(new long[]{0x0000000000000062L});
    public static final BitSet FOLLOW_STRING_in_templateDef178 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BIGSTRING_in_templateDef190 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_templateDef214 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_18_in_templateDef216 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ID_in_templateDef220 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_formalArg_in_formalArgs246 = new BitSet(new long[]{0x0000000000080002L});
    public static final BitSet FOLLOW_19_in_formalArgs249 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_formalArg_in_formalArgs251 = new BitSet(new long[]{0x0000000000080002L});
    public static final BitSet FOLLOW_ID_in_formalArg268 = new BitSet(new long[]{0x0000000000100002L});
    public static final BitSet FOLLOW_20_in_formalArg278 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_STRING_in_formalArg280 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_20_in_formalArg288 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_ANONYMOUS_TEMPLATE_in_formalArg290 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_dictDef320 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_18_in_dictDef324 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_dict_in_dictDef326 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_21_in_dict341 = new BitSet(new long[]{0x0000000000800020L});
    public static final BitSet FOLLOW_dictPairs_in_dict343 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_22_in_dict345 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_keyValuePair_in_dictPairs360 = new BitSet(new long[]{0x0000000000080002L});
    public static final BitSet FOLLOW_19_in_dictPairs363 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_keyValuePair_in_dictPairs365 = new BitSet(new long[]{0x0000000000080002L});
    public static final BitSet FOLLOW_19_in_dictPairs370 = new BitSet(new long[]{0x0000000000800020L});
    public static final BitSet FOLLOW_defaultValuePair_in_dictPairs372 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_defaultValuePair_in_dictPairs380 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_23_in_defaultValuePair395 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_24_in_defaultValuePair397 = new BitSet(new long[]{0x00000000000000F0L});
    public static final BitSet FOLLOW_keyValue_in_defaultValuePair399 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_in_keyValuePair414 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_24_in_keyValuePair416 = new BitSet(new long[]{0x00000000000000F0L});
    public static final BitSet FOLLOW_keyValue_in_keyValuePair418 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BIGSTRING_in_keyValue433 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ANONYMOUS_TEMPLATE_in_keyValue439 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_in_keyValue445 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_keyValue454 = new BitSet(new long[]{0x0000000000000002L});

}