package algs13;
import stdlib.*;
import algs12.Point2D;
/* ***********************************************************************
 *  Compilation:  javac Grid.java
 *  Execution:    java Grid N d
 *  Dependencies: Queue.java
 *
 *  Generate N random Euclidean points in unit box (coorinates
 *  between 0 and 1) and print out all pairs that are at
 *  distance <= d.
 *
 *************************************************************************/

public class XGrid {

	public static void main(String[] args) {
		int N    = Integer.parseInt(args[0]);
		double d = Double.parseDouble(args[1]);

		int G = (int) (Math.ceil(1.0 / d));    // rows and columns in grid

		// initialize data structure
		@SuppressWarnings("unchecked")
		final
		Queue<Point2D>[][] grid = new Queue[G+2][G+2];
		for (int i = 0; i <= G+1; i++)
			for (int j = 0; j <= G+1; j++)
				grid[i][j] = new Queue<>();

		// generate random points and check if any previous point <= d
		for (int n = 0; n < N; n++) {
			double x = Math.random();
			double y = Math.random();
			Point2D p  = new Point2D(x, y);
			int row = 1 + (int) (x * G);
			int col = 1 + (int) (y * G);
			for (int i = row-1; i <= row+1; i++) {
				for (int j = col-1; j <= row+1; j++) {
					for (Point2D q : grid[i][j])
						if (p.distanceTo(q) <= d)
							StdOut.println(p + " <--> " + q);
				}
			}
			grid[row][col].enqueue(p);
		}
	}
}

