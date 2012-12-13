/*
 [The "BSD licence"]
 Copyright (c) 2005-2007 Terence Parr
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
/** ANTLR v3 grammar written in ANTLR v3 with AST construction */
grammar ANTLR;

options {
  output       = AST;
  language     = Java5;
  ASTLabelType = CommonTree;
}

tokens {
  DOC_COMMENT;
  PARSER;
  LEXER;
  RULE;
  BLOCK;
  OPTIONAL;
  CLOSURE;
  POSITIVE_CLOSURE;
  SYNPRED;
  RANGE;
  CHAR_RANGE;
  EPSILON;
  ALT;
  EOR;
  EOB;
  EOA; // end of alt
  ID;
  ARG;
  ARGLIST;
  RET;
  LEXER_GRAMMAR;
  PARSER_GRAMMAR;
  TREE_GRAMMAR;
  COMBINED_GRAMMAR;
  INITACTION;
  LABEL; // $x used in rewrite rules
  TEMPLATE;
  SCOPE      = 'scope';
  SEMPRED;
  GATED_SEMPRED; // {p}? =>
  SYN_SEMPRED; // (...) =>   it's a manually-specified synpred converted to sempred
  BACKTRACK_SEMPRED; // auto backtracking mode syn pred converted to sempred
  FRAGMENT   = 'fragment';
  TREE_BEGIN = '^(';
  ROOT       = '^';
  BANG       = '!';
  RANGE      = '..';
  REWRITE    = '->';
  SEMI       = ';';
  COLON      = ':';
  DOT        = '.';
  ASSIGN     = '=';
  ELEMENT_OPTIONS; // TOKEN<options>
}

@lexer::header {
/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 *******************************************************************************/
package org.deved.antlride.internal.core.parser;
}

@header {
/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *******************************************************************************/ 
package org.deved.antlride.internal.core.parser;
}

@members {
  int gtype;
  boolean templateOutput=false;
  
  public void reportError(Exception ex) {
  	emitErrorMessage(ex.getClass().getName() + ": " + ex.getMessage());
  }
}

grammarDef
  :
  DOC_COMMENT?
  (
    'lexer' {gtype=LEXER_GRAMMAR;} // pure lexer
    | 'parser' {gtype=PARSER_GRAMMAR;} // pure parser
    | 'tree' {gtype=TREE_GRAMMAR;} // a tree parser
    | {gtype=COMBINED_GRAMMAR;} // merged parser/lexer
  )
  g='grammar' id ';' optionsSpec? delegateGrammars? tokensSpec? attrScope* action* rule+ EOF
    ->
      ^( {adaptor.create(gtype,$g)} id DOC_COMMENT? optionsSpec? delegateGrammars? tokensSpec? attrScope* action* rule+)
  ;

delegateGrammars
  :
  'import' delegateGrammar (',' delegateGrammar)* ';'
    ->
      ^('import' delegateGrammar+ ';')
  ;

delegateGrammar
  :
  lab=id '=' dg1=id
    ->
      ^('=' $lab $dg1)
  | dg2=id
    -> $dg2
  ;

tokensSpec
  :
  TOKENS tokenSpec* '}'
    ->
      ^(TOKENS tokenSpec* '}')
  ;

tokenSpec
  :
  TOKEN_REF
  (
    '=' (lit=STRING_LITERAL)
      ->
        ^('=' TOKEN_REF $lit)
    |
      -> TOKEN_REF
  )
  ';'
  ;

attrScope
  :
  'scope' id ACTION
    ->
      ^('scope' id ACTION)
  ;

/** Match stuff like @parser::members {int i;} */
action
  :
  '@' (actionScopeName '::')? id ACTION
    ->
      ^('@' actionScopeName? id ACTION)
  ;

/** Sometimes the scope names will collide with keywords; allow them as
 *  ids for action scopes.
 */
actionScopeName
  :
  id
  | l='lexer'
    -> ID[$l]
  | p='parser'
    -> ID[$p]
  ;

optionsSpec
  :
  OPTIONS (option ';')* '}'
    ->
      ^(OPTIONS option* '}')
  ;

option
  :
  id '=' optionValue {
    						if("output".equals($id.text)) {
								templateOutput = "template".equals($optionValue.text);
							}
							}
    ->
      ^('=' id optionValue)
  ;

optionValue
  :
  id
  | STRING_LITERAL
  | INT
  | s='*'
    -> STRING_LITERAL[$s] // used for k=*
  ;

