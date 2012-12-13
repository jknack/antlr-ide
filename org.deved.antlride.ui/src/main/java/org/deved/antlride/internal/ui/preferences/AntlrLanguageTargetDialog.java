/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.internal.ui.preferences;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.deved.antlride.core.AntlrCore;
import org.deved.antlride.core.resources.AntlrLanguageTarget;
import org.deved.antlride.core.resources.AntlrLanguageTargetRepository;
import org.deved.antlride.core.resources.AntlrPackage;
import org.deved.antlride.ui.dialogs.AntlrBaseDialog;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class AntlrLanguageTargetDialog extends AntlrBaseDialog {

	private class CreateLanguageTargetTask implements SelectionListener {
		public void widgetDefaultSelected(SelectionEvent e) {
		}

		public void widgetSelected(SelectionEvent e) {
			FileDialog dialog = new FileDialog(getShell());
			String file = dialog.open();
			if (file != null) {
				createLanguageTarget(file);
			}
		}
	}

	private static final IStatus INVALID_LANGUAGE = new Status(IStatus.ERROR,
			AntlrCore.PLUGIN_ID, "Invalid file");

	private AntlrPackage selectedPackage;

	private String title;

	private Text languageText;

	private AntlrLanguageTarget languageTarget;

	private IPath languagePath;

	protected AntlrLanguageTarget parentTarget;

	public AntlrLanguageTargetDialog(Shell parentShell, String title) {
		super(parentShell);
		this.title = title;
	}

	private void createLanguageTarget(String file) {
		languagePath = null;
		String language = null;
		try {
			JarFile jarFile = new JarFile(file);
			Enumeration<JarEntry> entries = jarFile.entries();
			IPath root = Path
					.fromPortableString(AntlrLanguageTargetRepository.STRING_TEMPLATE_PATH);
			while (entries.hasMoreElements()) {
				JarEntry entry = entries.nextElement();
				IPath path = Path.fromPortableString(entry.getName());
				IPath parent = path.removeLastSegments(1);
				if (parent.equals(root)) {
					languagePath = Path.fromOSString(file);
					language = path.lastSegment();
					break;
				}
			}
			jarFile.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (languagePath != null) {
			languageText.setText(language);
		} else {
			ErrorDialog.openError(getShell(), "Invalid language target",
					"Can't find an language target", INVALID_LANGUAGE);
		}
	}

	@Override
	protected Point getDefaultSize() {
		return new Point(383, 250);
	}

	@Override
	protected String getDialogName() {
		return "registerLanguageTarget";
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		GridData gd = null;
		Composite area = (Composite) super.createDialogArea(parent);
		getShell().setText(title);
		Composite composite = new Composite(area, SWT.NONE);
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));

		GridLayout layout = new GridLayout(3, false);
		composite.setLayout(layout);

		Label label = new Label(composite, SWT.NONE);
		label.setText("Name:");

		languageText = new Text(composite, SWT.READ_ONLY | SWT.BORDER);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		languageText.setLayoutData(gd);

		Button button = new Button(composite, SWT.PUSH);
		button.setText("Browse...");
		button.addSelectionListener(new CreateLanguageTargetTask());

		label = new Label(composite, SWT.NONE);
		label.setText("Parent:");

		ComboViewer comboViewer = new ComboViewer(composite);
		comboViewer.setContentProvider(new ArrayContentProvider());
		comboViewer.setInput(selectedPackage.getLanguageTargets());

		comboViewer
				.addSelectionChangedListener(new ISelectionChangedListener() {
					public void selectionChanged(SelectionChangedEvent event) {
						IStructuredSelection selection = (IStructuredSelection) event
								.getSelection();
						parentTarget = (AntlrLanguageTarget) selection
								.getFirstElement();
						validate();
					}
				});

		return composite;
	}

	@Override
	protected void okPressed() {
		languageTarget = null;
		if (validate()) {
			languageTarget = AntlrLanguageTargetRepository.create(parentTarget,
					languageText.getText(), languagePath.toOSString());
			AntlrLanguageTargetRepository.save(languageTarget);
			super.okPressed();
		}
	}

	@Override
	protected void cancelPressed() {
		languageTarget = null;
		super.cancelPressed();
	}

	public AntlrLanguageTarget getLanguageTarget() {
		return languageTarget;
	}

	private boolean validate() {
		if (languagePath == null) {
			setMessage("No language target defined", IMessageProvider.ERROR);
			return false;
		}
		if (parentTarget == null) {
			setMessage("Select a parent language", IMessageProvider.ERROR);
			return false;
		}
		if (AntlrLanguageTargetRepository.exists(languagePath.lastSegment())) {
			setMessage("Duplicated language: " + languagePath.lastSegment(),
					IMessageProvider.ERROR);
			return false;
		}
		setDefaultMessage();
		return true;
	}

	private void setDefaultMessage() {
		setMessage("Register a new language target for ANTLR",
				IMessageProvider.INFORMATION);
	}

	@Override
	protected void setTitle() {
		setTitle("Add language target");
		setDefaultMessage();
	}

	public void setInput(AntlrPackage selectedPackage) {
		this.selectedPackage = selectedPackage;
	}
}
