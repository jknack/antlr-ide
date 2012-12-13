/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.ui.text;

import org.deved.antlride.core.AntlrConstants;
import org.eclipse.jface.text.IDocument;

public interface AntlrTextPartitions extends AntlrConstants {
	String ANTLR_PARTITIONING = "__antlr3_partitioning";

	String[] ANTLR3_PARTITION_TYPES = { ANTLR_GRAMMAR_DECLARATION,
			ANTLR_OPTIONS, ANTLR_IMPORT, ANTLR_TOKENS, ANTLR_SCOPE,
			ANTLR_GRAMMAR_ACTION, ANTLR_TARGET_ACTION, ANTLR_RULE_ACTION,
			ANTLR_BRACKET, ANTLR_SINGLE_LINE_COMMENT, ANTLR_MULTI_LINE_COMMENT,
			ANTLR_STRING, IDocument.DEFAULT_CONTENT_TYPE };

	String[] LEGAL_CONTENT_TYPES = { ANTLR_GRAMMAR_DECLARATION, ANTLR_OPTIONS,
			ANTLR_IMPORT, ANTLR_TOKENS, ANTLR_SCOPE, ANTLR_GRAMMAR_ACTION,
			ANTLR_TARGET_ACTION, ANTLR_RULE_ACTION, ANTLR_BRACKET,
			ANTLR_SINGLE_LINE_COMMENT, ANTLR_MULTI_LINE_COMMENT, ANTLR_STRING };
}
