/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.internal.ui;

import org.deved.antlride.common.ui.AntlrImages;
import org.deved.antlride.core.build.AntlrProblem;
import org.deved.antlride.ui.AntlrUIConstants;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.debug.ui.console.FileLink;
import org.eclipse.jface.text.IDocument;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleFactory;
import org.eclipse.ui.console.IConsoleManager;
import org.eclipse.ui.console.IOConsole;
import org.eclipse.ui.console.IPatternMatchListener;
import org.eclipse.ui.console.PatternMatchEvent;
import org.eclipse.ui.console.TextConsole;

public class AntlrConsoleFactory implements IConsoleFactory {

	public static final String ANTLR_CONSOLE = "ANTLR Console";

	public void openConsole() {
		showConsole();
	}

	public static IOConsole getConsole() {
		IConsoleManager manager = ConsolePlugin.getDefault()
				.getConsoleManager();
		IConsole[] existing = manager.getConsoles();
		IOConsole console = null;
		for (int i = 0; i < existing.length; i++) {
			if (ANTLR_CONSOLE.equals(existing[i].getName()))
				console = (IOConsole) existing[i];
		}
		if (console == null) {
			console = new IOConsole(ANTLR_CONSOLE, AntlrImages
					.getDescriptor(AntlrImages.CONSOLE));
			console.addPatternMatchListener(new AntlrProblemPatternMatcher());
			manager.addConsoles(new IConsole[] { console });
		}
		return console;
	}

	public static void showConsole() {
		IConsoleManager manager = ConsolePlugin.getDefault()
				.getConsoleManager();
		manager.showConsoleView(getConsole());
	}

	// public static void main(String[] args) {
	// Pattern pattern = Pattern.compile("/.*(/.*)*\\.g");
	// String input =
	// "warning(200): /antlride/antlr/ANTLRv3.g:451:5: Decision can match input such as \"' ''$''A''N''T''L''R'' ''s''r''c'' ''\"''\\''\'''\"'' ''0'..'9'\" using multiple alternatives: 1, 2\nAs a result, alternative(s) 2 were disabled for that input";
	// Matcher matcher = pattern.matcher(input);
	// if (matcher.find()) {
	// System.out.println(matcher.group());
	// }
	// }

	private static class AntlrProblemPatternMatcher implements
			IPatternMatchListener {

		public int getCompilerFlags() {
			return 0;
		}

		public String getLineQualifier() {
			return null;
		}

		public String getPattern() {
			return "(warning|error)\\(\\d+\\).*";
		}

		public void connect(TextConsole console) {
		}

		public void disconnect() {
		}

		public void matchFound(PatternMatchEvent event) {
			try {
				IOConsole console = (IOConsole) event.getSource();
				IDocument document = console.getDocument();
				int offset = event.getOffset();
				int length = event.getLength();
				String message = document.get(offset, length);
				// message format warning|error(id):
				// find the ':'
				length = message.indexOf(':');
				AntlrProblem problem = (AntlrProblem) console
						.getAttribute(message);
				if (problem == null) {
					return;
				}
				IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
				IFile file = root.getFile(problem.getFilepath());
				if (!file.exists()) {
					// standalone version
					IFile[] files = root.findFilesForLocationURI(problem
							.getFilepath().toFile().toURI());
					file = files[0];
				}
				if (file != null && file.exists()) {
					FileLink link = new FileLink(file,
							AntlrUIConstants.EDITOR_ID, problem
									.getSourceStart(), problem.getSourceEnd()
									- problem.getSourceStart(), problem
									.getLine());
					console.addHyperlink(link, offset, length);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
