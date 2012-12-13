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
import org.deved.antlride.core.model.IParameter;
import org.deved.antlride.core.model.ISourceElement;
import org.deved.antlride.core.model.ast.IModelElementVisitor;
import org.deved.antlride.core.model.dltk.ast.DASTParameter;
import org.eclipse.dltk.ast.ASTNode;

public class AParameter extends AAbstractModelElement implements IParameter {

	private ISourceElement name;

	private ISourceElement type;

	private DASTParameter node;

	public AParameter(ISourceElement name, ISourceElement type) {
		super(type == null ? name.sourceStart() : type.sourceStart(), name
				.sourceEnd());
		this.name = name;
		this.type = type;
	}

	public <E> E getAdapter(Class<E> adapter) {
		if (adapter == IParameter.class)
			return adapter.cast(getParent());
		if (adapter == ASTNode.class)
			return adapter.cast(getAST());
		return getParent().getAdapter(adapter);
	}

	private ASTNode getAST() {
		if (node == null) {
			node = new DASTParameter(getElementKind().ordinal(), toString(),
					sourceStart(), sourceEnd());
		}
		return node;
	}

	public ElementKind getElementKind() {
		ElementKind kind = parent.getElementKind();
		if (kind == ElementKind.RULE_PARAMETERS) {
			return ElementKind.RULE_PARAMETER;
		}
		return ElementKind.CALL_PARAMETER;
	}

	public String getElementName() {
		return name.getText();
	}

	public ISourceElement getName() {
		return name;
	}

	public ISourceElement getType() {
		return type;
	}

	public void traverse(IModelElementVisitor visitor) {
		ElementKind kind = getElementKind();
		if (kind == ElementKind.RULE_PARAMETER) {
			if (visitor.visitRuleParameter(this)) {
				visitor.endvisitRuleParameter(this);
			}
		} else {
			if (visitor.visitCallParameter(this)) {
				visitor.endvisitCallParameter(this);
			}
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		if (type != null) {
			// call parameter
			builder.append(type.getText());
			builder.append(" ");
		}
		builder.append(name.getText());
		return builder.toString();
	}
}
