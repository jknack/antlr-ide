/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.core.resources;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.deved.antlride.core.AntlrConstants;
import org.deved.antlride.core.AntlrCore;
import org.deved.antlride.core.AntlrLanguageToolkit;
import org.deved.antlride.core.build.AntlrSourceParserRepository;
import org.deved.antlride.core.model.GrammarType;
import org.deved.antlride.core.model.IGrammar;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IParent;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.ModelException;

public class AntlrResourceFinder {

	private static final IModelElement[] NO_ELEMENTS = new IModelElement[0];

	public static IFile[] findGrammars(IScriptProject project) {
		List<IFile> list = listGramars(project);
		return list.toArray(new IFile[list.size()]);
	}

	private static String getGrammarTypeProperty(IResource resource) {
		try {
			return resource
					.getPersistentProperty(AntlrConstants.Q_ANTLR_GRAMMAR_TYPE);
		} catch (CoreException e) {
			AntlrCore.error(e);
		}
		return null;
	}

	private static void setGrammarTypeProperty(IResource resource,
			GrammarType grammarType) {
		try {
			resource.setPersistentProperty(AntlrConstants.Q_ANTLR_GRAMMAR_TYPE,
					grammarType.name());
		} catch (CoreException e) {
			AntlrCore.error(e);
		}
	}

	public static GrammarType getGrammarType(IResource resource) {
		GrammarType grammarType = null;
		if (isGrammarResource(resource)) {
			// try with the new model first
			// the new model will save a property in the file indicating which
			// grammar type is it
			String grammarTypeName = getGrammarTypeProperty(resource);
			if (grammarTypeName != null) {
				grammarType = GrammarType.valueOf(grammarTypeName);
			} else {
				// try parsing the content
				try {
					IGrammar grammar = AntlrSourceParserRepository
							.getGrammar(resource.getFullPath());
					grammarType = grammar.getGrammarType();
					setGrammarTypeProperty(resource, grammarType);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
		return grammarType;
	}

	private static boolean isGrammarResource(IResource resource) {
		return AntlrConstants.ANTLR_GRAMMAR_FILE_EXTENSION.equals(resource
				.getFileExtension());
	}

	private static List<IFile> listGramars(IModelElement element) {
		List<IFile> files = new ArrayList<IFile>();
		IModelElement[] elements = NO_ELEMENTS;
		try {
			if (element instanceof IParent) {
				elements = ((IParent) element).getChildren();
			} else if (element.getElementType() == IModelElement.SOURCE_MODULE) {
				elements = new IModelElement[1];
				elements[0] = element;
			}
			AntlrLanguageToolkit languageToolkit = AntlrLanguageToolkit.getDefault();
			for (IModelElement childE : elements) {
				if (childE.exists()
						&& childE.getElementType() == IModelElement.SOURCE_MODULE) {
					IFile resource = (IFile) childE.getCorrespondingResource();
					if (languageToolkit.validResource(resource)) {
						files.add(resource);
					}
				} else if (childE instanceof IParent
						&& ((IParent) childE).hasChildren()) {
					files.addAll(listGramars(childE));
				}
			}
		} catch (ModelException ex) {
			AntlrCore.error(ex);
		}
		return files;
	}
}
