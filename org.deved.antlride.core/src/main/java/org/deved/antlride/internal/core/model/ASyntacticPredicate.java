/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.internal.core.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.deved.antlride.core.model.ElementKind;
import org.deved.antlride.core.model.ICompositeStatement;
import org.deved.antlride.core.model.IGrammar;
import org.deved.antlride.core.model.IRule;
import org.deved.antlride.core.model.IStatement;
import org.deved.antlride.core.model.ISyntacticPredicate;
import org.deved.antlride.core.model.ast.IModelElementVisitor;

public class ASyntacticPredicate extends AStatement implements
		ISyntacticPredicate, ICompositeStatement {

	private IStatement predicate;

	private IStatement condition;

	private List<IStatement> statements;

	public ASyntacticPredicate(IStatement condition) {
		super(condition.sourceStart(), condition.sourceEnd());
		this.condition = condition;
		statements = new ArrayList<IStatement>();
		((AAbstractModelElement) condition).setParent(this);
	}

	public IStatement getCondition() {
		return condition;
	}

	public <E> E getAdapter(Class<E> adapter) {
		if (adapter == ISyntacticPredicate.class)
			return adapter.cast(this);
		if (adapter == IRule.class)
			return adapter.cast(getEnclosingRule());
		if (adapter == IGrammar.class)
			return adapter.cast(getEnclosingRule().getParent());
		return null;
	}

	public ElementKind getElementKind() {
		return ElementKind.SYN_PRED;
	}

	public String getElementName() {
		StringBuilder builder = new StringBuilder();
		builder.append(condition);
		builder.append(" => ");
		return builder.toString();
	}

	public IStatement getPredicate() {
		return predicate;
	}

	public void setPredicate(IStatement predicate) {
		this.predicate = predicate;
		((AStatement) predicate).setParent(this);
	}

	public void traverse(IModelElementVisitor visitor) {
		if (visitor.visitSyntacticPredicate(this)) {
			if (visitor.visitSyntacticPredicateCondition(condition)) {
				condition.traverse(visitor);
				visitor.endvisitSyntacticPredicateCondition(condition);
			}
			predicate.traverse(visitor);
			visitor.endvisitSyntacticPredicate(this);
		}
	}

	@Override
	public String toString() {
		return getCondition() + " => " + getPredicate();
	}

	public void add(int index, IStatement statement) {
		throw new UnsupportedOperationException();
	}

	public void add(IStatement statement) {
		throw new UnsupportedOperationException();
	}

	public int find(IStatement statement) {
		return statements.indexOf(statement);
	}

	public IStatement first() {
		return predicate;
	}

	public IStatement get(int index) {
		return statements.get(index);
	}

	public IStatement last() {
		return predicate;
	}

	public void remove(IStatement statement) {
		throw new UnsupportedOperationException();
	}

	public IStatement remove(int index) {
		throw new UnsupportedOperationException();
	}

	public void set(int index, IStatement statement) {
		throw new UnsupportedOperationException();
	}

	public int size() {
		return statements.size();
	}

	public Iterator<IStatement> iterator() {
		return statements.iterator();
	}
}
