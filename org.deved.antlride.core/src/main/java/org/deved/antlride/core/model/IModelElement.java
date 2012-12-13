/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 *******************************************************************************/
package org.deved.antlride.core.model;

import org.deved.antlride.core.model.ast.IModelElementVisitor;

public interface IModelElement {
	
	<E> E getAdapter(Class<E> adapter);
	
	ElementKind getElementKind();
	
	String getElementName();
	
	IModelElement getParent();

	boolean isIn(int position);
	
	int sourceStart();

	int sourceEnd();
	
	void traverse(IModelElementVisitor visitor);		
}
