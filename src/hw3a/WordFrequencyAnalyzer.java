package hw3a;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import algs31.SequentialSearchST;

public class WordFrequencyAnalyzer {
	/**********************************************************************************/
	/* You are not allowed to add any fields to this class beyond the one given below */
	/* You may only read in from the file once.  This means you may only use a single */
	/* word reader object.                                                            */
	/**********************************************************************************/
	
	// Maintain a counter for each word in the text.
	SequentialSearchST<String, Integer> counters = new SequentialSearchST<>();

	// Static prefix for the directory name of files
	static final String dir = "src/hw3a/data-files/";
	
	/**
	 * Stores a count of the number of times any word appears in a file.  The file is
	 * read in exactly once at the time time this object is constructed.
	 * 
	 * @param filename the name of the file on which to count all word occurrences.
	 */
	public WordFrequencyAnalyzer(String filename) {
		
		try {
			File file = new File(dir+filename);
			Scanner scanner = new Scanner(file);
			scanner.useDelimiter("\\s+");
			while(scanner.hasNext()) {
				String newKey = scanner.next();

				if(counters.contains(newKey)) {
					int count = counters.get(newKey);
					counters.put(newKey, count + 1);
				} else {
					counters.put(newKey, 1);
				}
				
			}
			scanner.close();
		} catch ( FileNotFoundException ex) {
			System.out.println(ex.getMessage());
		}
			
		
	}
	
	/**
	 * Returns the number of times a given word appears in the file from which this
	 * object was created.
	 * 
	 * @param word the word to count
	 * @return the number of times <code>word</code> appears.
	 */
	public int getCount(String word) {
		if(counters.contains(word)) {
			int count = counters.get(word);
			return count;
		}

		return 0;
	}
	
	/**
	 * Returns the maximum frequency over all words in the file from which this
	 * ojbect was created.
	 * 
	 * @return the maximum frequency of any word in the the file.
	 */
	public int maxCount() {
		int max = 0;
		int curVal;
		for (String key : counters.keys()) {
			if((curVal = counters.get(key)) > max) {
				max = curVal;
			}
			
		}
		return max;
	}
}
