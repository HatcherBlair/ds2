package algs32;
import java.util.Scanner;
import algs13.Queue;
import stdlib.*;

public class XTree {
	private Node root;
	private XTree (Node root) { this.root = root; }
	private static class Node {
		public final int val;
		public final Queue<Node> children; // children must not be null
		public Node (int val) {
			this.val = val;
			this.children = new Queue<>();
		}
	}

	// tree input has the form "num ( ... )"
	// where num is an integer and ... is another tree input
	// example: "0 ( 1 ( 11 12 13 ) 2 ( 21 22 ( 221 222 223 ) ) 3 ( ) 4 )"
	// empty parens "( )" are optional.
	public static XTree parse (String in) {
		Scanner sc = new Scanner (in);
		Node root = parseHelper (sc);
		return new XTree (root);
	}
	private static Node parseHelper (Scanner sc) {
		int val = sc.nextInt ();
		Node result = new Node (val);
		if (sc.hasNext ("\\(")) {
			sc.next (); // gobble "("
			while (sc.hasNextInt ()) {
				Node child = parseHelper (sc);
				result.children.enqueue (child);
			}
			sc.next ("\\)"); //gobble ")"
		}
		return result;
	}

	// prefix with parenthesis
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if (root != null) toString (sb, root);
		return sb.toString ();
	}
	private static void toString (StringBuilder sb, Node n) {
		sb.append (n.val);
		sb.append (" ");
		if (!n.children.isEmpty ()) {
			sb.append ("( ");
			for (Node child : n.children)
				toString (sb, child);
			sb.append (") ");
		}
	}

	public void toGraphviz(String filename) {
		GraphvizBuilder gb = new GraphvizBuilder ();
		if (root != null) toGraphviz (gb, null, root);
		gb.toFileUndirected (filename, "ordering=\"out\"");
	}
	private static void toGraphviz (GraphvizBuilder gb, Node parent, Node n) {
		gb.addLabeledNode (n, Integer.toString (n.val));
		if (parent != null) gb.addEdge (parent, n);
		for (Node child : n.children)
			toGraphviz (gb, n, child);
	}

	// prefix order traversal
	public void printPre () {
		if (root != null) printPre (root);
		StdOut.println ();
	}
	private static void printPre (Node n) {
		StdOut.print (n.val + " ");
		for (Node child : n.children) {
			printPre (child);
		}
	}

	// level order traversal
	public void printLevel () {
		Queue<Node> queue = new Queue<>();
		if (root != null) queue.enqueue(root);
		while (!queue.isEmpty()) {
			Node n = queue.dequeue();
			StdOut.print (n.val + " ");
			for (Node child : n.children) {
				queue.enqueue(child);
			}
		}
		StdOut.println ();
	}

	public static void main(String[] args) {
		XTree xtree = XTree.parse ("0 ( 1 ( 11 12 ( 121 ( 1211 1212 ) 122 123 124 125 ) 13 ) 2 ( 21 22 ( 221 222 223 ) ) 3 )");
		StdOut.println (xtree);
		xtree.printPre ();
		xtree.printLevel ();
		xtree.toGraphviz ("xtree.png");
	}
}
