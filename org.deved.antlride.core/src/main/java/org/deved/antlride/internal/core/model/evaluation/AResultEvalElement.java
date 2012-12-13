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
import org.deved.antlride.core.model.evaluation.IEvalElement;
import org.deved.antlride.core.model.evaluation.IResultEvalElement;

public class AResultEvalElement implements IResultEvalElement {

	private boolean fail;
	private IEvalElement result;
	private String resultDescription;
	private IGrammar grammar;

	public AResultEvalElement(IGrammar grammar, boolean fail, String resultDescription, IEvalElement result) {
		this.fail = fail;
		this.result = result;
		this.grammar = grammar;
		this.resultDescription = resultDescription;
	}
	
	public IGrammar getGrammar() {
		return grammar;
	}
	
	public boolean fail() {
		return fail;
	}

	public IEvalElement getResult() {
		return result;
	}

	public String getResultDescription() {
		return resultDescription;
	}

}
