package algs34;
import stdlib.*;
import java.util.HashSet;
/* ***********************************************************************
 *  Compilation:  javac PhoneNumber.java
 *  Execution:    java PhoneNumber
 *  Dependencies:
 *
 *  Immutable data type for US phone numbers, but with a broken
 *  overloaded implementation of equals(). By implementing equals()
 *  with the signature
 *
 *       public boolean equals(PhoneNumber that)
 *
 *  we do not override the equals() method inherited from Object.
 *  This causes unexpected behavior when used with java.util.HashSet.
 *
 *  DO NOT USE THIS CLASS
 *
 *************************************************************************/

public final class XPhoneNumberOverload {
	private final int area;   // area code (3 digits)
	private final int exch;   // exchange  (3 digits)
	private final int ext;    // extension (4 digits)

	public XPhoneNumberOverload(int area, int exch, int ext) {
		this.area = area;
		this.exch = exch;
		this.ext  = ext;
	}

	// overloaded equals - don't do this
	public boolean equals(XPhoneNumberOverload that) {
		return (this.area == that.area) && (this.exch == that.exch) && (this.ext == that.ext);
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
		XPhoneNumberOverload a = new XPhoneNumberOverload(609, 258, 4455);
		XPhoneNumberOverload b = new XPhoneNumberOverload(609, 876, 5309);
		XPhoneNumberOverload c = new XPhoneNumberOverload(609, 003, 5309);
		XPhoneNumberOverload d = new XPhoneNumberOverload(215, 876, 5309);
		XPhoneNumberOverload e = new XPhoneNumberOverload(609, 876, 5309);
		Object o = e;
		StdOut.format("a = %s [hashcode=%d]\n", a, a.hashCode ());
		StdOut.format("b = %s [hashcode=%d]\n", b, b.hashCode ());
		StdOut.format("c = %s [hashcode=%d]\n", c, c.hashCode ());
		StdOut.format("d = %s [hashcode=%d]\n", d, d.hashCode ());
		StdOut.format("e = %s [hashcode=%d]\n", e, e.hashCode ());

		// show broken behavior when you use covariant equals with a Set
		HashSet<XPhoneNumberOverload> set = new HashSet<>();
		set.add(a);
		set.add(b);
		set.add(c);
		StdOut.println("Added a, b, and c");
		StdOut.println("contains a:  " + set.contains(a));
		StdOut.println("contains b:  " + set.contains(b));
		StdOut.println("contains c:  " + set.contains(c));
		StdOut.println("contains d:  " + set.contains(d));
		StdOut.println("***contains e:  " + set.contains(e));  // not in set, but it should be!
		StdOut.println("b == e:      " + (b == e));
		StdOut.println("b.equals(e): " + (b.equals(e)));
		StdOut.println("o == e:      " + (o == e));
		StdOut.println("b.equals(o): " + (b.equals(o)));
	}

}

