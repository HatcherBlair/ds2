package algs32.kdtree;
import java.util.TreeSet;
import algs12.Point2D;
import algs13.Queue;
import stdlib.*;

public class RangeSearchCorrectnessTest {

	static int NUM_TARGETS = 1000;
	static int NUM_SIZES = 12;
	static int NUM_TESTS = 200;
	static int NUM_POSSIBLE_INIT = 1;
	static int TREE_SIZE_INIT = 0;
	static boolean ALLOW_DUPLICATES = true;
	static boolean SHOW_TREE_ON_FAILURE = true;
	static boolean STOP_AFTER_FIRST_FAILURE = true;
	static boolean CATCH_EXCEPTIONS = false;
	private static boolean passed = true;

	protected static Iterable<Point2D> range(KdTree kdtree, RectHV target) {
		if (!CATCH_EXCEPTIONS) {
			return kdtree.range (target);
		} else {
			try {
				return kdtree.range (target);
			} catch (Throwable e) {
				if (passed) {
					passed = false;
					e.printStackTrace ();
				}
				return null;
			}
		}
	}
	private static boolean showInsertionException = true;
	protected static boolean insert(KdTree kdtree, Point2D p) {
		if (!CATCH_EXCEPTIONS) {
			kdtree.insert (p);
			return true;
		} else {
			try {
				kdtree.insert (p);
				return true;
			} catch (Throwable e) {
				if (showInsertionException) {
					showInsertionException = false;
					e.printStackTrace ();
				}
				passed = false;
				return false;
			}
		}
	}
	private static double random(int numPossible) {
		return StdRandom.uniform (numPossible)/(double)numPossible;
	}
	public static void main(String[] args) {
		//StdRandom.setSeed (0); // uncomment to get the same results over and over

		Queue<RectHV> queue = new Queue<> ();
		while (queue.size () < NUM_TARGETS) {
			double x1 = random (1000);
			double x2 = random (1000);
			double y1 = random (1000);
			double y2 = random (1000);
			double xmin = Math.min (x1, x2);
			double xmax = Math.max (x1, x2);
			double ymin = Math.min (y1, y2);
			double ymax = Math.max (y1, y2);
			RectHV rect = new RectHV (xmin, ymin, xmax, ymax);
			if (rect.width () * rect.height () > 0.25)
				continue;  // rectangle is too large
			queue.enqueue(rect);
		}

		// treeSize and numPossible vary each time around the test loop
		// trying small trees with few possible values for points to start
		// doubling the treeSize each time
		// keeping numPossible a power of 10 so that decimal fractions print nicely
		int numPossible = NUM_POSSIBLE_INIT;
		int treeSize = TREE_SIZE_INIT;
		int numTested = 0;
		int numPassed = 0;
		int numTreesAttempted = 0;
		int numTreesCreated = 0;
		test: for (int numsize=0; numsize<NUM_SIZES; numsize++) {
			StdOut.format ("trying treeSize %d\n", treeSize);
			for (int numtest=0; numtest<NUM_TESTS; numtest++) {
				PointSET brute = new PointSET();
				KdTree kdtree = new KdTree();

				boolean treeCreated = true;
				for (int i=0; i<treeSize; i++) {
					Point2D p = new Point2D(random (numPossible), random (numPossible));
					if (ALLOW_DUPLICATES || !brute.contains (p)) {
						if (!insert(kdtree, p)) treeCreated = false;
						brute.insert(p);
					}
				}
				numTreesAttempted ++;
				if (treeCreated) numTreesCreated ++;
				rect: for (RectHV r : queue) {
					numTested ++;
					// check to see that the results are the same
					TreeSet<Point2D> set = new TreeSet<> ();
					for (Point2D p : brute.range(r))
						set.add (p);
					Iterable<Point2D> kdtreeRange = range (kdtree, r);
					if (kdtreeRange == null) {
						printError (treeSize, brute, kdtree, r);
						if (STOP_AFTER_FIRST_FAILURE) break test; else continue rect;
					}
					for (Point2D p : kdtreeRange)
						if (! set.remove (p)) {
							if (passed) StdOut.format ("KdTree range has extra %s\n", p);
							printError (treeSize, brute, kdtree, r);
							if (STOP_AFTER_FIRST_FAILURE) break test; else continue rect;
						}
					for (Point2D p : set) {
						if (passed) StdOut.format ("KdTree range missed %s\n", p);
						printError (treeSize, brute, kdtree, r);
						if (STOP_AFTER_FIRST_FAILURE) break test; else continue rect;
					}
					numPassed ++;
				}
			}
			treeSize += (treeSize==0) ? 1 : treeSize;
			if (numsize % 4==0) numPossible *= 10;
		}
		StdOut.format ("#RangeSearch %s: %d/%d passed, %d/%d trees created without thrown exception\n", passed ? "passed" : "failed", numPassed, numTested, numTreesCreated, numTreesAttempted);
	}
	private static void printError (int treeSize, PointSET brute, KdTree kdtree, RectHV r) {
		if (passed) {
			passed = false;
			StdOut.println ("Error!");
			StdOut.println ("  treeSize should be " + treeSize);
			//if (brute.size() != treeSize) StdOut.println ("  duplicate points");
			StdOut.println ("  PointSET       = " + brute);
			StdOut.println ("  KdTree         = " + kdtree);
			StdOut.println ("  target         = " + r);
			StdOut.println ("  PointSET range = " + brute.range(r));
			StdOut.println ("  KdTree range   = " + range (kdtree, r));
			if (SHOW_TREE_ON_FAILURE) {
				kdtree.toGraphviz ();
				kdtree.draw ();
			}
		}
	}
}
