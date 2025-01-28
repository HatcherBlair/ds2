package algs42;
import stdlib.*;
/* ***********************************************************************
 *  Compilation:  javac WarshallTC.java
 *  Dependencies: AdjMatrixDigraph.java StdOut.java
 *
 *  Compute transitive closure of a digraph G and support
 *  reachability queries.
 *
 *  Preprocessing time: O(V^3) time.
 *  Query time: O(1).
 *  Space: O(V^2).
 *
 *  % java WarshallTC 10 20
 *  10 20
 *  0: 9 2 7 5
 *  1: 6 7
 *  2:
 *  3: 9 9 9 7 6
 *  4: 7
 *  5: 7
 *  6: 7 1
 *  7: 2
 *  8: 7 6 0
 *  9: 2
 *
 *  Transitive closure
 *  -----------------------------------
 *         0  1  2  3  4  5  6  7  8  9
 *    0:   x     x        x     x     x
 *    1:      x  x           x  x
 *    2:         x
 *    3:      x  x  x        x  x     x
 *    4:         x     x        x
 *    5:         x        x     x
 *    6:      x  x           x  x
 *    7:         x              x
 *    8:   x  x  x        x  x  x  x  x
 *    9:         x                    x
 *
 *************************************************************************/

public class XWarshallTC {
	private final boolean[][] tc;    // tc[v][w] = true iff path from v to w

	public XWarshallTC(XAdjMatrixDigraph G) {

		// initialize tc[][]
		int V = G.V();
		tc = new boolean[V][V];
		for (int v = 0; v < V; v++) {
			for (int w : G.adj(v)) {
				tc[v][w] = true;
			}
			tc[v][v] = true;
		}

		// Warshall's algorithm
		for (int i = 0; i < V; i++) {
			for (int v = 0; v < V; v++) {
				if (!tc[v][i]) continue;        // optimization
				for (int w = 0; w < V; w++) {
					if (tc[v][i] && tc[i][w]) tc[v][w] = true;
				}
			}
		}

	}

	// is there a directed path from v to w?
	public boolean hasPath(int v, int w) {
		return tc[v][w];
	}


	// test client
	public static void main(String[] args) {
		args = new String[] { "10", "20" };

		int V = Integer.parseInt(args[0]);
		int E = Integer.parseInt(args[1]);
		XAdjMatrixDigraph G = new XAdjMatrixDigraph(V, E);
		StdOut.println(G);
		XWarshallTC tc = new XWarshallTC(G);

		// print header
		StdOut.println("Transitive closure");
		StdOut.println("-----------------------------------");
		StdOut.print("     ");
		for (int v = 0; v < G.V(); v++)
			StdOut.format("%3d", v);
		StdOut.println();

		// print transitive closure
		for (int v = 0; v < G.V(); v++) {
			StdOut.format("%3d: ", v);
			for (int w = 0; w < G.V(); w++) {
				if (tc.hasPath(v, w)) StdOut.format("  x");
				else                  StdOut.format("   ");
			}
			StdOut.println();
		}
	}

}
