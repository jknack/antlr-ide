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
import org.deved.antlride.core.model.IModelElement;
import org.deved.antlride.core.model.IRule;
import org.deved.antlride.core.model.ISourceElement;
import org.deved.antlride.core.model.IStatementAction;
import org.deved.antlride.core.model.ITargetAction;
import org.deved.antlride.core.model.ast.IModelElementVisitor;

public class AStatementAction extends AStatement implements IStatementAction {
	private ITargetAction targetAction;
	
	private boolean forced;

	public AStatementAction(ISourceElement action) {
		setSourceStart(action.sourceStart());
		setSourceEnd(action.sourceEnd() + 1);
		String actionCode = action.getText().trim();
		forced = actionCode.startsWith("{{") && actionCode.endsWith("}}");
		targetAction = new ATargetAction(this, action);
	}
	
	public boolean isForced() {
		return forced;
	}

	public <E> E getAdapter(Class<E> adapter) {
		if (adapter == IStatementAction.class)
			return adapter.cast(this);
		if (adapter == IRule.class)
			return adapter.cast(getEnclosingRule());
		if (adapter == ITargetAction.class)
			return adapter.cast(targetAction);
		if (adapter == IGrammar.class)
			return adapter.cast(getEnclosingRule().getParent());
		return null;
	}

	public ITargetAction getAction() {
		return targetAction;
	}

	public ElementKind getElementKind() {
		return ElementKind.STATEMENT_ACTION;
	}

	@Override
	public void setParent(IModelElement parent) {
		super.setParent(parent);
		
	}

	public String getElementName() {
		return "<{...}>";
	}

	public void traverse(IModelElementVisitor visitor) {
		if (visitor.visitStatementAction(this)) {
			if (targetAction != null)
				targetAction.traverse(visitor);
			visitor.endvisitStatementAction(this);
		}
	}

	@Override
	public String toString() {
		return targetAction.toString();
	}
}
