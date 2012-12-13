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

public class DASTGrammarAction extends Statement {

	private int kind;

	private int nameStart;

	private int nameEnd;

	private String text;

	public DASTGrammarAction(String text, int sourceStart, int sourceEnd, int nameStart, int nameEnd, int kind) {		
		super(sourceStart, sourceEnd + 1);
		this.kind = kind;
		this.nameStart = nameStart;
		this.nameEnd = nameEnd;
		this.text = text;
	}

	public int getNameStart() {
		return nameStart;
	}

	public String getText() {
		return text;
	}
	
	public int getNameEnd() {
		return nameEnd;
	}

	@Override
	public int getKind() {
		return kind;
	}

	@Override
	public void traverse(ASTVisitor visitor) throws Exception {
		if (visitor.visit(this)) {
			visitor.endvisit(this);
		}
	}
}
