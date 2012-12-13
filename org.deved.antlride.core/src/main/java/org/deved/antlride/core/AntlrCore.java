/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.core;

import org.deved.antlride.core.resources.AntlrResourceTracker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Preferences;
import org.eclipse.core.runtime.Status;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
@SuppressWarnings("deprecation")
public class AntlrCore extends Plugin {

	public static final boolean DEBUG = false;

	// The plug-in ID
	public static final String PLUGIN_ID = AntlrConstants.ANTLR_CORE;

	// The shared instance
	private static AntlrCore plugin;

	public static String ANTLR_SOURCE_PARSER_KEY = "antlr_core_source_parser";

	private BundleContext context;

	/**
	 * The constructor
	 */
	public AntlrCore() {
	}
	
	public static boolean getBooleanProperty(String name) {
		return getPreferences().getBoolean(name);
	}
		
	private static Preferences getPreferences() {
		return getDefault().getPluginPreferences();
	}

	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		this.context = context;
		//init
		AntlrLanguageToolkit.getDefault();
		AntlrResourceTracker.startTracking();
	}

	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
		this.context = (null);
		AntlrResourceTracker.stopTracking();
		savePluginPreferences();
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static AntlrCore getDefault() {
		return plugin;
	}

	public static void error(String message) {
		error(message, null);
	}

	public static void warning(String message) {
		warning(message, null);
	}

	public static void info(String message) {
		info(message, null);
	}

	public static void error(Throwable throwable) {
		error(throwable.getMessage(), throwable);
	}

	public static void error(CoreException ex) {
		getDefault().getLog().log(ex.getStatus());
	}

	public static void error(final String message, Throwable throwable) {
		org.eclipse.core.runtime.IStatus status = new Status(IStatus.ERROR,
				PLUGIN_ID, message, throwable);
		getDefault().getLog().log(status);
	}

	public static void warning(final String message, Throwable throwable) {
		org.eclipse.core.runtime.IStatus status = new Status(IStatus.WARNING,
				PLUGIN_ID, message, throwable);
		getDefault().getLog().log(status);
	}

	public static void info(String message, Throwable throwable) {
		org.eclipse.core.runtime.IStatus status = new Status(IStatus.INFO, getDefault()
				.getBundle().getSymbolicName(), 44, message, throwable);
		getDefault().getLog().log(status);
	}

	public BundleContext getContext() {
		return context;
	}
}
