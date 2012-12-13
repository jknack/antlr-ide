/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.core.model.test;

import org.deved.antlride.core.AntlrCore;
import org.deved.antlride.core.model.IGrammar;
import org.eclipse.core.runtime.IPath;

public class DefaultAntlrTestService implements AntlrTestService {

	public AntlrTestSuite get(IGrammar grammar) {
		IPath path = AntlrCore.getDefault().getStateLocation();
		path = path.append("testsuite").append(grammar.getElementName());
		return new AntlrTestSuite(grammar, path);
	}

}
