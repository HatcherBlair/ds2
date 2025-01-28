package algs33;
import stdlib.*;
/* ***********************************************************************
 *  Compilation:  javac RandomizedQueue.java
 *  Execution:    java RandomizedQueue
 *
 *  Implement a randomized queue in log N time per operation in the
 *  worst case.
 *
 *************************************************************************/

public class XRandomizedQueue<T> {

	private RedBlackBST<Integer, T> st = new RedBlackBST<>();

	public XRandomizedQueue() { }

	// add the item to the randomized queue
	public void enqueue(T item) {
		int N = st.size();
		int r = StdRandom.uniform(N+1);
		// r is between 0 and N, inclusive
		// initially N == r == 0
		//   st.get returns null if item not there
		//   so, initially, the next line does nothing
		st.put(N, st.get(r));
		st.put(r, item);
		// StdOut.format ("N=%d r=%d\n", N, r);
	}

	// delete and return a random item from the queue
	public T dequeue() {
		int N = st.size();
		if (N == 0) throw new Error("Randomized queue underflow");
		T item = st.get(N-1);
		st.delete(N-1);
		return item;
	}


	/* ***********************************************************************
	 *  Test client
	 *************************************************************************/
	public static void main(String[] args) {
		args = new String[] { "10000", "20" };
		int N = Integer.parseInt(args[0]);
		int k = Integer.parseInt(args[1]);
		XRandomizedQueue<Integer> queue = new XRandomizedQueue<>();
		for (int i = 0; i < N; i++)
			queue.enqueue(i);

		for (int i = 0; i < k; i++)
			StdOut.println(queue.dequeue());
	}
}
