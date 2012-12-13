/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.core.resources;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.deved.antlride.core.AntlrCore;
import org.deved.antlride.core.AntlrLanguageTargetName;

public class AntlrPackage implements Comparable<AntlrPackage> {

	public static final String[] PROPERTIES = { "Version", "Description" };

	private String version;

	private String classpath;

	private String description;

	private long created;

	private boolean selected;

	private String home;

	private Set<String> classpathSet;

	private URLClassLoader classLoader;

	private AntlrLanguageTarget[] targets = null;

	AntlrPackage(String home, String description, String version,
			String classpath, long created, boolean selected) {
		this.home = home;
		this.version = version;
		this.description = description;
		this.classpath = classpath;
		this.created = created;
		this.selected = selected;
	}

	AntlrPackage(String home, String description, String version,
			String classpath) {
		this(home, description, version, classpath, System.currentTimeMillis(),
				false);
	}

	public ClassLoader getClassLoader() {
		if (classLoader == null) {
			Set<String> classpath = getClasspath();
			URL[] urls = new URL[classpath.size()];
			int i = 0;
			for (String cpentry : classpath) {
				File jarFile = new File(cpentry);
				try {
					urls[i++] = jarFile.toURI().toURL();
				} catch (MalformedURLException e) {
					AntlrCore.error(e);
				}
			}
			classLoader = new URLClassLoader(urls);
		}
		return classLoader;
	}

	public AntlrLanguageTarget[] getLanguageTargets() {
		if (targets == null) {
			String[] allTargets = AntlrLanguageTargetName.names();
			List<AntlrLanguageTarget> list = new ArrayList<AntlrLanguageTarget>(
					allTargets.length);
			ClassLoader classLoader = getClassLoader();
			for (String target : allTargets) {
				String templateName = new StringBuilder().append(
						AntlrLanguageTargetRepository.STRING_TEMPLATE_PATH)
						.append("/").append(target).append("/").append(target)
						.append(".stg").toString();
				if (classLoader.getResource(templateName) != null) {
					list.add(AntlrLanguageTargetRepository.getLanguage(target));
				}
			}
			targets = list.toArray(new AntlrLanguageTarget[list.size()]);
		}
		return targets;
	}

	public boolean isSelected() {
		return selected;
	}

	public long getCreated() {
		return created;
	}

	public String getDescription() {
		return description;
	}

	public String getShortDescription() {
		return "ANTLR " + getVersion();
	}

	public String getVersion() {
		return version;
	}

	public String getClasspathAsString() {
		return classpath;
	}

	public Set<String> getClasspath() {
		if (classpathSet == null) {
			classpathSet = new HashSet<String>(Arrays.asList(classpath
					.split(File.pathSeparator)));
		}
		return classpathSet;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public void addExternalJARs(String jarPath) {
		if (getClasspath().add(jarPath)) {
			classpath = jarPath + File.pathSeparator + classpath;
		}
	}

	@Override
	public String toString() {
		return description;
	}

	public int compareTo(AntlrPackage o) {
		return (int) (created - o.created);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof AntlrPackage) {
			AntlrPackage that = (AntlrPackage) obj;
			return version.equals(that.version);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return version.hashCode();
	}

	public String getHome() {
		return home;
	}
}
