package algs35;
import stdlib.*;
/* ***********************************************************************
 *  Compilation:  javac Concordance.java
 *  Execution:    java Concordance < input.txt
 *  Dependencies: ST.java SET.java In.java StdIn.java Out.java
 *
 *  % java Concordance tale.txt
 *  cities
 *  tongues of the two *cities* that were blended in
 *
 *  majesty
 *  their turnkeys and the *majesty* of the law fired
 *  me treason against the *majesty* of the people in
 *  of his most gracious *majesty* king george the third
 *
 *  princeton
 *
 *************************************************************************/

public class XConcordance {

	public static void main(String[] args) {
		args = new String[] { "data/tale.txt" };
		StdIn.fromString ("cities majesty");

		int CONTEXT = 5;

		In in = new In(args[0]);
		String[] words = in.readAll().split("\\s+");
		ST<String, SET<Integer>> st = new ST<>();

		// build up concordance
		for (int i = 0; i < words.length; i++) {
			String s = words[i];
			if (!st.contains(s)) {
				st.put(s, new SET<>());
			}
			SET<Integer> set = st.get(s);
			set.add(i);
		}
		StdOut.println("Finished building concordance");

		// process queries
		while (!StdIn.isEmpty()) {
			String query = StdIn.readString();
			SET<Integer> set = st.get(query);
			if (set == null) set = new SET<>();
			for (int k : set) {
				for (int i = Math.max(0, k - CONTEXT + 1); i < k; i++)
					StdOut.print(words[i] + " ");
				StdOut.print("*" + words[k] + "* ");
				for (int i = k + 1; i < Math.min(k + CONTEXT, words.length); i++)
					StdOut.print(words[i] + " ");
				StdOut.println();
			}
			StdOut.println();
		}

	}
}
