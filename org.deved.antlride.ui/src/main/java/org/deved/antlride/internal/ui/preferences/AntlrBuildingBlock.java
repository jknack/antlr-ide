/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.internal.ui.preferences;

import java.text.MessageFormat;

import org.deved.antlride.core.AntlrConstants;
import org.deved.antlride.core.AntlrCore;
import org.deved.antlride.core.resources.AntlrPackages;
import org.deved.antlride.ui.AntlrUIHelper;
import org.eclipse.core.resources.IProject;
import org.eclipse.dltk.ui.preferences.AbstractOptionsBlock;
import org.eclipse.dltk.ui.preferences.PreferenceKey;
import org.eclipse.dltk.ui.util.IStatusChangeListener;
import org.eclipse.dltk.ui.util.PixelConverter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.preferences.IWorkbenchPreferenceContainer;

@SuppressWarnings("restriction")
public class AntlrBuildingBlock extends AbstractOptionsBlock {

	private CheckboxListener checkboxListener = new CheckboxListener();

	private final static PreferenceKey RUNTIME = new PreferenceKey(
			AntlrCore.PLUGIN_ID, AntlrConstants.ANTLR_BUILDER_RUNTIME);

	private final static PreferenceKey MAX_NUMBER_OF_PROBLEMS_REPORTED_PER_GRAMMAR = new PreferenceKey(
			AntlrCore.PLUGIN_ID,
			AntlrConstants.ANTLR_BUILDER_MAX_NUMBER_OF_PROBLEMS_REPORTED_PER_GRAMMAR);

	private final static PreferenceKey INCLUDE_STACK_TRACE_ON_INTERNAL_ERRORS = new PreferenceKey(
			AntlrCore.PLUGIN_ID,
			AntlrConstants.ANTLR_BUILDER_INCLUDE_STACK_TRACE_ON_INTERNAL_ERRORS);

	private final static PreferenceKey REPORT = new PreferenceKey(
			AntlrCore.PLUGIN_ID, AntlrConstants.ANTLR_BUILDER_REPORT);

	private final static PreferenceKey NFA = new PreferenceKey(
			AntlrCore.PLUGIN_ID, AntlrConstants.ANTLR_BUILDER_NFA);

	private final static PreferenceKey DFA = new PreferenceKey(
			AntlrCore.PLUGIN_ID, AntlrConstants.ANTLR_BUILDER_DFA);

	// private final static PreferenceKey X_DFA = new PreferenceKey(
	// AntlrCore.PLUGIN_ID, AntlrConstants.ANTLR_BUILDER_X_DFA);

	private final static PreferenceKey X_NO_PRUNE = new PreferenceKey(
			AntlrCore.PLUGIN_ID, AntlrConstants.ANTLR_BUILDER_X_NO_PRUNE);

	private final static PreferenceKey X_NO_COLLAPSE = new PreferenceKey(
			AntlrCore.PLUGIN_ID, AntlrConstants.ANTLR_BUILDER_X_NO_COLLAPSE);

	// private final static PreferenceKey X_DBG_CONVERSION = new PreferenceKey(
	// AntlrCore.PLUGIN_ID, AntlrConstants.ANTLR_BUILDER_X_DBG_CONVERSION);

	private final static PreferenceKey X_NO_MERGE_STOP_STATES = new PreferenceKey(
			AntlrCore.PLUGIN_ID,
			AntlrConstants.ANTLR_BUILDER_X_NO_MERGE_STOP_STATES);

	private final static PreferenceKey X_DFA_VERBOSE = new PreferenceKey(
			AntlrCore.PLUGIN_ID, AntlrConstants.ANTLR_BUILDER_X_DFA_VERBOSE);

	// private final static PreferenceKey X_WATCH_CONVERSION = new
	// PreferenceKey(
	// AntlrCore.PLUGIN_ID,
	// AntlrConstants.ANTLR_BUILDER_X_WATCH_CONVERSION);

	private final static PreferenceKey X_M = new PreferenceKey(
			AntlrCore.PLUGIN_ID, AntlrConstants.ANTLR_BUILDER_X_M);

	private final static PreferenceKey X_MAX_DFA_EDGES = new PreferenceKey(
			AntlrCore.PLUGIN_ID, AntlrConstants.ANTLR_BUILDER_X_MAX_DFA_EDGES);

	private final static PreferenceKey X_CONVERSION_TIME_OUT = new PreferenceKey(
			AntlrCore.PLUGIN_ID,
			AntlrConstants.ANTLR_BUILDER_X_CONVERSION_TIME_OUT);

