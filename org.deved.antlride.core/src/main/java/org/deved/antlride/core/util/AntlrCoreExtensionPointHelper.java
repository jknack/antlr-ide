/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.core.util;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.Platform;

public class AntlrCoreExtensionPointHelper {
	public static final String LANGUAGE_TARGET = "languageTarget";

	@SuppressWarnings("unchecked")
	public static <E> List<E> loadExtensions(String namespace,
			String extensionPointName) {
		IExtensionPoint extensionPoint = Platform.getExtensionRegistry()
				.getExtensionPoint(namespace, extensionPointName);
		IConfigurationElement[] configurationElements = extensionPoint
				.getConfigurationElements();
		List<E> filters = new ArrayList<E>(configurationElements.length);
		for (int i = 0; i < configurationElements.length; i++) {
			IConfigurationElement configurationElement = configurationElements[i];
			try {
				filters.add((E) configurationElement
						.createExecutableExtension("class"));
			} catch (Throwable t) {
			}
		}
		return filters;
	}	

}
