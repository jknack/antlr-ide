package org.deved.antlride.internal.core.parser;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonToken;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.Token;
import org.antlr.runtime.TokenStream;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.CommonTreeNodeStream;
import org.antlr.runtime.tree.Tree;
import org.deved.antlride.core.build.AntlrBuildUnit;
import org.deved.antlride.core.build.AntlrBuildUnitRepository;
import org.deved.antlride.core.model.ASTSuffix;
import org.deved.antlride.core.model.GrammarType;
import org.deved.antlride.core.model.IAlternative;
import org.deved.antlride.core.model.IBlock;
import org.deved.antlride.core.model.ICallExpression;
import org.deved.antlride.core.model.ICallExpressionOption;
import org.deved.antlride.core.model.ICompositeStatement;
import org.deved.antlride.core.model.IGrammar;
import org.deved.antlride.core.model.IGrammarAction;
import org.deved.antlride.core.model.IGrammarScope;
import org.deved.antlride.core.model.IImport;
import org.deved.antlride.core.model.IModelElement;
import org.deved.antlride.core.model.IOption;
import org.deved.antlride.core.model.IParameter;
import org.deved.antlride.core.model.IReturn;
import org.deved.antlride.core.model.IRule;
import org.deved.antlride.core.model.IRuleAction;
import org.deved.antlride.core.model.IRuleCatch;
import org.deved.antlride.core.model.IRuleScope;
import org.deved.antlride.core.model.IScope;
import org.deved.antlride.core.model.IScopeAttribute;
import org.deved.antlride.core.model.IScopeReference;
import org.deved.antlride.core.model.ISemanticPredicate;
import org.deved.antlride.core.model.ISourceElement;
import org.deved.antlride.core.model.IStatement;
import org.deved.antlride.core.model.ISyntacticPredicate;
import org.deved.antlride.core.model.ITemplateParameter;
import org.deved.antlride.core.model.IToken;
import org.deved.antlride.core.model.ITreeStatement;
import org.deved.antlride.core.model.IRule.RuleAccessModifier;
import org.deved.antlride.core.model.ISemanticPredicate.PredicateType;
import org.deved.antlride.core.model.IStatement.EBNF;
import org.deved.antlride.core.util.AntlrTextHelper;
import org.deved.antlride.internal.core.model.AAbstractModelElement;
import org.deved.antlride.internal.core.model.AAlternative;
import org.deved.antlride.internal.core.model.AAssign;
import org.deved.antlride.internal.core.model.ABangOperator;
import org.deved.antlride.internal.core.model.ABlock;
import org.deved.antlride.internal.core.model.ACallExpression;
import org.deved.antlride.internal.core.model.ACallExpressionOption;
import org.deved.antlride.internal.core.model.AGrammar;
import org.deved.antlride.internal.core.model.AGrammarAction;
import org.deved.antlride.internal.core.model.AGrammarScope;
import org.deved.antlride.internal.core.model.AImport;
import org.deved.antlride.internal.core.model.AImports;
import org.deved.antlride.internal.core.model.ANotOperator;
import org.deved.antlride.internal.core.model.AOption;
import org.deved.antlride.internal.core.model.AOptionName;
import org.deved.antlride.internal.core.model.AOptionValue;
import org.deved.antlride.internal.core.model.AOptions;
import org.deved.antlride.internal.core.model.AParameter;
import org.deved.antlride.internal.core.model.AParameters;
import org.deved.antlride.internal.core.model.ARange;
import org.deved.antlride.internal.core.model.AReturn;
import org.deved.antlride.internal.core.model.AReturns;
import org.deved.antlride.internal.core.model.ARootOperator;
import org.deved.antlride.internal.core.model.ARule;
import org.deved.antlride.internal.core.model.ARuleAction;
import org.deved.antlride.internal.core.model.ARuleBody;
import org.deved.antlride.internal.core.model.ARuleCatch;
import org.deved.antlride.internal.core.model.ARuleFinally;
import org.deved.antlride.internal.core.model.ARuleScope;
import org.deved.antlride.internal.core.model.ARuleThrows;
import org.deved.antlride.internal.core.model.AScope;
import org.deved.antlride.internal.core.model.AScopeAttribute;
import org.deved.antlride.internal.core.model.AScopeReference;
import org.deved.antlride.internal.core.model.ASemanticPredicate;
import org.deved.antlride.internal.core.model.ASourceElement;
import org.deved.antlride.internal.core.model.AStatement;
import org.deved.antlride.internal.core.model.AStatementAction;
import org.deved.antlride.internal.core.model.ASyntacticPredicate;
import org.deved.antlride.internal.core.model.ATemplate;
import org.deved.antlride.internal.core.model.ATemplateParameter;
import org.deved.antlride.internal.core.model.AToken;
import org.deved.antlride.internal.core.model.ATokens;
import org.deved.antlride.internal.core.model.ATreeStatement;
import org.deved.antlride.internal.core.model.AVariable;
import org.deved.antlride.internal.core.parser.ANTLRParser.grammarDef_return;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.dltk.core.builder.ISourceLineTracker;
import org.eclipse.dltk.utils.TextUtils;

