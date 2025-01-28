package algs34;
import stdlib.*;
import java.util.HashSet;
/* ***********************************************************************
 *  Compilation:  javac PhoneNumber.java
 *  Execution:    java PhoneNumber
 *  Dependencies:
 *
 *  Immutable data type for US phone numbers.
 *
 *************************************************************************/

public final class XPhoneNumber {
	private final int area;   // area code (3 digits)
	private final int exch;   // exchange  (3 digits)
	private final int ext;    // extension (4 digits)

	public XPhoneNumber(int area, int exch, int ext) {
		this.area = area;
		this.exch = exch;
		this.ext  = ext;
	}

	// how you're supposed to implement equals
	public boolean equals(Object y) {
		if (y == this) return true;
		if (y == null) return false;
		if (y.getClass() != this.getClass()) return false;
		XPhoneNumber that = (XPhoneNumber) y;
		if (!((this.area == that.area) && (this.exch == that.exch) && (this.ext == that.ext))) return false;
		return true;
	}

	// satisfies the hashCode contract
	public int hashCode() {
		int h = 17;
		h = ext + 31 * h;
		h = exch + 31 * h;
		h = area + 31 * h;
		return h;
	}

	// 0 for padding with leading 0s
	public String toString() {
		return String.format("(%03d) %03d-%04d", area, exch, ext);
	}

	public static void main(String[] args) {
		XPhoneNumber a = new XPhoneNumber(609, 258, 4455);
		XPhoneNumber b = new XPhoneNumber(609, 876, 5309);
		XPhoneNumber c = new XPhoneNumber(609, 003, 5309);
		XPhoneNumber d = new XPhoneNumber(215, 876, 5309);
		XPhoneNumber e = new XPhoneNumber(609, 876, 5309);
		StdOut.format("a = %s [hashcode=%d]\n", a, a.hashCode ());
		StdOut.format("b = %s [hashcode=%d]\n", b, b.hashCode ());
		StdOut.format("c = %s [hashcode=%d]\n", c, c.hashCode ());
		StdOut.format("d = %s [hashcode=%d]\n", d, d.hashCode ());
		StdOut.format("e = %s [hashcode=%d]\n", e, e.hashCode ());

		HashSet<XPhoneNumber> set = new HashSet<>();
		set.add(a);
		set.add(b);
		set.add(c);
		StdOut.println("Added a, b, and c");
		StdOut.println("contains a:  " + set.contains(a));
		StdOut.println("contains b:  " + set.contains(b));
		StdOut.println("contains c:  " + set.contains(c));
		StdOut.println("contains d:  " + set.contains(d));
		StdOut.println("contains e:  " + set.contains(e));
		StdOut.println("b == e:      " + (b == e));
		StdOut.println("b.equals(e): " + (b.equals(e)));
	}



}
