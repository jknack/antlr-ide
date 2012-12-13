/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.internal.core.model;

import org.deved.antlride.core.model.IGrammarBuilder;
import org.deved.antlride.internal.core.parser.AntlrGrammarBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.dltk.compiler.problem.IProblemReporter;

public final class GrammarRepository {
	private static GrammarRepository instance;

	public IGrammarBuilder createGrammarBuilder(IPath file,
			IProblemReporter reporter) throws CoreException {
		// builder config
		String projectName = file.segment(0);
		IGrammarBuilder grammarBuilder = new AntlrGrammarBuilder();
		// config builder
		grammarBuilder.setProblemReporter(reporter);
		grammarBuilder.setFile(file);
		grammarBuilder.setProject(projectName);
		return grammarBuilder;
	}

	public IGrammarBuilder createGrammarBuilder() throws CoreException {
		// builder config
		IGrammarBuilder grammarBuilder = new AntlrGrammarBuilder();
		// config builder
		return grammarBuilder;
	}

	private GrammarRepository() {
	}

	public static GrammarRepository getInstance() {
		if (instance == null) {
			instance = new GrammarRepository();
		}
		return instance;
	}
}
