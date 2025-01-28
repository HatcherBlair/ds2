package algs25;
import stdlib.*;
import java.util.Arrays;
/* ***********************************************************************
 *  Compilation:  javac Grade.java
 *  Execution:    java Grade
 *
 *  Illustrates implementation of Comparable interface
 *
 *************************************************************************/

public class XGrade implements Comparable<XGrade> {
	private final String grade;

	public XGrade(String grade) {
		this.grade = grade;
		if (gpa() < 0.0) throw new Error("Illegal grade");
	}

	public double gpa() {
		if (grade.equals("A"))  return 4.00;
		if (grade.equals("B"))  return 3.00;
		if (grade.equals("C"))  return 2.00;
		if (grade.equals("D"))  return 1.00;
		if (grade.equals("F"))  return 0.00;
		if (grade.equals("B+")) return 3.33;
		if (grade.equals("C+")) return 2.33;
		if (grade.equals("A-")) return 3.67;
		if (grade.equals("B-")) return 2.67;
		if (grade.equals("C-")) return 1.67;
		return -1.0;
	}

	public int compareTo(XGrade w) {
		XGrade v = this;
		if (v.gpa() < w.gpa()) return -1;
		if (v.gpa() > w.gpa()) return +1;
		return 0;
	}

	// use %-2s flag to left justify
	public String toString() {
		return String.format("%-2s %3.2f", grade, gpa());
	}


	// test client
	public static void main(String[] args) {
		XGrade[] grades = new XGrade[8];
		grades[0] = new XGrade("B+");
		grades[1] = new XGrade("A");
		grades[2] = new XGrade("C+");
		grades[3] = new XGrade("B-");
		grades[4] = new XGrade("A-");
		grades[5] = new XGrade("D");
		grades[6] = new XGrade("F");
		grades[7] = new XGrade("A-");


		StdOut.println("Unsorted");
		for (XGrade grade2 : grades)
			StdOut.println(grade2);
		StdOut.println();

		StdOut.println("Sorted");
		Arrays.sort(grades);
		for (XGrade grade2 : grades)
			StdOut.println(grade2);
	}

}


