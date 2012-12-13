package net.hydromatic.clapham.chart.draw2d;

import org.eclipse.draw2d.Graphics;

public class LabeledPath extends Path {

	private String label;

	public LabeledPath(String name, int x, int y) {
		super(x, y);
		this.label = name;
	}
	
	@Override
	public boolean next(Path path) {
		// TODO Auto-generated method stub
		return path instanceof LinePath;
	}

	@Override
	public void draw(Graphics graphics) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public String toString() {
		return "LabeledPath{" + label + ", " + x + ", " + y + "}";
	}
}
