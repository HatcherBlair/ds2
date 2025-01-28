package algs42;
import stdlib.*;
import algs13.Queue;
/* ***********************************************************************
 *  Compilation:  javac BruteSCC.java
 *  Dependencies: Digraph.java TransitiveClosure.java
 *
 *  Compute the strongly-connected components of a digraph using
 *  brute force.
 *
 *  Runs in O(EV) time.
 *
 *  % java BruteSCC tinyDG.txt
 *  5 components
 *  0 2 3 4 5
 *  1
 *  6
 *  7 8
 *  9 10 11 12
 *
 *************************************************************************/

public class XBruteSCC {
	private int count;    // number of strongly connected components
	private final int[] id;     // id[v] = id of strong component containing v

	public XBruteSCC(Digraph G) {

		// initially each vertex is in its own component
		id = new int[G.V()];
		for (int v = 0; v < G.V(); v++)
			id[v] = v;

		// compute transitive closure
		TransitiveClosure tc = new TransitiveClosure(G);

		// if v and w are mutally reachable, assign v to w's component
		for (int v = 0; v < G.V(); v++)
			for (int w = 0; w < v; w++)
				if (tc.reachable(v, w) && tc.reachable(w, v))
					id[v] = id[w];

		// compute number of strongly connected components
		for (int v = 0; v < G.V(); v++)
			if (id[v] == v)
				count++;
	}

	// return the number of strongly connected components
	public int count() { return count; }

	// are v and w strongly connected?
	public boolean stronglyConnected(int v, int w) {
		return id[v] == id[w];
	}

	// in which strongly connected component is vertex v?
	public int id(int v) { return id[v]; }


	public static void main(String[] args) {
		args = new String[] { "data/tinyDG.txt" };

		In in = new In(args[0]);
		Digraph G = DigraphGenerator.fromIn(in);
		XBruteSCC scc = new XBruteSCC(G);

		// number of connected components
		int M = scc.count();
		StdOut.println(M + " components");

		// compute list of vertices in each strong component
		@SuppressWarnings("unchecked")
		final
		Queue<Integer>[] components = new Queue[G.V()];
		for (int i = 0; i < G.V(); i++) {
			components[i] = new Queue<>();
		}
		for (int v = 0; v < G.V(); v++) {
			components[scc.id(v)].enqueue(v);
		}

		// print results
		for (int i = 0; i < G.V(); i++) {
			if (!components[i].isEmpty()) {
				for (int v : components[i]) {
					StdOut.print(v + " ");
				}
				StdOut.println();
			}
		}

	}

}
