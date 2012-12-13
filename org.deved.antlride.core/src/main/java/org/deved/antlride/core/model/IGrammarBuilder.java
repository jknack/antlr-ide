/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.core.model;

import org.eclipse.core.runtime.IPath;
import org.eclipse.dltk.compiler.problem.IProblemReporter;

public interface IGrammarBuilder {
	
	IGrammar process(String content);
	
	void setFile(IPath file);
	
	void setProblemReporter(IProblemReporter problemReporter);
	
	void setProject(String project);
}
