/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.internal.ui.wizards;

import org.eclipse.osgi.util.NLS;

public class AntlrWizardMessages extends NLS {

  private static final String BUNDLE_NAME =
      AntlrWizardMessages.class.getPackage().getName() + ".messages";

  public static String NewGrammarFilePage_Language;

  public static String NewGrammarFilePage_Grammar31;
  
  public static String NewGrammarFilePage_Grammar31_Prototype;
  
  public static String NewGrammarFilePage_TemplateOutput;

  public static String NewGrammarFilePage_ASTOutput;

  public static String NewGrammarFilePage_NoneOutput;

  public static String NewGrammarFilePage_Output;

  public static String NewGrammarFilePage_Options;

  public static String NewGrammarFilePage_TreeGrammarType;

  public static String NewGrammarFilePage_ParserGrammarType;

  public static String NewGrammarFileWizard_title;
  
  public static String NewGrammarFilePage_Error_EmptyGrammarName;
  
  public static String NewGrammarFilePage_Error_GrammarAlreadyExist;
  
  public static String NewGrammarFilePage_Error_InvalidGrammarName;

  public static String NewGrammarFilePage_title;

  public static String NewGrammarFilePage_description;

  public static String NewGrammarFilePage_JavaLanguage;

  public static String NewGrammarFilePage_CLanguage;
  
  public static String NewGrammarFilePage_CSharpLanguage;
  
  public static String NewGrammarFilePage_ObjCLanguage;
  
  public static String NewGrammarFilePage_RubyLanguage;
  
  public static String NewGrammarFilePage_PythonLanguage;

  public static String NewGrammarFilePage_GrammarType;

  public static String NewGrammarFilePage_CombinedGrammarType;

  public static String NewGrammarFilePage_LexerGrammarType;

  static {
    initializeMessages(BUNDLE_NAME, AntlrWizardMessages.class);
  }
}
