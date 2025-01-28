package algs35;
import stdlib.*;
/* ***********************************************************************
 *  Compilation:  javac FrequencyTable StdIn.java
 *  Execution:    java FrequencyTable < words.txt
 *
 *  Read in a list of words from standard input and print out
 *  each word and the number of times it appears.
 *
 *  % java Frequency < mobydick.txt | sort -rn | more
 *  13967 the
 *  6415 of
 *  6247 and
 *  4583 a
 *  4508 to
 *  4037 in
 *  2911 that
 *  2481 his
 *
 *************************************************************************/

public class XFrequencyTable<K extends Comparable<? super K>> {

	private final ST<K, Integer> st = new ST<>();

	// add 1 to the number of times key appears
	public void hit(K key) {
		Integer freq = st.get(key);
		if (freq == null) st.put(key, 1);
		else              st.put(key, freq + 1);
	}

	// return the number of times the key appears
	public int count(K key) {
		Integer freq = st.get(key);
		if (freq == null) return 0;
		else              return freq;
	}

	// print all the keys to standard output
	public void show() {
		for (K key : st.keys())
			StdOut.println(st.get(key) + " " + key);
	}


	public static void main(String[] args) {
		StdIn.fromFile ("data/mobydick.txt");

		XFrequencyTable<String> f = new XFrequencyTable<>();
		while (!StdIn.isEmpty()) {
			String key = StdIn.readString();
			f.hit(key);
		}
		f.show();


	}
}

