package algs32.kdtree;
import algs12.Point2D;
import stdlib.*;

/* ***********************************************************************
 *  Compilation:  javac RectHV.java
 *  Execution:    java RectHV
 *  Dependencies: Point2D.java
 *
 *  Implementation of 2D axis-aligned rectangle.
 *
 *************************************************************************/

public class RectHV {
	private final double xmin, ymin;   // minimum x- and y-coordinates
	private final double xmax, ymax;   // maximum x- and y-coordinates

	// construct the axis-aligned rectangle (xmin, ymin) x (xmax, ymax)
	public RectHV(double xmin, double ymin, double xmax, double ymax) {
		this.xmin = xmin;
		this.ymin = ymin;
		this.xmax = xmax;
		this.ymax = ymax;
		if (xmax < xmin || ymax < ymin) {
			throw new IllegalArgumentException("Invalid rectangle: " + this);
		}
	}

	// accessor methods for 4 coordinates
	public double xmin() { return xmin; }
	public double ymin() { return ymin; }
	public double xmax() { return xmax; }
	public double ymax() { return ymax; }

	// width and height of rectangle
	public double width()  { return xmax - xmin; }
	public double height() { return ymax - ymin; }

	// does this axis-aligned rectangle intersect that one?
	public boolean intersects(RectHV that) {
		return this.xmax >= that.xmin && this.ymax >= that.ymin
				&& that.xmax >= this.xmin && that.ymax >= this.ymin;
	}

	// draw this axis-aligned rectangle
	public void draw() {
		StdDraw.line(xmin, ymin, xmax, ymin);
		StdDraw.line(xmax, ymin, xmax, ymax);
		StdDraw.line(xmax, ymax, xmin, ymax);
		StdDraw.line(xmin, ymax, xmin, ymin);
	}

	// distance from p to closest point on this axis-aligned rectangle
	public double distanceTo(Point2D p) {
		return Math.sqrt(this.distanceSquaredTo(p));
	}

	// distance squared from p to closest point on this axis-aligned rectangle
	public double distanceSquaredTo(Point2D p) {
		double dx = 0.0, dy = 0.0;
		if      (p.x() < xmin) dx = p.x() - xmin;
		else if (p.x() > xmax) dx = p.x() - xmax;
		if      (p.y() < ymin) dy = p.y() - ymin;
		else if (p.y() > ymax) dy = p.y() - ymax;
		return dx*dx + dy*dy;
	}

	// does this axis-aligned rectangle contain p?
	public boolean contains(Point2D p) {
		return (p.x() >= xmin) && (p.x() <= xmax)
				&& (p.y() >= ymin) && (p.y() <= ymax);
	}

	// are the two axis-aligned rectangles equal?
	public boolean equals(Object y) {
		if (y == this) return true;
		if (y == null) return false;
		if (y.getClass() != this.getClass()) return false;
		final RectHV that = (RectHV) y;
		if (this.xmin != that.xmin) return false;
		if (this.ymin != that.ymin) return false;
		if (this.xmax != that.xmax) return false;
		if (this.ymax != that.ymax) return false;
		return true;
	}

	private int hashCode = 0;
	public int hashCode() {
		int hCode = hashCode;
		if (hCode != 0)
			return hCode;
		int result = 17;
		result = 37*result + Double.hashCode (xmin);
		result = 37*result + Double.hashCode (xmax);
		result = 37*result + Double.hashCode (ymin);
		result = 37*result + Double.hashCode (ymax);
		hashCode = result;
		return result;
	}

	// return a string representation of this axis-aligned rectangle
	public String toString() {
		return "(" + xmin + ", " + ymin + ") x (" + xmax + ", " + ymax + ")";
	}

}
