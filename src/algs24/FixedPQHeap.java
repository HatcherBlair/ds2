package algs24;
import stdlib.*;
import java.util.Arrays;

public class FixedPQHeap implements PQ {
	private int N;
	private double[] a;
	
	public FixedPQHeap(int capacity)  { N = 0; a = new double[capacity+1]; }	
	public void toGraphviz()          { GraphvizBuilder.binaryHeapToFile (a, N); }
	public String toString ()         { return Arrays.toString(a).replaceAll(" 0\\.0", " ").replaceAll("\\[0\\.0", "[ ").replaceAll("\\.0", ""); }
	public int size()                 { return N; }
	public boolean isEmpty()          { return N == 0; }
	
	public void insert(double x) {
		if (x == 0) throw new Error ("No zeroes allowed");
		if (N >= a.length-1) throw new Error("Overflow");
		N++;
		a[N] = x;
		swim(N);
	}
	public double min() {
		if (N <= 0) throw new Error("Underflow");
		return a[1];
	}
	public double delMin() {
		double result = min();
		exch(1, N);
		a[N] = 0.0;
		N--;
		sink(1);
		return result;
	}

	private boolean isMinHeap() {
		for (int i = 2; i < N; i++)
			if (a[i] < a[i/2]) return false;
		return true;
	}
	// for comparison:
	private boolean isSorted() {
		for (int i = 2; i < N; i++)
			if (a[i] < a[i-1]) return false;
		return true;
	}
	
	private void swim(int k) {
		while (k > 1 && a[k/2] > a[k]) {
			int parent = k/2;
			exch2(k, parent);
			k = parent;
		}
	}
	private void sink(int k) {
		while (2*k <= N) {
			int left  = 2*k;
			int right = 2*k + 1;
			int champ;
			if (right > N || a[left] < a[right]) 
				champ = left;
			else 
				champ = right;
			if (a[k] < a[champ]) break;
			exch2(k, champ);
			k = champ;
		}
	}
	private void exch(int i, int j) {
		double oldi = a[i];
		double oldj = a[j];
		a[i] = oldj;
		a[j] = oldi;
		if (TestPQ.SHOW_STEPS) { StdOut.format("  exch(%2.0f,%2.0f)> %s\n", oldi, oldj, this); toGraphviz(); }
	}
	private void exch2(int i, int j) {
		double oldi = a[i];
		double oldj = a[j];
		if (TestPQ.SHOW_STEPS) { StdOut.format("  exch(%2.0f,%2.0f)> %s\n", oldi, oldj, this); toGraphviz(); }
		a[i] = oldj;
		a[j] = oldi;
	}
}
