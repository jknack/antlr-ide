/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.internal.ui.views.interpreter;

import org.eclipse.osgi.util.NLS;

public class AntlrInterpreterMessages extends NLS {
	
	public static String GrammarInterpreter_Run;
	
	public static String GrammarInterpreter_Zoom_In;
	
	public static String GrammarInterpreter_Zoom_Out;
	
	public static String GrammarInterpreter_Cut;
	
	public static String GrammarInterpreter_Copy;
	
	public static String GrammarInterpreter_Paste;
		
	public static String GrammarInterpreter_Select_All;
	
	public static String GrammarInterpreter_Clear_Diagram;
	
	public static String GrammarInterpreter_Link_Editor;
	
	public static String GrammarInterpreter_SaveAs_Testcase;
	
	public static String GrammarInterpreter_Save_Testcase;
	
	public static String GrammarInterpreter_Remove_Testcase;
	
	public static String GrammarInterpreter_Search_Testcase;
	
	public static String GrammarInterpreter_Search_Rule;
	
	public static String GrammarInterpreter_New_Testcase;
	
	public static String GrammarInterpreter_Load_Testcase;
	
	public static String GrammarInterpreter_Grammar_with_Predicates;
	
	
	private AntlrInterpreterMessages() {
		// Do not instantiate
	}
	static {
	  String BUNDLE_NAME = AntlrInterpreterMessages.class.getName();
		NLS.initializeMessages(BUNDLE_NAME, AntlrInterpreterMessages.class);
	}
}
