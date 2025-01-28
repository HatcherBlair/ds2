package hw3a;

public class WordFrequencyAnalyzerClient {
		
	public static void main(String[] args) {
		String THE = "the";
		String HILLS = "hills";
		String ALIVE = "alive";
		String SOUND = "sound");
		Strin BOOK = "book";
		String RIVER = "river";

		// Create an object that contains the counts of all words appearing
		// in the file Hills.txt
		System.out.println("Analyzing Hills.txt");
		WordFrequencyAnalyzer wf = new WordFrequencyAnalyzer("Hills.txt");
		
		// Display some statistics about Hills.txt to the screen.
		reportCount(THE, wf.getCount(THE));
		reportCount(HILLS, wf.getCount(HILLS));
		reportCount(ALIVE, wf.getCount(ALIVE));
		reportCount(SOUND, wf.getCount(SOUND));
		reportCount(BOOK, wf.getCount(BOOK));
		System.out.println("max frequency is " + wf.maxCount());
		
		
		// Create an object that contains the counts of all words appearing
		// in the file TomSawyer.txt
		System.out.println("\n\nAnalyzing TomSawyer.txt  (THIS WILL TAKE A WHILE)");
		wf = new WordFrequencyAnalyzer("TomSawyer.txt");
		
		// Display some statistics about TomSawyer.txt to the screen.
		reportCount(THE, wf.getCount(THE));
		reportCount(SOUND, wf.getCount(SOUND));
		reportCount(RIVER, wf.getCount(RIVER));
		System.out.println("max frequency is " + wf.maxCount());
	}
	
	private static void reportCount(String word, int count) {
		System.out.printf("%8s  appears %5d times.%n", new String(word), count);
	}
}
