/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.internal.core.builder;

import static org.deved.antlride.core.AntlrConstants.ANTLR_BUILDER_RUNTIME;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.deved.antlride.core.AntlrConfiguration;
import org.deved.antlride.core.AntlrConstants;
import org.deved.antlride.core.AntlrCore;
import org.deved.antlride.core.AntlrLanguageTargetName;
import org.deved.antlride.core.AntlrLanguageToolkit;
import org.deved.antlride.core.build.AntlrBuildUnit;
import org.deved.antlride.core.build.AntlrSourceParserRepository;
import org.deved.antlride.core.integration.AntlrLanguageTargetService;
import org.deved.antlride.core.model.GrammarType;
import org.deved.antlride.core.model.IGrammar;
import org.deved.antlride.core.model.dltk.ast.DASTGrammar;
import org.deved.antlride.core.resources.AntlrLanguageTarget;
import org.deved.antlride.core.resources.AntlrLanguageTargetRepository;
import org.deved.antlride.core.resources.AntlrPackage;
import org.deved.antlride.core.resources.AntlrPackages;
import org.deved.antlride.core.util.AntlrTextHelper;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.dltk.compiler.problem.ProblemSeverities;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.core.builder.IBuildContext;
import org.eclipse.dltk.core.builder.ISourceLineTracker;
import org.eclipse.dltk.utils.TextUtils;

public class DefaultAntlrBuildUnit implements AntlrBuildUnit {

	public static final String ID = DefaultAntlrBuildUnit.class.getName();

	private static final String ANTLR_GEN_RESOURCE_PREFIX = "___antlr_gen__resource_";

	private static final String ANTLR_ROOT_PREFIX = "___antlr___root_";

	private static final String ANTLR_DEPENDENT_PREFIX = "___antlr___dependent_";

	private static final QualifiedName GENERATED_RESOURCES = new QualifiedName(
			AntlrCore.PLUGIN_ID, "___generated_resources_");

	private static final QualifiedName ROOTS = new QualifiedName(
			AntlrCore.PLUGIN_ID, "___roots_");

	private static final QualifiedName DEPENDENTS = new QualifiedName(
			AntlrCore.PLUGIN_ID, "___dependents_");

	private static final String SYS_PROPERTY = "-D%s=%s";

	protected IFile file;

	protected IGrammar grammar;

	protected AntlrConfiguration configuration;

	private AntlrPackage antlrPackage;

	private IPath outputPath;

	protected IWorkspaceRoot workspaceRoot;

	private IContainer outputContainer;

	private Collection<IResource> excludedResources;

	private IProgressMonitor monitor;

	private Collection<String> generatedResources;

	public Collection<String> addSystemProperties;

	private IPath absoluteLibFolder;

	protected char[] contents;

	protected ISourceLineTracker lineTracker;

	private IPath libFolder;

	private String description;

	public DefaultAntlrBuildUnit(IBuildContext buildContext) {
		DASTGrammar ast = (DASTGrammar) buildContext
				.get(IBuildContext.ATTR_MODULE_DECLARATION);

		if (ast != null) {
			grammar = ast.getGrammar();
		}
		if (grammar == null) {
			// forced? why???
			grammar = AntlrSourceParserRepository.getGrammar(buildContext
					.getSourceModule());
		}
		init(buildContext.getFile());

		loadGeneratedResources();

		lineTracker = buildContext.getLineTracker();

		contents = buildContext.getContents();
	}

	public DefaultAntlrBuildUnit(ISourceModule sourceModule) {
		grammar = AntlrSourceParserRepository.getGrammar(sourceModule);

		init((IFile) sourceModule.getResource());

		try {
			contents = sourceModule.getSourceAsCharArray();
		} catch (ModelException e) {
			contents = new char[0];
			AntlrCore.error(e);
		}

		lineTracker = TextUtils.createLineTracker(contents);
	}

	public DefaultAntlrBuildUnit(IGrammar grammar) {
		this.grammar = grammar;

		IFile resource = ResourcesPlugin.getWorkspace().getRoot()
				.getFile(grammar.getFile());

		init(resource);
	}

