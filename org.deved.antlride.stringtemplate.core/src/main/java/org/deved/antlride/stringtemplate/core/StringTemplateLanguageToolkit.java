/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.stringtemplate.core;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.dltk.core.AbstractLanguageToolkit;
import org.eclipse.dltk.core.DLTKLanguageManager;

public class StringTemplateLanguageToolkit extends AbstractLanguageToolkit {

	private static final IStatus ERROR_STATUS = new Status(Status.ERROR,
			StringTemplateCore.PLUGIN_ID, "Invalid file");
	private static StringTemplateLanguageToolkit instance;

	public String getLanguageContentType() {
		return StringTemplateConstants.LANGUAGE_CONTENT_TYPE;
	}

	public String getLanguageName() {
		return StringTemplateConstants.LANGUAGE_NAME;
	}

	public String getNatureId() {
		return StringTemplateNature.NATURE_ID;
	}

	public static StringTemplateLanguageToolkit getDefault() {
		if (instance == null) {
			try {
				instance = (StringTemplateLanguageToolkit) DLTKLanguageManager
						.getLanguageToolkit(StringTemplateConstants.NATURE_ID);
			} catch (Throwable t) {
				t.printStackTrace();
			}
		}
		return instance;
	}

	@Override
	public IStatus validateSourceModule(IResource resource) {
		if (isStringTemplateLikeFileName(resource.getName()))
			return Status.OK_STATUS;
		return ERROR_STATUS;
	}

	private boolean isStringTemplateLikeFileName(String name) {
		String[] extensions = StringTemplateConstants.FILE_EXTENSIONS;
		for (int i = 0; i < extensions.length; i++) {
			if (name.endsWith("." + extensions[i])) {
				return true;
			}
		}

		return false;
	}
}
