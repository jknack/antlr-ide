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
import org.deved.antlride.core.model.ISourceElement;

import org.deved.antlride.core.model.ast.IModelElementVisitor;

public class AReturn extends AAbstractModelElement implements IReturn {

	private ISourceElement name;

	private ISourceElement type;

	public AReturn(IReturns parent, ISourceElement name, ISourceElement type) {
		super(parent, type != null ? type.sourceStart() : name.sourceStart(),
				name.sourceEnd());
		this.name = name;
		this.type = type;
	}

	public <E> E getAdapter(Class<E> adapter) {
		if (adapter == IReturn.class)
			return adapter.cast(this);
		return getParent().getAdapter(adapter);
	}

	public ElementKind getElementKind() {
		return ElementKind.RULE_RETURN;
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
		if (visitor.visitRuleReturn(this)) {
			visitor.endvisitRuleReturn(this);
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(type.getText());
		builder.append(" ");
		builder.append(name.getText());
		return builder.toString();
	}
}
