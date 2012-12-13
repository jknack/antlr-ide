/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.core.model.ast.criteria;

import org.deved.antlride.core.model.IModelElement;
import org.deved.antlride.core.model.IRule;

class IgnoredRuleCriteria implements IModelElementCriteria {

	static final public IModelElementCriteria CRITERIA = new IgnoredRuleCriteria();
	
	public boolean accept(IModelElement element) {
		IRule rule = element.getAdapter(IRule.class);
		return rule == null? false: rule.isIgnored();
	}

}
