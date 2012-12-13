/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.common.ui.text;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.eclipse.dltk.internal.ui.editor.ScriptSourceViewer;
import org.eclipse.dltk.ui.CodeFormatterConstants;
import org.eclipse.dltk.ui.text.AbstractScriptScanner;
import org.eclipse.dltk.ui.text.IColorManager;
import org.eclipse.dltk.ui.text.ScriptPresentationReconciler;
import org.eclipse.dltk.ui.text.ScriptSourceViewerConfiguration;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.DefaultInformationControl;
import org.eclipse.jface.text.IInformationControl;
import org.eclipse.jface.text.IInformationControlCreator;
import org.eclipse.jface.text.information.IInformationPresenter;
import org.eclipse.jface.text.information.IInformationProvider;
import org.eclipse.jface.text.information.InformationPresenter;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.texteditor.ITextEditor;

/*******************************************************************************
 * The application's SourceViewerConfiguration subclass plays an important role
 * because it is used to add a range of features to your application, including
 * text formatting, syntax highlighting, double click support, text hovering and
 * content assistance. In fact, most of the value added features we discussed
 * earlier in the article are introduced to the application through the
 * SourceViewerConfiguration
 * 
 * 
 */
public abstract class AntlrCommonSourceViewerConfiguration extends
		ScriptSourceViewerConfiguration {

	private Map<String, AbstractScriptScanner> scanners;

	public AntlrCommonSourceViewerConfiguration(IColorManager colorManager,
			IPreferenceStore preferenceStore, ITextEditor editor,
			String partitioning) {
		super(colorManager, preferenceStore, editor, partitioning);
	}

	@Override
	public boolean affectsTextPresentation(PropertyChangeEvent event) {
		Collection<AbstractScriptScanner> scanners = this.scanners.values();
		for (AbstractScriptScanner scanner : scanners) {
			if (scanner.affectsBehavior(event)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public IInformationPresenter getHierarchyPresenter(
			ScriptSourceViewer viewer, boolean b) {
		return null;
	}

	@Override
	public String[] getIndentPrefixes(ISourceViewer sourceViewer,
			String contentType) {
		return new String[] { "\t", " " };
	}

	@Override
	public IPresentationReconciler getPresentationReconciler(
			ISourceViewer sourceViewer) {
		PresentationReconciler reconciler = new ScriptPresentationReconciler();
		reconciler
				.setDocumentPartitioning(getConfiguredDocumentPartitioning(sourceViewer));

		DefaultDamagerRepairer dr;
		Set<String> contentTypes = scanners.keySet();
		for (String contentType : contentTypes) {
			AbstractScriptScanner scanner = scanners.get(contentType);
			dr = new DefaultDamagerRepairer(scanner);
			reconciler.setDamager(dr, contentType);
			reconciler.setRepairer(dr, contentType);
		}
		return reconciler;
	}

	@Override
	public int getTabWidth(ISourceViewer sourceViewer) {
		if (fPreferenceStore == null)
			return super.getTabWidth(sourceViewer);
		return fPreferenceStore
				.getInt(CodeFormatterConstants.FORMATTER_TAB_SIZE);
	}

	@Override
	public void handlePropertyChangeEvent(PropertyChangeEvent event) {
		Collection<AbstractScriptScanner> scanners = this.scanners.values();
		for (AbstractScriptScanner scanner : scanners) {
			if (scanner.affectsBehavior(event)) {
				scanner.adaptToPreferenceChange(event);
				return;
			}
		}
	}

	@Override
	protected IInformationControlCreator getOutlinePresenterControlCreator(
			ISourceViewer sourceViewer, final String commandId) {
		return new IInformationControlCreator() {
			public IInformationControl createInformationControl(Shell parent) {
				int shellStyle = SWT.RESIZE;
				int treeStyle = SWT.V_SCROLL | SWT.H_SCROLL;
				return getOutlineInformationControl(parent, shellStyle,
						treeStyle, commandId);
			}
		};
	}

	protected IInformationControl getOutlineInformationControl(Shell parent,
			int shellStyle, int treeStyle, String commandId) {
		return new DefaultInformationControl(parent);
	}

	@Override
	public IInformationControlCreator getInformationControlCreator(
			ISourceViewer sourceViewer) {
		return super.getInformationControlCreator(sourceViewer);
	}

	@Override
	protected void initializeQuickOutlineContexts(
			InformationPresenter presenter, IInformationProvider provider) {
		Set<String> contentTypes = scanners.keySet();
		for (String contentType : contentTypes) {
			presenter.setInformationProvider(provider, contentType);
		}
	}

	@Override
	protected final void initializeScanners() {
		scanners = new LinkedHashMap<String, AbstractScriptScanner>();
		initializeScanners(scanners);
		super.initializeScanners();
	}

	protected abstract void initializeScanners(
			Map<String, AbstractScriptScanner> scanners);
}
