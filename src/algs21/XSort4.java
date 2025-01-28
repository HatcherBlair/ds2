package algs21;
import stdlib.*;
/* ***********************************************************************
 *  Compilation:  javac Sort4.java
 *  Execution:    java Sort a b c d
 *
 *  Reads in four integers and prints them out in sorted order without
 *  using a loop. Uses 5 compare-exchanges.
 *
 *  % java Sort4 33 22 18 66
 *  18 22 33 66
 *
 *  % java Sort4 43 22 18 22
 *  18 22 22 43
 *
 *  % java Sort4 43 12 8 4
 *  4 8 12 43
 *
 *************************************************************************/

public class XSort4 {

	public static void main(String[] args) {
		args = new String[] { "33", "22", "18", "66" };

		int a = Integer.parseInt(args[0]);
		int b = Integer.parseInt(args[1]);
		int c = Integer.parseInt(args[2]);
		int d = Integer.parseInt(args[3]);
		if (a > b) { final int t = b; b = a; a = t; }
		if (c > d) { final int t = d; d = c; c = t; }
		if (a > c) { final int t = c; c = a; a = t; }
		if (b > d) { final int t = d; d = b; b = t; }
		if (b > c) { final int t = c; c = b; b = t; }

		StdOut.println(a + " " + b + " " + c + " " + d);
	}

}
