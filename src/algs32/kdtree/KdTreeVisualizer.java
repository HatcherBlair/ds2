package algs32.kdtree;
import algs12.Point2D;
import stdlib.*;
/* ***********************************************************************
 *  Compilation:  javac KdTreeVisualizer.java
 *  Execution:    java KdTreeVisualizer
 *  Dependencies: StdDraw.java Point2D.java KdTree.java
 *
 *  Add the points that the user clicks in the standard draw window
 *  to a kd-tree and draw the resulting kd-tree.
 *
 *************************************************************************/

public class KdTreeVisualizer {

	private static double round (double d) {
		return Math.round(d * 100.0) / 100.0;
	}
	public static void main(String[] args) {
		//args = new String [] { "src/algs32/kdtree/kdtree-input10K.txt" };
		//args = new String [] { "src/algs32/kdtree/kdtree-circle4.txt" };
		//args = new String [] { "src/algs32/kdtree/kdtree-circle10.txt" };
		//args = new String [] { "src/algs32/kdtree/kdtree-circle100.txt" };
		//args = new String [] { "src/algs32/kdtree/kdtree-horizontal8.txt" };
		//args = new String [] { "src/algs32/kdtree/kdtree-vertical7.txt" };


		KdTree kdtree = new KdTree();
		StdDraw.show(0);

		if (args.length > 0) {
			String filename = args[0];
			In in = new In(filename);
			while (!in.isEmpty()) {
				double x = in.readDouble();
				double y = in.readDouble();
				Point2D p = new Point2D(round (x), round (y));
				kdtree.insert(p);
				StdDraw.setPenRadius ();
				StdDraw.setPenColor ();
				StdDraw.rectangle (.5, .5, .5, .5);
				kdtree.draw();
				StdDraw.show(100);
			}
		} else {
			while (true) {
				if (StdDraw.mousePressed()) {
					double x = StdDraw.mouseX();
					double y = StdDraw.mouseY();
					System.out.format("%8.6f %8.6f\n", x, y);
					Point2D p = new Point2D(round (x), round (y));
					kdtree.insert(p);
					StdDraw.clear();
					StdDraw.setPenRadius ();
					StdDraw.setPenColor ();
					StdDraw.rectangle (.5, .5, .5, .5);
					kdtree.draw();
					kdtree.toGraphviz ();
				}
				StdDraw.setPenRadius ();
				StdDraw.setPenColor ();
				StdDraw.rectangle (.5, .5, .5, .5);
				kdtree.draw();
				StdDraw.show(50);
			}
		}
	}
}
