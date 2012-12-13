/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.internal.ui.editor;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Pattern;

import org.deved.antlride.common.ui.AntlrImages;
import org.deved.antlride.core.AntlrCore;
import org.deved.antlride.core.AntlrLanguageTargetName;
import org.deved.antlride.core.build.AntlrBuildUnit;
import org.deved.antlride.core.build.AntlrBuildUnitRepository;
import org.deved.antlride.core.interpreter.AntlrInterpreter;
import org.deved.antlride.core.interpreter.DefaultAntlrInterpreter;
import org.deved.antlride.core.launch.AntlrLauncher;
import org.deved.antlride.core.model.IGrammar;
import org.deved.antlride.core.model.IRule;
import org.deved.antlride.core.model.ast.ModelElementQuery;
import org.deved.antlride.core.model.evaluation.AntlrResultListener;
import org.deved.antlride.core.model.evaluation.IEvalElement;
import org.deved.antlride.core.model.evaluation.IResultEvalElement;
import org.deved.antlride.core.model.test.AntlrTestCase;
import org.deved.antlride.core.model.test.AntlrTestSuite;
import org.deved.antlride.internal.ui.views.interpreter.AntlrInterpreterMessages;
import org.deved.antlride.ui.AntlrUIHelper;
import org.deved.antlride.ui.text.AntlrInputSourceViewer;
import org.deved.antlride.ui.text.AntlrTextSelection;
import org.deved.antlride.ui.views.interpreter.EvalElementViewer;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuCreator;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.services.IDisposable;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;

public class AntlrInterpreterPage extends Viewer implements IDisposable {

	private class DropDownMenu extends Action implements IMenuCreator {

		private Menu fMenu;

		private Action[] fDelegates;

		public DropDownMenu(Action... delegates) {
			super(delegates[0].getText(),
					delegates.length > 1 ? IAction.AS_DROP_DOWN_MENU
							: IAction.AS_PUSH_BUTTON);
			setImageDescriptor(delegates[0].getImageDescriptor());
			setToolTipText(delegates[0].getToolTipText());
			setMenuCreator(this);
			fDelegates = delegates;
		}

		public void dispose() {
			if (fMenu != null)
				fMenu.dispose();
			fDelegates = null;
		}

		public Menu getMenu(Control parent) {
			fMenu = new Menu(parent);

			for (int i = 1; i < fDelegates.length; i++) {
				ActionContributionItem item = new ActionContributionItem(
						fDelegates[i]);
				item.fill(fMenu, -1);
			}

			return fMenu;
		}

		public Menu getMenu(Menu parent) {
			return null;
		}

		@Override
		public void run() {
			fDelegates[0].run();
		}

		@Override
		public void setEnabled(boolean enabled) {
			for (int i = 1; i < fDelegates.length; i++) {
				fDelegates[i].setEnabled(enabled);
			}
			// super.setEnabled(enabled);
		}

		public void setEnabledAll(boolean enabled) {
			for (int i = 0; i < fDelegates.length; i++) {
				fDelegates[i].setEnabled(enabled);
			}
			super.setEnabled(enabled);
		}
	}

	private class ArrayContentProvider implements ITreeContentProvider {

		public Object[] getChildren(Object parentElement) {
			return null;
		}

		public Object getParent(Object element) {
			return null;
		}

		public boolean hasChildren(Object element) {
			return false;
		}

		public Object[] getElements(Object element) {
			return (Object[]) element;
		}

