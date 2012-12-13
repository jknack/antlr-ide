/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.common.ui.text.editor;

import org.deved.antlride.common.ui.SingleProjectProblem;
import org.deved.antlride.common.ui.text.folding.AntlrAbstractFoldingStructureProvider;
import org.eclipse.core.filebuffers.IDocumentSetupParticipant;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.internal.ui.actions.FoldingActionGroup;
import org.eclipse.dltk.internal.ui.editor.ScriptEditor;
import org.eclipse.dltk.internal.ui.editor.ScriptOutlinePage;
import org.eclipse.dltk.internal.ui.editor.SourceModuleDocumentProvider;
import org.eclipse.dltk.ui.text.ScriptTextTools;
import org.eclipse.dltk.ui.text.folding.IElementCommentResolver;
import org.eclipse.dltk.ui.text.folding.IFoldingStructureProvider;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentExtension3;
import org.eclipse.jface.text.IDocumentPartitioner;
import org.eclipse.jface.text.source.DefaultCharacterPairMatcher;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.texteditor.SourceViewerDecorationSupport;

@SuppressWarnings("restriction")
public abstract class AntlrBaseEditor extends ScriptEditor {

	private IFoldingStructureProvider fFoldingStructureProvider;

	/**
	 * DLTK BUG: can't define more than one language per project flag use to
	 * retrieve source module directly from document provider
	 * 
	 * @see AntlrBaseEditor#getInputModelElement()
	 */
	private boolean fUseDocumentProvider;

	public AntlrBaseEditor(boolean useDocumentProvider) {
		fUseDocumentProvider = useDocumentProvider;
	}

	public AntlrBaseEditor() {
		this(false);
	}

	@SuppressWarnings("unchecked")
	@Override	
	@SingleProjectProblem
	public Object getAdapter(Class required) {
		if (required == IElementCommentResolver.class) {
			// used by dltk.mylyn integration
			// FIXME do not reference AbstractASTFoldingStructureProvider
			@SingleProjectProblem
			IFoldingStructureProvider fsp = getFoldingStructureProvider();
			if (fsp instanceof AntlrAbstractFoldingStructureProvider) {
				AntlrAbstractFoldingStructureProvider foldingStructureProvider = (AntlrAbstractFoldingStructureProvider) fsp;
				return foldingStructureProvider.createElementCommentResolver(
						(ISourceModule) getInputModelElement(),
						getSourceViewer().getDocument().get());
			}

		}
		return super.getAdapter(required);
	}

	@Override
	@SingleProjectProblem
	public IModelElement getInputModelElement() {
		if (fUseDocumentProvider) {
			return getInputModelElementFromDocumentProvider();
		}
		return super.getInputModelElement();
	}

	@SingleProjectProblem
	private IModelElement getInputModelElementFromDocumentProvider() {
		IEditorInput editorInput = getEditorInput();
		if (editorInput == null)
			return null;
		SourceModuleDocumentProvider documentProvider = (SourceModuleDocumentProvider) getDocumentProvider();
		ISourceModule workingCopy = documentProvider
				.getWorkingCopy(editorInput);
		return workingCopy;
	}

	@Override
	protected void initializeEditor() {
		super.initializeEditor();
		setEditorContextMenuId(getContextMenuId());
	}

	@Override
	protected void initializeKeyBindingScopes() {
		setKeyBindingScopes(new String[] { getEditorContextId() });
	}

	@Override
	protected void connectPartitioningToElement(IEditorInput input,
			IDocument document) {
		if (document instanceof IDocumentExtension3) {
			IDocumentExtension3 extension = (IDocumentExtension3) document;
			IDocumentPartitioner partitioner = extension
					.getDocumentPartitioner(getPartitionId());

			if (partitioner == null) {
				IDocumentSetupParticipant participant = createDocumentSetupParticipant();
				if (participant != null)
					participant.setup(document);
			}
		}
	}

	@Override
	protected FoldingActionGroup createFoldingActionGroup() {
		return new FoldingActionGroup(this, getViewer(),
				getScriptPreferenceStore());
	}

	@Override
	protected abstract ScriptOutlinePage doCreateOutlinePage();

	@Override
	protected IFoldingStructureProvider getFoldingStructureProvider() {
		if (fFoldingStructureProvider == null) {
			fFoldingStructureProvider = createFoldingStructureProvider();
		}
		return fFoldingStructureProvider;
	}

	public ISourceModule getSourceModule() {
		return (ISourceModule) getInputModelElement();
	}

	@Override
	protected void configureSourceViewerDecorationSupport(
			SourceViewerDecorationSupport support) {
		String characters = getPairMatcherCharacters();
		if (characters != null) {
			support.setCharacterPairMatcher(new DefaultCharacterPairMatcher(
					characters.toCharArray()));
			support.setMatchingCharacterPainterPreferenceKeys(
					MATCHING_BRACKETS, MATCHING_BRACKETS_COLOR);
		}
		super.configureSourceViewerDecorationSupport(support);
	}

	protected String getPairMatcherCharacters() {
		return null;
	}

	protected abstract IFoldingStructureProvider createFoldingStructureProvider();

	protected abstract IDocumentSetupParticipant createDocumentSetupParticipant();

	protected abstract String getPartitionId();

	public abstract ScriptTextTools getTextTools();

	protected abstract String getContextMenuId();

	protected abstract String getEditorContextId();

	@Override
	@SingleProjectProblem
	public void dispose() {
		if (fFoldingStructureProvider != null) {
			fFoldingStructureProvider.uninstall();
		}
		super.dispose();
	}
}
