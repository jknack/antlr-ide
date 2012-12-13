/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.stringtemplate.core;

import java.util.Hashtable;

import org.deved.antlride.stringtemplate.core.parser.StringTemplateSourceElementParser;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;
import org.eclipse.dltk.core.ISourceElementParser;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceFactory;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

/**
 * The activator class controls the plug-in life cycle
 */
public class StringTemplateCore extends Plugin implements ServiceFactory {

	// The plug-in ID
	public static final String PLUGIN_ID = StringTemplateConstants.CORE_NAMESPACE;

	// The shared instance
	private static StringTemplateCore plugin;

	/**
	 * The constructor
	 */
	public StringTemplateCore() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.core.runtime.Plugins#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		Hashtable<String, String> properties = new Hashtable<String, String>();
		properties.put("parser", "StringTemplate");
		context.registerService(ISourceElementParser.class.getName(), this,
				properties);
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
		ServiceReference serviceReference = context
				.getServiceReference(ISourceElementParser.class.getName());
		context.ungetService(serviceReference);
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static StringTemplateCore getDefault() {
		return plugin;
	}

	public Object getService(Bundle bundle, ServiceRegistration registration) {
		return new StringTemplateSourceElementParser();
	}

	public void ungetService(Bundle bundle, ServiceRegistration registration,
			Object service) {
	}

	public static void error(final String message, Throwable throwable) {
		org.eclipse.core.runtime.IStatus status = new Status(Status.ERROR,
				PLUGIN_ID, message, throwable);
		getDefault().getLog().log(status);
	}
}
