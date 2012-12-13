package org.deved.antlride.runtime;

import java.io.File;
import java.io.FilenameFilter;

import org.antlr.Tool;
import org.antlr.tool.ErrorManager;

import antlr.FileLineFormatter;

public class Tool2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			System.setErr(System.out);
			AntlrErrorListener errorListener = new AntlrErrorListener();
			FileLineFormatter.setFormatter(errorListener);
			ErrorManager.setErrorListener(errorListener);
			// launch
			Tool tool = new Tool(args);
			tool.process();
			//clean up generated lexer file
			cleanupLexerGeneratedFiles();
		} catch (Throwable t) {
			t.printStackTrace(System.out);
		}
	}

	private static void cleanupLexerGeneratedFiles() {
		String gout = System.getProperty("gout");
		File output = new File(gout);			
		File[] files = output.listFiles(new FilenameFilter() {			
			public boolean accept(File dir, String name) {
				return name.endsWith("__.g");
			}
		});
		if(files != null) {
			for(int i = 0; i < files.length; i++) {
				files[i].delete();
			}
		}	
	}

}
