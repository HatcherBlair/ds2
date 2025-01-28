package algs11;
import stdlib.*;
import java.util.Arrays;

/* ***********************************************************************
 *  Compilation:  javac BinarySearch.java
 *  Execution:    java BinarySearch whitelist.txt input.txt
 *  Data files:   http://algs4.cs.princeton.edu/11model/tinyW.txt
 *                http://algs4.cs.princeton.edu/11model/tinyT.txt
 *                http://algs4.cs.princeton.edu/11model/largeW.txt
 *                http://algs4.cs.princeton.edu/11model/largeT.txt
 *
 *  % java BinarySearch tinyW.txt tinyT.txt
 *  50
 *  99
 *  13
 *
 *  % java BinarySearch largeW.txt largeT.txt
 *  499569
 *  984875
 *  295754
 *  207807
 *  140925
 *  161828
 *  [3,675,966 total values]
 *
 *************************************************************************/
public class BinarySearch {

	// This is a simple rank function
	// It works fine for small arrays
	public static int rank0(double key, double[] a) {
		for (int i = 0; i < a.length; i++)
			if (a[i] == key) return i;
		return -1;
	}

	// Here is more efficient version, coded three ways
	// precondition: array a[] is sorted
	public static int rank(double key, double[] a) {
		return rankHelper1 (key, a, 0, a.length - 1);
	}

	// Here is  a standard
	public static int rankHelper1(double key, double[] a, int lo, int hi) {
		// key is in a[lo..hi] or not present.
		if (lo > hi) return -1;
		int mid = lo + (hi - lo) / 2;
		if      (key < a[mid]) return rankHelper1 (key, a, lo, mid - 1);
		else if (key > a[mid]) return rankHelper1 (key, a, mid + 1, hi);
		else return mid;
	}

	// This is the same, but uses a loop
	public static int rankHelper2(double key, double[] a, int lo, int hi) {
		while (lo <= hi) {
			// key is in a[lo..hi] or not present.
			int mid = lo + (hi - lo) / 2;
			if      (key < a[mid]) hi = mid - 1;
			else if (key > a[mid]) lo = mid + 1;
			else return mid;
		}
		return -1;
	}

	// This is the same, but written with explicit variables
	public static int rankHelper3(double key, double[] a, int lo, int hi) {
		if (lo > hi) return -1;
		int result;
		int mid = lo + (hi - lo) / 2;
		if (key < a[mid])
			result = rankHelper3 (key, a, lo, mid - 1);
		else if (key > a[mid])
			result = rankHelper3 (key, a, mid + 1, hi);
		else
			result = mid;
		return result;
	}

	public static void testTrace(String whitelistFile, int key) {
		// get whitelist from file and sort
		double[] whitelist = new In(whitelistFile).readAllDoubles();
		Arrays.sort(whitelist);
		StdOut.println(Arrays.toString(whitelist));

		int rank = rank(key, whitelist);
		if (rank >= 0) {
			StdOut.println(key + " is in the list");
		} else {
			StdOut.println(key + " is NOT in the list");
		}
	}

	public static void testInteractive(String whitelistFile) {
		// get whitelist from file and sort
		double[] whitelist = new In(whitelistFile).readAllDoubles();
		Arrays.sort(whitelist);
		StdOut.println(Arrays.toString(whitelist));

		// read from the console; type Control-D or Control-Z at the beginning of a blank line to stop.
		StdOut.print("Enter a number: ");
		while (!StdIn.isEmpty()) {
			int key = StdIn.readInt();
			int rank = rank(key, whitelist);
			StdOut.format("%d %s in the list\n", key, (rank >= 0) ? "is" : "is not");
			StdOut.print("Enter a number: ");
		}
	}

	public static void testPerformance(String whitelistFile, String keyFile) {
		// get whitelist from file and sort
		double[] whitelist = new In(whitelistFile).readAllDoubles();
		Arrays.sort(whitelist);

		// open key file and start timer
		double[] keys = new In (keyFile).readAllDoubles();
		Stopwatch sw = new Stopwatch();

		// count number of data entries in the whitelist
		int count = 0;
		for (double key : keys) {
			if (rank0(key, whitelist) == -1) {
				//StdOut.println(key); // print to see the data, comment out for performance
				count = count + 1;
			}
		}

		StdOut.format ("%d misses\n%f seconds\n", count, sw.elapsedTime ());
	}

	public static void main(String[] args) {
		testInteractive("data/tinyW.txt");
		//testTrace("data/tinyW.txt", 28);
		//testPerformance("data/tinyW.txt", "data/tinyT.txt");
		//testPerformance("data/largeW.txt", "data/largeT.txt");
		
		// The following lines show why mid is computed as it is
		//int hi = 2_000_000_010;
		//int lo = 2_000_000_005;		
		//StdOut.format ("(hi+lo)/2    = %,d\n", (hi+lo)/2);
		//StdOut.format ("lo+(hi-lo)/2 = %,d\n", lo+(hi-lo)/2);
		
		// Similar things happen when using bit shifting
		//StdOut.format ("(hi+lo)>>1   = %,d\n", (hi+lo)>>1);
		//StdOut.format ("(hi+lo)>>>1  = %,d\n", (hi+lo)>>>1);
	}
}
