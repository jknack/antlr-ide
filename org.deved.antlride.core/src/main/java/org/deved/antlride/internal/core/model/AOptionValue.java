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
import org.deved.antlride.core.model.IModelElement;
import org.deved.antlride.core.model.IOptionName;
import org.deved.antlride.core.model.IOptionValue;
import org.deved.antlride.core.model.ISourceElement;
import org.deved.antlride.core.model.ast.IModelElementVisitor;

public class AOptionValue extends AAbstractModelElement implements IOptionValue {

	private ISourceElement value;

	public AOptionValue(IModelElement parent, ISourceElement name) {
		super(parent);
		setSourceStart(name.sourceStart());
		setSourceEnd(name.sourceEnd());
		this.value = name;
	}

	public <E> E getAdapter(Class<E> adapter) {
		if (adapter == IOptionName.class)
			return adapter.cast(this);
		return getParent().getAdapter(adapter);
	}

	public ElementKind getElementKind() {
		ElementKind kind = getParent().getElementKind();
		switch (kind) {
		case GRAMMAR_OPTION:
			return ElementKind.GRAMMAR_OPTION_VALUE;
		case RULE_OPTION:
			return ElementKind.RULE_OPTION_VALUE;
		default:
			return ElementKind.BLOCK_OPTION_VALUE;
		}
	}

	public void traverse(IModelElementVisitor visitor) {
		ElementKind kind = getElementKind();
		switch (kind) {
		case GRAMMAR_OPTION_VALUE:
			if (visitor.visitGrammarOptionValue(this)) {
				visitor.endvisitGrammarOptionValue(this);
			}
			break;
		case RULE_OPTION_VALUE:
			if (visitor.visitRuleOptionValue(this)) {
				visitor.endvisitRuleOptionValue(this);
			}
			break;
		case BLOCK_OPTION_VALUE:
			if (visitor.visitBlockOptionValue(this)) {
				visitor.endvisitBlockOptionValue(this);
			}
			break;
		}
	}

	public String getElementName() {
		return value.getText();
	}

	public String getText() {
		return getElementName();
	}

	@Override
	public String toString() {
		return getElementName();
	}
}
