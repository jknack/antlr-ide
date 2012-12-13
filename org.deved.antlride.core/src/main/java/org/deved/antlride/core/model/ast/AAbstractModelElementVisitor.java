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

public abstract class AAbstractModelElementVisitor implements IModelElementVisitor {

	protected boolean enabled;

	public void accept(IModelElement node) {
		enabled = true;
		node.traverse(this);
	}

	protected void in(IModelElement element) {
	}

	protected void out(IModelElement element) {
	}

	public void endvisitBlockOptionName(IOptionName node) {
		out(node);
	}

	public void endvisitBlockOptionValue(IOptionValue node) {
		out(node);
	}

	public void endvisitGrammarOptionName(IOptionName node) {
		out(node);
	}

	public void endvisitGrammarOptionValue(IOptionValue node) {
		out(node);
	}

	public void endvisitRuleOptionName(IOptionName node) {
		out(node);
	}

	public void endvisitRuleOptionValue(IOptionValue node) {
		out(node);
	}

	public void endvisitAlternative(IAlternative node) {
		out(node);
	}

	public void endvisitAssign(IAssign node) {
		out(node);
	}

	public void endvisitBangOperator(IBangOperator node) {
		out(node);
	}

	public void endvisitBlock(IBlock node) {
		out(node);
	}

	public void endvisitBlockOption(IOption node) {
		out(node);
	}

	public void endvisitBlockOptions(IOptions node) {
		out(node);
	}

	public void endvisitCallExpression(ICallExpression node) {
		out(node);
	}

	public void endvisitCallParameter(IParameter node) {
		out(node);
	}

	public void endvisitCallParameters(IParameters node) {
		out(node);
	}

	public void endvisitGrammar(IGrammar node) {
		out(node);
	}

	public void endvisitGrammarAction(IGrammarAction node) {
		out(node);
	}

	public void endvisitGrammarOption(IOption node) {
		out(node);
	}

	public void endvisitGrammarOptions(IOptions node) {
		out(node);
	}

	public void endvisitGrammarScope(IGrammarScope node) {
		out(node);
	}

	public void endvisitGrammarScopeAttribute(IScopeAttribute node) {
		out(node);
	}

	public void endvisitNotOperator(INotOperator node) {
		out(node);
	}

	public void endvisitRange(IRange node) {
		out(node);
	}

	public void endvisitRewriteAlternative(IAlternative node) {
		out(node);
	}
	
	public void endvisitRewriteBlock(IBlock node) {
		out(node);
	}

	public void endvisitRootOperator(IRootOperator node) {
		out(node);
	}

	public void endvisitRule(IRule node) {
		out(node);
	}

	public void endvisitRuleAction(IRuleAction node) {
		out(node);
	}

	public void endvisitRuleOption(IOption node) {
		out(node);
	}

	public void endvisitRuleOptions(IOptions node) {
		out(node);
	}

	public void endvisitRuleParameter(IParameter node) {
		out(node);
	}

	public void endvisitRuleParameters(IParameters node) {
		out(node);
	}

	public void endvisitRuleReturn(IReturn node) {
		out(node);
	}

	public void endvisitRuleReturns(IReturns node) {
		out(node);
	}

	public void endvisitRuleScope(IRuleScope node) {
		out(node);
	}

	public void endvisitRuleScopeAttribute(IScopeAttribute node) {
		out(node);
	}

	public void endvisitRuleScopeReference(IScopeReference node) {
		out(node);
	}

	public void endvisitSemanticPredicate(ISemanticPredicate node) {
		out(node);
	}

	public void endvisitStatementAction(IStatementAction node) {
		out(node);
	}

	public void endvisitSyntacticPredicate(ISyntacticPredicate node) {
		out(node);
	}

	public void endvisitToken(IToken node) {
		out(node);
	}

	public void endvisitTokens(ITokens node) {
		out(node);
	}

	public void endvisitTreeStatement(ITreeStatement node) {
		out(node);
	}

	public void endvisitVariable(IVariable node) {
		out(node);
	}

	public boolean visitBlockOptionName(IOptionName node) {
		if (enabled) {
			in(node);
		}
		return enabled;
	}

