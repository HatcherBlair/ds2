package algs34;
import stdlib.*;
import java.util.HashSet;
/* ***********************************************************************
 * Floating point is a pain!
 *************************************************************************/

public final class XBadPoint {
	static class BadPoint {
		private final double x;
		private final double y;
		public BadPoint(double x, double y) { this.x = x; this.y = y; }
		public String toString() { return "(" + x + ", " + y + ")";  }

		public boolean equals(Object other) {
			if (other == this) return true;
			if (other == null || other.getClass() != this.getClass()) return false;
			BadPoint that = (BadPoint) other;
			if (this.x != that.x) return false;
			if (this.y != that.y) return false;
			return true;
		}
		public int hashCode() {
			int h = 17;
			h = 31*h + Double.hashCode(x);
			h = 31*h + Double.hashCode(y);
			return h;
		}
	}

	public static void main(String[] args) {
		BadPoint a = new BadPoint(0.0, 0.0);
		BadPoint b = new BadPoint(0.0, 0.0);
		BadPoint e = new BadPoint(0.0,-0.0);
		StdOut.format("a = %s [hashcode=%d]\n", a, a.hashCode ());
		StdOut.format("b = %s [hashcode=%d]\n", b, b.hashCode ());
		StdOut.format("e = %s [hashcode=%d]\n", e, e.hashCode ());

		HashSet<BadPoint> set = new HashSet<>();
		set.add(a);
		StdOut.println("Added a");
		StdOut.println("a == b:      " + (a == b));
		StdOut.println("a.equals(b): " + (a.equals(b)));
		StdOut.println("contains b:  " + set.contains(b));
		StdOut.println("a == e:      " + (a == e));
		StdOut.println("a.equals(e): " + (a.equals(e)));
		StdOut.println("contains e:  " + set.contains(e));
	}
}
