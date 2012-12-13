/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.gunit.core;

public interface GUnitConstants {

	String NAMESPACE = "org.deved.antlride.gunit";
	
	String GUNIT = "gunit";
	
	String TESTSUITE = "testsuite";
	
	String[] FILE_EXTENSIONS = {TESTSUITE, GUNIT};

	String UI_NAMESPACE = NAMESPACE + ".ui";

	String CORE_NAMESPACE = NAMESPACE + ".core";

	String NATURE_ID = NAMESPACE + ".nature";

	String LANGUAGE_NAME = "GUnit";

	String LANGUAGE_CONTENT_TYPE = NAMESPACE + ".contentType";

	String EDITOR_ID = UI_NAMESPACE + ".editor";

	String EDITOR_CONTEXT = UI_NAMESPACE + ".editorContext";

	String EDITOR_CONTEXT_MENU = "#" + UI_NAMESPACE + ".editorContextMenu";

	String KEYWORDS[] = { "gunit", "walks", "header", "returns"};
}
