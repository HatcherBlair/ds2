package algs32;
import stdlib.*;
import algs13.Queue;
/* ***********************************************************************
 *  Compilation:  javac BSTWithStaticNode.java
 *  Execution:    java BSTWithStaticNode
 *  Dependencies: StdIn.java StdOut.java
 *  Data files:   http://algs4.cs.princeton.edu/32bst/tinyST.txt
 *
 *  A symbol table implemented with a binary search tree.
 *
 *  % more tinyST.txt
 *  S E A R C H E X A M P L E
 *
 *  % java BSTWithStaticNode < tinyST.txt
 *  A 8
 *  C 4
 *  E 12
 *  H 5
 *  L 11
 *  M 9
 *  P 10
 *  R 3
 *  S 0
 *  X 7
 *
 *************************************************************************/

public class XBSTWithNonStaticNode<K extends Comparable<? super K>, V> {
	private Node root;             // root of XBSTWithStaticNode

	private class Node {
		public final K key;           // sorted by key
		public V val;           // associated data
		public Node left, right;  // left and right subtrees
		public int N;           // number of nodes in subtree

		public Node(K key, V val, int N) {
			this.key = key;
			this.val = val;
			this.N = N;
		}
	}

	// is the symbol table empty?
	public boolean isEmpty() { return size() == 0; }

	// return number of key-value pairs in XBSTWithStaticNode
	public int size() { return size(root); }

	// return number of key-value pairs in XBSTWithStaticNode rooted at x
	private int size(Node x) {
		if (x == null) return 0;
		else return x.N;
	}

	/* *********************************************************************
	 *  Search XBSTWithStaticNode for given key, and return associated value if found,
	 *  return null if not found
	 ***********************************************************************/
	// does there exist a key-value pair with given key?
	public boolean contains(K key) { return get(key) != null; }

	// return value associated with the given key, or null if no such key exists
	public V get(K key) { return get(root, key); }

	private V get(Node x, K key) {
		if (x == null) return null;
		int cmp = key.compareTo(x.key);
		if      (cmp < 0) return get(x.left, key);
		else if (cmp > 0) return get(x.right, key);
		else              return x.val;
	}

	/* *********************************************************************
	 *  Insert key-value pair into XBSTWithStaticNode
	 *  If key already exists, update with new value
	 ***********************************************************************/
	public void put(K key, V val) {
		if (val == null) { delete(key); return; }
		root = put(root, key, val);
		//assert check();
	}

	private Node put(Node x, K key, V val) {
		if (x == null) return new Node(key, val, 1);
		int cmp = key.compareTo(x.key);
		if      (cmp < 0) x.left  = put(x.left,  key, val);
		else if (cmp > 0) x.right = put(x.right, key, val);
		else              x.val   = val;
		x.N = 1 + size(x.left) + size(x.right);
		return x;
	}

	/* *********************************************************************
	 *  Delete
	 ***********************************************************************/

	public void deleteMin() {
		if (isEmpty()) throw new Error("Symbol table underflow");
		root = deleteMin(root);
		//assert check();
	}

	private Node deleteMin(Node x) {
		if (x.left == null) return x.right;
		x.left = deleteMin(x.left);
		x.N = size(x.left) + size(x.right) + 1;
		return x;
	}

	public void deleteMax() {
		if (isEmpty()) throw new Error("Symbol table underflow");
		root = deleteMax(root);
		//assert check();
	}

	private Node deleteMax(Node x) {
		if (x.right == null) return x.left;
		x.right = deleteMax(x.right);
		x.N = size(x.left) + size(x.right) + 1;
		return x;
	}

	public void delete(K key) {
		root = delete(root, key);
		//assert check();
	}

	private Node delete(Node x, K key) {
		if (x == null) return null;
		int cmp = key.compareTo(x.key);
		if      (cmp < 0) x.left  = delete(x.left,  key);
		else if (cmp > 0) x.right = delete(x.right, key);
		else {
			if (x.right == null) return x.left;
			if (x.left  == null) return x.right;
			Node t = x;
			x = min(t.right);
			x.right = deleteMin(t.right);
			x.left = t.left;
		}
		x.N = size(x.left) + size(x.right) + 1;
		return x;
	}


	/* *********************************************************************
	 *  Min, max, floor, and ceiling
	 ***********************************************************************/
	public K min() {
		if (isEmpty()) return null;
		return min(root).key;
	}

	private Node min(Node x) {
		if (x.left == null) return x;
		else                return min(x.left);
	}

	public K max() {
		if (isEmpty()) return null;
		return max(root).key;
	}

	private Node max(Node x) {
		if (x.right == null) return x;
		else                 return max(x.right);
	}

	public K floor(K key) {
		Node x = floor(root, key);
		if (x == null) return null;
		else return x.key;
	}

