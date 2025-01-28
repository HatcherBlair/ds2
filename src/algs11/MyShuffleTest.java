// Exercise 1.1.36
package algs11;
import stdlib.*;

/*

1.1.36 Empirical shuffle check.
Run computational experiments to check that our shuffling code on page 32 works as advertised.
Write a program ShuffleTest that takes commandline arguments M and N, does N shuffles of an
array of size M that is initialized with a[i] = i before each shuffle, and prints an M-by-M table
such that row i gives the number of times i wound up in position j for all j. All entries in the
array should be close to N/M.

Suppose we start with N=3 and run the shuffling test four times,
resulting in the arrays:

  [2, 0, 1]
  [2, 1, 0]
  [2, 0, 1]
  [2, 0, 1]

(This is not very likely, but it is possible)
Then the resulting count array would be:

  0: 0 3 1
  1: 0 1 3
  2: 4 0 0

If we normalize by dividing by the number of times we ran the test,
then we get

  0: 0.000 0.750 0.250
  1: 0.000 0.250 0.750
  2: 1.000 0.000 0.000

That is:
element "2" was always in the first position;
element "0" was 75/25 between second and third position;
and likewise element "1".

You could compute the standard deviation for each of these, which
would be highest for "2".

--------------------------------------------------------------------------------
Here is a more detailed example

initially: counts = {
  {0,0,0},  // position of element 0 in shuffled array
  {0,0,0},  // position of element 1 in shuffled array
  {0,0,0} } // position of element 2 in shuffled array

Iteration 1: test=0
a. initialize array; a={0,1,2}
b. shuffle array; a={1,0,2}
c. increment counts = {
  {0,1,0},
  {1,0,0},
  {0,0,1} }

Iteration 2: test=1
a. initialize array; a={0,1,2}
b. shuffle array; a={0,2,1}
c. increment counts = {
  {1,1,0},
  {1,0,1},
  {0,1,1} }

Iteration 2: test=2
a. initialize array; a={0,1,2}
b. shuffle array; a={0,1,2}
c. increment counts = {
  {2,1,0},
  {1,1,1},
  {0,1,2} }

 */

public class MyShuffleTest {
	public static void main (String[] args) {
		args = new String[] { "3", "40000" };

		int N = Integer.parseInt (args[0]); // size of the array to shuffle
		int numTests = Integer.parseInt (args[1]); // number of times to shuffle

		if (N > Byte.MAX_VALUE) throw new RuntimeException ();

		byte[] a = new byte[N]; // the array
		double[][] counts = new double[N][N]; // a place to put the results

		for (int test = 0; test < numTests; test++) {
			for (byte b = 0; b < a.length; b++)
				a[b] = b;
			shuffle (a);
			if (numTests < 10) StdOut.println (java.util.Arrays.toString (a)); // for debugging: print the shuffled array
			incrementCounts (counts, a);
		}
		printCounts (counts, numTests);
	}

	private static void swap (byte[] a, int i, int j) {
		byte temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
	private static void shuffle (byte[] a) {
		int N = a.length;
		for (int i = 0; i < N; i++)
			swap (a, i, i + StdRandom.uniform (N - i));
	}
	private static void incrementCounts (double[][] counts, byte[] a) {
		// count[b][i] is the number of times that byte b is placed in position i
		// TODO
	}
	// This function destroys the counts matrix
	private static void printCounts (double[][] counts, int numTests) {
		// TODO
	}
}
