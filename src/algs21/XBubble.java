package algs21;
import stdlib.*;

public class XBubble {

	// use natural order and Comparable interface
	public static <T extends Comparable<? super T>> void sort(T[] a) {
		int N = a.length;
		for (int i = 0; i < N; i++) {
			for (int j = 1; j < (N - i); j++) {
				if (less (a[j], a[j - 1])) {
					exch (a, j, j - 1);
				}
				assert isSorted (a, N - i - 1, N - 1);
			}
		}
		assert isSorted (a);
	}

	/* *********************************************************************
	 *  Helper sorting functions
	 ***********************************************************************/

	// is v < w ?
	private static <T extends Comparable<? super T>> boolean less(T v, T w) {
		if (COUNT_OPS) DoublingTest.incOps ();
		return (v.compareTo(w) < 0);
	}

	// exchange a[i] and a[j]
	private static <T> void exch(T[] a, int i, int j) {
		if (COUNT_OPS) DoublingTest.incOps ();
		T swap = a[i];
		a[i] = a[j];
		a[j] = swap;
	}

	/* *********************************************************************
	 *  Check if array is sorted - useful for debugging
	 ***********************************************************************/
	private static <T extends Comparable<? super T>> boolean isSorted(T[] a) {
		return isSorted(a, 0, a.length - 1);
	}

	// is the array sorted from a[lo] to a[hi]
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
		//StdIn.fromFile ("data/tiny.txt");
		StdIn.fromFile ("data/words3.txt");
		String[] a = StdIn.readAllStrings();
		sort(a);
		show(a);

		COUNT_OPS = true;
		DoublingTest.run (2000, 5, N -> ArrayGenerator.integerRandomUnique (N),          (Integer[] x) -> sort (x));
		DoublingTest.run (2000, 5, N -> ArrayGenerator.integerRandom (N, 2),             (Integer[] x) -> sort (x));
		DoublingTest.run (2000, 5, N -> ArrayGenerator.integerPartiallySortedUnique (N), (Integer[] x) -> sort (x));

	}
}
