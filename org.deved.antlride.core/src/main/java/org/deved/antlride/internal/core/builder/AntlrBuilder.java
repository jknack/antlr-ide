/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.internal.core.builder;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.deved.antlride.core.AntlrConfiguration;
import org.deved.antlride.core.AntlrConsole;
import org.deved.antlride.core.AntlrCore;
import org.deved.antlride.core.AntlrLanguageToolkit;
import org.deved.antlride.core.build.AntlrBuildUnit;
import org.deved.antlride.core.build.AntlrBuildUnitRepository;
import org.deved.antlride.core.build.AntlrDeployer;
import org.deved.antlride.core.build.AntlrDeployerRepository;
import org.deved.antlride.core.build.AntlrProblem;
import org.deved.antlride.core.build.AntlrProblemFactory;
import org.deved.antlride.core.build.AntlrSourceParserRepository;
import org.deved.antlride.core.env.JavaEnvironmentRepository;
import org.deved.antlride.core.env.JavaEnvironmentRepositoryLookup;
import org.deved.antlride.core.util.AntlrTextHelper;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.dltk.compiler.problem.DefaultProblem;
import org.eclipse.dltk.compiler.problem.DefaultProblemFactory;
import org.eclipse.dltk.compiler.problem.IProblemFactory;
import org.eclipse.dltk.compiler.problem.IProblemReporter;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.builder.IBuildContext;
import org.eclipse.dltk.core.builder.IBuildParticipant;
import org.eclipse.dltk.core.builder.IBuildParticipantExtension;
import org.eclipse.dltk.internal.core.builder.BuildProblemReporter;
import org.eclipse.dltk.internal.core.builder.SourceModuleBuildContext;