rule
scope {
  String name;
}
  :
  DOC_COMMENT? modifier? id {$rule::name = $id.text;} bang='!'? (arg=ARG_ACTION)? ('returns' rt=ARG_ACTION)? throwsSpec? optionsSpec? ruleScopeSpec? ruleAction* colon=':' altList eor=';'? exceptionGroup?
    ->
      ^(
        RULE DOC_COMMENT? id modifier? {bang==null?null:adaptor.create(bang)}
        ^(ARG $arg)?
        ^(RET $rt)?
        throwsSpec? optionsSpec? ruleScopeSpec? ruleAction* COLON[$colon] altList exceptionGroup? {eor==null?adaptor.create(EOR, ";"):adaptor.create(EOR, eor)}
       )
  ;
catch [RecognitionException re] {
    reportError(re);
    consumeUntil(input, SEMI); // throw away all until ';'
    input.consume(); // eat the ';'
}
catch [Exception ex] {
    reportError(ex);
    consumeUntil(input, SEMI); // throw away all until ';'
    input.consume(); // eat the ';'
}

modifier
  :
  'protected'
  | 'public'
  | 'private'
  | 'fragment'
  ;

/** Match stuff like @init {int i;} */
ruleAction
  :
  '@' id ACTION
    ->
      ^('@' id ACTION)
  ;

throwsSpec
  :
  'throws' id (',' id)*
    ->
      ^('throws' id+)
  ;

ruleScopeSpec
  :
  'scope' ACTION
    ->
      ^('scope' ACTION)
  | 'scope' id (',' id)* ';'
    ->
      ^('scope' id+)
  | 'scope' ACTION 'scope' id (',' id)* ';'
    ->
      ^('scope' ACTION id+)
  ;

block
  :
  lp='(' ( (optionsSpec)? ':')? altpair ('|' altpair)* rp=')'
    ->
      ^(BLOCK[$lp] optionsSpec? altpair+ EOB[$rp])
  ;

altpair
  :
  alternative rewrite
  ;

altList
@init {
  // must create root manually as it's used by invoked rules in real antlr tool.
  // leave here to demonstrate use of {...} in rewrite rule
  // it's really BLOCK[firstToken,"BLOCK"]; set line/col to previous ( or : token.
    CommonTree blkStart = (CommonTree)adaptor.create(BLOCK,input.LT(-1),"BLOCK");
}
  :
  altpair ('|' altpair)*
    ->
      ^( {blkStart} altpair+ {adaptor.create(EOB, input.LT(-1))})
  ;

alternative
@init {
  Token firstToken = input.LT(1);
  Token prevToken = input.LT(-1); // either : or | I think
}
  :
  (element)+
    ->
      ^(ALT[firstToken] element+ {adaptor.create(EOA, input.LT(-1))})
  |
    ->
      ^(ALT[prevToken] EPSILON[prevToken] EOA[prevToken])
  ;

exceptionGroup
  :
  (exceptionHandler)+ (finallyClause)?
  | finallyClause
  ;

exceptionHandler
  :
  'catch' ARG_ACTION ACTION
    ->
      ^('catch' ARG_ACTION ACTION)
  ;

finallyClause
  :
  'finally' ACTION
    ->
      ^('finally' ACTION)
  ;

element
  :
  elementNoOptionSpec
  ;

elementNoOptionSpec
  :
  id
  (
    labelOp='='
    | labelOp='+='
  )
  atom
  (
    startEndStt=ebnfSuffix
      ->
        ^(
          ebnfSuffix
          ^(
            BLOCK[$startEndStt.tree.getToken()]
            ^(
              ALT[$startEndStt.tree.getToken()]
              ^($labelOp id atom)
              EOA[$startEndStt.tree.getToken()]
             )
            EOB[$startEndStt.tree.getToken()]
           )
         )
    |
      ->
        ^($labelOp id atom)
  )
  | id
  (
    labelOp='='
    | labelOp='+='
  )
  block
  (
    startEndStt=ebnfSuffix
      ->
        ^(
          ebnfSuffix
          ^(
            BLOCK[$startEndStt.tree.getToken()]
            ^(
              ALT[$startEndStt.tree.getToken()]
              ^($labelOp id block)
              EOA[$startEndStt.tree.getToken()]
             )
            EOB[$startEndStt.tree.getToken()]
           )
         )
    |
      ->
        ^($labelOp id block)
  )
  | atom
  (
    startEndStt=ebnfSuffix
      ->
        ^(
          ebnfSuffix
          ^(
            BLOCK[$startEndStt.tree.getToken()]
            ^(ALT[$startEndStt.tree.getToken()] atom EOA[$startEndStt.tree.getToken()])
            EOB[$startEndStt.tree.getToken()]
           )
         )
    |
      -> atom
  )
  | ebnf
  | ACTION
  | s=SEMPRED
  (
    '=>'
      -> GATED_SEMPRED[$s]
    |
      -> $s
  )
  | treeSpec
  (
    startEndStt=ebnfSuffix
      ->
        ^(
          ebnfSuffix
          ^(
            BLOCK[$startEndStt.tree.getToken()]
            ^(ALT[$startEndStt.tree.getToken()] treeSpec EOA[$startEndStt.tree.getToken()])
            EOB[$startEndStt.tree.getToken()]
           )
         )
    |
      -> treeSpec
  )
  ;

