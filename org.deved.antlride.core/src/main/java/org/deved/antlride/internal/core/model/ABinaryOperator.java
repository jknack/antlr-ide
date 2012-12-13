/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.internal.core.model;

import org.deved.antlride.core.model.IBinaryOperator;
import org.deved.antlride.core.model.ISourceElement;
import org.deved.antlride.core.model.IStatement;

public abstract class ABinaryOperator extends AOperator implements IBinaryOperator {

	protected IStatement left;
	
	protected IStatement right;

	public ABinaryOperator(ISourceElement operator, IStatement left, IStatement right) {
		super(operator);
		this.left = left;
		this.right= right;
		((AStatement)this.left).setParent(this);
		((AStatement)this.right).setParent(this);
	}
	
	public ISourceElement getOperator() {
		return operator;
	}

	public IStatement getLeft() {
		return left;
	}
	
	public IStatement getRight() {
		return right;
	}

	public int sourceEnd() {
		return right.sourceEnd();
	}

	public int sourceStart() {
		return left.sourceStart();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(left);
		builder.append(" ");
		builder.append(operator.getText());
		builder.append(" ");
		builder.append(right);
		return builder.toString();
	}
}
