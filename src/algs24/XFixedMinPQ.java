package algs24;
import stdlib.*;
import java.util.Iterator;
import java.util.NoSuchElementException;
/* ***********************************************************************
 *  Compilation:  javac MinPQ.java
 *  Execution:    java MinPQ < input.txt
 *
 *  Generic min priority queue implementation with a binary heap.
 *
 *  % java MinPQ < tinyPQ.txt
 *  E A E (6 left on pq)
 *
 *  We use a one-based array to simplify parent and child calculations.
 *
 *************************************************************************/
/* Modified by jriely@cs.depaul.edu */
public class XFixedMinPQ<K extends Comparable<? super K>> implements Iterable<K> {
	private final K[] pq;                    // store items at indices 1 to N
	private int N;                       // number of items on priority queue
	private final int MAXN;

	/** Create an empty priority queue with the given initial capacity, using the given comparator. */
	@SuppressWarnings("unchecked")
	public XFixedMinPQ(int initCapacity) {
		MAXN = initCapacity;
		pq = (K[]) new Comparable[initCapacity + 1];
		N = 0;
	}

	/** Is the priority queue empty? */
	public boolean isEmpty() { return N == 0; }

	/** Is the priority queue full? */
	public boolean isFull()  { return N == MAXN; }

	/** Return the number of items on the priority queue. */
	public int size() { return N; }

	/**
	 * Return the smallest key on the priority queue.
	 * Throw an exception if the priority queue is empty.
	 */
	public K min() {
		if (isEmpty()) throw new Error("Priority queue underflow");
		return pq[1];
	}

	/** Add a new key to the priority queue. */
	public void insert(K x) {
		if (isFull()) throw new Error("Priority queue overflow");

		// add x, and percolate it up to maintain heap invariant
		pq[++N] = x;
		swim(N);
		//assert isMinHeap();
	}

	/**
	 * Delete and return the smallest key on the priority queue.
	 * Throw an exception if the priority queue is empty.
	 */
	public K delMin() {
		if (N == 0) throw new Error("Priority queue underflow");
		exch(1, N);
		K min = pq[N--];
		sink(1);
		pq[N+1] = null; // avoid loitering and help with garbage collection
		//assert isMinHeap();
		return min;
	}


	/* *********************************************************************
	 * Helper functions to restore the heap invariant.
	 **********************************************************************/

	private void swim(int k) {
		while (k > 1 && greater(k/2, k)) {
			exch(k, k/2);
			k = k/2;
		}
	}

	private void sink(int k) {
		while (2*k <= N) {
			int j = 2*k;
			if (j < N && greater(j, j+1)) j++;
			if (!greater(k, j)) break;
			exch(k, j);
			k = j;
		}
	}

	/* *********************************************************************
	 * Helper functions for compares and swaps.
	 **********************************************************************/
	private boolean greater(int i, int j) {
		return pq[i].compareTo(pq[j]) > 0;
	}

	private void exch(int i, int j) {
		K swap = pq[i];
		pq[i] = pq[j];
		pq[j] = swap;
	}

	// is pq[1..N] a min heap?
	private boolean isMinHeap() {
		return isMinHeap(1);
	}

	// is subtree of pq[1..N] rooted at k a min heap?
	private boolean isMinHeap(int k) {
		if (k > N) return true;
		int left = 2*k, right = 2*k + 1;
		if (left  <= N && greater(k, left))  return false;
		if (right <= N && greater(k, right)) return false;
		return isMinHeap(left) && isMinHeap(right);
	}


	/* *********************************************************************
	 * Iterator
	 **********************************************************************/

	/**
	 * Return an iterator that iterates over all of the keys on the priority queue
	 * in ascending order.
	 * <p>
	 * The iterator doesn't implement {@code remove()} since it's optional.
	 */
	public Iterator<K> iterator() { return new HeapIterator(); }

	private class HeapIterator implements Iterator<K> {
		// create a new pq
		private final XFixedMinPQ<K> copy;

		// add all items to copy of heap
		// takes linear time since already in heap order so no keys move
		public HeapIterator() {
			copy = new XFixedMinPQ<>(size());
			for (int i = 1; i <= N; i++)
				copy.insert(pq[i]);
		}

		public boolean hasNext()  { return !copy.isEmpty();                     }
		public void remove()      { throw new UnsupportedOperationException();  }

		public K next() {
			if (!hasNext()) throw new NoSuchElementException();
			return copy.delMin();
		}
	}

	private void showHeap() {
		for (int i = 1; i <= N; i++)
			StdOut.print (pq[i] + " ");
		StdOut.println ();
	}

	/**
	 * A test client.
	 */
	public static void main(String[] args) {
		XFixedMinPQ<String> pq = new XFixedMinPQ<>(100);
		StdIn.fromString ("10 20 30 40 50 - - - 05 25 35 - - - 70 80 05 - - - - ");
		while (!StdIn.isEmpty()) {
			StdOut.print ("pq:  "); pq.showHeap();
			String item = StdIn.readString();
			if (item.equals("-")) StdOut.println("min: " + pq.delMin());
			else pq.insert(item);
		}
		StdOut.println("(" + pq.size() + " left on pq)");
	}
}
