/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.core.resources;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.Platform;

public class AntlrGeneratedResource implements IAdaptable {
	private IResource resource;
	private String grammarName;

	public AntlrGeneratedResource(IResource resource, String grammarName) {
		this.resource = resource;
		this.grammarName = grammarName;
	}

	@SuppressWarnings("rawtypes")
	public Object getAdapter(Class adapter) {
		if (adapter.isInstance(resource)) {
			return resource;
		}
		return Platform.getAdapterManager().getAdapter(this, adapter);
	}

	public String getGrammarName() {
		return grammarName;
	}

	public String getFileExtension() {
		return resource.getFileExtension();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(resource.getName());
		builder.append(" [ANTLR 3: ");
		builder.append(grammarName);
		builder.append("]");
		return builder.toString();
	}
}
