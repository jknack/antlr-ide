/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.stringtemplate.internal.ui;

import org.deved.antlride.stringtemplate.core.StringTemplateConstants;
import org.deved.antlride.stringtemplate.core.StringTemplateLanguageToolkit;
import org.deved.antlride.stringtemplate.internal.ui.text.StringTemplateSimpleSourceViewerConfiguration;
import org.deved.antlride.stringtemplate.internal.ui.text.partitions.StringTemplatePartitions;
import org.deved.antlride.stringtemplate.ui.StringTemplateUI;
import org.eclipse.core.resources.IProject;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.internal.ui.editor.ExternalStorageEditorInput;
import org.eclipse.dltk.ui.AbstractDLTKUILanguageToolkit;
import org.eclipse.dltk.ui.DLTKUILanguageManager;
import org.eclipse.dltk.ui.text.ScriptSourceViewerConfiguration;
import org.eclipse.dltk.ui.text.ScriptTextTools;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IFileEditorInput;

public class StringTemplateUILanguageToolkit extends AbstractDLTKUILanguageToolkit {
	private static StringTemplateUILanguageToolkit instance;

	public StringTemplateUILanguageToolkit() {
	}

	public StringTemplateLanguageToolkit getCoreToolkit() {
		return StringTemplateLanguageToolkit.getDefault();
	}

	public static StringTemplateUILanguageToolkit getDefault() {
		if (instance == null) {
			try {
				instance = (StringTemplateUILanguageToolkit) DLTKUILanguageManager
						.getLanguageToolkit(StringTemplateConstants.NATURE_ID);
			} catch (Throwable t) {
				t.printStackTrace();
			}
		}
		return instance;
	}

	public String getPartitioningId() {
		return StringTemplatePartitions.PARTITION_ID;
	}

	public String getEditorId(Object inputElement) {
		return StringTemplateConstants.EDITOR_ID;
	}

	public boolean getProvideMembers(ISourceModule element) {
		return true;
	}

	public ScriptTextTools getTextTools() {
		return StringTemplateUI.getDefault().getTextTools();
	}

	public ScriptSourceViewerConfiguration createSourceViewerConfiguration() {
		return new StringTemplateSimpleSourceViewerConfiguration(getTextTools()
				.getColorManager(), getPreferenceStore(), null);
	}

	public static IScriptProject getScriptProject(IEditorInput input) {
		IScriptProject dProject = null;
		if (input instanceof IFileEditorInput) {
			IProject project = ((IFileEditorInput) input).getFile()
					.getProject();
			if (project != null) {
				dProject = DLTKCore.create(project);
				if (!dProject.exists())
					dProject = null;
			}
		} else if (input instanceof ExternalStorageEditorInput) {
			IModelElement element = (IModelElement) input
					.getAdapter(IModelElement.class);
			if (element != null) {
				IScriptProject project = element.getScriptProject();
				if (project != null && project.exists()) {
					return project;
				}
			}
		}
		return dProject;
	}

	public IPreferenceStore getPreferenceStore() {
		return StringTemplateUI.getDefault().getPreferenceStore();
	}
}
