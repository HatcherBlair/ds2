package algs25;
import stdlib.*;
import java.util.Arrays;
import java.util.Comparator;
/* ***********************************************************************
 *  Compilation:  javac Student.java
 *  Execution:    java Student
 *
 *  Illustrates implementation of a Comparator
 *
 *  % By name
 *  ----------
 *  2 Alice
 *  1 Bob
 *  2 Carol
 *  1 Dave
 *  2 Eve
 *  3 Frank
 *  1 Grant
 *  3 Helia
 *  3 Isaac
 *  1 Jen
 *  1 Kevin
 *  2 Larry

 *  By section
 *  ----------
 *  1 Bob
 *  1 Dave
 *  1 Grant
 *  1 Jen
 *  1 Kevin
 *  2 Alice
 *  2 Carol
 *  2 Eve
 *  2 Larry
 *  3 Frank
 *  3 Helia
 *  3 Isaac
 *
 *  By Kevin
 *  ----------
 *  1 Kevin
 *  2 Larry
 *  2 Alice
 *  1 Bob
 *  2 Carol
 *  1 Dave
 *  2 Eve
 *  3 Frank
 *  1 Grant
 *  3 Helia
 *  3 Isaac
 *  1 Jen
 *
 *************************************************************************/

public class XStudent {

	public static final Comparator<XStudent> BY_NAME    = new ByName();
	public static final Comparator<XStudent> BY_SECTION = new BySection();
	public final Comparator<XStudent> BY_MY_NAME = new ByMyName();

	private final String name;
	private final int section;

	// constructor
	public XStudent(String name, int section) {
		this.name = name;
		this.section = section;
	}

	// comparator to sort by name
	private static class ByName implements Comparator<XStudent> {
		public int compare(XStudent a, XStudent b) {
			return a.name.compareTo(b.name);
		}
	}

	// comparator to sort by section
	private static class BySection implements Comparator<XStudent> {
		public int compare(XStudent a, XStudent b) {
			return a.section - b.section;
		}
	}

	// comparator to sort by name with this name first
	// illustrates the use of a non-static comparator
	private class ByMyName implements Comparator<XStudent> {
		public int compare(XStudent a, XStudent b) {
			if (a.name.compareTo(b.name) == 0) return 0;
			if (a.name.compareTo(name) == 0) return -1;
			if (b.name.compareTo(name) == 0) return +1;
			if ((a.name.compareTo(name) < 0) && (b.name.compareTo(name) > 0))
				return +1;
			if ((a.name.compareTo(name) > 0) && (b.name.compareTo(name) < 0))
				return -1;
			return a.name.compareTo(b.name);
		}
	}

	// return string representation
	public String toString() {
		return section + " " + name;
	}


	// test client
	public static void main(String[] args) {

		// create an array of students
		XStudent alice  = new XStudent("Alice",  2);
		XStudent bob    = new XStudent("Bob",    1);
		XStudent carol  = new XStudent("Carol",  2);
		XStudent dave   = new XStudent("Dave",   1);
		XStudent eve    = new XStudent("Eve",    2);
		XStudent frank  = new XStudent("Frank",  3);
		XStudent grant  = new XStudent("Grant",  1);
		XStudent helia  = new XStudent("Helia",  3);
		XStudent isaac  = new XStudent("Isaac",  3);
		XStudent jen    = new XStudent("Jen",    1);
		XStudent kevin  = new XStudent("Kevin",  1);
		XStudent larry  = new XStudent("Larry",  2);
		XStudent[] students = {
				larry, kevin, jen, isaac, grant, helia,
				frank, eve, dave, carol, bob, alice
		};

		// sort by name and print results
		StdOut.println("By name");
		StdOut.println("----------");
		Arrays.sort(students, BY_NAME);
		for (XStudent student : students)
			StdOut.println(student);
		StdOut.println();


		// now, sort by section and print results
		StdOut.println("By section");
		StdOut.println("----------");
		Arrays.sort(students, BY_SECTION);
		for (XStudent student : students)
			StdOut.println(student);
		StdOut.println();

		// now, sort by name relative to Kevin
		StdOut.println("By Kevin");
		StdOut.println("----------");
		Arrays.sort(students, kevin.BY_MY_NAME);
		for (XStudent student : students)
			StdOut.println(student);
		StdOut.println();


	}

}
