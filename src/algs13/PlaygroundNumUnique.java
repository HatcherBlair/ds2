package algs13;
import java.text.DecimalFormat;
import stdlib.*;
public class PlaygroundNumUnique {
	private Node first;
	static class Node { 
		public double item; 
		public Node next; 
		public Node (double item, Node next) { 
			this.item = item; 
			this.next = next; 
		}
	}
	
	/** Number of unique items, assuming the list is sorted */
	public int numUnique() {
		return StdRandom.uniform (100); //TODO: fix this
	}

	public static void main1 (String[] args) {
		Trace.showBuiltInObjects(true);
		Trace.drawStepsOfMethod ("numUnique");
		Trace.run ();
		PlaygroundNumUnique list1 = PlaygroundNumUnique.of ("11 21 21 21 31");
		int result1 = list1.numUnique ();
		Trace.draw();
		StdOut.println ("result: " + result1);
	}

	public static void main (String[] args) {
		testNumUnique(4, "11 21 21 21 31 41 41 41 41");
		testNumUnique(1, "11 11 11 11");
		testNumUnique(4, "11 21 31 41");
		testNumUnique(4, "11 11 11 21 31 31 31 31 41");
		testNumUnique(4, "11 11 21 21 21 31 31 41 41 41 41");
		testNumUnique(8, "11 11 11 11 21 31 41 41 41 41 41 51 51 61 71 81 81");
		testNumUnique(8, "11 21 31 41 41 41 41 41 51 51 61 71 81");
		testNumUnique(7, "11 11 11 11 21 31 41 41 41 41 41 51 51 61 71");
		testNumUnique(7, "11 21 31 41 41 41 41 41 51 51 61 71");
		testNumUnique(8, "-81 -81 -81 -81 -71 -61 -51 -51 -51 -51 -41 -41 -31 -21 -11 -11 -11");
		testNumUnique(3, "-11 -11 -11 0 0 11 11 11");
		testNumUnique(2, "0 11 11 11");
		testNumUnique(2, "-Infinity 11 11 11");
		testNumUnique(2, "11 11 11 Infinity");
		testNumUnique(1, "11 11");
		testNumUnique(1, "11");
		testNumUnique(0, "");
		
		StdOut.println ("Finished tests");
	}

	private static void testNumUnique (int expected, String sList) {
		PlaygroundNumUnique list = PlaygroundNumUnique.of (sList); 
		String sStart = list.toString ();
		int actual = list.numUnique ();
		if (expected != actual) {
			StdOut.format ("Failed: Expecting [%d] Actual [%d] with argument %s\n", expected, actual, list);
		}
		String sEnd = list.toString ();
		if (! sStart.equals (sEnd)) {
			StdOut.format ("Failed %s.numUnique(): List changed to %s\n", sStart, sEnd);
		}
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
	public static PlaygroundNumUnique of(String s) {
		PlaygroundNumUnique result = new PlaygroundNumUnique ();
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
}
