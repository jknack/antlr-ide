/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.core.resources;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.deved.antlride.core.AntlrCore;
import org.deved.antlride.core.AntlrLanguageTargetName;
import org.eclipse.core.runtime.IPath;

public class AntlrLanguageTargetRepository {

	private static Properties defaults = null;

	private static final String[] NO_KEYWORDS = new String[0];

	private static final IPath LANG_HOME = AntlrCore.getDefault()
			.getStateLocation().append("languages");

	private static Map<String, AntlrLanguageTarget> languages = new HashMap<String, AntlrLanguageTarget>();

	public static final String STRING_TEMPLATE_PATH = "org/antlr/codegen/templates";

	public static AntlrLanguageTarget getLanguage(String name) {
		return getLanguage(null, name);
	}

	public static boolean matchKeyword(String language, String keyword) {
		AntlrLanguageTarget languageTarget = getLanguage(language);
		if (languageTarget == null)
			return false;
		String[] keywords = languageTarget.getKeywords();
		if (keywords == null)
			return false;
		for (String k : keywords) {
			if (k.trim().equals(keyword.trim())) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean likeLanguage(AntlrLanguageTargetName target, String language) {
		String l1 = target.name().trim();
		String l2 = String.valueOf(language).trim();
		if(l1.equalsIgnoreCase(l2))
			return true;
		//check for derived language
		AntlrLanguageTarget[] list = list();
		for (AntlrLanguageTarget targets : list) {
			if(l1.equals(targets.getParent())) {
				return true;
			}
		}
		return false;
	}

	public static AntlrLanguageTarget create(AntlrLanguageTarget parent,
			String name, String path) {
		AntlrLanguageTarget target = new AntlrLanguageTarget(parent.getName(),
				name, parent.getKeywords(), path);
		return target;
	}

	public static boolean exists(String language) {
		return getLanguageTargetPath(language).toFile().exists();
	}

	private static IPath getLanguageTargetPath(String language) {
		return LANG_HOME.append(language).addFileExtension("properties");
	}

	public static AntlrLanguageTarget[] list() {
		List<AntlrLanguageTarget> langs = new ArrayList<AntlrLanguageTarget>();
		File home = LANG_HOME.toFile();
		if (home.exists()) {
			File[] files = home.listFiles(new FilenameFilter() {

				public boolean accept(File dir, String name) {
					return name.endsWith(".properties");
				}
			});
			if (files != null) {
				for (File file : files) {
					try {
						Properties properties = new Properties();
						FileInputStream in = new FileInputStream(file);
						properties.loadFromXML(in);
						langs.add(new AntlrLanguageTarget(properties));
						in.close();
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (InvalidPropertiesFormatException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return langs.toArray(new AntlrLanguageTarget[langs.size()]);
	}

	public static void save(AntlrLanguageTarget target) {
		try {
			IPath path = getLanguageTargetPath(target.getName());
			File file = path.toFile();
			file.getParentFile().mkdirs();
			BufferedOutputStream out = new BufferedOutputStream(
					new FileOutputStream(file));
			Properties properties = target.toProperties();
			properties.storeToXML(out, target.getName());
			out.close();
		} catch (FileNotFoundException e) {
			AntlrCore.error(e);
		} catch (IOException e) {
			AntlrCore.error(e);
		}
	}

	public static AntlrLanguageTarget getLanguage(AntlrLanguageTarget parent,
			String name) {
		AntlrLanguageTarget targetLanguage = languages.get(name);
		if (targetLanguage == null) {
			AntlrLanguageTargetName includedTarget = AntlrLanguageTargetName.find(name);
			if (includedTarget == null) {
				AntlrLanguageTarget[] list = list();
				for (AntlrLanguageTarget onelt : list) {
					if (onelt.getName().equalsIgnoreCase(name)) {
						targetLanguage = onelt;
					}
				}
			} else {
				Properties properties = getKeywords();
				String allkeys = properties.getProperty(name + "_Keywords");
				String[] keywords = NO_KEYWORDS;
				if (allkeys != null) {
					keywords = allkeys.split(",");
				}
				targetLanguage = new AntlrLanguageTarget(null, name, keywords,
						null);
			}
			languages.put(name, targetLanguage);
		}
		return targetLanguage;
	}

	private static Properties getKeywords() {
		if (defaults == null) {
			InputStream in = AntlrLanguageTargetRepository.class
					.getResourceAsStream("keywords.properties");
			defaults = load(in);
		}
		return defaults;
	}

	private static Properties load(InputStream in) {
		in = new BufferedInputStream(in);
		Properties properties = new Properties();
		try {
			properties.load(in);
			in.close();
		} catch (IOException e) {
			AntlrCore.error(e);
		}
		return properties;
	}

	public static void delete(AntlrLanguageTarget languageTarget) {
		File file = getLanguageTargetPath(languageTarget.getName()).toFile();
		file.delete();
	}
}
