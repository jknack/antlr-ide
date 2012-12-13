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
import org.deved.antlride.core.model.IScope;
import org.deved.antlride.core.model.IScopeAttribute;
import org.deved.antlride.core.model.ISourceElement;
import org.deved.antlride.core.model.ast.IModelElementVisitor;

public class AScopeAttribute extends AAbstractModelElement implements
		IScopeAttribute {

	private ISourceElement name;

	private ISourceElement type;

	public AScopeAttribute(IScope parent, ISourceElement name,
			ISourceElement type) {
		super(parent, type != null ? type.sourceStart() : name.sourceStart(),
				name.sourceEnd());
		this.name = name;
		this.type = type;
	}

	public <E> E getAdapter(Class<E> adapter) {
		if (adapter == IScopeAttribute.class)
			return adapter.cast(this);
		ElementKind kind = getElementKind();
		if (adapter == IRule.class) {
			if (kind == ElementKind.RULE_SCOPE_ATTRIBUTE)
				return adapter.cast(getParent().getAdapter(adapter));
		} else if (adapter == IGrammar.class) {
			return adapter.cast(getParent().getAdapter(adapter));
		}
		return null;
	}

	public ElementKind getElementKind() {
		ElementKind kind = getParent().getElementKind();
		if (kind == ElementKind.GRAMMAR_SCOPE)
			return ElementKind.GRAMMAR_SCOPE_ATTRIBUTE;
		return ElementKind.RULE_SCOPE_ATTRIBUTE;
	}

	public String getElementName() {
		return name.getText();
	}

	public ISourceElement getName() {
		return name;
	}

	public ISourceElement getType() {
		return type;
	}

	public void traverse(IModelElementVisitor visitor) {
		ElementKind kind = getElementKind();
		if (kind == ElementKind.GRAMMAR_SCOPE_ATTRIBUTE) {
			if (visitor.visitGrammarScopeAttribute(this)) {
				visitor.endvisitGrammarScopeAttribute(this);
			}
		} else {
			if (visitor.visitRuleScopeAttribute(this)) {
				visitor.endvisitRuleScopeAttribute(this);
			}
		}

	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		if (type != null) {
			builder.append(type.getText());
			builder.append(" ");
		}

		builder.append(name.getText());
		builder.append(";");
		return builder.toString();
	}
}
