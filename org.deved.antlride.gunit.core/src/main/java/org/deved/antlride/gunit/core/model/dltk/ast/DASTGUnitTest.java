/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.gunit.core.model.dltk.ast;

import org.eclipse.dltk.ast.declarations.MethodDeclaration;

public class DASTGUnitTest extends MethodDeclaration {	
	
	public DASTGUnitTest(String name, int nameStart, int nameEnd,
			int declStart, int declEnd) {
		super(name, nameStart, nameEnd, declStart, declEnd);
	}

	public DASTGUnitTest(String name, int start, int end) {
		this( name, start, end, start, end);
	}
	
}
