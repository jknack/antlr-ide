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
import org.deved.antlride.core.model.ISourceElement;
import org.deved.antlride.core.model.IToken;
import org.deved.antlride.core.model.ITokenName;
import org.deved.antlride.core.model.ITokenValue;
import org.deved.antlride.core.model.ast.IModelElementVisitor;
import org.deved.antlride.core.model.dltk.ast.DASTToken;
import org.eclipse.dltk.ast.ASTNode;

public class AToken extends AAbstractModelElement implements IToken {

	private ITokenName name;

	private ITokenValue value;

	private String text;

	private DASTToken node;

	public AToken(ISourceElement name, ISourceElement value) {
		super(name.sourceStart(), value == null ? name.sourceEnd() : value
				.sourceEnd());
		this.name = new ATokenName(this, name);
		// string representation
		StringBuilder builder = new StringBuilder();
		builder.append(name.getText());
		if (value != null) {
			builder.append(" = ");
			this.value = new ATokenValue(this, value);
			builder.append(value.getText());
		}
		text = builder.toString();
	}

	public boolean hasValue() {
		return value != null;
	}

	public <E> E getAdapter(Class<E> adapter) {
		if (adapter == IToken.class)
			return adapter.cast(this);
		if (adapter == ASTNode.class)
			return adapter.cast(getAST());
		return getParent().getAdapter(adapter);
	}

	private DASTToken getAST() {
		if (node == null) {
			ITokenName name = getName();
			node = new DASTToken(getElementName(), getElementKind().ordinal(),
					getText(), sourceStart(), sourceEnd(), name.sourceStart(),
					name.sourceEnd());
		}
		return node;
	}

	public ElementKind getElementKind() {
		return ElementKind.TOKEN;
	}

	public String getElementName() {
		return name.getText();
	}

	public ITokenName getName() {
		return name;
	}

	public String getText() {
		return text;
	}

	public ITokenValue getValue() {
		return value;
	}

	public String getValueAsString() {
		return value == null ? null : value.getText();
	}

	public void traverse(IModelElementVisitor visitor) {
		if (visitor.visitToken(this)) {
			name.traverse(visitor);
			if (value != null) {
				value.traverse(visitor);
			}
			visitor.endvisitToken(this);
		}
	}

	@Override
	public String toString() {
		return text;
	}
}
