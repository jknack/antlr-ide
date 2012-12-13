/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.core.model.ast;

import org.deved.antlride.core.model.IAlternative;
import org.deved.antlride.core.model.IAssign;
import org.deved.antlride.core.model.IBangOperator;
import org.deved.antlride.core.model.IBlock;
import org.deved.antlride.core.model.ICallExpression;
import org.deved.antlride.core.model.IGrammar;
import org.deved.antlride.core.model.IGrammarAction;
import org.deved.antlride.core.model.IGrammarScope;
import org.deved.antlride.core.model.IImport;
import org.deved.antlride.core.model.IImports;
import org.deved.antlride.core.model.IModelElement;
import org.deved.antlride.core.model.INotOperator;
import org.deved.antlride.core.model.IOption;
import org.deved.antlride.core.model.IOptionName;
import org.deved.antlride.core.model.IOptionValue;
import org.deved.antlride.core.model.IOptions;
import org.deved.antlride.core.model.IParameter;
import org.deved.antlride.core.model.IParameters;
import org.deved.antlride.core.model.IRange;
import org.deved.antlride.core.model.IReference;
import org.deved.antlride.core.model.IReturn;
import org.deved.antlride.core.model.IReturns;
import org.deved.antlride.core.model.IRootOperator;
import org.deved.antlride.core.model.IRule;
import org.deved.antlride.core.model.IRuleAction;
import org.deved.antlride.core.model.IRuleCatch;
import org.deved.antlride.core.model.IRuleFinally;
import org.deved.antlride.core.model.IRuleScope;
import org.deved.antlride.core.model.IRuleThrows;
import org.deved.antlride.core.model.IScopeAttribute;
import org.deved.antlride.core.model.IScopeReference;
import org.deved.antlride.core.model.ISemanticPredicate;
import org.deved.antlride.core.model.IStatement;
import org.deved.antlride.core.model.IStatementAction;
import org.deved.antlride.core.model.ISyntacticPredicate;
import org.deved.antlride.core.model.ITargetAction;
import org.deved.antlride.core.model.ITemplate;
import org.deved.antlride.core.model.ITemplateParameter;
import org.deved.antlride.core.model.IToken;
import org.deved.antlride.core.model.ITokenName;
import org.deved.antlride.core.model.ITokenValue;
import org.deved.antlride.core.model.ITokens;
import org.deved.antlride.core.model.ITreeStatement;
import org.deved.antlride.core.model.IVariable;

public interface IModelElementVisitor {

	public void accept(IModelElement node);

	boolean visitGrammar(IGrammar node);

	boolean visitGrammarOptions(IOptions node);

	boolean visitGrammarOption(IOption node);

	void endvisitGrammarOption(IOption node);

	boolean visitGrammarOptionName(IOptionName node);

	void endvisitGrammarOptionName(IOptionName node);

	boolean visitGrammarOptionValue(IOptionValue node);

	void endvisitGrammarOptionValue(IOptionValue node);

	void endvisitGrammarOptions(IOptions node);

	boolean visitTokens(ITokens node);

	boolean visitToken(IToken node);

	boolean visitTokenName(ITokenName node);

	boolean visitTokenValue(ITokenValue node);

	void endvisitTokenName(ITokenName node);

	void endvisitTokenValue(ITokenValue node);

	void endvisitToken(IToken node);

	void endvisitTokens(ITokens node);

	boolean visitGrammarAction(IGrammarAction node);

	void endvisitGrammarAction(IGrammarAction node);

	boolean visitGrammarScope(IGrammarScope node);

	boolean visitGrammarScopeAttribute(IScopeAttribute node);

	void endvisitGrammarScopeAttribute(IScopeAttribute node);

	void endvisitGrammarScope(IGrammarScope node);

	boolean visitRule(IRule node);

	boolean visitRuleParameters(IParameters node);

	boolean visitRuleParameter(IParameter node);

	void endvisitRuleParameter(IParameter node);

	void endvisitRuleParameters(IParameters node);

	boolean visitRuleReturns(IReturns node);

	boolean visitRuleReturn(IReturn node);

	void endvisitRuleReturn(IReturn node);

	void endvisitRuleReturns(IReturns node);

	boolean visitRuleOptions(IOptions node);

