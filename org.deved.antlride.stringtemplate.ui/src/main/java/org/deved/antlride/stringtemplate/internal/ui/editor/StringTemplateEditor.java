/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.stringtemplate.internal.ui.editor;

import org.deved.antlride.common.ui.SingleProjectProblem;
import org.deved.antlride.common.ui.text.editor.AntlrBaseEditor;
import org.deved.antlride.stringtemplate.core.StringTemplateConstants;
import org.deved.antlride.stringtemplate.core.StringTemplateLanguageToolkit;
import org.deved.antlride.stringtemplate.internal.ui.text.folding.StringTemplateFoldingStructureProvider;
import org.deved.antlride.stringtemplate.internal.ui.text.partitions.StringTemplatePartitions;
import org.deved.antlride.stringtemplate.ui.StringTemplateUI;
import org.eclipse.core.filebuffers.IDocumentSetupParticipant;
import org.eclipse.dltk.core.IDLTKLanguageToolkit;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.internal.ui.editor.ScriptOutlinePage;
import org.eclipse.dltk.ui.text.ScriptTextTools;
import org.eclipse.dltk.ui.text.folding.IFoldingStructureProvider;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.IEditorInput;

public class StringTemplateEditor extends AntlrBaseEditor {
	
	private StringTemplateDocumentProvider fDocumentProvider;

	public StringTemplateEditor() {
		super(true);
		setDocumentProvider(fDocumentProvider = StringTemplateUI.getDefault().getSourceModuleDocumentProvider());
	}

	@Override
	public String getEditorId() {
		return StringTemplateConstants.EDITOR_ID;
	}
	
	@Override
	protected String getContextMenuId() {
		return StringTemplateConstants.EDITOR_CONTEXT_MENU;
	}

	@Override
	@SingleProjectProblem
	public IDLTKLanguageToolkit getLanguageToolkit() {
		return StringTemplateLanguageToolkit.getDefault();
	}
	
	@Override
	protected IFoldingStructureProvider createFoldingStructureProvider() {
		return new StringTemplateFoldingStructureProvider();
	}
	
	@Override
	public IPreferenceStore getScriptPreferenceStore() {
		return StringTemplateUI.getDefault().getPreferenceStore();
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
		return StringTemplateUI.getDefault().getTextTools();
	}
	
	@Override
	protected String getPartitionId() {
		return StringTemplatePartitions.PARTITION_ID;
	}
	
	@Override
	protected IDocumentSetupParticipant createDocumentSetupParticipant() {
		return new StringTemplateDocumentSetupParticipant();
	}

	@Override
	protected String getEditorContextId() {
		return StringTemplateConstants.EDITOR_CONTEXT;
	}
	
	@Override
	protected String getPairMatcherCharacters() {		
		return "{}[]()<>$$";
	}
	
	@Override
	protected ScriptOutlinePage doCreateOutlinePage() {
		return new StringTemplateOutlinePage(this, getScriptPreferenceStore());
	}
}
