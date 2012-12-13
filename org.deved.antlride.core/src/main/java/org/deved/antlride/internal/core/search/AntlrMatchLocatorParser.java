/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.internal.core.search;

import org.deved.antlride.core.model.dltk.ast.DASTCallExpression;
import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.declarations.MethodDeclaration;
import org.eclipse.dltk.ast.expressions.CallExpression;
import org.eclipse.dltk.core.search.SearchPattern;
import org.eclipse.dltk.core.search.matching.MatchLocator;
import org.eclipse.dltk.core.search.matching.MatchLocatorParser;
import org.eclipse.dltk.core.search.matching.PatternLocator;
import org.eclipse.dltk.internal.core.search.matching.MatchingNodeSet;
import org.eclipse.dltk.internal.core.search.matching.MethodPattern;

@SuppressWarnings("restriction")
public class AntlrMatchLocatorParser extends MatchLocatorParser {

	private class AntlrPatternLocator extends PatternLocator {

		private String regex;

		public AntlrPatternLocator(SearchPattern pattern) {
			super(pattern);
			try {
				MethodPattern mp = ((MethodPattern) pattern);
				regex = new String(mp.selector);
			} catch (Exception e) {
				regex = "";
				e.printStackTrace();
			}
		}

		@Override
		public int match(CallExpression node, MatchingNodeSet nodeSet) {
			if (nodeSet != null && node.getName().equals(regex)) {
				return nodeSet.addMatch(node, ACCURATE_MATCH);
			}
			return super.match(node, nodeSet);
		}
	}

	private PatternLocator patternLocator;

	public AntlrMatchLocatorParser(MatchLocator locator) {
		super(locator);
		patternLocator = new AntlrPatternLocator(locator.pattern);
	}

	@Override
	protected void processStatement(ASTNode node, PatternLocator locator) {
		if (node instanceof DASTCallExpression) {
			DASTCallExpression expression = (DASTCallExpression) node;
			getPatternLocator().match(expression, getNodeSet());
		}
	}

	@Override
	protected PatternLocator getPatternLocator() {
		return patternLocator;
	}

	@Override
	public MethodDeclaration processMethod(MethodDeclaration node) {
		getPatternLocator().match(node, getNodeSet());
		return super.processMethod(node);
	}
}
