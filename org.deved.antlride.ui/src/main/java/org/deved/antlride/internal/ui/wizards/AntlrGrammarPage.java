/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.internal.ui.wizards;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.deved.antlride.core.AntlrConstants;
import org.deved.antlride.core.AntlrCore;
import org.deved.antlride.core.AntlrLanguageTargetName;
import org.deved.antlride.core.build.AntlrBuildUnit;
import org.deved.antlride.core.model.GrammarType;
import org.deved.antlride.core.resources.AntlrResourceFinder;
import org.deved.antlride.core.util.AntlrTextHelper;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.dltk.ui.DLTKUIPlugin;
import org.eclipse.dltk.ui.dialogs.StatusInfo;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.dialogs.ContainerSelectionDialog;
import org.eclipse.ui.ide.IDE;

public abstract class AntlrGrammarPage extends WizardPage {

	private static final String PROTOTYPE = "prototype";

	private class ValidationListener implements ModifyListener {
		public void modifyText(ModifyEvent e) {
			validate(getFolder(), getGrammarName(), getLanguage(),
					getTokenVocab());
		}
	}

	private class TokenVocabSelectionListener implements SelectionListener {

		public void widgetSelected(SelectionEvent e) {
			AntlrTokenVocabSelectionDialog dialog = new AntlrTokenVocabSelectionDialog(
					getShell());
			// dialog.addFilter(filter);
			IContainer container = ResourcesPlugin.getWorkspace().getRoot(); // getProject(getFolder());
			final List<IResource> resources = new ArrayList<IResource>();
			try {
				container.accept(new IResourceVisitor() {

					public boolean visit(IResource resource)
							throws CoreException {
						if (AntlrConstants.ANTLR_GRAMMAR_FILE_EXTENSION
								.equals(resource.getFileExtension())) {
							GrammarType grammarType = AntlrResourceFinder
									.getGrammarType(resource);
							if (grammarType == GrammarType.COMBINED
									|| grammarType == GrammarType.PARSER
									|| grammarType == GrammarType.LEXER) {
								resources.add(resource);
							}
						}
						return true;
					}
				});
			} catch (CoreException ex) {
				AntlrCore.error(ex);
			}
			dialog.setElements(resources);
			if (dialog.open() == Window.OK) {
				Object selection = dialog.getFirstResult();
				if (selection != null) {
					tokenVocabText
							.setText(resourceToString(((IFile) selection)));
				}
			}
		}

		public void widgetDefaultSelected(SelectionEvent e) {
		}
	}

	private class ProperyChangeListener implements SelectionListener {

		public void widgetDefaultSelected(SelectionEvent e) {
		}

		public void widgetSelected(SelectionEvent e) {
			Widget widget = e.widget;
			String property = (String) widget.getData(PROPERTY_NAME);
			String value = (String) widget.getData(property);
			if ((widget.getStyle() & SWT.CHECK) == SWT.CHECK) {
				value = "" + !Boolean.valueOf(value).booleanValue();
				widget.setData(property, value);
			}
			setProperty(property, value);
		}

	}

	private class ChooseFolderListener implements SelectionListener {

		public void widgetSelected(SelectionEvent e) {
			ContainerSelectionDialog dialog = new ContainerSelectionDialog(
					getShell(), folder, true, "Select a grammar container: ");

			if (dialog.open() == Window.OK) {
				Object[] selection = dialog.getResult();
				if (selection != null && selection.length > 0) {
					IPath path = (IPath) selection[0];
					folderText.setText(resourceToString(path));
				}
			}
		}

		public void widgetDefaultSelected(SelectionEvent e) {
		}
	}

	protected static final String FILE_PATH = "path";

	protected static final String LANGUAGE = "language";

	protected static final String GRAMMAR_NAME = "name";

	private static final String PROPERTY_NAME = "keyName";

	protected static final String OUTPUT = "output";

	private Map<String, String> properties;

	private Text folderText;

	private IContainer folder;

	private Text nameText;

	private Combo languageCombo;

	private Text tokenVocabText;

	private Button tokenVocabButton;

	public AntlrGrammarPage(String pageName) {
		super(pageName);

		properties = new HashMap<String, String>();

		setTitle(getPageTitle());

		setDescription(getPageDescription());
	}

