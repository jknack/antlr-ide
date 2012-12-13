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
import org.deved.antlride.core.model.ISourceElement;
import org.deved.antlride.core.model.ITreeStatement;
import org.deved.antlride.core.model.ast.IModelElementVisitor;

public class ATreeStatement extends ACompositeStatement implements
		ITreeStatement {

	private ISourceElement name;

	public ATreeStatement() {
	}
	
	public void setName(ISourceElement name) {
		this.name = name;
	}
	
	public ISourceElement getName() {
		return name;
	}

	public <E> E getAdapter(Class<E> adapter) {
		if (adapter == ITreeStatement.class)
			return adapter.cast(this);
		if (adapter == IRule.class)
			return adapter.cast(getEnclosingRule());
		if (adapter == IGrammar.class)
			return adapter.cast(getEnclosingRule().getParent());
		return null;
	}

	public ElementKind getElementKind() {
		return ElementKind.TREE_STATEMENT;
	}

	public String getElementName() {
		return "^(";
	}

	public int sourceStart() {
		if (size() > 0)
			return get(0).sourceStart();
		return 0;
	}

	public int sourceEnd() {
		if (size() > 0)
			return get(size() - 1).sourceEnd();
		return 0;
	}

	public void traverse(IModelElementVisitor visitor) {
		if (visitor.visitTreeStatement(this)) {
			for (int i = 0; i < size(); i++) {
				get(i).traverse(visitor);
			}
			visitor.endvisitTreeStatement(this);
		}

	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(getElementName());
		for (int index = 0; index < size(); index++) {
			builder.append(get(index));
			if (index + 1 < size()) {
				builder.append(" ");
			}
		}
		builder.append(" )");
		return builder.toString();
	}
}
