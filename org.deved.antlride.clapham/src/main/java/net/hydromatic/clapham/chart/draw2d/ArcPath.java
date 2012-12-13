package net.hydromatic.clapham.chart.draw2d;

import org.eclipse.draw2d.Graphics;

public class ArcPath extends Path {
	int width;
	int height;
	int startAngle;
	int arcAngle;

	public ArcPath(int x, int y, int width, int height, int startAngle,
			int arcAngle) {
		super(x, y);
		this.width = width;
		this.height = height;
		this.startAngle = startAngle;
		this.arcAngle = arcAngle;
	}
	
	@Override
	public boolean next(Path path) {
		if (path instanceof LinePath) {
			LinePath lp = (LinePath) path;
			return (lp.x == this.x + this.width) || (lp.y == this.y + this.height);
		}
		return false;
	}
	
	@Override
	public void draw(Graphics graphics) {
		graphics.drawArc(x, y, width, height, startAngle, arcAngle);
	}

	@Override
	public String toString() {
		return "ArcPath{" + x + ", " + y + ", " + width + ", " + height
				+ ", " + startAngle + ", " + arcAngle + "}";
	}
}

