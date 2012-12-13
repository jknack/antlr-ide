/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum AntlrLanguageTargetName {
	ActionScript,
	C,
	CPP,
	CSharp,
	CSharp2,
	CSharp3,
	Java,
	Delphi,
	JavaScript,
	ObjC,
	Perl5,
	Ruby,
	Python;
	
	private static String[] NAMES;
	
	public static List<String> nameList() {
		List<String> list = new ArrayList<String>(Arrays.asList(names()));
		return list;
	}
	
	public static AntlrLanguageTargetName find(String name) {
		AntlrLanguageTargetName[] values = values();
		for (AntlrLanguageTargetName t : values) {
			if(t.name().equalsIgnoreCase(name)) {
				return t;
			}
		}
		return null;
	}
	
	public static String[] names() {
		if(NAMES == null) {
			NAMES = new String[values().length];
			AntlrLanguageTargetName[] values = values();
			for (int i = 0; i < values.length; i++) {
				NAMES[i] = values[i].name();
			}
		}
		return NAMES;
	}
}
