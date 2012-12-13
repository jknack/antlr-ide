/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.internal.core.model;

import org.deved.antlride.core.model.IRule;
import org.deved.antlride.core.model.IStatement;

public abstract class AStatement extends AAbstractModelElement implements
		IStatement {	

	private EBNF ebnfOperator;

	public AStatement(int sourceStart, int sourceEnd) {
		super(sourceStart, sourceEnd);
		ebnfOperator = EBNF.NONE;
	}

	public AStatement() {
		this(0, 0);
	}

	public EBNF getEbnfOperator() {
		return ebnfOperator;
	}

	public IRule getEnclosingRule() {
		return ((IStatement)getParent()).getEnclosingRule();
	}

	public void setEbnfOperator(EBNF ebnfOperator) {
		this.ebnfOperator = ebnfOperator == null ? EBNF.NONE : ebnfOperator;
	}
}
