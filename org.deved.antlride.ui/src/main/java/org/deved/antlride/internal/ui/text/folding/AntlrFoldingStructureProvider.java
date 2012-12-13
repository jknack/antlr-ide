/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.internal.ui.text.folding;

import org.deved.antlride.core.AntlrNature;
import org.deved.antlride.core.model.ElementKind;
import org.deved.antlride.core.model.dltk.ast.DASTGrammarAction;
import org.deved.antlride.core.model.dltk.ast.DASTOptions;
import org.deved.antlride.core.model.dltk.ast.DASTRule;
import org.deved.antlride.core.model.dltk.ast.DASTScope;
import org.deved.antlride.core.model.dltk.ast.DASTTokens;
import org.deved.antlride.internal.ui.text.partitions.AntlrPartitionScanner;
import org.deved.antlride.internal.ui.text.partitions.AntlrPartitioner;
import org.deved.antlride.ui.AntlrPreferenceConstants;
import org.deved.antlride.ui.AntlrUI;
import org.deved.antlride.ui.text.AntlrTextPartitions;
import org.eclipse.core.runtime.ILog;
import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.statements.Statement;
import org.eclipse.dltk.ui.text.folding.AbstractASTFoldingStructureProvider;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.IDocumentPartitioner;
import org.eclipse.jface.text.rules.IPartitionTokenScanner;

/**
 */
public class AntlrFoldingStructureProvider extends
		AbstractASTFoldingStructureProvider {

	private boolean fInitCollapseOptions;

	private boolean fInitCollapseTokensSpecification;

	private boolean fInitCollapseScopes;

	private boolean fInitCollapseGrammarActions;

	private boolean fInitCollapseRules;

	public AntlrFoldingStructureProvider() {
	}

	@Override
	protected IDocumentPartitioner getDocumentPartitioner() {
		return new AntlrPartitioner(getPartitionScanner());
	}

	@Override
	public String getCommentPartition() {
		return AntlrTextPartitions.ANTLR_MULTI_LINE_COMMENT;
	}

	@Override
	protected FoldingASTVisitor getFoldingVisitor(int offset) {
		return new AntlrFoldingASTVisitor(offset);
	}

	@Override
	protected ILog getLog() {
		return AntlrUI.getDefault().getLog();
	}

	@Override
	protected String getPartition() {
		return AntlrTextPartitions.ANTLR_PARTITIONING;
	}

	@Override
	protected IPartitionTokenScanner getPartitionScanner() {
		AntlrPartitionScanner partitionScanner = new AntlrPartitionScanner();
		return partitionScanner;
	}

	@Override
	protected String[] getPartitionTypes() {
		return AntlrTextPartitions.LEGAL_CONTENT_TYPES;
	}

	@Override
	protected boolean collapseEmptyLines() {
		return false;
	}

	@Override
	protected void initializePreferences(IPreferenceStore store) {
		super.initializePreferences(store);
		fInitCollapseOptions = store
				.getBoolean(AntlrPreferenceConstants.EDITOR_OPTIONS_FOLDING_ENABLED);
		fInitCollapseGrammarActions = store
				.getBoolean(AntlrPreferenceConstants.EDITOR_GRAMMAR_ACTION_FOLDING_ENABLED);
		fInitCollapseRules = store
				.getBoolean(AntlrPreferenceConstants.EDITOR_RULE_FOLDING_ENABLED);
		fInitCollapseComments = store
				.getBoolean(AntlrPreferenceConstants.EDITOR_COMMENTS_FOLDING_ENABLED);
		fInitCollapseTokensSpecification = store
				.getBoolean(AntlrPreferenceConstants.EDITOR_TOKENS_SPECIFICATION_FOLDING_ENABLED);
		fInitCollapseScopes = store
				.getBoolean(AntlrPreferenceConstants.EDITOR_SCOPES_FOLDING_ENABLED);
	}

	@Override
	protected boolean initiallyCollapse(ASTNode node,
			FoldingStructureComputationContext ctx) {
		if (node instanceof DASTOptions) {
			return ctx.allowCollapsing() && fInitCollapseOptions;
		} else if (node instanceof DASTGrammarAction) {
			return ctx.allowCollapsing() && fInitCollapseGrammarActions;
		} else if (node instanceof DASTTokens) {
			return ctx.allowCollapsing() && fInitCollapseTokensSpecification;
		} else if (node instanceof DASTRule) {
			return ctx.allowCollapsing() && fInitCollapseRules;
		} else if (node instanceof DASTScope) {
			return ctx.allowCollapsing() && fInitCollapseScopes;
		}
		return false;
	}
	
	@Override
	protected boolean mayCollapse(ASTNode node,
			FoldingStructureComputationContext ctx) {
		return true;
	}
	
	protected class AntlrFoldingASTVisitor extends FoldingASTVisitor {
		protected AntlrFoldingASTVisitor(int offset) {
			super(offset);
		}

		@Override
		public boolean visit(Expression s) throws Exception {
			return true;
		}

		@Override
		public boolean endvisit(Expression s) throws Exception {
			return true;
		}

		@Override
		public boolean visitGeneral(ASTNode node) throws Exception {
			if (node.sourceStart() > 0 && node.sourceEnd() > 0) {
				if (supportFolding(node)) {
					add(node);
				}
			}
			return true;
		}

		private boolean supportFolding(ASTNode node) {
			if (!(node instanceof Statement))
				return false;
			Statement statement = (Statement) node;
			int kind = statement.getKind();
			if (kind == ElementKind.GRAMMAR_SCOPE.ordinal()
					|| kind == ElementKind.GRAMMAR_ACTION.ordinal()
					|| kind == ElementKind.GRAMMAR_OPTIONS.ordinal()
					|| kind == ElementKind.RULE_OPTIONS.ordinal()
					|| kind == ElementKind.RULE.ordinal()
					|| kind == ElementKind.RULE_ACTION.ordinal()
					|| kind == ElementKind.TOKENS.ordinal()) {
				return true;
			}
			return false;
		}
	}

	@Override
	protected String getNatureId() {
		return AntlrNature.NATURE_ID;
	}
}
