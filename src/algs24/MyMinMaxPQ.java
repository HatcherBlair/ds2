// Exercise 2.4.29
package algs24;
import stdlib.*;
import java.util.Iterator;
import java.util.NoSuchElementException;

// To turn on assertions for a program in eclipse,
// run the program once, then go to the menubar and select
//   Run > Run Configurations... > Arguments > VM Arguments
// And add
//   -ea
// As a VM argument
public class MyMinMaxPQ<K extends Comparable<? super K>> implements Iterable<K> {
	private int N;    // number of items on priority queue
	private int MAXN; // size of the the arrays
	private K[] a;  // minheap
	private K[] b;  // maxheap

	// the array ab maps from a to b
	// the array ba maps from b to a
	// they are inverses, so i == ba[ab[i]] == ab[ba[i]] for any i
	//
	private int[] ab; // index a to b: a[i] == b[ab[i]]
	private int[] ba; // index b to a: b[i] == a[ba[i]]


	@SuppressWarnings("unchecked")
	/** Create an empty priority queue with the given initial capacity, using the given comparator. */
	public MyMinMaxPQ (int capacity) {
		MAXN = capacity;
		a = (K[]) new Comparable[MAXN + 1];
		b = (K[]) new Comparable[MAXN + 1];
		ab = new int[MAXN + 1];
		ba = new int[MAXN + 1];
		N = 0;
	}

	/** Is the priority queue empty? */
	public boolean isEmpty () { return N == 0; }

	/** Is the priority queue full? */
	public boolean isFull () { return N == MAXN; }

	/** Return the number of items on the priority queue. */
	public int size () { return N; }

	/**
	 * Return the smallest key on the priority queue. Throw an exception if the
	 * priority queue is empty.
	 */
	public K min () {
		if (isEmpty ()) throw new Error ("Priority queue underflow");
		return a[1];
	}

	/** Add a new key to the priority queue. */
	public void insert (K x) {
		if (isFull ()) throw new Error ("Priority queue overflow");
		// add x, and percolate it up to maintain heap invariant
		N++;
		a[N] = x;
		b[N] = x;
		ab[N] = N;
		ba[N] = N;
		aSwim (N);
		bSwim (N);
		assert isMinMaxHeap ();
	}

	/**
	 * Delete and return the smallest key on the priority queue. Throw an
	 * exception if the priority queue is empty.
	 */
	public K delMin () {
		if (N == 0) throw new Error ("Priority queue underflow");
		// TODO
		assert isMinMaxHeap ();
		return null;
	}
	/**
	 * Delete and return the largest key on the priority queue. Throw an
	 * exception if the priority queue is empty.
	 */
	public K delMax () {
		if (N == 0) throw new Error ("Priority queue underflow");
		// TODO
		assert isMinMaxHeap ();
		return null;
	}

	/* *********************************************************************
	 * Helper functions to restore the heap invariant.
	 **********************************************************************/

	private void aSwim (int k) {
		while (k > 1 && aGreater (k / 2, k)) {
			aExch (k, k / 2);
			k = k / 2;
		}
	}
	private void aSink (int k) {
		while (2 * k <= N) {
			int j = 2 * k;
			if (j < N && aGreater (j, j + 1)) j++;
			if (!aGreater (k, j)) break;
			aExch (k, j);
			k = j;
		}
	}
	private void bSwim (int k) {
		while (k > 1 && bLess (k / 2, k)) {
			bExch (k, k / 2);
			k = k / 2;
		}
	}
	private void bSink (int k) {
		while (2 * k <= N) {
			int j = 2 * k;
			if (j < N && bLess (j, j + 1)) j++;
			if (!bLess (k, j)) break;
			bExch (k, j);
			k = j;
		}
	}

	/* *********************************************************************
	 * Helper functions for compares and swaps.
	 **********************************************************************/
	private boolean aGreater (int i, int j) {
		return a[i].compareTo (a[j]) > 0;
	}
	private boolean bLess (int i, int j) {
		return b[i].compareTo (b[j]) < 0;
	}
	private void swap (K[] a, int i, int j) {
		K tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}
	private void swap (int[] a, int i, int j) {
		int tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}
	private void aExch (int ai, int aj) {
		int bi = ab[ai];
		int bj = ab[aj];
		swap (a, ai, aj);
		swap (ab, ai, aj);
		swap (ba, bi, bj);
	}
	private void bExch (int bi, int bj) {
		int ai = ba[bi];
		int aj = ba[bj];
		swap (b, bi, bj);
		swap (ab, ai, aj);
		swap (ba, bi, bj);
	}

	private void showHeap () {
		StdOut.print ("a:  ");
		for (int i = 1; i <= N; i++)
			StdOut.print (a[i] + " ");
		StdOut.println ();
		StdOut.print ("b:  ");
		for (int i = 1; i <= N; i++)
			StdOut.print (b[i] + " ");
		StdOut.println ();
		/*
		 * StdOut.print ("ab: "); for (int i = 1; i <= N; i++) StdOut.print
		 * (ab[i] + " "); StdOut.println (); StdOut.print ("ba: "); for (int i =
		 * 1; i <= N; i++) StdOut.print (ba[i] + " "); StdOut.println ();
		 */
	}
	private void check () {
		for (int i = 1; i <= N; i++) {
			// a and b have the same data, mapped by ab and ba
			assert a[i] == b[ab[i]];
			assert b[i] == a[ba[i]];
			// ab and ba are inverses
			assert i == ab[ba[i]];
			assert i == ba[ab[i]];
		}
	}

