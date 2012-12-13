package org.deved.antlride.jdt.launch;

import static org.eclipse.jdt.launching.IJavaLaunchConfigurationConstants.ATTR_CLASSPATH;
import static org.eclipse.jdt.launching.IJavaLaunchConfigurationConstants.ATTR_DEFAULT_CLASSPATH;
import static org.eclipse.jdt.launching.IJavaLaunchConfigurationConstants.ATTR_MAIN_TYPE_NAME;
import static org.eclipse.jdt.launching.IJavaLaunchConfigurationConstants.ATTR_PROGRAM_ARGUMENTS;
import static org.eclipse.jdt.launching.IJavaLaunchConfigurationConstants.ATTR_PROJECT_NAME;
import static org.eclipse.jdt.launching.IJavaLaunchConfigurationConstants.ID_JAVA_APPLICATION;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.regex.Matcher;

import org.deved.antlride.core.AntlrConstants;
import org.deved.antlride.core.build.AntlrBuildUnit;
import org.deved.antlride.core.build.AntlrDeployer;
import org.deved.antlride.core.launch.AntlrLauncher;
import org.deved.antlride.core.model.IGrammar;
import org.deved.antlride.core.model.IGrammarAction;
import org.deved.antlride.core.model.evaluation.AntlrResultListener;
import org.deved.antlride.core.model.test.AntlrTestCase;
import org.deved.antlride.debug.AntlrDebugConstants;
import org.deved.antlride.debug.model.AntlrDebugTarget;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationType;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.launching.IRuntimeClasspathEntry;
import org.eclipse.jdt.launching.JavaRuntime;

public class AntlrJavaDebugLauncher extends AntlrAbstractJavaLauncher implements
		AntlrLauncher {

	private int run(IProgressMonitor monitor, AntlrBuildUnit unit,
			AntlrTestCase test, IJavaProject javaProject,
			IRuntimeClasspathEntry[] classpath, IPath src, IPath classes)
			throws CoreException, InterruptedException {
		ILaunchManager manager = DebugPlugin.getDefault().getLaunchManager();
		ILaunchConfigurationType type = manager
				.getLaunchConfigurationType(ID_JAVA_APPLICATION);// AntlrDebugConstants.ANTLR_LAUNCH_CONFIGURATION_TYPE);

		ILaunchConfigurationWorkingCopy workingCopy = type.newInstance(null,
				new StringBuilder(test.getRule()).append(" ").append(
						test.getName()).toString());

		workingCopy.setAttribute(ATTR_PROJECT_NAME, javaProject
				.getElementName());

		workingCopy.setAttribute(AntlrDebugConstants.ATTR_PROJECT_NAME,
				javaProject.getElementName());

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
				test.getPath().toOSString()).append(" ");
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

		workingCopy.setAttribute(AntlrDebugConstants.ATTR_ANTLR_PROGRAM, unit
				.getFile().getProjectRelativePath().toPortableString());
		// workingCopy.setAttribute(
		// IJavaLaunchConfigurationConstants.ATTR_SOURCE_PATH_PROVIDER,
		// "antlrJavaSourceLocator");
		workingCopy.setAttribute(AntlrDebugConstants.JAVA_EXTERNAL_SOURCE_PATH,
				src.toOSString());
		
		workingCopy.setAttribute(ILaunchConfiguration.ATTR_SOURCE_LOCATOR_ID,
				"org.deved.antlride.jdt.sourceLocator");
		// create and run the launch configuration
		final ILaunchConfiguration config = workingCopy.doSave();
		final ILaunch launch = config
				.launch(ILaunchManager.DEBUG_MODE, monitor);
		AntlrDebugTarget target = new AntlrDebugTarget(grammar, launch, launch
				.getProcesses()[0], port);
		launch.addDebugTarget(target);
		new Thread(new Runnable() {

			public void run() {
				while (!launch.isTerminated()) {
					try {
						Thread.sleep(1000L);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				try {
					config.delete();
				} catch (CoreException e) {
					e.printStackTrace();
				}
			}
		}).start();

		return port;
	}

	public void doLaunch(IProgressMonitor monitor, IJavaProject javaProject,
			IRuntimeClasspathEntry[] runtimeClassPath, boolean rebuild,
			AntlrBuildUnit unit, AntlrTestCase testCase, IPath compilerPath,
			IPath src, IPath classes) throws CoreException,
			InterruptedException, UnknownHostException, IOException {

		// ++++++++++++++++++++++++++++++++++++++++++++++++++++
		// ++++++++++++++++++++ run
		// ++++++++++++++++++++++++++++++++++++++++++++++++++++
		run(monitor, unit, testCase, javaProject, runtimeClassPath, src,
				classes);
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
		// do nothing
	}
}
