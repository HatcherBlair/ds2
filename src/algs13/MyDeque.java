package algs13;
import stdlib.*;
import java.text.DecimalFormat;
import java.util.NoSuchElementException;

/**
 * This is a skeleton file for your homework.
 * Complete the functions below. 
 * You may also edit the function "main" to test your code.
 * 
 * You should not use any loops or recursions, except in "delete"
 * Delete may use one loop or recursive helper.
 *
 * You must not add fields or static variables.
 * As always, you must not change the declaration of any method.
 * Do not change any of the methods I have provided, such as "toString" and "check".
 */

public class MyDeque {
	Node first = null;
	Node last = null;
	int N = 0;

	// class invariants:
	//  N is the number of items in the deque
	// first is the first item in the deque
	// last is the last item in the deque
	// deque has no cycles

	static class Node {
		public Node (double item) { this.item = item; }
		public double item;
		public Node next;
		public Node prev;
	}

	public MyDeque ()         { };
	public boolean isEmpty () { return N == 0; }
	public int size ()        { return N; }
   
	private int sizeByIter1(){
		int count =0;
		for(Node x = first; x != null; x = x.next){
			count++;
		}
		return count;
		
	}
	private int sizeByIter2(){
		int count =0;
		for(Node x = last; x != null; x = x.prev){
			count++;
		}
		return count;
	}
	public void pushLeft (double item) {
		// N++
		// TODO
	}

	public double popLeft () {
		if (N == 0) throw new NoSuchElementException ();
		return StdRandom.uniform ();
		// TODO
	}

	public void pushRight (double item) {
		// TODO
	}
	
	public double popRight () {
		if (N == 0) throw new NoSuchElementException ();
		return StdRandom.uniform ();
		// TODO
	}

	/* The concat method should take the Nodes from "that" and add them to "this"
	 * After execution, "that" should be empty.
	 * See the tests in the main program.
	 *
	 * Do not use a loop or a recursive definition.
	 * This method can declare Node variables, but may not create new node objects (using "new").
	 * Therefore the method should not call pushLeft or pushRight (which use create new objects).
	 */
	public void concat (MyDeque that) {
		// TODO
	}

	/* Delete should delete and return the kth element from the left (where k is between 0 and N-1).
	 * See the tests in the main program.
	 *
	 * You may use a loop or a recursive definition here.
	 * This method can declare Node variables, but may not create new node objects (using "new").
	 * Therefore the method should not call pushLeft or pushRight (which use create new objects).
	 */
	public double delete (int k) {
		if (k < 0 || k >= N) throw new IllegalArgumentException ();
		return StdRandom.uniform ();
		// TODO 
	}

