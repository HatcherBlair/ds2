// Exercise 2.5.27 (Solution published at http://algs4.cs.princeton.edu/)
package algs21;
import stdlib.*;
import java.util.Comparator;
/* ***********************************************************************
 *  Compilation:  javac Insertion.java
 *  Execution:    java Insertion < input.txt
 *  Dependencies: StdOut.java StdIn.java
 *  Data files:   http://algs4.cs.princeton.edu/21sort/tiny.txt
 *                http://algs4.cs.princeton.edu/21sort/words3.txt
 *
 *  Sorts a sequence of strings from standard input using insertion sort.
 *
 *  % more tiny.txt
 *  S O R T E X A M P L E
 *
 *  % java Insertion < tiny.txt
 *  A E E L M O P R S T X                 [ one string per line ]
 *
 *  % more words3.txt
 *  bed bug dad yes zoo ... all bad yet
 *
 *  % java Insertion < words3.txt
 *  all bad bed bug dad ... yes yet zoo   [ one string per line ]
 *
 *************************************************************************/

public class Insertion {

	// use natural order and Comparable interface
	public static <T extends Comparable<? super T>> void sort(T[] a) {
		int N = a.length;
		for (int i = 0; i < N; i++) {
			for (int j = i; j > 0 && less(a[j], a[j-1]); j--) {
				exch(a, j, j-1);
			}
			//assert isSorted(a, 0, i);
		}
		//assert isSorted(a);
	}

	// use a custom order and Comparator interface - see Section 3.5
	public static <T> void sort(T[] a, Comparator<? super T> c) {
		int N = a.length;
		for (int i = 0; i < N; i++) {
			for (int j = i; j > 0 && less(c, a[j], a[j-1]); j--) {
				exch(a, j, j-1);
			}
			assert isSorted(a, c, 0, i);
		}
		assert isSorted(a, c);
	}

	// return a permutation that gives the elements in a[] in ascending order
	// do not change the original array a[]
	public static <T extends Comparable<? super T>> int[] indexSort(T[] a) {
		int N = a.length;
		int[] index = new int[N];
		for (int i = 0; i < N; i++)
			index[i] = i;

		for (int i = 0; i < N; i++)
			for (int j = i; j > 0 && less(a[index[j]], a[index[j-1]]); j--)
				exch(index, j, j-1);

		return index;
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
	
	
	// is v < w ?
	private static <T> boolean less(Comparator<? super T> c, T v, T w) {
		if (COUNT_OPS) DoublingTest.incOps ();
		return (c.compare(v, w) < 0);
	}

	// exchange a[i] and a[j]  (for indexSort)
	private static void exch(int[] a, int i, int j) {
		if (COUNT_OPS) DoublingTest.incOps ();
		int swap = a[i];
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

	private static <T> boolean isSorted(T[] a, Comparator<? super T> c) {
		return isSorted(a, c, 0, a.length - 1);
	}

	// is the array sorted from a[lo] to a[hi]
	private static <T> boolean isSorted(T[] a, Comparator<? super T> c, int lo, int hi) {
		for (int i = lo + 1; i <= hi; i++)
			if (less(c, a[i], a[i-1])) return false;
		return true;
	}

	// print array to standard output
	private static <T> void show(T[] a) {
		StdOut.println(java.util.Arrays.toString(a));
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
		// sort(a);
		// show(a);

		COUNT_OPS = false;
		// DoublingTest.run (2000, 6, 20, N -> ArrayGenerator.integerRandomUnique (N),      (Integer[] x) -> sort (x));
		//DoublingTest.run (2000, 6, N -> ArrayGenerator.integerRandom (N, 2),             (Integer[] x) -> sort (x));
		DoublingTest.run (2000, 6, N -> ArrayGenerator.integerPartiallySortedUnique (N), (Integer[] x) -> sort (x));
	}
}
