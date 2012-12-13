/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.core.model.ast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.deved.antlride.core.model.IModelElement;
import org.deved.antlride.core.model.ast.criteria.IModelElementCriteria;

public class AntlrModelElementCollectorVisitor extends
		AAbstractModelElementVisitor {

	private List<IModelElement> elements;

	protected IModelElement root;

	private IModelElementCriteria criteria;

	private Set<String> elementsNames;

	private boolean acceptDuplicates;

	public AntlrModelElementCollectorVisitor(IModelElementCriteria criteria) {
		elements = new ArrayList<IModelElement>();
		this.criteria = criteria;
		elementsNames = new HashSet<String>();
	}

	@Override
	public void accept(IModelElement node) {
		root = node;
		elements.clear();
		elementsNames.clear();
		super.accept(node);
	}
	
	public void setCriteria(IModelElementCriteria criteria) {
		this.criteria = criteria;
	}

	public void setAcceptDuplicates(boolean acceptDuplicates) {
		this.acceptDuplicates = acceptDuplicates;
	}

	public int getResultSize() {
		return elements.size();
	}

	public IModelElement[] getResult() {
		IModelElement[] result = new IModelElement[elements.size()];
		elements.toArray(result);
		return result;
	}

	@Override
	protected void in(IModelElement element) {
		if (root != element)
			if (criteria.accept(element)) {
				if (!acceptDuplicates
						&& elementsNames.contains(element.getElementName()))
					return;
				elements.add(element);
				elementsNames.add(element.getElementName());
			}
	}
}
