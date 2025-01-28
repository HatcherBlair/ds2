// Exercise 1.3.14 (Solution published at http://algs4.cs.princeton.edu/)
package algs13;
import stdlib.*;
import java.util.Iterator;
import java.util.NoSuchElementException;
/* ***********************************************************************
 *  Compilation:  javac ResizingArrayQueue.java
 *  Execution:    java ResizingArrayQueue < input.txt
 *  Data files:   http://algs4.cs.princeton.edu/13stacks/tobe.txt
 *
 *  Queue implementation with a resizing array.
 *
 *  % java ResizingArrayQueue < tobe.txt
 *  to be or not to be (2 left on queue)
 *
 *************************************************************************/

public class ResizingArrayQueue<T> implements Iterable<T> {
	private T[] q;           // queue elements
	private int N;           // number of elements on queue
	private int first;       // index of first element of queue
	private int last;        // index of next available slot

	// cast needed since no generic array creation in Java
	@SuppressWarnings("unchecked")
	public ResizingArrayQueue() {
		this.q = (T[]) new Object[2];
		this.N = 0;
		this.first = 0;
		this.last = 0;
	}

	public boolean isEmpty() { return N == 0;    }
	public int size()        { return N;         }

	@SuppressWarnings("unchecked")
	private void resize(int capacity) {
		if (capacity <= N) throw new IllegalArgumentException ();
		T[] temp = (T[]) new Object[capacity];
		for (int i = 0; i < N; i++) temp[i] = q[(first + i) % q.length];
		q = temp;
		first = 0;
		last  = N;
	}

	public void enqueue(T item) {
		if (N == q.length) resize(2*q.length);   // double size of array if necessary
		q[last] = item;                          // add item
		last = (last + 1) % q.length;
		N++;
	}

	// remove the least recently added item
	public T dequeue() {
		if (isEmpty()) throw new Error("Queue underflow");
		T item = q[first];
		q[first] = null;                         // to avoid loitering
		N--;
		first = (first + 1) % q.length;
		if (N > 0 && N == q.length/4) resize(q.length/2); // shrink size of array if necessary
		return item;
	}

	public Iterator<T> iterator() { return new ArrayIterator(); }
	private class ArrayIterator implements Iterator<T> {
		private int i = 0;
		public boolean hasNext()  { return i < N;                               }
		public void remove()      { throw new UnsupportedOperationException();  }

		public T next() {
			if (!hasNext()) throw new NoSuchElementException();
			T item = q[(first + i) % q.length];
			i++;
			return item;
		}
	}

	public String toString () {
		if (isEmpty()) return "[]";
		StringBuilder sb = new StringBuilder ("[");
		Iterator<T> i = iterator();
		sb.append (i.next ());
		while (i.hasNext ()) {
			sb.append (" ");
			sb.append (i.next ());
		}
		sb.append ("]");
		return sb.toString ();
	}
	private void check (String expected) {
		if (N<0 || N>q.length) throw new Error ();
		if (N==0 && q.length!=2) throw new Error ("Expected length 2, got " + q.length);
		if (N!=0 && N<q.length/4) throw new Error ();
		if (((first + N) % q.length) != last) throw new Error ();
		for (int i=0; i<N; i++) {
			if (q[(first + i) % q.length] == null) throw new Error ();
		}
		for (int i=N; i<q.length; i++) {
			if (q[(first + i) % q.length] != null) throw new Error ();
		}
		if (expected != null) {
			if (!expected.equals(this.toString ())) throw new Error ("Expected \"" + expected + "\", got \"" + this + "\"");
		}
		StdOut.println (this);
	}
	private void check (T iExpected, T iActual, String expected) {
		if (!iExpected.equals (iActual)) throw new Error ("Expected \"" + iExpected + "\", got \"" + iActual + "\"");
		check (expected);
	}
	public static void mainx (String args[]) {
		ResizingArrayQueue<Integer> d1;
		Integer k;
		d1 = new ResizingArrayQueue<> ();
		d1.enqueue (11); d1.check ("[11]");
		d1.enqueue (12); d1.check ("[11 12]");
		k = d1.dequeue(); d1.check (11, k, "[12]");
		k = d1.dequeue(); d1.check (12, k, "[]");

		d1 = new ResizingArrayQueue<> ();
		for (int i = 10; i < 20; i++)
			d1.enqueue (i);
		d1.check ("[10 11 12 13 14 15 16 17 18 19]");

		for (int i = 0; i < 10; i++) {
			d1.dequeue (); d1.check (null);
		}
		try { d1.dequeue (); throw new Error ("Expected exception"); } catch (Error e) {}
	}

	// A test client
	public static void main (String[] args) {
		StdIn.fromString ("to be or not to - be - - that - - - is");

		ResizingArrayQueue<String> q = new ResizingArrayQueue<>();
		while (!StdIn.isEmpty()) {
			String item = StdIn.readString();
			if (!item.equals("-")) q.enqueue(item);
			else StdOut.print(q.dequeue() + " ");
		}
		StdOut.println("(" + q.size() + " left on queue)");
	}
}
