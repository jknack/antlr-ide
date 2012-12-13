package net.hydromatic.clapham.chart.draw2d;

import org.eclipse.draw2d.Graphics;

public class LinePath extends Path {
	int x2, y2;

	public LinePath(int x1, int y1, int x2, int y2) {
		super(x1, y1);
		this.x2 = x2;
		this.y2 = y2;
	}
	
	@Override
	public boolean isAlt(Path path) {
		if (path instanceof ArcPath) {
			ArcPath arcPath = (ArcPath) path;
			return this.x == arcPath.x && this.y == arcPath.y;
		}
		return false;
	}
	
	@Override
	public boolean next(Path path) {
		if(path instanceof LabeledPath) {
			return true;
		}
		if(this.y == path.y) {
			return (path instanceof LinePath);
		} 
		return false;
	}

	@Override
	public void draw(Graphics graphics) {
		graphics.drawLine(x, y, x2, y2);
	}

	@Override
	public String toString() {
		return "LinePath{" + x + ", " + y + ", " + x2 + ", " + y2 + "}";
	}
}