	public static void main2(String[] args){ 
    MyDeque d0 = new MyDeque();
      d0.first = new Node(0);
      d0.last = d0.first; d0.N =1;
      System.out.println(d0.toString());
      
      
       MyDeque d1 = new MyDeque();
      d1.first= new Node(1);
      d1.first.next = new Node(0);d1.first.next.prev = d1.first; 
      d1.last = d1.first.next;d1.N=2;
      System.out.println(d1.toString());
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Node n1 = new Node(1);
		Node n2 = new Node(2);
		Node n3 = new Node(3);
		n1.prev = n3; n1.next = n2;
		n2.prev = n1; n2.next = n3;
		n3.prev = n2; n3.next = n1;

		int x =1;

	}
	public static void main1 (String args[]) {
		// TODO: Uncomment these lines to use the visual debugger
		// Note, however, that this will substantially slow things down
		// So you should leave these commented unless you are using the debugger
		//
		// Trace.drawStepsOfMethod ("main");
		// Trace.drawStepsOfMethod ("pushLeft");
		// Trace.run ();

		// Here are some tests to get you started.
		// You can edit this all you like.
		MyDeque d1, d2, d3;
		double k;

		////////////////////////////////////////////////////////////////////
		// push/pop tests
		////////////////////////////////////////////////////////////////////
		d1 = new MyDeque ();
		d1.pushLeft (11);
		check ("left1", d1, "[ 11 ]");
		d1.pushLeft (12);
		check ("left2", d1, "[ 12 11 ]");
		d1.pushLeft (13);
		check ("left3", d1, "[ 13 12 11 ]");
		k = d1.popLeft ();
		check ("left4", d1, "[ 12 11 ]", k, 13);
		k = d1.popLeft ();
		check ("left5", d1, "[ 11 ]", k, 12);
		k = d1.popLeft ();
		check ("left6", d1, "[ ]", k, 11);

		d1 = new MyDeque ();
		d1.pushRight (11);
		check ("right1", d1, "[ 11 ]");
		d1.pushRight (12);
		check ("right2", d1, "[ 11 12 ]");
		d1.pushRight (13);
		check ("right3", d1, "[ 11 12 13 ]");
		k = d1.popRight ();
		check ("right4", d1, "[ 11 12 ]", k, 13);
		k = d1.popRight ();
		check ("right5", d1, "[ 11 ]", k, 12);
		k = d1.popRight ();
		check ("right6", d1, "[ ]", k, 11);

		d1 = new MyDeque ();
		d1.pushLeft (11);
		check ("left/right1", d1, "[ 11 ]");
		d1.pushRight (21);
		check ("left/right2", d1, "[ 11 21 ]");
		d1.pushLeft (12);
		check ("left/right3", d1, "[ 12 11 21 ]");
		d1.pushRight (22);
		check ("left/right4", d1, "[ 12 11 21 22 ]");
		k = d1.popLeft ();
		check ("left/right5", d1, "[ 11 21 22 ]", k, 12);
		k = d1.popLeft ();
		check ("left/right6", d1, "[ 21 22 ]", k, 11);
		k = d1.popLeft ();
		check ("left/right7", d1, "[ 22 ]", k, 21);
		k = d1.popLeft ();
		check ("left/right8", d1, "[ ]", k, 22);

		d1 = new MyDeque ();
		d1.pushLeft (11);
		check ("left/right10", d1, "[ 11 ]");
		d1.pushRight (21);
		check ("left/right11", d1, "[ 11 21 ]");
		d1.pushLeft (12);
		check ("left/right12", d1, "[ 12 11 21 ]");
		d1.pushRight (22);
		check ("left/right13", d1, "[ 12 11 21 22 ]");
		k = d1.popRight ();
		check ("left/right14", d1, "[ 12 11 21 ]", k, 22);
		k = d1.popRight ();
		check ("left/right15", d1, "[ 12 11 ]", k, 21);
		k = d1.popRight ();
		check ("left/right16", d1, "[ 12 ]", k, 11);
		k = d1.popRight ();
		check ("left/right17", d1, "[ ]", k, 12);

		////////////////////////////////////////////////////////////////////
		//  test exceptions
		////////////////////////////////////////////////////////////////////
		try {
			d1.popLeft ();
			showError ("Expected exception1");
		} catch (NoSuchElementException e) {}
		try {
			d1.popRight ();
			showError ("Expected exception2");
		} catch (NoSuchElementException e) {}

		////////////////////////////////////////////////////////////////////
		// concat tests (and more push/pop tests)
		////////////////////////////////////////////////////////////////////
		d1 = new MyDeque ();
		d1.concat (new MyDeque ());
		check ("concat1", d1, "[ ]");
		d1.pushLeft (11);
		d1.concat (new MyDeque ());
		check ("concat2", d1, "[ 11 ]");

		d1 = new MyDeque ();
		d2 = new MyDeque ();
		d2.pushLeft (11);
		d1.concat (d2);
		check ("concat3a", d1, "[ 11 ]");
		check ("concat3b", d2, "[ ]");

		d1 = new MyDeque ();
		d2 = new MyDeque ();
		d1.pushLeft (11);
		d1.pushLeft (12);
		d2.pushLeft (21);
		d2.pushLeft (22);
		d1.concat (d2);
		check ("concat4a", d1, "[ 12 11 22 21 ]");
		check ("concat4b", d2, "[ ]");
		d2.concat (d1);
		check ("concat5a", d2, "[ 12 11 22 21 ]");
		check ("concat5b", d1, "[ ]");

		d1 = new MyDeque ();
		for (int i = 10; i < 13; i++) { d1.pushLeft (i); checkInvariants ("left1", d1); }
		for (int i = 20; i < 23; i++) { d1.pushRight (i); checkInvariants ("right2", d1); }
		check ("concat6", d1, "[ 12 11 10 20 21 22 ]");
		d2 = new MyDeque ();
		d1.concat (d2);
		check ("concat7a", d1, "[ 12 11 10 20 21 22 ]");
		check ("concat7b", d2, "[ ]");

		for (int i = 30; i < 33; i++) { d2.pushLeft (i); checkInvariants ("left3", d2); }
		for (int i = 40; i < 43; i++) { d2.pushRight (i); checkInvariants ("right4", d2); }
		check ("concat9", d2, "[ 32 31 30 40 41 42 ]");

		d3 = new MyDeque ();
		d2.concat (d3);
		check ("concat10", d2, "[ 32 31 30 40 41 42 ]");
		check ("concat11", d3, "[ ]");

		d1.concat (d2);
		check ("concat12", d1, "[ 12 11 10 20 21 22 32 31 30 40 41 42 ]");
		check ("concat13", d2, "[ ]");
		for (int i = 0; i < 12; i++) { d1.popLeft (); checkInvariants ("left5", d1); }
		////////////////////////////////////////////////////////////////////
		// delete tests
		////////////////////////////////////////////////////////////////////
		d1 = new MyDeque ();
		d1.pushLeft (11);
		k = d1.delete (0);
		check ("delete1", d1, "[ ]", k, 11);
		for (int i = 10; i < 20; i++) { d1.pushRight (i); checkInvariants ("right6", d1); }
		k = d1.delete (0);
		check ("delete2", d1, "[ 11 12 13 14 15 16 17 18 19 ]", k, 10);
		k = d1.delete (8);
		check ("delete3", d1, "[ 11 12 13 14 15 16 17 18 ]", k, 19);
		k = d1.delete (4);
		check ("delete4", d1, "[ 11 12 13 14 16 17 18 ]", k, 15);
		k = d1.delete (3);
		check ("delete5", d1, "[ 11 12 13 16 17 18 ]", k, 14);
		k = d1.delete (0);
		check ("delete6", d1, "[ 12 13 16 17 18 ]", k, 11);
		k = d1.delete (4);
		check ("delete7", d1, "[ 12 13 16 17 ]", k, 18);
		k = d1.delete (3);
		check ("delete8", d1, "[ 12 13 16 ]", k, 17);
		k = d1.delete (1);
		check ("delete9", d1, "[ 12 16 ]", k, 13);
		k = d1.delete (1);
		check ("delete10", d1, "[ 12 ]", k, 16);
		k = d1.delete (0);
		check ("delete10", d1, "[ ]", k, 12);
		
		////////////////////////////////////////////////////////////////////
		// More push/pop
		////////////////////////////////////////////////////////////////////
		d1 = new MyDeque();
		d1.pushRight(21);
		check("left/right20", d1, "[ 21 ]");
		d1.pushLeft(11);
		check("left/right21", d1, "[ 11 21 ]");
		k = d1.popLeft();
		check("left/right22", d1, "[ 21 ]", k, 11);
		d1.pushRight(22);
		check("left/right23", d1, "[ 21 22 ]");

		d1 = new MyDeque();
		d1.pushRight(21);
		check("left/right30", d1, "[ 21 ]");
		d1.pushRight(22);
		check("left/right31", d1, "[ 21 22 ]");
		d1.pushLeft(11);
		check("left/right32", d1, "[ 11 21 22 ]");
		k = d1.popLeft();
		check("left/right33", d1, "[ 21 22 ]", k, 11);
		k = d1.popLeft();
		check("left/right34", d1, "[ 22 ]", k, 21);
		d1.pushLeft(12);
		check("left/right35", d1, "[ 12 22 ]");
		StdOut.println ("Finished tests");
	}
	
