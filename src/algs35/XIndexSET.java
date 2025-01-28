package algs35;
import stdlib.*;
/* ***********************************************************************
 *  Compilation:  javac IndexSET.java
 *  Execution:    java IndexSET
 *  Dependencies: ST.java
 *
 *  Indexed set. Assigns an integer index to each new element.
 *
 *  Remarks
 *  -------
 *   - could use generic array for ts
 *
 *  % java IndexSET
 *
 *************************************************************************/

public class XIndexSET<K extends Comparable<? super K>> {
	private int N;
	private final ST<K, Integer> st = new ST<>();
	private final ST<Integer, K> ts = new ST<>();

	public void add(K key) {
		if (!st.contains(key)) {
			st.put(key, N);
			ts.put(N, key);
			N++;
		}
	}

	public int indexOf(K key) {
		return st.get(key);
	}

	public K keyOf(int index) {
		return ts.get(index);
	}

	public boolean contains(K key) { return st.contains(key);  }
	public int size()              { return st.size();         }
	public Iterable<K> keys()      { return st.keys();         }


	/* *********************************************************************
	 * Test routine.
	 **********************************************************************/
	public static void main(String[] args) {
		XIndexSET<String> set = new XIndexSET<>();

		// insert some keys
		set.add("www.cs.princeton.edu");
		set.add("www.princeton.edu");
		set.add("www.yale.edu");
		set.add("www.amazon.com");
		set.add("www.simpsons.com");

		// does given key exist?
		StdOut.println(set.contains("www.cs.princeton.edu"));
		StdOut.println(set.contains("www.amazon.com"));
		StdOut.println(!set.contains("www.amazon.edu"));
		StdOut.println();

		// print out all keys in the set
		for (String s : set.keys()) {
			StdOut.println(s + " : " + set.indexOf(s));
		}

	}

}

