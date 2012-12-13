/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.internal.core.model;

import org.deved.antlride.core.model.ISourceElement;
import org.deved.antlride.core.model.IStatement;
import org.deved.antlride.core.model.ITreeOperator;

public abstract class ATreeOperator extends AUnaryOperator implements
		ITreeOperator {

	public ATreeOperator(ISourceElement operator, IStatement statement) {
		super(operator, statement);
	}

	public int sourceEnd() {
		return operator.sourceEnd();
	}

	public int sourceStart() {
		return statement.sourceStart();
	}	

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(statement);
		builder.append(operator.getText());
		return builder.toString();
	}
}
