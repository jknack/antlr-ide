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
import org.deved.antlride.core.model.ISourceElement;
import org.deved.antlride.core.model.ITargetAction;
import org.deved.antlride.core.model.ITemplateParameter;
import org.deved.antlride.core.model.ast.IModelElementVisitor;

public class ATemplateParameter extends AStatement implements
		ITemplateParameter {

	private ISourceElement name;

	private ITargetAction targetAction;

	public ATemplateParameter(ISourceElement name, ISourceElement action) {
		this.name = name;
		sourceStart = name.sourceStart();
		sourceEnd = sourceStart == action.sourceEnd() ? sourceStart + 1
				: action.sourceEnd();
		targetAction = new ATargetAction(this, action);
	}
	
	public ISourceElement getName() {
		return name;
	}

	public <E> E getAdapter(Class<E> adapter) {
		if (adapter == ITemplateParameter.class)
			return adapter.cast(this);
		if (adapter == IRule.class)
			return adapter.cast(getEnclosingRule());
		if (adapter == IGrammar.class)
			return adapter.cast(getEnclosingRule().getParent());
		if (adapter == ITargetAction.class)
			return adapter.cast(targetAction);
		return null;
	}

	public ITargetAction getAction() {
		return targetAction;
	}

	public ElementKind getElementKind() {
		return ElementKind.TEMPLATE_PARAMETER;
	}

	public String getElementName() {
		return name.getText();
	}

	public void traverse(IModelElementVisitor visitor) {
		if (visitor.visitTemplateParameter(this)) {
			if (targetAction != null)
				targetAction.traverse(visitor);
			visitor.endvisitTemplateParameter(this);
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(getElementName());
		builder.append(" = ");
		builder.append(targetAction);
		return builder.toString();
	}
}