	private final static PreferenceKey[] keys = { RUNTIME, REPORT, NFA, DFA,
			X_NO_PRUNE, X_NO_COLLAPSE, X_NO_MERGE_STOP_STATES, X_DFA_VERBOSE,
			X_M, X_MAX_DFA_EDGES, X_CONVERSION_TIME_OUT,
			MAX_NUMBER_OF_PROBLEMS_REPORTED_PER_GRAMMAR,
			INCLUDE_STACK_TRACE_ON_INTERNAL_ERRORS };

	public AntlrBuildingBlock(IStatusChangeListener context, IProject project,
			IWorkbenchPreferenceContainer container) {
		super(context, project, keys, container);
	}

	// -report=false[Console]
	// -nfa=false
	// -dfa=false
	// -Xdfa=false[Console]
	// -Xnoprune=true
	// -Xnocollapse=true
	// -Xdbgconversion=false [Console]
	// -Xnomergestopstates=true
	// -Xdfaverbose =false
	// -Xwatchconversion=false[Console]
	// -Xm m=4
	// -Xmaxdfaedges
	// -Xconversiontimeout

	@Override
	protected Control createOptionsBlock(Composite parent) {

		ScrolledForm form = AntlrUIHelper.createScrolledForm(parent);

		Composite composite = AntlrUIHelper
				.createExpandableSection(form, "General", true);

		if (isProjectPreferencePage()) {
			createRuntimeSectionForProject(composite);
		}

		addLabelledTextField(
				composite,
				AntlrPreferenceMessages.Builder_max_number_of_problems_reported_per_grammar,
				"", MAX_NUMBER_OF_PROBLEMS_REPORTED_PER_GRAMMAR, 2, 5,
				SWT.RIGHT);
		addCheckBox(
				composite,
				AntlrPreferenceMessages.Builder_include_stack_trace_on_internal_errors,
				INCLUDE_STACK_TRACE_ON_INTERNAL_ERRORS, 0);
		addCheckBox(composite, AntlrPreferenceMessages.Builder_report, REPORT,
				0);
		addCheckBox(composite, AntlrPreferenceMessages.Builder_nfa, NFA, 0);
		addCheckBox(composite, AntlrPreferenceMessages.Builder_dfa, DFA, 0);

		composite = AntlrUIHelper.createExpandableSection(form, "Extended", true);

		createExtendedOptionsSection(composite);

		return form;
	}

	@Override
	protected boolean processChanges(IWorkbenchPreferenceContainer container) {
		String option = "-Xm";
		String value = getString(X_M);
		if (!validatePositiveNumber(option, value)) {
			return false;
		}
		option = "-Xmaxdfaedges";
		value = getString(X_MAX_DFA_EDGES);
		if (!validatePositiveNumber(option, value)) {
			return false;
		}
		option = "-Xconversiontimeout";
		value = getString(X_CONVERSION_TIME_OUT);
		if (!validatePositiveNumber(option, value)) {
			return false;
		}
		return super.processChanges(container);
	}

	private Composite createRuntimeSectionForProject(Composite composite) {

		GridData gd = new GridData();

		String[] versions = AntlrPackages.getAvailablePackageNames();
		Control[] widgets = addComboBox(composite,
				AntlrPreferenceMessages.Builder_Runtime, RUNTIME, versions,
				versions);

		widgets[0].setLayoutData(gd);

		gd = new GridData(SWT.END, SWT.FILL, false, true);
		gd.widthHint = 100;

		widgets[1].setLayoutData(gd);

		return composite;
	}

	@Override
	public void dispose() {
		super.dispose();
	}

	private Composite createExtendedOptionsSection(Composite composite) {

		// addCheckBox(composite, AntlrPreferenceMessages.Builder_Xdfa, X_DFA,
		// 0);
		addCheckBox(composite, AntlrPreferenceMessages.Builder_Xnoprune,
				X_NO_PRUNE, 0);
		addCheckBox(composite, AntlrPreferenceMessages.Builder_Xnocollapse,
				X_NO_COLLAPSE, 0);
		// addCheckBox(composite,
		// AntlrPreferenceMessages.Builder_Xdbgconversion,
		// X_DBG_CONVERSION, 0);
		addCheckBox(composite,
				AntlrPreferenceMessages.Builder_Xnomergestopstates,
				X_NO_MERGE_STOP_STATES, 0);
		addCheckBox(composite, AntlrPreferenceMessages.Builder_Xdfaverbose,
				X_DFA_VERBOSE, 0);
		// addCheckBox(composite,
		// AntlrPreferenceMessages.Builder_Xwatchconversion,
		// X_WATCH_CONVERSION, 0);

		addLabelledTextField(composite, AntlrPreferenceMessages.Builder_Xm,
				"-Xm", X_M, 2, 5, SWT.LEFT);

		addLabelledTextField(composite,
				AntlrPreferenceMessages.Builder_Xmaxdfaedges, "-Xmaxdfaedges",
				X_MAX_DFA_EDGES, 6, 5, SWT.LEFT);

		addLabelledTextField(composite,
				AntlrPreferenceMessages.Builder_Xconversiontimeout,
				"-Xconversiontimeout", X_CONVERSION_TIME_OUT, 5, 5, SWT.LEFT);

		return composite;
	}

