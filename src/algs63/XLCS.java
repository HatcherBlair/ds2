package algs63; // section 6.3
import stdlib.*;
/* ***********************************************************************
 *  Compilation:  javac LCS.java
 *  Execution:    java LCS file1.txt file2.txt
 *  Dependencies: In.java SuffixArray.java
 *
 *  Reads in two text strings, replaces all consecutive blocks of
 *  whitespace with a single space, and then computes the longest
 *  common substring.
 *
 *  Assumes that the character '\0' does not appear in either text.
 *
 *  % java LCS mobydick.txt tale.txt
 *  ' seemed on the point of being '
 *
 *************************************************************************/


public class XLCS {

	public static void main(String[] args) {
		In in1 = new In(args[0]);
		In in2 = new In(args[1]);
		String text1 = in1.readAll().replaceAll("\\s+", " ");
		String text2 = in2.readAll().replaceAll("\\s+", " ");
		//int N1 = text1.length();
		int N2 = text2.length();

		SuffixArray sa = new SuffixArray(text1 + "\0" + text2);
		int N = sa.length();

		String substring = "";
		for (int i = 1; i < N; i++) {

			// adjacent suffixes both from second text string
			if (sa.select(i).length() <= N2 && sa.select(i-1).length() <= N2) continue;

			// adjacent suffixes both from first text string
			if (sa.select(i).length() > N2+1 && sa.select(i-1).length() > N2+1) continue;

			// check if adjacent suffixes longer common substring
			int length = sa.lcp(i);
			if (length > substring.length())
				substring = sa.select(i).substring(0, length);
		}

		StdOut.println(substring.length());
		StdOut.println("'" + substring + "'");
	}
}
