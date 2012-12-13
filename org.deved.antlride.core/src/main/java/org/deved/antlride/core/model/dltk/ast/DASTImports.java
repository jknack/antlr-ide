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

public class DASTImports extends Statement {

	private DASTImport[] imports;
	private int kind;
	
	public DASTImports(int kind, int sourceStart, int sourceEnd, DASTImport[] imports) {
		super(sourceStart, sourceEnd);
		this.imports = imports;
		this.kind = kind;
	}
	
	@Override
	public int getKind() {
		return kind;
	}	
	
	public String getName() {
		return "import";
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
			for (int i = 0; i < imports.length; i++) {
				imports[i].traverse(visitor);
			}
			visitor.endvisit(this);
		}
	}

}