atom
  :
  range
  (
    (
      op='^'
      | op='!'
    )
      ->
        ^($op range)
    |
      -> range
  )
  | terminal
  | notSet
  (
    (
      op='^'
      | op='!'
    )
      ->
        ^($op notSet)
    |
      -> notSet
  )
  | call
  (
    op='^'
    | op='!'
  )?
    -> {op!=null}?
      ^($op call)
    -> call
  ;

call
  :
  RULE_REF ARG_ACTION?
  ;

notSet
  :
  '~'
  (
    notTerminal
      ->
        ^('~' notTerminal)
    | block
      ->
        ^('~' block)
  )
  ;

treeSpec
  :
  tb='^(' element (element)+ ')'
    ->
      ^(TREE_BEGIN[tb] element+)
  ;

/** Matches ENBF blocks (and token sets via block rule) */
ebnf
@init {
    Token firstToken = input.LT(1);
}
@after {
  Token token = $ebnf.tree.getToken();
  if(token != null) {
	  token.setLine(firstToken.getLine());
	  token.setCharPositionInLine(firstToken.getCharPositionInLine());
  }
}
  :
  block
  (
    op='?'
      ->
        ^(OPTIONAL[op] block)
    | op='*'
      ->
        ^(CLOSURE[op] block)
    | op='+'
      ->
        ^(POSITIVE_CLOSURE[op] block)
    | op='^'
      ->
        ^(ROOT[op] block)
    | op='!'
      ->
        ^(BANG[op] block)
    | op='=>' // syntactic predicate
      -> {gtype==COMBINED_GRAMMAR && Character.isUpperCase($rule::name.charAt(0))}?
        // if lexer rule in combined, leave as pred for lexer
        ^(SYNPRED[op] block)
      // in real antlr tool, text for SYN_SEMPRED is predname
      ->
        ^(SYN_SEMPRED[op] block)
    |
      -> block
  )
  ;

range!
  :
  c1=STRING_LITERAL r=RANGE c2=STRING_LITERAL
    ->
      ^(CHAR_RANGE[$r] $c1 $c2)
  //diff: c1=CHAR_LITERAL RANGE c2=CHAR_LITERAL -> ^(CHAR_RANGE[$c1,".."] $c1 $c2)
  ;

// ---------------
// Generic options
//
// Terminals may be adorned with certain options when
// reference in the grammar: TOK<,,,>
//

elementOptions
  : // Options begin with < and end with >
  //
  '<' elementOption (',' elementOption)* '>'
    ->
      ^(ELEMENT_OPTIONS elementOption+)
  ;

// WHen used with elements we can specify what the tree node type can
// be and also assign settings of various options  (which we do not check here)

elementOption
  : // This format indicates the default node option
  qid
  | // This format indicates option assignment
   id ASSIGN^
  (
    qid
    | STRING_LITERAL
  )
  ;

qid
  :
  id (DOT id)*
    -> ID[$qid.start, $text]
  ;

terminal
  :
  (
    // Args are only valid for lexer rules
    TOKEN_REF elementOptions? ARG_ACTION?
      ->
        ^(TOKEN_REF elementOptions? ARG_ACTION?)
    | STRING_LITERAL elementOptions?
      ->
        ^(STRING_LITERAL elementOptions?)
    | '.'
      -> '.'
  )
  (
    '^'
      ->
        ^('^' $terminal)
    | '!'
      ->
        ^('!' $terminal)
  )?
  ;

notTerminal
  :
  TOKEN_REF
  | STRING_LITERAL
  ;

ebnfSuffix
@init {
  Token op = input.LT(1);
}
  :
  '?'
    -> OPTIONAL[op]
  | '*'
    -> CLOSURE[op]
  | '+'
    -> POSITIVE_CLOSURE[op]
  ;

// R E W R I T E  S Y N T A X

