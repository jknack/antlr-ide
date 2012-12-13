/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.core.model.ast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.deved.antlride.core.model.ElementKind;
import org.deved.antlride.core.model.IBlock;
import org.deved.antlride.core.model.ICallExpression;
import org.deved.antlride.core.model.ICompositeStatement;
import org.deved.antlride.core.model.IGrammar;
import org.deved.antlride.core.model.IModelElement;
import org.deved.antlride.core.model.IRule;
import org.deved.antlride.core.model.IScope;
import org.deved.antlride.core.model.IStatement;
import org.deved.antlride.core.model.ast.criteria.IModelElementCriteria;
import org.deved.antlride.core.model.ast.criteria.ModelElementCriteriaFactory;
import org.deved.antlride.core.util.AntlrTextHelper;

public class ModelElementQuery {

	public static int count(IModelElement element) {
		return count(element, null);
	}

	public static int count(IModelElement element,
			IModelElementCriteria criteria) {
		ModelElementCounterVisitor visitor = new ModelElementCounterVisitor(
				criteria);
		visitor.accept(element);
		return visitor.getResult();
	}

	@SuppressWarnings("unchecked")
	public static IRule[] collectRules(IGrammar grammar,
			boolean searchDependents) {
		AntlrModelElementCollectorVisitor collector = new AntlrModelElementCollectorVisitor(
				ModelElementCriteriaFactory.kind(ElementKind.RULE));
		collector.accept(grammar);
		Collection<IRule> ruleList = new ArrayList<IRule>(
				(Collection<? extends IRule>) Arrays.asList(collector
						.getResult()));
		for (IGrammar g : grammar.getDependents()) {
			collector.accept(g);
			ruleList.addAll((Collection<? extends IRule>) Arrays
					.asList(collector.getResult()));
		}
		return ruleList.toArray(new IRule[ruleList.size()]);
	}

	public static IModelElement getElementAt(IGrammar grammar, int line,
			int column) {
		String source = grammar.getSource();
		int offset = AntlrTextHelper.getOffsetAtLine(source, line) + column;
		AntlrModelElementLocator locator = new AntlrModelElementLocator(grammar);
		IModelElement element = locator.getElementAt(offset);
		return element;
	}

	public static List<IModelElement> getRuleInvocationStack(
			IModelElement element) {
		List<IModelElement> elements = new ArrayList<IModelElement>();
		Set<IModelElement> temp = new HashSet<IModelElement>();
		internalGetRuleInvocationStack(element, temp, elements);
		Collections.sort(elements, new Comparator<IModelElement>() {

			public int compare(IModelElement e1, IModelElement e2) {
				return e2.sourceStart() - e1.sourceEnd();
			}
		});
		IGrammar grammar = element.getAdapter(IGrammar.class);
		String source = grammar.getSource();
		System.out.println("-----begin stack: ");
		for (IModelElement e : elements) {
			int line = AntlrTextHelper.getLineAtOffset(source, e.sourceStart());
			int offset = AntlrTextHelper.getOffsetAtLine(source, line);
			int column = e.sourceStart() - offset + 1;
			System.out.println("\t" + e.getElementName() + " at " + line + ": "
					+ column);
		}
		System.out.println("-----end stack: ");
		return elements;
	}

	private static void internalGetRuleInvocationStack(IModelElement element,
			Set<IModelElement> processedElements,
			Collection<IModelElement> stack) {
		if (element == null)
			return;
		IGrammar grammar = element.getAdapter(IGrammar.class);
		if (grammar == null)
			return;
		IModelElementCriteria criteria = ModelElementCriteriaFactory
				.nameEquals(element.getElementName());
		AntlrModelElementCollectorVisitor visitor = new AntlrModelElementCollectorVisitor(
				criteria);
		visitor.setAcceptDuplicates(true);
		visitor.accept(grammar);
		IModelElement[] elements = visitor.getResult();
		for (IModelElement me : elements) {
			IRule enclosingRule = me.getAdapter(IRule.class);
			if (me != enclosingRule
					&& !processedElements.contains(enclosingRule)) {
				processedElements.add(enclosingRule);
				stack.add(me);
				internalGetRuleInvocationStack(enclosingRule,
						processedElements, stack);
			}
		}
	}

	public static boolean isRuleDeclaration(IModelElement element) {
		return element instanceof IRule;
	}

	public static boolean isRuleInvocation(IModelElement element) {
		return (element instanceof ICallExpression)
				&& element.getElementName() != null
				&& Character.isLowerCase(element.getElementName().charAt(0));
	}

