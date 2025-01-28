package algs41;

import java.util.Arrays;
import algs13.Stack;
import algs24.MinPQ;
import stdlib.*;

public class GraphGenerator {

	/**
	 * Create a graph from input stream.
	 */
	public static Graph fromIn (In in) {
		Graph G = new Graph (in.readInt());
		int E = in.readInt();
		for (int i = 0; i < E; i++) {
			int v = in.readInt();
			int w = in.readInt();
			G.addEdge(v, w);
		}
		return G;
	}
	public static Graph copy (Graph G) {
		Graph R = new Graph (G.V());
        for (int v = 0; v < G.V(); v++) {
            // reverse so that adjacency list is in same order as original
            Stack<Integer> reverse = new Stack<Integer>();
            for (int w : G.adj(v)) {
                reverse.push(w);
            }
            for (int w : reverse) {
                R.addEdge (v, w);
            }
        }
		return R;
	}
	public static Graph complete (int V) {
		return simple (V, V * (V - 1) / 2);
	}
	public static Graph simple (int V, int E) {
		if (V < 0 || E < 0) throw new IllegalArgumentException ();
		if (E > V * (V - 1) / 2) throw new IllegalArgumentException ("Number of edges must be less than V*(V-1)/2");
		Graph G = new Graph (V);
		newEdge: while (E > 0) {
			int v = StdRandom.uniform (V);
			int w = StdRandom.uniform (V);
			if (v == w) continue;
			for (int w2 : G.adj (v))
				if (w == w2) continue newEdge;
			G.addEdge (v, w);
			E--;
		}
		return G;
	}
	public static Graph simpleConnected (int V, int E) {
		if (V < 0 || E < 0) throw new IllegalArgumentException ();
		if (E > V * (V - 1) / 2) throw new IllegalArgumentException ("Number of edges must be less than V*(V-1)/2");
		Graph G = spanningTree (V);
		newEdge: while (G.E () < E) {
			int v = StdRandom.uniform (V);
			int w = StdRandom.uniform (V);
			if (v == w) continue;
			for (int w2 : G.adj (v))
				if (w == w2) continue newEdge;
			G.addEdge (v, w);
			E--;
		}
		return G;
	}
	public static Graph connected (int V, int E) {
		if (V < 0 || E < 0) throw new IllegalArgumentException ();
		Graph G = spanningTree (V);
		while (G.E () < E) {
			int v = StdRandom.uniform (V);
			int w = StdRandom.uniform (V);
			G.addEdge (v, w);
		}
		return G;
	}
	public static Graph random (int V, int E) {
		if (V < 0 || E < 0) throw new IllegalArgumentException ();
		Graph G = new Graph (V);
		while (G.E () < E) {
			int v = StdRandom.uniform (V);
			int w = StdRandom.uniform (V);
			G.addEdge (v, w);
		}
		return G;
	}
	public static Graph spanningTree (int V) {
		if (V < 1) throw new IllegalArgumentException ();
		int[] vertices = new int[V];
		for (int i = 0; i < V; i++)
			vertices[i] = i;
		StdRandom.shuffle (vertices);
		Graph G = new Graph (V);
		for (int i = 1; i < V; i++) {
			int v = vertices[StdRandom.uniform (i)];
			int w = vertices[i];
			G.addEdge (v, w);
		}
		return G;
	}

