// Exercise 2.5.19 (Solution published at http://algs4.cs.princeton.edu/)
package algs25;
import stdlib.*;
import algs22.XInversions;
/* ***********************************************************************
 *  Compilation:  javac KendallTau.java
 *  Execution:    java KendallTau N
 *
 *  Generate two random permutations of size N and compute their
 *  Kendall tau distance (number of inversions).
 *
 *************************************************************************/

public class XKendallTau {

	// return Kendall tau distance between two permutations
	public static int distance(int[] a, int[] b) {
		if (a.length != b.length) throw new Error("Array dimensions disagree");
		int N = a.length;

		int[] ainv = new int[N];
		for (int i = 0; i < N; i++) ainv[a[i]] = i;

		Integer[] bnew = new Integer[N];
		for (int i = 0; i < N; i++) bnew[i] = ainv[b[i]];

		return XInversions.count(bnew);
	}


	// return a random permutation of size N
	public static int[] permutation(int N) {
		int[] a = new int[N];
		for (int i = 0; i < N; i++) {
			int r = (int) (Math.random() * (i + 1));
			a[i] = a[r];
			a[r] = i;
		}
		return a;
	}




	public static void main(String[] args) {

		// two random permutation of size N
		int N = Integer.parseInt(args[0]);
		int[] a = permutation(N);
		int[] b = permutation(N);


		// print initial permutation
		for (int i = 0; i < N; i++)
			StdOut.println(a[i] + " " + b[i]);
		StdOut.println();

		StdOut.println("inversions = " + distance(a, b));
	}
}


