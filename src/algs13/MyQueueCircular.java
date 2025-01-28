// Exercise 1.3.29
package algs13;
import stdlib.*;
import java.util.Iterator;

public class MyQueueCircular<T> implements Iterable<T> {
	private int N; // number of elements on queue
	private Node<T> last; // end of queue

	// helper linked list class
	private static class Node<T> {
		public Node() { }
		public T item;
		public Node<T> next;
	}

	public MyQueueCircular () {}

	public boolean isEmpty () {
		return false;
	}

	public int size () {
		return 0;
	}

	public T peek () {
		return null;
	}

	public void enqueue (T item) {}

	public T dequeue () {
		return null;
	}

	public String toString () {
		return null;
	}

	public Iterator<T> iterator () {
		return new QueueIterator ();
	}

	// an iterator, doesn't implement remove() since it's optional
	private class QueueIterator implements Iterator<T> {
		public boolean hasNext () {
			return false;
		}
		public T next () {
			return null;
		}
		public void remove () {}
	}

	/**
	 * A test client.
	 */
	public static void main (String[] args) {
		MyQueueCircular<String> q = new MyQueueCircular<> ();
		while (!StdIn.isEmpty ()) {
			String item = StdIn.readString ();
			if (!item.equals ("-")) q.enqueue (item);
			else if (!q.isEmpty ()) StdOut.print (q.dequeue () + " ");
		}
		StdOut.println ("(" + q.size () + " left on queue)");
	}
}
