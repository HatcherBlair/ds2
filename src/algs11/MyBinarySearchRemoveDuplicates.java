// Exercise 1.1.28
package algs11;
import stdlib.*;
import java.util.Arrays;

/*
   1.1.28 Remove duplicates.
   Modify the test client in BinarySearch to remove any duplicate keys in the whitelist after the sort.

   Hint: For this problem, you will need to create an second array that is
   smaller than the original whitelist.  You need to go through the
   original whitelist twice.  The first time, you need to determine the number
   of unique items in the whitelist; use this as the size of the second array,
   which you create.  The second time, copy the unique items to the new
   array.
 */
public class MyBinarySearchRemoveDuplicates {

	// precondition: array a[] is sorted
	public static int rank (int key, int[] a) {
		int lo = 0;
		int hi = a.length - 1;
		while (lo <= hi) {
			// key is in a[lo..hi] or not present.
			int mid = lo + (hi - lo) / 2;
			if (key < a[mid]) hi = mid - 1;
			else if (key > a[mid]) lo = mid + 1;
			else return mid;
		}
		return -1;
	}

	// precondition: array a[] is sorted
	public static int[] removeDuplicates (int[] a) {
		int N = a.length;
		if (N < 1) return a;

		// determine number of unique elements
		// TODO

		// copy from a to new array
		// TODO
		return null;
	}

	public static void printRemoveDuplicates (int[] a) {
		//StdOut.println (Arrays.toString (a));
		StdOut.println (Arrays.toString (removeDuplicates (a)));
	}

	public static void testRemoveDuplicates () {
		printRemoveDuplicates (new int[] {});
		printRemoveDuplicates (new int[] {10});
		printRemoveDuplicates (new int[] {10, 10});
		printRemoveDuplicates (new int[] {10, 20});
		printRemoveDuplicates (new int[] {10, 10, 20, 30});
		printRemoveDuplicates (new int[] {10, 10, 10, 20, 30});
		printRemoveDuplicates (new int[] {10, 20, 20, 30});
		printRemoveDuplicates (new int[] {10, 20, 20, 20, 30});
		printRemoveDuplicates (new int[] {10, 20, 30, 30});
		printRemoveDuplicates (new int[] {10, 20, 30, 30, 30});
		printRemoveDuplicates (new int[] {10, 10, 10, 10, 10, 10, 20, 20, 20, 30});
	}

	public static void main (String[] args) {
		testRemoveDuplicates ();
		//        args = new String[] { "data/tinyW.txt" };
		//        StdIn.fromFile ("data/tinyT.txt");
		//        int[] whitelist = new In (args[0]).readAllInts();
		//
		//        Arrays.sort (whitelist);
		//        whitelist = removeDuplicates (whitelist);
		//
		//        // read key; print if not in whitelist
		//        while (!StdIn.isEmpty ()) {
		//            int key = StdIn.readInt ();
		//            if (BinarySearch.rank (key, whitelist) == -1) StdOut.println (key);
		//        }
	}
}
