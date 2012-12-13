package org.deved.antlride.core.exporter;

import java.io.File;

import org.deved.antlride.core.model.IGrammar;

public interface Exporter {
	public void export(File out, IGrammar grammar);
}
