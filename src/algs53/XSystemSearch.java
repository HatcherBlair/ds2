package algs53; // section 5.3
import stdlib.*;
/* *************************************************************
 *
 *  Compilation:  % java System.java
 *  Execution:    % java System n
 *
 *  Search for the string a^N b in the string  a^2N
 *  where N = 2^n.
 *
 ***************************************************************/

public class XSystemSearch {


	public static void main(String[] args) {
		args = new String[] { "10" };
		int n = Integer.parseInt(args[0]);
		String txt  = "a";
		String pat = "a";
		for (int i = 0; i < n; i++) {
			txt  = txt  + txt;
			pat = pat + pat;
		}
		txt = txt + txt;
		pat = pat + "b";
		//        StdOut.println(text);
		//        StdOut.println(query);
		StdOut.println(txt.indexOf(pat));
	}
}
