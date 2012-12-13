/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.internal.jdt.sourcelookup;

import java.io.File;

import org.deved.antlride.debug.AntlrDebugConstants;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.sourcelookup.ISourceContainer;
import org.eclipse.debug.core.sourcelookup.containers.DirectorySourceContainer;
import org.eclipse.debug.core.sourcelookup.containers.LocalFileStorage;
import org.eclipse.dltk.internal.launching.LaunchConfigurationUtils;
import org.eclipse.jdt.launching.sourcelookup.containers.JavaSourcePathComputer;

public class AntlrJavaSourcePathComputer extends JavaSourcePathComputer {
	@Override
	public ISourceContainer[] computeSourceContainers(
			ILaunchConfiguration configuration, IProgressMonitor monitor)
			throws CoreException {
		ISourceContainer[] sourceContainers = super.computeSourceContainers(
				configuration, monitor);
		String path = LaunchConfigurationUtils.getString(configuration,
				AntlrDebugConstants.JAVA_EXTERNAL_SOURCE_PATH, null);
		ISourceContainer[] newsc = new ISourceContainer[sourceContainers.length + 1];
		System
				.arraycopy(sourceContainers, 0, newsc, 1,
						sourceContainers.length);
		newsc[0] = new FlatDirectorySourceContainer(new File(path), false);
		sourceContainers = newsc;
		return sourceContainers;
	}

	private class FlatDirectorySourceContainer extends DirectorySourceContainer {

		public FlatDirectorySourceContainer(File dir, boolean subfolders) {
			super(dir, subfolders);
		}

		@Override
		public Object[] findSourceElements(String name) throws CoreException {
			File directory = getDirectory();
			name = name.substring(name.lastIndexOf(File.separator)
					+ File.separator.length());
			File file = new File(directory, name);
			if (file.exists() && file.isFile()) {
				return new Object[] { new LocalFileStorage(file) };
			}
			return EMPTY;
		}
	}
}
