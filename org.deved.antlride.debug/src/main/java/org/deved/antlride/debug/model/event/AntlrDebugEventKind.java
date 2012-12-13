/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.debug.model.event;

public enum AntlrDebugEventKind {
	PROTOCOL("ANTLR"),
	GRAMMAR("grammar"),
	ENTER_RULE("enterRule"),
	EXIT_RULE("exitRule"),
	LOCATION("location"),
	ENTER_ALT("enterAlt"),
	LT("LT"),
	LN("LN"),
	CONSUME_TOKEN("consumeToken"),
	CONSUME_HIDDEN_TOKEN("consumeHiddenToken"),
	ENTER_SUB_RULE("enterSubRule"),
	EXIT_SUB_RULE("exitSubRule"),
	ENTER_DECISION("enterDecision"),
	EXIT_DECISION("exitDecision"),
	EXCEPTION("exception"),
	/**
	 * Tree elements
	 */
	NIL_NODE("nilNode"),
	CREATE_NODE_FROM_TOKEN_ELEMENTS("createNodeFromTokenElements"),
	BECOME_ROOT("becomeRoot"),
	CREATE_NODE("createNode"),
	ADD_CHILD("addChild"),
	BEGIN_BACKTRACK("beginBacktrack"),
	END_BACKTRACK("endBacktrack"),
	BEGIN_RESYNC("beginResync"),
	END_RESYNC("endResync"),
	ERROR_NODE("errorNode"),	
	CONSUME_NODE("consumeNode"),
	/**
	 * Misc
	 */
	MARK("mark"),
	REWIND("rewind"),
	SEMANTIC_PREDICATE("semanticPredicate"),
	SET_TOKEN_BOUNDARIES("setTokenBoundaries"),
	TERMINATE("terminate");
	
	private String description;

	private AntlrDebugEventKind(String description) {
		this.description = description;
	}

	public String description() {
		return description;
	}
	
	public static AntlrDebugEventKind fromString(String event) {
		String eventName = event.split("\\s")[0];
		AntlrDebugEventKind[] events = values();
		for (AntlrDebugEventKind eventType : events) {
			if(eventType.description.equals(eventName))
				return eventType;
		}
		return null;
	}
}
