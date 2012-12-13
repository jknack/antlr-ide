/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.core.model;

public interface IRule extends IModelElement {

	public enum RuleAccessModifier {
		PUBLIC(""), PROTECTED("protected"), FRAGMENT("fragment"), PRIVATE(
				"private");

		private final String description;

		RuleAccessModifier(String description) {
			this.description = description;
		}

		public static RuleAccessModifier getAccessModifier(String modifier) {
			return FRAGMENT.description.equals(modifier) ? FRAGMENT
					: PROTECTED.description.equals(modifier) ? PROTECTED
							: PRIVATE.description.equals(modifier) ? PRIVATE
									: PUBLIC;
		}

		public String description() {
			return description;
		}
	}
	
	ASTSuffix getAstSuffix();

	IRuleAction findAction(String name);

	IOption findOption(String name);

	IScope findScope(String name);

	IScopeReference findScopeReference(String name);

	RuleAccessModifier getAccessModifier();

	IRuleAction[] getActions();

	IBlock getBody();

	String getDeclaration();

	String getPlainDocumentation();
	
	String getDocumentation();
	
	ISourceElement getName();

	IOptions getOptions();

	IParameter getParameter(int index);

	IParameters getParameters();

	int getParametersCount();

	IReturn getReturn(int index);

	IReturns getReturns();

	int getReturnsCount();

	RuleType getRuleType();

	IRuleScope[] getScopes();
	
	IRuleCatch[] getCatchs();
	
	IRuleFinally getFinally();

	IScopeReference[] getScopesReferences();

	boolean hasActions();

	boolean hasOptions();

	boolean hasParameters();

	boolean hasReturns();

	boolean hasScopes();
	
	boolean hasCatchs();

	boolean hasScopesReferences();

	boolean isIgnored();

	boolean isLexerRule();
	
	boolean isEmpty();

	void setStringTree(String stringTree);

	String toStringTree();
	
	String toEbnf();

	String[] PREDEFINED_RULE_PROPERTIES = { "text", "start", "stop", "tree",
			"st" };

	String[] PREDEFINED_LEXER_RULE_PROPERTIES = { "text", "type", "line",
			"index", "pos", "channel", "start", "stop" };

	String[] PREDEFINED_TREE_RULE_PROPERTIES = { "text", "start", "tree", "st" };
}