	protected Control[] addLabelledTextField(Composite composite, String label,
			String option, PreferenceKey key, int textLimit, int indentation,
			int align) {

		PixelConverter pixelConverter = new PixelConverter(composite);
		Text textControl;
		Label labelControl;
		if (align == SWT.LEFT) {
			textControl = new Text(composite, SWT.BORDER | SWT.SINGLE);
			labelControl = new Label(composite, SWT.NONE);
		} else {
			labelControl = new Label(composite, SWT.NONE);
			textControl = new Text(composite, SWT.BORDER | SWT.SINGLE);
		}
		GridData gd = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING);
		gd.widthHint = pixelConverter
				.convertWidthInCharsToPixels(textLimit + 1);
		textControl.setLayoutData(gd);
		textControl.setTextLimit(textLimit);

		textControl.setText(getString(key));

		textControl.setData("key", key);
		textControl.setData("option", option);
		textControl.addModifyListener(new ModifyListener() {

			public void modifyText(ModifyEvent e) {
				numberFieldChanged((Text) e.widget);
			}

		});
		labelControl.setText(label);
		gd = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING);
		gd.horizontalIndent = indentation;
		labelControl.setLayoutData(gd);

		return new Control[] { labelControl, textControl };
	}

	private void numberFieldChanged(Text textControl) {
		setString(textControl.getData("key"), textControl.getText());
	}

	private boolean validatePositiveNumber(String option, String number) {
		Integer n = null;
		try {
			n = new Integer(number);
		} catch (Exception ex) {
		}
		if (n == null || n.intValue() <= 0) {
			MessageBox dialog = new MessageBox(getShell(), SWT.OK);
			String message = MessageFormat.format(
					AntlrPreferenceMessages.Builder_Xnumber_error, option);
			dialog.setText(option);
			dialog.setMessage(message);
			dialog.open();
			return false;
		}
		return true;
	}

	private Button addCheckBox(Composite parent, String label,
			PreferenceKey key, int indentation) {
		Button button = new Button(parent, SWT.CHECK);
		button.setText(label);

		GridData gd = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING);
		gd.horizontalIndent = indentation;
		gd.horizontalSpan = 2;
		button.setLayoutData(gd);
		button.addSelectionListener(checkboxListener);
		String value = getString(key);
		button.setData(key);
		button.setSelection("true".equals(value));

		return button;
	}

	private Control[] addComboBox(Composite parent, String label,
			PreferenceKey key, String[] items, String[] values) {

		Label labelControl = new Label(parent, SWT.NONE);
		labelControl.setText(label);

		Combo combo = new Combo(parent, SWT.SINGLE | SWT.READ_ONLY);
		combo.setFont(parent.getFont());
		combo.setItems(items);
		combo.setData("key", key);
		String value = getString(key);
		int i = 0;
		for (; i < values.length; i++)
			if (values[i].equals(value))
				break;
		if (values.length > 0 && i < values.length) {
			combo.select(i);
			setString(key, values[i]);
		}
		for (i = 0; i < values.length; i++)
			combo.setData("" + i, values[i]);
		combo.addSelectionListener(new ComboboxListener());
		return new Control[] { labelControl, combo };
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

	private class ComboboxListener implements SelectionListener {

		public void widgetDefaultSelected(SelectionEvent e) {
		}

		public void widgetSelected(SelectionEvent e) {
			Combo b = (Combo) e.widget;
			PreferenceKey key = (PreferenceKey) b.getData("key");
			String runtimeVersion = (String) b.getData(""
					+ b.getSelectionIndex());
			// String runtimeVersion = b.getText();
			setString(key, runtimeVersion);
		}
	}
}
