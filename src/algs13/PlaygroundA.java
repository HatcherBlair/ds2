package algs13;
import java.text.DecimalFormat;
import stdlib.*;
public class PlaygroundA {
	private double a[]; 
	private Node first;
	static class Node { 
		public double item; 
		public Node next; 
		public Node (double item, Node next) { 
			this.item = item; 
			this.next = next; 
		}
	}

	public int numFives () {
		return StdRandom.uniform (100); //TODO: fix this
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
	public static PlaygroundA of(String s) {
		PlaygroundA result = new PlaygroundA ();
		if ("".equals (s)) {
			// special case for the empty array
			result.first = null;
			result.a = new double [0];
		} else {
			String[] nums = s.split (" ");
			Node first = null;
			double[] a = new double[nums.length];
			for (int i = nums.length-1; i >= 0; i--) {
				double num = Double.NaN;
				try { 
					num = Double.parseDouble (nums[i]); 
				} catch (NumberFormatException e) { 
					throw new IllegalArgumentException (String.format ("Bad argument \"%s\": could not parse \"nums[i]\" as a double", s, nums[i]));
				}
				a[i] = num;
				first = new Node (num, first);  
			}
			result.a = a;
			result.first = first;
		} 
		return result;
	}

	public static void testNumFives (int expected, String sList) {
		PlaygroundA list = PlaygroundA.of (sList); 
		int actual = list.numFives ();
		if (expected != actual) {
			StdOut.format ("Failed: Expecting [%d] Actual [%d] with argument %s\n", expected, actual, list);
		}
	}
	public static void main (String[] args) {
		testNumFives (2, "11 5 5 21");
		testNumFives (0, "11 21 31 41");
		testNumFives (1, "11 21 5 31 41");
		testNumFives (1, "5 11 21 31 41");
		testNumFives (1, "11 21 31 41 5");
		testNumFives (5, "5 11 21 5 5 31 5 41 5");
		testNumFives (3, "5 5 5");
		testNumFives (1, "5");
		testNumFives (0, "11");
		testNumFives (0, "");
		StdOut.println ("Finished tests");
	}
	public static void main1 (String[] args) {
		Trace.drawStepsOfMethod ("numFives");
		Trace.drawStepsOfMethod ("numFivesH");
		Trace.run ();
		PlaygroundA list0 = PlaygroundA.of ("5");
		PlaygroundA list1 = PlaygroundA.of ("5 11 5 5");
		int result = list1.numFives ();
		StdOut.println ("result: " + result);
	}
}