	protected String getProperty(String property) {
		return properties.get(property);
	}

	protected void setProperty(String property, String value) {
		properties.put(property, value);
	}

	protected abstract String getPageDescription();

	protected abstract String getPageTitle();

	public void createControl(Composite parent) {
		initializeDialogUnits(parent);

		Composite composite = new Composite(parent, SWT.NONE);

		GridLayout gridLayout = new GridLayout(3, false);
		gridLayout.verticalSpacing = 10;
		composite.setLayout(gridLayout);

		Label label = null;
		GridData gd = null;

		ValidationListener validator = new ValidationListener();

		label = new Label(composite, SWT.NONE);
		label.setText("Container:");
		folderText = new Text(composite, SWT.BORDER);
		folderText.setText(resourceToString(folder));
		folderText.addModifyListener(validator);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		folderText.setLayoutData(gd);
		Button browse = new Button(composite, SWT.PUSH);
		browse.addSelectionListener(new ChooseFolderListener());
		browse.setText("    Browse...    ");

		label = new Label(composite, SWT.NONE);
		label.setText("Name:");
		nameText = new Text(composite, SWT.BORDER);
		nameText.addModifyListener(validator);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		nameText.setLayoutData(gd);

		label = new Label(composite, SWT.SEPARATOR | SWT.HORIZONTAL);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 3;
		label.setLayoutData(gd);

		if (hasOutputSection()) {
			createOutputSection(composite);
		}
		// language section
		label = new Label(composite, SWT.NONE);
		label.setText("Language:");
		Composite langSection = new Composite(composite, SWT.NONE);
		langSection.setLayout(new GridLayout(3, true));
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		langSection.setLayoutData(gd);
		String[] languages = AntlrLanguageTargetName.names();
		languageCombo = (Combo) addComboBox(langSection, "Language:", LANGUAGE,
				languages, languages, AntlrLanguageTargetName.Java.ordinal())[0];
		languageCombo.addModifyListener(validator);

		// Dependent section
		if (hasDependentSection()) {
			label = new Label(composite, SWT.NONE);
			label.setText("Dependent:");
			Button dependentButton = addCheckButton(composite, PROTOTYPE,
					"false", false);
			gd = new GridData(GridData.FILL_HORIZONTAL);
			gd.horizontalSpan = 2;
			dependentButton.setLayoutData(gd);
		}

		if (hasTokenVocabSection()) {
			createTokenVocabSection(composite);
		}

		setControl(composite);

		validate(getFolder(), getGrammarName(), getLanguage(), getTokenVocab());

		if (getFolder().length() > 0) {
			Display.getDefault().asyncExec(new Runnable() {

				public void run() {
					nameText.setFocus();
				}
			});
		}
	}

	protected boolean hasOutputSection() {
		return false;
	}

	protected void createTokenVocabSection(Composite composite) {
		Label label;
		GridData gd;

		label = new Label(composite, SWT.NONE);
		label.setText("Token Vocabulary:");
		tokenVocabText = new Text(composite, SWT.BORDER);
		tokenVocabText.addModifyListener(new ValidationListener());
		gd = new GridData(GridData.FILL_HORIZONTAL);
		tokenVocabText.setLayoutData(gd);
		tokenVocabButton = new Button(composite, SWT.PUSH);
		tokenVocabButton
				.addSelectionListener(new TokenVocabSelectionListener());
		tokenVocabButton.setText("    Browse...    ");
	}

	protected void createOutputSection(Composite composite) {
		Label label = new Label(composite, SWT.NONE);
		label.setText("Output:");
		Composite outputSection = new Composite(composite, SWT.NONE);
		outputSection.setLayout(new GridLayout(3, true));
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;

		outputSection.setLayoutData(gd);

		addRadioButton(outputSection,
				AntlrWizardMessages.NewGrammarFilePage_NoneOutput, OUTPUT,
				null, true);

		addRadioButton(outputSection,
				AntlrWizardMessages.NewGrammarFilePage_ASTOutput, OUTPUT,
				"AST", false);//$NON-NLS-1$

		addRadioButton(outputSection,
				AntlrWizardMessages.NewGrammarFilePage_TemplateOutput, OUTPUT,
				"template", false);//$NON-NLS-1$
	}

