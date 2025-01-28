package algs23;
import stdlib.*;
import java.util.Arrays;
/* ***********************************************************************
 *  Compilation:  javac IntegerSort.java
 *  Execution:    java IntegerSort N < input.txt
 *  Dependencies: StdIn.java
 *
 *************************************************************************/

public class XIntegerSort {

	public static void main(String[] args) {
		args = new String[] { "500000" }; StdIn.fromFile ("data/antiquicksort500K.txt");
		int N = Integer.parseInt(args[0]);

		// initialize and read in data
		Stopwatch timer1 = new Stopwatch();
		int[] a = new int[N];
		for (int i = 0; i < N; i++)
			a[i] = StdIn.readInt();
		double elapsed1 = timer1.elapsedTime();
		System.err.println("Input:  " + elapsed1 + " seconds");

		// sort
		Stopwatch timer2 = new Stopwatch();
		Arrays.sort(a, 0, N);
		double elapsed2 = timer2.elapsedTime();
		System.err.println("Sort:   " + elapsed2 + " seconds");

		// print first 10 values in sorted order
		for (int i = 0; i < N && i < 10; i++)
			StdOut.println(a[i]);
		StdOut.println("...");
	}

}
