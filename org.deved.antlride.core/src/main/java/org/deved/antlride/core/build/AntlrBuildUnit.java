/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.core.build;

import java.util.Collection;
import java.util.Set;

import org.deved.antlride.core.AntlrConfiguration;
import org.deved.antlride.core.AntlrConstants;
import org.deved.antlride.core.model.IGrammar;
import org.deved.antlride.internal.core.builder.DefaultAntlrBuildUnit;
import org.deved.antlride.internal.core.builder.FileSystemAntlrBuildUnit;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.builder.IBuildContext;
import org.eclipse.dltk.core.builder.ISourceLineTracker;

public interface AntlrBuildUnit {

	public enum AntlrBuildUnitType {
		WORKSPACE {
			@Override
			public AntlrBuildUnit create(IBuildContext buildContext) {
				return new DefaultAntlrBuildUnit(buildContext);
			}

			@Override
			public AntlrBuildUnit create(IGrammar grammar) {
				return new DefaultAntlrBuildUnit(grammar);
			}

			@Override
			public AntlrBuildUnit create(ISourceModule sourceModule) {
				return new DefaultAntlrBuildUnit(sourceModule);
			}
		},
		FILE_SYSTEM {
			@Override
			public AntlrBuildUnit create(IBuildContext buildContext) {
				return new FileSystemAntlrBuildUnit(buildContext);
			}
			
			@Override
			public AntlrBuildUnit create(IGrammar grammar) {
				return new FileSystemAntlrBuildUnit(grammar);
			}
			
			@Override
			public AntlrBuildUnit create(ISourceModule sourceModule) {
				return new FileSystemAntlrBuildUnit(sourceModule);
			}
		};

		public abstract AntlrBuildUnit create(IBuildContext buildContext);

		public abstract AntlrBuildUnit create(ISourceModule sourceModule);

		/**
		 * This method is not intend for public use
		 * 
		 * @param grammar
		 * @return
		 */
		public abstract AntlrBuildUnit create(IGrammar grammar);
	}

	QualifiedName DEPENDENT_GRAMMAR = AntlrConstants.Q_ANTLR_PROTOTYPE_GRAMMAR;

	boolean canBuild();

	void excludeResource(IResource resource);

	boolean isGeneratedResource(IResource resource);

	boolean markAsGeneratedResource(IResource resource);

	String getClasspath();

	String getPackageClasspath();

	AntlrConfiguration getConfiguration();

	IPath getOutputFolder();

	IPath getAbsoluteLibraryPath();

	IPath getLibraryPath();

	void cleanupResources();

	Set<IFile> getRoots();

	Set<IFile> getDependents();

	IFile getTokenVocabFile();

	void setDependents(Set<IFile> dependents);

	IContainer getOutputContainer();

	IFile getFile();

	IContainer getFolder();

	IPath getPath();

	IPath getAbsolutePath();

	IPath getFolderPath();

	IPath getAbsoluteFolderPath();

	IProgressMonitor getMonitor();

	String getDescription();

	void addSystemProperty(String name, Object value);

	Collection<String> getSystemProperties();

	IFile[] getDependencies();

	Collection<? extends String> getApplicationArgs();

	String getBuildClassName();

	char[] getContents();

	ISourceLineTracker getLineTracker();

	IGrammar getGrammar();
}