rewrite
  :
  (rew+='->' preds+=SEMPRED predicated+=rewrite_alternative)* rew2='->' last=rewrite_alternative
    ->
      ^($rew $preds $predicated)*
      ^($rew2 $last)
  |
  ;

rewrite_alternative
options {
  backtrack = true;
}
@init {
  Token firstToken = input.LT(-1);
}
  :
  {templateOutput}? rewrite_template
  | rewrite_tree_alternative
  | /* empty rewrite */
    ->
      ^(ALT[firstToken] EPSILON[firstToken] EOA[firstToken])
  ;

rewrite_tree_block
  :
  lp='(' rewrite_tree_alternative rp=')'
    ->
      ^(BLOCK[$lp] rewrite_tree_alternative EOB[$rp])
  ;

rewrite_tree_alternative
@init {
  Token firstToken = input.LT(1);
}
  :
  rewrite_tree_element+
    ->
      ^(ALT[firstToken] rewrite_tree_element+ {adaptor.create(EOA, input.LT(-1))})
  ;

rewrite_tree_element
  :
  rewrite_tree_atom
  | rewrite_tree_atom startEndStt=ebnfSuffix
    ->
      ^(
        ebnfSuffix
        ^(
          BLOCK[$startEndStt.tree.getToken()]
          ^(ALT[$startEndStt.tree.getToken()] rewrite_tree_atom EOA[$startEndStt.tree.getToken()])
          EOB[$startEndStt.tree.getToken()]
         )
       )
  | rewrite_tree
  (
    startEndStt=ebnfSuffix
      ->
        ^(
          ebnfSuffix
          ^(
            BLOCK[$startEndStt.tree.getToken()]
            ^(ALT[$startEndStt.tree.getToken()] rewrite_tree EOA[$startEndStt.tree.getToken()])
            EOB[$startEndStt.tree.getToken()]
           )
         )
    |
      -> rewrite_tree
  )
  | rewrite_tree_ebnf
  ;

rewrite_tree_atom
  :
  TOKEN_REF elementOptions? ARG_ACTION?
    ->
      ^(TOKEN_REF elementOptions? ARG_ACTION?) // for imaginary nodes
  | RULE_REF
  | STRING_LITERAL elementOptions?
    ->
      ^(STRING_LITERAL elementOptions?)
  | '$' id
    -> LABEL[$id.start, $id.text] // reference to a label in a rewrite rule
  | ACTION
  ;

rewrite_tree_ebnf
@init {
    Token firstToken = input.LT(1);
}
@after {
  $rewrite_tree_ebnf.tree.getToken().setLine(firstToken.getLine());
  $rewrite_tree_ebnf.tree.getToken().setCharPositionInLine(firstToken.getCharPositionInLine());
}
  :
  rewrite_tree_block ebnfSuffix
    ->
      ^(ebnfSuffix rewrite_tree_block)
  ;

rewrite_tree
  :
  '^(' rewrite_tree_atom rewrite_tree_element* ')'
    ->
      ^(TREE_BEGIN rewrite_tree_atom rewrite_tree_element*)
  ;

/** Build a tree for a template rewrite:
      ^(TEMPLATE (ID|ACTION) ^(ARGLIST ^(ARG ID ACTION) ...) )
    where ARGLIST is always there even if no args exist.
    ID can be "template" keyword.  If first child is ACTION then it's
    an indirect template ref

    -> foo(a={...}, b={...})
    -> ({string-e})(a={...}, b={...})  // e evaluates to template name
    -> {%{$ID.text}} // create literal template from string (done in ActionTranslator)
  -> {st-expr} // st-expr evaluates to ST
 */
rewrite_template
  : // -> template(a={...},...) "..."    inline template
  id lp='(' rewrite_template_args ')'
  (
    str=DOUBLE_QUOTE_STRING_LITERAL
    | str=DOUBLE_ANGLE_STRING_LITERAL
  )
    ->
      ^(TEMPLATE[$lp,"TEMPLATE"] id rewrite_template_args $str)
  | // -> foo(a={...}, ...)
   rewrite_template_ref
  | // -> ({expr})(a={...}, ...)
   rewrite_indirect_template_head
  | // -> {...}
   ACTION
  ;

/** -> foo(a={...}, ...) */
rewrite_template_ref
  :
  id lp='(' rewrite_template_args ')'
    ->
      ^(TEMPLATE[$lp,"TEMPLATE"] id rewrite_template_args)
  ;

/** -> ({expr})(a={...}, ...) */
rewrite_indirect_template_head
  :
  lp='(' ACTION ')' '(' rewrite_template_args ')'
    ->
      ^(TEMPLATE[$lp,"TEMPLATE"] ACTION rewrite_template_args)
  ;

