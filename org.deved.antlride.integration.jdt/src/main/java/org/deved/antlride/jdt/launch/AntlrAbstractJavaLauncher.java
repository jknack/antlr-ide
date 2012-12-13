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

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.UnknownHostException;
import java.util.Arrays;

import org.deved.antlride.core.AntlrCore;
import org.deved.antlride.core.build.AntlrBuildUnit;
import org.deved.antlride.core.build.AntlrDeployer;
import org.deved.antlride.core.build.AntlrDeployerRepository;
import org.deved.antlride.core.launch.AntlrLauncher;
import org.deved.antlride.core.model.IGrammar;
import org.deved.antlride.core.model.test.AntlrTestCase;
import org.deved.antlride.core.util.AntlrCoreHelper;
import org.deved.antlride.integration.jdt.AntlrJDT;
import org.deved.antlride.integration.jdt.AntlrJavaTargetService;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationType;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.launching.IRuntimeClasspathEntry;
import org.eclipse.jdt.launching.JavaRuntime;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.widgets.Display;
import org.osgi.framework.Bundle;

public abstract class AntlrAbstractJavaLauncher implements AntlrLauncher {

	public AntlrAbstractJavaLauncher() {
	}

	public final void launch(final boolean rebuild, final AntlrBuildUnit unit,
			final AntlrTestCase testCase) throws CoreException {
		try {
			final IJavaProject javaProject = JavaCore.create(unit.getFile()
					.getProject());
			if (javaProject != null /* && javaProject.exists() */) {
				ProgressMonitorDialog dialog = new ProgressMonitorDialog(null);
				dialog.run(true, true, new IRunnableWithProgress() {

					public void run(IProgressMonitor monitor)
							throws InvocationTargetException,
							InterruptedException {
						try {
							AntlrDeployer deployer = AntlrDeployerRepository
									.createDeployer();
							deployer.deployRuntime();

							IPath compilerPath = deployCompiler();
							IGrammar grammar = unit.getGrammar();
							IPath src = getFolder(grammar, "src", false);
							IPath classes = getFolder(grammar, "classes",
									rebuild);

							final IJavaProject javaProject = JavaCore
									.create(unit.getFile().getProject());

							IRuntimeClasspathEntry[] runtimeClassPath;
							if (javaProject.exists()) {
								runtimeClassPath = AntlrJavaTargetService
										.computeDefaultRuntimeClassPath(javaProject);
							} else {
								runtimeClassPath = new IRuntimeClasspathEntry[0];
							}

							runtimeClassPath = AntlrJavaTargetService
									.mergeClasspath(monitor, unit,
											runtimeClassPath);

							String[] classpath = AntlrJavaTargetService
									.getMementoClassPath(runtimeClassPath);
							// ++++++++++++++++++++++++++++++++++++++++++++++++++++
							// ++++++++++++++++++++ generate code
							// ++++++++++++++++++++++++++++++++++++++++++++++++++++
							if (rebuild) {
								generateCode(monitor, unit, javaProject,
										classpath, src);
								// ++++++++++++++++++++++++++++++++++++++++++++++++++++
								// ++++++++++++++++++++ compile
								// ++++++++++++++++++++++++++++++++++++++++++++++++++++
								compile(monitor, unit, javaProject,
										runtimeClassPath, compilerPath, src,
										classes);
							}

							doLaunch(monitor, javaProject, runtimeClassPath,
									rebuild, unit, testCase, compilerPath, src,
									classes);
						} catch (CoreException e) {
							showError(e.getStatus());
						} catch (UnknownHostException e) {
							showError(createFailStatus(e));
						} catch (IOException e) {
							showError(createFailStatus(e));
						}

					}
				});
			}
		} catch (Exception ex) {
			showError(createFailStatus(ex));
		}
	}

	private static void showError(final IStatus status) {
		Display.getDefault().asyncExec(new Runnable() {

			public void run() {
				ErrorDialog.openError(null, "Launch problem",
						"Execution was cancelled", status);
			}
		});

	}

	private static IStatus createFailStatus(Exception ex) {
		IStatus status = new Status(IStatus.ERROR, AntlrJDT.PLUGIN_ID, ex
				.getMessage(), ex);
		return status;
	}

	protected abstract void doLaunch(IProgressMonitor monitor,
			IJavaProject javaProject,
			IRuntimeClasspathEntry[] runtimeClassPath, boolean rebuild,
			AntlrBuildUnit unit, AntlrTestCase testCase, IPath compilerPath,
			IPath src, IPath classes) throws CoreException,
			InterruptedException, UnknownHostException, IOException;

