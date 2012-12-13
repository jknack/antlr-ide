/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.internal.ui.text.highlighting;

import org.deved.antlride.core.build.AntlrSourceParserRepository;
import org.deved.antlride.core.model.IGrammar;
import org.deved.antlride.core.model.IOption;
import org.deved.antlride.internal.ui.editor.AntlrEditor;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.ui.text.AbstractScriptScanner;
import org.eclipse.dltk.ui.text.IColorManager;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.texteditor.ITextEditor;

public abstract class AntlrAbstractHighlightScanner extends
		AbstractScriptScanner {
	private AntlrEditor fEditor;

	public AntlrAbstractHighlightScanner(IColorManager manager,
			IPreferenceStore store) {
		super(manager, store);
	}

	public AntlrAbstractHighlightScanner(IColorManager manager,
			IPreferenceStore store, ITextEditor editor) {
		super(manager, store);
		fEditor = (AntlrEditor) editor;
	}

	protected ISourceModule getSourceModule() {
		if (fEditor == null) {
			return null;
		}
		return fEditor.getSourceModule();
	}

	private IGrammar getGrammar() {
		ISourceModule sourceModule = getSourceModule();
		if (sourceModule == null)
			return null;
		return AntlrSourceParserRepository.getGrammar(sourceModule);
	}

	public String getGrammarOption(String optionName, String defaultValue) {
		try {
			IGrammar grammar = getGrammar();
			if (grammar != null) {
				IOption option = grammar.findOption(optionName);
				if (option != null)
					return option.getValue().getText();
			}
		} catch (Throwable t) {
			// shhh....
		}
		return defaultValue;
	}
}
