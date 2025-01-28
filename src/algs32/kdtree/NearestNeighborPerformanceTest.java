package algs32.kdtree;
import algs12.Point2D;
import algs13.Queue;
import stdlib.*;

/*
 * If the output looks like this, then you have a bug!
 *
 * 500: kd=0.146000 brute=0.021000
 * 1000: kd=0.034000 brute=0.015000
 * 2000: kd=0.052000 brute=0.032000
 * 4000: kd=0.082000 brute=0.053000
 * 8000: kd=0.175000 brute=0.108000
 * 16000: kd=0.308000 brute=0.224000
 * 32000: kd=0.649000 brute=0.508000
 * 64000: kd=1.329000 brute=2.123000
 * 128000: kd=3.375000 brute=4.144000
 * 256000: kd=7.147000 brute=10.214000
 * 512000: kd=18.383000 brute=26.161000
 *
 * Output should look like this:
 *
 * 500: kd=0.054000 brute=0.026000
 * 1000: kd=0.036000 brute=0.018000
 * 2000: kd=0.008000 brute=0.038000
 * 4000: kd=0.011000 brute=0.063000
 * 8000: kd=0.013000 brute=0.178000
 * 16000: kd=0.019000 brute=0.274000
 * 32000: kd=0.037000 brute=0.895000
 * 64000: kd=0.050000 brute=2.099000
 * 128000: kd=0.089000 brute=4.416000
 * 256000: kd=0.082000 brute=10.716000
 * 512000: kd=3.682000 brute=27.141000
 *
 * This is also okay:
 *
 * 500: kd=0.002000 brute=0.033000
 * 1000: kd=0.001000 brute=0.014000
 * 2000: kd=0.000000 brute=0.034000
 * 4000: kd=0.000000 brute=0.098000
 * 8000: kd=0.000000 brute=0.197000
 * 16000: kd=0.001000 brute=0.407000
 * 32000: kd=0.000000 brute=1.654000
 * 64000: kd=0.000000 brute=3.241000
 * 128000: kd=0.000000 brute=6.194000
 * 256000: kd=0.000000 brute=13.980000

 */
public class NearestNeighborPerformanceTest {

	static int NUM_SIZES = 11;
	public static void main(String[] args) {

		Queue<Point2D> queue = new Queue<> ();
		for (int i=0; i<1000; i++)
			queue.enqueue(new Point2D(Math.random(), Math.random()));

		int N = 250;
		for (int count=1; count<NUM_SIZES; count++) {
			N += N;
			PointSET brute = new PointSET();
			KdTree kdtree = new KdTree();

			for (int i=0; i<N; i++) {
				Point2D p = new Point2D(Math.random(), Math.random());
				NearestNeighborCorrectnessTest.insert (kdtree, p);
				brute.insert(p);
			}
			Stopwatch sw1 = new Stopwatch ();
			for (Point2D p : queue)
				NearestNeighborCorrectnessTest.nearest (kdtree, p);
			double d1 = sw1.elapsedTime ();

			Stopwatch sw2 = new Stopwatch ();
			for (Point2D p : queue)
				brute.nearest (p);
			double d2 = sw2.elapsedTime ();

			StdOut.format ("%d: kd=%f brute=%f\n", N, d1, d2);
		}
	}
}
