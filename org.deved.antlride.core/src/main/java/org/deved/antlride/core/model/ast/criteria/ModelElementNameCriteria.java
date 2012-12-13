/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.core.model.ast.criteria;

import org.deved.antlride.core.model.IModelElement;

class ModelElementNameCriteria implements IModelElementCriteria {
	
	public static final int EQ_OPERATOR = 1;
	
	public static final int SW_OPERATOR = 2;
	
	private String elementName;
	
	private int operator;
	
	public ModelElementNameCriteria(int operator, String elementName) {
		this.operator = operator;
		this.elementName = elementName;
	}
	
	public boolean accept(IModelElement element) {
		if(operator == EQ_OPERATOR)
			return element.getElementName().equals(elementName);
		return element.getElementName().startsWith(elementName);
	}

}
