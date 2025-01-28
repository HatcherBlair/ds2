package algs14;
import java.util.function.Function;
import stdlib.*;

public class DoublingImproved {

	private static final int MAXVAL = 10000;
	public static void main(String[] args) {
		Function<Integer, int[]> generator = N -> ArrayGenerator.intRandom (N, -MAXVAL, MAXVAL);
		StdOut.println ("One");
		DoublingTest.run (200000, 8, 2, generator, (int[] x) -> XOneSum.count (x));

		StdOut.println ("Two");
		DoublingTest.run (200, 8, 2, generator, (int[] x) -> XTwoSum.count (x));

		StdOut.println ("Three");
		DoublingTest.run (200, 5, 2, generator, (int[] x) -> ThreeSum.count(x));

		StdOut.println ("Four");
		DoublingTest.run (200, 3, 2, generator, (int[] x) -> XFourSum.count(x));

		StdOut.println ("Two Fast");
		DoublingTest.run (200, 8, 2, generator, (int[] x) -> XTwoSumFast.count (x));

		StdOut.println ("Three Fast");
		DoublingTest.run (200, 5, 2, generator, (int[] x) -> ThreeSumFast.count (x));
	}
}