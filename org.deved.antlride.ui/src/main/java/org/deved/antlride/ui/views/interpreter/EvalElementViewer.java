/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.ui.views.interpreter;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.deved.antlride.common.ui.AntlrImages;
import org.deved.antlride.core.model.IGrammar;
import org.deved.antlride.core.model.IRule;
import org.deved.antlride.core.model.IToken;
import org.deved.antlride.core.model.evaluation.EvalElementKind;
import org.deved.antlride.core.model.evaluation.IEvalElement;
import org.deved.antlride.core.model.evaluation.IExceptionEvalElement;
import org.deved.antlride.core.model.evaluation.ITokenEvalElement;
import org.deved.antlride.internal.ui.editor.AntlrEditor;
import org.deved.antlride.internal.ui.views.interpreter.AntlrFigure;
import org.deved.antlride.internal.ui.views.interpreter.AntlrInterpreterMessages;
import org.deved.antlride.ui.AntlrUI;
import org.deved.antlride.ui.action.DropDownMenu;
import org.deved.antlride.ui.text.AntlrTextSelection;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.dltk.ui.DLTKUIPlugin;
import org.eclipse.draw2d.ActionEvent;
import org.eclipse.draw2d.ActionListener;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LayoutManager;
import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.SWTGraphics;
import org.eclipse.draw2d.ScalableLayeredPane;
import org.eclipse.draw2d.ScrollPane;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.text.TextSelection;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;

public class EvalElementViewer extends Viewer {
	private Figure fRootFigure;

	private int horizontalGap = 5;

	private int verticalGap = 20;

	private LightweightSystem lws;

	private double fScale = 0.1;

	private ScalableLayeredPane fScalableLayeredPane;

	private IEvalElement fInput;

	private ActionListener fSelectionActionListener;

	private ScrollPane fScrollPane;

	private Canvas fCanvas;

	private Map<Integer, Action> fActions;

	private List<IExceptionEvalElement> fExceptions;

	private TableViewer fErrors;

	private SashForm fControl;

	public static final int ZOOM_IN_ACTION = 1001;

	public static final int ZOOM_OUT_ACTION = 1002;

	public static final int CLEAR_DIAGRAM_ACTION = 1003;

	public static final int EXPORT_ACTION = 1004;

	private class ClearDiagramAction extends Action {

		public ClearDiagramAction() {
			setText(AntlrInterpreterMessages.GrammarInterpreter_Clear_Diagram);
			setToolTipText(AntlrInterpreterMessages.GrammarInterpreter_Clear_Diagram);
			setImageDescriptor(AntlrImages.getDescriptor(AntlrImages.CLEAR));
		}

		@Override
		public void run() {
			clear();
		}
	}

	protected class ExportAsImage extends Action {
		private int format;
		private String formatName;

		public ExportAsImage(String formatName, int format) {
			super("Export as " + formatName, IAction.AS_PUSH_BUTTON);
			this.formatName = formatName;
			this.format = format;
		}

		@Override
		public void run() {
			FileDialog dialog = new FileDialog(getControl().getShell(),
					SWT.SAVE);
			final String imageFile = fInput.getElementName() + "." + formatName;
			dialog.setFileName(imageFile);
			final String path = dialog.open();
			if (path != null) {
				ProgressMonitorDialog monitor = new ProgressMonitorDialog(
						getControl().getShell());
				try {
					monitor.run(false, false, new IRunnableWithProgress() {

						public void run(IProgressMonitor monitor)
								throws InvocationTargetException,
								InterruptedException {
							exportAsImage(monitor, new File(path), format);
						}
					});
				} catch (InvocationTargetException e) {
					ErrorDialog.openError(getControl().getShell(),
							"Parse Tree Exporter", null, new Status(
									IStatus.ERROR, AntlrUI.PLUGIN_ID, imageFile
											+ " can't be exported", e
											.getTargetException()));
					AntlrUI.error(e.getTargetException());
				} catch (InterruptedException e) {
					AntlrUI.error(e);
				}
			}
		}
	}

	private class ZoomInAction extends Action {
		public ZoomInAction() {
			setText(AntlrInterpreterMessages.GrammarInterpreter_Zoom_In);
			setToolTipText(AntlrInterpreterMessages.GrammarInterpreter_Zoom_In);
			setImageDescriptor(AntlrImages.getDescriptor(AntlrImages.ZOOM_IN));
		}

		@Override
		public void run() {
			zoomIn();
		}
	}

	private class ZoomOutAction extends Action {

