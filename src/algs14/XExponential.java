package algs14;
import java.util.Arrays;
import stdlib.*;
/* ***********************************************************************
 *  Compilation:  javac Exponential.java
 *  Execution:    java Exponential N
 *
 *  A program with exponential running time. Read in N integers
 *  and find the subset whose sum is closest to 0.
 *
 *  Limitations
 *  -----------
 *     - we assume no sum of N integers will overflow a int
 *
 *************************************************************************/

public class XExponential {

	public static double timeTrial(int N, int[] a) {
		Stopwatch s = new Stopwatch();

		// find subset closest to 0
		long best = Long.MAX_VALUE;
		int stop = 1 << N;
		for (int n = 1; n < stop; n++)  {
			long sum = 0;
			for (int i = 0; i < N; i++) {
				if (((n >> i) & 1) == 1) sum = sum + a[i];
			}
			if (Math.abs(sum) < Math.abs(best)) best = sum;
		}
		//System.out.println(best);
		return s.elapsedTime();
	}


	public static void main(String[] args) {
		int MAX = 28;
		int[] a = new int[MAX];
		for (int i = 0; i < MAX; i++)
			a[i] = StdRandom.uniform (Integer.MIN_VALUE, Integer.MAX_VALUE);
		StdOut.println(Arrays.toString (a));

		StdOut.format("%7s %7s %4s\n", "size", "time", "ratio");
		double prev = timeTrial(10, a);
		for (int N = 1; N < MAX; N += 1) {
			double curr = timeTrial(N, a);
			StdOut.format("%7d %7.2f %4.2f\n", N, curr, curr / prev);
			prev = curr;
		}
	}
}
