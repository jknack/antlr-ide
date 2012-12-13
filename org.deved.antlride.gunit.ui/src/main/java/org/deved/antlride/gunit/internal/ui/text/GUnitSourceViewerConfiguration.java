/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.gunit.internal.ui.text;

import static org.eclipse.jface.text.IDocument.DEFAULT_CONTENT_TYPE;

import java.util.Map;

import org.deved.antlride.common.ui.SingleProjectProblem;
import org.deved.antlride.common.ui.text.AntlrCommonSourceViewerConfiguration;
import org.deved.antlride.gunit.internal.ui.text.highlighting.GUnitMultilineStringHighlightScanner;
import org.deved.antlride.gunit.internal.ui.text.highlighting.GUnitHighlightScanner;
import org.deved.antlride.gunit.internal.ui.text.highlighting.GUnitMultilineCommentHighlightScanner;
import org.deved.antlride.gunit.internal.ui.text.highlighting.GUnitSinglelineCommentHighlightScanner;
import org.deved.antlride.gunit.internal.ui.text.highlighting.GUnitSinglelineStringHighlightScanner;
import org.deved.antlride.gunit.internal.ui.text.partitions.GUnitPartitions;
import org.eclipse.dltk.ui.actions.IScriptEditorActionDefinitionIds;
import org.eclipse.dltk.ui.text.AbstractScriptScanner;
import org.eclipse.dltk.ui.text.IColorManager;
import org.eclipse.dltk.ui.text.completion.ContentAssistPreference;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.AbstractInformationControlManager;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IInformationControl;
import org.eclipse.jface.text.information.IInformationPresenter;
import org.eclipse.jface.text.information.IInformationProvider;
import org.eclipse.jface.text.information.InformationPresenter;
import org.eclipse.jface.text.reconciler.IReconciler;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.texteditor.ITextEditor;

public class GUnitSourceViewerConfiguration extends
		AntlrCommonSourceViewerConfiguration {
	public GUnitSourceViewerConfiguration(IColorManager colorManager,
			IPreferenceStore preferenceStore, ITextEditor editor,
			String partitioning) {
		super(colorManager, preferenceStore, editor, partitioning);
	}

	@Override
	protected void initializeScanners(
			Map<String, AbstractScriptScanner> scanners) {

		AbstractScriptScanner scanner;
		scanner = new GUnitHighlightScanner(getColorManager(),
				fPreferenceStore);
		scanners.put(DEFAULT_CONTENT_TYPE, scanner);
		// <<|>>
		scanner = new GUnitMultilineStringHighlightScanner(
				getColorManager(), fPreferenceStore);
		scanners.put(GUnitPartitions.MULTI_LINE_STRING, scanner);
		// <\...>
		// string
		scanner = new GUnitSinglelineStringHighlightScanner(
				getColorManager(), fPreferenceStore);

		scanners.put(GUnitPartitions.STRING, scanner);
		// comment's		
		scanner = new GUnitSinglelineCommentHighlightScanner(getColorManager(),
				fPreferenceStore);
		scanners.put(GUnitPartitions.SINGLE_LINE_COMMENT, scanner);
		
		scanner = new GUnitMultilineCommentHighlightScanner(getColorManager(),
				fPreferenceStore);
		scanners.put(GUnitPartitions.MULTI_LINE_COMMENT, scanner);
		
//		scanner = new StringTemplateSTGDocumentCommentHighlightScanner(getColorManager(),
//				fPreferenceStore);
//		scanners.put(StringTemplatePartitions.STG_DOC_COMMENT, scanner);
	}

	@Override
	public String[] getConfiguredContentTypes(ISourceViewer sourceViewer) {
		return GUnitPartitions.PARTITION_TYPES;
	}
	
	@Override
	@SingleProjectProblem
	public IInformationPresenter getOutlinePresenter(
			ISourceViewer sourceViewer, boolean doCodeResolve) {
		InformationPresenter presenter;
		if (doCodeResolve)
			presenter = new GUnitInformationPresenter(
					getOutlinePresenterControlCreator(sourceViewer,
							IScriptEditorActionDefinitionIds.OPEN_STRUCTURE));
		else
			presenter = new GUnitInformationPresenter(
					getOutlinePresenterControlCreator(sourceViewer,
							IScriptEditorActionDefinitionIds.SHOW_OUTLINE));
		presenter
				.setDocumentPartitioning(getConfiguredDocumentPartitioning(sourceViewer));
		presenter.setAnchor(AbstractInformationControlManager.ANCHOR_GLOBAL);
		IInformationProvider provider = new GUnitElementProvider(getEditor(),
				doCodeResolve);
		presenter.setInformationProvider(provider,
				IDocument.DEFAULT_CONTENT_TYPE);
		initializeQuickOutlineContexts(presenter, provider);

		presenter.setSizeConstraints(50, 20, true, false);
		return presenter;
	}
	
	@Override
	protected ContentAssistPreference getContentAssistPreference() {
		return GUnitContentAssistPreference.getDefault();
	}
	
	@Override
	public ITextEditor getEditor() {
		return super.getEditor();
	}
	
	@Override
	protected IInformationControl getOutlineInformationControl(Shell parent,
			int shellStyle, int treeStyle, String commandId) {
		return new GUnitOutlineInformationControl(getEditor(), parent, shellStyle, treeStyle, commandId);
	}
		
	@Override
	public IReconciler getReconciler(ISourceViewer sourceViewer) {
		return null;
	}
}
