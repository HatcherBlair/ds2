package algs11;
import stdlib.*;
import java.util.Arrays;
public class XArrayFunctions {

	public static double average (double[] a) {
		int N = a.length;
		double sum = 0.0;
		for (int i = 0; i < N; i++)
			sum += a[i];
		double average = sum / N;
		return average;
	}

	public static double[] copy (double[] a) {
		int N = a.length;
		double[] b = new double[N];
		for (int i = 0; i < N; i++)
			b[i] = a[i];
		return b;
	}

	public static void reverse (double[] a) {
		int N = a.length;
		for (int i = 0; i < N / 2; i++) {
			double temp = a[i];
			a[i] = a[N - 1 - i];
			a[N - i - 1] = temp;
		}
	}

	public static double[][] multiply (double[][] a, double[][] b) {
		int N = a.length;
		double[][] c = new double[N][N];
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				// Compute dot product of row i and column j.
				for (int k = 0; k < N; k++)
					c[i][j] += a[i][k] * b[k][j];
		return c;
	}

	public static void main (String[] args) {
		double[] a = new double[] { 10, 20, 30, 40, 50 };

		StdOut.format ("average: %f\n", average (a));

		double[] b = copy (a);
		reverse (b);
		StdOut.format ("reverse: %s\n", Arrays.toString (b));
	}
}
