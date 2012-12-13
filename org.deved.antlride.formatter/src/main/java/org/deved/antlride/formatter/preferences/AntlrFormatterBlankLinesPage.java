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
import org.eclipse.dltk.ui.formatter.FormatterModifyTabPage;
import org.eclipse.dltk.ui.formatter.IFormatterControlManager;
import org.eclipse.dltk.ui.formatter.IFormatterModifyDialog;
import org.eclipse.dltk.ui.util.SWTFactory;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;

public class AntlrFormatterBlankLinesPage extends FormatterModifyTabPage {

	public AntlrFormatterBlankLinesPage(IFormatterModifyDialog dialog) {
		super(dialog);
	}

	protected void createOptions(IFormatterControlManager manager,
			Composite parent) {
		manager = new AntlrFormatterControlManager(manager);

		Group group = SWTFactory.createGroup(parent,
				AntlrFormatterMessages.BlankLines_blankLinesInSourceFile, 2, 1,
				GridData.FILL_HORIZONTAL);	
		// Options
		manager.createNumber(group,
				AntlrFormatterPreferences.BlankLines.LINES_BEFORE_OPTIONS,
				AntlrFormatterMessages.BlankLines_beforeOptions);
		manager.createNumber(group,
				AntlrFormatterPreferences.BlankLines.LINES_AFTER_OPTIONS,
				AntlrFormatterMessages.BlankLines_afterOptions);
		// Tokens
		manager.createNumber(group,
				AntlrFormatterPreferences.BlankLines.LINES_BEFORE_TOKENS,
				AntlrFormatterMessages.BlankLines_beforeTokens);
		manager.createNumber(group,
				AntlrFormatterPreferences.BlankLines.LINES_AFTER_TOKENS,
				AntlrFormatterMessages.BlankLines_afterTokens);
		manager.createNumber(group,
				AntlrFormatterPreferences.BlankLines.LINES_AFTER_TOKENS,
				AntlrFormatterMessages.BlankLines_afterTokens);
		// Scopes
		manager.createNumber(group,
				AntlrFormatterPreferences.BlankLines.LINES_BEFORE_SCOPE,
				AntlrFormatterMessages.BlankLines_beforeScope);
		manager.createNumber(group,
				AntlrFormatterPreferences.BlankLines.LINES_AFTER_SCOPE,
				AntlrFormatterMessages.BlankLines_afterScope);
		// @Action
		manager.createNumber(group,
				AntlrFormatterPreferences.BlankLines.LINES_BEFORE_ACTION,
				AntlrFormatterMessages.BlankLines_beforeAction);
		manager.createNumber(group,
				AntlrFormatterPreferences.BlankLines.LINES_AFTER_ACTION,
				AntlrFormatterMessages.BlankLines_afterAction);
		//
		group = SWTFactory.createGroup(parent,
				AntlrFormatterMessages.BlankLines_inRules, 2, 1,
				GridData.FILL_HORIZONTAL);

		// Rule
		manager.createNumber(group,
				AntlrFormatterPreferences.BlankLines.LINES_BEFORE_FIRST_RULE,
				AntlrFormatterMessages.BlankLines_beforeFirstRule);

		manager.createNumber(group,
				AntlrFormatterPreferences.BlankLines.LINES_BEFORE_RULE,
				AntlrFormatterMessages.BlankLines_beforeRule);

		manager.createNumber(group,
				AntlrFormatterPreferences.BlankLines.LINES_AFTER_RULE,
				AntlrFormatterMessages.BlankLines_afterRule);

		manager.createNumber(group,
				AntlrFormatterPreferences.BlankLines.LINES_BEFORE_RULE_OPTIONS,
				AntlrFormatterMessages.BlankLines_beforeOptions);
		manager.createNumber(group,
				AntlrFormatterPreferences.BlankLines.LINES_AFTER_RULE_OPTIONS,
				AntlrFormatterMessages.BlankLines_afterOptions);
		manager.createNumber(group,
				AntlrFormatterPreferences.BlankLines.LINES_BEFORE_RULE_SCOPE,
				AntlrFormatterMessages.BlankLines_beforeScope);
		manager.createNumber(group,
				AntlrFormatterPreferences.BlankLines.LINES_AFTER_RULE_SCOPE,
				AntlrFormatterMessages.BlankLines_afterScope);
		
		manager.createNumber(group,
				AntlrFormatterPreferences.BlankLines.LINES_BEFORE_RULE_ACTION,
				AntlrFormatterMessages.BlankLines_beforeAction);
		manager.createNumber(group,
				AntlrFormatterPreferences.BlankLines.LINES_AFTER_RULE_ACTION,
				AntlrFormatterMessages.BlankLines_afterAction);
	}

	protected URL getPreviewContent() {
		return getClass().getResource("blank-lines-preview"); //$NON-NLS-1$
	}

}
