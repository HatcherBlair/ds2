package algs21;
import stdlib.*;
import algs12.XCard;

public class XSortCards1 {
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
	public static void main (String[] args) {
		XCard[] d = XCard.newDeck ();
		show ("Initial", d);
		StdRandom.shuffle (d);
		show ("Shuffled", d);
		Insertion.sort (d);
		show ("Sorted", d);
	}
}
