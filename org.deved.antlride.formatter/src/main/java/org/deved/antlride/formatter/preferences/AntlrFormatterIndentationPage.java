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
import org.deved.antlride.core.formatter.AntlrFormatterOptions.IndentStyle;
import org.eclipse.dltk.ui.formatter.FormatterModifyTabPage;
import org.eclipse.dltk.ui.formatter.IFormatterControlManager;
import org.eclipse.dltk.ui.formatter.IFormatterModifyDialog;
import org.eclipse.dltk.ui.util.SWTFactory;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;

public class AntlrFormatterIndentationPage extends FormatterModifyTabPage {

	private class TabPolicyListener extends SelectionAdapter implements
			IFormatterControlManager.IInitializeListener {

		private final IFormatterControlManager manager;

		public TabPolicyListener(IFormatterControlManager manager) {
			this.manager = manager;
		}

		public void widgetSelected(SelectionEvent e) {
			Combo tabPolicy = (Combo) e.widget;
			int index = tabPolicy.getSelectionIndex();
			if (index >= 0) {
				final boolean tabMode = IndentStyle.TAB.name().equals(
						TAB_POLICY_ITEMS[index]);
				manager.enableControl(indentSize, !tabMode);
			}
		}

		public void initialize() {
			final boolean tabMode = IndentStyle.TAB
					.name()
					.equals(
							manager
									.getString(AntlrFormatterPreferences.Indent.TAB_CHAR));
			manager.enableControl(indentSize, !tabMode);
		}

	}

	private Text indentSize;

	private Text tabSize;

	private TabPolicyListener tabPolicyListener;

	private static final String[] TAB_POLICY_ITEMS = IndentStyle
			.toStringArray();

	private static final URL PREVIEW = AntlrFormatterIndentationPage.class
			.getResource("indentation-preview"); //$NON-NLS-1$;

	/**
	 * @param dialog
	 */
	public AntlrFormatterIndentationPage(IFormatterModifyDialog dialog) {
		super(dialog);
	}

	protected void createOptions(IFormatterControlManager manager,
			Composite parent) {

		manager = new AntlrFormatterControlManager(manager);

		Group tabPolicyGroup = SWTFactory.createGroup(parent,
				AntlrFormatterMessages.Indentation_generalSettings, 2, 1,
				GridData.FILL_HORIZONTAL);
		final Combo tabPolicy = manager.createCombo(tabPolicyGroup,
				AntlrFormatterPreferences.Indent.TAB_CHAR,
				AntlrFormatterMessages.Indentation_tab_policy,
				TAB_POLICY_ITEMS, new String[] {
						AntlrFormatterMessages.Indentation_tab_policy_SPACE,
						AntlrFormatterMessages.Indentation_tab_policy_TAB });

		tabPolicyListener = new TabPolicyListener(manager);
		tabPolicy.addSelectionListener(tabPolicyListener);
		manager.addInitializeListener(tabPolicyListener);
		indentSize = manager.createNumber(tabPolicyGroup,
				AntlrFormatterPreferences.Indent.INDENTATION_SIZE,
				AntlrFormatterMessages.Indentation_indent_size);
		tabSize = manager.createNumber(tabPolicyGroup,
				AntlrFormatterPreferences.Indent.TAB_SIZE,
				AntlrFormatterMessages.Indentation_tab_size);

		tabSize.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				int index = tabPolicy.getSelectionIndex();
				if (index >= 0) {
					final boolean tabMode = IndentStyle.TAB.name().equals(
							TAB_POLICY_ITEMS[index]);
					if (tabMode) {
						indentSize.setText(tabSize.getText());
					}
				}
			}
		});
		// Section Group
		Group indentSectionGroup = SWTFactory.createGroup(parent,
				AntlrFormatterMessages.Indentation_indentSections, 1, 1,
				GridData.FILL_HORIZONTAL);

		manager.createCheckbox(indentSectionGroup,
				AntlrFormatterPreferences.Indent.OPTIONS,
				AntlrFormatterMessages.Indentation_options);

		manager.createCheckbox(indentSectionGroup,
				AntlrFormatterPreferences.Indent.TOKENS,
				AntlrFormatterMessages.Indentation_tokens);

		// Rule Section
		Group indentRuleGroup = SWTFactory.createGroup(parent,
				AntlrFormatterMessages.Indentation_indentRules, 1, 1,
				GridData.FILL_HORIZONTAL);
		manager.createCheckbox(indentRuleGroup,
				AntlrFormatterPreferences.Indent.RULE_OPTIONS,
				AntlrFormatterMessages.Indentation_ruleOptions);

		manager.createCheckbox(indentRuleGroup,
				AntlrFormatterPreferences.Indent.RULE,
				AntlrFormatterMessages.Indentation_ruleBody);

		// Block Section
		Group indentBlockGroup = SWTFactory.createGroup(parent,
				AntlrFormatterMessages.Indentation_indentBlocks, 1, 1,
				GridData.FILL_HORIZONTAL);

		manager.createCheckbox(indentBlockGroup,
				AntlrFormatterPreferences.Indent.BLOCK_OPTIONS,
				AntlrFormatterMessages.Indentation_blockOptions);

		manager.createCheckbox(indentBlockGroup,
				AntlrFormatterPreferences.Indent.BLOCKS,
				AntlrFormatterMessages.Indentation_blocks);

		manager.createCheckbox(indentBlockGroup,
				AntlrFormatterPreferences.Indent.REWRITE_OPERATOR,
				AntlrFormatterMessages.Indentation_rewriteOperator);

		// Align tokens in columns
		Group alignGroup = SWTFactory.createGroup(parent,
				AntlrFormatterMessages.Indentation_alignSections, 1, 1,
				GridData.FILL_HORIZONTAL);

		manager.createCheckbox(alignGroup,
				AntlrFormatterPreferences.Indent.ALIGN_OPTIONS_IN_COLUMNS,
				AntlrFormatterMessages.Indentation_alignOptionsInColumns);

		manager.createCheckbox(alignGroup,
				AntlrFormatterPreferences.Indent.ALIGN_TOKENS_IN_COLUMNS,
				AntlrFormatterMessages.Indentation_alignTokensInColumns);
	}

	protected URL getPreviewContent() {
		return PREVIEW;
	}
}
