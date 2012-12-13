/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.jdt.launch;

import static org.eclipse.jdt.launching.IJavaLaunchConfigurationConstants.ATTR_CLASSPATH;
import static org.eclipse.jdt.launching.IJavaLaunchConfigurationConstants.ATTR_DEFAULT_CLASSPATH;
import static org.eclipse.jdt.launching.IJavaLaunchConfigurationConstants.ATTR_MAIN_TYPE_NAME;
import static org.eclipse.jdt.launching.IJavaLaunchConfigurationConstants.ATTR_PROGRAM_ARGUMENTS;
import static org.eclipse.jdt.launching.IJavaLaunchConfigurationConstants.ATTR_PROJECT_NAME;
import static org.eclipse.jdt.launching.IJavaLaunchConfigurationConstants.ID_JAVA_APPLICATION;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.regex.Matcher;

import org.deved.antlride.core.AntlrConstants;
import org.deved.antlride.core.build.AntlrBuildUnit;
import org.deved.antlride.core.build.AntlrDeployer;
import org.deved.antlride.core.launch.AntlrLauncher;
import org.deved.antlride.core.model.IGrammar;
import org.deved.antlride.core.model.IGrammarAction;
import org.deved.antlride.core.model.evaluation.AntlrResultListener;
import org.deved.antlride.core.model.evaluation.IEvalElement;
import org.deved.antlride.core.model.test.AntlrTestCase;
import org.deved.antlride.debug.model.event.AntlrDebugEvent;
import org.deved.antlride.debug.model.event.AntlrDebugEventFactory;
import org.deved.antlride.debug.ui.AntlrDebugParser;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationType;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.launching.IRuntimeClasspathEntry;
import org.eclipse.jdt.launching.JavaRuntime;
import org.eclipse.swt.widgets.Display;

