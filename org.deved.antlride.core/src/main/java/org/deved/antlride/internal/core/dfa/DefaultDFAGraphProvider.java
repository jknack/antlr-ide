/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.internal.core.dfa;

import java.util.ArrayList;
import java.util.Collection;

import org.antlr.analysis.DFA;
import org.antlr.codegen.CodeGenerator;
import org.antlr.tool.DOTGenerator;
import org.antlr.tool.Grammar;
import org.deved.antlride.core.dfa.DFAGraphProvider;
import org.deved.antlride.core.dot.DotGraph;
import org.deved.antlride.core.model.IGrammar;
import org.deved.antlride.internal.core.antlr.ANTLRGrammarProvider;
import org.deved.antlride.internal.core.dot.DotGraphProvider;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;

public class DefaultDFAGraphProvider implements DFAGraphProvider {

	public DotGraph[] dfa(IProgressMonitor monitor, IGrammar grammar) {
		ANTLRGrammarProvider grammarProvider = new ANTLRGrammarProvider();
		monitor.beginTask("Initializing Decision DFAs", 8);
		grammarProvider.build(monitor, grammar);
		Grammar g = grammarProvider.getGrammar();
		DotGraphProvider graphProvider = new DotGraphProvider();
		Collection<DotGraph> graphs = new ArrayList<DotGraph>();
		SubProgressMonitor subProgressMonitor = new SubProgressMonitor(monitor,
				g.getNumberOfDecisions());
		subProgressMonitor.beginTask("Creating DFAs", g.getNumberOfDecisions());
		CodeGenerator codeGenerator = new CodeGenerator(g.tool, g, (String) g
				.getOption("language"));
		// build dfa
		for (int d = 1; d <= g.getNumberOfDecisions(); d++) {
			DFA dfa = g.getLookaheadDFA(d);
			if (dfa == null) {
				continue; // not there for some reason, ignore
			}
			String ruleName = dfa.decisionNFAStartState.enclosingRule.name;
			subProgressMonitor.subTask("Creating " + ruleName + "::dec" + d);
			DOTGenerator dotGenerator = new DOTGenerator(g);
			g.setCodeGenerator(codeGenerator);
			String dot = dotGenerator.getDOT(dfa.startState);
			DotGraph graph = graphProvider.graph(ruleName, d,
					dfa.decisionNFAStartState.getDescription(), dot);
			if (graph != null) {
				graphs.add(graph);
			}
		}
		subProgressMonitor.done();
		monitor.done();
		return graphs.toArray(new DotGraph[graphs.size()]);
	}
}
