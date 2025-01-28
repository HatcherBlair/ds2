package algs31;
import stdlib.*;
/* ***********************************************************************
 *  Compilation:  javac Person.java
 *  Execution:    java Person
 *
 *  Implementing equals() in a client-defined type.
 *
 *************************************************************************/

public final class XPerson {
	private final String name;
	private final long info;

	public XPerson(String name, long info) {
		this.name = name;
		this.info = info;
	}

	// how you're supposed to implement equals
	public boolean equals(Object y) {
		if (y == this) return true;
		if (y == null) return false;
		if (y.getClass() != this.getClass()) return false;
		XPerson that = (XPerson) y;
		return (this.name.equals(that.name)) && (this.info == that.info);
	}

	public String toString() {
		return name + " " + info;
	}

	public static void main(String[] args) {
		XPerson a = new XPerson("Alice", 1234);
		XPerson b = new XPerson("Alice", 1234);
		XPerson c = new XPerson("Bob",   1234);
		XPerson d = new XPerson("Alice", 4321);
		StdOut.println("a = " + a);
		StdOut.println("b = " + b);
		StdOut.println("c = " + c);
		StdOut.println("d = " + d);
		StdOut.println("a == a: " + a.equals(a));
		StdOut.println("a == b: " + a.equals(b));
		StdOut.println("a == c: " + a.equals(c));
		StdOut.println("a == d: " + a.equals(d));
	}



}
