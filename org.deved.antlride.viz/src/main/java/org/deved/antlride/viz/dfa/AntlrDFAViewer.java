/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.viz.dfa;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.deved.antlride.common.ui.AntlrImages;
import org.deved.antlride.core.dfa.DFAGraphProvider;
import org.deved.antlride.core.dfa.DFAGraphProviderRepository;
import org.deved.antlride.core.dot.DotEdge;
import org.deved.antlride.core.dot.DotGraph;
import org.deved.antlride.core.dot.DotNode;
import org.deved.antlride.core.model.IGrammar;
import org.deved.antlride.viz.dot.DotFigure;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.dltk.core.builder.ISourceLineTracker;
import org.eclipse.dltk.ui.DLTKUIPlugin;
import org.eclipse.dltk.utils.TextUtils;
import org.eclipse.draw2d.FigureUtilities;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.SWTGraphics;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuCreator;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
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
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.services.IDisposable;
import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.zest.core.widgets.Graph;
import org.eclipse.zest.core.widgets.GraphConnection;
import org.eclipse.zest.core.widgets.GraphNode;
import org.eclipse.zest.core.widgets.IContainer;
import org.eclipse.zest.core.widgets.ZestStyles;
import org.eclipse.zest.layouts.LayoutAlgorithm;
import org.eclipse.zest.layouts.LayoutStyles;
import org.eclipse.zest.layouts.algorithms.DirectedGraphLayoutAlgorithm;
import org.eclipse.zest.layouts.algorithms.GridLayoutAlgorithm;
import org.eclipse.zest.layouts.algorithms.HorizontalTreeLayoutAlgorithm;
import org.eclipse.zest.layouts.algorithms.RadialLayoutAlgorithm;
import org.eclipse.zest.layouts.algorithms.SpringLayoutAlgorithm;
import org.eclipse.zest.layouts.algorithms.TreeLayoutAlgorithm;

public class AntlrDFAViewer extends Viewer implements IDisposable {

	private class ExportAsDot extends Action {

		public ExportAsDot() {
			super("Export as dot", IAction.AS_PUSH_BUTTON);
		}

		@Override
		public void run() {
			FileDialog dialog = new FileDialog(getControl().getShell(),
					SWT.SAVE);
			dialog.setFileName(currentGraph.ruleName + "-dec"
					+ currentGraph.decisionNumber + ".dot");
			String path = dialog.open();
			if (path != null) {
				File out = new File(path);
				exportAsDot(out);
			}
		}
	}

