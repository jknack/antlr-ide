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
import org.deved.antlride.core.model.IModelElement;
import org.deved.antlride.core.model.IReference;
import org.deved.antlride.core.model.IScope;
import org.deved.antlride.core.model.IScopeReference;
import org.deved.antlride.core.model.ISourceElement;
import org.deved.antlride.core.model.ast.IModelElementVisitor;

public class AReference extends AAbstractModelElement implements IReference {

	private IModelElement reference;

	private ISourceElement referenceName;

	public AReference(IModelElement parent, ISourceElement referenceName,
			IModelElement reference) {
		super(parent, referenceName.sourceStart(),
				referenceName.sourceEnd() + 1);// plus $
		this.referenceName = referenceName;
		this.reference = reference;
	}

	public <E> E getAdapter(Class<E> adapter) {
		if (adapter == IReference.class)
			return adapter.cast(this);
		if (reference != null && adapter.isInstance(reference))
			return adapter.cast(reference);
		if (adapter == IScope.class && reference instanceof IScopeReference)
			return reference.getAdapter(adapter);
		return getParent().getAdapter(adapter);
	}

	public IModelElement getReference() {
		return reference;
	}

	public ISourceElement getReferenceName() {
		return referenceName;
	}

	public boolean exists() {
		return reference != null;
	}

	public ElementKind getElementKind() {
		// if (exists()) {
		// ElementKind kind = reference.getElementKind();
		// if (kind == ElementKind.GRAMMAR_SCOPE)
		// return ElementKind.SCOPE_REFERENCE;
		// if (kind == ElementKind.GRAMMAR_SCOPE_ATTRIBUTE)
		// return ElementKind.SCOPE_ATTRIBUTE_REFERENCE;
		// if (kind == ElementKind.RULE_SCOPE)
		// return ElementKind.RULE_SCOPE_REFERENCE;
		// if (kind == ElementKind.RULE_SCOPE_ATTRIBUTE)
		// return ElementKind.RULE_SCOPE_ATTRIBUTE_REFERENCE;
		// if (kind == ElementKind.CALL)
		// return ElementKind.RULE_REFERENCE;
		// }
		return ElementKind.REFERENCE;
	}

	public String getElementName() {
		return referenceName.getText();
	}

	public void traverse(IModelElementVisitor visitor) {
		if (visitor.visitReference(this)) {
			visitor.endvisitReference(this);
		}
	}

	@Override
	public String toString() {
		return "$" + getElementName();
	}
}
