package algs13;

import java.util.Iterator;
import stdlib.*;

public class XIteratorClient {
	public static int sum1 (Stack<Integer> a) {
		int result = 0;
		Iterator<Integer> it = a.iterator ();
		while (it.hasNext ()) {
			int item = it.next ();
			result += item;
		}
		return result;
	}
	public static int sum2 (Stack<Integer> a) {
		int result = 0;
		for (int item : a) {
			result += item;
		}
		return result;
	}
	public static void main (String[] args) {
		Stack<Integer> a = new Stack<> ();
		a.push (-1);
		a.push (-2);
		a.push (-3);
		StdOut.println (sum1(a));

	}

}
