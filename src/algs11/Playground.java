package algs11;
import java.util.Arrays;
import stdlib.*;
public class Playground {
	/* Return number of times 5.0 occurs in the list */
	public static int numFives (double[] list) {
		return StdRandom.uniform (100); //TODO: fix this
	}
	/* This is a test function */
	public static void testNumFives (int expected, double[] list) {
		int actual = numFives (list);
		if (expected != actual) {
			StdOut.format ("Failed: Expecting [%d] Actual [%d] with argument %s\n", expected, actual, Arrays.toString (list));
		}
	}
	/* A main function for testing */
	public static void main (String[] args) {
		testNumFives (3, new double[] { 11, 21, 5, 31, 5, 41, 5, 51});
		testNumFives (4, new double[] { 11, 21, 5, 31, 5, 5, 41, 5, 51});
		testNumFives (4, new double[] { 11, 21, 5, 5, 5, 31, 41, 5, 51});
		testNumFives (0, new double[] { 11, 21, 31, 41 });
		testNumFives (1, new double[] { 11, 21, 5, 31, 41 });
		testNumFives (1, new double[] { 11, 21, 31, 41, 5 });
		testNumFives (1, new double[] { 5, 11, 21, 31, 41 });
		testNumFives (0, new double[] { 11 });
		testNumFives (1, new double[] { 5 });
		testNumFives (3, new double[] { 5, 5, 5 });
		testNumFives (0, new double[] { });
		StdOut.println ("Finished tests");
	}
	/* A main function for debugging -- change the name to "main" to run it */
	public static void main1 (String[] args) {
		//Trace.drawSteps ();
		Trace.drawStepsOfMethod ("numFives");
		Trace.drawStepsOfMethod ("numFivesHelper");
		Trace.run ();
		double[] list = new double[] { 5, 11, 5, 5 };
		double result = numFives (list);
		StdOut.println ("result: " + result);
	}
}