	protected Control[] addComboBox(Composite parent, String label, String key,
			String[] items, String[] values, int selected) {
		GridData gd = null;
		Combo combo = new Combo(parent, SWT.SINGLE);
		gd = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING);
		combo.setFont(parent.getFont());
		combo.setItems(items);
		combo.setLayoutData(gd);
		combo.select(selected);
		return new Control[] { combo };
	}

	protected Button addCheckButton(Composite parent, String key, String value,
			boolean selected) {
		Button checkbox = new Button(parent, SWT.CHECK);
		checkbox.setData(key, value);
		checkbox.setData(PROPERTY_NAME, key);
		checkbox.setSelection(selected);
		checkbox.addSelectionListener(new ProperyChangeListener());
		return checkbox;
	}

	protected Button addRadioButton(Composite parent, String label, String key,
			String value, boolean selected) {
		Button radio = new Button(parent, SWT.RADIO);
		radio.setData(key, value);
		radio.setData(PROPERTY_NAME, key);
		radio.setSelection(selected);
		radio.setText(label);
		radio.addSelectionListener(new ProperyChangeListener());
		return radio;
	}

	protected Composite createSubsection(Composite parent, String label,
			int align) {
		Group group = new Group(parent, SWT.SHADOW_NONE);
		group.setLayout(new FillLayout(align));
		group.setText(label);
		if (parent.getLayout() instanceof GridLayout) {
			GridData data = new GridData(SWT.FILL, SWT.CENTER, true, false);
			data.horizontalSpan = 3;
			group.setLayoutData(data);
		}
		return group;
	}

	public void init(IResource resource) {
		folder = null;
		if (resource != null) {
			if (resource instanceof IContainer) {
				folder = (IContainer) resource;
			} else {
				folder = resource.getParent();
			}
		}
	}

	protected void updateStatus(IStatus status) {
		setPageComplete(status.isOK());
		int severity = status.getSeverity();
		if (severity == IStatus.OK) {
			setMessage(status.getMessage(), IMessageProvider.NONE);
			setErrorMessage(null);
		} else {
			setMessage(null);
			setErrorMessage(status.getMessage());
		}
	}

	protected void validate(String folder, String name, String language,
			String tokenVocab) {
		enableTokenVocab(false);
		IStatus status = validateFolder(folder);
		if (status != null) {
			updateStatus(status);
			return;
		}
		status = validateGrammar(folder, name);
		if (status != null) {
			updateStatus(status);
			return;
		}
		status = validateLanguage(getLanguage());
		if (status != null) {
			updateStatus(status);
			return;
		}
		boolean dependent = Boolean.valueOf(getProperty(PROTOTYPE))
				.booleanValue();
		if (!dependent) {
			enableTokenVocab(true);
			status = validateTokenVocab(tokenVocab);
			if (status != null) {
				updateStatus(status);
				return;
			}
		}
		updateStatus(new StatusInfo(StatusInfo.OK, getPageDescription()));
	}

	private void enableTokenVocab(boolean enabled) {
		if (hasTokenVocabSection()) {
			tokenVocabButton.setEnabled(enabled);
			tokenVocabText.setEditable(enabled);
		}
	}

	protected boolean hasTokenVocabSection() {
		return false;
	}

	protected IStatus validateTokenVocab(String tokenVocab) {
		if (hasTokenVocabSection()) {
			if (tokenVocab == null || tokenVocab.length() == 0) {
				return createErrorStatus("Token Vocabulary is empty");
			}
			IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
			IFile tokenVocabFile = root.getFile(new Path(tokenVocab));
			if (tokenVocabFile == null || !tokenVocabFile.exists()) {
				return createErrorStatus("Token Vocabulary does not exist");
			}
		}
		return null;
	}

	protected IStatus validateFolder(String folder) {
		if (folder == null || folder.length() == 0) {
			return createErrorStatus("Container is empty");
		}
		IContainer container = getContainer(folder);
		if (container == null || !container.exists()) {
			return createErrorStatus("Container does not exist");
		}

		return null;
	}

	protected IStatus validateLanguage(String language) {
		if (language == null || language.length() == 0) {
			return createErrorStatus("Language is empty");
		}
		return null;
	}

	private String getLanguage() {
		return languageCombo.getText();
	}

	private IContainer getContainer(String path) {
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		IPath containerPath = new Path(path);
		if (containerPath.segmentCount() == 1) {
			// is a project
			String projectName = containerPath.lastSegment();
			return root.getProject(projectName);
		}
		return root.getFolder(containerPath);
	}

	protected IStatus validateGrammar(String folder, String name) {
		if (name == null || name.length() == 0) {
			return createErrorStatus(AntlrWizardMessages.NewGrammarFilePage_Error_EmptyGrammarName);
		} else {
			if (!AntlrTextHelper.isGrammarName(name)) {
				return createErrorStatus(MessageFormat
						.format(
								AntlrWizardMessages.NewGrammarFilePage_Error_InvalidGrammarName,
								name));
			}
			IPath path = new Path(folder).append(name).addFileExtension("g");
			IWorkspaceRoot ws = ResourcesPlugin.getWorkspace().getRoot();
			IFile file = ws.getFile(path);
			if (file.exists()) {
				return createErrorStatus(MessageFormat
						.format(
								AntlrWizardMessages.NewGrammarFilePage_Error_GrammarAlreadyExist,
								name));
			}
		}
		return null;
	}

	protected IStatus createErrorStatus(String message) {
		StatusInfo status = new StatusInfo(StatusInfo.ERROR, message);
		return status;
	}

	private String getFolder() {
		return folderText.getText();
	}

	private String getTokenVocab() {
		if (hasTokenVocabSection())
			return tokenVocabText.getText();
		return null;
	}

	private String getGrammarName() {
		return nameText.getText();
	}

	protected String resourceToString(IResource resource) {
		if (resource == null) {
			return "";
		}
		return resourceToString(resource.getFullPath());
	}

	protected String resourceToString(IPath fullpath) {
		if (fullpath == null) {
			return "";
		}
		return fullpath.makeRelative().toString();
	}

	public final IFile createGrammar(IProgressMonitor monitor) {
		InputStream contents = null;
		try {
			IContainer folder = getContainer(getFolder());
			IFile file = folder
					.getFile(new Path(getGrammarName())
							.addFileExtension(AntlrConstants.ANTLR_GRAMMAR_FILE_EXTENSION));
			// set all options
			setProperty(GRAMMAR_NAME, getGrammarName());
			setProperty(AntlrConstants.ANTLR_GRAMMAR_TYPE, getGrammarType());
			setProperty(LANGUAGE, getLanguage());
			setProperty(FILE_PATH, file.getFullPath().toString());
			setProperty("tokenVocab", getTokenVocab());
			contents = new ByteArrayInputStream(getContents().getBytes());
			file.create(contents, IResource.FORCE, monitor);
			file.setPersistentProperty(AntlrConstants.Q_ANTLR_GRAMMAR_TYPE,
					getGrammarType());
			fileCreated(file);
			return file;
		} catch (CoreException ex) {
			AntlrCore.error(ex);
		} finally {
			if (contents != null) {
				try {
					contents.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}
		return null;
	}

	protected abstract String getGrammarType();

	protected void fileCreated(IFile file) throws CoreException {
		if (hasDependentSection()) {
			String prototype = Boolean.valueOf(getProperty(PROTOTYPE))
					.toString();
			file.setPersistentProperty(AntlrBuildUnit.DEPENDENT_GRAMMAR,
					prototype);
		}
	}

	protected boolean hasDependentSection() {
		return true;
	}

	public void openGrammar(final IFile resource) {
		final IWorkbenchPage activePage = DLTKUIPlugin.getActivePage();
		if (activePage != null) {
			final Display display = getShell().getDisplay();
			if (display != null) {
				display.syncExec(new Runnable() {
					public void run() {
						try {
							IDE.openEditor(activePage, resource, true);
						} catch (PartInitException e) {
							DLTKUIPlugin.log(e);
						}
					}
				});
			}
		}
	}

	protected abstract String getContents();
}
