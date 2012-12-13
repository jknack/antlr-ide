/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.stringtemplate.internal.ui.text;

import static org.eclipse.jface.text.IDocument.DEFAULT_CONTENT_TYPE;

import java.util.Map;

import org.deved.antlride.common.ui.text.AntlrCommonSourceViewerConfiguration;
import org.deved.antlride.stringtemplate.internal.ui.text.highlighting.StringTemplateBuildInStringHighlightScanner;
import org.deved.antlride.stringtemplate.internal.ui.text.highlighting.StringTemplateCodeHighlightScanner;
import org.deved.antlride.stringtemplate.internal.ui.text.highlighting.StringTemplateCommentHighlightScanner;
import org.deved.antlride.stringtemplate.internal.ui.text.highlighting.StringTemplateDoubleAngleBracketHighlightScanner;
import org.deved.antlride.stringtemplate.internal.ui.text.highlighting.StringTemplateEspecialCharactersHighlightScanner;
import org.deved.antlride.stringtemplate.internal.ui.text.highlighting.StringTemplateMapHighlightScanner;
import org.deved.antlride.stringtemplate.internal.ui.text.highlighting.StringTemplateSTGMultilineCommentHighlightScanner;
import org.deved.antlride.stringtemplate.internal.ui.text.highlighting.StringTemplateSTGSinglelineCommentHighlightScanner;
import org.deved.antlride.stringtemplate.internal.ui.text.highlighting.StringTemplateSinglelineStringHighlightScanner;
import org.deved.antlride.stringtemplate.internal.ui.text.highlighting.StringTemplateTemplateBodyHighlightScanner;
import org.deved.antlride.stringtemplate.internal.ui.text.highlighting.StringTemplateTemplateHighlightScanner;
import org.deved.antlride.stringtemplate.internal.ui.text.highlighting.StringTemplateTemplateRegionHighlightScanner;
import org.deved.antlride.stringtemplate.internal.ui.text.partitions.StringTemplatePartitions;
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

public class StringTemplateSourceViewerConfiguration extends
		AntlrCommonSourceViewerConfiguration {
	public StringTemplateSourceViewerConfiguration(IColorManager colorManager,
			IPreferenceStore preferenceStore, ITextEditor editor,
			String partitioning) {
		super(colorManager, preferenceStore, editor, partitioning);
	}

	@Override
	protected void initializeScanners(
			Map<String, AbstractScriptScanner> scanners) {

		AbstractScriptScanner scanner;
		scanner = new StringTemplateCodeHighlightScanner(getColorManager(),
				fPreferenceStore);
		scanners.put(DEFAULT_CONTENT_TYPE, scanner);
		// template
		scanner = new StringTemplateTemplateHighlightScanner(getColorManager(),
				fPreferenceStore);
		scanners.put(StringTemplatePartitions.TEMPLATE, scanner);
		// <<|>>
		scanner = new StringTemplateDoubleAngleBracketHighlightScanner(
				getColorManager(), fPreferenceStore);
		scanners.put(StringTemplatePartitions.DOUBLE_ANGLE_BRACKETS, scanner);
		// [...]
		scanner = new StringTemplateMapHighlightScanner(getColorManager(),
				fPreferenceStore);
		scanners.put(StringTemplatePartitions.MAP, scanner);
		// <@...>
		scanner = new StringTemplateTemplateRegionHighlightScanner(
				getColorManager(), fPreferenceStore);
		scanners.put(StringTemplatePartitions.TEMPLATE_REGION, scanner);
		// <\...>
		scanner = new StringTemplateEspecialCharactersHighlightScanner(
				getColorManager(), fPreferenceStore);
		scanners.put(StringTemplatePartitions.ESPECIAL_CHARACTERS, scanner);
		// build in string: "<...>"
		scanner = new StringTemplateBuildInStringHighlightScanner(
				getColorManager(), fPreferenceStore);
		scanners.put(StringTemplatePartitions.BUILD_IN_STRING, scanner);
		// template body: <...>
		scanner = new StringTemplateTemplateBodyHighlightScanner(
				getColorManager(), fPreferenceStore);
		scanners.put(StringTemplatePartitions.TEMPLATE_BODY, scanner);
		// string
		scanner = new StringTemplateSinglelineStringHighlightScanner(
				getColorManager(), fPreferenceStore);

		scanners.put(StringTemplatePartitions.STRING, scanner);
		// comment's
		scanner = new StringTemplateCommentHighlightScanner(getColorManager(),
				fPreferenceStore);
		scanners.put(StringTemplatePartitions.COMMENT, scanner);

		scanner = new StringTemplateSTGSinglelineCommentHighlightScanner(
				getColorManager(), fPreferenceStore);
		scanners.put(StringTemplatePartitions.STG_SINGLE_LINE_COMMENT, scanner);

		scanner = new StringTemplateSTGMultilineCommentHighlightScanner(
				getColorManager(), fPreferenceStore);
		scanners.put(StringTemplatePartitions.STG_MULTI_LINE_COMMENT, scanner);

		// scanner = new
		// StringTemplateSTGDocumentCommentHighlightScanner(getColorManager(),
		// fPreferenceStore);
		// scanners.put(StringTemplatePartitions.STG_DOC_COMMENT, scanner);
	}

	@Override
	public String[] getConfiguredContentTypes(ISourceViewer sourceViewer) {
		return StringTemplatePartitions.PARTITION_TYPES;
	}

	@Override
	public IInformationPresenter getOutlinePresenter(
			ISourceViewer sourceViewer, boolean doCodeResolve) {
		InformationPresenter presenter;
		if (doCodeResolve)
			presenter = new StringTemplateInformationPresenter(
					getOutlinePresenterControlCreator(sourceViewer,
							IScriptEditorActionDefinitionIds.OPEN_STRUCTURE));
		else
			presenter = new StringTemplateInformationPresenter(
					getOutlinePresenterControlCreator(sourceViewer,
							IScriptEditorActionDefinitionIds.SHOW_OUTLINE));
		presenter
				.setDocumentPartitioning(getConfiguredDocumentPartitioning(sourceViewer));
		presenter.setAnchor(AbstractInformationControlManager.ANCHOR_GLOBAL);
		IInformationProvider provider = new StringTemplateElementProvider(
				getEditor(), doCodeResolve);
		presenter.setInformationProvider(provider,
				IDocument.DEFAULT_CONTENT_TYPE);
		initializeQuickOutlineContexts(presenter, provider);

		presenter.setSizeConstraints(50, 20, true, false);
		return presenter;
	}

	@Override
	protected ContentAssistPreference getContentAssistPreference() {
		return StringTemplateContentAssistPreference.getDefault();
	}

	@Override
	protected IInformationControl getOutlineInformationControl(Shell parent,
			int shellStyle, int treeStyle, String commandId) {
		return new StringTemplateOutlineInformationControl(getEditor(), parent,
				shellStyle, treeStyle, commandId);
	}

	@Override
	public IReconciler getReconciler(ISourceViewer sourceViewer) {
		return null;
	}
}
