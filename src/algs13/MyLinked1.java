package algs13;
import java.text.DecimalFormat;
import stdlib.*;

/* 
 * Complete the methods below.
 * See the tests in main for examples of what each function should do.
 * 
 * deleteFirst should modify the list.
 * None of the other methods should modify the list.
 * 
 * You may not add any fields to the node or list classes.
 * You may not add any methods to the node class.
 * You may not create any new node objects or other objects.
 * For example, you cannot create a new Stack or ArrayList.
 * 
 * Each function must be independent: you cannot call one solution function from the other.  
 * You MAY add private methods to the list class (helper functions for the recursion). 
 */
public class MyLinked1 {
	static class Node {
		public Node (double item, Node next) { this.item = item; this.next = next; }
		public double item;
		public Node next;
	}
	Node first;


	// write a function to compute the size of the list, using a loop
	// empty list has size 0
	public int sizeLoop () {
		return StdRandom.uniform (100); //TODO: fix this
	}

	// write a function to compute the size of the list, using a forward recursion
	// empty list has size 0
	public int sizeForward () {
		return StdRandom.uniform (100); //TODO: fix this
	}

	// write a function to compute the size of the list, using a backward recursion
	// empty list has size 0
	public int sizeBackward () {
		return StdRandom.uniform (100); //TODO: fix this
	}

	// delete the first element of this list
	public void deleteFirst () {
		// TODO
	}
	
	// compute the position of the first 5.0 in the list, counting as an offset from the beginning.  
	// if 5.0 is the FIRST element, the position is 0
	// if 5.0 does not appear, return a negative number
	// you can write this using a loop or recursion, in any style, but you should only have one loop or recursive helper
	// I would expect
	// [0,1,2,5,5,5,5,5,8,9].positionOfFirstFiveFromBeginning() == 3
	// [0,1,2,5,5,5,5,5,8,9].positionOfLastFiveFromEnd() == 2
	public int positionOfFirstFiveFromBeginning () {
		return StdRandom.uniform (100); //TODO: fix this
	}

	// compute the position of the last 5.0 in the list, counting as an offset from the end.  
	// if 5.0 is the LAST element, the position is 0
	// if 5.0 does not appear, return a negative number
	// you can write this using a loop or recursion, in any style, but you should only have one loop or recursive helper
	// Hint: One way to do this is to use a backwards recursion.
	//   If the number does appear return a non-negative number.
    //   If the number does not appear, return the distance to the END of the list as a NEGATIVE number.
	//   For example:
	//                  [].positionOfLastFiveFromEnd() == -1
	//                [41].positionOfLastFiveFromEnd() == -2
	//             [31 41].positionOfLastFiveFromEnd() == -3
	//          [21 31 41].positionOfLastFiveFromEnd() == -4
	//       [11 21 31 41].positionOfLastFiveFromEnd() == -5
	//     [5 11 21 31 41].positionOfLastFiveFromEnd() ==  4
	//   [5 5 11 21 31 41].positionOfLastFiveFromEnd() ==  4
	// [1 5 5 11 21 31 41].positionOfLastFiveFromEnd() ==  4
	//                                                      
	//                  [].positionOfLastFiveFromEnd() == -1
	//                 [5].positionOfLastFiveFromEnd() ==  0
	//                                                      
	//                  [].positionOfLastFiveFromEnd() == -1
	//                [41].positionOfLastFiveFromEnd() == -2
	//             [31 41].positionOfLastFiveFromEnd() == -3
	//           [5 31 41].positionOfLastFiveFromEnd() ==  2
	//        [21 5 31 41].positionOfLastFiveFromEnd() ==  2
	//      [5 21 5 31 41].positionOfLastFiveFromEnd() ==  2
	public int positionOfLastFiveFromEnd () {
		return StdRandom.uniform (100); //TODO: fix this
	}
	
