package algs12;
import stdlib.*;
/* ***********************************************************************
 *  Compilation:  javac Flips.java
 *  Execution:    java Flips N
 *  Dependencies: Counter.java StdRandom.java StdOut.java
 *
 *  % java FLips 10
 *  5 heads
 *  5 tails
 *  delta: 0
 *
 *  % java Flips 10
 *  8 heads
 *  2 tails
 *  delta: 6
 *
 *  % java Flips 1000000
 *  499710 heads
 *  500290 tails
 *  delta: 580
 *
 *************************************************************************/

public class XFlips {
	public static void main(String[] args) {
		args = new String[] { "1000000" };

		int T = Integer.parseInt(args[0]);
		Counter heads = new Counter("heads");
		Counter tails = new Counter("tails");
		for (int t = 0; t < T; t++) {
			if (StdRandom.bernoulli(0.5)) heads.increment();
			else                          tails.increment();
		}
		StdOut.println(heads);
		StdOut.println(tails);
		int delta = heads.tally() - tails.tally();
		StdOut.println("delta: " + Math.abs(delta));
	}
}
