package algs32.kdtree;
import algs12.Point2D;
import algs13.Queue;
import stdlib.*;

/*
 * Example output:
 *
 * 500: kd=0.041000 brute=0.035000
 * 1000: kd=0.021000 brute=0.022000
 * 2000: kd=0.007000 brute=0.050000
 * 4000: kd=0.008000 brute=0.073000
 * 8000: kd=0.018000 brute=0.156000
 * 16000: kd=0.018000 brute=0.304000
 * 32000: kd=0.052000 brute=0.730000
 * 64000: kd=0.095000 brute=2.359000
 * 128000: kd=0.163000 brute=4.735000
 * 256000: kd=0.260000 brute=11.390000
 * 512000: kd=1.499000 brute=27.822000
 */
public class RangeSearchPerformanceTest {

	static int NUM_SIZES = 12;
	public static void main(String[] args) {
		Queue<RectHV> queue = new Queue<> ();
		while (queue.size () < 1000) {
			double x1 = Math.random ();
			double x2 = Math.random ();
			double y1 = Math.random ();
			double y2 = Math.random ();
			double xmin = Math.min (x1, x2);
			double xmax = Math.max (x1, x2);
			double ymin = Math.min (y1, y2);
			double ymax = Math.max (y1, y2);
			RectHV rect = new RectHV (xmin, ymin, xmax, ymax);
			if (rect.width () * rect.height () > 0.01)
				continue;  // rectangle is too large
			queue.enqueue(rect);
		}
		int N = 250;
		for (int count=1; count<NUM_SIZES; count++) {
			N += N;
			PointSET brute = new PointSET();
			KdTree kdtree = new KdTree();

			for (int i=0; i<N; i++) {
				Point2D p = new Point2D(Math.random(), Math.random());
				RangeSearchCorrectnessTest.insert (kdtree, p);
				brute.insert(p);
			}
			Stopwatch sw1 = new Stopwatch ();
			for (RectHV r : queue)
				RangeSearchCorrectnessTest.range (kdtree, r);
			double d1 = sw1.elapsedTime ();

			Stopwatch sw2 = new Stopwatch ();
			for (RectHV r : queue)
				brute.range (r);
			double d2 = sw2.elapsedTime ();

			StdOut.format ("%d: kd=%f brute=%f\n", N, d1, d2);
		}
	}
}
