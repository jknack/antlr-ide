/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.debug.breakpoints;

import org.deved.antlride.debug.model.event.AntlrDebugTokenEvent;
import org.deved.antlride.debug.model.event.AntlrDebugEvent;
import org.deved.antlride.debug.model.event.AntlrDebugEventKind;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;

public class AntlrConsumeTokenBreakpoint extends AntlrTokenBreakpoint {
	
	public static final String ID = "org.deved.antlride.debug.consumeTokenBreakpoint";
	
	public AntlrConsumeTokenBreakpoint() {
	}

	public AntlrConsumeTokenBreakpoint(final IResource resource, final String elementName,
			final int lineNumber, final int columnNumber) throws CoreException {
		super(resource, elementName, lineNumber, columnNumber);
	}
	
	@Override
	public String getId() {
		return ID;
	}
		
	@Override
	public String getName() {
		return "consumeToken";
	}

	@Override
	protected boolean match(AntlrDebugEvent event) {
		if (event.getEventKind() == AntlrDebugEventKind.CONSUME_TOKEN) {
			String elementName = "";
			try {
				AntlrDebugTokenEvent tokenEvent = (AntlrDebugTokenEvent) event;
				elementName = getElementName();
				return elementName.equals(tokenEvent.getTokenName());
			} catch (Exception ex) {
			}
		}
		return false;
	}

}
