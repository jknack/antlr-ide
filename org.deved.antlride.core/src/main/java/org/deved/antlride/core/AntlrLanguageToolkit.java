/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.core;

import java.text.MessageFormat;
import java.util.Collection;

import org.deved.antlride.core.integration.AntlrLanguageTargetService;
import org.deved.antlride.core.util.AntlrCoreExtensionPointHelper;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.dltk.core.AbstractLanguageToolkit;
import org.eclipse.dltk.core.DLTKLanguageManager;
import org.eclipse.dltk.core.IDLTKLanguageToolkit;
import org.eclipse.dltk.core.IModelStatus;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class AntlrLanguageToolkit extends AbstractLanguageToolkit implements
		IDLTKLanguageToolkit {

	public static final String[] FILE_EXTENSIONS = { AntlrConstants.ANTLR_GRAMMAR_FILE_EXTENSION };

	private static AntlrLanguageToolkit instance;

	private Collection<AntlrLanguageTargetService> languageTargetServices;

	private AntlrConsole console;

	private Status ERROR_STATUS = new Status(IStatus.ERROR,
			AntlrCore.PLUGIN_ID, -1, MessageFormat.format(
					AntlrCoreMessages.convention_unit_notScriptName,
					new Object[] { AntlrConstants.ANTLR_GRAMMAR_FILE_EXTENSION,
							getLanguageName() }), null);

	public AntlrLanguageToolkit() {
		languageTargetServices = AntlrCoreExtensionPointHelper.loadExtensions(
				AntlrCore.PLUGIN_ID,
				AntlrCoreExtensionPointHelper.LANGUAGE_TARGET);
	}

	public AntlrLanguageTargetService getLanguageTargetService(String name) {
		if (name == null)
			return null;
		AntlrLanguageTargetName languageTargetName = AntlrLanguageTargetName
				.find(name);
		if (languageTargetName == null)
			return null;

		for (AntlrLanguageTargetService languageTargetService : languageTargetServices) {
			if (languageTargetName.equals(languageTargetService.getName())) {
				return languageTargetService;
			}
		}
		return null;
	}

	public boolean validResource(IResource resource) {
		for (AntlrLanguageTargetService languageTargetService : languageTargetServices) {
			if (!languageTargetService.accept(resource)) {
				return false;
			}
		}
		return true;
	}

	public static AntlrLanguageToolkit getDefault() {
		if (instance == null) {
			try {
				instance = (AntlrLanguageToolkit) DLTKLanguageManager
						.getLanguageToolkit(AntlrNature.NATURE_ID);
			} catch (Throwable t) {
				t.printStackTrace();
			}
		}
		return instance;
	}

	public AntlrConsole getConsole() {
		if (console == null) {
			try {
				BundleContext bundleContext = AntlrCore.getDefault()
						.getContext();
				ServiceReference serviceReference = bundleContext
						.getServiceReference(AntlrConsole.class.getName());
				console = (AntlrConsole) bundleContext
						.getService(serviceReference);
			} catch (Throwable t) {
				AntlrCore.error("Can't access to the console", t);
			}
		}
		return console;
	}

	public String getLanguageName() {
		return "ANTLR";
	}

	public String getNatureId() {
		return AntlrNature.NATURE_ID;
	}

	public boolean languageSupportZIPBuildpath() {
		return false;
	}

	public IStatus validateSourceModule(IResource resource) {
		String name = resource.getName();
		if (!isGrammarFileName(name)) {
			return ERROR_STATUS;
		}
		return validResource(resource) ? IModelStatus.VERIFIED_OK
				: ERROR_STATUS;
	}

	private boolean isGrammarFileName(String name) {
		String[] extensions = FILE_EXTENSIONS;
		for (int i = 0; i < extensions.length; i++) {
			if (name.endsWith("." + extensions[i])) {
				return true;
			}
		}

		return false;
	}

	public String getLanguageContentType() {
		return "org.deved.antlride.antlrContentType";
	}
}
