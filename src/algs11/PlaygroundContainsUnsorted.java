package algs11;
import java.util.Arrays;
import stdlib.*;

public class PlaygroundContainsUnsorted {
  /* Return true if val is in the list */
  public static boolean contains (double val, double[] list) {
    return StdRandom.bernoulli (); //TODO: fix this
  }
  /* This is a test function */
  public static void testContains (boolean expected, double val, double[] list) {
    boolean actual = contains (val, list);
    if (expected != actual) {
      StdOut.format ("Failed: Expecting [%b] Actual [%b] with argument (%f, %s)\n", expected, actual, val, Arrays.toString (list));
    }
  }
  /* A main function for testing */
  public static void main (String[] args) {        
    for (double v : new double[] { 5, 7 }) {
      testContains (true, v, new double[] { 11, 21, 31, v, 41 });
      testContains (true, v, new double[] { v, 11, 21, 31, 41 });
      testContains (true, v, new double[] { 11, 21, 31, 41, v });
      testContains (true, v, new double[] { 11, v, 21, v, 31, 41 });
      testContains (true, v, new double[] { v });
      testContains (true, v, new double[] { v, v });
      testContains (false, v, new double[] { 11, 21, 31, 41 });
      testContains (false, v, new double[] { 11 });
      testContains (false, v, new double[] {});
    }
    StdOut.println ("Finished tests");
  }
}