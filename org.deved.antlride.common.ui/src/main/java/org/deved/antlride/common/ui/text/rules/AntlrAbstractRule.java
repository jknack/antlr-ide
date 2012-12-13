/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.common.ui.text.rules;

import org.deved.antlride.common.ui.text.partitions.AntlrBasePartitionScanner;
import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IPredicateRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.IWhitespaceDetector;
import org.eclipse.jface.text.rules.IWordDetector;
import org.eclipse.jface.text.rules.Token;

public abstract class AntlrAbstractRule implements IPredicateRule {

	protected IToken fSuccessToken;

	protected IWhitespaceDetector fWhitespaceDetector;

	protected IWordDetector fWordDetector;

	private IWordDetector fAnyDetector;

	protected StringBuilder fInput;

	private StringBuilder fDebugBuffer;

	private boolean fDebug;

	protected int fCharsReaded;

	protected boolean fEOF;

	private static final String EMPTY = "";

	protected AntlrAbstractRule(IToken successToken,
			IWordDetector wordDetector, IWhitespaceDetector whitespaceDetector) {
		fSuccessToken = successToken;
		fWhitespaceDetector = whitespaceDetector;
		fWordDetector = wordDetector;
		fAnyDetector = AntlrAnyDetector.instance();
		fInput = new StringBuilder();
		fDebugBuffer = new StringBuilder();
	}

	protected AntlrAbstractRule(IToken successToken) {
		this(successToken, AntlrWordDetector.instance(),
				AntlrWhitespaceDetector.instance());
	}

	public IToken getSuccessToken() {
		return fSuccessToken;
	}

	public IToken evaluate(ICharacterScanner scanner) {
		int ch = scanner.read();
		if (notEOF(ch)) {
			scanner.unread();
			return evaluate(scanner, false);
		}
		scanner.unread();
		return Token.UNDEFINED;
	}

	public IToken evaluate(ICharacterScanner scanner, boolean resume) {
		fCharsReaded = 0;
		return doEvaluate(scanner);
	}

	protected abstract IToken doEvaluate(ICharacterScanner scanner);

	protected void unread(ICharacterScanner scanner, int length) {
		for (int i = length - 1; i >= 0; i--)
			scanner.unread();
		fCharsReaded -= length;
		if (fDebug) {
			while (length > 0) {
				fDebugBuffer.deleteCharAt(fDebugBuffer.length() - 1);
				length--;
			}
		}
	}

	protected void unread(ICharacterScanner scanner) {
		unread(scanner, fCharsReaded);
		fCharsReaded = 0;
	}

	protected void consumeWS(ICharacterScanner scanner) {
		fInput.setLength(0);
		int ch = scanner.read();
		fCharsReaded++;
		if (notEOF(ch)) {
			scanner.unread();
			fCharsReaded--;
			do {
				ch = scanner.read();
				fCharsReaded++;
				fInput.append((char) ch);
			} while (notEOF(ch) && fWhitespaceDetector.isWhitespace((char) ch));
			scanner.unread();
			fInput.deleteCharAt(fInput.length() - 1);
			fCharsReaded--;
		} else {
			scanner.unread();
			fCharsReaded--;
		}
		fDebugBuffer.append(getTextReaded());
	}

	protected void debugPartition() {
		if (fDebug && fDebugBuffer.length() > 0) {
			System.out.println(getClass().getName() + "::" + fDebugBuffer);
			fDebugBuffer.setLength(0);
		}
	}

	protected String getTextReaded() {
		return fInput.length() == 0 ? EMPTY : fInput.toString();
	}

	protected int getTextLengthReaded() {
		return fInput.length();
	}

	protected String getLastWordReaded(ICharacterScanner scanner) {
		StringBuilder builder = new StringBuilder();
		AntlrBasePartitionScanner scanner2 = ((AntlrBasePartitionScanner) scanner);
		int mark = scanner2.getOffset();
		scanner.unread();
		int ch = scanner.read();
		while (notEOF(ch) && fWordDetector.isWordPart((char) ch)) {
			builder.insert(0, (char) ch);
			scanner.unread();
			scanner.unread();
			ch = scanner.read();
		}
		if (notEOF(ch)
				&& (fWordDetector.isWordStart((char) ch) || ch == '\'' || ch == '\"')) {
			builder.insert(0, (char) ch);
		}
		while (scanner2.getOffset() != mark) {
			scanner2.read();
		}
		return builder.toString();
	}

	protected void consumeWord(ICharacterScanner scanner) {
		consume(scanner, fWordDetector);
	}

	protected void consumeAny(ICharacterScanner scanner) {
		consume(scanner, fAnyDetector);
	}

	protected void consumeOne(ICharacterScanner scanner) {
		consume(scanner, 1);
	}

	protected void consume(ICharacterScanner scanner, int count) {
		fInput.setLength(0);
		int ch = scanner.read();
		fCharsReaded++;
		if (notEOF(ch)) {
			while (notEOF(ch) && count > 0) {
				fInput.append((char) ch);
				count--;
				ch = scanner.read();
				fCharsReaded++;
			}
			scanner.unread();
			fCharsReaded--;
		} else {
			scanner.unread();
			fCharsReaded--;
		}
		if (fDebug)
			fDebugBuffer.append(getTextReaded());
	}

	protected void consume(ICharacterScanner scanner, IWordDetector wordDetector) {
		fInput.setLength(0);
		int ch = scanner.read();
		fCharsReaded++;
		if (notEOF(ch) && wordDetector.isWordStart((char) ch)) {
			scanner.unread();
			fCharsReaded--;
			do {
				ch = scanner.read();
				fCharsReaded++;
				fInput.append((char) ch);
			} while (notEOF(ch) && wordDetector.isWordPart((char) ch));
			scanner.unread();
			fInput.deleteCharAt(fInput.length() - 1);
			fCharsReaded--;
		} else {
			scanner.unread();
			fCharsReaded--;
		}
		if (fDebug)
			fDebugBuffer.append(getTextReaded());
	}

	protected void setDebug(boolean debug) {
		fDebug = debug;
		fDebugBuffer.setLength(0);
	}

	protected boolean notEOF(int ch) {
		return !EOF(ch);
	}

	protected boolean EOF(int ch) {
		return fEOF = (ch == ICharacterScanner.EOF);
	}
}
