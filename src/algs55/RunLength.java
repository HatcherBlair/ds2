package algs55; // section 5.5
import stdlib.*;
/* ***********************************************************************
 *  Compilation:  javac RunLength.java
 *  Execution:    java RunLength - < input.txt   (compress)
 *  Execution:    java RunLength + < input.txt   (expand)
 *  Dependencies: BinaryIn.java BinaryOut.java
 *
 *  Compress or expand binary input from standard input using
 *  run-length encoding.
 *
 *  % java BinaryDump 40 < 4runs.bin
 *  0000000000000001111111000000011111111111
 *  40 bits
 *
 *  This has runs of 15 0s, 7 1s, 7 0s, and 11 1s.
 *
 *  % java RunLength - < 4runs.bin | java HexDump
 *  0f 07 07 0b
 *  4 bytes
 *
 *************************************************************************/

public class RunLength {
	private static BinaryIn binaryIn;
	private static BinaryOut binaryOut;
	private static final int R   = 256;
	private static final int lgR = 8;

	public static void expand() {
		boolean b = false;
		while (!binaryIn.isEmpty()) {
			int run = binaryIn.readInt(lgR);
			for (int i = 0; i < run; i++)
				binaryOut.write(b);
			b = !b;
		}
		binaryOut.close();
	}

	public static void compress() {
		char run = 0;
		boolean old = false;
		while (!binaryIn.isEmpty()) {
			boolean b = binaryIn.readBoolean();
			if (b != old) {
				binaryOut.write(run, lgR);
				run = 1;
				old = !old;
			}
			else {
				if (run == R-1) {
					binaryOut.write(run, lgR);
					run = 0;
					binaryOut.write(run, lgR);
				}
				run++;
			}
		}
		binaryOut.write(run, lgR);
		binaryOut.close();
	}


	public static void main(String[] args) {
		String txtFile = "data/4runs.bin";
		String binFile = "4runsOut.bin";
		//args = new String[] { "+" }; binaryIn = new BinaryIn(binFile); binaryOut = new BinaryOut();
		args = new String[] { "-" }; binaryIn = new BinaryIn(txtFile); binaryOut = new BinaryOut(binFile);
		if      (args[0].equals("-")) compress();
		else if (args[0].equals("+")) expand();
		else throw new Error("Illegal command line argument");
	}

}
