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
import org.deved.antlride.core.model.IGrammarAction;
import org.deved.antlride.core.model.ISourceElement;
import org.deved.antlride.core.model.ast.IModelElementVisitor;
import org.deved.antlride.core.model.dltk.ast.DASTGrammarAction;
import org.eclipse.dltk.ast.ASTNode;

public class AGrammarAction extends AAbstractModelElement implements
		IGrammarAction {

	private ISourceElement code;

	private ISourceElement scope;

	private ISourceElement name;

	private String text;

	private DASTGrammarAction node;

	public AGrammarAction(ISourceElement scope, ISourceElement name,
			ISourceElement code, int sourceStart, int sourceEnd, int lineStart, int lineEnd) {
		super(sourceStart, sourceEnd);
		this.scope = scope;
		this.name = name;
		this.code = code;
		// string representation
		StringBuilder builder = new StringBuilder();
		if (scope != null) {
			builder.append(scope.getText());
			builder.append("::");
		}
		if (name != null) {
			if (scope == null) {
			}
			builder.append(name.getText());
			builder.append(" ");
		}
		text = builder.toString();
	}

	public <E> E getAdapter(Class<E> adapter) {
		if (adapter == IGrammarAction.class)
			return adapter.cast(this);
		if (adapter == IGrammar.class)
			return adapter.cast(getParent());
		if (adapter == ASTNode.class)
			return adapter.cast(getAST());
		return null;
	}

	private DASTGrammarAction getAST() {
		if (node == null) {
			int sourceStart = sourceStart();
			int sourceEnd = sourceEnd();
			ISourceElement se = getScope();
			if (se == null) {
				se = getName();
			}
			int nameStart = se.sourceStart();
			int nameEnd = getName().sourceEnd();
			node = new DASTGrammarAction(getText(), sourceStart, sourceEnd,
					nameStart, nameEnd, getElementKind().ordinal());
		}
		return node;
	}

	public ISourceElement getAction() {
		return code;
	}

	public ElementKind getElementKind() {
		return ElementKind.GRAMMAR_ACTION;
	}

	public String getElementName() {
		StringBuilder elementName = new StringBuilder();
		if (scope != null) {
			elementName.append(scope.getText());
			elementName.append("::");
		}
		elementName.append(name.getText());
		return elementName.toString();
	}

	public ISourceElement getName() {
		return name;
	}

	public ISourceElement getScope() {
		return scope;
	}

	public String getText() {
		return text;
	}

	public void traverse(IModelElementVisitor visitor) {
		if (visitor.visitGrammarAction(this)) {
			visitor.endvisitGrammarAction(this);
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("@");
		builder.append(text);
		builder.append(code.getText());
		builder.append("\n");
		return builder.toString();
	}
}
