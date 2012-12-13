/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.internal.core.builder;

import org.deved.antlride.core.model.IGrammar;
import org.eclipse.core.runtime.IPath;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.builder.IBuildContext;

public class FileSystemAntlrBuildUnit extends DefaultAntlrBuildUnit {

	public FileSystemAntlrBuildUnit(IBuildContext buildContext) {
		super(buildContext);
	}

	public FileSystemAntlrBuildUnit(IGrammar grammar) {
		super(grammar);
	}

	public FileSystemAntlrBuildUnit(ISourceModule sourceModule) {
		super(sourceModule);
	}

	@Override
	public IPath getPath() {
		return file.getLocation();
	}
}
