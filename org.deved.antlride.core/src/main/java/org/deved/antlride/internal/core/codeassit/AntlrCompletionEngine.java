/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.internal.core.codeassit;

import java.util.ArrayList;
import java.util.Collection;

import org.deved.antlride.core.AntlrCore;
import org.deved.antlride.core.build.AntlrSourceParserRepository;
import org.deved.antlride.core.model.ElementKind;
import org.deved.antlride.core.model.ICallExpression;
import org.deved.antlride.core.model.IGrammar;
import org.deved.antlride.core.model.IModelElement;
import org.deved.antlride.core.model.IOption;
import org.deved.antlride.core.model.IOptions;
import org.deved.antlride.core.model.IReference;
import org.deved.antlride.core.model.IRule;
import org.deved.antlride.core.model.IScope;
import org.deved.antlride.core.model.IStatement;
import org.deved.antlride.core.model.ITargetAction;
import org.deved.antlride.core.model.RuleType;
import org.deved.antlride.core.model.ast.AntlrModelElementLocator;
import org.deved.antlride.core.model.ast.ModelElementQuery;
import org.deved.antlride.core.util.AntlrTextHelper;
import org.deved.antlride.internal.core.model.dltk.FakeField;
import org.deved.antlride.internal.core.model.dltk.FakeMethod;
import org.eclipse.dltk.codeassist.ScriptCompletionEngine;
import org.eclipse.dltk.compiler.env.IModuleSource;
import org.eclipse.dltk.core.CompletionProposal;
import org.eclipse.dltk.core.IField;
import org.eclipse.dltk.core.IMethod;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.IType;
import org.eclipse.dltk.core.ModelException;

public class AntlrCompletionEngine extends ScriptCompletionEngine {
	private ISourceModule currentModule;

	private static final int OPTION_RELEVANCE = 424241;

	private static final int RULE_RELEVANCE = 424242;

	private static final int VARIABLE_REFERENCE_RELEVANCE = 424251;

	public AntlrCompletionEngine() {
	}

	public void complete(
			IModuleSource envSourceModule,
			int position, int i) {
		this.requestor.beginReporting();
		this.actualCompletionPosition = position;
		try {
			currentModule = (ISourceModule) envSourceModule;

			IGrammar grammar = AntlrSourceParserRepository
					.getGrammar(currentModule);

			char[] source = currentModule.getSourceAsCharArray();

			AntlrModelElementLocator locator = new AntlrModelElementLocator(
					grammar);
			IModelElement element = locator.getElementAt(position - 1);
			if (element == null)
				return;

			if (element.getElementKind() == ElementKind.REFERENCE
					|| element.getElementKind() == ElementKind.CALL_PARAMETER) {
				element = element.getParent();
			}
			// complete findRule
			if (element instanceof IStatement
					|| element.getElementKind() == ElementKind.CALL_PARAMETERS) {
				completeRule(element, position, source);
			} else {
				ElementKind kind = element.getElementKind();
				if (kind == ElementKind.GRAMMAR_OPTIONS
						|| kind.isChildOf(ElementKind.GRAMMAR_OPTIONS)
						|| kind == ElementKind.RULE_OPTIONS
						|| kind.isChildOf(ElementKind.RULE_OPTIONS)) {
					IOptions options = element.getAdapter(IOptions.class);
					completeOptions(options, position, source);
				}
			}
		} catch (Throwable t) {
			if(AntlrCore.DEBUG)
				t.printStackTrace();
		} finally {
			this.requestor.endReporting();
		}
	}

	private void completeOptions(IOptions options, int position, char[] source) {
		// source range information
		String prefix = AntlrTextHelper.findWord(source, position - 1, false);
		String word = AntlrTextHelper.findWord(source, position - 1, true);
		int start = position - prefix.length();
		int end = start + word.length();
		setSourceRange(start, end);
		String[] missingOptions = getOptions(options);
		for (String string : missingOptions) {
			FakeField field = new FakeField(currentModule, string);
			reportOption(field);
		}
	}

	private String[] getOptions(IOptions options) {
		String[] result = null;
		switch (options.getElementKind()) {
		case GRAMMAR_OPTIONS:
			result = IOptions.EOptions.getMissingGrammarOptions(options);
			break;
		case RULE_OPTIONS:
		case BLOCK_OPTIONS:
			result = IOptions.EOptions.getMissingBlockOptions(options);
			break;
		}
		return result;
	}

	private boolean match(char[] source, int position, char ch) {
		try {
			return source[position] == ch;
		} catch (Exception ex) {
		}
		return false;
	}

