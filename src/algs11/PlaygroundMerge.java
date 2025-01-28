package algs11;
import java.util.Arrays;
import stdlib.*;

public class PlaygroundMerge {
	/* Merge a and b into a sorted result */
	public static double[] merge (double[] a, double[] b) {
		return null; //TODO: fix this
	}

	/* A main function for testing */
	public static void main (String[] args) {  
		//Trace.drawStepsOfMethod ("merge");
		//Trace.run ();
		testMerge ("11 13 15", "11 15", "13");
		testMerge ("11 13 15", "13", "11 15");
		testMerge ("11 12", "11", "12");
		testMerge ("11 12", "12", "11");
		testMerge ("11", "11", "");
		testMerge ("11", "", "11");
		testMerge ("", "", "");

		// this is an example of random testing:
		for (int numTests = 10; numTests>0; numTests--) {
			double[] a = ArrayGenerator.doubleRandom (StdRandom.uniform (10), 1000);
			double[] b = ArrayGenerator.doubleRandom (StdRandom.uniform (10), 1000);
			Arrays.sort (a);
			Arrays.sort (b);
			double[] result = new double[a.length+b.length];
			System.arraycopy (a, 0, result, 0, a.length);
			System.arraycopy (b, 0, result, a.length, b.length);
			Arrays.sort (result);
			testMerge (result, a, b);
		}
		StdOut.println ("Finished tests");
	}
	
	private static void testMerge (double[] expected, double[] a, double[] b) {
		double[] actual = merge (a, b);
		if (!Arrays.equals (expected, actual)) {
			StdOut.format ("Failed merge([%s],[%s]): Expecting (%s) Actual (%s)\n", Arrays.toString (a), Arrays.toString (b), Arrays.toString (expected), Arrays.toString (actual));
		}
	}
	private static void testMerge (String expected, String aList, String bList) {
		double[] a = doublesFromString (aList);
		double[] b = doublesFromString (bList);
		double[] actual = merge (a, b);
		if (!Arrays.equals (a, doublesFromString (aList))) {
			StdOut.format ("Failed merge([%s]): Array modified\n", aList);
		}
		if (!Arrays.equals (b, doublesFromString (bList))) {
			StdOut.format ("Failed merge([%s]): Array modified\n", bList);
		}
		double[] aExpected = doublesFromString (expected);
		if (!Arrays.equals (aExpected, actual)) {
			StdOut.format ("Failed merge([%s],[%s]): Expecting (%s) Actual (%s)\n", aList, bList, Arrays.toString (aExpected), Arrays.toString (actual));
		}
	}

	/* A utility function to create an array of doubles from a string. */
	// The string should include a list of numbers, separated by single spaces.
	private static double[] doublesFromString (String s) {
		if ("".equals (s)) return new double[0]; // empty array is a special case
		String[] nums = s.split (" ");
		double[] result = new double[nums.length];
		for (int i = nums.length - 1; i >= 0; i--) {
			try {
				result[i] = Double.parseDouble (nums[i]);
			} catch (NumberFormatException e) {
				throw new IllegalArgumentException (String.format ("Bad argument \"%s\": could not parse \"%s\" as a double", s, nums[i]));
			}
		}
		return result;
	}
}