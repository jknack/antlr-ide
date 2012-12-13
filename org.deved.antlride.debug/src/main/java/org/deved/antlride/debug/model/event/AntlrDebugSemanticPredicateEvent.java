/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.debug.model.event;

import org.deved.antlride.debug.model.AntlrDebugTarget;

public class AntlrDebugSemanticPredicateEvent extends AntlrDebugEvent {

	private boolean result;
	
	private String predicate;
	
	public AntlrDebugSemanticPredicateEvent(AntlrDebugTarget debugTarget,AntlrDebugEventKind eventKind, String...properties) {
		super(debugTarget, eventKind);
		this.result = Boolean.parseBoolean( properties[1] );
		this.predicate = getTextFromEvent(2, properties);
	}

	public boolean getResult() {
		return result;
	}

	public String getPredicate() {
		return predicate;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(super.toString());
		builder.append(result);
		builder.append(' ');
		builder.append(predicate);
		return builder.toString();
	}
}
