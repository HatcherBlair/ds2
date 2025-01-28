package algs51;

import java.util.Arrays;
import java.util.Random;
import java.util.function.Consumer;
import stdlib.*;

/*
 * Using random.nextLong ():
 * RESULTS WITH Arrays.sort (Quicksort - Bentley/McIlroy 3-way)
 *
 *  2560000 [0.388 1.672]
 *  5120000 [0.657 1.693]
 * 10240000 [1.284 1.954]
 *
 * RESULTS WITH LSD
 *
 *  2560000 [0.149 1.355]
 *  5120000 [0.276 1.852]
 * 10240000 [0.520 1.884]
 */

public class XLSDLong {
	private static int byteAt(long i, int d) {
		return (int) (i>>>(d*8)) & 0xff;
	}
	public static void sort(long[] a, boolean show) {
		int W = 8;
		int N = a.length;
		int R = 256;   // one byte
		long[] b = new long[N];
		for (int d = 0; d <= W-1; d++) {
			int[] count = new int[R+1];
			for (int i = 0; i < N; i++)
				count[byteAt(a[i], d) + 1]++;
			for (int r = 0; r < R; r++)
				count[r+1] += count[r];
			for (int i = 0; i < N; i++)
				b[count[byteAt(a[i], d)]++] = a[i];
			// copy back -- assumes that W is even!
			{ long[] tmp = b; b = a; a = tmp; }
			if (show) show(a);
		}
	}
	private static boolean isSorted(long[] a) {
		for (int i = a.length-1; i > 0; i--)
			if (a[i] < a[i-1]) return false;
		return true;
	}
	private static void show(long[] a) {
		int W = 8;
		for (int i = 0; i < a.length; i++) {
			for (int d = W-1; d >= 0; d--)
				StdOut.format ("%02x ", byteAt(a[i], d));
			StdOut.println ();
		}
		StdOut.println ();
	}



	/* *********************************************************************************
	 *  Test code
	 ***********************************************************************************/
	private static void exch(int[] a, int i, int j) {
		int swap = a[i];
		a[i] = a[j];
		a[j] = swap;
	}
	private static double time;
	private static void countops (int N, Consumer<long[]> sort) {
		Random random = new Random();
		long[] a = new long[N];
		for (int i = 0; i < a.length; i++) a[i] = random.nextLong () & 0x7fffffffffffffffL; // positive numbers

		Stopwatch sw = new Stopwatch ();
		sort.accept (a);
		time = sw.elapsedTime ();
		if (!isSorted(a)) throw new Error();
	}
	public static void run(String name, Consumer<long[]> sort) {
		StdOut.println (name);
		int N = 1280000;
		countops (N, sort);
		double prevTime = time;
		for (int i=0; i<3; i++) {
			N *= 2;
			countops (N, sort);
			StdOut.format("%8d [%5.3f %5.3f]\n", N, time, time/prevTime);
			prevTime = time;
		}
	}

	public static void main(String[] args) {
		run ("LSD", (a) -> sort (a, false));
		run ("Quicksort", (a) -> Arrays.sort (a));
	}
}
