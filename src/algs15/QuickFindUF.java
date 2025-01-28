// Exercise 1.5.7 (Solution published at http://algs4.cs.princeton.edu/)
package algs15;
import java.util.Arrays;
import stdlib.*;
/* **************************************************************************
 *  Compilation:  javac QuickFindUF.java
 *  Execution:  java QuickFindUF < input.txt
 *  Dependencies: StdIn.java StdOut.java
 *
 *  Quick-find algorithm.
 *
 *  % java QuickFindUF < largeUF.txt
 *  QuickFindUF # components: 6 [1054.198000]  // 17 minutes
 *
 ****************************************************************************/

public class QuickFindUF implements UF {
	private int[] id;
	private int count;

	// instantiate N isolated components 0 through N-1
	public QuickFindUF(int N) {
		count = N;
		id = new int[N];
		for (int i = 0; i < N; i++)
			id[i] = i;
	}

	// return number of connected components
	public int count() {
		return count;
	}


	// are elements p and q in the same component?
	public boolean connected(int p, int q) {
		return id[p] == id[q];
	}
	
	// Return component identifier for component containing p
	public int find(int p) {
		return id[p];
	}

	// merge components containing p and q
	public void union(int p, int q) {
		if (connected(p, q)) return;
		int pid = id[p]; // loser
		int qid = id[q]; // champion
		for (int i = 0; i < id.length; i++)
			if (id[i] == pid) id[i] = qid;
		count--;
	}

	public String toString() { return Arrays.toString (id); }
	public void toGraphviz() { GraphvizBuilder.ufToFile (id); }

	public static void main(String[] args) {
		boolean print = true;
		StdIn.fromFile ("data/tinyUF.txt"); 
		//StdIn.fromFile ("data/mediumUF.txt"); print = false;
		//StdIn.fromFile ("data/largeUF.txt"); print = false;

		int N = StdIn.readInt();
		QuickFindUF uf = new QuickFindUF(N);
		if (print) { uf.toGraphviz(); StdOut.println("   : " + uf); }

		// read in a sequence of pairs of integers (each in the range 0 to N-1),
		// calling find() for each pair: If the members of the pair are not already
		// call union() and print the pair.
		Stopwatch sw = new Stopwatch ();
		while (!StdIn.isEmpty()) {
			int p = StdIn.readInt();
			int q = StdIn.readInt();
			if (uf.connected(p, q)) continue;
			uf.union(p, q);
			if (print) { StdOut.println(p + " " + q + ": " + uf); uf.toGraphviz(); }
		}
		StdOut.format("QuickFindUF # components: %d [%f]\n", uf.count(), sw.elapsedTime ());
	}

}
