package algs14;
import stdlib.*;
import java.util.Arrays;
/* ***********************************************************************
 *  Compilation:  javac TwoSumFast.java
 *  Execution:    java TwoSumFast input.txt
 *
 *  Dependencies: In.java Stopwatch.java
 *  Data files:   http://algs4.cs.princeton.edu/14analysis/1Kints.txt
 *                http://algs4.cs.princeton.edu/14analysis/2Kints.txt
 *                http://algs4.cs.princeton.edu/14analysis/4Kints.txt
 *                http://algs4.cs.princeton.edu/14analysis/8Kints.txt
 *                http://algs4.cs.princeton.edu/14analysis/16Kints.txt
 *                http://algs4.cs.princeton.edu/14analysis/32Kints.txt
 *                http://algs4.cs.princeton.edu/14analysis/1Mints.txt
 *
 *  A program with N log N running time. Read in N integers
 *  and counts the number of pairs that sum to exactly 0.
 *
 *  Limitations
 *  -----------
 *     - we ignore integer overflow
 *
 *
 *  % java TwoSumFast 2Kints.txt
 *  2
 *
 *  % java TwoSumFast 1Kints.txt
 *  1
 *
 *  % java TwoSumFast 2Kints.txt
 *  2
 *
 *  % java TwoSumFast 4Kints.txt
 *  3
 *
 *  % java TwoSumFast 8Kints.txt
 *  19
 *
 *  % java TwoSumFast 16Kints.txt
 *  66
 *
 *  % java TwoSumFast 32Kints.txt
 *  273

 *
 *************************************************************************/

public class XTwoSumFast {

	// print distinct pairs (i, j) such that a[i] + a[j] = 0
	public static void printAll(int[] a) {
		int N = a.length;
		Arrays.sort(a);
		for (int i = 0; i < N; i++) {
			int j = Arrays.binarySearch(a, -a[i]);
			if (j > i) StdOut.println(a[i] + " " + a[j]);
		}
	}

	// return number of distinct pairs (i, j) such that a[i] + a[j] = 0
	public static int count(int[] a) {
		int N = a.length;
		Arrays.sort(a);
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			int j = Arrays.binarySearch(a, -a[i]);
			if (j > i) cnt++;
		}
		return cnt;
	}

	public static void main(String[] args)  {
		//args = new String[] { "data/2Kints.txt" };
		int[] a = new In(args[0]).readAllInts();
		int cnt = count(a);
		StdOut.println(cnt);
	}
}
