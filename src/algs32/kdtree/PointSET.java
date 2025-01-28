package algs32.kdtree;
import stdlib.*;
import algs12.Point2D;
import algs13.Queue;
//import algs35.SET;

public class PointSET {
	private final java.util.TreeSet<Point2D> set;

	/* construct an empty set of points */
	public PointSET() {
		set = new java.util.TreeSet<> ();
	}

	/* is the set empty? */
	public boolean isEmpty() {
		return set.isEmpty ();
	}

	/* number of points in the set */
	public int size() {
		return set.size ();
	}

	/* add the point p to the set (if it is not already in the set) */
	public void insert(Point2D p) {
		if (p == null)
			throw new IllegalArgumentException ();
		set.add (p);
	}

	/* does the set contain the point p? */
	public boolean contains(Point2D target) {
		return set.contains (target);
	}

	/* draw all of the points to standard draw */
	public void draw() {
		for (Point2D p : set)
			p.draw ();
	}

	/* all points in the set that are inside the rectangle */
	public Iterable<Point2D> range(RectHV target) {
		final Queue<Point2D> queue = new Queue<> ();
		for (Point2D p : set)
			if (target.contains (p)) queue.enqueue (p);
		return queue;
	}


	/* a nearest neighbor in the set to p; null if set is empty */
	public Point2D nearest(Point2D target) {
		Point2D champ = null;
		double distance = Double.POSITIVE_INFINITY;
		for (Point2D q : set) {
			final double d = target.distanceTo (q);
			if (d <= distance) {
				champ = q;
				distance = d;
			}
		}
		return champ;
	}

	public String toString () {
		StringBuilder sb = new StringBuilder ();
		for (Point2D key : set)
			sb.append (key + " ");
		return sb.toString ();
	}

	/* unit testing of the methods */
	public static void main(String[] args) {
		PointSET brute = new PointSET ();
		brute.insert (new Point2D (0.04,0.02));
		brute.insert (new Point2D (0.83,0.19));
		brute.insert (new Point2D (0.81,0.26));
		brute.insert (new Point2D (0.95,0.13));
		brute.insert (new Point2D (0.02,0.65));
		brute.insert (new Point2D (0.70,0.94));
		brute.insert (new Point2D (0.41,0.89));
		StdOut.println (brute.size ());
		StdOut.println (brute);
	}
}
