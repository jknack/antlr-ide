/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.internal.core.model.dltk;

import org.deved.antlride.core.model.ElementKind;
import org.deved.antlride.core.model.ICallExpression;
import org.deved.antlride.core.model.IModelElement;
import org.deved.antlride.core.model.IRule;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.ISourceRange;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.internal.core.ModelElement;
import org.eclipse.dltk.internal.core.SourceMethod;
import org.eclipse.dltk.internal.core.SourceRange;

@SuppressWarnings("restriction")
public class FakeMethod extends SourceMethod {

	private int offset;

	private int length;

	private String[] parameters;

	public FakeMethod(ISourceModule parent, IModelElement element) {
		super((ModelElement) parent, element.getElementName());
		this.offset = element.sourceStart();
		this.length = element.sourceEnd() - offset;
		ElementKind kind = element.getElementKind();
		String parameters = null;
		switch (kind) {
		case RULE:
			IRule rule = (IRule) element;
			if (rule.hasParameters())
				parameters = rule.getParameters().toString();
			break;
		case CALL:
			ICallExpression callExpression = (ICallExpression) element;
			if (callExpression.getParameters() != null)
				parameters = callExpression.getParameters().toString();
			break;
		}
		if (parameters != null) {
			parameters = parameters.substring(0, parameters.length() - 1);// remove
																			// []
			this.parameters = parameters.split(",");
		} else {
			this.parameters = new String[0];
		}
	}

	public ISourceRange getNameRange() throws ModelException {
		return new SourceRange(offset, length);
	}

	public ISourceRange getSourceRange() throws ModelException {
		return new SourceRange(offset, length);
	}

	@Override
	public String[] getParameterNames() throws ModelException {
		return parameters;
	}
}
