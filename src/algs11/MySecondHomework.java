package algs11;

import java.util.Arrays;
import stdlib.*;

/**
 * This is a skeleton file for your homework. Edit the sections marked TODO. You
 * may add new functions. You may also edit the function "main" to test your
 * code.
 *
 * You must not add static variables. You MAY add static functions, just not
 * static variables.
 *
 * It is okay to add functions, such as
 *
 * <pre>
 *     public static double sumHelper (double[] list, int i, double sumSoFar) {
 * </pre>
 *
 * but it is NOT okay to add static variables, such as
 *
 * <pre>
 * public static int x;
 * </pre>
 *
 * As for homework 1, you must not change the declaration of any method.
 * 
 * You can edit the main function all you want. I will not run your main
 * function when grading.
 */
public class MySecondHomework {

	/**
	 * As a model, here is a minValue function, both iteratively and recursively
	 */
	/** iterative version */
	public static double minValueI (double[] list) {
		double result = list[0];
		int i = 1;
		while (i < list.length) {
			if (list[i] < result) result = list[i];
			i = i + 1;
		}
		return result;
	}

	/** recursive version */
	public static double minValue (double[] list) {
		return minValueHelper (list, 1, list[0]);
	}
	private static double minValueHelper (double[] list, int i, double result) {
		if (i < list.length) {
			if (list[i] < result) result = list[i];
			result = minValueHelper (list, i + 1, result);
		}
		return result;
	}

	/**
	 * PROBLEM 1: Translate the following sum function from iterative to
	 * recursive.
	 *
	 * You should write a helper method. You may not use any "fields" to solve
	 * this problem (a field is a variable that is declared "outside" of the
	 * function declaration --- either before or after).
	 */
	public static double sumI (double[] a) {
		double result = 0.0;
		int i = 0;
		while (i < a.length) {
			result = result + a[i];
			i = i + 1;
		}
		return result;
	}
	public static double sum (double[] a) {
		// TODO
		return StdRandom.uniform ();
	}

	/**
	 * PROBLEM 2: Do the same translation for this in-place reverse function
	 *
	 * You should write a helper method. You may not use any "fields" to solve
	 * this problem (a field is a variable that is declared "outside" of the
	 * function declaration --- either before or after).
	 */
	public static void reverseI (double[] a) {
		int hi = a.length - 1;
		int lo = 0;
		while (lo < hi) {
			double loVal = a[lo];
			double hiVal = a[hi];
			a[hi] = loVal;
			a[lo] = hiVal;
			lo = lo + 1;
			hi = hi - 1;
		}
	}
	public static void reverse (double[] a) {
		// TODO
	}

	/**
	 * PROBLEM 3: The following function draws mickey mouse, if you call it like
	 * this from main:
	 *
	 * <pre>
	 * draw (.5, .5, .25);
	 * </pre>
	 *
	 * Change the code to draw mickey moose instead. Your solution should be
	 * recursive.
	 * 
	 * Before picture:
	 * http://fpl.cs.depaul.edu/jriely/ds1/images/MickeyMouse.png After picture:
	 * http://fpl.cs.depaul.edu/jriely/ds1/images/MickeyMoose.png
	 *
	 * You may not use any "fields" to solve this problem (a field is a variable
	 * that is declared "outside" of the function declaration --- either before
	 * or after).
	 */
	public static void draw (double centerX, double centerY, double radius) {
		// TODO 
		if (radius < .0005) return;

		StdDraw.setPenColor (StdDraw.LIGHT_GRAY);
		StdDraw.filledCircle (centerX, centerY, radius);
		StdDraw.setPenColor (StdDraw.BLACK);
		StdDraw.circle (centerX, centerY, radius);

		double change = radius * 0.90;

		StdDraw.setPenColor (StdDraw.LIGHT_GRAY);
		StdDraw.filledCircle (centerX + change, centerY + change, radius / 2);
		StdDraw.setPenColor (StdDraw.BLACK);
		StdDraw.circle (centerX + change, centerY + change, radius / 2);

		StdDraw.setPenColor (StdDraw.LIGHT_GRAY);
		StdDraw.filledCircle (centerX - change, centerY + change, radius / 2);
		StdDraw.setPenColor (StdDraw.BLACK);
		StdDraw.circle (centerX - change, centerY + change, radius / 2);
	}

	/**
	 * PROBLEM 4: Run runTerribleLoop for one hour. You can stop the program
	 * using the red "stop" square in eclipse. Fill in the OUTPUT line below
	 * with the numbers you saw LAST --- edit the line, replacing the two ...
	 * with what you saw:
	 *
	 * OUTPUT: terribleFibonacci(...)=... // TODO
	 *
	 * Comment: the code uses "long" variables, which are like "int", but
	 * bigger. It's because fibonacci numbers get really big really fast.
	 */
	public static void runTerribleLoop () {
		for (int N = 0; N < 100; N++)
			StdOut.format ("terribleFibonacci(%2d)=%d\n", N, terribleFibonacci (N));
	}
	public static long terribleFibonacci (int n) {
		if (n <= 1) return n;
		return terribleFibonacci (n - 1) + terribleFibonacci (n - 2);
	}

