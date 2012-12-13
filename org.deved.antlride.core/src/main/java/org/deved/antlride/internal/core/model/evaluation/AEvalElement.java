/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.internal.core.model.evaluation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.deved.antlride.core.model.IGrammar;
import org.deved.antlride.core.model.evaluation.EvalElementKind;
import org.deved.antlride.core.model.evaluation.IEvalElement;

public abstract class AEvalElement implements IEvalElement {

	private List<IEvalElement> elements;

	private EvalElementKind elementKind;

	private String elementName;

	private IEvalElement parentElement;

	private Object userData;

	private IGrammar grammar;

	public AEvalElement(IGrammar grammar, AEvalElement parent,
			String elementName, EvalElementKind elementKind) {
		elements = new ArrayList<IEvalElement>(5);
		if (parent != null)
			parent.elements.add(this);
		this.grammar = grammar;
		this.parentElement = parent;
		this.elementKind = elementKind;
		this.elementName = elementName;
	}

	public IGrammar getGrammar() {
		return grammar;
	}

	public Object getUserData() {
		return userData;
	}

	public void clear() {
		userData = null;
		for (IEvalElement e : elements) {
			e.clear();
		}
		elements.clear();
	}

	public void setUserData(Object userData) {
		this.userData = userData;
	}

	public IEvalElement getElement(int index) {
		return elements.get(index);
	}

	public IEvalElement firstElement() {
		if (getElementCount() > 0)
			return getElement(0);
		return null;
	}

	public IEvalElement lastElement() {
		if (getElementCount() > 0)
			return getElement(getElementCount() - 1);
		return null;
	}

	public int getElementCount() {
		return elements.size();
	}

	public EvalElementKind getElementKind() {
		return elementKind;
	}

	public String getElementName() {
		return elementName;
	}

	public IEvalElement getParent() {
		return parentElement;
	}

	public boolean isLeaf() {
		return getElementCount() == 0;
	}

	public boolean isRoot() {
		return getParent() == null;
	}

	public Iterator<IEvalElement> iterator() {
		return elements.iterator();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(getElementName());
		if (getElementCount() > 0) {
			builder.append("(");
			for (IEvalElement e : this) {
				builder.append(e);
				builder.append(" ");
			}
			builder.setCharAt(builder.length() - 1, ')');
		}
		return builder.toString();
	}
}
