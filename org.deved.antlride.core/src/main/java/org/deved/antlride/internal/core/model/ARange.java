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
import org.deved.antlride.core.model.ICallExpression;
import org.deved.antlride.core.model.IGrammar;
import org.deved.antlride.core.model.IRange;
import org.deved.antlride.core.model.IRule;
import org.deved.antlride.core.model.ISourceElement;
import org.deved.antlride.core.model.ast.IModelElementVisitor;

public class ARange extends ABinaryOperator implements IRange {

	public ARange(ISourceElement operator, ICallExpression from,
			ICallExpression to) {
		super(operator, from, to);
	}

	public <E> E getAdapter(Class<E> adapter) {
		if (adapter == IRange.class)
			return adapter.cast(this);
		if (adapter == IRule.class)
			return adapter.cast(getEnclosingRule());
		if (adapter == IGrammar.class)
			return adapter.cast(getEnclosingRule().getParent());
		return null;
	}

	public ElementKind getElementKind() {
		return ElementKind.RANGE;
	}

	public String getElementName() {
		return "..";
	}

	public void traverse(IModelElementVisitor visitor) {
		if (visitor.visitRange(this)) {
			left.traverse(visitor);
			right.traverse(visitor);
			visitor.endvisitRange(this);
		}
	}
}
