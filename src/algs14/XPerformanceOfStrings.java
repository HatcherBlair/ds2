package algs14;
import stdlib.*;
public class XPerformanceOfStrings {
	/** create a string consisting of N asterisks */
	public static String makeStringUsingConcat (int N) {
		String result = "";
		for (int i=0; i<N; i+=1) {
			result = result + "*";
			ops += result.length();
		}
		return result;
	}
	/** create a string consisting of N asterisks */
	public static String makeStringUsingBuffer (int N) {
		StringBuffer result = new StringBuffer ();
		for (int i=0; i<N; i+=1) { 
			result.append ("*");
			ops += 1;
		}
		return result.toString ();
	}
	private static long ops;
	private static String result;
	public static void main(String[] args) {
		timeTrial(32);
		StdOut.println(result);
		
		int MIN = 256; 
		int MAX = 1_000_000_000;
		double prevTime = timeTrial(MIN);
		double prevOps = ops;
		for (int N = MIN*2; N<=MAX; N += N) {
			double time = timeTrial(N);
			StdOut.format ("Elapsed count f(%,13d): %,17d: %10.3f [%10.3f : %10.3f]\n", N, ops, ops / prevOps, time, time/prevTime);
			prevTime = time;
			prevOps = ops;
		}
	}
	public static double timeTrial(int N) {
		ops = 0;
		Stopwatch sw = new Stopwatch();
		result = makeStringUsingConcat(N);
		//result = makeStringUsingBuffer(N);		
		return sw.elapsedTime();
	}
}