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

import org.deved.antlride.common.ui.AntlrImages;
import org.deved.antlride.core.AntlrConstants;
import org.deved.antlride.core.AntlrCore;
import org.deved.antlride.core.resources.AntlrLanguageTarget;
import org.deved.antlride.core.resources.AntlrLanguageTargetRepository;
import org.deved.antlride.core.resources.AntlrPackage;
import org.deved.antlride.core.resources.AntlrPackages;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.dltk.ui.preferences.AbstractOptionsBlock;
import org.eclipse.dltk.ui.preferences.PreferenceKey;
import org.eclipse.dltk.ui.util.IStatusChangeListener;
import org.eclipse.dltk.ui.util.PixelConverter;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.preferences.IWorkbenchPreferenceContainer;

@SuppressWarnings("restriction")
public class AntlrBuilderBlock extends AbstractOptionsBlock {

	private class AntlrLanguageLabelProvider extends LabelProvider {

		@Override
		public String getText(Object element) {
			return super.getText(element);
		}

		@Override
		public Image getImage(Object element) {
			return AntlrImages.getImage(AntlrImages.TARGET_LANGUAGE);
		}
	}

	private class AntlrLanguageTargetContentProvider implements
			IStructuredContentProvider {

		public Object[] getElements(Object inputElement) {
			AntlrPackage ap = (AntlrPackage) inputElement;
			return ap == null ? new Object[0] : ap.getLanguageTargets();
		}

		public void dispose() {

		}

		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}

	}

	private class AntlrPackageSelectionChangedListener implements
			ISelectionChangedListener {

		public void selectionChanged(SelectionChangedEvent event) {
			AntlrPackage ap = null;
			IStructuredSelection selection = (IStructuredSelection) event
					.getSelection();
			if (!selection.isEmpty()) {
				ap = (AntlrPackage) selection.getFirstElement();
				langViewer.setInput(ap);
				AntlrLanguageTarget[] targets = ap.getLanguageTargets();
				langViewer.setSelection(new StructuredSelection(targets[0]));
			} else {
				langViewer.setInput(ap);
			}
		}
	}

	private class AntlrLanguageSelectionChangedListener implements
			ISelectionChangedListener {

		public void selectionChanged(SelectionChangedEvent event) {
			IStructuredSelection selection = (IStructuredSelection) event
					.getSelection();
			removeButton.setEnabled(!selection.isEmpty());
		}
	}

	private class RemoveCustomLanguageTarget implements SelectionListener {
		public void widgetSelected(SelectionEvent e) {
			IStructuredSelection selection = (IStructuredSelection) custLangViewer
					.getSelection();
			if (!selection.isEmpty()) {
				AntlrLanguageTargetRepository
						.delete((AntlrLanguageTarget) selection
								.getFirstElement());
				custLangViewer.setInput(AntlrLanguageTargetRepository.list());
			}
		}

		public void widgetDefaultSelected(SelectionEvent e) {

		}
	}
	
	private class AddCustomLanguageTarget implements SelectionListener {
		public void widgetSelected(SelectionEvent e) {
			AntlrLanguageTargetDialog dialog = new AntlrLanguageTargetDialog(
					getShell(), "Add language target");
			IStructuredSelection ss = (IStructuredSelection) packageViewer
					.getSelection();
			dialog.setInput((AntlrPackage) ss.getFirstElement());
			dialog.open();
			if (dialog.getLanguageTarget() != null)
				custLangViewer.setInput(AntlrLanguageTargetRepository
						.list());
		}

		public void widgetDefaultSelected(SelectionEvent e) {
		}
	}

	private AntlrPackages packages;

	private TableViewer packageViewer;

	private TableViewer langViewer;

	private TableViewer custLangViewer;

	private Button removeButton;

	private final static PreferenceKey RUNTIME = new PreferenceKey(
			AntlrCore.PLUGIN_ID, AntlrConstants.ANTLR_BUILDER_RUNTIME);

	private final static PreferenceKey[] keys = { RUNTIME };

	private static final IStatus NO_RUNTIME = new Status(IStatus.ERROR,
			AntlrCore.PLUGIN_ID,
			"No package defined. Please add an ANTLR package.");

	private static final IStatus NO_PROBLEM = new Status(IStatus.OK,
			AntlrCore.PLUGIN_ID, "Builder");

	public AntlrBuilderBlock(IStatusChangeListener context, IProject project,
			IWorkbenchPreferenceContainer container) {
		super(context, project, keys, container);
	}

	@Override
	protected Control createOptionsBlock(Composite parent) {

		packages = AntlrPackages.getInstance();

		SashForm form = new SashForm(parent, SWT.VERTICAL);

		createPackagePanel(form);

		createLanguagePanel(form);

		form.setWeights(new int[] { 35, 65 });

		updateInput();

		return form;
	}

	private void createLanguagePanel(Composite parent) {
		Composite optionsParent = createSubsection(parent, "Languages:");

		GridLayout layout = new GridLayout(4, false);
		optionsParent.setLayout(layout);

		Label label = new Label(optionsParent, SWT.NONE);
		GridData gd = new GridData();
		gd.verticalAlignment = SWT.BEGINNING;
		gd.horizontalSpan = 2;
		label.setText("Available language targets");
		label.setLayoutData(gd);

		label = new Label(optionsParent, SWT.NONE);
		gd = new GridData();
		gd.verticalAlignment = SWT.BEGINNING;
		gd.horizontalSpan = 2;
		label.setText("Custom language targets");
		label.setLayoutData(gd);

		Table table = new Table(optionsParent, SWT.SINGLE | SWT.FULL_SELECTION
				| SWT.BORDER);
		gd = new GridData(GridData.FILL_BOTH);
		gd.horizontalSpan = 2;
		table.setLayoutData(gd);

		Composite composite = new Composite(optionsParent, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		gd = new GridData(GridData.FILL_BOTH);
		gd.horizontalSpan = 2;
		composite.setLayoutData(gd);
		Table table2 = new Table(composite, SWT.SINGLE | SWT.FULL_SELECTION
				| SWT.BORDER);
		gd = new GridData(GridData.FILL_BOTH);
		gd.horizontalSpan = 2;
		table2.setLayoutData(gd);

		Button addButton = new Button(composite, SWT.PUSH);
		addButton.setText("Add...");
		addButton.setToolTipText("Add language target");
		addButton.addSelectionListener(new AddCustomLanguageTarget());
		gd = new GridData();
		gd.widthHint = 100;
		addButton.setLayoutData(gd);

		removeButton = new Button(composite, SWT.PUSH);
		removeButton.setText("Remove");
		removeButton.setToolTipText("Remove target language");
		removeButton.setEnabled(false);
		removeButton.addSelectionListener(new RemoveCustomLanguageTarget());
		gd = new GridData();
		gd.widthHint = 100;
		removeButton.setLayoutData(gd);

		langViewer = new TableViewer(table);
		langViewer.setContentProvider(new AntlrLanguageTargetContentProvider());
		langViewer.setLabelProvider(new AntlrLanguageLabelProvider());

		custLangViewer = new TableViewer(table2);
		custLangViewer.setContentProvider(new ArrayContentProvider());
		custLangViewer.setLabelProvider(new AntlrLanguageLabelProvider());
		custLangViewer
				.addSelectionChangedListener(new AntlrLanguageSelectionChangedListener());
		custLangViewer.setInput(AntlrLanguageTargetRepository.list());
	}

	@Override
	protected boolean processChanges(IWorkbenchPreferenceContainer container) {
		try {
			packages.save();
		} catch (IOException e) {
			AntlrCore.error("Can't save packages", e);
			return false;
		}
		AntlrPackage selectedPackage = packages.getSelectedPackage();
		if (selectedPackage != null) {
			setString(RUNTIME, selectedPackage.getVersion());
		}
		return super.processChanges(container);
	}

	private Composite createPackagePanel(Composite parent) {
		Composite composite = createSubsection(parent,
				AntlrPreferenceMessages.Builder_Packages);

		GridLayout layout = new GridLayout(2, false);

		layout.verticalSpacing = 10;

		composite.setLayout(layout);

		packageViewer = new TableViewer(composite, SWT.CHECK | SWT.BORDER
				| SWT.V_SCROLL | SWT.H_SCROLL | SWT.SINGLE | SWT.FULL_SELECTION);
		final Table table = packageViewer.getTable();
		table.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				if (event.detail == SWT.CHECK) {
					updateSelection();
					TableItem item = (TableItem) event.item;
					AntlrPackage ap = (AntlrPackage) item.getData();
					item.setChecked(true);
					ap.setSelected(true);
				}
			}
		});
		GridData gd = new GridData(GridData.FILL_BOTH);
		// gd.verticalSpan = 10;
		gd.horizontalSpan = 2;
		table.setLayoutData(gd);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		packageViewer.setUseHashlookup(true);
		int[] widths = { 75, 250 };
		for (int i = 0; i < AntlrPackage.PROPERTIES.length; i++) {
			TableColumn tableColumn = new TableColumn(table, SWT.LEFT);
			tableColumn.setText(AntlrPackage.PROPERTIES[i]);
			tableColumn.setWidth(widths[i]);
		}
		packageViewer.setLabelProvider(new PackageLabelProvider());
		packageViewer.setContentProvider(new ArrayContentProvider());

		Button addButton = new Button(composite, SWT.NONE);
		gd = new GridData();
		gd.widthHint = 100;
		addButton.setLayoutData(gd);
		addButton.setText(AntlrPreferenceMessages.Builder_Package_Add);
		addButton.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				Shell shell = new Shell(getShell());
				AntlrPackageDialog dialog = new AntlrPackageDialog(shell,
						packages, "Add ANTLR Package");
				dialog.open();
				AntlrPackage selectedPackage = dialog.getSelectedPackage();
				if (selectedPackage != null) {
					packages.register(selectedPackage);
					AntlrPackage[] pkgs = packages.getPackages();
					updateInput();
					if (pkgs.length == 1) {
						table.getItem(0).setChecked(true);
					}
				}
				dialog = null;
			}

			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});

		final Button removeButton = new Button(composite, SWT.NONE);
		gd = new GridData();
		gd.widthHint = 100;
		removeButton.setLayoutData(gd);
		removeButton.setText(AntlrPreferenceMessages.Builder_Package_Remove);
		removeButton.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				int selectionIndex = table.getSelectionIndex();
				if (selectionIndex >= 0) {
					TableItem item = table.getItem(selectionIndex);
					AntlrPackage ap = (AntlrPackage) item.getData();
					packages.unregister(ap);
					updateInput();
					AntlrPackage[] pkgs = packages.getPackages();
					if (pkgs.length > 0) {
						AntlrPackage selectedPackage = packages
								.getSelectedPackage();
						if (selectedPackage == null) {
							selectedPackage = pkgs[0];
							selectedPackage.setSelected(true);
						}
						int index = packages.getSelectedPackageIndex();
						table.getItem(index).setChecked(true);
					}
				}
			}

			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});

		packageViewer
				.addSelectionChangedListener(new AntlrPackageSelectionChangedListener());

		return composite;
	}

	private void updateInput() {
		AntlrPackage[] pkgs = this.packages.getPackages();
		if (pkgs.length == 0) {
			statusChanged(NO_RUNTIME);
		} else {
			statusChanged(NO_PROBLEM);
		}
		packageViewer.setInput(pkgs);
		if (pkgs.length > 0) {
			packageViewer.setSelection(new StructuredSelection(packages
					.getSelectedPackage()));
		} else {
			packageViewer.setSelection(StructuredSelection.EMPTY);
		}
		int selectedIndex = packages.getSelectedPackageIndex();
		if (selectedIndex >= 0) {
			packageViewer.getTable().getItem(selectedIndex).setChecked(true);
		}
	}

	@Override
	public void dispose() {
		super.dispose();
	}

	private void updateSelection() {
		Table table = packageViewer.getTable();
		for (int i = 0; i < table.getItemCount(); i++) {
			TableItem titem = table.getItem(i);
			titem.setChecked(false);
			AntlrPackage ap = (AntlrPackage) titem.getData();
			ap.setSelected(false);
		}
	}

	protected Control[] addLabelledTextField(Composite parent, String label,
			String option, PreferenceKey key, int textLimit, int indentation,
			int align) {

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

	private Composite createSubsection(Composite parent, String label) {
		Group group = new Group(parent, SWT.SHADOW_NONE);
		group.setText(label);
		GridData data = new GridData(SWT.FILL, SWT.CENTER, true, false);
		group.setLayoutData(data);
		return group;
	}

	private class PackageLabelProvider extends LabelProvider implements
			ITableLabelProvider {

		public Image getColumnImage(Object element, int columnIndex) {
			if (columnIndex == 0) {
				return AntlrImages.getImage(AntlrImages.PACKAGE);
			}
			return null;
		}

		public String getColumnText(Object element, int columnIndex) {
			AntlrPackage ap = (AntlrPackage) element;
			if (columnIndex == 0) {
				return ap.getVersion();
			} else if (columnIndex == 1) {
				return ap.getDescription();
			}
			return "";
		}
	}
}
