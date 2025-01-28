package algs12;

import stdlib.*;
public class XStrings {
	public static boolean isPalindrome (String s) {
		int N = s.length ();
		for (int i = 0; i < N / 2; i++)
			if (s.charAt (i) != s.charAt (N - 1 - i)) return false;
		return true;
	}

	public static boolean isSorted (String[] a) {
		for (int i = 1; i < a.length; i++) {
			if (a[i - 1].compareTo (a[i]) > 0) return false;
		}
		return true;
	}

	public static void printBaseAndExtension (String s) {
		// extract file name and extension from a command-line argument
		int dot = s.lastIndexOf (".");
		String base = s.substring (0, dot);
		String extension = s.substring (dot + 1, s.length ());
		StdOut.format ("base: %s\nextension: %s\n", base, extension);
	}

	public static void printLinesThatContain (String query) {
		// print all lines in standard input that contain a string specified on the command line
		while (!StdIn.isEmpty ()) {
			String s = StdIn.readLine ();
			if (s.contains (query)) StdOut.println (s);
		}
	}

	public static void printSplit () {
		// create an array of the strings on StdIn delimited by whitespace
		String input = StdIn.readAll ();
		String[] words = input.split ("\\s+");
		for (int i = 0; i < words.length; i++) {
			StdOut.println (words[i]);
		}
	}

	public static void main (String[] args) {
		//printBaseAndExtension ("the/place/for/pig.dog.cat");
		//StdIn.fromFile ("data/tale.txt");  printLinesThatContain ("children");
		StdIn.fromFile ("data/tinyTale.txt"); printSplit ();
	}
}
