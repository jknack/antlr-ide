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

public class DASTTokens extends Statement {

	private DASTToken[] tokens;
	private int kind;
	
	public DASTTokens(int kind, int sourceStart, int sourceEnd, DASTToken[] tokens) {
		super(sourceStart, sourceEnd);
		this.tokens = tokens;
		this.kind = kind;
	}
	
	@Override
	public int getKind() {
		return kind;
	}	
	
	public String getName() {
		return "tokens";
	}

	public int getNameStart() {
		return sourceStart();
	}

	public int getNameEnd() {
		return getNameStart() + getName().length();
	}

	@Override
	public void traverse(ASTVisitor visitor) throws Exception {
		if(visitor.visit(this)) {
			for (int i = 0; i < tokens.length; i++) {
				tokens[i].traverse(visitor);
			}
			visitor.endvisit(this);
		}
	}

}
