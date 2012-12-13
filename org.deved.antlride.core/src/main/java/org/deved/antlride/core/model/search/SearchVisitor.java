/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.core.model.search;

import org.deved.antlride.core.model.IModelElement;
import org.deved.antlride.core.model.ast.AAbstractModelElementVisitor;

class SearchVisitor extends AAbstractModelElementVisitor {

	private int position;
	
	private IModelElement element;
	
	private IModelElement input;

	public SearchVisitor(int position) {
		this.position = position;
	}

	@Override
	public void accept(IModelElement node) {
		input = node;
		element = null;		
		super.accept(node);
	}

	public IModelElement getResult() {
		return element;
	}

	@Override
	protected void in(IModelElement element) {
		if (input != element)
			if (element.isIn(position)) {
				this.element = (element);
				enabled = false;
			}
	}
}
