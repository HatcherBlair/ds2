package algs12;
import stdlib.*;
/* ***********************************************************************
 *  Compilation:  javac VisualAccumulator.java
 *
 *************************************************************************/


public class XVisualAccumulator {
	private double total;
	private int N;

	public XVisualAccumulator(int trials, double max) {
		StdDraw.setXscale(0, trials);
		StdDraw.setYscale(0, max);
		StdDraw.setPenRadius(.005);
	}

	public void addDataValue(double val) {
		N++;
		total += val;
		StdDraw.setPenColor(StdDraw.DARK_GRAY);
		StdDraw.point(N, val);
		StdDraw.setPenColor(StdDraw.RED);
		StdDraw.point(N, mean());
	}

	public double mean() {
		return total / N;
	}

	public String toString() {
		return "Mean (" + N + " values): " + String.format("%8.5f", mean());
	}

	public static void main (String[] args) {
		args = new String[] { "1000" };
		int T = Integer.parseInt (args[0]);
		XVisualAccumulator a = new XVisualAccumulator (T, 1.0);
		for (int t = 0; t < T; t++)
			a.addDataValue (StdRandom.random ());
		StdOut.println (a);
	}
}
