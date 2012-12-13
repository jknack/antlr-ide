/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.gunit.internal.ui.preferences;

import org.eclipse.osgi.util.NLS;

public class GUnitPreferenceMessages extends NLS {
	/**
	 * Editor. Smart Typing
	 */
	public static String SmartTypingConfigurationBlock_typing;	
	public static String SmartTypingConfigurationBlock_closeBrackets;
	public static String SmartTypingConfigurationBlock_closeBraces;
	public static String SmartTypingConfigurationBlock_closeStrings;
	
	/**
	 * Editor. Syntax Coloring 
	 */
	public static String SyntaxColoringSinglelineComments;
	
	public static String SyntaxColoringMultilineComments;
	
	public static String SyntaxColoringDocComments;
	
	public static String SyntaxColoringKeywords;
	
	public static String SyntaxColoringStrings;
	
	public static String SyntaxColoringTestSuite;
	
	public static String SyntaxColoringMultileStrings;
	
	public static String SyntaxColoringOkKeyword;
	
	public static String SyntaxColoringFailKeyword;
	
	public static String SyntaxColoringHeaderDirective;
	
	public static String SyntaxColoringMLStringOperators;
	
	public static String SyntaxColoringExpectedResultOperator;
	
	public static String SyntaxColoringOthers;
	
	private GUnitPreferenceMessages() {
		// Do not instantiate
	}
	static {
	  String BUNDLE_NAME = GUnitPreferenceMessages.class.getName();//$NON-NLS-1$
		NLS.initializeMessages(BUNDLE_NAME, GUnitPreferenceMessages.class);
	}
}
