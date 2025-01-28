package algs15;
import java.util.Scanner;
import stdlib.*;

public class TestUF {
	public static enum Order { ZERO_I, I_ZERO, I_MINUS, MINUS_I, RANDOM }
	public static void main(String[] args) {
		show(10, "4 3, 3 8, 6 5, 9 4, 2 1, 8 9, 5 0, 7 2, 6 1, 1 0, 6 7"); //textbook tinyUF.txt		
		show(10, "0 1, 0 2, 0 3, 0 4, 0 5, 0 6, 0 7, 0 8, 0 9"); // ZERO_I
		show(10, "1 0, 2 0, 3 0, 4 0, 5 0, 6 0, 7 0, 8 0, 9 0"); // I_ZERO
		show(10, "1 0, 2 1, 3 2, 4 3, 5 4, 6 5, 7 6, 8 7, 9 8"); // I_MINUS
		show(10, "0 1, 1 2, 2 3, 3 4, 4 5, 5 6, 6 7, 7 8, 8 9"); // MINUS_I
		show(16, "0 1, 2 3, 4 5, 6 7, 8 9, 10 11, 12 13, 14 15," + "0 2, 4 6, 8 10, 12 14," + "0 4, 8 12," + "0 8");
		showRandom(12);
		double prevUnion = 0;
		double prevConnected = 0;
		for (int N = 128; N<=MAX; N += N) {
			timeTrial(N, Order.RANDOM);
			StdOut.format("N=%,13d Union=%7.3f [%8.3f] Connected=%7.3f [%8.3f]\n", N, timeUnion, timeUnion/prevUnion, timeConnected, timeConnected/prevConnected);
			prevUnion = timeUnion;
			prevConnected = timeConnected;
		}
	}
	private static int MAX=50_000_000;
	private static UF getUF (int N) {
		  MAX=500_000; return new QuickFindUF(N);  //   (262_144,RANDOM) Union ~ 36  Connected ~ .006
	    //MAX=500_000; return new QuickUnionUF(N); //   (262_144,RANDOM) Union ~ 17  Connected ~ 18
		//return new WeightedUF(N);                //(33_554_432,RANDOM) Union ~ 10  Connected ~ 10
		//return new CompressionUF(N);             //(33_554_432,RANDOM) Union ~ 16  Connected ~ 7
		//return new XWeightedHalvingUF(N);        //(33_554_432,RANDOM) Union ~  9  Connected ~ 6
		//return new XWeightedCompressionUF(N);    //(33_554_432,RANDOM) Union ~  9  Connected ~ 6
	}
	
	private static double timeUnion;
	private static double timeConnected;
	private static void timeTrial(int N, Order order) {
		UF ufTime = getUF(N);				
		SHOW_COMPRESSION_STEPS = false;
		Stopwatch sw1 = new Stopwatch();
		for (int i=1; i<N; i+= 1) {
			int p = StdRandom.uniform(N);
			int q = StdRandom.uniform(N);
			switch (order) {
			case ZERO_I: ufTime.union (0, i); break; 
			case I_ZERO: ufTime.union (i, 0); break; 
			case I_MINUS: ufTime.union (i, i-1); break;
			case MINUS_I: ufTime.union (i-1, i); break;
			case RANDOM: ufTime.union (p, q); break;
			}
		}
		timeUnion = sw1.elapsedTime();

		Stopwatch sw2 = new Stopwatch();		
		for (int i=1; i<N; i+= 1) {
			int p = StdRandom.uniform(N);
			int q = StdRandom.uniform(N);
			ufTime.connected(p, q);
		}	
		timeConnected = sw2.elapsedTime();
	}
	
	public static boolean SHOW_COMPRESSION_STEPS=false;
	private static void showRandom (int N) {
		SHOW_COMPRESSION_STEPS = true;
		UF uf = getUF(N);
		uf.toGraphviz();
		StdOut.format("       %2d%s\n", uf.count(), uf);
		for (int i=1; i<4*N; i++) {
			int p = StdRandom.uniform(N);
			int q = StdRandom.uniform(N);
			if (uf.connected(p, q)) {
				StdOut.format("%2d %2d: connected\n", p, q); 
			} else {
				uf.union(p, q);
				uf.toGraphviz();
				StdOut.format("%2d %2d: %2d%s\n", p, q, uf.count(), uf);
			}
		}
		StdOut.println();
	}
	private static void show (int N, String input) {
		SHOW_COMPRESSION_STEPS = true;
		Scanner s = new Scanner(input);
		s.useDelimiter("[\\s,]\\s*"); // use comma or space as delimiter

		UF uf = getUF(N);
		uf.toGraphviz();
		StdOut.format("       %2d%s\n", uf.count(), uf);
		while (s.hasNext()) {
			int p = s.nextInt();
			int q = s.nextInt();
			if (uf.connected(p, q)) {
				StdOut.format("%2d %2d: connected\n", p, q); 
			} else {
				uf.union(p, q);
				uf.toGraphviz();
				StdOut.format("%2d %2d: %2d%s\n", p, q, uf.count(), uf);
			}
		}
		StdOut.println();
		s.close();
	}
}