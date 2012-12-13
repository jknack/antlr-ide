/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.internal.ui.preferences;

import org.deved.antlride.core.AntlrCore;
import org.deved.antlride.core.resources.AntlrPackage;
import org.deved.antlride.core.resources.AntlrPackages;
import org.deved.antlride.core.resources.InvalidPackageException;
import org.deved.antlride.ui.dialogs.AntlrBaseDialog;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class AntlrPackageDialog extends AntlrBaseDialog {

	private AntlrPackages packages;

	private AntlrPackage apackage;

	private Button addExtJARsButton;

	private Text homeText;

	private ListViewer viewer;

	private Text descriptionText;

	private Text versionText;

	private String title;

	private class CreatePackageTask implements SelectionListener {
		public void widgetDefaultSelected(SelectionEvent e) {
		}

		public void widgetSelected(SelectionEvent e) {
			DirectoryDialog dialog = new DirectoryDialog(getShell());
			String directory = dialog.open();
			if (directory != null) {
				createPackage(directory);
			}
		}
	}

	private class AddExternalJARsTask implements SelectionListener {
		public void widgetDefaultSelected(SelectionEvent e) {
		}

		public void widgetSelected(SelectionEvent e) {
			FileDialog dialog = new FileDialog(getShell());
			dialog.setFilterExtensions(new String[] { "*.jar" });
			String file = dialog.open();
			if (file != null) {
				apackage.addExternalJARs(file);
				updateUI(apackage);
			}
		}
	}

	public AntlrPackageDialog(Shell parentShell, AntlrPackages packages,
			String title) {
		super(parentShell);
		this.title = title;
		this.packages = packages;
	}

	@Override
	protected String getDialogName() {
		return "package";
	}

	public AntlrPackage getSelectedPackage() {
		return apackage;
	}

	private void createPackage(String directory) {
		apackage = null;
		try {
			apackage = AntlrPackages
					.createPackage(Path.fromOSString(directory));
			if (packages.exist(apackage)) {
				MessageDialog
						.openError(
								getShell(),
								AntlrPreferenceMessages.Builder_Package_Title,
								AntlrPreferenceMessages.Builder_Package_Duplicated_Package);
				apackage = null;
			} else {
				updateUI(apackage);
			}
		} catch (InvalidPackageException ex) {
			AntlrCore.error(ex);
			String message = ex.getMessage();
			Status status = new Status(IStatus.ERROR, AntlrCore.PLUGIN_ID,
					"Couldn't create a package", message != null
							&& message.length() > 0 ? ex : null);

			ErrorDialog.openError(getShell(),
					AntlrPreferenceMessages.Builder_Package_Title,
					AntlrPreferenceMessages.Builder_Package_Invalid_Antlr_Home,
					status);
		} finally {
			addExtJARsButton.setEnabled(apackage != null);
		}
	}

	@Override
	protected void cancelPressed() {
		apackage = null;
		super.cancelPressed();
	}

	@Override
	protected void setTitle() {
		setTitle("ANTLR Definition");
		setMessage("Specify attributes for an ANTLR",
				IMessageProvider.INFORMATION);
	}

	public void setInput(AntlrPackage antlrPackage) {
		this.apackage = antlrPackage;
	}

	private void updateUI(AntlrPackage antlrPackage) {
		if (antlrPackage != null) {
			homeText.setText(antlrPackage.getHome());
			descriptionText.setText(antlrPackage.getDescription());
			versionText.setText(antlrPackage.getVersion());
			viewer.setInput(antlrPackage.getClasspath());
		} else {
			homeText.setText("");
			descriptionText.setText("");
			versionText.setText("");
			viewer.setInput(null);
		}
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		GridData gd = null;
		Composite area = (Composite) super.createDialogArea(parent);
		getShell().setText(title);
		Composite composite = new Composite(area, SWT.NONE);
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));

		GridLayout layout = new GridLayout(3, false);
		layout.marginLeft = 10;
		layout.marginRight = 10;
		layout.verticalSpacing = 10;
		composite.setLayout(layout);

		Label label = new Label(composite, SWT.NONE);
		label.setText(AntlrPreferenceMessages.Builder_Package_Home);
		homeText = new Text(composite, SWT.BORDER);
		homeText.setEditable(false);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		homeText.setLayoutData(gd);
		Button directoryButton = new Button(composite, SWT.PUSH);
		directoryButton
				.setText(AntlrPreferenceMessages.Builder_Package_Directory);
		directoryButton.addSelectionListener(new CreatePackageTask());
		gd = new GridData();
		directoryButton.setLayoutData(gd);

		label = new Label(composite, SWT.NONE);
		label.setText(AntlrPreferenceMessages.Builder_Package_Version);
		gd = new GridData();
		label.setLayoutData(gd);
		versionText = new Text(composite, SWT.BORDER);
		versionText.setEditable(false);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		versionText.setLayoutData(gd);

		label = new Label(composite, SWT.NONE);
		label.setText(AntlrPreferenceMessages.Builder_Package_Desc);
		gd = new GridData();
		label.setLayoutData(gd);
		descriptionText = new Text(composite, SWT.BORDER);
		descriptionText.setEditable(false);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		descriptionText.setLayoutData(gd);

		label = new Label(composite, SWT.NONE);
		label.setText(AntlrPreferenceMessages.Builder_Package_Libs);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 3;
		label.setLayoutData(gd);

		viewer = new ListViewer(composite);
		viewer.setContentProvider(new ArrayContentProvider());
		viewer.setLabelProvider(new LabelProvider());
		gd = new GridData(GridData.FILL_BOTH);
		gd.horizontalSpan = 2;
		gd.verticalSpan = 10;
		viewer.getList().setLayoutData(gd);
		addExtJARsButton = new Button(composite, SWT.PUSH);
		addExtJARsButton
				.setText(AntlrPreferenceMessages.Builder_Package_Add_Ext_JARS);
		addExtJARsButton.setEnabled(false);
		addExtJARsButton.addSelectionListener(new AddExternalJARsTask());
		addExtJARsButton.setToolTipText("Useful for add additional targets");
		gd = new GridData(GridData.FILL_BOTH);
		gd.verticalAlignment = SWT.BEGINNING;

		updateUI(apackage);
		return composite;
	}
}