		public ZoomOutAction() {
			setText(AntlrInterpreterMessages.GrammarInterpreter_Zoom_Out);
			setToolTipText(AntlrInterpreterMessages.GrammarInterpreter_Zoom_Out);
			setImageDescriptor(AntlrImages.getDescriptor(AntlrImages.ZOOM_OUT));
		}

		@Override
		public void run() {
			zoomOut();
		}
	}

	private class ExceptionLabelProvider extends LabelProvider {

		@Override
		public Image getImage(Object element) {
			ISharedImages sharedImages = PlatformUI.getWorkbench()
					.getSharedImages();
			return sharedImages.getImage(ISharedImages.IMG_OBJS_ERROR_TSK);
		}

		@Override
		public String getText(Object element) {
			IExceptionEvalElement exception = (IExceptionEvalElement) element;
			StringBuilder text = new StringBuilder(exception.getElementName());
			text.append(": ");
			text.append(exception.getMessage());
			return text.toString();
		}
	}

	public EvalElementViewer(Composite composite) {
		fExceptions = new ArrayList<IExceptionEvalElement>();
		createControl(composite);
	}

	protected void createControl(Composite parent) {
		fControl = new SashForm(parent, SWT.VERTICAL);
		fControl.setLayoutData(new GridData(GridData.FILL_BOTH));
		// graph
		fCanvas = new Canvas(fControl, SWT.BORDER);
		fCanvas.setBackground(Display.getCurrent().getSystemColor(
				SWT.COLOR_WHITE));
		this.lws = new LightweightSystem(fCanvas);
		fScalableLayeredPane = new ScalableLayeredPane();
		fScrollPane = new ScrollPane();
		fScrollPane.setContents(fScalableLayeredPane);
		fSelectionActionListener = new ModelFigureSelectionAction();
		// errors
		fErrors = new TableViewer(fControl);
		fErrors.setLabelProvider(new ExceptionLabelProvider());
		fErrors.setContentProvider(new ArrayContentProvider());
		fErrors.addDoubleClickListener(new SelectExceptionListener());
		Table table = fErrors.getTable();
		TableColumn column = new TableColumn(table, SWT.LEFT);
		column.setText("Problems");//$NON-NLS-1
		column.setWidth(600);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		// spliter
		fControl.setWeights(new int[] { 70, 30 });
		// actions
		createActions();
	}

	@Override
	public Control getControl() {
		return fControl;
	}

	@Override
	public Object getInput() {
		return fInput;
	}

	@Override
	public ISelection getSelection() {
		return StructuredSelection.EMPTY;
	}

	@Override
	public void refresh() {
	}

	@Override
	public void setInput(Object input) {
		// fInput = (IEvalElement) input;
		fExceptions.clear();
		draw((IEvalElement) input);
		fActions.get(EXPORT_ACTION).setEnabled(true);
		fErrors.setInput(fExceptions);
	}

	@Override
	public void setSelection(ISelection selection, boolean reveal) {
	}

	private void draw(IEvalElement input) {
		clear();
		this.fInput = input;
		fRootFigure = new Figure();
		fRootFigure.setLayoutManager(new XYLayout());
		// build graph
		draw(input, 10, 10);
		fScalableLayeredPane.setScale(1.0);
		fScalableLayeredPane.add(fRootFigure);
		lws.setContents(fScrollPane);
		fScrollPane.setContents(fScalableLayeredPane);
	}

	public void zoomIn() {
		double scale = fScalableLayeredPane.getScale();
		scale += zoomFactor();
		fScalableLayeredPane.setScale(scale);
	}

	public void zoomOut() {
		double scale = fScalableLayeredPane.getScale();
		scale -= zoomFactor();
		if (scale <= 0) {
			return;
		}
		fScalableLayeredPane.setScale(scale);
	}

	private double zoomFactor() {
		return fScale;// / 10;
	}

	public void clear() {
		try {
			if (fRootFigure != null) {
				fScrollPane.setContents(null);
				fScalableLayeredPane.remove(fRootFigure);
			}
			if (fInput != null) {
				fInput.clear();
			}
			fExceptions.clear();
			fErrors.setInput(fExceptions);
		} catch (Exception ex) {
		} finally {
			if (fRootFigure != null)
				fRootFigure.erase();
			fRootFigure = null;
		}
	}

	public void dispose() {
		getControl().dispose();
	}

