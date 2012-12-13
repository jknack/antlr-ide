/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.internal.ui.preferences;

import org.eclipse.osgi.util.NLS;

public class AntlrPreferenceMessages extends NLS {
	private AntlrPreferenceMessages() {
		// Do not instantiate
	}

	static {
		String BUNDLE_NAME = AntlrPreferenceMessages.class.getName();//$NON-NLS-1$
		NLS.initializeMessages(BUNDLE_NAME, AntlrPreferenceMessages.class);
	}

	public static String CodeGenerator_x_max_swith_case_labels;

	public static String CodeGenerator_x_min_swith_alts;
	
	public static String SaveActions_text;
	
	public static String SaveActions_generate_resources;
	
	public static String SaveActions_format_code;

	public static String ANTLREditorPreferencePage_general;

	public static String ANTLREditorContentAssistPreferencePage;
	public static String ANTLREditorContentAssistPreferencePage_autoActivationTitle;
	public static String ANTLREditorContentAssistPreferencePage_autoActivation;
	public static String ANTLREditorContentAssistPreferencePage_autoActivationDelay;
	public static String ANTLREditorContentAssistPreferencePage_autoActivationTriggers;
	public static String ANTLREditorContentAssistPreferencePage_insertionTitle;
	public static String ANTLREditorContentAssistPreferencePage_insertionCompletionInserts;
	public static String ANTLREditorContentAssistPreferencePage_insertionCompletionOverwrites;
	public static String ANTLREditorContentAssistPreferencePage_insertionDescription;
	public static String ANTLREditorContentAssistPreferencePage_sortTitle;
	public static String ANTLREditorContentAssistPreferencePage_sortProposals;
	public static String ANTLREditorContentAssistPreferencePage_sortProposalsByRelevance;
	public static String ANTLREditorContentAssistPreferencePage_sortProposalsAlphabetically;
	public static String ANTLREditorContentAssistPreferencePage_sortShowVisibleProposals;

	public static String General_description;
	public static String General_mark_generated_resources_as_derived;

	public static String Builder_extended_options;
	public static String Builder_options;

	public static String Builder_max_number_of_problems_reported_per_grammar;
	public static String Builder_include_stack_trace_on_internal_errors;
	public static String Builder_Xnumber_error;
	public static String Builder_Runtime;
	public static String Builder_Packages;
	public static String Builder_Package_Add;
	public static String Builder_Package_Version;
	public static String Builder_Package_Remove;
	public static String Builder_Package_Title;
	public static String Builder_Package_Directory;
	public static String Builder_Package_Add_Ext_JARS;
	public static String Builder_Package_Home;
	public static String Builder_Package_Libs;
	public static String Builder_Package_Desc;
	public static String Builder_Package_Invalid_Package;
	public static String Builder_Package_Invalid_Antlr_Home;
	public static String Builder_Package_Duplicated_Package;
	public static String Builder_report;
	public static String Builder_nfa;
	public static String Builder_dfa;
	public static String Builder_Xdfa;
	public static String Builder_Xnoprune;
	public static String Builder_Xnocollapse;
	public static String Builder_Xdbgconversion;
	public static String Builder_Xnomergestopstates;
	public static String Builder_Xdfaverbose;
	public static String Builder_Xwatchconversion;
	public static String Builder_Xm;
	public static String Builder_Xmaxdfaedges;
	public static String Builder_Xconversiontimeout;

	public static String CodeGenerator_o_option_folder;
	public static String CodeGenerator_o_option_folder_same_as_grammar;
	public static String CodeGenerator_o_option_folder_for_java;
	public static String CodeGenerator_java_options;
	public static String CodeGenerator_o_option_folder_description;
	public static String CodeGenerator_o_option_folder_name;
	public static String CodeGenerator_o_option_folder_relative_folder_name;
	public static String CodeGenerator_o_option_folder_absolute_folder_name;
	public static String CodeGenerator_o_option_folder_error_empty;
	public static String CodeGenerator_o_option_folder_error_invalid;
	public static String CodeGenerator_XdbgST;
	public static String CodeGenerator_Xnoinlinedfa;
	public static String CodeGenerator_options;
	public static String CodeGenerator_profile;
	public static String CodeGenerator_debug;
	public static String CodeGenerator_trace;
	public static String CodeGenerator_max_memory;

	public static String ANTLREditorColoringConfigurationBlock_rules;
	public static String ANTLREditorColoringConfigurationBlock_lexer_rules;
	public static String ANTLREditorColoringConfigurationBlock_local_variable_references;
	public static String ANTLREditorColoringConfigurationBlock_local_variable_declarations;
	public static String ANTLREditorColoringConfigurationBlock_single_line_comment;
	public static String ANTLREditorColoringConfigurationBlock_multi_line_comment;
	public static String ANTLREditorColoringConfigurationBlock_doc_comment;
	public static String ANTLREditorColoringConfigurationBlock_keywords;
	public static String ANTLREditorColoringConfigurationBlock_grammar_actions;
	public static String ANTLREditorColoringConfigurationBlock_strings;
	public static String ANTLREditorColoringConfigurationBlock_grammar_name;
	public static String ANTLREditorColoringConfigurationBlock_invalid_grammar_actions;
	public static String ANTLREditorColoringConfigurationBlock_keyword_returns;
	public static String ANTLREditorColoringConfigurationBlock_target_language_keywords;
	public static String ANTLREditorColoringConfigurationBlock_target_language_comments;
	public static String ANTLREditorColoringConfigurationBlock_target_language_literals;
	public static String ANTLREditorColoringConfigurationBlock_ebnf_operators;
	public static String ANTLREditorColoringConfigurationBlock_tree_operators;
	public static String ANTLREditorColoringConfigurationBlock_rewrite_operator;
	public static String ANTLREditorColoringConfigurationBlock_others;

	public static String ANTLRSmartTypingConfigurationBlock_closeBrackets;
	public static String ANTLRSmartTypingConfigurationBlock_closeBraces;
	public static String ANTLRSmartTypingConfigurationBlock_closeStrings;
	public static String ANTLRSmartTypingConfigurationBlock_typing;
}
