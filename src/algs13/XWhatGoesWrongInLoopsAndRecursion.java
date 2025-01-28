package algs13;
import stdlib.*;

@SuppressWarnings("all")
public class XWhatGoesWrongInLoopsAndRecursion {
	private Node first = null;
	static class Node {
		public Node (double item, Node next) { this.item = item; this.next = next; }
		double item;
		Node next;
	}

	/*
	 * numFives returns the number of fives in an array, written with a loop
	 */
	public static int numFives0 (double[] a) {
		int result = 0;
		for (int i=0; i<a.length; i++)
			if (a[i] == 5.0)
				result++;		
		return result;
	}

	public static int numFives1 (double[] a) {
		double[] list = new double[] { 4, 5, 6, 5, 3 };
		int result = 0;
		for (int i=0; i<list.length; i++)
			if (list[i] == 5.0)
				result++;		
		return result;
	}

	public static int numFives2 (double[] a) {
		int result = 0;
		for (int i=0; i<a.length; i++) {
			if (a[i] == 5.0)
				result++;	
			else
				return result;
			return result;
		}
		StdOut.print (result); 
		return 0;//added to remove compiler error
	}

	public static int numFives3 (double[] a) {
		int result = 0;
		for (int i=0; i<a.length; i++)
			if (a[i] == 5.0)
				result++;		
			else
				i++;
		return result;
	}

	public static int numFives4 (double[] a) {
		int result = 0;
		for (int i=0; i<a.length; i++)
			if (i == 5.0)
				result++;		
		return result;
	}

	public static int numFives5 (double[] a) {
		int result = 0;
		for (int i=0; i<a.length; i++)
			//if (a == 5.0)
			result++;		
		return result;
	}

	public static int numFives6 (double[] a) {
		int result = 0;
		for (int i=0; 0<a.length; i++)
			if (a[i] == 5.0)
				result++;		
		return result;
	}

	public static int numFives7 (double[] a) {
		int result = 0;
		for (int i=0; i>0; i++)
			if (a[i] == 5.0)
				result++;		
		return result;
	}

	/*
	 * numFours returns the number of fours in an array, written recursively
	 */
	public static int numFours0 (double[] a) {
		return numFours0 (a, 0, 0);
	}
	private static int numFours0 (double[] a, int i, int result) {
		if (i>a.length) 
			return result;
		if (a[i] == 4.0) 
			return numFours0 (a, i+1, result+1);
		else
			return numFours0 (a, i+1, result);
	}

	public static int numFours1 (double[] a) {
		return numFours1 (a, 0);
	}
	private static int numFours1 (double[] a, int i) {
		if (i>a.length) 
			return 0;
		if (a[i] == 4.0) 
			return 1 + numFours1 (a, i+1);
		else
			return numFours1 (a, i+1);
	}

	public static int numFours00 (double[] a) {
		return numFours00 (a, 0, 0);
	}
	private static int numFours00 (double[] a, int i, int result) {
		if (i>a.length) 
			return result;
		if (a[i] == 4.0) 
			result = result+1;
		result = numFours00 (a, i+1, result);
		return result;
	}

	public static int numFours11 (double[] a) {
		return numFours11 (a, 0);
	}
	private static int numFours11 (double[] a, int i) {
		if (i>a.length) 
			return 0;
		int result = numFours11 (a, i+1);
		if (a[i] == 4.0) 
			result = result+1;
		return result;
	}

	/*
	 * numThrees returns the number of threes in a linked list
	 */
	public int numThrees0 () {
		int result = 0;
		for (Node x = first; x != null; x = x.next) 
			if (x.item == 3.0)
				result = result + 1;
		return result;
	}
	public int numThrees00 () {
		Node x = first;
		int result = 0;
		while (x != null) {
			if (x.item == 3.0)
				result = result + 1;
			x = x.next; 
		}
		return result;
	}

	
	public int numThrees1 () {
		Node x = first;
		int result = 0;
		while (x != null) {
			if (x.item == 3.0)
				result = result + 1;
			x = x.next.next; 
		}
		return result;
	}	
	
	public int numThrees3 () {
		if (first == null) return 0;
		int result = 0;
		if (first.item == 3.0) 
			result = result + 1;
		Node x = first;
		while (x.next != null) {
			if (x.next.item == 3.0)
				result = result + 1;
			x.next = x.next.next; 
		}
		return result;
	}	
	public int numThrees2 () {
		if (first == null) return 0;
		int result = 0;
		if (first.item == 3.0) 
			result = result + 1;
		Node x = first;
		while (x.next != null) {
			if (x.next.item == 3.0)
				result = result + 1;
			x = x.next; 
		}
		return result;
	}
	public int numThrees4 () {
		if (first == null) return 0;
		int result = 0;
		if (first.item == 3.0) 
			result = result + 1;
		Node x = first;
		while (x.next != null) {
			if (x.item == 3.0)
				result = result + 1;
			x = x.next; 
		}
		return result;
	}	

	public int numThrees20 () {
		if (first.next == null) return 0;
		Node x = first;
		int result = 0;
		while (x.next != null) {
			if (x.item != 3.0) x = x.next;
			if (x.item == 3.0) {
				result = result + 1;
				x = x.next; 
			}
		}
		if (x.next == null && x.item == 3.0) {
			result = result + 1;
		}
		return result;
	}



	public static void main (String[] args) {
	}

}
