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
import org.deved.antlride.core.model.IReturn;
import org.deved.antlride.core.model.IReturns;
import org.deved.antlride.core.model.IRule;
import org.deved.antlride.core.model.ast.IModelElementVisitor;

public class AReturns extends AAbstractModelElement implements IReturns {

	private static final IReturn[] EMPTY_RETURNS = new IReturn[0];

	private IReturn[] returns;

	private String text;

	public AReturns(IRule parent, int sourceStart, int sourceEnd) {
		super(parent, sourceStart, sourceEnd);
	}

	public <E> E getAdapter(Class<E> adapter) {
		if (adapter == IReturns.class)
			return adapter.cast(this);
		return getParent().getAdapter(adapter);
	}

	public ElementKind getElementKind() {
		return ElementKind.RULE_RETURNS;
	}

	public String getElementName() {
		return "<returns>";
	}

	public int getReturnsCount() {
		return returns.length;
	}

	public IReturn[] getReturns() {
		return returns;
	}

	public IReturn findReturn(String name) {
		for (int i = 0; i < returns.length; i++) {
			IReturn parameter = returns[i];
			if (parameter.getName().getText().equals(name)) {
				return parameter;
			}
		}
		return null;
	}

	public IReturn getReturn(int index) {
		return returns[index];
	}

	public void setReturns(IReturn[] returns) {
		if (returns == null || returns.length == 0) {
			this.returns = EMPTY_RETURNS;
		} else {
			this.returns = returns;
		}
	}

	public void traverse(IModelElementVisitor visitor) {
		if (visitor.visitRuleReturns(this)) {
			for (int i = 0; i < returns.length; i++) {
				returns[i].traverse(visitor);
			}
			visitor.endvisitRuleReturns(this);
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("returns ");
		for (int i = 0; i < returns.length; i++) {
			builder.append(returns[i]);
			if (i + 1 < returns.length) {
				builder.append(", ");
			}
		}
		builder.append("");
		return builder.toString();
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
