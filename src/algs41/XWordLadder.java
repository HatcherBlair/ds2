package algs41;
import stdlib.*;
import algs35.XIndexSET;
/* ***********************************************************************
 *  Compilation:  javac WordLadder.java
 *  Execution:    java WordLadder word1 word2 < wordlist.txt
 *  Dependencies: Graph.java IndexSET.java In.java BreadthFirstPaths.java
 *
 *  Data files:   http://www.cs.princeton.edu/algs4/41undirected/words5.txt
 *                http://www.cs.princeton.edu/algs4/41undirected/words6.txt
 *                http://www.cs.princeton.edu/algs4/41undirected/words5-knuth.txt
 *
 *  java WordLadder flirt break < words5.txt
 *  length = 11
 *  flirt
 *  flint
 *  fling
 *  cling
 *  clink
 *  click
 *  clock
 *  cloak
 *  croak
 *  creak
 *  break
 *
 *  % java WordLadder allow brown < words5.txt
 *  NOT CONNECTED
 *
 *  % java WordLadder white house < words5.txt
 *  length = 18
 *  white
 *  while
 *  whale
 *  shale
 *  shake
 *  slake
 *  slate
 *  plate
 *  place
 *  peace
 *  peach
 *  poach
 *  coach
 *  couch
 *  cough
 *  rough
 *  rouge
 *  rouse
 *  house
 *
 *  % java WordLadder white house < words5-knuth.txt
 *  length = 9
 *  white
 *  whits
 *  shits
 *  shots
 *  soots
 *  roots
 *  routs
 *  route
 *  rouse
 *  house
 *
 *************************************************************************/

public class XWordLadder {

	// return true if two strings differ in exactly one letter
	public static boolean isNeighbor(String a, String b) {
		assert a.length() == b.length();
		int differ = 0;
		for (int i = 0; i < a.length(); i++) {
			if (a.charAt(i) != b.charAt(i)) differ++;
		}
		return (differ == 1);
	}

	public static void main(String[] args) {
		args = new String[] { "flirt", "break" }; StdIn.fromFile ("data/words5.txt");
		//args = new String[] { "white", "house" }; StdIn.fromFile ("data/words5.txt");
		//args = new String[] { "allow", "brown" }; StdIn.fromFile ("data/words5.txt");
		//args = new String[] { "white", "house" }; StdIn.fromFile ("data/words5-knuth.txt");

		String from = args[0];
		String to   = args[1];
		if (from.length() != to.length())
			throw new Error("Words have different lengths");

		/* *****************************************************************
		 *  Read a list of strings, all of the same length.
		 *  At most 10000 words supported here.
		 *******************************************************************/
		XIndexSET<String> words = new XIndexSET<>();
		while (!StdIn.isEmpty()) {
			String word = StdIn.readString();
			if (word.length() != from.length())
				throw new Error("Words have different lengths");
			words.add(word);
		}
		System.err.println("Finished reading word list");

		/* *****************************************************************
		 *  Insert connections between neighboring words into graph.
		 *  This construction process can be improved from LN^2 to
		 *  L N log N by sorting, where L = length of words.
		 *
		 *  We insert two copies of each edge, but this is easily avoided
		 *  by checking if word1.compareTo(word2) < 0
		 *
		 *******************************************************************/
		Graph G = new Graph(words.size());
		for (String word1 : words.keys()) {
			for (String word2 : words.keys()) {
				if (isNeighbor(word1, word2)) {
					G.addEdge(words.indexOf(word1), words.indexOf(word2));
				}
			}
		}
		System.err.println("Finished building graph");

		/* *****************************************************************
		 *  Run breadth first search
		 *******************************************************************/
		BreadthFirstPaths bfs = new BreadthFirstPaths(G, words.indexOf(from));
		//DepthFirstPaths bfs = new DepthFirstPaths(G, words.indexOf(from));
		if (bfs.hasPathTo(words.indexOf(to))) {
			StdOut.println("length = " + bfs.distTo(words.indexOf(to)));
			for (int v : bfs.pathTo(words.indexOf(to))) {
				StdOut.println(words.keyOf(v));
			}
		}
		else StdOut.println("NOT CONNECTED");
	}
}
