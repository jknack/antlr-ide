/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.internal.core.model;

import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;

import org.deved.antlride.core.model.ASTSuffix;
import org.deved.antlride.core.model.ElementKind;
import org.deved.antlride.core.model.GrammarType;
import org.deved.antlride.core.model.IAlternative;
import org.deved.antlride.core.model.IBlock;
import org.deved.antlride.core.model.ICallExpression;
import org.deved.antlride.core.model.IGrammar;
import org.deved.antlride.core.model.IModelElement;
import org.deved.antlride.core.model.IOption;
import org.deved.antlride.core.model.IOptions;
import org.deved.antlride.core.model.IParameter;
import org.deved.antlride.core.model.IParameters;
import org.deved.antlride.core.model.IReturn;
import org.deved.antlride.core.model.IReturns;
import org.deved.antlride.core.model.IRule;
import org.deved.antlride.core.model.IRuleAction;
import org.deved.antlride.core.model.IRuleCatch;
import org.deved.antlride.core.model.IRuleFinally;
import org.deved.antlride.core.model.IRuleScope;
import org.deved.antlride.core.model.IRuleThrows;
import org.deved.antlride.core.model.IScope;
import org.deved.antlride.core.model.IScopeReference;
import org.deved.antlride.core.model.ISourceElement;
import org.deved.antlride.core.model.IStatement;
import org.deved.antlride.core.model.IStatementAction;
import org.deved.antlride.core.model.RuleType;
import org.deved.antlride.core.model.ast.IModelElementVisitor;
import org.deved.antlride.core.model.ast.ModelElementQuery;
import org.deved.antlride.core.model.ast.ToEbnfVisitor;
import org.deved.antlride.core.model.ast.criteria.ModelElementCriteriaFactory;
import org.deved.antlride.core.model.dltk.ast.DASTRule;
import org.deved.antlride.core.util.AntlrTextHelper;
import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.Modifiers;
import org.eclipse.dltk.ast.declarations.Argument;
import org.eclipse.dltk.ast.statements.Block;

public class ARule extends AAbstractModelElement implements IRule {

	private static final IRuleAction[] EMPTY_ACTIONS = new IRuleAction[0];

	private static final IRuleScope[] NO_RULE_SCOPES = new IRuleScope[0];

	private static final IRuleCatch[] NO_CATCHS = new IRuleCatch[0];

	private static final IScopeReference[] NO_SCOPES_REFERENCES = new IScopeReference[0];

	private static final Pattern IGNORED_RULES = Pattern
			.compile(".*\\$channel\\s*=\\s*HIDDEN.*");

	private IOptions options;

	private RuleAccessModifier accessModifier;

	private String documentation;

	private ISourceElement name;

	private IParameters parameters;

	private IReturns returns;

	private IRuleScope[] scopes = NO_RULE_SCOPES;

	private IScopeReference[] usesScopes = NO_SCOPES_REFERENCES;

	private IRuleAction actions[];

	private boolean ignored;

	private IBlock body;

	private ASTSuffix astSuffix;

	private DASTRule ruleNode;

	private String declaration;

	private String stringList;

	private String stringTree;

	private IRuleCatch[] catchs = NO_CATCHS;

	private ARuleFinally ruleFinally;

	private ARuleThrows ruleThrows;

	public ARule(IGrammar parent) {
		super(parent);
		accessModifier = RuleAccessModifier.PUBLIC;
		setAstSuffix(ASTSuffix.NONE);
	}

	public ASTSuffix getAstSuffix() {
		return astSuffix;
	}

	public void setAccessModifier(RuleAccessModifier accessModifier) {
		this.accessModifier = accessModifier;
	}

	public void setAstSuffix(ASTSuffix astSuffix) {
		this.astSuffix = astSuffix;
	}

	public void setName(ISourceElement name) {
		this.name = name;
	}

	public <E> E getAdapter(Class<E> adapter) {
		if (adapter == IRule.class)
			return adapter.cast(this);
		if (adapter == IGrammar.class)
			return adapter.cast(getParent());
		if (adapter == IOptions.class)
			return adapter.cast(getOptions());
		if (adapter == ASTNode.class)
			return adapter.cast(getAST());
		return null;
	}

	public IBlock getBody() {
		return body;
	}

