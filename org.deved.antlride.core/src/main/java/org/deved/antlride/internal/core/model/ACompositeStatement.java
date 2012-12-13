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

import org.deved.antlride.core.model.ICompositeStatement;
import org.deved.antlride.core.model.IStatement;

public abstract class ACompositeStatement extends AStatement implements
		ICompositeStatement {

	/*
	 * private class CompositeNavigator extends AAbstractModelElementVisitor {
	 * private boolean compositeFound; private IModelElement root;
	 * 
	 * @Override public void accept(IModelElement node) { root = node;
	 * super.accept(node); }
	 * 
	 * @Override public boolean visitRewriteBlock(IBlock node) { return false; }
	 * 
	 * @Override public boolean visitRewriteAlternative(IAlternative node) {
	 * return false; }
	 * 
	 * @Override public boolean visitAlternative(IAlternative node) { if (root
	 * != node) { compositeFound = true; return false; } return
	 * super.visitAlternative(node); }
	 * 
	 * @Override public boolean visitBlock(IBlock node) { if (root != node) {
	 * compositeFound = true; return false; } return super.visitBlock(node); } }
	 */

	private List<IStatement> statements;

	public ACompositeStatement(int statementCount) {
		statements = new ArrayList<IStatement>(statementCount);
	}

	public ACompositeStatement() {
		this(3);
	}

	public IStatement get(int index) {
		return statements.get(index);
	}

	public IStatement first() {
		try {
			return get(0);
		} catch (Throwable t) {
			return null;
		}
	}
	
	public int sourceStart() {
		if (sourceStart == 0) {
			sourceStart = get(0).sourceStart();
		}
		return sourceStart;
	}

	public int sourceEnd() {
		if (sourceEnd == 0 && size() > 0) {
			sourceEnd = get(size() - 1).sourceEnd();
		}
		return sourceEnd;
	}

	public IStatement last() {
		try {
			return get(size() - 1);
		} catch (Throwable t) {
			return null;
		}
	}

	public int size() {
		return statements.size();
	}

	public void add(IStatement statement) {
		this.add(size(), statement);
	}

	public void remove(IStatement statement) {
		this.statements.remove(statement);
	}

	public Iterator<IStatement> iterator() {
		return statements.iterator();
	}

	public void add(int index, IStatement statement) {
		this.statements.add(index, statement);
		((AAbstractModelElement) statement).setParent(this);
		if (statement instanceof AAlternative) {
			AAlternative alt = (AAlternative) statement;
			alt.setNumber(size() - 1);
		}
	}

	public void set(int index, IStatement statement) {
		this.statements.set(index, statement);
	}

	public int find(IStatement statement) {
		return this.statements.indexOf(statement);
	}

	public IStatement remove(int index) {
		return this.statements.remove(index);
	}
}
