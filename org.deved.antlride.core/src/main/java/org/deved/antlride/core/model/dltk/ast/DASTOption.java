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
import org.eclipse.dltk.ast.statements.Statement;

public class DASTOption extends Statement {

	private String text;
	
	private int kind;

	public DASTOption(String text, int kind, int sourceStart, int sourceEnd) {
		super(sourceStart, sourceEnd - 1);
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
