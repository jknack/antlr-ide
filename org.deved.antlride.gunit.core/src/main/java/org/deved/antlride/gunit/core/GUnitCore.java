/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.gunit.core;

import java.util.Hashtable;

import org.deved.antlride.gunit.core.parser.GUnitSourceElementParser;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.dltk.core.ISourceElementParser;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceFactory;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

/**
 * The activator class controls the plug-in life cycle
 */
public class GUnitCore extends Plugin implements ServiceFactory {

	// The plug-in ID
	public static final String PLUGIN_ID = GUnitConstants.CORE_NAMESPACE;

	// The shared instance
	private static GUnitCore plugin;

	/**
	 * The constructor
	 */
	public GUnitCore() {
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
		Hashtable<String, String> properties = new Hashtable<String, String>();
		properties.put("parser", "GUnit");
		context.registerService(ISourceElementParser.class.getName(), this,
				properties);
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
	public static GUnitCore getDefault() {
		return plugin;
	}

	public Object getService(Bundle bundle, ServiceRegistration registration) {
		return new GUnitSourceElementParser();
	}

	public void ungetService(Bundle bundle, ServiceRegistration registration,
			Object service) {
		// TODO Auto-generated method stub

	}

}
