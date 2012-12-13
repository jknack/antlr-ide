/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.internal.core.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.deved.antlride.core.AntlrCore;
import org.deved.antlride.core.build.AntlrBuildUnit;
import org.deved.antlride.core.build.AntlrSourceParserRepository;
import org.deved.antlride.core.model.ElementKind;
import org.deved.antlride.core.model.GrammarType;
import org.deved.antlride.core.model.IGrammar;
import org.deved.antlride.core.model.IGrammarAction;
import org.deved.antlride.core.model.IGrammarScope;
import org.deved.antlride.core.model.IImport;
import org.deved.antlride.core.model.IImports;
import org.deved.antlride.core.model.IModelElement;
import org.deved.antlride.core.model.IOption;
import org.deved.antlride.core.model.IOptionValue;
import org.deved.antlride.core.model.IOptions;
import org.deved.antlride.core.model.IRule;
import org.deved.antlride.core.model.IScope;
import org.deved.antlride.core.model.ISourceElement;
import org.deved.antlride.core.model.IToken;
import org.deved.antlride.core.model.ITokens;
import org.deved.antlride.core.model.ast.AntlrModelElementLocator;
import org.deved.antlride.core.model.ast.IModelElementVisitor;
import org.deved.antlride.core.model.dltk.ast.DASTGrammar;
import org.deved.antlride.core.util.AntlrTextHelper;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.dltk.ast.ASTNode;

public class AGrammar implements IGrammar {

	private static final IGrammarAction[] EMPTY_ACTIONS = new IGrammarAction[0];

	private static final IRule[] EMPTY_RULES = new IRule[0];

	private static final IGrammar[] NO_DEPS = new IGrammar[0];

	private static final IGrammarScope[] EMPTY_SCOPES = new IGrammarScope[0];

