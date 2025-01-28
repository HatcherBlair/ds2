package algs11;
import java.util.Arrays;
import stdlib.*;
public class PlaygroundLongestSequenceOf5s {
	/* Return the length of the longest contiguous sequence of 5.0s in the list */
	public static int longestSequenceOf5s (double[] list) {
		return StdRandom.uniform (100); //TODO: fix this
	}
	/* This is a test function */
	public static void testLongestSequence (int expected, double[] list) {
		int actual = longestSequenceOf5s (list);
		if (expected != actual) {
			StdOut.format ("Failed: Expecting [%d] Actual [%d] with argument %s\n", expected, actual, Arrays.toString (list));
		}
	}
	/* A main function for testing */
	public static void main (String[] args) {
		testLongestSequence (3, new double[] { 1, 5, 5, 1, 1, 5, 5, 5, 1, 5, 5, 1});
		testLongestSequence (4, new double[] { 1, 5, 1, 5, 5, 5, 5, 1, 5});
		testLongestSequence (2, new double[] { 5, 5, 1, 1, 5, 5, 1, 5, 5});
		testLongestSequence (4, new double[] { 1, 5, 1, 5, 5, 5, 5, 1, 5, 1});
		testLongestSequence (3, new double[] { 1, 5, 5, 5, 1, 5, 1});
		testLongestSequence (0, new double[] { 1 });
		testLongestSequence (1, new double[] { 5 });
		testLongestSequence (3, new double[] { 5, 5, 5 });
		testLongestSequence (0, new double[] { });
		StdOut.println ("Finished tests");
	}
	/* A main function for debugging -- change the name to "main" to run it */
	public static void main2 (String[] args) {
		//Trace.drawSteps ();
		Trace.drawStepsOfMethod ("longestSequenceOf5s");
		Trace.drawStepsOfMethod ("longestSequenceOf5sHelper");
		Trace.run ();
		double[] list = new double[] { 5, 11, 5, 5 };
		double result = longestSequenceOf5s (list);
		StdOut.println ("result: " + result);
	}
}
