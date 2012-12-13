/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.internal.ui.wizards;

import org.deved.antlride.core.model.GrammarType;
import org.eclipse.core.runtime.Path;

public class AntlrDefaultGrammarPage extends AntlrGrammarPage {

	public AntlrDefaultGrammarPage() {
		super("grammar");
	}

	@Override
	protected String getContents() {
		String nl = System.getProperty("line.separator");
		StringBuilder content = new StringBuilder();
		content.append(getGrammarTypeName());
		content.append("grammar ");//$NON-NLS-1$
		content.append(getProperty(GRAMMAR_NAME));
		content.append(";");//$NON-NLS-1$
		content.append(nl);
		content.append(nl);
		content.append("options {");
		content.append(nl);
		content.append("  language = ");
		content.append(getProperty(LANGUAGE));
		content.append(";");
		content.append(nl);
		String output = getProperty(OUTPUT);
		if (output != null) {
			content.append("  output = ");
			content.append(output);
			content.append(";");
			content.append(nl);
		}
		if (hasTokenVocabSection()) {
			content.append("  tokenVocab = ");
			content.append(new Path(getProperty("tokenVocab"))
					.removeFileExtension().lastSegment());
			content.append(";");
			content.append(nl);
		}
		if (GrammarType.TREE_PARSER.name().equals(getGrammarType())) {
			content.append("  ASTLabelType = CommonTree;");
			content.append(nl);
		}
		content.append("}");
		content.append(nl);
		content.append(nl);
		content.append(getRuleTemplate());
		content.append(": ;");//$NON-NLS-1$
		content.append(nl);
		return content.toString();
	}

	protected String getRuleTemplate() {
		return "rule";
	}
	
	@Override
	protected boolean hasDependentSection() {
		return false;
	}

	@Override
	protected String getGrammarType() {
		return GrammarType.COMBINED.name();
	}

	protected String getGrammarTypeName() {
		return "";
	}

	@Override
	protected boolean hasOutputSection() {
		return true;
	}

	@Override
	protected String getPageDescription() {
		return "Created a new combined grammar";
	}

	@Override
	protected String getPageTitle() {
		return "Combined Grammar";
	}

}
