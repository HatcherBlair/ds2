package algs24;
import java.util.Scanner;
import stdlib.*;

public class TestPQ {
	public static enum Order { INCREASING, DECREASING, RANDOM }
	public static void main(String[] args) {
		show(11, true, "1 4 3 6 8 5 11 10 7 9 2 - - 2"); // Example from text 
		show(9, true, "1 2 3 4 5 6 7 8 9 - - - - - - - -");
		show(9, true, "9 8 7 6 5 4 3 2 1 - - - - - - - -");
		showRandom(10, true);
		double prevInsert = 0;
		double prevDelMin = 0;
		for (int N = 128; N<=MAX; N += N) {
			timeTrial(N, Order.RANDOM);
			StdOut.format("N=%,13d Insert=%7.3f [%8.3f] DelMin=%7.3f [%8.3f]\n", N, timeInsert, timeInsert/prevInsert, timeDelMin, timeDelMin/prevDelMin);
			prevInsert = timeInsert;
			prevDelMin = timeDelMin;
		}
	}
	private static int MAX=200_000;
	private static PQ getPQ (int N) {
		//return new FixedPQSortedIncreasing(N);
		//return new FixedPQSortedDecreasing(N);
		//return new FixedPQUnordered(N);
		MAX=50_000_000; return new FixedPQHeap(N);
	}

	private static double timeInsert;
	private static double timeDelMin;
	private static void timeTrial(int N, Order order) {
		SHOW_STEPS=false;
		PQ pqTime = getPQ(N);				
		Stopwatch sw1 = new Stopwatch();
		for (int i=0; i<N; i+=1) {
			double item = StdRandom.uniform(1,N+1);
			switch (order) {
			case INCREASING: pqTime.insert (i); break; 
			case DECREASING: pqTime.insert(N-i); break; 
			case RANDOM: pqTime.insert (item); break;
			}
		}
		timeInsert = sw1.elapsedTime();

		Stopwatch sw2 = new Stopwatch();		
		for (int i=0; i<N; i+= 1) {
			pqTime.delMin();
		}	
		timeDelMin = sw2.elapsedTime();
	}

	public static boolean SHOW_STEPS=false;
	private static void showRandom (int N, boolean showSteps) {
		SHOW_STEPS=showSteps;
		PQ pq = getPQ(N);
		pq.toGraphviz();
		StdOut.format("               %s\n", pq);
		for (int i=0; i<4*N; i+=1) {
			if (pq.size() > N/2 && (pq.size() == N || StdRandom.random()<.45)) {
				double item = pq.delMin();
				StdOut.format("-%2.0f          : %s\n", item, pq);	
			} else {
				double item = StdRandom.uniform(10,100);
				pq.insert(item);
				StdOut.format("+%2.0f          : %s\n", item, pq);
			}
		}
		StdOut.println();
	}
	private static void show (int N, boolean showSteps, String input) {
		SHOW_STEPS=showSteps;
		Scanner s = new Scanner(input);
		PQ pq = getPQ(N);
		pq.toGraphviz();
		StdOut.format("               %s\n", pq);
		while (s.hasNext()) {
			String next = s.next();
			if ("-".equals(next)) { 
				double item = pq.delMin();
				StdOut.format("-%2.0f          : %s\n", item, pq);
			} else {
				double item = Integer.parseInt(next);
				pq.insert(item);
				StdOut.format("+%2.0f          : %s\n", item, pq);
			}
			pq.toGraphviz();
		}
		StdOut.println();
		s.close();
	}
}