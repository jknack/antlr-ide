/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.internal.ui.text;

import static org.deved.antlride.core.AntlrConstants.ANTLR_BRACKET;
import static org.deved.antlride.core.AntlrConstants.ANTLR_GRAMMAR_ACTION;
import static org.deved.antlride.core.AntlrConstants.ANTLR_GRAMMAR_DECLARATION;
import static org.deved.antlride.core.AntlrConstants.ANTLR_IMPORT;
import static org.deved.antlride.core.AntlrConstants.ANTLR_MULTI_LINE_COMMENT;
import static org.deved.antlride.core.AntlrConstants.ANTLR_OPTIONS;
import static org.deved.antlride.core.AntlrConstants.ANTLR_RULE_ACTION;
import static org.deved.antlride.core.AntlrConstants.ANTLR_SCOPE;
import static org.deved.antlride.core.AntlrConstants.ANTLR_SINGLE_LINE_COMMENT;
import static org.deved.antlride.core.AntlrConstants.ANTLR_STRING;
import static org.deved.antlride.core.AntlrConstants.ANTLR_TARGET_ACTION;
import static org.deved.antlride.core.AntlrConstants.ANTLR_TOKENS;
import static org.eclipse.jface.text.IDocument.DEFAULT_CONTENT_TYPE;

import java.util.Map;

