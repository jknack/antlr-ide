/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.debug.breakpoints;

import org.deved.antlride.core.model.IGrammar;
import org.deved.antlride.core.model.IModelElement;
import org.deved.antlride.core.model.IToken;
import org.deved.antlride.core.model.ast.ModelElementQuery;
import org.deved.antlride.debug.model.AntlrDebugTarget;
import org.deved.antlride.debug.model.event.AntlrDebugEvent;
import org.deved.antlride.debug.model.event.AntlrDebugEventKind;
import org.deved.antlride.debug.model.event.AntlrDebugLTEvent;
import org.deved.antlride.debug.model.event.AntlrDebugLocationEvent;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;

public class AntlrLTBreakpoint extends AntlrTokenBreakpoint {

	public static final String ID = "org.deved.antlride.debug.LTBreakpoint";

	public AntlrLTBreakpoint() {
	}

	public AntlrLTBreakpoint(final IResource resource,
			final String elementName, final int lineNumber,
			final int columnNumber) throws CoreException {
		super(resource, elementName, lineNumber, columnNumber);
	}

	@Override
	public String getId() {
		return ID;
	}

	@Override
	public String getName() {
		return "LT";
	}

	@Override
	protected boolean match(AntlrDebugEvent event) {
		if (event.getEventKind() == AntlrDebugEventKind.LT) {
			AntlrDebugTarget debugTarget = event.getDebugTarget();
			try {
				if(debugTarget.isInDecision()) {
					return false;
				}
				String elementName = getElementName();
				AntlrDebugLTEvent lt = (AntlrDebugLTEvent) event;
				IGrammar grammar = debugTarget.getGrammar();
				IToken token = grammar.findToken(lt.getTokenName());
				if (elementName.equals(lt.getTokenName()) || (token!=null && elementName.equals(token.getValueAsString()))) {
					AntlrDebugEvent[] events = debugTarget.getEvents();
					AntlrDebugEvent le = events[events.length - 2];
					if (le.getEventKind() != AntlrDebugEventKind.LOCATION) {
						return false;
					}
					AntlrDebugLocationEvent location = (AntlrDebugLocationEvent) le;					
					IModelElement element = ModelElementQuery.getElementAt(
							grammar, location.getLine(), location.getColumn());
					if (element != null)
						return element.getElementName().equals(elementName);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}

}
