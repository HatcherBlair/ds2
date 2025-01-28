package algs32;

import java.util.Scanner;
import stdlib.*;

// A Playground for binary trees

public class XBTree {
	static class BNode {
		public BNode (double item, BNode left, BNode right) {
			this.item = item;
			this.left = left;
			this.right = right;
		}
		public double item;
		public BNode left;
		public BNode right;
	}
	BNode root;

	// height/depth of empty tree is -1
	// height/depth of singleton tree is 0
	public int height () {
		//TODO
		return StdRandom.uniform (100);
	}
	public int depth () {
		//TODO
		return StdRandom.uniform (100);
	}	
	public int depthOfShallowestFive () {
		//TODO
		return StdRandom.uniform (100);
	}
	public int heightOfShortestFive () {
		//TODO
		return StdRandom.uniform (100);
	}

	// prefix with parenthesis
	public String toString () {
		StringBuilder sb = new StringBuilder ();
		toString (sb, root);
		return sb.toString ();
	}
	private static void toString (StringBuilder sb, BNode n) {
		if (n == null) {
			sb.append ("X ");			
		} else if (n.left == null && n.right == null) {
			sb.append (n.item);
			sb.append (" ");
		} else {
			sb.append ("( ");
			sb.append (n.item);
			sb.append (" ");
			toString (sb, n.left);			
			toString (sb, n.right);			
			sb.append (") ");
		}
	}
	public void toGraphviz (String filename) {
		GraphvizBuilder gb = new GraphvizBuilder ();
		toGraphviz (gb, null, root);
		gb.toFileUndirected (filename, "ordering=\"out\"");
	}
	private void toGraphviz (GraphvizBuilder gb, BNode parent, BNode n) {
		if (n == null) {
			gb.addNullEdge (parent);
			return;
		}
		gb.addLabeledNode (n, Double.toString (n.item));
		if (parent != null) gb.addEdge (parent, n);
		toGraphviz (gb, n, n.left);
		toGraphviz (gb, n, n.right);
	}

	public static XBTree randomTree (double chanceOfNull, int maxDepth) {
		XBTree result = new XBTree ();
		result.root = randomTreeHelper (chanceOfNull, maxDepth, 0);
		return result;
	}
	private static BNode randomTreeHelper (double chanceOfNull, int maxDepth, int depth) {
		if (depth > maxDepth || StdRandom.uniform () < chanceOfNull * depth) 
			return null;
		BNode left = randomTreeHelper (chanceOfNull, maxDepth, depth+1);
		BNode right = randomTreeHelper (chanceOfNull, maxDepth, depth+1);
		double val = StdRandom.uniform (10);
		return new BNode (val, left, right);
	}
	
	// Parses the following kinds of tree "T"
	// T ::= "X" -- empty
	// T ::= num -- a node with no children
	// T ::= ( num T T ) -- a node with children
	// For example: ( 0 ( 01 12 X ) 02 )
	public static XBTree of (String in) {
		Scanner sc = new Scanner (in);
		XBTree result = new XBTree ();
		result.root = ofHelper (sc);
		return result;
	}
	private static BNode ofHelper (Scanner sc) {
		BNode result = null;
		if (sc.hasNext ("X")) {			
			sc.next (); // gobble "X"
		}
		if (sc.hasNextDouble ()) {
			double val = sc.nextDouble ();
			result = new BNode (val, null, null);
		} else if (sc.hasNext ("\\(")) {
			sc.next (); // gobble "("
			double val = sc.nextDouble ();
			result = new BNode (val, null, null);
			result.left = ofHelper (sc);
			result.right = ofHelper (sc);
			sc.next ("\\)"); //gobble ")"
		} 
		return result;
	}
	private static int count = 0;
	private static void testHeightOfShortestFive (XBTree tree, int expected) {
		int actual = tree.heightOfShortestFive ();
		if (!DEBUG && expected != actual) {
			//if (!DEBUG && ((expected >= 0 && expected != actual) || (expected < 0 && actual > 0))) {
			count++;
			tree.toGraphviz ("error" + count + ".png");
			StdOut.format ("error%d.heightOfShortestFive: expected=%d, actual=%d\n", count, expected, actual);
		}
	}
	private static void testDepthOfShallowestFive (XBTree tree, int expected) {
		int actual = tree.depthOfShallowestFive ();
		if (!DEBUG && expected != actual) {
			count++;
			tree.toGraphviz ("error" + count + ".png");
			StdOut.format ("error%d.depthOfShallowestFive: expected=%d, actual=%d\n", count, expected, actual);
		}
	}
	
