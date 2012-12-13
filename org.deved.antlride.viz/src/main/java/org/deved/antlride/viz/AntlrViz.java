/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.viz;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class AntlrViz extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.deved.antlride.viz";

	// The shared instance
	private static AntlrViz plugin;

	/**
	 * The constructor
	 */
	public AntlrViz() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext
	 * )
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext
	 * )
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
	public static AntlrViz getDefault() {
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
		error(throwable.getClass().getName(), throwable);
	}

	public static void error(CoreException ex) {
		getDefault().getLog().log(ex.getStatus());
	}

	public static void error(final String message, Throwable throwable) {
		org.eclipse.core.runtime.IStatus status = new Status(Status.ERROR,
				PLUGIN_ID, message, throwable);
		getDefault().getLog().log(status);
	}

	public static void warning(final String message, Throwable throwable) {
		org.eclipse.core.runtime.IStatus status = new Status(Status.WARNING,
				PLUGIN_ID, message, throwable);
		getDefault().getLog().log(status);
	}

	public static void info(String message, Throwable throwable) {
		org.eclipse.core.runtime.IStatus status = new Status(1, getDefault()
				.getBundle().getSymbolicName(), 44, message, throwable);
		getDefault().getLog().log(status);
	}
}
