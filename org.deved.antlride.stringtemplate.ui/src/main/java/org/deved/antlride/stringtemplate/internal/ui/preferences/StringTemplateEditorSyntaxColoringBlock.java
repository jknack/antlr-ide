/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.stringtemplate.internal.ui.preferences;

import java.io.InputStream;

import org.deved.antlride.stringtemplate.internal.ui.StringTemplatePreferenceConstants;
import org.deved.antlride.stringtemplate.internal.ui.editor.StringTemplateDocumentSetupParticipant;
import org.deved.antlride.stringtemplate.internal.ui.text.StringTemplateSimpleSourceViewerConfiguration;
import org.eclipse.dltk.internal.ui.editor.ScriptSourceViewer;
import org.eclipse.dltk.ui.preferences.AbstractScriptEditorColoringConfigurationBlock;
import org.eclipse.dltk.ui.preferences.IPreferenceConfigurationBlock;
import org.eclipse.dltk.ui.preferences.OverlayPreferenceStore;
import org.eclipse.dltk.ui.text.IColorManager;
import org.eclipse.dltk.ui.text.ScriptSourceViewerConfiguration;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.source.IOverviewRuler;
import org.eclipse.jface.text.source.IVerticalRuler;
import org.eclipse.jface.text.source.projection.ProjectionViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.texteditor.ITextEditor;

public class StringTemplateEditorSyntaxColoringBlock extends
		AbstractScriptEditorColoringConfigurationBlock implements
		IPreferenceConfigurationBlock {

	private static final String PREVIEW_FILE_NAME = "stringtemplate.txt";

	private static final String[][] fSyntaxColorListModel = new String[][] {
			{
					StringTemplatePreferenceMessages.SyntaxColoringSinglelineComments,
					StringTemplatePreferenceConstants.EDITOR_STG_SINGLE_LINE_COMMENT_COLOR,
					sCommentsCategory },
			{
					StringTemplatePreferenceMessages.SyntaxColoringMultilineComments,
					StringTemplatePreferenceConstants.EDITOR_STG_MULTI_LINE_COMMENT_COLOR,
					sCommentsCategory },
			{
					StringTemplatePreferenceMessages.SyntaxColoringDocComments,
					StringTemplatePreferenceConstants.EDITOR_STG_DOCUMENT_COMMENT_COLOR,
					sCommentsCategory },
			{ StringTemplatePreferenceMessages.SyntaxColoringComments,
					StringTemplatePreferenceConstants.EDITOR_COMMENT_COLOR,
					sCommentsCategory },
			{ StringTemplatePreferenceMessages.SyntaxColoringKeywords,
					StringTemplatePreferenceConstants.EDITOR_KEYWORD_COLOR,
					sCoreCategory },

			{ StringTemplatePreferenceMessages.SyntaxColoringTemplateInGroup,
					StringTemplatePreferenceConstants.EDITOR_TEMPLATE_COLOR,
					sCoreCategory },
			{
					StringTemplatePreferenceMessages.SyntaxColoringAssignTemplateOperator,
					StringTemplatePreferenceConstants.EDITOR_ASSIGN_TEMPLATE_COLOR,
					sCoreCategory },
			{
					StringTemplatePreferenceMessages.SyntaxColoringTemplateRegion,
					StringTemplatePreferenceConstants.EDITOR_TEMPLATE_REGION_COLOR,
					sCoreCategory },
			{
					StringTemplatePreferenceMessages.SyntaxColoringTemplateDelimeters,
					StringTemplatePreferenceConstants.EDITOR_TEMPLATE_DELIMETERS_COLOR,
					sCoreCategory },
			{ StringTemplatePreferenceMessages.SyntaxColoringStrings,
					StringTemplatePreferenceConstants.EDITOR_STRING_COLOR,
					sCoreCategory },
			{
					StringTemplatePreferenceMessages.SyntaxColoringEspecialCharacters,
					StringTemplatePreferenceConstants.EDITOR_ESPECIAL_CHARACTERS_COLOR,
					sCoreCategory },
			{
					StringTemplatePreferenceMessages.SyntaxColoringTemplateGroupDelimeters,
					StringTemplatePreferenceConstants.EDITOR_DOUBLE_ANGLE_BRACKET_COLOR,
					sCoreCategory },
			{
					StringTemplatePreferenceMessages.SyntaxColoringOthers,
					StringTemplatePreferenceConstants.EDITOR_OTHERS_COLOR,
					sCoreCategory } };

	public StringTemplateEditorSyntaxColoringBlock(OverlayPreferenceStore store) {
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

	protected ScriptSourceViewerConfiguration createSimpleSourceViewerConfiguration(
			IColorManager colorManager, IPreferenceStore preferenceStore,
			ITextEditor editor, boolean configureFormatter) {
		return new StringTemplateSimpleSourceViewerConfiguration(colorManager,
				preferenceStore, editor);
	}

	protected void setDocumentPartitioning(IDocument document) {
		StringTemplateDocumentSetupParticipant participant = new StringTemplateDocumentSetupParticipant();
		participant.setup(document);
	}

	protected InputStream getPreviewContentReader() {
		return getClass().getResourceAsStream(PREVIEW_FILE_NAME);
	}
}
