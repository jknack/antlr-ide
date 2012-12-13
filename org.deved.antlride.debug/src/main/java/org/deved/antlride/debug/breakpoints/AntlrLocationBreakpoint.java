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
import org.deved.antlride.debug.model.event.AntlrDebugEventKind;
import org.deved.antlride.debug.model.event.AntlrDebugLocationEvent;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.debug.core.model.ILineBreakpoint;

public class AntlrLocationBreakpoint extends AntlrBreakpoint implements
		ILineBreakpoint {

	public static final String ID = "org.deved.antlride.debug.locationBreakpoint";

	public static final String COLUMN_NUMBER = ID + ".columnNumber";

	public static final String GRAMMAR_NAME = ID + ".grammarName";

	public static final String ELEMENT_NAME = ID + ".elementName";

	public AntlrLocationBreakpoint() {
	}

	public AntlrLocationBreakpoint(final IResource resource,
			final String elementName, final int lineNumber,
			final int columnNumber) throws CoreException {
		IWorkspaceRunnable runnable = new IWorkspaceRunnable() {
			public void run(IProgressMonitor monitor) throws CoreException {
				IMarker marker = resource.createMarker(getId());
				setMarker(marker);
				marker.setAttribute(IBreakpoint.ENABLED, Boolean.TRUE);
				marker.setAttribute(IMarker.LINE_NUMBER, lineNumber);
				marker.setAttribute(COLUMN_NUMBER, columnNumber);
				marker.setAttribute(IBreakpoint.ID, getModelIdentifier());
				marker.setAttribute(ELEMENT_NAME, elementName);
				marker.setAttribute(GRAMMAR_NAME, resource.getName());

				marker.setAttribute(IMarker.MESSAGE, getMessage());
			}
		};
		run(getMarkerRule(resource), runnable);
	}

	public String getId() {
		return ID;
	}

	@Override
	public String getName() {
		return "location";
	}

	public String getMessage() {
		StringBuilder builder = new StringBuilder();
		try {
			builder.append(getGrammarName());
			builder.append(" [");
			builder.append(getName());
			builder.append(" (");
			builder.append(getLineNumber());
			builder.append(", ");
			builder.append(getColumnNumber());
			builder.append(")]");
		} catch (Exception ex) {

		}
		return builder.toString();
	}

	public int getLineNumber() throws CoreException {
		IMarker m = getMarker();
		if (m != null) {
			return m.getAttribute(IMarker.LINE_NUMBER, -1);
		}
		return -1;
	}

	public String getGrammarName() throws CoreException {
		IMarker m = getMarker();
		if (m != null) {
			return m.getAttribute(GRAMMAR_NAME, "");
		}
		return "";
	}

	public String getElementName() throws CoreException {
		IMarker m = getMarker();
		if (m != null) {
			return m.getAttribute(ELEMENT_NAME, "");
		}
		return "";
	}

	public int getColumnNumber() throws CoreException {
		IMarker m = getMarker();
		if (m != null) {
			return m.getAttribute(COLUMN_NUMBER, -1);
		}
		return -1;
	}

	public int getCharStart() throws CoreException {
		IMarker m = getMarker();
		if (m != null) {
			return m.getAttribute(IMarker.CHAR_START, -1);
		}
		return -1;
	}

	public int getCharEnd() throws CoreException {
		IMarker m = getMarker();
		if (m != null) {
			return m.getAttribute(IMarker.CHAR_END, -1);
		}
		return -1;
	}	

	protected boolean match(AntlrDebugEvent event) {
		if (event.getEventKind() == AntlrDebugEventKind.LOCATION) {
			AntlrDebugLocationEvent locationEvent = (AntlrDebugLocationEvent) event;
			return matchLocation(locationEvent.getLine(), locationEvent
					.getColumn());
		}
		return false;
	}

	protected boolean matchLocation(int line, int column) {
		try {
			return getLineNumber() == line && getColumnNumber() == column;
		} catch (CoreException ex) {
		}
		return false;
	}	
}
