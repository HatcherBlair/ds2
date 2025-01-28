package algs14;
import stdlib.*;
public class XCountingString {	
	// Test variants that resize additively or multiplicatively
	// int capacity = 1 + a.length;
	// int capacity = 2 * a.length;
	// int capacity = (int)Math.ceil(1.5 * a.length);
	private static char[] resize (char[] a) {
		int capacity = 1 + a.length;
		char[] result = new char[capacity]; 
		for (int i = 0; i < a.length; i = i + 1) {
			result[i] = a[i];
			numOps = numOps + 1;
		}
		return result;
	}
	// f creates a string of length N, counting the total size of all strings created
	public static String f (long N) {
		char[] a = new char[1];
		for (int i = 0; i < N; i = i + 1) {
			if (i >= a.length) 
				a = resize(a);
			a[i] = '*';
			numOps = numOps + 1;
		}		
		return String.valueOf(a); // creates a string in linear time
	}
	private static long numOps;
	public static void main (String[] args) {
		long MIN = 500L;
		long MAX = 32000L;
		Stopwatch sw = new Stopwatch ();
		numOps = 0;
		f(MIN);
		double prevCount = numOps;
		double prevTime = sw.elapsedTime ();
		for (long N = MIN*2; N <= MAX; N=N*2) {
			sw = new Stopwatch ();
			numOps = 0;
			f(N);
			long count = numOps; 
			double time = sw.elapsedTime ();
			StdOut.format ("Elapsed count f(%,13d): %,17d: %10.3f [%10.3f : %10.3f]\n", N, count, count / prevCount, time, time/prevTime);
			//StdOut.format ("Elapsed count f(%,13d): %,17d: %10.3f\n", N, count, count / prevCount);
			prevCount = count;
			prevTime = time;
		}
	}
}