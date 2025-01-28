// Exercise 2.2.2 (Solution published at http://algs4.cs.princeton.edu/)
package algs24;
import stdlib.*;
import java.awt.Font;
/* ***********************************************************************
 *  Compilation:  javac TraceMerge.java
 *  Execution:    java  TraceMerge input
 *
 *  Mergesort the sequence of strings specified as the command-line
 *  arguments and show the detailed trace.
 *
 *  % java TraceMerge MERGESORTEXAMPLE
 *
 *************************************************************************/

public class XTraceHeap {
	private static int line = 0;

	public static void sort(String[] pq) {
		int N = pq.length;
		for (int k = N/2; k >= 1; k--) {
			sink(pq, k, N);
			draw (pq, N, k); line++;
		}
		while (N > 1) {
			exch(pq, 1, N--);
			sink(pq, 1, N);
			draw (pq, N, 1); line++;
		}
	}

	/* *********************************************************************
	 * Helper functions to restore the heap invariant.
	 **********************************************************************/

	private static void sink(String[] pq, int k, int N) {
		while (2*k <= N) {
			int j = 2*k;
			if (j < N && less(pq, j, j+1)) j++;
			if (!less(pq, k, j)) break;
			exch(pq, k, j);
			k = j;
		}
	}

	/* *********************************************************************
	 * Helper functions for comparisons and swaps.
	 * Indices are "off-by-one" to support 1-based indexing.
	 **********************************************************************/
	private static boolean less(String[] pq, int i, int j) {
		return pq[i-1].compareTo(pq[j-1]) < 0;
	}

	private static void exch(String[] pq, int i, int j) {
		String swap = pq[i-1];
		pq[i-1] = pq[j-1];
		pq[j-1] = swap;
	}

	// is v < w ?
	private static boolean less(String v, String w) {
		return (v.compareTo(w) < 0);
	}


	// draw the contents of the array
	private static void draw(String[] a, int N, int k) {
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.text(-2.50, line, N  + "");
		StdDraw.text(-1.25, line, k + "");
		for (int i = 0; i < a.length; i++) {
			StdDraw.text(i, line, a[i]);
		}
	}

	// display header
	private static void header(String[] a) {
		int N = a.length;
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.text(N/2, -3, "a[ ]");
		for (int i = 0; i < N; i++)
			StdDraw.text(i, -2, i + "");
		StdDraw.text(-2.50, -2, "N");
		StdDraw.text(-1.25, -2, "k");
		StdDraw.setPenColor(StdDraw.BOOK_RED);
		StdDraw.line(-4, -1.65, N-.5, -1.65);
		StdDraw.setPenColor(StdDraw.BLACK);
		for (int i = 0; i < a.length; i++)
			StdDraw.text(i, -1, a[i]);
	}

	// display footer
	private static void footer(String[] a) {
		int N = a.length;
		StdDraw.setPenColor(StdDraw.BLACK);
		for (int i = 0; i < a.length; i++)
			StdDraw.text(i, line, a[i]);
	}


	// test client
	public static void main(String[] args) {
		args = new String[] { "SORTEXAMPLE" };

		// parse command-line argument as an array of 1-character strings
		String s = args[0];
		int N = s.length();
		String[] a = new String[N];
		for (int i = 0; i < N; i++)
			a[i] = s.substring(i, i+1);

		// set canvas size
		StdDraw.setCanvasSize(30*(N+3), (int) (30*(1.5*N+3)));
		StdDraw.setXscale(-4, N+1);
		StdDraw.setYscale(1.5*N+1, -4);
		StdDraw.setFont(new Font("SansSerif", Font.PLAIN, 13));

		// display the header
		header(a);

		// sort the array and display trace
		sort(a);

		// display the footer
		footer(a);
	}

}
