/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.core.model.evaluation;

import java.util.Iterator;

import org.deved.antlride.core.model.IGrammar;

public interface IEvalElement extends Iterable<IEvalElement> {
	String getElementName();

	EvalElementKind getElementKind();

	int getElementCount();
	
	IEvalElement firstElement();
	
	IEvalElement lastElement();

	IEvalElement getElement(int index);
	
	IGrammar getGrammar();
	
	void clear();

	Iterator<IEvalElement> iterator();
	
	boolean isLeaf();
	
	boolean isRoot();
	
	IEvalElement getParent();
	
	Object getUserData();
	
	void setUserData(Object userData);
}
