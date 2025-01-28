package algs15;
import java.util.Arrays;
import  stdlib.*;

/* **************************************************************************
 *  Compilation:  javac QuickUnionPathCompressionUF.java
 *  Execution:  java QuickUnionPathCompressionUF < input.txt
 *  Dependencies: StdIn.java StdOut.java
 *
 *  Quick-union with path compression.
 *
 ****************************************************************************/

public class CompressionUF implements UF {
	private int[] id;    // id[i] = parent of i
	private int count;   // number of components

	// Create an empty union find data structure with N isolated sets.
	public CompressionUF(int N) {
		count = N;
		id = new int[N];
		for (int i = 0; i < N; i++) {
			id[i] = i;
		}
	}

	// Return the number of disjoint sets.
	public int count() {
		return count;
	}
	
	// Are objects p and q in the same set?
	public boolean connected(int p, int q) {
		return find(p) == find(q);
	}
	
	// Return component identifier for component containing p
	public int find(int p) {
		int root = p;
		while (root != id[root])
			root = id[root];
		while (id[p] != root) {
			int newp = id[p];
			id[p] = root;
			if (TestUF.SHOW_COMPRESSION_STEPS) { StdOut.format("%2d %2d> %2d%s\n", p, root, this.count(), this); toGraphviz(); }
			p = newp;
		}
		return root;
	}

	// Replace sets containing p and q with their union.
	public void union(int p, int q) {
		int pid = find(p); // loser
		int qid = find(q); // champion
		if (pid == qid) return;
		id[pid] = qid;
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
		CompressionUF uf = new CompressionUF(N);
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
		StdOut.format("CompressionUF # components: %d [%f]\n", uf.count(), sw.elapsedTime ());
	}

}
