package algs14;
import stdlib.*;
public class XPrintingLoops {
	// To Print: 
	//
	// Test variant with one, two or three nested loops
	//
	// Outermost:
	// for (long i = 1; i <= N; i = i+1)
	// for (long i = 1; i <= N; i = i*2)
	//
	// Next:
	// for (long j = 1; j <= N; j = j+1)
	// for (long j = 1; j <= i; j = j+1)
	// for (long j = 1; j <= N; j = j*2)
	// for (long j = 1; j <= i; j = j*2)
	//
	// Next:
	// for (long k = 1; k <= N; k = k+1)
	// for (long k = 1; k <= j; k = k+1)
	// for (long k = 1; k <= N; k = k*2)
	// for (long k = 1; k <= j; k = k*2)

	public static long timesTwoSum (int N, boolean print) {
		long count = 0;		
		for (long i = 1; i <= N; i = i*2) {
			for (long j = 1; j <= i; j = j+1) {
				if (print) StdOut.printf ("%02d ", i);
				count += 1;
			}
			if (print) StdOut.println ();
		}
		return count;
	}
	public static long plusOneSum (int N, boolean print) {
		long count = 0;		
		for (long i = 1; i <= N; i = i+1) {
			for (long j = 1; j <= i; j = j+1) {
				if (print) StdOut.printf ("%02d ", i);
				count += 1;
			}
			if (print) StdOut.println ();
		}
		return count;
	}
	public static void main (String[] args) {
		plusOneSum(16, true);
		timesTwoSum(16, true);
		for (int N=1; N<=524288; N*=2) {
			long p1 = plusOneSum(N, false);
			long t2 = timesTwoSum(N, false);
			StdOut.format("N=%6d t2=%,10d p1=%,15d\n", N, t2, p1);
		}
	}
}