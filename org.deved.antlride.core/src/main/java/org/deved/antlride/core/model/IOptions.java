/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.core.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public interface IOptions extends IModelElement {

	String AST_LABEL_TYPE = "ASTLabelType";

	String BACKTRACK = "backtrack";

	String OUTPUT = "output";

	public enum EOptions {
		E_AST_LABEL_TYPE(AST_LABEL_TYPE, GrammarType.COMBINED,
				GrammarType.PARSER, GrammarType.TREE_PARSER),

		E_BACKTRACK(BACKTRACK),

		E_FILTER("filter", GrammarType.LEXER),

		E_K("k"),

		E_GREEDY("greedy"),

		E_LANGUAGE("language"),

		E_MEMOIZE("memoize"),

		E_OUTPUT(OUTPUT, GrammarType.COMBINED, GrammarType.PARSER,
				GrammarType.TREE_PARSER),

		E_REWRITE("rewrite"),

		E_SUPER_CLASS("superClass"),

		E_TOKEN_VOCAB("tokenVocab", GrammarType.PARSER, GrammarType.TREE_PARSER),

		E_TOKEN_LABEL_TYPE("TokenLabelType");

		private String optionName;

		private GrammarType[] supportedGrammars;

		private EOptions(String optionName, GrammarType... supportedGrammars) {
			this.optionName = optionName;
			this.supportedGrammars = supportedGrammars;
		}

		static public String[] getMissingBlockOptions(IOptions options) {
			Collection<String> stringOptions = collectOptions(options);
			Collection<String> missingOptions = new ArrayList<String>();
			EOptions[] optionValues = { E_K, E_GREEDY, E_MEMOIZE, E_BACKTRACK };
			IGrammar grammar = options.getAdapter(IGrammar.class);
			GrammarType grammarType;
			if (grammar != null
					&& (grammarType = grammar.getGrammarType()) != null) {
				for (EOptions option : optionValues) {
					if (option.isValidOption(grammarType)
							&& (!stringOptions.contains(option.optionName))) {
						missingOptions.add(option.optionName);
					}
				}
			}
			String[] result = new String[missingOptions.size()];
			missingOptions.toArray(result);
			missingOptions.clear();
			return result;
		}

		static public String[] getMissingGrammarOptions(IOptions options) {
			Collection<String> stringOptions = collectOptions(options);
			Collection<String> missingOptions = new ArrayList<String>();
			EOptions[] optionValues = values();
			IGrammar grammar = options.getAdapter(IGrammar.class);
			GrammarType grammarType;
			if (grammar != null
					&& (grammarType = grammar.getGrammarType()) != null) {
				for (EOptions option : optionValues) {
					if (option.isValidOption(grammarType)
							&& (!stringOptions.contains(option.optionName))) {
						missingOptions.add(option.optionName);
					}
				}
			}
			String[] result = new String[missingOptions.size()];
			missingOptions.toArray(result);
			missingOptions.clear();
			return result;
		}

		static private Collection<String> collectOptions(IOptions options) {
			Collection<String> stringOptions = Collections.emptyList();
			if (options != null && options.getOptions() != null
					&& options.getOptions().length > 0) {
				stringOptions = new ArrayList<String>();
				IOption[] optionsArray = options.getOptions();
				for (int i = 0; i < optionsArray.length; i++) {
					IOptionName optionName = optionsArray[i].getName();
					if (optionName != null)
						stringOptions.add(optionName.getElementName());
				}
			}
			return stringOptions;
		}

		public boolean isValidOption(GrammarType grammarType) {
			if (supportedGrammars == null || supportedGrammars.length == 0)
				return true;
			for (GrammarType gt : supportedGrammars) {
				if (gt == grammarType)
					return true;
			}
			return false;
		}

		public String optionName() {
			return optionName;
		}

	}

	IOption findOption(String name);

	IOption[] getOptions();

	int length();
}
