/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.internal.ui.preferences;

import org.deved.antlride.core.AntlrConstants;
import org.deved.antlride.core.AntlrCore;
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
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.preferences.IWorkbenchPreferenceContainer;

public class AntlrGlobalBlock extends AbstractOptionsBlock {

	private CheckboxListener checkboxListener = new CheckboxListener();

	private final static PreferenceKey MARK_GENERATED_RESOURCES_AS_DERIVED = new PreferenceKey(
			AntlrCore.PLUGIN_ID,
			AntlrConstants.ANTLR_GENERAL_MARK_GENERATED_RESOURCES_AS_DERIVED);

	private final static PreferenceKey[] keys = { MARK_GENERATED_RESOURCES_AS_DERIVED };

	public AntlrGlobalBlock(IStatusChangeListener context, IProject project,
			IWorkbenchPreferenceContainer container) {
		super(context, project, keys, container);
	}

	@Override
	protected Control createOptionsBlock(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout(1, false);
		layout.verticalSpacing = 15;
		composite.setLayout(layout);

		Label label = new Label(composite, SWT.NONE);
		label.setText(AntlrPreferenceMessages.General_description);

		addCheckBox(
				composite,
				AntlrPreferenceMessages.General_mark_generated_resources_as_derived,
				MARK_GENERATED_RESOURCES_AS_DERIVED, 15);
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

		button.addSelectionListener(checkboxListener);
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
