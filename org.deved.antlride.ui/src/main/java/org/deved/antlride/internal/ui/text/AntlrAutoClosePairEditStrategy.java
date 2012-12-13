/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.internal.ui.text;

import org.eclipse.dltk.ui.PreferenceConstants;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.DocumentCommand;
import org.eclipse.jface.text.IAutoEditStrategy;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITypedRegion;
import org.eclipse.jface.text.TextUtilities;

public class AntlrAutoClosePairEditStrategy implements IAutoEditStrategy {
	private IPreferenceStore fStore;
	private String fPartitioning;

	public AntlrAutoClosePairEditStrategy(IPreferenceStore store,
			String partitioning) {
		fStore = store;
		fPartitioning = partitioning;
	}

	public void customizeDocumentCommand(IDocument d, DocumentCommand c) {
		if (c.doit == false)
			return;
		if (c.length <= 1 && c.text.length() == 1) {
			switch (c.text.charAt(0)) {
			case '\"':
			case '\'':
			case '(':
			case '{':
			case '[':
			case '}':
			case ']':
			case ')':
				// check partition
				String partitioning = getRegionType(d, c.offset);
				if (partitioning == null
						|| partitioning != IDocument.DEFAULT_CONTENT_TYPE)
					return;
				autoClose(d, c);
				break;
			}
		}
	}

	private boolean closeBrackets() {
		return fStore.getBoolean(PreferenceConstants.EDITOR_CLOSE_BRACKETS);
	}

	private boolean closeBraces() {
		return fStore.getBoolean(PreferenceConstants.EDITOR_CLOSE_BRACES);
	}

	private boolean closeStrings() {
		return fStore.getBoolean(PreferenceConstants.EDITOR_CLOSE_STRINGS);
	}

	private void autoClose(IDocument document, DocumentCommand command) {
		if (command.offset == -1)
			return;
		try {
			if (document.getChar(command.offset - 1) == '\\')
				return;
		} catch (BadLocationException e1) {
		}
		if ('\"' == command.text.charAt(0) && !closeStrings())
			return;
		if ('\'' == command.text.charAt(0) && !closeStrings())
			return;
		if (!closeBrackets()
				&& ('[' == command.text.charAt(0) || '(' == command.text
						.charAt(0)))
			return;
		if (!closeBraces() && ('{' == command.text.charAt(0)))
			return;
		try {

			switch (command.text.charAt(0)) {
			case '\"':
			case '\'':
				// if we close existing quote, do nothing
				if ('\"' == command.text.charAt(0) && command.offset > 0
						&& "\"".equals(document.get(command.offset - 1, 1)))
					return;

				if ('\'' == command.text.charAt(0) && command.offset > 0
						&& "\'".equals(document.get(command.offset - 1, 1)))
					return;

				if (command.offset != document.getLength()
						&& command.text.charAt(0) == document.get(
								command.offset, 1).charAt(0))
					command.text = "";
				else {
					command.text += command.text;
					command.length = 0;
				}

				command.shiftsCaret = false;
				command.caretOffset = command.offset + 1;
				break;
			case '(':
			case '{':
			case '[':
				if (command.offset != document.getLength()
						&& command.text.charAt(0) == document.get(
								command.offset, 1).charAt(0))
					return;
				// add closing peer
				command.text = command.text
						+ getBracePair(command.text.charAt(0));
				command.length = 0;

				command.shiftsCaret = false;
				command.caretOffset = command.offset + 1;
				break;
			case '}':
				if (!closeBraces())
					return;
				// if we already have bracket we should jump over it
				if (command.offset != document.getLength()
						&& command.text.charAt(0) == document.get(
								command.offset, 1).charAt(0)) {
					command.text = "";
					command.shiftsCaret = false;
					command.caretOffset = command.offset + 1;
					return;
				}
				break;
			case ']':
			case ')':
				if (!closeBrackets())
					return;
				// if we already have bracket we should jump over it
				if (command.offset != document.getLength()
						&& command.text.charAt(0) == document.get(
								command.offset, 1).charAt(0)) {
					command.text = "";
					command.shiftsCaret = false;
					command.caretOffset = command.offset + 1;
					return;
				}
				break;
			}
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}

	private char getBracePair(char ch) {
		switch (ch) {
		case '(':
			return ')';
		case ')':
			return '(';
		case '[':
			return ']';
		case ']':
			return '[';
		case '{':
			return '}';
		case '}':
			return '{';
		case '\"':
			return '\"';
		case '\'':
			return '\'';
		}
		return ch;
	}

	private String getRegionType(IDocument d, int offset) {
		try {
			int p = ((offset == d.getLength()) ? offset - 1 : offset);
			ITypedRegion region;
			region = TextUtilities.getPartition(d, fPartitioning, p, true);
			return region.getType();
		} catch (BadLocationException ex) {
		}
		return null;
	}
}
