/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.jdt.env;

import java.io.File;

import org.deved.antlride.core.env.JavaEnvironment;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.launching.IVMInstall;

public class JDTEnvironment implements JavaEnvironment {

	private IPath javaHomePath;

	private IPath javaPath;

	private IPath javacPath;

	public JDTEnvironment(IVMInstall jvm) {
		File installLocation = jvm.getInstallLocation();
		javaHomePath = Path.fromOSString(installLocation.getAbsolutePath());
		javaPath = javaHomePath.append("bin").append("java");
		javacPath = javaHomePath.append("bin").append("javac");
		if(!javacPath.toFile().exists()) {
			javacPath = null;
		}
	}

	public IPath getJavaHomePath() {
		return javaHomePath;
	}

	public IPath getJavaPath() {
		return javaPath;
	}

	public IPath getJavacPath() {
		return javacPath;
	}
}