	private void select(IEvalElement evalElement) {
		ISelection editorSelection = null;

		int line = -1;
		int offset = -1;
		int length = -1;
		if (evalElement.getElementKind() == EvalElementKind.RULE) {
			IGrammar grammar = evalElement.getGrammar();
			String ruleName = evalElement.getElementName();
			IRule rule = grammar.findRule(ruleName);
			if (rule != null) {
				editorSelection = new TextSelection(rule.getName()
						.sourceStart(), ruleName.length());
			} else {
				IToken token = grammar.findToken(ruleName);
				if (token != null) {
					editorSelection = new TextSelection(token.getName()
							.sourceStart(), ruleName.length());
				}
			}

		} else if (evalElement.getElementKind() == EvalElementKind.TOKEN) {
			ITokenEvalElement token = (ITokenEvalElement) evalElement;
			line = token.getLine();
			offset = token.getColumn();
			length = token.getText().length();
		} else if (evalElement.getElementKind() == EvalElementKind.EXCEPTION) {
			IExceptionEvalElement token = (IExceptionEvalElement) evalElement;
			line = token.getLine();
			offset = token.getColumn();
			length = 0;// token.getText().length();
		}
		// update editor selection
		IWorkbenchPage page = DLTKUIPlugin.getActivePage();
		if (page != null && editorSelection != null) {
			IEditorPart part = page.getActiveEditor();
			if (part != null) {
				AntlrEditor editor = (AntlrEditor) part
						.getAdapter(AntlrEditor.class);
				ISelectionProvider selectionProvider = editor
						.getSelectionProvider();
				selectionProvider.setSelection(editorSelection);
			}
		}
		if (line != -1) {
			ISelection selection = new AntlrTextSelection(line, offset, length);
			fireSelectionChanged(new SelectionChangedEvent(this, selection));
		}
	}

	@SuppressWarnings("unchecked")
	private void draw(IEvalElement tree, int initialX, int initialY) {
		// create initial graph
		traverse(tree, initialX, initialY);
		// create connectors
		createAnchors(tree);
		// set contraints for each figure
		LayoutManager layout = this.fRootFigure.getLayoutManager();
		List<IFigure> figures = this.fRootFigure.getChildren();
		for (IFigure f : figures) {
			if (f instanceof AntlrFigure) {
				AntlrFigure figure = (AntlrFigure) f;
				layout.setConstraint(figure, new Rectangle(figure.x, figure.y,
						figure.width, figure.height));
			}
		}
	}

	private void createAnchors(IEvalElement tree) {
		if (tree.isLeaf())
			return;
		int childCount = tree.getElementCount();
		AntlrFigure parent = (AntlrFigure) tree.getUserData();
		// create connection
		int x1 = parent.x + (parent.width / 2);
		int y1 = parent.y + parent.height;
		int x2 = x1;
		int y2 = y1 + ((childCount == 1) ? verticalGap : verticalGap / 2);
		int middle = x1;
		createConnection(parent, x1, y1, x2, y2);
		if (childCount > 1) {
			AntlrFigure first = (AntlrFigure) tree.firstElement().getUserData();
			AntlrFigure last = (AntlrFigure) tree.lastElement().getUserData();
			x1 = first.x + first.width / 2;
			x2 = last.x + last.width / 2 + 1;
			y1 = y1 + verticalGap / 2;
			y2 = y1;
			if (x2 < middle) {
				x2 = middle;
			}
			createConnection(parent, x1, y1, x2, y2);
		}
		y1 = parent.y + parent.height + verticalGap / 2;
		y2 = parent.y + parent.height + verticalGap;
		for (int index = 0; index < childCount; index++) {
			IEvalElement treeChild = tree.getElement(index);
			// create link
			AntlrFigure child = (AntlrFigure) treeChild.getUserData();
			x1 = child.x + (child.width / 2);
			x2 = x1;
			if (childCount > 1) {
				createConnection(parent, x1, y1, x2, y2);
			}
			createAnchors(treeChild);
		}
	}

	private void createConnection(IFigure owner, int x1, int y1, int x2, int y2) {
		IFigure figure = new Figure();
		figure.setBorder(new LineBorder(ColorConstants.black, 1));
		fRootFigure.add(figure);
		LayoutManager layout = this.fRootFigure.getLayoutManager();
		if (x1 == x2) {
			x2 = 1;
		} else {
			x2 -= x1;
		}
		if (y1 == y2) {
			y2 = 1;
		} else {
			y2 -= y1;
		}
		layout.setConstraint(figure, new Rectangle(x1, y1, x2, y2));
	}

