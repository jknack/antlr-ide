/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.core;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ProjectScope;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.core.runtime.preferences.DefaultScope;
import org.eclipse.core.runtime.preferences.IPreferencesService;
import org.eclipse.core.runtime.preferences.IScopeContext;
import org.eclipse.core.runtime.preferences.InstanceScope;

public class AntlrConfiguration {

	private IPreferencesService service;

	private IScopeContext[] contexts;

	private IPath fFile;

	private IPath fFolder;

	private IPath fAbsoluteFile;

	private IPath fAbsoluteFolder;

	private Map<String, String> runtimePrefs;

	public AntlrConfiguration(IResource file) {
		IProject project = file.getProject();
		service = Platform.getPreferencesService();
		contexts = new IScopeContext[] { new ProjectScope(project),
				new InstanceScope(), new DefaultScope() };
		fFile = file.getFullPath();
		fFolder = fFile.removeLastSegments(1);
		fAbsoluteFile = file.getLocation();
		fAbsoluteFolder = fAbsoluteFile.removeLastSegments(1);
		runtimePrefs = new HashMap<String, String>();
	}

	public boolean getIncludeStackStraceOnInternalErrors() {
		return Boolean
				.valueOf(getOption(AntlrConstants.ANTLR_BUILDER_INCLUDE_STACK_TRACE_ON_INTERNAL_ERRORS));
	}

	public boolean getNfa() {
		return Boolean.valueOf(getOption(AntlrConstants.ANTLR_BUILDER_NFA));
	}

	public boolean getDfa() {
		return Boolean.valueOf(getOption(AntlrConstants.ANTLR_BUILDER_DFA));
	}

	public boolean getReport() {
		return Boolean.valueOf(getOption(AntlrConstants.ANTLR_BUILDER_REPORT));
	}

	public boolean getMarkResourcesAsDerived() {
		return Boolean
				.valueOf(getOption(AntlrConstants.ANTLR_GENERAL_MARK_GENERATED_RESOURCES_AS_DERIVED));
	}

	public boolean getProfile() {
		return Boolean
				.valueOf(getOption(AntlrConstants.ANTLR_CODE_GENERATOR_PROFILE));
	}

	public boolean getXDfaVerbose() {
		return Boolean
				.valueOf(getOption(AntlrConstants.ANTLR_BUILDER_X_DFA_VERBOSE));
	}

	public boolean getXNoPrune() {
		return Boolean
				.valueOf(getOption(AntlrConstants.ANTLR_BUILDER_X_NO_PRUNE));
	}

	public String getXmx() {
		return getOption(AntlrConstants.ANTLR_CODE_GENERATOR_MAX_MEMORY);
	}

	public String getXMaxSwitchCaseLabels() {
		return getOption(AntlrConstants.ANTLR_CODE_GENERATOR_X_MAX_SWITCH_CASE_LABELS);
	}

	public String getXMinSwitchAlts() {
		return getOption(AntlrConstants.ANTLR_CODE_GENERATOR_X_MIN_SWITCH_ALTS);
	}

	public boolean getXNoCollapse() {
		return Boolean
				.valueOf(getOption(AntlrConstants.ANTLR_BUILDER_X_NO_COLLAPSE));
	}

	public boolean getXNoMergeStopStates() {
		return Boolean
				.valueOf(getOption(AntlrConstants.ANTLR_BUILDER_X_NO_MERGE_STOP_STATES));
	}

	public boolean getDebug() {
		return Boolean
				.valueOf(getOption(AntlrConstants.ANTLR_CODE_GENERATOR_DEBUG));
	}

	public boolean getTrace() {
		return Boolean
				.valueOf(getOption(AntlrConstants.ANTLR_CODE_GENERATOR_TRACE));
	}

	public boolean getXdbgSt() {
		return Boolean
				.valueOf(getOption(AntlrConstants.ANTLR_CODE_GENERATOR_X_DBG_ST));
	}

	public boolean getAppendJavaPackage() {
		return Boolean
				.valueOf(getOption(AntlrConstants.ANTLR_CODE_GENERATOR_APPEND_JAVA_PACKAGE_TO_OUTPUT_FOLDER));
	}

	public boolean isOutputFolderRelativeToWorkspace() {
		if (getOption(
				AntlrConstants.ANTLR_SAVE_ACTIONS_GENERATE_RESOURCES_ENABLED)
				.equals("false"))
			return false;
		String outputOption = getOption(AntlrConstants.ANTLR_CODE_GENERATOR_OUTPUT_OPTION);
		if (AntlrConstants.ANTLR_CODE_GENERATOR_OUTPUT_OPTION_ABSOLUTE_FOLDER
				.equals(outputOption))
			return false;
		return true;
	}

	public String getExternalOption(String name) {
		String value = runtimePrefs.get(name);
		if (name.equals("-o")) {
			value = getOutputOption(false);
		} else if (name.equals("-ro")) {
			value = getOutputOption(true);
		}
		return value;
	}

	public void setOption(String name, String value) {
		runtimePrefs.put(name, value);
	}

	public String getOption(String name) {
		String value = null;

		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		IResource resource = root.getFile(fFile);

		try {
			value = resource.getPersistentProperty(new QualifiedName(
					AntlrCore.PLUGIN_ID, name));
		} catch (CoreException ex) {
			ex.printStackTrace();
		}

		if (value == null) {
			value = service.getString(AntlrConstants.ANTLR_CORE, name, null,
					contexts);
		}
		if (value == null) {
			value = getExternalOption(name);
		}
		return value;
	}

	private String getOutputOption(boolean relative) {
		String generateResources = getOption(AntlrConstants.ANTLR_SAVE_ACTIONS_GENERATE_RESOURCES_ENABLED);
		String outputOption = getOption(AntlrConstants.ANTLR_CODE_GENERATOR_OUTPUT_OPTION);
		String outputFolder = getOption(AntlrConstants.ANTLR_CODE_GENERATOR_OUTPUT_FOLDER);
		if ("false".equals(generateResources)) {
			outputFolder = System.getProperty("java.io.tmpdir");
		} else if (outputOption
				.equals(AntlrConstants.ANTLR_CODE_GENERATOR_OUTPUT_OPTION_SAME_AS_GRAMMAR)) {
			if (relative) {
				outputFolder = fFolder.toOSString();
			} else {
				outputFolder = fAbsoluteFolder.toOSString();
			}
		} else if (outputOption
				.equals(AntlrConstants.ANTLR_CODE_GENERATOR_OUTPUT_OPTION_RELATIVE_FOLDER)) {
			String projectName = fFolder.segment(0);
			IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
			IPath path = null;
			if (relative) {
				path = new Path(projectName);
			} else {
				path = root.getProject(projectName).getLocation();
			}
			if (outputFolder != null && outputFolder.length() > 0) {
				String[] paths = outputFolder.replace('\\', '/').split("/");
				for (String p : paths) {
					path = path.append(p);
				}
			}
			outputFolder = path.toOSString();
		}
		return outputFolder;
	}
}
