/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.core.resources;

import java.util.Properties;

public class AntlrLanguageTarget {
	private String name;

	private String[] keywords;

	private String keywordsAsString;

	private String parent;

	private String[] packages;

	private String path;

	private static final String[] NO_KEYWORDS = new String[0];

	AntlrLanguageTarget(String parent, String name, String[] keywords,
			String path) {
		this.name = name;
		this.parent = parent;
		this.keywords = keywords == null ? NO_KEYWORDS : keywords;

		StringBuilder builder = new StringBuilder();
		for (String keyword : this.keywords) {
			builder.append(keyword);
			builder.append(",");
		}
		if (builder.length() > 0) {
			builder.setLength(builder.length() - ",".length());
		}
		keywordsAsString = builder.toString();
		this.path = path;
	}
	
	public String getParent() {
		return parent;
	}

	AntlrLanguageTarget(Properties properties) {
		this.parent = properties.getProperty("parent");;
		this.name = properties.getProperty("name");
		this.path = properties.getProperty("path");
		this.keywordsAsString = properties.getProperty("keywords");
		this.keywords = keywordsAsString.split(",");
		this.packages = properties.getProperty("packages").split(",");
	}

	public Properties toProperties() {
		Properties properties = new Properties();
		properties.setProperty("parent", parent);
		properties.setProperty("name", name);
		properties.setProperty("keywords", getKeywordsAsString());
		properties.setProperty("packages", getPackagesAsString());
		properties.setProperty("path", path);
		return properties;
	}

	public String getName() {
		return name;
	}

	public String getPackagesAsString() {
		StringBuilder builder = new StringBuilder();
		if (this.packages != null) {
			for (String keyword : this.packages) {
				builder.append(keyword);
				builder.append(", ");
			}
			if (builder.length() > 0) {
				builder.setLength(builder.length() - ", ".length());
			}
		}
		return builder.toString();
	}

	public String[] getKeywords() {
		return keywords;
	}

	public String getKeywordsAsString() {
		return keywordsAsString;
	}

	public String getPath() {
		return path;
	}

	@Override
	public String toString() {
		return getName();
	}
}
