package algs13;
import stdlib.*;
/* ***********************************************************************
 *  Compilation:  javac Reverse.java
 *  Execution:    java Reverse
 *  Dependencies: Stack.java StdOut.java StdIn.java
 *
 *  Read a sequence of integers from standard input and print them
 *  in reverse order.
 *
 *  % java  java Reverse
 *  1 2 3 4 5
 *  5
 *  4
 *  3
 *  2
 *  1

 *************************************************************************/

public class XReverseStdIn {
	public static void main(String[] args) {
		//StdIn.fromString ("1 2 3 4 5");
		StdIn.fromString ("10 20 30 40 50");

		Stack<Integer> stack = new Stack<>();
		while (!StdIn.isEmpty()) {
			int i = StdIn.readInt();
			stack.push(i);
		}
		while (! stack.isEmpty()) {
			int i = stack.pop ();
			StdOut.println(i);
		}
	}
}
