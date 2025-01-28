package algs11;
import stdlib.*;

public class XSwap {
	public static void main (String[] args) {
		{
			int x = 42;
			int y = 27;
			StdOut.format ("before x=%d y=%d\n" , x, y);
			swap1 (x, y);
			StdOut.format ("after  x=%d y=%d\n\n" , x, y);
		}
		{
			Integer x = Integer.valueOf (42);
			Integer y = Integer.valueOf (27);
			StdOut.format ("before x=%d y=%d\n" , x, y);
			swap2 (x, y);
			StdOut.format ("after  x=%d y=%d\n\n" , x, y);
		}
		{
			int[] a = new int[2];
			a[0] = 42;
			a[1] = 27;
			StdOut.format ("before x=%d y=%d\n" , a[0], a[1]);
			swap3 (a, 0, 1);
			StdOut.format ("after  x=%d y=%d\n\n" , a[0], a[1]);
		}
		{
			Integer[] a = new Integer[2];
			a[0] = Integer.valueOf (42);
			a[1] = Integer.valueOf (27);
			StdOut.format ("before x=%d y=%d\n" , a[0], a[1]);
			swap4 (a, 0, 1);
			StdOut.format ("after  x=%d y=%d\n\n" , a[0], a[1]);
		}
	}
	static void swap1 (int a, int b) {
		int t = a;
		a = b;
		b = t;
	}
	static void swap2 (Integer a, Integer b) {
		Integer t = a;
		a = b;
		b = t;
	}
	static void swap3 (int[] a, int i, int j) {
		int t = a[i];
		a[i] = a[j];
		a[j] = t;
	}
	static void swap4 (Object[] a, int i, int j) {
		Object t = a[i];
		a[i] = a[j];
		a[j] = t;
	}
}
