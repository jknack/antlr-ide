/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.gunit.ui;

import org.deved.antlride.gunit.core.GUnitConstants;
import org.deved.antlride.gunit.internal.ui.editor.GUnitDocumentProvider;
import org.deved.antlride.gunit.internal.ui.text.GUnitTextTools;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class GUnitUI extends AbstractUIPlugin {

	private GUnitTextTools textTools;

	private GUnitDocumentProvider fSourceModuleDocumentProvider;

	// The plug-in ID
	public static final String PLUGIN_ID = GUnitConstants.UI_NAMESPACE;

	// The shared instance
	private static GUnitUI plugin;

	/**
	 * The constructor
	 */
	public GUnitUI() {
	}

	public synchronized GUnitTextTools getTextTools() {
		if (textTools == null) {
			textTools = new GUnitTextTools(true);
		}
		return textTools;
	}
	
	public synchronized GUnitDocumentProvider getSourceModuleDocumentProvider() {

		if (fSourceModuleDocumentProvider == null) {
			fSourceModuleDocumentProvider = new GUnitDocumentProvider();
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
	public static GUnitUI getDefault() {
		return plugin;
	}

}
