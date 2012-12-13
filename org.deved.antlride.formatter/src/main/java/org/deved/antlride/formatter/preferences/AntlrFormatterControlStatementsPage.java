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

public class AntlrFormatterControlStatementsPage extends FormatterModifyTabPage {

	private static final URL PREVIEW = AntlrFormatterControlStatementsPage.class
			.getResource("controlstts-preview"); //$NON-NLS-1$;

	/**
	 * @param dialog
	 */
	public AntlrFormatterControlStatementsPage(IFormatterModifyDialog dialog) {
		super(dialog);
	}

	protected void createOptions(IFormatterControlManager manager,
			Composite parent) {

		manager = new AntlrFormatterControlManager(manager);

		// New Lines
		Group group = SWTFactory.createGroup(parent,
				AntlrFormatterMessages.ControlStatements_General, 1, 1,
				GridData.FILL_HORIZONTAL);

		manager
				.createCheckbox(
						group,
						AntlrFormatterPreferences.ControlStatements.NL_AFTER_RULE_MODIFIER,
						AntlrFormatterMessages.ControlStatements_afterRuleModifier);

		manager.createCheckbox(group,
				AntlrFormatterPreferences.ControlStatements.NL_BEFORE_RULE_ARGS,
				AntlrFormatterMessages.ControlStatements_beforeRuleArgs);

		manager
				.createCheckbox(
						group,
						AntlrFormatterPreferences.ControlStatements.NL_BEFORE_RULE_RETURNS,
						AntlrFormatterMessages.ControlStatements_beforeRuleReturns);

		manager
				.createCheckbox(
						group,
						AntlrFormatterPreferences.ControlStatements.NL_BEFORE_RULE_THROWS,
						AntlrFormatterMessages.ControlStatements_beforeRuleThrows);

		manager.createCheckbox(group,
				AntlrFormatterPreferences.ControlStatements.NL_BEFORE_RULE_COLON,
				AntlrFormatterMessages.ControlStatements_beforeRuleColon);

		manager
				.createCheckbox(
						group,
						AntlrFormatterPreferences.ControlStatements.NL_AFTER_REWRITE_OPERATOR,
						AntlrFormatterMessages.ControlStatements_afterRewriteOperator);

		manager.createCheckbox(group,
				AntlrFormatterPreferences.ControlStatements.NL_BEFORE_RULE_END,
				AntlrFormatterMessages.ControlStatements_beforeRuleEnd);

		manager
				.createCheckbox(
						group,
						AntlrFormatterPreferences.ControlStatements.EMPTY_RULE_ON_ONE_LINE,
						AntlrFormatterMessages.ControlStatements_ruleEmpty);

	}

	protected URL getPreviewContent() {
		return PREVIEW;
	}

}
