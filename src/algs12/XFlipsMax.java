package algs12;
import stdlib.*;
/* ***********************************************************************
 *  Compilation:  javac FlipsMax.java
 *  Execution:    java FlipsMax N
 *  Dependencies: Counter.java StdRandom.java StdOut.java
 *
 *  A static method that takes two objects as arguments and returns
 *  an object.
 *
 *  % java FlipsMax 1000000
 *  500281 tails wins
 *
 *************************************************************************/

public class XFlipsMax {

	public static Counter max(Counter x, Counter y) {
		if (x.tally() > y.tally()) return x;
		else return y;
	}

	public static void main(String[] args) {
		args = new String[] { "1000000" };

		int T = Integer.parseInt(args[0]);
		Counter heads = new Counter("heads");
		Counter tails = new Counter("tails");
		for (int t = 0; t < T; t++) {
			if (StdRandom.bernoulli(0.5)) heads.increment();
			else                          tails.increment();
		}

		if (heads.tally() == tails.tally())
			StdOut.println("Tie");
		else
			StdOut.println(max(heads, tails) + " wins");
	}
}