	public static Graph connected(int V, int E, int c) {
		if (c >= V || c <= 0)
			throw new IllegalArgumentException("Number of components must be between 1 and V");
		if (E <= (V-c))
			throw new IllegalArgumentException("Number of edges must be at least (V-c)");
		if (E > V * (V - 1) / 2)
			throw new IllegalArgumentException("Too many edges");

		int[] label = new int[V];
		for (int v = 0; v < V; v++) {
			label[v] = StdRandom.uniform(c);
		}
		// The following hack ensures that each color appears at least once
		{
			Arrays.sort (label);
			label[0] = 0;
			for (int v = 1; v < V; v++) {
				if (label[v]-label[v-1] > 1 || V-v == c-label[v-1]-1)
					label[v] = label[v-1]+1;
			}
			StdRandom.shuffle (label);
		}

		// make all vertices with label c a connected component
		Graph G = new Graph(V);
		for (int i = 0; i < c; i++) {
			// how many vertices in component c
			int count = 0;
			for (int v = 0; v < V; v++) {
				if (label[v] == i) count++;
			}
			int[] vertices = new int[count];
			{
				int j = 0;
				for (int v = 0; v < V; v++)
					if (label[v] == i) vertices[j++] = v;
			}
			StdRandom.shuffle(vertices);

			for (int j = 1; j < count; j++) {
				int v = vertices[StdRandom.uniform (j)];
				int w = vertices[j];
				G.addEdge (v, w);
			}
		}

		while (G.E() < E) {
			int v = StdRandom.uniform(V);
			int w = StdRandom.uniform(V);
			if (v != w && label[v] == label[w]) {
				G.addEdge(v, w);
			}
		}

		return G;
	}
    /**
     * Returns a random simple bipartite graph on {@code V1} and {@code V2} vertices,
     * containing each possible edge with probability {@code p}.
     * @param V1 the number of vertices in one partition
     * @param V2 the number of vertices in the other partition
     * @param p the probability that the graph contains an edge with one endpoint in either side
     * @return a random simple bipartite graph on {@code V1} and {@code V2} vertices,
     *    containing each possible edge with probability {@code p}
     * @throws IllegalArgumentException if probability is not between 0 and 1
     */
    public static Graph bipartite(int V1, int V2, double p) {
        if (p < 0.0 || p > 1.0)
            throw new IllegalArgumentException("Probability must be between 0 and 1");
        int[] vertices = new int[V1 + V2];
        for (int i = 0; i < V1 + V2; i++)
            vertices[i] = i;
        StdRandom.shuffle(vertices);
        Graph G = new Graph(V1 + V2);
        for (int i = 0; i < V1; i++)
            for (int j = 0; j < V2; j++)
                if (StdRandom.bernoulli(p))
                    G.addEdge(vertices[i], vertices[V1+j]);
        return G;
    }

    /**
     * Returns a path graph on {@code V} vertices.
     * @param V the number of vertices in the path
     * @return a path graph on {@code V} vertices
     */
    public static Graph path(int V) {
        Graph G = new Graph(V);
        int[] vertices = new int[V];
        for (int i = 0; i < V; i++)
            vertices[i] = i;
        StdRandom.shuffle(vertices);
        for (int i = 0; i < V-1; i++) {
            G.addEdge(vertices[i], vertices[i+1]);
        }
        return G;
    }

    /**
     * Returns a complete binary tree graph on {@code V} vertices.
     * @param V the number of vertices in the binary tree
     * @return a complete binary tree graph on {@code V} vertices
     */
    public static Graph binaryTree(int V) {
        Graph G = new Graph(V);
        int[] vertices = new int[V];
        for (int i = 0; i < V; i++)
            vertices[i] = i;
        StdRandom.shuffle(vertices);
        for (int i = 1; i < V; i++) {
            G.addEdge(vertices[i], vertices[(i-1)/2]);
        }
        return G;
    }

    /**
     * Returns a cycle graph on {@code V} vertices.
     * @param V the number of vertices in the cycle
     * @return a cycle graph on {@code V} vertices
     */
    public static Graph cycle(int V) {
        Graph G = new Graph(V);
        int[] vertices = new int[V];
        for (int i = 0; i < V; i++)
            vertices[i] = i;
        StdRandom.shuffle(vertices);
        for (int i = 0; i < V-1; i++) {
            G.addEdge(vertices[i], vertices[i+1]);
        }
        G.addEdge(vertices[V-1], vertices[0]);
        return G;
    }

