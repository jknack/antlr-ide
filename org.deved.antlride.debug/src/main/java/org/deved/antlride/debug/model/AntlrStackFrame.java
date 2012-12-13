/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.debug.model;

import java.util.Map;

import org.deved.antlride.core.model.ElementKind;
import org.deved.antlride.core.model.IGrammar;
import org.deved.antlride.core.model.IModelElement;
import org.deved.antlride.core.model.ast.ModelElementQuery;
import org.deved.antlride.core.util.AntlrTextHelper;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IRegisterGroup;
import org.eclipse.debug.core.model.IStackFrame;
import org.eclipse.debug.core.model.IThread;
import org.eclipse.debug.core.model.IVariable;

public class AntlrStackFrame extends AntlrDebugElement implements IStackFrame {

	private AntlrThread fThread;

	private String fName;

	private int fLineNumber;

	private int fColumnNumber;

	private String fFileName;

	private int fId;

	private int fCharEnd = -1;

	private int fCharStart = -1;

	public static final String ATTR_ID = "id";

	public static final String ATTR_LINE_NUMBER = "lineNumber";

	public static final String ATTR_STACK_FRAME = "stackFrame";

	public static final String ATTR_GRAMMAR_FILE = "grammarFile";

	public static final String ATTR_COLUMN_NUMBER = "columnNumber";

	public AntlrStackFrame(AntlrThread thread, Map<String, Object> context) {
		super(thread.getDebugTarget());
		fThread = thread;
		IGrammar grammar = thread.getDebugTarget().getGrammar();
		fId = ((Integer) context.get(ATTR_ID)).intValue();
		fLineNumber = ((Integer) context.get(ATTR_LINE_NUMBER)).intValue();
		fColumnNumber = ((Integer) context.get(ATTR_COLUMN_NUMBER)).intValue();
		fName = (String) context.get(ATTR_STACK_FRAME);
		fFileName = (String) context.get(ATTR_GRAMMAR_FILE);
		IModelElement element = ModelElementQuery.getElementAt(grammar,
				fLineNumber, fColumnNumber);
		if (element == null || element.getElementKind() == ElementKind.RULE) {
			fCharStart = AntlrTextHelper.getOffsetAtLine(grammar.getSource(),
					fLineNumber)
					+ fColumnNumber - 1;
			fCharEnd = fCharStart + fName.length();
		} else {
			fCharStart = element.sourceStart();
			fCharEnd = element.sourceEnd();
		}
	}

	public int getCharEnd() throws DebugException {
		return fCharEnd;
	}

	public int getCharStart() throws DebugException {
		return fCharStart;
	}

	public int getLineNumber() {
		return fLineNumber;
	}

	public int getColumnNumber() {
		return fColumnNumber;
	}

	public String getName() {
		return fName;
	}

	public IRegisterGroup[] getRegisterGroups() throws DebugException {
		return null;
	}

	public String getSourceName() {
		return fFileName;
	}

	public IThread getThread() {
		return fThread;
	}

	public IVariable[] getVariables() throws DebugException {
		return new IVariable[0];
	}

	public int hashCode() {
		return getSourceName().hashCode() + fId;
	}

	public boolean hasRegisterGroups() throws DebugException {
		return false;
	}

	public boolean hasVariables() throws DebugException {
		return getVariables().length > 0;
	}

	public boolean canStepInto() {
		return getThread().canStepInto();
	}

	public boolean canStepOver() {
		return getThread().canStepOver();
	}

	public boolean canStepReturn() {
		return getThread().canStepReturn();
	}

	public boolean isStepping() {
		return getThread().isStepping();
	}

	public void stepInto() throws DebugException {
		getThread().stepInto();
	}

	public void stepOver() throws DebugException {
		getThread().stepOver();
	}

	public void stepReturn() throws DebugException {
		getThread().stepReturn();
	}

	public boolean canResume() {
		return getThread().canResume();
	}

	public boolean canSuspend() {
		return getThread().canSuspend();
	}

	public boolean isSuspended() {
		return getThread().isSuspended();
	}

	public void resume() throws DebugException {
		getThread().resume();
	}

	public void suspend() throws DebugException {
		getThread().suspend();
	}

	public boolean canTerminate() {
		return getThread().canTerminate();
	}

	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (obj instanceof AntlrStackFrame) {
			AntlrStackFrame sf = (AntlrStackFrame) obj;
			return fId == sf.fId;
		}
		return false;
	}

	public boolean isTerminated() {
		return getThread().isTerminated();
	}

	public void terminate() throws DebugException {
		getThread().terminate();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(getName());
		builder.append(" [location: (");
		builder.append(getLineNumber());
		builder.append(", ");
		builder.append(getColumnNumber());
		builder.append(")]");
		return builder.toString();
	}
}
