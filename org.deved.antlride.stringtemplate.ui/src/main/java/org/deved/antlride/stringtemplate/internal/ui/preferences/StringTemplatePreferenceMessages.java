/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.stringtemplate.internal.ui.preferences;

import org.eclipse.osgi.util.NLS;

public class StringTemplatePreferenceMessages extends NLS {
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
	
	public static String SyntaxColoringComments;
	
	public static String SyntaxColoringKeywords;
	
	public static String SyntaxColoringTemplateInGroup;
	
	public static String SyntaxColoringTemplateRegion;
	
	public static String SyntaxColoringAssignTemplateOperator;
	
	public static String SyntaxColoringTemplateDelimeters;
	
	public static String SyntaxColoringStrings;
	
	public static String SyntaxColoringEspecialCharacters;
	
	public static String SyntaxColoringTemplateGroupDelimeters;
	
	public static String SyntaxColoringOthers;
	
	private StringTemplatePreferenceMessages() {
		// Do not instantiate
	}
	static {
	  String BUNDLE_NAME = StringTemplatePreferenceMessages.class.getName();//$NON-NLS-1$
		NLS.initializeMessages(BUNDLE_NAME, StringTemplatePreferenceMessages.class);
	}
}