	public static void main (String args[]) {
		//mainDebug ();
		mainRunTests ();
	}
	private static void mainDebug () {
		// Use this for debugging!
		// Uncomment the call in main to use this.
		// Add the names of helper functions if you use them.
		Trace.drawStepsOfMethod ("sizeLoop");
		Trace.drawStepsOfMethod ("sizeForward");
		Trace.drawStepsOfMethod ("sizeBackward");
		Trace.drawStepsOfMethod ("positionOfFirstFiveFromBeginning");
		Trace.drawStepsOfMethod ("positionOfLastFiveFromEnd");
		Trace.drawStepsOfMethod ("deleteFirst");
		Trace.run (); 
		// TODO:  Put the test here you want to debug:
		testSizeLoop (4, "11 -21.2 31 41");
	}
	private static void mainRunTests () {
		testSizeLoop (2, "11 21");	
		testSizeLoop (4, "11 -21.2 31 41");
		testSizeLoop (1, "11");
		testSizeLoop (0, "");	

		testSizeForward (2, "11 21");	
		testSizeForward (4, "11 -21.2 31 41");
		testSizeForward (1, "11");
		testSizeForward (0, "");
		
		testSizeBackward (2, "11 21");	
		testSizeBackward (4, "11 -21.2 31 41");
		testSizeBackward (1, "11");
		testSizeBackward (0, "");
		
		testPositionOfFirstFiveFromBeginning (1, "11 5 21 31 41");
		testPositionOfFirstFiveFromBeginning (2, "11 21 5 31 41");
		testPositionOfFirstFiveFromBeginning (3, "11 21 31 5 41");
		testPositionOfFirstFiveFromBeginning (4, "11 21 31 41 5");
		testPositionOfFirstFiveFromBeginning (0, "5 11 21 31 41");
		testPositionOfFirstFiveFromBeginning (3, "0 1 2 5 5 5 5 5 8 9");
		testPositionOfFirstFiveFromBeginning (-1, "11 21 31 41");
		testPositionOfFirstFiveFromBeginning (-1, "11");
		testPositionOfFirstFiveFromBeginning (-1, "");

		testPositionOfLastFiveFromEnd (3, "11 5 21 31 41");
		testPositionOfLastFiveFromEnd (2, "11 21 5 31 41");
		testPositionOfLastFiveFromEnd (1, "11 21 31 5 41");
		testPositionOfLastFiveFromEnd (0, "11 21 31 41 5");
		testPositionOfLastFiveFromEnd (4, "5 11 21 31 41");
		testPositionOfLastFiveFromEnd (2, "0 1 2 5 5 5 5 5 8 9");
		testPositionOfLastFiveFromEnd (2, "0 1 2 5 5 5 5 5 5 8 9");
		testPositionOfLastFiveFromEnd (-5, "11 21 31 41");
		testPositionOfLastFiveFromEnd (-2, "11");
		testPositionOfLastFiveFromEnd (-1, "");
		
		testDeleteFirst ("21 31", "11 21 31");	
		testDeleteFirst ("21 11", "31 21 11");
		testDeleteFirst ("21", "11 21");	
		testDeleteFirst ("", "11");
		StdOut.println ("Finished tests");
	}
	

	/* ToString method to print */
	public String toString () { 
		// Use DecimalFormat #.### rather than String.format 0.3f to leave off trailing zeroes
		DecimalFormat format = new DecimalFormat ("#.###");
		StringBuilder result = new StringBuilder ("[ ");
		for (Node x = first; x != null; x = x.next) {
			result.append (format.format (x.item));
			result.append (" ");
		}
		result.append ("]");
		return result.toString ();
	}

	/* Method to create lists */
	public static MyLinked1 of(String s) {
		MyLinked1 result = new MyLinked1 ();
		if ("".equals (s)) return result;
		
		Node first = null;
		String[] nums = s.split (" ");
		for (int i = nums.length-1; i >= 0; i--) {
			try { 
				double num = Double.parseDouble (nums[i]); 
				first = new Node (num, first);      
			} catch (NumberFormatException e) {
				throw new IllegalArgumentException (String.format ("Bad argument \"%s\": could not parse \"%s\" as a double", s, nums[i]));
			}
		}
		result.first = first;
		return result;
	}

