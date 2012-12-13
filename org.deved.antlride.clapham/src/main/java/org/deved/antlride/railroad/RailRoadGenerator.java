/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *******************************************************************************/
package org.deved.antlride.railroad;

import java.awt.Dimension;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import net.hydromatic.clapham.chart.Chart;
import net.hydromatic.clapham.chart.ChartFactory;
import net.hydromatic.clapham.chart.ChartOptions;
import net.hydromatic.clapham.chart.draw2d.Draw2dChart;
import net.hydromatic.clapham.chart.exporter.ChartDocumentationProvider;
import net.hydromatic.clapham.chart.exporter.ChartExporterMonitor;
import net.hydromatic.clapham.chart.exporter.ChartHtmlExporter;
import net.hydromatic.clapham.graph.Grammar;
import net.hydromatic.clapham.graph.GrammarFactory;
import net.hydromatic.clapham.parser.ebnf.ANTLRLanguage;

import org.deved.antlride.core.model.IGrammar;
import org.deved.antlride.core.model.IRule;
import org.deved.antlride.core.util.AntlrTextHelper;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;

public class RailRoadGenerator {

	private static class AntlrDocumentationProvider implements
			ChartDocumentationProvider {

		private IGrammar grammar;

		public AntlrDocumentationProvider(IGrammar grammar) {
			this.grammar = grammar;
		}

		public String grammar(String grammarName) {
			return AntlrTextHelper.parseDoc(grammar.getDocumentation());
		}

		public String rule(String ruleName) {
			IRule rule = grammar.findRule(ruleName);
			return rule != null ? AntlrTextHelper.parseDoc(rule
					.getDocumentation()) : null;
		}

	}

	private static class SWTChartExporterMonitor implements
			ChartExporterMonitor {

		private IProgressMonitor monitor;

		public SWTChartExporterMonitor(IProgressMonitor monitor) {
			this.monitor = monitor;
		}

		public void beginTask(String message, int totalOfWork) {
			monitor.beginTask(message, totalOfWork);
		}

		public void done() {
			monitor.done();
		}

		public void subTask(String message) {
			monitor.subTask(message);
		}

		public void worked(int unit) {
			monitor.worked(unit);
		}
	}

	private static enum Draw2dChartFactory implements ChartFactory {

		SINGLETON;

		public Chart createChart(Grammar grammar) {
			return new Draw2dChart(grammar);
		}

	}

	private static class RailRoadDiagram extends Figure {

		private Draw2dChart chart;

		private String[] symbolNames;

		public RailRoadDiagram(Draw2dChart chart, String[] symbolNames) {
			this.symbolNames = symbolNames;
			this.chart = chart;
		}

		@Override
		protected void paintFigure(Graphics graphics) {
			chart.setGraphics(graphics);
			chart.prepare();
			int w = 0;
			int h = 0;
			int initialX = chart.getOptions().initialX();
			int initialY = chart.getOptions().initialY();
			for (int i = 0; i < symbolNames.length; i++) {
				Dimension size = chart.size(symbolNames[i]);
				chart.draw(symbolNames[i]);
				w = Math.max(w, size.width);
				h = Math.max(h, size.height);

				chart.getOptions().withInitialLocation(
						initialX,
						h + chart.getOptions().componentGapHeight()
								+ chart.getOptions().symbolGapHeight());
			}
			// reset the original values
			chart.getOptions().withInitialLocation(initialX, initialY);

			setSize(w, h);
			setPreferredSize(getSize());
		}
	}

	private static String toEbnf(IRule[] rules) {
		StringBuilder buff = new StringBuilder();
		for (IRule rule : rules) {
			buff.append(rule.toEbnf()).append("\n");
		}
		return buff.toString();
	}

	private static String[] names(IRule[] rules) {
		String[] names = new String[rules.length];
		for (int i = 0; i < rules.length; i++) {
			names[i] = rules[i].getElementName();
		}
		return names;
	}

	public static IFigure generate(IRule[] rules, boolean optimize) {
		Reader input = new StringReader(toEbnf(rules));
		Grammar grammar = GrammarFactory.build(ANTLRLanguage.SINGLETON, input,
				optimize);
		return generate(grammar, rules, optimize, ChartOptions.INITIAL_X,
				ChartOptions.INITIAL_Y);
	}

	private static IFigure generate(Grammar grammar, IRule[] rules,
			boolean optimize, int x, int y) {
		Draw2dChart chart = new Draw2dChart(grammar);
		ChartOptions options = chart.createOptions();
		options.withInitialLocation(x, y);
		options.withSymbolName(rules.length > 1);
		chart.setOptions(options);
		return new RailRoadDiagram(chart, names(rules));
	}

	public void export(final IProgressMonitor monitor, IGrammar grammar,
			RailRoadExportOptions options) throws IOException {

		ChartDocumentationProvider documentationProvider = options
				.includeDocumentation() ? new AntlrDocumentationProvider(
				grammar) : null;

		ChartHtmlExporter exporter = new ChartHtmlExporter(
				Draw2dChartFactory.SINGLETON).withEbnfNotation(
				options.includeTextualDefinition()).withOptimize(
				options.optimized()).withOutputDirectory(
				options.outputDirectory()).withDocumentationProvider(
				documentationProvider);

		exporter.export(new SWTChartExporterMonitor(monitor), grammar
				.getElementName(), new StringReader(grammar.toEbnf()),
				ANTLRLanguage.SINGLETON);
	}
}