	public boolean visitBlockOptionValue(IOptionValue node) {
		if (enabled) {
			in(node);
		}
		return enabled;
	}

	public boolean visitGrammarOptionName(IOptionName node) {
		if (enabled) {
			in(node);
		}
		return enabled;
	}
	
	public boolean visitGrammarOptionValue(IOptionValue node) {
		if (enabled) {
			in(node);
		}
		return enabled;		
	}
	
	public boolean visitRuleOptionName(IOptionName node) {
		if (enabled) {
			in(node);
		}
		return enabled;
	}
	
	public boolean visitRuleOptionValue(IOptionValue node) {
		if (enabled) {
			in(node);
		}
		return enabled;
	}

	public boolean visitAlternative(IAlternative node) {
		if (enabled) {
			in(node);
		}
		return enabled;
	}

	public boolean visitAssign(IAssign node) {
		if (enabled) {
			in(node);
		}
		return enabled;
	}

	public boolean visitBangOperator(IBangOperator node) {
		if (enabled) {
			in(node);
		}
		return enabled;
	}

	public boolean visitBlock(IBlock node) {
		if (enabled) {
			in(node);
		}
		return enabled;
	}
	
	public boolean visitRuleBody(IBlock node) {
		if (enabled) {
			in(node);
		}
		return enabled;
	}
	
	public void endvisitRuleBody(IBlock node) {
		
	}

	public boolean visitBlockOption(IOption node) {
		if (enabled) {
			in(node);
		}
		return enabled;
	}

	public boolean visitBlockOptions(IOptions node) {
		if (enabled) {
			in(node);
		}
		return enabled;
	}

	public boolean visitCallExpression(ICallExpression node) {
		if (enabled) {
			in(node);
		}
		return enabled;
	}

	public boolean visitCallParameter(IParameter node) {
		if (enabled) {
			in(node);
		}
		return enabled;
	}

	public boolean visitCallParameters(IParameters node) {
		if (enabled) {
			in(node);
		}
		return enabled;
	}

	public boolean visitGrammar(IGrammar node) {
		if (enabled) {
			in(node);
		}
		return enabled;
	}

	public boolean visitGrammarAction(IGrammarAction node) {
		if (enabled) {
			in(node);
		}
		return enabled;
	}

	public boolean visitGrammarOption(IOption node) {
		if (enabled) {
			in(node);
		}
		return enabled;
	}

	public boolean visitGrammarOptions(IOptions node) {
		if (enabled) {
			in(node);
		}
		return enabled;
	}

	public boolean visitGrammarScope(IGrammarScope node) {
		if (enabled) {
			in(node);
		}
		return enabled;
	}

	public boolean visitGrammarScopeAttribute(IScopeAttribute node) {
		if (enabled) {
			in(node);
		}
		return enabled;
	}

	public boolean visitNotOperator(INotOperator node) {
		if (enabled) {
			in(node);
		}
		return enabled;
	}

	public boolean visitRange(IRange node) {
		if (enabled) {
			in(node);
		}
		return enabled;
	}

	public boolean visitRewriteAlternative(IAlternative node) {
		if (enabled) {
			in(node);
		}
		return enabled;
	}
	
	public boolean visitRewriteBlock(IBlock node) {
		if (enabled) {
			in(node);
		}
		return enabled;
	}

	public boolean visitRootOperator(IRootOperator node) {
		if (enabled) {
			in(node);
		}
		return enabled;
	}

	public boolean visitRule(IRule node) {
		if (enabled) {
			in(node);
		}
		return enabled;
	}

	public boolean visitRuleAction(IRuleAction node) {
		if (enabled) {
			in(node);
		}
		return enabled;
	}

	public boolean visitRuleOption(IOption node) {
		if (enabled) {
			in(node);
		}
		return enabled;
	}

	public boolean visitRuleOptions(IOptions node) {
		if (enabled) {
			in(node);
		}
		return enabled;
	}

	public boolean visitRuleParameter(IParameter node) {
		if (enabled) {
			in(node);
		}
		return enabled;
	}

