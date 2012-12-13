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
import org.deved.antlride.core.model.ICallExpression;
import org.deved.antlride.core.model.IGrammar;
import org.deved.antlride.core.model.IModelElement;
import org.deved.antlride.core.model.IParameter;
import org.deved.antlride.core.model.IParameters;
import org.deved.antlride.core.model.IRule;
import org.deved.antlride.core.model.ISourceElement;
import org.deved.antlride.core.model.ITargetAction;
import org.deved.antlride.core.model.ast.IModelElementVisitor;

public class AParameters extends AAbstractModelElement implements IParameters {

	private static final IParameter[] EMPTY_PARAMETERS = new IParameter[0];

	private IParameter[] parameters;

	private ATargetAction targetAction;

	public AParameters(IModelElement parent, ISourceElement action) {
		super(parent);
		setSourceStart(action.sourceStart());
		setSourceEnd(action.sourceEnd());
		targetAction = new ATargetAction(this, action);
	}

	public <E> E getAdapter(Class<E> adapter) {
		if (adapter == IParameters.class)
			return adapter.cast(this);
		ElementKind kind = getElementKind();
		switch (kind) {
		case RULE_PARAMETERS:
			if (adapter == IRule.class)
				return adapter.cast(getParent());
			if (adapter == ITargetAction.class)
				return adapter.cast(getAction());
			if (adapter == IGrammar.class)
				return adapter.cast(getParent().getParent());
			break;
		case CALL_PARAMETERS:
			if (adapter == ICallExpression.class)
				return adapter.cast(getParent());
			if (adapter == ITargetAction.class)
				return adapter.cast(getAction());
			if (adapter == IRule.class)
				return adapter.cast(((ICallExpression) getParent())
						.getEnclosingRule());
			if (adapter == IGrammar.class)
				return adapter.cast(((ICallExpression) getParent())
						.getEnclosingRule().getParent());
			break;
		}
		return null;
	}

	public ElementKind getElementKind() {
		ElementKind kind = parent.getElementKind();
		if (kind == ElementKind.RULE)
			return ElementKind.RULE_PARAMETERS;
		return ElementKind.CALL_PARAMETERS;
	}

	public String getElementName() {
		return "<parameters>";
	}

	public int getParametersCount() {
		return parameters.length;
	}

	public IParameter[] getParemeters() {
		return parameters;
	}

	public IParameter findParameter(String name) {
		for (int i = 0; i < parameters.length; i++) {
			IParameter parameter = parameters[i];
			if (parameter.getName().getText().equals(name)) {
				return parameter;
			}
		}
		return null;
	}

	public ITargetAction getAction() {
		return targetAction;
	}

	public IParameter getParemeter(int index) {
		return parameters[index];
	}

	public void setParemeters(IParameter[] parameters) {
		if (parameters == null || parameters.length == 0) {
			this.parameters = EMPTY_PARAMETERS;
		} else {
			this.parameters = parameters;
			for (int i = 0; i < parameters.length; i++) {
				((AParameter) this.parameters[i]).setParent(this);
			}
		}
	}

	public void traverse(IModelElementVisitor visitor) {
		ElementKind kind = getElementKind();
		if (kind == ElementKind.RULE_PARAMETERS) {
			if (visitor.visitRuleParameters(this)) {
				for (int i = 0; i < parameters.length; i++) {
					parameters[i].traverse(visitor);
				}
				visitor.endvisitRuleParameters(this);
			}
		} else {
			if (visitor.visitCallParameters(this)) {
				for (int i = 0; i < parameters.length; i++) {
					parameters[i].traverse(visitor);
				}
				visitor.endvisitCallParameters(this);
			}
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < parameters.length; i++) {
			builder.append(parameters[i]);
			if (i + 1 < parameters.length) {
				builder.append(", ");
			}
		}
		return builder.toString();
	}
}
