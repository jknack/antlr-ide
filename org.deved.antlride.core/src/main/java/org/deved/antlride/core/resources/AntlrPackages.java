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
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.deved.antlride.core.AntlrCore;
import org.deved.antlride.core.env.JavaEnvironment;
import org.deved.antlride.core.env.JavaEnvironmentRepository;
import org.deved.antlride.core.env.JavaEnvironmentRepositoryLookup;
import org.eclipse.core.runtime.IPath;

public class AntlrPackages {

	private static final String ANTLR_AVAILABLE_PKG = "antlr.packages";

	public static final String[] PROPERTIES = { "Version", "Description" };

	private static final Pattern VERSION = Pattern.compile("\\d+(\\.\\w+)+");

	private static final String PACKAGES_FILE = "antlr-packages.xml";

	private static final String ANTLR_DESC = "antlr.%s.desc";

	private static final String ANTLR_CP = "antlr.%s.cp";

	private static final String ANTLR_CREATED = "antlr.%s.created";

	private static final String ANTLR_SELECTED = "antlr.%s.selected";

	private static final String ANTLR_HOME = "antlr.%s.home";

	private static ArrayList<AntlrPackage> _packageList;

	private List<AntlrPackage> packageList;

	private AntlrPackages(List<AntlrPackage> packageList) {
		this.packageList = new ArrayList<AntlrPackage>(packageList);
	}

	public static AntlrPackages getInstance() {
		// force a reload from disk
		_packageList = null;
		return new AntlrPackages(getCachedPackageList());
	}

	public boolean exist(AntlrPackage antlrPackage) {
		return packageList.contains(antlrPackage);
	}

	public boolean register(AntlrPackage antlrPackage) {
		if (!packageList.contains(antlrPackage)) {
			packageList.add(antlrPackage);
			if (packageList.size() == 1) {
				antlrPackage.setSelected(true);
			}
			return true;
		}
		return false;
	}

	public void unregister(AntlrPackage antlrPackage) {
		packageList.remove(antlrPackage);
	}

	public void save() throws IOException {
		getCachedPackageList().clear();
		getCachedPackageList().addAll(packageList);
		savePackages();
	}

	public AntlrPackage[] getPackages() {
		return packageList.toArray(new AntlrPackage[packageList.size()]);
	}

	public static AntlrPackage[] getAvailablePackages() {
		List<AntlrPackage> list = getCachedPackageList();
		return list.toArray(new AntlrPackage[list.size()]);
	}

	public static AntlrPackage getPackage(String pckname) {
		List<AntlrPackage> list = getCachedPackageList();
		for (AntlrPackage ap : list) {
			if (ap.getVersion().equals(pckname)) {
				return ap;
			}
		}
		return null;
	}

	public AntlrPackage getSelectedPackage() {
		for (AntlrPackage antlrPackage : packageList) {
			if (antlrPackage.isSelected()) {
				return antlrPackage;
			}
		}
		return null;
	}

	public int getSelectedPackageIndex() {
		return packageList.indexOf(getSelectedPackage());
	}

	public static String[] getAvailablePackageNames() {
		List<AntlrPackage> list = getCachedPackageList();
		String[] names = new String[list.size()];
		for (int i = 0; i < names.length; i++) {
			names[i] = list.get(i).getVersion();
		}
		return names;
	}

	private static void savePackages() throws IOException {
		Properties properties = new Properties();
		StringBuilder versions = new StringBuilder();
		String[] packageNames = getAvailablePackageNames();
		if (packageNames.length > 0) {
			for (String pname : packageNames) {
				versions.append(pname);
				versions.append(",");
			}
			versions.delete(versions.length() - 1, versions.length());
			properties.setProperty(ANTLR_AVAILABLE_PKG, versions.toString());
			AntlrPackage[] packages = getAvailablePackages();
			for (AntlrPackage ap : packages) {
				properties.put(String.format(ANTLR_CP, ap.getVersion()), ap
						.getClasspathAsString());
				properties.put(String.format(ANTLR_HOME, ap.getVersion()), ap
						.getHome());
				properties.put(String.format(ANTLR_DESC, ap.getVersion()), ap
						.getDescription());
				properties.put(String.format(ANTLR_CREATED, ap.getVersion()),
						new Long(ap.getCreated()).toString());
				properties.put(String.format(ANTLR_SELECTED, ap.getVersion()),
						new Boolean(ap.isSelected()).toString());
			}
		}
		OutputStream out = new BufferedOutputStream(new FileOutputStream(
				getPackagesFile()));
		properties.storeToXML(out, "ANTLR Packages");
		out.flush();
		out.close();
	}

