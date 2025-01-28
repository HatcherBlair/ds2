package algs41;
import stdlib.*;
import algs13.Stack;
/* ***********************************************************************
 *  Compilation:  javac DepthFirstPaths.java
 *  Execution:    java DepthFirstPaths G s
 *  Dependencies: Graph.java Stack.java StdOut.java
 *  Data files:   http://algs4.cs.princeton.edu/41undirected/tinyCG.txt
 *
 *  Run depth first search on an undirected graph.
 *  Runs in O(E + V) time.
 *
 *  %  java Graph tinyCG.txt
 *  6 8
 *  0: 2 1 5
 *  1: 0 2
 *  2: 0 1 3 4
 *  3: 5 4 2
 *  4: 3 2
 *  5: 3 0
 *
 *  % java DepthFirstPaths tinyCG.txt 0
 *  0 to 0:  0
 *  0 to 1:  0-2-1
 *  0 to 2:  0-2
 *  0 to 3:  0-2-3
 *  0 to 4:  0-2-3-4
 *  0 to 5:  0-2-3-5
 *
 *************************************************************************/

public class DepthFirstPaths {
	private final boolean[] marked; // marked[v] = is there an s-v path?
	private final int[] edgeTo;     // edgeTo[v] = last edge on s-v path
	private final int s;            // source vertex

	public DepthFirstPaths(Graph G, int s) {
		this.s = s;
		edgeTo = new int[G.V()];
		marked = new boolean[G.V()];
		dfsLoop (G, s);
	}

	// depth first search from v
	private void dfs(Graph G, int v) {
		//StdOut.format ("visited %d\n", v);
		marked[v] = true;
		for (int w : G.adj(v)) {
			if (!marked[w]) {
				edgeTo[w] = v;
				dfs(G, w);
			}
		}
	}
	private void dfsLoop(Graph G, int s) {
		Stack<Integer> stack = new Stack<>();
		stack.push(s);

		while (!stack.isEmpty()) {
			int v = stack.pop();
			marked[v] = true; // For DFS, mark as you pop
			for (int w : G.adj(v)) {
				if (!marked[w]) {
					edgeTo[w] = v;
					stack.push(w);
				}
			}
		}
	}
	private void dfsLoopReversed(Graph G, int s) {
		Stack<Integer> stack = new Stack<>();
		stack.push(s);

		while (!stack.isEmpty()) {
			int v = stack.pop();
			marked[v] = true;

			// tmp is used to get the vertices in the same order as used by the recursive version
			// tmp is not necessary, if you don't care about the order
			Stack<Integer> tmp = new Stack<>();
			for (int w : G.adj(v)) {
				if (!marked[w]) {
					edgeTo[w] = v;
					tmp.push(w);
				}
			}
			while (!tmp.isEmpty ())
				stack.push (tmp.pop ());
		}
	}



	// is there a path between s and w?
	public boolean hasPathTo(int w) {
		return marked[w];
	}

	// return a path between s to w; null if no such path
	public Iterable<Integer> pathTo(int w) {
		if (!hasPathTo(w)) return null;
		Stack<Integer> path = new Stack<>();
		for (int x = w; x != s; x = edgeTo[x])
			path.push(x);
		path.push(s);
		return path;
	}

	public static void main(String[] args) {
		args = new String [] { "data/tinyG.txt", "0" };
		//args = new String [] { "data/tinyCG.txt", "0" };

		In in = new In(args[0]);
		Graph G = GraphGenerator.fromIn (in);
		StdOut.println (G);
		G.toGraphviz ("graph");

		int s = Integer.parseInt(args[1]);
		DepthFirstPaths dfs = new DepthFirstPaths(G, s);

		for (int v = 0; v < G.V(); v++) {
			if (dfs.hasPathTo(v)) {
				StdOut.format("%d to %d:  ", s, v);
				for (int x : dfs.pathTo(v)) {
					if (x == s) StdOut.print(x);
					else        StdOut.print("-" + x);
				}
				StdOut.println();
			}

			else {
				StdOut.format("%d to %d:  not connected\n", s, v);
			}

		}
	}

}
