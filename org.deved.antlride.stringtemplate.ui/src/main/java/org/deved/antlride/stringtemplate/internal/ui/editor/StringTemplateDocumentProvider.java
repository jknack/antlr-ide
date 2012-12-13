/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.stringtemplate.internal.ui.editor;

import org.deved.antlride.common.ui.SingleProjectProblem;
import org.deved.antlride.stringtemplate.core.StringTemplateLanguageToolkit;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.Path;
import org.eclipse.dltk.core.DLTKContentTypeManager;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IDLTKLanguageToolkit;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IProjectFragment;
import org.eclipse.dltk.core.IScriptFolder;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.internal.core.ModelManager;
import org.eclipse.dltk.internal.ui.editor.SourceModuleDocumentProvider;

/**
 * DLTK BUG: can't define more than one language per project
 *
 */
@SuppressWarnings("restriction")
@SingleProjectProblem
public class StringTemplateDocumentProvider extends
		SourceModuleDocumentProvider {

	@Override
	protected ISourceModule createSourceModule(IFile file) {
		if (file == null) {
			return null;
		}
		IScriptProject project = DLTKCore.create(file.getProject());
		if (isValidSourceModule(project, file)) {
			return createSourceModuleFrom(file, project);
		}
		return null;
	}

	private static boolean isValidSourceModule(IModelElement parent,
			IResource resource) {
		IDLTKLanguageToolkit toolkit = StringTemplateLanguageToolkit
				.getDefault();
		return DLTKContentTypeManager.isValidResourceForContentType(toolkit,
				resource);
	}

	private static ISourceModule createSourceModuleFrom(IFile file,
			IScriptProject project) {
		if (file == null)
			return null;
		if (project == null) {
			project = DLTKCore.create(file.getProject());
		}
		IScriptFolder folder = (IScriptFolder) ModelManager
				.determineIfOnBuildpath(file, project);
		if (folder == null) {
			// not on buildpath - make the root its folder
			IProjectFragment root = project
					.getProjectFragment(file.getParent());
			folder = root.getScriptFolder(Path.EMPTY);
		}
		return folder.getSourceModule(file.getName());
	}
}
