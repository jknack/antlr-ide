/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.internal.ui.preferences;

import java.io.File;

import org.deved.antlride.core.AntlrConstants;
import org.deved.antlride.ui.AntlrUIHelper;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.dltk.ui.preferences.PreferenceKey;
import org.eclipse.dltk.ui.util.PixelConverter;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.ScrolledForm;

public class AntlrCodeGeneratorPageBuilder {

	private final static IInputValidator fileValidator = new FilePathValidator();

	private Text absolutePathText;

	private Text relativePathText;

	private Shell shell;

	private AntlrCodeGeneratorPreferences preferences;

	private CheckboxListener checkboxListener = new CheckboxListener();

	private SelectionListener outputOptionListener = new OutputOptionListener();

	public AntlrCodeGeneratorPageBuilder(Shell shell,
			AntlrCodeGeneratorPreferences preferences) {
		this.shell = shell;
		this.preferences = preferences;
	}

	public boolean processChanges() {
		String outputOption = getString(AntlrCodeGeneratorPreferences.OUTPUT_OPTION);
		Text resetText = null;
		String outputFolder = null;
		if (outputOption
				.equals(AntlrConstants.ANTLR_CODE_GENERATOR_OUTPUT_OPTION_ABSOLUTE_FOLDER)) {

			if (!isPathValid(absolutePathText.getText()))
				return false;
			outputFolder = absolutePathText.getText();
			resetText = relativePathText;
		} else if (outputOption
				.equals(AntlrConstants.ANTLR_CODE_GENERATOR_OUTPUT_OPTION_RELATIVE_FOLDER)) {
			outputFolder = relativePathText.getText();
			resetText = absolutePathText;
		}
		if (outputFolder != null) {
			resetText.setText("");
			setString(AntlrCodeGeneratorPreferences.OUTPUT_FOLDER, outputFolder);
		}
		preferences.savePreferences();
		return true;
	}

	private boolean isPathValid(String path) {
		String message = fileValidator.isValid(path);
		if (message != null) {
			MessageBox dialog = new MessageBox(shell, SWT.OK);
			dialog
					.setText(AntlrPreferenceMessages.CodeGenerator_o_option_folder_absolute_folder_name);
			dialog.setMessage(message);
			dialog.open();
			return false;
		}
		return true;
	}

	private void createSameAsGrammarOption(Composite parent) {
		addRadioButton(
				parent,
				AntlrPreferenceMessages.CodeGenerator_o_option_folder_same_as_grammar,
				AntlrCodeGeneratorPreferences.OUTPUT_OPTION,
				AntlrConstants.ANTLR_CODE_GENERATOR_OUTPUT_OPTION_SAME_AS_GRAMMAR);
	}

	private void createJavaSection(ScrolledForm parent) {
		Composite composite = AntlrUIHelper.createExpandableSection(parent,
				AntlrPreferenceMessages.CodeGenerator_java_options, true);

		addCheckBox(
				composite,
				AntlrPreferenceMessages.CodeGenerator_o_option_folder_for_java,
				AntlrCodeGeneratorPreferences.APPEND_JAVA_PACKAGE_TO_OUTPUT_FOLDER,
				0);
	}

	private void createRelativeOption(Composite parent) {
		Button button = addRadioButton(
				parent,
				AntlrPreferenceMessages.CodeGenerator_o_option_folder_relative_folder_name,
				AntlrCodeGeneratorPreferences.OUTPUT_OPTION,
				AntlrConstants.ANTLR_CODE_GENERATOR_OUTPUT_OPTION_RELATIVE_FOLDER);

		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		Control[] controls = addLabelledTextField(composite,
				AntlrPreferenceMessages.CodeGenerator_o_option_folder_name, 35,
				0, new FilePathValidator());
		relativePathText = (Text) controls[1];
		relativePathText.setEditable(button.getSelection());
		if (button.getSelection()) {
			String outputFolder = getString(AntlrCodeGeneratorPreferences.OUTPUT_FOLDER);
			relativePathText.setText(outputFolder);
		}
	}

