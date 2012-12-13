/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.internal.ui.preferences;

import java.io.InputStream;

import org.deved.antlride.internal.ui.editor.AntlrDocumentSetupParticipant;
import org.deved.antlride.internal.ui.text.AntlrSimpleSourceViewerConfiguration;
import org.deved.antlride.ui.AntlrPreferenceConstants;
import org.deved.antlride.ui.AntlrUI;
import org.deved.antlride.ui.text.AntlrTextPartitions;
import org.eclipse.dltk.internal.ui.editor.ScriptSourceViewer;
import org.eclipse.dltk.ui.preferences.AbstractScriptEditorColoringConfigurationBlock;
import org.eclipse.dltk.ui.preferences.IPreferenceConfigurationBlock;
import org.eclipse.dltk.ui.preferences.OverlayPreferenceStore;
import org.eclipse.dltk.ui.preferences.PreferencesMessages;
import org.eclipse.dltk.ui.text.IColorManager;
import org.eclipse.dltk.ui.text.ScriptSourceViewerConfiguration;
import org.eclipse.dltk.ui.text.ScriptTextTools;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.source.IOverviewRuler;
import org.eclipse.jface.text.source.IVerticalRuler;
import org.eclipse.jface.text.source.projection.ProjectionViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.texteditor.ITextEditor;

public class AntlrEditorSyntaxColoringBlock extends
		AbstractScriptEditorColoringConfigurationBlock implements
		IPreferenceConfigurationBlock {

	private static final String PREVIEW_FILE_NAME = "PreviewFile.txt";

	private static final String[][] fSyntaxColorListModel = new String[][] {
			{
					AntlrPreferenceMessages.ANTLREditorColoringConfigurationBlock_single_line_comment,
					AntlrPreferenceConstants.EDITOR_SINGLE_LINE_COMMENT_COLOR,
					sCommentsCategory },
			{
					AntlrPreferenceMessages.ANTLREditorColoringConfigurationBlock_multi_line_comment,
					AntlrPreferenceConstants.EDITOR_MULTI_LINE_COMMENT_COLOR,
					sCommentsCategory },
			{
					AntlrPreferenceMessages.ANTLREditorColoringConfigurationBlock_doc_comment,
					AntlrPreferenceConstants.EDITOR_DOC_COMMENT_COLOR,
					sCommentsCategory },
			{
					AntlrPreferenceMessages.ANTLREditorColoringConfigurationBlock_target_language_comments,
					AntlrPreferenceConstants.EDITOR_TARGET_LANGUAGE_COMMENT_COLOR,
					sCommentsCategory },
			{
					AntlrPreferenceMessages.ANTLREditorColoringConfigurationBlock_keywords,
					AntlrPreferenceConstants.EDITOR_KEYWORD_COLOR,
					sCoreCategory },

			{
					AntlrPreferenceMessages.ANTLREditorColoringConfigurationBlock_rules,
					AntlrPreferenceConstants.EDITOR_RULE_COLOR, sCoreCategory },

			{
					AntlrPreferenceMessages.ANTLREditorColoringConfigurationBlock_lexer_rules,
					AntlrPreferenceConstants.EDITOR_LEXER_RULE_COLOR,
					sCoreCategory },
			{
					AntlrPreferenceMessages.ANTLREditorColoringConfigurationBlock_local_variable_declarations,
					AntlrPreferenceConstants.EDITOR_LOCAL_VAR_DECLARATION_COLOR,
					sCoreCategory },
			{
					AntlrPreferenceMessages.ANTLREditorColoringConfigurationBlock_local_variable_references,
					AntlrPreferenceConstants.EDITOR_LOCAL_VAR_REFERENCE_COLOR,
					sCoreCategory },
			{
					AntlrPreferenceMessages.ANTLREditorColoringConfigurationBlock_grammar_name,
					AntlrPreferenceConstants.EDITOR_GRAMMAR_NAME_COLOR,
					sCoreCategory },
			{
					AntlrPreferenceMessages.ANTLREditorColoringConfigurationBlock_grammar_actions,
					AntlrPreferenceConstants.EDITOR_GRAMMAR_ACTION_COLOR,
					sCoreCategory },
			{
					AntlrPreferenceMessages.ANTLREditorColoringConfigurationBlock_invalid_grammar_actions,
					AntlrPreferenceConstants.EDITOR_UNKNOW_GRAMMAR_ACTION_COLOR,
					sCoreCategory },
			{
					AntlrPreferenceMessages.ANTLREditorColoringConfigurationBlock_keyword_returns,
					AntlrPreferenceConstants.EDITOR_KEYWORD_RETURNS_COLOR,
					sCoreCategory },
			{
					AntlrPreferenceMessages.ANTLREditorColoringConfigurationBlock_target_language_keywords,
					AntlrPreferenceConstants.EDITOR_TARGET_LANGUAGE_KEYWORD_COLOR,
					sCoreCategory },
			{
					AntlrPreferenceMessages.ANTLREditorColoringConfigurationBlock_target_language_literals,
					AntlrPreferenceConstants.EDITOR_TARGET_LANGUAGE_LITERAL_COLOR,
					sCoreCategory },
			{
					AntlrPreferenceMessages.ANTLREditorColoringConfigurationBlock_ebnf_operators,
					AntlrPreferenceConstants.EDITOR_EBNF_OPERATORS_COLOR,
					sCoreCategory },
			{
					AntlrPreferenceMessages.ANTLREditorColoringConfigurationBlock_tree_operators,
					AntlrPreferenceConstants.EDITOR_TREE_OPERATORS_COLOR,
					sCoreCategory },
			{
					AntlrPreferenceMessages.ANTLREditorColoringConfigurationBlock_rewrite_operator,
					AntlrPreferenceConstants.EDITOR_REWRITE_OPERATOR_COLOR,
					sCoreCategory },
			{ PreferencesMessages.DLTKEditorPreferencePage_strings,
					AntlrPreferenceConstants.EDITOR_STRING_COLOR, sCoreCategory },
			{
					AntlrPreferenceMessages.ANTLREditorColoringConfigurationBlock_others,
					AntlrPreferenceConstants.EDITOR_OTHERS_COLOR, sCoreCategory } };

	public AntlrEditorSyntaxColoringBlock(OverlayPreferenceStore store) {
		super(store);
	}

	protected String[][] getSyntaxColorListModel() {
		return fSyntaxColorListModel;
	}

	protected ProjectionViewer createPreviewViewer(Composite parent,
			IVerticalRuler verticalRuler, IOverviewRuler overviewRuler,
			boolean showAnnotationsOverview, int styles, IPreferenceStore store) {
		return new ScriptSourceViewer(parent, verticalRuler, overviewRuler,
				showAnnotationsOverview, styles, store);
	}
	
	@Override
	protected ScriptTextTools getTextTools() {
		return AntlrUI.getDefault().getTextTools();
	}

	protected ScriptSourceViewerConfiguration createSimpleSourceViewerConfiguration(
			IColorManager colorManager, IPreferenceStore preferenceStore,
			ITextEditor editor, boolean configureFormatter) {
		return new AntlrSimpleSourceViewerConfiguration(colorManager,
				preferenceStore, editor,
				AntlrTextPartitions.ANTLR_PARTITIONING, configureFormatter);
	}

	protected void setDocumentPartitioning(IDocument document) {
		AntlrDocumentSetupParticipant participant = new AntlrDocumentSetupParticipant();
		participant.setup(document);
	}

	protected InputStream getPreviewContentReader() {
		return getClass().getResourceAsStream(PREVIEW_FILE_NAME);
	}
}
