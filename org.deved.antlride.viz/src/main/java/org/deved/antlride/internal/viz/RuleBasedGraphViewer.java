/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *******************************************************************************/
package org.deved.antlride.internal.viz;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.deved.antlride.common.ui.AntlrImages;
import org.deved.antlride.core.model.IGrammar;
import org.deved.antlride.core.model.IRule;
import org.deved.antlride.ui.AntlrPreferenceConstants;
import org.deved.antlride.viz.AntlrViz;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.draw2d.SWTGraphics;
import org.eclipse.draw2d.ScalableLayeredPane;
import org.eclipse.draw2d.ScrollPane;
import org.eclipse.draw2d.Viewport;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.resource.JFaceResources;
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
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.services.IDisposable;

public abstract class RuleBasedGraphViewer extends Viewer implements
		IDisposable {

	protected class ExportAsImage extends Action {
		private int format;
		private String formatName;

		public ExportAsImage(String formatName, int format) {
			super("Export as " + formatName, IAction.AS_PUSH_BUTTON);
			this.formatName = formatName;
			this.format = format;
			setImageDescriptor(AntlrImages.getDescriptor(AntlrImages.SAVE_AS));
			setToolTipText(getText());
		}

		@Override
		public void run() {
			FileDialog dialog = new FileDialog(getControl().getShell(),
					SWT.SAVE);
			dialog.setFileName(getSelectedRuleName() + "." + formatName);
			String path = dialog.open();
			if (path != null) {
				File out = new File(path);
				exportAsImage(out, format);
			}
		}
	}
	
	private class RuleLabelProvider extends LabelProvider {
		@Override
		public String getText(Object element) {
			return ((IRule) element).getElementName();
		}

		@Override
		public Image getImage(Object element) {
			return AntlrImages.getImage(AntlrImages.RULE);
		}
	}

	private class RuleContentProvider implements ITreeContentProvider {

		public Object[] getChildren(Object element) {
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

	private class GraphSelectionListener implements ISelectionChangedListener {

		public void selectionChanged(SelectionChangedEvent event) {
			ISelection selection = event.getSelection();
			if (selection.isEmpty()) {
				clearGraph();
			} else {
				IStructuredSelection ss = (IStructuredSelection) selection;
				IRule element = (IRule) ss.getFirstElement();
				draw(element);
			}
		}
	}

	private class ZoomInAction extends Action {
		public ZoomInAction() {
			setText("Zoom In");
			setToolTipText("Zoom In");
			setImageDescriptor(AntlrImages.getDescriptor(AntlrImages.ZOOM_IN));
		}

		@Override
		public void run() {
			zoomIn();
		}
	}

	private class ZoomOutAction extends Action {

		public ZoomOutAction() {
			setText("Zoom out");
			setToolTipText("Zoom out");
			setImageDescriptor(AntlrImages.getDescriptor(AntlrImages.ZOOM_OUT));
		}

		@Override
		public void run() {
			zoomOut();
		}
	}

	private class ClearAction extends Action {

		public ClearAction() {
			setText("Clear");
			setToolTipText("Clear");
			setImageDescriptor(AntlrImages.getDescriptor(AntlrImages.CLEAR));
		}

		@Override
		public void run() {
			onClearGraph();
			clearGraph();
		}
	}

	private TreeViewer ruleViewer;

	protected IGrammar grammar;

	private SashForm control;

	private Canvas canvas;

	private LightweightSystem lightweightSystem;

	private ScalableLayeredPane scalableLayeredPane;

	private ScrollPane scrollPane;

	public RuleBasedGraphViewer(Composite composite) {
		createControl(composite);
	}

	public final String getSelectedRuleName() {
		IRule rule = getSelectedRule();
		return rule == null ? null : rule.getElementName();
	}

	public final IRule getSelectedRule() {
		IStructuredSelection selection = (IStructuredSelection) ruleViewer
				.getSelection();
		if (selection.isEmpty())
			return null;
		return ((IRule) selection.getFirstElement());
	}

	public final void draw(IRule element) {
		clearGraph();

		IFigure figure = doDraw(element);

		scalableLayeredPane.add(figure);

		scrollPane.setContents(scalableLayeredPane);

		lightweightSystem.setContents(scrollPane);
	}

	protected abstract IFigure doDraw(IRule rule);

	protected void onClearGraph(){}
	
	private final void clearGraph() {
		try {			
			if (scalableLayeredPane != null) {
				@SuppressWarnings("unchecked")
				List<IFigure> children = scalableLayeredPane.getChildren();
				if (children != null && children.size() > 0) {
					IFigure root = children.get(0);
					root.erase();
					scalableLayeredPane.remove(root);
				}
				scalableLayeredPane.erase();
			}
			if (scrollPane != null) {
				IFigure contents = scrollPane.getContents();
				if (contents != null) {
					contents.erase();
				}
				scrollPane.erase();
			}
			scrollPane = createScrollPane();
			scalableLayeredPane = createScalableLayeredPane();
		} catch (Exception ex) {
			AntlrViz.error("Couldn't clear graph", ex);
		}
	}

	private void zoomIn() {
		if (scalableLayeredPane == null)
			return;
		double scale = scalableLayeredPane.getScale();
		scale += zoomFactor();
		scalableLayeredPane.setScale(scale);
	}

	private void zoomOut() {
		if (scalableLayeredPane == null)
			return;
		double scale = scalableLayeredPane.getScale();
		scale -= zoomFactor();
		if (scale <= 0) {
			return;
		}
		scalableLayeredPane.setScale(scale);
	}

	protected double zoomFactor() {
		return 0.125;
	}

	protected ScalableLayeredPane createScalableLayeredPane() {
		return new ScalableLayeredPane();
	}

	protected ScrollPane createScrollPane() {
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setViewport(new Viewport(useGraphicsTransalate()));
		return scrollPane;
	}

	protected void createControl(Composite composite) {
		GridData gd;

		SashForm sashForm = new SashForm(composite, SWT.HORIZONTAL);

		Composite leftPane = new Composite(sashForm, SWT.NONE);
		leftPane.setLayout(new GridLayout(3, false));

		Label label = new Label(leftPane, SWT.NONE);
		label.setText("Rule:");
		label.setToolTipText("Search Rule");

		final Text ruleText = new Text(leftPane, SWT.BORDER);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		ruleText.setLayoutData(gd);
		ruleText.setToolTipText("Search Rule");
		ruleText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				ruleViewer.refresh();
			}
		});

		ToolBar bar = new ToolBar(leftPane, SWT.HORIZONTAL);
		ToolItem clearFilter = new ToolItem(bar, SWT.PUSH);
		clearFilter.setToolTipText("Clear");
		clearFilter.setImage(AntlrImages.getImage(AntlrImages.CLEAR));
		clearFilter.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				ruleText.setText("");
				ruleViewer.refresh();
			}

			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		ruleViewer = new TreeViewer(leftPane);
		gd = new GridData(GridData.FILL_BOTH);
		gd.horizontalSpan = 3;
		ruleViewer.getTree().setLayoutData(gd);
		ruleViewer.addFilter(new ViewerFilter() {

			@Override
			public boolean select(Viewer viewer, Object parentElement,
					Object element) {
				String ruleName = ruleText.getText().toLowerCase();
				if (ruleName.length() > 0) {
					boolean select = false;
					if (element instanceof IRule) {
						IRule rule = (IRule) element;
						select = rule.getElementName().toLowerCase()
								.startsWith(ruleName);
					}
					if (select) {
						ruleViewer
								.setSelection(new StructuredSelection(element));
					}
					return select;
				}
				return true;
			}
		});
		ruleViewer.setLabelProvider(new RuleLabelProvider());
		ruleViewer.setContentProvider(new RuleContentProvider());
		ruleViewer.addSelectionChangedListener(new GraphSelectionListener());

		Composite rightPane = new Composite(sashForm, SWT.NONE);

		rightPane.setLayout(new GridLayout(2, false));

		bar = new ToolBar(rightPane, SWT.FLAT | SWT.RIGHT
				| SWT.HORIZONTAL);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalAlignment = SWT.END;
		gd.horizontalSpan = 2;
		bar.setLayoutData(gd);

		// add actions
		IToolBarManager barManager = new ToolBarManager(bar);
		barManager.add(new ZoomInAction());
		barManager.add(new ZoomOutAction());
		barManager.add(new ClearAction());

		fillToolBar(barManager);

		barManager.update(true);

		canvas = new Canvas(rightPane, SWT.BORDER);
		gd = new GridData(GridData.FILL_BOTH);
		gd.horizontalSpan = 2;
		canvas.setLayoutData(gd);
		Color color = JFaceResources.getColorRegistry().get(
				AntlrPreferenceConstants.EDITOR_BACKGROUND_COLOR);
		canvas.setBackground(color == null ? ColorConstants.white : color);
		lightweightSystem = new LightweightSystem(canvas);

		sashForm.setWeights(new int[] { 30, 70 });
		control = sashForm;
	}

	protected void fillToolBar(IToolBarManager manager) {

	}

	/**
	 * Returns <code>true</code> if this viewport uses graphics translation.
	 * 
	 * @return whether this viewport uses graphics translation
	 */
	protected boolean useGraphicsTransalate() {
		return false;
	}

	@Override
	public Control getControl() {
		return control;
	}

	@Override
	public Object getInput() {
		return grammar;
	}

	@Override
	public ISelection getSelection() {
		return ruleViewer.getSelection();
	}

	@Override
	public void refresh() {
	}

	@Override
	public void setInput(Object input) {
		if (grammar != input) {
			grammar = (IGrammar) input;
			ruleViewer.setInput(grammar.getRules());
		}
	}

	@Override
	public void setSelection(ISelection selection, boolean reveal) {
	}

	public void dispose() {
		clearGraph();
	}

	private void exportAsImage(File file, int format) {
		IFigure figure = (IFigure) scalableLayeredPane.getChildren().get(0);
		Rectangle r = figure.getBounds();

		OutputStream out = null;

		Image image = null;
		GC gc = null;
		Graphics g = null;
		try {
			out = new BufferedOutputStream(new FileOutputStream(file));
			image = new Image(getControl().getDisplay(), r.width, r.height);
			gc = new GC(image);
			g = new SWTGraphics(gc);
			g.translate(r.x * -1, r.y * -1);

			figure.paint(g);

			ImageLoader imageLoader = new ImageLoader();
			imageLoader.data = new ImageData[] { image.getImageData() };
			imageLoader.save(out, format);
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
		}
	}
}
