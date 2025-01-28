package algs21;
import stdlib.*;
import java.util.Comparator;

public class XSortCards0 {
	public static void show (String title, String[] d) {
		StdOut.println (title);
		for (int i=0; i<4; i++) {
			for (int j=0; j<13; j++) {
				StdOut.format ("%3s ", d[i*13+j]);
			}
			StdOut.println ();
		}
		StdOut.println ();
	}
	private static class CardComparator implements Comparator<String> {
		private static char suit (String s) {
			return s.charAt (s.length () - 1);
		}
		private static int rank (String s) {
			char rank = s.charAt (0);
			switch (rank) {
			case '1' : return 10;
			case 'J' : return 11;
			case 'Q' : return 12;
			case 'K' : return 13;
			case 'A' : return 14;
			default: return rank - '0';
			}
		}
		public int compare (String c1, String c2) {
			if (suit(c1) < suit(c2)) return -1;
			if (suit(c1) > suit(c2)) return +1;
			if (rank(c1) < rank(c2)) return -1;
			if (rank(c1) > rank(c2)) return +1;
			return 0;
		}
	}
	public static void main (String[] args) {
		String[] d =  {
				"2C", "3C", "4C", "5C", "6C", "7C", "8C", "9C", "10C", "JC", "QC", "KC", "AC",
				"2D", "3D", "4D", "5D", "6D", "7D", "8D", "9D", "10D", "JD", "QD", "KD", "AD",
				"2H", "3H", "4H", "5H", "6H", "7H", "8H", "9H", "10H", "JH", "QH", "KH", "AH",
				"2S", "3S", "4S", "5S", "6S", "7S", "8S", "9S", "10S", "JS", "QS", "KS", "AS" };
		show ("Initial", d);
		StdRandom.shuffle (d);
		show ("Shuffled", d);
		Insertion.sort (d);
		show ("Sort1", d);
		Insertion.sort (d, new CardComparator ());
		show ("Sort2", d);
		
		String c1 = "@E";
		d[0] = c1;
		StdRandom.shuffle (d);
		Insertion.sort (d, new CardComparator ());
		show ("Sort3", d);
	}
}
