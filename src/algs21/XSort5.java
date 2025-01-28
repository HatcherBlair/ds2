package algs21;
import stdlib.*;
/* ***********************************************************************
 *  Compilation:  javac Sort5.java
 *  Execution:    java Sort A B C D E
 *
 *  Reads in five integers and prints them out in sorted order without
 *  using a loop.
 *
 *  % java Sort5 33 22 18 66 43
 *  18 22 33 43 66
 *
 *  % java Sort5 43 22 18 22 33
 *  18 22 22 33 43
 *
 *  % java Sort5 43 12 8 4 1
 *  1 4 8 12 43
 *
 *************************************************************************/

public class XSort5 {
	public static void main(String[] args) {
		args = new String[] { "33", "22", "18", "66", "43" };

		int a = Integer.parseInt(args[0]);
		int b = Integer.parseInt(args[1]);
		int c = Integer.parseInt(args[2]);
		int d = Integer.parseInt(args[3]);
		int e = Integer.parseInt(args[4]);

		if (a > b) { final int t = a; a = b; b = t; }
		if (d > e) { final int t = d; d = e; e = t; }
		if (a > c) { final int t = a; a = c; c = t; }
		if (b > c) { final int t = b; b = c; c = t; }
		if (a > d) { final int t = a; a = d; d = t; }
		if (c > d) { final int t = c; c = d; d = t; }
		if (b > e) { final int t = b; b = e; e = t; }
		if (b > c) { final int t = b; b = c; c = t; }
		if (d > e) { final int t = d; d = e; e = t; }

		StdOut.println(a + " " + b + " " + c + " " + d + " " + e);
	}

}
