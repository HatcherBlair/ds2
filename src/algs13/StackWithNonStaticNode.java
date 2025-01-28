package algs13;
import  stdlib.*;
import java.util.Iterator;
import java.util.NoSuchElementException;
/* ***********************************************************************
 *  Compilation:  javac Stack.java
 *  Execution:    java Stack < input.txt
 *
 *  A generic stack, implemented using a linked list. Each stack
 *  element is of type Item.
 *
 *  % more tobe.txt
 *  to be or not to - be - - that - - - is
 *
 *  % java Stack < tobe.txt
 *  to be not that or be (2 left on stack)
 *
 *************************************************************************/

/**
 *  The {@code Stack} class represents a last-in-first-out (LIFO) stack of generic items.
 *  It supports the usual <em>push</em> and <em>pop</em> operations, along with methods
 *  for peeking at the top item, testing if the stack is empty, and iterating through
 *  the items in LIFO order.
 *  <p>
 *  All stack operations except iteration are constant time.
 *  <p>
 *  For additional documentation, see <a href="/algs4/13stacks">Section 1.3</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 */
public class StackWithNonStaticNode<T> implements Iterable<T> {
	private int N;          // size of the stack
	private Node first;     // top of stack

	// helper linked list class
	private class Node {
		T item;
		Node next;
	}

	/**
	 * Create an empty stack.
	 */
	public StackWithNonStaticNode() {
		this.first = null;
		this.N = 0;
	}

	/**
	 * Is the stack empty?
	 */
	public boolean isEmpty() {
		return first == null;
	}

	/**
	 * Return the number of items in the stack.
	 */
	public int size() {
		return N;
	}

	/**
	 * Add the item to the stack.
	 */
	public void push(T item) {
		Node oldfirst = first;
		first = new Node();
		first.item = item;
		first.next = oldfirst;
		N++;
	}

	/**
	 * Delete and return the item most recently added to the stack.
	 * @throws java.util.NoSuchElementException if stack is empty.
	 */
	public T pop() {
		if (isEmpty()) throw new NoSuchElementException("Stack underflow");
		T item = first.item;        // save item to return
		first = first.next;            // delete first node
		N--;
		return item;                   // return the saved item
	}


	/**
	 * Return the item most recently added to the stack.
	 * @throws java.util.NoSuchElementException if stack is empty.
	 */
	public T peek() {
		if (isEmpty()) throw new NoSuchElementException("Stack underflow");
		return first.item;
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


	// check internal invariants
	private static <T> boolean check(StackWithNonStaticNode<T> that) {
		int N = that.N;
		StackWithNonStaticNode<T>.Node first = that.first;
		if (N == 0) {
			if (first != null) return false;
		}
		else if (N == 1) {
			if (first == null)      return false;
			if (first.next != null) return false;
		}
		else {
			if (first.next == null) return false;
		}

		// check internal consistency of instance variable N
		int numberOfNodes = 0;
		for (StackWithNonStaticNode<T>.Node x = first; x != null; x = x.next) {
			numberOfNodes++;
		}
		if (numberOfNodes != N) return false;

		return true;
	}


	/**
	 * Return an iterator to the stack that iterates through the items in LIFO order.
	 */
	public Iterator<T> iterator()  { return new ListIterator();  }

	// an iterator, doesn't implement remove() since it's optional
	private class ListIterator implements Iterator<T> {
		private Node current = first;
		public boolean hasNext()  { return current != null;                     }
		public void remove()      { throw new UnsupportedOperationException();  }

		public T next() {
			if (!hasNext()) throw new NoSuchElementException();
			T item = current.item;
			current = current.next;
			return item;
		}
	}


	/**
	 * A test client.
	 */
	public static void bookMain(String[] args) {
		StdIn.fromString ("to be or not to - be - - that - - - is");
		StackWithNonStaticNode<String> stack = new StackWithNonStaticNode<>();
		while (!StdIn.isEmpty()) {
			String item = StdIn.readString();
			if (!item.equals("-")) stack.push(item);
			else if (!stack.isEmpty()) StdOut.print(stack.pop() + " ");
		}
		StdOut.println(stack.size() + " left on stack:");
		for (String s : stack) {
			StdOut.print (s + " ");
		}
		StdOut.println ();
	}
	public static void main(String[] args) {
		//Trace.showBuiltInObjectsVerbose (true);
		Trace.drawStepsOfMethod ("main");
		Trace.run ();
		Integer r1 = null;
		StackWithNonStaticNode<Integer> s1 = new StackWithNonStaticNode<>();
		s1.push (11);
		s1.push (21);
		s1.push (31);
		s1.push (41);
		s1.push (51);
		r1 = s1.pop ();
		r1 = s1.pop ();
		r1 = s1.pop ();
		r1 = null;
		s1.push (61);
		s1.push (71);
		
		String r2 = null;
		StackWithNonStaticNode<String> s2 = new StackWithNonStaticNode<>();		
		s2.push ("a");
		s2.push ("b");
		s2.push ("c");
		s2.push ("d");
		s2.push ("e");
		r2 = s2.pop ();
		r2 = s2.pop ();
		r2 = s2.pop ();
		r2 = null;
		s2.push ("f");
		s2.push ("g");
		s2.push ("h");
	}
	public static void main2(String[] args) {
		Trace.showObjectIdsRedundantly (true);
		//Trace.showBuiltInObjectsVerbose (true);
		Trace.drawStepsOfMethod ("main");
		Trace.run ();
		StackWithNonStaticNode<Integer> s1 = new StackWithNonStaticNode<>();
		s1.push (300);
		StackWithNonStaticNode<String> s2 = new StackWithNonStaticNode<>();
		s2.push ("duck");
		s2.push ("goose");
	}
}
