/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.internal.core.model;

import java.util.List;

import org.deved.antlride.core.model.ElementKind;
import org.deved.antlride.core.model.IGrammar;
import org.deved.antlride.core.model.IRule;
import org.deved.antlride.core.model.ISourceElement;
import org.deved.antlride.core.model.ITargetAction;
import org.deved.antlride.core.model.ITemplate;
import org.deved.antlride.core.model.ITemplateParameter;
import org.deved.antlride.core.model.ast.IModelElementVisitor;

public class ATemplate extends AStatement implements ITemplate {

	private ISourceElement name;

	private final static ITemplateParameter[] EMPTY_PARAMETERS = new ITemplateParameter[0];

	private ITemplateParameter[] parameters = EMPTY_PARAMETERS;

	private ISourceElement inlineTemplate;

	private ATargetAction action;

	private boolean simpleActionTemplate;

	public ATemplate() {
	}

	public ITargetAction getAction() {
		return action;
	}

	public ISourceElement getName() {
		return name;
	}

	public boolean isInline() {
		return inlineTemplate != null;
	}

	public void setName(ISourceElement name) {
		this.name = name;
	}

	public void setAction(ISourceElement action) {
		this.action = new ATargetAction(this, action);
	}

	public <E> E getAdapter(Class<E> adapter) {
		if (adapter == ITemplate.class)
			return adapter.cast(this);
		if (adapter == IRule.class)
			return adapter.cast(getEnclosingRule());
		if (adapter == IGrammar.class)
			return adapter.cast(getEnclosingRule().getParent());
		return null;
	}

	public ISourceElement getInlineTemplate() {
		return inlineTemplate;
	}

	public boolean hasParameters() {
		return parameters != null && parameters.length > 0;
	}

	public ITemplateParameter[] getParameters() {
		return parameters;
	}

	public ElementKind getElementKind() {
		return ElementKind.TEMPLATE;
	}

	public String getElementName() {
		if (name != null) {
			return name.getText();
		}
		return "unamedTemplate";
	}

	public boolean isNamed() {
		return name != null;
	}

	public void traverse(IModelElementVisitor visitor) {
		if (visitor.visitTemplate(this)) {
			if (hasParameters()) {
				for (int i = 0; i < parameters.length; i++) {
					parameters[i].traverse(visitor);
				}
			}
			visitor.endvisitTemplate(this);
		}
	}

	public void setParameters(List<ITemplateParameter> parameters) {
		if (parameters != null && parameters.size() > 0) {
			this.parameters = new ITemplateParameter[parameters.size()];
			this.parameters = parameters.toArray(this.parameters);
			for (ITemplateParameter templateParameter : parameters) {
				((AAbstractModelElement) templateParameter).setParent(this);
			}
		}
	}

	public void setInlineTemplate(ISourceElement inlineTemplate) {
		this.inlineTemplate = inlineTemplate;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		if (name != null) {
			builder.append(name.getText());
		}
		if (action != null) {
			if (isSimpleActionTemplate()) {
				builder.append(action);
			} else {
				builder.append("(").append(action).append(")");
			}
		}
		if(!isSimpleActionTemplate()) {
			builder.append('(');
		}
		if (hasParameters()) {
			
			for (int i = 0; i < parameters.length - 1; i++) {
				builder.append(parameters[i]);
				builder.append(", ");
			}
			builder.append(parameters[parameters.length - 1]);
		}
		if(!isSimpleActionTemplate()) {
			builder.append(')');
		}
		if (isInline()) {
			builder.append("\n");
			builder.append(inlineTemplate.getText());
		}
		return builder.toString();
	}

	public boolean isSimpleActionTemplate() {
		return simpleActionTemplate;
	}
	
	public void setSimpleActionTemplate(boolean simpleActionTemplate) {
		this.simpleActionTemplate = simpleActionTemplate;
	}
}