	private Node floor(Node x, K key) {
		if (x == null) return null;
		int cmp = key.compareTo(x.key);
		if (cmp == 0) return x;
		if (cmp <  0) return floor(x.left, key);
		Node t = floor(x.right, key);
		if (t != null) return t;
		else return x;
	}

	public K ceiling(K key) {
		Node x = ceiling(root, key);
		if (x == null) return null;
		else return x.key;
	}

	private Node ceiling(Node x, K key) {
		if (x == null) return null;
		int cmp = key.compareTo(x.key);
		if (cmp == 0) return x;
		if (cmp < 0) {
			Node t = ceiling(x.left, key);
			if (t != null) return t;
			else return x;
		}
		return ceiling(x.right, key);
	}

	/* *********************************************************************
	 *  Rank and selection
	 ***********************************************************************/
	public K select(int k) {
		if (k < 0 || k >= size())  return null;
		Node x = select(root, k);
		return x.key;
	}

	// Return key of rank k.
	private Node select(Node x, int k) {
		if (x == null) return null;
		int t = size(x.left);
		if      (t > k) return select(x.left,  k);
		else if (t < k) return select(x.right, k-t-1);
		else            return x;
	}

	public int rank(K key) {
		return rank(key, root);
	}

	// Number of keys in the subtree less than x.key.
	private int rank(K key, Node x) {
		if (x == null) return 0;
		int cmp = key.compareTo(x.key);
		if      (cmp < 0) return rank(key, x.left);
		else if (cmp > 0) return 1 + size(x.left) + rank(key, x.right);
		else              return size(x.left);
	}

	/* *********************************************************************
	 *  Range count and range search.
	 ***********************************************************************/
	public Iterable<K> keys() {
		return keys(min(), max());
	}

	public Iterable<K> keys(K lo, K hi) {
		Queue<K> queue = new Queue<>();
		keys(root, queue, lo, hi);
		return queue;
	}

	private void keys(Node x, Queue<K> queue, K lo, K hi) {
		if (x == null) return;
		int cmplo = lo.compareTo(x.key);
		int cmphi = hi.compareTo(x.key);
		if (cmplo < 0) keys(x.left, queue, lo, hi);
		if (cmplo <= 0 && cmphi >= 0) queue.enqueue(x.key);
		if (cmphi > 0) keys(x.right, queue, lo, hi);
	}

	public int size(K lo, K hi) {
		if (lo.compareTo(hi) > 0) return 0;
		if (contains(hi)) return rank(hi) - rank(lo) + 1;
		else              return rank(hi) - rank(lo);
	}


	// height of this XBSTWithStaticNode (one-node tree has height 0)
	public int height() { return height(root); }
	private int height(Node x) {
		if (x == null) return -1;
		return 1 + Math.max(height(x.left), height(x.right));
	}

	/* ***********************************************************************
	 *  Check integrity of XBSTWithStaticNode data structure
	 *************************************************************************/
	private boolean check() {
		if (!isBST())            StdOut.println("Not in symmetric order");
		if (!isSizeConsistent()) StdOut.println("Subtree counts not consistent");
		if (!isRankConsistent()) StdOut.println("Ranks not consistent");
		return isBST() && isSizeConsistent() && isRankConsistent();
	}

	// does this binary tree satisfy symmetric order?
	// Note: this test also ensures that data structure is a binary tree since order is strict
	private boolean isBST() {
		return isBST(root, null, null);
	}

	// is the tree rooted at x a XBSTWithStaticNode with all keys strictly between min and max
	// (if min or max is null, treat as empty constraint)
	// Credit: Bob Dondero's elegant solution
	private boolean isBST(Node x, K min, K max) {
		if (x == null) return true;
		if (min != null && x.key.compareTo(min) <= 0) return false;
		if (max != null && x.key.compareTo(max) >= 0) return false;
		return isBST(x.left, min, x.key) && isBST(x.right, x.key, max);
	}

	// are the size fields correct?
	private boolean isSizeConsistent() { return isSizeConsistent(root); }
	private boolean isSizeConsistent(Node x) {
		if (x == null) return true;
		if (x.N != size(x.left) + size(x.right) + 1) return false;
		return isSizeConsistent(x.left) && isSizeConsistent(x.right);
	}

	// check that ranks are consistent
	private boolean isRankConsistent() {
		for (int i = 0; i < size(); i++)
			if (i != rank(select(i))) return false;
		for (K key : keys())
			if (key.compareTo(select(rank(key))) != 0) return false;
		return true;
	}


	/* ***************************************************************************
	 *  Test client
	 *****************************************************************************/
	public static void main(String[] args) {
		XBSTWithNonStaticNode<String, Integer> st = new XBSTWithNonStaticNode<>();
		for (int i = 0; !StdIn.isEmpty(); i++) {
			String key = StdIn.readString();
			st.put(key, i);
		}
		for (String s : st.keys())
			StdOut.println(s + " " + st.get(s));
	}
}
