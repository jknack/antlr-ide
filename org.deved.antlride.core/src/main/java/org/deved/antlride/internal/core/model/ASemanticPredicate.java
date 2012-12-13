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
import org.deved.antlride.core.model.ISemanticPredicate;
import org.deved.antlride.core.model.ISourceElement;
import org.deved.antlride.core.model.IStatement;
import org.deved.antlride.core.model.ast.IModelElementVisitor;

public class ASemanticPredicate extends AStatement implements
		ISemanticPredicate, ICompositeStatement {

	private ISourceElement condition;

	private PredicateType predicateType;

	private IStatement predicate;

	private List<IStatement> list;

	public ASemanticPredicate(PredicateType predicateType,
			ISourceElement condition, IStatement predicate) {
		super(condition.sourceStart(), condition.sourceEnd());
		this.predicateType = predicateType;
		this.condition = condition;
		setPredicate(predicate);
	}

	public void setPredicate(IStatement predicate) {
		this.predicate = predicate;
		list = new ArrayList<IStatement>();
		if (predicate != null) {
			((AStatement) predicate).setParent(this);
			list.add(predicate);
		}
	}

	public <E> E getAdapter(Class<E> adapter) {
		if (adapter == ISemanticPredicate.class)
			return adapter.cast(this);
		if (adapter == IRule.class)
			return adapter.cast(getEnclosingRule());
		if (adapter == IGrammar.class)
			return adapter.cast(getEnclosingRule().getParent());
		return null;
	}

	public ElementKind getElementKind() {
		return ElementKind.SEMPRED;
	}

	public String getElementName() {
		StringBuilder builder = new StringBuilder();
		builder.append(condition.getText());
		return builder.toString();
	}

	public ISourceElement getCondition() {
		return condition;
	}

	public IStatement getPredicate() {
		return predicate;
	}

	public PredicateType getPredicateType() {
		return predicateType;
	}

	public void traverse(IModelElementVisitor visitor) {
		if (visitor.visitSemanticPredicate(this)) {
			if (predicate != null) {
				predicate.traverse(visitor);
			}
			visitor.endvisitSemanticPredicate(this);
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(getElementName());
		if (predicateType == PredicateType.GATED_SEMPRED) {
			builder.append(" => ");
		}
		if (predicate != null) {
			builder.append(predicate);
		}
		return builder.toString();
	}

	public void add(int index, IStatement statement) {
		throw new UnsupportedOperationException();
	}

	public void add(IStatement statement) {
		throw new UnsupportedOperationException();
	}

	public int find(IStatement statement) {
		return list.indexOf(statement);
	}

	public IStatement first() {
		return predicate;
	}

	public IStatement get(int index) {
		return list.get(index);
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
		return list.size();
	}

	public Iterator<IStatement> iterator() {
		return list.iterator();
	}

	public boolean isValidating() {
		return getPredicate() == null;
	}

	public boolean hasPredicate() {
		return getPredicate() != null;
	}
}
