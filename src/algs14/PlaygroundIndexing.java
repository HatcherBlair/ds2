package algs14;
import java.util.*;
import stdlib.*;
public class PlaygroundIndexing {	
	public static void main (String[] args) {
		ArrayList<Integer> array =  new ArrayList<>();
		LinkedList<Integer> linked =  new LinkedList<>();
		int size = 60_000_000;
		for (int i=1; i<=size; i++) {
			array.add(i);
			linked.add(i);
		}
		
		int MIN = 256;
		int MAX = size;
		double prevTime = performanceLinked (linked, MIN);
		for (int N = MIN*2; N <= MAX; N=N*2) {
			double time = performanceLinked (linked, N);
			StdOut.format ("Linked elapsed count N=%,13d [%10.3f : %10.3f]\n", N, time, time/prevTime);
			prevTime = time;
		}

		MIN = 256;
		MAX = size;
		prevTime = performanceArray (array, MIN);
		for (int N = MIN*2; N <= MAX; N=N*2) {
			double time = performanceArray (array, N);
			StdOut.format ("Array  elapsed count N=%,13d [%10.3f : %10.3f]\n", N, time, time/prevTime);
			prevTime = time;
		}
	}
	private static double performanceLinked (LinkedList<Integer> linked, int N) {
		Stopwatch sw = new Stopwatch ();
		linked.get(N);
		return sw.elapsedTime ();
	}
	private static double performanceArray (ArrayList<Integer> linked, int N) {
		Stopwatch sw = new Stopwatch ();
		linked.get(N);
		return sw.elapsedTime ();
	}
	
}