/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *******************************************************************************/
package org.deved.antlride.debug.ui;

import java.util.Arrays;
import java.util.Collection;

import org.deved.antlride.core.model.IGrammar;
import org.deved.antlride.core.model.evaluation.EvalElementFactory;
import org.deved.antlride.core.model.evaluation.IEvalElement;
import org.deved.antlride.core.util.AntlrTextHelper;
import org.deved.antlride.debug.model.event.AntlrDebugEvent;
import org.deved.antlride.debug.model.event.AntlrDebugEventKind;
import org.deved.antlride.debug.model.event.AntlrDebugExceptionEvent;
import org.deved.antlride.debug.model.event.AntlrDebugRuleEvent;
import org.deved.antlride.debug.model.event.AntlrDebugTokenEvent;

public class AntlrDebugParser {
	
	
	public static IEvalElement evaluate(IGrammar owner, AntlrDebugEvent[] elements) {
		return createGraphModel(owner, Arrays.asList(elements));
	}
	
	public static IEvalElement createGraphModel(IGrammar owner, Collection<AntlrDebugEvent> elements) {
		IEvalElement root = null;

		IEvalElement current = null;

		int decision = 0;

		for (AntlrDebugEvent event : elements) {
			AntlrDebugEventKind kind = event.getEventKind();
//			System.out.println(event);
			switch (kind) {
			case ENTER_DECISION:
				decision++;
				break;
			case EXIT_DECISION:
				decision--;
				break;
			case CONSUME_TOKEN: {
				if (decision != 0) {
					break;
				}
				AntlrDebugTokenEvent tokenEvent = (AntlrDebugTokenEvent) event;
				String tokenDisplayName = tokenEvent.getTokenName();
//				boolean isLexerRule = tokenDisplayName.length() >0&& tokenDisplayName.charAt(0) != '\'';
//				if (isLexerRule) {
//					IEvalElement evalElement = EvalElementFactory
//							.createRuleEvalElement(g, current, tokenDisplayName);
//					root = checkRoot(root, evalElement);
//					// set current node
//					current = evalElement;
//				}
				IEvalElement evalElement = EvalElementFactory
						.createTokenEvalElement(owner, current, AntlrTextHelper
								.unEscapeNewlines(tokenEvent.getText()),
								tokenEvent.getLine(), tokenEvent.getColumn());
				root = checkRoot(root, evalElement);
//				if (isLexerRule) {
//					current = current.getParent();
//				}
			}
				break;
			case ENTER_RULE: {
				AntlrDebugRuleEvent ruleEvent = (AntlrDebugRuleEvent) event;
				IEvalElement evalElement = EvalElementFactory
						.createRuleEvalElement(owner, current, ruleEvent.getRule());
				root = checkRoot(root, evalElement);
				// set current node
				current = evalElement;
			}
				break;
			case EXIT_RULE: {
				if (current != null) {
					current = current.getParent();
				}
			}
				break;
			case EXCEPTION: {
				if (decision != 0) {
					break;
				}
				AntlrDebugExceptionEvent exEvent = (AntlrDebugExceptionEvent) event;
				IEvalElement evalElement = EvalElementFactory
						.createExceptionEvalElement(owner, current, exEvent
								.getException(), exEvent.getMessage(), exEvent
								.getLine(), exEvent.getColumn());
				root = checkRoot(root, evalElement);
			}
				break;
			}
		}
		return root;
	}
	
	private static IEvalElement checkRoot(IEvalElement root, IEvalElement element) {
		return root == null ? element : root;
	}
}
