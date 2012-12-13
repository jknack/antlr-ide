/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *******************************************************************************/
package org.deved.antlride.viz.railroad;

import java.io.File;

import org.deved.antlride.railroad.RailRoadExportOptions;
import org.deved.antlride.ui.AntlrUIHelper;
import org.deved.antlride.ui.dialogs.AntlrBaseDialog;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.ScrolledForm;

public class RailRoadExportDialog extends AntlrBaseDialog implements
		RailRoadExportOptions {

	private static final String TITLE = "Railroad Exporter";

	private boolean includeDoc = true;

	private boolean includeText = true;

	private File outputDirectory;

	private boolean optimizedGraph = true;

	public RailRoadExportDialog(Shell shell) {
		super(shell);
	}

	@Override
	protected void okPressed() {
		if (outputDirectory == null) {
			setMessage("Please select the output directory",
					IMessageProvider.ERROR);
		} else {
			super.okPressed();
		}
	}

	@Override
	protected void setTitle() {
		setTitle(TITLE);
		setMessage("Export an ANTLR grammar as HTML page",
				IMessageProvider.INFORMATION);
		getShell().setText(TITLE);
	}

	@Override
	protected Point getDefaultSize() {
		return new Point(500, 400);
	}
	
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout(3, false);		
		layout.marginTop = 20;
		layout.verticalSpacing = 20;
		layout.marginBottom = 10;
		layout.marginLeft = 10;
		layout.marginRight = 10;

		composite.setLayout(layout);

		Label label = new Label(composite, SWT.NONE);
		label.setText("To directory:");
		final Text text = new Text(composite, SWT.READ_ONLY | SWT.BORDER);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.minimumWidth = 280;
		text.setLayoutData(gd);

		Button button = new Button(composite, SWT.PUSH);
		button.setLayoutData(new GridData());
		button.forceFocus();
		button.setText("   Browse...   ");
		button.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				DirectoryDialog dialog = new DirectoryDialog(getShell(),
						SWT.SAVE);
				String path = dialog.open();
				if (path != null) {
					outputDirectory = Path.fromOSString(path).toFile();
					text.setText(path);
				}
			}

			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		ScrolledForm form = AntlrUIHelper.createScrolledForm(composite);
		gd = new GridData();
		gd.horizontalSpan = 3;
		form.setLayoutData(gd);

		Composite section = AntlrUIHelper.createExpandableSection(form,
				"Options", true);
		button = new Button(section, SWT.CHECK);
		gd = new GridData();
		gd.horizontalSpan = 3;
		button.setLayoutData(gd);

		button.setText("Optimize diagram");
		button.setSelection(true);
		button.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				optimizedGraph = !optimizedGraph;
			}

			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});

		button = new Button(section, SWT.CHECK);
		gd = new GridData();
		gd.horizontalSpan = 3;
		button.setLayoutData(gd);
		button.setText("Add textual definition to each rule");
		button.setSelection(true);
		button.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				includeText = !includeText;
			}

			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});

		button = new Button(section, SWT.CHECK);
		gd = new GridData();
		gd.horizontalSpan = 3;
		button.setLayoutData(gd);
		button.setText("Add documentation to each rule");
		button.setSelection(true);
		button.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				includeDoc = !includeDoc;
			}

			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});

		return super.createDialogArea(parent);
	}

	public boolean includeDocumentation() {
		return includeDoc;
	}

	public boolean includeTextualDefinition() {
		return includeText;
	}

	public File outputDirectory() {
		return outputDirectory;
	}

	public boolean optimized() {
		return optimizedGraph;
	}

	@Override
	protected String getDialogName() {
		return getClass().getSimpleName();
	}
}