public class DefaultGrammarBuilder implements GrammarBuilder,
		GrammarBuilderFactory {

	private class DefaultRuleBuilder implements RuleBuilder {

		private ARule rule;

		private OptionsBuilder optionsBuilder;

		private List<IRuleScope> scopes;

		private List<IScopeReference> scopeReferences;

		private List<IRuleAction> actions;

		private List<IRuleCatch> catchs;

		public DefaultRuleBuilder() {
			rule = new ARule(grammar);
			rule.setAccessModifier(RuleAccessModifier.PUBLIC);
			scopes = new ArrayList<IRuleScope>();
			scopeReferences = new ArrayList<IScopeReference>();
			actions = new ArrayList<IRuleAction>();
			catchs = new ArrayList<IRuleCatch>();
			optionsBuilder = ExceptionSafeProxy.create(
					new DefaultOptionsBuilder(), OptionsBuilder.class);
		}

		public RuleBuilder documentation(CommonTree documentationNode) {
			rule.setDocumentation(documentationNode.getText());
			return this;
		}

		public RuleBuilder accessModifier(RuleAccessModifier modifier) {
			rule.setAccessModifier(modifier);
			return this;
		}

		public RuleBuilder scope(CommonTree scopeNode, CommonTree bodyNode) {
			ARuleScope ruleScope = new ARuleScope(rule);
			ruleScope.setSourceStart(_start(scopeNode));
			ruleScope.setSourceEnd(_end(bodyNode));
			IScopeAttribute[] attrs = createScopeAttrs(ruleScope, bodyNode);
			ruleScope.setAttributes(attrs);
			scopes.add(ruleScope);
			return this;
		}

		public RuleBuilder scopeReference(CommonTree scopeNameNode) {
			IGrammarScope grammarScope = grammar.findScope(scopeNameNode
					.getText());
			if (grammarScope == null) {
				grammarScope = new AGrammarScope(grammar, createSourceElement(
						scopeNameNode.getText(), -1, -1, false));
			}
			AScopeReference scopeReference = new AScopeReference(rule,
					grammarScope, createSourceElement(scopeNameNode.getText(),
							_start(scopeNameNode), _end(scopeNameNode), false));
			scopeReferences.add(scopeReference);
			return this;
		}

		public RuleBuilder body(BlockBuilder block) {
			block.body();
			ARuleBody body = (ARuleBody) block.build();
			rule.setBody(body);
			return this;
		}

		public RuleBuilder bodyStart(CommonTree node) {
			((AAbstractModelElement) rule.getBody())
					.setSourceStart(_start(node) + 1);
			return this;
		}

		public RuleBuilder bodyEnd(CommonTree node) {
			((AAbstractModelElement) rule.getBody())
					.setSourceEnd(_end(node) - 1);
			return this;
		}

		public RuleBuilder astSuffix(ASTSuffix suffix) {
			rule.setAstSuffix(suffix);
			return this;
		}

		public RuleBuilder name(CommonTree nameNode) {
			rule.setName(createSourceElement(nameNode));
			return this;
		}

		public IRule build() {
			rule.setRuleScopes(scopes);
			rule.setUsesScopes(scopeReferences);
			rule.setActions(actions);
			rule.setCatchs(catchs);
			this.actions.clear();
			this.scopeReferences.clear();
			this.scopes.clear();
			this.catchs.clear();
			return rule;
		}

		public RuleBuilder option(CommonTree name, CommonTree value) {
			optionsBuilder.option(name, value);
			return this;
		}

		public RuleBuilder options(CommonTree startNode, CommonTree endNode) {
			optionsBuilder.options(startNode, endNode);
			AOptions options = optionsBuilder.build();
			rule.setOptions(options);
			options.setParent(rule);
			return this;
		}

		public RuleBuilder returns(CommonTree returnsNode) {
			int sourceStart = _start(returnsNode);
			int sourceEnd = _end(returnsNode);
			String text = AntlrTextHelper.removeComments(returnsNode.getText());
			String[] args = text.split(",");
			IReturn[] rets = new IReturn[args.length];
			int start, end;
			AReturns returns = new AReturns(rule, sourceStart, sourceEnd);
			for (int i = 0; i < args.length; i++) {
				// get param name and type
				String[] arg = splitVariableDecl(args[i]);
				// create param name
				start = sourceStart + text.indexOf(arg[0]) + 1;
				end = start + arg[0].length();
				ISourceElement paramName = new ASourceElement(arg[0], start,
						end);
				ISourceElement paramType = null;
				// create param type
				if (arg[1] != null) {
					start = sourceStart + text.indexOf(arg[1]);
					end = start + arg[1].length();
					paramType = new ASourceElement(arg[1], start, end);
				}
				rets[i] = new AReturn(returns, paramName, paramType);
			}
			returns.setReturns(rets);
			returns.setText(text);
			rule.setReturns(returns);
			return this;
		}

		public RuleBuilder parameters(CommonTree paramNode) {
			AParameters params = createParameters(rule, paramNode);
			rule.setParameters(params);
			return this;
		}

		public RuleBuilder action(CommonTree nameNode, CommonTree bodyNode) {
			int start = _start(nameNode);
			int end = _end(bodyNode);
			ARuleAction action = new ARuleAction(rule,
					createSourceElement(nameNode),
					createSourceElement(bodyNode), start, end);
			actions.add(action);
			return this;
		}

		public RuleBuilder ruleStart(CommonTree startNode) {
			rule.setSourceStart(_start(startNode));
			return this;
		}

		public RuleBuilder ruleEnd(CommonTree endNode) {
			rule.setSourceEnd(_end(endNode));
			return this;
		}

		public RuleBuilder ruleCatch(CommonTree catchNode, CommonTree argNode,
				CommonTree actionNode) {
			ARuleCatch ruleCatch = new ARuleCatch(_text(argNode),
					createSourceElement(actionNode));
			catchs.add(ruleCatch);
			return this;
		}

		public RuleBuilder ruleFinally(CommonTree finallyNode,
				CommonTree actionNode) {
			ARuleFinally ruleFinally = new ARuleFinally(
					createSourceElement(actionNode));
			rule.setFinally(ruleFinally);
			return this;
		}

		public RuleBuilder ruleThrows(List<CommonTree> exceptionList) {
			String[] exceptions = new String[exceptionList.size()];
			for (int i = 0; i < exceptions.length; i++) {
				exceptions[i] = _text(exceptionList.get(i));
			}
			ARuleThrows ruleThrows = new ARuleThrows(exceptions);
			rule.setThrows(ruleThrows);
			return this;
		}
	}

	private class DefaultOptionsBuilder implements OptionsBuilder {

		private Collection<IOption> optionList;

		private AOptions options;

		public DefaultOptionsBuilder() {
			optionList = new ArrayList<IOption>();
		}

		public OptionsBuilder option(CommonTree nameNode, CommonTree valueNode) {
			ISourceElement name = createSourceElement(nameNode);
			ISourceElement value = createSourceElement(valueNode);
			int sourceStart = name.sourceStart();
			int sourceEnd = value.sourceEnd() + 1;
			AOption option = new AOption(sourceStart, sourceEnd);
			// name
			option.setName(new AOptionName(option, name));
			// value
			option.setValue(new AOptionValue(option, value));

			this.optionList.add(option);
			return this;
		}

		public OptionsBuilder options(CommonTree startNode, CommonTree endNode) {
			int sourceStart = _start(startNode);
			int sourceEnd = _end(endNode) + 1;// plus '}'
			options = new AOptions(sourceStart, sourceEnd, _line(sourceStart),
					_line(sourceEnd));
			options.setOptions(this.optionList);
			return this;
		}

		public AOptions build() {
			optionList.clear();
			return options;
		}
	}

	private class DefaultBlockBuilder extends AbstractStatementBuilder
			implements BlockBuilder {

		private OptionsBuilder optionsBuilder;

		private ABlock block;

		private boolean body = false;

		public DefaultBlockBuilder(boolean ruleBody) {
			optionsBuilder = ExceptionSafeProxy.create(
					new DefaultOptionsBuilder(), OptionsBuilder.class);
			block = ruleBody ? new ARuleBody() : new ABlock();
		}

		public BlockBuilder body() {
			body = true;
			return this;
		}

		public BlockBuilder start(CommonTree start) {
			block.setSourceStart(_start(start));
			return this;
		}

		public BlockBuilder end(CommonTree node) {
			block.setSourceEnd(_end(node));
			return this;
		}

		public BlockBuilder statement(StatementBuilder statement) {
			block.add(statement.build());
			return this;
		}

		@Override
		protected IStatement getTarget() {
			if (!body) {
				if (block.hasOptions() || block.getLeftParenthesis() != null
						|| block.getRightParenthesis() != null) {
					return block;
				}
				IStatement statement = null;
				ICompositeStatement compositeStatement = block;
				// simplify
				while (compositeStatement.size() == 1) {
					statement = compositeStatement.first();
					if (statement instanceof IAlternative) {
						compositeStatement = (ICompositeStatement) statement;
					} else {
						break;
					}
				}
				if (statement != null) {
					// found something
					if (statement instanceof ICompositeStatement) {
						if (statement instanceof ITreeStatement) {
							// simplify
							// from:: block->alternative->tree-statement
							// to:: tree-statement
							return statement;
						}
					} else {
						// simplify
						// from:: block->alternative->call
						// to:: call
						((AStatement) statement).setEbnfOperator(block
								.getEbnfOperator());
						return statement;
					}
				}
			}
			return block;
		}

		public OptionsAware option(CommonTree nameNode, CommonTree valueNode) {
			optionsBuilder.option(nameNode, valueNode);
			return this;
		}

		public OptionsAware options(CommonTree startNode, CommonTree endNode) {
			optionsBuilder.options(startNode, endNode);
			AOptions options = optionsBuilder.build();
			options.setParent(block);
			block.setOptions(options);
			return this;
		}

		public BlockBuilder lp(CommonTree node) {
			if ("(".equals(node.getText())) {
				block.setLeftParenthesis(createSourceElement(node, true));
			}
			return this;
		}

		public BlockBuilder rp(CommonTree node) {
			if (")".equals(node.getText())) {
				block.setRightParenthesis(createSourceElement(node, true));
			}
			return this;
		}
	}

	private abstract class AbstractStatementBuilder implements StatementBuilder {
		private ISourceElement not;

		private ISourceElement root;

		private ISourceElement bang;

		private EBNF ebnf = EBNF.NONE;

		public StatementBuilder bang(CommonTree commonTree) {
			bang = createSourceElement(commonTree);
			return this;
		}

		public StatementBuilder not(CommonTree commonTree) {
			not = createSourceElement(commonTree);
			return this;
		}

		public StatementBuilder root(CommonTree commonTree) {
			root = createSourceElement(commonTree);
			return this;
		}

		public StatementBuilder ebnf(CommonTree ebnfValue) {
			ebnf = EBNF.fromString(ebnfValue.getText());
			return this;
		}

		public StatementBuilder treeOp(CommonTree commonTree) {
			ASTSuffix suffix = ASTSuffix.getASTSuffix(commonTree.getText());
			if (suffix == ASTSuffix.BANG) {
				bang(commonTree);
			} else if (suffix == ASTSuffix.ROOT) {
				root(commonTree);
			}
			return this;
		}

		protected abstract IStatement getTarget();

		public final IStatement build() {
			IStatement statement = getTarget();
			// apply operators in order
			if (not != null) {
				statement = new ANotOperator(not, statement);
			}
			// ast operators
			if (bang != null) {
				statement = new ABangOperator(bang, statement);
			} else if (root != null) {
				statement = new ARootOperator(root, statement);
			}
			// ebnf
			((AStatement) statement).setEbnfOperator(ebnf);
			return statement;
		}
	}

	private class DefaultAssignBuilder extends AbstractStatementBuilder
			implements AssignBuilder {

		private AAssign assign;

		@Override
		protected IStatement getTarget() {
			return assign;
		}

		public AssignBuilder assign(CommonTree operatorNode,
				CommonTree variableNode, StatementBuilder statement) {
			AVariable variable = new AVariable(
					createSourceElement(variableNode));
			assign = new AAssign(createSourceElement(operatorNode), variable,
					statement.build());
			return this;
		}

	}

	private class DefaultSynPredBuilder extends AbstractStatementBuilder
			implements SynPredicateBuilder {

		private StatementBuilder statement;

		@Override
		protected IStatement getTarget() {
			return new ASyntacticPredicate(statement.build());
		}

		public SynPredicateBuilder predicate(StatementBuilder statement) {
			this.statement = statement;
			return this;
		}
	}

	private class DefaultTreeBuilder extends AbstractStatementBuilder implements
			TreeBuilder {

		private ATreeStatement tree;

		public DefaultTreeBuilder() {
			tree = new ATreeStatement();
		}

		public TreeBuilder statement(StatementBuilder statement) {
			tree.add(statement.build());
			return this;
		}

		@Override
		protected IStatement getTarget() {
			return tree;
		}
	}

	private class DefaultAlternativeBuilder extends AbstractStatementBuilder
			implements AlternativeBuilder {

		private AAlternative alternative;

		private List<IStatement> elements;

		public DefaultAlternativeBuilder() {
			alternative = new AAlternative();
			elements = new ArrayList<IStatement>();
		}

		public AlternativeBuilder start(CommonTree node) {
			alternative.setSourceStart(_start(node));
			return this;
		}

		public AlternativeBuilder end(CommonTree node) {
			alternative.setSourceEnd(_end(node));
			return this;
		}

		@Override
		protected IStatement getTarget() {
			Iterator<IStatement> iterator = elements.iterator();
			while (iterator.hasNext()) {
				IStatement statement = iterator.next();
				if (statement instanceof ISemanticPredicate
						&& iterator.hasNext()) {
					ASemanticPredicate semanticPredicate = (ASemanticPredicate) statement;
					semanticPredicate.setPredicate(iterator.next());
					AAlternative sempredAlt = new AAlternative();
					sempredAlt.add(semanticPredicate);

					ABlock block = new ABlock();
					block.add(sempredAlt);
					alternative.add(block);
				} else if (statement instanceof ISyntacticPredicate) {
					ASyntacticPredicate synpredicate = (ASyntacticPredicate) statement;
					AAlternative synElementAlts = new AAlternative();
					// just eat all the elements
					while (iterator.hasNext()) {
						synElementAlts.add(iterator.next());
					}
					ABlock synBlock = new ABlock();
					synBlock.add(synElementAlts);
					synpredicate.setPredicate(synBlock);

					AAlternative synAlt = new AAlternative();
					synAlt.add(synpredicate);

					ABlock block = new ABlock();
					block.add(synAlt);
					alternative.add(block);
				} else {
					alternative.add(statement);
				}
			}
			elements.clear();
			return alternative;
		}

		public AlternativeBuilder statement(StatementBuilder statement) {
			elements.add(statement.build());
			return this;
		}

		public AlternativeBuilder statement(int index,
				StatementBuilder statement) {
			elements.add(index, statement.build());
			return this;
		}

		public AlternativeBuilder rewrite(StatementBuilder statement) {
			((BlockBuilder) statement).body();
			alternative.setRewriteAlternative((IBlock) statement.build());
			return this;
		}
	}

	private class DefaultActionBuilder extends AbstractStatementBuilder
			implements ActionBuilder {

		private ISourceElement action;

		@Override
		protected IStatement getTarget() {
			return new AStatementAction(action);
		}

		public ActionBuilder action(CommonTree actionNode) {
			action = createSourceElement(actionNode);
			return this;
		}

	}

	private class DefaultSemPredBuilder extends AbstractStatementBuilder
			implements SemPredicateBuilder {

		private ISourceElement condition;

		private PredicateType predicateType;

		private StatementBuilder predicate;

		@Override
		protected IStatement getTarget() {
			return new ASemanticPredicate(predicateType, condition,
					predicate != null ? predicate.build() : null);
		}

		public DefaultSemPredBuilder condition(CommonTree operatorNode) {
			condition = createSourceElement(operatorNode);
			return this;
		}

		public SemPredicateBuilder type(PredicateType predicateType) {
			this.predicateType = predicateType;
			return this;
		}

		public SemPredicateBuilder predicate(StatementBuilder predicate) {
			this.predicate = predicate;
			return this;
		}
	}

	private class DefaultRangeBuilder extends AbstractStatementBuilder
			implements RangeBuilder {

		private ICallExpression from;

		private ICallExpression to;

		private ISourceElement operator;

		@Override
		protected IStatement getTarget() {
			return new ARange(operator, from, to);
		}

		public RangeBuilder operator(CommonTree operatorNode) {
			operator = createSourceElement(operatorNode);
			return this;
		}

		public RangeBuilder from(CommonTree fromNode) {
			from = (ICallExpression) new DefaultCallExpressionBuilder().name(
					fromNode).build();
			return this;
		}

		public RangeBuilder to(CommonTree toNode) {
			to = (ICallExpression) new DefaultCallExpressionBuilder().name(
					toNode).build();
			return this;
		}

	}

	private class DefaultCallExpressionBuilder extends AbstractStatementBuilder
			implements CallExpressionBuilder {

		private ISourceElement name;

		private boolean label = false;

		private ASTSuffix suffix = ASTSuffix.NONE;

		private AParameters parameters;

		private List<ICallExpressionOption> options;

		public DefaultCallExpressionBuilder() {
			this.options = new ArrayList<ICallExpressionOption>();
		}

		public CallExpressionBuilder name(CommonTree nameNode) {
			this.name = createSourceElement(nameNode);
			return this;
		}

		public CallExpressionBuilder parameters(CommonTree paramNode) {
			parameters = createParameters(null, paramNode);
			return this;
		}

		public CallExpressionBuilder option(CommonTree optionNode) {
			options.add(new ACallExpressionOption(
					createSourceElement(optionNode)));
			return this;
		}

		public CallExpressionBuilder option(CommonTree optionNode,
				CommonTree optionValue) {
			options.add(new ACallExpressionOption(
					createSourceElement(optionNode),
					createSourceElement(optionValue)));
			return this;
		}

		@Override
		public IStatement getTarget() {
			ACallExpression callExpression = new ACallExpression(name, suffix,
					label);
			if (parameters != null) {
				parameters.setParent(callExpression);
				callExpression.setParameters(parameters);
			}
			callExpression.setOptions(options
					.toArray(new ACallExpressionOption[options.size()]));
			return callExpression;
		}

		public CallExpressionBuilder labeled() {
			label = true;
			return this;
		}
	}

	private class DefaultTemplateBuilder extends AbstractStatementBuilder
			implements TemplateBuilder {

		private List<ITemplateParameter> parameters;

		private ATemplate template;

		public DefaultTemplateBuilder() {
			template = new ATemplate();
			parameters = new ArrayList<ITemplateParameter>();
		}

		@Override
		protected IStatement getTarget() {
			template.setParameters(parameters);
			if (!template.isInline() && template.hasParameters()) {
				// adjust the end position
				template.setSourceEnd(parameters.get(parameters.size() - 1)
						.sourceEnd());
			}
			return template;
		}

		public TemplateBuilder name(CommonTree node) {
			template.setName(createSourceElement(node));
			template.setSourceStart(_start(node));
			return this;
		}

		public TemplateBuilder action(CommonTree node) {
			ISourceElement action = createSourceElement(node);
			template.setAction(action);
			template.setSourceStart(action.sourceStart());
			template.setSourceEnd(action.sourceEnd());
			return this;
		}

		public TemplateBuilder setSimpleActionTemplate(boolean value) {
			template.setSimpleActionTemplate(value);
			return this;
		}

		public TemplateBuilder parameter(CommonTree id, CommonTree action) {
			ITemplateParameter parameter = new ATemplateParameter(
					createSourceElement(id), createSourceElement(action));
			parameters.add(parameter);
			return this;
		}

		public TemplateBuilder inlineTemplate(CommonTree node) {
			template.setInlineTemplate(createSourceElement(node));
			template.setSourceEnd(_end(node));
			return this;
		}
	}

	private AGrammar grammar;

	private Collection<IToken> tokens;

	private Collection<IScope> scopes;

	private Collection<IGrammarAction> actions;

	private Collection<IRule> rules;

	private Collection<IImport> imports;

	private String content;

	private RuleBuilder rule;

	private OptionsBuilder optionsBuilder;

	private ISourceLineTracker lineTracker;

	public DefaultGrammarBuilder(AGrammar grammar) {
		this.grammar = grammar;
		tokens = new ArrayList<IToken>();
		scopes = new ArrayList<IScope>(5);
		actions = new ArrayList<IGrammarAction>(5);
		optionsBuilder = ExceptionSafeProxy.create(new DefaultOptionsBuilder(),
				OptionsBuilder.class);
		rules = new ArrayList<IRule>();
		imports = new ArrayList<IImport>();
		this.content = grammar.getSource();
		lineTracker = TextUtils.createLineTracker(this.content);
	}

	public GrammarBuilderFactory importGrammar(CommonTree name) {
		importGrammar(name, null);
		return this;
	}

	public GrammarBuilderFactory imports(CommonTree startNode,
			CommonTree endNode) {
		AImports imports = new AImports(this.imports
				.toArray(new IImport[this.imports.size()]));
		imports.setSourceStart(_start(startNode));
		imports.setSourceEnd(_end(endNode));
		grammar.setImports(imports);
		return this;
	}

	private void processImports() {
		if (grammar.getFile() == null || !grammar.hasImports()) {
			return;
		}
		AntlrBuildUnit unit = AntlrBuildUnitRepository.create(grammar);
		Set<IFile> dependents = new HashSet<IFile>();
		IImport[] imports = grammar.getImports().getImports();
		for (IImport imp : imports) {
			try {
				IPath dependentPath = unit.getLibraryPath().append(
						imp.getElementName()).addFileExtension("g");
				IFile dependentFile = ResourcesPlugin.getWorkspace().getRoot()
						.getFile(dependentPath);
				dependents.add(dependentFile);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		// check for self reference and remove it
		Iterator<IFile> iterator = dependents.iterator();
		while (iterator.hasNext()) {
			IFile f = iterator.next();
			if (f.getFullPath().equals(grammar.getFile())) {
				// avoid self reference
				iterator.remove();
			}
		}
		unit.setDependents(dependents);
	}

	public GrammarBuilderFactory importGrammar(CommonTree name, CommonTree alias) {
		AImport imp = new AImport(createSourceElement(name),
				alias == null ? null : createSourceElement(alias));
		imports.add(imp);
		return this;
	}

	public GrammarBuilderFactory action(CommonTree ampersandNode,
			CommonTree scopeNode, CommonTree nameNode, CommonTree actionNode) {
		int sourceStart = _start(ampersandNode);
		int sourceEnd = _end(actionNode);
		ISourceElement scope = scopeNode == null ? null
				: createSourceElement(scopeNode);
		ISourceElement name = nameNode == null ? null
				: createSourceElement(nameNode);
		ISourceElement actionCode = actionNode == null ? null
				: createSourceElement(actionNode);
		IGrammarAction action = new AGrammarAction(scope, name, actionCode,
				sourceStart, sourceEnd, _line(sourceStart), _line(sourceEnd));
		this.actions.add(action);
		return this;
	}

	public GrammarBuilderFactory beginRule() {
		rule = ExceptionSafeProxy.create(new DefaultRuleBuilder(),
				RuleBuilder.class);
		return this;
	}

	public GrammarBuilderFactory endRule() {
		rules.add(rule.build());
		rule = null;
		return this;
	}

	public RuleBuilder rule() {
		return rule;
	}

	public GrammarBuilderFactory rules() {
		grammar.setRules(rules);
		return this;
	}

	public GrammarBuilderFactory actions() {
		grammar.setActions(actions);
		return this;
	}

	public GrammarBuilderFactory action(CommonTree ampersandNode,
			CommonTree nameName, CommonTree actionNode) {
		return action(ampersandNode, null, nameName, actionNode);
	}

	public GrammarBuilderFactory scope(CommonTree nameNode,
			CommonTree actionNode) {
		AGrammarScope scope = new AGrammarScope(grammar,
				createSourceElement(nameNode));
		IScopeAttribute[] attrs = createScopeAttrs(scope, actionNode);
		scope.setAttributes(attrs);
		scope.setSourceStart(_start(nameNode));
		scope.setSourceEnd(_end(actionNode));
		scopes.add(scope);
		return this;
	}

	public GrammarBuilderFactory scopes() {
		grammar.setScopes(scopes);
		return this;
	}

	public GrammarBuilder option(CommonTree name, CommonTree value) {
		optionsBuilder.option(name, value);
		return this;
	}

	public GrammarBuilder options(CommonTree startNode, CommonTree endNode) {
		optionsBuilder.options(startNode, endNode);
		AOptions options = optionsBuilder.build();
		grammar.setOptions(options);
		options.setParent(grammar);
		return this;
	}

	public GrammarBuilderFactory type(GrammarType grammarType) {
		grammar.setGrammarType(grammarType);
		return this;
	}

	public GrammarBuilderFactory name(CommonTree node) {
		grammar.setName(createSourceElement(node));
		return this;
	}

	public GrammarBuilderFactory documentation(CommonTree node) {
		grammar.setDocumentation(node.getText());
		return this;
	}

	public GrammarBuilderFactory token(CommonTree nameNode, CommonTree valueNode) {
		ISourceElement name = createSourceElement(nameNode);
		ISourceElement value = valueNode == null ? null
				: createSourceElement(valueNode);
		AToken token = new AToken(name, value);
		this.tokens.add(token);
		return this;
	}

	public GrammarBuilderFactory token(CommonTree nameNode) {
		return token(nameNode, null);
	}

	public GrammarBuilderFactory tokens(CommonTree startNode, CommonTree endNode) {
		ATokens tokens = new ATokens(grammar, _start(startNode), _end(endNode));
		tokens.setTokens(this.tokens);
		grammar.setTokens(tokens);
		return this;
	}

	protected ISourceElement createSourceElement(CommonTree node) {
		return createSourceElement(node, true);
	}

	protected ISourceElement createSourceElement(CommonTree node, boolean check) {
		CommonToken token = (CommonToken) node.getToken();
		return createSourceElement(token, check);
	}

	protected int _start(Token token) {
		return _start((CommonToken) token);
	}

	protected int _start(CommonToken token) {
		return token.getStartIndex();
	}

	protected int _start(CommonTree node) {
		return _start(node.token);
	}

	protected int _line(int offset) {
		return lineTracker.getLineNumberOfOffset(offset);
	}

	protected int _end(Token token) {
		return _end((CommonToken) token);
	}

	protected String _text(CommonTree node) {
		return _text(node.getToken());
	}

	protected String _text(Token token) {
		if (token == null)
			return "";
		String text = token.getText();
		return text == null ? "" : text;
	}

	protected int _end(CommonToken token) {
		return token.getStopIndex();
	}

	protected int _end(CommonTree node) {
		return _end(node.token);
	}

	public CallExpressionBuilder newCallExpressionBuilder() {
		return ExceptionSafeProxy.create(new DefaultCallExpressionBuilder(),
				CallExpressionBuilder.class);
	}

	public RangeBuilder newRangeBuilder() {
		return ExceptionSafeProxy.create(new DefaultRangeBuilder(),
				RangeBuilder.class);
	}

	public SemPredicateBuilder newSemPredBuilder() {
		return ExceptionSafeProxy.create(new DefaultSemPredBuilder(),
				SemPredicateBuilder.class);
	}

	public SynPredicateBuilder newSynPredBuilder() {
		return ExceptionSafeProxy.create(new DefaultSynPredBuilder(),
				SynPredicateBuilder.class);
	}

	public BlockBuilder newBlockBuilder() {
		return newBlockBuilder(false);
	}

	public BlockBuilder newBlockBuilder(boolean ruleBody) {
		return ExceptionSafeProxy.create(new DefaultBlockBuilder(ruleBody),
				BlockBuilder.class);
	}

	public ActionBuilder newActionBuilder() {
		return ExceptionSafeProxy.create(new DefaultActionBuilder(),
				ActionBuilder.class);
	}

	public AlternativeBuilder newAlternativeBuilder() {
		return ExceptionSafeProxy.create(new DefaultAlternativeBuilder(),
				AlternativeBuilder.class);
	}

	public AssignBuilder newAssignBuilder() {
		return ExceptionSafeProxy.create(new DefaultAssignBuilder(),
				AssignBuilder.class);
	}

	public TreeBuilder newTreeBuilder() {
		return ExceptionSafeProxy.create(new DefaultTreeBuilder(),
				TreeBuilder.class);
	}

	public TemplateBuilder newTemplateBuilder() {
		return ExceptionSafeProxy.create(new DefaultTemplateBuilder(),
				TemplateBuilder.class);
	}

	@SuppressWarnings("unchecked")
	public GrammarBuilder newGrammarBuilder() {
		return (GrammarBuilder) ExceptionSafeProxy.create(this,
				GrammarBuilder.class, GrammarBuilderFactory.class);
	}

	protected ISourceElement createSourceElement(CommonToken node) {
		return createSourceElement(node, node.getTokenIndex() != -1);
	}

	protected ISourceElement createSourceElement(CommonToken node, boolean check) {
		String text = node.getText();
		int start = node.getStartIndex();
		int end = node.getStopIndex() + 1;
		return createSourceElement(text, start, end, check);
	}

	protected ISourceElement createSourceElement(String text, int start, int end) {
		return createSourceElement(text, start, end, true);
	}

	protected ISourceElement createSourceElement(String text, int start,
			int end, boolean check) {
		try {
			if (check) {
				String chunk = content.substring(start, end);
				if (!text.equals(chunk)) {
					throw new IllegalArgumentException(String.format(
							"Expected %s found %s at %s..%s", text, chunk,
							start, end));
				}
			}
		} catch (StringIndexOutOfBoundsException ex) {
			ex.printStackTrace();
		} catch (IllegalArgumentException ex) {
			ex.printStackTrace();
		}
		return new ASourceElement(text, start, end);
	}

	private IScopeAttribute[] createScopeAttrs(AScope scope, CommonTree action) {
		int offset = _start(action);
		String text = action.getText();
		if (text.startsWith("{")) {
			text = text.substring(1);
		}
		if (text.endsWith("}")) {
			text = text.substring(0, text.length() - 1);
		}
		scope.setText(text);
		text = AntlrTextHelper.removeComments(text);
		String[] attrs = split(text, ",;");
		int start, end;
		IScopeAttribute[] attributes = new IScopeAttribute[attrs.length];
		int startIndex = 0;
		for (int i = 0; i < attrs.length; i++) {
			String[] attr = splitVariableDecl(attrs[i]);
			// create param name
			start = offset + text.indexOf(attr[0], startIndex) + 1;
			end = start + attr[0].length();
			ISourceElement attrName = createSourceElement(attr[0], start, end);
			ISourceElement attrType = null;
			// create param type
			if (attr[1] != null) {
				int startType = offset + text.indexOf(attr[1], startIndex) + 1;
				end = startType + attr[1].length();
				attrType = createSourceElement(attr[1], startType, end);
			}
			attributes[i] = new AScopeAttribute(scope, attrName, attrType);
			startIndex = start - offset + attr[0].length();
		}
		return attributes;
	}

	private AParameters createParameters(IModelElement parent,
			CommonTree paramNode) {
		String text = AntlrTextHelper.removeComments(AntlrTextHelper
				.trimBracket(_text(paramNode)));
		int pstart = _start(paramNode);
		int pend = _end(paramNode);
		if (pstart == pend - 1) {
			pend = pstart + text.length();
		} else {
			// eat the "["
			pstart++;
		}
		ISourceElement action = createSourceElement(text, pstart, pend);
		String[] args = split(text, ",");
		IParameter[] params = new IParameter[args.length];
		int start, end;
		for (int i = 0; i < args.length; i++) {
			// get param name and type
			String[] arg = splitVariableDecl(args[i]);
			// create param name
			start = action.sourceStart() + text.indexOf(arg[0]);
			end = start + arg[0].length();
			ISourceElement paramName = createSourceElement(arg[0], start, end);
			ISourceElement paramType = null;
			// create param type
			if (arg[1] != null) {
				start = action.sourceStart() + text.indexOf(arg[1]);
				end = start + arg[1].length();
				paramType = createSourceElement(arg[1], start, end);
			}
			AParameter parameter = new AParameter(paramName, paramType);
			params[i] = parameter;
		}
		AParameters parameters = new AParameters(parent, action);
		parameters.setParemeters(params);
		return parameters;
	}

	private String[] split(String text, String separators) {
		StringTokenizer tokenizer = new StringTokenizer(text, separators);
		Collection<String> tokens = new ArrayList<String>(5);
		while (tokenizer.hasMoreTokens()) {
			String token = tokenizer.nextToken().trim();
			if (token.length() > 0) {
				tokens.add(token);
			}
		}
		String[] result = new String[tokens.size()];
		result = tokens.toArray(result);
		tokens.clear();
		return result;
	}

	private String[] splitVariableDecl(String text) {
		return splitVariableDecl(text, " \t\n\r\f");
	}

	private String[] splitVariableDecl(String text, String separators) {
		StringTokenizer tokenizer = new StringTokenizer(text, separators);
		String[] arg = { null, null };
		int tokenCount = tokenizer.countTokens();
		if (tokenCount > 1) {
			arg[1] = tokenizer.nextToken().trim();
			arg[0] = tokenizer.nextToken().trim();
		} else if (tokenCount == 1) {
			arg[0] = tokenizer.nextToken().trim();
		}
		return arg;
	}

	@Override
	public String toString() {
		return grammar.toString();
	}

	public IGrammar build() {
		processImports();

		this.actions.clear();
		this.content = null;
		this.rules.clear();
		this.scopes.clear();
		this.tokens.clear();
		this.imports.clear();
		return grammar;
	}

	public static void main(String[] args) throws IOException,
			RecognitionException {
		String file = "grammar's/ANTLR/ANTLRv3.g";
		String content = getContent(file);
		ANTLRLexer lexer = new ANTLRLexer(new ANTLRStringStream(content));

		TokenStream tokens = new CommonTokenStream(lexer);

		ANTLRParser parser = new ANTLRParser(tokens);

		grammarDef_return grammarDef = parser.grammarDef();

		CommonTreeNodeStream nodes = new CommonTreeNodeStream(
				(Tree) grammarDef.tree);
		nodes.setTokenStream(tokens);
		ANTLRTreeWalker walker = new ANTLRWalker(nodes);
		DefaultGrammarBuilder builder = new DefaultGrammarBuilder(new AGrammar(
				content));
		walker.grammarDef(builder);
	}

	public static String getContent(String file) throws IOException {
		StringBuilder builder = new StringBuilder();
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(
				file));
		int readed = bis.read();
		while (readed != -1) {
			builder.append((char) readed);
			readed = bis.read();
		}
		bis.close();
		return builder.toString();
	}
}