	public static boolean DEBUG = false;
	public static XBTree demoTree () {
		String sl = "( 0 ( 0 X ( 0 ( 0 X ( 0 X X ) ) X ) ) ( 0 X ( 0 X X ) ) )";
		String sr = "( 0 ( 0 ( 0 ( 0 X X ) ( 0 X X ) ) ( 0 X ( 0 ( 0 X X ) X ) ) ) ( 0 ( 0 X X ) X ) )";
		String s = "( 0 " + sl + " " + sr + " )";
		return XBTree.of (s);
	}
	public static void main (String[] args) {
		if (DEBUG) Trace.run (); 
		
		XBTree t0 = demoTree();			
		testDepthOfShallowestFive(t0, -1);
		t0.root.right.left.right.left.left.item = 5.0;
		testDepthOfShallowestFive(t0, 5);
		t0.root.left.left.left.left.left.item = 5.0;
		testDepthOfShallowestFive(t0, 5);
		t0.root.left.left.left.left.item = 5.0;
		testDepthOfShallowestFive(t0, 4);
		t0.root.right.left.right.item = 5.0;
		testDepthOfShallowestFive(t0, 3);
		t0.root.right.right.item = 5.0;
		testDepthOfShallowestFive(t0, 2);
		t0.root.left.item = 5.0;
		testDepthOfShallowestFive(t0, 1);
		t0.root.item = 5.0;
		testDepthOfShallowestFive(t0, 0);

		XBTree t1 = demoTree();			
		testHeightOfShortestFive(t1, -7);
		t1.root.item = 5.0;
		testHeightOfShortestFive(t1, 5);
		t1.root.left.item = 5.0;
		testHeightOfShortestFive(t1, 4);
		t1.root.right.item = 5.0;
		testHeightOfShortestFive(t1, 4);		
		t1.root.right.left.item = 5.0;
		testHeightOfShortestFive(t1, 3);
		t1.root.left.left.left.item = 5.0;
		testHeightOfShortestFive(t1, 2);
		t1.root.left.left.left.left.item = 5.0;
		testHeightOfShortestFive(t1, 1);
		t1.root.right.left.right.left.left.item = 5.0;
		testHeightOfShortestFive(t1, 0);
		StdOut.println ("Finished tests");
		
//		YBTree t2 = demoTree();			
//		t2.toGraphviz ("t2.png");		
//		StdOut.format ("t2: %s\n", t2);
//		StdOut.format ("%d = t2.depth()\n", t2.depth ());
//		StdOut.format ("%d = t2.height()\n", t2.height ());
//		StdOut.format ("%d = t2.depthOfShallowestFive()\n", t2.depthOfShallowestFive ());
//		StdOut.format ("%d = t2.heightOfShortestFive()\n", t2.heightOfShortestFive ());
//		
//		YBTree t3 = randomTree (0.10, 6);
//		t3.toGraphviz ("t3.png");
//		StdOut.format ("t3: %s\n", tree0);			
//		StdOut.format ("%d = t3.depth()\n", t3.depth ());
//		StdOut.format ("%d = t3.height()\n", t3.height ());
//		StdOut.format ("%d = t3.depthOfShallowestFive()\n", t3.depthOfShallowestFive ());
//		StdOut.format ("%d = t3.heightOfShortestFive()\n", t3.heightOfShortestFive ());
	}
}
