/******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *******************************************************************************/
package org.deved.antlride.core.selection;

import static org.junit.Assert.fail;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

import junit.framework.Assert;

import org.deved.antlride.core.build.AntlrSourceParserRepository;
import org.deved.antlride.core.formatter.AntlrTestUtility;
import org.deved.antlride.core.model.IBlock;
import org.deved.antlride.core.model.IGrammar;
import org.deved.antlride.core.model.IModelElement;
import org.deved.antlride.core.model.IRule;
import org.deved.antlride.core.model.search.Searcher;
import org.eclipse.core.runtime.CoreException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(value = Parameterized.class)
public class TestEnclosingRule {

	private int offset;

	private String ruleName;

	private File file;

	public TestEnclosingRule(File file, int offset, String ruleName) {
		this.offset = offset;
		this.file = file;
		this.ruleName = ruleName;
	}

	@Test
	public void search() {
		try {

			Assert.assertTrue(file.exists());

			System.out.printf("Grammar: %s\n", file);

			String source = AntlrTestUtility.readContent(file);

			IGrammar grammar = AntlrSourceParserRepository.parse(source);

			IRule expectedRule = grammar.findRule(ruleName);

			Assert.assertNotNull(expectedRule);

			IBlock body = expectedRule.getBody();
			
			System.out
					.printf(
							"Rule \"%s\" [start: %s; end:%s]. Body [start: %s; end:%s]\n",
							expectedRule.getElementName(), expectedRule
									.sourceStart(), expectedRule.sourceEnd(),
							body.sourceStart(), body.sourceEnd());

			System.out.printf("Searching for element at: %s\n", offset);

			IModelElement element = Searcher.search(grammar, offset);

			IRule foundRule = element.getAdapter(IRule.class);

			Assert.assertEquals(expectedRule, foundRule);

			System.out.printf("Found!!!\n\n");

		} catch (CoreException e) {
			System.err.println("Fail: " + file);
			e.printStackTrace(System.err);
			fail(e.getMessage());
		}
	}

	@Parameters
	public static Collection<Object[]> ARGUMENTS() {
		Collection<Object[]> args = new ArrayList<Object[]>();

		// ANTLR grammar
		args.add(new Object[] { new File("grammar's/ANTLR/ANTLRv3.g"), 2839,
				"grammarDef" });

		args.add(new Object[] { new File("grammar's/ANTLR/ANTLRv3.g"), 2855,
				"tokensSpec" });
		
		args.add(new Object[] { new File("grammar's/ANTLR/ANTLRv3.g"), 13600,
		"WS_LOOP" });

		return args;
	}
}
