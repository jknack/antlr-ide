/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.internal.ui.wizards;

import org.deved.antlride.core.model.GrammarType;

public class AntlrLexerGrammarPage extends AntlrDefaultGrammarPage {

	public AntlrLexerGrammarPage() {
	}
	
	@Override
	protected String getGrammarTypeName() {
		return "lexer ";
	}

	@Override
	protected String getGrammarType() {
		return GrammarType.LEXER.name();
	}
	
	@Override
	protected boolean hasDependentSection() {
		return true;
	}
	
	@Override
	protected String getRuleTemplate() {
		return "RULE";
	}

	@Override
	protected boolean hasOutputSection() {
		return false;
	}

	@Override
	protected String getPageDescription() {
		return "Created a new lexer grammar";
	}

	@Override
	protected String getPageTitle() {
		return "Lexer Grammar";
	}

}
