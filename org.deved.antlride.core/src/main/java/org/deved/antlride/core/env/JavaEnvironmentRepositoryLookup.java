/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.core.env;

import org.deved.antlride.core.AntlrCore;
import org.eclipse.core.resources.IProject;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class JavaEnvironmentRepositoryLookup {

	private static final JavaEnvironmentRepository DEF_ENV = new DefaultJavaEnvironmentRepository();

	public static JavaEnvironmentRepository lookup() {
		JavaEnvironmentRepository delegate = null;
		try {
			Bundle bundle = AntlrCore.getDefault().getBundle();
			BundleContext bundleContext = bundle.getBundleContext();
			ServiceReference serviceReference = bundleContext
					.getServiceReference(JavaEnvironmentRepository.class
							.getName());
			delegate = (JavaEnvironmentRepository) bundleContext
					.getService(serviceReference);
		} catch (Exception ex) {
			delegate = DEF_ENV;
		}
		return new ForwardingJavaEnvironmentRepository( delegate );
	}

	private static class ForwardingJavaEnvironmentRepository implements
			JavaEnvironmentRepository {
		private JavaEnvironmentRepository delegate;

		public ForwardingJavaEnvironmentRepository(
				JavaEnvironmentRepository delegate) {
			this.delegate = delegate;
		}

		public JavaEnvironment getEnvironment(IProject project) {
			JavaEnvironment environment = delegate.getEnvironment(project);
			return environment == null ? DEF_ENV.getEnvironment(project)
					: environment;
		}

		public JavaEnvironment getEnvironment() {
			JavaEnvironment environment = delegate.getEnvironment();
			return environment == null ? DEF_ENV.getEnvironment() : environment;
		}

	}
}
