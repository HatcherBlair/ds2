package algs42;
import stdlib.*;
import java.util.Iterator;
import java.util.NoSuchElementException;
/* ***********************************************************************
 *  Compilation:  javac AdjMatrixDigraph.java
 *  Execution:    java AdjMatrixDigraph V E
 *  Dependencies: StdOut.java
 *
 *  A digraph, implemented using an adjacency matrix.
 *  Parallel edges are disallowed; self-loops are allowed.
 *
 *************************************************************************/

public class XAdjMatrixDigraph {
	private final int V;
	private int E;
	private final boolean[][] adj;

	// empty graph with V vertices
	public XAdjMatrixDigraph(int V) {
		if (V < 0) throw new Error("Number of vertices must be nonnegative");
		this.V = V;
		this.E = 0;
		this.adj = new boolean[V][V];
	}

	// random graph with V vertices and E edges
	public XAdjMatrixDigraph(int V, int E) {
		this(V);
		if (E < 0) throw new Error("Number of edges must be nonnegative");
		if (E > V*V) throw new Error("Too many edges");

		// can be inefficient
		while (this.E != E) {
			int v = (int) (V * Math.random());
			int w = (int) (V * Math.random());
			addEdge(v, w);
		}
	}

	// number of vertices and edges
	public int V() { return V; }
	public int E() { return E; }


	// add directed edge v->w
	public void addEdge(int v, int w) {
		if (!adj[v][w]) E++;
		adj[v][w] = true;
	}

	// return list of neighbors of v
	public Iterable<Integer> adj(int v) {
		return new AdjIterator(v);
	}

	// support iteration over graph vertices
	private class AdjIterator implements Iterator<Integer>, Iterable<Integer> {
		private final int v;
		private int w = 0;
		AdjIterator(int v) { this.v = v; }

		public Iterator<Integer> iterator() { return this; }

		public boolean hasNext() {
			while (w < V) {
				if (adj[v][w]) return true;
				w++;
			}
			return false;
		}

		public Integer next() {
			if (hasNext()) { return w++;                         }
			else           { throw new NoSuchElementException(); }
		}

		public void remove()  { throw new UnsupportedOperationException();  }
	}


	// string representation of Graph - takes quadratic time
	public String toString() {
		String NEWLINE = System.getProperty("line.separator");
		StringBuilder s = new StringBuilder();
		s.append(V + " " + E + NEWLINE);
		for (int v = 0; v < V; v++) {
			s.append(v + ": ");
			for (int w : adj(v)) {
				s.append(w + " ");
			}
			s.append(NEWLINE);
		}
		return s.toString();
	}


	// test client
	public static void main(String[] args) {
		args = new String[] { "10", "20" };

		int V = Integer.parseInt(args[0]);
		int E = Integer.parseInt(args[1]);
		XAdjMatrixDigraph G = new XAdjMatrixDigraph(V, E);
		StdOut.println(G);
	}

}
