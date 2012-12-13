/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.core.build;

import org.eclipse.core.runtime.IPath;
import org.eclipse.dltk.compiler.problem.DefaultProblem;
import org.eclipse.dltk.compiler.problem.IProblem;
import org.eclipse.dltk.compiler.problem.ProblemSeverities;
import org.eclipse.dltk.compiler.problem.IProblemIdentifier;
import org.eclipse.dltk.compiler.problem.DefaultProblemIdentifier;
import org.eclipse.dltk.compiler.problem.ProblemSeverity;

public final class AntlrProblem {

	private static String[] EMPTY_STRING = {};

	private ProblemSeverity severity;

	private IProblemIdentifier id;

	private int line;

	private int column;

	private int sourceStart;

	private int sourceEnd;

	private String rawMessage;

	private IPath filepath;

	private String shortMessage;

	private String lineWithProblem;

	AntlrProblem(IPath filepath, ProblemSeverity severity, int id, int line, int column,
			int start, int end, String rawMessage, String shortMessage,
			String lineWithProblem) {
		this.filepath = filepath;
		this.severity = severity;
		this.id = DefaultProblemIdentifier.decode(id);
		this.line = line;
		this.column = column;
		this.sourceStart = start;
		this.sourceEnd = end;
		this.rawMessage = rawMessage;
		this.shortMessage = shortMessage;
		this.lineWithProblem = lineWithProblem.trim();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj instanceof AntlrProblem) {
			AntlrProblem problem = (AntlrProblem) obj;
			return rawMessage.equals(problem.rawMessage);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return rawMessage.hashCode();
	}

	public String getLineWithProblem() {
		return lineWithProblem;
	}

	public int getColumn() {
		return column;
	}

	public IPath getFilepath() {
		return filepath;
	}

	public IProblemIdentifier getId() {
		return id;
	}

	public int getLine() {
		return line;
	}

	public String getRawMessage() {
		return rawMessage;
	}

	public ProblemSeverity getSeverity() {
		return severity;
	}

	public int getSourceEnd() {
		return sourceEnd;
	}

	public int getSourceStart() {
		return sourceStart;
	}

	public boolean isError() {
		return severity == ProblemSeverities.Error;
	}

	@Override
	public String toString() {
		return rawMessage;
	}

	public IProblem toDLTKProblem() {
		return new DefaultProblem("", shortMessage, id, EMPTY_STRING, severity,
				sourceStart, sourceEnd, line - 1, 0);
	}
}
