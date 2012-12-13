/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.core.model.ast;

import java.util.StringTokenizer;

import org.deved.antlride.core.model.ElementKind;
import org.deved.antlride.core.model.IAssign;
import org.deved.antlride.core.model.ICallExpression;
import org.deved.antlride.core.model.IGrammar;
import org.deved.antlride.core.model.IModelElement;
import org.deved.antlride.core.model.IParameter;
import org.deved.antlride.core.model.IReference;
import org.deved.antlride.core.model.IRule;
import org.deved.antlride.core.model.IRuleAction;
import org.deved.antlride.core.model.IScope;
import org.deved.antlride.core.model.IScopeAttribute;
import org.deved.antlride.core.model.IScopeReference;
import org.deved.antlride.core.model.ISourceElement;
import org.deved.antlride.core.model.IStatementAction;
import org.deved.antlride.core.model.IVariable;
import org.deved.antlride.core.model.ast.criteria.IModelElementCriteria;
import org.deved.antlride.core.model.ast.criteria.ModelElementCriteriaFactory;

public class AntlrModelElementLocator {

	private IModelElement element;

	public AntlrModelElementLocator(IModelElement element) {
		this.element = element;
	}

	public IModelElement getElementAt(int position) {
		IModelElement[] elements = internalGetElementAt(position);
		int[] positions = { Integer.MAX_VALUE, Integer.MAX_VALUE,
				Integer.MAX_VALUE };
		for (int i = 0; i < elements.length; i++) {
			if (elements[i] != null && elements[i].isIn(position)) {
				int min1 = Math.abs(elements[i].sourceStart() - position);
				int min2 = Math.abs(elements[i].sourceEnd() - position);
				positions[i] = Math.min(min1, min2);
			}
		}
		int min = Integer.MAX_VALUE;
		int element = 0;
		for (int i = 0; i < positions.length; i++) {
			if (positions[i] < min || (positions[i] == min && element != 1)) {
				min = positions[i];
				element = i;
			}
		}
		IModelElement e = elements[element];
		if (e != null) {
			IRule r = e.getAdapter(IRule.class);
			if (r != null) {
				ISourceElement ruleName = r.getName();
				if (ruleName.isIn(position)) {
					return r;
				}
			}
		}
		return e;
	}

