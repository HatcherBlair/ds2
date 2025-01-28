package algs12;
import stdlib.*;

public class XCard implements Comparable<XCard> {
	public static enum Rank {
		TWO("2"),
		THREE("3"),
		FOUR("4"),
		FIVE("5"),
		SIX("6"),
		SEVEN("7"),
		EIGHT("8"),
		NINE("9"),
		TEN("10"),
		JACK("J"),
		QUEEN("Q"),
		KING("K"),
		ACE("A");

		private final String name;
		private Rank (String name) { this.name = name; }
		public String toString () { return name; }
	}
	public static enum Suit {
		CLUBS("C"),
		DIAMONDS("D"),
		HEARTS("H"),
		SPADES("S");

		private final String name;
		private Suit (String name) { this.name = name; }
		public String toString () { return name; }
	}

	public Rank rank;
	public Suit suit;
	private XCard (Rank r, Suit s) { this.rank = r; this.suit = s; }
	public String toString () { return rank.toString () + suit.toString (); }
	public int compareTo (XCard that) {
		if (this.suit.compareTo (that.suit) < 0) return -1;
		if (this.suit.compareTo (that.suit) > 0) return +1;
		if (this.rank.compareTo (that.rank) < 0) return -1;
		if (this.rank.compareTo (that.rank) > 0) return +1;
		return 0;
	}
	/* 
	 * NOTE: We don't need to override Object.equals 
	 *  because there is only one possible instance of each card.
	 *  But if you did do it, it would look like this:
	 */
	//public boolean equals (Object that) {
	//	if (that == null)
	//		return false;
	//	if (this.getClass() != that.getClass())
	//		return false;
	//	XCard thatCard = (XCard) that;
	//	return (this.rank == thatCard.rank) && (this.suit == thatCard.suit);
	//}

	private static XCard[] protoDeck = new XCard[52];
	static { // This is how you run a loop to initialize a static variable.
		int i = 0;
		for (Suit s : Suit.values ())
			for (Rank r : Rank.values ()) {
				protoDeck[i] = new XCard (r, s);
				i++;
			}
	}
	public static XCard[] newDeck () {
		XCard[] deck = new XCard[protoDeck.length];
		for (int i = 0; i<protoDeck.length; i++)
			deck[i] = protoDeck[i];
		return deck;
	}

	public static void main (String[] args) {
		XCard[] d1 = XCard.newDeck ();  final XCard c1 = d1[51];
		XCard[] d2 = XCard.newDeck ();  final XCard c2 = d2[50];
		StdOut.println (c1 + (c1.equals(c2) ? " equals " : " does not equal ") + c2);
	}
}
