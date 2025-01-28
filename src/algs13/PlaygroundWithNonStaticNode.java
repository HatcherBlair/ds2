package algs13;
import java.text.DecimalFormat;
import stdlib.*;
public class PlaygroundWithNonStaticNode {
	private Node first;
	class Node { 
		public double item; 
		public Node next; 
		public Node (double item, Node next) { 
			this.item = item; 
			this.next = next; 
		}
	}

	public static void main (String[] args) {
		Trace.showBuiltInObjects(true);
		Trace.run ();
		PlaygroundWithNonStaticNode list1 = PlaygroundWithNonStaticNode.of ("5 11 5 5");
		PlaygroundWithNonStaticNode list2 = PlaygroundWithNonStaticNode.of ("24 35 67");
		Trace.draw();
	}	

	/* A silly method to show list creation */
	public PlaygroundWithNonStaticNode example(int i) {
		Node x1 = new Node (i+10, null);
		Node x2 = new Node (i+20, null);
		Node x3 = new Node (i+30, null);
		Node x4 = new Node (i+40, null);
		PlaygroundWithNonStaticNode result = new PlaygroundWithNonStaticNode ();
		result.first = x1;
		x1.next = x2;
		x2.next = x3;
		x3.next = x4;
		return result;
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
	public static PlaygroundWithNonStaticNode of(String s) {
		PlaygroundWithNonStaticNode result = new PlaygroundWithNonStaticNode ();
		if ("".equals (s)) return result;

		Node first = null;
		String[] nums = s.split (" ");
		for (int i = nums.length-1; i >= 0; i--) {
			try { 
				double num = Double.parseDouble (nums[i]); 
				first = result.new Node (num, first);      
			} catch (NumberFormatException e) {
				throw new IllegalArgumentException (String.format ("Bad argument \"%s\": could not parse \"%s\" as a double", s, nums[i]));
			}
		}
		result.first = first;
		return result;
	}

}
