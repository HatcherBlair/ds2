// Exercise 2.2.19 (Solution published at http://algs4.cs.princeton.edu/)
package algs22;
import stdlib.*;
/* ***********************************************************************
 *  Compilation:  javac Inversions.java
 *  Execution:    java Inversions N
 *
 *  Generate N pseudo-random numbers between 0 and 1 and count
 *  the number of inversions in O(N log N) time.
 *
 *************************************************************************/

public class XInversions {

	// merge and count
	private static <T extends Comparable<? super T>> int merge(T[] a, T[] aux, int lo, int mid, int hi) {
		int inversions = 0;

		// copy to aux[]
		for (int k = lo; k <= hi; k++) {
			aux[k] = a[k];
		}

		// merge back to a[]
		int i = lo, j = mid+1;
		for (int k = lo; k <= hi; k++) {
			if      (i > mid)                a[k] = aux[j++];
			else if (j > hi)                 a[k] = aux[i++];
			else if (less(aux[j], aux[i])) { a[k] = aux[j++]; inversions += (mid - i + 1); }
			else                             a[k] = aux[i++];
		}
		return inversions;
	}

	// return the number of inversions in the subarray b[lo..hi]
	// side effect b[lo..hi] is rearranged in ascending order
	private static <T extends Comparable<? super T>> int count(T[] a, T[] b, T[] aux, int lo, int hi) {
		int inversions = 0;
		if (hi <= lo) return 0;
		int mid = lo + (hi - lo) / 2;
		inversions += count(a, b, aux, lo, mid);
		inversions += count(a, b, aux, mid+1, hi);
		inversions += merge(b, aux, lo, mid, hi);
		assert inversions == brute(a, lo, hi);
		return inversions;
	}


	// count number of inversions in the array a[] - do not overwrite a[]
	@SuppressWarnings("unchecked")
	public static <T extends Comparable<? super T>> int count(T[] a) {
		T[] b   = (T[]) new Comparable[a.length];
		T[] aux = (T[]) new Comparable[a.length];
		for (int i = 0; i < a.length; i++) b[i] = a[i];
		int inversions = count(a, b, aux, 0, a.length - 1);
		return inversions;
	}


	// is v < w ?
	private static <T extends Comparable<? super T>> boolean less(T v, T w) {
		return (v.compareTo(w) < 0);
	}

	// count number of inversions in a[lo..hi] via brute force (for debugging only)
	private static <T extends Comparable<? super T>> int brute(T[] a, int lo, int hi) {
		int inversions = 0;
		for (int i = lo; i <= hi; i++)
			for (int j = i + 1; j <= hi; j++)
				if (less(a[j], a[i])) inversions++;
		return inversions;
	}




	// generate N real numbers between 0 and 1, and mergesort them
	public static void main(String[] args) {
		args = new String[] { "20" };

		int N = Integer.parseInt(args[0]);
		Double[] a = new Double[N];
		for (int i = 0; i < N; i++)
			a[i] = Math.random();
		StdOut.println(brute(a, 0, N-1));
		StdOut.println(count(a));
		for (int i = 0; i < N; i++)
			StdOut.println(a[i]);
	}
}
