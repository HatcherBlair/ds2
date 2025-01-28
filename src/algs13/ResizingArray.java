package algs13;
import stdlib.*;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
/* ***********************************************************************
 *  Compilation:  javac ResizingArray.java
 *  Execution:    java ResizingArray < input.txt
 *************************************************************************/

public class ResizingArray<T> implements Iterable<T> {
	private T[] a;
	private int N;
	private long opcount;

	@SuppressWarnings("unchecked")
	public ResizingArray(int initialCapacity) {
		this.N = 0;
		this.a = (T[]) new Object[initialCapacity];
	}
	public ResizingArray()                       { this (4); }
	public ResizingArray(Iterable<T> collection) { this (); addAll (collection); }
	public ResizingArray(T[] array)              { this (); addAll (array); }

	public int size() { return N;  }

	@SuppressWarnings("unchecked")
	public void ensureCapacity(int minCapacity) {
		opcount++;
		if (minCapacity > a.length) {
			int newCapacity = Math.max (minCapacity, a.length*2);
			T[] temp = (T[]) new Object[newCapacity];
			for (int i = a.length - 1; i >= 0; i--) temp[i] = a[i];
			a = temp;
		}
	}

	public void set(int index, T item) {
		if (index >= N) throw new Error ();
		opcount++;
		a[index] = item;
	}
	public void add(T item) {
		ensureCapacity(N+1);
		a[N++] = item;
	}
	public void addAll(Iterable<T> collection) {
		for (T item : collection) {
			ensureCapacity(N+1);
			a[N] = item;
			N++;
		}
	}
	public void addAll(T[] array) {
		int newSize = N + array.length;
		ensureCapacity(newSize);
		for (int i = 0; i < array.length ; i++) {
			a[i+N] = array[i];
		}
		N = newSize;
	}



	public Iterator<T> iterator()  { return new ArrayIterator(); }
	private class ArrayIterator implements Iterator<T> {
		private int index = 0;
		private long oc = opcount;

		public boolean hasNext()  { return index < N; }
		public void remove()      { throw new UnsupportedOperationException();  }
		public T next() {
			if (opcount != oc) throw new ConcurrentModificationException ();
			if (!hasNext()) throw new NoSuchElementException();
			T item = a[index];
			index++;
			return item;
		}
	}

	public String toString () {
		if (N == 0) return "[]";
		StringBuilder sb = new StringBuilder ("[");
		sb.append (N + "/" + a.length + ":");
		sb.append (a[0]);
		for (int i = 1; i < N; i++) {
			sb.append (" ");
			sb.append (a[i]);
		}
		sb.append ("]");
		return sb.toString ();
	}

	public static void main(String[] args) {
		ResizingArray<Integer> a = new ResizingArray<>();
		for (int i=0; i<10; i++) {
			a.add (StdRandom.uniform(100));
			StdOut.println (a);
		}
		a.set (1, 5);
		StdOut.println (a);
		a.addAll (new Integer[] { 1, 2, 3, 4, 5 });
		StdOut.println (a);
		Queue<Integer> q = new Queue<>();
		for (int i = 1; i < 6; i++)
			q.enqueue (i);
		a.addAll (q);
		StdOut.println (a);
	}
}
