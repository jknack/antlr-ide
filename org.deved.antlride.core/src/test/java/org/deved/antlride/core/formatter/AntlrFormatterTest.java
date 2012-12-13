/******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *******************************************************************************/
package org.deved.antlride.core.formatter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;

import org.antlr.runtime.tree.CommonTree;
import org.deved.antlride.core.build.AntlrSourceParserRepository;
import org.deved.antlride.core.model.IGrammar;
import org.deved.antlride.internal.core.parser.TreePrinter;
import org.deved.antlride.internal.core.parser.TreeToString;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(value = Parameterized.class)
public class AntlrFormatterTest {

	private static final String OUTPUT = "target/formatter";

	private File file;

	private String content;

	private int id;

	private AntlrFormatterOptions options;

	public AntlrFormatterTest(int id, File filename, String content,
			AntlrFormatterOptions options) {
		this.file = filename;
		this.id = id;
		this.content = content;
		this.options = options;
	}

	private static PrintWriter createWriter(String filename, String ext)
			throws FileNotFoundException {
		return new PrintWriter(OUTPUT + "/" + filename + ext);
	}

	private static void write(String filename, String ext, String content)
			throws FileNotFoundException {
		PrintWriter writer = createWriter(filename, ext);
		writer.append(content);
		writer.flush();
		writer.close();
	}

	@Test
	public void format() {
		try {
			System.out.printf("\n%s) Formatter<<%s>>. Source %s\n", id,
					options, file);
			// get a grammar
			IGrammar grammar = AntlrSourceParserRepository.parse(content);
			String source = new String(grammar.getSource());

			AntlrFormatter formatter = new AntlrFormatter(options);

			formatter.accept(grammar);

			String formattedSource = formatter.content();

			CommonTree tree = AntlrTestUtility.source(content);
			CommonTree formattedTree = AntlrTestUtility.source(formattedSource);

			// provide an String representation of the Syntax Tree
			TreePrinter printer = new TreePrinter(false);
			printer.setToStringStrategy(TreeToString.NO_WHITESPACES_STRATEGY);

			printer.render(tree, createWriter(file.getName(), "c.tree"));
			printer.render(formattedTree,
					createWriter(file.getName(), "d.tree"));

			// Source Syntax Tree
			String stringTree = printer.renderAsString(tree, "Grammar");

			// Formatted Syntax Tree
			String formattedStringTree = printer.renderAsString(formattedTree,
					"Grammar");

			write(file.getName(), "a.source", source);

			write(file.getName(), "b.source", formattedSource);
			// Compare both
			// match the Abstract Syntax Tree

			assertEquals(file.getName(), stringTree, formattedStringTree);
		} catch (Throwable e) {
			System.err.println("Fail: " + file);
			e.printStackTrace(System.err);
			fail(e.getMessage());
		}
	}

	@BeforeClass
	public static void setUp() throws Exception {
//		AntlrFormatter.DEBUG = true;

		File output = new File(OUTPUT);
		output.mkdirs();
		Collection<File> files = AntlrTestUtility.collectFiles(output, false,
				"source", "tree");
		System.out.println("Cleaning output:");
		for (File file : files) {
			System.out.println("\tDeleting: " + file);
			file.delete();
		}
	}

	@After
	public void tearDown() throws Exception {
	}

	@Parameters
	public static Collection<Object[]> getPaths() {
		Collection<Object[]> paths = new ArrayList<Object[]>();

//		File root = new File("grammar's/ANTLR/v4/ANTLRParserBug.g");
		 File root = new File("grammar's");

		Collection<File> files = AntlrTestUtility.collectFiles(root, true, "g");
		int i = 0;
		for (File file : files) {
			paths.add(new Object[] { i++, file,
					AntlrTestUtility.readContent(file),
					AntlrFormatterOptions.DEFAULT });
		}
		return paths;
	}
}
