/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.core.build;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import org.deved.antlride.core.AntlrCore;
import org.deved.antlride.core.AntlrNature;
import org.deved.antlride.core.model.IGrammar;
import org.deved.antlride.core.model.IGrammarBuilder;
import org.deved.antlride.core.model.dltk.ast.DASTGrammar;
import org.deved.antlride.internal.core.model.GrammarRepository;
import org.deved.antlride.internal.core.parser.visitors.AntlrSourceElementRequest;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.compiler.SourceElementRequestVisitor;
import org.eclipse.dltk.compiler.env.IModuleSource;
import org.eclipse.dltk.core.AbstractSourceElementParser;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.ISourceElementParser;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.SourceParserUtil;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class AntlrSourceParserRepository extends AbstractSourceElementParser {

	private static class GrammarResourceLocator implements IResourceVisitor {

		private String grammarName;

		private IFile grammarFile = null;

		public GrammarResourceLocator(String grammarName) {
			this.grammarName = grammarName;
		}

		public boolean visit(IResource resource) throws CoreException {
			if (resource.getType() == IResource.FOLDER) {
				return true;
			}
			if (resource.getType() == IResource.FILE) {
				if (resource.getName().equals(grammarName)) {
					grammarFile = (IFile) resource;
				}
			}
			return false;
		}

	}

	private static final Map<String, String> PARSERS = new HashMap<String, String>() {
		private static final long serialVersionUID = 1L;

		{
			put("stg", "StringTemplate");
			put("st", "StringTemplate");
			put("gunit", "GUnit");
			put("testsuite", "GUnit");
		}
	};

	public AntlrSourceParserRepository() {
	}

	public static IGrammar parse(String source) throws CoreException {
		// java.library.path
		GrammarRepository builderFactory = GrammarRepository.getInstance();
		IGrammarBuilder grammarBuilder = builderFactory.createGrammarBuilder();
		IGrammar grammar = grammarBuilder.process(source);
		return grammar;
	}

	@Override
	public void parseSourceModule(IModuleSource module) {
		// TODO: REMOVE THIS METHOD WHEN DLTK ALLOW MULTIPLE parser per project
		ISourceElementParser sep = getSourceElementParser(module
				.getModelElement().getElementName());
		if (sep != null) {
			sep.setRequestor(getRequestor());
			sep.setReporter(getProblemReporter());
			sep.parseSourceModule(module);
			return;
		}
		super.parseSourceModule(module);
	}

	/**
	 * 
	 * @param filename
	 * @return
	 */
	private static ISourceElementParser getSourceElementParser(String filename) {
		try {
			String ext = filename.substring(filename.lastIndexOf('.') + 1);
			String parserName = PARSERS.get(ext);
			if (parserName == null)
				return null;
			BundleContext context = AntlrCore.getDefault().getContext();
			ServiceReference[] serviceReference = context.getServiceReferences(
					ISourceElementParser.class.getName(), "(parser="
							+ parserName + ")");
			ISourceElementParser sep = (ISourceElementParser) context
					.getService(serviceReference[0]);
			return sep;
		} catch (Throwable t) {
			AntlrCore.error(t);
			return null;
		}
	}

	// @Override
	// protected ModuleDeclaration parse(
	// org.eclipse.dltk.compiler.env.ISourceModule module,
	// ISourceModuleInfo mifo) {
	// try {
	// String filename = new String(module.getFileName());
	// IPath filePath = new Path(filename).makeRelative();
	// if ("|".equals(filePath.segment(0))) {
	// // the DLTK indexer send the file name with | at the first
	// // character, remove this character
	// filePath = filePath.removeFirstSegments(1);
	// }
	// IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(
	// filePath);
	// boolean isProtoype = isPrototype(file);
	// final ModuleDeclaration moduleDeclaration;
	// IProblemReporter problemReporter = null;
	// if (!isProtoype) {
	// problemReporter = getProblemReporter();
	// }
	//
	// // source module
	// if (module instanceof ISourceModule) {
	// moduleDeclaration = SourceParserUtil.getModuleDeclaration(
	// (ISourceModule) module, problemReporter, mifo);
	// } else {
	// moduleDeclaration = SourceParserUtil.getModuleDeclaration(
	// filePath.toString().toCharArray(), module
	// .getContentsAsCharArray(), getNatureId(),
	// problemReporter, mifo);
	// }
	// return moduleDeclaration;
	// } catch (Throwable e) {
	// AntlrCore.error(e);
	// return new ModuleDeclaration(module.getContentsAsCharArray().length);
	// }
	// }

	// private boolean isPrototype(IFile file) throws Exception {
	// boolean isProtoype = Boolean
	// .valueOf(file
	// .getPersistentProperty(AntlrConstants.Q_ANTLR_PROTOTYPE_GRAMMAR));
	// if (!isProtoype) {
	// String key = file.getFullPath().toString();
	// isProtoype = AntlrCore.getDefault().getPluginPreferences()
	// .getBoolean(key);
	// }
	// return isProtoype;
	// }

	private static IFile findGrammarFile(IContainer root, String tokenVocab) {
		IFile tokenVocabFile = root.getFile(new Path(tokenVocab));
		if (tokenVocabFile != null && tokenVocabFile.exists()) {
			// hey, we have luck the token grammar is in the same folder
			return tokenVocabFile;
		}
		// no luck :(, go to the top most folder and start searching
		// from there
		IContainer parent = root.getParent();
		while (parent.getType() != IResource.PROJECT) {
			root = (IContainer) parent;
			parent = parent.getParent();
		}
		GrammarResourceLocator locator = new GrammarResourceLocator(tokenVocab);
		try {
			root.accept(locator);
		} catch (CoreException e) {
			AntlrCore.error(e);
		}
		return locator.grammarFile;
	}

	public static IFile lookupFile(IPath root, String grammarName) {
		IWorkspaceRoot workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
		IContainer containter = root.segmentCount() == 1 ? workspaceRoot
				.getProject(root.segment(0)) : workspaceRoot.getFolder(root);

		return findGrammarFile(containter, grammarName);
	}

	public static IGrammar lookupGrammar(IPath root, String grammarName) {
		IFile file = lookupFile(root, grammarName);

		if (file != null && file.exists()) {
			ISourceModule sourceModule = DLTKCore.createSourceModuleFrom(file);
			return getGrammar(sourceModule);
		}
		return null;
	}

	public static IGrammar getGrammar(IPath path) throws FileNotFoundException {
		IFile fileSourceModule = ResourcesPlugin.getWorkspace().getRoot()
				.getFile(path);
		if (!fileSourceModule.exists()) {
			throw new FileNotFoundException(path.toOSString());
		}
		ISourceModule sourceModule = DLTKCore
				.createSourceModuleFrom(fileSourceModule);
		return getGrammar(sourceModule);
	}

	public static ISourceModule getSourceModule(IFile file)
			throws FileNotFoundException {
		ISourceModule sourceModule = DLTKCore.createSourceModuleFrom(file);
		return sourceModule;
	}

	public static IGrammar getGrammar(ISourceModule module) {
		ISourceElementParser sourceElementParser = getSourceElementParser(module
				.getResource().getFullPath().toString());
		if (sourceElementParser != null) {
			return null;
		}
		DASTGrammar grammarModule = (DASTGrammar) SourceParserUtil
				.getModuleDeclaration(module, null);
		return grammarModule.getGrammar();
	}

	public static DASTGrammar parseModule(ISourceModule module) {
		IGrammar grammar = getGrammar(module);

		return (DASTGrammar) grammar.getAdapter(ASTNode.class);
	}

	@Override
	protected String getNatureId() {
		return AntlrNature.NATURE_ID;
	}

	@Override
	protected SourceElementRequestVisitor createVisitor() {
		return new AntlrSourceElementRequest(getRequestor());
	}
}