	public IRuleAction findAction(String name) {
		if (hasActions()) {
			for (int i = 0; i < actions.length; i++) {
				IRuleAction ruleAction = actions[i];
				if (ruleAction.getElementName().equals(name)) {
					return ruleAction;
				}
			}
		}
		return null;
	}

	public IOption findOption(String name) {
		if (hasOptions())
			return options.findOption(name);
		return null;
	}

	public IScope findScope(String name) {
		if (hasScopes()) {
			for (int i = 0; i < scopes.length; i++) {
				IRuleScope ruleScope = scopes[i];
				if (ruleScope.getElementName().equals(name)) {
					return ruleScope;
				}
			}
		}
		return null;
	}

	public IScopeReference findScopeReference(String name) {
		if (hasScopesReferences()) {
			for (int i = 0; i < usesScopes.length; i++) {
				IScopeReference scope = usesScopes[i];
				if (scope.getElementName().equals(name)) {
					return scope;
				}
			}
		}
		return null;
	}

	public IRuleAction[] getActions() {
		return actions;
	}

	public RuleAccessModifier getAccessModifier() {
		return accessModifier;
	}

	private DASTRule getAST() {
		if (ruleNode == null) {
			ruleNode = new DASTRule(getElementName(), name.sourceStart(), name
					.sourceEnd(), sourceStart(), sourceEnd());
			Block body = ruleNode.getBody();

			switch (accessModifier) {
			case PUBLIC:
				ruleNode.setModifier(Modifiers.AccPublic);
				break;
			case FRAGMENT:
			case PRIVATE:
				ruleNode.setModifier(Modifiers.AccPrivate);
				break;
			case PROTECTED:
				ruleNode.setModifier(Modifiers.AccProtected);
				break;
			}

			if (hasParameters()) {
				for (int i = 0; i < parameters.getParametersCount(); i++) {
					IParameter paremeter = parameters.getParemeter(i);
					Argument argument = (Argument) paremeter
							.getAdapter(ASTNode.class);
					ruleNode.addArgument(argument);
				}
			}

			if (hasOptions()) {
				ruleNode.addDecorator(options.getAdapter(ASTNode.class));
			}

			if (hasActions()) {
				for (int i = 0; i < actions.length; i++) {
					ruleNode.addDecorator(actions[i].getAdapter(ASTNode.class));
				}
			}

			if (hasScopes()) {
				for (int i = 0; i < scopes.length; i++) {
					ruleNode.addDecorator(scopes[i].getAdapter(ASTNode.class));
				}
			}

			if (hasScopesReferences()) {
				for (int i = 0; i < usesScopes.length; i++) {
					ruleNode.addDecorator(usesScopes[i]
							.getAdapter(ASTNode.class));
				}
			}
			IModelElement[] calls = ModelElementQuery.collectCalls(this);
			for (int i = 0; i < calls.length; i++) {
				ICallExpression callExpression = (ICallExpression) calls[i];
				body.addStatement(callExpression.getAdapter(ASTNode.class));
			}
			calls = null;
		}
		return ruleNode;
	}

	public String getDeclaration() {
		if (declaration == null) {
			try {
				declaration = body.toString();
			} catch (Exception ex) {

			}
		}
		return declaration;
	}

	public String getPlainDocumentation() {
		return documentation;
	}
	
	public String getDocumentation() {
		return AntlrTextHelper.parseDoc( documentation );
	}

	public ElementKind getElementKind() {
		return ElementKind.RULE;
	}

	public String getElementName() {
		return name.getText();
	}

	public ISourceElement getName() {
		return name;
	}

	public IOptions getOptions() {
		return options;
	}

	public IParameter getParameter(int index) {
		return parameters == null ? null : parameters.getParemeter(index);
	}

	public IParameters getParameters() {
		return parameters;
	}

	public int getParametersCount() {
		return parameters == null ? 0 : parameters.getParametersCount();
	}

	public IReturn getReturn(int index) {
		return returns == null ? null : returns.getReturn(index);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (obj instanceof IRule) {
			IRule rule = (IRule) obj;
			return getParent().equals(rule.getParent())
					&& getElementName().equals(rule.getElementName());
		}
		return false;
	}

	public IReturns getReturns() {
		return returns;
	}

