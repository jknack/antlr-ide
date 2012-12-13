package org.deved.antlride.gunit.internal.ui.text.highlighting.rules;

import org.deved.antlride.common.ui.text.rules.AntlrAbstractRule;
import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.Token;

public class GUnitTestSuiteHighlight extends AntlrAbstractRule {

	public GUnitTestSuiteHighlight(IToken successToken) {
		super(successToken);
	}

	@Override
	protected IToken doEvaluate(ICharacterScanner scanner) {
		consumeWord(scanner);
		String text = getTextReaded();
		if(text.length() > 0) {
			//try: testname:
			consumeWS(scanner);
			consumeOne(scanner);
			text = getTextReaded();
			unread(scanner, 1);
			if(text.equals(":")) {
				return fSuccessToken;
			}
			//try: testname walks
			int l1 = fCharsReaded;
			consumeWS(scanner);
			consumeWord(scanner);
			text = getTextReaded();
			if(text.equals("walks")) {
				unread(scanner, fCharsReaded - l1);
				return fSuccessToken;
			}
		}
		unread(scanner);
		return Token.UNDEFINED;
	}

}
