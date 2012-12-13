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

public class DASTOptions extends Statement {

	private DASTOption[] options;
	
	private int kind;
	
	public DASTOptions(int kind, int sourceStart, int sourceEnd, DASTOption[] options) {
		super(sourceStart, sourceEnd);
		this.options = options;
		this.kind = kind;
	}
	
	@Override
	public int getKind() {
		return kind;
	}	
	
	public String getName() {
		return "options";
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
			for (int i = 0; i < options.length; i++) {
				options[i].traverse(visitor);
			}
			visitor.endvisit(this);
		}
	}

}
