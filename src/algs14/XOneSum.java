package algs14;
import stdlib.*;
/* ***********************************************************************
 *  Compilation:  javac OneSum.java
 *  Execution:    java OneSum input.txt
 *  Dependencies: In.java Stopwatch.java
 *
 *  A program with N running time. Read in N integers
 *  and counts the number that are exactly 0.
 *
 *  % java OneSum 2Kints.txt
 *  0
 *************************************************************************/

public class XOneSum {

	// print indices i such that a[i] = 0
	public static void printAll(int[] a) {
		int N = a.length;
		for (int i = 0; i < N; i++) {
			if (a[i] == 0) {
				StdOut.println(a[i]);
			}
		}
	}


	// return number of indices i such that a[i] = 0
	public static int count(int[] a) {
		int N = a.length;
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			if (a[i] == 0) {
				cnt++;
			}
		}
		return cnt;
	}

	public static void main(String[] args)  {
		args = new String[] { "data/2Kints.txt" };
		int[] a = new In(args[0]).readAllInts();
		Stopwatch timer = new Stopwatch();
		int cnt = count(a);
		StdOut.println("elapsed time = " + timer.elapsedTime());
		StdOut.println(cnt);
	}
}
