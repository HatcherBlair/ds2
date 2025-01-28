package algs13;
import stdlib.*;
import java.util.Iterator;
import java.util.NoSuchElementException;
/* ***********************************************************************
 *  Compilation:  javac ResizingSlowStack.java
 *  Execution:    java ResizingSlowStack < input.txt
 *  Data files:   http://algs4.cs.princeton.edu/13stacks/tobe.txt
 *
 *  Stack implementation with a resizing array.
 *
 *  % more tobe.txt
 *  to be or not to - be - - that - - - is
 *
 *  % java ResizingSlowStack < tobe.txt
 *  to be not that or be (2 left on stack)
 *
 *************************************************************************/

public class XResizingArraySlowStack<T> implements Iterable<T> {
	private T[] a;         // array of items
	private int N = 0;    // number of elements on stack

	// create an empty stack
	@SuppressWarnings("unchecked")
	public XResizingArraySlowStack() {
		a = (T[]) new Object[N];
	}

	public boolean isEmpty() { return N == 0; }
	public int size()        { return N;      }

	// resize the underlying array holding the elements
	private void resize(int capacity) {
		@SuppressWarnings("unchecked")
		T[] temp = (T[]) new Object[capacity];
		for (int i = 0; i < N; i++)
			temp[i] = a[i];
		a = temp;
	}

	// push a new item onto the stack
	public void push(T item) {
		if (N == a.length) resize(N+1);
		a[N] = item;
		N++;
	}

	// delete and return the item most recently added
	public T pop() {
		if (isEmpty()) { throw new Error("Stack underflow error"); }
		N--;
		T item = a[N];
		a[N] = null;  // to avoid loitering
		resize(N); // shrink size of array if necessary
		return item;
	}


	public Iterator<T> iterator()  { return new LIFOIterator();  }

	// an iterator, doesn't implement remove() since it's optional
	private class LIFOIterator implements Iterator<T> {
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
	public static void main(String[] args) {
		Trace.drawStepsOfMethod ("resize");
		Trace.run ();
		StdIn.fromString ("to be or not to - be - - that - - - is");

		XResizingArraySlowStack<String> s = new XResizingArraySlowStack<>();
		while (!StdIn.isEmpty()) {
			String item = StdIn.readString();
			if (!item.equals("-")) s.push(item);
			else if (!s.isEmpty()) StdOut.print(s.pop() + " ");
		}
		StdOut.println("(" + s.size() + " left on stack)");
	}
}
