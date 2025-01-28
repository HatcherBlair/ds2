package algs13;
import stdlib.*;

public class XResizingArrayStackOfStrings {
	private String[] a;   // array of items
	private int N = 0;    // number of elements on stack
	// Invariant (after the constructor):
	//   a[0]..a[N-1] are non null
	//   a[N]..a[a.length-1] are null
	
	// create an empty stack
	public XResizingArrayStackOfStrings(long N) {
		int n = (int) N;
		a = new String[n];
	}

	public boolean isEmpty() { return N == 0; }
	public int size()        { return N;      }

	// resize the underlying array holding the elements
	private void resize(int capacity) {
		String[] temp = new String[capacity];
		for (int i = 0; i < N; i++)
			temp[i] = a[i];
		a = temp;
	}

	// push a new item onto the stack
	public void push(String item) {
		if (N == a.length) resize(2*a.length);
		a[N] = item;
		N++;
	}

	// delete and return the item most recently added
	public String pop() {
		if (isEmpty()) { throw new Error("Stack underflow error"); }
		N--;
		String item = a[N];
		a[N] = null;  // to avoid loitering
		if (N > 0 && N <= a.length/4) {
			// StdOut.println("shrinking");
			resize(a.length/2); // shrink size of array if necessary
		}
		return item;
	}

	private static void f(long N, XResizingArrayStackOfStrings s){
		for (long i = 0; i < N; i++) {
			s.push("b"); 
			s.pop();
		}
	}

	public static double timeTrial(long N) {
		long T = 10; // number of tests
		double sum = 0;
		for (long t = 0; t < T; t++) {
			XResizingArrayStackOfStrings s = new XResizingArrayStackOfStrings(N);
			for (long i = 0; i < N; i++) {
				s.push("a");
			}
			Stopwatch st = new Stopwatch();
			f(N,s);  
			sum +=  st.elapsedTime();
		}
		return sum/T;
	}

	private static final long MIN = 1000;
	// private static final long MAX = 40960L;
	private static final long MAX = Long.MAX_VALUE/2;
	public static void main(String[] args) {
		double prev = timeTrial(MIN);
		for (long N = MIN*2; N<=MAX; N += N) {
			double time = timeTrial(N);
			StdOut.format("%19d %9.3f %5.1f\n", N, time, time/prev);
			prev = time;
		}
	}

	/* *********************************************************************
	 * Test routine.
	 **********************************************************************/
	public static void main2(String[] args) {
		Trace.drawStepsOfMethod ("resize");
		Trace.run ();
		StdIn.fromString ("to be or not to - be - - that - - - is");

		XResizingArrayStackOfStrings s = new XResizingArrayStackOfStrings(0);
		while (!StdIn.isEmpty()) {
			String item = StdIn.readString();
			if (!item.equals("-")) s.push(item);
			else if (!s.isEmpty()) StdOut.print(s.pop() + " ");
		}
		StdOut.println("(" + s.size() + " left on stack)");
	}
}
