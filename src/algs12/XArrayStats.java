package algs12;

import java.util.Arrays;
import stdlib.*;

public class XArrayStats {
	private double theMin;
	private double theMax;
	private double theMean;
	public XArrayStats (double[] array) {
		double min = Double.POSITIVE_INFINITY;
		double max = Double.NEGATIVE_INFINITY;
		double sum = 0;
		for (double d : array) {
			if (d < min) min = d;
			if (d > max) max = d;
			sum += d;
		}
		theMin = min;
		theMax = max;
		theMean = sum/array.length;
	}
	public double min  () { return theMin; }
	public double max  () { return theMax; }
	public double mean () { return theMean; }


	public static void main (String[] args) {
		double[] a = { 2, 6, -5, 9, 8, -1, 0 };
		XArrayStats stats = new XArrayStats (a);
		StdOut.println (Arrays.toString (a));
		StdOut.format ("min=%f, max=%f, mean=%f\n", stats.min(),  stats.max(),  stats.mean() );
	}
}
