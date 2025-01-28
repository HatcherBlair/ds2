package hw3a;


public class WordFrequencyAnalyzer {
	/**********************************************************************************/
	/* You are not allowed to add any fields to this class beyond the one given below */
	/* You may only read in from the file once.  This means you may only use a single */
	/* word reader object.                                                            */
	/**********************************************************************************/
	
	// Maintain a counter for each word in the text.
	SequentialSearchST<String, Integer> counters;
	
	/**
	 * Stores a count of the number of times any word appears in a file.  The file is
	 * read in exactly once at the time time this object is constructed.
	 * 
	 * @param filename the name of the file on which to count all word occurrences.
	 */
	public WordFrequencyAnalyzer(String filename) {
		throw new RuntimeException("Not implemented.");
	}
	
	/**
	 * Returns the number of times a given word appears in the file from which this
	 * object was created.
	 * 
	 * @param word the word to count
	 * @return the number of times <code>word</code> appears.
	 */
	public int getCount(String word) {
		throw new RuntimeException("Not implemented.");
	}
	
	/**
	 * Returns the maximum frequency over all words in the file from which this
	 * ojbect was created.
	 * 
	 * @return the maximum frequency of any word in the the file.
	 */
	public int maxCount() {
		throw new RuntimeException("Not implemented.");
	}
}
