/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.internal.core.codeassit;

import org.deved.antlride.core.AntlrCore;
import org.deved.antlride.core.build.AntlrSourceParserRepository;
import org.deved.antlride.core.model.ElementKind;
import org.deved.antlride.core.model.IGrammar;
import org.deved.antlride.core.model.IModelElement;
import org.deved.antlride.core.model.ast.AntlrModelElementLocator;
import org.deved.antlride.internal.core.model.dltk.FakeField;
import org.deved.antlride.internal.core.model.dltk.FakeMethod;
import org.deved.antlride.internal.core.model.dltk.FakeType;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.dltk.codeassist.ScriptSelectionEngine;
import org.eclipse.dltk.compiler.env.IModuleSource;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.ModelException;

public class AntlrSelectionEngine extends ScriptSelectionEngine {
	private static final org.eclipse.dltk.core.IModelElement[] EMPTY_D_ELEMENTS = new org.eclipse.dltk.core.IModelElement[0];

	public org.eclipse.dltk.core.IModelElement[] select(
			IModuleSource envModule,
			int selectionSourceStart, int selectionSourceEnd) {
		org.eclipse.dltk.core.IModelElement[] delements = EMPTY_D_ELEMENTS;
		org.eclipse.dltk.core.IModelElement e = null;
		try {
			ISourceModule sourceModule = (ISourceModule) envModule;
			
			IGrammar grammar = AntlrSourceParserRepository.getGrammar(sourceModule);
			
			AntlrModelElementLocator locator = new AntlrModelElementLocator(grammar);
			IModelElement element = locator.getElementDeclaration(selectionSourceStart);			
			if (element != null) {
				IGrammar referencedGrammar = element.getAdapter(IGrammar.class);
				if(grammar != referencedGrammar) {
					IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
					IFile file = root.getFile(referencedGrammar.getFile());
					ISourceModule referencedSourceModule = DLTKCore.createSourceModuleFrom(file);
					sourceModule = referencedSourceModule;
				}				
				ElementKind kind = element.getElementKind();
				switch (kind) {
				case RULE_SCOPE:
					e = new FakeType(sourceModule, "scope", element.sourceStart(), element.sourceStart() + "scope".length());
					break;
				case SCOPE_REFERENCE:
					e = new FakeType(sourceModule, element);
					break;
				case TOKEN:
				case CALL:
					e = new FakeMethod(sourceModule, element);
					break;
				case GRAMMAR_SCOPE:
				case RULE:
					e = findElement(sourceModule, element.sourceStart());
					break;
				case CALL_PARAMETER:
				case GRAMMAR_SCOPE_ATTRIBUTE:
				case RULE_SCOPE_ATTRIBUTE:				
				case VARIABLE:				
					e = new FakeField(sourceModule, element);
					break;
				}				
			} else if(grammar.getName()!=null && grammar.getName().isIn(selectionSourceStart)){
				e = new FakeType(sourceModule, grammar.getElementName(), grammar.getName().sourceStart(), grammar.getName().sourceEnd());//findElement(sourceModule, grammar.getName().sourceStart());
			}
		} catch (Exception ex) {
			if(AntlrCore.DEBUG)
				ex.printStackTrace();
		} finally {
		}
		if (e != null) {
			delements = new org.eclipse.dltk.core.IModelElement[1];
			delements[0] = e;
		}
		return delements;
	}

	private org.eclipse.dltk.core.IModelElement findElement(
			ISourceModule module, int position) {
		org.eclipse.dltk.core.IModelElement e = null;
		try {
			e =module.getElementAt(position);
			if (e==null || !e.exists()) {
				e = null;
			}
		} catch (ModelException ex) {
			ex.printStackTrace();
		}		
		
//		element = module.getField(elementName);
//		if (element.exists()) {
//			return element;
//		}
//		element = module.getMethod(elementName);
//		if (element.exists()) {
//			return element;
//		}
		return e;
	}
}