    /**
     * Returns an Eulerian cycle graph on {@code V} vertices.
     *
     * @param  V the number of vertices in the cycle
     * @param  E the number of edges in the cycle
     * @return a graph that is an Eulerian cycle on {@code V} vertices
     *         and {@code E} edges
     * @throws IllegalArgumentException if either {@code V <= 0} or {@code E <= 0}
     */
    public static Graph eulerianCycle(int V, int E) {
        if (E <= 0)
            throw new IllegalArgumentException("An Eulerian cycle must have at least one edge");
        if (V <= 0)
            throw new IllegalArgumentException("An Eulerian cycle must have at least one vertex");
        Graph G = new Graph(V);
        int[] vertices = new int[E];
        for (int i = 0; i < E; i++)
            vertices[i] = StdRandom.uniform(V);
        for (int i = 0; i < E-1; i++) {
            G.addEdge(vertices[i], vertices[i+1]);
        }
        G.addEdge(vertices[E-1], vertices[0]);
        return G;
    }

    /**
     * Returns an Eulerian path graph on {@code V} vertices.
     *
     * @param  V the number of vertices in the path
     * @param  E the number of edges in the path
     * @return a graph that is an Eulerian path on {@code V} vertices
     *         and {@code E} edges
     * @throws IllegalArgumentException if either {@code V <= 0} or {@code E < 0}
     */
    public static Graph eulerianPath(int V, int E) {
        if (E < 0)
            throw new IllegalArgumentException("negative number of edges");
        if (V <= 0)
            throw new IllegalArgumentException("An Eulerian path must have at least one vertex");
        Graph G = new Graph(V);
        int[] vertices = new int[E+1];
        for (int i = 0; i < E+1; i++)
            vertices[i] = StdRandom.uniform(V);
        for (int i = 0; i < E; i++) {
            G.addEdge(vertices[i], vertices[i+1]);
        }
        return G;
    }

    /**
     * Returns a wheel graph on {@code V} vertices.
     * @param V the number of vertices in the wheel
     * @return a wheel graph on {@code V} vertices: a single vertex connected to
     *     every vertex in a cycle on {@code V-1} vertices
     */
    public static Graph wheel(int V) {
        if (V <= 1) throw new IllegalArgumentException("Number of vertices must be at least 2");
        Graph G = new Graph(V);
        int[] vertices = new int[V];
        for (int i = 0; i < V; i++)
            vertices[i] = i;
        StdRandom.shuffle(vertices);

        // simple cycle on V-1 vertices
        for (int i = 1; i < V-1; i++) {
            G.addEdge(vertices[i], vertices[i+1]);
        }
        G.addEdge(vertices[V-1], vertices[1]);

        // connect vertices[0] to every vertex on cycle
        for (int i = 1; i < V; i++) {
            G.addEdge(vertices[0], vertices[i]);
        }

        return G;
    }

    /**
     * Returns a star graph on {@code V} vertices.
     * @param V the number of vertices in the star
     * @return a star graph on {@code V} vertices: a single vertex connected to
     *     every other vertex
     */
    public static Graph star(int V) {
        if (V <= 0) throw new IllegalArgumentException("Number of vertices must be at least 1");
        Graph G = new Graph(V);
        int[] vertices = new int[V];
        for (int i = 0; i < V; i++)
            vertices[i] = i;
        StdRandom.shuffle(vertices);

        // connect vertices[0] to every other vertex
        for (int i = 1; i < V; i++) {
            G.addEdge(vertices[0], vertices[i]);
        }

        return G;
    }

    /**
     * Returns a uniformly random {@code k}-regular graph on {@code V} vertices
     * (not necessarily simple). The graph is simple with probability only about e^(-k^2/4),
     * which is tiny when k = 14.
     *
     * @param V the number of vertices in the graph
     * @param k degree of each vertex
     * @return a uniformly random {@code k}-regular graph on {@code V} vertices.
     */
    public static Graph regular(int V, int k) {
        if (V*k % 2 != 0) throw new IllegalArgumentException("Number of vertices * k must be even");
        Graph G = new Graph(V);

        // create k copies of each vertex
        int[] vertices = new int[V*k];
        for (int v = 0; v < V; v++) {
            for (int j = 0; j < k; j++) {
                vertices[v + V*j] = v;
            }
        }

        // pick a random perfect matching
        StdRandom.shuffle(vertices);
        for (int i = 0; i < V*k/2; i++) {
            G.addEdge(vertices[2*i], vertices[2*i + 1]);
        }
        return G;
    }