		public void dispose() {
		}

		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}
	}

	private class RunAction extends Action {

		public RunAction() {
			setToolTipText(AntlrInterpreterMessages.GrammarInterpreter_Run);
			setImageDescriptor(AntlrImages.getDescriptor(AntlrImages.RUN));
		}

		@Override
		public void run() {
			runTest(sourceViewer.getTextWidget().getText());
		}
	}

	private class RunJavaAction extends Action {

		public RunJavaAction() {
			setText("Run (Java)");
			setToolTipText("Run (Java)");
			setImageDescriptor(AntlrImages.getDescriptor(AntlrImages.RUN_JAVA));
		}

		@Override
		public void run() {
			launch(AntlrLanguageTargetName.Java, "run");
		}
	}

	private class DebugJavaAction extends Action {

		public DebugJavaAction() {
			setText("Debug (Java)");
			setToolTipText("Debug (Java)");
			setImageDescriptor(AntlrImages
					.getDescriptor(AntlrImages.DEBUG_JAVA));
		}

		@Override
		public void run() {
			launch(AntlrLanguageTargetName.Java, "debug");
		}
	}

	private class NewTestCaseAction extends Action {
		public NewTestCaseAction() {
			setToolTipText("New test case");
			setImageDescriptor(AntlrImages
					.getDescriptor(AntlrImages.NEW_TEST_CASE));
		}

		@Override
		public void run() {
			selectTestCase(null);
		}
	}

	private class SaveTestCaseAction extends Action {

		public SaveTestCaseAction() {
			setToolTipText("Save test case");
			setImageDescriptor(AntlrImages
					.getDescriptor(AntlrImages.SAVE_TEST_CASE));
		}

		@Override
		public void run() {
			String input = (String) ((Document) sourceViewer.getInput()).get();
			if (selectedTestCase == null) {
				InputDialog dlg = new InputDialog(getControl().getShell(),
						"Save test case", "Name:", "", new IInputValidator() {
							public String isValid(String text) {
								boolean matches = Pattern.matches(
										"[a-zA-Z][a-zA-Z_0-9\\-]*", text);
								if (!matches)
									return "Invalid test name";
								return testSuite.exists(selectedRule
										.getElementName(), text) ? "Duplicated test name"
										: null;
							}
						});
				if (dlg.open() == InputDialog.OK) {
					selectedTestCase = testSuite.create(selectedRule
							.getElementName(), dlg.getValue(), input);
				}
			}
			if (selectedTestCase != null) {
				try {
					testSuite.save(selectedTestCase, input);
					AntlrTestCase[] list = getTestList(selectedRule);
					testViewer.setInput(list);
					testViewer.setSelection(new StructuredSelection(
							selectedTestCase));
					runTest(sourceViewer.getTextWidget().getText());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	private Control control;

	private TreeViewer ruleViewer;

	private Text ruleNameText;

	private IGrammar grammar;

	private Text testNameText;

	private TreeViewer testViewer;

	private IRule selectedRule;

	private Label description;

	private AntlrInputSourceViewer sourceViewer;

	private AntlrTestCase selectedTestCase;

	private AntlrTestSuite testSuite;

	private AntlrInterpreter interpreter;

	private EvalElementViewer evalViewer;

	private ToolItem clearRuleFilter;

	private ToolItem clearTestFilter;

	private boolean rebuild;

	private AntlrMultiPageEditor pageEditor;

	private DropDownMenu dropDownMenu;

	private boolean nativeLaunchersEnabled;

	private NewTestCaseAction newTestCaseAction;

	private SaveTestCaseAction saveTestCaseAction;

	public AntlrInterpreterPage(AntlrMultiPageEditor pageEditor,
			Composite composite) {
		control = createControl(composite);
		this.pageEditor = pageEditor;
	}

	private void createRulePanel(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout(3, false));
		Label label = new Label(container, SWT.NONE);
		label.setText("Rule:");
		label.setToolTipText("Search Rule");
		ruleNameText = new Text(container, SWT.BORDER);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		ruleNameText.setLayoutData(gd);
		ruleNameText.setToolTipText("Search Rule");
		ruleNameText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				setRules(grammar);
			}
		});
		ToolBar bar = new ToolBar(container, SWT.HORIZONTAL);
		clearRuleFilter = new ToolItem(bar, SWT.PUSH);
		clearRuleFilter.setToolTipText("Clear");
		clearRuleFilter.setImage(AntlrImages.getImage(AntlrImages.CLEAR));
		clearRuleFilter.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				ruleNameText.setText("");
				setRules(grammar);
			}

			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});

		ruleViewer = new TreeViewer(container, SWT.SINGLE | SWT.FULL_SELECTION);
		gd = new GridData(GridData.FILL_BOTH);
		gd.horizontalSpan = 3;
		ruleViewer.setUseHashlookup(true);
		ruleViewer.getControl().setLayoutData(gd);
		ruleViewer.addFilter(new ViewerFilter() {
			@Override
			public boolean select(Viewer viewer, Object parentElement,
					Object element) {
				IRule rule = (IRule) element;
				String startWith = ruleNameText.getText();
				if (startWith.length() > 0) {
					return rule.getElementName().startsWith(startWith);
				}
				return true;
			}
		});
		ruleViewer.setContentProvider(new ArrayContentProvider());
		ruleViewer.setLabelProvider(new LabelProvider() {
			@Override
			public String getText(Object element) {
				return ((IRule) element).getElementName();
			}

			@Override
			public Image getImage(Object element) {
				return AntlrImages.getImage(AntlrImages.RULE);
			}
		});
		ruleViewer.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				IStructuredSelection selection = (IStructuredSelection) event
						.getSelection();
				IRule rule = null;
				if (!selection.isEmpty()) {
					rule = (IRule) selection.getFirstElement();
				}
				AntlrTestCase[] list = getTestList(rule);
				testViewer.setInput(list);
				updateRuleSelection(rule);
				dropDownMenu.setEnabled(false);
			}
		});
	}

	private void createTestPanel(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout(3, false));
		Label label = new Label(container, SWT.NONE);
		label.setText("Test:");
		label.setToolTipText("Search Test");
		testNameText = new Text(container, SWT.BORDER);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		testNameText.setLayoutData(gd);
		testNameText.setToolTipText("Search Test");
		testNameText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				testViewer.setInput(getTestList(selectedRule));
				selectTest(0);
			}
		});
		ToolBar bar = new ToolBar(container, SWT.HORIZONTAL);
		clearTestFilter = new ToolItem(bar, SWT.PUSH);
		clearTestFilter.setToolTipText("Clear");
		clearTestFilter.setImage(AntlrImages.getImage(AntlrImages.CLEAR));
		clearTestFilter.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				testNameText.setText("");
				testViewer.setInput(getTestList(selectedRule));
			}

			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});

		testViewer = new TreeViewer(container, SWT.SINGLE | SWT.FULL_SELECTION);
		testViewer.setLabelProvider(new LabelProvider() {
			@Override
			public String getText(Object element) {
				AntlrTestCase testCase = (AntlrTestCase) element;
				return testCase.getName();
			}
		});
		testViewer.addFilter(new ViewerFilter() {
			@Override
			public boolean select(Viewer viewer, Object parentElement,
					Object element) {
				AntlrTestCase testcase = (AntlrTestCase) element;
				String startWith = testNameText.getText();
				return testcase.getName().startsWith(startWith);
			}
		});
		testViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				ISelection selection = event.getSelection();
				AntlrTestCase testCase = null;
				if (!selection.isEmpty()) {
					testCase = (AntlrTestCase) ((IStructuredSelection) selection)
							.getFirstElement();
				}
				selectTestCase(testCase);
			}
		});
		testViewer.setContentProvider(new ArrayContentProvider());

		gd = new GridData(GridData.FILL_BOTH);
		gd.horizontalSpan = 3;
		testViewer.getControl().setLayoutData(gd);
	}

	protected Control createControl(Composite parent) {
		SashForm mainPanel = new SashForm(parent, SWT.HORIZONTAL);

		// rulePanel | inputPanel
		// ----------|-----------
		// testPanel | graphPanel
		SashForm leftPanel = new SashForm(mainPanel, SWT.VERTICAL);
		createRulePanel(leftPanel);
		createTestPanel(leftPanel);

		SashForm rightPanel = new SashForm(mainPanel, SWT.VERTICAL);
		createInputPanel(rightPanel);
		createGraphPanel(rightPanel);

		rightPanel.setWeights(new int[] { 30, 70 });

		mainPanel.setWeights(new int[] { 20, 80 });

		return mainPanel;
	}

	private void createGraphPanel(Composite parent) {
		GridData gd;

		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(1, true));

		ToolBar bar = new ToolBar(composite, SWT.FLAT | SWT.RIGHT
				| SWT.HORIZONTAL);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalAlignment = SWT.END;
		bar.setLayoutData(gd);

		evalViewer = new EvalElementViewer(composite);
		gd = new GridData(GridData.FILL_BOTH);
		evalViewer.getControl().setLayoutData(gd);
		evalViewer.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				AntlrUIHelper.select(sourceViewer.getTextWidget(),
						(AntlrTextSelection) event.getSelection());

			}
		});

		// add actions
		IToolBarManager barManager = new ToolBarManager(bar);
		barManager.add(evalViewer.getAction(EvalElementViewer.ZOOM_IN_ACTION));
		barManager.add(evalViewer.getAction(EvalElementViewer.ZOOM_OUT_ACTION));
		barManager.add(evalViewer
				.getAction(EvalElementViewer.CLEAR_DIAGRAM_ACTION));
		barManager.add(new Separator());
		barManager.add(evalViewer.getAction(EvalElementViewer.EXPORT_ACTION));
		barManager.update(true);
	}

	private void createInputPanel(Composite parent) {
		GridData gd;
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));

		description = new Label(composite, SWT.NONE);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		description.setLayoutData(gd);

		ToolBar bar = new ToolBar(composite, SWT.FLAT | SWT.RIGHT
				| SWT.HORIZONTAL);

		Collection<Action> dropdownActions = new ArrayList<Action>(3);

		RunAction run = new RunAction();
		dropdownActions.add(run);
		AntlrLauncher[] launchers = getLaunchers(AntlrLanguageTargetName.Java,
				null);
		if (launchers.length > 0) {
			dropdownActions.add(new RunJavaAction());
			dropdownActions.add(new DebugJavaAction());
		}
		// DropDownMenu
		ToolBarManager barManager = new ToolBarManager(bar);

		dropDownMenu = new DropDownMenu(dropdownActions
				.toArray(new Action[dropdownActions.size()]));
		barManager.add(dropDownMenu);

		barManager.add(new Separator());

		newTestCaseAction = new NewTestCaseAction();
		barManager.add(newTestCaseAction);

		saveTestCaseAction = new SaveTestCaseAction();
		barManager.add(saveTestCaseAction);

		barManager.update(true);

		sourceViewer = new AntlrInputSourceViewer(composite, true);
		gd = new GridData(GridData.FILL_BOTH);
		gd.horizontalSpan = 2;
		sourceViewer.getControl().setLayoutData(gd);
		sourceViewer.getTextWidget().addKeyListener(new KeyListener() {

			public void keyReleased(KeyEvent e) {
			}

			public void keyPressed(KeyEvent e) {
				if (e.keyCode == SWT.F5) {
					runTest(sourceViewer.getTextWidget().getText());
				}
			}
		});
	}

	private AntlrLauncher[] getLaunchers(AntlrLanguageTargetName language,
			String filter) {
		try {
			Bundle bundle = AntlrCore.getDefault().getBundle();
			BundleContext bundleContext = bundle.getBundleContext();
			ServiceReference[] serviceReferences = bundleContext
					.getServiceReferences(AntlrLauncher.class.getName(), filter);
			if (serviceReferences != null) {
				AntlrLauncher[] launchers = new AntlrLauncher[serviceReferences.length];
				for (int i = 0; i < launchers.length; i++) {
					launchers[i] = (AntlrLauncher) bundleContext
							.getService(serviceReferences[i]);
				}
				return launchers;
			}
		} catch (InvalidSyntaxException e) {
			e.printStackTrace();
		}
		return new AntlrLauncher[0];
	}

	private void launch(AntlrLanguageTargetName language, String mode) {
		try {
			evalViewer.clear();
			AntlrLauncher[] launchers = getLaunchers(language, "(mode=" + mode
					+ ")");
			AntlrBuildUnit unit = AntlrBuildUnitRepository.create(grammar);
			launchers[0].setResultListener(new AntlrResultListener() {

				public void setResult(IEvalElement input) {
					evalViewer.setInput(input);
				}
			});
			if ("debug".equals(mode)) {
				pageEditor.setActivePage(0);
			}
			launchers[0].launch(rebuild, unit, selectedTestCase);
			if (rebuild) {
				rebuild = false;
			}
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}

	private void runTest(String text) {
		IResultEvalElement result = interpreter.interpret(selectedRule, text);
		IEvalElement evalElement = result.getResult();
		evalViewer.setInput(evalElement);
	}

	@Override
	public Control getControl() {
		return control;
	}

	public void dispose() {
		if (interpreter != null)
			interpreter.endSession();
		if (sourceViewer != null)
			sourceViewer.dispose();
		if (evalViewer != null)
			evalViewer.dispose();
	}

	@Override
	public Object getInput() {
		return null;
	}

	@Override
	public ISelection getSelection() {
		return null;
	}

	@Override
	public void refresh() {
	}

	@Override
	public void setInput(Object input) {
		rebuild = false;
		if (grammar != input) {
			grammar = (IGrammar) input;
			enabled(!grammar.isTreeParserGrammar());
			rebuild = true;
			sourceViewer.setInput("");
			sourceViewer.setGrammar(grammar);
			nativeLaunchersEnabled = grammar.isCombinedGrammar()
					|| grammar.isParserGrammar();
			evalViewer.clear();
			if (interpreter != null) {
				interpreter.endSession();
			}
			ProgressMonitorDialog dlg = new ProgressMonitorDialog(getControl()
					.getShell());
			try {
				dlg.run(true, false, new IRunnableWithProgress() {
					public void run(IProgressMonitor monitor)
							throws InvocationTargetException,
							InterruptedException {
						interpreter = new DefaultAntlrInterpreter();
						final IStatus status = interpreter.beginSession(
								monitor, grammar);
						if (status.isOK()) {
							testSuite = interpreter.getTestSuite();
							Display.getDefault().asyncExec(new Runnable() {
								public void run() {
									setRules(grammar);
								}
							});
						} else {
							Display.getDefault().asyncExec(new Runnable() {
								public void run() {
									enabled(false);
									updateRuleSelection(null);
									ErrorDialog.openError(getControl()
											.getShell(), "Interpreter",
											"The interpreter is disabled",
											status);
								}
							});
						}
					}
				});
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void enabled(boolean enabled) {
		ruleNameText.setEditable(enabled);
		testNameText.setEditable(enabled);
		clearRuleFilter.setEnabled(enabled);
		clearTestFilter.setEnabled(enabled);
		newTestCaseAction.setEnabled(enabled);
		saveTestCaseAction.setEnabled(enabled);
		dropDownMenu.setEnabledAll(enabled);
	}

	private void setRules(IGrammar grammar) {
		IRule[] rules = ModelElementQuery.collectRules(grammar, true);
		ruleViewer.setInput(rules);
		if (rules.length > 0) {
			selectRule(0);
		}
	}

	private void selectTestCase(AntlrTestCase testCase) {
		StringBuilder buffer = new StringBuilder();
		sourceViewer.setInput("");
		evalViewer.clear();
		if (selectedRule != null) {
			buffer.append(selectedRule.getElementName());
		}
		try {
			if (testCase != null) {
				sourceViewer.setInput(testCase.getInput());
				buffer.append(" :: ");
				buffer.append(testCase.getName());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		dropDownMenu.setEnabled(testCase != null && nativeLaunchersEnabled);
		description.setText(buffer.toString());
		this.selectedTestCase = testCase;
	}

	private AntlrTestCase[] getTestList(IRule rule) {
		if (rule != null) {
			try {
				AntlrTestCase[] list = testSuite.list(rule.getElementName());
				return list;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return new AntlrTestCase[0];
	}

	private void selectRule(int i) {
		TreeItem[] items = ruleViewer.getTree().getItems();
		if (i >= 0 && i < items.length) {
			IRule rule = (IRule) items[i].getData();
			ruleViewer.setSelection(new StructuredSelection(rule));
		} else {
			updateRuleSelection(null);
		}
	}

	private void selectTest(int i) {
		TreeItem[] items = testViewer.getTree().getItems();
		if (i >= 0 && i < items.length) {
			AntlrTestCase testcase = (AntlrTestCase) items[i].getData();
			testViewer.setSelection(new StructuredSelection(testcase));
		}
	}

	private void updateRuleSelection(IRule rule) {
		boolean buttonsEnabled = rule != null;
		dropDownMenu.setEnabled(buttonsEnabled && nativeLaunchersEnabled);
		description.setText(rule == null ? "" : rule.getElementName());
		this.selectedRule = rule;
	}

	@Override
	public void setSelection(ISelection selection, boolean reveal) {
	}

}
