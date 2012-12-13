package org.deved.antlride.stringtemplate.core.parser;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.TokenStream;
import org.deved.antlride.stringtemplate.internal.core.parser.GroupLexer;
import org.deved.antlride.stringtemplate.internal.core.parser.GroupParser;
import org.deved.antlride.stringtemplate.internal.core.parser.STLexer;
import org.deved.antlride.stringtemplate.internal.core.parser.STParser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(value = Parameterized.class)
public class StringTemplateGroupSourceParserTest {

	private String sourceFile;

	public StringTemplateGroupSourceParserTest(String sourceFile) {
		this.sourceFile = sourceFile;
	}

	@Test
	public void parse() throws IOException, RecognitionException {
		System.out.println("Parsing: " + sourceFile);
		CharStream input = new ANTLRInputStream(new FileInputStream(sourceFile));
		GroupLexer lexer = new GroupLexer(input);

		TokenStream tokenStream = new CommonTokenStream(lexer);
		GroupParser parser = new GroupParser(tokenStream);

		parser.group();
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Parameters
	public static Collection<Object[]> getPaths() {
		Collection<Object[]> paths = new ArrayList<Object[]>();

		paths.add(new Object[]{"src/examples/stg/antlr/Java.stg"});
		paths.add(new Object[]{"src/examples/stg/antlr/ST.stg"});
		paths.add(new Object[]{"src/examples/stg/antlr/AST.stg"});
		paths.add(new Object[]{"src/examples/stg/antlr/ASTDbg.stg"});
		paths.add(new Object[]{"src/examples/stg/antlr/ASTParser.stg"});
		paths.add(new Object[]{"src/examples/stg/antlr/ASTTreeParser.stg"});
		paths.add(new Object[]{"src/examples/stg/antlr/Bytecode.stg"});
		paths.add(new Object[]{"src/examples/stg/antlr/Dbg.stg"});
		return paths;
	}
}