	private void createAbsoluteOption(Composite parent) {
		Button optionButton = addRadioButton(
				parent,
				AntlrPreferenceMessages.CodeGenerator_o_option_folder_absolute_folder_name,
				AntlrCodeGeneratorPreferences.OUTPUT_OPTION,
				AntlrConstants.ANTLR_CODE_GENERATOR_OUTPUT_OPTION_ABSOLUTE_FOLDER);

		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(3, false));

		Control[] controls = addLabelledTextField(composite,
				AntlrPreferenceMessages.CodeGenerator_o_option_folder_name, 35,
				0, new FilePathValidator());

		absolutePathText = (Text) controls[1];
		absolutePathText.setEditable(optionButton.getSelection());
		if (optionButton.getSelection()) {
			String string = getString(AntlrCodeGeneratorPreferences.OUTPUT_FOLDER);
			absolutePathText.setText(string);
		}
		Button button = new Button(composite, SWT.PUSH);
		button.setText("Browse"); //$NON-NLS-1$
		button.addSelectionListener(new ShowDirectoryDialog());
	}

	private Composite createOutputSection(ScrolledForm parent) {
		Composite composite = AntlrUIHelper.createExpandableSection(parent,
				AntlrPreferenceMessages.CodeGenerator_o_option_folder, true);

		GridLayout layout = new GridLayout(1, false);

		composite.setLayout(layout);

		Label description = new Label(composite, SWT.NONE);

		description
				.setText(AntlrPreferenceMessages.CodeGenerator_o_option_folder_description);

		createSameAsGrammarOption(composite);

		createRelativeOption(composite);

		createAbsoluteOption(composite);

		return composite;
	}

	private Composite createOptionsSection(ScrolledForm parent) {
		Composite composite = AntlrUIHelper.createExpandableSection(parent,
				AntlrPreferenceMessages.CodeGenerator_options, false);

		GridLayout layout = new GridLayout(1, false);

		composite.setLayout(layout);

		addCheckBox(composite, AntlrPreferenceMessages.CodeGenerator_debug,
				AntlrCodeGeneratorPreferences.DEBUG, 0);

		addCheckBox(composite, AntlrPreferenceMessages.CodeGenerator_profile,
				AntlrCodeGeneratorPreferences.PROFILE, 0);

		addCheckBox(composite, AntlrPreferenceMessages.CodeGenerator_trace,
				AntlrCodeGeneratorPreferences.TRACE, 0);

		addLabelledTextField(composite,
				AntlrPreferenceMessages.CodeGenerator_max_memory,
				AntlrCodeGeneratorPreferences.MAX_MEMORY, 4, 0, SWT.LEFT);

		addCheckBox(composite, AntlrPreferenceMessages.CodeGenerator_XdbgST,
				AntlrCodeGeneratorPreferences.X_DBG_ST, 0);

		addLabelledTextField(composite,
				AntlrPreferenceMessages.CodeGenerator_x_max_swith_case_labels,
				AntlrCodeGeneratorPreferences.X_MAX_SWITCH_CASE_LABELS, 3, 0,
				SWT.LEFT);

		addLabelledTextField(composite,
				AntlrPreferenceMessages.CodeGenerator_x_min_swith_alts,
				AntlrCodeGeneratorPreferences.X_MIN_SWITCH_ALTS, 3, 0, SWT.LEFT);

		return composite;
	}

	public Control createContents(Composite parent) {
		// Composite composite = new Composite(parent, SWT.NONE);
		//
		// GridLayout layout = new GridLayout(1, false);
		//
		// layout.verticalSpacing = 15;
		//
		// composite.setLayout(layout);

		ScrolledForm form = AntlrUIHelper.createScrolledForm(parent);

		createOutputSection(form);

		createJavaSection(form);

		createOptionsSection(form);

		return form;
	}

	private Button addRadioButton(Composite parent, String label,
			PreferenceKey key, String value) {
		GridData gd = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING);

		Button button = new Button(parent, SWT.RADIO);
		button.setText(label);
		button.setData(new Object[] { key, String.valueOf(value) });
		button.addSelectionListener(outputOptionListener);
		button.setLayoutData(gd);
		String str = getString(key);
		button.setSelection(str.equals(value));

		return button;
	}

	private Control[] addLabelledTextField(Composite composite, String label,
			int textLimit, int indentation, IInputValidator validator) {

		PixelConverter pixelConverter = new PixelConverter(composite);

		Label labelControl = new Label(composite, SWT.NONE);
		labelControl.setText(label);
		GridData gd = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING);
		gd.horizontalIndent = indentation;
		labelControl.setLayoutData(gd);

		Text textControl = new Text(composite, SWT.BORDER | SWT.SINGLE);
		gd = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING);
		gd.widthHint = pixelConverter
				.convertWidthInCharsToPixels(textLimit + 1);
		textControl.setLayoutData(gd);
		// textControl.setTextLimit(textLimit);

		return new Control[] { labelControl, textControl };
	}

	protected Control[] addLabelledTextField(Composite parent, String label,
			PreferenceKey key, int textLimit, int indentation, int align) {

		Composite composite = new Composite(parent, SWT.NONE);

		composite.setLayout(new GridLayout(2, false));

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
		setString((PreferenceKey) textControl.getData("key"), textControl
				.getText());
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
		button.setData("key", key);
		button.setSelection("true".equals(value));

		return button;
	}

	public String getString(PreferenceKey key) {
		return preferences.getString(key);
	}

	public void setString(PreferenceKey key, String value) {
		preferences.setString(key, value);
	}

	public void setBoolean(PreferenceKey key, boolean value) {
		preferences.setBoolean(key, value);
	}

	private class OutputOptionListener implements SelectionListener {

		public void widgetDefaultSelected(SelectionEvent e) {
		}

		public void widgetSelected(SelectionEvent e) {
			Button b = (Button) e.widget;
			if (b.getSelection()) {
				Object[] data = (Object[]) b.getData();
				String value = (String) data[1];
				absolutePathText.setEditable(false);
				relativePathText.setEditable(false);
				if (value
						.equals(AntlrConstants.ANTLR_CODE_GENERATOR_OUTPUT_OPTION_ABSOLUTE_FOLDER)) {
					absolutePathText.setEditable(true);
					absolutePathText.setFocus();
				} else if (value
						.equals(AntlrConstants.ANTLR_CODE_GENERATOR_OUTPUT_OPTION_RELATIVE_FOLDER)) {
					relativePathText.setEditable(true);
					relativePathText.setFocus();
				}
				setString((PreferenceKey) data[0], value);
			}
		}

	}

	private class CheckboxListener implements SelectionListener {

		public void widgetDefaultSelected(SelectionEvent e) {
		}

		public void widgetSelected(SelectionEvent e) {
			Button b = (Button) e.widget;
			PreferenceKey key = (PreferenceKey) b.getData("key");
			setBoolean(key, b.getSelection());
		}

	}

	private class ShowDirectoryDialog implements SelectionListener {

		public void widgetSelected(SelectionEvent e) {
			DirectoryDialog dialog = new DirectoryDialog(shell);
			String folder = dialog.open();
			if (folder != null && isPathValid(folder)) {
				absolutePathText.setText(folder);
			}
			dialog = null;
		}

		public void widgetDefaultSelected(SelectionEvent e) {

		}
	}

	private static class FilePathValidator implements IInputValidator {
		public String isValid(String newText) {
			IPath path = Path.fromOSString(newText);
			File file = path.toFile();

			String error = null;
			if ("".equals(newText)) { //$NON-NLS-1$
				error = AntlrPreferenceMessages.CodeGenerator_o_option_folder_error_empty;
			} else if (!file.exists()) {
				error = AntlrPreferenceMessages.CodeGenerator_o_option_folder_error_invalid;
			}
			return error;
		}
	}
}
