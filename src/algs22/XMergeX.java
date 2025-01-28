// Exercise 2.2.11 (Solution published at http://algs4.cs.princeton.edu/)
package algs22;
import stdlib.*;
/* ***********************************************************************
 *  Compilation:  javac MergeX.java
 *  Execution:    java MergeX < input.txt
 *  Dependencies: StdOut.java StdIn.java
 *  Data files:   http://algs4.cs.princeton.edu/22merge/tiny.txt
 *                http://algs4.cs.princeton.edu/22merge/words3.txt
 *
 *  Sorts a sequence of strings from standard input using an
 *  optimized version of mergesort.
 *
 *  % more tiny.txt
 *  S O R T E X A M P L E
 *
 *  % java MergeX < tiny.txt
 *  A E E L M O P R S T X                 [ one string per line ]
 *
 *  % more words3.txt
 *  bed bug dad yes zoo ... all bad yet
 *
 *  % java MergeX < words3.txt
 *  all bad bed bug dad ... yes yet zoo    [ one string per line ]
 *
 *************************************************************************/

public class XMergeX {
	private static final int CUTOFF = 7;  // cutoff to insertion sort


	private static <T extends Comparable<? super T>> void merge(T[] src, T[] dst, int lo, int mid, int hi) {

		// precondition: src[lo .. mid] and src[mid+1 .. hi] are sorted subarrays
		assert isSorted(src, lo, mid);
		assert isSorted(src, mid+1, hi);

		int i = lo, j = mid+1;
		for (int k = lo; k <= hi; k++) {
			if      (i > mid)              dst[k] = src[j++];
			else if (j > hi)               dst[k] = src[i++];
			else if (less(src[j], src[i])) dst[k] = src[j++];   // to ensure stability
			else                           dst[k] = src[i++];
		}
		if (COUNT_OPS) DoublingTest.addOps (hi-lo);

		// postcondition: dst[lo .. hi] is sorted subarray
		assert isSorted(dst, lo, hi);
	}

	private static <T extends Comparable<? super T>> void sort(T[] src, T[] dst, int lo, int hi) {
		if (hi <= lo + CUTOFF) {
			insertionSort(src, lo, hi);
			return;
		}
		int mid = lo + (hi - lo) / 2;
		sort(dst, src, lo, mid);
		sort(dst, src, mid+1, hi);

		/*
        if (!less(dst[mid+1], dst[mid])) {
            for (int i = lo; i <= hi; i++) src[i] = dst[i];
            return;
        }
		 */
		// a bit faster
		if (!less(dst[mid+1], dst[mid])) {
			System.arraycopy(dst, lo, src, lo, hi - lo + 1);
			return;
		}

		merge(dst, src, lo, mid, hi);
	}

	public static <T extends Comparable<? super T>> void sort(T[] a) {
		/*
        Comparable[] aux = new Comparable[a.length];
        for (int i = 0; i < a.length; i++)
            aux[i] = a[i];
		 */
		// a bit faster
		T[] aux = a.clone();
		sort(a, aux, 0, a.length-1);

		assert isSorted(a);
	}


	// sort from a[lo] to a[hi] using insertion sort
	private static <T extends Comparable<? super T>> void insertionSort(T[] a, int lo, int hi) {
		for (int i = lo; i <= hi; i++)
			for (int j = i; j > lo && less(a[j], a[j-1]); j--)
				exch(a, j, j-1);
	}


	// exchange a[i] and a[j]
	private static <T> void exch(T[] a, int i, int j) {
		if (COUNT_OPS) DoublingTest.incOps ();
		T swap = a[i];
		a[i] = a[j];
		a[j] = swap;
	}

	// is a[i] < a[j]?
	private static <T extends Comparable<? super T>> boolean less(T a, T b) {
		if (COUNT_OPS) DoublingTest.incOps ();
		return (a.compareTo(b) < 0);
	}

	/* *********************************************************************
	 *  Check if array is sorted - useful for debugging
	 ***********************************************************************/
	private static <T extends Comparable<? super T>> boolean isSorted(T[] a) {
		return isSorted(a, 0, a.length - 1);
	}

	private static <T extends Comparable<? super T>> boolean isSorted(T[] a, int lo, int hi) {
		for (int i = lo + 1; i <= hi; i++)
			if (less(a[i], a[i-1])) return false;
		return true;
	}

	// print array to standard output
	private static <T> void show(T[] a) {
		for (T element : a) {
			StdOut.println(element);
		}
	}

	// test code
	private static boolean COUNT_OPS = false;
	public static void main(String[] args) {
		StdIn.fromFile ("data/words3.txt");
		String[] a = StdIn.readAllStrings();
		sort(a);
		show(a);

		COUNT_OPS = true;
		DoublingTest.run (20000, 5, N -> ArrayGenerator.integerRandomUnique (N),          (Integer[] x) -> sort (x));
		DoublingTest.run (20000, 5, N -> ArrayGenerator.integerRandom (N, 2),             (Integer[] x) -> sort (x));
		DoublingTest.run (20000, 5, N -> ArrayGenerator.integerPartiallySortedUnique (N), (Integer[] x) -> sort (x));

	}
}
