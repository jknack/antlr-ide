/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.core.model.ast.criteria;

import java.util.regex.Pattern;

import org.deved.antlride.core.model.IModelElement;

class ModelElementPatternCriteria implements IModelElementCriteria {
	
	private String pattern;
	
	public ModelElementPatternCriteria(String pattern) {
		this.pattern = pattern;
	}
	
	public boolean accept(IModelElement element) {
		return Pattern.matches(pattern, element.getElementName());
	}

}
