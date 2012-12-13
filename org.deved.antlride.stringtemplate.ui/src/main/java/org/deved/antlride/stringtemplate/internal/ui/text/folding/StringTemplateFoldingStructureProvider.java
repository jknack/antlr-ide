/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.stringtemplate.internal.ui.text.folding;

import org.deved.antlride.common.ui.text.folding.AntlrAbstractFoldingStructureProvider;
import org.deved.antlride.stringtemplate.core.StringTemplateConstants;
import org.deved.antlride.stringtemplate.core.parser.StringTemplateSourceParserFactory;
import org.deved.antlride.stringtemplate.internal.ui.text.partitions.StringTemplatePartitions;
import org.deved.antlride.stringtemplate.ui.StringTemplateUI;
import org.eclipse.core.runtime.ILog;
import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.declarations.MethodDeclaration;
import org.eclipse.dltk.ast.parser.ISourceParser;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.rules.IPartitionTokenScanner;

public class StringTemplateFoldingStructureProvider extends
		AntlrAbstractFoldingStructureProvider {

	@Override
	protected void initializePreferences(IPreferenceStore store) {

		super.initializePreferences(store);
	}

	@Override
	protected ISourceParser getSourceParser() {
		return StringTemplateSourceParserFactory.create();
	}

	protected String getCommentPartition() {
		return StringTemplatePartitions.STG_MULTI_LINE_COMMENT;
	}

	protected ILog getLog() {
		return StringTemplateUI.getDefault().getLog();
	}

	protected String getNatureId() {
		return StringTemplateConstants.NATURE_ID;
	}

	protected String getPartition() {
		return StringTemplatePartitions.PARTITION_ID;
	}

	protected IPartitionTokenScanner getPartitionScanner() {
		return StringTemplateUI.getDefault().getTextTools()
				.getPartitionScanner();
	}

	protected String[] getPartitionTypes() {
		return StringTemplatePartitions.LEGAL_CONTENT_TYPES;
	}

	protected boolean initiallyCollapse(ASTNode s,
			FoldingStructureComputationContext ctx) {
		return !(s instanceof MethodDeclaration);
	}

	@Override
	protected boolean initiallyCollapseComments(IRegion commentRegion,
			FoldingStructureComputationContext ctx) {
		return true;
	}

	protected boolean mayCollapse(ASTNode s,
			FoldingStructureComputationContext ctx) {
		return true;
	}

}