	private int traverse(IEvalElement element, int x, int y) {
		if (element.getElementKind() == EvalElementKind.EXCEPTION
				&& !fExceptions.contains(element)) {
			fExceptions.add((IExceptionEvalElement) element);
		}
		// create figure
		AntlrFigure figure = createFigure(element, x, y);
		// reset tree payload
		element.setUserData(figure);
		// traverse childs
		for (IEvalElement child : element) {
			x = traverse(child, x, y + figure.height + verticalGap);
		}
		int minLeft = figure.x;
		int maxWidth = maxSpan(element);
		if (element.getElementCount() >= 1) {
			alignChild(element, minLeft, maxWidth);
		}
		// add figure
		addFigure(figure);
		return maxWidth + horizontalGap;
	}

	private void alignChild(IEvalElement tree, int left, int maxWidth) {
		AntlrFigure childFigure = (AntlrFigure) tree.getUserData();
		int offset = left + ((maxWidth - left - childFigure.width) / 2);
		childFigure.x = offset;
		if (tree.getElementCount() == 1) {
			alignChild(tree.firstElement(), left, maxWidth);
		}
	}

	private int maxSpan(IEvalElement tree) {
		AntlrFigure f = (AntlrFigure) tree.getUserData();
		int max = f.x + f.width;
		int childCount = tree.getElementCount();
		if (childCount > 0) {
			int index = childCount - 1;
			IEvalElement child = tree.getElement(index);
			int childMax = maxSpan(child);
			if (childMax > max) {
				max = childMax;
			}
		}
		return max;
	}

	private AntlrFigure createFigure(IEvalElement evalElement, int x, int y) {
		AntlrFigure figure = new AntlrFigure(evalElement, x, y);
		figure.addActionListener(fSelectionActionListener);
		return figure;
	}

	private void addFigure(IFigure figure) {
		fRootFigure.add(figure);
	}

	public Action getAction(int action) {
		return fActions.get(action);
	}

	protected void createActions() {
		fActions = new HashMap<Integer, Action>();
		fActions.put(CLEAR_DIAGRAM_ACTION, new ClearDiagramAction());
		fActions.put(ZOOM_IN_ACTION, new ZoomInAction());
		fActions.put(ZOOM_OUT_ACTION, new ZoomOutAction());
		DropDownMenu exportAction = new DropDownMenu(new ExportAsImage("png",
				SWT.IMAGE_PNG), new ExportAsImage("jpeg", SWT.IMAGE_JPEG));
		exportAction.setImageDescriptor(AntlrImages
				.getDescriptor(AntlrImages.SAVE_AS));
		exportAction.setEnabled(false);
		fActions.put(EXPORT_ACTION, exportAction);
	}

	private class ModelFigureSelectionAction implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			AntlrFigure figure = (AntlrFigure) event.getSource();
			select(figure.element);
		}
	}

	private class SelectExceptionListener implements IDoubleClickListener {

		public void doubleClick(DoubleClickEvent event) {
			IStructuredSelection selection = (IStructuredSelection) event
					.getSelection();
			IExceptionEvalElement exception = (IExceptionEvalElement) selection
					.getFirstElement();
			select(exception);
		}

	}

	private void exportAsImage(IProgressMonitor monitor, File file, int format) {
		monitor.beginTask("Parse Tree Exporter", 3);

		IFigure figure = fRootFigure;
		int maxX = 0;
		int maxW = 0;
		monitor.subTask("Calculating bounds");
		@SuppressWarnings("unchecked")
		List<IFigure> figures = figure.getChildren();
		for (IFigure childFigure : figures) {
			if (childFigure instanceof AntlrFigure) {
				int x = childFigure.getBounds().x;
				if (x > maxX) {
					maxX = x;
					maxW = childFigure.getBounds().width;
				}
			}
		}
		Rectangle r = Rectangle.SINGLETON;
		r.x = figure.getBounds().x;
		r.y = figure.getBounds().y;
		r.height = figure.getBounds().height + 10;
		r.width = maxX + maxW + 15;

		monitor.worked(1);

		OutputStream out = null;

		Image image = null;
		GC gc = null;
		Graphics g = null;
		try {
			monitor.subTask("Creating image");
			image = new Image(getControl().getDisplay(), r.width, r.height);
			gc = new GC(image);
			g = new SWTGraphics(gc);
			g.translate(r.x * -1, r.y * -1);

			figure.paint(g);
			monitor.worked(1);
			ImageLoader imageLoader = new ImageLoader();
			imageLoader.data = new ImageData[] { image.getImageData() };

			monitor.subTask("Writing to disk");
			out = new BufferedOutputStream(new FileOutputStream(file));
			imageLoader.save(out, format);
			monitor.worked(1);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (g != null) {
				g.dispose();
			}
			if (gc != null) {
				gc.dispose();
			}
			if (image != null) {
				image.dispose();
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			monitor.done();
		}
	}
}
