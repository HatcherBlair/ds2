package algs44;
import stdlib.*;
/* ***********************************************************************
 *  Compilation:  javac DirectedEdge.java
 *  Execution:    java DirectedEdge
 *
 *  Immutable weighted directed edge.
 *
 *************************************************************************/

/**
 *  The {@code DirectedEdge} class represents a weighted edge in an directed graph.
 *  <p>
 *  For additional documentation, see <a href="http://algs4.cs.princeton.edu/44sp">Section 4.4</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 */

public class DirectedEdge implements Comparable<DirectedEdge> {
	private final int v;
	private final int w;
	private final double weight;

	/**
	 * Create a directed edge from v to w with given weight.
	 */
	public DirectedEdge(int v, int w, double weight) {
		this.v = v;
		this.w = w;
		this.weight = weight;
	}

	/**
	 * Return the vertex where this edge begins.
	 */
	public int from() {
		return v;
	}

	/**
	 * Return the vertex where this edge ends.
	 */
	public int to() {
		return w;
	}

	/**
	 * Return the weight of this edge.
	 */
	public double weight() { return weight; }

	/**
	 * Return a string representation of this edge.
	 */
	public String toString() {
		return String.format("%d->%d %.2f", v, w, weight);
	}

	/**
	 * Compare by weights.
	 */
	public int compareTo (DirectedEdge o) {
		if (weight < o.weight) return -1;
		if (weight > o.weight) return 1;
		return 0;
	}

	/**
	 * Test client.
	 */
	public static void main(String[] args) {
		DirectedEdge e = new DirectedEdge(12, 23, 3.14);
		StdOut.println(e);
	}
}
