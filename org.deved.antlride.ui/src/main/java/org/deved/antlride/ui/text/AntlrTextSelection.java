package org.deved.antlride.ui.text;

import org.eclipse.jface.text.ITextSelection;

public class AntlrTextSelection implements ITextSelection {
	private int fLine;

	private int fOffset;

	private int fLength;

	private String fText;

	public AntlrTextSelection(int line, int offset, int length, String text) {
		fOffset = offset;
		fLine = line;
		fLength = length;
		fText = text;
	}

	public AntlrTextSelection(int line, int offset, int length) {
		this(line, offset, length, null);
	}

	public int getStartLine() {
		return fLine;
	}

	public int getEndLine() {
		return 0;
	}

	public int getLength() {
		return fLength;
	}

	public int getOffset() {
		return fOffset;
	}

	public String getText() {
		return fText;
	}

	public boolean isEmpty() {
		return fOffset < 0 || fLength < 0;
	}
}
