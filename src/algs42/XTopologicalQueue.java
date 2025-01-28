// Exercise 4.2.39 (Solution published at http://algs4.cs.princeton.edu/)
package algs42;
import stdlib.*;
import algs13.Queue;
/* ***********************************************************************
 *  Compilation:  javac TopologicalQueue.java
 *  Execution:    java TopologicalQueue V E F
 *  Dependencies: Queue.java
 *
 *  Compute topological ordering of a DAG using queue-based algorithm.
 *  Runs in O(E + V) time.
 *
 *
 *************************************************************************/

public class XTopologicalQueue {
	private final Queue<Integer> order;     // vertices in topological order
	private final int[] indegree;           // indegree[v] = indegree of vertex v
	private final int[] rank;               // rank[v] = order where vertex v appers in order
	private int count;                // for computing the ranks

	public XTopologicalQueue(Digraph G) {
		indegree = new int[G.V()];
		rank = new int[G.V()];
		order = new Queue<>();

		// compute indegrees
		for (int v = 0; v < G.V(); v++) {
			for (int w : G.adj(v)) {
				indegree[w]++;
			}
		}

		// initialize queue to contain all vertices with indegree = 0
		Queue<Integer> queue = new Queue<>();
		for (int v = 0; v < G.V(); v++)
			if (indegree[v] == 0) queue.enqueue(v);

		while (!queue.isEmpty()) {
			int v = queue.dequeue();
			order.enqueue(v);
			rank[v] = count++;
			for (int w : G.adj(v)) {
				indegree[w]--;
				if (indegree[w] == 0) queue.enqueue(w);
			}
		}
	}

	// is G a directed acyclic graph?
	public boolean isDAG() {
		for (int element : indegree)
			if (element != 0) return false;
		return true;
	}

	// the vertices in topological order (assuming G is a DAG)
	public Iterable<Integer> order() {
		return order;
	}


	// the rank of vertex v in topological order
	public int rank(int v) {
		return rank[v];
	}

	// certify that digraph is acyclic
	private boolean check(Digraph G) {

		// digraph is acyclic
		if (isDAG()) {
			// check that ranks are a permutation of 0 to V-1
			boolean[] found = new boolean[G.V()];
			for (int i = 0; i < G.V(); i++) {
				found[rank(i)] = true;
			}
			for (int i = 0; i < G.V(); i++) {
				if (!found[i]) {
					System.err.println("No vertex with rank " + i);
					return false;
				}
			}

			// check that ranks provide a valid toplogical order
			for (int v = 0; v < G.V(); v++) {
				for (int w : G.adj(v)) {
					if (rank(v) > rank(w)) {
						System.err.format("%d-%d: rank(%d) = %d, rank(%d) = %d\n",
								v, w, v, rank(v), w, rank(w));
						return false;
					}
				}
			}

			// check that order() is consistent with rank()
			int r = 0;
			for (int v : order()) {
				if (rank(v) != r) {
					System.err.println("order() and rank() inconsistent");
					return false;
				}
				r++;
			}
		}


		return true;
	}

	public static void main(String[] args) {
		args = new String[] { "10", "20", "2" };

		// create random DAG with V vertices and E edges; then add F random edges
		int V = Integer.parseInt(args[0]);
		int E = Integer.parseInt(args[1]);
		int F = Integer.parseInt(args[2]);
		Digraph G = new Digraph(V);
		int[] vertices = new int[V];
		for (int i = 0; i < V; i++) vertices[i] = i;
		StdRandom.shuffle(vertices);
		for (int i = 0; i < E; i++) {
			int v, w;
			do {
				v = StdRandom.uniform(V);
				w = StdRandom.uniform(V);
			} while (v >= w);
			G.addEdge(vertices[v], vertices[w]);
		}

		// add F extra edges
		for (int i = 0; i < F; i++) {
			int v = (int) (Math.random() * V);
			int w = (int) (Math.random() * V);
			G.addEdge(v, w);
		}

		StdOut.println(G);

		// find a directed cycle
		XTopologicalQueue topological = new XTopologicalQueue(G);
		if (!topological.isDAG()) {
			StdOut.println("Not a DAG");
		}

		// or give topologial sort
		else {
			StdOut.print("Topological order: ");
			for (int v : topological.order()) {
				StdOut.print(v + " ");
			}
			StdOut.println();
		}
	}

}
