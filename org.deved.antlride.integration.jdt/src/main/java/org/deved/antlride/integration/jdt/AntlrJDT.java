/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.integration.jdt;

import java.util.Arrays;
import java.util.Hashtable;

import org.deved.antlride.core.env.JavaEnvironmentRepository;
import org.deved.antlride.core.launch.AntlrLauncher;
import org.deved.antlride.jdt.env.JDTEnvironmentRepository;
import org.deved.antlride.jdt.launch.AntlrJavaDebugLauncher;
import org.deved.antlride.jdt.launch.AntlrJavaLauncher;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.DebugPlugin;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

/**
 * The activator class controls the plug-in life cycle
 */
public class AntlrJDT extends Plugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.deved.antlride.integration.jdt";

	public static final boolean DEBUG = false;

	// The shared instance
	private static AntlrJDT instance;

	/**
	 * The constructor
	 */
	public AntlrJDT() {		
	}
	
	public static void main(String[] args) {
		args = DebugPlugin.parseArguments("-o /home/edgar/Documents\" \"and\" \"Settings/.metadata/.plugins/org.deved.antlride.core/java/whitespacebug/Expr/src");
		System.out.println(Arrays.toString(args)+"="+args.length);
	}

	public void start(BundleContext context) throws Exception {
		super.start(context);
		// java environment provider
		context
				.registerService(JavaEnvironmentRepository.class.getName(),
						new JDTEnvironmentRepository(),
						new Hashtable<String, Object>());
		// java launcher
		Hashtable<String, Object> properties = new Hashtable<String, Object>();
		properties.put("mode", "run");
		context.registerService(AntlrLauncher.class.getName(),
				new AntlrJavaLauncher(), properties);

		properties = new Hashtable<String, Object>();
		properties.put("mode", "debug");
		context.registerService(AntlrLauncher.class.getName(),
				new AntlrJavaDebugLauncher(), properties);

		instance = this;
	}

	public void stop(BundleContext context) throws Exception {
		instance = null;
		// java environment provider
		ServiceReference[] serviceReferences = context.getServiceReferences(
				JavaEnvironmentRepository.class.getName(), null);
		for (ServiceReference serviceReference : serviceReferences) {
			context.ungetService(serviceReference);
		}
		// java launcher
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static AntlrJDT getDefault() {
		return instance;
	}

	public static void error(Throwable throwable) {
		error(throwable.getMessage(), throwable);
	}

	public static void error(IStatus status) {
		getDefault().getLog().log(status);
	}

	public static void error(final String message, Throwable throwable) {
		org.eclipse.core.runtime.IStatus status = new Status(Status.ERROR,
				PLUGIN_ID, message, throwable);
		getDefault().getLog().log(status);
	}
}
