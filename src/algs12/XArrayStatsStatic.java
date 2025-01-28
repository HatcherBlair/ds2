package algs12;
import stdlib.*;
import java.util.Arrays;

public class XArrayStatsStatic {
	public static double min (double[] array) {
		double theMin = Double.POSITIVE_INFINITY;
		for (double d : array) {
			if (d < theMin) theMin = d;
		}
		return theMin;
	}
	public static double max (double[] array) {
		double theMax = Double.NEGATIVE_INFINITY;
		for (double d : array) {
			if (d > theMax) theMax = d;
		}
		return theMax;
	}
	public static double mean (double[] array) {
		double theSum = 0;
		for (double d : array) {
			theSum += d;
		}
		return theSum/array.length;
	}

	public static void main (String[] args) {
		double[] a = { 2, 6, -5, 9, 8, -1, 0 };
		StdOut.println (Arrays.toString (a));
		StdOut.format ("min=%f, max=%f, mean=%f\n", min(a),  max(a),  mean(a) );
	}
}
