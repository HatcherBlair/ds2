package algs32.kdtree;
import algs12.Point2D;
import stdlib.*;
/* ***********************************************************************
 *  Compilation:  javac NearestNeighborVisualizer.java
 *  Execution:    java NearestNeighborVisualizer input.txt
 *  Dependencies: PointSET.java KdTree.java Point2D.java In.java StdDraw.java
 *
 *  Read points from a file (specified as a command-line argument) and
 *  draw to standard draw. Highlight the closest point to the mouse.
 *
 *  The nearest neighbor according to the brute-force algorithm is drawn
 *  in red; the nearest neighbor using the kd-tree algorithm is drawn in blue.
 *
 *************************************************************************/

public class NearestNeighborVisualizer {

	public static void main(String[] args) {
		//args = new String [] { "src/algs32/kdtree/kdtree-input10K.txt" };
		//args = new String [] { "src/algs32/kdtree/kdtree-circle4.txt" };
		args = new String [] { "src/algs32/kdtree/kdtree-circle10.txt" };
		//args = new String [] { "src/algs32/kdtree/kdtree-circle100.txt" };
		//args = new String [] { "src/algs32/kdtree/kdtree-horizontal8.txt" };
		//args = new String [] { "src/algs32/kdtree/kdtree-vertical7.txt" };

		String filename = args[0];
		In in = new In(filename);

		StdDraw.show(0);

		// initialize the two data structures with point from standard input
		PointSET brute = new PointSET();
		KdTree kdtree = new KdTree();
		while (!in.isEmpty()) {
			double x = in.readDouble();
			double y = in.readDouble();
			Point2D p = new Point2D(x, y);
			kdtree.insert(p);
			brute.insert(p);
		}

		while (true) {

			// the location (x, y) of the mouse
			double x = StdDraw.mouseX();
			double y = StdDraw.mouseY();
			Point2D query = new Point2D(x, y);
			// query = new Point2D(.5, .5);
			Point2D b = brute.nearest(query);
			Point2D k = kdtree.nearest (query);
			double diff = query.distanceTo(b) - query.distanceTo (k);
			if (diff != 0.0) StdOut.format ("diff = %f\n", diff);

			// draw all of the points
			StdDraw.clear();
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.setPenRadius(.01);
			brute.draw();
			query.draw();

			// draw in red the nearest neighbor according to the brute-force algorithm
			StdDraw.setPenRadius(.03);
			StdDraw.setPenColor(StdDraw.RED);
			b.draw();

			// draw in blue the nearest neighbor according to the kd-tree algorithm
			StdDraw.setPenRadius(.02);
			StdDraw.setPenColor(StdDraw.BLUE);
			k.draw();

			StdDraw.show(0);
			StdDraw.show(40);
		}
	}
}
