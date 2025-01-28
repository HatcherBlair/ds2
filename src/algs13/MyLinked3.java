package algs13;
import stdlib.*;

public class MyLinked3 {
	static class Node {
		public Node() { }
		public double item;
		public Node next;
	}

	int N;
	Node first;

	// first starts a ayclic list whsoe length is given by N

	public MyLinked3 () {
		first = null;
		N = 0;
		checkInvariants ();
	}

	private void myassert (String s, boolean b) { if (!b) throw new Error ("Assertion failed: " + s); }
	private void checkInvariants() {
		myassert("Empty <==> first==null", (N == 0) == (first == null));
		Node x = first;
		for (int i = 0; i < N; i++) {
			if (x==null) {
				throw new Error ("List too short!");
			}
			x = x.next;
		}
		myassert("EndOfList == null", x == null);
	}

	public boolean isEmpty () { return first == null; }
	public int size () { return N; }
	public void add (double item) {
		Node newfirst = new Node ();
		newfirst.item = item;
		newfirst.next = first;
		first = newfirst;
		N++;
	}

	public void addB(double item){
		N++;
		Node tmp = new Node();
		tmp.item = item;
		tmp.next = null;
		if (first == null) {
			first = tmp;
		}
		else {
			Node curr;
			for (curr = first; curr.next != null; curr = curr.next) ;
			curr.next = tmp;
		}
		checkInvariants();
	}
			

	// return Double.NEGATIVE_INFINITY if the linked list is empty
	public double sum () { return sum (first); }
	private static double sum (Node x) {
		double result = 0;
		while (x != null) {
			result = result + x.item;
			x = x.next;
		}
		return result;
	}
	
	// return Double.NEGATIVE_INFINITY if the linked list is empty
	public double max () { 
		// TODO
		return Double.NEGATIVE_INFINITY;
	}

	// return Double.NEGATIVE_INFINITY if the linked list is empty
	public double maxRecursive () { 
		// TODO
		return Double.NEGATIVE_INFINITY;
	}

	// delete the kth element
	public void delete (int k) {
		if (k < 0 || k >= N) throw new IllegalArgumentException ();
		// TODO 1.3.20
		checkInvariants ();
	}

	// reverse the list "in place"... without creating any new nodes
	public void reverse () {
		// TODO 1.3.30
		checkInvariants ();
	}

	// remove
	public void remove (double item) {
		// TODO 1.3.26
		checkInvariants ();
	}


	private static void print (String s, MyLinked3 b) {
		StdOut.print (s + ": ");
		for (Node x = b.first; x != null; x = x.next)
			StdOut.print (x.item + " ");
		StdOut.println ();
	}
	private static void print (String s, MyLinked3 b, double i) {
		StdOut.print (s + ": ");
		for (Node x = b.first; x != null; x = x.next)
			StdOut.print (x.item + " ");
		StdOut.println (": " + i);
	}
	public static MyLinked3 listFromString (String s) {
		MyLinked3 list = new MyLinked3 ();
		String[] nums = s.split (" ");
		for (int i = nums.length-1; i >= 0; i--) {
			try { list.add (Integer.parseInt (nums[i])); } catch (NumberFormatException e) {}
		}
		return list;
	}
	private static void testMax () {
		MyLinked3 b = new MyLinked3 ();
		print ("empty", b, b.max());
		b.add (-1);
		print ("singleton", b, b.max());
		b.add (-2);
		b.add (-3);
		b.add (-4);
		print ("at end", b, b.max());
		b.add (5);
		print ("at beginning", b, b.max());
		b.add (3);
		b.add (2);
		b.add (4);
		print ("in the middle", b, b.max());
	}
	private static void testMaxRecursive () {
		MyLinked3 b = new MyLinked3 ();
		print ("empty", b, b.maxRecursive());
		b.add (-1);
		print ("singleton", b, b.maxRecursive());
		b.add (-2);
		b.add (-3);
		b.add (-4);
		print ("at end", b, b.maxRecursive());
		b.add (5);
		print ("at beginning", b, b.maxRecursive());
		b.add (3);
		b.add (2);
		b.add (4);
		print ("in the middle", b, b.maxRecursive());
	}
	private static void testDelete () {
		MyLinked3 b = new MyLinked3 ();
		b.add (1);
		print ("singleton", b);
		b.delete (0);
		print ("deleted", b);
		for (double i = 1; i < 13; i++) {
			b.add (i);
		}
		print ("bigger list", b);
		b.delete (0);
		print ("deleted at beginning", b);
		b.delete (10);
		print ("deleted at end", b);
		b.delete (4);
		print ("deleted in middle", b);
	}
	private static void testReverse () {
		MyLinked3 b = new MyLinked3 ();
		b.reverse ();
		print ("reverse empty", b);
		b.add (1);
		print ("singleton", b);
		b.reverse ();
		print ("reverse singleton", b);
		b.add (2);
		print ("two", b);
		b.reverse ();
		print ("reverse two", b);
		b.reverse ();
		print ("reverse again", b);
		for (double i = 3; i < 7; i++) {
			b.add (i);
			b.add (i);
		}
		print ("bigger list", b);
		b.reverse ();
		print ("reversed", b);
	}
	private static void testRemove () {
		MyLinked3 b = new MyLinked3 ();
		b.remove (4);
		print ("removed 4 from empty", b);
		b.add (1);
		b.remove (4);
		print ("removed 4 from singelton", b);
		b.remove (1);
		print ("removed 1 from singelton", b);
		for (double i = 1; i < 5; i++) {
			b.add (i);
			b.add (i);
		}
		for (double i = 1; i < 5; i++) {
			b.add (i);
			b.add (i);
			b.add (i);
			b.add (i);
			b.add (i);
		}
		print ("longer list", b);
		b.remove (9);
		print ("removed all 9s", b); // does nothing
		b.remove (3);
		print ("removed all 3s", b);
		b.remove (1);
		print ("removed all 1s", b);
		b.remove (4);
		print ("removed all 4s", b);
		b.remove (2);
		print ("removed all 2s", b); // should be empty
	}

	public static void main (String args[]) {
		MyLinked3 b = new MyLinked3 ();
		print("b",b);
		b.add(2);
		print("b",b);
		b.add(3);
		print("b",b);

		int x =1;
		//   testMax ();
		//        testMaxRecursive ();
		//        testDelete ();
		//        testReverse ();
		//        testRemove ();
	}
}

































