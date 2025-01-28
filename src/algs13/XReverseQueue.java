package algs13;
import stdlib.*;
public class XReverseQueue {
	public static void main(String[] args) {
		Trace.drawStepsOfMethod ("main");
		Trace.run ();
		Queue<String> q = new Queue<> ();
		q.enqueue ("a");
		q.enqueue ("b");
		q.enqueue ("c");
		q.enqueue ("d");
		Stack<String> stack = new Stack<>();

		while (!q.isEmpty())
			stack.push(q.dequeue());
		while (!stack.isEmpty())
			q.enqueue(stack.pop());
		
		// another version with temporaries:
//		while (!q.isEmpty()) {
//			String x = q.dequeue ();
//			stack.push(x);
//		}
//		while (!stack.isEmpty()) {
//			String x = stack.pop ();
//			q.enqueue(x);
//		}
	}
}
