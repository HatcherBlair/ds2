package algs55; // section 5.5
import stdlib.*;
import algs52.TST;
/* ***********************************************************************
 *  Compilation:  javac LZW.java
 *  Execution:    java LZW - < input.txt   (compress)
 *  Execution:    java LZW + < input.txt   (expand)
 *  Dependencies: BinaryIn.java BinaryOut.java
 *
 *  Compress or expand binary input from standard input using LZW.
 *
 *  WARNING: STARTING WITH ORACLE JAVA 6, UPDATE 7 the SUBSTRING
 *  METHOD TAKES TIME AND SPACE LINEAR IN THE SIZE OF THE EXTRACTED
 *  SUBSTRING (INSTEAD OF CONSTANT SPACE AND TIME AS IN EARLIER
 *  IMPLEMENTATIONS).
 *
 *  See <a href = "http://java-performance.info/changes-to-string-java-1-7-0_06/">this article</a>
 *  for more details.
 *
 **************************************************************************/

public class LZW {
	private static BinaryIn binaryIn;
	private static BinaryOut binaryOut;

	private static final int R = 256;        // number of input chars
	private static final int L = 4096;       // number of codewords = 2^W
	private static final int W = 12;         // codeword width

	public static void compress() {
		String input = binaryIn.readString();
		TST<Integer> st = new TST<>();
		for (int i = 0; i < R; i++)
			st.put("" + (char) i, i);
		int code = R+1;  // R is codeword for EOF

		while (input.length() > 0) {
			String s = st.longestPrefixOf(input);  // Find max prefix match s.
			binaryOut.write(st.get(s), W);         // Print s's encoding.
			int t = s.length();
			if (t < input.length() && code < L)    // Add s to symbol table.
				st.put(input.substring(0, t + 1), code++);
			input = input.substring(t);            // Scan past s in input.
		}
		binaryOut.write(R, W);
		binaryOut.close();
	}


	public static void expand() {
		String[] st = new String[L];
		int i; // next available codeword value

		// initialize symbol table with all 1-character strings
		for (i = 0; i < R; i++)
			st[i] = "" + (char) i;
		st[i++] = "";                        // (unused) lookahead for EOF

		int codeword = binaryIn.readInt(W);
		String val = st[codeword];

		while (true) {
			binaryOut.write(val);
			codeword = binaryIn.readInt(W);
			if (codeword == R) break;
			String s = st[codeword];
			if (i == codeword) s = val + val.charAt(0);   // special case hack
			if (i < L) st[i++] = val + s.charAt(0);
			val = s;
		}
		binaryOut.close();
	}



	public static void main(String[] args) {
		String txtFile = "data/genomeTiny.txt";
		String binFile = "/tmp/genomeTiny.bin";
		//args = new String[] { "+" }; binaryIn = new BinaryIn(binFile); binaryOut = new BinaryOut();
		args = new String[] { "-" }; binaryIn = new BinaryIn(txtFile); binaryOut = new BinaryOut(binFile);
		if      (args[0].equals("-")) compress();
		else if (args[0].equals("+")) expand();
		else throw new Error("Illegal command line argument");
	}

}
