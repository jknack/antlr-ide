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
/** ANTLR v3 tree grammar to walk trees created by ANTLRv3.g */
tree grammar ANTLRWalker;

options {
  tokenVocab   = ANTLR;
  ASTLabelType = CommonTree;
  superClass   = BaseTreeParser;
  language     = Java5;
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

import org.deved.antlride.core.model.*;
import org.deved.antlride.internal.core.model.*;
import java.util.List;
import java.util.ArrayList;
}

@members {
	private GrammarBuilder builder;	
	private GrammarBuilderFactory factory;
	
 	public void reportError(Exception ex) {
  		emitErrorMessage(ex.getClass().getName() + ": " + ex.getMessage());
  	}
}

grammarDef[GrammarBuilderFactory factory]
@init {
	this.factory = factory;
	builder = factory.newGrammarBuilder();
}
  :
  ^(
    grammarType ID {builder.name($ID);}
    (
      DOC_COMMENT {builder.documentation($DOC_COMMENT);}
    )?
    optionsSpec[builder]? delegateGrammars? tokensSpec? attrScope* {builder.scopes();} action* {builder.actions();} rule+ {builder.rules();}
   )
  ;

grammarType
  :
  LEXER_GRAMMAR {builder.type(GrammarType.LEXER);}
  | PARSER_GRAMMAR {builder.type(GrammarType.PARSER);}
  | TREE_GRAMMAR {builder.type(GrammarType.TREE_PARSER);}
  | COMBINED_GRAMMAR {builder.type(GrammarType.COMBINED);}
  ;

delegateGrammars
  :
  ^(impStart='import' delegateGrammar+ impEnd=';')
  {
  			builder.imports(impStart, impEnd);
  		}
  ;

delegateGrammar
  :
  ^('=' label=ID dg=ID)
  {builder.importGrammar(dg, label);}
  | ID {builder.importGrammar($ID);}
  ;

tokensSpec
  :
  ^(TOKENS tokenSpec* toksEnd='}')
  {builder.tokens($TOKENS, $toksEnd);}
  ;

tokenSpec
  :
  ^('=' TOKEN_REF STRING_LITERAL)
  {builder.token($TOKEN_REF, $STRING_LITERAL);}
  | TOKEN_REF {builder.token($TOKEN_REF);}
  ;

attrScope
  :
  ^('scope' ID ACTION)
  {builder.scope($ID, $ACTION);}
  ;

action
  :
  ^(amp='@' scp=ID name=ID act=ACTION)
  {builder.action($amp, $scp, $name, $act);}
  |
  ^(amp='@' name=ID act=ACTION)
  {builder.action($amp, $name, $act);}
  ;

optionsSpec[OptionsAware options]
  :
  ^(OPTIONS option[options]* optEnd='}')
  {options.options($OPTIONS, $optEnd);}
  ;

option[OptionsAware options]
  :
  ^('=' ID optval=optionValue {options.option($ID, optval);})
  ;

optionValue returns [CommonTree token = null;]
  :
  ID {token = $ID;}
  | STRING_LITERAL {token = $STRING_LITERAL;}
  | INT {token = $INT;}
  ;

