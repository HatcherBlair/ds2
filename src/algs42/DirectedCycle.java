package algs42;
import stdlib.*;
import algs13.Stack;
/* ***********************************************************************
 *  Compilation:  javac DirectedCycle.java
 *  Execution:    java DirectedCycle < input.txt
 *  Dependencies: Digraph.java Stack.java StdOut.java In.java
 *  Data files:   http://algs.cs.princeton.edu/42digraph/tinyDG.txt
 *                http://algs.cs.princeton.edu/42digraph/tinyDAG.txt
 *
 *  Finds a directed cycle in a digraph.
 *  Runs in O(E + V) time.
 *
 *  % java DirectedCycle tinyDG.txt
 *  Cycle: 3 5 4 3
 *
 *  %  java DirectedCycle tinyDAG.txt
 *  No cycle
 *
 *************************************************************************/

public class DirectedCycle {
	private boolean[] marked;    // marked[v] = has vertex v been marked?
	private int[] edgeTo;        // edgeTo[v] = previous vertex on path to v
	private boolean[] onStack;   // onStack[v] = is vertex on the stack?
	private Stack<Integer> cycle;      // directed cycle (or null if no such cycle)

	public DirectedCycle(Digraph G) {
		marked  = new boolean[G.V()];
		onStack = new boolean[G.V()];
		edgeTo = new int[G.V()];
		for (int v = 0; v < G.V(); v++) {
			if (!marked[v] && hasCycleFrom(G, -1, v)) {
				assert check(G);
				return;
			}
		}
	}

	private boolean hasCycleFromSimple(Digraph G, int v) {
		onStack[v] = true;
		marked[v] = true;
		for (int w : G.adj(v)) {
			if (onStack[w]) return true;
			if (marked[w]) continue;
			if (hasCycleFromSimple (G, w)) return true;
		}
		onStack[v] = false;
		return false;
	}
	
	// if the cycle is not interesting, then u is not necessary.
	private boolean hasCycleFrom(Digraph G, int u, int v) {
		onStack[v] = true;
		marked[v] = true;
		edgeTo[v] = u;
		for (int w : G.adj(v)) {
			if (onStack[w]) {
				cycle = new Stack<>();
				cycle.push(w);
				for (int x = v; x != w; x = edgeTo[x]) {
					cycle.push(x);
				}
				cycle.push(w);
				return true;
			} 
			if (marked[w]) continue;
			if (hasCycleFrom (G, v, w)) return true;
		}
		onStack[v] = false;
		return false;
	}
	
	// if the cycle is not interesting, then u is not necessary.
	private boolean TEXTBOOKhasCycleFrom(Digraph G, int u, int v) {
		onStack[v] = true;
		marked[v] = true;
		edgeTo[v] = u;
		for (int w : G.adj(v))
			if (onStack[w] || (!marked[w] && hasCycleFrom (G, v, w))) {
				if (cycle == null) {
					cycle = new Stack<>();
					cycle.push(w);
					for (int x = v; x != w; x = edgeTo[x]) {
						cycle.push(x);
					}
					cycle.push(w);
				}
				return true;
			}
		onStack[v] = false;
		return false;
	}

	public boolean hasCycle()        { return cycle != null;   }
	public Iterable<Integer> cycle() { return cycle;           }

	// certify that digraph is either acyclic or has a directed cycle
	private boolean check(Digraph G) {
		if (hasCycle()) {
			// verify cycle
			int first = -1, last = -1;
			for (int v : cycle()) {
				if (first == -1) first = v;
				last = v;
			}
			if (first != last) {
				System.err.format("cycle begins with %d and ends with %d\n", first, last);
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		args = new String[] { "data/tinyDG.txt" };
		//args = new String[] { "data/tinyDAG.txt" };

		In in = new In(args[0]);
		Digraph G = DigraphGenerator.fromIn(in);
		G.toGraphviz ("g.png");

		DirectedCycle finder = new DirectedCycle(G);
		if (finder.hasCycle()) {
			//if (finder.hasCycle(G)) {
			StdOut.print("Cycle: ");
			for (int v : finder.cycle()) {
				StdOut.print(v + " ");
			}
			StdOut.println();
		} else {
			StdOut.println("No cycle");
		}
	}

}
