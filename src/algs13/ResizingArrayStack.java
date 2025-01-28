package algs13;
import stdlib.*;
import java.util.Iterator;
import java.util.NoSuchElementException;
/* ***********************************************************************
 *  Compilation:  javac ResizingArrayStack.java
 *  Execution:    java ResizingArrayStack < input.txt
 *  Data files:   http://algs4.cs.princeton.edu/13stacks/tobe.txt
 *
 *  Stack implementation with a resizing array.
 *
 *  % more tobe.txt
 *  to be or not to - be - - that - - - is
 *
 *  % java ResizingArrayStack < tobe.txt
 *  to be not that or be (2 left on stack)
 *
 *************************************************************************/
public class ResizingArrayStack<T> implements Iterable<T> {
	private T[] a;        // array of items
	private int N;        // number of elements on stack

	// create an empty stack
	@SuppressWarnings("unchecked")
	public ResizingArrayStack() {
		this.a = (T[]) new Object[2];
		this.N = 0;
	}

	public boolean isEmpty() { return N == 0; }
	public int size()        { return N;      }


	// resize the underlying array holding the elements
	@SuppressWarnings("unchecked")
	private void resize(int capacity) {
		if (capacity <= N) throw new IllegalArgumentException ();
		T[] temp = (T[]) new Object[capacity];
		for (int i = 0; i < N; i++)
			temp[i] = a[i];
		a = temp;
	}

	// push a new item onto the stack
	public void push(T item) {
		if (N == a.length) resize(2*N); // increase array size if necessary
		//if (N == a.length) resize((int)Math.ceil (N*1.5));
		a[N] = item;
		N++;
	}

	// delete and return the item most recently added
	public T pop() {
		if (isEmpty()) { throw new Error("Stack underflow error"); }
		N--;
		T item = a[N];
		a[N] = null; // to avoid loitering
		if (N > 0 && N == a.length/4) resize(a.length/2); // shrink size of array if necessary
		return item;
	}

	/**
	 * Return string representation.
	 */
	public String toString() {
		StringBuilder s = new StringBuilder();
		for (T item : this)
			s.append(item + " ");
		return s.toString();
	}
	
	public Iterator<T> iterator()  { return new ReverseArrayIterator();  }

	// an iterator, doesn't implement remove() since it's optional
	private class ReverseArrayIterator implements Iterator<T> {
		private int i = N;
		public boolean hasNext()  { return i > 0;                               }
		public void remove()      { throw new UnsupportedOperationException();  }

		public T next() {
			if (!hasNext()) throw new NoSuchElementException();
			return a[--i];
		}
	}

	/* *********************************************************************
	 * Test routine.
	 **********************************************************************/
//	public static void bookMain(String[] args) {
//		StdIn.fromString ("to be or not to - be - - that - - - is");
//
//		ResizingArrayStack<String> s = new ResizingArrayStack<>();
//		while (!StdIn.isEmpty()) {
//			String item = StdIn.readString();
//			if (!item.equals("-")) s.push(item);
//			else if (!s.isEmpty()) StdOut.print(s.pop() + " ");
//		}
//		StdOut.println("(" + s.size() + " left on stack)");
//	}
//
	/* *********************************************************************
	 * Test routine.
	 **********************************************************************/
	public static void main(String[] args) {
		double prevTime = 1;
		for (int i = 0, size = 20; i<19; i += 1, size *= 2) {
			Stopwatch s = new Stopwatch ();

			for (int k = 0; k < 1; k++) {
				ResizingArrayStack<Double> stack = new ResizingArrayStack<> ();
				for (int j = 0; j < size; j++) {
					stack.push (1.2);
				}
			}

			double thisTime = s.elapsedTime ();
			StdOut.format ("size=%d thisTime=%f ratio=%f\n", size, thisTime, thisTime/prevTime);
			prevTime = thisTime;
		}
	}
	public static void main2 (String[] args) {
		//Trace.showObjectIdsRedundantly (true);
		Trace.showBuiltInObjects (true);
		//Trace.showBuiltInObjectsVerbose (true);
		Trace.drawStepsOfMethod ("main");
		Trace.drawStepsOfMethod ("resize");
		Trace.run ();
		
		ResizingArrayStack<Integer> s1 = new ResizingArrayStack<> ();
		ResizingArrayStack<String> s2 = new ResizingArrayStack<> ();
		s1.push (11);
		s1.push (21);
		s1.push (31);

		//s2.push (41);
		s2.push ("duck");
		s2.push ("goose");
	}
}
