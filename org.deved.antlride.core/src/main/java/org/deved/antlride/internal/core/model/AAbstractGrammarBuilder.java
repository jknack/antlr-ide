/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.internal.core.model;

import org.deved.antlride.core.model.IGrammar;
import org.deved.antlride.core.model.IGrammarBuilder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.dltk.compiler.problem.IProblemReporter;

public abstract class AAbstractGrammarBuilder implements IGrammarBuilder {

	protected IProblemReporter problemReporter;

	protected IPath filename;

	protected String project;

	public AAbstractGrammarBuilder() {
	}

	public abstract IGrammar process(String content);

	public void setFile(IPath file) {
		this.filename = file;
	}

	public IPath getAbsolutePath() {
		IPath path = ResourcesPlugin.getWorkspace().getRoot().getProject(
				project).getLocation().removeLastSegments(1);
		return path.append(filename);
	}

	public void setProject(String project) {
		this.project = project;
	}

	public void setProblemReporter(IProblemReporter problemReporter) {
		this.problemReporter = problemReporter;
	}

}
