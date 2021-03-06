/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.core.env;

import org.eclipse.core.resources.IProject;

public class DefaultJavaEnvironmentRepository implements JavaEnvironmentRepository {

	public JavaEnvironment getEnvironment(IProject project) {
		return new DefaultJavaEnvironment();
	}

	public JavaEnvironment getEnvironment() {
		return new DefaultJavaEnvironment();
	}
}
