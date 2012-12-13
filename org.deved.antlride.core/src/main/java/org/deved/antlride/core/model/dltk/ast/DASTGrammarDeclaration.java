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
import org.eclipse.dltk.ast.declarations.TypeDeclaration;

public class DASTGrammarDeclaration extends TypeDeclaration {

	public DASTGrammarDeclaration(String name, int start, int end) {
		super(name, start, end, start, end);
		setModifier(ElementKind.GRAMMAR.ordinal());
	}
}
