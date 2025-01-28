package algs33;
import stdlib.*;
import java.util.Iterator;
import java.util.NoSuchElementException;
import algs13.Stack;
/* ***********************************************************************
 *  Compilation:  javac RandomizedBST.java
 *  Execution:    java RandomizedBST
 *
 *  Symbol table (map) implemented with a randomized BST.
 *
 *
 *************************************************************************/

public class XRandomizedBST<K extends Comparable<? super K>, V> implements Iterable<K> {

	private Node<K,V> root;   // root of the BST

	// BST helper node data type
	private static class Node<K,V> {
		public K key;          // key
		public V val;          // associated data
		public Node<K,V> left, right;   // left and right subtrees
		public int N;              // node count of descendents

		public Node(K key, V val) {
			this.key = key;
			this.val = val;
			this.N   = 1;
		}
	}


	/* ***********************************************************************
	 *  BST search
	 *************************************************************************/

	public boolean contains(K key) {
		return (get(key) != null);
	}

	// return value associated with the given key
	// if no such value, return null
	// if multiple such values, return first one on path from root
	public V get(K key) {
		return get(root, key);
	}

	private V get(Node<K,V> x, K key) {
		if (x == null) return null;
		int cmp = key.compareTo(x.key);
		if      (cmp == 0) return x.val;
		else if (cmp  < 0) return get(x.left,  key);
		else               return get(x.right, key);
	}


	/* ***********************************************************************
	 *  randomized insertion
	 *************************************************************************/
	public void put(K key, V val) {
		root = put(root, key, val);
	}

	// make new node the root with uniform probability
	private Node<K,V> put(Node<K,V> x, K key, V val) {
		if (x == null) return new Node<>(key, val);
		int cmp = key.compareTo(x.key);
		if (cmp == 0) { x.val = val; return x; }
		if (StdRandom.bernoulli(1.0 / (size(x) + 1.0))) return putRoot(x, key, val);
		if (cmp < 0) x.left  = put(x.left,  key, val);
		else         x.right = put(x.right, key, val);
		// (x.N)++;
		fix(x);
		return x;
	}


	private Node<K,V> putRoot(Node<K,V> x, K key, V val) {
		if (x == null) return new Node<>(key, val);
		int cmp = key.compareTo(x.key);
		if      (cmp == 0) { x.val = val; return x; }
		else if (cmp  < 0) { x.left  = putRoot(x.left,  key, val); x = rotR(x); }
		else               { x.right = putRoot(x.right, key, val); x = rotL(x); }
		return x;
	}



	/* ***********************************************************************
	 *  deletion
	 *************************************************************************/
	private Node<K,V> joinLR(Node<K,V> a, Node<K,V> b) {
		if (a == null) return b;
		if (b == null) return a;

		if (StdRandom.bernoulli((double) size(a) / (size(a) + size(b))))  {
			a.right = joinLR(a.right, b);
			fix(a);
			return a;
		}
		else {
			b.left = joinLR(a, b.left);
			fix(b);
			return b;
		}
	}

	private Node<K,V> remove(Node<K,V> x, K key) {
		if (x == null) return null;
		int cmp = key.compareTo(x.key);
		if      (cmp == 0) x = joinLR(x.left, x.right);
		else if (cmp  < 0) x.left  = remove(x.left,  key);
		else               x.right = remove(x.right, key);
		fix(x);
		return x;
	}

	// remove and return value associated with given key; if no such key, return null
	public V remove(K key) {
		V val = get(key);
		root = remove(root, key);
		return val;
	}

	/* ***********************************************************************
	 *  Selection
	 *************************************************************************/

	// return the kth largest key
	public K select(int k) { Node<K,V> x = select(root, k); return x.key; }
	private Node<K,V> select(Node<K,V> x, int k) {
		if (x == null) return null;
		int t = size(x.left);
		if      (t > k) return select(x.left,  k);
		else if (t < k) return select(x.right, k-t-1);
		else            return x;
	}



	// return the smallest key
	public K min() {
		K key = null;
		for (Node<K,V> x = root; x != null; x = x.left)
			key = x.key;
		return key;
	}

	// return the largest key
	public K max() {
		K key = null;
		for (Node<K,V> x = root; x != null; x = x.right)
			key = x.key;
		return key;
	}

	// return the smallest key >= query key; if no such key return null
	public K ceil(K key) {
		Node<K,V> best = ceil(root, key, null);
		if (best == null) return null;
		return best.key;
	}
	private Node<K,V> ceil(Node<K,V> x, K key, Node<K,V> best) {
		if      (x == null)        return best;
		else if (eq(key, x.key))   return x;
		else if (less(key, x.key)) return ceil(x.left,  key, x);
		else                       return ceil(x.right, key, best);
	}

	// return the smallest key >= query key; if no such key return null
	public K ceil2(K key) {
		Node<K,V> best = null;
		Node<K,V> x = root;
		while (x != null) {
			int cmp = key.compareTo(x.key);
			if      (cmp < 0) { best = x; x = x.left; }
			else if (cmp > 0) { x = x.right;          }
			else              return x.key;
		}
		if (best == null) return null;
		return best.key;
	}


	/* *********************************************************************
	 *  Iterate using inorder traversal using a stack.
	 *  Iterating through N elements takes O(N) time.
	 ***********************************************************************/
	public Iterator<K> iterator() { return new BSTIterator(root); }

	// an iterator
	private class BSTIterator implements Iterator<K> {
		private Stack<Node<K,V>> stack = new Stack<>();

