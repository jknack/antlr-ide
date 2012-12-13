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
import org.deved.antlride.core.model.IAssign;
import org.deved.antlride.core.model.IGrammar;
import org.deved.antlride.core.model.IRule;
import org.deved.antlride.core.model.ISourceElement;
import org.deved.antlride.core.model.IVariable;
import org.deved.antlride.core.model.ast.IModelElementVisitor;

public class AVariable extends AStatement implements IVariable {

	private ISourceElement variable;

	public AVariable(ISourceElement variable) {
		super(variable.sourceStart(), variable.sourceEnd());
		this.variable = variable;
	}

	public <E> E getAdapter(Class<E> adapter) {
		if (adapter == IVariable.class)
			return adapter.cast(this);
		if (adapter == IAssign.class)
			return adapter.cast(getParent());
		if (adapter == IRule.class)
			return adapter.cast(getEnclosingRule());
		if (adapter == IGrammar.class)
			return adapter.cast(getEnclosingRule().getParent());
		return null;
	}

	public ElementKind getElementKind() {
		return ElementKind.VARIABLE;
	}

	public String getElementName() {
		return variable.getText();
	}

	public String getName() {
		return variable.getText();
	}

	public void traverse(IModelElementVisitor visitor) {
		if (visitor.visitVariable(this)) {
			visitor.endvisitVariable(this);
		}
	}

	@Override
	public String toString() {
		return variable.getText();
	}
}
