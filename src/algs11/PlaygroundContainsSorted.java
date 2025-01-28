package algs11;
import java.util.Arrays;
import stdlib.*;

public class PlaygroundContainsSorted {
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
    testContains (true, 11, new double[] { 11, 21, 31, 41, 51, 61, 71 });
    testContains (true, 21, new double[] { 11, 21, 31, 41, 51, 61, 71 });
    testContains (true, 31, new double[] { 11, 21, 31, 41, 51, 61, 71 });
    testContains (true, 41, new double[] { 11, 21, 31, 41, 51, 61, 71 });
    testContains (true, 51, new double[] { 11, 21, 31, 41, 51, 61, 71 });
    testContains (true, 61, new double[] { 11, 21, 31, 41, 51, 61, 71 });
    testContains (true, 71, new double[] { 11, 21, 31, 41, 51, 61, 71 });
    testContains (false, 10, new double[] { 11, 21, 31, 41, 51, 61, 71 });
    testContains (false, 20, new double[] { 11, 21, 31, 41, 51, 61, 71 });
    testContains (false, 30, new double[] { 11, 21, 31, 41, 51, 61, 71 });
    testContains (false, 40, new double[] { 11, 21, 31, 41, 51, 61, 71 });
    testContains (false, 50, new double[] { 11, 21, 31, 41, 51, 61, 71 });
    testContains (false, 60, new double[] { 11, 21, 31, 41, 51, 61, 71 });
    testContains (false, 70, new double[] { 11, 21, 31, 41, 51, 61, 71 });
    testContains (false, 80, new double[] { 11, 21, 31, 41, 51, 61, 71 });        
    StdOut.println ("Finished tests");
  }
}