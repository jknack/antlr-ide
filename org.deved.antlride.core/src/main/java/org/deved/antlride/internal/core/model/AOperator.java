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

public abstract class AOperator extends AStatement implements IOperator {

	protected ISourceElement operator;	

	public AOperator(ISourceElement operator) {
		this.operator = operator;
	}
	
	public ISourceElement getOperator() {
		return operator;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(operator.getText());
		builder.append(" ");
		//builder.append(statement);
		return builder.toString();
	}
}
