/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.internal.core.model;

import org.deved.antlride.core.model.IModelElement;
import org.deved.antlride.core.model.IRule;

public class ARuleBody extends ABlock {

	private IRule enclosingRule;

	public ARuleBody() {
	}

	@Override
	public IRule getEnclosingRule() {
		return enclosingRule;
	}

	@Override
	public void setParent(IModelElement parent) {
		super.setParent(parent);
		this.enclosingRule = ((IRule) parent);
	}

}
