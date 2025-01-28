package algs14;
import java.util.Arrays;
import stdlib.*;

public class PlaygroundSearch {
	/* Return true if val is in the list */
	public static boolean contains (double val, double[] list) {
		return StdRandom.bernoulli(); // TODO
	}

	public static void main (String[] args) {
		//printTest(1023);
		correctnessTest();
		performanceTest();
	}	
	private static void correctnessTest () {    
		correctnessHelper (true,  11, new double[] { 11, 21, 31, 41, 51, 61, 71 });
		correctnessHelper (true,  21, new double[] { 11, 21, 31, 41, 51, 61, 71 });
		correctnessHelper (true,  31, new double[] { 11, 21, 31, 41, 51, 61, 71 });
		correctnessHelper (true,  41, new double[] { 11, 21, 31, 41, 51, 61, 71 });
		correctnessHelper (true,  51, new double[] { 11, 21, 31, 41, 51, 61, 71 });
		correctnessHelper (true,  61, new double[] { 11, 21, 31, 41, 51, 61, 71 });
		correctnessHelper (true,  71, new double[] { 11, 21, 31, 41, 51, 61, 71 });
		correctnessHelper (false, 10, new double[] { 11, 21, 31, 41, 51, 61, 71 });
		correctnessHelper (false, 20, new double[] { 11, 21, 31, 41, 51, 61, 71 });
		correctnessHelper (false, 30, new double[] { 11, 21, 31, 41, 51, 61, 71 });
		correctnessHelper (false, 40, new double[] { 11, 21, 31, 41, 51, 61, 71 });
		correctnessHelper (false, 50, new double[] { 11, 21, 31, 41, 51, 61, 71 });
		correctnessHelper (false, 60, new double[] { 11, 21, 31, 41, 51, 61, 71 });
		correctnessHelper (false, 70, new double[] { 11, 21, 31, 41, 51, 61, 71 });
		correctnessHelper (false, 80, new double[] { 11, 21, 31, 41, 51, 61, 71 });        
		correctnessHelper (true,  11, new double[] { 11, 21, 31, 41, 51, 61 });
		correctnessHelper (true,  21, new double[] { 11, 21, 31, 41, 51, 61 });
		correctnessHelper (true,  31, new double[] { 11, 21, 31, 41, 51, 61 });
		correctnessHelper (true,  41, new double[] { 11, 21, 31, 41, 51, 61 });
		correctnessHelper (true,  51, new double[] { 11, 21, 31, 41, 51, 61 });
		correctnessHelper (true,  61, new double[] { 11, 21, 31, 41, 51, 61 });
		correctnessHelper (false, 10, new double[] { 11, 21, 31, 41, 51, 61 });
		correctnessHelper (false, 20, new double[] { 11, 21, 31, 41, 51, 61 });
		correctnessHelper (false, 30, new double[] { 11, 21, 31, 41, 51, 61 });
		correctnessHelper (false, 40, new double[] { 11, 21, 31, 41, 51, 61 });
		correctnessHelper (false, 50, new double[] { 11, 21, 31, 41, 51, 61 });
		correctnessHelper (false, 60, new double[] { 11, 21, 31, 41, 51, 61 });
		correctnessHelper (false, 70, new double[] { 11, 21, 31, 41, 51, 61 });
		correctnessHelper (false,  0, new double[] { });        
		correctnessHelper (true,  11, new double[] { 11 });        
		correctnessHelper (false, 10, new double[] { 11 });        
		correctnessHelper (false, 20, new double[] { 11 }); 
		correctnessHelper (true,  11, new double[] { 11, 21 });        
		correctnessHelper (true,  21, new double[] { 11, 21 });        
		correctnessHelper (false, 10, new double[] { 11, 21 });        
		correctnessHelper (false, 20, new double[] { 11, 21 }); 
		correctnessHelper (false, 30, new double[] { 11, 21 }); 
		StdOut.println ("Finished tests");
	}
	private static void correctnessHelper (boolean expected, double val, double[] list) {
		boolean actual = contains (val, list);
		if (expected != actual) {
			StdOut.format ("Failed: Expecting [%b] Actual [%b] with argument (%f, %s)\n", expected, actual, val, Arrays.toString (list));
		}
	}
	private static void performanceTest () {
		int MIN = 256; // for powers of ten, start with 500L
		int MAX = 320000000;
		double prevTime = performanceHelper(MIN);
		for (int N = MIN*2; N <= MAX; N=N*2) {
			double time = performanceHelper(N);
			StdOut.format ("Elapsed count N=%,13d [%10.3f : %10.3f]\n", N, time, time/prevTime);
			prevTime = time;
		}
	}
	private static double performanceHelper (int N) {
		double[] list = ArrayGenerator.doubleRandomUnique(N);
		//double val = StdRandom.uniform (N); // gauranteed to succeed
		double val = N; // gauranteed to fail
		Stopwatch sw = new Stopwatch ();
		contains (val, list);
		return sw.elapsedTime ();
	}
	
	// To print values, paste these in the code:
	// StdOut.format("%4d/%4d \n", lo, hi);
	// StdOut.format("%4d ", hi-lo+1);
	private static void printTest (int N) {
		double[] list = ArrayGenerator.doubleSortedUnique(N);
		StdOut.println (Arrays.toString(list).substring(0, 50) + "...");
		int found = 0;
		int count = 10;
		for (int i=1; i<=count; i++) {
			double val = StdRandom.uniform (N+1);
			if (StdRandom.bernoulli()) 
				val = val - 0.5;
			if (contains (val, list))
				found = found + 1;
			StdOut.println();
		}
		StdOut.format("found %4d/%4d", found, count);
	}	
}