package org.deved.antlride.core.exporter.ebnf;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

import org.deved.antlride.core.build.AntlrSourceParserRepository;
import org.deved.antlride.core.formatter.AntlrTestUtility;
import org.deved.antlride.core.model.IGrammar;
import org.deved.antlride.core.model.IRule;
import org.eclipse.core.runtime.CoreException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(value = Parameterized.class)
public class EbnfNotationTest {

	private File file;
	private String content;

	public EbnfNotationTest(File file, String content) {
		this.file = file;
		this.content = content;
	}
	
	@Test
	public void toEbnf() throws CoreException {
		System.out.printf("Exporting <<%s>>\n", file);
		// get a grammar
		IGrammar grammar = AntlrSourceParserRepository.parse(content);
		
		IRule[] rules = grammar.getRules();
		for (IRule rule : rules) {
			System.out.println(rule.toEbnf());
		}
	}
	
	@Parameters
	public static Collection<Object[]> data() {
		Collection<Object[]> paths = new ArrayList<Object[]>();

		// File root = new File("grammar's/java-from-v2/Javav2.g");
		File root = new File("grammar's/ANTLR/ANTLRv3.g");

		Collection<File> files = AntlrTestUtility.collectFiles(root, true, "g");
		for (File file : files) {
			paths
					.add(new Object[] { file,
							AntlrTestUtility.readContent(file) });
		}
		return paths;
	}
}