	/**
	 * Search for jar files at the ANTLR HOME PATH. The algorithm append the
	 * 'lib' path to the ANTLR HOME PATH and search for any *.jar files located
	 * in the 'lib' directory.
	 * 
	 * @param path
	 *            The ANTLR HOME PATH
	 * @return The files found under the lib directory
	 * @throws FileNotFoundException
	 */
	private static File[] getJarFiles(IPath path) {
		IPath libPath = path.append("lib");
		File rootDir = libPath.toFile();
		if (!rootDir.exists()) {
			// ANTLR 3.2+ version
			rootDir = path.toFile();
		}
		File[] jarFiles = rootDir.listFiles(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return !name.startsWith(".") && name.endsWith(".jar");
			}
		});

		return jarFiles;
	}

	/**
	 * Create an ANTLR package from the path. The algorithm search for jar files
	 * under the given path then it try to launch the org.antlr.Tool class and
	 * ask for the version, if the process respond OK the package will be
	 * created
	 * 
	 * @param path
	 *            The ANTLR HOME PATH
	 * @return A new ANTLR package
	 * @throws InvalidPackageException
	 */
	public static AntlrPackage createPackage(IPath path)
			throws InvalidPackageException {
		Process process = null;
		BufferedReader in = null;
		StringBuilder err = new StringBuilder();
		try {
			// scan the antlr home directory
			File[] jarFiles = getJarFiles(path);

			// build the classpath with all the files found
			StringBuilder classpath = new StringBuilder();
			if (jarFiles != null && jarFiles.length > 0) {
				for (int i = 0; i < jarFiles.length; i++) {
					File jarFile = jarFiles[i];
					classpath.append(jarFile.getAbsolutePath());
					classpath.append(File.pathSeparator);
				}
				// remove the last separator
				classpath.setLength(classpath.length()
						- File.pathSeparator.length());
			}
			AntlrPackage antlrPackage = null;
			if (classpath.length() > 0) {
				try {
					// start a jvm and ask to org.antlr.Tool for version
					JavaEnvironmentRepository javaEnvironmentRepository = JavaEnvironmentRepositoryLookup
							.lookup();
					JavaEnvironment environment = javaEnvironmentRepository
							.getEnvironment();
					String[] cmd = { environment.getJavaPath().toOSString(),
							"-classpath", classpath.toString(),
							"org.antlr.Tool", "-version", "-verbose" };
					for (String c : cmd) {
						err.append(c).append(" ");
					}
					err.append("\n");
					ProcessBuilder pb = new ProcessBuilder(cmd);
					process = pb.start();
					int status = process.waitFor();
					in = new BufferedReader(new InputStreamReader(process
							.getErrorStream()));
					String line = in.readLine();
					if (status == 0) {
						if (line != null) {
							Matcher matcher = VERSION.matcher(line);
							String version = null;
							while (matcher.find()) {
								version = matcher.group();
							}
							// create only if the version can be taken from
							// ANTLR
							if (version != null) {
								antlrPackage = new AntlrPackage(path
										.toOSString(), line, version, classpath
										.toString());
							}
						}
					} else {
						// error
						while (line != null) {
							err.append(line).append("\n");
							line = in.readLine();
						}
					}
				} catch (Exception ex) {
					throw new InvalidPackageException(ex);
				}
			}
			if (antlrPackage == null) {
				throw new InvalidPackageException(err.toString().trim());
			}
			return antlrPackage;
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException ex) {
				}
			}
			if (process != null) {
				try {
					process.destroy();
				} catch (Exception ex) {
				}
			}
		}
	}

	private static List<AntlrPackage> getCachedPackageList() {
		if (_packageList == null) {
			_packageList = new ArrayList<AntlrPackage>();
			File file = getPackagesFile();
			if (file.exists()) {
				try {
					InputStream in = new BufferedInputStream(
							new FileInputStream(file));
					Properties properties = new Properties();
					properties.loadFromXML(in);
					String availablePkgs = properties
							.getProperty(ANTLR_AVAILABLE_PKG);
					if (availablePkgs != null) {
						String[] packages = availablePkgs.split(",");
						for (String packageName : packages) {
							String home = properties.getProperty(String.format(
									ANTLR_HOME, packageName));
							String desc = properties.getProperty(String.format(
									ANTLR_DESC, packageName));
							String cp = properties.getProperty(String.format(
									ANTLR_CP, packageName));
							long created = Long.parseLong(properties
									.getProperty(String.format(ANTLR_CREATED,
											packageName), Long.MAX_VALUE + ""));
							boolean selected = Boolean.valueOf(properties
									.getProperty(String.format(ANTLR_SELECTED,
											packageName), Boolean.FALSE
											.toString()));
							_packageList.add(new AntlrPackage(home, desc,
									packageName, cp, created, selected));
						}
						in.close();
						Collections.sort(_packageList);
					}
				} catch (IOException e) {
					AntlrCore.error(e);
				}
			}
		}
		return _packageList;
	}

	private static File getPackagesFile() {
		IPath root = AntlrCore.getDefault().getStateLocation();
		return new File(root.toFile(), PACKAGES_FILE);
	}
}
