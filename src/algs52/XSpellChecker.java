package algs52; // section 5.2
import stdlib.*;
import algs35.SET;
/* ***********************************************************************
 *  Compilation:  javac SpellChecker.java
 *  Execution:    java SpellChecker words.txt
 *  Dependencies: StringSET.java In.java
 *
 *  Read in a dictionary of words from the file words.txt, and print
 *  out any misspelled words that appear on standard input.
 *
 *************************************************************************/

public class XSpellChecker {

	public static void main(String[] args) {
		SET<String> dictionary = new SET<>();

		// read in dictionary of words
		In dict = new In(args[0]);
		while (!dict.isEmpty()) {
			String word = dict.readString();
			dictionary.add(word);
		}
		StdOut.println("Done reading dictionary");

		// read strings from standard input and print out if not in dictionary
		StdOut.println("Enter words, and I'll print out the misspelled ones");
		while (!StdIn.isEmpty()) {
			String word = StdIn.readString();
			if (!dictionary.contains(word)) StdOut.println(word);
		}
	}
}
