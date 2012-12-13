/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.core;

import java.util.regex.Pattern;

import org.eclipse.core.runtime.QualifiedName;

public interface AntlrConstants {

	String ANTLR_LIB = "___antlr_dependencies__";

	String ANTLR_GRAMMAR_DECLARATION = "__antlr_grammar_declaration";

	String ANTLR_OPTIONS = "__antlr_options";

	String ANTLR_IMPORT = "__antlr_import";

	String ANTLR_TOKENS = "__antlr_tokens";

	String ANTLR_SCOPE = "__antlr_scope";

	String ANTLR_GRAMMAR_ACTION = "__antlr_grammar_action";

	String ANTLR_SINGLE_LINE_COMMENT = "__antlr_single_line_comment";

	String ANTLR_MULTI_LINE_COMMENT = "__antlr_multi_line_comment";

	String ANTLR_STRING = "__antlr_string_literal";

	String ANTLR_TARGET_ACTION = "__antlr_target_action";

	String ANTLR_RULE_ACTION = "__antlr_rule_action";

	String ANTLR_BRACKET = "__antlr_bracket";

	String ANTLR_CORE = "org.deved.antlride.core";

	String ANTLR_RESOURCE_OWNER = "antlr_core_resource_owner";

	String ANTLR_PROTOTYPE_GRAMMAR = "antlr_core_prototype_grammar";

	String ANTLR_GRAMMAR_FILE_EXTENSION = "g";

	String ANTLR_GRAMMAR_TYPE = "gtype";

	// qualified properties

	QualifiedName Q_ANTLR_RESOURCE_OWNER = new QualifiedName(ANTLR_CORE,
			ANTLR_RESOURCE_OWNER);

	QualifiedName Q_ANTLR_PROTOTYPE_GRAMMAR = new QualifiedName(ANTLR_CORE,
			ANTLR_PROTOTYPE_GRAMMAR);

	QualifiedName Q_ANTLR_GRAMMAR_TYPE = new QualifiedName(ANTLR_CORE,
			ANTLR_GRAMMAR_TYPE);

	// properties

	String ANTLR_GENERAL_MARK_GENERATED_RESOURCES_AS_DERIVED = "antlr_core_mark_generated_resources_as_derived";

	/**
	 * Builder options
	 */

	String ANTLR_BUILDER_RUNTIME = "antlr_core_builder_runtime";

	String ANTLR_BUILDER_MAX_NUMBER_OF_PROBLEMS_REPORTED_PER_GRAMMAR = "antlr_core_builder_max_number_of_problems_reported_per_grammar";

	String ANTLR_BUILDER_INCLUDE_STACK_TRACE_ON_INTERNAL_ERRORS = "antlr_core_builder_include_stacktrace_on_internal_errors";

	String ANTLR_BUILDER_REPORT = "antlr_core_builder_report";

	/**
	 * -nfa
	 */
	String ANTLR_BUILDER_NFA = "antlr_core_builder_nfa";

	/**
	 * -dfa
	 */
	String ANTLR_BUILDER_DFA = "antlr_core_builder_dfa";

	String ANTLR_BUILDER_X_DFA = "antlr_core_builder_Xdfa";

	/**
	 * -Xnoprune
	 */
	String ANTLR_BUILDER_X_NO_PRUNE = "antlr_core_builder_Xnoprune";

	/**
	 * -Xnocollapse
	 */
	String ANTLR_BUILDER_X_NO_COLLAPSE = "antlr_core_builder_Xnocollapse";

	String ANTLR_BUILDER_X_DBG_CONVERSION = "antlr_core_builder_Xdbgconversion";

	String ANTLR_BUILDER_X_NO_MERGE_STOP_STATES = "antlr_core_builder_Xnomergestopstates";

	/**
	 * -Xdfaverbose
	 */
	String ANTLR_BUILDER_X_DFA_VERBOSE = "antlr_core_builder_Xdfaverbose";

	String ANTLR_BUILDER_X_WATCH_CONVERSION = "antlr_core_builder_Xwatchconversion";

	String ANTLR_BUILDER_X_M = "antlr_core_builder_Xm";

	String ANTLR_BUILDER_X_MAX_DFA_EDGES = "antlr_core_builder_Xmaxdfaedges";

	String ANTLR_BUILDER_X_CONVERSION_TIME_OUT = "antlr_core_builder_Xconversiontimeout";

	/**
	 * Code generator options
	 */

	String ANTLR_CODE_GENERATOR_X_DBG_ST = "antlr_core_code_generator_XdbgST";

	String ANTLR_CODE_GENERATOR_DEBUG = "antlr_core_code_generator_debug";

	String ANTLR_CODE_GENERATOR_PROFILE = "antlr_core_code_generator_profile";

	String ANTLR_CODE_GENERATOR_TRACE = "antlr_core_code_generator_trace";

	String ANTLR_CODE_GENERATOR_MAX_MEMORY = "antlr_core_code_generator_max_memory";

	String ANTLR_CODE_GENERATOR_X_MAX_SWITCH_CASE_LABELS = "antlr_core_code_generator_x_max_switch_case_labels";

	String ANTLR_CODE_GENERATOR_X_MIN_SWITCH_ALTS = "antlr_core_code_generator_x_min_switch_alts";

	String ANTLR_CODE_GENERATOR_OUTPUT_OPTION = "antlr_core_code_generator_out_option";

	String ANTLR_CODE_GENERATOR_OUTPUT_FOLDER = "antlr_core_code_generator_out_folder";

	String ANTLR_CODE_GENERATOR_APPEND_JAVA_PACKAGE_TO_OUTPUT_FOLDER = "antlr_core_code_generator_append_java_package_to_out_folder";

	String ANTLR_CODE_GENERATOR_OUTPUT_OPTION_SAME_AS_GRAMMAR = "g";

	String ANTLR_CODE_GENERATOR_OUTPUT_OPTION_RELATIVE_FOLDER = "p";

	String ANTLR_CODE_GENERATOR_OUTPUT_OPTION_ABSOLUTE_FOLDER = "a";

	String ANTLR_SAVE_ACTIONS_ENABLED = "antlr_core_save_actions_enabled";

	String ANTLR_SAVE_ACTIONS_GENERATE_RESOURCES_ENABLED = "antlr_core_save_actions_generate_resources_enabled";
	
	String ANTLR_SAVE_ACTIONS_FORMAT_CODE_ENABLED = "antlr_core_save_actions_format_code_enabled";

	Pattern ANTLR_JAVA_PACKAGE_PATTERN = Pattern
			.compile("package\\s*(((\\w)+\\.?)+)\\s*;");
}
