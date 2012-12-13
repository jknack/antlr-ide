package org.deved.antlride.core.model.ast;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import org.deved.antlride.core.model.IAlternative;
import org.deved.antlride.core.model.IBlock;
import org.deved.antlride.core.model.ICallExpression;
import org.deved.antlride.core.model.IGrammar;
import org.deved.antlride.core.model.IModelElement;
import org.deved.antlride.core.model.INotOperator;
import org.deved.antlride.core.model.IRange;
import org.deved.antlride.core.model.IRule;
import org.deved.antlride.core.model.ISemanticPredicate;
import org.deved.antlride.core.model.IStatement;
import org.deved.antlride.core.model.ISyntacticPredicate;
import org.deved.antlride.core.model.ISemanticPredicate.PredicateType;

public class ToEbnfVisitor extends AAbstractModelElementVisitor {

	private static final String RP = ")";

	private static final String LP = "(";

	private static final String SPACE = " ";

	private static final String RANGE = "..";

	private static final String QUOTE = "\'";

	private static final String ASSIGN = " ::= ";

	private static final String ALT = " | ";

	private static final String PREDICATE = "??";

	private static final String NOT = "~";

	private static final String PREDICATE_OP = "=>";

	private StringBuilder buff;

	private Map<String, String> ebnfMap;

	private Set<IModelElement> visited;

	private boolean joinRules = false;

	public ToEbnfVisitor(IRule rule) {
		this(new HashSet<IModelElement>(), new HashMap<String, String>());
		buff.append(rule.getElementName()).append(ASSIGN);
		accept(rule);
	}

	private ToEbnfVisitor(Set<IModelElement> visited,
			Map<String, String> ebnfMap) {
		buff = new StringBuilder();
		this.visited = visited;
		this.ebnfMap = ebnfMap;
	}

	@Override
	public void accept(IModelElement node) {
		visited.add(node);
		super.accept(node);
	}

	public String toEbnf() {
		return buff.toString();
	}

	@Override
	public boolean visitSemanticPredicate(ISemanticPredicate node) {
		if (node.hasPredicate()) {
			// ignore validating semantic predicates
			String elementName = quoteChars(normalize(node.getCondition()
					.getText()));
			buff.append(PREDICATE).append(QUOTE).append(elementName);
			if (node.getPredicateType() == PredicateType.GATED_SEMPRED) {
				buff.append(PREDICATE_OP);
			}
			buff.append(QUOTE).append(" (");
		}
		return true;
	}

	private String normalize(String text) {
		StringBuilder buff = new StringBuilder();
		StringTokenizer tokenizer = new StringTokenizer(text);
		while (tokenizer.hasMoreElements()) {
			buff.append(tokenizer.nextToken()).append(SPACE);
		}
		buff.setLength(buff.length() - SPACE.length());
		return buff.toString().trim();
	}

	@Override
	public boolean visitNotOperator(INotOperator node) {
		buff.append(NOT).append(LP);
		return true;
	}

	@Override
	public void endvisitNotOperator(INotOperator node) {
		buff.append(RP);
	}

	@Override
	public void endvisitSemanticPredicate(ISemanticPredicate node) {
		if (node.hasPredicate()) {
			buff.append(RP);
		}
	}

	@Override
	public boolean visitSyntacticPredicate(ISyntacticPredicate node) {
		ToEbnfVisitor visitor = new ToEbnfVisitor(visited, ebnfMap);
		visitor.accept(node.getCondition());
		buff.append(PREDICATE).append(QUOTE).append(
				quoteChars(visitor.toEbnf())).append("=>").append(QUOTE);
		return true;
	}

	private String quoteChars(String text) {
		return text.trim().replace("'", "\\'");
	}

	@Override
	public boolean visitSyntacticPredicateCondition(IStatement node) {
		return false;
	}

	@Override
	public void endvisitSyntacticPredicate(ISyntacticPredicate node) {
	}

	@Override
	public boolean visitRange(IRange node) {
		buff.append(QUOTE)
				.append(removeQuotes(node.getLeft().getElementName())).append(
						RANGE).append(
						removeQuotes(node.getRight().getElementName())).append(
						QUOTE).append(node.getEbnfOperator().description())
				.append(SPACE);
		return false;
	}

	private String removeQuotes(String statement) {
		return statement.replace("\'", "");
	}

	@Override
	public boolean visitCallExpression(ICallExpression node) {
		if (joinRules) {
			// TODO: this need more work
			if (node.isStringLiteralCall() || node.isLexerRuleCall()) {
				buff.append(node.getElementName());
				buff.append(node.getEbnfOperator().description()).append(SPACE);
			} else {
				IGrammar grammar = node.getAdapter(IGrammar.class);
				IRule rule = grammar.findRule(node.getElementName());
				if (rule == null || visited.contains(rule)) {
					// just a token ref
					buff.append(node.getElementName());
					buff.append(node.getEbnfOperator().description()).append(
							SPACE);
				} else {
					String ebnf = ebnfMap.get(node.getElementName());
					if (ebnf == null) {
						ToEbnfVisitor ebnfVisitor = new ToEbnfVisitor(visited,
								ebnfMap);
						ebnfVisitor.accept(rule);
						ebnf = ebnfVisitor.toEbnf();
						ebnfMap.put(node.getElementName(), ebnf);
					}
					buff.append(SPACE).append(ebnf).append(SPACE);
				}
			}
		} else {
			buff.append(node.getElementName());
			buff.append(node.getEbnfOperator().description()).append(SPACE);
		}
		return true;
	}

	@Override
	public boolean visitBlock(IBlock node) {
		visitBlock(node, false);
		return true;
	}

	private void visitBlock(IBlock node, boolean ruleBody) {
		if (!ruleBody) {
			buff.append(LP);
		}
	}

	@Override
	public boolean visitAlternative(IAlternative node) {
		boolean treatAsEmpty = false;
		// check for validating semantic predicates
		// their are excluded from the ebnf notation
		boolean validatingPredicates = true;
		for (int i = 0; i < node.size(); i++) {
			IStatement statement = node.get(i);
			if (statement instanceof ISemanticPredicate) {
				ISemanticPredicate predicate = (ISemanticPredicate) statement;
				validatingPredicates = validatingPredicates
						&& predicate.isValidating();
			} else {
				validatingPredicates = false;
			}
		}
		treatAsEmpty = validatingPredicates;
		return !treatAsEmpty;
	}

	@Override
	public void endvisitAlternative(IAlternative node) {
		buff.append(ALT);
	}

	private void endvisitBlock(IBlock node, boolean ruleBody) {
		if (buff.toString().endsWith(ALT)) {
			buff.setLength(buff.length() - ALT.length());
		}
		if (!ruleBody) {
			buff.append(RP).append(node.getEbnfOperator().description())
					.append(SPACE);
		}
		super.endvisitBlock(node);
	}

	@Override
	public void endvisitBlock(IBlock node) {
		endvisitBlock(node, false);
	}

	@Override
	public void endvisitRuleBody(IBlock node) {
		endvisitBlock(node, true);
	}

	@Override
	public boolean visitRuleBody(IBlock node) {
		visitBlock(node, true);
		return true;
	}

	public boolean visitRewriteAlternative(IAlternative node) {
		return false;
	}

	@Override
	public boolean visitRewriteBlock(IBlock node) {
		return false;
	}
}
