package algs13;
import stdlib.*;
public class XFixedCapacityStack<T> {
	private T[] a; // holds the items
	private int N; // number of items in stack

	@SuppressWarnings("unchecked")
	public XFixedCapacityStack (int capacity) {
		this.a = (T[]) new Object[capacity]; // no generic array creation
		this.N = 0;
	}
	public int size ()        { return N; }
	public boolean isEmpty () { return (N == 0); }
	public void push (T item) {
		if (item == null) throw new IllegalArgumentException ();
		if (N >= a.length) throw new RuntimeException ("Stack full");
		a[N] = item;
		N++;
	}
	public T pop () {
		if (N <= 0) throw new RuntimeException ("Stack empty");
		N--;
		T result = a[N];
		a[N] = null;
		return result;
	}

	public static void main (String[] args) {
		//Trace.showObjectIdsRedundantly (true);
		//Trace.showBuiltInObjects (true);
		//Trace.showBuiltInObjectsVerbose (true);
		Trace.drawStepsOfMethod ("main");
		Trace.run ();
		
		XFixedCapacityStack<Integer> s1 = new XFixedCapacityStack<> (5);
		XFixedCapacityStack<String> s2 = new XFixedCapacityStack<> (3);
		s1.push (11);
		s1.push (21);
		s1.push (31);

		//s2.push (41);
		s2.push ("duck");
		s2.push ("goose");
	}
}
