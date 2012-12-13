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
import org.deved.antlride.core.model.IModelElement;
import org.deved.antlride.core.model.IReference;
import org.deved.antlride.core.model.IRule;
import org.deved.antlride.core.model.IScope;
import org.deved.antlride.core.model.IScopeReference;
import org.deved.antlride.core.model.ISourceElement;
import org.deved.antlride.core.model.ast.IModelElementVisitor;
import org.deved.antlride.core.model.dltk.ast.DASTScope;
import org.eclipse.dltk.ast.ASTNode;

public class AScopeReference extends AReference implements IScopeReference {
	private ISourceElement reference;
	private DASTScope node;

	public AScopeReference(IModelElement parent, IGrammarScope globalScope, ISourceElement reference) {
		super(parent, reference, globalScope);
		this.reference = reference;
	}

	@Override
	public <E> E getAdapter(Class<E> adapter) {
		if (adapter == IReference.class)
			return adapter.cast(this);
		if (adapter == IScope.class || adapter == IGrammarScope.class)
			return adapter.cast(getReference());
		if (adapter == IRule.class)
			return adapter.cast(getParent());
		if (adapter == IGrammar.class) {
			return adapter.cast(getParent().getAdapter(adapter));
		}
		if (adapter == ASTNode.class)
			return adapter.cast(getAST());
		return null;
	}

	public ElementKind getElementKind() {
		return ElementKind.SCOPE_REFERENCE;
	}

	public void traverse(IModelElementVisitor visitor) {
		if (visitor.visitRuleScopeReference(this)) {
			visitor.endvisitRuleScopeReference(this);
		}
	}

	@Override
	public String toString() {
		return reference.getText();
	}

	private DASTScope getAST() {
		if (node == null) {
			node = new DASTScope(getElementKind().ordinal(), getElementName(),
					sourceStart, sourceEnd, sourceStart, sourceEnd);
		}
		return node;
	}
}
