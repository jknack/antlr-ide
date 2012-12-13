/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.internal.core.model;

import org.deved.antlride.core.model.ElementKind;
import org.deved.antlride.core.model.IGrammar;
import org.deved.antlride.core.model.IGrammarScope;
import org.deved.antlride.core.model.ISourceElement;
import org.deved.antlride.core.model.ast.IModelElementVisitor;
import org.deved.antlride.core.model.dltk.ast.DASTScope;
import org.eclipse.dltk.ast.ASTNode;

public class AGrammarScope extends AScope implements IGrammarScope {
	private ISourceElement name;

	private DASTScope node;

	public AGrammarScope(IGrammar parent, ISourceElement name) {
		this.name = name;
		setParent(parent);
	}

	public <E> E getAdapter(Class<E> adapter) {
		if (adapter == IGrammarScope.class)
			return adapter.cast(this);
		if (adapter == IGrammar.class)
			return adapter.cast(getParent());
		if (adapter == ASTNode.class)
			return adapter.cast(getAST());
		return null;
	}

	private DASTScope getAST() {
		if (node == null) {
			String scopeName = name.getText();
			int nameStart = name.sourceStart();
			int nameEnd = nameStart + scopeName.length();

			node = new DASTScope(getElementKind().ordinal(), scopeName,
					nameStart, nameEnd, sourceStart, sourceEnd);

			attributes(node);
		}
		return node;
	}

	public ElementKind getElementKind() {
		return ElementKind.GRAMMAR_SCOPE;
	}

	public String getElementName() {
		return name.getText();
	}

	public ISourceElement getName() {
		return name;
	}

	public void traverse(IModelElementVisitor visitor) {
		if (visitor.visitGrammarScope(this)) {
			if (attributes != null && attributes.length > 0)
				for (int i = 0; i < attributes.length; i++) {
					attributes[i].traverse(visitor);
				}
			visitor.endvisitGrammarScope(this);
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("scope ");
		builder.append(name.getText());
		builder.append("{\n");
		for (int i = 0; i < attributes.length; i++) {
			builder.append(attributes[i]);
		}
		builder.append("\n}\n");
		return builder.toString();
	}
}
