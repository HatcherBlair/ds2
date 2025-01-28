// Exercise 1.2.13 2.1.21 (Solution published at http://algs4.cs.princeton.edu/)
// Exercise 1.2.13
package algs12;
import stdlib.*;
import java.util.Arrays;
import java.util.Comparator;
/* ***********************************************************************
 *  Compilation:  javac Transaction.java
 *  Execution:    java Transaction
 *
 *  Data type for commercial transactions.
 *
 *************************************************************************/
public class Transaction implements Comparable<Transaction> {
	private final String  who;      // customer
	private final Date    when;     // date
	private final double  amount;   // amount

	public Transaction(String who, Date when, double amount) {
		this.who    = who;
		this.when   = when;
		this.amount = amount;
	}

	// create new transaction by parsing string of the form: name,
	// date, real number, separated by whitespace
	public Transaction(String transaction) {
		String[] a = transaction.split("\\s+");
		who    = a[0];
		when   = new Date(a[1]);
		amount = Double.parseDouble(a[2]);
	}

	// accessor methods
	public String  who()    { return who;      }
	public Date    when()   { return when;     }
	public double  amount() { return amount;   }

	public String toString() {
		return String.format("%-10s %10s %8.2f", who, when, amount);
	}

	public int compareTo(Transaction that) {
		if      (this.amount < that.amount) return -1;
		else if (this.amount > that.amount) return +1;
		else                                return  0;
	}

	// is this Transaction equal to x?
	public boolean equals(Object x) {
		if (x == this) return true;
		if (x == null) return false;
		if (x.getClass() != this.getClass()) return false;
		Transaction that = (Transaction) x;
		return (this.amount == that.amount) && (this.who.equals(that.who))
				&& (this.when.equals(that.when));
	}




	public int hashCode() {
		int hash = 17;
		hash = 31*hash + who.hashCode();
		hash = 31*hash + when.hashCode();
		hash = 31*hash + ((Double) amount).hashCode();
		return hash;
	}

	public static final Comparator<Transaction> WHO_ORDER = new WhoOrder();
	public static final Comparator<Transaction> WHEN_ORDER = new WhenOrder();
	public static final Comparator<Transaction> HOW_MUCH_ORDER = new HowMuchOrder();
	// ascending order of account number
	private static class WhoOrder implements Comparator<Transaction> {
		public int compare(Transaction v, Transaction w) {
			return v.who.compareTo(w.who);
		}
	}

	// ascending order of time
	private static class WhenOrder implements Comparator<Transaction> {
		public int compare(Transaction v, Transaction w) {
			return v.when.compareTo(w.when);
		}
	}

	// ascending order of amount
	private static class HowMuchOrder implements Comparator<Transaction> {
		public int compare(Transaction v, Transaction w) {
			if      (v.amount < w.amount) return -1;
			else if (v.amount > w.amount) return +1;
			else                          return  0;
		}
	}


	// test client
	public static void main(String[] args) {
		Transaction[] a = new Transaction[4];
		a[0] = new Transaction("Turing   6/17/1990  644.08");
		a[1] = new Transaction("Tarjan   3/26/2002  4121.85");
		a[2] = new Transaction("Knuth    6/14/1999  288.34");
		a[3] = new Transaction("Dijkstra 8/22/2007  2678.40");

		StdOut.println("Unsorted");
		for (Transaction element : a)
			StdOut.println(element);
		StdOut.println();

		StdOut.println("Sort by date");
		Arrays.sort(a, new WhenOrder());
		for (Transaction element : a)
			StdOut.println(element);
		StdOut.println();

		StdOut.println("Sort by customer");
		Arrays.sort(a, new WhoOrder());
		for (Transaction element : a)
			StdOut.println(element);
		StdOut.println();

		StdOut.println("Sort by amount");
		Arrays.sort(a, new HowMuchOrder());
		for (Transaction element : a)
			StdOut.println(element);
		StdOut.println();
	}

}

