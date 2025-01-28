package algs53; // section 5.3
import stdlib.*;
/* *************************************************************
 *  Compilation:  java KMPplus.java
 *  Execution:    java KMPplus pattern text
 *
 *  Knuth-Morris-Pratt algorithm over UNICODE alphabet.
 *
 *  % java KMPplus ABABAC BCBAABACAABABACAA
 *  text:    BCBAABACAABABACAA
 *  pattern:          ABABAC
 *
 *  % java KMPplus aabaaaba ccaabaabaabaaabaab
 *  text:    ccaabaabaabaaabaab
 *  pattern:         aabaaaba
 *
 *  % java KMPplus aabaaabb ccaabaabaabaaabaab
 *  text:    ccaabaabaabaaabaab
 *  pattern:                   aabaaabb
 *
 ***************************************************************/

public class XKMPplus {
	private final String pattern;
	private final int[] next;

	// create Knuth-Morris-Pratt NFA from pattern
	public XKMPplus(String pattern) {
		this.pattern = pattern;
		int M = pattern.length();
		next = new int[M];
		int j = -1;
		for (int i = 0; i < M; i++) {
			if (i == 0)                                      next[i] = -1;
			else if (pattern.charAt(i) != pattern.charAt(j)) next[i] = j;
			else                                             next[i] = next[j];
			while (j >= 0 && pattern.charAt(i) != pattern.charAt(j)) {
				j = next[j];
			}
			j++;
		}

		for (int i = 0; i < M; i++)
			StdOut.println("next[" + i + "] = " + next[i]);
	}

	// return offset of first occurrence of text in pattern (or N if no match)
	// simulate the NFA to find match
	public int search(String text) {
		int M = pattern.length();
		int N = text.length();
		int i, j;
		for (i = 0, j = 0; i < N && j < M; i++) {
			while (j >= 0 && text.charAt(i) != pattern.charAt(j))
				j = next[j];
			j++;
		}
		if (j == M) return i - M;
		return N;
	}


	// test client
	public static void main(String[] args) {
		//args = new String[] { "abracadabra", "abacadabrabracabracadabrabrabracad" };
		//args = new String[] { "rab",         "abacadabrabracabracadabrabrabracad" };
		//args = new String[] { "bcara",       "abacadabrabracabracadabrabrabracad" };
		//args = new String[] { "rabrabracad", "abacadabrabracabracadabrabrabracad" };
		args = new String[] { "abacad",      "abacadabrabracabracadabrabrabracad" };
		String pat = args[0];
		String txt = args[1];

		// substring search
		XKMPplus kmp = new XKMPplus(pat);
		int offset = kmp.search(txt);

		// print results
		StdOut.println("text:    " + txt);
		StdOut.print("pattern: ");
		for (int i = 0; i < offset; i++)
			StdOut.print(" ");
		StdOut.println(pat);
	}

}
