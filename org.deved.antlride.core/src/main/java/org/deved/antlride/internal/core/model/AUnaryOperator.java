/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.internal.core.model;

import org.deved.antlride.core.model.IOperator;
import org.deved.antlride.core.model.ISourceElement;
import org.deved.antlride.core.model.IStatement;

public abstract class AUnaryOperator extends AOperator implements IOperator {

	protected IStatement statement;

	public AUnaryOperator(ISourceElement operator, IStatement statement) {
		super(operator);
		this.statement = statement;
		((AStatement)this.statement).setParent(this);
	}
	
	public ISourceElement getOperator() {
		return operator;
	}

	public IStatement getStatement() {
		return statement;
	}

	public int sourceEnd() {
		return statement.sourceEnd();
	}

	public int sourceStart() {
		return operator.sourceStart();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(operator.getText());
		builder.append(" ");
		builder.append(statement);
		return builder.toString();
	}
}
