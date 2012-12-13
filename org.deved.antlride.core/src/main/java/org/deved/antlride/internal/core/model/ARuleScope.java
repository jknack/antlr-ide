/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.internal.core.model;

import org.deved.antlride.core.model.ElementKind;
import org.deved.antlride.core.model.IGrammar;
import org.deved.antlride.core.model.IRule;
import org.deved.antlride.core.model.IRuleScope;
import org.deved.antlride.core.model.IScopeAttribute;
import org.deved.antlride.core.model.ast.IModelElementVisitor;
import org.deved.antlride.core.model.dltk.ast.DASTScope;
import org.eclipse.dltk.ast.ASTNode;

public class ARuleScope extends AScope implements IRuleScope {

	private DASTScope node;

	public ARuleScope(IRule parent) {
		setParent(parent);
	}

	public IScopeAttribute findAttribute(String name) {
		for (int i = 0; i < attributes.length; i++) {
			IScopeAttribute attribute = attributes[i];
			if (attribute.getName().getText().equals(name)) {
				return attribute;
			}
		}
		return null;
	}

	public <E> E getAdapter(Class<E> adapter) {
		if (adapter == IRuleScope.class)
			return adapter.cast(this);
		if (adapter == IRule.class) {
			return adapter.cast(getParent());
		}
		if (adapter == IGrammar.class) {
			return adapter.cast(getParent().getParent());
		}
		if (adapter == ASTNode.class)
			return adapter.cast(getAST());
		return null;
	}

	private DASTScope getAST() {
		if (node == null) {
			String scopeName = "scope";
			int nameStart = sourceStart;
			int nameEnd = nameStart + scopeName.length();
			node = new DASTScope(getElementKind().ordinal(), scopeName,
					nameStart, nameEnd, sourceStart, sourceEnd);

			attributes(node);
		}
		return node;
	}

	public ElementKind getElementKind() {
		return ElementKind.RULE_SCOPE;
	}

	public String getElementName() {
		return ((IRule) getParent()).getElementName();
	}

	public void traverse(IModelElementVisitor visitor) {
		if (visitor.visitRuleScope(this)) {
			if (attributes != null && attributes.length > 0)
				for (int i = 0; i < attributes.length; i++) {
					attributes[i].traverse(visitor);
				}
			visitor.endvisitRuleScope(this);
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < attributes.length; i++) {
			builder.append(attributes[i]);
		}
		return builder.toString();
	}
}
