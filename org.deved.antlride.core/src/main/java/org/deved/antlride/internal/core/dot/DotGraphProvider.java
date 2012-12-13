package org.deved.antlride.internal.core.dot;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.TokenStream;
import org.deved.antlride.core.dot.DotGraph;

public class DotGraphProvider {
	public DotGraph graph(String ruleName, int decision, String description, String dot) {
		DotGraph graph = new DotGraph(ruleName, decision, description, dot);
		try {
			ANTLRStringStream input = new ANTLRStringStream(dot);
			SimpleDotLexer lexer = new SimpleDotLexer(input);
			TokenStream tokens = new CommonTokenStream(lexer);
			SimpleDotParser parser = new SimpleDotParser(tokens);
			parser.graph(graph);

		} catch (RecognitionException e) {
			e.printStackTrace();
		}
		return graph;
	}
}
