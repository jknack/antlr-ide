/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.core.model.dltk.ast;

import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.declarations.FieldDeclaration;

public class DASTImport extends FieldDeclaration {

	private String text;
	private int kind;

	public DASTImport(String name, int kind, String text, int sourceStart, int sourceEnd, int nameStart, int nameEnd) {
		super(name, nameStart, nameEnd, sourceStart, sourceEnd);
		this.text = text;
		this.kind = kind;
	}

	public String getText() {
		return text;
	}
	
	@Override
	public int getKind() {
		return kind;
	}

	@Override
	public void traverse(ASTVisitor visitor) throws Exception {
		if(visitor.visit(this)) {
			visitor.endvisit(this);
		}
	}

}
