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
import org.deved.antlride.core.model.IBlock;
import org.deved.antlride.core.model.IGrammar;
import org.deved.antlride.core.model.IOptions;
import org.deved.antlride.core.model.IRule;
import org.deved.antlride.core.model.ISourceElement;
import org.deved.antlride.core.model.IStatement;
import org.deved.antlride.core.model.ast.IModelElementVisitor;

public class ABlock extends ACompositeStatement implements IBlock {
	private IOptions options;

	private boolean rewritten;

	private ISourceElement leftParenthesis;

	private ISourceElement rightParenthesis;

	public ABlock() {
		super(3);
	}

	public <E> E getAdapter(Class<E> adapter) {
		if (adapter == IBlock.class)
			return adapter.cast(this);
		if (adapter == IRule.class)
			return adapter.cast(getEnclosingRule());
		if (adapter == IOptions.class)
			return adapter.cast(options);
		if (adapter == IGrammar.class)
			return adapter.cast(getEnclosingRule().getParent());
		return null;
	}

	public ElementKind getElementKind() {
		return ElementKind.BLOCK;
	}

	public String getElementName() {
		return "<block>";
	}

	public boolean isRewritten() {
		return rewritten;
	}

	public void setRewritten(boolean rewritten) {
		this.rewritten = rewritten;
	}	

	public IOptions getOptions() {
		return options;
	}

	public void setOptions(IOptions options) {
		this.options = options;
	}

	public void traverse(IModelElementVisitor visitor) {
		boolean visitable = false;
		if (isRewritten()) {
			visitable = visitor.visitRewriteBlock(this);
		} else {
			if (getParent() instanceof IRule) {
				visitable = visitor.visitRuleBody(this);
			} else {
				visitable = visitor.visitBlock(this);
			}
		}
		if (visitable) {
			if (hasOptions()) {
				getOptions().traverse(visitor);
			}
			for (int i = 0; i < size(); i++) {
				get(i).traverse(visitor);
			}
			if (isRewritten()) {
				visitor.endvisitRewriteBlock(this);
			} else {
				if (getParent() instanceof IRule) {
					visitor.endvisitRuleBody(this);
				} else {
					visitor.endvisitBlock(this);
				}
			}
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		// boolean parenthesis = parenthesis();
		if (leftParenthesis != null)
			builder.append("(\n");

		if (options != null) {
			builder.append(options);
			builder.append(": ");
		}

		for (int index = 0; index < size(); index++) {
			IStatement statement = get(index);
			builder.append(statement);
			if (index + 1 < size()) {
				if (isRewritten()) {
					builder.append("\n->");
				} else {
					builder.append("\n|");
				}
			}
		}

		if (rightParenthesis != null)
			builder.append("\n)");

		builder.append(getEbnfOperator().description());

		return builder.toString();
	}

	public void setLeftParenthesis(ISourceElement leftParenthesis) {
		this.leftParenthesis = leftParenthesis;
	}

	public ISourceElement getLeftParenthesis() {
		return leftParenthesis;
	}

	public void setRightParenthesis(ISourceElement rightParenthesis) {
		this.rightParenthesis = rightParenthesis;
	}

	public ISourceElement getRightParenthesis() {
		return rightParenthesis;
	}

	public boolean hasOptions() {
		return options != null && options.length() > 0;
	}
	
	@Override
	public void setSourceEnd(int sourceEnd) {
		// TODO Auto-generated method stub
		super.setSourceEnd(sourceEnd);
	}
	
	@Override
	public void setSourceStart(int sourceStart) {
		// TODO Auto-generated method stub
		super.setSourceStart(sourceStart);
	}
}
