/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.core.model.ast.criteria;

import org.deved.antlride.core.model.ElementKind;

public class ModelElementCriteriaFactory {

	private static IModelElementCriteria RULE_OR_TOKEN = or(
			kind(ElementKind.RULE), kind(ElementKind.TOKEN));

	private static IModelElementCriteria PREDICATES = or(
			kind(ElementKind.SEMPRED), kind(ElementKind.SYN_PRED));

	private static IModelElementCriteria LEXER_RULE = and(
			kind(ElementKind.RULE), LexerRuleCriteria.CRITERIA);

	private static IModelElementCriteria LITERAL_REFERENCE = and(
			kind(ElementKind.CALL), LiteralReferenceCriteria.CRITERIA);

	private static IModelElementCriteria IGNORED_RULE = and(LEXER_RULE,
			IgnoredRuleCriteria.CRITERIA);

	private static IModelElementCriteria SLITERAL = new ModelElementPatternCriteria(
			"\".*\"");

	private static IModelElementCriteria CLITERAL = new ModelElementPatternCriteria(
			"'.*'");

	private static IModelElementCriteria CLITERAL_OR_SLITERAL = or(SLITERAL,
			CLITERAL);

	/**
	 * Kind criteria's
	 */

	private static IModelElementCriteria CALL_OR_VARIABLE = or(
			kind(ElementKind.CALL), kind(ElementKind.VARIABLE));

	private static IModelElementCriteria GSCOPE_OR_RSCOPE = or(
			kind(ElementKind.GRAMMAR_SCOPE), kind(ElementKind.RULE_SCOPE));

	private static IModelElementCriteria GSCOPEATTRIBUTE_OR_RSCOPEATTRIBUTE = or(
			kind(ElementKind.GRAMMAR_SCOPE_ATTRIBUTE),
			kind(ElementKind.RULE_SCOPE_ATTRIBUTE));

	public static IModelElementCriteria and(IModelElementCriteria left,
			IModelElementCriteria right) {
		return new ModelElementAndCriteria(left, right);
	}

	public static IModelElementCriteria or(IModelElementCriteria left,
			IModelElementCriteria right) {
		return new ModelElementOrCriteria(left, right);
	}

	public static IModelElementCriteria not(IModelElementCriteria criteria) {
		return new ModelElementNotCriteria(criteria);
	}

	public static IModelElementCriteria kind(ElementKind kind) {
		return new ModelElementKindCriteria(kind);
	}

	public static IModelElementCriteria nameEquals(String elementName) {
		return new ModelElementNameCriteria(
				ModelElementNameCriteria.EQ_OPERATOR, elementName);
	}

	public static IModelElementCriteria nameStartsWith(String elementName) {
		return new ModelElementNameCriteria(
				ModelElementNameCriteria.SW_OPERATOR, elementName);
	}

	public static IModelElementCriteria pattern(String pattern) {
		return new ModelElementPatternCriteria(pattern);
	}

	public static IModelElementCriteria sourcePosition(int position) {
		return new ModelElementSourcePositionCriteria(position);
	}

	public static IModelElementCriteria call(String elementName) {
		IModelElementCriteria criteria = kind(ElementKind.VARIABLE);
		if (elementName != null && elementName.length() > 0) {
			criteria = ModelElementCriteriaFactory.and(criteria,
					ModelElementCriteriaFactory.nameEquals(elementName));
		}
		return criteria;
	}

	public static IModelElementCriteria ruleOrToken() {
		return RULE_OR_TOKEN;
	}

	public static IModelElementCriteria predicates() {
		return PREDICATES;
	}

	public static IModelElementCriteria charLiteralOrStringLiteral() {
		return CLITERAL_OR_SLITERAL;
	}

	public static IModelElementCriteria lexerRule() {
		return LEXER_RULE;
	}

	public static IModelElementCriteria literalReference() {
		return LITERAL_REFERENCE;
	}

	public static IModelElementCriteria ignoredRule() {
		return IGNORED_RULE;
	}

	public static IModelElementCriteria grammarScopeOrRuleScope() {
		return GSCOPE_OR_RSCOPE;
	}

	public static IModelElementCriteria grammarScopeAttributeOrRuleScopeAttribute() {
		return GSCOPEATTRIBUTE_OR_RSCOPEATTRIBUTE;
	}

	public static IModelElementCriteria callOrVariable() {
		return CALL_OR_VARIABLE;
	}

	public static IModelElementCriteria callOrVariable(String elementName) {
		IModelElementCriteria criteria = CALL_OR_VARIABLE;
		if (elementName != null && elementName.length() > 0) {
			criteria = ModelElementCriteriaFactory.and(criteria,
					ModelElementCriteriaFactory.nameEquals(elementName));
		}
		return criteria;
	}
}
