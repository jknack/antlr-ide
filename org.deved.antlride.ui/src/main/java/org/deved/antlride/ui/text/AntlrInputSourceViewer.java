/******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.ui.text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.deved.antlride.core.model.IGrammar;
import org.deved.antlride.core.model.IModelElement;
import org.deved.antlride.core.model.ast.ModelElementQuery;
import org.deved.antlride.internal.ui.views.interpreter.AntlrInterpreterMessages;
import org.deved.antlride.ui.AntlrPreferenceConstants;
import org.deved.antlride.ui.AntlrUIHelper;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.ITextOperationTarget;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.source.CompositeRuler;
import org.eclipse.jface.text.source.IVerticalRuler;
import org.eclipse.jface.text.source.IVerticalRulerColumn;
import org.eclipse.jface.text.source.LineNumberRulerColumn;
import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ExtendedModifyEvent;
import org.eclipse.swt.custom.ExtendedModifyListener;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.texteditor.ITextEditorActionConstants;
import org.eclipse.ui.texteditor.IUpdate;

public class AntlrInputSourceViewer extends Viewer {

	private IGrammar fGrammar;

	private InputSourceViewerListener fListener;

	private Map<String, IAction> fGlobalActions;

	private MenuManager fMenuManager;

	private SourceViewer fSourceViewer;

	private Composite fComposite;

	private Label fStatusBar;

	private class InputSourceViewerListener implements ExtendedModifyListener,
			ISelectionChangedListener, KeyListener, MouseListener {

		public void modifyText(ExtendedModifyEvent event) {
			StyledText styledText = (StyledText) event.widget;
			String text = styledText.getText();
			if (text != null && text.length() > 0) {
				highlightSyntax(text);
			}
		}

		public void selectionChanged(SelectionChangedEvent event) {
			updateAction(ITextEditorActionConstants.CUT);
			updateAction(ITextEditorActionConstants.COPY);
		}

		public void keyPressed(KeyEvent e) {
			updateStatusLine();
		}

		public void keyReleased(KeyEvent e) {
		}

		public void mouseDoubleClick(MouseEvent e) {
		}

		public void mouseDown(MouseEvent e) {
			updateStatusLine();
		}

		public void mouseUp(MouseEvent e) {
		}
	}

	private class TextViewerAction extends Action implements IUpdate {

		private int fOperationCode = -1;
		private ITextOperationTarget fOperationTarget;

		/**
		 * Creates a new action.
		 * 
		 * @param viewer
		 *            the viewer
		 * @param operationCode
		 *            the opcode
		 */
		public TextViewerAction(ITextViewer viewer, int operationCode) {
			fOperationCode = operationCode;
			fOperationTarget = viewer.getTextOperationTarget();
			update();
		}

		/**
		 * Updates the enabled state of the action. Fires a property change if
		 * the enabled state changes.
		 * 
		 * @see Action#firePropertyChange(String, Object, Object)
		 */
		public void update() {

			boolean wasEnabled = isEnabled();
			boolean isEnabled = (fOperationTarget != null && fOperationTarget
					.canDoOperation(fOperationCode));
			setEnabled(isEnabled);

			if (wasEnabled != isEnabled) {
				firePropertyChange(ENABLED, wasEnabled ? Boolean.TRUE
						: Boolean.FALSE, isEnabled ? Boolean.TRUE
						: Boolean.FALSE);
			}
		}

		/**
		 * @see Action#run()
		 */
		public void run() {
			if (fOperationCode != -1 && fOperationTarget != null) {
				fOperationTarget.doOperation(fOperationCode);
			}

		}
	}

	private class ShowMenuListener implements IMenuListener {

		public void menuAboutToShow(IMenuManager manager) {
			onMenuAboutToShow(manager);
		}
	}

	public AntlrInputSourceViewer(Composite parent, boolean editable) {
		createControl(parent, editable);
	}

	public AntlrInputSourceViewer(Composite parent) {
		this(parent, true);
	}

	public void setEditable(boolean editable) {
		fSourceViewer.setEditable(editable);
	}

	protected void createControl(Composite parent, boolean editable) {
		fComposite = new Composite(parent, SWT.BORDER);
		GridLayout layout = new GridLayout(2, false);
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		fComposite.setLayout(layout);
		IVerticalRuler verticalRuler = createVerticalRuler();
		int style = SWT.MULTI | SWT.V_SCROLL | SWT.BORDER;
		fSourceViewer = new SourceViewer(fComposite, verticalRuler, style);
		fSourceViewer.setDocument(new Document());
		fSourceViewer.setEditable(editable);
		// configure text
		fListener = new InputSourceViewerListener();
		StyledText styledText = fSourceViewer.getTextWidget();
		styledText.setFont(getDefaultFont());
		styledText.addExtendedModifyListener(fListener);
		styledText.addMouseListener(fListener);
		styledText.addKeyListener(fListener);
		GridData gd = new GridData(SWT.FILL, SWT.FILL, true, true);
		gd.horizontalSpan = 2;

		fSourceViewer.getControl().setLayoutData(gd);

		createTextActions();

		fSourceViewer.addSelectionChangedListener(fListener);
		fMenuManager = new MenuManager();
		fMenuManager.addMenuListener(new ShowMenuListener());
		fMenuManager.setRemoveAllWhenShown(true);

		Menu menu = fMenuManager.createContextMenu(styledText);

		styledText.setMenu(menu);
		// status bar
		gd = new GridData(SWT.RIGHT, SWT.FILL, true, false);
		gd.widthHint = 40;
		fStatusBar = new Label(fComposite, SWT.LEFT);
		fStatusBar.setLayoutData(gd);
		updateStatusLine();
		Label separator = new Label(fComposite, SWT.SEPARATOR);
		// separator.setText(" ");
		gd = new GridData();
		gd.widthHint = 8;
		gd.heightHint = 8;
		separator.setLayoutData(gd);
	}

	public StyledText getTextWidget() {
		return fSourceViewer.getTextWidget();
	}

	private void updateStatusLine() {
		StyledText styledText = getTextWidget();
		StringBuilder buf = new StringBuilder();
		int line = styledText.getLineAtOffset(styledText.getCaretOffset());
		buf.append(line + 1);
		buf.append(" : ");
		buf.append(styledText.getCaretOffset()
				- styledText.getOffsetAtLine(line) + 1);
		// buf.append(" ");
		fStatusBar.setText(buf.toString());
	}

	private static Font getDefaultFont() {
		return JFaceResources.getFont("Monospaced");
	}

	protected void onMenuAboutToShow(IMenuManager menuManager) {
		String[] actions = { ITextEditorActionConstants.CUT,
				ITextEditorActionConstants.COPY,
				ITextEditorActionConstants.PASTE, null,
				ITextEditorActionConstants.SELECT_ALL };
		for (String actionId : actions) {
			IAction action = fGlobalActions.get(actionId);
			if (action == null) {
				menuManager.add(new Separator());
			} else {
				menuManager.add(action);
			}
		}
		menuManager.update();
	}

	private void updateAction(String actionId) {
		IAction action = (IAction) fGlobalActions.get(actionId);
		if (action instanceof IUpdate)
			((IUpdate) action).update();
	}

	private void createTextActions() {
		fGlobalActions = new HashMap<String, IAction>();

		ISharedImages sharedImages = PlatformUI.getWorkbench()
				.getSharedImages();

		TextViewerAction action;

		action = new TextViewerAction(fSourceViewer, ITextOperationTarget.CUT);
		action.setAccelerator(SWT.CTRL | 'X');
		action.setText(AntlrInterpreterMessages.GrammarInterpreter_Cut);
		action.setImageDescriptor(sharedImages
				.getImageDescriptor(ISharedImages.IMG_TOOL_CUT));
		action.setDisabledImageDescriptor(sharedImages
				.getImageDescriptor(ISharedImages.IMG_TOOL_CUT_DISABLED));
		fGlobalActions.put(ITextEditorActionConstants.CUT, action);

		action = new TextViewerAction(fSourceViewer, ITextOperationTarget.COPY);
		action.setAccelerator(SWT.CTRL | 'C');
		action.setText(AntlrInterpreterMessages.GrammarInterpreter_Copy);
		action.setImageDescriptor(sharedImages
				.getImageDescriptor(ISharedImages.IMG_TOOL_COPY));
		action.setDisabledImageDescriptor(sharedImages
				.getImageDescriptor(ISharedImages.IMG_TOOL_COPY_DISABLED));
		fGlobalActions.put(ITextEditorActionConstants.COPY, action);

		action = new TextViewerAction(fSourceViewer, ITextOperationTarget.PASTE);
		action.setAccelerator(SWT.CTRL | 'V');
		action.setText(AntlrInterpreterMessages.GrammarInterpreter_Paste);
		action.setImageDescriptor(sharedImages
				.getImageDescriptor(ISharedImages.IMG_TOOL_PASTE));
		action.setDisabledImageDescriptor(sharedImages
				.getImageDescriptor(ISharedImages.IMG_TOOL_PASTE_DISABLED));
		fGlobalActions.put(ITextEditorActionConstants.PASTE, action);

		action = new TextViewerAction(fSourceViewer,
				ITextOperationTarget.SELECT_ALL);
		action.setText(AntlrInterpreterMessages.GrammarInterpreter_Select_All);
		fGlobalActions.put(ITextEditorActionConstants.SELECT_ALL, action);

	}

	public void dispose() {
		fGrammar = null;
		fListener = null;
		fComposite.dispose();
	}

	private void highlightSyntax(String text) {
		IGrammar grammar = fGrammar;
		if (grammar == null)
			return;
		StyledText styledText = getTextWidget();
		IModelElement[] literals = ModelElementQuery.collectLiterals(grammar);
		if (literals != null && literals.length > 0) {
			Pattern pattern = Pattern.compile("(\\w(\\w|\\d)*)|(\\S)");
			Matcher matcher = pattern.matcher(text);
			List<StyleRange> styleRanges = new ArrayList<StyleRange>();
			while (matcher.find()) {
				String word = matcher.group();
				if (word.length() > 1) {
					for (IModelElement literal : literals) {
						String elementName = literal.getElementName()
								.substring(1,
										literal.getElementName().length() - 1);
						if (elementName.equals(word)) {
							int start = matcher.start();
							int length = matcher.end() - matcher.start();
							styleRanges.add(createStyleRange(start, length));
							break;
						}
					}
				}
			}
			styledText.setStyleRanges(styleRanges
					.toArray(new StyleRange[styleRanges.size()]));
		}
	}

	private StyleRange createStyleRange(int start, int length) {
		StyleRange styleRange = new StyleRange();
		styleRange.start = start;
		styleRange.length = length;
		styleRange.fontStyle = SWT.BOLD;
		styleRange.foreground = JFaceResources.getColorRegistry().get(
				AntlrPreferenceConstants.EDITOR_KEYWORD_COLOR);
		return styleRange;
	}

	public IGrammar getGrammar() {
		return fGrammar;
	}

	public void setGrammar(IGrammar grammar) {
		fGrammar = grammar;
	}

	private static IVerticalRuler createVerticalRuler() {
		CompositeRuler ruler = new CompositeRuler();
		ruler.addDecorator(0, createLineNumberRulerColumn());
		return ruler;
	}

	private static IVerticalRulerColumn createLineNumberRulerColumn() {
		LineNumberRulerColumn verticalRulerColumn = new LineNumberRulerColumn();
		Color color = Display.getCurrent().getSystemColor(SWT.COLOR_DARK_GRAY);
		verticalRulerColumn.setFont(getDefaultFont());
		verticalRulerColumn.setForeground(color);
		return verticalRulerColumn;
	}

	public IAction getAction(String actionId) {
		return fGlobalActions.get(actionId);
	}

	@Override
	public Control getControl() {
		return fComposite;
	}

	@Override
	public Object getInput() {
		return fSourceViewer.getInput();
	}

	@Override
	public ISelection getSelection() {
		return fSourceViewer.getSelection();
	}

	@Override
	public void refresh() {
		fSourceViewer.refresh();
	}

	@Override
	public void setInput(Object input) {
		if (input instanceof String) {
			input = new Document((String) input);
		}
		fSourceViewer.setInput(input);
		highlightSyntax(((Document) fSourceViewer.getInput()).get());
	}

	@Override
	public void setSelection(ISelection selection, boolean reveal) {
		AntlrUIHelper.select(getTextWidget(), (AntlrTextSelection) selection);
	}

	@Override
	public void addSelectionChangedListener(ISelectionChangedListener listener) {
		fSourceViewer.addSelectionChangedListener(listener);
	}
}
