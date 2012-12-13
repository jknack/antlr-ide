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
import org.deved.antlride.core.model.evaluation.ITokenEvalElement;

public class ATokenEvalElement extends AEvalElement implements ITokenEvalElement {

	private int line;
	
	private int column;
	
	public ATokenEvalElement(IGrammar grammar, IEvalElement parent, String text, int line, int colum) {
		super(grammar, (AEvalElement) parent, mask(text), EvalElementKind.TOKEN);
		this.line = line;
		this.column = colum;
	}
	
	private static String mask(String text) {
		if(text == null) {
			return " ";
		}
		return text.replace("\n", "\\n").replace("\t", "\\t").replace("\t", "\\t").replace("\r", "\\r");
	}

	public int getColumn() {
		return column;
	}

	public int getLine() {
		return line;
	}

	public String getText() {
		return getElementName();
	}

}
