/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.internal.ui.text.templates;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.deved.antlride.core.build.AntlrSourceParserRepository;
import org.deved.antlride.core.model.IGrammar;
import org.deved.antlride.core.model.IModelElement;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.ui.templates.ScriptTemplateAccess;
import org.eclipse.dltk.ui.templates.ScriptTemplateCompletionProcessor;
import org.eclipse.dltk.ui.templates.ScriptTemplateContext;
import org.eclipse.dltk.ui.text.completion.ScriptContentAssistInvocationContext;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.Region;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.templates.Template;
import org.eclipse.jface.text.templates.TemplateException;
import org.eclipse.jface.text.templates.TemplateProposal;

public class AntlrTemplateCompletionProcessor extends
		ScriptTemplateCompletionProcessor {

	private static final class ProposalComparator implements
			Comparator<ICompletionProposal> {
		public int compare(ICompletionProposal o1, ICompletionProposal o2) {
			return ((TemplateProposal) o2).getRelevance()
					- ((TemplateProposal) o1).getRelevance();
		}
	}

	private static final Comparator<ICompletionProposal> comaparator = new ProposalComparator();

	private static char[] IGNORE = new char[] { '.', ':', '@', '$' };

	public AntlrTemplateCompletionProcessor(
			ScriptContentAssistInvocationContext context) {
		super(context);

	}

	@Override
	public ICompletionProposal[] computeCompletionProposals(ITextViewer viewer,
			int offset) {
		ITextSelection selection = (ITextSelection) viewer
				.getSelectionProvider().getSelection();

		// adjust offset to end of normalized selection
		if (selection.getOffset() == offset)
			offset = selection.getOffset() + selection.getLength();

		String prefix = extractPrefix(viewer, offset);
		Region region = new Region(offset - prefix.length(), prefix.length());
		ScriptTemplateContext context = (ScriptTemplateContext) createContext(
				viewer, region);
		if (context == null)
			return new ICompletionProposal[0];

		ISourceModule sourceModule = context.getSourceModule();

		IGrammar grammar = AntlrSourceParserRepository.getGrammar(sourceModule);

		IModelElement element = grammar.getElementAt(offset);

		if (element != null && !(prefix == null || prefix.length() == 0)) {
			return new ICompletionProposal[0];
		}

		// name of the selection variables {line, word}_selection
		context.setVariable("selection", selection.getText()); //$NON-NLS-1$

		Template[] templates = getTemplates(context.getContextType().getId());

		List<ICompletionProposal> matches = new ArrayList<ICompletionProposal>();
		for (int i = 0; i < templates.length; i++) {
			Template template = templates[i];
			try {
				context.getContextType().validate(template.getPattern());
			} catch (TemplateException e) {
				continue;
			}

			// Addes check of startsWith
			if (template.getName().startsWith(prefix)
					&& template.matches(prefix, context.getContextType()
							.getId()))
				matches.add(createProposal(template, context, (IRegion) region,
						getRelevance(template, prefix)));
		}

		Collections.sort(matches, comaparator);

		return (ICompletionProposal[]) matches
				.toArray(new ICompletionProposal[matches.size()]);
	}

	@Override
	protected String getContextTypeId() {
		return AntlrTemplateContextType.CONTEXT_TYPE_ID;
	}

	@Override
	protected char[] getIgnore() {
		return IGNORE;
	}

	@Override
	protected ScriptTemplateAccess getTemplateAccess() {
		return AntlrTemplateAccess.getInstance();
	}
}
