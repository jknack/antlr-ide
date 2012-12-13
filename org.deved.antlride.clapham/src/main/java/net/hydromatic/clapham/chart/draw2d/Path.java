package net.hydromatic.clapham.chart.draw2d;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.draw2d.Graphics;

public abstract class Path implements Comparable<Path> {
	int x;

	int y;

	public Path(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public abstract boolean next(Path path);

	public int compareTo(Path that) {
		int diff = this.x - that.x;
		if (diff == 0) {
			diff = this.y - that.y;
		}
		if (diff == 0) {
			Map<Class<?>, Integer> order = new HashMap<Class<?>, Integer>();
			order.put(LinePath.class, 0);
			order.put(LabeledPath.class, 0);
			order.put(ArcPath.class, 1);
			
			diff = order.get(getClass()) - order.get(that.getClass());
		}
		return diff;
	}

	abstract public void draw(Graphics graphics);

	public boolean isAlt(Path path) {
		return false;
	}
}