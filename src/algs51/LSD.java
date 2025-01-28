package algs51; // section 5.1
import stdlib.*;
/* *********************************************************************************
 *  Compilation: javac LSD.java
 *  Execution:   java LSD < input.txt
 *
 *  LSD radix sort an array of extended ASCII strings, each of length W.
 *
 *  % java LSD < words3.txt
 *  all
 *  bad
 *  bed
 *  bug
 *  dad
 *  ...
 *  yes
 *  yet
 *  zoo
 *
 * LENGTH = 8
 * RESULTS WITH Arrays.sort (Mergesort)
 *
 *     $ ./runjava algs51.LSD
 *       256000 [0.239 0.879]
 *       512000 [0.303 1.268]
 *      1024000 [0.750 2.475]
 *     $ ./runjava algs51.LSD
 *       256000 [0.224 0.783]
 *       512000 [0.302 1.348]
 *      1024000 [0.763 2.526]
 *     $ ./runjava algs51.LSD
 *       256000 [0.261 0.839]
 *       512000 [0.306 1.172]
 *      1024000 [0.738 2.412]
 *
 * RESULTS with LSD
 *
 *     $ ./runjava algs51.LSD
 *       256000 [0.463 1.564]
 *       512000 [0.990 2.138]
 *      1024000 [2.293 2.316]
 *     $ ./runjava algs51.LSD
 *       256000 [0.442 1.811]
 *       512000 [0.956 2.163]
 *      1024000 [2.251 2.355]
 *     $ ./runjava algs51.LSD
 *       256000 [0.440 1.753]
 *       512000 [0.958 2.177]
 *      1024000 [2.278 2.378]
 *
 * LENGTH = 4
 * RESULTS WITH Arrays.sort (Mergesort)
 *
 *     $ #ARRAYS 4
 *     $ ./runjava algs51.LSD
 *       256000 [0.259 0.893]
 *       512000 [0.345 1.332]
 *      1024000 [0.762 2.209]
 *     $ ./runjava algs51.LSD
 *       256000 [0.267 0.837]
 *       512000 [0.953 3.569]
 *      1024000 [1.625 1.705]
 *     $ ./runjava algs51.LSD
 *       256000 [0.252 1.016]
 *       512000 [0.964 3.825]
 *      1024000 [1.629 1.690]
 *
 * RESULTS with LSD
 *
 *     $ ./runjava algs51.LSD
 *       256000 [0.185 1.350]
 *       512000 [0.844 4.562]
 *      1024000 [1.862 2.206]
 *     $ ./runjava algs51.LSD
 *       256000 [0.179 1.366]
 *       512000 [0.813 4.542]
 *      1024000 [1.827 2.247]
 *     $ ./runjava algs51.LSD
 *       256000 [0.178 1.348]
 *       512000 [0.815 4.579]
 *      1024000 [1.848 2.267]
 *
 ***********************************************************************************/

public class LSD {

	// LSD radix sort
	public static void sort(String[] a, int W) {
		int N = a.length;
		int R = 256;   // extend ASCII alphabet size
		String[] aux = new String[N];

		for (int d = W-1; d >= 0; d--) {
			// sort by key-indexed counting on dth character

			// compute frequency counts
			int[] count = new int[R+1];
			for (int i = 0; i < N; i++)
				count[a[i].charAt(d) + 1]++;

			// compute cumulates
			for (int r = 0; r < R; r++)
				count[r+1] += count[r];

			// move data
			for (int i = 0; i < N; i++)
				aux[count[a[i].charAt(d)]++] = a[i];

			// copy back
			for (int i = 0; i < N; i++)
				a[i] = aux[i];
		}
	}


	/* *********************************************************************************
	 *  Test code
	 ***********************************************************************************/
	private static final int LENGTH = 4;
	private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789`~!@#$%^&*()_-+={[}]\\|:;'\"<>,./?";
	private static String randomString() {
		char[] text = new char[LENGTH];
		int NUM_CHARACTERS = CHARACTERS.length ();
		for (int i = 0; i < LENGTH; i++) {
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
		sort (a, LENGTH);
		//Merge.sort (a);
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
	public static void main2(String[] args) {
		StdIn.fromFile ("data/words3.txt");
		String[] a = StdIn.readAllStrings();
		int N = a.length;

		// check that strings have fixed length
		int W = a[0].length();
		for (int i = 0; i < N; i++)
			assert a[i].length() == W : "Strings must have fixed length";

		// sort the strings
		sort(a, W);

		// print results
		for (int i = 0; i < N; i++)
			StdOut.println(a[i]);
	}
}
