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
 * Syntactic predicates, as we’ve seen, are parenthesized grammar fragments
 * followed by the => operator. If a syntactic predicate matches, the associated
 * alternative is valid.
 * </p>
 * <p>
 * Example
 * </p>
 * 
 * <pre>
 * grammar b;
 * backtrack
 *         (cast ';' )=> cast ';'
 *     :
 *         (e ';' )=>    e ';'
 *     |
 *                       e '.'
 *     |
 *     ;
 *         '(' ID ')' ;
 * cast:
 *         '(' e ')'
 * e   :
 *     |   ID
 *         ;
 *         'a'..'z' + ;
 * ID  :
 * 
 * </pre>
 * 
 * @author Edgar Espina
 * 
 */
public interface ISyntacticPredicate extends IExpression {
	IStatement getCondition();

	IStatement getPredicate();
}