	/**
	 * PROBLEM 5: The implementation of terribleFibonacci is TERRIBLE! Write a
	 * more efficient version of fibonacci. Do not change runFibonacciLoop or
	 * runFibonacciSomeValues.
	 *
	 * To make fibonacci run faster, you want it so that each call to
	 * fibonacci(n) computes the fibonacci numbers between 0 and n once, not
	 * over and over again.
	 *
	 * Comment: You will want to use a local variable of type "long" rather than
	 * type "int", for the reasons discussed above.
	 *
	 * Comment: At some point, your fibonacci numbers might become negative.
	 * This is normal and expected.
	 * http://en.wikipedia.org/wiki/Integer_overflow We discuss this at length
	 * in our systems classes.
	 *
	 * You may not use any "fields" to solve this problem (a field is a variable
	 * that is declared "outside" of the function declaration --- either before
	 * or after).
	 * 
	 * You may use a loop on this problem.
	 * You do not need to use recursion.
	 */
	public static void runFibonacciLoop () {
		for (int N = 0; N < 100; N++)
			StdOut.format ("fibonacci(%2d)=%d\n", N, fibonacci (N));
	}
	public static long fibonacci (int n) {
		return 0; // TODO
	}

	/**
	 * A test program, using private helper functions.  See below.
	 * To make typing tests a little easier, I've written a function to convert strings to arrays.  See below.
	 * You can modify this -- it is not graded.
	 */
	public static void main (String[] args) {
		testSum ("11 21 81 -41 51 61");
		testSum ("11 21 81 -41 51");
		testSum ("11 21 81 -41");
		testSum ("11 21 81");
		testSum ("11 21");
		testSum ("11");
		testSum ("");

		testReverse ("11 21 81 -41 51 61");
		testReverse ("11 21 81 -41 51");
		testReverse ("11 21 81 -41");
		testReverse ("11 21 81");
		testReverse ("11 21");
		testReverse ("11");
		testReverse ("");

		testFibonacci (0, 0);
		testFibonacci (1, 1);
		testFibonacci (1, 2);
		testFibonacci (2, 3);
		testFibonacci (21, 8);
		testFibonacci (233, 13);
		testFibonacci (75025, 25);
		testFibonacci (             1_836_311_903L, 46);
		testFibonacci (             2_971_215_073L, 47);
		testFibonacci (       308_061_521_170_129L, 71); 
		testFibonacci (       498_454_011_879_264L, 72);
		testFibonacci ( 7_540_113_804_746_346_429L, 92);
		testFibonacci (-6_246_583_658_587_674_878L, 93);
		testFibonacci (  -813_251_414_217_914_645L, 376);
		StdOut.println ("Finished tests");

		draw (.5, .5, .25);

		// TODO: uncomment these temporarily when you want to see the output of your Fibonacci functions
		//runTerribleLoop ();	
		//runFibonacciLoop();		
	}
	
	/*
	 * A main function for debugging -- change the name to "main" to run it (and
	 * rename the existing main method to something else). Change the test as
	 * appropriate.
	 */
	public static void main1 (String[] args) {
		Trace.drawStepsOfMethod ("minValueI");
		Trace.drawStepsOfMethod ("minValue");
		Trace.drawStepsOfMethod ("minValueHelper");
		Trace.run ();
		testMinValue ("11 21 9 31 41");
	}

	/* Test functions --- lot's of similar code! */
	private static void testSum (String list) {
		double[] aList = doublesFromString (list);
		double expected = sumI (aList);
		double actual = sum (aList);
		if (! Arrays.equals (aList, doublesFromString (list))) {
			StdOut.format ("Failed sum([%s]): Array modified\n", list);
		}
		if (expected != actual) {
			StdOut.format ("Failed sum([%s]): Expecting (%.1f) Actual (%.1f)\n", list, expected, actual);
		}
	}
	private static void testMinValue (String list) {
		double[] aList = doublesFromString (list);
		double expected = minValueI (aList);
		double actual = minValue (aList);
		if (! Arrays.equals (aList, doublesFromString (list))) {
			StdOut.format ("Failed minValue([%s]): Array modified\n", list);
		}
		if (expected != actual) {
			StdOut.format ("Failed minValue([%s]): Expecting (%.1f) Actual (%.1f)\n", list, expected, actual);
		}
	}
	private static void testReverse (String list) {
		double[] expected = doublesFromString (list);
		reverseI (expected);
		double[] actual = doublesFromString (list);
		reverse (actual);
		// != does not do what we want on arrays
		if (! Arrays.equals (expected, actual)) {
			StdOut.format ("Failed reverse([%s]): Expecting (%s) Actual (%s)\n", list, Arrays.toString (expected), Arrays.toString (actual));
		}
	}
	private static void testFibonacci (long expected, int n) {
		long actual = fibonacci (n);
		if (expected != actual) {
			StdOut.format ("Failed fibonacci(%d): Expecting (%d) Actual (%d)\n", n, expected, actual);
		}
	}

	/* A utility function to create an array of doubles from a string. */
	// The string should include a list of numbers, separated by single spaces.
	private static double[] doublesFromString (String s) {
		if ("".equals (s)) return new double [0]; // empty array is a special case
		String[] nums = s.split (" ");
		double[] result = new double[nums.length];
		for (int i = nums.length-1; i >= 0; i--) {
			try { 
				result[i] = Double.parseDouble (nums[i]); 
			} catch (NumberFormatException e) { 
				throw new IllegalArgumentException (String.format ("Bad argument \"%s\": could not parse \"%s\" as a double", s, nums[i]));
			}
		}
		return result;
	}
}
