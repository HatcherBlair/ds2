// Exercise 2.1.24, 2.1.25 (Solution published at http://algs4.cs.princeton.edu/)
package algs21;
import stdlib.*;

public class XInsertionX {

	public static <T extends Comparable<? super T>> void sort(T[] a) {
		int N = a.length;

		// put smallest element in position to serve as sentinel
		for (int i = N-1; i > 0; i--)
			if (less(a[i], a[i-1])) exch(a, i, i-1);

		// insertion sort with half-exchanges
		for (int i = 2; i < N; i++) {
			T v = a[i];
			int j = i;
			while (less(v, a[j-1])) {
				a[j] = a[j-1];
				j--;
			}
			a[j] = v;
		}

		assert isSorted(a);
	}


	/* *********************************************************************
	 *  Helper sorting functions
	 ***********************************************************************/

	// is v < w ?
	private static <T extends Comparable<? super T>> boolean less(T v, T w) {
		if (COUNT_OPS) DoublingTest.incOps ();
		return v.compareTo(w) < 0;
	}

	// exchange a[i] and a[j]
	private static void exch(Object[] a, int i, int j) {
		if (COUNT_OPS) DoublingTest.incOps ();
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

	// print array to standard output
	private static <T> void show(T[] a) {
		for (T element : a) {
			StdOut.println(element);
		}
	}

	// test code
	private static boolean COUNT_OPS = false;
	public static void main(String[] args) {
		StdIn.fromString ("S O R T E X A M P L E");
		//StdIn.fromFile ("data/tiny.txt");
		//StdIn.fromFile ("data/cards.txt");
		//StdIn.fromFile ("data/words3.txt");

		String[] a = StdIn.readAllStrings();
		//StdRandom.shuffle (a);
		//show(a);
		//StdOut.println ("----------------");
		sort(a);
		show(a);

		COUNT_OPS = true;
		DoublingTest.run (2000, 10, N -> ArrayGenerator.integerRandomUnique (N),          (Integer[] x) -> sort (x));
		//DoublingTest.run (2000, 10, N -> ArrayGenerator.integerRandom (N, 2),             (Integer[] x) -> sort (x));
		//DoublingTest.run (2000, 10, N -> ArrayGenerator.integerPartiallySortedUnique (N), (Integer[] x) -> sort (x));
	}
}
