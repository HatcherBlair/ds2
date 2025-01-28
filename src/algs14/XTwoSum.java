package algs14;
import stdlib.*;
/* ***********************************************************************
 *  Compilation:  javac TwoSum.java
 *  Execution:    java TwoSum input.txt
 *  Dependencies: In.java Stopwatch.java
 *  Data files:   http://algs4.cs.princeton.edu/14analysis/1Kints.txt
 *                http://algs4.cs.princeton.edu/14analysis/2Kints.txt
 *                http://algs4.cs.princeton.edu/14analysis/4Kints.txt
 *                http://algs4.cs.princeton.edu/14analysis/8Kints.txt
 *                http://algs4.cs.princeton.edu/14analysis/16Kints.txt
 *                http://algs4.cs.princeton.edu/14analysis/32Kints.txt
 *                http://algs4.cs.princeton.edu/14analysis/1Mints.txt
 *
 *  A program with N^2 running time. Read in N integers
 *  and counts the number of pairs that sum to exactly 0.
 *
 *
 *  Limitations
 *  -----------
 *     - we ignore integer overflow
 *
 *
 *  % java TwoSum 2Kints.txt
 *  2
 *
 *  % java TwoSum 1Kints.txt
 *  1
 *
 *  % java TwoSum 2Kints.txt
 *  2
 *
 *  % java TwoSum 4Kints.txt
 *  3
 *
 *  % java TwoSum 8Kints.txt
 *  19
 *
 *  % java TwoSum 16Kints.txt
 *  66
 *
 *  % java TwoSum 32Kints.txt
 *  273
 *
 *************************************************************************/

public class XTwoSum {

	// print distinct pairs (i, j) such that a[i] + a[j]  = 0
	public static void printAll(int[] a) {
		int N = a.length;
		for (int i = 0; i < N; i++) {
			for (int j = i+1; j < N; j++) {
				if (a[i] + a[j] == 0) {
					StdOut.println(a[i] + " " + a[j]);
				}
			}
		}
	}

	// return number of distinct pairs (i, j) 	such that a[i] + a[j] = 0
	public static int count(int[] a) {
		int N = a.length;
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = i+1; j < N; j++) {
				if (a[i] + a[j] == 0) {
					cnt++;
				}
			}
		}
		return cnt;
	}

//	public static void main(String[] args)  {
//		args = new String[] { "data/2Kints.txt" };
//		int[] a = new In(args[0]).readAllInts();
//		Stopwatch timer = new Stopwatch();
//		int cnt = count(a);
//		StdOut.println("elapsed time = " + timer.elapsedTime());
//		StdOut.println(cnt);
//	}
}
