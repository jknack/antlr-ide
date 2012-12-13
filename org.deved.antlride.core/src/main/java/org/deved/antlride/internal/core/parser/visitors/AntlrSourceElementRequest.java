/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.internal.core.parser.visitors;

import org.deved.antlride.core.model.ElementKind;
import org.deved.antlride.core.model.dltk.ast.DASTCallExpression;
import org.deved.antlride.core.model.dltk.ast.DASTGrammarAction;
import org.deved.antlride.core.model.dltk.ast.DASTImport;
import org.deved.antlride.core.model.dltk.ast.DASTImports;
import org.deved.antlride.core.model.dltk.ast.DASTOption;
import org.deved.antlride.core.model.dltk.ast.DASTOptions;
import org.deved.antlride.core.model.dltk.ast.DASTRuleAction;
import org.deved.antlride.core.model.dltk.ast.DASTScopeAttribute;
import org.deved.antlride.core.model.dltk.ast.DASTToken;
import org.deved.antlride.core.model.dltk.ast.DASTTokens;
import org.eclipse.dltk.ast.declarations.TypeDeclaration;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.statements.Statement;
import org.eclipse.dltk.compiler.ISourceElementRequestor;
import org.eclipse.dltk.compiler.SourceElementRequestVisitor;

public class AntlrSourceElementRequest extends SourceElementRequestVisitor {

	public AntlrSourceElementRequest(ISourceElementRequestor requesor) {
		super(requesor);
	}

	private void addTypeReference(TypeDeclaration node) {
		fRequestor.acceptTypeReference(node.getName(), node.sourceStart());
	}

	private void addFieldReference(DASTScopeAttribute node) {
		fRequestor.acceptFieldReference(node.getName(), node.sourceStart());
	}

	public void addMethodReference(DASTCallExpression node) throws Exception {
		String refName = node.getName();
		if (refName.charAt(0) == '\"' || refName.charAt(0) == '\'')
			return;
		fRequestor.acceptMethodReference(refName, 0, node.sourceStart(), node
				.sourceEnd());
	}

	@Override
	public boolean visit(Expression expression) throws Exception {
		if (expression instanceof DASTCallExpression) {
			DASTCallExpression callExpressionNode = (DASTCallExpression) expression;
			addMethodReference(callExpressionNode);
		}
		return super.visit(expression);
	}

	@Override
	public boolean visit(TypeDeclaration type) throws Exception {
		addTypeReference(type);
		return super.visit(type);
	}

	@Override
	public boolean visit(Statement statement) throws Exception {
		ElementKind kind = ElementKind.values()[statement.getKind()];

		switch (kind) {
		case RULE_OPTIONS:
		case GRAMMAR_OPTIONS: {
			return visit((DASTOptions) statement);
		}
		case RULE_OPTION:
		case GRAMMAR_OPTION: {
			return visit((DASTOption) statement);
		}
		case IMPORTS: {
			return visit((DASTImports) statement);
		}
		case IMPORT: {
			return visit((DASTImport) statement);
		}
		case TOKENS: {
			return visit((DASTTokens) statement);
		}
		case TOKEN: {
			return visit((DASTToken) statement);
		}
		case RULE_SCOPE_ATTRIBUTE:
		case GRAMMAR_SCOPE_ATTRIBUTE: {
			return visit((DASTScopeAttribute) statement);
		}
		case GRAMMAR_ACTION: {
			return visit((DASTGrammarAction) statement);
		}
		case RULE_ACTION: {
			return visit((DASTRuleAction) statement);
		}
		}
		return true;
	}

	@Override
	public boolean endvisit(Statement statement) throws Exception {
		ElementKind kind = ElementKind.values()[statement.getKind()];

		switch (kind) {
		case RULE_OPTIONS:
		case GRAMMAR_OPTIONS: {
			return endvisit((DASTOptions) statement);
		}
		case RULE_OPTION:
		case GRAMMAR_OPTION: {
			return endvisit((DASTOption) statement);
		}
		case IMPORTS: {
			return endvisit((DASTImports) statement);
		}
		case IMPORT: {
			return endvisit((DASTImport) statement);
		}
		case TOKENS: {
			return endvisit((DASTTokens) statement);
		}
		case TOKEN: {
			return endvisit((DASTToken) statement);
		}
		case RULE_SCOPE_ATTRIBUTE:
		case GRAMMAR_SCOPE_ATTRIBUTE: {
			return endvisit((DASTScopeAttribute) statement);
		}
		case GRAMMAR_ACTION: {
			return endvisit((DASTGrammarAction) statement);
		}
		case RULE_ACTION: {
			return endvisit((DASTRuleAction) statement);
		}
		}
		return true;
	}

