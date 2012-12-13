/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.internal.ui.wizards;

public class AntlrParserGrammarWizard extends AntlrGrammarWizard {

	@Override
	protected AntlrGrammarPage createGrammarPage() {
		return new AntlrParserGrammarPage();
	}
	
	@Override
	protected String getWizardTitle() {
		return "New Parser Grammar";
	}

}
