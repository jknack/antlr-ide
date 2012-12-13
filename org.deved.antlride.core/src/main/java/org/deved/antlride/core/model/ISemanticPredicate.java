/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.core.model;

/**
 * <p>
 * Semantic predicates are boolean expressions you can use to specify the
 * semantic validity of an alternative. The term predicate simply means
 * conditional, and the term semantic implies you are talking about arbitrary
 * boolean expressions rather than a syntactic condition.
 * </p>
 * <p>
 * Semantic predicates are available to any ANTLR grammar and have three
 * variations:
 * </p>
 * <li>Disambiguating semantic predicates, which disambiguate syntactically
 * identical statements. They looks like {condition}?
 * 
 * <pre>
 * stat: 'if' ...
 *     | {allowAssert}? 'assert' expr
 *     ;
 * </pre> <li>Gated semantic predicates, which dynamically turn on and off portions of
 * a grammar. They looks like {condition}?=>
 * 
 * <pre>
 * stat: 'if' ...
 *     | {allowAssert}?<strong>=></strong> 'assert' expr
 *     ;
 * </pre> <li>Validating semantic predicates, which throw a recognition exception if
 * the predicate fails. Although most semantic analysis occurs in a separate
 * phase for complicated language applications, sometimes it is convenient to
 * place semantic checks within a grammar that throw an exception upon failure
 * like syntax errors do. For example, in the following grammar, the recognizer
 * throws a FailedPredicateException if the input program references a variable
 * without a prior definition where the highlighted region is the code generated
 * for the validating semantic predicate:
 * 
 * <pre>
 * expr: INT
 *     | ID {isDefined($ID.text)}?
 *     ;
 * </pre>
 * 
 * @author Edgar Espina
 * 
 */
public interface ISemanticPredicate extends IExpression {

	public enum PredicateType {
		SEMPRED(""), GATED_SEMPRED("=>");

		private String desc;

		private PredicateType(String desc) {
			this.desc = desc;
		}

		public String description() {
			return desc;
		}
	}

	PredicateType getPredicateType();

	IStatement getPredicate();

	ISourceElement getCondition();
	
	boolean hasPredicate();
	
	boolean isValidating();
}
