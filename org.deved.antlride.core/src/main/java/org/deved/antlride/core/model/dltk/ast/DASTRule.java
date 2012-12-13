/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.core.model.dltk.ast;

import java.util.ArrayList;
import java.util.List;

import org.deved.antlride.core.model.ElementKind;
import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.declarations.Argument;
import org.eclipse.dltk.ast.declarations.MethodDeclaration;

public class DASTRule extends MethodDeclaration {

	private List<ASTNode> decorators;

	public DASTRule(String text, int nameStart, int nameEnd, int declStart,
			int declEnd) {
		super(text, nameStart, nameEnd, declStart, declEnd);
		decorators = new ArrayList<ASTNode>(3);
	}

	public void addDecorator(ASTNode decorator) {
		decorators.add(decorator);
	}
	
	@Override
	public int getKind() {
		return ElementKind.RULE.ordinal();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void traverse(ASTVisitor visitor) throws Exception {
		if (visitor.visit(this)) {
			// Decorators
			for (ASTNode decorator : decorators) {
				decorator.traverse(visitor);
			}
			// Arguments
			List<Argument> arguments = getArguments();
			for (Argument argument : arguments) {
				argument.traverse(visitor);
			}

			// Body
			if (getBody() != null) {
				getBody().traverse(visitor);
			}

			visitor.endvisit(this);
		}
	}
}
