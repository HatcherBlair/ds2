package algs41;
import stdlib.*;
import algs13.Stack;
/* ***********************************************************************
 *  Compilation:  javac Cycle.java
 *  Dependencies: Graph.java Stack.java
 *
 *  Identifies a cycle.
 *  Runs in O(E + V) time.
 *
 *************************************************************************/

public class Cycle {
	private boolean[] marked;
	private int[] edgeTo;
	private Stack<Integer> cycle;

	public Cycle(Graph G) {
		if (hasSelfLoop(G)) return;
		if (hasParallelEdges(G)) return;
		marked = new boolean[G.V()];
		edgeTo = new int[G.V()];
		for (int v = 0; v < G.V(); v++)
			if (!marked[v] && hasCycleFrom(G, -1, v))
				return;
	}

	// Assuming there is no self loop or parallel edges, does this graph have a cycle?
	private boolean hasCycleFromSimple(Graph G, int u, int v) {
		marked[v] = true; 
		for (int w : G.adj(v)) {
			if (w == u) continue; // skip the back edge: edgeTo[v] = u
			if (marked[w]) return true; // been here before
			if (hasCycleFromSimple (G, v, w)) return true; // search recursively
		}
		return false;
	}

	// Assuming there is no self loop or parallel edges, does this graph have a cycle?
	// side effect: initialize cycle field
	private boolean hasCycleFrom(Graph G, int u, int v) {
		//StdOut.format ("dfs(%d, %d)\n", u, v);
		marked[v] = true;
		edgeTo[v] = u;
		for (int w : G.adj(v)) {
			if (w == u) continue; // skip the back edge
			if (marked[w]) {				
				cycle = new Stack<>();
				cycle.push(w);
				for (int	 x = v; x != w; x = edgeTo[x])
					cycle.push(x);
				cycle.push(w);
				return true;
			}
			if (hasCycleFrom (G, v, w)) return true;
		}
		return false;
	}
	private boolean TEXTBOOKhasCycleFrom(Graph G, int u, int v) {
		marked[v] = true;
		edgeTo[v] = u;
		for (int w : G.adj(v)) {
			if ((marked[w] && w != u) || (!marked[w] && hasCycleFrom (G, v, w))) {
				if (cycle == null) {
					cycle = new Stack<>();
					cycle.push(w);
					for (int x = v; x != w; x = edgeTo[x])
						cycle.push(x);
					cycle.push(w);
				}
				return true;
			}
		}
		return false;
	}
	// does this graph have a self loop?
	// side effect: initialize cycle field
	private boolean hasSelfLoop(Graph G) {
		for (int v = 0; v < G.V(); v++) {
			for (int w : G.adj(v)) {
				if (v == w) {
					cycle = new Stack<>();
					cycle.push(v);
					cycle.push(v);
					return true;
				}
			}
		}
		return false;
	}

	// does this graph have two parallel edges?
	// side effect: initialize cycle field
	private boolean hasParallelEdges(Graph G) {
		for (int v = 0; v < G.V(); v++) {
			boolean[] marked = new boolean[G.V()];
			for (int w : G.adj(v)) {
				if (!marked[w]) {
					marked[w] = true;
				} else {
					// edge occurs twice
					cycle = new Stack<>();
					cycle.push(v);
					cycle.push(w);
					cycle.push(v);
					return true;
				}
			}
		}
		return false;
	}

	public boolean hasCycle()        { return cycle != null; }
	public Iterable<Integer> cycle() { return cycle;         }

	// test client
	public static void main(String[] args) {
		//		args = new String [] { "10", "5" };
		//		final int V = Integer.parseInt(args[0]);
		//		final int E = Integer.parseInt(args[1]);
		//		final Graph G = GraphGenerator.simple(V, E);
		//		StdOut.println(G);

		//args = new String [] { "data/tinyAG.txt" };
		args = new String [] { "data/tinyG.txt" };
		In in = new In(args[0]);
		Graph G = GraphGenerator.fromIn (in);
		StdOut.println(G);
		G.toGraphviz ("g.png");

		Cycle finder = new Cycle(G);
		if (finder.hasCycle()) {
			for (int v : finder.cycle()) {
				StdOut.print(v + " ");
			}
			StdOut.println();
		}
		else {
			StdOut.println("Graph is acyclic");
		}
	}


}

