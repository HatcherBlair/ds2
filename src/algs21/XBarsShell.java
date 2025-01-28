package algs21;
import stdlib.*;
import java.awt.Font;
/* ***********************************************************************
 *  Compilation:  javac ZShellBars.java
 *  Execution:    java ZShellBars N
 *  Dependencies: StdDraw.java
 *
 *  Shellsor N random real numbers between 0 and 1, visualizing
 *  the results by ploting bars with heights proportional to the values.
 *
 *  % java ZShellBars 150
 *
 *************************************************************************/

public class XBarsShell {
	private static final int FF = 4;


	public static void sort(double[] a) {
		int N = a.length;
		int k = 1;
		int h = 1;
		while (h < N/3) {
			h = 3*h + 1;
			k++;
		}
		show(a, k, "input");

		while (h >= 1) {
			for (int i = h; i < N; i++) {
				for (int j = i; j >= h && less(a[j], a[j-h]); j -= h) {
					exch(a, j, j-h);
				}
			}
			if (h < N) show(a, --k, h + "-sorted");
			h = h/3;
		}
	}

	private static void show(double[] a, int k, String message) {
		for (int i = 0; i < a.length; i++) {
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.line(i, FF*k, i, FF*k + a[i]*(FF-1));
		}
		StdDraw.setPenColor(StdDraw.BOOK_RED);
		StdDraw.text(0, FF*k - .3, message);
	}

	private static boolean less(double v, double w) {
		return v < w;
	}

	private static void exch(double[] a, int i, int j) {
		double t = a[i];
		a[i] = a[j];
		a[j] = t;
	}


	public static void main(String[] args) {
		args = new String[] { "120" };
		int N = Integer.parseInt(args[0]);
		int k = (int) Math.round(Math.log(N) / Math.log(3));
		StdDraw.show(0);
		StdDraw.setCanvasSize(756, 900);
		StdDraw.setFont(new Font("SansSerif", Font.PLAIN, 9));
		StdDraw.setXscale(-1, N+1);
		StdDraw.setYscale(-1, (1+FF)*k);
		StdDraw.setPenRadius(.005);
		sort (ArrayGenerator.doubleRandomUnique (N));
		//sort (ArrayGenerator.partiallySortedUnique (N));
		//sort (ArrayGenerator.sortedUnique (N));
		//sort (ArrayGenerator.reverseSortedUnique (N));
		StdDraw.show(0);
	}
}
