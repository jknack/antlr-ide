/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.core.model.dltk.ast;

import org.deved.antlride.core.model.ElementKind;
import org.eclipse.dltk.ast.expressions.CallArgumentsList;
import org.eclipse.dltk.ast.expressions.CallExpression;

public class DASTCallExpression extends CallExpression {

	public DASTCallExpression(String name, int start, int end,
			CallArgumentsList args) {
		super(start, end, null, name, args);
	}

	@Override
	public int getKind() {
		return ElementKind.CALL.ordinal();
	}
}
