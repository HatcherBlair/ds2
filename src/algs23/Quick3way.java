package algs23;
import stdlib.*;
/* ***********************************************************************
 *  Compilation:  javac Quick3way.java
 *  Execution:    java Quick3way < input.txt
 *  Dependencies: StdOut.java StdIn.java
 *  Data files:   http://algs4.cs.princeton.edu/23quicksort/tiny.txt
 *                http://algs4.cs.princeton.edu/23quicksort/words3.txt
 *
 *  Sorts a sequence of strings from standard input using 3-way quicksort.
 *
 *  % more tiny.txt
 *  S O R T E X A M P L E
 *
 *  % java Quick3way < tiny.txt
 *  A E E L M O P R S T X                 [ one string per line ]
 *
 *  % more words3.txt
 *  bed bug dad yes zoo ... all bad yet
 *
 *  % java Quick3way < words3.txt
 *  all bad bed bug dad ... yes yet zoo    [ one string per line ]
 *
 *************************************************************************/

public class Quick3way {

	// quicksort the array a[] using 3-way partitioning
	public static <T extends Comparable<? super T>> void sort(T[] a) {
		sort(a, 0, a.length - 1);
		assert isSorted(a);
	}

	// quicksort the subarray a[lo .. hi] using 3-way partitioning
	private static <T extends Comparable<? super T>> void sort(T[] a, int lo, int hi) {
		if (hi <= lo) return;
		int lt = lo, gt = hi;
		T v = a[lo];
		int i = lo;
		while (i <= gt) {
			int cmp = a[i].compareTo(v);
			if      (cmp < 0) exch(a, lt++, i++);
			else if (cmp > 0) exch(a, i, gt--);
			else              i++;
		}

		// a[lo..lt-1] < v = a[lt..gt] < a[gt+1..hi].
		sort(a, lo, lt-1);
		sort(a, gt+1, hi);
		assert isSorted(a, lo, hi);
	}



	/* *********************************************************************
	 *  Helper sorting functions
	 ***********************************************************************/

	// is v < w ?
	private static <T extends Comparable<? super T>> boolean less(T v, T w) {
		if (COUNT_OPS) DoublingTest.incOps ();
		return (v.compareTo(w) < 0);
	}

	// does v == w ?
	private static <T extends Comparable<? super T>> boolean eq(T v, T w) {
		if (COUNT_OPS) DoublingTest.incOps ();
		return (v.compareTo(w) == 0);
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
		//String[] cards = In.readStrings ("data/cards.txt");
		//StdRandom.shuffle (cards);

		//StdIn.fromFile ("data/tiny.txt");
		//StdIn.fromFile ("data/cards.txt");
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
