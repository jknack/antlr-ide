package org.deved.antlride.internal.core.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.antlr.runtime.tree.Tree;

public interface TreeToString {

	TreeToString NO_WHITESPACES_STRATEGY = new TreeToString() {

		public String toString(Tree node) {
			String text = node.getText();
			if (text == null)
				return "";
			Matcher matcher = Pattern.compile("\\s+").matcher(text);
			StringBuffer buff = new StringBuffer();
			while (matcher.find()) {
				matcher.appendReplacement(buff, "");
			}
			matcher.appendTail(buff);
			return buff.toString();
		}
	};

	String toString(Tree node);
}
