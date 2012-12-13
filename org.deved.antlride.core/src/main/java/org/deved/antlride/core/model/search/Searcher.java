/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.core.model.search;

import org.deved.antlride.core.model.IModelElement;

public class Searcher {

	/**
	 * Search for an {@link IModelElement element} in the given
	 * {@link IModelElement search scope} at the given source offset.
	 * 
	 * @param scope
	 *            . The search scope
	 * @param offset
	 *            . Is located at the source offset
	 * @return The element located at the given offset or null if no element is
	 *         at the location
	 */
	public static IModelElement search(IModelElement scope, int offset) {
		SearchVisitor visitor = new SearchVisitor(offset);

		visitor.accept(scope);

		return visitor.getResult();
	}
}
