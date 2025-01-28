package algs25;
import stdlib.*;
import java.util.Arrays;
/* ***********************************************************************
 *  Compilation:  javac Rhymer.java StdIn.java
 *  Execution:    java Rhymer < data.txt
 *
 *  Reads in a sequence of words.
 *
 *************************************************************************/

public class XRhymer {


	private static String reverse(String s) {
		StringBuffer sb = new StringBuffer(s);
		sb.reverse();
		return new String(sb);
	}


	public static void main(String[] args) {
		String input = StdIn.readAll();
		String[] strings = input.split("\\s+");

		// reverse order of letters in each word
		for (int i = 0; i < strings.length; i++)
			strings[i] = reverse(strings[i]);

		// sort the words
		Arrays.sort(strings);

		// reverse order of letters in each word
		for (int i = 0; i < strings.length; i++)
			strings[i] = reverse(strings[i]);

		// print words
		for (String string : strings)
			StdOut.println(string);
	}
}
