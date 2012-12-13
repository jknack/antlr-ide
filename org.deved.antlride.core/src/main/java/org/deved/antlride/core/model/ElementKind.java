/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.core.model;

public enum ElementKind {
	GRAMMAR(null),
	GRAMMAR_OPTIONS(GRAMMAR),
	GRAMMAR_OPTION(GRAMMAR_OPTIONS),
	GRAMMAR_OPTION_NAME(GRAMMAR_OPTION),
	GRAMMAR_OPTION_VALUE(GRAMMAR_OPTION),
	GRAMMAR_SCOPE(GRAMMAR),
	GRAMMAR_SCOPE_ATTRIBUTE(GRAMMAR_SCOPE),
	/**Tokens*/
	TOKENS(GRAMMAR),
	TOKEN(TOKENS),
	TOKEN_NAME(TOKEN),
	TOKEN_VALUE(TOKEN),
	/**Grammar Action*/
	GRAMMAR_ACTION(GRAMMAR),
	/**Imports*/
	IMPORTS(GRAMMAR),
	IMPORT(IMPORTS),
	/**RULES*/
	RULE(GRAMMAR),
	RULE_PARAMETERS(RULE),
	RULE_PARAMETER(RULE),
	RULE_RETURNS(RULE),
	RULE_RETURN(RULE),
	RULE_OPTIONS(RULE),
	RULE_OPTION(RULE_OPTIONS),
	RULE_OPTION_NAME(RULE_OPTION),
	RULE_OPTION_VALUE(RULE_OPTION),
	RULE_SCOPE(RULE),
	RULE_SCOPE_ATTRIBUTE(RULE),	
	RULE_ACTION(RULE),
	RULE_THROWS(RULE),
	RULE_CATCH(RULE),
	RULE_FINALLY(RULE),
	/**CALL*/
	CALL(RULE),
	CALL_OPTION(CALL),
	CALL_PARAMETERS(CALL),
	CALL_PARAMETER(CALL_PARAMETERS),
	/**Statements*/
	BLOCK(RULE),
	BLOCK_OPTIONS(BLOCK),
	BLOCK_OPTION(BLOCK_OPTIONS),
	BLOCK_OPTION_NAME(BLOCK_OPTION),
	BLOCK_OPTION_VALUE(BLOCK_OPTION),
	ALTERNATIVE(RULE),
	TEMPLATE(RULE),
	TEMPLATE_PARAMETER(TEMPLATE),
	TREE_STATEMENT(RULE),
	STATEMENT_ACTION(RULE),
	VARIABLE(RULE),
	ASSIGN_OPERATOR(RULE),
	NOT_OPERATOR(RULE),
	ROOT_OPERATOR(RULE),
	BANG_OPERATOR(RULE),
	RANGE(RULE),
	/**Predicates*/
	SEMPRED(RULE),
	SYN_PRED(RULE),
	TARGET_ACTION(RULE),
	/**References*/
	REFERENCE(RULE),
	SCOPE_REFERENCE(RULE),
	SCOPE_ATTRIBUTE_REFERENCE(RULE),
	RULE_SCOPE_REFERENCE(RULE),
	RULE_SCOPE_ATTRIBUTE_REFERENCE(RULE),
	RULE_REFERENCE(RULE);
	
	
	private ElementKind parent;
	
	private ElementKind(ElementKind parent) {
		this.parent = parent;
	}
	
	public boolean isChildOf(ElementKind parent) {
		ElementKind p = this.parent;
		while(p!=null) {
			if(p == parent)
				return true;
			p = p.parent;
		}
		return false;
	}
	
	public ElementKind parent() {
		return parent;
	}
}
