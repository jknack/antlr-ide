/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.stringtemplate.ui;

import org.deved.antlride.stringtemplate.core.StringTemplateConstants;
import org.deved.antlride.stringtemplate.internal.ui.editor.StringTemplateDocumentProvider;
import org.deved.antlride.stringtemplate.internal.ui.text.StringTemplateTextTools;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class StringTemplateUI extends AbstractUIPlugin {

	private StringTemplateTextTools textTools;

	private StringTemplateDocumentProvider fSourceModuleDocumentProvider;

	// The plug-in ID
	public static final String PLUGIN_ID = StringTemplateConstants.UI_NAMESPACE;

	// The shared instance
	private static StringTemplateUI plugin;

	/**
	 * The constructor
	 */
	public StringTemplateUI() {
	}

	public synchronized StringTemplateTextTools getTextTools() {
		if (textTools == null) {
			textTools = new StringTemplateTextTools(true);
		}
		return textTools;
	}
	
	public synchronized StringTemplateDocumentProvider getSourceModuleDocumentProvider() {

		if (fSourceModuleDocumentProvider == null) {
			fSourceModuleDocumentProvider = new StringTemplateDocumentProvider();
		}
		return fSourceModuleDocumentProvider;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.core.runtime.Plugins#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.core.runtime.Plugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static StringTemplateUI getDefault() {
		return plugin;
	}

}