import org.deved.antlride.common.ui.text.AntlrCommonSourceViewerConfiguration;
import org.deved.antlride.internal.ui.editor.AntlrEditor;
import org.deved.antlride.internal.ui.text.completion.AntlrCompletionProcessor;
import org.deved.antlride.internal.ui.text.highlighting.AntlrBracketHighlightScanner;
import org.deved.antlride.internal.ui.text.highlighting.AntlrCodeHighlightScanner;
import org.deved.antlride.internal.ui.text.highlighting.AntlrGrammarActionHighlightScanner;
import org.deved.antlride.internal.ui.text.highlighting.AntlrGrammarDeclarationHighlightScanner;
import org.deved.antlride.internal.ui.text.highlighting.AntlrImportHighlightScanner;
import org.deved.antlride.internal.ui.text.highlighting.AntlrMultilineCommentScanner;
import org.deved.antlride.internal.ui.text.highlighting.AntlrOptionsHighlightScanner;
import org.deved.antlride.internal.ui.text.highlighting.AntlrRuleActionHighlightScanner;
import org.deved.antlride.internal.ui.text.highlighting.AntlrScopeHighlightScanner;
import org.deved.antlride.internal.ui.text.highlighting.AntlrSinglelineCommentScanner;
import org.deved.antlride.internal.ui.text.highlighting.AntlrStringHighlightScanner;
import org.deved.antlride.internal.ui.text.highlighting.AntlrTargetActionHighlightScanner;
import org.deved.antlride.internal.ui.text.highlighting.AntlrTokensHighlightScanner;
import org.deved.antlride.ui.text.AntlrTextPartitions;
import org.eclipse.dltk.ui.text.AbstractScriptScanner;
import org.eclipse.dltk.ui.text.IColorManager;
import org.eclipse.dltk.ui.text.completion.ContentAssistPreference;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.DefaultIndentLineAutoEditStrategy;
import org.eclipse.jface.text.IAutoEditStrategy;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IInformationControl;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.jface.text.source.ISourceViewer;
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
public class AntlrSourceViewerConfiguration extends
		AntlrCommonSourceViewerConfiguration {

	public AntlrSourceViewerConfiguration(IColorManager colorManager,
			IPreferenceStore preferenceStore, ITextEditor editor,
			String partitioning) {
		super(colorManager, preferenceStore, editor, partitioning);
	}

	@Override
	public IAutoEditStrategy[] getAutoEditStrategies(
			ISourceViewer sourceViewer, String contentType) {
		String partitioning = getConfiguredDocumentPartitioning(sourceViewer);
		return new IAutoEditStrategy[] {
				new DefaultIndentLineAutoEditStrategy(),
				new AntlrAutoClosePairEditStrategy(fPreferenceStore,
						partitioning) };
	}

	@Override
	public String[] getConfiguredContentTypes(ISourceViewer sourceViewer) {
		return AntlrTextPartitions.ANTLR3_PARTITION_TYPES;
	}

	@Override
	protected void alterContentAssistant(ContentAssistant assistant) {
		if (getEditor() != null) {
			AntlrCompletionProcessor completionProcessor = new AntlrCompletionProcessor(
					getEditor(), assistant, IDocument.DEFAULT_CONTENT_TYPE);

			assistant.setContentAssistProcessor(completionProcessor,
					IDocument.DEFAULT_CONTENT_TYPE);

			assistant.setContentAssistProcessor(completionProcessor,
					AntlrTextPartitions.ANTLR_OPTIONS);

			assistant.setContentAssistProcessor(completionProcessor,
					AntlrTextPartitions.ANTLR_RULE_ACTION);
			
			assistant.setContentAssistProcessor(completionProcessor,
					AntlrTextPartitions.ANTLR_TARGET_ACTION);

			assistant.setContentAssistProcessor(completionProcessor,
					AntlrTextPartitions.ANTLR_BRACKET);

			AntlrContentAssistPreference.getDefault().configure(assistant,
					fPreferenceStore);
		}
	}
	
	@Override
	protected IInformationControl getOutlineInformationControl(Shell parent,
			int shellStyle, int treeStyle, String commandId) {
		return new AntlrOutlineInformationControl(parent, shellStyle,
				treeStyle, commandId);
	}

	@Override
	public AntlrEditor getEditor() {
		return (AntlrEditor) super.getEditor();
	}
	
	@Override
	protected String getCommentPrefix() {
		return "//";
	}

	@Override
	protected void initializeScanners(Map<String, AbstractScriptScanner> scanners) {

		AbstractScriptScanner codeScanner = new AntlrCodeHighlightScanner(
				getColorManager(), fPreferenceStore, getEditor());

		scanners.put(DEFAULT_CONTENT_TYPE, codeScanner);

		AbstractScriptScanner grammarDeclScanner = new AntlrGrammarDeclarationHighlightScanner(
				getColorManager(), fPreferenceStore);

		scanners.put(ANTLR_GRAMMAR_DECLARATION, grammarDeclScanner);

		AbstractScriptScanner optionsScanner = new AntlrOptionsHighlightScanner(
				getColorManager(), fPreferenceStore);

		scanners.put(ANTLR_OPTIONS, optionsScanner);

		AbstractScriptScanner importScanner = new AntlrImportHighlightScanner(
				getColorManager(), fPreferenceStore, getEditor());
		scanners.put(ANTLR_IMPORT, importScanner);

		AbstractScriptScanner tokensScanner = new AntlrTokensHighlightScanner(
				getColorManager(), fPreferenceStore);
		scanners.put(ANTLR_TOKENS, tokensScanner);

		AbstractScriptScanner scopeScanner = new AntlrScopeHighlightScanner(
				getColorManager(), fPreferenceStore, getEditor());

		scanners.put(ANTLR_SCOPE, scopeScanner);

		AbstractScriptScanner stringScanner = new AntlrStringHighlightScanner(
				getColorManager(), fPreferenceStore);

		scanners.put(ANTLR_STRING, stringScanner);

		AbstractScriptScanner grammarActionScanner = new AntlrGrammarActionHighlightScanner(
				getColorManager(), fPreferenceStore, getEditor());

		scanners.put(ANTLR_GRAMMAR_ACTION, grammarActionScanner);

		AbstractScriptScanner ruleActionScanner = new AntlrRuleActionHighlightScanner(
				getColorManager(), fPreferenceStore, getEditor());

		scanners.put(ANTLR_RULE_ACTION, ruleActionScanner);

		AbstractScriptScanner bracketScanner = new AntlrBracketHighlightScanner(
				getColorManager(), fPreferenceStore, getEditor());

		scanners.put(ANTLR_BRACKET, bracketScanner);

		AbstractScriptScanner targetActionScanner = new AntlrTargetActionHighlightScanner(
				getColorManager(), fPreferenceStore, getEditor());

		scanners.put(ANTLR_TARGET_ACTION, targetActionScanner);

		AbstractScriptScanner singlelineCommentScanner = new AntlrSinglelineCommentScanner(
				getColorManager(), fPreferenceStore);

		scanners.put(ANTLR_SINGLE_LINE_COMMENT, singlelineCommentScanner);

		AbstractScriptScanner multilineCommentScanner = new AntlrMultilineCommentScanner(
				getColorManager(), fPreferenceStore);

		scanners.put(ANTLR_MULTI_LINE_COMMENT, multilineCommentScanner);
	}

	@Override
	protected ContentAssistPreference getContentAssistPreference() {
		return AntlrContentAssistPreference.getDefault();
	}
}
