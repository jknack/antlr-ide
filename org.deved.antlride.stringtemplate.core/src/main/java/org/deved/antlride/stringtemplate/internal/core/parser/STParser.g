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

/** Recognize a single StringTemplate template text, expressions, and conditionals */
parser grammar STParser;

options {
	tokenVocab=STLexer;
	TokenLabelType = CommonToken;
}

@header {
package org.deved.antlride.stringtemplate.internal.core.parser;

import org.deved.antlride.stringtemplate.core.model.dltk.ast.DASTStringTemplate;
import java.util.Set;
import java.util.HashSet;
}

@members {
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

}

templateAndEOF
	:	template EOF
	;

template
	:	element*
	;

element
	:	( i=INDENT )?
		ifstat
		// kill \n for <endif> on line by itself if multi-line IF
		( {$ifstat.start.getLine()!=input.LT(1).getLine()}? NEWLINE )?
	|	i=INDENT       	 
		exprTag          
	|	exprTag
	|	i=INDENT         
		text             
	|	text
	|   (i=INDENT)? region						 
	|	i=INDENT         
	 	NEWLINE           
	|	NEWLINE          
	;

text
	:	TEXT
	;

exprTag
	:	LDELIM
		expr
		(	';' exprOptions
		|
		)
		RDELIM
	;

/**match $@foo$...$@end$*/
region
	:	LDELIM '@' ID RDELIM
		LDELIM '@end' RDELIM
	;
	
subtemplate
	:	'{' ( ids+=ID (',' ids+=ID)* '|' )? '}'
    ;

/** The (...)* loop in rule template doesn't think '}' can follow it because
 *  we call template in an action (via compileAnonTemplate).  To avoid
 *  syntax errors upon '}' in rule templatee, we force '}' into FOLLOW set.
 *  I hope to make ANTLR ignore FOLLOW set for (...)* in future.
 */
addTemplateEndTokensToFollowOfTemplateRule : template ('}'|LDELIM '@end') ;

ifstat
@init {
    /** Tracks address of branch operand (in code block).  It's how
     *  we backpatch forward references when generating code for IFs.
     */
    int prevBranchOperand = -1;
    /** Branch instruction operands that are forward refs to end of IF.
     *  We need to update them once we see the endif.
     */
    List<Integer> endRefs = new ArrayList<Integer>();
}
	:	LDELIM 'if' '(' conditional ')' RDELIM
		template
		(	INDENT? LDELIM 'elseif'
			'(' conditional ')' RDELIM
			template
		)*
		(	INDENT? LDELIM 'else' RDELIM
			template
		)?
		INDENT? endif=LDELIM 'endif' RDELIM
		//( {true}? NEWLINE )? // kill \on for <endif> on line by itself
	;
		
conditional
	:	andConditional ('||' andConditional)*
	;
	
andConditional
	:	notConditional ('&&' notConditional)*
	;

notConditional
	:	'!' memberExpr
	|	memberExpr
	;
	
exprOptions
	:	option (',' option)*
	;

option
	:	ID ( '=' exprNoComma | )
	;
	
exprNoComma
	:	memberExpr
		(	':' templateRef )?
	;

expr : mapExpr ;

mapExpr
@init {int nt=1, ne=1; int a=$start.getStartIndex();}
	:	memberExpr (c=',' memberExpr {ne++;} )*
		(	':' templateRef
			(	(',' templateRef {nt++;})+
			|	
			)
		)*
	;

memberExpr
	:	callExpr
		(	'.' ID {statements.get(statements.size()-1).appendName(text($ID));}
		|	'.' lp='(' mapExpr rp=')'						
		)*
	;
	
callExpr
options {k=2;} // prevent full LL(*), which fails, falling back on k=1; need k=2
	:	{funcs.contains(input.LT(1).getText())}?
		ID '(' expr ')' 
		             {
		              String text = text($ID);
		              int start = start($ID);
		              int end = end($ID);
		              int declStart = start;
		              int declEnd = end;
		              statements.add(new DASTStringTemplate(text, start, end, declStart, declEnd));
		              funcs.add(text);
		             }
	|	(s='super' '.')? ID
		'(' args? ')'
		            {
                  String text = text($ID);
                  int start = start($ID);
                  int end = end($ID);
                  int declStart = start;
                  int declEnd = end;
                  statements.add(new DASTStringTemplate(text, start, end, declStart, declEnd));
                 }
	|	'@' (s='super' '.')? ID '(' rp=')'	// convert <@r()> to <region__enclosingTemplate__r()>
	             {
                  String text =  text($ID);
                  int start = start($ID);
                  int end = end($ID);
                  int declStart = start;
                  int declEnd = end;
                  statements.add(new DASTStringTemplate(text, start, end, declStart, declEnd));
                 }
  |	primary
	;

primary
	:	o=ID
	            {
                  String text =  text($o);
                  int start = start($o);
                  int end = end($o);
                  int declStart = start;
                  int declEnd = end;
                  statements.add(new DASTStringTemplate(text, start, end, declStart, declEnd));
                 }
	|	STRING
	|	subtemplate
	|	list
	|	lp='(' expr rp=')'
		(	               
			'(' args? ')' // indirect call
		)? 
	;

args:	arg (',' arg)* ;

arg :	ID '=' exprNoComma 
	|	exprNoComma        
	|	elip='...'
	;

/**
expr:template()      apply template to expr
expr:{arg | ...}     apply subtemplate to expr
expr:(e)()           convert e to a string template name and apply to expr
*/
templateRef
	:	ID  '(' ')'
	|	subtemplate 
	|	lp='(' mapExpr rp=')' '(' ')'
	;
	
list: '[' listElement (',' listElement)* ']'
	|	'[' ']'
	;

listElement
    :   exprNoComma
    ;
    