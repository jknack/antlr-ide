/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.core.build;

import org.deved.antlride.core.AntlrCore;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;

public interface AntlrDeployer {

	String RUNTIME_JAR = "antlride-runtime.jar";

	IPath RUNTIME_PATH = Path.fromPortableString("jars").append(RUNTIME_JAR);

	IPath RUNTIME_LOCATION = AntlrCore.getDefault().getStateLocation().append(
			"antlride-runtime-" + AntlrCore.getDefault().getBundle().getLastModified() +
			"-" + AntlrCore.getDefault().getBundle().getVersion() + ".jar");

	IPath[] deployRuntime() throws CoreException;
}
