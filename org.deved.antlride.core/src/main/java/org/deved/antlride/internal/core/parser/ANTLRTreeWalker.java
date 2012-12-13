/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.internal.core.parser;

import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.tree.CommonTree;
import org.deved.antlride.core.model.IRule;

public interface ANTLRTreeWalker {

	void grammarDef(GrammarBuilderFactory factory)
			throws RecognitionException;

	void grammarType() throws RecognitionException;

	void delegateGrammars() throws RecognitionException;

	void delegateGrammar() throws RecognitionException;

	void tokensSpec() throws RecognitionException;

	void tokenSpec() throws RecognitionException;

	void attrScope() throws RecognitionException;

	void action() throws RecognitionException;

	void optionsSpec(OptionsAware options)
			throws RecognitionException;

	void option(OptionsAware options)
			throws RecognitionException;

	CommonTree optionValue() throws RecognitionException;

	void rule() throws RecognitionException;

	IRule.RuleAccessModifier modifier()
			throws RecognitionException;

	void ruleAction() throws RecognitionException;

	void throwsSpec(RuleBuilder rule) throws RecognitionException;

	void ruleScopeSpec() throws RecognitionException;

	BlockBuilder block() throws RecognitionException;

	BlockBuilder altList() throws RecognitionException;

	AlternativeBuilder alternative()
			throws RecognitionException;

	void exceptionGroup(RuleBuilder rule) throws RecognitionException;

	void exceptionHandler(RuleBuilder rule) throws RecognitionException;

	void finallyClause(RuleBuilder rule) throws RecognitionException;

	StatementBuilder element() throws RecognitionException;

	StatementBuilder elementNoOptionSpec()
			throws RecognitionException;

	StatementBuilder atom() throws RecognitionException;

	StatementBuilder notSet() throws RecognitionException;

	TreeBuilder treeSpec() throws RecognitionException;

	StatementBuilder ebnf() throws RecognitionException;

	RangeBuilder range() throws RecognitionException;

	CallExpressionBuilder terminal()
			throws RecognitionException;

	CallExpressionBuilder notTerminal()
			throws RecognitionException;

	CommonTree ebnfSuffix() throws RecognitionException;

	StatementBuilder rewrite() throws RecognitionException;

	AlternativeBuilder rewrite_alternative()
			throws RecognitionException;

	BlockBuilder rewrite_tree_block()
			throws RecognitionException;

	AlternativeBuilder rewrite_tree_alternative()
			throws RecognitionException;

	StatementBuilder rewrite_tree_element()
			throws RecognitionException;

	StatementBuilder rewrite_tree_atom()
			throws RecognitionException;

	StatementBuilder rewrite_tree_ebnf()
			throws RecognitionException;

	TreeBuilder rewrite_tree() throws RecognitionException;

	TemplateBuilder rewrite_template() throws RecognitionException;

	void rewrite_template_ref(TemplateBuilder template) throws RecognitionException;

	void rewrite_indirect_template_head(TemplateBuilder template)
			throws RecognitionException;

	void rewrite_template_args(TemplateBuilder template) throws RecognitionException;

	void rewrite_template_arg(TemplateBuilder template) throws RecognitionException;
}