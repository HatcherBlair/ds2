package algs14;
import stdlib.*;

public class XPerformanceOfArrays {
	private double[] a;
	private int N;
	public XPerformanceOfArrays() {
		this.a = new double[1];
		this.N = 0;
	}
	private void resize(int capacity) {
		double[] temp = new double[capacity];
		for (int i = 0; i < N; i+=1) {
			if (SHOW) StdOut.format ("%02d ", i);
			temp[i] = a[i];
			ops += 1;
		}
		if (SHOW) StdOut.println();
		a = temp;
	}
	public void push(double item) {
		if (N == a.length) resize (1+N); //resize((int)Math.ceil (N*1.5));
		a[N] = item;
		N += 1;
		ops += 1;
	}
	
	private static long ops;
	private static boolean SHOW = true;
	public static void main(String[] args) {
		timeTrial(32);
		SHOW = false;
		
		int MIN = 256; 
		int MAX = 100_000_000;
		double prevTime = timeTrial(MIN);
		double prevOps = ops;
		double deltaSum = 0;
		int deltaNum = 0;
		for (int N = MIN*2; N<=MAX; N += N) {
			double time = timeTrial(N);
			StdOut.format ("Elapsed count f(%,13d): %,17d: %10.3f [%10.3f : %10.3f]\n", N, ops, ops / prevOps, time, time/prevTime);
			prevTime = time;
			deltaSum += ops/prevOps;
			deltaNum += 1;
			prevOps = ops;
		}
		StdOut.format ("Average delta: %.3f\n", deltaSum/deltaNum);
	}
	public static double timeTrial(int N) {
		ops = 0;
		Stopwatch s = new Stopwatch();
		XPerformanceOfArrays stack = new XPerformanceOfArrays();
		for (int j=0; j<N; j+=1) {
			stack.push (j);
		}
		return s.elapsedTime();
	}
}