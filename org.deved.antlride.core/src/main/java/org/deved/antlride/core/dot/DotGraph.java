/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.core.dot;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DotGraph {
	private Map<String, DotNode> nodeMap = new LinkedHashMap<String, DotNode>();

	private List<DotEdge> edgeList = new ArrayList<DotEdge>();

	public final String name;

	public final int decisionNumber;

	public final String ruleName;

	public final String decision;

	public final String description;

	public final String dot;

	public DotGraph(String ruleName, int decision, String description,
			String dot) {
		this.ruleName = ruleName;
		this.decisionNumber = decision;
		this.decision = "dec" + decisionNumber;
		this.description = description;
		this.dot = dot;
		this.name = new StringBuilder(ruleName).append("::").append(decision)
				.toString();
	}

	public Iterator<DotNode> nodes() {
		return nodeMap.values().iterator();
	}

	public Iterator<DotEdge> edges() {
		return edgeList.iterator();
	}

	public void node(String name, DotAttributesBuilder attributesBuilder) {
		nodeMap.put(name, new DotNode(name,
				attributesBuilder == null ? new DotAttribute[0]
						: attributesBuilder.build()));
	}

	public void node(DotNode node) {
		nodeMap.put(node.name, node);
	}

	public void edge(String from, String to,
			DotAttributesBuilder attributesBuilder) {
		edgeList.add(new DotEdge(nodeMap.get(from), nodeMap.get(to),
				attributesBuilder));
	}

	public void edge(String from, String to, DotAttribute attributes[]) {
		edgeList
				.add(new DotEdge(nodeMap.get(from), nodeMap.get(to), attributes));
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (DotNode node : nodeMap.values()) {
			builder.append(node);
			builder.append("\n");
		}
		for (DotEdge edge : edgeList) {
			builder.append(edge);
			builder.append("\n");
		}
		return builder.toString();
	}
}
