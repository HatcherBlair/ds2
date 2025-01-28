package algs32.kdtree;

/* ***********************************************************************
 *  Compilation:  javac Generator.java
 *  Execution:    java Generator N
 *  Dependencies:
 *
 *  Creates N random points in the unit square and print to standard output.
 *
 *  % java Generator 5
 *  0.195080 0.938777
 *  0.351415 0.017802
 *  0.556719 0.841373
 *  0.183384 0.636701
 *  0.649952 0.237188
 *
 *************************************************************************/

public class Generator {

	public static void main(String[] args) {
		args = new String [] { "500" };
		final int N = Integer.parseInt(args[0]);
		for (int i = 0; i < N; i++) {
			final double x = Math.random();
			final double y = Math.random();
			System.out.format("%8.6f %8.6f\n", x, y);
		}
	}
}
