/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.internal.core.model.evaluation;

import org.deved.antlride.core.model.IGrammar;
import org.deved.antlride.core.model.evaluation.EvalElementKind;
import org.deved.antlride.core.model.evaluation.IEvalElement;
import org.deved.antlride.core.model.evaluation.IExceptionEvalElement;

public class AExceptionEvalElement extends AEvalElement implements IExceptionEvalElement {

	private int fLine;
	
	private int fColumn;
	
	private String fMessage;
	
	public AExceptionEvalElement(IGrammar grammar, IEvalElement parent, String exception, String text, int line, int colum) {
		super(grammar, (AEvalElement) parent, exception, EvalElementKind.EXCEPTION);
		this.fLine = line;		
		this.fColumn = colum;
		this.fMessage = text;
	}

	public int getColumn() {
		return fColumn;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof IExceptionEvalElement) {
			IExceptionEvalElement eee = (IExceptionEvalElement) obj;
			return getElementName().equals(eee.getElementName()) && getMessage().equals(eee.getMessage());
		}
		return false;
	}

	public int getLine() {
		return fLine;
	}

	public String getMessage() {
		return fMessage;
	}

}
