package algs42;
import stdlib.*;
import algs13.Stack;
/* ***********************************************************************
 *  Compilation:  javac ShortestDirectedCycle.java
 *  Execution:    java DirectedCycle < input.txt
 *  Dependencies: Digraph.java BreadthFirstDirectedPaths.java Stack.java StdOut.java In.java
 *  Data files:   http://algs4.cs.princeton.edu/42directed/tinyDG.txt
 *                http://algs4.cs.princeton.edu/42directed/tinyDAG.txt
 *
 *  Finds a shortest directed cycle in a digraph.
 *  Runs in O(V * (E + V)) time.
 *
 *  % java ShortestDirectedCycle tinyDG.txt
 *  Shortest directed cycle: 2 3 2
 *
 *  %  java ShortestDirectedCycle tinyDAG.txt
 *  No cycle
 *
 *************************************************************************/

public class XShortestDirectedCycle {
	private Stack<Integer> cycle;    // directed cycle (or null if no such cycle)
	private int length;

	public XShortestDirectedCycle(Digraph G) {
		Digraph R = G.reverse();
		length = G.V() + 1;
		for (int v = 0; v < G.V(); v++) {
			BreadthFirstDirectedPaths bfs = new BreadthFirstDirectedPaths(R, v);
			for (int w : G.adj(v)) {
				if (bfs.hasPathTo(w) && (bfs.distTo(w) + 1) < length) {
					length = bfs.distTo(w) + 1;
					cycle = new Stack<>();
					for (int x : bfs.pathTo(w))
						cycle.push(x);
					cycle.push(v);
				}
			}
		}
	}


	public boolean hasCycle()        { return cycle != null;   }
	public Iterable<Integer> cycle() { return cycle;           }
	public int length()              { return length;          }

	public static void main(String[] args) {
		args = new String[] { "data/tinyDG.txt" };

		Digraph G;
		if (args.length == 1) {
			In in = new In(args[0]);
			G = DigraphGenerator.fromIn(in);
		} else {
			int V = Integer.parseInt(args[0]);
			int E = Integer.parseInt(args[1]);
			G = DigraphGenerator.simple(V, E);
		}

		XShortestDirectedCycle finder = new XShortestDirectedCycle(G);
		if (finder.hasCycle()) {
			StdOut.print("Shortest directed cycle: ");
			for (int v : finder.cycle()) {
				StdOut.print(v + " ");
			}
			StdOut.println();
		}

		else {
			StdOut.println("No cycle");
		}
	}

}
