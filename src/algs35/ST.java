package algs35;
import stdlib.*;
import java.util.Iterator;
import java.util.SortedMap;
import java.util.TreeMap;
/* ***********************************************************************
 *  Compilation:  javac ST.java
 *  Execution:    java ST
 *
 *  Sorted symbol table implementation using a java.util.TreeMap.
 *  Does not allow duplicates.
 *
 *  % java ST
 *
 *************************************************************************/

/**
 *  This class represents an ordered symbol table. It assumes that
 *  the keys are {@code Comparable}.
 *  It supports the usual <em>put</em>, <em>get</em>, <em>contains</em>,
 *  and <em>remove</em> methods.
 *  It also provides ordered methods for finding the <em>minimum</em>,
 *  <em>maximum</em>, <em>floor</em>, and <em>ceiling</em>.
 *  <p>
 *  The class implements the <em>associative array</em> abstraction: when associating
 *  a value with a key that is already in the table, the convention is to replace
 *  the old value with the new value.
 *  The class also uses the convention that values cannot be null. Setting the
 *  value associated with a key to null is equivalent to removing the key.
 *  <p>
 *  This class implements the Iterable interface for compatiblity with
 *  the version from <em>Introduction to Programming in Java: An Interdisciplinary
 *  Approach</em>.
 *  <p>
 *  This implementation uses a balanced binary search tree.
 *  The <em>put</em>, <em>contains</em>, <em>remove</em>, <em>minimum</em>,
 *  <em>maximum</em>, <em>ceiling</em>, and <em>floor</em> methods take
 *  logarithmic time.
 *  <p>
 *  For additional documentation, see <a href="http://algs4.cs.princeton.edu/35applications">Section 4.5</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 */
public class ST<K extends Comparable<? super K>, V> implements Iterable<K> {

	private final TreeMap<K, V> st;

	/**
	 * Create an empty symbol table.
	 */
	public ST() {
		st = new TreeMap<>();
	}

	/**
	 * Put key-value pair into the symbol table. Remove key from table if
	 * value is null.
	 */
	public void put(K key, V val) {
		if (val == null) st.remove(key);
		else             st.put(key, val);
	}

	/**
	 * Return the value paired with given key; null if key is not in table.
	 */
	public V get(K key) {
		return st.get(key);
	}

	/**
	 * Delete the key (and paired value) from table.
	 * Return the value paired with given key; null if key is not in table.
	 */
	public V delete(K key) {
		return st.remove(key);
	}

	/**
	 * Is the key in the table?
	 */
	public boolean contains(K key) {
		return st.containsKey(key);
	}

	/**
	 * How many keys are in the table?
	 */
	public int size() {
		return st.size();
	}

	/**
	 * Return an {@code Iterable} for the keys in the table.
	 * To iterate over all of the keys in the symbol table {@code st}, use the
	 * foreach notation: {@code for (K key : st.keys())}.
	 */
	public Iterable<K> keys() {
		return st.keySet();
	}

	/**
	 * Return an {@code Iterator} for the keys in the table.
	 * To iterate over all of the keys in the symbol table {@code st}, use the
	 * foreach notation: {@code for (K key : st)}.
	 * This method is for backward compatibility with the version from <em>Introduction
	 * to Programming in Java: An Interdisciplinary Approach.</em>
	 */
	public Iterator<K> iterator() {
		return st.keySet().iterator();
	}

	/**
	 * Return the smallest key in the table.
	 */
	public K min() {
		return st.firstKey();
	}

	/**
	 * Return the largest key in the table.
	 */
	public K max() {
		return st.lastKey();
	}


	/**
	 * Return the smallest key in the table {@code >= k}.
	 */
	public K ceil(K k) {
		SortedMap<K, V> tail = st.tailMap(k);
		if (tail.isEmpty()) return null;
		else return tail.firstKey();
	}

	/**
	 * Return the largest key in the table {@code <= k}.
	 */
	public K floor(K k) {
		if (st.containsKey(k)) return k;

		// does not include key if present (!)
		SortedMap<K, V> head = st.headMap(k);
		if (head.isEmpty()) return null;
		else return head.lastKey();
	}

	/* *********************************************************************
	 * Test routine.
	 **********************************************************************/
	public static void main(String[] args) {
		ST<String, Integer> st = new ST<>();
		for (int i = 0; !StdIn.isEmpty(); i++) {
			String key = StdIn.readString();
			st.put(key, i);
		}
		for (String s : st.keys())
			StdOut.println(s + " " + st.get(s));
	}

}
