/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.integration.jdt;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.deved.antlride.core.AntlrCore;
import org.deved.antlride.core.AntlrLanguageTargetName;
import org.deved.antlride.core.build.AntlrBuildUnit;
import org.deved.antlride.core.integration.AntlrLanguageTargetService;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.ToolFactory;
import org.eclipse.jdt.core.formatter.CodeFormatter;
import org.eclipse.jdt.core.formatter.DefaultCodeFormatterConstants;
import org.eclipse.jdt.launching.IRuntimeClasspathEntry;
import org.eclipse.jdt.launching.JavaRuntime;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.text.edits.MalformedTreeException;
import org.eclipse.text.edits.TextEdit;

public class AntlrJavaTargetService implements AntlrLanguageTargetService {

	private static class Version {
		private String version;
	}

	public Map<String, Object> getClasspathInformation(
			IProgressMonitor monitor, AntlrBuildUnit unit) throws CoreException {

		final IJavaProject javaProject = JavaCore.create(unit.getFile()
				.getProject());

		if (javaProject == null || !javaProject.exists()) {
			return Collections.emptyMap();
		}

		IRuntimeClasspathEntry[] runtimeClassPath = computeDefaultRuntimeClassPath(javaProject);
		Version v = new Version();
		runtimeClassPath = mergeClasspath(v, monitor, unit, runtimeClassPath);

		String[] classpath = getLocationClassPath(runtimeClassPath);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("classpath", classpath);
		map.put("description", v.version);
		return map;
	}

	public static String[] getMementoClassPath(IRuntimeClasspathEntry[] entries)
			throws CoreException {
		String[] classpath = new String[entries.length];

		for (int i = 0; i < entries.length; i++) {
			classpath[i] = entries[i].getMemento();
			System.out.println(classpath[i]);
		}

		return classpath;
	}

	public static String[] getLocationClassPath(IRuntimeClasspathEntry[] entries)
			throws CoreException {
		String[] classpath = new String[entries.length];

		for (int i = 0; i < entries.length; i++) {
			classpath[i] = entries[i].getLocation();
		}

		return classpath;
	}

	public static IRuntimeClasspathEntry[] mergeClasspath(
			IProgressMonitor monitor, AntlrBuildUnit unit,
			IRuntimeClasspathEntry[] runtimeClassPath) throws CoreException {
		return mergeClasspath(new Version(), monitor, unit, runtimeClassPath);
	}

