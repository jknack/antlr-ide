package org.deved.antlride.internal.core.antlr;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
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
import org.antlr.misc.IntSet;
import org.antlr.tool.GrammarNonDeterminismMessage;

import antlr.Token;

public class Nondeterminism {
	
	public Nondeterminism() {
	}
	
	public Map<Integer, List<Token>> findAltPaths(GrammarNonDeterminismMessage message) {		
		DecisionProbe decisionProbe = message.probe;

		DFA dfa = decisionProbe.dfa;

		MachineProbe probe = new MachineProbe(dfa);

		Map<Integer, List<Token>> paths = findPaths(decisionProbe, probe, message.problemState);
		
		return paths;
	}

	@SuppressWarnings("unchecked")
	private Map<Integer, List<Token>> findPaths(DecisionProbe decisionProbe,
			MachineProbe probe, DFAState ambiguousState) {
		List<Integer> alts = decisionProbe
				.getNonDeterministicAltsForState(ambiguousState);

		Collections.sort(alts);
//		System.out.println("ambig alts=" + alts);

		List<DFAState> dfaStates = probe.getAnyDFAPathToTarget(ambiguousState);
//		System.out.print("DFA path =");
//		for (DFAState dfaState : dfaStates) {
//			System.out.print(" " + dfaState.stateNumber);
//		}
//		System.out.println("");

		List<IntSet> labels = probe.getEdgeLabels(ambiguousState);

//		System.out
//				.println("labels=" + probe.getInputSequenceDisplay(g, labels));

		Map<Integer, List<Token>> map = new LinkedHashMap<Integer, List<Token>>();
		for (int alt : alts) {
			List<Set<NFAState>> nfaStates = collectNFAState(dfaStates, alt);
			
			List<Token> path = probe.getGrammarLocationsForInputSequence(
					nfaStates, labels);
			
			map.put(alt, path);
		}
		return map;
	}

	private List<Set<NFAState>> collectNFAState(Collection<DFAState> dfaStates,
			int alt) {
		List<Set<NFAState>> states = new ArrayList<Set<NFAState>>();
		for (DFAState dfaState : dfaStates) {
			states.add(collectNFAState(dfaState, alt));
		}
		return states;
	}

	private Set<NFAState> collectNFAState(DFAState dfaState, int alt) {
		NFA nfa = dfaState.dfa.nfa;
		Set<NFAState> states = new HashSet<NFAState>();
		for (Object object : dfaState.nfaConfigurations) {
			NFAConfiguration nfaConfig = (NFAConfiguration) object;
			if (nfaConfig.alt == alt) {
				NFAState state = nfa.getState(nfaConfig.state);
				states.add(state);
			}
		}
		return states;
	}
}
