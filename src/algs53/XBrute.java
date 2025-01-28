package algs53; // section 5.3
import stdlib.*;
/* *************************************************************
 *
 *  Compilation:  javac Brtue.java
 *  Execution:    java Brute pattern text
 *
 *  Reads in two strings, the pattern and the input text, and
 *  searches for the pattern in the input text using brute force.
 *
 *  % java Brute abracadabra abacadabrabracabracadabrabrabracad
 *  text:    abacadabrabracabracadabrabrabracad
 *  pattern:               abracadabra
 *
 *  % java Brute rab abacadabrabracabracadabrabrabracad
 *  text:    abacadabrabracabracadabrabrabracad
 *  pattern:         rab
 *
 *  % java Brute rabrabracad abacadabrabracabracadabrabrabracad
 *  text:    abacadabrabracabracadabrabrabracad
 *  pattern:                        rabrabracad

 *
 *  % java Brute bcara abacadabrabracabracadabrabrabracad
 *  text:    abacadabrabracabracadabrabrabracad
 *  pattern:                                   bcara
 *
 *  % java Brute abacad abacadabrabracabracadabrabrabracad
 *  text:    abacadabrabracabracadabrabrabracad
 *  pattern: abacad
 *
 ***************************************************************/

public class XBrute {

	/* *************************************************************************
	 *  String versions
	 ***************************************************************************/

	// return offset of first match or N if no match
	public static int search1(String pat, String txt) {
		int M = pat.length();
		int N = txt.length();

		for (int i = 0; i <= N - M; i++) {
			int j;
			for (j = 0; j < M; j++) {
				if (txt.charAt(i+j) != pat.charAt(j))
					break;
			}
			if (j == M) return i;            // found at offset i
		}
		return N;                            // not found
	}

	// return offset of first match or N if no match
	public static int search2(String pat, String txt) {
		int M = pat.length();
		int N = txt.length();
		int i, j;
		for (i = 0, j = 0; i < N && j < M; i++) {
			if (txt.charAt(i) == pat.charAt(j)) j++;
			else { i -= j; j = 0;  }
		}
		if (j == M) return i - M;    // found
		else        return N;        // not found
	}


	/* *************************************************************************
	 *  char[] array versions
	 ***************************************************************************/

	// return offset of first match or N if no match
	public static int search1(char[] pattern, char[] text) {
		int M = pattern.length;
		int N = text.length;

		for (int i = 0; i <= N - M; i++) {
			int j;
			for (j = 0; j < M; j++) {
				if (text[i+j] != pattern[j])
					break;
			}
			if (j == M) return i;            // found at offset i
		}
		return N;                            // not found
	}

	// return offset of first match or N if no match
	public static int search2(char[] pattern, char[] text) {
		int M = pattern.length;
		int N = text.length;
		int i, j;
		for (i = 0, j = 0; i < N && j < M; i++) {
			if (text[i] == pattern[j]) j++;
			else { i -= j; j = 0;  }
		}
		if (j == M) return i - M;    // found
		else        return N;        // not found
	}


	// test client
	public static void main(String[] args) {
		//args = new String[] { "abracadabra", "abacadabrabracabracadabrabrabracad" };
		//args = new String[] { "rab",         "abacadabrabracabracadabrabrabracad" };
		//args = new String[] { "bcara",       "abacadabrabracabracadabrabrabracad" };
		//args = new String[] { "rabrabracad", "abacadabrabracabracadabrabrabracad" };
		args = new String[] { "abacad",      "abacadabrabracabracadabrabrabracad" };
		String pat1 = args[0];
		String txt1 = args[1];
		char[] pat2 = pat1.toCharArray();
		char[] txt2 = txt1.toCharArray();

		int offset1a = search1(pat1, txt1);
		int offset2a = search2(pat1, txt1);
		int offset1b = search1(pat2, txt2);
		int offset2b = search2(pat2, txt2);

		// print results
		StdOut.println("text:    " + txt1);

		// from brute force search method 1a
		StdOut.print("pattern: ");
		for (int i = 0; i < offset1a; i++)
			StdOut.print(" ");
		StdOut.println(pat1);

		// from brute force search method 2a
		StdOut.print("pattern: ");
		for (int i = 0; i < offset2a; i++)
			StdOut.print(" ");
		StdOut.println(pat2);

		// from brute force search method 1b
		StdOut.print("pattern: ");
		for (int i = 0; i < offset1b; i++)
			StdOut.print(" ");
		StdOut.println(pat1);

		// from brute force search method 2b
		StdOut.print("pattern: ");
		for (int i = 0; i < offset2b; i++)
			StdOut.print(" ");
		StdOut.println(pat2);
	}
}
