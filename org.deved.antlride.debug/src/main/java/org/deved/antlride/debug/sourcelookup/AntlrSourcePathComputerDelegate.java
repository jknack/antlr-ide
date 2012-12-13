/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.debug.sourcelookup;

import org.deved.antlride.debug.AntlrDebugConstants;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.sourcelookup.ISourceContainer;
import org.eclipse.debug.core.sourcelookup.ISourcePathComputerDelegate;
import org.eclipse.debug.core.sourcelookup.containers.FolderSourceContainer;
import org.eclipse.debug.core.sourcelookup.containers.ProjectSourceContainer;
import org.eclipse.debug.core.sourcelookup.containers.WorkspaceSourceContainer;

public class AntlrSourcePathComputerDelegate implements
		ISourcePathComputerDelegate {

	public ISourceContainer[] computeSourceContainers(
			ILaunchConfiguration configuration, IProgressMonitor monitor)
			throws CoreException {
		String path = configuration.getAttribute(
				AntlrDebugConstants.ATTR_ANTLR_PROGRAM, (String) null);
		ISourceContainer sourceContainer = null;
		if (path != null) {
			IResource resource = ResourcesPlugin.getWorkspace().getRoot()
					.findMember(new Path(path));
			if (resource != null) {
				IContainer container = resource.getParent();
				if (container.getType() == IResource.PROJECT) {
					sourceContainer = new ProjectSourceContainer(
							(IProject) container, false);
				} else if (container.getType() == IResource.FOLDER) {
					sourceContainer = new FolderSourceContainer(container,
							false);
				}
			}
		}
		if (sourceContainer == null) {
			sourceContainer = new WorkspaceSourceContainer();
		}
		return new ISourceContainer[] { sourceContainer };
	}
}