	private class ExportAsImage extends Action {
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
			dialog.setFileName(currentGraph.ruleName + "-dec"
					+ currentGraph.decisionNumber + "." + formatName);
			String path = dialog.open();
			if (path != null) {
				File out = new File(path);
				exportAsImage(out, format);
			}
		}
	}

	private class LayoutAction extends Action {
		private Class<? extends LayoutAlgorithm> layoutClass;

		public LayoutAction(String text,
				Class<? extends LayoutAlgorithm> layoutClass) {
			super(text, IAction.AS_RADIO_BUTTON);
			this.layoutClass = layoutClass;
		}

		@Override
		public void run() {
			applyLayout(layoutClass);
		}
	}

	private class DropDownMenu extends Action implements IMenuCreator {

		private Menu fMenu;

		private Action[] delegates;

		public DropDownMenu(Action... delegates) {
			super("", delegates.length > 1 ? IAction.AS_DROP_DOWN_MENU
					: IAction.AS_PUSH_BUTTON);
			setMenuCreator(this);
			this.delegates = delegates;
			delegates[0].setChecked(true);
		}

		public void dispose() {
			if (fMenu != null)
				fMenu.dispose();
			delegates = null;
		}

		public Menu getMenu(Control parent) {
			fMenu = new Menu(parent);
			for (int i = 0; i < delegates.length; i++) {
				ActionContributionItem item = new ActionContributionItem(
						delegates[i]);
				item.fill(fMenu, -1);
			}

			return fMenu;
		}

		public Menu getMenu(Menu parent) {
			return null;
		}

		@Override
		public void run() {
			fMenu.setVisible(true);
		}

		@Override
		public void setEnabled(boolean enabled) {
			for (int i = 0; i < delegates.length; i++) {
				delegates[i].setEnabled(enabled);
			}
			super.setEnabled(enabled);
		}
	}

	private class DFANode extends org.deved.antlride.viz.dot.DotNode {

		public DFANode(IContainer graphModel, int style, String text) {
			super(graphModel, style, text);
		}

		@Override
		protected IFigure createFigureForModel() {
			String shape = currentNode.getAttribute("shape");
			DotFigure figure = new DotFigure(currentNode.name,
					getDefaultSize(currentNode), shape);
			figure.setBackgroundColor(DEFAULT_NODE_COLOR);
			figure.setForegroundColor(DARK_BLUE);
			return figure;
		}
	}

	private class GraphLabelProvider extends LabelProvider {
		@Override
		public String getText(Object element) {
			if (element instanceof String) {
				return (String) element;
			}
			DotGraph graph = (DotGraph) element;
			return graph.decision;
		}

		@Override
		public Image getImage(Object element) {
			if (element instanceof String) {
				return AntlrImages.getImage(AntlrImages.RULE);
			}
			return AntlrImages.getImage(AntlrImages.DECISION);
		}
	}

	private class GraphContentProvider implements ITreeContentProvider {

		private Map<String, List<DotGraph>> graphMap = new LinkedHashMap<String, List<DotGraph>>();

		public Object[] getElements(Object element) {
			return graphMap.keySet().toArray(new Object[0]);
		}

		public void dispose() {
			graphMap.clear();
		}

		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
			graphMap.clear();
			if (newInput != null) {
				DotGraph[] dotGraphs = (DotGraph[]) newInput;

				for (DotGraph dotGraph : dotGraphs) {
					List<DotGraph> graphList = graphMap.get(dotGraph.ruleName);
					if (graphList == null) {
						graphList = new ArrayList<DotGraph>();
						graphMap.put(dotGraph.ruleName, graphList);
					}
					graphList.add(dotGraph);
				}
			}
		}

		public Object[] getChildren(Object parentElement) {
			return graphMap.get(parentElement).toArray(new Object[0]);
		}

		public Object getParent(Object element) {
			return null;
		}

		public boolean hasChildren(Object element) {
			return String.class.isInstance(element);
		}
	}

	private class GraphSelectionListener implements ISelectionChangedListener {

		public void selectionChanged(SelectionChangedEvent event) {
			ISelection selection = event.getSelection();
			if (selection.isEmpty()) {
				clearGraph();
			} else {
				IStructuredSelection ss = (IStructuredSelection) selection;
				Object element = ss.getFirstElement();
				if (element instanceof String) {
					clearGraph();
				} else {
					draw((DotGraph) element);
				}
			}
		}
	}

	private Color DEFAULT_NODE_COLOR = new Color(null, 216, 228, 248);

	private Color DARK_BLUE = new Color(null, 1, 70, 122);

	private static final Pattern LINE_NUMBER_PATTERN = Pattern
			.compile("\\d+:\\d+:");

	private Graph graph;

	private Composite control;

	private TreeViewer decisionViewer;

	// TODO: temporary solution, invalid initialization of GraphNode
	private DotNode currentNode;

	private DotGraph currentGraph;

	private DropDownMenu layoutAction;

	private Link description;

	private DropDownMenu exportAction;

	private IGrammar grammar;

	private ISourceLineTracker lineTracker;

	public AntlrDFAViewer(Composite composite) {
		createControl(composite);
	}

	public void exportAsDot(File file) {
		IStructuredSelection selection = (IStructuredSelection) decisionViewer
				.getSelection();
		try {
			DotGraph g = (DotGraph) selection.getFirstElement();
			OutputStream out = new BufferedOutputStream(new FileOutputStream(
					file));
			out.write(g.dot.getBytes());
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void exportAsImage(File file, int format) {
		IFigure figure = graph.getContents();
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
			imageLoader.save(out, SWT.IMAGE_PNG);
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

	protected void applyLayout(Class<? extends LayoutAlgorithm> layoutClass) {
		try {
			if (graph != null) {
				LayoutAlgorithm layoutAlgorithm = layoutClass.newInstance();
				layoutAlgorithm.setStyle(LayoutStyles.NO_LAYOUT_NODE_RESIZING);
				graph.setLayoutAlgorithm(layoutAlgorithm, true);
			}
		} catch (InstantiationException e) {
			graph.setLayoutAlgorithm(new DirectedGraphLayoutAlgorithm(
					LayoutStyles.NO_LAYOUT_NODE_RESIZING), true);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	private void clearGraph() {
		exportAction.setEnabled(false);
		layoutAction.setEnabled(false);
		description.setText("");
		@SuppressWarnings("unchecked")
		List<GraphNode> nodes = graph.getNodes();
		while (nodes != null && nodes.size() > 0) {
			GraphNode node = nodes.get(0);
			if (node != null && !node.isDisposed()) {
				node.dispose();
			}
		}
		@SuppressWarnings("unchecked")
		List<GraphConnection> connections = graph.getConnections();
		while (connections != null && connections.size() > 0) {
			GraphConnection connection = connections.get(0);
			if (connection != null && !connection.isDisposed()) {
				connection.dispose();
			}
		}
	}

	private void draw(DotGraph dotGraph) {
		clearGraph();
		currentGraph = dotGraph;
		exportAction.setEnabled(true);
		layoutAction.setEnabled(true);
		Matcher matcher = LINE_NUMBER_PATTERN.matcher(dotGraph.description);

		StringBuilder desc = new StringBuilder();
		desc.append("<a>");
		desc.append(dotGraph.name);
		desc.append("</a>: ");
		String detail = null;
		String position = null;
		if (matcher.find()) {
			position = matcher.group();
			detail = dotGraph.description.substring(matcher.end() + 1);
		} else {
			detail = dotGraph.description;
		}
		desc.append(detail);
		description.setText(desc.toString());
		description.setData(position);
		Iterator<DotNode> nodes = dotGraph.nodes();
		Map<DotNode, GraphNode> m = new HashMap<DotNode, GraphNode>();

		// create nodes
		while (nodes.hasNext()) {
			currentNode = nodes.next();
			GraphNode gnode = new DFANode(this.graph, SWT.NONE,
					currentNode.name);
			m.put(currentNode, gnode);
		}
		// create connections
		Iterator<DotEdge> edges = dotGraph.edges();
		Color black = Display.getDefault().getSystemColor(SWT.COLOR_BLACK);
		while (edges.hasNext()) {
			DotEdge dotEdge = edges.next();
			GraphConnection connection = new GraphConnection(graph, SWT.NONE, m
					.get(dotEdge.from), m.get(dotEdge.to));
			connection.setLineColor(black);
			String label = dotEdge.getAttribute("label");
			String[] largeLabel = label.split(",");
			if (largeLabel.length > 2) {
				StringBuilder shortLabel = new StringBuilder(largeLabel[0]);
				shortLabel.append(",...,");
				shortLabel.append(largeLabel[largeLabel.length - 1]);
				connection.setText(shortLabel.toString());
			} else {
				connection.setText(label);
			}
			connection.setTooltip(new org.eclipse.draw2d.Label(label));
		}
		m.clear();
		graph.applyLayout();
	}

	protected int getDefaultSize(DotGraph dotGraph) {
		Iterator<DotNode> nodes = dotGraph.nodes();
		int defaultSize = Integer.MIN_VALUE;
		// create nodes
		while (nodes.hasNext()) {
			DotNode dotNode = nodes.next();
			int size = getDefaultSize(dotNode);
			if (size > defaultSize) {
				defaultSize = size;
			}
		}
		return defaultSize + 5;
	}

	private int getDefaultSize(DotNode dotNode) {
		Font font = getDefaultFont();
		int size = FigureUtilities.getTextExtents(dotNode.name + "  ", font).width;
		return size + 10;
	}

	private Font getDefaultFont() {
		return Display.getDefault().getSystemFont();
	}

	private void createControl(Composite composite) {
		GridData gd;

		SashForm sashForm = new SashForm(composite, SWT.HORIZONTAL);

		Composite leftPane = new Composite(sashForm, SWT.NONE);
		leftPane.setLayout(new GridLayout(3, false));

		Label label = new Label(leftPane, SWT.NONE);
		label.setText("Rule:");
		label.setToolTipText("Search Rule");

		final Text decisionText = new Text(leftPane, SWT.BORDER);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		decisionText.setLayoutData(gd);
		decisionText.setToolTipText("Search Rule");
		decisionText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				decisionViewer.refresh();
			}
		});

		ToolItem clearFilter = new ToolItem(new ToolBar(leftPane,
				SWT.HORIZONTAL), SWT.PUSH);
		clearFilter.setImage(AntlrImages.getImage(AntlrImages.CLEAR));
		clearFilter.setToolTipText("Clear");
		clearFilter.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				decisionText.setText("");
				decisionViewer.refresh();
			}

			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		decisionViewer = new TreeViewer(leftPane);
		gd = new GridData(GridData.FILL_BOTH);
		gd.horizontalSpan = 3;
		decisionViewer.getTree().setLayoutData(gd);
		decisionViewer.addFilter(new ViewerFilter() {

			@Override
			public boolean select(Viewer viewer, Object parentElement,
					Object element) {
				String ruleName = decisionText.getText().toLowerCase();
				if (ruleName.length() > 0) {
					String s = null;
					if (String.class.isInstance(parentElement)) {
						s = (String) parentElement;
					} else if (String.class.isInstance(element)) {
						s = (String) element;
					}
					if (s != null) {
						return s.startsWith(ruleName);
					}
					return false;
				}
				return true;
			}
		});
		decisionViewer.setLabelProvider(new GraphLabelProvider());
		decisionViewer.setContentProvider(new GraphContentProvider());
		decisionViewer
				.addSelectionChangedListener(new GraphSelectionListener());

		Composite rightPane = new Composite(sashForm, SWT.NONE);
		rightPane.setLayout(new GridLayout(2, false));

		description = new Link(rightPane, SWT.NONE);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		description.setLayoutData(gd);
		description.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				String position = (String) description.getData();
				try {
					if (position != null) {
						String[] location = position.split(":");
						int line = Integer.parseInt(location[0]) - 1;
						int length = Integer.parseInt(location[1]);
						// update editor selection
						IWorkbenchPage page = DLTKUIPlugin.getActivePage();
						if (page != null) {
							IEditorPart part = page.getActiveEditor();
							if (part != null) {
								ITextEditor editor = (ITextEditor) part
										.getAdapter(ITextEditor.class);
								int offset = getLineTracker().getLineOffset(
										line)
										+ length - 1;
								editor.selectAndReveal(0, 1);
								editor.selectAndReveal(offset, 1);
							}
						}
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}

			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});

		ToolBar bar = new ToolBar(rightPane, SWT.FLAT);
		gd = new GridData();
		gd.horizontalAlignment = SWT.END;
		bar.setLayoutData(gd);
		ToolBarManager manager = new ToolBarManager(bar);

		Action[] exportActions = { new ExportAsDot(),
				new ExportAsImage("png", SWT.IMAGE_PNG) /*
														 * , new
														 * ExportAsImage("gif",
														 * SWT.IMAGE_GIF), new
														 * ExportAsImage("jpeg",
														 * SWT.IMAGE_JPEG), new
														 * ExportAsImage("bmp",
														 * SWT.IMAGE_BMP)
														 */};
		exportAction = new DropDownMenu(exportActions);
		exportAction.setImageDescriptor(AntlrImages
				.getDescriptor(AntlrImages.SAVE_AS));
		exportAction.setToolTipText("Export graph");
		exportAction.setEnabled(false);
		manager.add(exportAction);

		Action[] layoutActions = {
				new LayoutAction("Horizontal Tree Layout",
						HorizontalTreeLayoutAlgorithm.class),
				new LayoutAction("Grid Layout", GridLayoutAlgorithm.class),
				new LayoutAction("Tree Layout", TreeLayoutAlgorithm.class),
				new LayoutAction("Radial Layout", RadialLayoutAlgorithm.class),
				new LayoutAction("Spring Layout", SpringLayoutAlgorithm.class) };
		layoutAction = new DropDownMenu(layoutActions);
		layoutAction.setImageDescriptor(AntlrImages
				.getDescriptor(AntlrImages.LAYOUT));
		layoutAction.setEnabled(false);
		manager.add(layoutAction);
		manager.update(true);

		graph = new Graph(rightPane, SWT.NONE);
		gd = new GridData(GridData.FILL_BOTH);
		gd.horizontalSpan = 2;
		graph.setLayoutData(gd);
		graph.setConnectionStyle(ZestStyles.CONNECTIONS_DIRECTED);
		graph.setLayoutAlgorithm(new HorizontalTreeLayoutAlgorithm(
				LayoutStyles.NO_LAYOUT_NODE_RESIZING), false);

		sashForm.setWeights(new int[] { 30, 70 });
		control = sashForm;
	}

	@Override
	public Control getControl() {
		return control;
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

	private ISourceLineTracker getLineTracker() {
		if (lineTracker == null) {
			lineTracker = TextUtils.createLineTracker(grammar.getSource());
		}
		return lineTracker;
	}

	@Override
	public void setInput(Object input) {
		if (grammar != input) {
			grammar = (IGrammar) input;
			lineTracker = null;
			ProgressMonitorDialog dlg = new ProgressMonitorDialog(getControl()
					.getShell());
			try {
				dlg.run(true, false, new IRunnableWithProgress() {
					public void run(IProgressMonitor monitor)
							throws InvocationTargetException,
							InterruptedException {
						DFAGraphProvider graphProvider = DFAGraphProviderRepository
								.create();
						final DotGraph[] graphs = graphProvider.dfa(monitor,
								grammar);
						monitor.done();
						Display.getDefault().asyncExec(new Runnable() {
							public void run() {
								decisionViewer.setInput(graphs);
							}
						});
					}
				});
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void setSelection(ISelection selection, boolean reveal) {

	}

	public void dispose() {
		if (control != null && !control.isDisposed()) {
			control.dispose();
			control = null;
		}

		if (graph != null && !graph.isDisposed()) {
			graph.dispose();
		}
		this.grammar = null;

		if (DARK_BLUE != null && !DARK_BLUE.isDisposed()) {
			DARK_BLUE.dispose();
			DARK_BLUE = null;
		}

		if (DEFAULT_NODE_COLOR != null && !DEFAULT_NODE_COLOR.isDisposed()) {
			DEFAULT_NODE_COLOR.dispose();
			DEFAULT_NODE_COLOR = null;
		}
	}

}
