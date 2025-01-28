package algs21;
import stdlib.*;

public class Selection {

	// selection sort
	public static <T extends Comparable<? super T>> void sort(T[] a) {
		int N = a.length;
		for (int i = 0; i < N; i++) {
			int min = i;
			for (int j = i+1; j < N; j++) {
				if (less(a[j], a[min])) min = j;
			}
			if (i!=min)
				exch(a, i, min);
			assert isSorted(a, 0, i);
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

	// is the array a[] sorted?
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
		StdIn.fromString ("S O R T E X A M P L E");
		//StdIn.fromFile ("data/tiny.txt");
		//StdIn.fromFile ("data/cards.txt");
		//StdIn.fromFile ("data/words3.txt");

		String[] a = StdIn.readAllStrings();
		//StdRandom.shuffle (a);
		//show(a);
		//StdOut.println ("----------------");
		//sort(a);
		//show(a);

		COUNT_OPS = false;
		DoublingTest.run (2000, 6, N -> ArrayGenerator.integerRandomUnique (N),          (Integer[] x) -> sort (x));
		//DoublingTest.run (2000, 6, N -> ArrayGenerator.integerRandom (N, 2),             (Integer[] x) -> sort (x));
		//DoublingTest.run (2000, 6, N -> ArrayGenerator.integerPartiallySortedUnique (N), (Integer[] x) -> sort (x));
	}
}