	public static boolean isInRewriteAlternative(IModelElement element) {
		IBlock block = null;
		if (element instanceof IBlock) {
			block = (IBlock) element;
		}
		if (block != null && block.isRewritten()) {
			return true;
		}
		if (element.getParent() != null)
			return isInRewriteAlternative(element.getParent());
		return false;
	}

	public static IModelElement[] collectRules(IGrammar grammar, String ruleName) {
		IModelElementCriteria criteria = ModelElementCriteriaFactory
				.ruleOrToken();
		if (ruleName != null && ruleName.length() > 0) {
			criteria = ModelElementCriteriaFactory.and(criteria,
					ModelElementCriteriaFactory.nameStartsWith(ruleName));
		}
		AntlrModelElementCollectorVisitor collector = new AntlrModelElementCollectorVisitor(
				criteria);
		collector.accept(grammar);
		return collector.getResult();
	}

	public static IModelElement[] collectLocalReferences(IRule rule,
			int sourcePosition, String referenceName) {
		IModelElementCriteria typeCriteria = ModelElementCriteriaFactory.or(
				ModelElementCriteriaFactory.callOrVariable(),
				ModelElementCriteriaFactory.grammarScopeOrRuleScope());
		IModelElementCriteria excludedNames = ModelElementCriteriaFactory
				.not(ModelElementCriteriaFactory.charLiteralOrStringLiteral());
		IModelElementCriteria criteria = ModelElementCriteriaFactory.and(
				ModelElementCriteriaFactory.sourcePosition(sourcePosition - 1),
				ModelElementCriteriaFactory.and(typeCriteria, excludedNames));
		if (referenceName != null && referenceName.length() > 0) {
			IModelElementCriteria swCriteria = ModelElementCriteriaFactory
					.nameStartsWith(referenceName);
			criteria = ModelElementCriteriaFactory.and(criteria, swCriteria);
		}
		AntlrModelElementCollectorVisitor collector = new AntlrModelElementCollectorVisitor(
				criteria);
		collector.accept(rule);
		return collector.getResult();
	}

	public static IModelElement[] collectScopeAttributes(IScope scope) {
		return collectScopeAttributes(scope, null);
	}

	public static IModelElement[] collectScopeAttributes(IScope scope,
			String attributeName) {
		IModelElementCriteria criteria = ModelElementCriteriaFactory
				.grammarScopeAttributeOrRuleScopeAttribute();
		if (attributeName != null && attributeName.length() > 0) {
			criteria = ModelElementCriteriaFactory.and(
					ModelElementCriteriaFactory.nameStartsWith(attributeName),
					criteria);
		}
		AntlrModelElementCollectorVisitor collector = new AntlrModelElementCollectorVisitor(
				criteria);
		collector.accept(scope);
		return collector.getResult();
	}

	public static IModelElement[] collectCalls(IRule owner) {
		return collectCalls(owner, true);
	}

	public static IModelElement[] collectCalls(IRule owner,
			boolean acceptDuplicates) {
		AntlrModelElementCollectorVisitor collector = new AntlrModelElementCollectorVisitor(
				ModelElementCriteriaFactory.kind(ElementKind.CALL));
		collector.setAcceptDuplicates(true);
		collector.accept(owner);
		IModelElement[] calls = collector.getResult();
		return calls;
	}

	public static IModelElement[] collectTokens(IGrammar grammar) {
		AntlrModelElementCollectorVisitor collector = new AntlrModelElementCollectorVisitor(
				ModelElementCriteriaFactory.kind(ElementKind.TOKEN));
		collector.accept(grammar);
		IModelElement[] elements = collector.getResult();
		return elements;
	}

	public static IModelElement[] collectAllTokens(IGrammar grammar) {
		IModelElementCriteria criteria = ModelElementCriteriaFactory.or(
				ModelElementCriteriaFactory.or(ModelElementCriteriaFactory
						.kind(ElementKind.TOKEN), ModelElementCriteriaFactory
						.literalReference()), ModelElementCriteriaFactory
						.lexerRule());
		AntlrModelElementCollectorVisitor collector = new AntlrModelElementCollectorVisitor(
				criteria);
		collector.accept(grammar);
		IModelElement[] elements = collector.getResult();
		return elements;
	}

	public static IModelElement[] collectAlts(ICompositeStatement element) {
		AntlrModelElementCollectorVisitor collector = new AntlrModelElementCollectorVisitor(
				ModelElementCriteriaFactory.kind(ElementKind.ALTERNATIVE));
		collector.setAcceptDuplicates(true);
		collector.accept(element);
		IModelElement[] elements = collector.getResult();
		return elements;
	}

