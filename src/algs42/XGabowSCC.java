package algs42;
import stdlib.*;
import algs13.Queue;
import algs13.Stack;
/* ***********************************************************************
 *  Compilation:  javac GabowSCC.java
 *  Execution:    java GabowSCC V E
 *  Dependencies: Digraph.java Stack.java TransitiveClosure.java StdOut.java
 *
 *  Compute the strongly-connected components of a digraph using
 *  Gabow's algorithm (aka Cheriyan-Mehlhorn algorithm).
 *
 *  Runs in O(E + V) time.
 *
 *  % java GabowSCC tinyDG.txt
 *  5 components
 *  1
 *  0 2 3 4 5
 *  9 10 11 12
 *  6
 *  7 8
 *
 *************************************************************************/

public class XGabowSCC {

	private final boolean[] marked;        // marked[v] = has v been visited?
	private final int[] id;                // id[v] = id of strong component containing v
	private final int[] preorder;          // preorder[v] = preorder of v
	private int pre;                 // preorder number counter
	private int count;               // number of strongly-connected components
	private final Stack<Integer> stack1;
	private final Stack<Integer> stack2;


	public XGabowSCC(Digraph G) {
		marked = new boolean[G.V()];
		stack1 = new Stack<>();
		stack2 = new Stack<>();
		id = new int[G.V()];
		preorder = new int[G.V()];
		for (int v = 0; v < G.V(); v++) id[v] = -1;

		for (int v = 0; v < G.V(); v++) {
			if (!marked[v]) dfs(G, v);
		}

		// check that id[] gives strong components
		assert check(G);
	}

	private void dfs(Digraph G, int v) {
		marked[v] = true;
		preorder[v] = pre++;
		stack1.push(v);
		stack2.push(v);
		for (int w : G.adj(v)) {
			if (!marked[w]) dfs(G, w);
			else if (id[w] == -1) {
				while (preorder[stack2.peek()] > preorder[w])
					stack2.pop();
			}
		}

		// found strong component containing v
		if (stack2.peek() == v) {
			stack2.pop();
			int w;
			do {
				w = stack1.pop();
				id[w] = count;
			} while (w != v);
			count++;
		}
	}



	// return the number of strongly connected components
	public int count() { return count; }


	// are v and w strongly connected?
	public boolean stronglyConnected(int v, int w) {
		return id[v] == id[w];
	}

	// in which strongly connected component is vertex v?
	public int id(int v) { return id[v]; }

	// does the id[] array contain the strongly connected components?
	private boolean check(Digraph G) {
		TransitiveClosure tc = new TransitiveClosure(G);
		for (int v = 0; v < G.V(); v++) {
			for (int w = 0; w < G.V(); w++) {
				if (stronglyConnected(v, w) != (tc.reachable(v, w) && tc.reachable(w, v)))
					return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		args = new String[] { "data/tinyDG.txt" };

		In in = new In(args[0]);
		Digraph G = DigraphGenerator.fromIn(in);
		XGabowSCC scc = new XGabowSCC(G);

		// number of connected components
		int M = scc.count();
		StdOut.println(M + " components");

		// compute list of vertices in each strong component
		@SuppressWarnings("unchecked")
		final
		Queue<Integer>[] components = new Queue[M];
		for (int i = 0; i < M; i++) {
			components[i] = new Queue<>();
		}
		for (int v = 0; v < G.V(); v++) {
			components[scc.id(v)].enqueue(v);
		}

		// print results
		for (int i = 0; i < M; i++) {
			for (int v : components[i]) {
				StdOut.print(v + " ");
			}
			StdOut.println();
		}

	}

}
