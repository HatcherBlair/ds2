package algs22;
import stdlib.*;
/* ***********************************************************************
 *  Compilation:  javac MergeBU.java
 *  Execution:    java MergeBU < input.txt
 *  Dependencies: StdOut.java StdIn.java
 *  Data files:   http://algs4.cs.princeton.edu/22merge/tiny.txt
 *                http://algs4.cs.princeton.edu/22merge/words3.txt
 *
 *  Sorts a sequence of strings from standard input using
 *  bottom-up mergesort.
 *
 *  % more tiny.txt
 *  S O R T E X A M P L E
 *
 *  % java MergeBU < tiny.txt
 *  A E E L M O P R S T X                 [ one string per line ]
 *
 *  % more words3.txt
 *  bed bug dad yes zoo ... all bad yet
 *
 *  % java MergeBU < words3.txt
 *  all bad bed bug dad ... yes yet zoo    [ one string per line ]
 *
 *************************************************************************/

public class MergeBU {

	// stably merge a[lo..m] with a[m+1..hi] using aux[lo..hi]
	private static <T extends Comparable<? super T>> void merge(T[] a, T[] aux, int lo, int m, int hi) {

		// copy to aux[]
		for (int k = lo; k <= hi; k++) {
			aux[k] = a[k];
		}

		// merge back to a[]
		int i = lo, j = m+1;
		for (int k = lo; k <= hi; k++) {
			if      (i > m)                a[k] = aux[j++];
			else if (j > hi)               a[k] = aux[i++];
			else if (less(aux[j], aux[i])) a[k] = aux[j++];
			else                           a[k] = aux[i++];
		}
		if (COUNT_OPS) DoublingTest.addOps (hi-lo);

	}

	// bottom-up mergesort
	@SuppressWarnings("unchecked")
	public static <T extends Comparable<? super T>> void sort(T[] a) {
		int N = a.length;
		T[] aux = (T[]) new Comparable[N];
		for (int n = 1; n < N; n = n+n) {
			for (int i = 0; i < N-n; i += n+n) {
				int lo = i;
				int m  = i+n-1;
				int hi = Math.min(i+n+n-1, N-1);
				merge(a, aux, lo, m, hi);
			}
		}
		assert isSorted(a);
	}

	/* *********************************************************************
	 *  Helper sorting functions
	 ***********************************************************************/

	// is v < w ?
	private static <T extends Comparable<? super T>> boolean less(T v, T w) {
		if (COUNT_OPS) DoublingTest.incOps ();
		return (v.compareTo(w) < 0);
	}


	/* *********************************************************************
	 *  Check if array is sorted - useful for debugging
	 ***********************************************************************/
	private static <T extends Comparable<? super T>> boolean isSorted(T[] a) {
		for (int i = 1; i < a.length; i++)
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


