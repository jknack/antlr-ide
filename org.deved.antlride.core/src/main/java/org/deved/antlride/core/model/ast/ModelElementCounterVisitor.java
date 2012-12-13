/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.core.model.ast;

import org.deved.antlride.core.model.IModelElement;
import org.deved.antlride.core.model.ast.criteria.IModelElementCriteria;

public class ModelElementCounterVisitor extends AAbstractModelElementVisitor {
	private int count;

	private IModelElement input;

	private IModelElementCriteria criteria;

	public ModelElementCounterVisitor(IModelElementCriteria criteria) {
		this.criteria = criteria;
	}
	
	public ModelElementCounterVisitor() {
		this(null);
	}

	@Override
	public void accept(IModelElement node) {
		count = 0;
		input = node;
		super.accept(node);
	}

	@Override
	protected void in(IModelElement element) {
		if (input != element) {
			if (criteria == null || criteria.accept(element)) {
				count++;
			}
		}
		super.in(element);
	}

	public int getResult() {
		return count;
	}
}
