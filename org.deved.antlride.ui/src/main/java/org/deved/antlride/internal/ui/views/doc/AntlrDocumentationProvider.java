/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.internal.ui.views.doc;

import java.io.Reader;
import java.io.StringReader;

import org.deved.antlride.core.build.AntlrSourceParserRepository;
import org.deved.antlride.core.model.IGrammar;
import org.deved.antlride.core.model.IRule;
import org.deved.antlride.core.util.AntlrTextHelper;
import org.eclipse.dltk.core.IMember;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.ui.documentation.IScriptDocumentationProvider;

public class AntlrDocumentationProvider implements IScriptDocumentationProvider {

	public Reader getInfo(String content) {
		return null;
	}

	public Reader getInfo(IMember element, boolean lookIntoParents,
			boolean lookIntoExternal) {
		String elementName = element.getElementName();
		ISourceModule sourceModule = element.getSourceModule();
		IGrammar grammar = AntlrSourceParserRepository.getGrammar(sourceModule);
		if (grammar == null)
			return null;
		String doc = null;
		// String declaration = null;
		if (element.getElementType() == IMember.TYPE) {
			if (grammar.getElementName().equals(element.getElementName()))
				doc = grammar.getDocumentation();
		} else if (element.getElementType() == IMember.METHOD) {
			IRule rule = grammar.findRule(elementName);
			if (rule != null) {
				doc = rule.getDocumentation();
			}
		}
		if (doc == null) {
			return null;
		}
		return new StringReader(AntlrTextHelper.parseDoc(doc));
	}

}
