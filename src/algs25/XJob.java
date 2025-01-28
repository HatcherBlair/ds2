package algs25;
import stdlib.*;
/* ***********************************************************************
 *  Compilation:  javac Job.java
 *  Execution:    java Job

 *  A data type that implements a processing job.
 *
 *
 *************************************************************************/

public class XJob implements Comparable<XJob> {
	private final String name;
	private final double time;

	public XJob(String name, double time) {
		if (time < 0) throw new Error("Can't have negative processing time");
		this.name = name;
		this.time = time;
	}

	public double time() { return time; }

	public int compareTo(XJob that) {
		if      (this.time < that.time) return -1;
		else if (this.time > that.time) return +1;
		else return 0;
	}

	public String toString() {
		return String.format("%s %.1f", name, time);
	}

	public static void main(String[] args) {
		int N = Integer.parseInt(args[0]);
		StdOut.println(N);
		for (int i = 0; i < N; i++) {
			double time = StdRandom.uniform(1000);
			XJob job = new XJob("JOB" + i, time);
			StdOut.println(job);
		}
	}

}
