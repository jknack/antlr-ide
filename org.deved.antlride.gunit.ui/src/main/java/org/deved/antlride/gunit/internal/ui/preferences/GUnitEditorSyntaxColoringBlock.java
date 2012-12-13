/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.gunit.internal.ui.preferences;

import java.io.InputStream;

import org.deved.antlride.gunit.internal.ui.GUnitPreferenceConstants;
import org.deved.antlride.gunit.internal.ui.editor.GUnitDocumentSetupParticipant;
import org.deved.antlride.gunit.internal.ui.text.GUnitSimpleSourceViewerConfiguration;
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

public class GUnitEditorSyntaxColoringBlock extends
		AbstractScriptEditorColoringConfigurationBlock implements
		IPreferenceConfigurationBlock {

	private static final String PREVIEW_FILE_NAME = "gunit.txt";

	private static final String[][] fSyntaxColorListModel = new String[][] {
			{ GUnitPreferenceMessages.SyntaxColoringSinglelineComments,
					GUnitPreferenceConstants.EDITOR_SINGLE_LINE_COMMENT_COLOR,
					sCommentsCategory },
			{ GUnitPreferenceMessages.SyntaxColoringMultilineComments,
					GUnitPreferenceConstants.EDITOR_MULTI_LINE_COMMENT_COLOR,
					sCommentsCategory },
			{ GUnitPreferenceMessages.SyntaxColoringDocComments,
					GUnitPreferenceConstants.EDITOR_DOC_COMMENT_COLOR,
					sCommentsCategory },
			{ GUnitPreferenceMessages.SyntaxColoringKeywords,
					GUnitPreferenceConstants.EDITOR_KEYWORD_COLOR,
					sCoreCategory },
			{ GUnitPreferenceMessages.SyntaxColoringOkKeyword,
					GUnitPreferenceConstants.EDITOR_OK_KEYWORD_COLOR,
					sCoreCategory },
			{ GUnitPreferenceMessages.SyntaxColoringHeaderDirective,
					GUnitPreferenceConstants.EDITOR_DIRECTIVE_KEYWORD_COLOR,
					sCoreCategory },
			{ GUnitPreferenceMessages.SyntaxColoringMLStringOperators,
					GUnitPreferenceConstants.EDITOR_ML_STRING_OPERATORS_COLOR,
					sCoreCategory },
			{ GUnitPreferenceMessages.SyntaxColoringExpectedResultOperator,
					GUnitPreferenceConstants.EDITOR_EXPECT_OPERATOR_COLOR,
					sCoreCategory },
			{ GUnitPreferenceMessages.SyntaxColoringFailKeyword,
					GUnitPreferenceConstants.EDITOR_FAIL_KEYWORD_COLOR,
					sCoreCategory },
			{ GUnitPreferenceMessages.SyntaxColoringTestSuite,
					GUnitPreferenceConstants.EDITOR_TEST_COLOR, sCoreCategory },
			{ GUnitPreferenceMessages.SyntaxColoringStrings,
					GUnitPreferenceConstants.EDITOR_STRING_COLOR, sCoreCategory },
			{ GUnitPreferenceMessages.SyntaxColoringMultileStrings,
					GUnitPreferenceConstants.EDITOR_ML_STRING_COLOR,
					sCoreCategory },
			{ GUnitPreferenceMessages.SyntaxColoringOthers,
					GUnitPreferenceConstants.EDITOR_OTHERS_COLOR, sCoreCategory } };

	public GUnitEditorSyntaxColoringBlock(OverlayPreferenceStore store) {
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
		return new GUnitSimpleSourceViewerConfiguration(colorManager,
				preferenceStore, editor);
	}

	protected void setDocumentPartitioning(IDocument document) {
		GUnitDocumentSetupParticipant participant = new GUnitDocumentSetupParticipant();
		participant.setup(document);
	}

	protected InputStream getPreviewContentReader() {
		return getClass().getResourceAsStream(PREVIEW_FILE_NAME);
	}
}
