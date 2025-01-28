package algs25;
//import stdlib.*;
import algs13.Queue;

/* ***********************************************************************
 *  Compilation:  javac Processor.java
 *  Execution:    java Processor
 *
 *  XProcessor data type represents a processor with a list of
 *  jobs, each of integer length. Its load is the total amount
 *  of processing time.
 *
 *  The client program finds an approximate solution to the load balancing
 *  problem using the LPT (longest processing time first) rule.
 *
 *************************************************************************/

public class XProcessor implements Comparable<XProcessor> {
	private double load = 0;
	private final Queue<XJob> list = new Queue<>();

	public void add(XJob job) {
		list.enqueue(job);
		load += job.time();
	}

	public int compareTo(XProcessor that) {
		if (this.load < that.load) return -1;
		if (this.load > that.load) return +1;
		return 0;
	}

	public String toString() {
		String s = load + ": ";
		for (XJob x : list)
			s += x + " ";
		return s;
	}

}
