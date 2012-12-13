/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.core.resources;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdapterFactory;

public abstract class AntlrAdapterFactory implements IAdapterFactory {
	private Map<IResource, AntlrGeneratedResource> adapters;

	private static final Class<?>[] SUPPORTED_TYPES = { AntlrGeneratedResource.class };

	public AntlrAdapterFactory() {
		adapters = new HashMap<IResource, AntlrGeneratedResource>();
	}

	protected AntlrGeneratedResource getResourceAdapter(IResource resource) {
		return adapters.get(resource);
	}

	protected AntlrGeneratedResource createResourceAdapter(IResource resource,
			String grammarName) {
		AntlrGeneratedResource antlrResource = new AntlrGeneratedResource(
				resource, grammarName);
		adapters.put(resource, antlrResource);
		return antlrResource;
	}

	public Class<?>[] getAdapterList() {
		return SUPPORTED_TYPES;
	}
}
