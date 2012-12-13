/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.common.ui.text.partitions.rules;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IToken;

public abstract class AntlrBlockPartition extends AntlrAbstractPartition {

	private Collection<String> fExclusionPatterns;

	private String fStartBlockCharacter;

	private String fEndBlockCharacter;

	protected AntlrBlockPartition(IToken successToken,
			char startBlockCharacter, char endBlockCharacter) {
		super(successToken);
		fExclusionPatterns = new ArrayList<String>();
		fStartBlockCharacter = "" + startBlockCharacter;
		fEndBlockCharacter = "" + endBlockCharacter;
	}

	protected AntlrBlockPartition(IToken successToken) {
		this(successToken, '{', '}');
	}

	// public static void main(String[] args) {
	// String text = "RCURLY='};";
	// System.out.println(text.matches(".*'.*}.*'.*"));
	// }

	protected String[] isInIgnoreMode(String ch, ICharacterScanner scanner) {
		if (ch.equals("'")) {
			return new String[] { "'", "'", null };
		}
		if (ch.equals("\"")) {
			return new String[] { "\"", "\"", null };
		}
		if (ch.equals("/")) {
			consumeOne(scanner);
			String nextChar = getTextReaded();
			unread(scanner, 1);
			if (nextChar.equals("/")) {
				return new String[] { "//", "\n", null };
			}
			if (nextChar.equals("*")) {
				return new String[] { "/*", "*/", null };
			}
		}
		return null;
	}

	protected IToken doBlock(ICharacterScanner scanner) {
		// StringBuilder partition = new StringBuilder();
		try {
			consumeOne(scanner);
			int count = 1;
			StringBuilder ignored = new StringBuilder();
			while (!fEOF) {
				String ch = getTextReaded();
				// partition.append(ch);
				String[] patterns = isInIgnoreMode(ch, scanner);
				if (patterns != null) {
					// 0=first pattern; 1=end pattern; 2=escape sequence
					ignored.append(ch);
					consumeOne(scanner);
					String endSequence = patterns[1];
					String escapeSequence = patterns[2];
					String escapePattern = patterns[2] + endSequence;
					String pattern = null;
					while (!fEOF) {
						String ich = getTextReaded();
						ignored.append(ich);
						// partition.append(ich);
						pattern = ignored.toString();
						if (pattern.endsWith(endSequence)) {
							if (escapeSequence == null
									|| !pattern.endsWith(escapePattern)) {
								break;
							}
						}
						consumeOne(scanner);
					}
					// System.out.println("IGNORED---" + ignored + "---");
					ignored.setLength(0);
				}
				if (fExclusionPatterns.size() > 0) {
					String word = getLastWordReaded(scanner);
					if (excluded(ch) || excluded(word)) {
						unread(scanner, word.length());
						return fSuccessToken;
					}
				}
				if (patterns == null) {
					if (ch.equals(fEndBlockCharacter)) {
						count--;
						if (count == 0) {
							return fSuccessToken;
						}
					} else if (ch.equals(fStartBlockCharacter)) {
						count++;
					}
				}
				consumeOne(scanner);
			}
			return fSuccessToken;
		} finally {
			// debugPartition();
			// System.out.println(partition);
		}
	}

	protected void addExclusionPattern(String pattern) {
		fExclusionPatterns.add(pattern);
	}

	protected boolean excluded(String text) {
		return fExclusionPatterns.contains(text);
	}

	protected void setStartBlockCharacter(char startBlockCharacter) {
		fStartBlockCharacter = "" + startBlockCharacter;
	}

	protected void setEndBlockCharacter(char endBlockCharacter) {
		fEndBlockCharacter = "" + endBlockCharacter;
	}
}
