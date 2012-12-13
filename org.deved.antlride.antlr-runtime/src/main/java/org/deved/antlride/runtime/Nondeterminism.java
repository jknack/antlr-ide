package org.deved.antlride.runtime;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.antlr.analysis.DFA;
import org.antlr.analysis.DFAState;
import org.antlr.analysis.DecisionProbe;
import org.antlr.analysis.NFA;
import org.antlr.analysis.NFAConfiguration;
import org.antlr.analysis.NFAState;
import org.antlr.tool.GrammarNonDeterminismMessage;

public class Nondeterminism {
	
	public Nondeterminism() {
	}
	
	public Map findAltPaths(GrammarNonDeterminismMessage message) {		
		DecisionProbe decisionProbe = message.probe;

		DFA dfa = decisionProbe.dfa;

		MachineProbe probe = new MachineProbe(dfa);

		Map paths = findPaths(decisionProbe, probe, message.problemState);
		
		return paths;
	}

	private Map findPaths(DecisionProbe decisionProbe,
			MachineProbe probe, DFAState ambiguousState) {
		List alts = decisionProbe.getNonDeterministicAltsForState(ambiguousState);

		Collections.sort(alts);
//		System.out.println("ambig alts=" + alts);

		List dfaStates = probe.getAnyDFAPathToTarget(ambiguousState);
//		System.out.print("DFA path =");
//		for (DFAState dfaState : dfaStates) {
//			System.out.print(" " + dfaState.stateNumber);
//		}
//		System.out.println("");

		List labels = probe.getEdgeLabels(ambiguousState);

//		System.out
//				.println("labels=" + probe.getInputSequenceDisplay(g, labels));

		Map map = new LinkedHashMap();
		for (Iterator it = alts.iterator(); it.hasNext(); ) {
			Integer alt = (Integer) it.next();
			List nfaStates = collectNFAState(dfaStates, alt.intValue());
			
			List path = probe.getGrammarLocationsForInputSequence(
					nfaStates, labels);
			
			map.put(alt, path);
		}
		return map;
	}

	private List collectNFAState(Collection dfaStates, int alt) {
		List states = new ArrayList();
		for (Iterator it = dfaStates.iterator(); it.hasNext(); ) {
			states.add(collectNFAState((DFAState) it.next(), alt));
		}
		return states;
	}

	private Set collectNFAState(DFAState dfaState, int alt) {
		NFA nfa = dfaState.dfa.nfa;
		Set states = new HashSet();
		for (Iterator it = dfaState.nfaConfigurations.iterator(); it.hasNext(); ) {
			NFAConfiguration nfaConfig = (NFAConfiguration) it.next();
			if (nfaConfig.alt == alt) {
				NFAState state = nfa.getState(nfaConfig.state);
				states.add(state);
			}
		}
		return states;
	}
}
