package algs51; // section 5.0
import stdlib.*;
/* ***********************************************************************
 *  Compilation:  javac Subsequence.java
 *  Execution:    java Subsequence s t
 *
 *  Determines whether string s is a subsequence of string t.
 *
 *************************************************************************/


public class XSubsequence {
	public static boolean isXSubsequence(String s, String t) {
		int M = s.length();
		int N = t.length();

		int i = 0;
		for (int j = 0; j < N; j++) {
			if (s.charAt(i) == t.charAt(j)) i++;
			if (i == M) return true;
		}
		return false;
	}

	public static void main(String[] args) {
		String s = args[0];
		String t = args[1];
		StdOut.println(isXSubsequence(s, t));
	}

}
