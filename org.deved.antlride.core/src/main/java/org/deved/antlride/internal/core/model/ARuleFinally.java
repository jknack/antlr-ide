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
import org.deved.antlride.core.model.IRuleFinally;
import org.deved.antlride.core.model.ISourceElement;
import org.deved.antlride.core.model.ITargetAction;
import org.deved.antlride.core.model.ast.IModelElementVisitor;

public class ARuleFinally extends AAbstractModelElement implements IRuleFinally {

	private ATargetAction action;

	public ARuleFinally(ISourceElement action) {
		this.action = new ATargetAction(this, action);
	}

	public ITargetAction getAction() {
		return action;
	}

	@SuppressWarnings("unchecked")
	public <E> E getAdapter(Class<E> adapter) {
		if (adapter == IRuleFinally.class)
			return (E) this;
		if (adapter == IRule.class)
			return (E) getParent();
		if (adapter == IGrammar.class)
			return (E) getParent().getParent();
		return null;
	}

	public ElementKind getElementKind() {
		return ElementKind.RULE_FINALLY;
	}

	public String getElementName() {
		return "finally";
	}

	public void traverse(IModelElementVisitor visitor) {
		if (visitor.visitRuleFinally(this)) {
			visitor.endvisitRuleFinally(this);
		}
	}

}
