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
import org.deved.antlride.core.model.IAlternative;
import org.deved.antlride.core.model.IBlock;
import org.deved.antlride.core.model.ICallExpression;
import org.deved.antlride.core.model.IGrammar;
import org.deved.antlride.core.model.IRule;
import org.deved.antlride.core.model.IStatement;
import org.deved.antlride.core.model.ast.AAbstractModelElementVisitor;
import org.deved.antlride.core.model.ast.IModelElementVisitor;

public class AAlternative extends ACompositeStatement implements IAlternative {

	private static class Counter extends AAbstractModelElementVisitor {
		private int count = 0;
		
		@Override
		public boolean visitCallExpression(ICallExpression node) {
			count++;
			return true;
		}
		
		@Override
		public boolean visitRewriteAlternative(IAlternative node) {
			return false;
		}
		
		@Override
		public boolean visitRewriteBlock(IBlock node) {
			return false;
		}
	}
	
	private IStatement rewriteAlternative;

	private int number;
	
	public AAlternative() {
	}

	public <E> E getAdapter(Class<E> adapter) {
		if (adapter == IAlternative.class)
			return adapter.cast(this);
		if (adapter == IRule.class)
			return adapter.cast(getEnclosingRule());
		if (adapter == IGrammar.class)
			return adapter.cast(getEnclosingRule().getParent());
		return null;
	}

	public boolean empty() {
		Counter counter = new Counter();
		counter.accept(this);
		return counter.count == 0;
	}

	public ElementKind getElementKind() {
		return ElementKind.ALTERNATIVE;
	}

	public String getElementName() {
		return "<alternative>";
	}

	public int getNumber() {
		return number;
	}

	public IStatement getRewriteAlternative() {
		return rewriteAlternative;
	}

	public boolean hasRewriteRule() {
		return rewriteAlternative != null;
	}

	public int sourceStart() {
		return this.sourceStart;
	}

	public int sourceEnd() {
		if (hasRewriteRule()) {
			return this.sourceEnd = rewriteAlternative.sourceEnd();
		}
		if (size() > 0) {
			return this.sourceEnd = get(size() - 1).sourceEnd();
		}
		return 0;
	}

	public void setRewriteAlternative(IBlock rewriteAlternative) {
		this.rewriteAlternative = rewriteAlternative;
		((ABlock) rewriteAlternative).setRewritten(true);
		((ABlock) rewriteAlternative).setParent(this);
	}

	public String getOperator() {
		IBlock parent = getParent().getAdapter(IBlock.class);
		return parent.isRewritten() ? "->" : "|";
	}

	public void traverse(IModelElementVisitor visitor) {
		if (visitor.visitAlternative(this)) {
			for (int i = 0; i < size(); i++) {
				get(i).traverse(visitor);
			}
			if (hasRewriteRule()) {
				rewriteAlternative.traverse(visitor);
			}
			visitor.endvisitAlternative(this);
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (int index = 0; index < size(); index++) {
			IStatement statement = get(index);
			builder.append(statement);
			if (index + 1 < size()) {
				builder.append(" ");
			}
		}
		if (hasRewriteRule()) {
			builder.append(" -> ");
			builder.append(rewriteAlternative);
		}
		return builder.toString();
	}

	public void setNumber(int number) {
		this.number = number;
	}

	@Override
	public void setSourceEnd(int sourceEnd) {
		super.setSourceEnd(sourceEnd);
	}

	@Override
	public void setSourceStart(int sourceStart) {
		super.setSourceStart(sourceStart);
	}
}
