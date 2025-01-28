package algs13;
import stdlib.*;
/* ***********************************************************************
 *  Compilation:  javac Server.java
 *  Execution:    java Server N
 *  Dependencies: Queue.java
 *
 *  Load balancing example.
 *
 *************************************************************************/

public class XServer {
	private final Queue<String> list = new Queue<>();      // list of users
	private int load;                                      // load

	// add a new user to the list
	public void add(String user) {
		list.enqueue(user);
		load++;
	}

	// string representation
	public String toString() {
		// String s = String.format("%5d:  ", load);
		String s = "";
		for (String user : list)
			s += user + " ";
		return s;
	}

	public static void main(String[] args) {
		int N = Integer.parseInt(args[0]);

		XServer[] servers = new XServer[N];
		for (int i = 0; i < N; i++)
			servers[i] = new XServer();

		// generate N random jobs and assign to a random processor
		for (int j = 0; j < N; j++) {
			String user = "user" + j;
			int i = StdRandom.uniform(N);
			servers[i].add(user);
		}

		// see how even the distribution is by printing out the
		// contents of each server
		for (int i = 0; i < N; i++)
			StdOut.println(i + ": " + servers[i]);
	}
}