	public static IModelElement[] collectBlocksOrAlts(
			ICompositeStatement element) {
		IModelElementCriteria criteria = ModelElementCriteriaFactory.or(
				ModelElementCriteriaFactory.kind(ElementKind.ALTERNATIVE),
				ModelElementCriteriaFactory.kind(ElementKind.BLOCK));
		AntlrModelElementCollectorVisitor collector = new AntlrModelElementCollectorVisitor(
				criteria);
		collector.setAcceptDuplicates(true);
		collector.accept(element);
		IModelElement[] elements = collector.getResult();
		return elements;
	}

	public static IModelElement[] collectTreeStatements(
			ICompositeStatement element) {
		IModelElementCriteria criteria = ModelElementCriteriaFactory.or(
				ModelElementCriteriaFactory.kind(ElementKind.TREE_STATEMENT),
				ModelElementCriteriaFactory.kind(ElementKind.BLOCK));
		AntlrModelElementCollectorVisitor collector = new AntlrModelElementCollectorVisitor(
				criteria);
		collector.setAcceptDuplicates(true);
		collector.accept(element);
		IModelElement[] elements = collector.getResult();
		return elements;
	}

	public static IModelElement[] collectLexerRules(IGrammar grammar) {
		AntlrModelElementCollectorVisitor collector = new AntlrModelElementCollectorVisitor(
				ModelElementCriteriaFactory.lexerRule());
		collector.accept(grammar);
		IModelElement[] elements = collector.getResult();
		return elements;
	}

	public static IModelElement[] collectLiterals(IGrammar grammar) {
		AntlrModelElementCollectorVisitor collector = new AntlrModelElementCollectorVisitor(
				ModelElementCriteriaFactory.literalReference());
		collector.accept(grammar);
		List<IModelElement> literals = new ArrayList<IModelElement>(Arrays
				.asList(collector.getResult()));
		IGrammar[] dependents = grammar.getDependents();
		for (IGrammar depGrammar : dependents) {
			collector.accept(depGrammar);
			literals.addAll(Arrays.asList(collector.getResult()));
		}
		return literals.toArray(new IModelElement[literals.size()]);
	}

	public static IModelElement[] collectPredicates(IGrammar grammar) {
		AntlrModelElementCollectorVisitor collector = new AntlrModelElementCollectorVisitor(
				ModelElementCriteriaFactory.predicates());
		collector.accept(grammar);
		IModelElement[] elements = collector.getResult();
		return elements;
	}

	public static String[] collectRuleNamesWithPredicates(IGrammar grammar,
			boolean ignorePredicatesInRewriteAlt) {
		IRule[] rules = collectRulesWithPredicates(grammar,
				ignorePredicatesInRewriteAlt);
		String[] ruleNames = new String[rules.length];
		for (int i = 0; i < rules.length; i++) {
			ruleNames[i] = rules[i].getElementName();
		}
		return ruleNames;
	}

	public static IRule[] collectRulesWithPredicates(IGrammar grammar,
			boolean ignorePredicatesInRewriteAlt) {
		Collection<IRule> rules = new ArrayList<IRule>();
		IModelElement[] predicates = collectPredicates(grammar,
				ignorePredicatesInRewriteAlt);
		for (IModelElement p : predicates) {
			IStatement stt = (IStatement) p;
			IRule rule = stt.getEnclosingRule();
			if (!rules.contains(rule)) {
				rules.add(rule);
			}
		}
		return rules.toArray(new IRule[rules.size()]);
	}

	public static IModelElement[] collectPredicates(IGrammar grammar,
			boolean ignorePredicatesInRewriteAlt) {
		IModelElement[] elements = collectPredicates(grammar);
		if (elements.length > 0 && ignorePredicatesInRewriteAlt) {
			Collection<IModelElement> c = new ArrayList<IModelElement>(
					elements.length);
			for (IModelElement e : elements) {
				if (!isInRewriteAlternative(e)) {
					c.add(e);
				}
			}
			if (elements.length != c.size()) {
				elements = new IModelElement[c.size()];
				elements = c.toArray(elements);
			}
			c.clear();
			c = null;
		}
		return elements;
	}

	public static IModelElement[] collectPredicates(IRule rule) {
		AntlrModelElementCollectorVisitor collector = new AntlrModelElementCollectorVisitor(
				ModelElementCriteriaFactory.predicates());
		collector.accept(rule);
		IModelElement[] elements = collector.getResult();
		return elements;
	}

	public static IModelElement[] findIgnoredRules(IGrammar grammar) {
		AntlrModelElementCollectorVisitor collector = new AntlrModelElementCollectorVisitor(
				ModelElementCriteriaFactory.ignoredRule());
		collector.accept(grammar);
		IModelElement[] elements = collector.getResult();
		return elements;
	}
}
