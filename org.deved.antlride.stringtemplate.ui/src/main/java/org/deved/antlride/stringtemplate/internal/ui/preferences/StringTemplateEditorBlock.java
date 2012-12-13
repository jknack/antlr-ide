/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.stringtemplate.internal.ui.preferences;

import java.util.ArrayList;

import org.eclipse.dltk.ui.CodeFormatterConstants;
import org.eclipse.dltk.ui.PreferenceConstants;
import org.eclipse.dltk.ui.preferences.AbstractConfigurationBlock;
import org.eclipse.dltk.ui.preferences.FormatterMessages;
import org.eclipse.dltk.ui.preferences.OverlayPreferenceStore;
import org.eclipse.dltk.ui.preferences.PreferencesMessages;
import org.eclipse.dltk.ui.preferences.OverlayPreferenceStore.OverlayKey;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public class StringTemplateEditorBlock extends AbstractConfigurationBlock {
	private boolean smartDisabled = false;
	boolean tabAlwaysIndent = false;

	public StringTemplateEditorBlock(PreferencePage mainPreferencePage,
			OverlayPreferenceStore store) {
		super(store, mainPreferencePage);
		getPreferenceStore().addKeys(createOverlayStoreKeys());
	}

	public StringTemplateEditorBlock(PreferencePage mainPreferencePage,
			OverlayPreferenceStore store, boolean disableSmart) {
		super(store, mainPreferencePage);
		getPreferenceStore().addKeys(createOverlayStoreKeys());
		this.smartDisabled = disableSmart;
	}

	public StringTemplateEditorBlock(PreferencePage mainPreferencePage,
			OverlayPreferenceStore store, boolean disableSmart,
			boolean tabAlwaysIndent) {
		super(store, mainPreferencePage);
		getPreferenceStore().addKeys(createOverlayStoreKeys());
		this.smartDisabled = disableSmart;
		this.tabAlwaysIndent = tabAlwaysIndent;
	}

	private OverlayPreferenceStore.OverlayKey[] createOverlayStoreKeys() {
		ArrayList<OverlayKey> overlayKeys = new ArrayList<OverlayKey>();

		overlayKeys.add(new OverlayPreferenceStore.OverlayKey(
				OverlayPreferenceStore.BOOLEAN,
				PreferenceConstants.EDITOR_SMART_HOME_END));
		overlayKeys.add(new OverlayPreferenceStore.OverlayKey(
				OverlayPreferenceStore.BOOLEAN,
				PreferenceConstants.EDITOR_SUB_WORD_NAVIGATION));
		overlayKeys.add(new OverlayPreferenceStore.OverlayKey(
				OverlayPreferenceStore.BOOLEAN,
				PreferenceConstants.EDITOR_SMART_INDENT));
		// Automatically close
		overlayKeys.add(new OverlayPreferenceStore.OverlayKey(
				OverlayPreferenceStore.BOOLEAN,
				PreferenceConstants.EDITOR_CLOSE_STRINGS));
		overlayKeys.add(new OverlayPreferenceStore.OverlayKey(
				OverlayPreferenceStore.BOOLEAN,
				PreferenceConstants.EDITOR_CLOSE_BRACKETS));
		overlayKeys.add(new OverlayPreferenceStore.OverlayKey(
				OverlayPreferenceStore.BOOLEAN,
				PreferenceConstants.EDITOR_CLOSE_BRACES));
		// indentation
		overlayKeys.add(new OverlayPreferenceStore.OverlayKey(
				OverlayPreferenceStore.STRING,
				CodeFormatterConstants.FORMATTER_TAB_CHAR));
		overlayKeys.add(new OverlayPreferenceStore.OverlayKey(
				OverlayPreferenceStore.INT,
				CodeFormatterConstants.FORMATTER_TAB_SIZE));
		overlayKeys.add(new OverlayPreferenceStore.OverlayKey(
				OverlayPreferenceStore.INT,
				CodeFormatterConstants.FORMATTER_INDENTATION_SIZE));
		overlayKeys.add(new OverlayPreferenceStore.OverlayKey(
				OverlayPreferenceStore.BOOLEAN,
				PreferenceConstants.EDITOR_TAB_ALWAYS_INDENT));

		OverlayPreferenceStore.OverlayKey[] keys = new OverlayPreferenceStore.OverlayKey[overlayKeys
				.size()];
		overlayKeys.toArray(keys);
		return keys;
	}

	/**
	 * Creates page for appearance preferences.
	 * 
	 * @param parent
	 *            the parent composite
	 * @return the control for the preference page
	 */
	public Control createControl(Composite parent) {
		initializeDialogUnits(parent);

		Composite control = new Composite(parent, SWT.NONE);
		control.setLayout(new GridLayout());
		Composite composite;
		if (!smartDisabled) {

			composite = createSubsection(control, null,
					PreferencesMessages.EditorPreferencePage_title0);
			createSmartCaretGroup(composite);
		}
		composite = createSubsection(
				control,
				null,
				StringTemplatePreferenceMessages.SmartTypingConfigurationBlock_typing);
		createClosePairGroup(composite);
		createIdentationTabsGroup(control);

		return control;
	}

	private void createIdentationTabsGroup(Composite composite) {
		Composite generalGroup = createSubsection(composite, null,
				FormatterMessages.IndentationTabPage_preview_header);

		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		generalGroup.setLayout(layout);

		final String[] tabPolicyValues = new String[] {
				CodeFormatterConstants.SPACE, CodeFormatterConstants.TAB,
				CodeFormatterConstants.MIXED };
		final String[] tabPolicyLabels = new String[] {
				FormatterMessages.IndentationTabPage_general_group_option_tab_policy_SPACE,
				FormatterMessages.IndentationTabPage_general_group_option_tab_policy_TAB,
				FormatterMessages.IndentationTabPage_general_group_option_tab_policy_MIXED };

		addComboBox(
				generalGroup,
				FormatterMessages.IndentationTabPage_general_group_option_tab_policy,
				CodeFormatterConstants.FORMATTER_TAB_CHAR, tabPolicyLabels,
				tabPolicyValues);

		addLabelledTextField(
				generalGroup,
				FormatterMessages.IndentationTabPage_general_group_option_indent_size,
				CodeFormatterConstants.FORMATTER_INDENTATION_SIZE, 2, 1, true);

		addLabelledTextField(
				generalGroup,
				FormatterMessages.IndentationTabPage_general_group_option_tab_size,
				CodeFormatterConstants.FORMATTER_TAB_SIZE, 2, 1, true);

		if (tabAlwaysIndent) {
			addCheckBox(generalGroup,
					PreferencesMessages.EditorPreferencePage_tabAlwaysIndent,
					PreferenceConstants.EDITOR_TAB_ALWAYS_INDENT, 2);
		}
	}

	private Control createSmartCaretGroup(Composite composite) {
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		composite.setLayout(layout);

		String label;

		label = PreferencesMessages.EditorPreferencePage_smartHomeEnd;
		addCheckBox(composite, label,
				PreferenceConstants.EDITOR_SMART_HOME_END, 0);

		label = PreferencesMessages.EditorPreferencePage_subWordNavigation;
		addCheckBox(composite, label,
				PreferenceConstants.EDITOR_SUB_WORD_NAVIGATION, 0);

		label = PreferencesMessages.EditorPreferencePage_smartIndent;
		addCheckBox(composite, label, PreferenceConstants.EDITOR_SMART_INDENT,
				0);

		return composite;
	}

	private Control createClosePairGroup(Composite composite) {
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		composite.setLayout(layout);

		String label;

		label = StringTemplatePreferenceMessages.SmartTypingConfigurationBlock_closeStrings;
		addCheckBox(composite, label, PreferenceConstants.EDITOR_CLOSE_STRINGS,
				0);

		label = StringTemplatePreferenceMessages.SmartTypingConfigurationBlock_closeBrackets;
		addCheckBox(composite, label,
				PreferenceConstants.EDITOR_CLOSE_BRACKETS, 0);

		label = StringTemplatePreferenceMessages.SmartTypingConfigurationBlock_closeBraces;
		addCheckBox(composite, label, PreferenceConstants.EDITOR_CLOSE_BRACES,
				0);

		return composite;
	}

	public void initialize() {

		super.initialize();

	}

	public void performDefaults() {
		super.performDefaults();

	}
}
