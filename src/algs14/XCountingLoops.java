package algs14;
import stdlib.*;
public class XCountingLoops {
	// To Print: StdOut.printf ("N=%3d i=%3d N-i=%d\n", N, i, N-i);
	//
	// Test variant with one, two or three nested loops
	//
	// Outermost:
	// for (long i = 1; i <= N; i = i+1) {
	// for (long i = 1; i <= N; i = i*2) {
	//
	// Next:
	// for (long j = 1; j <= N; j = j+1) {
	// for (long j = 1; j <= i; j = j+1) {
	// for (long j = 1; j <= N; j = j*2) {
	// for (long j = 1; j <= i; j = j*2) {
	//
	// Next:
	// for (long k = 1; k <= N; k = k+1) {
	// for (long k = 1; k <= j; k = k+1) {
	// for (long k = 1; k <= N; k = k*2) {
	// for (long k = 1; k <= j; k = k*2) {

	// f counts the number of times the innermost loop executes 
	static long f (long N) {
		long result = 0;
		for (long i = 1; i <= N; i = i+1) {
			for (long j = 1; j <= N; j = j+1) {
				result = result+1;
				if (N <= 64) StdOut.format ("%02d ", i);
			}
			if (N <= 64) StdOut.println ();
		}
		return result;
	}
	public static void main (String[] args) {
		f(16);

		long MIN = 256L; // for powers of ten, start with 500L
		long MAX = 3_200_000_000L;
		Stopwatch sw = new Stopwatch ();
		double prevCount = f(MIN);
		double prevTime = sw.elapsedTime ();
		for (long N = MIN*2; N <= MAX; N=N*2) {
			sw = new Stopwatch ();
			long count = f(N);
			double time = sw.elapsedTime ();
			StdOut.format ("Elapsed count f(%,13d): %,16d: %10.3f [%10.3f : %10.3f]\n", N, count, count / prevCount, time, time/prevTime);
			//StdOut.format ("Elapsed count f(%,13d): %,16d: %10.3f\n", N, count, count / prevCount);
			prevCount = count;
			prevTime = time;
		}
	}
}