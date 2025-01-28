package algs55; // section 5.5
import stdlib.*;
import algs24.MinPQ;
/* ***********************************************************************
 *  Compilation:  javac Huffman.java
 *  Execution:    java Huffman - < input.txt   (compress)
 *  Execution:    java Huffman + < input.txt   (expand)
 *  Dependencies: BinaryIn.java BinaryOut.java
 *  Data files:   http://algs4.cs.princeton.edu/55compression/abra.txt
 *                http://algs4.cs.princeton.edu/55compression/tinytinyTale.txt
 *
 *  Compress or expand a binary input stream using the Huffman algorithm.
 *
 *  % java Huffman - < abra.txt | java BinaryDump 60
 *  010100000100101000100010010000110100001101010100101010000100
 *  000000000000000000000000000110001111100101101000111110010100
 *  120 bits
 *
 *  % java Huffman - < abra.txt | java Huffman +
 *  ABRACADABRA!
 *
 *************************************************************************/

public class Huffman {
	private static BinaryIn binaryIn;
	private static BinaryOut binaryOut;

	// alphabet size of extended ASCII
	private static final int R = 256;

	// Huffman trie node
	private static class Node implements Comparable<Node> {
		public final char ch;
		public final int freq;
		public final Node left, right;

		public Node(char ch, int freq, Node left, Node right) {
			this.ch    = ch;
			this.freq  = freq;
			this.left  = left;
			this.right = right;
		}

		// is the node a leaf node?
		private boolean isLeaf() {
			assert (left == null && right == null) || (left != null && right != null);
			return (left == null && right == null);
		}

		// compare, based on frequency
		public int compareTo(Node that) {
			return this.freq - that.freq;
		}
	}


	// compress bytes from standard input and write to standard output
	public static void compress() {
		// read the input
		String s = binaryIn.readString();
		char[] input = s.toCharArray();

		// tabulate frequency counts
		int[] freq = new int[R];
		for (int i = 0; i < input.length; i++)
			freq[input[i]]++;

		// build Huffman trie
		Node root = buildTrie(freq);
		writeTrie(root);
		printTrie (root);

		// build code table
		String[] st = new String[R];
		buildCode(st, root, "");

		// print number of bytes in original uncompressed message
		binaryOut.write(input.length);
		StdOut.print ("Length: " + input.length);

		// use Huffman code to encode input
		for (int i = 0; i < input.length; i++) {
			String code = st[input[i]];
			for (int j = 0; j < code.length(); j++) {
				if (code.charAt(j) == '0') {
					binaryOut.write(false);
				}
				else if (code.charAt(j) == '1') {
					binaryOut.write(true);
				}
				else throw new IllegalStateException("Illegal state");
			}
		}

		// close output stream
		binaryOut.close();
	}

	// expand Huffman-encoded input from standard input and write to standard output
	public static void expand() {

		// read in Huffman trie from input stream
		Node root = readTrie();
		printTrie (root);

		// number of bytes to write
		int length = binaryIn.readInt();

		StdOut.print ("Length: " + length);
		// decode using the Huffman trie
		for (int i = 0; i < length; i++) {
			Node x = root;
			while (!x.isLeaf()) {
				boolean bit = binaryIn.readBoolean();
				if (bit) x = x.right;
				else     x = x.left;
			}
			binaryOut.write(x.ch);
		}
		binaryOut.close();
	}

	// build the Huffman trie given frequencies
	private static Node buildTrie(int[] freq) {

		// initialze priority queue with singleton trees
		MinPQ<Node> pq = new MinPQ<>();
		for (char i = 0; i < R; i++)
			if (freq[i] > 0)
				pq.insert(new Node(i, freq[i], null, null));

		// merge two smallest trees
		while (pq.size() > 1) {
			Node left  = pq.delMin();
			Node right = pq.delMin();
			Node parent = new Node('\0', left.freq + right.freq, left, right);
			pq.insert(parent);
		}
		return pq.delMin();
	}


	// make a lookup table from symbols and their encodings
	private static void buildCode(String[] st, Node x, String s) {
		if (!x.isLeaf()) {
			buildCode(st, x.left,  s + '0');
			buildCode(st, x.right, s + '1');
		}
		else {
			st[x.ch] = s;
		}
	}

	// write bitstring-encoded trie to standard output
	private static void writeTrie(Node x) {
		//StdOut.println ("wrote " + x.isLeaf ());
		if (x.isLeaf()) {
			//StdOut.println ("wrote " + x.ch);
			binaryOut.write(true);
			binaryOut.write(x.ch, 8);
		} else {
			binaryOut.write(false);
			writeTrie(x.left);
			writeTrie(x.right);
		}
	}

	private static Node readTrie() {
		boolean isLeaf = binaryIn.readBoolean();
		//StdOut.println ("read " + isLeaf);
		if (isLeaf) {
			char ch = binaryIn.readChar(8);
			//StdOut.println ("read " + ch);
			return new Node(ch, -1, null, null);
		} else {
			Node left = readTrie();
			Node right = readTrie();
			return new Node('\0', -1, left, right);
		}
	}
	private static void printTrie(Node x) { printTrie(x, ""); }
	private static void printTrie(Node x, String pre) {
		if (x.isLeaf()) {
			StdOut.format ("%c %s\n", x.ch, pre);
		}
		if (x.left!=null)  printTrie(x.left, pre + "0");
		if (x.right!=null) printTrie(x.right, pre + "1");
	}

	// seems to be broken...
	public static void main(String[] args) {
		String txtFile = "data/abra.txt";
		String outFile = "/tmp/abra.txt";
		String binFile = "/tmp/abra.bin";

		//args = new String[] { "-" }; BinaryStdIn.fromFile (txtFile); BinaryStdOut.toFile (binFile);
		//args = new String[] { "+" }; BinaryStdIn.fromFile (binFile); BinaryStdOut.toFile (outFile);

		//args = new String[] { "-" }; binaryIn = new BinaryIn(txtFile); binaryOut = new BinaryOut(binFile);
		//args = new String[] { "+" }; binaryIn = new BinaryIn(binFile); binaryOut = new BinaryOut(outFile);

		if      (args[0].equals("-")) compress();
		else if (args[0].equals("+")) expand();
		else throw new Error("Illegal command line argument");
	}

}
