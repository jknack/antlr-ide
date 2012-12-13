/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.core.interpreter;

import org.deved.antlride.core.model.IGrammar;
import org.deved.antlride.core.model.IRule;
import org.deved.antlride.core.model.evaluation.IResultEvalElement;
import org.deved.antlride.core.model.test.AntlrTestSuite;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;

public interface AntlrInterpreter {

	IResultEvalElement interpret(IRule rule, String text);

	IStatus beginSession(IProgressMonitor monitor, IGrammar grammar);

	AntlrTestSuite getTestSuite();

	void endSession();
}