	private void completeRule(IModelElement element, int position, char[] source) {
		ElementKind kind = ElementKind.RULE;
		// source range information
		String prefix = AntlrTextHelper.findWord(source, position - 1, false);
		String word = AntlrTextHelper.findWord(source, position - 1, true);
		String refName = null;
		int start = position - prefix.length();
		int end = start + word.length();
		int pos = position - prefix.length() - 1;
		setSourceRange(start, end);
		// completation kind
		if (element.getElementKind() == ElementKind.BLOCK
				|| element.getElementKind() == ElementKind.ALTERNATIVE) {
			kind = ElementKind.CALL;
		}
		if (match(source, pos, '$')) {
			kind = ElementKind.VARIABLE;
		} else if (match(source, pos, '.')) {
			refName = AntlrTextHelper.findWord(source, pos - 1, false);
			kind = ElementKind.RULE_REFERENCE;
		} else if (match(source, pos, ':') && match(source, pos - 1, ':')) {
			refName = AntlrTextHelper.findWord(source, pos - 2, false);
			kind = ElementKind.SCOPE_REFERENCE;
		}
		// complete
		switch (kind) {
		case VARIABLE:
			completeVariableReference(element, prefix);
			break;
		case CALL_PARAMETERS:
		case RULE_REFERENCE: {
			IReference reference = findReference(element, refName);
			completeRuleProperties(reference, prefix);
		}
			break;
		case SCOPE_REFERENCE: {
			IReference reference = findReference(element, refName);
			completeScopeAttributes(reference, prefix);
		}
			break;
		default:
			completeRuleCall((IStatement) element, source, position, prefix);
			break;
		}
	}

	private IReference findReference(IModelElement statement,
			String referenceName) {
		IReference reference = null;
		ITargetAction targetAction = statement.getAdapter(ITargetAction.class);
		if (targetAction != null) {
			IReference[] references = targetAction.getReferences();
			for (int i = 0; i < references.length; i++) {
				if (references[i].getElementName().equals(referenceName)) {
					reference = references[i];
					break;
				}
			}
		}
		return reference;
	}

	private void completeRuleProperties(IReference reference, String prefix) {
		if (reference == null)
			return;
		ITargetAction targetAction = reference.getAdapter(ITargetAction.class);
		ICallExpression callExpression = reference
				.getAdapter(ICallExpression.class);
		IRule rule = reference.getAdapter(IRule.class);// enclosing rule
		IRule referenedRule = null;
		if (callExpression != null) {
			IGrammar grammar = callExpression.getAdapter(IGrammar.class);
			referenedRule = grammar.findRule(callExpression.getRuleName()
					.getText());
		} else {
			if (rule != null
					&& reference.getElementName().equals(rule.getElementName())) {
				referenedRule = rule;// same as enclosing rule
			}
		}
		if (referenedRule != null) {
			Collection<String> properties = getProperties(referenedRule,
					targetAction, rule.getElementName());
			for (String property : properties) {
				reportVariableDeclaration(new FakeField(currentModule, property));
			}
		}
	}

	private Collection<String> getProperties(IRule rule,
			ITargetAction targetAction, String referenceName) {
		boolean after = false;
		boolean sameRule = referenceName.equals(rule.getElementName());
		IGrammar grammar = (IGrammar) rule.getParent();
		String output = "";
		Collection<String> properties = new ArrayList<String>(5);
		properties.add("text");
		// @after
		IModelElement parent = targetAction.getParent();
		if (parent.getElementKind() == ElementKind.RULE_ACTION) {
			after = parent.getElementName().equals("after");
		}
		// output=?
		if (grammar.hasOptions()) {
			IOption option = grammar.findOption(IOptions.OUTPUT);
			if (option != null && option.getValue() != null) {
				output = option.getValue().getText();
			}
		}
		// rule type
		RuleType ruleType = rule.getRuleType();
		switch (ruleType) {
		case LEXER:
			properties.add("type");
			properties.add("line");
			properties.add("pos");
			properties.add("index");
			properties.add("channel");
			break;
		case PARSER:
		case TREE_PARSER:
			properties.add("start");
			if (output.equals("AST")) {
				if (after || !sameRule)
					properties.add("tree");
			} else if (output.equals("template")) {
				if (after || !sameRule)
					properties.add("st");
			}
			if (ruleType != RuleType.TREE_PARSER) {
				if (after || !sameRule)
					properties.add("stop");
			}
			break;
		}
		return properties;
	}

	private void completeScopeAttributes(IReference reference, String prefix) {
		if (reference != null) {
			IScope scope = reference.getAdapter(IScope.class);
			if (scope != null) {				
				IModelElement[] elements = ModelElementQuery.collectScopeAttributes(scope, prefix);
				for (IModelElement e : elements) {
					reportVariableDeclaration(new FakeField(currentModule, e));
				}
			}
		}
	}

