package algs55; // section 5.5
import stdlib.*;
import algs51.Alphabet;
/* ***********************************************************************
 *  Compilation:  javac Genome.java
 *  Execution:    java Genome - < input.txt   (compress)
 *  Execution:    java Genome + < input.txt   (expand)
 *  Dependencies: BinaryIn.java BinaryOut.java
 *
 *  Compress or expand a genomic sequence using a 2-bit code.
 *
 *  % more genomeTiny.txt
 *  ATAGATGCATAGCGCATAGCTAGATGTGCTAGC
 *
 *  % java Genome - < genomeTiny.txt | java Genome +
 *  ATAGATGCATAGCGCATAGCTAGATGTGCTAGC
 *
 *************************************************************************/

public class Genome {
	private static BinaryIn binaryIn;
	private static BinaryOut binaryOut;

	public static void compress() {
		Alphabet DNA = new Alphabet("ACTG");
		String s = binaryIn.readString();
		int N = s.length();
		binaryOut.write(N);

		// Write two-bit code for char.
		for (int i = 0; i < N; i++) {
			int d = DNA.toIndex(s.charAt(i));
			binaryOut.write(d, 2);
		}
		binaryOut.close();
	}

	public static void expand() {
		Alphabet DNA = new Alphabet("ACTG");
		int N = binaryIn.readInt();
		// Read two bits; write char.
		for (int i = 0; i < N; i++) {
			char c = binaryIn.readChar(2);
			binaryOut.write(DNA.toChar(c), 8);
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