    // http://www.proofwiki.org/wiki/Labeled_Tree_from_Prüfer_Sequence
    // http://citeseerx.ist.psu.edu/viewdoc/download?doi=10.1.1.36.6484&rep=rep1&type=pdf
    /**
     * Returns a uniformly random tree on {@code V} vertices.
     * This algorithm uses a Prufer sequence and takes time proportional to <em>V log V</em>.
     * @param V the number of vertices in the tree
     * @return a uniformly random tree on {@code V} vertices
     */
    public static Graph tree(int V) {
        Graph G = new Graph(V);

        // special case
        if (V == 1) return G;

        // Cayley's theorem: there are V^(V-2) labeled trees on V vertices
        // Prufer sequence: sequence of V-2 values between 0 and V-1
        // Prufer's proof of Cayley's theorem: Prufer sequences are in 1-1
        // with labeled trees on V vertices
        int[] prufer = new int[V-2];
        for (int i = 0; i < V-2; i++)
            prufer[i] = StdRandom.uniform(V);

        // degree of vertex v = 1 + number of times it appers in Prufer sequence
        int[] degree = new int[V];
        for (int v = 0; v < V; v++)
            degree[v] = 1;
        for (int i = 0; i < V-2; i++)
            degree[prufer[i]]++;

        // pq contains all vertices of degree 1
        MinPQ<Integer> pq = new MinPQ<Integer>();
        for (int v = 0; v < V; v++)
            if (degree[v] == 1) pq.insert(v);

        // repeatedly delMin() degree 1 vertex that has the minimum index
        for (int i = 0; i < V-2; i++) {
            int v = pq.delMin();
            G.addEdge(v, prufer[i]);
            degree[v]--;
            degree[prufer[i]]--;
            if (degree[prufer[i]] == 1) pq.insert(prufer[i]);
        }
        G.addEdge(pq.delMin(), pq.delMin());
        return G;
    }

	public static void print (Graph G, String filename) {
		if (G == null) return;
		System.out.println (filename);
		System.out.println (G);
		System.out.println ();
		G.toGraphviz (filename + ".png");
	}
	
	public static void main (String[] args) {
		//StdRandom.setSeed (10);
		args = new String[] { "6", "10", "3" };

		int V = Integer.parseInt (args[0]);
		int E = Integer.parseInt (args[1]);
		int c = Integer.parseInt (args[2]);

		for (int i= 5; i>0; i--) {
			print (GraphGenerator.random (V, E), "random-" + V + "-" + E);
			print (GraphGenerator.random (V, E), "random-" + V + "-" + E);
			print (GraphGenerator.simple (V, E), "simple-" + V + "-" + E);
			print (GraphGenerator.complete (V), "complete-" + V);
			print (GraphGenerator.spanningTree (V), "spanningTree-" + V);
			print (GraphGenerator.simpleConnected (V, E), "simpleConnected-" + V + "-" + E);
			print (GraphGenerator.connected (V, E), "connected-" + V + "-" + E);
			print (GraphGenerator.path (V), "path-" + V);
			print (GraphGenerator.binaryTree (V), "binaryTree-" + V);
			print (GraphGenerator.cycle (V), "cycle-" + V);
			if (E <= (V - c)) E = (V - c) + 1;
			print (GraphGenerator.connected (V, E, c), "connected-" + V + "-" + E + "-" + c);
			print (GraphGenerator.eulerianCycle (V, E), "eulerian-" + V + "-" + E);
		}
	}
}
