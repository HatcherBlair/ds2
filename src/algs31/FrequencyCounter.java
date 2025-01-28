package algs31;
import stdlib.*;
import algs32.BST;
import algs33.RedBlackBST;
import algs34.LinearProbingHashST;
import algs34.SeparateChainingHashST;
import algs35.ST;
/* ***********************************************************************
 *  Compilation:  javac FrequencyCounter.java
 *  Execution:    java FrequencyCounter L < input.txt
 *  Dependencies: ST.java StdIn.java StdOut.java
 *  Data files:   http://algs4.cs.princeton.edu/41elementary/tnyTale.txt
 *                http://algs4.cs.princeton.edu/41elementary/tale.txt
 *                http://algs4.cs.princeton.edu/41elementary/tale.txt
 *                http://algs4.cs.princeton.edu/41elementary/leipzig100K.txt
 *                http://algs4.cs.princeton.edu/41elementary/leipzig300K.txt
 *                http://algs4.cs.princeton.edu/41elementary/leipzig1M.txt
 *
 *  Read in a list of words from standard input and print out
 *  the most frequently occurring word.
 *
 *  % java FrequencyCounter 1 < tinyTale.txt
 *  it 10
 *
 *  % java FrequencyCounter 8 < tale.txt
 *  business 122
 *
 *  % java FrequencyCounter 10 < leipzig1M.txt
 *  government 24763
 *
 *
 *************************************************************************/
/*
-------------------------------------------------
Results Leipzig, 15
-------------------------------------------------
Java TreeMap (ST)
  put time: 17.078
  get time: 0.023
  most frequent word = over-the-counter (1067 times)
  distinct = 22172
  words    = 64647
LinearProbingHashST
  put time: 17.11
  get time: 0.024
SeparateChainingHashST
  put time: 19.439
  get time: 0.03
BST
  put time: 24.604
  get time: 0.045
RedBlackBST
  put time: 25.651
  get time: 0.045
Ordered Array (BinarySearchST)
  put time: 25.862
  get time: 0.035
Unordered Array (ArrayST)
  put time: 54.04
  get time: 7.073
Unordered Linked List (SequentialSearchST)
  put time: 71.719
  get time: 11.849

-------------------------------------------------
Results Leipzig, 10
-------------------------------------------------
Java TreeMap (ST)
  put time: 30.91
  get time: 0.154
  most frequent word = government (24763 times)
  distinct =  165555
  words    = 1610829
LinearProbingHashST
  put time: 25.752
  get time: 0.075
SeparateChainingHashST
  put time: 27.603
  get time: 0.123
BST
  put time: 31.053
  get time: 0.248
RedBlackBST
  put time: 32.014
  get time: 0.237
Ordered Array (BinarySearchST)
  put time: 153.086
  get time: 0.252
Unordered Array (ArrayST)
  ...
 */
public class FrequencyCounter {

	public static void main(String[] args) {
		//int minlen = 2; String file = "data/tinyTale.txt";
		int minlen = 6; String file = "data/tale.txt";
		//int minlen = 12; String file = "data/leipzig1M.txt";
		StdOut.println ("Java TreeMap (ST)"); testST(minlen, file);
		//StdOut.println ("LinearProbingHashST"); testLinearProbingHashST(minlen, file);
		//StdOut.println ("SeparateChainingHashST"); testSeparateChainingHashST(minlen, file);
		//StdOut.println ("BST"); testBST(minlen, file);
		//StdOut.println ("RedBlackBST"); testRedBlackBST(minlen, file);
		//StdOut.println ("Ordered Array (BinarySearchST)"); testBinarySearchST(minlen, file);
		//StdOut.println ("Unordered Array (ArrayST)"); testArrayST(minlen, file);
		//StdOut.println ("Unordered Linked List (SequentialSearchST)"); testSequentialSearchST(minlen, file);
	}

