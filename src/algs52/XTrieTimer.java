package algs52;
import stdlib.*;

public class XTrieTimer {
	public static void main(String[] args) {
		//final String buildFilename = "data/shells.txt";
		//final String buildFilename = "data/leipzig1M.txt";
		String buildFilename = "data/mobydick.txt";

		//final String searchFilename = buildFilename;
		//final String searchFilename = "data/mobydick.txt";
		String searchFilename = "data/leipzig1M.txt";

		//final RedBlackBST<String,Object> st = new RedBlackBST<>();
		//final SeparateChainingHashST<String,Object> st = new SeparateChainingHashST<>();
		TST<Object> st = new TST<>();
		//final LinearProbingHashST<String,Object> st = new LinearProbingHashST<>();

		// read in strings and add to set
		Object nullObject = new Object();
		In buildFile = new In (buildFilename);
		Stopwatch buildTimer = new Stopwatch();
		while (!buildFile.isEmpty()) {
			String key = buildFile.readString();
			if (!st.contains(key)) {
				st.put(key, nullObject);
				//StdOut.println(key);
			}
		}
		StdOut.format ("build time:  %8.5f\n", buildTimer.elapsedTime ());

		// read in strings and check membership
		In searchFile = new In (searchFilename);
		Stopwatch searchTimer = new Stopwatch();
		int hits = 0;
		int misses = 0;
		while (!searchFile.isEmpty()) {
			String key = searchFile.readString();
			if (st.contains(key)) hits++;
			else                  misses++;
		}
		StdOut.format ("search time: %8.5f\n", searchTimer.elapsedTime ());
		StdOut.format ("hits:   %13d\n", hits);
		StdOut.format ("misses: %13d\n", misses);
	}
}
