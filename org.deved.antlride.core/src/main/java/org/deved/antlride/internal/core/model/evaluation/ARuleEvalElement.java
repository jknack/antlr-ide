/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.internal.core.model.evaluation;

import org.deved.antlride.core.model.IGrammar;
import org.deved.antlride.core.model.evaluation.EvalElementKind;
import org.deved.antlride.core.model.evaluation.IEvalElement;
import org.deved.antlride.core.model.evaluation.IRuleEvalElement;

public class ARuleEvalElement extends AEvalElement implements IRuleEvalElement {

	public ARuleEvalElement(IGrammar grammar, IEvalElement parent, String elementName) {
		super(grammar, (AEvalElement) parent, elementName, EvalElementKind.RULE);
	}

	public boolean isLexerRule() {
		return Character.isUpperCase(getElementName().charAt(0));
	}
}
