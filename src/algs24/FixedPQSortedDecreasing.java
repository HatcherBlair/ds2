package algs24;
import stdlib.*;
import java.util.Arrays;

public class FixedPQSortedDecreasing implements PQ {
	private int N;
	private double[] a;
	
	public FixedPQSortedDecreasing(int capacity){ N = 0; a = new double[capacity+1]; }	
	public void toGraphviz()          { }
	public String toString ()         { return Arrays.toString(a).replaceAll(" 0\\.0", " ").replaceAll("\\[0\\.0", "[ ").replaceAll("\\.0", ""); }
	public int size()                 { return N; }
	public boolean isEmpty()          { return N == 0; }
	
	public void insert(double x) {
		if (x == 0) throw new Error ("No zeroes allowed");
		if (N >= a.length-1) throw new Error("Overflow");
		N++;
		a[N] = x;
		for (int j = N; j > 1 && a[j] > a[j-1]; j--)
			exch2(j, j-1);
	}
	public double min() {
		return a[N];
	}
	public double delMin() {
		double result = a[N]; 
		a[N] = 0.0;
		N--;
		return result;
	}
	private void exch2(int i, int j) {
		double oldi = a[i];
		double oldj = a[j];
		if (TestPQ.SHOW_STEPS) { StdOut.format("  exch(%2.0f,%2.0f)> %s\n", oldi, oldj, this); toGraphviz(); }
		a[i] = oldj;
		a[j] = oldi;
	}
}
