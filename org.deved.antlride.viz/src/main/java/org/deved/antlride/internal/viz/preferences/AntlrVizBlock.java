/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *******************************************************************************/
package org.deved.antlride.internal.viz.preferences;

import org.deved.antlride.ui.AntlrPreferenceConstants;
import org.deved.antlride.ui.AntlrUI;
import org.eclipse.core.resources.IProject;
import org.eclipse.dltk.ui.preferences.AbstractOptionsBlock;
import org.eclipse.dltk.ui.preferences.PreferenceKey;
import org.eclipse.dltk.ui.util.IStatusChangeListener;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.preferences.IWorkbenchPreferenceContainer;

public class AntlrVizBlock extends AbstractOptionsBlock {

	private static final PreferenceKey SHOW_DFA = new PreferenceKey(
			AntlrUI.PLUGIN_ID, AntlrPreferenceConstants.DFA_VIEW);

	private static final PreferenceKey SHOW_RAIL_ROAD = new PreferenceKey(
			AntlrUI.PLUGIN_ID, AntlrPreferenceConstants.RAILROAD_VIEW);

	private static final PreferenceKey[] KEYS = { SHOW_DFA, SHOW_RAIL_ROAD };

	public AntlrVizBlock(IStatusChangeListener newStatusChangedListener,
			IProject project, IWorkbenchPreferenceContainer container) {
		super(newStatusChangedListener, project, KEYS, container);
	}

	@Override
	protected Control createOptionsBlock(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout(1, false);
		layout.verticalSpacing = 15;
		composite.setLayout(layout);

		addCheckBox(composite, "Show railroad page", SHOW_RAIL_ROAD, 15);

		addCheckBox(composite, "Show decision page", SHOW_DFA, 15);
		return composite;
	}

	private Button addCheckBox(Composite parent, String label,
			PreferenceKey key, int indentation) {
		Button button = new Button(parent, SWT.CHECK);
		button.setText(label);

		GridData gd = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING);
		gd.horizontalIndent = indentation;
		// gd.horizontalSpan = 2;
		button.setLayoutData(gd);

		button.addSelectionListener(new CheckboxListener());
		String value = getString(key);
		button.setData(key);
		button.setSelection("true".equals(value));

		return button;
	}

	private class CheckboxListener implements SelectionListener {

		public void widgetDefaultSelected(SelectionEvent e) {
		}

		public void widgetSelected(SelectionEvent e) {
			Button b = (Button) e.widget;
			PreferenceKey key = (PreferenceKey) b.getData();
			setBoolean(key, b.getSelection());
		}
	}

}
