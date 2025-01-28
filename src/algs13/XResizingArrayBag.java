package algs13;
import stdlib.*;
import java.util.Iterator;
import java.util.NoSuchElementException;
/* ***********************************************************************
 *  Compilation:  javac ResizingArrayBag.java
 *  Execution:    java ResizingArrayBag
 *
 *  Bag implementation with a resizing array.
 *
 *************************************************************************/

public class XResizingArrayBag<T> implements Iterable<T> {
	private T[] a;         // array of items
	private int N = 0;        // number of elements on stack

	// create an empty bag
	@SuppressWarnings("unchecked")
	public XResizingArrayBag() {
		a = (T[]) new Object[2];
	}

	public boolean isEmpty() { return N == 0; }
	public int size()        { return N;      }

	// resize the underlying array holding the elements
	private void resize(int capacity) {
		if (capacity <= N) throw new IllegalArgumentException ();
		@SuppressWarnings("unchecked")
		T[] temp = (T[]) new Object[capacity];
		for (int i = 0; i < N; i++)
			temp[i] = a[i];
		a = temp;
	}

	// add a new item to the bag
	public void add(T item) {
		if (N == a.length) resize(2*a.length);    // double size of array if necessary
		a[N++] = item;                            // add item
	}

	public Iterator<T> iterator()  { return new ArrayIterator();  }

	// an iterator, doesn't implement remove() since it's optional
	private class ArrayIterator implements Iterator<T> {
		private int i = 0;
		public boolean hasNext()  { return i < N;                               }
		public void remove()      { throw new UnsupportedOperationException();  }

		public T next() {
			if (!hasNext()) throw new NoSuchElementException();
			return a[i++];
		}
	}



	/* *********************************************************************
	 * Test routine.
	 **********************************************************************/
	public static void main(String[] args) {
		XResizingArrayBag<String> bag = new XResizingArrayBag<>();
		bag.add("Hello");
		bag.add("World");
		bag.add("how");
		bag.add("are");
		bag.add("you");

		for (String s : bag)
			StdOut.println(s);
	}

}
