package algs32;
import algs13.Queue;
import stdlib.*;

/* ***********************************************************************
 * A simple BST with int keys and no values
 *
 * Complete each function below.
 * Write each function as a separate recursive definition (do not use more than one helper per function).
 * Depth of root==0.
 * Height of leaf==0.
 * Size of empty tree==0.
 * Height of empty tree=-1.
 *************************************************************************/
public class MyIntSET2 {
	private Node root;
	private static class Node {
		public final int key;
		public Node left, right;
		public int H;
		public Node(int key) { this.key = key; }
	}

	// The field "H" indicates the height of each node.
	// You must then modify "put" to update the height of each node.
	// "removeHeight" should also update the field "H".

	// the number of nodes in the tree, at exactly height k
	// include node n if height(n) == k
	public int sizeAtHeight(int k) { return 0; }

	// the number of nodes in the tree, above height k (not including k)
	// include node n if height(n) > k
	public int sizeAboveHeight(int k) { return 0; }

	// the number of nodes in the tree, below height k (not including k)
	// include node n if height(n) < k
	public int sizeBelowHeight(int k) { return 0; }

	// remove all subtrees of height k from the original tree
	public void removeHeight(int k) {}

	/* ***************************************************************************
	 * Some methods to create and display trees
	 *****************************************************************************/

	// Modify "put" to update the field "H".
	public void put(int key) { root = put(root, key); }
	private Node put(Node n, int key) {
		if (n == null) return new Node(key);
		if      (key < n.key) n.left  = put(n.left,  key);
		else if (key > n.key) n.right = put(n.right, key);
		return n;
	}
	// Do not modify "copy"
	public MyIntSET2 copy () {
		MyIntSET2 that = new MyIntSET2 ();
		for (int key : levelOrder())
			that.put (key);
		return that;
	}
	// Do not modify "levelOrder"
	public Iterable<Integer> levelOrder() {
		Queue<Integer> keys = new Queue<>();
		Queue<Node> queue = new Queue<>();
		queue.enqueue(root);
		while (!queue.isEmpty()) {
			Node n = queue.dequeue();
			if (n == null) continue;
			keys.enqueue(n.key);
			queue.enqueue(n.left);
			queue.enqueue(n.right);
		}
		return keys;
	}
	// Do not modify "toString"
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int key: levelOrder())
			sb.append (key + " ");
		return sb.toString ();
	}
	// You may modify "toGraphviz" if you wish
	public void toGraphviz(String filename) {
		GraphvizBuilder gb = new GraphvizBuilder ();
		toGraphviz (gb, null, root);
		gb.toFileUndirected (filename, "ordering=\"out\"");
	}
	private static void toGraphviz (GraphvizBuilder gb, Node parent, Node n) {
		if (n == null) { gb.addNullEdge (parent); return; }
		gb.addLabeledNode (n, Integer.toString (n.key));
		if (parent != null) gb.addEdge (parent, n);
		toGraphviz (gb, n, n.left);
		toGraphviz (gb, n, n.right);
	}

	/* ***************************************************************************
	 *  Test client
	 *  You can modify any of these methods, if you wish
	 *****************************************************************************/
	// create a tree from a string
	public static MyIntSET2 fromString (String ints) {
		MyIntSET2 set = new MyIntSET2 ();
		for (String s : ints.split (" "))
			try { set.put (Integer.parseInt (s)); } catch (NumberFormatException e) {}
		return set;
	}
	private static int exampleCount = 0;
	public static void show(MyIntSET2 set, String filename) {
		exampleCount++;
		set.toGraphviz ("x" + exampleCount + "-" + filename + ".png");
	}
	public static void test(String s) {
		MyIntSET2 set = MyIntSET2.fromString(s);
		StdOut.println ("###############################################################");
		StdOut.format ("tree: %s\n", set); show(set, "original");

		//StdOut.format ("size()                 = %d\n", set.size());
		//StdOut.format ("height()               = %d\n", set.height());
		//StdOut.format ("sizeOdd()              = %d\n", set.sizeOdd());
		//StdOut.format ("sizeAtDepth(2)         = %d\n", set.sizeAtDepth(2));
		//StdOut.format ("sizeAboveDepth(2)      = %d\n", set.sizeAboveDepth(2));
		//StdOut.format ("sizeBelowDepth(2)      = %d\n", set.sizeBelowDepth(2));
		//StdOut.format ("isPerfectlyBalancedS() = %b\n", set.isPerfectlyBalancedS());
		//StdOut.format ("isPerfectlyBalancedH() = %b\n", set.isPerfectlyBalancedH());
		//StdOut.format ("isOddBalanced()        = %b\n", set.isOddBalanced());
		//StdOut.format ("isSemiBalanced()       = %b\n", set.isSemiBalanced());
		StdOut.format ("sizeAtHeight(2)        = %d\n", set.sizeAtHeight(2));
		StdOut.format ("sizeAboveHeight(2)     = %d\n", set.sizeAboveHeight(2));
		StdOut.format ("sizeBelowHeight(2)     = %d\n", set.sizeBelowHeight(2));
		
		//set = MyIntSET2.fromString(s); set.removeOddSubtrees(); StdOut.format ("removeOddSubtrees()  %s\n", set); show(set, "removeOddSubtrees");
		//set = MyIntSET2.fromString(s); set.removeBelowDepth(2); StdOut.format ("removeBelowDepth(2)  %s\n", set); show(set, "removeBelowDepth2");
		//set = MyIntSET2.fromString(s); set.removeLeaves();      StdOut.format ("removeLeaves()       %s\n", set); show(set, "removeLeaves");
		//set = MyIntSET2.fromString(s); set.removeSingles();     StdOut.format ("removeSingles()      %s\n", set); show(set, "removeSingles");
		//set = MyIntSET2.fromString(s); set.addZeroToSingles();  StdOut.format ("addZeroToSingles()   %s\n", set); show(set, "addZeroToSingles");
		set = MyIntSET2.fromString(s); set.removeHeight(2);     StdOut.format ("removeHeight(2)      %s\n", set); show(set, "removeHeight2");
	}
	public static void main(String[] args) {
		test ("90 30 100 10 80 20 40 60 50 70");
		//test ("40 20 60 10 30 50 70");
		//test ("");
		//test ("1");
		//test ("41 21 61 11 31");
		//test ("41 21 61 11 31 51 71 111");
		//test ("101 41 21 61 11 31 51 71 111");
		//test ("101 41 21 61 11 31 51 71 141 121 161 111 131 151 171 ");
		//test ("101 40 20 61 11 31 51 71 140 120 161 111 131 151 171 ");
		//test ("90 30 100 10 81 20 40 60 50 70 62 63 64");
		//test ("40 20 61 10 30 50 70");
		//test ("41 21 61 11 30 51 70");
		//test ("");
		//test ("1");
		//test ("90 30 100 10 80 20 40 60 5 85 35 95 105 2 6 110 103 104 102 93 97 96 82 86 12 21 45 62 106 111 92 94 98 34 36");

	}
}
