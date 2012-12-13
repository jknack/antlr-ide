/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.internal.ui.text.folding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.deved.antlride.ui.AntlrPreferenceConstants;
import org.eclipse.dltk.ui.preferences.OverlayPreferenceStore;
import org.eclipse.dltk.ui.preferences.OverlayPreferenceStore.OverlayKey;
import org.eclipse.dltk.ui.text.folding.IFoldingPreferenceBlock;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;

/**
 * ANTLR 3 default folding preferences.
 * 
 */
public class AntlrEditorFoldingPreferenceBlock implements
		IFoldingPreferenceBlock {

	private OverlayPreferenceStore fOverlayStore;
	private OverlayKey[] fKeys;
	private Map<Button, String> fCheckBoxes = new HashMap<Button, String>();
	private SelectionListener fCheckBoxListener = new SelectionListener() {
		public void widgetDefaultSelected(SelectionEvent e) {
		}

		public void widgetSelected(SelectionEvent e) {
			Button button = (Button) e.widget;
			fOverlayStore.setValue(fCheckBoxes.get(button), button
					.getSelection());
		}
	};

	public AntlrEditorFoldingPreferenceBlock(OverlayPreferenceStore store) {
		fOverlayStore = store;
		fKeys = createKeys();
		fOverlayStore.addKeys(fKeys);
	}

	private OverlayKey[] createKeys() {
		List<OverlayKey> overlayKeys = new ArrayList<OverlayKey>();

		overlayKeys.add(new OverlayPreferenceStore.OverlayKey(
				OverlayPreferenceStore.BOOLEAN,
				AntlrPreferenceConstants.EDITOR_RULE_FOLDING_ENABLED));
		overlayKeys.add(new OverlayPreferenceStore.OverlayKey(
				OverlayPreferenceStore.BOOLEAN,
				AntlrPreferenceConstants.EDITOR_OPTIONS_FOLDING_ENABLED));
		overlayKeys
				.add(new OverlayPreferenceStore.OverlayKey(
						OverlayPreferenceStore.BOOLEAN,
						AntlrPreferenceConstants.EDITOR_GRAMMAR_ACTION_FOLDING_ENABLED));
		overlayKeys
				.add(new OverlayPreferenceStore.OverlayKey(
						OverlayPreferenceStore.BOOLEAN,
						AntlrPreferenceConstants.EDITOR_TOKENS_SPECIFICATION_FOLDING_ENABLED));
		overlayKeys.add(new OverlayPreferenceStore.OverlayKey(
				OverlayPreferenceStore.BOOLEAN,
				AntlrPreferenceConstants.EDITOR_SCOPES_FOLDING_ENABLED));

		OverlayPreferenceStore.OverlayKey[] keys = new OverlayPreferenceStore.OverlayKey[overlayKeys
				.size()];
		overlayKeys.toArray(keys);
		return keys;
	}

	public Control createControl(Composite composite) {
		fOverlayStore.load();
		fOverlayStore.start();

		Composite inner = new Composite(composite, SWT.NONE);
		GridLayout layout = new GridLayout(1, true);
		layout.verticalSpacing = 3;
		layout.marginWidth = 0;
		inner.setLayout(layout);

		Label label = new Label(inner, SWT.LEFT);
		label.setText(AntlrFoldingMessages.FoldingPreferenceBlock_title);

		addCheckBox(inner, AntlrFoldingMessages.FoldingPreferenceBlock_options,
				AntlrPreferenceConstants.EDITOR_OPTIONS_FOLDING_ENABLED, 0);
		addCheckBox(
				inner,
				AntlrFoldingMessages.FoldingPreferenceBlock_tokensSpecification,
				AntlrPreferenceConstants.EDITOR_TOKENS_SPECIFICATION_FOLDING_ENABLED,
				0);
		addCheckBox(inner, AntlrFoldingMessages.FoldingPreferenceBlock_scopes,
				AntlrPreferenceConstants.EDITOR_SCOPES_FOLDING_ENABLED, 0);
		addCheckBox(inner,
				AntlrFoldingMessages.FoldingPreferenceBlock_grammarActions,
				AntlrPreferenceConstants.EDITOR_GRAMMAR_ACTION_FOLDING_ENABLED,
				0);
		addCheckBox(inner, AntlrFoldingMessages.FoldingPreferenceBlock_rules,
				AntlrPreferenceConstants.EDITOR_RULE_FOLDING_ENABLED, 0);

		return inner;
	}

	protected Button addCheckBox(Composite parent, String label, String key,
			int indentation) {
		Button checkBox = new Button(parent, SWT.CHECK);
		checkBox.setText(label);

		GridData gd = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING);
		gd.horizontalIndent = indentation;
		gd.horizontalSpan = 1;
		gd.grabExcessVerticalSpace = false;
		checkBox.setLayoutData(gd);
		checkBox.addSelectionListener(fCheckBoxListener);

		fCheckBoxes.put(checkBox, key);

		return checkBox;
	}

	private void initializeFields() {
		Iterator<Button> it = fCheckBoxes.keySet().iterator();
		while (it.hasNext()) {
			Button b = it.next();
			String key = fCheckBoxes.get(b);
			b.setSelection(fOverlayStore.getBoolean(key));
		}
	}

	public void performOk() {
		fOverlayStore.propagate();
	}

	public void initialize() {
		initializeFields();
	}

	public void performDefaults() {
		fOverlayStore.loadDefaults();
		initializeFields();
	}

	public void dispose() {
		fOverlayStore.stop();
	}
}
