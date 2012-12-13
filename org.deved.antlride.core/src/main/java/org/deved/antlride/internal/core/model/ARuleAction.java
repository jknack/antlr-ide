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
import org.deved.antlride.core.model.IRuleAction;
import org.deved.antlride.core.model.ISourceElement;
import org.deved.antlride.core.model.ITargetAction;
import org.deved.antlride.core.model.ast.IModelElementVisitor;
import org.deved.antlride.core.model.dltk.ast.DASTRuleAction;
import org.eclipse.dltk.ast.ASTNode;

public class ARuleAction extends AAbstractModelElement implements IRuleAction {

	private ITargetAction targetAction;

	private ISourceElement name;

	private DASTRuleAction node;

	public ARuleAction(IRule rule, ISourceElement name, ISourceElement action,
			int sourceStart, int sourceEnd) {
		super(rule, sourceStart, sourceEnd);
		this.name = name;
		this.targetAction = new ATargetAction(this, action);
	}

	public <E> E getAdapter(Class<E> adapter) {
		if (adapter == IRuleAction.class)
			return adapter.cast(this);
		if (adapter == IRule.class)
			return adapter.cast(getParent());
		if (adapter == IGrammar.class)
			return adapter.cast(getParent().getParent());
		if (adapter == ITargetAction.class)
			return adapter.cast(getAction());
		if (adapter == ASTNode.class)
			return adapter.cast(getAST());
		return null;
	}

	private DASTRuleAction getAST() {
		if (node == null) {
			node = new DASTRuleAction(getText(), getElementKind().ordinal(),
					sourceStart(), getName().sourceEnd(), sourceStart(),
					sourceEnd());
		}
		return node;
	}

	@Override
	public void setParent(IModelElement parent) {
		super.setParent(parent);		
	}

	public ITargetAction getAction() {
		return targetAction;
	}

	public ElementKind getElementKind() {
		return ElementKind.RULE_ACTION;
	}

	public String getElementName() {
		return name.getText();
	}

	public ISourceElement getName() {
		return name;
	}

	public String getText() {
		return name.getText();
	}

	public void traverse(IModelElementVisitor visitor) {
		if (visitor.visitRuleAction(this)) {
			if (targetAction != null) {
				targetAction.traverse(visitor);
			}
			visitor.endvisitRuleAction(this);
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("@");
		builder.append(name.getText());
		builder.append(targetAction.getText());
		builder.append("\n");
		return builder.toString();
	}
}
