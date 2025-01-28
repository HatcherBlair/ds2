package algs12;
import stdlib.*;
/* ***********************************************************************
 *  Compilation:  javac Rolls.java
 *  Execution:    java Rolls N
 *  Dependencies: Counter.java StdRandom.java StdOut.java
 *
 *  % java Rolls 1000000
 *  167308 1's
 *  166540 2's
 *  166087 3's
 *  167051 4's
 *  166422 5's
 *  166592 6's
 *
 *************************************************************************/

public class XRolls {
	public static void main(String[] args) {
		args = new String[] { "1000000" };

		int T = Integer.parseInt(args[0]);
		int SIDES = 6;

		// initialize counters
		Counter[] rolls = new Counter[SIDES+1];
		for (int i = 1; i <= SIDES; i++) {
			rolls[i] = new Counter(i+1 + "'s");
		}

		// flip dice
		for (int t = 0; t < T; t++) {
			int result = StdRandom.uniform(1, SIDES+1);
			rolls[result].increment();
		}

		// print results
		for (int i = 1; i <= SIDES; i++) {
			StdOut.println(rolls[i]);
		}
	}
}