	public MyDeque (String s) {
		String[] nums = s.split (" ");
		for (int i = nums.length-1; i >= 0; i--) {
			try { 
				pushLeft (Double.parseDouble (nums[i]));			
			} catch (NumberFormatException e) {	}
		}
	}

	// return a HUMAN READABLE string representation of the deque
	public String toString () { 
		DecimalFormat format = new DecimalFormat ("#.###");
		StringBuilder result = new StringBuilder ("[ ");
		for (Node x = first; x != null; x = x.next) {
			result.append (format.format (x.item));
			result.append (" ");
		}
		result.append ("]");
		return result.toString ();
	}

	// o.toString().  So, the receiver is an object of the class	
	// <ClassName>.checkInvariants(..)


	private static void checkInvariants (String message, MyDeque that) {
		// int i= N not permitted
		// can only refer to static methods, static variables
		int N = that.N;
		MyDeque.Node first = that.first;
		MyDeque.Node last = that.last;

		if (N < 0) throw new Error ();
		// if N ==0, then first and last must be null
		// if N !=0, then first and last must not be null
		// cannot be that one of first/last is null and the other is not
		if (N == 0) { 
			if (first != null) {
				showError (String.format ("%s: N==0, Expected first == null.", message));
			}
			if (last != null) {
				showError (String.format ("%s: N==0, Expected last == null.", message));
			}
		} else {
			if (first == null) {
				showError (String.format ("%s: N!=0, Expected first != null.", message));
			}
			if (last == null) {
				showError (String.format ("%s: N!=0, Expected last != null.", message));
			}
		}
		if (N > 0) {
			MyDeque.Node prev = null;
			MyDeque.Node current = first;
			for (int i = 0; i < N; i++) {
				if (current == null) {
					showError (String.format ("%s: N==%d, Expected %d next nodes, but got less.", message, N, N));
					return;
				}
				if (current.prev != prev) { 
					showError (String.format ("%s: i=%d, Broken prev link.", message, i));
					return;
				}
				prev = current;
				current = current.next;
			}
			if (current != null) {
				showError (String.format ("%s: N==%d, Expected %d next nodes, but got more.", message, N, N));
				return;
			}
			MyDeque.Node next = null;
			current = last;
			for (int i = 0; i < N; i++) {
				if (current == null) {
					showError (String.format ("%s: N==%d, Expected %d prev nodes, but got less.", message, N, N));
					return;
				}
				if (current.next != next) {
					showError (String.format ("%s: i=%d, Broken next link.", message, i));
					return;
				}
				next = current;
				current = current.prev;
			}
			if (current != null) {
				showError (String.format ("%s: N==%d, Expected %d prev nodes, but got more.", message, N, N));
				return;
			}
		}
	}
	private static void check (String message, MyDeque actual, String expected) {
		checkInvariants (message, actual);
		if (expected != null) {
			if (!expected.equals (actual.toString ())) {
				showError ("Expected \"" + expected + "\", got \"" + actual + "\"");
				return;
			}
		}
	}
	private static void check (String message, MyDeque actual, String expected, double dActual, double dExpected) {
		if (dExpected != dActual) {
			showError ("Expected \"" + dExpected + "\", got \"" + dActual + "\"");
			return;
		}
		check (message, actual, expected);
	}

	private static void showError (String message) {
		Trace.draw ();
		StdOut.println (message);
		throw new Error (); // stops execution
	}
}
