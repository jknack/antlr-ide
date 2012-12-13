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
import org.deved.antlride.core.model.IGrammar;
import org.deved.antlride.core.model.IRule;
import org.deved.antlride.core.model.IRuleFinally;
import org.deved.antlride.core.model.IRuleThrows;
import org.deved.antlride.core.model.ast.IModelElementVisitor;

public class ARuleThrows extends AAbstractModelElement implements IRuleThrows {

	private static final String[] NO_EXCEPTIONS = new String[0];

	private String[] exceptions = NO_EXCEPTIONS;

	public ARuleThrows(String[] exceptions) {
		if (exceptions == null)
			throw new IllegalArgumentException("exceptions");
		this.exceptions = exceptions;
	}

	@SuppressWarnings("unchecked")
	public <E> E getAdapter(Class<E> adapter) {
		if (adapter == IRuleFinally.class)
			return (E) this;
		if (adapter == IRule.class)
			return (E) getParent();
		if (adapter == IGrammar.class)
			return (E) getParent().getParent();
		return null;
	}

	public ElementKind getElementKind() {
		return ElementKind.RULE_THROWS;
	}

	public String getElementName() {
		return "throws";
	}

	public void traverse(IModelElementVisitor visitor) {
		if (visitor.visitRuleThrows(this)) {
			visitor.endvisitRuleThrows(this);
		}
	}

	public String[] getExceptions() {
		return exceptions;
	}

	@Override
	public String toString() {
		StringBuilder buffer = new StringBuilder(getElementName());
		for (int i = 0; i < exceptions.length; i++) {
			buffer.append(exceptions[i]).append(", ");
		}
		// eat the ", "
		buffer.setLength(buffer.length() - 2);
		return buffer.toString();
	}
}
