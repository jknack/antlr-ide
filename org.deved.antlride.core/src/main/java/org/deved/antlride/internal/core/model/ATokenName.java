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
import org.deved.antlride.core.model.IModelElement;
import org.deved.antlride.core.model.ISourceElement;
import org.deved.antlride.core.model.ITokenName;
import org.deved.antlride.core.model.ast.IModelElementVisitor;

public class ATokenName extends AAbstractModelElement implements ITokenName {

	private ISourceElement name;

	public ATokenName(IModelElement parent, ISourceElement name) {
		super(parent);
		setSourceStart(name.sourceStart());
		setSourceEnd(name.sourceEnd());
		this.name = name;
	}

	public <E> E getAdapter(Class<E> adapter) {
		if (adapter == ITokenName.class)
			return adapter.cast( this );
		return getParent().getAdapter(adapter);
	}

	public ElementKind getElementKind() {
		return ElementKind.TOKEN_NAME;
	}

	public String getElementName() {
		return name.getText();
	}

	public void traverse(IModelElementVisitor visitor) {
		if (visitor.visitTokenName(this)) {
			visitor.endvisitTokenName(this);
		}
	}

	public String getText() {
		return getElementName();
	}

	@Override
	public String toString() {
		return getElementName();
	}
}
