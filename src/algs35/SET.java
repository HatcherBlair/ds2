package algs35;
import stdlib.*;
import java.util.Iterator;
import java.util.Objects;
import java.util.SortedSet;
import java.util.TreeSet;
/* ***********************************************************************
 *  Compilation:  javac SET.java
 *  Execution:    java SET
 *
 *  Set implementation using Java's TreeSet library.
 *  Does not allow duplicates.
 *
 *  % java SET
 *  128.112.136.11
 *  208.216.181.15
 *  null
 *
 *  Remarks
 *  -------
 *   - The equals() method declares two empty sets to be equal
 *     even if they are parameterized by different generic types.
 *     This is consistent with the way equals() works with Java's
 *     Collections framework.
 *
 *************************************************************************/

/**
 *  The {@code SET} class represents an ordered set. It assumes that
 *  the elements are {@code Comparable}.
 *  It supports the usual <em>add</em>, <em>contains</em>, and <em>delete</em>
 *  methods. It also provides ordered methods for finding the <em>minimum</em>,
 *  <em>maximum</em>, <em>floor</em>, and <em>ceiling</em>.
 *  <p>
 *  This implementation uses a balanced binary search tree.
 *  The <em>add</em>, <em>contains</em>, <em>delete</em>, <em>minimum</em>,
 *  <em>maximum</em>, <em>ceiling</em>, and <em>floor</em> methods take
 *  logarithmic time.
 *  <p>
 *  For additional documentation, see <a href="/algs4/45applications">Section 4.5</a> of
 *  <i>Algorithms in Java, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 */

public class SET<K extends Comparable<? super K>> implements Iterable<K> {
	private final TreeSet<K> set;

	/**
	 * Create an empty set.
	 */
	public SET() {
		set = new TreeSet<>();
	}

	/**
	 * Is this set empty?
	 */
	public boolean isEmpty() { return set.isEmpty(); }

	/**
	 * Add the key to this set.
	 */
	public void add(K key) { set.add(key); }

	/**
	 * Does this set contain the given key?
	 */
	public boolean contains(K key) { return set.contains(key); }

	/**
	 * Delete the given key from this set.
	 */
	public void delete(K key) { set.remove(key); }

	/**
	 * Return the number of keys in this set.
	 */
	public int size() { return set.size(); }

	/**
	 * Return an Iterator for this set.
	 */
	public Iterator<K> iterator() { return set.iterator(); }

	/**
	 * Return the key in this set with the maximum value.
	 */
	public K max() { return set.last(); }

	/**
	 * Return the key in this set with the minimum value.
	 */
	public K min() { return set.first(); }

	/**
	 * Return the smallest key in this set {@code >= k}.
	 */
	public K ceil(K k) {
		SortedSet<K> tail = set.tailSet(k);
		if (tail.isEmpty()) return null;
		else return tail.first();
	}

	/**
	 * Return the largest key in this set {@code <= k}.
	 */
	public K floor(K k) {
		if (set.contains(k)) return k;

		// does not include key if present (!)
		SortedSet<K> head = set.headSet(k);
		if (head.isEmpty()) return null;
		else return head.last();
	}

	/**
	 * Return the union of this set with that set.
	 */
	public SET<K> union(SET<K> that) {
		SET<K> c = new SET<>();
		for (K x : this) { c.add(x); }
		for (K x : that) { c.add(x); }
		return c;
	}

	/**
	 * Return the intersection of this set with that set.
	 */
	public SET<K> intersects(SET<K> that) {
		SET<K> c = new SET<>();
		if (this.size() < that.size()) {
			for (K x : this) {
				if (that.contains(x)) c.add(x);
			}
		}
		else {
			for (K x : that) {
				if (this.contains(x)) c.add(x);
			}
		}
		return c;
	}

	/**
	 * Does this SET equal that set.
	 */
	@SuppressWarnings("unchecked")
	public boolean equals(Object y) {
		if (y == this) return true;
		if (y == null) return false;
		if (y.getClass() != this.getClass()) return false;
		SET<K> that = (SET<K>) y;
		if (this.size() != that.size()) return false;
		try {
			for (K k : this)
				if (!that.contains(k)) return false;
		}
		catch (ClassCastException exception) {
			return false;
		}
		return true;
	}

	// must override hashcode if you override equals
	// See Item 9 of Effective Java (2e) by Joshua Block
	// This code based on java.util.AbstractMap.java
	public int hashCode() {
		int h = 0;
		for (K k : this)
			h = 31*h + Objects.hashCode(k);
		return h;
	}

	/**
	 * String represenation of this set.
	 */
	public String toString() {
		StringBuilder s = new StringBuilder();
		for (K key : this)
			s.append(key + " ");
		return s.toString();
	}

	/* *********************************************************************
	 * Test routine.
	 **********************************************************************/
	public static void main(String[] args) {
		SET<String> set = new SET<>();


		// insert some keys
		set.add("www.cs.princeton.edu");
		set.add("www.cs.princeton.edu");    // overwrite old value
		set.add("www.princeton.edu");
		set.add("www.math.princeton.edu");
		set.add("www.yale.edu");
		set.add("www.amazon.com");
		set.add("www.simpsons.com");
		set.add("www.stanford.edu");
		set.add("www.google.com");
		set.add("www.ibm.com");
		set.add("www.apple.com");
		set.add("www.slashdot.com");
		set.add("www.whitehouse.gov");
		set.add("www.espn.com");
		set.add("www.snopes.com");
		set.add("www.movies.com");
		set.add("www.cnn.com");
		set.add("www.iitb.ac.in");


		StdOut.println(set.contains("www.cs.princeton.edu"));
		StdOut.println(!set.contains("www.harvardsucks.com"));
		StdOut.println(set.contains("www.simpsons.com"));
		StdOut.println();

		StdOut.println("ceil(www.simpsonr.com) = " + set.ceil("www.simpsonr.com"));
		StdOut.println("ceil(www.simpsons.com) = " + set.ceil("www.simpsons.com"));
		StdOut.println("ceil(www.simpsont.com) = " + set.ceil("www.simpsont.com"));
		StdOut.println("floor(www.simpsonr.com) = " + set.floor("www.simpsonr.com"));
		StdOut.println("floor(www.simpsons.com) = " + set.floor("www.simpsons.com"));
		StdOut.println("floor(www.simpsont.com) = " + set.floor("www.simpsont.com"));
		StdOut.println();


		// print out all keys in the set in lexicographic order
		for (String s : set) {
			StdOut.println(s);
		}

	}

}
