package algs12;
import stdlib.*;

public class XCardSimple implements Comparable<XCardSimple> {
	public static enum Rank { TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE }
	public static enum Suit { CLUBS, DIAMONDS, HEARTS, SPADES }

	public final Rank rank;
	public final Suit suit;
	public XCardSimple (Rank r, Suit s) { this.rank = r; this.suit = s; }
	public String toString () { return rank + " of " + suit; }
	public int compareTo (XCardSimple that) {
		if (this.suit.compareTo (that.suit) < 0) return -1;
		if (this.suit.compareTo (that.suit) > 0) return +1;
		if (this.rank.compareTo (that.rank) < 0) return -1;
		if (this.rank.compareTo (that.rank) > 0) return +1;
		return 0;
	}
	public boolean equals (Object that) {
		if (that == null)
			return false;
		if (this.getClass() != that.getClass())
			return false;
		// This does the right thing but is inefficient.
		// Since equals may be used more than compareTo, there is usually a separate implementation.
		return 0 == this.compareTo ((XCardSimple) that);
	}

	public static void main (String[] args) {
		Suit s1 = Suit.CLUBS;
		//Suit s2 = new Suit();

		XCardSimple c1 = new XCardSimple(Rank.ACE, Suit.SPADES);
		XCardSimple c2 = new XCardSimple(Rank.ACE, Suit.SPADES);
		StdOut.println (c1 + (c1.equals(c2) ? " equals " : " does not equal ") + c2);
	}
}
