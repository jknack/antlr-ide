/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.core.interpreter;

import org.antlr.runtime.EarlyExitException;
import org.antlr.runtime.FailedPredicateException;
import org.antlr.runtime.MismatchedNotSetException;
import org.antlr.runtime.MismatchedSetException;
import org.antlr.runtime.MismatchedTokenException;
import org.antlr.runtime.MismatchedTreeNodeException;
import org.antlr.runtime.MissingTokenException;
import org.antlr.runtime.NoViableAltException;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.Token;
import org.antlr.runtime.UnwantedTokenException;
import org.antlr.tool.Grammar;
import org.deved.antlride.core.model.IGrammar;
import org.deved.antlride.core.model.evaluation.EvalElementFactory;
import org.deved.antlride.core.model.evaluation.IEvalElement;
import org.deved.antlride.core.model.evaluation.IResultEvalElement;

class AntlrEvalElementBuilder extends
		org.antlr.runtime.debug.BlankDebugEventListener {

	private int inDecision = 0;

	private IEvalElement element;

	private IEvalElement current;

	private String resultDescription;

	private IGrammar g;

	private Grammar nativeG;

	public AntlrEvalElementBuilder(IGrammar g) {
		this.g = g;

	}

	public void commence(Grammar nativeG) {
		this.nativeG = nativeG;
	}

	public IResultEvalElement getResult() {
		IResultEvalElement resultEvalElement = EvalElementFactory
				.createResultEvalElement(g, resultDescription != null,
						resultDescription, element);
		current = null;
		return resultEvalElement;
	}

	@Override
	public void enterDecision(int decisionNumber) {
		super.enterDecision(decisionNumber);
		inDecision++;
	}

	@Override
	public void exitDecision(int decisionNumber) {
		inDecision--;
		super.exitDecision(decisionNumber);
	}

	@Override
	public void enterRule(String s, String ruleName) {
		createRuleEvalElement(ruleName);
	}

	@Override
	public void exitRule(String s, String ruleName) {
		if (current != null)
			current = current.getParent();
	}

	@Override
	public void consumeToken(Token token) {
		if (inDecision == 0) {
			String tokenDisplayName = getTokenName(token.getType());
			boolean isLexerRule = tokenDisplayName.charAt(0) != '\'';
			if (isLexerRule) {
				createRuleEvalElement(tokenDisplayName);
			}
			createTokenEvalElement(token.getText(), token.getLine(), token
					.getCharPositionInLine());
			if (isLexerRule) {
				current = current.getParent();
			}
		}
	}

	@Override
	public void recognitionException(RecognitionException e) {
		String hdr = getErrorHeader(e);
		String msg = getErrorMessage(e);
		createExceptionEvalElement(e.getClass().getSimpleName(), hdr + " "
				+ msg, e.line, e.charPositionInLine);
	}

	private void createRuleEvalElement(String elementName) {
		IEvalElement evalElement = EvalElementFactory.createRuleEvalElement(g,
				current, elementName);
		addElement(evalElement);
		// set current node
		current = evalElement;
	}

	private void createTokenEvalElement(String text, int line, int column) {
		IEvalElement evalElement = EvalElementFactory.createTokenEvalElement(g,
				current, text, line, column);
		addElement(evalElement);
	}

	private void createExceptionEvalElement(String exception, String text,
			int line, int column) {
		IEvalElement evalElement = EvalElementFactory
				.createExceptionEvalElement(g, current, exception, text, line,
						column);
		this.resultDescription = text;
		addElement(evalElement);
	}

	private void addElement(IEvalElement evalElement) {
		if (element == null) {
			element = evalElement;
		}
	}

	/**
	 * How should a token be displayed in an error message? The default is to
	 * display just the text, but during development you might want to have a
	 * lot of information spit out. Override in that case to use t.toString()
	 * (which, for CommonToken, dumps everything about the token). This is
	 * better than forcing you to override a method in your token objects
	 * because you don't have to go modify your lexer so that it creates a new
	 * Java type.
	 */
	private String getTokenErrorName(Token t) {
		if (t == null) {
			return "UNKNOW";
		}
		String s = t.getText();
		if (s == null) {
			if (t.getType() == Token.EOF) {
				s = "<EOF>";
			} else {
				s = "<" + t.getType() + ">";
			}
		}
		s = s.replaceAll("\n", "\\\\n");
		s = s.replaceAll("\r", "\\\\r");
		s = s.replaceAll("\t", "\\\\t");
		return "'" + s + "'";
	}

	@Override
	public void terminate() {
		nativeG = null;
	}

	private String getTokenName(int type) {
		return nativeG.getTokenDisplayName(type);
	}

	/**
	 * What is the error header, normally line/character position information?
	 * 
	 * @see org.antlr.runtime.BaseRecognizer#getErrorHeader(RecognitionException)
	 * */
	private String getErrorHeader(RecognitionException e) {
		return "line " + e.line + ":" + e.charPositionInLine;
	}

	/**
	 * What error message should be generated for the various exception types?
	 * 
	 * Not very object-oriented code, but I like having all error message
	 * generation within one method rather than spread among all of the
	 * exception classes. This also makes it much easier for the exception
	 * handling because the exception classes do not have to have pointers back
	 * to this object to access utility routines and so on. Also, changing the
	 * message for an exception type would be difficult because you would have
	 * to subclassing exception, but then somehow get ANTLR to make those kinds
	 * of exception objects instead of the default. This looks weird, but trust
	 * me--it makes the most sense in terms of flexibility.
	 * 
	 * For grammar debugging, you will want to override this to add more
	 * information such as the stack frame with getRuleInvocationStack(e,
	 * this.getClass().getName()) and, for no viable alts, the decision
	 * description and state etc...
	 * 
	 * Override this to change the message generated for one or more exception
	 * types.
	 * 
	 * @see org.antlr.runtime.BaseRecognizer#getErrorMessage(RecognitionException,
	 *      String[])
	 */
	private String getErrorMessage(RecognitionException e) {
		String msg = e.getMessage();
		if (e instanceof UnwantedTokenException) {
			UnwantedTokenException ute = (UnwantedTokenException) e;
			String tokenName = "<unknown>";
			if (ute.expecting == Token.EOF) {
				tokenName = "EOF";
			} else {
				tokenName = getTokenName(ute.expecting);
			}
			msg = "extraneous input "
					+ getTokenErrorName(ute.getUnexpectedToken())
					+ " expecting " + tokenName;
		} else if (e instanceof MissingTokenException) {
			MissingTokenException mte = (MissingTokenException) e;
			String tokenName = "<unknown>";
			if (mte.expecting == Token.EOF) {
				tokenName = "EOF";
			} else {
				tokenName = getTokenName(mte.expecting);
			}
			msg = "missing " + tokenName + " at " + getTokenErrorName(e.token);
		} else if (e instanceof MismatchedTokenException) {
			MismatchedTokenException mte = (MismatchedTokenException) e;
			String tokenName = "<unknown>";
			if (mte.expecting == Token.EOF) {
				tokenName = "EOF";
			} else {
				tokenName = getTokenName(mte.expecting);
			}
			msg = "mismatched input " + getTokenErrorName(e.token)
					+ " expecting " + tokenName;
		} else if (e instanceof MismatchedTreeNodeException) {
			MismatchedTreeNodeException mtne = (MismatchedTreeNodeException) e;
			String tokenName = "<unknown>";
			if (mtne.expecting == Token.EOF) {
				tokenName = "EOF";
			} else {
				tokenName = getTokenName(mtne.expecting);
			}
			msg = "mismatched tree node: " + mtne.node + " expecting "
					+ tokenName;
		} else if (e instanceof NoViableAltException) {
			// NoViableAltException nvae = (NoViableAltException)e;
			// for development, can add
			// "decision=<<"+nvae.grammarDecisionDescription+">>"
			// and "(decision="+nvae.decisionNumber+") and
			// "state "+nvae.stateNumber
			msg = "no viable alternative at input "
					+ getTokenErrorName(e.token);
		} else if (e instanceof EarlyExitException) {
			// EarlyExitException eee = (EarlyExitException)e;
			// for development, can add "(decision="+eee.decisionNumber+")"
			msg = "required (...)+ loop did not match anything at input "
					+ getTokenErrorName(e.token);
		} else if (e instanceof MismatchedSetException) {
			MismatchedSetException mse = (MismatchedSetException) e;
			msg = "mismatched input " + getTokenErrorName(e.token)
					+ " expecting set " + mse.expecting;
		} else if (e instanceof MismatchedNotSetException) {
			MismatchedNotSetException mse = (MismatchedNotSetException) e;
			msg = "mismatched input " + getTokenErrorName(e.token)
					+ " expecting set " + mse.expecting;
		} else if (e instanceof FailedPredicateException) {
			FailedPredicateException fpe = (FailedPredicateException) e;
			msg = "rule " + fpe.ruleName + " failed predicate: {"
					+ fpe.predicateText + "}?";
		}
		return msg;
	}
}
