package algs15;
import java.util.Arrays;
import stdlib.*;
/* **************************************************************************
 *  Compilation:  javac WeightedUF.java
 *  Execution:    java UF < input.txt
 *  Dependencies: StdIn.java StdOut.java
 *  Data files:   http://algs4.cs.princeton.edu/15uf/tinytxt
 *                http://algs4.cs.princeton.edu/15uf/mediumtxt
 *                http://algs4.cs.princeton.edu/15uf/largetxt
 *
 *  Weighted quick-union (without path compression).
 *
 *  % java UF < tinyUF.txt
 *  4 3
 *  3 8
 *  6 5
 *  9 4
 *  2 1
 *  5 0
 *  7 2
 *  6 1
 *  # components: 2
 *
 *  % java UF < largeUF.txt
 *  UF # components: 6 [4.372000]
 *
 ****************************************************************************/


/**
 *  The {@code UF} class represents a union-find data data structure.
 *  It supports the <em>union</em> and <em>find</em>
 *  operations, along with a method for determining the number of
 *  disjoint sets.
 *  <p>
 *  This implementation uses weighted quick union.
 *  Creating a data structure with N objects takes linear time.
 *  Afterwards, all operations are logarithmic worst-case time.
 *  <p>
 *  For additional documentation, see <a href="http://algs4.cs.princeton.edu/15uf">Section 1.5</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 */

public class WeightedUF implements UF {
	private int[] id;    // id[i] = parent of i
	private int[] sz;    // sz[i] = number of objects in subtree rooted at i
	private int count;   // number of components

	/**
	 * Create an empty union find data structure with N isolated sets.
	 */
	public WeightedUF(int N) {
		if (N < 0) throw new IllegalArgumentException();
		count = N;
		id = new int[N];
		sz = new int[N];
		for (int i = 0; i < N; i++) {
			id[i] = i;
			sz[i] = 1;
		}
	}

	/**
	 * Return the number of disjoint sets.
	 */
	public int count() {
		return count;
	}

	/**
	 * Are objects p and q in the same set?
	 */
	public boolean connected(int p, int q) {
		return find(p) == find(q);
	}
	
	/**
	 * Return the id of component corresponding to object p.
	 */
	public int find(int p) {
		int root = p;
		while (root != id[root])
			root = id[root];
		return root;
	}

	/**
	 * Replace sets containing p and q with their union.
	 */
	public void union(int p, int q) {
		int pid = find(p);
		int qid = find(q);
		if (pid == qid) return;

		// make smaller root point to larger one
		// in the case of a tie, p is the champion
		if   (sz[pid] < sz[qid]) { id[pid] = qid; sz[qid] += sz[pid]; }
		else                     { id[qid] = pid; sz[pid] += sz[qid]; }
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
		WeightedUF uf = new WeightedUF(N);
		if (print) { GraphvizBuilder.ufToFile (uf.id); StdOut.println("   : " + uf); }

		// read in a sequence of pairs of integers (each in the range 0 to N-1),
		// calling find() for each pair: If the members of the pair are not already
		// call union() and print the pair.
		Stopwatch sw = new Stopwatch ();
		while (!StdIn.isEmpty()) {
			int p = StdIn.readInt();
			int q = StdIn.readInt();
			if (uf.connected(p, q)) continue;
			uf.union(p, q);
			if (print) { StdOut.println(p + " " + q + ": " + uf); GraphvizBuilder.ufToFile (uf.id); }
		}
		StdOut.format("WeightedUF # components: %d [%f]\n", uf.count(), sw.elapsedTime ());
	}

}

