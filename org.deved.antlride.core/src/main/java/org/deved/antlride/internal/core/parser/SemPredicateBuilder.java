/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.internal.core.parser;

import org.antlr.runtime.tree.CommonTree;
import org.deved.antlride.core.model.ISemanticPredicate.PredicateType;

public interface SemPredicateBuilder extends StatementBuilder {
	
	SemPredicateBuilder condition(CommonTree operatorNode);
	
	SemPredicateBuilder type(PredicateType predicateType);
	
	SemPredicateBuilder predicate(StatementBuilder body);
}
