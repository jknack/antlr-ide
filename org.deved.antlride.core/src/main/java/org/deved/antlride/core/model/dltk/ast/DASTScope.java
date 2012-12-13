/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.core.model.dltk.ast;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.declarations.TypeDeclaration;

public class DASTScope extends TypeDeclaration {

	private int kind;

	public DASTScope(int kind, String name, int nameStart, int nameEnd,
			int start, int end) {
		super(name, nameStart, nameEnd, start, end);
		this.kind = kind;
		//setModifier(ElementKind.GRAMMAR_SCOPE.ordinal());
	}

	@Override
	public int getKind() {
		return kind;
	}

	@Override
	public void traverse(ASTVisitor visitor) throws Exception {
		if (visitor.visit(this)) {
			if (this.fVariables != null) {
				for(int i = 0; i < fVariables.size(); i++) {
					ASTNode childAST = (ASTNode) fVariables.get(i);
					childAST.traverse(visitor);
				}
			}
			visitor.endvisit(this);
		}
	}
}
