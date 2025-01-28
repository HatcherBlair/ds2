package algs35;
import stdlib.*;
import java.util.HashMap;
import java.util.TreeSet;
/* ***********************************************************************
 *  Compilation:  javac IndirectPQ.java
 *  Execution:    java IndirectPQ
 *
 *  Indirect priority queue implementation with Java's TreeSet and
 *  HashMap. It assumes the priorities are integers and the values
 *  are strings, although it is easily modifiable for Comparable
 *  priorities and Object values.
 *
 *  % java IndirectPQ
 *  this
 *  is
 *  a
 *  test
 *
 *  Remarks
 *  -------
 *  All operations are efficient, but it could be improved by using
 *  a binary heap instead of the red-black tree.
 *
 *************************************************************************/

public class XIndirectPQ {
	private final TreeSet<Element> pq = new TreeSet<>();
	private final HashMap<String,Element> st = new HashMap<>();

	private static class Element implements Comparable<Element> {
		public final String key;
		public final int priority;

		public Element(String key, int priority) {
			this.key       = key;
			this.priority  = priority;
		}
		public int compareTo(Element object) {
			Element e = object;
			if (priority != e.priority) return priority - e.priority;
			return key.compareTo(e.key);
		}
		public boolean equals(Object e) {
			if (e == null) return false;
			Element that = (Element) e;
			return (priority == that.priority && key.equals(that.key));
		}
	}


	public boolean isEmpty() { return pq.isEmpty(); }
	public int size()        { return pq.size();    }

	// insert a key with a given priority (changing the priority if the key is present)
	public void put(String key, int priority) {
		delete(key);
		Element e = new Element(key, priority);
		st.put(key, e);
		pq.add(e);
	}

	// does the key exist?
	public boolean exists(String key) {
		return (st.get(key) != null);
	}

	// delete key
	void delete(String key) {
		Element e = st.get(key);
		if (e != null) {
			pq.remove(e);
			st.remove(key);
		}
	}

	// return the priority of a given key
	int get(String key) {
		Element e = st.get(key);
		return e.priority;
	}

	// return minimum priority, error if empty
	public int min() {
		Element min = pq.first();
		return min.priority;
	}

	// return minimum priority, error if empty
	public int max() {
		Element max = pq.last();
		return max.priority;
	}

	// delete and return the minimum value, error if empty
	public String delMin() {
		Element min = pq.first();
		pq.remove(min);
		st.remove(min.key);
		return min.key;
	}

	// delete and return the maximum value, error if empty
	public String delMax() {
		Element max = pq.last();
		pq.remove(max);
		st.remove(max.key);
		return max.key;
	}


	// test client
	public static void main(String[] args) {
		XIndirectPQ pq = new XIndirectPQ();
		pq.put("test", 31);
		pq.put("is",   55);
		pq.put("this", 25);
		pq.put("not",  65);
		pq.put("a",    36);
		pq.put("this", 61);      // changes the key
		pq.delete("not");

		while (!pq.isEmpty())
			StdOut.println(pq.delMax());
	}

}
