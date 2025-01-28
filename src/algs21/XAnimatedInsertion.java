package algs21;
import stdlib.*;

public class XAnimatedInsertion {

	public static void sort (double[] a) {
		int N = a.length;
		show (a, 0, 0);
		for (int i = 0; i < N; i++) {
			for (int j = i; j > 0 && less (a[j], a[j - 1]); j--) {
				exch (a, j, j - 1);
				show (a, i, j - 1);
			}
			assert isSorted (a, 0, i);
		}
		assert isSorted (a);
	}

	private static void show (double[] a, int i, int min) {
		StdDraw.clear ();
		//StdDraw.setYscale(-a.length + i + 1, i);
		StdDraw.setPenColor (StdDraw.LIGHT_GRAY);
		for (int k = 0; k < i; k++)
			StdDraw.line (k, 0, k, a[k]);
		StdDraw.setPenColor (StdDraw.BLACK);
		for (int k = i; k < a.length; k++)
			StdDraw.line (k, 0, k, a[k]);
		StdDraw.setPenColor (StdDraw.BOOK_RED);
		StdDraw.line (min, 0, min, a[min]);
		StdDraw.show (100);
	}

	private static boolean less (double v, double w) {
		return v < w;
	}
	private static void exch (double[] a, int i, int j) {
		double t = a[i];
		a[i] = a[j];
		a[j] = t;
	}
	private static boolean isSorted (double[] a) {
		return isSorted (a, 0, a.length - 1);
	}
	private static boolean isSorted (double[] a, int lo, int hi) {
		for (int i = lo + 1; i <= hi; i++)
			if (less (a[i], a[i - 1])) return false;
		return true;
	}

	public static void main (String[] args) {
		args = new String[] { "25" };
		int N = Integer.parseInt (args[0]);
		StdDraw.setCanvasSize (N*7, 320);
		StdDraw.setXscale (-1, N + 1);
		StdDraw.setPenRadius (.006);
		sort (ArrayGenerator.doublePartiallySortedUnique (N));
		sort (ArrayGenerator.doubleRandomUnique (N));
		sort (ArrayGenerator.doubleSortedUnique (N));
		sort (ArrayGenerator.doubleReverseSortedUnique (N));
	}

}
