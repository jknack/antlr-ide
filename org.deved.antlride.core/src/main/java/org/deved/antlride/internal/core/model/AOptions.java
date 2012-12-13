/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.internal.core.model;

import java.util.Collection;

import org.deved.antlride.core.model.ElementKind;
import org.deved.antlride.core.model.IBlock;
import org.deved.antlride.core.model.IGrammar;
import org.deved.antlride.core.model.IModelElement;
import org.deved.antlride.core.model.IOption;
import org.deved.antlride.core.model.IOptions;
import org.deved.antlride.core.model.IRule;
import org.deved.antlride.core.model.ast.IModelElementVisitor;
import org.deved.antlride.core.model.dltk.ast.DASTOption;
import org.deved.antlride.core.model.dltk.ast.DASTOptions;
import org.eclipse.dltk.ast.ASTNode;

public class AOptions extends AAbstractModelElement implements IOptions {

	private static final IOption[] EMPTY_OPTIONS = new IOption[0];

	private IOption[] options;

	private DASTOptions node;

	public AOptions(int sourceStart, int sourceEnd, int lineStart, int lineEnd) {
		super(sourceStart, sourceEnd);
	}

	private DASTOptions getAST() {
		if (node == null) {
			IOption[] optionsArray = getOptions();
			DASTOption[] optionsAST = new DASTOption[optionsArray.length];
			for (int i = 0; i < optionsArray.length; i++) {
				optionsAST[i] = optionsArray[i].getAST();
			}
			node = new DASTOptions(getElementKind().ordinal(), sourceStart(),
					sourceEnd(), optionsAST);
		}
		return node;
	}

	public int length() {
		return options == null ? 0 : options.length;
	}

	public <E> E getAdapter(Class<E> adapter) {
		if (adapter == IOptions.class)
			return adapter.cast(this);
		if (adapter == ASTNode.class)
			return adapter.cast(getAST());
		ElementKind kind = getElementKind();
		switch (kind) {
		case GRAMMAR_OPTIONS:
			if (adapter == IGrammar.class)
				return adapter.cast(getParent());
			break;
		case RULE_OPTIONS:
			if (adapter == IGrammar.class)
				return adapter.cast(getParent().getParent());
			if (adapter == IRule.class)
				return adapter.cast(getParent());
			break;
		case BLOCK_OPTIONS:
			if (adapter == IGrammar.class)
				return adapter.cast(((IBlock) getParent()).getEnclosingRule()
						.getParent());
			if (adapter == IRule.class)
				return adapter.cast(((IBlock) getParent()).getEnclosingRule());
			if (adapter == IBlock.class)
				return adapter.cast(getParent());
			break;
		}
		return null;
	}

	public ElementKind getElementKind() {
		IModelElement parent = getParent();
		ElementKind kind = parent.getElementKind();
		switch (kind) {
		case GRAMMAR:
			return ElementKind.GRAMMAR_OPTIONS;
		case RULE:
			return ElementKind.RULE_OPTIONS;
		}
		return ElementKind.BLOCK_OPTIONS;
	}

	public String getElementName() {
		return "<options>";
	}

	public IOption findOption(String name) {
		for (int i = 0; i < options.length; i++) {
			IOption option = options[i];
			if (option.getName().getText().equals(name)) {
				return option;
			}
		}
		return null;
	}

	public IOption[] getOptions() {
		return this.options;
	}

	public void setOptions(Collection<IOption> options) {
		if (options == null || options.size() == 0) {
			this.options = EMPTY_OPTIONS;
		} else {
			this.options = new IOption[options.size()];
			this.options = options.toArray(this.options);
			for (IOption option : options) {
				((AOption) option).setParent(this);
			}
		}
	}

	public void traverse(IModelElementVisitor visitor) {
		ElementKind kind = getElementKind();
		switch (kind) {
		case GRAMMAR_OPTIONS: {
			if (visitor.visitGrammarOptions(this)) {
				for (int i = 0; i < options.length; i++) {
					options[i].traverse(visitor);
				}
				visitor.endvisitGrammarOptions(this);
			}
		}
			break;
		case RULE_OPTIONS: {
			if (visitor.visitRuleOptions(this)) {
				for (int i = 0; i < options.length; i++) {
					options[i].traverse(visitor);
				}
				visitor.endvisitRuleOptions(this);
			}
		}
			break;
		case BLOCK_OPTIONS: {
			if (visitor.visitBlockOptions(this)) {
				for (int i = 0; i < options.length; i++) {
					options[i].traverse(visitor);
				}
				visitor.endvisitBlockOptions(this);
			}
		}
			break;
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("options {");
		String separator;
		if (parent.getElementKind() != ElementKind.BLOCK) {
			separator = "\n";
		} else {
			separator = " ";
		}
		builder.append(separator);
		for (int i = 0; i < options.length; i++) {
			builder.append(options[i]);
			builder.append(";");
			builder.append(separator);
		}
		builder.append("}");
		builder.append(separator);
		return builder.toString();
	}
}
