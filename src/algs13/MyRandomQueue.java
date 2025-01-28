// Exercise 4.3.36
package algs13;
import stdlib.*;
import java.util.Iterator;
public class MyRandomQueue<T> implements Iterable<T> {

	/** Create an empty random queue. */
	public MyRandomQueue () {}

	/** Is it empty? */
	public boolean isEmpty () {
		return true;
	}

	/** Return the number of elements. */
	public int size () {
		return 0;
	}

	/** Add an item. */
	public void enqueue (T item) {}

	/** Return (but do not remove) a random item. */
	public T sample () {
		return null;
	}

	/** Remove and return a random item. */
	public T dequeue () {
		return null;
	}

	/** Return an iterator over the items in random order. */
	public Iterator<T> iterator () {
		return new QueueIterator ();
	}

	// an iterator, doesn't implement remove() since it's optional
	private class QueueIterator implements Iterator<T> {
		public boolean hasNext () { return false; }
		public T next () { return null; }
		public void remove () {}
	}

	// Test code from https://blog.itu.dk/BADS-F2011/2011/02/10/programming-exercise-3-randomqueue/
	public static void main (String args[]) {
		int NUMROLLS = 10000;
		// Build a queue containing the Integers 1,2,...,6:
		MyRandomQueue<Integer> q1 = new MyRandomQueue<> ();
		for (int i = 1; i < 7; ++i)
			q1.enqueue (i); // autoboxing! cool!

		// Print 30 die rolls to standard output
		StdOut.print ("Some die rolls: ");
		for (int i = 1; i < 30; ++i)
			StdOut.print (q1.sample () + " ");
		StdOut.println ();

		// Let's be more serious: do they really behave like die rolls?
		int[] rolls = new int[NUMROLLS];
		for (int i = 0; i < NUMROLLS; ++i)
			rolls[i] = q1.sample (); // autounboxing! Also cool!
		StdOut.format ("Mean (should be around 3.5): %5.4f\n", StdStats.mean (rolls));
		StdOut.format ("Standard deviation (should be around 1.7): %5.4f\n", StdStats.stddev (rolls));

		// Let's look at the iterator. First, we make a queue of colours:
		MyRandomQueue<String> q2 = new MyRandomQueue<> ();
		q2.enqueue ("red");
		q2.enqueue ("blue");
		q2.enqueue ("green");
		q2.enqueue ("yellow"); //q2.print ();
		q2.dequeue (); //q2.print ();
		q2.dequeue (); //q2.print ();
		q2.enqueue ("purple"); //q2.print ();
		q2.enqueue ("orange"); //q2.print ();

		Iterator<String> it1 = q2.iterator ();
		Iterator<String> it2 = q2.iterator ();

		StdOut.print ("Two colours from first shuffle: ");
		StdOut.print (it1.next () + " ");
		StdOut.print (it1.next () + " ");

		StdOut.print ("\nEntire second shuffle: ");
		while (it2.hasNext ())
			StdOut.print (it2.next () + " ");

		StdOut.print ("\nRemaining two colours from first shuffle: ");
		StdOut.print (it1.next () + " ");
		StdOut.println (it1.next ());

		Stopwatch s = new Stopwatch ();
		int[][] irolls = new int[6][NUMROLLS];
		for (int i = 0; i < NUMROLLS; ++i) {
			Iterator<Integer> it3 = q1.iterator ();
			for (int j = 0; j < 6; j++)
				irolls[j][i] = it3.next ();
		}
		StdOut.format ("Elapsed time: %5.4f\n", s.elapsedTime ());
		for (int j = 0; j < 6; j++) {
			StdOut.format ("Mean (should be around 3.5): %5.4f\n", StdStats.mean (irolls[j]));
			StdOut.format ("Standard deviation (should be around 1.7): %5.4f\n", StdStats.stddev (irolls[j]));
		}
	}

}