	protected String safeArg(String arg) {
		String[] tokens = arg.split(" ");
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < tokens.length - 1; i++) {
			builder.append(tokens[i]).append("\" \"");
		}
		builder.append(tokens[tokens.length - 1]);
		return builder.toString();
	}

	protected void generateCode(IProgressMonitor monitor, AntlrBuildUnit unit,
			IJavaProject javaProject, String[] classpath, IPath src)
			throws CoreException, InterruptedException {
		ILaunchManager manager = DebugPlugin.getDefault().getLaunchManager();
		ILaunchConfigurationType type = manager
				.getLaunchConfigurationType(ID_JAVA_APPLICATION);
		ILaunchConfigurationWorkingCopy cgwc = type.newInstance(null,
				"Generating Code for " + unit.getGrammar().getElementName());
		cgwc.setAttribute(ATTR_PROJECT_NAME, javaProject.getElementName());
		cgwc.setAttribute(ATTR_MAIN_TYPE_NAME, "org.antlr.Tool");
		cgwc.setAttribute(ATTR_DEFAULT_CLASSPATH, false);
		cgwc.setAttribute(ATTR_CLASSPATH, Arrays.asList(classpath));
		// program arguments
		StringBuilder programArgs = new StringBuilder();
		programArgs.append("-verbose ");
		programArgs.append("-debug ");
		programArgs.append("-o ");
		programArgs.append(safeArg(src.toOSString()));
		programArgs.append(" ");
		IGrammar grammar = unit.getGrammar();
		if (grammar.isParserGrammar()) {
			String lexerGrammarName = grammar.getOption("tokenVocab");
			IPath lexerFile = unit.getAbsoluteLibraryPath().append(
					lexerGrammarName).addFileExtension("g");
			programArgs.append(safeArg(lexerFile.toOSString())).append(" ");
		}
		programArgs.append(safeArg(unit.getAbsolutePath().toOSString()));

		cgwc.setAttribute(ATTR_PROGRAM_ARGUMENTS, programArgs.toString());
		// create and run the launch configuration
		ILaunchConfiguration config = cgwc.doSave();
		ILaunch launch = config.launch(ILaunchManager.RUN_MODE, monitor);
		while (!launch.isTerminated()) {
			Thread.sleep(500L);
		}
		// delete the configuration
		config.delete();
		int exitValue = launch.getProcesses()[0].getExitValue();
		if (exitValue != 0) {
			throw new CoreException(new Status(IStatus.ERROR,
					AntlrJDT.PLUGIN_ID, "Code generation fails"));
		}
	}

	protected void compile(IProgressMonitor monitor, AntlrBuildUnit unit,
			IJavaProject javaProject, IRuntimeClasspathEntry[] classpath,
			IPath compilerPath, IPath src, IPath classes) throws CoreException,
			InterruptedException {
		ILaunchManager manager = DebugPlugin.getDefault().getLaunchManager();
		ILaunchConfigurationType type = manager
				.getLaunchConfigurationType(ID_JAVA_APPLICATION);

		ILaunchConfigurationWorkingCopy workingCopy = type.newInstance(null,
				"Compiling " + unit.getGrammar().getElementName());

		workingCopy.setAttribute(ATTR_PROJECT_NAME, javaProject
				.getElementName());
		workingCopy.setAttribute(ATTR_MAIN_TYPE_NAME,
				"org.eclipse.jdt.internal.compiler.batch.Main");
		workingCopy.setAttribute(ATTR_DEFAULT_CLASSPATH, false);

		IRuntimeClasspathEntry compilerEntry = JavaRuntime
				.newArchiveRuntimeClasspathEntry(compilerPath);
		compilerEntry.setClasspathProperty(IRuntimeClasspathEntry.USER_CLASSES);

		workingCopy.setAttribute(ATTR_CLASSPATH, Arrays.asList(compilerEntry
				.getMemento()));

		StringBuilder compilerArgs = new StringBuilder();
		compilerArgs.append("-showversion ");
		compilerArgs.append("-cp ");
		for (IRuntimeClasspathEntry rcp : classpath) {
			compilerArgs.append(safeArg(rcp.getLocation()));
			compilerArgs.append(File.pathSeparator);
		}
		compilerArgs.setLength(compilerArgs.length() - 1);
		String sourceLevel = "-"
				+ javaProject.getOption(JavaCore.COMPILER_COMPLIANCE, true);
		compilerArgs.append(" -nowarn ");
		compilerArgs.append(sourceLevel);
		compilerArgs.append(" -d ");
		compilerArgs.append(safeArg(classes.toOSString()));
		compilerArgs.append(" ");
		compilerArgs.append(safeArg(src.toOSString()));

		workingCopy.setAttribute(ATTR_PROGRAM_ARGUMENTS, compilerArgs
				.toString());
		// create and run the launch configuration
		ILaunchConfiguration config = workingCopy.doSave();
		ILaunch launch = config.launch(ILaunchManager.RUN_MODE, monitor);
		while (!launch.isTerminated()) {
			Thread.sleep(500L);
		}
		// delete the configuration
		config.delete();
		int exitValue = launch.getProcesses()[0].getExitValue();
		if (exitValue != 0) {
			throw new CoreException(new Status(IStatus.ERROR,
					AntlrJDT.PLUGIN_ID, "Compilation fails"));
		}
	}

	private static IPath deployCompiler() {
		IPath root = AntlrJDT.getDefault().getStateLocation().append("jars");
		IPath lib = Path.fromPortableString("lib");
		String jarFileName = "org.eclipse.jdt.core_3.4.0.jar";
		IPath compilerJar = root.append(jarFileName);
		try {
			File file = compilerJar.toFile();
			File container = file.getParentFile();
			if (!container.exists()) {
				container.mkdirs();
			}
			if (!file.exists()) {
				Bundle bundle = AntlrJDT.getDefault().getBundle();
				String bundleFile = lib.append(jarFileName).toPortableString();
				AntlrCoreHelper.copyFileFromBundle(bundle, bundleFile, file);
			}
		} catch (IOException e) {
			AntlrCore.error(e);
		}

		return compilerJar;
	}

	private static IPath getFolder(IGrammar grammar, String name, boolean clean) {
		IPath path = getDeployHome(grammar).append(grammar.getElementName())
				.append(name);
		File folder = path.toFile();
		if (!folder.exists()) {
			folder.mkdirs();
		} else if (clean) {
			clean(folder);
		}
		return path;
	}

	private static IPath getDeployHome(IGrammar grammar) {
		return AntlrCore.getDefault().getStateLocation().append("java").append(
				grammar.getFolder());
	}

	private static void clean(File dir) {
		File[] files = dir.listFiles();
		if (files != null && files.length > 0) {
			for (File file : files) {
				if (file.isDirectory()) {
					clean(file);
				}
				file.delete();
			}
		}
	}
}