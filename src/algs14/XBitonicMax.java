package algs14;
import java.util.Arrays;
import stdlib.*;
/* ***********************************************************************
 *  Compilation:  javac BitonicMax.java
 *  Execution:    java BitonicMax N
 *
 *  Find the maximum in a bitonic array (strictly increasing, then strictly
 *  decreasing) of size N in log N time.
 *
 *  % java BitonicMax N
 *
 *************************************************************************/

public class XBitonicMax {

	// create a bitonic array of size N.  First element is always 0
	public static int[] bitonic(int N) {
		if (N < 3) throw new IllegalArgumentException();

		int[] a = new int[N];
		int mid = StdRandom.uniform(N);

		for (int i = 1;   i < mid; i++) a[i] = a[i-1]   + StdRandom.uniform(9) + 1;  // increasing
		if (mid != 0)                 a[mid] = a[mid-1] + StdRandom.uniform(10) - 5; // increasing or decreasing
		for (int i = mid+1; i < N; i++) a[i] = a[i-1]   - StdRandom.uniform(9) - 1;  // decreasing
		return a;
	}

	// find the index of the maximum in a bitonic subarray a[lo..hi]
	public static int max(int[] a, int lo, int hi) {
		if (hi == lo) return hi;
		int mid = lo + (hi - lo) / 2;
		if (a[mid] < a[mid + 1]) return max(a, mid+1, hi);
		if (a[mid] > a[mid + 1]) return max(a, lo, mid);
		else return mid;
	}



	public static void main(String[] args) {
		args = new String[] { "3" };
		int N = Integer.parseInt(args[0]);
		int[] a = bitonic(N);
		StdOut.println("a = " + Arrays.toString (a));
		StdOut.println("max = " + a[max(a, 0, N-1)]);
	}
}

