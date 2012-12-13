package net.hydromatic.clapham.chart.draw2d;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import net.hydromatic.clapham.chart.ChartOptions;

public class TracePath {

	private List<Path> pathList;
	private ChartOptions options;

	private static class XYMatcher {
		int x;

		int y;

		public XYMatcher(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public boolean match(Path path) {
			return x == path.x && y == path.y;
		}
	}

	private static class X2YMatcher extends XYMatcher {

		public X2YMatcher(int x, int y) {
			super(x, y);
		}

		@Override
		public boolean match(Path path) {
			return x == ((LinePath) path).x2 && y == path.y;
		}
	}

	private interface Trace<P extends Path> {
		public Map<String, Collection<Path>> trace(P path);
	}

	private class RerunTrace implements Trace<ArcPath> {
		public Map<String, Collection<Path>> trace(ArcPath path) {
			Map<String, Collection<Path>> result = createMapResult();
			LinePath lp = null;
			ArcPath ap = null;
			Path p = path;

			int start = pathList.indexOf(p);

			// the top-right corner
			result.get("path").add(p);

			// find the vertical line between the arcs
			start = findLine(start, p.x + options.arcSize(), p.y
					+ options.arcSize() / 2);

			result.get("path").add(p = pathList.get(start));

			// the bottom-right arc
			lp = (LinePath) p;
			// override start and start from the beginning
			start = pathList.indexOf(path) + 1;
			start = findArc(start, path.x, lp.y2 - options.arcSize() / 2 - 1);

			result.get("path").add(p = pathList.get(start));

			// start from the beginning
			ap = (ArcPath) p;
			start = findLine(0, new X2YMatcher(ap.x + options.arcSize() / 2
					+ 1, ap.y + options.arcSize()));
			result.get("path").add(p = pathList.get(start));
			
			lp = (LinePath) p;
			
			return result;
		}
	}

	private class OptionTrace implements Trace<ArcPath> {

		public Map<String, Collection<Path>> trace(ArcPath path) {
			LinePath lp = null;

			Map<String, Collection<Path>> result = createMapResult();

			int start = pathList.indexOf(path);
			// the left-top arc
			result.get("path").add(pathList.get(start));
			// the vertical line
			Path p = null;
			start = findLine(start, path.x + path.width, path.y + path.height
					/ 2);
			result.get("path").add(p = pathList.get(start));
			// the left-bottom arc
			lp = (LinePath) p;

			start = findArc(start, p.x, lp.y2 - options.arcSize() / 2 - 1);

			result.get("path").add(p = pathList.get(start));
			// the long horizontal line
			ArcPath ap = (ArcPath) p;
			start = findLine(start, ap.x + ap.width / 2, ap.y + ap.height);
			result.get("path").add(p = pathList.get(start));
			// the bottom-right corner
			lp = (LinePath) p;
			start = findArc(start, lp.x2 - options.arcSize() / 2 - 1, lp.y
					- options.arcSize());
			result.get("path").add(p = pathList.get(start));
			// the vertical line between the arcs
			ap = (ArcPath) p;
			start = findLine(start, ap.x + options.arcSize(), path.y
					+ options.arcSize() / 2);
			result.get("path").add(p = pathList.get(start));
			// the top-right arc
			lp = (LinePath) p;
			start = findArc(start - 1, lp.x, lp.y - options.arcSize() / 2);
			result.get("path").add(p = pathList.get(start));
			return result;
		}
	}

	public TracePath(ChartOptions options) {
		pathList = new ArrayList<Path>();
		this.options = options;
	}

	public TracePath add(Path path) {
		pathList.add(path);
		return this;
	}

	private int findArc(int start, int x, int y) {
		return find(ArcPath.class, start, x, y);
	}

	private int findArc(int start, XYMatcher matcher) {
		return find(ArcPath.class, start, matcher);
	}

	private int findLine(int start, int x, int y) {
		return find(LinePath.class, start, x, y);
	}

	private int findLine(int start, XYMatcher matcher) {
		return find(LinePath.class, start, matcher);
	}

	private <P extends Path> int find(Class<P> type, int start, int x, int y) {
		return find(type, start, new XYMatcher(x, y));
	}

	private <P extends Path> int find(Class<P> type, int start,
			XYMatcher matcher) {
		while (start < pathList.size()) {
			Path nextp = pathList.get(start++);
			if (type.isInstance(nextp)) {
				if (matcher.match(nextp)) {
					return start - 1;
				}
			}
		}
		throw new IllegalStateException();
	}

	private Map<String, Collection<Path>> trace(LinePath path) {
		Map<String, Collection<Path>> result = createMapResult();

		int start = pathList.indexOf(path) + 1;

		Path prev = path;
		result.get("path").add(path);
		for (int i = start; i < pathList.size(); i++) {
			Path p = pathList.get(i);
			if (prev.next(p)) {
				result.get("path").add(p);
				if (!(p instanceof LabeledPath)) {
					prev = p;
				}
			} else if (prev.isAlt(p)) {
				result.get("alt").add(p);
			}
		}

		return result;
	}

	@SuppressWarnings("unchecked")
	public Map<String, Collection<Path>> trace(Path path) {
		Collections.sort(pathList);
		if (path instanceof LinePath) {
			return trace((LinePath) path);
		} else if (path instanceof ArcPath) {
			Trace[] trace = { new OptionTrace(), new RerunTrace() };
			for (int i = 0; i < trace.length; i++) {
				try {
					return trace[i].trace((ArcPath) path);
				} catch (IllegalStateException e) {
					// just ignore and try the next trace
				}
			}
			// no luck!
			return createMapResult();
		}
		throw new UnsupportedOperationException(path.toString());
	}

	private Map<String, Collection<Path>> createMapResult() {
		Map<String, Collection<Path>> result = new HashMap<String, Collection<Path>>();

		result.put("path", new LinkedHashSet<Path>());
		result.put("alt", new LinkedHashSet<Path>());

		return result;
	}

	@Override
	public String toString() {
		Collections.sort(pathList);
		return pathList.toString();
	}

	public Path first() {
		return pathList.get(0);
	}
}