	private boolean isMinMaxHeap () {
		check ();
		return isMaxHeap (1) && isMinHeap (1);
	}
	private boolean isMaxHeap (int k) {
		if (k > N) return true;
		int left = 2 * k, right = 2 * k + 1;
		if (left <= N && bLess (k, left))   { StdOut.format ("Not a max heap k=%d left=%d\n",  k, left);  showHeap(); return false; }
		if (right <= N && bLess (k, right)) { StdOut.format ("Not a max heap k=%d right=%d\n", k, right); showHeap(); return false; }
		return isMaxHeap (left) && isMaxHeap (right);
	}
	private boolean isMinHeap (int k) {
		if (k > N) return true;
		int left = 2 * k, right = 2 * k + 1;
		//StdOut.format ("checkmin: %s %s %s\n", a[k], a[left], a[right]);
		if (left <= N && aGreater (k, left))   { StdOut.format ("Not a min heap k=%d left=%d\n",  k, left);  showHeap(); return false; }
		if (right <= N && aGreater (k, right)) { StdOut.format ("Not a min heap k=%d right=%d\n", k, right); showHeap(); return false; }
		return isMinHeap (left) && isMinHeap (right);
	}


	/* *********************************************************************
	 * Iterator
	 **********************************************************************/

	/**
	 * Return an iterator that iterates over all of the keys on the priority
	 * queue in ascending order.
	 * <p>
	 * The iterator doesn't implement {@code remove()} since it's optional.
	 */
	public Iterator<K> iterator () { return new HeapIterator (); }
	private class HeapIterator implements Iterator<K> {
		// create a new pq
		private MyMinMaxPQ<K> copy;

		// add all items to copy of heap
		// takes linear time since already in heap order so no keys move
		public HeapIterator () {
			copy = new MyMinMaxPQ<> (size ());
			for (int i = 1; i <= N; i++)
				copy.insert (a[i]);
		}

		public boolean hasNext () { return !copy.isEmpty (); }
		public void remove () { throw new UnsupportedOperationException (); }
		public K next () {
			if (!hasNext ()) throw new NoSuchElementException ();
			return copy.delMin ();
		}
	}

	/**
	 * A test client.
	 */
	private static int randomValue () { return StdRandom.uniform (100); }
	private static MyMinMaxPQ<Integer> randomPQ (int maxSize) {
		int minCapacity = 5;
		int capacity = StdRandom.uniform(maxSize)+minCapacity;
		MyMinMaxPQ<Integer> pq = new MyMinMaxPQ<> (capacity);
		for (int i=StdRandom.uniform (capacity); i>0; i--)
			pq.insert (randomValue());
		return pq;
	}
	private static void randomOps (MyMinMaxPQ<Integer> pq, int NUMOPS, boolean log) {
		for (int i=NUMOPS; i>0; i--) {
			switch (StdRandom.uniform (4)) {
			case 0:
				if (! pq.isEmpty()) {
					int x = pq.delMin();
					if (log) { StdOut.format ("delMin=%d\n", x); pq.showHeap(); }
				}
				break;
			case 1:
				if (! pq.isEmpty()) {
					int x = pq.delMax();
					if (log) { StdOut.format ("delMax=%d\n", x); pq.showHeap(); }
				}
				break;
			default:
				if (! pq.isFull()) {
					int x = randomValue();
					pq.insert(x);
					if (log) { StdOut.format ("insert=%d\n", x); pq.showHeap(); }
				}
				break;
			}
		}
	}
	private static void randomEmpty (MyMinMaxPQ<Integer> pq, boolean log) {
		while (! pq.isEmpty ())
			switch (StdRandom.uniform (2)) {
			case 0:
				if (! pq.isEmpty()) {
					int x = pq.delMin();
					if (log) { StdOut.format ("delMin=%d\n", x); pq.showHeap(); }
				}
				break;
			case 1:
				if (! pq.isEmpty()) {
					int x = pq.delMax();
					if (log) { StdOut.format ("delMax=%d\n", x); pq.showHeap(); }
				}
				break;
			}
	}
	private static boolean assertionsAreOn () {
		StdOut.println ("ASSERTIONS ARE ON!");
		return true;
	}
	public static void main (String[] args) {
		StdRandom.setSeed (0);
		for (int i=100; i>0; i--) {
			MyMinMaxPQ<Integer> pq = randomPQ (10);
			randomOps (pq, 10, true);
			randomEmpty (pq, true);
		}
		for (int i=100; i>0; i--) {
			MyMinMaxPQ<Integer> pq = randomPQ (1000);
			randomOps (pq, 10000, false);
			randomEmpty (pq, false);
		}
		StdOut.println ("If you don't see a statement below saying that assertions are on, then they are not on.");
		StdOut.println ("That means that you have not really tested anything!");
		StdOut.println ("You must enable assertions!");
		StdOut.println ("To enable assertions, see the instructions at the top of this .java file.");
		assert assertionsAreOn();


		//MyMinMaxPQ<Integer> pq = new MyMinMaxPQ<> (100);
		//StdIn.fromString ("10 20 30 40 50 + - + 05 25 35 - + - 70 80 05 + - - + ");  // This is not a very good test
		//StdIn.fromString ("10 20 40 50 30 + 70 60 - 30 - 50 20 +");                  // This is a good test
		//while (!StdIn.isEmpty ()) {
		//    pq.showHeap ();
		//    String item = StdIn.readString ();
		//    if (item.equals ("-")) StdOut.println ("min: " + pq.delMin ());
		//    else if (item.equals ("+")) StdOut.println ("max: " + pq.delMax ());
		//    else pq.insert (Integer.parseInt (item));
		//}
		//StdOut.println ("(" + pq.size () + " left on pq)");
	}
}
