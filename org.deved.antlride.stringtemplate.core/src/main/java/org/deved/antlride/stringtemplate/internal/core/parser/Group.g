/*
 [The "BSD licence"]
 Copyright (c) 2009 Terence Parr
 All rights reserved.

 Redistribution and use in source and binary forms, with or without
 modification, are permitted provided that the following conditions
 are met:
 1. Redistributions of source code must retain the above copyright
    notice, this list of conditions and the following disclaimer.
 2. Redistributions in binary form must reproduce the above copyright
    notice, this list of conditions and the following disclaimer in the
    documentation and/or other materials provided with the distribution.
 3. The name of the author may not be used to endorse or promote products
    derived from this software without specific prior written permission.

 THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
 IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
 INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
grammar Group;

options {
  language = Java5;
}

@header {
package org.deved.antlride.stringtemplate.internal.core.parser;

import org.deved.antlride.stringtemplate.core.model.dltk.ast.DASTStringTemplate;
}

@lexer::header {
package org.deved.antlride.stringtemplate.internal.core.parser;
}

@members {
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
}

group
  :
  'group' ID ('implements' ID)? ';' def+
  ;

/** Match template and dictionary defs outside of (...)+ loop in group.
 *  The key is catching while still in the loop; must keep prediction of
 *  elements separate from "stay in loop" prediction.
 */
def
  :
  templateDef
  | dictDef
  ;

templateDef
  :
  (
    (
      '@' prefix=ID '.' name=ID '(' ')'
      | name=ID '(' formalArgs? ')'
    )
    '::='
    (
      body=STRING
      | body=BIGSTRING
      |
    )
    | name=ID '::=' body=ID
  )
  
  {
   String templateName = prefix == null ? "" : text(prefix) + ".";
   templateName += text(name);
   int start = prefix == null ? start(name) : start(prefix);
   int end = end(name);
   int declStart = body == null ? start : start(body);
   int declEnd = body == null ? end : end(body);
   statements.add(new DASTStringTemplate(templateName, start, end, declStart,
   		declEnd));
  }
  ;

formalArgs
  :
  formalArg (',' formalArg)*
  ;

formalArg
  :
  ID
  (
    '=' STRING
    | '=' ANONYMOUS_TEMPLATE
  )?
  ;
/*
suffix returns [int cardinality=FormalArgument.REQUIRED]
    :   OPTIONAL 
    |   STAR     
    |   PLUS     
	|
    ;
        */


dictDef
@after {
  String text = text($id);
  int start = start($id);
  int end = end($id);
  int declStart = end($assign);
  int declEnd = end($stop);
  statements.add(new DASTStringTemplate(text, start, end, declStart, declEnd));
}
  :
  id=ID assign='::=' dict
  ;

dict
  :
  '[' dictPairs ']'
  ;

dictPairs
  :
  keyValuePair (',' keyValuePair)* (',' defaultValuePair)?
  | defaultValuePair
  ;

defaultValuePair
  :
  'default' ':' keyValue
  ;

keyValuePair
  :
  STRING ':' keyValue
  ;

keyValue
  :
  BIGSTRING
  | ANONYMOUS_TEMPLATE
  | STRING
  | {input.LT(1).getText().equals("key")}?=> ID
  ;

ID
  :
  (
    'a'..'z'
    | 'A'..'Z'
    | '_'
  )
  (
    'a'..'z'
    | 'A'..'Z'
    | '0'..'9'
    | '-'
    | '_'
  )*
  ;

STRING
  :
  '"'
  (
    '\\' '"'
    | '\\' ~'"'
    | '\n'
    |
    ~(
      '\\'
      | '"'
      | '\n'
     )
  )*
  '"'
  ;

BIGSTRING
  :
  '<<'
  ( options {greedy=false;}: '\\' '>' // \> escape
    | '\\' ~'>'
    | ~'\\'
  )*
  '>>'
  ;

ANONYMOUS_TEMPLATE
  :
  '{' (options {greedy=false;}: .)* '}'
  ;

COMMENT
  :
  '/*' (options {greedy=false;}: .)* '*/' 
                                         {
                                          skip();
                                         }
  ;

LINE_COMMENT
  :
  '//'
  ~(
    '\n'
    | '\r'
   )*
  '\r'? '\n' 
            {
             skip();
            }
  ;

WS
  :
  (
    ' '
    | '\r'
    | '\t'
    | '\n'
  )
  
  {
   skip();
  }
  ;
