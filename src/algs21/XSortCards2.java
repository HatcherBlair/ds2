package algs21;
import stdlib.*;
import algs12.XCard;
import java.util.Comparator;

public class XSortCards2 {
	public static void show (String title, XCard[] d) {
		StdOut.println (title);
		for (int i=0; i<4; i++) {
			for (int j=0; j<13; j++) {
				StdOut.format ("%3s ", d[i*13+j]);
			}
			StdOut.println ();
		}
		StdOut.println ();
	}
	private static class RankFirstComparator implements Comparator<XCard> {
		public int compare (XCard c1, XCard c2) {
			if (c1.rank.compareTo (c2.rank) < 0) return -1;
			if (c1.rank.compareTo (c2.rank) > 0) return +1;
			if (c1.suit.compareTo (c2.suit) < 0) return -1;
			if (c1.suit.compareTo (c2.suit) > 0) return +1;
			return 0;
		}
	}
	public static void main (String[] args) {
		XCard[] d = XCard.newDeck ();
		show ("Initial", d);
		StdRandom.shuffle (d);
		show ("Shuffled", d);
		Insertion.sort (d);
		show ("Sort1", d);
		Insertion.sort (d, new RankFirstComparator ());
		show ("Sort2", d);
	}
}
