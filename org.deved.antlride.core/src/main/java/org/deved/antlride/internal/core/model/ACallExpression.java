/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.internal.core.model;

import org.deved.antlride.core.model.ASTSuffix;
import org.deved.antlride.core.model.ElementKind;
import org.deved.antlride.core.model.ICallExpression;
import org.deved.antlride.core.model.ICallExpressionOption;
import org.deved.antlride.core.model.IGrammar;
import org.deved.antlride.core.model.IModelElement;
import org.deved.antlride.core.model.IParameters;
import org.deved.antlride.core.model.IRule;
import org.deved.antlride.core.model.ISourceElement;
import org.deved.antlride.core.model.ast.IModelElementVisitor;
import org.deved.antlride.core.model.dltk.ast.DASTCallExpression;
import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.expressions.CallArgumentsList;

public class ACallExpression extends AStatement implements ICallExpression {

	private IParameters parameters;

	private ISourceElement ruleName;

	private boolean label;

	private ASTSuffix astSuffix;

	private DASTCallExpression callExpressionNode;

	private ICallExpressionOption[] options;

	public ACallExpression(ISourceElement ruleName, ASTSuffix astSuffix,
			boolean label) {
		super(label ? ruleName.sourceStart() + 1 : ruleName.sourceStart(),
				label ? ruleName.sourceEnd() + 1 : ruleName.sourceEnd());
		this.ruleName = ruleName;
		this.label = label;
		this.astSuffix = astSuffix;
	}

	public ACallExpression() {
		label = false;
		astSuffix = ASTSuffix.NONE;
	}

	public void setLabel(boolean label) {
		this.label = label;
		adjustPositions();
	}

	public void setRuleName(ISourceElement ruleName) {
		this.ruleName = ruleName;
		adjustPositions();
	}

	private void adjustPositions() {
		if (ruleName != null) {
			int start = ruleName.sourceStart();
			int end = ruleName.sourceEnd();
			if (label) {
				start++;
				end++;
			}
			setSourceStart(start);
			setSourceEnd(end);
		}
	}

	public <E> E getAdapter(Class<E> adapter) {
		if (adapter == ICallExpression.class)
			return adapter.cast(this);
		if (adapter == IRule.class)
			return adapter.cast(getEnclosingRule());
		if (adapter == IParameters.class)
			return adapter.cast(parameters);
		if (adapter == IGrammar.class)
			return adapter.cast(getEnclosingRule().getParent());
		if (adapter == ASTNode.class)
			return adapter.cast(getAST());
		return null;
	}

	public ICallExpressionOption[] getOptions() {
		return options;
	}

	public void setOptions(ICallExpressionOption[] options) {
		this.options = options;
		for (ICallExpressionOption option : options) {
			((AStatement) option).setParent(this);
		}
	}

	private ASTNode getAST() {
		if (callExpressionNode == null) {
			CallArgumentsList argumentsList = null;
			if (parameters != null) {
				argumentsList = new CallArgumentsList(parameters.sourceStart(),
						parameters.sourceEnd());
				for (int i = 0; i < parameters.getParametersCount(); i++) {
					argumentsList.addNode(parameters.getParemeter(i)
							.getAdapter(ASTNode.class));
				}
			}
			callExpressionNode = new DASTCallExpression(getElementName(),
					sourceStart(), sourceEnd(), argumentsList);
		}
		return callExpressionNode;
	}

	public ASTSuffix getAstSuffix() {
		return astSuffix;
	}

	public ElementKind getElementKind() {
		return ElementKind.CALL;
	}

	public String getElementName() {
		return ruleName.getText();
	}

	public IParameters getParameters() {
		return parameters;
	}

	public ISourceElement getRuleName() {
		return ruleName;
	}

	public String[] getProperties() {
		if (isStringLiteralCall()) {
			return IRule.PREDEFINED_LEXER_RULE_PROPERTIES;
		}
		return null;
	}

	public boolean isStringLiteralCall() {
		String ruleName = getElementName();
		if (ruleName.charAt(0) == '\'' || ruleName.charAt(0) == '\"')
			return true;
		return false;
	}

	public boolean isLexerRuleCall() {
		String ruleName = getElementName();
		if (Character.isUpperCase(ruleName.charAt(0)))
			return true;
		return false;
	}

	public boolean hasLabel() {
		return label;
	}

	public boolean hasParameters() {
		return parameters != null && parameters.getParametersCount() > 0;
	}

	public void setAstSuffix(ASTSuffix astSuffix) {
		this.astSuffix = astSuffix;
	}

	public void setParameters(IParameters parameters) {
		this.parameters = parameters;
	}

	@Override
	public void setParent(IModelElement parent) {
		super.setParent(parent);
		if (parameters != null)
			((AParameters) parameters).setParent(this);
	}

	public void traverse(IModelElementVisitor visitor) {
		if (visitor.visitCallExpression(this)) {
			if (parameters != null) {
				parameters.traverse(visitor);
			}
			visitor.endvisitCallExpression(this);
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		if (hasLabel())
			builder.append("$");

		builder.append(ruleName.getText());

		builder.append(getEbnfOperator().description());

		if (parameters != null) {
			builder.append(parameters);
		}

		if (astSuffix != ASTSuffix.NONE) {
			builder.append(astSuffix.description());
		}

		return builder.toString();
	}
}
