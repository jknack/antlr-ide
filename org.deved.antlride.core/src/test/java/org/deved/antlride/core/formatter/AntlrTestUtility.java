package org.deved.antlride.core.formatter;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.antlr.runtime.ANTLRFileStream;
import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.tree.CommonTree;
import org.deved.antlride.internal.core.parser.ANTLRLexer;
import org.deved.antlride.internal.core.parser.ANTLRParser;
import org.deved.antlride.internal.core.parser.ANTLRParser.grammarDef_return;

public class AntlrTestUtility {

	public static CommonTree file(String file) throws IOException,
			RecognitionException {
		ANTLRFileStream in = new ANTLRFileStream(file);
		ANTLRLexer lexer = new ANTLRLexer(in);
		CommonTokenStream stream = new CommonTokenStream(lexer);
		ANTLRParser parser = new ANTLRParser(stream);

		grammarDef_return grammarDef = parser.grammarDef();

		return (CommonTree) grammarDef.getTree();
	}

	public static CommonTree source(String source) throws IOException,
			RecognitionException {
		ANTLRStringStream in = new ANTLRStringStream(source);
		ANTLRLexer lexer = new ANTLRLexer(in);
		CommonTokenStream stream = new CommonTokenStream(lexer);
		ANTLRParser parser = new ANTLRParser(stream);

		grammarDef_return grammarDef = parser.grammarDef();

		return (CommonTree) grammarDef.getTree();
	}

	public static List<File> collectFiles(File root, boolean recursive,
			String ext, String... exts) {
		File[] listFiles = root.listFiles();
		ArrayList<File> gfiles = new ArrayList<File>();
		Set<String> extensions = new HashSet<String>();
		extensions.add(ext);
		extensions.addAll(Arrays.asList(exts));
		if (listFiles != null) {
			for (File file : listFiles) {
				if (file.isDirectory() && recursive) {
					gfiles.addAll(collectFiles(file, recursive, ext, exts));
				} else if (extensions.contains(file.getPath().substring(
						file.getPath().lastIndexOf(".") + 1))) {
					gfiles.add(file);
				}
			}
		} else if (root.isFile()) {
			if (root.getPath().endsWith(".g")) {
				gfiles.add(root);
			}
		}
		return gfiles;
	}

	public static String readContent(File filename) {
		StringBuilder buff = new StringBuilder();
		try {
			InputStream in = new BufferedInputStream(new FileInputStream(
					filename));
			int ch = in.read();
			while (ch != -1) {
				buff.append((char) ch);
				ch = in.read();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return buff.toString();
	}
}
