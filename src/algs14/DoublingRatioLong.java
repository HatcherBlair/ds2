package algs14;
import stdlib.*;
/* ***********************************************************************
 *  Compilation:  javac DoublingRatioLong.java
 *  Execution:    java DoublingRatioLong
 *  Dependencies: Stopwatch.java StdOut.java
 *
 *  This version is suited for testing functions that require very large N
 *  to run long enough to measure.
 *
 *  % java DoublingRatioLong
 *      512 6.48
 *     1024 8.30
 *     2048 7.75
 *     4096 8.00
 *     8192 8.05
 *   ...
 *
 *************************************************************************/

public class DoublingRatioLong {

	static private long f0 (long N) {
		long sum =0;
		return sum;
	}

	static private long f1 (long N) {
		long sum = 0; // 1
		for (long i = 1; i <= N; i += 1) { // 3*N + 1
			sum =sum +1;  // 1
		}
		// 3N + 2... 
		// ~N
		return sum;
	}
	static private long f2(long N) {
		long sum = 0;
		for (long i = 1; i <= N; i += 1) {
			for (long j = i; j <= N; j += 1) {
				sum++;
			}
		}
		return sum;
	}

	static private long f21(long N) {
		long sum = 0;
		for (long i = 1; i <= N; i += 1) {
			for (long j = 1; j <= N; j += 1) {
				sum++;
			}
		}
		// ~N^2
		return sum;
	}
	static private long f22(long N) {
		long sum = 0;
		for (long i = 1; i <= N; i += 1) {
			for (long j = 1; j <= N; j *= 2) { //lg N
				sum++;
			}
		}
		// N log N
		return sum;
	}

	// a is sorted in ascending order
	static boolean binSearch(int[] a, int key) {
		return binAux(a, key, 0, a.length-1);
	}

	// search a for key between indices lo and hi
	static boolean binAux(int[] a, int key, int lo, int hi){
		if (lo > hi) return false;
		int mid = (lo + hi) / 2;
		if (key < a[mid]) return binAux(a, key, lo, mid-1);
		if (key > a[mid]) return binAux(a, key, mid+1, hi);
		return true;
	}

	// write the following versions
	// without recursion

	static boolean IterBinsearch(int[] a, int key) {
		int lo = 0;
		int hi = a.length-1;
		while (lo <= hi) {
			int mid = (lo + hi) / 2;
			if (key < a[mid]) hi = mid-1;
			else if (key > a[mid]) lo = mid+1;
			else return true;
		}
		return false;
	}

	// static int binSearch(int[] a, int key).  Returns the index of first key in a that is >= key
	// [1,2,3,8,9], 
	// Key = 3.  Answer is 2
	// Key = 4.  Answer is 3
	// Key is 2.5.  Answer is 2



	static private long f3(long N) {
		long sum = 0;
		for (long i = 1; i <= N; i += 1) {
			for (long j = i; j <= N; j += 1) {
				for (long k = j; k <= N; k += 1) {
					sum++;
				}
			}
		}
		return sum;
	}
	public static double timeTrial(long N) { // how long for N  
		long T = 10; // number of tests
		double sum = 0;
		for (long t = 0; t < T; t++) {
			Stopwatch s = new Stopwatch();
			
			f22(N);  
			sum +=  s.elapsedTime();
		}
		return sum/T;
	}

	private static final long MIN = 10;
	// private static final long MAX = 40960L;
	private static final long MAX = Long.MAX_VALUE/2;
	public static void main(String[] args) {
		double prev = timeTrial(MIN);
		for (long N = MIN*2; N<=MAX; N += N) {
			double time = timeTrial(N);
			StdOut.format("%19d %9.3f %5.1f\n", N, time, time/prev);
			prev = time;
		}
	}
}

