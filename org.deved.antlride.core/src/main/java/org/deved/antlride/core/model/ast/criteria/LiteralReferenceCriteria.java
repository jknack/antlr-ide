/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.core.model.ast.criteria;

import org.deved.antlride.core.model.ICallExpression;
import org.deved.antlride.core.model.IModelElement;

class LiteralReferenceCriteria implements IModelElementCriteria {

	static final public IModelElementCriteria CRITERIA = new LiteralReferenceCriteria();

	private LiteralReferenceCriteria() {
	}

	public boolean accept(IModelElement element) {
		ICallExpression call = element.getAdapter(ICallExpression.class);
		if (call == null)
			return false;
		return call.getElementName().charAt(0) == '\'';
	}

}
