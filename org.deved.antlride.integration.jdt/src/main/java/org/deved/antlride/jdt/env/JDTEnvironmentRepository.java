/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.jdt.env;

import org.deved.antlride.core.env.JavaEnvironment;
import org.deved.antlride.core.env.JavaEnvironmentRepository;
import org.eclipse.core.resources.IProject;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.launching.IVMInstall;
import org.eclipse.jdt.launching.JavaRuntime;

public class JDTEnvironmentRepository implements JavaEnvironmentRepository {

	public JavaEnvironment getEnvironment() {
		return getEnvironment(null);
	}

	public JavaEnvironment getEnvironment(IProject project) {
		try {
			IJavaProject javaProject = JavaCore.create(project);
			if (javaProject != null && javaProject.exists()) {
				IVMInstall vmInstall = JavaRuntime.getVMInstall(javaProject);
				if (vmInstall == null) {
					vmInstall = JavaRuntime.getDefaultVMInstall();
				}
				if (vmInstall != null) {
					return new JDTEnvironment(vmInstall);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
}
