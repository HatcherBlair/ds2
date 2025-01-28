package algs11;
import stdlib.*;
public class PlaygroundPerformance {
	/* Return number of times 5.0 occurs in the list */
	public static int numFives (double[] a) {
		int result = 0;
		for (int i=0; i<a.length; i++)
			if (a[i] == 5.0)
				result++;   
		return result;
	}
	public static double timeTrial(int N) {
		double[] a = ArrayGenerator.doubleRandom(N, 10);
		Stopwatch s = new Stopwatch();
		numFives(a);
		return s.elapsedTime();
	}

	private static final int MIN =     1_000_000;
	private static final int MAX = 1_000_000_000;
	public static void main(String[] args) {
		double prev = timeTrial(MIN);
		for (int N = MIN*2; N<=MAX; N += N) {
			double time = timeTrial(N);
			StdOut.format("%,13d %10.3f %10.3f\n", N, time, time/prev);
			prev = time;
		}
	}
}