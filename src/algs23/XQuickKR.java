package algs23;
import stdlib.*;
/* ***********************************************************************
 *  Compilation:  javac QuickKR.java
 *  Execution:    java QuickKR N
 *
 *  Generate N random real numbers between 0 and 1 and quicksort them.
 *  Uses version of quicksort from K+R.
 *
 *  Reference: The C Programming Language by Brian W. Kernighan and
 *  Dennis M. Ritchie, p. 87.
 *
 *************************************************************************/

public class XQuickKR {

	public static <T extends Comparable<? super T>> void sort(T[] a) {
		sort(a, 0, a.length - 1);
	}

	private static <T extends Comparable<? super T>> void sort(T[] a, int lo, int hi) {
		if (hi <= lo) return;
		exch(a, lo, (lo + hi) / 2);  // use middle element as partition
		int last = lo;
		for (int i = lo + 1; i <= hi; i++)
			if (less(a[i], a[lo])) exch(a, ++last, i);
		exch(a, lo, last);
		sort(a, lo, last-1);
		sort(a, last+1, hi);
	}


	/* *********************************************************************
	 *  Helper sorting functions
	 ***********************************************************************/

	// is v < w ?
	private static <T extends Comparable<? super T>> boolean less(T v, T w) {
		return (v.compareTo(w) < 0);
	}

	// exchange a[i] and a[j]
	private static void exch(Object[] a, int i, int j) {
		Object swap = a[i];
		a[i] = a[j];
		a[j] = swap;
	}


	/* *********************************************************************
	 *  Check if array is sorted - useful for debugging
	 ***********************************************************************/
	private static <T extends Comparable<? super T>> boolean isSorted(T[] a) {
		for (int i = 1; i < a.length; i++)
			if (less(a[i], a[i-1])) return false;
		return true;
	}



	// test client
	public static void main(String[] args) {

		// generate array of N random reals between 0 and 1
		int N = Integer.parseInt(args[0]);
		Double[] a = new Double[N];
		for (int i = 0; i < N; i++) {
			a[i] = Math.random();
		}

		// sort the array
		sort(a);

		// display results
		for (int i = 0; i < N; i++) {
			StdOut.println(a[i]);
		}
		StdOut.println("isSorted = " + isSorted(a));
	}

}