	public static IRuntimeClasspathEntry[] mergeClasspath(Version version,
			IProgressMonitor monitor, AntlrBuildUnit unit,
			IRuntimeClasspathEntry[] runtimeClassPath) throws CoreException {
		monitor.subTask("Checking classpath");
		String[] classpath = getLocationClassPath(runtimeClassPath);
		Collection<URL> urls = new LinkedHashSet<URL>();
		for (int i = 0; i < classpath.length; i++) {
			try {
				URL url = new File(classpath[i]).toURI().toURL();
				urls.add(url);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}
		if (urls.size() > 0) {
			URLClassLoader classLoader = new URLClassLoader(urls
					.toArray(new URL[urls.size()]), null);
			boolean valid = true;
			String[] classes = { "org.antlr.runtime.Token", "org.antlr.Tool",
					"antlr.Tool" };
			String prefix = "ANTLR Parser Generator ";
			for (String classname : classes) {
				try {
					// check the antlr runtime
					Class<?> clazz = classLoader.loadClass(classname);
					if (clazz.getName().equals("org.antlr.Tool")) {
						// try 3.1.3+ or higher
						InputStream in = clazz
								.getResourceAsStream("antlr.properties");
						if (in != null) {
							try {
								Properties prop = new Properties();
								prop.load(in);
								version.version = prefix
										+ prop.getProperty("antlr.version");
							} catch (IOException e) {
								e.printStackTrace();
							}
						} else {
							// 3.1.2 or minor
							try {
								Field vField = clazz
										.getDeclaredField("VERSION");
								version.version = prefix + vField.get(null);
							} catch (Throwable e) {
								e.printStackTrace();
							}
						}
					}
				} catch (ClassNotFoundException e) {
					valid = false;
					break;
				}
			}
			if (!valid) {
				classpath = unit.getPackageClasspath()
						.split(File.pathSeparator);
				Collection<IRuntimeClasspathEntry> entries = new ArrayList<IRuntimeClasspathEntry>();
				for (String jarLocation : classpath) {
					IRuntimeClasspathEntry runtimeEntry = JavaRuntime
							.newArchiveRuntimeClasspathEntry(Path
									.fromOSString(jarLocation));
					runtimeEntry
							.setClasspathProperty(IRuntimeClasspathEntry.USER_CLASSES);
					entries.add(runtimeEntry);
				}
				entries.addAll(Arrays.asList(runtimeClassPath));
				return entries.toArray(new IRuntimeClasspathEntry[entries
						.size()]);
			}
		}
		return runtimeClassPath;
	}

	public static IRuntimeClasspathEntry[] computeDefaultRuntimeClassPath(
			IJavaProject jproject) throws CoreException {
		IRuntimeClasspathEntry[] unresolved = JavaRuntime
				.computeUnresolvedRuntimeClasspath(jproject);
		// 1. remove bootpath entries
		// 2. resolve & translate to local file system paths
		List<IRuntimeClasspathEntry> resolved = new ArrayList<IRuntimeClasspathEntry>(
				unresolved.length);
		for (int i = 0; i < unresolved.length; i++) {
			IRuntimeClasspathEntry entry = unresolved[i];
			if (entry.getClasspathProperty() == IRuntimeClasspathEntry.USER_CLASSES) {
				IRuntimeClasspathEntry[] entries = JavaRuntime
						.resolveRuntimeClasspathEntry(entry, jproject);
				for (int j = 0; j < entries.length; j++) {
					String location = entries[j].getLocation();
					if (location != null) {
						resolved.add(entries[j]);
					}
				}
			}
		}
		return resolved.toArray(new IRuntimeClasspathEntry[resolved.size()]);
	}

	public boolean accept(IResource resource) {
		boolean accept = false;
		try {
			IProject project = resource.getProject();
			IJavaProject javaProject = JavaCore.create(project);
			if (javaProject.exists()) {
				IPath resourcePath = new Path(project.getName())
						.append(resource.getProjectRelativePath());
				IPath outputLocation = javaProject.getOutputLocation();
				if (resourcePath.segmentCount() >= outputLocation
						.segmentCount()) {
					for (int segmentIndex = 0; segmentIndex < outputLocation
							.segmentCount(); segmentIndex++) {
						if (!outputLocation.segment(segmentIndex).equals(
								resourcePath.segment(segmentIndex))) {
							accept = true;
							break;
						}
					}
				}
			} else {
				accept = true;
			}
		} catch (JavaModelException e) {
			e.printStackTrace();
		}
		// if (!accept) {
		// System.out.println("Excluding " + resource);
		// }
		return accept;
	}

	public String format(String source) {
		try {
			@SuppressWarnings("unchecked")
			Map<Object, Object> options = DefaultCodeFormatterConstants
					.getEclipseDefaultSettings();
			// initialize the compiler settings to be able to format 1.5 code
			options.put(JavaCore.COMPILER_COMPLIANCE, JavaCore.VERSION_1_5);
			options.put(JavaCore.COMPILER_CODEGEN_TARGET_PLATFORM,
					JavaCore.VERSION_1_5);
			options.put(JavaCore.COMPILER_SOURCE, JavaCore.VERSION_1_5);

			final CodeFormatter codeFormatter = ToolFactory
					.createCodeFormatter(options);
			final int[] K = { CodeFormatter.K_STATEMENTS,
					CodeFormatter.K_CLASS_BODY_DECLARATIONS,
					CodeFormatter.K_EXPRESSION};
			TextEdit edit = null;

			for (int i = 0; i < K.length; i++) {
				edit = codeFormatter.format(K[i], source,
						0, source.length(), 0, System
								.getProperty("line.separator"));
				if (edit != null) {
					// found
					break;
				}
			}
			if (edit != null) {
				IDocument document = new Document(source);
				edit.apply(document);
				return document.get();
			}
		} catch (MalformedTreeException ex) {
			AntlrCore.error(ex);
		} catch (BadLocationException ex) {
			AntlrCore.error(ex);
		}
		return source;
	}

	public AntlrLanguageTargetName getName() {
		return AntlrLanguageTargetName.Java;
	}
}
