package algs13;

import java.text.DecimalFormat;
import stdlib.*;

public class MyLinked2GenericTests {
	//TODO: remove this before you give it to students
	private static void test (String message, java.util.function.Consumer<MyLinked2> function, String list, String expected) {
		MyLinked2 actual = MyLinked2.of (list);
		try {
			function.accept (actual);
		} catch (Throwable e) {
			String exception = e.getClass ().getName ();
			if (! exception.equals (expected)) {
				showError (String.format ("Error: [ %s ].%s: expected=%s, actual=%s", list, message, expected, exception));
				e.printStackTrace (); // for debugging
			}
			return;
		}
		checkInvariants (message, actual);
		if (!expected.equals (actual.toString ())) {
			// If you want to stop execution on an error, replace "StdOut.println" with "throw new Error"
			showError (String.format ("Error: [ %s ].%s: expected=%s, actual=%s", list, message, expected, actual.toString ()));
		}
	}
	public static void main (String[] args) {
		test ("delete(0)", list -> list.delete (0), "", "java.lang.IllegalArgumentException");
		test ("delete(0)", list -> list.delete(-1), "11 21 31", "java.lang.IllegalArgumentException");
		test ("delete(0)", list -> list.delete (3), "11 21 31", "java.lang.IllegalArgumentException");
		test ("delete(0)", list -> list.delete (0), "11", "[ ]");
		test ("delete(0)", list -> list.delete (0), "11 21 31 41", "[ 21 31 41 ]");
		test ("delete(1)", list -> list.delete (1), "11 21 31 41", "[ 11 31 41 ]");
		test ("delete(2)", list -> list.delete (2), "11 21 31 41", "[ 11 21 41 ]");
		test ("delete(3)", list -> list.delete (3), "11 21 31 41", "[ 11 21 31 ]");
		test ("delete(0)", list -> list.delete (0), "11 21 31 41 51", "[ 21 31 41 51 ]");
		test ("delete(1)", list -> list.delete (1), "11 21 31 41 51", "[ 11 31 41 51 ]");
		test ("delete(2)", list -> list.delete (2), "11 21 31 41 51", "[ 11 21 41 51 ]");
		test ("delete(3)", list -> list.delete (3), "11 21 31 41 51", "[ 11 21 31 51 ]");
		test ("delete(4)", list -> list.delete (4), "11 21 31 41 51", "[ 11 21 31 41 ]");		
		test ("reverse", MyLinked2::reverse, "", "[ ]");
		test ("reverse", MyLinked2::reverse, "11", "[ 11 ]");
		test ("reverse", MyLinked2::reverse, "11 21", "[ 21 11 ]");
		test ("reverse", MyLinked2::reverse, "11 21 31", "[ 31 21 11 ]");
		test ("reverse", MyLinked2::reverse, "11 21 31 41", "[ 41 31 21 11 ]");
		test ("reverse", MyLinked2::reverse, "11 21 31 41 51", "[ 51 41 31 21 11 ]");
		test ("remove(5)", list -> list.remove (5), "", "[ ]");
		test ("remove(5)", list -> list.remove (5), "5", "[ ]");
		test ("remove(5)", list -> list.remove (5), "5 5", "[ ]");
		test ("remove(5)", list -> list.remove (5), "5 5 5", "[ ]");
		test ("remove(5)", list -> list.remove (5), "11", "[ 11 ]");
		test ("remove(5)", list -> list.remove (5), "11 21", "[ 11 21 ]");
		test ("remove(5)", list -> list.remove (5), "11 21 31", "[ 11 21 31 ]");
		test ("remove(5)", list -> list.remove (5), "5 11 21 31 41", "[ 11 21 31 41 ]");
		test ("remove(5)", list -> list.remove (5), "5 5 11 21 31 41", "[ 11 21 31 41 ]");
		test ("remove(5)", list -> list.remove (5), "5 5 5 11 21 31 41", "[ 11 21 31 41 ]");
		test ("remove(5)", list -> list.remove (5), "11 21 31 41 5", "[ 11 21 31 41 ]");
		test ("remove(5)", list -> list.remove (5), "11 21 31 41 5 5", "[ 11 21 31 41 ]");
		test ("remove(5)", list -> list.remove (5), "11 21 31 41 5 5 5", "[ 11 21 31 41 ]");
		test ("remove(5)", list -> list.remove (5), "11 21 5 31 41", "[ 11 21 31 41 ]");
		test ("remove(5)", list -> list.remove (5), "11 21 5 5 31 41", "[ 11 21 31 41 ]");
		test ("remove(5)", list -> list.remove (5), "11 21 5 5 5 31 41", "[ 11 21 31 41 ]");
		test ("remove(5)", list -> list.remove (5), "5 11 21 31 41", "[ 11 21 31 41 ]");
		test ("remove(5)", list -> list.remove (5), "5 5 11 21 31 41", "[ 11 21 31 41 ]");
		test ("remove(5)", list -> list.remove (5), "5 5 5 11 21 31 41", "[ 11 21 31 41 ]");
		test ("remove(5)", list -> list.remove (5), "11 21 31 41 5", "[ 11 21 31 41 ]");
		test ("remove(5)", list -> list.remove (5), "11 21 31 41 5 5", "[ 11 21 31 41 ]");
		test ("remove(5)", list -> list.remove (5), "11 21 31 41 5 5 5", "[ 11 21 31 41 ]");
		test ("remove(5)", list -> list.remove (5), "11 21 5 31 41", "[ 11 21 31 41 ]");
		test ("remove(5)", list -> list.remove (5), "11 21 5 5 31 41", "[ 11 21 31 41 ]");
		test ("remove(5)", list -> list.remove (5), "11 21 5 5 5 31 41", "[ 11 21 31 41 ]");
		test ("remove(5)", list -> list.remove (5), "5 5 5 11 5 5 21 5 31 5 5 41 5 5 5", "[ 11 21 31 41 ]");
		test ("remove(5.1)", list -> list.remove (5.1), "5.1 5.1 5.1 5 11 5.1 5.1 21 5.1 31 5.1 5.1 41 5.1 5.1 5.1", "[ 5 11 21 31 41 ]");
		
		// What follows is an example of random testing. 
		// Generating good random tests is tricky!
		// The test input and expected response are generated together.
		int NUM_TESTS = 100;
		int MAX_LENGTH = 200;
		DecimalFormat format = new DecimalFormat ("#.###");
		for (int i=0; i<NUM_TESTS; i++) {
			StringBuilder list = new StringBuilder ();
			StringBuilder expected = new StringBuilder ("[ ");
			int length = StdRandom.uniform (MAX_LENGTH);
			double item = StdRandom.uniform ();
			for (int j=0; j<length; j++) {
				boolean makeItem = StdRandom.uniform () < .33;
				if (makeItem) {
					list.append (item); 
					list.append (" ");
				} else {
					double num = StdRandom.uniform ();
					list.append (num); 
					list.append (" ");
					expected.append (format.format (num));
					expected.append (" ");
				}				
			}
			expected.append ("]");
			test (String.format ("remove(%f)", item), li -> li.remove (item), list.toString (), expected.toString ());
		}		
		StdOut.println ("Finished tests");
	}
	
	
	static void showError (String message) {
		Trace.draw ();
		StdOut.println (message);
		//throw new Error (); // stops execution
	}
	private static void checkInvariants (String message, MyLinked2 list) {
		MyLinked2.Node x = list.first;
		int N = list.N;
		for (int i = 0; i < N; i++) {
			if (x == null) {
				showError (String.format ("%s: Expected %d nodes, but got less.", message, N));
			}
			x = x.next;
		}
		if (x != null) {
			showError (String.format ("%s: Expected %d nodes, but got more.", message, N));
		}
	}
	private static void check (String message, MyLinked2 actual, String expected) {
		checkInvariants (message, actual);
		if (!expected.equals (actual.toString ())) {
			showError (String.format ("%s: expected=%s, actual=%s", message, expected, actual.toString ()));
		}
	}
}