	public int getReturnsCount() {
		return returns == null ? 0 : returns.getReturnsCount();
	}

	public RuleType getRuleType() {
		IGrammar grammar = (IGrammar) getParent();
		GrammarType grammarType = grammar.getGrammarType();
		if (grammarType == GrammarType.LEXER)
			return RuleType.LEXER;
		if (grammarType == GrammarType.TREE_PARSER)
			return RuleType.TREE_PARSER;
		if (Character.isUpperCase(getElementName().charAt(0)))
			return RuleType.LEXER;
		return RuleType.PARSER;
	}

	public IRuleScope[] getScopes() {
		return scopes;
	}

	public IScopeReference[] getScopesReferences() {
		return usesScopes;
	}

	public boolean hasActions() {
		return actions != null && actions.length > 0;
	}

	public boolean hasParameters() {
		return parameters != null;
	}

	public boolean hasOptions() {
		return options != null;
	}

	public boolean hasReturns() {
		return returns != null;
	}

	public boolean hasScopes() {
		return scopes != null && scopes.length > 0;
	}

	public boolean hasScopesReferences() {
		return usesScopes != null && usesScopes.length > 0;
	}

	public boolean isIgnored() {
		return ignored;
	}

	public boolean isEmpty() {
		return ModelElementQuery.count(body, ModelElementCriteriaFactory
				.kind(ElementKind.CALL)) == 0
				&& ModelElementQuery.count(body, ModelElementCriteriaFactory
						.kind(ElementKind.STATEMENT_ACTION)) == 0;
		// return false;
	}

	public boolean isLexerRule() {
		return Character.isUpperCase(getElementName().charAt(0));
	}

	public String toStringList() {
		return stringList;
	}

	public String toStringTree() {
		return stringTree;
	}

	public void setBody(IBlock body) {
		this.body = body;
		((ARuleBody) body).setParent(this);
		// ((AStatement) body).setParent(this);
		// ((AStatement) body).setEnclosingRule(this);
		// parent(body);
		if (isLexerRule()) {
			// check if it's ignored
			if (this.body.size() > 0) {
				IAlternative alternative = (IAlternative) this.body.get(0);
				if (alternative.size() - 1 >= 0) {
					IStatement statement = alternative
							.get(alternative.size() - 1);
					if (statement.getElementKind() == ElementKind.STATEMENT_ACTION) {
						IStatementAction statementAction = statement
								.getAdapter(IStatementAction.class);
						String text = statementAction.getAction().getText();
						ignored = IGNORED_RULES.matcher(text).matches();
					}
				}
			}
		}
	}

	public void setDocumentation(String documentation) {
		this.documentation = documentation;
	}

	public void setOptions(IOptions options) {
		this.options = options;
		((AOptions) this.options).setParent(this);
	}

	public void setParameters(IParameters parameters) {
		this.parameters = parameters;
	}

	public void setReturns(IReturns returns) {
		this.returns = returns;
	}

	public void setActions(Collection<IRuleAction> actions) {
		if (actions == null || actions.size() == 0) {
			this.actions = EMPTY_ACTIONS;
		} else {
			this.actions = new IRuleAction[actions.size()];
			this.actions = actions.toArray(this.actions);
			for (int i = 0; i < this.actions.length; i++) {
				((AAbstractModelElement) this.actions[i]).setParent(this);
			}
		}
	}

	public void setUsesScopes(List<IScopeReference> scopes) {
		if (scopes == null || scopes.size() == 0) {
			this.usesScopes = NO_SCOPES_REFERENCES;
		} else {
			// scope reference
			this.usesScopes = scopes
					.toArray(new IScopeReference[scopes.size()]);
		}
		for (int i = 0; i < this.usesScopes.length; i++) {
			((AAbstractModelElement) this.usesScopes[i]).setParent(this);
		}
	}

	public void setRuleScopes(List<IRuleScope> scopes) {
		if (scopes == null || scopes.size() == 0) {
			this.scopes = NO_RULE_SCOPES;
		} else {
			this.scopes = scopes.toArray(new IRuleScope[scopes.size()]);
		}
		for (int i = 0; i < this.scopes.length; i++) {
			((AAbstractModelElement) this.scopes[i]).setParent(this);
		}
	}

