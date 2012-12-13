/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.debug.breakpoints;

import org.deved.antlride.debug.model.event.AntlrDebugEvent;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;

public abstract class AntlrTokenBreakpoint extends AntlrLocationBreakpoint {

	protected AntlrTokenBreakpoint() {
	}

	protected AntlrTokenBreakpoint(final IResource resource,
			final String elementName, final int lineNumber,
			final int columnNumber) throws CoreException {
		super(resource, elementName, lineNumber, columnNumber);
	}

	@Override
	public abstract String getId();
	
	@Override
	public abstract String getName();

	@Override
	public String getMessage() {
		StringBuilder builder = new StringBuilder();
		try {
			builder.append(getGrammarName());
			builder.append(" [");
			builder.append(getName());
			builder.append(" (");
			builder.append(getElementName());
			builder.append(")]");
		} catch (Exception ex) {

		}
		return builder.toString();
	}

	@Override
	protected abstract boolean match(AntlrDebugEvent event);
}
