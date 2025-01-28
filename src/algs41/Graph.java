// Exercise 4.1.3 (Solution published at http://algs4.cs.princeton.edu/)
package algs41;
import stdlib.*;
import algs13.Bag;

/**
 *  The {@code Graph} class represents an undirected graph of vertices
 *  named {@code 0} through {@code V-1}.
 *  It supports the following operations: add an edge to the graph,
 *  iterate over all of the neighbors adjacent to a vertex.
 *  Parallel edges and self-loops are permitted.
 *  <p>
 *  For additional documentation, see <a href="http://algs4.cs.princeton.edu/51undirected">Section 5.1</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 */
public class Graph {
	private final int V; // number of vertices
	private int E; // number of edges
	private final Bag<Integer>[] adj; // adjacency lists
	// private final String[] names;

	// size of adj is V

	/**
	 * Create an empty graph with V vertices.
	 */
	@SuppressWarnings("unchecked")
	public Graph(int V) {
		if (V < 0) throw new Error("Number of vertices must be nonnegative");
		this.V = V;
		this.E = 0;
		this.adj = new Bag[V];
		for (int v = 0; v < V; v++) {
			adj[v] = new Bag<>();
		}
	}

	// void explore(int v){
	//	for all w in adj[v]
	//         explore(w)
	
	
	/**
	 * Return the number of vertices in the graph.
	 */
	public int V() { return V; }

	/**
	 * Return the number of edges in the graph.
	 */
	public int E() { return E; }


	/**
	 * Add the undirected edge v-w to graph.
	 * @throws java.lang.IndexOutOfBoundsException unless both {@code 0 <= v < V} and {@code 0 <= w < V}
	 */
	public void addEdge(int v, int w) {
		if (v < 0 || v >= V) throw new IndexOutOfBoundsException();
		if (w < 0 || w >= V) throw new IndexOutOfBoundsException();
		E++;
		adj[v].add(w);
		adj[w].add(v);
	}


	/**
	 * Return the list of neighbors of vertex v as in Iterable.
	 * @throws java.lang.IndexOutOfBoundsException unless {@code 0 <= v < V}
	 */
	public Iterable<Integer> adj(int v) {
		if (v < 0 || v >= V) throw new IndexOutOfBoundsException();
		return adj[v];
	}
	
    /**
     * Returns the degree of vertex {@code v}.
     *
     * @param  v the vertex
     * @return the degree of vertex {@code v}
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public int degree(int v) {
		if (v < 0 || v >= V) throw new IndexOutOfBoundsException();
		return adj[v].size();
    }

	/**
	 * Return a string representation of the graph.
	 */
	public String toString() {
		StringBuilder s = new StringBuilder();
		String NEWLINE = System.getProperty("line.separator");
		s.append(V + " vertices, " + E + " edges " + NEWLINE);
		for (int v = 0; v < V; v++) {
			s.append(v + ": ");
			for (int w : adj[v]) {
				s.append(w + " ");
			}
			s.append(NEWLINE);
		}
		return s.toString();
	}

	/**
	 * Save a graphviz representation of the graph.
	 * See <a href="http://www.graphviz.org/">graphviz.org</a>.
	 */
	public void toGraphviz(String filename) {
		GraphvizBuilder gb = new GraphvizBuilder ();
		for (int v = 0; v < V; v++) {
			gb.addNode (v);
			boolean showSelfLoop = false;
			for (int w : adj[v]) {
				if (v < w) // only once each edge
					gb.addEdge (v, w);
				if (v == w) {
					showSelfLoop = !showSelfLoop;
					if (showSelfLoop)
						gb.addEdge (v, w);
				}
			}
		}
		gb.toFileUndirected (filename);
	}

	/**
	 * Test client.
	 */
	public static void main(String[] args) {
		//args = new String [] { "data/tinyCG.txt" };
		args = new String [] { "data/tinyG.txt" };
		//args = new String [] { "20", "40" };

		Graph G;
		if (args.length == 1) {
			In in = new In(args[0]);
			G = GraphGenerator.fromIn (in);
		} else {
			int V = Integer.parseInt (args[0]);
			int E = Integer.parseInt (args[1]);
			G = GraphGenerator.simple(V, E);
		}
		StdOut.println(G);
		G.toGraphviz ("g.png");
	}
}
