package algs13;
import stdlib.*;

public class XResizingArrayQueueofStrings {
	private String[] q;      // queue elements
	private int N;           // number of elements on queue
	private int first;       // index of first element of queue
	private int last;        // index of next available slot
	// Invariant (after the constructor):
	//   a[(first + 0) % q.length]..a[(first + N-1) % q.length] are non null
	//   a[(first + N) % q.length]..a[(first + a.length-1) % q.length] are null
	public XResizingArrayQueueofStrings() {
		this.q = new String[2];
		this.N = 0;
		this.first = 0;
		this.last = 0;
	}

	public boolean isEmpty() { return N == 0;    }
	public int size()        { return N;         }

	private void resize(int capacity) {
		if (capacity <= N) throw new IllegalArgumentException ();
		String[] temp = new String[capacity];
		for (int i = 0; i < N; i++) { 
			temp[i] = q[(first + i) % q.length];
		}
		q = temp;
		first = 0;
		last  = N;
	}

	public void enqueue(String item) {
		if (N == q.length) resize(2*q.length);  // double size of array if necessary
		q[last] = item;
		last = (last + 1) % q.length;
		N++;
	}

	// remove the least recently added item
	public String dequeue() {
		if (isEmpty()) throw new Error("Queue underflow");
		String item = q[first];
		q[first] = null;                         // to avoid loitering
		N--;
		first = (first + 1) % q.length;
		if (N > 0 && N == q.length/4) resize(q.length/2); // shrink size of array if necessary
		return item;
	}

	public String toString () {
		if (isEmpty()) return "[]";
		StringBuilder sb = new StringBuilder ("[");
		sb.append (q[first % q.length]);
		int i = 1;
		while (i < N) {
			sb.append (" ");
			sb.append (q[(first + i) % q.length]);
			i++;
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
	private void check (String iExpected, String iActual, String expected) {
		if (!iExpected.equals (iActual)) throw new Error ("Expected \"" + iExpected + "\", got \"" + iActual + "\"");
		check (expected);
	}
	public static void mainX (String args[]) {
		String k;
		var d1 = new XResizingArrayQueueofStrings ();
		d1.enqueue ("11"); d1.check ("[11]");
		d1.enqueue ("12"); d1.check ("[11 12]");
		k = d1.dequeue(); d1.check ("11", k, "[12]");
		k = d1.dequeue(); d1.check ("12", k, "[]");

		d1 = new XResizingArrayQueueofStrings ();
		for (int i = 10; i < 20; i++)
			d1.enqueue (Integer.toString(i));
		d1.check ("[10 11 12 13 14 15 16 17 18 19]");

		for (int i = 10; i < 20; i++) {
			d1.dequeue (); d1.check (null);
		}
		try { d1.dequeue (); throw new Error ("Expected exception"); } catch (Error e) {}
	}

	// A test client
	public static void main (String[] args) {
		StdIn.fromString ("to be or not to - be - - that - - - is");

		var q = new XResizingArrayQueueofStrings();
		while (!StdIn.isEmpty()) {
			String item = StdIn.readString();
			if (!item.equals("-")) q.enqueue(item);
			else StdOut.print(q.dequeue() + " ");
		}
		StdOut.println("(" + q.size() + " left on queue)");
	}
}
