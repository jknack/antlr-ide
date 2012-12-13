/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.gunit.internal.ui;

import org.deved.antlride.gunit.core.GUnitConstants;
import org.deved.antlride.gunit.core.GUnitLanguageToolkit;
import org.deved.antlride.gunit.internal.ui.text.GUnitSimpleSourceViewerConfiguration;
import org.deved.antlride.gunit.internal.ui.text.partitions.GUnitPartitions;
import org.deved.antlride.gunit.ui.GUnitUI;
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

public class GUnitUILanguageToolkit extends AbstractDLTKUILanguageToolkit {
	private static GUnitUILanguageToolkit instance;

	public GUnitUILanguageToolkit() {
	}

	public GUnitLanguageToolkit getCoreToolkit() {
		return GUnitLanguageToolkit.getDefault();
	}

	public static GUnitUILanguageToolkit getDefault() {
		if (instance == null) {
			try {
				instance = (GUnitUILanguageToolkit) DLTKUILanguageManager
						.getLanguageToolkit(GUnitConstants.NATURE_ID);
			} catch (Throwable t) {
				t.printStackTrace();
			}
		}
		return instance;
	}

	public String getPartitioningId() {
		return GUnitPartitions.PARTITION_ID;
	}

	public String getEditorId(Object inputElement) {
		return GUnitConstants.EDITOR_ID;
	}

	public boolean getProvideMembers(ISourceModule element) {
		return true;
	}

	public ScriptTextTools getTextTools() {
		return GUnitUI.getDefault().getTextTools();
	}

	public ScriptSourceViewerConfiguration createSourceViewerConfiguration() {
		return new GUnitSimpleSourceViewerConfiguration(getTextTools()
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
		return GUnitUI.getDefault().getPreferenceStore();
	}

}
