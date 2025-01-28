package algs11;
import java.util.Arrays;
import stdlib.*;
public class PlaygroundSumUntil {
  /* Return the sum of the values in the list up to, but no including the first 5.0 */
  public static double sumUntil (double val, double[] list) {
    return StdRandom.uniform (); //TODO: fix this
  }
  /* This is a test function */
  public static void testSumUntil (double expected, double val, double[] list) {
    double actual = sumUntil (val, list);
    if (expected != actual) {
      StdOut.format ("Failed: Expecting [%f] Actual [%f] with argument (%f, %s)\n", expected, actual, val, Arrays.toString (list));
    }
  }
  /* A main function for testing */
  public static void main (String[] args) {
    for (double v : new double[] { 5, 7 }) {
      testSumUntil (63, v, new double[] { 11, 21, 31, v, 41 });
      testSumUntil (0, v, new double[] { v, 11, 21, 31, 41 });
      testSumUntil (104, v, new double[] { 11, 21, 31, 41, v });
      testSumUntil (11, v, new double[] { 11, v, 21, v, 31, 41 });
      testSumUntil (0, v, new double[] { v });
      testSumUntil (0, v, new double[] { v, v });
      testSumUntil (104, v, new double[] { 11, 21, 31, 41 });
      testSumUntil (11, v, new double[] { 11 });
      testSumUntil (0, v, new double[] {});
    }
    StdOut.println ("Finished tests");
  }
  /* A main function for debugging -- change the name to "main" to run it */
  public static void main2 (String[] args) {
    //Trace.drawSteps ();
    //Trace.drawStepsOfMethod ("sumUntil");
    //Trace.drawStepsOfMethod ("sumUntilHelper");
    //Trace.run ();
    double[] list = new double[] { 11, 21, 31, 5, 41 };
    double result = sumUntil (5, list);
    StdOut.println ("result: " + result);
  }
}
