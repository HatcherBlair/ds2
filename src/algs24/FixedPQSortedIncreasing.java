package algs24;
import stdlib.*;
import java.util.Arrays;

public class FixedPQSortedIncreasing implements PQ {
	private int N;
	private double[] a;
	
	public FixedPQSortedIncreasing(int capacity){ N = 0; a = new double[capacity+1]; }	
	public void toGraphviz()          { }
	public String toString ()         { return Arrays.toString(a).replaceAll(" 0\\.0", " ").replaceAll("\\[0\\.0", "[ ").replaceAll("\\.0", ""); }
	public int size()                 { return N; }
	public boolean isEmpty()          { return N == 0; }
	
	public void insert(double x) {
		if (x == 0) throw new Error ("No zeroes allowed");
		if (N >= a.length-1) throw new Error("Overflow");
		N++;
		a[N] = x;
		for (int j = N; j > 1 && a[j] < a[j-1]; j--)
			exch2(j, j-1);
	}
	public double min() {
		return a[1];
	}
	public double delMin() {
		double result = a[1]; 
		for (int j = 2; j <= N; j++)
			exch(j, j-1);
		a[N] = 0.0;
		N--;
		return result;
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
	
	private boolean isSorted() {
		for (int i = 2; i < N; i++)
			if (a[i] < a[i-1]) return false;
		return true;
	}
}
