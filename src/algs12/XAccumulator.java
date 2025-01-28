package algs12;

import stdlib.*;

/* ***********************************************************************
 *  Compilation:  javac Accumulator.java
 *
 *  Mutable data type that calculates mean of data values.
 *
 *************************************************************************/


public class XAccumulator {
	private double total;
	private int N;

	public void addDataValue(double val) {
		N++;
		total += val;
	}

	public double mean() {
		return total / N;
	}

	public String toString() {
		return "Mean (" + N + " values): " + String.format("%7.5f", mean());
	}

	public static void main (String[] args) {
		args = new String[] { "100" };
		int T = Integer.parseInt (args[0]);
		XAccumulator a = new XAccumulator ();
		for (int t = 0; t < T; t++)
			a.addDataValue (StdRandom.random ());
		StdOut.println (a);
	}
}