	// lots of copy and paste in these test!
	private static void testSizeLoop (int expected, String sList) {
		MyLinked1 list = MyLinked1.of (sList);
		String sStart = list.toString ();
		int actual = list.sizeLoop();
		if (expected != actual) {
			StdOut.format ("Failed %s.sizeLoop(): Expecting (%d) Actual (%d)\n", sStart, expected, actual);
		}
		String sEnd = list.toString ();
		if (! sStart.equals (sEnd)) {
			StdOut.format ("Failed %s.sizeLoop(): List changed to %s\n", sStart, sEnd);
		}
	}	
	private static void testSizeForward (int expected, String sList) {
		MyLinked1 list = MyLinked1.of (sList);
		String sStart = list.toString ();
		int actual = list.sizeForward ();
		if (expected != actual) {
			StdOut.format ("Failed %s.sizeForward(): Expecting (%d) Actual (%d)\n", sStart, expected, actual);
		}
		String sEnd = list.toString ();
		if (! sStart.equals (sEnd)) {
			StdOut.format ("Failed %s.sizeForward(): List changed to %s\n", sStart, sEnd);
		}
	}	
	private static void testSizeBackward (int expected, String sList) {
		MyLinked1 list = MyLinked1.of (sList);
		String sStart = list.toString ();
		int actual = list.sizeBackward ();
		if (expected != actual) {
			StdOut.format ("Failed %s.sizeBackward(): Expecting (%d) Actual (%d)\n", sStart, expected, actual);
		}
		String sEnd = list.toString ();
		if (! sStart.equals (sEnd)) {
			StdOut.format ("Failed %s.sizeBackward(): List changed to %s\n", sStart, sEnd);
		}
	}
	private static void testPositionOfFirstFiveFromBeginning (int expected, String sList) {
		MyLinked1 list = MyLinked1.of (sList);
		String sStart = list.toString ();
		int actual = list.positionOfFirstFiveFromBeginning ();
		//if ((expected >= 0 && expected != actual) || (expected < 0 && actual > 0)) {
		if (expected != actual) {
			StdOut.format ("Failed %s.positionOfFirstFiveFromBeginning(): Expecting (%d) Actual (%d)\n", sStart, expected, actual);
		}
		String sEnd = list.toString ();
		if (! sStart.equals (sEnd)) {
			StdOut.format ("Failed %s.positionOfFirstFiveFromBeginning(): List changed to %s\n", sStart, sEnd);
		}
	}
	private static void testPositionOfLastFiveFromEnd (int expected, String sList) {
		MyLinked1 list = MyLinked1.of (sList);
		String sStart = list.toString ();
		int actual = list.positionOfLastFiveFromEnd ();
		//if ((expected >= 0 && expected != actual) || (expected < 0 && actual > 0)) {
		if (expected != actual) {
			StdOut.format ("Failed %s.testPositionOfLastFiveFromEnd(): Expecting (%d) Actual (%d)\n", sStart, expected, actual);
		}
		String sEnd = list.toString ();
		if (! sStart.equals (sEnd)) {
			StdOut.format ("Failed %s.testPositionOfLastFiveFromEnd(): List changed to %s\n", sStart, sEnd);
		}
	}
	private static void testDeleteFirst (String expected, String sList) {
		String sExpected = MyLinked1.of (expected).toString ();
		MyLinked1 list = MyLinked1.of (sList);
		String sStart = list.toString ();
		list.deleteFirst ();
		String sEnd = list.toString ();
		if (! sExpected.equals (sEnd)) {
			StdOut.format ("Failed %s.deleteFirst(): Expecting %s Actual %s\n", sStart, sExpected, sEnd);
		}
	}	
}
