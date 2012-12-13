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
import org.deved.antlride.core.model.IRule;
import org.deved.antlride.core.model.IRuleCatch;
import org.deved.antlride.core.model.ISourceElement;
import org.deved.antlride.core.model.ITargetAction;
import org.deved.antlride.core.model.ast.IModelElementVisitor;

public class ARuleCatch extends AAbstractModelElement implements IRuleCatch {

	private String exception;

	private ATargetAction action;

	public ARuleCatch(String exception,
			ISourceElement action) {
		this.exception = exception;
		this.action = new ATargetAction(this, action);
	}

	public ITargetAction getAction() {
		return action;
	}

	public String getException() {
		return exception;
	}

	@SuppressWarnings("unchecked")
	public <E> E getAdapter(Class<E> adapter) {
		if (adapter == IRuleCatch.class)
			return (E) this;
		if (adapter == IRule.class)
			return (E) getParent();
		if (adapter == IGrammar.class)
			return (E) getParent().getParent();
		return null;
	}

	public ElementKind getElementKind() {
		return ElementKind.RULE_CATCH;
	}

	public String getElementName() {
		return "catch";
	}

	public void traverse(IModelElementVisitor visitor) {
		if(visitor.visitRuleCatch(this)) {
			visitor.endvisitRuleCatch(this);
		}
	}

}