	private static void testLinearProbingHashST (int minlen, String file) {
		LinearProbingHashST<String, Integer> st = new LinearProbingHashST<>();

		In in = new In (file);
		Stopwatch sw = new Stopwatch();
		while (!in.isEmpty()) {
			String key = in.readString();
			if (key.length() < minlen) continue;
			if (st.contains(key)) { st.put(key, st.get(key) + 1); }
			else                  { st.put(key, 1); }
		}
		StdOut.println("  put time: " + sw.elapsedTime ());

		sw = new Stopwatch();
		String max = "";
		st.put(max, 0);
		for (String word : st.keys()) {
			if (st.get(word) > st.get(max))
				max = word;
		}
		StdOut.println("  get time: " + sw.elapsedTime ());
		StdOut.println("  most frequent word = " + max + " (" + st.get(max) + " times)");
	}
	private static void testSeparateChainingHashST (int minlen, String file) {
		SeparateChainingHashST<String, Integer> st = new SeparateChainingHashST<>();

		In in = new In (file);
		Stopwatch sw = new Stopwatch();
		while (!in.isEmpty()) {
			String key = in.readString();
			if (key.length() < minlen) continue;
			if (st.contains(key)) { st.put(key, st.get(key) + 1); }
			else                  { st.put(key, 1); }
		}
		StdOut.println("  put time: " + sw.elapsedTime ());

		sw = new Stopwatch();
		String max = "";
		st.put(max, 0);
		for (String word : st.keys()) {
			if (st.get(word) > st.get(max))
				max = word;
		}
		StdOut.println("  get time: " + sw.elapsedTime ());
		StdOut.println("  most frequent word = " + max + " (" + st.get(max) + " times)");
	}
	private static void testArrayST (int minlen, String file) {
		ArrayST<String, Integer> st = new ArrayST<>();

		In in = new In (file);
		Stopwatch sw = new Stopwatch();
		while (!in.isEmpty()) {
			String key = in.readString();
			if (key.length() < minlen) continue;
			if (st.contains(key)) { st.put(key, st.get(key) + 1); }
			else                  { st.put(key, 1); }
		}
		StdOut.println("  put time: " + sw.elapsedTime ());

		sw = new Stopwatch();
		String max = "";
		st.put(max, 0);
		for (String word : st.keys()) {
			if (st.get(word) > st.get(max))
				max = word;
		}
		StdOut.println("  get time: " + sw.elapsedTime ());
		StdOut.println("  most frequent word = " + max + " (" + st.get(max) + " times)");
	}
	private static void testRedBlackBST (int minlen, String file) {
		RedBlackBST<String, Integer> st = new RedBlackBST<>();

		In in = new In (file);
		Stopwatch sw = new Stopwatch();
		while (!in.isEmpty()) {
			String key = in.readString();
			if (key.length() < minlen) continue;
			if (st.contains(key)) { st.put(key, st.get(key) + 1); }
			else                  { st.put(key, 1); }
		}
		StdOut.println("  put time: " + sw.elapsedTime ());

		sw = new Stopwatch();
		String max = "";
		st.put(max, 0);
		for (String word : st.keys()) {
			if (st.get(word) > st.get(max))
				max = word;
		}
		StdOut.println("  get time: " + sw.elapsedTime ());
		StdOut.println("  most frequent word = " + max + " (" + st.get(max) + " times)");
	}
	private static void testSequentialSearchST (int minlen, String file) {
		SequentialSearchST<String, Integer> st = new SequentialSearchST<>();

		In in = new In (file);
		Stopwatch sw = new Stopwatch();
		while (!in.isEmpty()) {
			String key = in.readString();
			if (key.length() < minlen) continue;
			if (st.contains(key)) { st.put(key, st.get(key) + 1); }
			else                  { st.put(key, 1); }
		}
		StdOut.println("  put time: " + sw.elapsedTime ());

		sw = new Stopwatch();
		String max = "";
		st.put(max, 0);
		for (String word : st.keys()) {
			if (st.get(word) > st.get(max))
				max = word;
		}
		StdOut.println("  get time: " + sw.elapsedTime ());
		StdOut.println("  most frequent word = " + max + " (" + st.get(max) + " times)");
	}
	private static void testBST (int minlen, String file) {
		BST<String, Integer> st = new BST<>();

		In in = new In (file);
		Stopwatch sw = new Stopwatch();
		while (!in.isEmpty()) {
			String key = in.readString();
			if (key.length() < minlen) continue;
			if (st.contains(key)) { st.put(key, st.get(key) + 1); }
			else                  { st.put(key, 1); }
		}
		StdOut.println("  put time: " + sw.elapsedTime ());

		sw = new Stopwatch();
		String max = "";
		st.put(max, 0);
		for (String word : st.keys()) {
			if (st.get(word) > st.get(max))
				max = word;
		}
		StdOut.println("  get time: " + sw.elapsedTime ());
		StdOut.println("  most frequent word = " + max + " (" + st.get(max) + " times)");
	}
	private static void testBinarySearchST (int minlen, String file) {
		BinarySearchST<String, Integer> st = new BinarySearchST<>();

		In in = new In (file);
		Stopwatch sw = new Stopwatch();
		while (!in.isEmpty()) {
			String key = in.readString();
			if (key.length() < minlen) continue;
			if (st.contains(key)) { st.put(key, st.get(key) + 1); }
			else                  { st.put(key, 1); }
		}
		StdOut.println("  put time: " + sw.elapsedTime ());

		sw = new Stopwatch();
		String max = "";
		st.put(max, 0);
		for (String word : st.keys()) {
			if (st.get(word) > st.get(max))
				max = word;
		}
		StdOut.println("  get time: " + sw.elapsedTime ());
		StdOut.println("  most frequent word = " + max + " (" + st.get(max) + " times)");
	}
	private static void testST (int minlen, String file) {
		int words = 0;
		In in = new In (file);
		ST<String, Integer> st = new ST<>();

		// compute frequency counts
		Stopwatch sw = new Stopwatch();
		while (!in.isEmpty()) {
			String key = in.readString();
			if (key.length() < minlen) continue;
			words++;
			if (st.contains(key)) { st.put(key, st.get(key) + 1); }
			else                  { st.put(key, 1); }
		}
		StdOut.println("  put time: " + sw.elapsedTime ());

		// find a key with the highest frequency count
		sw = new Stopwatch();
		String max = "";
		st.put(max, 0);
		for (String word : st.keys()) {
			if (st.get(word) > st.get(max))
				max = word;
		}
		StdOut.println("  get time: " + sw.elapsedTime ());

		StdOut.println("  most frequent word = " + max + " (" + st.get(max) + " times)");
		StdOut.println("  distinct = " + (st.size ()-1));
		StdOut.println("  words  = " + words);
	}


}