public class AntlrJavaLauncher extends AntlrAbstractJavaLauncher implements
		AntlrLauncher {

	private AntlrResultListener listener;

	private int run(IProgressMonitor monitor, AntlrBuildUnit unit,
			AntlrTestCase test, IJavaProject javaProject,
			IRuntimeClasspathEntry[] classpath, IPath src, IPath classes)
			throws CoreException, InterruptedException {
		ILaunchManager manager = DebugPlugin.getDefault().getLaunchManager();
		ILaunchConfigurationType type = manager
				.getLaunchConfigurationType(ID_JAVA_APPLICATION);

		ILaunchConfigurationWorkingCopy workingCopy = type.newInstance(null,
				new StringBuilder(test.getRule()).append(" ").append(
						test.getName()).toString());

		workingCopy.setAttribute(ATTR_PROJECT_NAME, javaProject
				.getElementName());

		workingCopy.setAttribute(ATTR_MAIN_TYPE_NAME,
				"org.deved.antlride.runtime.LaunchParser");
		workingCopy.setAttribute(ATTR_DEFAULT_CLASSPATH, false);

		IPath runtimePath = AntlrDeployer.RUNTIME_LOCATION;
		IRuntimeClasspathEntry runtimeEntry = JavaRuntime
				.newArchiveRuntimeClasspathEntry(runtimePath);
		runtimeEntry.setClasspathProperty(IRuntimeClasspathEntry.USER_CLASSES);

		IRuntimeClasspathEntry classesEntry = JavaRuntime
				.newArchiveRuntimeClasspathEntry(classes);
		classesEntry.setClasspathProperty(IRuntimeClasspathEntry.USER_CLASSES);
		classesEntry.setSourceAttachmentRootPath(src);

		String[] cp = new String[classpath.length + 2];
		cp[0] = runtimeEntry.getMemento();
		cp[1] = classesEntry.getMemento();
		for (int i = 0; i < classpath.length; i++) {
			cp[i + 2] = classpath[i].getMemento();
		}
		workingCopy.setAttribute(ATTR_CLASSPATH, Arrays.asList(cp));

		IGrammar grammar = unit.getGrammar();
		String grammarName = grammar.getElementName();
		String lexerPackage = getLexerJavaPackage(grammar);
		StringBuilder lexerClass = new StringBuilder(lexerPackage);

		String parserPackage = getParserJavaPackage(grammar);
		StringBuilder parserClass = new StringBuilder(parserPackage)
				.append(grammarName);

		if (grammar.isCombinedGrammar()) {
			lexerClass.append(grammarName).append("Lexer");
			parserClass.append("Parser");
		} else {
			// it can only be a parser grammar
			lexerClass.append(grammar.getOption("tokenVocab"));
		}

		int port = findFreePort();
		StringBuilder programArgs = new StringBuilder();
		programArgs.append("-port").append(" ").append(port).append(" ");
		programArgs.append("-testCase").append(" ").append(
				safeArg( test.getPath().toOSString())).append(" ");
		programArgs.append("-lexer").append(" ").append(lexerClass).append(" ");
		programArgs.append("-parser").append(" ").append(parserClass).append(
				" ");
		programArgs.append("-ruleName").append(" ").append(test.getRule());
		// programArgs.append("").append(" ").append();
		// programArgs.append("").append(" ").append();
		// programArgs.append("").append(" ").append();
		// programArgs.append("").append(" ").append();
		// programArgs.append("").append(" ").append();

		workingCopy
				.setAttribute(ATTR_PROGRAM_ARGUMENTS, programArgs.toString());
		// create and run the launch configuration
		ILaunchConfiguration config = workingCopy.doSave();
		config.launch(ILaunchManager.RUN_MODE, monitor);
		// delete the configuration
		config.delete();

		return port;
	}

	public void doLaunch(IProgressMonitor monitor, IJavaProject javaProject,
			IRuntimeClasspathEntry[] runtimeClassPath, boolean rebuild,
			AntlrBuildUnit unit, AntlrTestCase testCase, IPath compilerPath,
			IPath src, IPath classes) throws CoreException,
			InterruptedException, UnknownHostException, IOException {
		String[] classpath = new String[runtimeClassPath.length];

		for (int i = 0; i < runtimeClassPath.length; i++) {
			classpath[i] = runtimeClassPath[i].getMemento();
		}

		IGrammar grammar = unit.getGrammar();
		// ++++++++++++++++++++++++++++++++++++++++++++++++++++
		// ++++++++++++++++++++ run
		// ++++++++++++++++++++++++++++++++++++++++++++++++++++
		int port = run(monitor, unit, testCase, javaProject, runtimeClassPath,
				src, classes);
		// ++++++++++++++++++++++++++++++++++++++++++++++++++++
		// ++++++++++++++++++++ start listener
		// ++++++++++++++++++++++++++++++++++++++++++++++++++++
		startListener(grammar, port);
	}

	private void startListener(final IGrammar grammar, int port)
			throws UnknownHostException, IOException {
		try {
			// wait for debugger
			Thread.sleep(1000L);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		;
		final Socket socket = new Socket("localhost", port);
		socket.setTcpNoDelay(true);
		final PrintWriter writer = createWriter(socket);
		final BufferedReader reader = createReader(socket);

		Job job = new Job("ANTLR Event Listener") {
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				try {
					String line = reader.readLine();
					Collection<AntlrDebugEvent> events = new LinkedHashSet<AntlrDebugEvent>();
					while (!"terminate".equals(line)) {
						AntlrDebugEvent event = AntlrDebugEventFactory
								.createFromString(null, grammar, line);
						events.add(event);
						writer.println("ack");
						writer.flush();
						line = reader.readLine();
					}
					final IEvalElement element = AntlrDebugParser.createGraphModel(grammar, events);
					Display.getDefault().asyncExec(new Runnable() {

						public void run() {
							listener.setResult(element);
						}
					});
					reader.close();
					writer.close();
					socket.close();
					events.clear();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return Status.OK_STATUS;
			}
		};
		job.schedule();
	}
	
	private static PrintWriter createWriter(Socket socket) throws IOException {
		OutputStream os = socket.getOutputStream();
		OutputStreamWriter osw = new OutputStreamWriter(os, "UTF8");
		return new PrintWriter(new BufferedWriter(osw));
	}

	private static BufferedReader createReader(Socket socket)
			throws IOException {
		return new BufferedReader(new InputStreamReader(
				socket.getInputStream(), "UTF8"));
	}

	private static int findFreePort() {
		ServerSocket socket = null;
		try {
			socket = new ServerSocket(0);
			return socket.getLocalPort();
		} catch (IOException e) {
		} finally {
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
				}
			}
		}
		return 49153;
	}

	private static String getParserJavaPackage(IGrammar grammar) {
		if (grammar.isCombinedGrammar() || grammar.isParserGrammar()
				|| grammar.isTreeParserGrammar()) {
			return extractJavaPackage(grammar, "header");
		}
		return "";
	}

	private static String getLexerJavaPackage(IGrammar grammar) {
		if (grammar.isCombinedGrammar()) {
			return extractJavaPackage(grammar, "lexer::header");
		}
		if (grammar.isParserGrammar() || grammar.isTreeParserGrammar()) {
			IGrammar tokenGrammar = grammar.getTokenVocab();
			if (tokenGrammar != null) {
				return extractJavaPackage(tokenGrammar, "header");
			}
		}
		if (grammar.isLexerGrammar()) {
			return extractJavaPackage(grammar, "header");
		}
		return "";
	}

	private static String extractJavaPackage(IGrammar grammar, String action) {
		IGrammarAction header = grammar.findAction(action);
		String packageName = "";
		if (header != null) {
			String headerText = header.toString();
			Matcher matcher = AntlrConstants.ANTLR_JAVA_PACKAGE_PATTERN
					.matcher(headerText);
			if (matcher.find()) {
				packageName = matcher.group(1) + ".";
			}
		}
		return packageName;
	}

	public void setResultListener(AntlrResultListener listener) {
		this.listener = listener;
	}
}
