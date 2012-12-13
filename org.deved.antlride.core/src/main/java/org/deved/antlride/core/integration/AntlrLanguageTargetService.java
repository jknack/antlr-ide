/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.core.integration;

import java.util.Map;

import org.deved.antlride.core.AntlrLanguageTargetName;
import org.deved.antlride.core.build.AntlrBuildUnit;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

public interface AntlrLanguageTargetService {

	Map<String, Object> getClasspathInformation(IProgressMonitor monitor, AntlrBuildUnit unit)
			throws CoreException;

	boolean accept(IResource resource);

	AntlrLanguageTargetName getName();
	
	String format(String code);
}
