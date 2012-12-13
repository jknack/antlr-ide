/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.core.launch;

import org.deved.antlride.core.build.AntlrBuildUnit;
import org.deved.antlride.core.model.evaluation.AntlrResultListener;
import org.deved.antlride.core.model.test.AntlrTestCase;
import org.eclipse.core.runtime.CoreException;

public interface AntlrLauncher {
	
	void launch(boolean rebuild, AntlrBuildUnit unit, AntlrTestCase testCase) throws CoreException;

	void setResultListener(AntlrResultListener listener);
}
