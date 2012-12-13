/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.internal.ui.text.completion;

import org.deved.antlride.internal.ui.text.templates.AntlrTemplateCompletionProcessor;
import org.eclipse.dltk.ui.text.completion.ScriptCompletionProposalCollector;
import org.eclipse.dltk.ui.text.completion.ScriptCompletionProposalComputer;
import org.eclipse.dltk.ui.text.completion.ScriptContentAssistInvocationContext;
import org.eclipse.jface.text.templates.TemplateCompletionProcessor;

public class AntlrTypeCompletionProposalComputer extends ScriptCompletionProposalComputer {
  protected ScriptCompletionProposalCollector createCollector(ScriptContentAssistInvocationContext context) {
    return new AntlrCompletionProposalCollector(context.getSourceModule());
  }

  protected TemplateCompletionProcessor createTemplateProposalComputer(ScriptContentAssistInvocationContext context) {
    return new AntlrTemplateCompletionProcessor(context);
  }
}
