/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.internal.core.builder;

import java.io.File;
import java.io.IOException;

import org.deved.antlride.core.AntlrCore;
import org.deved.antlride.core.build.AntlrDeployer;
import org.deved.antlride.core.util.AntlrCoreHelper;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;

public class DefaultAntlrDeployer implements AntlrDeployer {

	public IPath[] deployRuntime() throws CoreException {
		IPath from = Path.fromPortableString("lib").append(RUNTIME_JAR);
		IPath path = RUNTIME_LOCATION;
		File externalFile = path.toFile();
		if (!externalFile.exists()) {
			File pf = externalFile.getParentFile();
			if (!pf.exists()) {
				pf.mkdirs();
			}

			try {
				AntlrCoreHelper.copyFileFromBundle(AntlrCore.getDefault()
						.getBundle(), from.toPortableString(), externalFile);
			} catch (IOException ex) {
				throw new CoreException(new Status(IStatus.ERROR,
						AntlrCore.PLUGIN_ID, "Couldn't deploy runtime", ex));
			}
		}
		return new IPath[] { path };
	}
}
