/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *******************************************************************************/
package org.deved.antlride.viz.railroad;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.LinkedHashSet;

import org.deved.antlride.common.ui.AntlrImages;
import org.deved.antlride.core.model.IRule;
import org.deved.antlride.internal.viz.RuleBasedGraphViewer;
import org.deved.antlride.railroad.RailRoadGenerator;
import org.deved.antlride.ui.action.DropDownMenu;
import org.deved.antlride.viz.AntlrViz;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.draw2d.IFigure;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

public class RailRoadGraphViewer extends RuleBasedGraphViewer {

	private class ExportAsHtmlAction extends Action {
		public ExportAsHtmlAction() {
			super("Export to HTML", IAction.AS_PUSH_BUTTON);
		}

		@Override
		public void run() {
			exportToHtml();
		}
	}

	private class OptmizeGraphAction extends Action {

		public OptmizeGraphAction() {
			super("", IAction.AS_CHECK_BOX);
			setImageDescriptor(AntlrImages
					.getDescriptor(AntlrImages.OPTIMIZE_GRAPH));
			setToolTipText("Optimize Graph");
			setChecked(true);
		}

		@Override
		public void run() {
			optimizeGraph = !optimizeGraph;
			IRule selectedRule = getSelectedRule();
			if (selectedRule != null) {
				draw(selectedRule);
			}
		}
	}

	private class AppendGraphAction extends Action {

		public AppendGraphAction() {
			super("", IAction.AS_CHECK_BOX);
			setImageDescriptor(AntlrImages
					.getDescriptor(AntlrImages.APPEND_GRAPH));
			setToolTipText("Enqueue graph");
			setChecked(false);
		}

		@Override
		public void run() {
			append = !append;
			IRule selectedRule = getSelectedRule();
			if (selectedRule != null) {
				draw(selectedRule);
			}
		}
	}

	private DropDownMenu exportMenu;

	private boolean optimizeGraph = true;

	private boolean append = false;

	private Collection<IRule> rules;

	public RailRoadGraphViewer(Composite composite) {
		super(composite);
		rules = new LinkedHashSet<IRule>();
	}

	@Override
	protected IFigure doDraw(IRule rule) {
		if (!append) {
			rules.clear();
		}

		rules.add(rule);

		exportMenu.setEnabled(true);
		IFigure figure = RailRoadGenerator.generate(rules
				.toArray(new IRule[rules.size()]), optimizeGraph);
		return figure;
	}

	@Override
	protected void fillToolBar(IToolBarManager manager) {
		exportMenu = new DropDownMenu(new ExportAsHtmlAction(),
				new ExportAsImage("png", SWT.IMAGE_PNG), new ExportAsImage(
						"jpeg", SWT.IMAGE_JPEG));
		exportMenu.setImageDescriptor(AntlrImages
				.getDescriptor(AntlrImages.SAVE_AS));
		exportMenu.setToolTipText("Export diagram");

		manager.add(new Separator());
		manager.add(new OptmizeGraphAction());
		manager.add(new AppendGraphAction());
		manager.add(new Separator());
		manager.add(exportMenu);
	}

	private void exportToHtml() {
		try {
			final RailRoadExportDialog options = new RailRoadExportDialog(
					getControl().getShell());
			if (options.open() == Dialog.OK) {
				ProgressMonitorDialog progress = new ProgressMonitorDialog(
						getControl().getShell());
				progress.run(false, false, new IRunnableWithProgress() {

					public void run(IProgressMonitor monitor)
							throws InvocationTargetException,
							InterruptedException {
						try {
							new RailRoadGenerator().export(monitor, grammar,
									options);
							MessageDialog.open(MessageDialog.INFORMATION,
									getControl().getShell(),
									"Railroad exporter", grammar
											.getElementName()
											+ " successfully exported.",
									MessageDialog.OK);
						} catch (IOException ex) {
							throw new InvocationTargetException(ex);
						}
					}
				});
			}
		} catch (InvocationTargetException e) {
			ErrorDialog.openError(getControl().getShell(), "Railroad exporter",
					null, new Status(IStatus.ERROR, AntlrViz.PLUGIN_ID, grammar
							.getElementName()
							+ " can't be exported", e.getTargetException()));
			AntlrViz.error(e.getTargetException());
		} catch (InterruptedException e) {
			AntlrViz.error(e);
		}
	}

	@Override
	protected void onClearGraph() {
		rules.clear();
	}

	@Override
	protected boolean useGraphicsTransalate() {
		return true;
	}
}