	boolean visitRuleOption(IOption node);

	boolean visitRuleOptionName(IOptionName node);

	void endvisitRuleOptionName(IOptionName node);

	boolean visitRuleOptionValue(IOptionValue node);

	void endvisitRuleOptionValue(IOptionValue node);

	void endvisitRuleOption(IOption node);

	boolean visitRuleBody(IBlock node);

	void endvisitRuleBody(IBlock node);

	void endvisitRuleOptions(IOptions node);

	boolean visitRuleAction(IRuleAction node);

	void endvisitRuleAction(IRuleAction node);

	boolean visitRuleScope(IRuleScope node);

	void endvisitRuleScope(IRuleScope node);

	boolean visitRuleScopeAttribute(IScopeAttribute node);

	void endvisitRuleScopeAttribute(IScopeAttribute node);

	boolean visitRuleScopeReference(IScopeReference node);

	void endvisitRuleScopeReference(IScopeReference node);

	void endvisitRule(IRule node);

	boolean visitBlock(IBlock node);

	boolean visitBlockOptions(IOptions node);

	boolean visitBlockOption(IOption node);

	boolean visitBlockOptionName(IOptionName node);

	void endvisitBlockOptionName(IOptionName node);

	boolean visitBlockOptionValue(IOptionValue node);

	void endvisitBlockOptionValue(IOptionValue node);

	void endvisitBlockOption(IOption node);

	void endvisitBlockOptions(IOptions node);

	void endvisitBlock(IBlock node);

	boolean visitAlternative(IAlternative node);

	void endvisitAlternative(IAlternative node);

	boolean visitRewriteAlternative(IAlternative node);

	void endvisitRewriteAlternative(IAlternative node);

	boolean visitRewriteBlock(IBlock node);

	void endvisitRewriteBlock(IBlock node);

	boolean visitAssign(IAssign node);

	void endvisitAssign(IAssign node);

	boolean visitVariable(IVariable node);

	void endvisitVariable(IVariable node);

	void endvisitGrammar(IGrammar node);

	boolean visitRootOperator(IRootOperator node);

	void endvisitRootOperator(IRootOperator node);

	boolean visitBangOperator(IBangOperator node);

	void endvisitBangOperator(IBangOperator node);

	boolean visitCallExpression(ICallExpression node);

	boolean visitCallParameters(IParameters node);

	boolean visitCallParameter(IParameter node);

	void endvisitCallParameter(IParameter node);

	void endvisitCallParameters(IParameters node);

	void endvisitCallExpression(ICallExpression node);

	boolean visitNotOperator(INotOperator node);

	void endvisitNotOperator(INotOperator node);

	boolean visitRange(IRange node);

	void endvisitRange(IRange node);

	boolean visitSemanticPredicate(ISemanticPredicate node);

	void endvisitSemanticPredicate(ISemanticPredicate node);

	boolean visitSyntacticPredicate(ISyntacticPredicate node);

	void endvisitSyntacticPredicate(ISyntacticPredicate node);

	boolean visitStatementAction(IStatementAction node);

	void endvisitStatementAction(IStatementAction node);

	boolean visitTreeStatement(ITreeStatement node);

	void endvisitTreeStatement(ITreeStatement node);

	boolean visitTargetAction(ITargetAction node);

	void endvisitTargetAction(ITargetAction node);

	boolean visitReference(IReference node);

	void endvisitReference(IReference node);

	boolean visitTemplate(ITemplate node);

	void endvisitTemplate(ITemplate node);

	boolean visitTemplateParameter(ITemplateParameter node);

	void endvisitTemplateParameter(ITemplateParameter node);

	boolean visitImports(IImports node);

	void endvisitImport(IImport node);

	boolean visitImport(IImport node);

	void endvisitImports(IImports node);

	boolean visitRuleCatch(IRuleCatch node);
	
	void endvisitRuleCatch(IRuleCatch node);

	boolean visitRuleFinally(IRuleFinally node);
	
	void endvisitRuleFinally(IRuleFinally node);

	boolean visitRuleThrows(IRuleThrows node);
	
	void endvisitRuleThrows(IRuleThrows node);
	
	boolean visitSyntacticPredicateCondition(IStatement node);
	
	void endvisitSyntacticPredicateCondition(IStatement node);
}
