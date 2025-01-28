package algs35;
import stdlib.*;
import java.io.File;
/* ***********************************************************************
 *  Compilation:  javac FileIndex.java
 *  Execution:    java FileIndex file1.txt file2.txt file3.txt ...
 *  Dependencies: ST.java SET.java In.java StdIn.java StdOut.java
 *  Data files:   http://algs4.cs.princeton.edu/35applications/ex1.txt
 *                http://algs4.cs.princeton.edu/35applications/ex2.txt
 *                http://algs4.cs.princeton.edu/35applications/ex3.txt
 *                http://algs4.cs.princeton.edu/35applications/ex4.txt
 *
 *  % java FileIndex ex*.txt
 *  age
 *   ex3.txt
 *   ex4.txt
 * best
 *   ex1.txt
 * was
 *   ex1.txt
 *   ex2.txt
 *   ex3.txt
 *   ex4.txt
 *
 *  % java FileIndex *.txt
 *
 *  % java FileIndex *.java
 *
 *************************************************************************/

public class FileIndex {

	public static void main(String[] args) {
		args = new String[] { "data/ex1.txt", "data/ex2.txt", "data/ex3.txt", "data/ex4.txt" };
		StdIn.fromString ("age best was");

		// key = word, value = set of files containing that word
		ST<String, SET<File>> st = new ST<>();

		// create inverted index of all files
		StdOut.println("Indexing files");
		for (String filename : args) {
			StdOut.println("  " + filename);
			File file = new File(filename);
			In in = new In(file);
			while (!in.isEmpty()) {
				String word = in.readString();
				if (!st.contains(word)) st.put(word, new SET<>());
				SET<File> set = st.get(word);
				set.add(file);
			}
		}


		// read queries from standard input, one per line
		while (!StdIn.isEmpty()) {
			String query = StdIn.readString();
			StdOut.println(query);
			if (st.contains(query)) {
				SET<File> set = st.get(query);
				for (File file : set) {
					StdOut.println("  " + file.getName());
				}
			}
		}

	}

}