	private static final Map<String, String> DEFAULT_OPTIONS = new HashMap<String, String>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			put("language", "Java");
		}
	};

	private IGrammarAction[] actions;

	private String documentation;

	private ISourceElement name;

	private IOptions options;

	private IRule[] rules;

	private IGrammarScope[] scopes;

	private ITokens tokens;

	private GrammarType type;

	private String source;

	private int[] offsets;

	private IPath file;

	private IPath folder;

	private IPath absoluteFile;

	private IPath absoluteFolder;

	private boolean valid;

	private DASTGrammar grammarModule;

	private boolean implicitLexerGrammar;

	private boolean prototypeGrammar;

	private IImports imports;

	private Collection<IGrammar> grammars;

	private IRule[] allrules;

	private List<String> comments = new ArrayList<String>();

	public AGrammar(IPath file, String source) {
		this.source = source;
		this.offsets = offsets();
		// paths
		this.file = file;
		this.folder = file.removeLastSegments(1);
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		String parent = file.segment(0).trim();
		if (parent.length() == 0) {
			absoluteFile = root.getLocation().removeLastSegments(1)
					.append(file.lastSegment());
		} else {
			IProject project = root.getProject(parent);
			absoluteFile = project.getLocation().removeLastSegments(1)
					.append(file);
		}
		absoluteFolder = absoluteFile.removeLastSegments(1);
		// check for implicit lexer grammar or prototype grammar
		try {
			IFile resource = root.getFile(file);
			processResource(resource);
		} catch (Exception ex) {
			AntlrCore.error(ex);
		}
	}

	public AGrammar(String source) {
		this.source = source;
	}

	protected void processResource(IFile resource) throws CoreException {
		String prototype = resource
				.getPersistentProperty(AntlrBuildUnit.DEPENDENT_GRAMMAR);
		this.prototypeGrammar = Boolean.valueOf(prototype).booleanValue();
	}

	public IImports getImports() {
		return imports;
	}

	public void setImports(IImports imports) {
		this.imports = imports;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (obj instanceof IGrammar) {
			IGrammar grammar = (IGrammar) obj;
			return getAbsoluteFile().equals(grammar.getAbsoluteFile());
		}
		return false;
	}

	@Override
	public int hashCode() {
		return getAbsoluteFile().hashCode();
	}

	public IRule firstRule() {
		if (hasRules())
			return rules[0];
		return null;
	}

	public boolean isValid() {
		return valid;
	}

	public boolean isCombinedGrammar() {
		return type == GrammarType.COMBINED;
	}

	public boolean isImplicitLexerGrammar() {
		return implicitLexerGrammar;
	}

	public boolean isLexerGrammar() {
		return type == GrammarType.LEXER;
	}

	public boolean isParserGrammar() {
		return type == GrammarType.PARSER;
	}

	public boolean isPrototypeGrammar() {
		return prototypeGrammar;
	}

	public boolean isTreeParserGrammar() {
		return type == GrammarType.TREE_PARSER;
	}

	public boolean isCompositeGrammar() {
		return hasImports();
	}

	public void setValid(boolean generateCode) {
		this.valid = generateCode;
	}

	public <E> E getAdapter(Class<E> adapter) {
		if (adapter == IGrammar.class)
			return adapter.cast(this);
		if (adapter == ASTNode.class)
			return adapter.cast(getGrammarModule());
		return null;
	}

	private DASTGrammar getGrammarModule() {
		if (grammarModule == null) {
			grammarModule = new DASTGrammar(this);
		}
		return grammarModule;
	}

	public ElementKind getElementKind() {
		return ElementKind.GRAMMAR;
	}

	public IModelElement getElementAt(int position) {
		AntlrModelElementLocator locator = new AntlrModelElementLocator(this);
		IModelElement element = locator.getElementAt(position);
		return element;
	}

	public String getElementName() {
		if (name == null) {
			String name = getFile().removeFileExtension().segment(
					getFile().segmentCount() - 1);
			return name;
		}
		return name.getText();
	}

	public void traverse(IModelElementVisitor visitor) {
		if (visitor.visitGrammar(this)) {
			if (hasOptions())
				options.traverse(visitor);

			if (hasImports())
				imports.traverse(visitor);

			if (hasTokens())
				tokens.traverse(visitor);

			if (hasScopes()) {
				for (int i = 0; i < scopes.length; i++) {
					scopes[i].traverse(visitor);
				}
			}

			if (hasActions()) {
				for (int i = 0; i < actions.length; i++) {
					actions[i].traverse(visitor);
				}
			}
			IRule[] rules = getRules();
			if (rules != null && rules.length > 0) {
				for (int i = 0; i < rules.length; i++) {
					rules[i].traverse(visitor);
				}
			}

			visitor.endvisitGrammar(this);
		}
	}

	public IGrammarAction findAction(String name) {
		if (hasActions())
			for (int i = 0; i < actions.length; i++) {
				IGrammarAction action = actions[i];
				if (action.getElementName().equals(name)) {
					return action;
				}
			}
		return null;
	}

	public IOption findOption(String name) {
		if (hasOptions())
			return options.findOption(name);
		return null;
	}

	public IRule findRule(String name) {
		IRule[] rules = getRules();
		for (int i = 0; i < rules.length; i++) {
			IRule rule = rules[i];
			if (rule.getName().getText().equals(name)) {
				return rule;
			}
		}
		switch (type) {
		case TREE_PARSER:
		case PARSER:
			IGrammar tokenGrammar = getTokenVocab();
			if (tokenGrammar != null) {
				return tokenGrammar.findRule(name);
			}
		}
		return null;
	}

	public IGrammarScope findScope(String name) {
		for (int i = 0; i < scopes.length; i++) {
			IGrammarScope scope = scopes[i];
			if (scope.getElementName().equals(name)) {
				return scope;
			}
		}
		return null;
	}

	public IImport findImport(String name) {
		if (hasImports()) {
			IImport[] imports = getImports().getImports();
			for (IImport imp : imports) {
				if (name.equals(imp.getElementName())) {
					return imp;
				}
			}
		}
		return null;
	}

	public IToken findToken(String name) {
		if (hasTokens())
			return tokens.findToken(name);
		switch (type) {
		case TREE_PARSER:
		case PARSER:
			IGrammar tokenGrammar = getTokenVocab();
			if (tokenGrammar != null) {
				return tokenGrammar.findToken(name);
			}
		}
		return null;
	}

	public IGrammarAction[] getActions() {
		return actions;
	}

	public String getPlainDocumentation() {
		return documentation;
	}

	public String getDocumentation() {
		return AntlrTextHelper.parseDoc(documentation);
	}

	public boolean isIn(int position) {
		return true;
	}

	public IPath getAbsoluteFile() {
		return absoluteFile;
	}

	public IPath getAbsoluteFolder() {
		return absoluteFolder;
	}

	public IPath getFile() {
		return file;
	}

	public IPath getFolder() {
		return folder;
	}

	public ISourceElement getName() {
		return name;
	}

	public String getOption(String optionName) {
		String value = null;
		IOption option = findOption(optionName);
		if (option != null) {
			IOptionValue optionValue = option.getValue();
			if (optionValue != null) {
				value = optionValue.getText();
			}
		}
		if (value == null) {
			value = DEFAULT_OPTIONS.get(optionName);
		}
		return value;
	}

	public IOptions getOptions() {
		return options;
	}

	public IRule[] getRules() {
		// if (isCompositeGrammar()) {
		// if (allrules == null) {
		// Collection<IRule> ruleList = new ArrayList<IRule>();
		// ruleList.addAll(Arrays.asList(rules));
		// for (IGrammar g : grammars) {
		// ruleList.addAll(Arrays.asList(g.getRules()));
		// }
		// allrules = ruleList.toArray(new IRule[ruleList.size()]);
		// ruleList.clear();
		// for (int i = 0; i < rules.length; i++) {
		// rules[i] = null;
		// }
		// rules = null;
		// }
		// } else {
		allrules = rules;
		// }
		return allrules;
	}

	public void addDependent(IGrammar grammar) {
		if (grammars == null) {
			grammars = new ArrayList<IGrammar>();
		}
		grammars.add(grammar);
	}

	public IGrammarScope[] getScopes() {
		return scopes;
	}

	public String getSource() {
		return source;
	}

	public ITokens getTokens() {
		return tokens;
	}

	public GrammarType getGrammarType() {
		return type == null ? GrammarType.COMBINED : type;
	}

	public IGrammar[] getDependents() {
		if (grammars == null) {
			return NO_DEPS;
		}
		return grammars.toArray(new IGrammar[grammars.size()]);
	}

	public IGrammar getTokenVocab() {
		IGrammar tokenGrammar = null;
		String tokenGrammarName = getOption("tokenVocab");
		if (tokenGrammarName != null) {
			tokenGrammar = AntlrSourceParserRepository.lookupGrammar(
					getFolder(), tokenGrammarName + ".g");
		}
		return tokenGrammar;
	}

	public boolean hasActions() {
		return actions != null && actions.length > 0;
	}

	public boolean hasImports() {
		return imports != null && imports.getImports().length > 0;
	}

	public boolean hasOptions() {
		return options != null;
	}

	public boolean hasOption(String name) {
		return findOption(name) != null;
	}

	public boolean hasRules() {
		return getRules() != null && getRules().length > 0;
	}

	public boolean hasRule(String name) {
		return findRule(name) != null;
	}

	public boolean hasScope(String name) {
		return findScope(name) != null;
	}

	public boolean hasScopes() {
		return scopes != null && scopes.length > 0;
	}

	public boolean hasToken(String name) {
		return findToken(name) != null;
	}

	public boolean hasTokens() {
		return tokens != null;
	}

	public IModelElement getParent() {
		return null;
	}

	public int sourceStart() {
		return 0;
	}

	public int sourceEnd() {
		IRule[] rules = getRules();
		if (rules.length > 0) {
			return rules[rules.length - 1].sourceEnd();
		}
		return 0;
	}

	private int[] offsets() {
		List<Integer> clines = new ArrayList<Integer>();
		clines.add(new Integer(0));
		int offset = 0;
		for (int i = 0; i < source.length(); i++) {
			char ch = source.charAt(i);
			offset++;
			if (ch == '\n') {
				clines.add(new Integer(offset));
			}
		}
		int[] lines = new int[clines.size()];
		for (int i = 0; i < lines.length; i++) {
			lines[i] = clines.get(i).intValue();
		}
		return lines;
	}

	public void setName(ISourceElement name) {
		this.name = name;
	}

	public int getOffset(int line) {
		if (line > -1 && line < offsets.length)
			return offsets[line];
		return -1;
	}

	public void setDocumentation(String documentation) {
		this.documentation = documentation;
	}

	public void setOptions(IOptions options) {
		this.options = options;
	}

	public void setTokens(ITokens tokens) {
		this.tokens = tokens;
	}

	public void setGrammarType(GrammarType type) {
		this.type = type;
	}

	public void setActions(Collection<IGrammarAction> actions) {
		if (actions == null || actions.size() == 0) {
			this.actions = EMPTY_ACTIONS;
		} else {
			this.actions = new IGrammarAction[actions.size()];
			this.actions = actions.toArray(this.actions);
			for (IGrammarAction action : this.actions) {
				((AGrammarAction) action).setParent(this);
			}
		}
	}

	public void setRules(Collection<IRule> rules) {
		if (rules == null || rules.size() == 0) {
			this.rules = EMPTY_RULES;
		} else {
			this.rules = rules.toArray(new IRule[rules.size()]);
		}
	}

	public void setScopes(Collection<IScope> scopes) {
		if (scopes == null || scopes.size() == 0) {
			this.scopes = EMPTY_SCOPES;
		} else {
			this.scopes = new IGrammarScope[scopes.size()];
			this.scopes = scopes.toArray(this.scopes);
			for (int i = 0; i < this.scopes.length; i++) {
				((AGrammarScope) this.scopes[i]).setParent(this);
			}
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		if (documentation != null && documentation.length() > 0) {
			builder.append(documentation);
			builder.append("\n");
		}
		if (type != null && type.description().length() > 0) {
			builder.append(type.description());
			builder.append(" ");
		}
		if (name != null) {
			builder.append("grammar ");
			builder.append(name.getText());
			builder.append(";\n");
		}

		if (options != null) {
			builder.append(options);
		}

		if (tokens != null) {
			builder.append(tokens);
		}

		if (scopes != null && scopes.length > 0) {
			builder.append("\n");
			for (int i = 0; i < scopes.length; i++) {
				builder.append(scopes[i]);
			}
		}

		if (actions != null) {
			for (int i = 0; i < actions.length; i++) {
				builder.append(actions[i]);
			}
		}

		if (rules != null) {
			for (int i = 0; i < rules.length; i++) {
				builder.append(rules[i]);
			}
		}
		return builder.toString();
	}

	public void addComment(String comment) {
		comments.add(comment);
	}

	public String[] getComments() {
		return comments.toArray(new String[comments.size()]);
	}

	public String toEbnf() {
		StringBuilder buff = new StringBuilder();
		if (hasRules()) {
			IRule[] rules = getRules();
			for (IRule rule : rules) {
				buff.append(rule.toEbnf()).append("\n");
			}
		}
		return buff.toString();
	}
}