	private void init(IFile file) {
		this.file = file;

		configuration = new AntlrConfiguration(file);

		String packageName = configuration.getOption(ANTLR_BUILDER_RUNTIME);

		antlrPackage = AntlrPackages.getPackage(packageName);

		if (antlrPackage == null && packageName != null
				&& packageName.length() > 0) {
			throw new IllegalStateException("Invalid package: ANTLR-"
					+ packageName);
		}

		workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();

		excludedResources = new ArrayList<IResource>();

		addSystemProperties = new HashSet<String>();

		monitor = new NullProgressMonitor();

		try {
			file.setPersistentProperty(AntlrConstants.Q_ANTLR_GRAMMAR_TYPE,
					grammar.getGrammarType().name());
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}

	public Set<IFile> getRoots() {
		return readFilesFromPersistentProperty(getFile(), ROOTS,
				ANTLR_ROOT_PREFIX);
	}

	private Set<IFile> readFilesFromPersistentProperty(IFile owner,
			QualifiedName property, String prefix) {
		int count;
		try {
			count = Integer.parseInt(owner.getPersistentProperty(property));
		} catch (Exception ex) {
			count = 0;
		}

		Set<IFile> files = new HashSet<IFile>(count);
		for (int i = 0; i < count; i++) {
			QualifiedName qname = new QualifiedName(AntlrCore.PLUGIN_ID, prefix
					+ i);
			String path;
			try {
				path = owner.getPersistentProperty(qname);
			} catch (CoreException e) {
				path = null;
			}
			if (path != null) {
				IFile f = workspaceRoot.getFile(Path.fromPortableString(path));
				if (f.exists()) {
					files.add(f);
				}
			}
		}
		return files;
	}

	private void saveFilesToPersistentProperty(IFile owner, Set<IFile> files,
			QualifiedName property, String prefix) {
		int count = 0;
		for (IFile file : files) {
			if (file.exists()) {
				try {
					QualifiedName qname = new QualifiedName(
							AntlrCore.PLUGIN_ID, prefix + count);
					owner.setPersistentProperty(qname, file.getFullPath()
							.toString());
					count++;
				} catch (CoreException e) {
					e.printStackTrace();
				}
			}
		}
		try {
			if (ANTLR_ROOT_PREFIX.equals(prefix)) {
				// dependent grammar mark as dependent
				owner.setPersistentProperty(DEPENDENT_GRAMMAR, "true");
			}
			owner.setPersistentProperty(property, "" + count);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void setDependents(Set<IFile> dependents) {
		IFile owner = getFile();
		saveFilesToPersistentProperty(owner, dependents, DEPENDENTS,
				ANTLR_DEPENDENT_PREFIX);
		// set the root grammar
		for (IFile file : dependents) {
			if (file.exists()) {
				Set<IFile> roots = readFilesFromPersistentProperty(file, ROOTS,
						ANTLR_ROOT_PREFIX);
				roots.add(owner);
				saveFilesToPersistentProperty(file, roots, ROOTS,
						ANTLR_ROOT_PREFIX);
			}
		}
	}

	public Set<IFile> getDependents() {
		return readFilesFromPersistentProperty(getFile(), DEPENDENTS,
				ANTLR_DEPENDENT_PREFIX);
	}

	public void addSystemProperty(String name, Object value) {
		addSystemProperties.add(systemProperty(name, value));
	}

	public Collection<String> getSystemProperties() {
		Collection<String> list = new HashSet<String>();
		list.add(systemProperty("gERROR", ProblemSeverities.Error.ordinal()));
		list.add(systemProperty("gWARNING", ProblemSeverities.Warning.ordinal()));
		list.add(systemProperty("gFILE", getPath().lastSegment()));
		list.add(systemProperty("gINCLUDE_STACKTRACE",
				configuration.getIncludeStackStraceOnInternalErrors()));
		list.add(systemProperty("gout", getOutputFolder().toOSString()));
		// additional properties
		list.addAll(addSystemProperties);
		return list;
	}

	public char[] getContents() {
		return contents;
	}

	public ISourceLineTracker getLineTracker() {
		return lineTracker;
	}

	public String getBuildClassName() {
		return "org.deved.antlride.runtime.Tool2";
	}

	public IPath getAbsoluteLibraryPath() {
		if (absoluteLibFolder == null) {
			absoluteLibFolder = getOutputFolder();
			IFile tokenVocabFile = getTokenVocabFile();
			if (tokenVocabFile != null) {
				absoluteLibFolder = tokenVocabFile.getParent().getLocation();
			}
			File file = absoluteLibFolder.toFile();
			if (!file.exists()) {
				file.mkdirs();
			}
		}
		return absoluteLibFolder;
	}

	public IPath getLibraryPath() {
		if (libFolder == null) {
			libFolder = getOutputContainer().getFullPath();
			IFile tokenVocabFile = getTokenVocabFile();
			if (tokenVocabFile != null) {
				libFolder = tokenVocabFile.getParent().getFullPath();
			}
			File file = libFolder.toFile();
			if (!file.exists()) {
				file.mkdirs();
			}
		}
		return libFolder;
	}

	public Collection<? extends String> getApplicationArgs() {
		List<String> args = new ArrayList<String>();

		if (configuration.getNfa()) {
			args.add("-nfa");
		}
		if (configuration.getDfa()) {
			args.add("-dfa");
		}
		if (configuration.getReport()) {
			args.add("-report");
		}
		if (configuration.getProfile()) {
			args.add("-profile");
		}
		// -Xdfaverbose
		if (configuration.getXDfaVerbose()) {
			args.add("-Xdfaverbose");
		}
		// -Xnoprune
		if (configuration.getXNoPrune()) {
			args.add("-Xnoprune");
		}
		// -Xnocollapse
		if (configuration.getXNoCollapse()) {
			args.add("-Xnocollapse");
		}
		// -Xnomergestopstates
		if (configuration.getXNoMergeStopStates()) {
			args.add("-Xnomergestopstates");
		}
		// -debug
		if (configuration.getDebug()) {
			args.add("-debug");
		}
		// -trace
		if (configuration.getTrace()) {
			args.add("-trace");
		}
		// -XdbgST
		if (configuration.getXdbgSt()) {
			args.add("-XdbgST");
		}
		// -Xm
		String Xm = configuration.getOption(AntlrConstants.ANTLR_BUILDER_X_M);
		if (!"4".equals(Xm)) {
			// not the default value
			args.add("-Xm");
			args.add(Xm);
		}
		// - Xmaxdfaedges
		String Xmaxdfaedges = configuration
				.getOption(AntlrConstants.ANTLR_BUILDER_X_MAX_DFA_EDGES);
		if (!"65534".equals(Xmaxdfaedges)) {
			// not the default value
			args.add("-Xmaxdfaedges");
			args.add(Xmaxdfaedges);
		}
		// -Xconversiontimeout
		String Xconversiontimeout = configuration
				.getOption(AntlrConstants.ANTLR_BUILDER_X_CONVERSION_TIME_OUT);
		if (!"1000".equals(Xconversiontimeout)) {
			// not the default value
			args.add("-Xconversiontimeout");
			args.add(Xconversiontimeout);
		}
		// -Xmaxswitchcaselabels
		String Xmaxswitchcaselabels = configuration
				.getOption(AntlrConstants.ANTLR_CODE_GENERATOR_X_MAX_SWITCH_CASE_LABELS);
		if (!"300".equals(Xmaxswitchcaselabels)) {
			// not the default value
			args.add("-Xmaxswitchcaselabels");
			args.add(Xmaxswitchcaselabels);
		}
		// -Xminswitchalts
		String Xminswitchalts = configuration
				.getOption(AntlrConstants.ANTLR_CODE_GENERATOR_X_MIN_SWITCH_ALTS);
		if (!"3".equals(Xminswitchalts)) {
			// not the default value
			args.add("-Xminswitchalts");
			args.add(Xminswitchalts);
		}

		return args;
	}

	public IFile getTokenVocabFile() {
		if (grammar.isParserGrammar() || grammar.isTreeParserGrammar()) {
			String tokenVocab = grammar.getOption("tokenVocab");
			if (tokenVocab != null && tokenVocab.length() > 0) {
				return AntlrSourceParserRepository.lookupFile(
						grammar.getFolder(), tokenVocab + ".g");
			}
		}
		return null;
	}

	public IFile[] getDependencies() {
		Collection<IFile> deps = new HashSet<IFile>();
		if (grammar.isPrototypeGrammar()) {
			Set<IFile> roots = getRoots();
			if (roots.size() > 0) {
				IFile rootFile = roots.iterator().next();
				deps.addAll(readFilesFromPersistentProperty(rootFile,
						DEPENDENTS, ANTLR_DEPENDENT_PREFIX));
			}
		} else if (grammar.hasImports()) {
			// composite
			deps.addAll(readFilesFromPersistentProperty(file, DEPENDENTS,
					ANTLR_DEPENDENT_PREFIX));
		}
		return deps.toArray(new IFile[deps.size()]);
	}

	private int getGeneratedResourceCount() {
		int genResourceCount;
		try {
			String s = getOwner().getPersistentProperty(GENERATED_RESOURCES);
			genResourceCount = Integer.parseInt(s);
		} catch (NumberFormatException ex) {
			genResourceCount = 0;
		} catch (NullPointerException ex) {
			genResourceCount = 0;
		} catch (CoreException ex) {
			genResourceCount = 0;
		}
		return genResourceCount;
	}

	private void setGeneratedResourceCount(int count) throws CoreException {
		file.setPersistentProperty(GENERATED_RESOURCES, Integer.toString(count));
	}

	private IFile getOwner() {
		IFile file = getFile();
		try {
			if (getGrammar().isPrototypeGrammar()) {
				Set<IFile> roots = getRoots();
				if (roots.size() > 0) {
					file = roots.iterator().next();
				}
			}
		} catch (Exception ex) {
			AntlrCore.error("Couldn't get owner for " + file, ex);
		}
		return file;
	}

	private void loadGeneratedResources() {
		int genResourceCount = getGeneratedResourceCount();
		generatedResources = new ArrayList<String>();
		IFile file = getOwner();
		for (int i = 0; i < genResourceCount; i++) {
			try {
				QualifiedName qname = new QualifiedName(AntlrCore.PLUGIN_ID,
						ANTLR_GEN_RESOURCE_PREFIX + i);
				generatedResources.add(file.getPersistentProperty(qname));
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}

		try {
			file.setPersistentProperty(GENERATED_RESOURCES, "0");
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}

	public void cleanupResources() {
		for (String path : generatedResources) {
			try {
				IFile genfile = workspaceRoot.getFile(new Path(path));
				genfile.delete(true, monitor);
			} catch (CoreException ex) {
				AntlrCore.error(ex);
			}
		}
	}

	public boolean isGeneratedResource(IResource resource) {
		boolean generated = false;
		try {
			String owner = resource
					.getPersistentProperty(AntlrConstants.Q_ANTLR_RESOURCE_OWNER);
			if (owner != null) {
				generated = getPath().lastSegment().equals(
						new Path(owner).lastSegment());
				if (!generated && getGrammar().isPrototypeGrammar()) {
					Set<IFile> roots = getRoots();
					if (roots.size() > 0) {
						generated = roots.iterator().next().getFullPath()
								.lastSegment()
								.equals(new Path(owner).lastSegment());
					}
				}
			}
		} catch (CoreException ex) {
			AntlrCore.error(ex);
		}
		return generated;
	}

	public void excludeResource(IResource resource) {
		excludedResources.add(resource);
	}

	public IProgressMonitor getMonitor() {
		return monitor;
	}

	public IContainer getOutputContainer() {
		if (outputContainer == null) {
			outputContainer = workspaceRoot
					.getContainerForLocation(getOutputFolder());
		}
		return outputContainer;
	}

	public boolean markAsGeneratedResource(IResource resource) {
		if (!excludedResources.contains(resource) && resource instanceof IFile) {
			try {
				boolean derived = configuration.getMarkResourcesAsDerived();
				resource.setDerived(derived);
				IPath owner = getOwner().getFullPath();
				resource.setPersistentProperty(
						AntlrConstants.Q_ANTLR_RESOURCE_OWNER, owner.toString());
				// register generated resource at grammar file
				registerGeneratedResource(resource);
				return true;
			} catch (CoreException ex) {
				AntlrCore.error(ex);
			}
		}
		return false;
	}

	private void registerGeneratedResource(IResource resource) {
		try {
			int nextResource = getGeneratedResourceCount();

			QualifiedName qname = new QualifiedName(AntlrCore.PLUGIN_ID,
					ANTLR_GEN_RESOURCE_PREFIX + nextResource);

			file.setPersistentProperty(qname, resource.getFullPath().toString());

			setGeneratedResourceCount(nextResource + 1);
		} catch (CoreException ex) {
			AntlrCore.error(ex);
		}
	}

	public String getPackageClasspath() {
		return antlrPackage.getClasspathAsString();
	}

	public String getClasspath() {
		String language = grammar.getOption("language");
		AntlrLanguageTargetService languageTargetService = AntlrLanguageToolkit
				.getDefault().getLanguageTargetService(language);
		String[] projectClasspath = null;
		if (languageTargetService != null) {
			try {
				Map<String, Object> classpathInformation = languageTargetService
						.getClasspathInformation(new NullProgressMonitor(),
								this);
				projectClasspath = (String[]) classpathInformation
						.get("classpath");
				description = (String) classpathInformation.get("description");
			} catch (CoreException e) {
				AntlrCore.error(e);
			}
		}
		StringBuilder classpath = new StringBuilder();
		// use the defined packages
		// add custom target
		AntlrLanguageTarget[] languageTargets = AntlrLanguageTargetRepository
				.list();
		for (AntlrLanguageTarget antlrLanguageTarget : languageTargets) {
			classpath.append(antlrLanguageTarget.getPath());
			classpath.append(File.pathSeparator);
		}
		if (projectClasspath != null) {
			// use the project classpath
			for (String classpathentry : projectClasspath) {
				classpath.append(classpathentry);
				classpath.append(File.pathSeparator);
			}
			classpath.setLength(classpath.length()
					- File.pathSeparator.length());
			return classpath.toString();
		} else {
			classpath.append(antlrPackage.getClasspathAsString());
		}
		return classpath.toString();
	}

	public IPath getOutputFolder() {
		if (outputPath == null) {
			// language
			String lang = grammar.getOption("language");
			// grammar type
			GrammarType grammarType = grammar.getGrammarType();
			// -o output dir
			String outputDir = configuration.getOption("-o");
			outputPath = Path.fromOSString(outputDir);
			// append java package?
			if (AntlrLanguageTargetRepository.likeLanguage(
					AntlrLanguageTargetName.Java, lang)
					&& configuration.getAppendJavaPackage()) {
				String pathSuffix = null;
				if (grammarType == GrammarType.LEXER) {
					pathSuffix = AntlrTextHelper.getLexerJavaPackage(grammar);
				} else {
					pathSuffix = AntlrTextHelper.getJavaPackage(grammar);
				}
				if (pathSuffix != null && pathSuffix.length() > 0) {
					outputPath = outputPath.append(pathSuffix.replace('.',
							File.separatorChar));
				}
			}
			File file = outputPath.toFile();
			if (!file.exists()) {
				file.mkdirs();
			}
		}
		return outputPath;
	}

	public boolean canBuild() {
		if (grammar == null)
			return false;
		if (antlrPackage == null)
			return false;
		if (grammar.isPrototypeGrammar()) {
			return getRoots().size() > 0;
		}
		return true;
	}

	public AntlrConfiguration getConfiguration() {
		return configuration;
	}

	public IFile getFile() {
		return file;
	}

	public IGrammar getGrammar() {
		return grammar;
	}

	public IContainer getFolder() {
		return getFile().getParent();
	}

	public IPath getAbsolutePath() {
		if (grammar.isPrototypeGrammar()) {
			Set<IFile> roots = getRoots();
			if (roots.size() > 0) {
				return roots.iterator().next().getLocation();
			}
		}
		return file.getLocation();
	}

	public IPath getPath() {
		return file.getFullPath();
	}

	public IPath getFolderPath() {
		return getFolder().getFullPath();
	}

	public IPath getAbsoluteFolderPath() {
		return getFolder().getLocation();
	}

	public String getDescription() {
		StringBuilder builder = new StringBuilder();
		if (description == null) {
			builder.append(antlrPackage.getDescription()).append(
					".\nUsing project classpath: No.");
		} else {
			builder.append(description).append(
					".\nUsing project classpath: Yes.");
		}
		return builder.toString();
	}

	public static String systemProperty(String name, Object value) {
		return String.format(SYS_PROPERTY, name, value);
	}
}
