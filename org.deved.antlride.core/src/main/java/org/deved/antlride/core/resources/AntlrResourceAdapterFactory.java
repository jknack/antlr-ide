/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.core.resources;

import org.deved.antlride.core.AntlrConstants;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.core.runtime.Path;

public class AntlrResourceAdapterFactory extends AntlrAdapterFactory implements
		IAdapterFactory {

	@SuppressWarnings("rawtypes")
	public Object getAdapter(Object adaptableObject, Class adapterType) {
		if (AntlrGeneratedResource.class == adapterType) {
			IAdaptable adaptable = (IAdaptable) adaptableObject;
			IResource resourceFile = (IResource) adaptable
					.getAdapter(IResource.class);
			return getAdapter(resourceFile);
		}
		return null;
	}

	protected Object getAdapter(IResource resourceFile) {
		if (resourceFile == null || !resourceFile.isDerived()) {
			return null;
		}
		try {
			String owner = resourceFile
					.getPersistentProperty(AntlrConstants.Q_ANTLR_RESOURCE_OWNER);
			if (owner != null) {
				AntlrGeneratedResource antlrResource = getResourceAdapter(resourceFile);
				if (antlrResource == null) {
					String grammarName = new Path(owner).lastSegment();
					antlrResource = createResourceAdapter(resourceFile,
							grammarName);
				}
				return antlrResource;
			}
		} catch (CoreException e) {
			e.printStackTrace();
			// shhhhh
		}
		return null;
	}
}
