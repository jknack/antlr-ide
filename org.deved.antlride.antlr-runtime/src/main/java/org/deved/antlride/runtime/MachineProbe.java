package org.deved.antlride.runtime;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.antlr.analysis.DFA;
import org.antlr.analysis.DFAState;
import org.antlr.analysis.NFAState;
import org.antlr.analysis.Transition;
import org.antlr.misc.IntSet;
import org.antlr.tool.Grammar;

public class MachineProbe {
	private DFA dfa;

	public MachineProbe(DFA dfa) {
		this.dfa = dfa;
	}

	public List getAnyDFAPathToTarget(DFAState targetState) {
		Set visited = new HashSet();
		return getAnyDFAPathToTarget(dfa.startState, targetState, visited);
	}

	public List getAnyDFAPathToTarget(DFAState startState,
			DFAState targetState, Set visited) {
		List dfaStates = new ArrayList();
		visited.add(startState);
		if (startState.equals(targetState)) {
			dfaStates.add(targetState);
			return dfaStates;
		}
		// for (Edge e : startState.edges) { // walk edges looking for valid
		// path
		for (int i = 0; i < startState.getNumberOfTransitions(); i++) {
			Transition e = startState.getTransition(i);
			if (!visited.contains(e.target)) {
				List path = getAnyDFAPathToTarget(
						(DFAState) e.target, targetState, visited);
				if (path != null) { // found path, we're done
					dfaStates.add(startState);
					dfaStates.addAll(path);
					return dfaStates;
				}
			}
		}
		return null;
	}

	/** Return a list of edge labels from start state to targetState. */
	public List getEdgeLabels(DFAState targetState) {
		List dfaStates = getAnyDFAPathToTarget(targetState);
		List labels = new ArrayList();
		for (int i = 0; i < dfaStates.size() - 1; i++) {
			DFAState d = (DFAState) dfaStates.get(i);
			DFAState nextState = (DFAState) dfaStates.get(i + 1);
			// walk looking for edge whose target is next dfa state
			for (int j = 0; j < d.getNumberOfTransitions(); j++) {
				Transition e = d.getTransition(j);
				if (e.target.stateNumber == nextState.stateNumber) {
					labels.add(e.label.getSet());
				}
			}
		}
		return labels;
	}

	/**
	 * Given List<IntSet>, return a String with a useful representation of the
	 * associated input string. One could show something different for lexers
	 * and parsers, for example.
	 */
	public String getInputSequenceDisplay(Grammar g, List labels) {
		List tokens = new ArrayList();
		Iterator iterator = labels.iterator();
		while (iterator.hasNext()) {
			IntSet label = (IntSet) iterator.next();
			tokens.add(label.toString(g));
		}
		return tokens.toString();
	}

	/**
	 * Given an alternative associated with a DFA state, return the list of
	 * tokens (from grammar) associated with path through NFA following the
	 * labels sequence. The nfaStates gives the set of NFA states associated
	 * with alt that take us from start to stop. One of the NFA states in
	 * nfaStates[i] will have an edge intersecting with labels[i].
	 */
	public List getGrammarLocationsForInputSequence(
			List nfaStates, List labels) {
		List tokens = new ArrayList();
		for (int i = 0; i < nfaStates.size() - 1; i++) {
			Set cur = (Set) nfaStates.get(i);
			Set next = (Set) nfaStates.get(i + 1);
			IntSet label = (IntSet) labels.get(i);
			// find NFA state with edge whose label matches labels[i]
			for (Iterator it = cur.iterator(); it.hasNext(); ) {
				NFAState p = (NFAState) it.next();
				// walk p's transitions, looking for label
				for (int j = 0; j < p.getNumberOfTransitions(); j++) {
					Transition t = p.transition(j);
					if (!t.isEpsilon() && !t.label.getSet().and(label).isNil()
							&& next.contains(t.target)) {
						if (p.associatedASTNode != null
								&& p.associatedASTNode.token != null) {
							antlr.Token token = p.associatedASTNode.token;
							tokens.add(token);
						}
					}
				}
			}
		}
		return tokens;
	}

	// /** Used to find paths through syntactically ambiguous DFA. If we've
	// * seen statement number before, what did we learn?
	// */
	// protected Map<Integer, Integer> stateReachable;
	//
	// public Map<DFAState, Set<DFAState>> getReachSets(Collection<DFAState>
	// targets) {
	// Map<DFAState, Set<DFAState>> reaches = new HashMap<DFAState,
	// Set<DFAState>>();
	// // targets can reach themselves
	// for (final DFAState d : targets) {
	// reaches.put(d,new HashSet<DFAState>() {{add(d);}});
	// }
	//
	// boolean changed = true;
	// while ( changed ) {
	// changed = false;
	// for (DFAState d : dfa.states.values()) {
	// if ( d.getNumberOfEdges()==0 ) continue;
	// Set<DFAState> r = reaches.get(d);
	// if ( r==null ) {
	// r = new HashSet<DFAState>();
	// reaches.put(d, r);
	// }
	// int before = r.size();
	// // add all reaches from all edge targets
	// for (Edge e : d.edges) {
	// //if ( targets.contains(e.target) ) r.add(e.target);
	// r.addAll( reaches.get(e.target) );
	// }
	// int after = r.size();
	// if ( after>before) changed = true;
	// }
	// }
	// return reaches;
	// }

}
