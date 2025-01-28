package algs24;
import stdlib.*;
import java.util.Arrays;

public class FixedPQUnordered implements PQ {
	private int N;
	private double[] a;
	
	public FixedPQUnordered(int capacity)  { N = 0; a = new double[capacity+1]; }	
	public void toGraphviz()          { }
	public String toString ()         { return Arrays.toString(a).replaceAll(" 0\\.0", " ").replaceAll("\\[0\\.0", "[ ").replaceAll("\\.0", ""); }
	public int size()                 { return N; }
	public boolean isEmpty()          { return N == 0; }
	
	public void insert(double x) {
		if (x == 0) throw new Error ("No zeroes allowed");
		if (N >= a.length-1) throw new Error("Overflow");
		N++;
		a[N] = x;
	}
	private int minPosition() {
		if (N<=0) throw new Error("Underflow");
		int result = 1;
		for (int i=2; i<=N; i++) 
			if (a[i] < a[result]) result = i;
		return result;
	}
	public double min() {
		int m = minPosition(); 
		return a[m];
	}
	public double delMin() {
		int m = minPosition(); 
		double result = a[m];
		exch2(m, N);
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