	public boolean visitRuleParameters(IParameters node) {
		if (enabled) {
			in(node);
		}
		return enabled;
	}

	public boolean visitRuleReturn(IReturn node) {
		if (enabled) {
			in(node);
		}
		return enabled;
	}

	public boolean visitRuleReturns(IReturns node) {
		if (enabled) {
			in(node);
		}
		return enabled;
	}

	public boolean visitRuleScope(IRuleScope node) {
		if (enabled) {
			in(node);
		}
		return enabled;
	}

	public boolean visitRuleScopeAttribute(IScopeAttribute node) {
		if (enabled) {
			in(node);
		}
		return enabled;
	}

	public boolean visitRuleScopeReference(IScopeReference node) {
		if (enabled) {
			in(node);
		}
		return enabled;
	}

	public boolean visitSemanticPredicate(ISemanticPredicate node) {
		if (enabled) {
			in(node);
		}
		return enabled;
	}

	public boolean visitStatementAction(IStatementAction node) {
		if (enabled) {
			in(node);
		}
		return enabled;
	}

	public boolean visitSyntacticPredicate(ISyntacticPredicate node) {
		if (enabled) {
			in(node);
		}
		return enabled;
	}

	public boolean visitToken(IToken node) {
		if (enabled) {
			in(node);
		}
		return enabled;
	}
	
	public boolean visitTokenName(ITokenName node) {
		if (enabled) {
			in(node);
		}
		return enabled;
	}
	
	public boolean visitTokenValue(ITokenValue node) {
		if (enabled) {
			in(node);
		}
		return enabled;
	}
	
	public void endvisitTokenName(ITokenName node) {
		
	}
	
	public void endvisitTokenValue(ITokenValue node) {
		
	}

	public boolean visitTokens(ITokens node) {
		if (enabled) {
			in(node);
		}
		return enabled;
	}

	public boolean visitTreeStatement(ITreeStatement node) {
		if (enabled) {
			in(node);
		}
		return enabled;
	}

	public boolean visitVariable(IVariable node) {
		if (enabled) {
			in(node);
		}
		return enabled;
	}

	public boolean visitTargetAction(ITargetAction node) {
		if (enabled) {
			in(node);
		}
		return enabled;
	}

	public void endvisitTargetAction(ITargetAction node) {

	}

	public boolean visitReference(IReference node) {
		if (enabled) {
			in(node);
		}
		return enabled;
	}

	public void endvisitReference(IReference node) {
	}
	
	public boolean visitTemplate(ITemplate node) {
		if (enabled) {
			in(node);
		}
		return enabled;
	}
	
	public void endvisitTemplate(ITemplate node) {	
	}
	
	public boolean visitTemplateParameter(ITemplateParameter node) {
		if (enabled) {
			in(node);
		}
		return enabled;
	}
	
	public void endvisitTemplateParameter(ITemplateParameter node) {	
	}
	
	public boolean visitImport(IImport node) {
		if (enabled) {
			in(node);
		}
		return enabled;
	}
	
	public boolean visitImports(IImports node) {
		if (enabled) {
			in(node);
		}
		return enabled;
	}
	
	public void endvisitImport(IImport node) {
		
	}
	
	public void endvisitImports(IImports node) {
		
	}
	
	public void endvisitRuleCatch(IRuleCatch node) {
	}
	
	public boolean visitRuleCatch(IRuleCatch node) {
		if (enabled) {
			in(node);
		}
		return enabled;
	}
	
	public void endvisitRuleFinally(IRuleFinally node) {
	}
	
	public boolean visitRuleFinally(IRuleFinally node) {
		if (enabled) {
			in(node);
		}
		return enabled;
	}
	
	public void endvisitRuleThrows(IRuleThrows node) {
		
	}
	
	public boolean visitRuleThrows(IRuleThrows node) {
		if (enabled) {
			in(node);
		}
		return enabled;
	}
	
	public boolean visitSyntacticPredicateCondition(IStatement node) {
		if (enabled) {
			in(node);
		}
		return enabled;
	}
	
	public void endvisitSyntacticPredicateCondition(IStatement node) {
		
	}
}
