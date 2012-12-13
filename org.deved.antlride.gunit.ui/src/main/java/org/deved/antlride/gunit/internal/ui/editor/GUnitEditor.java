/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.gunit.internal.ui.editor;

import org.deved.antlride.common.ui.SingleProjectProblem;
import org.deved.antlride.common.ui.text.editor.AntlrBaseEditor;
import org.deved.antlride.gunit.core.GUnitConstants;
import org.deved.antlride.gunit.core.GUnitLanguageToolkit;
import org.deved.antlride.gunit.internal.ui.text.folding.GUnitFoldingStructureProvider;
import org.deved.antlride.gunit.internal.ui.text.partitions.GUnitPartitions;
import org.deved.antlride.gunit.ui.GUnitUI;
import org.eclipse.core.filebuffers.IDocumentSetupParticipant;
import org.eclipse.dltk.core.IDLTKLanguageToolkit;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.internal.ui.editor.ScriptOutlinePage;
import org.eclipse.dltk.ui.text.ScriptTextTools;
import org.eclipse.dltk.ui.text.folding.IFoldingStructureProvider;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.IEditorInput;

public class GUnitEditor extends AntlrBaseEditor {
	
	private GUnitDocumentProvider fDocumentProvider;

	public GUnitEditor() {
		super(true);
		setDocumentProvider(fDocumentProvider = GUnitUI.getDefault().getSourceModuleDocumentProvider());
	}

	@Override
	public String getEditorId() {
		return GUnitConstants.EDITOR_ID;
	}
	
	@Override
	protected String getContextMenuId() {
		return GUnitConstants.EDITOR_CONTEXT_MENU;
	}

	@Override
	public IDLTKLanguageToolkit getLanguageToolkit() {
		return GUnitLanguageToolkit.getDefault();
	}
	
	@Override
	protected IFoldingStructureProvider createFoldingStructureProvider() {
		return new GUnitFoldingStructureProvider();
	}
	
	@Override
	public IPreferenceStore getScriptPreferenceStore() {
		return GUnitUI.getDefault().getPreferenceStore();
	}

	@Override
	@SingleProjectProblem
	public IModelElement getInputModelElement() {
		IEditorInput editorInput = getEditorInput();
		if (editorInput == null)
			return null;
		ISourceModule workingCopy = fDocumentProvider.getWorkingCopy(editorInput);
		return workingCopy;
	}
	
	@Override
	public ScriptTextTools getTextTools() {
		return GUnitUI.getDefault().getTextTools();
	}
	
	@Override
	protected String getPartitionId() {
		return GUnitPartitions.PARTITION_ID;
	}
	
	@Override
	protected IDocumentSetupParticipant createDocumentSetupParticipant() {
		return new GUnitDocumentSetupParticipant();
	}

	@Override
	protected String getEditorContextId() {
		return GUnitConstants.EDITOR_CONTEXT;
	}
	
	@Override
	protected String getPairMatcherCharacters() {		
		return "[]()<>";
	}
	
	@Override
	protected ScriptOutlinePage doCreateOutlinePage() {
		return new GUnitOutlinePage(this, getScriptPreferenceStore());
	}
}
