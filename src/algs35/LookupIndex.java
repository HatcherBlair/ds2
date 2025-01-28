package algs35;
import stdlib.*;
import algs13.Queue;
/* ***********************************************************************
 *  Compilation:  javac LookupIndex.java
 *  Execution:    java LookupIndex movies.txt "/"
 *  Dependencies: ST.java Queue.java In.java StdIn.java StdOut.java
 *  Data files:   http://algs4.cs.princeton.edu/35applications/aminoI.txt
 *                http://algs4.cs.princeton.edu/35applications/movies.txt
 *
 *  % java LookupIndex aminoI.txt ","
 *  Serine
 *    TCT
 *    TCA
 *    TCG
 *    AGT
 *    AGC
 *  TCG
 *    Serine
 *
 *  % java LookupIndex movies.txt "/"
 *  Bacon, Kevin
 *    Animal House (1978)
 *    Apollo 13 (1995)
 *    Beauty Shop (2005)
 *    Diner (1982)
 *    Few Good Men, A (1992)
 *    Flatliners (1990)
 *    Footloose (1984)
 *    Friday the 13th (1980)
 *    ...
 *  Tin Men (1987)
 *    DeBoy, David
 *    Blumenfeld, Alan
 *    ...
 *
 *************************************************************************/

public class LookupIndex {

	public static void main(String[] args) {
		//args = new String[] { "data/aminoI.txt", "," }; StdIn.fromString ("Serine");
		//args = new String[] { "data/aminoI.txt", "," }; //StdIn.fromString ("ATG");
		//args = new String[] { "data/movies.txt", "/" }; StdIn.fromString ("Bacon, Kevin");
		args = new String[] { "data/movies.txt", "/" }; StdIn.fromString ("12 Angry Men (1957)");

		String filename  = args[0];
		String separator = args[1];
		In in = new In(filename);

		ST<String, Queue<String>> st = new ST<>();
		ST<String, Queue<String>> ts = new ST<>();

		while (in.hasNextLine()) {
			String line = in.readLine();
			String[] fields = line.split(separator);
			String key = fields[0];
			for (int i = 1; i < fields.length; i++) {
				String val = fields[i];
				if (!st.contains(key)) st.put(key, new Queue<>());
				if (!ts.contains(val)) ts.put(val, new Queue<>());
				st.get(key).enqueue(val);
				ts.get(val).enqueue(key);
			}
		}

		StdOut.println("Done indexing");

		// read queries from standard input, one per line
		while (!StdIn.isEmpty()) {
			String query = StdIn.readLine();
			if (st.contains(query))
				for (String vals : st.get(query))
					StdOut.println("  " + vals);
			if (ts.contains(query))
				for (String keys : ts.get(query))
					StdOut.println("  " + keys);
		}

	}

}