@SuppressWarnings("restriction")
public class AntlrBuilder implements IBuildParticipant,
		IBuildParticipantExtension {

	private static final String PATTERN = "%32";

	private static final String LINE_ESC_PROPERTY = "%0A";

	private static final String CARRY_RET_ESC_PROPERTY = "%0D";

//	private boolean depEnable = false;

	public AntlrBuilder() {
	}

	public boolean beginBuild(int buildType) {
		return buildType == INCREMENTAL_BUILD || buildType == FULL_BUILD;
	}

	
	public void build(IBuildContext context) throws CoreException {
		try {
			AntlrLanguageToolkit languageToolkit = AntlrLanguageToolkit
					.getDefault();
			AntlrConsole console = languageToolkit.getConsole();

			// create a build unit for the current resource
			AntlrBuildUnit unit = AntlrBuildUnitRepository.create(context);

			if (!unit.canBuild()) {
				// no package defined or can't get the grammar from context
				return;
			}

			IFile tokenVocabFile = unit.getTokenVocabFile();
			if (tokenVocabFile != null) {
				ISourceModule tokenVocabModule = AntlrSourceParserRepository
						.getSourceModule(tokenVocabFile);
				SourceModuleBuildContext tokenVocabBuildContext = new SourceModuleBuildContext(
						new DefaultProblemFactory(), tokenVocabModule, INCREMENTAL_BUILD);
				build(tokenVocabBuildContext);
				BuildProblemReporter reporter = (BuildProblemReporter) tokenVocabBuildContext
						.getProblemReporter();
				if (reporter != null) {
					reporter.flush();
				}
			}

			// clean up errors for composite grammars
			IFile[] dependencies = unit.getDependencies();
			for (IFile file : dependencies) {
				clearMarkers(file);
			}

			// pre-build phase
			preBuild(unit);

			// java virtual machine path
			JavaEnvironmentRepository javaEnvironmentRepository = JavaEnvironmentRepositoryLookup
					.lookup();

			IPath java = javaEnvironmentRepository.getEnvironment(
					unit.getFile().getProject()).getJavaPath();

			List<String> command = new ArrayList<String>();
			command.add(java.toOSString());
			String memory = unit.getConfiguration().getXmx();
			if (!"0".equals(memory)) {
				command.add("-Xmx" + memory + "m");
			}
			// system properties
			unit.addSystemProperty("gPATTERN", PATTERN);
			unit.addSystemProperty("gLINE_ESCAPE", LINE_ESC_PROPERTY);
			unit.addSystemProperty("gCREUTRNS_ESCAPE", CARRY_RET_ESC_PROPERTY);
			command.addAll(unit.getSystemProperties());

			// build classpath
			AntlrDeployer deployer = AntlrDeployerRepository.createDeployer();

			Set<String> hortogonalClasspath = new LinkedHashSet<String>();
			for (IPath deployClasspath : deployer.deployRuntime())
				hortogonalClasspath.add(deployClasspath.toOSString());
			hortogonalClasspath.addAll(Arrays.asList(unit.getClasspath().split(
					File.pathSeparator)));

			StringBuilder classpath = new StringBuilder();
			Iterator<String> classPathIterator = hortogonalClasspath.iterator();
			classpath.append(classPathIterator.next());
			while (classPathIterator.hasNext()) {
				classpath.append(File.pathSeparator);
				classpath.append(classPathIterator.next());
			}

			command.add("-classpath");
			command.add(classpath.toString());

			// create the process
			command.add(unit.getBuildClassName());

			List<String> appcmd = new ArrayList<String>();
			appcmd.addAll(unit.getApplicationArgs());
			appcmd.add("-lib");
			appcmd.add(unit.getAbsoluteLibraryPath().toOSString());
			appcmd.add("-o");
			appcmd.add(unit.getOutputFolder().toOSString());
			appcmd.add(unit.getAbsolutePath().toOSString());

			command.addAll(appcmd);
			String fullpath = unit.getAbsolutePath().toOSString();

//			console.info(command.toString());
			console.info(unit.getDescription());
			console.info("Grammar: " + fullpath);
			// StringBuilder options = new StringBuilder();
			// for (int k = 0; k < appcmd.size() - 1; k++) {
			// options.append(appcmd.get(k));
			// options.append(" ");
			// }
			// console.info("Options: " + options.append("\n").toString());

			ProcessBuilder pb = new ProcessBuilder(command);
			pb.directory(unit.getAbsoluteFolderPath().toFile());

			long startBuild = System.currentTimeMillis();

			Process process = pb.start();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					process.getInputStream()));

			IProblemReporter problemReporter = context.getProblemReporter();
			Set<String> problems = new HashSet<String>();
			/** This map of problem reporters is used with composite grammars */
			Map<IPath, IProblemReporter> reporters = new HashMap<IPath, IProblemReporter>() {
				private static final long serialVersionUID = 1L;

				private IProblemFactory problemFactory = new DefaultProblemFactory();

				@Override
				public IProblemReporter get(Object key) {
					IProblemReporter problemReporter = super.get(key);
					if (problemReporter == null) {
						IWorkspaceRoot root = ResourcesPlugin.getWorkspace()
								.getRoot();
						IPath path = (IPath) key;
						IResource resource = root.getFile(path);
						problemReporter = new BuildProblemReporter(problemFactory, resource);
						put(path, problemReporter);
					}
					return problemReporter;
				}
			};
			reporters.put(unit.getPath(), problemReporter);
			int errors = 0;
			int warnings = 0;
			String line = in.readLine();
			while (line != null) {
				if (line.startsWith(PATTERN)) {
					line = AntlrTextHelper.unEscapeNewlines(line);
					// check for duplicated problems
					if (problems.add(line)) {
						String[] message = line.split(PATTERN);
						AntlrProblem problem = AntlrProblemFactory.create(unit,
								message);
						// report problems
						reporters.get(problem.getFilepath()).reportProblem(
								problem.toDLTKProblem());
						if (problem.isError()) {
							errors++;
						} else {
							warnings++;
						}
						String desc = problem.getRawMessage();
						console.error(desc);
						if (problem.getLineWithProblem().length() > 0) {
							console.error(" |---> "
									+ problem.getLineWithProblem() + "\n");
						}
						String key = desc.split("\n")[0];
						console.setAttribute(key.trim(), problem);
					}
				} else {
					if (!fullpath.equals(line)) {
						// ANTLR 3.1.3 print the full path, don't let to print
						// it
						console.info(line);
					}
				}
				line = in.readLine();
			}
			long endBuild = System.currentTimeMillis();
			long buildTime = endBuild - startBuild;
			try {
				in.close();
				process.destroy();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			long seconds = TimeUnit.MILLISECONDS.toSeconds(buildTime);
			if (warnings > 0) {
				console.error(String.format("\n%s warning%s\n", warnings,
						warnings == 1 ? "" : "s"));
			}
			if (errors == 0) {
				console.info("BUILD SUCCESSFUL");
			} else {
				console.error(String.format("%s error%s\n", errors,
						errors == 1 ? "" : "s"));
				console.error("BUILD FAIL");
			}
			long time = seconds;
			String timeunit = "second";
			if (time <= 0) {
				time = buildTime;
				timeunit = "millisecond";
			}
			console.info(String.format("Total time: %s %s%s\n", time, timeunit,
					time == 1 ? "" : "s"));
			postBuild(unit);
		} catch (CoreException ex) {
			throw ex;
		} catch (Exception ex) {
			IStatus status = new Status(IStatus.ERROR, AntlrCore.PLUGIN_ID,
					"Build fail", ex);
			throw new CoreException(status);
		}
	}

	private void preBuild(final AntlrBuildUnit unit) {
		AntlrConfiguration conf = unit.getConfiguration();
		if (conf.isOutputFolderRelativeToWorkspace()) {
			// check for generated resources and deleted
			// this only works for compatibility for ANTLR IDE 1.3.0
			IContainer outputContainer = unit.getOutputContainer();
			if (outputContainer != null && outputContainer.exists()) {
				IResourceVisitor preBuildVisitor = new IResourceVisitor() {
					public boolean visit(IResource resource)
							throws CoreException {
						if (unit.isGeneratedResource(resource)) {
							try {
								resource.delete(true, unit.getMonitor());
							} catch (CoreException ex) {
								AntlrCore.error(ex);
							}
						} else {
							unit.excludeResource(resource);
						}
						return true;
					}
				};
				try {
					outputContainer.accept(preBuildVisitor,
							IResource.DEPTH_ONE, IContainer.INCLUDE_PHANTOMS);
				} catch (CoreException e) {
					AntlrCore.error(e);
				}
			}
			unit.cleanupResources();
		}
	}

	private void postBuild(final AntlrBuildUnit unit) {
		AntlrConfiguration conf = unit.getConfiguration();
		if (conf.isOutputFolderRelativeToWorkspace()) {
			IContainer outputContainer = unit.getOutputContainer();
			IResourceVisitor postBuildVisitor = new IResourceVisitor() {
				public boolean visit(IResource resource) throws CoreException {
					if (unit.markAsGeneratedResource(resource)) {
						// move the *.tokens file
						String ext = resource.getLocation().getFileExtension();
						if ("tokens".equals(ext)) {
							IPath outputFolder = unit.getOutputFolder();
							IPath grammarFolder = unit.getAbsoluteFolderPath();
							if (!grammarFolder.equals(outputFolder)) {
								try {
									IPath moveTo = unit.getFolderPath().append(
											resource.getName());
									IWorkspaceRoot root = ResourcesPlugin
											.getWorkspace().getRoot();
									IFile prevTokensFile = root.getFile(moveTo);
									if (prevTokensFile.exists()) {
										prevTokensFile.delete(true, unit
												.getMonitor());
									}
									resource.move(moveTo, true, unit
											.getMonitor());
								} catch (CoreException e) {
									AntlrCore.error(e);
								}
							}
						}
					}
					return true;
				}
			};
			try {
				if (outputContainer != null) {
					outputContainer.refreshLocal(IResource.DEPTH_ONE, unit
							.getMonitor());
					outputContainer.accept(postBuildVisitor,
							IResource.DEPTH_ONE, IResource.NONE);
					// after process all the generated resources the *.tokens
					// file
					// is moved to the same location of the grammar
					// refresh the output dir
					outputContainer.refreshLocal(IResource.DEPTH_ONE, unit
							.getMonitor());
				}
				// refresh the grammar folder
				unit.getFolder().refreshLocal(IResource.DEPTH_ONE,
						unit.getMonitor());
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
	}

	public void endBuild(IProgressMonitor monitor) {
	}

	public void buildExternalModule(IBuildContext context) throws CoreException {
	}

	private static void clearMarkers(IResource resource) {
		if (resource != null) {
			try {
				if (resource.findMarkers(DefaultProblem.MARKER_TYPE_PROBLEM,
						true, IResource.DEPTH_INFINITE).length > 0) {
					resource.deleteMarkers(DefaultProblem.MARKER_TYPE_PROBLEM,
							true, IResource.DEPTH_INFINITE);
				}
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
	}

//	@SuppressWarnings("rawtyps")
//	public DependencyResponse getDependencies(int buildType, Set localElements,
//			Set externalElements, Set oldExternalFolders, Set externalFolders) {
//		DependencyResponse response = null;
//		// enable this when the build order works as expected
//		if (depEnable) {
//			Set<ISourceModule> depmodules = new LinkedHashSet<ISourceModule>();
//			for (Object object : localElements) {
//				ISourceModule sourceModule = (ISourceModule) object;
//				AntlrBuildUnit unit = AntlrBuildUnitRepository
//						.create(sourceModule);
//				if (unit.canBuild()) {
//					depmodules.addAll(getDependencies(unit));
//				}
//			}
//			if (depmodules.size() > 0) {
//				System.out.println("DEPS=>" + depmodules);
//				response = DependencyResponse.createLocal(depmodules);
//			}
//		}
//		return response;
//	}

//	private Set<ISourceModule> getDependencies(AntlrBuildUnit unit) {
//		Set<ISourceModule> depmodules = new LinkedHashSet<ISourceModule>();
//		IFile[] dependencies = unit.getDependencies();
//		for (IFile depfile : dependencies) {
//			try {
//				if (depfile.exists()) {
//					ISourceModule depsm = AntlrSourceParserRepository
//							.getSourceModule(depfile);
//					if (depsm != null) {
//						depmodules.add(depsm);
//					}
//				}
//			} catch (FileNotFoundException e) {
//				e.printStackTrace();
//			}
//		}
//		return depmodules;
//	}

}
