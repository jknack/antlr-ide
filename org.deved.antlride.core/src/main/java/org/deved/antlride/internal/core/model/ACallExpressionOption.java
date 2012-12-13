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
import org.deved.antlride.core.model.ICallExpression;
import org.deved.antlride.core.model.ICallExpressionOption;
import org.deved.antlride.core.model.IRule;
import org.deved.antlride.core.model.ISourceElement;
import org.deved.antlride.core.model.ast.IModelElementVisitor;

public class ACallExpressionOption extends AStatement implements
		ICallExpressionOption {

	private ISourceElement optionValue;
	
	private ISourceElement optionName;

	public ACallExpressionOption(ISourceElement name) {
		super(name.sourceStart(), name.sourceEnd());
		this.optionName = name;
	}
	
	public ACallExpressionOption(ISourceElement name, ISourceElement value) {
		super(name.sourceStart(), value.sourceEnd());
		this.optionName = name;
		this.optionValue = value;
	}
	
	public ISourceElement getOptionValue() {
		return optionValue;
	}

	public ISourceElement getOptionName() {
		return optionName;
	}
	
	public boolean hasOptionValue() {
		return optionValue != null;
	}

	@SuppressWarnings("unchecked")
	public <E> E getAdapter(Class<E> adapter) {
		if(adapter == ICallExpressionOption.class)
			return (E) this;
		if(adapter == ICallExpression.class)
			return (E) getParent();
		if(adapter == IRule.class)
			return (E) getEnclosingRule();
		return null;
	}

	public ElementKind getElementKind() {
		return ElementKind.CALL_OPTION;
	}

	public String getElementName() {
		return getOptionName().getText();
	}

	public void traverse(IModelElementVisitor visitor) {
		//TODO: traverse call expression option
	}

}
