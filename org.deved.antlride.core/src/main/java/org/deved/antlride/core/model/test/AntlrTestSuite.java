/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.core.model.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.deved.antlride.core.model.IGrammar;
import org.deved.antlride.core.util.AntlrCoreHelper;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;

public class AntlrTestSuite {

	private IPath path;

	//private IGrammar grammar;

	private Map<String, AntlrTestCase[]> testcaseMap;

	public AntlrTestSuite(IGrammar grammar, IPath path) {
		this.path = path;
		//this.grammar = grammar;
		testcaseMap = new HashMap<String, AntlrTestCase[]>();
	}

	public AntlrTestCase create(String rule, String name, String input) {
		IPath testpath = path.append(rule).append(name);
		AntlrTestCase testcase = new AntlrTestCase(testpath, input);
		return testcase;
	}

	public void save(AntlrTestCase testCase, String content) throws IOException {
		testCase.save(content);
		clear(testCase.getRule());
	}

	public void delete(AntlrTestCase testCase, String content)
			throws IOException {
		File file = testCase.getPath().toFile();
		if (file.exists()) {
			file.delete();
		}
		clear(testCase.getRule());
	}

	private void clear(String rule) {
		AntlrTestCase[] testcases = testcaseMap.remove(rule);
		if (testcases != null) {
			for (int i = 0; i < testcases.length; i++) {
				testcases[i] = null;
			}
		}
	}

	public boolean exists(String rule, String name) {
		try {
			AntlrTestCase[] list = list(rule);
			for (AntlrTestCase testCase : list) {
				if (testCase.getName().equals(name)) {
					return true;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public AntlrTestCase[] list(String rule) throws IOException {
		AntlrTestCase[] result = testcaseMap.get(rule);
		if (result != null) {
			return result;
		}
		Collection<AntlrTestCase> list = internalList(rule);
		result = list.toArray(new AntlrTestCase[list.size()]);
		list.clear();
		testcaseMap.put(rule, result);
		return result;
	}

	private Collection<AntlrTestCase> internalList(String rule)
			throws IOException {
		Collection<AntlrTestCase> list = new ArrayList<AntlrTestCase>();
		File home = path.append(rule).toFile();
		File[] files = home.listFiles();
		if (files != null) {
			for (File file : files) {
				String input = AntlrCoreHelper
						.loadFileAsString(new FileInputStream(file));
				AntlrTestCase test = new AntlrTestCase(Path.fromOSString(file
						.getAbsolutePath()), input);
				list.add(test);
			}
		}
		return list;
	}

	public void destroy() {
//		this.grammar = null;
		this.testcaseMap.clear();
		path = null;
	}
}
