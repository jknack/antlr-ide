/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.gunit.core;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.dltk.core.AbstractLanguageToolkit;
import org.eclipse.dltk.core.DLTKLanguageManager;

public class GUnitLanguageToolkit extends AbstractLanguageToolkit {

	private static final IStatus ERROR_STATUS = new Status(Status.ERROR,
			GUnitCore.PLUGIN_ID, "Invalid file");
	private static GUnitLanguageToolkit instance;

	public String getLanguageContentType() {
		return GUnitConstants.LANGUAGE_CONTENT_TYPE;
	}

	public String getLanguageName() {
		return GUnitConstants.LANGUAGE_NAME;
	}

	public String getNatureId() {
		return GUnitNature.NATURE_ID;
	}

	public static GUnitLanguageToolkit getDefault() {
		if (instance == null) {
			try {
				instance = (GUnitLanguageToolkit) DLTKLanguageManager
						.getLanguageToolkit(GUnitConstants.NATURE_ID);
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
		String[] extensions = GUnitConstants.FILE_EXTENSIONS;
		for (int i = 0; i < extensions.length; i++) {
			if (name.endsWith("." + extensions[i])) {
				return true;
			}
		}

		return false;
	}
}
