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
import org.deved.antlride.core.model.INotOperator;
import org.deved.antlride.core.model.IRule;
import org.deved.antlride.core.model.ISourceElement;
import org.deved.antlride.core.model.IStatement;
import org.deved.antlride.core.model.ast.IModelElementVisitor;

public class ANotOperator extends AUnaryOperator implements INotOperator {

	public ANotOperator(ISourceElement operator, IStatement statement) {
		super(operator, statement);		
	}
	
	public <E> E getAdapter(Class<E> adapter) {
		if(adapter == INotOperator.class)
			return adapter.cast( this );
		if(adapter == IRule.class)
			return adapter.cast( getEnclosingRule() );
		if(adapter == IGrammar.class)
			return adapter.cast( getEnclosingRule().getParent() );
		return null;
	}
	
	public ElementKind getElementKind() {
		return ElementKind.NOT_OPERATOR;
	}
	
	public String getElementName() {
		return "~";
	}

	public void traverse(IModelElementVisitor visitor) {
		if(visitor.visitNotOperator(this)) {
			statement.traverse(visitor);
			visitor.endvisitNotOperator(this);
		}
	}
}