rule
@init {
	builder.beginRule();
}
@after {
	builder.endRule();
}
  :
  ^(
    RULE
    (
      doc=DOC_COMMENT {builder.rule().documentation(doc);}
    )?
    ID {builder.rule().name($ID).ruleStart($ID);}
    (
      mod=modifier {builder.rule().accessModifier(mod).ruleStart($ID);}
    )?
    (
      '!' {builder.rule().astSuffix(ASTSuffix.BANG);}
    )?
    (
      ^(ARG params=ARG_ACTION {builder.rule().parameters(params);})
    )?
    (
      ^(RET rets=ARG_ACTION {builder.rule().returns(rets);})
    )?
    throwsSpec[builder.rule()]? optionsSpec[builder.rule()]? ruleScopeSpec? ruleAction* COLON body=altList {builder.rule().body(body).bodyStart($COLON);} exceptionGroup[builder.rule()]? EOR {builder.rule().bodyEnd($EOR).ruleEnd($EOR);}
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

modifier returns [IRule.RuleAccessModifier mod = IRule.RuleAccessModifier.PUBLIC;]
  :
  'protected' {mod = IRule.RuleAccessModifier.PROTECTED;}
  | 'public' {mod = IRule.RuleAccessModifier.PUBLIC;}
  | 'private' {mod = IRule.RuleAccessModifier.PRIVATE;}
  | 'fragment' {mod = IRule.RuleAccessModifier.FRAGMENT;}
  ;

/** Match stuff like @init {int i;} */
ruleAction
  :
  ^('@' ID ACTION)
  {builder.rule().action($ID, $ACTION);}
  ;

throwsSpec[RuleBuilder rule]
  :
  ^('throws' exception+=ID+)
  {rule.ruleThrows($exception);}
  ;

ruleScopeSpec
  :
  ^(s='scope' ACTION)
  {builder.rule().scope(s, $ACTION);}
  |
  ^(
    s='scope' ACTION {builder.rule().scope(s, $ACTION);}
    (
      ID {builder.rule().scopeReference($ID);}
    )+
   )
  |
  ^(
    s='scope'
    (
      ID {builder.rule().scopeReference($ID);}
    )+
   )
  ;

/**
	This block has '(' and ')'
*/
block returns [BlockBuilder block = factory.newBlockBuilder();]
  :
  ^(
    BLOCK optionsSpec[block]?
    (
      stt=alternative {block.statement(stt);} rwstt=rewrite {if(rwstt!=null)stt.rewrite(rwstt);}
    )+
    EOB
   )
  {block.start($BLOCK).lp($BLOCK).end($EOB).rp($EOB);}
  ;

altList returns [BlockBuilder block = factory.newBlockBuilder(true);]
  :
  ^(
    BLOCK
    (
      stt=alternative {block.start($BLOCK).statement(stt);} rwstt=rewrite {if(rwstt!=null)stt.rewrite(rwstt);}
    )+
    EOB {block.end($EOB);}
   )
  ;

alternative returns [AlternativeBuilder alt = factory.newAlternativeBuilder();]
  :
  ^(
    ALT
    (
      stt=element {alt.statement(stt);}
    )+
    EOA {alt.start($ALT).end($EOA);}
   )
  |
  ^(ALT EPSILON EOA {alt.start($ALT).end($EOA);})
  ;

exceptionGroup[RuleBuilder rule]
  :
  exceptionHandler[rule]+ finallyClause[rule]?
  | finallyClause[rule]
  ;

exceptionHandler[RuleBuilder rule]
  :
  ^(c='catch' ARG_ACTION ACTION)
  {rule.ruleCatch($c, $ARG_ACTION, $ACTION);}
  ;

finallyClause[RuleBuilder rule]
  :
  ^(f='finally' ACTION)
  {rule.ruleFinally($f, $ACTION);}
  ;

element returns [StatementBuilder statement = null;]
  :
  stt=elementNoOptionSpec {statement=stt;}
  ;

elementNoOptionSpec returns [StatementBuilder statement = null;]
  :
  ^(
    (
      op='='
      | op='+='
    )
    ID stt=block
   )
  {statement=factory.newAssignBuilder().assign($op, $ID, stt);}
  |
  ^(
    (
      op='='
      | op='+='
    )
    ID stt=atom
   )
  {statement=factory.newAssignBuilder().assign($op, $ID, stt);}
  | stt=atom {statement=stt;}
  | stt=ebnf {statement=stt;}
  | ACTION {statement=factory.newActionBuilder().action($ACTION);}
  | SEMPRED {statement=factory.newSemPredBuilder().condition($SEMPRED).type(ISemanticPredicate.PredicateType.SEMPRED);}
  | GATED_SEMPRED {statement=factory.newSemPredBuilder().condition($GATED_SEMPRED).type(ISemanticPredicate.PredicateType.GATED_SEMPRED);}
  | stt1=treeSpec {statement=stt1;}
  ;

atom returns [StatementBuilder statement = null;]
  :
  ^(
    (
      treeOp='^'
      | treeOp='!'
    )
    stt1=atom
   )
  {statement=stt1.treeOp($treeOp);}
  | stt2=range {statement = stt2;}
  | stt3=notSet {statement = stt3;}
  | stt4=call {statement = stt4;}
  | stt5=terminal {statement = stt5;}
  ;

call returns [StatementBuilder statement = null]
  :
  RULE_REF ARG_ACTION? {
		if($ARG_ACTION!=null) {
    		statement = factory.newCallExpressionBuilder().name($RULE_REF).parameters($ARG_ACTION);
    	} else {
    		statement = factory.newCallExpressionBuilder().name($RULE_REF);
    	}
    }
  ;

notSet returns [StatementBuilder statement = null;]
  :
  ^(not='~' stt=notTerminal)
  {statement=stt.not($not);}
  |
  ^(not='~' stt1=block)
  {statement=stt1.not($not);}
  ;

treeSpec returns [TreeBuilder statement = factory.newTreeBuilder();]
  :
  ^(
    TREE_BEGIN
    (
      elem=element {statement.statement(elem);}
    )+
   )
  ;

/** Matches ENBF blocks (and token sets via block rule) */
ebnf returns [StatementBuilder statement = null;]
  :
  ^(SYNPRED stt=block)
  {statement=factory.newSynPredBuilder().predicate(stt);}
  |
  ^(SYN_SEMPRED stt=block)
  {statement=factory.newSynPredBuilder().predicate(stt);}
  |
  ^(op=ebnfSuffix stt1=block)
  {statement=stt1.ebnf(op);}
  |
  ^(ROOT stt1=block)
  {statement=stt1.treeOp($ROOT);}
  |
  ^(BANG stt1=block)
  {statement=stt1.treeOp($BANG);}
  | stt=block {statement=stt;}
  ;

range returns [RangeBuilder statement = factory.newRangeBuilder();]
  :
  ^(op=CHAR_RANGE from=STRING_LITERAL to=STRING_LITERAL)
  {statement.operator($op).from($from).to($to);}
  ;

elementOptions[CallExpressionBuilder parent]
  :
  ^(ELEMENT_OPTIONS elementOption[parent]+)
  ;

elementOption[CallExpressionBuilder parent]
  :
  ID {parent.option($ID);}
  |
  ^(ASSIGN n=ID v=ID)
  {parent.option($n, $v);}
  |
  ^(ASSIGN n=ID v=STRING_LITERAL)
  {parent.option($n, $v);}
  ;

terminal returns [CallExpressionBuilder statement = factory.newCallExpressionBuilder();]
  :
  ^(TOKEN_REF elementOptions[statement] ARG_ACTION)
  {statement.name($TOKEN_REF);}
  |
  ^(TOKEN_REF elementOptions[statement])
  {statement.name($TOKEN_REF);}
  |
  ^(TOKEN_REF ARG_ACTION)
  {statement.name($TOKEN_REF).parameters($ARG_ACTION);}
  | TOKEN_REF {statement.name($TOKEN_REF);}
  |
  ^(STRING_LITERAL elementOptions[statement])
  {statement.name($STRING_LITERAL);}
  | STRING_LITERAL {statement.name($STRING_LITERAL);}
  | any='.' {statement.name($any);}
  ;

notTerminal returns [CallExpressionBuilder statement = factory.newCallExpressionBuilder();]
  :
  TOKEN_REF {statement.name($TOKEN_REF);}
  | STRING_LITERAL {statement.name($STRING_LITERAL);}
  ;

ebnfSuffix returns [CommonTree node = null;]
  :
  OPTIONAL {node = $OPTIONAL;}
  | CLOSURE {node = $CLOSURE;}
  | POSITIVE_CLOSURE {node = $POSITIVE_CLOSURE;}
  ;

// R E W R I T E  S Y N T A X

rewrite returns [StatementBuilder statement = null]
@init {
	BlockBuilder blockBuilder = null;
}
  :
  (
    ^(blockStart='->' SEMPRED alt=rewrite_alternative {	
  				StatementBuilder semstt = factory.newSemPredBuilder()  													
  													.type(ISemanticPredicate.PredicateType.SEMPRED)
  													.condition($SEMPRED)
  													.predicate(alt);
  				blockBuilder=(blockBuilder==null? factory.newBlockBuilder():blockBuilder);
  				blockBuilder.start($blockStart);
  				blockBuilder.statement( factory.newAlternativeBuilder().statement(semstt) );  				
  			})
  )*
  ^(blockStart='->' alt=rewrite_alternative {  			
  			blockBuilder=(blockBuilder==null? factory.newBlockBuilder():blockBuilder);
  			blockBuilder.start($blockStart);
  			blockBuilder.statement( alt );
  		})
  {statement=blockBuilder;}
  | {	
  		//an alternative without rewrite rule, returns null
  	}
  ;

rewrite_alternative returns [AlternativeBuilder statement=null;]
  :
  stt0=rewrite_template {statement=factory.newAlternativeBuilder().statement(stt0);}
  | stt1=rewrite_tree_alternative {statement=stt1;}
  |
  ^(ALT EPSILON EOA)
  {statement=factory.newAlternativeBuilder().start($ALT).end($EOA);}
  ;

rewrite_tree_block returns [BlockBuilder statement=factory.newBlockBuilder();]
  :
  ^(BLOCK {statement.start($BLOCK).lp($BLOCK);} stt=rewrite_tree_alternative {statement.statement(stt);} EOB {statement.end($EOB).rp($EOB);})
  ;

rewrite_tree_alternative returns [AlternativeBuilder statement=factory.newAlternativeBuilder();]
  :
  ^(
    ALT
    (
      stt=rewrite_tree_element {statement.statement(stt);}
    )+
    EOA
   )
  {statement.start($ALT).end($EOA);}
  ;

rewrite_tree_element returns [StatementBuilder statement = null;]
  :
  stt=rewrite_tree_atom {statement=stt;}
  | stt=rewrite_tree {statement=stt;}
  | stt1=rewrite_tree_block {statement=stt1;}
  | stt=rewrite_tree_ebnf {statement=stt;}
  ;

rewrite_tree_atom returns [StatementBuilder statement = null]
  :
  ^(TOKEN_REF {statement=factory.newCallExpressionBuilder().name($TOKEN_REF);} elementOptions[(CallExpressionBuilder)statement] ARG_ACTION {((CallExpressionBuilder)statement).parameters($ARG_ACTION);}) // for imaginary nodes
  |
  ^(TOKEN_REF {statement=factory.newCallExpressionBuilder().name($TOKEN_REF);} elementOptions[(CallExpressionBuilder)statement]) // for imaginary nodes
  |
  ^(TOKEN_REF ARG_ACTION) // for imaginary nodes
  {statement=factory.newCallExpressionBuilder().name($TOKEN_REF).parameters($ARG_ACTION);}
  | TOKEN_REF {statement=factory.newCallExpressionBuilder().name($TOKEN_REF);}
  | RULE_REF {statement=factory.newCallExpressionBuilder().name($RULE_REF);}
  |
  ^(STRING_LITERAL {statement=factory.newCallExpressionBuilder().name($STRING_LITERAL);} elementOptions[(CallExpressionBuilder)statement])
  | STRING_LITERAL {statement=factory.newCallExpressionBuilder().name($STRING_LITERAL);}
  | LABEL {statement=factory.newCallExpressionBuilder().name($LABEL).labeled();}
  | ACTION {statement=factory.newActionBuilder().action($ACTION);}
  ;

rewrite_tree_ebnf returns [StatementBuilder statement=null]
  :
  ^(op=ebnfSuffix stt=rewrite_tree_block {statement=stt.ebnf(op);})
  ;

rewrite_tree returns [TreeBuilder statement = factory.newTreeBuilder();]
  :
  ^(
    TREE_BEGIN tatom=rewrite_tree_atom {statement.statement(tatom);}
    (
      telem=rewrite_tree_element {statement.statement(telem);}
    )*
   )
  ;

rewrite_template returns [TemplateBuilder template=factory.newTemplateBuilder()]
  :
  ^(
    TEMPLATE ID {template.name($ID);} rewrite_template_args[template]
    (
      inlineNode=DOUBLE_QUOTE_STRING_LITERAL
      | inlineNode=DOUBLE_ANGLE_STRING_LITERAL
    )
    {template.inlineTemplate(inlineNode);}
   )
  | rewrite_template_ref[template]
  | rewrite_indirect_template_head[template]
  | ACTION {template.action($ACTION).setSimpleActionTemplate(true);}
  ;

/** foo(a={...}, ...) */
rewrite_template_ref[TemplateBuilder template]
  :
  ^(TEMPLATE ID {template.name($ID);} rewrite_template_args[template])
  ;

/** ({expr})(a={...}, ...) */
rewrite_indirect_template_head[TemplateBuilder template]
  :
  ^(TEMPLATE ACTION {template.action($ACTION);} rewrite_template_args[template])
  ;

rewrite_template_args[TemplateBuilder template]
  :
  ^(ARGLIST rewrite_template_arg[template]+)
  | ARGLIST
  ;

rewrite_template_arg[TemplateBuilder template]
  :
  ^(ARG ID ACTION {template.parameter($ID, $ACTION);})
  ;
