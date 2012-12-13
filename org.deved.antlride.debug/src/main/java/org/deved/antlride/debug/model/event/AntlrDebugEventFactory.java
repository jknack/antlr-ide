/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.debug.model.event;

import org.deved.antlride.core.model.IGrammar;
import org.deved.antlride.debug.model.AntlrDebugTarget;

public class AntlrDebugEventFactory {

	private AntlrDebugEventKind type;

	private AntlrDebugEventFactory() {
	}

	public static AntlrDebugEvent createFromString(
			AntlrDebugTarget debugTarget, String event) {
		return createFromString(debugTarget, debugTarget.getGrammar(), event);
	}

	public static AntlrDebugEvent createFromString(
			AntlrDebugTarget debugTarget, IGrammar grammar, String event) {
		String[] properties = event.split("\\s");
		AntlrDebugEvent debugEvent = null;
		AntlrDebugEventKind eventKind = AntlrDebugEventKind.fromString(event);
		switch (eventKind) {
		case PROTOCOL: {
			debugEvent = new AntlrDebugProtocolEvent(debugTarget, eventKind,
					properties);
		}
			break;
		case GRAMMAR: {
			debugEvent = new AntlrDebugGrammarEvent(debugTarget, eventKind,
					properties);
		}
			break;
		case ENTER_RULE:
		case EXIT_RULE: {
			debugEvent = new AntlrDebugRuleEvent(debugTarget, eventKind,
					properties);
		}
			break;
		case ENTER_SUB_RULE:
		case EXIT_SUB_RULE: {
			debugEvent = new AntlrDebugSubRuleEvent(debugTarget, eventKind,
					properties);
		}
			break;
		case ENTER_DECISION:
		case EXIT_DECISION: {
			debugEvent = new AntlrDebugDecisionEvent(debugTarget, eventKind,
					properties);
		}
			break;
		case LOCATION: {
			debugEvent = new AntlrDebugLocationEvent(debugTarget, eventKind,
					properties);
		}
			break;
		case ENTER_ALT: {
			debugEvent = new AntlrDebugAlternativeEvent(debugTarget, eventKind,
					properties);
		}
			break;
		case LT: {
			AntlrDebugLTEvent ltEvent = new AntlrDebugLTEvent(debugTarget,
					eventKind, properties);
//			ltEvent.setTokenName(grammar.getTokenName(ltEvent.getType()));
			ltEvent.setTokenName(ltEvent.getText());
			debugEvent = ltEvent;
		}
			break;
		case LN: {
			debugEvent = new AntlrDebugLNEvent(debugTarget, eventKind,
					properties);
		}
			break;
		case CONSUME_HIDDEN_TOKEN:
		case CONSUME_TOKEN: {
			AntlrDebugTokenEvent consumeTokenEvent = new AntlrDebugTokenEvent(
					debugTarget, eventKind, event, properties);
			consumeTokenEvent.setTokenName(consumeTokenEvent.getText());
//			consumeTokenEvent.setTokenName(grammar
//					.getTokenName(consumeTokenEvent.getType()));
			debugEvent = consumeTokenEvent;
		}
			break;
		case EXCEPTION: {
			debugEvent = new AntlrDebugExceptionEvent(debugTarget, eventKind,
					event, properties);
		}
			break;
		case CREATE_NODE_FROM_TOKEN_ELEMENTS: {
			debugEvent = new AntlrDebugNodeFromTokenElementsEvent(debugTarget,
					eventKind, properties);
		}
			break;
		case NIL_NODE: {
			debugEvent = new AntlrDebugNilNodeEvent(debugTarget, eventKind,
					properties);
		}
			break;
		case BECOME_ROOT: {
			debugEvent = new AntlrDebugBecomeRootEvent(debugTarget, eventKind,
					properties);
		}
			break;
		case CREATE_NODE: {
			debugEvent = new AntlrDebugCreateNodeEvent(debugTarget, eventKind,
					properties);
		}
			break;
		case ADD_CHILD: {
			debugEvent = new AntlrDebugAddChildEvent(debugTarget, eventKind,
					properties);
		}
			break;
		case BEGIN_BACKTRACK:
		case END_BACKTRACK: {
			debugEvent = new AntlrDebugBacktrackEvent(debugTarget, eventKind,
					properties);
		}
			break;
		case BEGIN_RESYNC:
		case END_RESYNC: {
			debugEvent = new AntlrDebugResyncEvent(debugTarget, eventKind);
		}
			break;
		case ERROR_NODE: {
			debugEvent = new AntlrDebugErrorNodeEvent(debugTarget, eventKind,
					properties);
		}
			break;
		case CONSUME_NODE: {
			debugEvent = new AntlrDebugConsumeNodeEvent(debugTarget, eventKind,
					properties);
		}
			break;
		case MARK: {
			debugEvent = new AntlrDebugMarkEvent(debugTarget, eventKind,
					properties);
		}
			break;
		case REWIND: {
			debugEvent = new AntlrDebugRewindEvent(debugTarget, eventKind,
					properties);
		}
			break;
		case SEMANTIC_PREDICATE: {
			debugEvent = new AntlrDebugSemanticPredicateEvent(debugTarget,
					eventKind, properties);
		}
			break;
		case SET_TOKEN_BOUNDARIES: {
			debugEvent = new AntlrDebugTokenBoundariesEvent(debugTarget,
					eventKind, properties);
		}
			break;
		case TERMINATE: {
			debugEvent = new AntlrDebugTerminateEvent(debugTarget, eventKind);
		}
			break;
		}
		// create event
		return debugEvent;
	}

	public AntlrDebugEventKind getType() {
		return type;
	}
}
