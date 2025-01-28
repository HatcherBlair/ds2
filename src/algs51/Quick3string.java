package algs51; // section 5.1
import stdlib.*;
/* *********************************************************************************
 *  Compilation: javac Quick3string.java
 *  Execution:   java Quick3string < input.txt
 *
 *  Reads string from standard input and 3-way string quicksort them.
 *
 *  WARNING: This program assumes that the {@code substring()} method takes
 *  constant time and space. Beginning with Oracle / OpenJDK Java 7, Update 6,
 *  the substring method takes linear time and space in the size of the
 *  extracted substring. Do NOT use this code with such versions.
 *
 *  To fix: replace less() with a version that uses charAt().
 *
 *  PATCH HAS BEEN APPLIED HERE: See less() versus less1() below.
 *
 *  % java Quick3string < shell.txt
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
 *     $ ./runjava algs51.Quick3string
 *       256000 [0.282 0.876]
 *       512000 [0.384 1.362]
 *      1024000 [0.839 2.185]
 *     $ ./runjava algs51.Quick3string
 *       256000 [0.291 1.003]
 *       512000 [0.380 1.306]
 *      1024000 [0.880 2.316]
 *     $ ./runjava algs51.Quick3string
 *       256000 [0.243 0.721]
 *       512000 [0.396 1.630]
 *      1024000 [0.846 2.136]
 *
 * RESULTS with Quick3string (with less implemented using charAt)
 *
 *     $ ./runjava  algs51.Quick3string
 *       256000 [0.108 1.714]
 *       512000 [0.248 2.296]
 *      1024000 [0.567 2.286]
 *     $ ./runjava  algs51.Quick3string
 *       256000 [0.104 1.576]
 *       512000 [0.250 2.404]
 *      1024000 [0.605 2.420]
 *     $ ./runjava  algs51.Quick3string
 *       256000 [0.110 1.746]
 *       512000 [0.261 2.373]
 *      1024000 [0.589 2.257]
 *
 * RESULTS with Quick3string (with less implemented using substring)
 *
 *     $ ./runjava  algs51.Quick3string
 *       256000 [0.251 0.815]
 *       512000 [0.422 1.681]
 *      1024000 [0.893 2.116]
 *     $ ./runjava  algs51.Quick3string
 *       256000 [0.235 0.739]
 *       512000 [0.404 1.719]
 *      1024000 [0.883 2.186]
 *     $ ./runjava  algs51.Quick3string
 *       256000 [0.265 0.828]
 *       512000 [0.413 1.558]
 *      1024000 [0.886 2.145]
 *
 * RESULTS WITH Arrays.sort (Mergesort)
 *
 *     $ ./runjava algs51.Quick3string
 *     dickens: [6.855]
 *     $ ./runjava algs51.Quick3string
 *     dickens: [3.650]
 *     $ ./runjava algs51.Quick3string
 *     dickens: [4.384]
 *
 * RESULTS with Quick3string (with less implemented using charAt)
 *
 *     $ ./runjava algs51.Quick3string
 *     dickens: [2.103]
 *     $ ./runjava algs51.Quick3string
 *     dickens: [2.309]
 *     $ ./runjava algs51.Quick3string
 *     dickens: [2.136]
 *
 * RESULTS with Quick3string (with less implemented using substring)
 *
 *     $ ./runjava algs51.Quick3string
 *     dickens: [6.783]
 *     $ ./runjava algs51.Quick3string
 *     dickens: [7.296]
 *     $ ./runjava algs51.Quick3string
 *     dickens: [6.995]
 *
 ***********************************************************************************/

public class Quick3string {
	private static final int CUTOFF =  15;   // cutoff to insertion sort

	// sort the array a[] of strings
	public static void sort(String[] a) {
		// StdRandom.shuffle(a);
		sort(a, 0, a.length-1, 0);
		assert isSorted(a);
	}

	// return the dth character of s, -1 if d = length of s
	private static int charAt(String s, int d) {
		assert d >= 0 && d <= s.length();
		if (d == s.length()) return -1;
		return s.charAt(d);
	}


	// 3-way string quicksort a[lo..hi] starting at dth character
	private static void sort(String[] a, int lo, int hi, int d) {

		// cutoff to insertion sort for small subarrays
		if (hi <= lo + CUTOFF) {
			insertion(a, lo, hi, d);
			return;
		}

		int lt = lo, gt = hi;
		int v = charAt(a[lo], d);
		int i = lo + 1;
		while (i <= gt) {
			int t = charAt(a[i], d);
			if      (t < v) exch(a, lt++, i++);
			else if (t > v) exch(a, i, gt--);
			else              i++;
		}

		// a[lo..lt-1] < v = a[lt..gt] < a[gt+1..hi].
		sort(a, lo, lt-1, d);
		if (v >= 0) sort(a, lt, gt, d+1);
		sort(a, gt+1, hi, d);
	}

	// sort from a[lo] to a[hi], starting at the dth character
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
			if (c1 != c2)
				return c1 < c2;
			k++;
		}
		return len1 < len2;
	}


	// is the array sorted
	private static boolean isSorted(String[] a) {
		for (int i = 1; i < a.length; i++)
			if (a[i].compareTo(a[i-1]) < 0) return false;
		return true;
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
		for (int i = 0; i < a.length; i++) a[i] = randomString ();
		//Arrays.sort (a);
		//Arrays.sort (a); for (int i = 0; i < (N-1)/2; i++) exch(a, i, N-i-1);

		Stopwatch sw = new Stopwatch ();
		sort (a);
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
	public static void main1(String[] args) {
		String[] a = new In("data/dickens.txt").readAllStrings ();
		Stopwatch sw = new Stopwatch ();
		sort (a);
		//Arrays.sort (a);
		StdOut.format("dickens: [%5.3f]\n", sw.elapsedTime ());
	}
	public static void main2(String[] args) {
		// read in the strings from standard input
		String[] a = StdIn.readAllStrings();
		int N = a.length;

		// sort the strings
		sort(a);

		// print the results
		for (int i = 0; i < N; i++)
			StdOut.println(a[i]);
	}
}