		public BSTIterator(Node<K,V> x) {
			while (x != null) {
				stack.push(x);
				x = x.left;
			}
		}

		public boolean hasNext()  { return !stack.isEmpty();                    }

		// it's optional and we don't want to support it
		public void remove()      { throw new UnsupportedOperationException();  }

		public K next() {
			if (!hasNext()) throw new NoSuchElementException();
			Node<K,V> x = stack.pop();
			K key = x.key;
			x = x.right;
			while (x != null) {
				stack.push(x);
				x = x.left;
			}
			return key;
		}
	}




	/* ***********************************************************************
	 *  Utility functions.
	 *************************************************************************/

	// return number of nodes in subtree rooted at x
	public int size() { return size(root); }
	private int size(Node<K,V> x) {
		if (x == null) return 0;
		else           return x.N;
	}

	// height of tree (empty tree height = 0)
	public int height() { return height(root); }
	private int height(Node<K,V> x) {
		if (x == null) return 0;
		return 1 + Math.max(height(x.left), height(x.right));
	}


	/* ***********************************************************************
	 *  helper BST functions
	 *************************************************************************/

	// fix subtree count field
	private void fix(Node<K,V> x) {
		if (x == null) return;
		x.N = 1 + size(x.left) + size(x.right);
	}

	// right rotate
	private Node<K,V> rotR(Node<K,V> h) {
		Node<K,V> x = h.left;
		h.left = x.right;
		x.right = h;
		fix(h);
		fix(x);
		return x;
	}

	// left rotate
	private Node<K,V> rotL(Node<K,V> h) {
		Node<K,V> x = h.right;
		h.right = x.left;
		x.left = h;
		fix(h);
		fix(x);
		return x;
	}


	/* ***********************************************************************
	 *  Debugging functions that test the integrity of the tree
	 *************************************************************************/

	// check integrity of subtree count fields
	public boolean check() { return checkCount() && isBST(); }

	// check integrity of count fields
	private boolean checkCount() { return checkCount(root); }
	private boolean checkCount(Node<K,V> x) {
		if (x == null) return true;
		return checkCount(x.left) && checkCount(x.right) && (x.N == 1 + size(x.left) + size(x.right));
	}


	// does this tree satisfy the BST property?
	private boolean isBST() { return isBST(root, min(), max()); }

	// are all the values in the BST rooted at x between min and max, and recursively?
	private boolean isBST(Node<K,V> x, K min, K max) {
		if (x == null) return true;
		if (less(x.key, min) || less(max, x.key)) return false;
		return isBST(x.left, min, x.key) && isBST(x.right, x.key, max);
	}



	/* ***********************************************************************
	 *  helper comparison functions
	 *************************************************************************/

	private boolean less(K k1, K k2) {
		return k1.compareTo(k2) < 0;
	}

	private boolean eq(K k1, K k2) {
		return k1.compareTo(k2) == 0;
	}



	/* ***********************************************************************
	 *  test client
	 *************************************************************************/
	public static void main(String[] args) {
		XRandomizedBST<String, String> st = new XRandomizedBST<>();

		// insert some key-value pairs
		st.put("www.cs.princeton.edu",   "128.112.136.11");
		st.put("www.cs.princeton.edu",   "128.112.136.35");    // overwrite old value
		st.put("www.princeton.edu",      "128.112.130.211");
		st.put("www.math.princeton.edu", "128.112.18.11");
		st.put("www.yale.edu",           "130.132.51.8");
		st.put("www.amazon.com",         "207.171.163.90");
		st.put("www.simpsons.com",       "209.123.16.34");
		st.put("www.stanford.edu",       "171.67.16.120");
		st.put("www.google.com",         "64.233.161.99");
		st.put("www.ibm.com",            "129.42.16.99");
		st.put("www.apple.com",          "17.254.0.91");
		st.put("www.slashdot.com",       "66.35.250.150");
		st.put("www.whitehouse.gov",     "204.153.49.136");
		st.put("www.espn.com",           "199.181.132.250");
		st.put("www.snopes.com",         "66.165.133.65");
		st.put("www.movies.com",         "199.181.132.250");
		st.put("www.cnn.com",            "64.236.16.20");
		st.put("www.iitb.ac.in",         "202.68.145.210");


		StdOut.println(st.get("www.cs.princeton.edu"));
		StdOut.println(st.get("www.harvardsucks.com"));
		StdOut.println(st.get("www.simpsons.com"));
		StdOut.println();

		StdOut.println("integrity check: " + st.check());
		StdOut.println();

		StdOut.println("ceil(www.simpsonr.com) = " + st.ceil("www.simpsonr.com"));
		StdOut.println("ceil(www.simpsons.com) = " + st.ceil("www.simpsons.com"));
		StdOut.println("ceil(www.simpsont.com) = " + st.ceil("www.simpsont.com"));

		StdOut.println("ceil(www.simpsonr.com) = " + st.ceil2("www.simpsonr.com"));
		StdOut.println("ceil(www.simpsons.com) = " + st.ceil2("www.simpsons.com"));
		StdOut.println("ceil(www.simpsont.com) = " + st.ceil2("www.simpsont.com"));
		StdOut.println();

		for (int i = 0; i < st.size(); i++) {
			StdOut.println(i + "th: key  " + st.select(i));
		}
		StdOut.println();

		StdOut.println("min key: " + st.min());
		StdOut.println("max key: " + st.max());
		StdOut.println("size:    " + st.size());
		StdOut.println("height:  " + st.height());
		StdOut.println();
	}

}
