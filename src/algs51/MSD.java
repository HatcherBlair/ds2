package algs51; // section 5.1
import stdlib.*;
/* *********************************************************************************
 *  Compilation: javac MSD.java
 *  Execution:   java MSD < input.txt
 *
 *  Reads extended ASCII string from standard input and MSD radix sorts them.
 *
 *  % java MSD < shells.txt
 *  are
 *  by
 *  sea
 *  seashells
 *  seashells
 *  sells
 *  sells
 *  she
 *  she
 *  shells
 *  shore
 *  surely
 *  the
 *  the
 *
 * MAX_LENGTH = 64
 * RESULTS WITH Arrays.sort (Mergesort)
 *
 *     $ ./runjava algs51.MSD
 *       256000 [0.243 0.880]
 *       512000 [0.398 1.638]
 *      1024000 [0.851 2.138]
 *     $ ./runjava algs51.MSD
 *       256000 [0.281 1.011]
 *       512000 [0.365 1.299]
 *      1024000 [0.848 2.323]
 *     $ ./runjava algs51.MSD
 *       256000 [0.270 1.031]
 *       512000 [0.370 1.370]
 *      1024000 [0.857 2.316]
 *
 * RESULTS with MSD
 *
 *     $ ./runjava algs51.MSD
 *       256000 [0.299 3.785]
 *       512000 [0.151 0.505]
 *      1024000 [0.305 2.020]
 *     $ ./runjava algs51.MSD
 *       256000 [0.296 3.795]
 *       512000 [0.139 0.470]
 *      1024000 [0.303 2.180]
 *     $ ./runjava algs51.MSD
 *       256000 [0.294 3.769]
 *       512000 [0.141 0.480]
 *      1024000 [0.282 2.000]
 *
 * RESULTS WITH Arrays.sort (Mergesort)
 *
 *     $ ./runjava algs51.MSD
 *     dickens: [4.205]
 *     $ ./runjava algs51.MSD
 *     dickens: [4.257]
 *     $ ./runjava algs51.MSD
 *     dickens: [4.222]
 *
 * RESULTS with MSD
 *
 *     $ ./runjava algs51.MSD
 *     dickens: [7.331]
 *     $ ./runjava algs51.MSD
 *     dickens: [7.411]
 *     $ ./runjava algs51.MSD
 *     dickens: [7.523]
 *
 ***********************************************************************************/

public class MSD {
	private static final int R      = 256;   // extended ASCII alphabet size
	private static final int CUTOFF =  15;   // cutoff to insertion sort

	// sort array of strings
	public static void sort(String[] a) {
		int N = a.length;
		String[] aux = new String[N];
		sort(a, 0, N-1, 0, aux);
	}

	// return dth character of s, -1 if d = length of string
	private static int charAt(String s, int d) {
		assert d >= 0 && d <= s.length();
		if (d == s.length()) return -1;
		return s.charAt(d);
	}

	// sort from a[lo] to a[hi], starting at the dth character
	private static void sort(String[] a, int lo, int hi, int d, String[] aux) {

		// cutoff to insertion sort for small subarrays
		if (hi <= lo + CUTOFF) {
			insertion(a, lo, hi, d);
			return;
		}

		// compute frequency counts
		int[] count = new int[R+2];
		for (int i = lo; i <= hi; i++) {
			int c = charAt(a[i], d);
			count[c+2]++;
		}

		// transform counts to indicies
		for (int r = 0; r < R+1; r++)
			count[r+1] += count[r];

		// distribute
		for (int i = lo; i <= hi; i++) {
			int c = charAt(a[i], d);
			aux[count[c+1]++] = a[i];
		}

		// copy back
		for (int i = lo; i <= hi; i++)
			a[i] = aux[i - lo];


		// recursively sort for each character
		for (int r = 0; r < R; r++) {
			//sort(a, lo + count[r], lo + count[r+1] - 1, d+1, aux);

			int newLo = lo + count[r];
			int newHi = lo + count[r+1] - 1;
			if (newHi > newLo)
				sort(a, newLo, newHi, d+1, aux);
		}
	}


	// return dth character of s, -1 if d = length of string
	private static void insertion(String[] a, int lo, int hi, int d) {
		for (int i = lo; i <= hi; i++)
			for (int j = i; j > lo && less(a[j], a[j-1], d); j--)
				exch(a, j, j-1);
	}

	// exchange a[i] and a[j]
	private static void exch(String[] a, int i, int j) {
		String temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	// is v less than w, starting at character d
	private static boolean less1(String v, String w, int d) {
		assert v.substring(0, d).equals(w.substring(0, d));
		return v.substring(d).compareTo(w.substring(d)) < 0;
	}
	// is v less than w, starting at character d
	private static boolean less(String v, String w, int d) {
		assert v.substring(0, d).equals(w.substring(0, d));
		int len1 = v.length ();
		int len2 = w.length ();
		int n = Math.min(len1, len2);
		int k = 0;
		while (k < n) {
			char c1 = v.charAt (k);
			char c2 = w.charAt (k);
			if (c1 != c2) {
				return c1 < c2;
			}
			k++;
		}
		return len1 < len2;
	}


	/* *********************************************************************************
	 *  Test code
	 ***********************************************************************************/
	private static final int MAX_LENGTH = 64;
	private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789`~!@#$%^&*()_-+={[}]\\|:;'\"<>,./?";
	private static String randomString() {
		int length = 2 + StdRandom.uniform (MAX_LENGTH);
		char[] text = new char[length];
		int NUM_CHARACTERS = CHARACTERS.length ();
		for (int i = 0; i < length; i++) {
			text[i] = CHARACTERS.charAt(StdRandom.uniform (NUM_CHARACTERS));
		}
		return new String(text);
	}
	private static void exch(int[] a, int i, int j) {
		int swap = a[i];
		a[i] = a[j];
		a[j] = swap;
	}
	private static double time;
	private static void countops (int N) {
		String[] a = new String[N];
		//for (int i = 0; i < a.length; i++) a[i] = (N*i)/N;
		//StdRandom.shuffle (a);
		for (int i = 0; i < a.length; i++) a[i] = randomString ();
		//Arrays.sort (a);
		//Arrays.sort (a); for (int i = 0; i < (N-1)/2; i++) exch(a, i, N-i-1);

		Stopwatch sw = new Stopwatch ();
		sort (a);
		//Quick3way.sort (a);
		//Arrays.sort (a);
		time = sw.elapsedTime ();
	}
	public static void main(String[] args) {
		int N = 128000;
		countops (N);
		//System.exit (0);
		double prevTime = time;
		for (int i=0; i<3; i++) {
			N *= 2;
			countops (N);
			StdOut.format("%8d [%5.3f %5.3f]\n", N, time, time/prevTime);
			prevTime = time;
		}
	}
	public static void main3(String[] args) {
		String[] a = new In("data/dickens.txt").readAllStrings ();
		Stopwatch sw = new Stopwatch ();
		sort (a);
		//Arrays.sort (a);
		StdOut.format("dickens: [%5.3f]\n", sw.elapsedTime ());
	}
	public static void main2(String[] args) {
		String[] a = StdIn.readAllStrings();
		int N = a.length;
		sort(a);
		for (int i = 0; i < N; i++)
			StdOut.println(a[i]);
	}
}