	private IModelElement[] internalGetElementAt(int position) {
		IModelElement[] elements = { null, null, null };
		IModelElement e = null;
		try {
			e = findElement(position);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (e != null) {
			elements[1] = e;
			int previous = position - 1;
			while (previous >= 0 && elements[1] == e) {
				try {
					e = findElement(previous);
					previous--;
				} catch (Exception ex) {
					e = null;
				}
			}
			elements[0] = e;
			e = elements[1];
			position++;
			IGrammar grammar = e.getAdapter(IGrammar.class);
			int sourceEnd = grammar.sourceEnd();
			while (position < sourceEnd && elements[1] == e) {
				try {
					e = findElement(position);
					position++;
				} catch (Exception ex) {
					e = null;
				}
			}
			elements[2] = e;
		}
		return elements;
	}

	private IModelElement findElement(int position) {
		AntlrModelElementLocatorVisitor locator = new AntlrModelElementLocatorVisitor(
				position);
		locator.accept(element);
		IModelElement e = locator.getResult();
		IModelElement p = null;
		while (e != null) {
			p = e;
			locator.accept(e);
			e = locator.getResult();
		}
		return p;
	}

	public IModelElement getElementDeclaration(int position) {
		IModelElement element = getElementAt(position);
		if (element == null || !element.isIn(position)) {
			return null;
		}
		IModelElement e = null;
		if (element.getElementKind() == ElementKind.CALL) {
			e = process((ICallExpression) element);
		} else if (element.getElementKind() == ElementKind.REFERENCE) {
			e = ((IReference) element).getReference();
		} else if (element.getElementKind() == ElementKind.STATEMENT_ACTION) {
			IStatementAction statementAction = (IStatementAction) element;
			e = processAction(statementAction, statementAction
					.getEnclosingRule(), statementAction.getAction().getText(),
					statementAction.sourceStart(), position);
		} else if (element.getElementKind() == ElementKind.CALL_PARAMETER) {
			e = processCallParameter((IParameter) element, position);
		} else if (element.getElementKind() == ElementKind.SCOPE_REFERENCE) {
			IScopeReference scopeReference = (IScopeReference) element;
			e = scopeReference.getReference();
		} else if (element.getElementKind() == ElementKind.VARIABLE) {
			IVariable variable = (IVariable) element;
			IAssign assign = variable.getAdapter(IAssign.class);
			if (assign != null) {
				e = assign.getStatement();
			}
		} else if (element.getElementKind() == ElementKind.RULE) {
			IRule rule = (IRule) element;
			ISourceElement name = rule.getName();
			if (name.isIn(position)) {
				e = rule;
			}
		} else if (element.getElementKind() == ElementKind.RULE_ACTION) {
			IRuleAction ruleAction = (IRuleAction) element;
			String text = ruleAction.getAction().getText();
			int start = ruleAction.getAction().sourceStart();
			e = processAction(ruleAction, (IRule) ruleAction.getParent(), text,
					start, position);
		}
		return e;
	}

	private IModelElement processCallParameter(IParameter parameter,
			int position) {
		// try to find a var decl
		String parameterName = parameter.getElementName();
		if (parameterName.charAt(0) == '$')
			parameterName = parameterName.substring(1);
		IModelElementCriteria criteria = ModelElementCriteriaFactory.callOrVariable(parameterName);
		AntlrModelElementCollectorVisitor collector = new AntlrModelElementCollectorVisitor(
				criteria);
		String pattern = ".";
		int index = parameterName.indexOf(pattern);
		if (index > -1) {
			parameterName = parameterName.substring(0, index);
		} else {
			pattern = "::";
			index = parameterName.indexOf(pattern);
			if (index > -1) {
				parameterName = parameterName.substring(0, index);
			}
		}
		if (position <= parameter.sourceStart() + parameterName.length()) {
			Object[] elements = findClosestStatement(collector, parameter
					.getParent(), parameterName);
			if (elements != null && elements.length > 0) {
				return (IModelElement) elements[0];
			}
		}
		return null;
	}

	private IModelElement processAction(IModelElement statement, IRule rule,
			String text, int sourceStart, int position) {
		int offset = sourceStart + 1;
		IModelElement e = null;
		String split = " \t\n\r\f=,;+-*/^()!&|<>%[]{}";
		StringTokenizer tokenizer = new StringTokenizer(text, split, true);
		IModelElement parent = (IModelElement) statement.getParent();
		while (tokenizer.hasMoreTokens()) {
			String token = tokenizer.nextToken();
			if (split.indexOf(token) == -1 && token.charAt(0) == '$') {
				int start = offset + 1;
				int end = start + token.length() - 1;
				if (position >= start && position < end) {
					int index = token.indexOf("::");
					if (index != -1) {
						// scope
						String scopeName = token.substring(1, index);
						IScope scope = rule.findScope(scopeName);
						IScopeReference scopeReference = null;
						if (scope == null) {
							scopeReference = rule.findScopeReference(scopeName);
							scope = (IScope) scopeReference.getReference();
						}
						if (scope == null) {
							continue;
						}
						int scopeLen = start + scopeName.length();
						if (position < scopeLen) {
							e = scopeReference == null ? scope : scopeReference;
							break;
						}
						if (position == scopeLen) {
							break;
						}
						int attrStart = scopeLen + 2;
						if (position < attrStart) {
							break;
						}
						int i1 = attrStart - offset;
						String attrName = token.substring(i1);
						IScopeAttribute scopeAttribute = scope
								.findAttribute(attrName);
						if (scopeAttribute == null) {
							e = scope;
							break;
						}
						e = scopeAttribute;
						break;
					} else {
						index = token.indexOf('.');
						String ruleName = token.substring(1);
						if (index != -1) {
							ruleName = token.substring(1, index);
						}
						if (position < start + ruleName.length()) {
							IModelElementCriteria criteria = ModelElementCriteriaFactory.callOrVariable(ruleName);
							AntlrModelElementCollectorVisitor collector = new AntlrModelElementCollectorVisitor(
									criteria);
							// try to find a var decl
							Object[] elements = findClosestStatement(collector,
									parent, ruleName);
							if (elements != null && elements.length > 0) {
								e = (IModelElement) elements[0];
							}
							break;
						}
					}
				}
			}
			offset += token.length();
		}
		return e;
	}

	private Object[] findClosestStatement(
			AntlrModelElementCollectorVisitor collector, IModelElement p,
			String elementName) {
		boolean notMatch = true;
		while (notMatch && p.getElementKind() != ElementKind.RULE) {
			collector.accept(p);
			if (collector.getResultSize() > 0) {
				notMatch = false;
			}
			p = p.getParent();
		}
		return notMatch ? null : collector.getResult();
	}

	private IModelElement process(ICallExpression callExpression) {
		IModelElement e = null;
		IRule rule = callExpression.getEnclosingRule();
		if (callExpression.hasLabel()) {
			String varName = callExpression.getElementName();
			IModelElementCriteria criteria = ModelElementCriteriaFactory.call(varName);
			AntlrModelElementCollectorVisitor collector = new AntlrModelElementCollectorVisitor(
					criteria);
			Object[] elements = findClosestStatement(collector, callExpression
					.getParent(), varName);
			if (elements != null && elements.length > 0)
				return (IModelElement) elements[0];
			if (varName.equals(rule.getElementName()))
				return rule;
		} else {
			String ruleName = callExpression.getRuleName().getText();
			IGrammar grammar = (IGrammar) rule.getParent();
			e = grammar.findRule(ruleName);
			if (e == null) {
				e = grammar.findToken(ruleName);
			}
		}
		return e;
	}
}
