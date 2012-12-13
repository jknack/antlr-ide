/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.formatter.preferences;

import java.net.URL;

import org.deved.antlride.core.formatter.AntlrFormatterPreferences;
import org.deved.antlride.core.formatter.AntlrFormatterOptions;
import org.eclipse.dltk.ui.formatter.FormatterModifyTabPage;
import org.eclipse.dltk.ui.formatter.IFormatterControlManager;
import org.eclipse.dltk.ui.formatter.IFormatterModifyDialog;
import org.eclipse.dltk.ui.util.SWTFactory;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;

public class AntlrFormatterBracesPage extends FormatterModifyTabPage {

	private static final URL PREVIEW = AntlrFormatterBracesPage.class
			.getResource("braces-preview"); //$NON-NLS-1$;

	/**
	 * @param dialog
	 */
	public AntlrFormatterBracesPage(IFormatterModifyDialog dialog) {
		super(dialog);
	}

	protected void createOptions(IFormatterControlManager manager,
			Composite parent) {

		manager = new AntlrFormatterControlManager(manager);

		// Brace Group
		Group bracesInGrammarGroup = SWTFactory.createGroup(parent,
				AntlrFormatterMessages.BracesPositionsInGrammar, 2, 1,
				GridData.FILL_HORIZONTAL);
		String[] bracesStyles = AntlrFormatterOptions.BracesStyle
				.toStringArray();
		
		manager.createCombo(bracesInGrammarGroup,
				AntlrFormatterPreferences.Braces.OPTIONS,
				AntlrFormatterMessages.Braces_Options, bracesStyles,
				new String[] { AntlrFormatterMessages.Braces_sameLine,
						AntlrFormatterMessages.Braces_nextLine });
		
		manager.createCombo(bracesInGrammarGroup,
				AntlrFormatterPreferences.Braces.TOKENS,
				AntlrFormatterMessages.Braces_Tokens, bracesStyles,
				new String[] { AntlrFormatterMessages.Braces_sameLine,
						AntlrFormatterMessages.Braces_nextLine });
		
		manager.createCombo(bracesInGrammarGroup,
				AntlrFormatterPreferences.Braces.SCOPES,
				AntlrFormatterMessages.Braces_Scopes, bracesStyles,
				new String[] { AntlrFormatterMessages.Braces_sameLine,
						AntlrFormatterMessages.Braces_nextLine });

		manager.createCombo(bracesInGrammarGroup,
				AntlrFormatterPreferences.Braces.ACTIONS,
				AntlrFormatterMessages.Braces_Actions, bracesStyles,
				new String[] { AntlrFormatterMessages.Braces_sameLine,
						AntlrFormatterMessages.Braces_nextLine });
		
		Group bracesInRuleGroup = SWTFactory.createGroup(parent,
				AntlrFormatterMessages.BracesPositionsInRule, 2, 1,
				GridData.FILL_HORIZONTAL);
		
		manager.createCombo(bracesInRuleGroup,
				AntlrFormatterPreferences.Braces.RULE_OPTIONS,
				AntlrFormatterMessages.Braces_Options, bracesStyles,
				new String[] { AntlrFormatterMessages.Braces_sameLine,
						AntlrFormatterMessages.Braces_nextLine });		
		
		manager.createCombo(bracesInRuleGroup,
				AntlrFormatterPreferences.Braces.RULE_SCOPES,
				AntlrFormatterMessages.Braces_Scopes, bracesStyles,
				new String[] { AntlrFormatterMessages.Braces_sameLine,
						AntlrFormatterMessages.Braces_nextLine });

		manager.createCombo(bracesInRuleGroup,
				AntlrFormatterPreferences.Braces.RULE_ACTIONS,
				AntlrFormatterMessages.Braces_Actions, bracesStyles,
				new String[] { AntlrFormatterMessages.Braces_sameLine,
						AntlrFormatterMessages.Braces_nextLine });
	}

	protected URL getPreviewContent() {
		return PREVIEW;
	}

}
