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
import org.deved.antlride.stringtemplate.internal.core.parser.STLexer;
import org.deved.antlride.stringtemplate.internal.core.parser.STParser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(value = Parameterized.class)
public class StringTemplateSourceParserTest {

	private String sourceFile;
	private char start;
	private char end;

	public StringTemplateSourceParserTest(String sourceFile, char start, char end) {
		this.sourceFile = sourceFile;
		this.start = start;
		this.end = end;
	}

	@Test
	public void parse() throws IOException, RecognitionException {
		CharStream input = new ANTLRInputStream(new FileInputStream(sourceFile));
		STLexer lexer = new STLexer(input, start, end);

		TokenStream tokenStream = new CommonTokenStream(lexer);
		STParser parser = new STParser(tokenStream);

		parser.templateAndEOF();
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

		paths.add(new Object[]{"src/examples/st/body.st", '<', '>'});
		
		paths.add(new Object[]{"src/examples/st/method.st", '<', '>'});
		
		paths.add(new Object[]{"src/examples/st/page.st", '$', '$'});
		
		paths.add(new Object[]{"src/examples/st/row.st", '$', '$'});
		
		paths.add(new Object[]{"src/examples/st/users_list.st", '$', '$'});
		
		return paths;
	}
}
