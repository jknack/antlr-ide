/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.internal.core.model;

import java.util.Collection;

import org.deved.antlride.core.model.ElementKind;
import org.deved.antlride.core.model.IGrammar;
import org.deved.antlride.core.model.IToken;
import org.deved.antlride.core.model.ITokens;
import org.deved.antlride.core.model.ast.IModelElementVisitor;
import org.deved.antlride.core.model.dltk.ast.DASTToken;
import org.deved.antlride.core.model.dltk.ast.DASTTokens;
import org.eclipse.dltk.ast.ASTNode;

public class ATokens extends AAbstractModelElement implements ITokens {

	private static final IToken[] EMPTY_TOKENS = new IToken[0];

	private IToken[] tokens;

	private DASTTokens node;

	public ATokens(IGrammar parent, int sourceStart, int sourceEnd) {
		super(parent, sourceStart, sourceEnd);
	}

	public IToken findToken(String name) {
		for (int i = 0; i < tokens.length; i++) {
			IToken token = tokens[i];
			if (token.getName().getText().equals(name)) {
				return token;
			}
		}
		return null;
	}

	public <E> E getAdapter(Class<E> adapter) {
		if (adapter == ITokens.class)
			return adapter.cast(this);
		if (adapter == IGrammar.class)
			return adapter.cast(getParent());
		if (adapter == ASTNode.class)
			return adapter.cast(getAST());
		return null;
	}

	private DASTTokens getAST() {
		if (node == null) {
			IToken[] tokens = getTokens();
			DASTToken[] astTokens = new DASTToken[tokens.length];
			for (int i = 0; i < tokens.length; i++) {
				astTokens[i] = (DASTToken) tokens[i].getAdapter(ASTNode.class);
			}
			node = new DASTTokens(getElementKind().ordinal(), sourceStart(),
					sourceEnd(), astTokens);
		}
		return node;
	}

	public ElementKind getElementKind() {
		return ElementKind.TOKENS;
	}

	public String getElementName() {
		return "<tokens>";
	}

	public IToken[] getTokens() {
		return tokens;
	}

	public void traverse(IModelElementVisitor visitor) {
		if (visitor.visitTokens(this)) {
			for (int i = 0; i < tokens.length; i++) {
				tokens[i].traverse(visitor);
			}
			visitor.endvisitTokens(this);
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("tokens {\n");
		for (int i = 0; i < tokens.length; i++) {
			builder.append("  ");
			builder.append(tokens[i]);
			builder.append(";\n");
		}
		builder.append("}\n");
		return builder.toString();
	}

	public void setTokens(Collection<IToken> tokens) {
		if (tokens == null || tokens.size() == 0) {
			this.tokens = EMPTY_TOKENS;
		} else {
			this.tokens = tokens.toArray(new IToken[tokens.size()]);
			for (IToken token : tokens) {
				((AToken) token).setParent(this);
			}
		}
	}
}
