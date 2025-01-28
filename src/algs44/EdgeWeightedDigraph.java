// Exercise 4.4.2 (Solution published at http://algs4.cs.princeton.edu/)
package algs44;
import stdlib.*;
import algs13.Bag;
import algs13.Stack;
/**
 *  The {@code EdgeWeightedDigraph} class represents an directed graph of vertices
 *  named 0 through V-1, where each edge has a real-valued weight.
 *  It supports the following operations: add an edge to the graph,
 *  iterate over all of edges leaving a vertex.
 *  Parallel edges and self-loops are permitted.
 *  <p>
 *  For additional documentation, see <a href="http://algs4.cs.princeton.edu/44sp">Section 4.4</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 */

public class EdgeWeightedDigraph {
	private final int V;
	private int E;
	private final Bag<DirectedEdge>[] adj;

	/**
	 * Create an empty edge-weighted digraph with V vertices.
	 */
	@SuppressWarnings("unchecked")
	public EdgeWeightedDigraph(int V) {
		if (V < 0) throw new Error("Number of vertices must be nonnegative");
		this.V = V;
		this.E = 0;
		adj = new Bag[V];
		for (int v = 0; v < V; v++)
			adj[v] = new Bag<>();
	}

	/**
	 * Create a random edge-weighted graph with V vertices and E edges with no parallel edges or self loops.
	 * The expected running time is proportional to V + E.
	 */
	public EdgeWeightedDigraph(int V, int E) { this (V, E, false); }
	/**
	 * Create a random edge-weighted graph with V vertices and E edges.
	 * The expected running time is proportional to V + E.
	 */
	public EdgeWeightedDigraph(int V, int E, boolean allowParallelEdgesAndSelfLoops) {
		this(V);
		if (E < 0) throw new Error("Number of edges must be nonnegative");
		if (allowParallelEdgesAndSelfLoops) {
			for (int i = 0; i < E; i++) {
				int v = (int) (Math.random() * V);
				int w = (int) (Math.random() * V);
				double weight = Math.round(100 * Math.random()) / 100.0;
				DirectedEdge e = new DirectedEdge(v, w, weight);
				addEdge(e);
			}
		} else {
			if (E > V*(V-1)/2) throw new Error("Number of edges must be less than V*(V-1)/2");
			newEdge: while (E>0) {
				int v = (int) (Math.random() * V);
				int w = (int) (Math.random() * V);
				if (v == w) continue;
				for (DirectedEdge e: adj[v])
					if (w == e.to())
						continue newEdge;
				double weight = Math.round(100 * Math.random()) / 100.0;
				DirectedEdge e = new DirectedEdge(v, w, weight);
				addEdge(e);
				E--;
			}
		}
	}
	/**
	 * Create an edge-weighted digraph from input stream.
	 */
	public EdgeWeightedDigraph(In in) {
		this(in.readInt());
		int E = in.readInt();
		for (int i = 0; i < E; i++) {
			int v = in.readInt();
			int w = in.readInt();
			double weight = in.readDouble();
			addEdge(new DirectedEdge(v, w, weight));
		}
	}

	/**
	 * Copy constructor.
	 */
	public EdgeWeightedDigraph(EdgeWeightedDigraph G) {
		this(G.V());
		this.E = G.E();
		for (int v = 0; v < G.V(); v++) {
			// reverse so that adjacency list is in same order as original
			Stack<DirectedEdge> reverse = new Stack<>();
			for (DirectedEdge e : G.adj[v]) {
				reverse.push(e);
			}
			for (DirectedEdge e : reverse) {
				adj[v].add(e);
			}
		}
	}

	/**
	 * Return the number of vertices in this digraph.
	 */
	public int V() {
		return V;
	}

	/**
	 * Return the number of edges in this digraph.
	 */
	public int E() {
		return E;
	}


	/**
	 * Add the edge e to this digraph.
	 */
	public void addEdge(DirectedEdge e) {
		int v = e.from();
		adj[v].add(e);
		E++;
	}


	/**
	 * Return the edges leaving vertex v as an Iterable.
	 * To iterate over the edges leaving vertex v, use foreach notation:
	 * {@code for (DirectedEdge e : graph.adj(v))}.
	 */
	public Iterable<DirectedEdge> adj(int v) {
		return adj[v];
	}

	/**
	 * Return all edges in this graph as an Iterable.
	 * To iterate over the edges, use foreach notation:
	 * {@code for (DirectedEdge e : graph.edges())}.
	 */
	public Iterable<DirectedEdge> edges() {
		Bag<DirectedEdge> list = new Bag<>();
		for (int v = 0; v < V; v++) {
			for (DirectedEdge e : adj(v)) {
				list.add(e);
			}
		}
		return list;
	}

	/**
	 * Return number of edges leaving v.
	 */
	public int outdegree(int v) {
		return adj[v].size();
	}



	/**
	 * Return a string representation of this graph.
	 */
	public String toString() {
		String NEWLINE = System.getProperty("line.separator");
		StringBuilder s = new StringBuilder();
		s.append(V + " " + E + NEWLINE);
		for (int v = 0; v < V; v++) {
			s.append(v + ": ");
			for (DirectedEdge e : adj[v]) {
				s.append(e + "  ");
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
			for (DirectedEdge e : adj[v]) {
				int w = e.to();
				gb.addLabeledEdge (v, w, e.weight ());
			}
		}
		gb.toFile (filename);
	}

	/**
	 * Test client.
	 */
	public static void main(String[] args) {
		//args = new String [] { "data/tinyEWDAG.txt" };
		//args = new String [] { "data/tinyEWD.txt" };
		//args = new String [] { "data/tinyEWDn.txt" };
		//args = new String [] { "data/tinyEWDnc.txt" };
		args = new String [] { "20", "20" };

		EdgeWeightedDigraph G;
		if (args.length == 1) {
			In in = new In(args[0]);
			G = new EdgeWeightedDigraph(in);
		} else {
			int V = Integer.parseInt (args[0]);
			int E = Integer.parseInt (args[1]);
			G = new EdgeWeightedDigraph(V, E, false);
		}
		StdOut.println(G);
		G.toGraphviz ("g.png");
	}

}
