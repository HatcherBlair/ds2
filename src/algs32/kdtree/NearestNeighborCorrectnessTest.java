package algs32.kdtree;
import algs12.Point2D;
import algs13.Queue;
import stdlib.*;

public class NearestNeighborCorrectnessTest {

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

	protected static Point2D nearest (KdTree kdtree, Point2D target) {
		if (!CATCH_EXCEPTIONS) {
			return kdtree.nearest (target);
		} else {
			try {
				return kdtree.nearest (target);
			} catch (Throwable e) {
				if (passed) {
					passed = false;
					e.printStackTrace ();
				}
				return new Point2D (666, 666);
			}
		}
	}
	private static boolean showInsertionException = true;
	protected static boolean insert (KdTree kdtree, Point2D p) {
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

		Queue<Point2D> queue = new Queue<> ();
		for (int i=0; i<NUM_TARGETS; i++)
			queue.enqueue(new Point2D(random(1000), random(1000)));

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
				point: for (Point2D p : queue) {
					numTested ++;
					Point2D b = brute.nearest(p);
					Point2D k = nearest (kdtree, p);
					if (b==null) {
						if (k!=null) {
							printError (treeSize, brute, kdtree, p);
							if (STOP_AFTER_FIRST_FAILURE) break test; else continue point;
						}
					} else if (k==null) {
						printError (treeSize, brute, kdtree, p);
						if (STOP_AFTER_FIRST_FAILURE) break test; else continue test;
					} else if (p.distanceTo(b) - p.distanceTo (k) != 0.0) {
						printError (treeSize, brute, kdtree, p);
						if (STOP_AFTER_FIRST_FAILURE) break test; else continue point;
					}
					numPassed ++;
				}
			}
			treeSize += (treeSize==0) ? 1 : treeSize;
			if (numsize % 4==0) numPossible *= 10;
		}
		StdOut.format ("#NearestNeighbor %s: %d/%d passed, %d/%d trees created without thrown exception\n", passed ? "passed" : "failed", numPassed, numTested, numTreesCreated, numTreesAttempted);

	}
	private static void printError (int treeSize, PointSET brute, KdTree kdtree, Point2D p) {
		if (passed) {
			passed = false;
			StdOut.println ("Error!");
			//StdOut.println ("  treeSize should be " + treeSize);
			//if (brute.size() != treeSize) StdOut.println ("  duplicate points");
			StdOut.println ("  PointSET         = " + brute);
			StdOut.println ("  KdTree           = " + kdtree);
			StdOut.println ("  target           = " + p);
			StdOut.println ("  PointSET nearest = " + brute.nearest(p));
			StdOut.println ("  KdTree nearest   = " + nearest(kdtree, p));
			if (SHOW_TREE_ON_FAILURE) {
				kdtree.toGraphviz ();
				kdtree.draw ();
			}
		}
	}
}
