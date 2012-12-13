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
import org.deved.antlride.core.model.IOption;
import org.deved.antlride.core.model.IOptionName;
import org.deved.antlride.core.model.IOptionValue;
import org.deved.antlride.core.model.IOptions;
import org.deved.antlride.core.model.ast.IModelElementVisitor;
import org.deved.antlride.core.model.dltk.ast.DASTOption;

public class AOption extends AAbstractModelElement implements IOption {

	private IOptionName name;

	private IOptionValue value;

	private DASTOption ast;

	public AOption(int sourceStart, int sourceEnd) {
		super(sourceStart, sourceEnd);
	}
	
	public void setName(IOptionName name) {
		this.name = name;
	}
	
	public void setValue(IOptionValue value) {
		this.value = value;
	}

	public <E> E getAdapter(Class<E> adapter) {
		if (adapter == IOption.class)
			return adapter.cast(this);
		if (adapter == IOptions.class) {
			return adapter.cast(getParent());
		}
		return getParent().getAdapter(adapter);
	}

	public ElementKind getElementKind() {
		ElementKind parentKind = parent.getElementKind();
		switch (parentKind) {
		case GRAMMAR_OPTIONS:
			return ElementKind.GRAMMAR_OPTION;
		case RULE_OPTIONS:
			return ElementKind.RULE_OPTION;
		}
		return ElementKind.BLOCK_OPTION;
	}

	public String getElementName() {
		return name.getText();
	}

	public DASTOption getAST() {
		if (ast == null)
			ast = new DASTOption(toString(), getElementKind().ordinal(),
					sourceStart(), sourceEnd());
		return ast;
	}

	public IOptionName getName() {
		return name;
	}

	public IOptionValue getValue() {
		return value;
	}

	public void traverse(IModelElementVisitor visitor) {
		ElementKind kind = getElementKind();
		switch (kind) {
		case GRAMMAR_OPTION: {
			if (visitor.visitGrammarOption(this)) {
				if (name != null)
					name.traverse(visitor);
				if (value != null)
					value.traverse(visitor);
				visitor.endvisitGrammarOption(this);
			}
		}
			break;
		case RULE_OPTION: {
			if (visitor.visitRuleOption(this)) {
				if (name != null)
					name.traverse(visitor);
				if (value != null)
					value.traverse(visitor);
				visitor.endvisitRuleOption(this);
			}
		}
			break;
		case BLOCK_OPTION: {
			if (visitor.visitBlockOption(this)) {
				if (name != null)
					name.traverse(visitor);
				if (value != null)
					value.traverse(visitor);
				visitor.endvisitBlockOption(this);
			}
		}
			break;
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(name);
		builder.append(" = ");
		builder.append(value);
		return builder.toString();
	}

}