	public void traverse(IModelElementVisitor visitor) {
		if (visitor.visitRule(this)) {
			if (hasParameters())
				parameters.traverse(visitor);

			if (hasReturns())
				returns.traverse(visitor);

			if (ruleThrows != null) {
				ruleThrows.traverse(visitor);
			}

			if (hasOptions())
				options.traverse(visitor);

			if (hasScopes()) {
				for (int i = 0; i < scopes.length; i++) {
					scopes[i].traverse(visitor);
				}
			}

			if (usesScopes != null && usesScopes.length > 0) {
				for (int i = 0; i < usesScopes.length; i++) {
					usesScopes[i].traverse(visitor);
				}
			}

			if (hasActions()) {
				for (int i = 0; i < actions.length; i++) {
					actions[i].traverse(visitor);
				}
			}

			body.traverse(visitor);

			visitor.endvisitRule(this);
			if (hasCatchs()) {
				for (int i = 0; i < catchs.length; i++) {
					catchs[i].traverse(visitor);
				}
			}

			if (ruleFinally != null) {
				ruleFinally.traverse(visitor);
			}
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		if (documentation != null) {
			builder.append(documentation);
			builder.append("\n");
		}
		if (accessModifier != RuleAccessModifier.PUBLIC) {
			builder.append(accessModifier.description());
			builder.append(" ");
		}
		builder.append(name.getText());

		if (astSuffix != ASTSuffix.NONE) {
			builder.append(astSuffix.description());
		}

		// parameters
		if (parameters != null) {
			builder.append(parameters);
		}

		// returns
		if (returns != null) {
			builder.append(returns);
		}
		// throws
		if (ruleThrows != null) {
			builder.append(ruleThrows);
		}
		// options
		if (this.options != null) {
			builder.append("\n");
			builder.append(options);
		}
		// scopes
		if (scopes != null && scopes.length > 0) {
			builder.append("\n");
			builder.append("scope {\n");
			for (int i = 0; i < scopes.length; i++) {
				builder.append(scopes[i]);
				if (i + 1 < scopes.length) {
					builder.append(", ");
				}
			}
			builder.append("\n}\n");
		}

		if (actions != null && actions.length > 0) {
			builder.append("\n");
			for (int i = 0; i < actions.length; i++) {
				builder.append(actions[i]);
			}
		}

		if (this.usesScopes != null && this.usesScopes.length > 0) {
			builder.append("\nscope ");
			for (int i = 0; i < usesScopes.length; i++) {
				builder.append(usesScopes[i]);
				if (i + 1 < usesScopes.length) {
					builder.append(", ");
				}
			}
			builder.append(";\n");
		}
		builder.append(":\n");

		if (body != null) {
			builder.append(body);
		}
		builder.append("\n;\n\n");

		if (hasCatchs()) {
			for (int i = 0; i < catchs.length; i++) {
				builder.append(catchs[i]);
			}
		}
		if (ruleFinally != null) {
			builder.append(ruleFinally);
		}
		return builder.toString();
	}

	public void setStringList(String stringList) {
		this.stringList = stringList;
	}

	public void setStringTree(String stringTree) {
		this.stringTree = stringTree;
	}

	public void setCatchs(List<IRuleCatch> catchs) {
		if (catchs == null || catchs.size() == 0) {
			this.catchs = NO_CATCHS;
		} else {
			this.catchs = catchs.toArray(new IRuleCatch[catchs.size()]);
			for (IRuleCatch ruleCatch : catchs) {
				((AAbstractModelElement) ruleCatch).setParent(this);
			}
		}
	}

	public IRuleCatch[] getCatchs() {
		return catchs;
	}

	public boolean hasCatchs() {
		return catchs.length > 0;
	}

	public void setFinally(ARuleFinally ruleFinally) {
		this.ruleFinally = ruleFinally;
		ruleFinally.setParent(this);
	}

	public IRuleFinally getFinally() {
		return ruleFinally;
	}

	public IRuleThrows getThrows() {
		return ruleThrows;
	}

	public void setThrows(ARuleThrows ruleThrows) {
		this.ruleThrows = ruleThrows;
		ruleThrows.setParent(this);
	}

	public String toEbnf() {
		return new ToEbnfVisitor(this).toEbnf();
	}

}
