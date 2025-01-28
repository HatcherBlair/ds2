package algs21;
import stdlib.*;
/* ***********************************************************************
 *  Compilation:  javac Sort3.java
 *  Execution:    java Sort3 a b c
 *
 *  Reads in three integers and prints them out in sorted order without
 *  using a loop.
 *
 *  % java Sort3 33 22 18
 *  18 22 33
 *
 *  % java Sort3 43 12 8
 *  8 12 43
 *
 *************************************************************************/

public class XSort3 {
	public static void main(String[] args) {
		args = new String[] { "33", "22", "18" };

		// read in 3 command-line integers to sort
		int a = Integer.parseInt(args[0]);
		int b = Integer.parseInt(args[1]);
		int c = Integer.parseInt(args[2]);

		// 3 compare-exchanges
		if (b < a) { final int t = b; b = a; a = t; }
		if (c < b) { final int t = c; c = b; b = t; }
		if (b < a) { final int t = b; b = a; a = t; }

		// print out results
		StdOut.println(a + " " + b + " " + c);
	}

}
