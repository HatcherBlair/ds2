package algs55; // section 5.5
import stdlib.*;
/* ***********************************************************************
 *  Compilation:  javac BinaryDump.java
 *  Execution:    java BinaryDump N < file
 *  Dependencies: BinaryIn.java
 *  Data file:    http://introcs.cs.princeton.edu/stdlib/abra.txt
 *
 *  Reads in a binary file and writes out the bits, N per line.
 *
 *  % more abra.txt
 *  ABRACADABRA!
 *
 *  % java BinaryDump 16 < abra.txt
 *  0100000101000010
 *  0101001001000001
 *  0100001101000001
 *  0100010001000001
 *  0100001001010010
 *  0100000100100001
 *  96 bits
 *
 *************************************************************************/

public class BinaryDump {
	// Note: Code from textbook uses BinaryStdIn. I've refactored it to use BinaryIn.
	// The BinaryIn/BinaryOut code is not very well tested, so if you find problems
	// with the code in this section, you should look for bugs in those classes first.
	private static BinaryIn binaryIn;

	public static void main(String[] args) {
		binaryIn = new BinaryIn ("/tmp/abra.bin");
		args = new String[] { "60" };

		int BITS_PER_LINE = 16;
		if (args.length == 1) {
			BITS_PER_LINE = Integer.parseInt(args[0]);
		}

		int count;
		for (count = 0; !binaryIn.isEmpty(); count++) {
			if (BITS_PER_LINE == 0) { binaryIn.readBoolean(); continue; }
			else if (count != 0 && count % BITS_PER_LINE == 0) StdOut.println();
			if (binaryIn.readBoolean()) StdOut.print(1);
			else                           StdOut.print(0);
		}
		if (BITS_PER_LINE != 0) StdOut.println();
		StdOut.println(count + " bits");
	}
}
