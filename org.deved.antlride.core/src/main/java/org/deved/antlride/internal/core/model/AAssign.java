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
import org.deved.antlride.core.model.IAssign;
import org.deved.antlride.core.model.IGrammar;
import org.deved.antlride.core.model.IRule;
import org.deved.antlride.core.model.ISourceElement;
import org.deved.antlride.core.model.IStatement;
import org.deved.antlride.core.model.IVariable;
import org.deved.antlride.core.model.ast.IModelElementVisitor;

public class AAssign extends AStatement implements IAssign {

	private IStatement statement;

	private ISourceElement operator;

	private IVariable variable;

	public AAssign(ISourceElement operator, IVariable variable,
			IStatement statement) {
		super(variable.sourceStart(), statement.sourceEnd());
		this.operator = operator;
		this.variable = variable;
		this.statement = statement;
		((AAbstractModelElement) variable).setParent(this);
		((AAbstractModelElement) statement).setParent(this);
	}

	public <E> E getAdapter(Class<E> adapter) {
		if (adapter == IAssign.class)
			return adapter.cast(this);
		if (adapter == IRule.class)
			return adapter.cast(getEnclosingRule());
		if (statement != null && adapter.isInstance(statement))
			return adapter.cast(statement);
		if (adapter == IVariable.class)
			return adapter.cast(variable);
		if (adapter == IGrammar.class)
			return adapter.cast(getEnclosingRule().getParent());
		return null;
	}

	public String getElementName() {
		return getOperator().getText().trim();
	}

	public ElementKind getElementKind() {
		return ElementKind.ASSIGN_OPERATOR;
	}

	public IStatement getStatement() {
		return statement;
	}

	public ISourceElement getOperator() {
		return operator;
	}

	public IVariable getVariable() {
		return variable;
	}

	public void traverse(IModelElementVisitor visitor) {
		if (visitor.visitAssign(this)) {
			variable.traverse(visitor);
			statement.traverse(visitor);
			visitor.endvisitAssign(this);
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(variable);
		builder.append(" ");
		builder.append(operator.getText());
		builder.append(" ");
		builder.append(statement);
		return builder.toString();
	}
}
