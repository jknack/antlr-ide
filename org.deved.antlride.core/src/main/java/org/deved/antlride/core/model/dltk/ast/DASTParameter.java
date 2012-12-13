/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.core.model.dltk.ast;

import org.eclipse.dltk.ast.declarations.Argument;
import org.eclipse.dltk.ast.references.SimpleReference;

public class DASTParameter extends Argument {
	
	private int kind;

	public DASTParameter(int kind, String text, int start, int end) {
		super(new SimpleReference(start, end, text), start, end, null, 0);
		this.kind = kind;
	}
	
	@Override
	public int getKind() {
		return kind;
	}
}