	public boolean visit(DASTOptions node) throws Exception {
		this.fNodes.push(node);
		return visitField(node.getName(), node.getNameStart(), node
				.getNameEnd());
	}

	public boolean visit(DASTOption node) throws Exception {
		this.fNodes.push(node);
		return visitField(node.getText(), node.sourceStart(), node.sourceEnd());
	}

	public boolean visit(DASTTokens node) throws Exception {
		this.fNodes.push(node);
		return visitField(node.getName(), node.getNameStart(), node
				.getNameEnd());
	}

	public boolean visit(DASTToken node) throws Exception {
		this.fNodes.push(node);
		return visitField(node.getText(), node.sourceStart(), node.sourceEnd());
	}

	public boolean visit(DASTImports node) throws Exception {
		this.fNodes.push(node);
		return visitField(node.getName(), node.getNameStart(), node
				.getNameEnd());
	}

	public boolean visit(DASTImport node) throws Exception {
		this.fNodes.push(node);
		return visitField(node.getText(), node.sourceStart(), node.sourceEnd());
	}

	public boolean visit(DASTScopeAttribute node) throws Exception {
		this.fNodes.push(node);
		addFieldReference(node);
		return visitField(node.getName(), node.sourceStart(), node.sourceEnd());
	}

	public boolean visit(DASTGrammarAction node) throws Exception {
		this.fNodes.push(node);
		ISourceElementRequestor.FieldInfo fi = new ISourceElementRequestor.FieldInfo();
		fi.name = node.getText();
		fi.nameSourceEnd = node.getNameEnd() - 1;
		fi.nameSourceStart = node.getNameStart();
		fi.declarationStart = node.getNameStart();
		fi.modifiers = ElementKind.GRAMMAR_ACTION.ordinal();
		fRequestor.enterField(fi);
		return true;
	}

	public boolean endvisit(DASTOption node) throws Exception {
		fRequestor.exitField(node.sourceEnd());
		this.fNodes.pop();
		return true;
	}

	public boolean endvisit(DASTScopeAttribute node) throws Exception {
		fRequestor.exitField(node.sourceEnd());
		this.fNodes.pop();
		return true;
	}

	public boolean endvisit(DASTOptions node) throws Exception {
		fRequestor.exitField(node.getNameEnd());
		this.fNodes.pop();
		return true;
	}

	public boolean endvisit(DASTToken node) throws Exception {
		fRequestor.exitField(node.sourceEnd());
		this.fNodes.pop();
		return true;
	}

	public boolean endvisit(DASTTokens node) throws Exception {
		fRequestor.exitField(node.getNameEnd());
		this.fNodes.pop();
		return true;
	}

	public boolean endvisit(DASTImport node) throws Exception {
		fRequestor.exitField(node.sourceEnd());
		this.fNodes.pop();
		return true;
	}

	public boolean endvisit(DASTImports node) throws Exception {
		fRequestor.exitField(node.getNameEnd());
		this.fNodes.pop();
		return true;
	}

	public boolean visit(DASTRuleAction node) throws Exception {
		this.fNodes.push(node);
		ISourceElementRequestor.FieldInfo fi = new ISourceElementRequestor.FieldInfo();
		fi.name = node.getText();
		fi.nameSourceEnd = node.getNameEnd() - 1;
		fi.nameSourceStart = node.getNameStart();
		fi.declarationStart = node.getNameStart();
		fi.modifiers = ElementKind.RULE_ACTION.ordinal();
		fRequestor.enterField(fi);
		return true;
	}

	public boolean endvisit(DASTRuleAction node) throws Exception {
		fRequestor.exitField(node.getNameEnd());
		this.fNodes.pop();
		return true;
	}

	public boolean endvisit(DASTGrammarAction node) throws Exception {
		fRequestor.exitField(node.getNameEnd());
		this.fNodes.pop();
		return true;
	}

	private boolean visitField(String name, int start, int end)
			throws Exception {
		ISourceElementRequestor.FieldInfo fi = new ISourceElementRequestor.FieldInfo();
		fi.name = name;
		fi.nameSourceEnd = end - 1;
		fi.nameSourceStart = start;
		fi.declarationStart = start;
		fRequestor.enterField(fi);
		return true;
	}
}