	private void completeVariableReference(IModelElement element, String prefix) {
		IRule rule = element.getAdapter(IRule.class);
		ITargetAction targetAction = element.getAdapter(ITargetAction.class);
		
		IModelElement[] elements = ModelElementQuery.collectLocalReferences(rule, actualCompletionPosition - 1, prefix);
		for (IModelElement e : elements) {
			if (e.getElementKind() == ElementKind.CALL) {
				if (e.getParent().getElementKind() == ElementKind.ASSIGN_OPERATOR) {
					continue;
				}
			}
			IField method = new FakeField(currentModule, e);
			reportVariableDeclaration(method);
		}
		if (targetAction != null) {
			Collection<String> properties = getProperties(rule,
					targetAction, rule.getElementName());
			for (String property : properties) {
				if (property.startsWith(prefix))
					reportVariableDeclaration(new FakeField(currentModule,
							property));
			}
		}
		if (rule.getElementName().startsWith(prefix) && !rule.hasScopes())
			reportVariableDeclaration(new FakeField(currentModule, rule));
	}

	private void completeRuleCall(IStatement statement, char[] source,
			int position, String prefix) {
		if (statement == null
				|| statement.getElementKind() == ElementKind.TARGET_ACTION)
			// ignore rule auto completation in target action
			return;
		IRule rule = statement.getAdapter(IRule.class);
		if (position < rule.getBody().sourceStart()) {
			// :
			return;
		}
		IGrammar grammar = rule.getAdapter(IGrammar.class);		
		IModelElement[] elements = ModelElementQuery.collectRules(grammar, prefix);
		for (IModelElement e : elements) {
			IMethod method = new FakeMethod(currentModule, e);
			reportRuleReference(method, RULE_RELEVANCE);
		}
	}

	private void reportOption(IField field) {
		String elementName = field.getElementName();

		// accept result
		noProposal = false;
		if (!requestor.isIgnored(CompletionProposal.LABEL_REF)) {
			CompletionProposal proposal = createProposal(
					CompletionProposal.LABEL_REF, actualCompletionPosition);

			proposal.setModelElement(field);
			proposal.setName(elementName);
			proposal.setCompletion(elementName);
			// proposal.setFlags(Flags.AccDefault);
			proposal.setReplaceRange(this.startPosition - this.offset,
					this.endPosition - this.offset);
			proposal.setRelevance(OPTION_RELEVANCE);
			this.requestor.accept(proposal);
		}

	}

	private void reportVariableDeclaration(IField field) {
		String elementName = field.getElementName();

		// accept result
		noProposal = false;
		if (!requestor.isIgnored(CompletionProposal.LOCAL_VARIABLE_REF)) {
			CompletionProposal proposal = createProposal(
					CompletionProposal.LOCAL_VARIABLE_REF,
					actualCompletionPosition);

			proposal.setModelElement(field);
			proposal.setName(elementName);
			proposal.setCompletion(elementName);
			// proposal.setFlags(Flags.AccDefault);
			proposal.setReplaceRange(this.startPosition - this.offset,
					this.endPosition - this.offset);
			proposal.setRelevance(VARIABLE_REFERENCE_RELEVANCE);
			this.requestor.accept(proposal);
		}
	}

	private void reportRuleReference(IMethod method, int relevance) {
		String elementName = method.getElementName();

		if (elementName.indexOf('.') != -1) {
			elementName = elementName.substring(elementName.indexOf('.') + 1);
		}

		// accept result
		noProposal = false;
		if (!requestor.isIgnored(CompletionProposal.METHOD_DECLARATION)) {
			CompletionProposal proposal = createProposal(
					CompletionProposal.METHOD_DECLARATION,
					actualCompletionPosition);

			String[] params = null;
			try {
				params = method.getParameterNames();
			} catch (ModelException e) {
				// ANTLR3Plugin.error(e);
			}

			if (params != null && params.length > 0) {
				proposal.setParameterNames(params);
			}
			proposal.setModelElement(method);
			proposal.setName(elementName);
			proposal.setCompletion(elementName);
			proposal.setReplaceRange(this.startPosition - this.offset,
					this.endPosition - this.offset);
			proposal.setRelevance(relevance);
			this.requestor.accept(proposal);
		}
	}

	@Override
	protected int getEndOfEmptyToken() {
		return 0;
	}

	@Override
	protected String processMethodName(IMethod method, String token) {
		return method.getElementName();
	}

	@Override
	protected String processTypeName(IType method, String token) {
		return null;
	}
}
