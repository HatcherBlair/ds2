package algs34;
import stdlib.*;
import java.util.HashSet;
/* ***********************************************************************
 *  Compilation:  javac PhoneNumber.java
 *  Execution:    java PhoneNumber
 *  Dependencies:
 *
 *  Mutable data type for US phone numbers, with overridden versions
 *  equals and hashcode.  This can cause data to get lost in java.util.HashSet.
 *
 *  DO NOT USE THIS CLASS
 *************************************************************************/

public final class XPhoneNumberMutable {
	private int area;   // area code (3 digits)
	private int exch;   // exchange  (3 digits)
	private int ext;    // extension (4 digits)

	public XPhoneNumberMutable(int area, int exch, int ext) {
		this.area = area;
		this.exch = exch;
		this.ext  = ext;
	}

	public void set(int area, int exch, int ext) {
		this.area = area;
		this.exch = exch;
		this.ext  = ext;
	}

	// how you're supposed to implement equals
	public boolean equals(Object y) {
		if (y == this) return true;
		if (y == null) return false;
		if (y.getClass() != this.getClass()) return false;
		XPhoneNumberMutable that = (XPhoneNumberMutable) y;
		return (this.area == that.area) && (this.exch == that.exch) && (this.ext == that.ext);
	}

	// 0 for padding with leading 0s
	public String toString() {
		return String.format("(%03d) %03d-%04d", area, exch, ext);
	}

	// satisfies the hashCode contract
	public int hashCode() {
		int h = 17;
		h = ext + 31 * h;
		h = exch + 31 * h;
		h = area + 31 * h;
		return h;
	}


	public static void main(String[] args) {
		XPhoneNumberMutable a = new XPhoneNumberMutable(609, 258, 4455);
		XPhoneNumberMutable b = new XPhoneNumberMutable(609, 876, 5309);
		XPhoneNumberMutable c = new XPhoneNumberMutable(609, 003, 5309);
		XPhoneNumberMutable d = new XPhoneNumberMutable(215, 876, 5309);
		XPhoneNumberMutable e = new XPhoneNumberMutable(609, 876, 5309);
		StdOut.format("a = %s [hashcode=%d]\n", a, a.hashCode ());
		StdOut.format("b = %s [hashcode=%d]\n", b, b.hashCode ());
		StdOut.format("c = %s [hashcode=%d]\n", c, c.hashCode ());
		StdOut.format("d = %s [hashcode=%d]\n", d, d.hashCode ());
		StdOut.format("e = %s [hashcode=%d]\n", e, e.hashCode ());

		HashSet<XPhoneNumberMutable> set = new HashSet<>();
		set.add(a);
		set.add(b);
		set.add(c);
		StdOut.println("\nAdded a, b, and c: " + set);
		StdOut.println("contains a:  " + set.contains(a));
		StdOut.println("contains b:  " + set.contains(b));
		StdOut.println("contains c:  " + set.contains(c));
		StdOut.println("contains d:  " + set.contains(d));
		StdOut.println("contains e:  " + set.contains(e));
		StdOut.println("b == e:      " + (b == e));
		StdOut.println("b.equals(e): " + (b.equals(e)));

		c.set(609, 876, 5309);
		b.set(609, 003, 5309);
		StdOut.format("\na = %s [hashcode=%d]\n", a, a.hashCode ());
		StdOut.format("b = %s [hashcode=%d]\n", b, b.hashCode ());
		StdOut.format("c = %s [hashcode=%d]\n", c, c.hashCode ());
		StdOut.format("d = %s [hashcode=%d]\n", d, d.hashCode ());
		StdOut.format("e = %s [hashcode=%d]\n", e, e.hashCode ());
		StdOut.println("\nSwapped values of b and c: " + set);
		StdOut.println("contains a:  " + set.contains(a));
		StdOut.println("***contains b:  " + set.contains(b));
		StdOut.println("***contains c:  " + set.contains(c));
		StdOut.println("contains d:  " + set.contains(d));
		StdOut.println("***contains e:  " + set.contains(e));
		StdOut.println("c == e:      " + (c == e));
		StdOut.println("c.equals(e): " + (c.equals(e)));

	}



}
