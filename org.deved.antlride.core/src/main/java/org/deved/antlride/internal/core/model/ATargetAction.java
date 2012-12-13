/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.internal.core.model;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.deved.antlride.core.model.ElementKind;
import org.deved.antlride.core.model.IGrammar;
import org.deved.antlride.core.model.IModelElement;
import org.deved.antlride.core.model.IReference;
import org.deved.antlride.core.model.IRule;
import org.deved.antlride.core.model.IRuleAction;
import org.deved.antlride.core.model.ISourceElement;
import org.deved.antlride.core.model.IStatement;
import org.deved.antlride.core.model.ITargetAction;
import org.deved.antlride.core.model.ast.IModelElementVisitor;
import org.deved.antlride.core.model.ast.criteria.IModelElementCriteria;
import org.deved.antlride.core.model.ast.criteria.ModelElementCriteriaFactory;

public class ATargetAction extends AAbstractModelElement implements
		ITargetAction {

	private static final IReference[] NO_REFERENCES = new IReference[0];

	private String action;

	private IReference[] references;

	public ATargetAction(IModelElement parent, ISourceElement action) {
		super(parent);
		this.action = action.getText();
		setSourceStart(action.sourceStart());
		setSourceEnd(action.sourceEnd());
		//TODO: review the search references code
		references = NO_REFERENCES;
//		System.out.println(getText());
	}

	public <E> E getAdapter(Class<E> adapter) {
		if (adapter == ITargetAction.class)
			return adapter.cast(this);
		if (adapter == IRule.class && getEnclosingRule() != null)
			return adapter.cast(getEnclosingRule().getAdapter(adapter));
		return getParent().getAdapter(adapter);
	}

	public IRule getEnclosingRule() {
		IRule rule = null;
		IModelElement element = getParent();
		if (element instanceof IStatement) {
			IStatement statement = (IStatement) element;
			rule = statement.getEnclosingRule();
		} else if (element instanceof IRuleAction) {
			IRuleAction ruleAction = (IRuleAction) element;
			rule = (IRule) ruleAction.getParent();
		}
		return rule;
	}

	public EBNF getEbnfOperator() {
		return EBNF.NONE;
	}

	public IReference[] getReferences() {
		if (references == null) {			
			references = processAction(getParent(), getText(), sourceStart());
		}
		return references;
	}

	public String getText() {
		return action;
	}

	public ElementKind getElementKind() {
		return ElementKind.TARGET_ACTION;
	}

	public String getElementName() {
		return "<{...}>";
	}

	public void traverse(IModelElementVisitor visitor) {
		if (visitor.visitTargetAction(this)) {
			IReference[] references = getReferences();
			if (references.length > 0) {
				for (int i = 0; i < references.length; i++) {
					references[i].traverse(visitor);
				}
			}
			visitor.endvisitTargetAction(this);
		}
	}

	// private IScope
	private IReference[] processAction(IModelElement element, String text,
			int sourceStart) {
		int offset = sourceStart + 1;
		String split = " \t\n\r\f=,;+-*/^()!&|<>%[]{}";
		List<IReference> references = new ArrayList<IReference>(5);
		StringTokenizer tokenizer = new StringTokenizer(text, split, true);
//		IRule rule = getEnclosingRule();
		while (tokenizer.hasMoreTokens()) {
			String token = tokenizer.nextToken();
			int k = token.indexOf('$');
			if (split.indexOf(token) == -1 && k != -1) {
//				int start = offset + k;
				int index = token.indexOf(":", k);
				if (index != -1) {
					// scope
					int i0 = token.indexOf("::", k);
					if (i0 == -1) {
						i0 = index;
					}
//					String scopeName = token.substring(k + 1, index);
//					IModelElement scope = findScope(element, scopeName);
//					int scopeLen = start + scopeName.length();

//					IReference scopeReference = new AReference(this,
//							new ASourceElement(scopeName, start, scopeLen),
//							scope);
//					references.add(scopeReference);
//					int attrStart = scopeLen + 2;

//					int i1 = attrStart - offset + 1;
//					int i2 = token.indexOf('.');
//					String attrName = "";
//					try {
//						if (i2 == -1) {
//							attrName = token.substring(i1);
//						} else {
//							attrName = token.substring(i1, i2);
//						}
//					} catch (StringIndexOutOfBoundsException ex) {
//
//					}

//					IScopeAttribute scopeAttribute = findScopeAttribute(scope,
//							attrName);
//					IReference scopeAttributeReference = new AReference(this,
//							new ASourceElement(attrName, attrStart, attrStart
//									+ attrName.length()), scopeAttribute);
//					references.add(scopeAttributeReference);
				} else {
//					index = token.indexOf('.', k);
//					String ruleName = null;
//					if (index != -1) {
//						ruleName = token.substring(k + 1, index);
//					} else {
//						ruleName = token.substring(k + 1);
//					}
					IModelElementCriteria kindCriteria = ModelElementCriteriaFactory
							.callOrVariable();
					kindCriteria = ModelElementCriteriaFactory.or(kindCriteria,
							ModelElementCriteriaFactory
									.grammarScopeOrRuleScope());
//					IModelElementCriteria criteria = ModelElementCriteriaFactory
//							.and(kindCriteria, ModelElementCriteriaFactory
//									.nameEquals(ruleName));
//					AntlrModelElementCollectorVisitor collector = new AntlrModelElementCollectorVisitor(
//							criteria);
					// try to find a var decl
//					Object[] elements = findClosestStatement(collector, parent,
//							ruleName);
//					IModelElement e = null;
//					if (elements != null && elements.length > 0) {
//						e = (IModelElement) elements[0];
//					} else {
//						if (ruleName.equals(rule.getElementName())) {
//							e = rule;
//						}
//					}
//					IReference reference = new AReference(this,
//							new ASourceElement(ruleName, start, start
//									+ ruleName.length()), e);
//					references.add(reference);
				}
			}
			offset += token.length();
		}
		IReference[] result = NO_REFERENCES;
		if (references.size() > 0)
			result = references.toArray(new IReference[references.size()]);
		references.clear();
		return result;
	}

//	private Object[] findClosestStatement(
//			AntlrModelElementCollectorVisitor collector, IModelElement p,
//			String elementName) {
//		boolean notMatch = true;
//		while (notMatch && p.getElementKind() != ElementKind.RULE) {
//			collector.accept(p);
//			if (collector.getResultSize() > 0) {
//				notMatch = false;
//			}
//			p = p.getParent();
//		}
//		return notMatch ? null : collector.getResult();
//	}

	private IModelElement findScope(IModelElement element, String scopeName) {
		IModelElement scope = null;
		IRule rule = element.getAdapter(IRule.class);
		IGrammar grammar = element.getAdapter(IGrammar.class);
		// if (element instanceof IStatement) {
		// IStatement statement = (IStatement) element;
		// rule = statement.getEnclosingRule();
		// grammar = (IGrammar) rule.getParent();
		// } else if (element instanceof IRule) {
		// rule = (IRule) element;
		// grammar = (IGrammar) rule.getParent();
		// } else if (element instanceof IRuleAction) {
		// IRuleAction ruleAction = (IRuleAction) element;
		// rule = (IRule) ruleAction.getParent();
		// grammar = (IGrammar) rule.getParent();
		// } else if (element instanceof IGrammar) {
		// grammar = (IGrammar) element;
		// }
		if (rule != null) {
			scope = rule.findScope(scopeName);
			if (scope == null) {
				scope = rule.findScopeReference(scopeName);
			}
		}
		if (scope == null) {
			scope = grammar.findScope(scopeName);
		}
		return scope;
	}

//	private IScopeAttribute findScopeAttribute(IModelElement element,
//			String scopeAttributeName) {
//		if (element == null)
//			return null;
//		IScope scope = null;
//		if (element instanceof IScope) {
//			scope = (IScope) element;
//		} else if (element instanceof IScopeReference) {
//			scope = (IScope) ((IScopeReference) element).getReference();
//		}
//		IScopeAttribute scopeAttribute = null;
//		if (scope != null) {
//			scopeAttribute = scope.findAttribute(scopeAttributeName);
//		}
//		return scopeAttribute;
//	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		// builder.append('{');
		builder.append(getText());
		// builder.append('}');
		return builder.toString();
	}
}
