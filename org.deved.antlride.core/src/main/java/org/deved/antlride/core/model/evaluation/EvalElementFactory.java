/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.core.model.evaluation;

import org.deved.antlride.core.model.IGrammar;
import org.deved.antlride.internal.core.model.evaluation.AResultEvalElement;
import org.deved.antlride.internal.core.model.evaluation.AExceptionEvalElement;
import org.deved.antlride.internal.core.model.evaluation.ARuleEvalElement;
import org.deved.antlride.internal.core.model.evaluation.ATokenEvalElement;

public final class EvalElementFactory {
	
	public static IRuleEvalElement createRuleEvalElement(IGrammar grammar, IEvalElement parent,
			String elementName) {
		return new ARuleEvalElement(grammar, parent, elementName);
	}
	
	public static ITokenEvalElement createTokenEvalElement(IGrammar grammar, IEvalElement parent,
			String text, int line, int column) {
		return new ATokenEvalElement(grammar, parent, text, line, column);
	}
	
	public static IExceptionEvalElement createExceptionEvalElement(IGrammar grammar, IEvalElement parent,
			String exception, String text, int line, int column) {
		return new AExceptionEvalElement(grammar, parent, exception, text, line, column);
	}
	
	public static IResultEvalElement createResultEvalElement(IGrammar grammar, boolean fail, String resultDescription, IEvalElement result) {
		return new AResultEvalElement(grammar, fail, resultDescription, result);
	}
}
