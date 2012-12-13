/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.core.model.evaluation;

import org.deved.antlride.core.model.IGrammar;

public interface IResultEvalElement {
	boolean fail();
	
	String getResultDescription();
	
	IEvalElement getResult();
	
	IGrammar getGrammar();
}