rewrite_template_args
  :
  rewrite_template_arg (',' rewrite_template_arg)*
    ->
      ^(ARGLIST rewrite_template_arg+)
  |
    -> ARGLIST
  ;

rewrite_template_arg
  :
  id '=' ACTION
    ->
      ^(ARG[$id.start] id ACTION)
  ;

id
  :
  TOKEN_REF
    -> ID[$TOKEN_REF]
  | RULE_REF
    -> ID[$RULE_REF]
  ;

// L E X I C A L   R U L E S

SL_COMMENT
  :
  '//'
  (
    ' $ANTLR ' SRC // src directive
    |
    ~(
      '\r'
      | '\n'
     )*
  )
  '\r'? '\n' {$channel=HIDDEN;}
  ;

ML_COMMENT
  :
  '/*' {if (input.LA(1)=='*') $type=DOC_COMMENT; else $channel=HIDDEN;} .* '*/'
  ;

STRING_LITERAL
  :
  '\'' LITERAL_CHAR* '\''
  ;

fragment
LITERAL_CHAR
  :
  ESC
  |
  ~(
    '\''
    | '\\'
   )
  ;

DOUBLE_QUOTE_STRING_LITERAL
  :
  '"'
  (
    ESC
    |
    ~(
      '\\'
      | '"'
     )
  )*
  '"'
  ;

DOUBLE_ANGLE_STRING_LITERAL
  :
  '<<' .* '>>'
  ;

fragment
ESC
  :
  '\\'
  (
    'n'
    | 'r'
    | 't'
    | 'b'
    | 'f'
    | '"'
    | '\''
    | '\\'
    | '>'
    | 'u' XDIGIT XDIGIT XDIGIT XDIGIT
    | . // unknown, leave as it is
  )
  ;

fragment
XDIGIT
  :
  '0'..'9'
  | 'a'..'f'
  | 'A'..'F'
  ;

INT
  :
  '0'..'9'+
  ;

ARG_ACTION
  :
  NESTED_ARG_ACTION
  ;

fragment
NESTED_ARG_ACTION
  :
  '['
  ( options {greedy=false; k=1;}: NESTED_ARG_ACTION
    | ACTION_STRING_LITERAL
    | ACTION_CHAR_LITERAL
    | .
  )*
  ']'
  //{setText(getText().substring(1, getText().length()-1));}
  ;

ACTION
  :
  NESTED_ACTION ('?' {$type = SEMPRED;})?
  ;

fragment
NESTED_ACTION
  :
  '{'
  ( options {greedy=false; k=2;}: NESTED_ACTION
    | SL_COMMENT
    | ML_COMMENT
    | ACTION_STRING_LITERAL
    | ACTION_CHAR_LITERAL
    | .
  )*
  '}'
  ;

fragment
ACTION_CHAR_LITERAL
  :
  '\''
  (
    ACTION_ESC
    |
    ~(
      '\\'
      | '\''
     )
  )
  '\''
  ;

fragment
ACTION_STRING_LITERAL
  :
  '"'
  (
    ACTION_ESC
    |
    ~(
      '\\'
      | '"'
     )
  )*
  '"'
  ;

fragment
ACTION_ESC
  :
  '\\\''
  | '\\' '"' // ANTLR doesn't like: '\\"'
  | '\\'
  ~(
    '\''
    | '"'
   )
  ;

TOKEN_REF
  :
  'A'..'Z'
  (
    'a'..'z'
    | 'A'..'Z'
    | '_'
    | '0'..'9'
  )*
  ;

RULE_REF
  :
  'a'..'z'
  (
    'a'..'z'
    | 'A'..'Z'
    | '_'
    | '0'..'9'
  )*
  ;

/** Match the start of an options section.  Don't allow normal
 *  action processing on the {...} as it's not a action.
 */
OPTIONS
  :
  'options' WS_LOOP '{'
  ;

TOKENS
  :
  'tokens' WS_LOOP '{'
  ;

/** Reset the file and line information; useful when the grammar
 *  has been generated so that errors are shown relative to the
 *  original file like the old C preprocessor used to do.
 */
fragment
SRC
  :
  'src' ' ' ACTION_STRING_LITERAL ' ' INT
  ;

WS
  :
  (
    ' '
    | '\t'
    | '\r'? '\n'
  )+
  {$channel=HIDDEN;}
  ;

fragment
WS_LOOP
  :
  (
    WS
    | SL_COMMENT
    | ML_COMMENT
  )*
  ;
