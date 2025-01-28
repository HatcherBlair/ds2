package algs54; // section 5.4
import stdlib.*;
/* ***********************************************************************
 *  Compilation:  javac Validate.java
 *  Execution:    java Validate pattern text
 *
 *  Reads in a regular expression and a text input string from the
 *  command line and prints true or false depending on whether
 *  the pattern matches the text.
 *
 *  % java Validate "..oo..oo." bloodroot
 *  true
 *
 *  % java Validate "..oo..oo." nincompoophood
 *  false
 *
 *  % java Validate "[^aeiou]{6}" rhythm
 *  true

 *  % java Validate "[^aeiou]{6}" rhythms
 *  false
 *
 *************************************************************************/

public class XValidate {

	public static void main(String[] args) {
		//args = new String[] { "..oo..oo.", "bloodroot" };
		//args = new String[] { "..oo..oo.", "nincompoophood" };
		//args = new String[] { "[^aeiou]{6}", "rhythm" };
		args = new String[] { "[^aeiou]{6}", "rhythms" };

		String regexp = args[0];
		String text   = args[1];
		StdOut.println(text.matches(regexp));
	}

}
