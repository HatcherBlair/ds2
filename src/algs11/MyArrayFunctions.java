package algs11;
import stdlib.*;
import java.util.Arrays;

import javax.lang.model.util.ElementScanner14;
/*
 PROBLEM 1: Run the program algs11.XAutoboxingValueOf and answer the following questions.

   QUESTION: What is the difference between the following two statements:
     x = "Dog";
     x = new String("Dog");

   ANSWER:

   QUESTION: What is the difference between the following two statements:
     x = Integer.valueOf (3);
     x = new Integer (3);

   ANSWER:

   QUESTION: Aside from the difference in the numbers,
   what is the difference between the following two statements:
     x = Integer.valueOf (12);
     x = Integer.valueOf (132973);

   ANSWER:

 PROBLEM 2: Using the formula given in class, write recursive versions of "average", "copy" and
"reverse", using the iterative versions below as a starting point.  The iterative versions are
"averageI", "plusOneI" and "reverseI".  HINT: You will need to define helper functions.
 */
public class MyArrayFunctions {

	// Precondition:a.length > 0
	// return: max value in a
	// Postcondition: a is unchanged
	public static double max (double[] a) {
		double result=a[0];
		for (int i = 0; i < a.length; i++)
			if (a[i] > result)
				result = a[i];
		return result; // TODO
	}

	// Precondition:a.length >= 0, IMPLIES a != null
	// return: max value in a
	// Postcondition: a is unchanged
	public static double max2 (double[] a) {
		double result=Double.NEGATIVE_INFINITY;
		for (int i = 0; i < a.length; i++){
			// INvariant: result is the max of a[0..i-1]
			if (a[i] > result)
				result = a[i];
		}
		return result; // TODO
	}

	public static boolean find(double[] a, double x) {
		boolean b = false;
		for (int i = 0; i < a.length; i++)
		// INVARIANT: b is true if x is in a[0..i-1]
			if (a[i] == x) 
				{b = true; break;}

		return b;
	}

	public static int sumI(int[] a){
		int sum = 0;
		for (int i = 0; i < a.length; i++)
			sum += a[i];
		return sum;
	}

	public static int sum(int[] a){
		return sumAux(a, 0,0);
	}

	public static int sumAux(int[] a, int i, int sum){
		if (i == a.length) return sum;
		return sumAux(a, i+1, sum+a[i]);
	}

	// Precondition: j >=0
	static int f(int i, int j) {
		int rold = i+j;
		while (j >= 0) {
			// rold = i+j;
			i++;
			j--;
		}
		return i; // i = rold
	}


	public static double maxI (double[] a) {
		return 0;
	}

	public static double[] plusOne (double[] a) {
		return null; //TODO
	}
	public static double[] plusOneI (double[] a) {
		int N = a.length;
		double[] result = new double[N];
		for (int i = 0; i < N; i++)
			result[i] = a[i] + 1;
		return result;
	}

	public static void reverse (double[] a) {
		return; //TODO
	}
	public static void reverseI (double[] a) {
		int N = a.length;
		for (int i = 0; i < N / 2; i++) {
			double temp = a[i];
			a[i] = a[N - 1 - i];
			a[N - i - 1] = temp;
		}
	}

	public static void test (double[] a) {
		StdOut.format ("max: %f\n", maxI (a));
		double[] b = plusOne (a);
		StdOut.format ("plusOne: %s\n", Arrays.toString (b));
		reverse (a);
		StdOut.format ("reverse: %s\n", Arrays.toString (a));
	}
	public static void testI (double[] a) {
		StdOut.format ("max: %f\n", maxI (a));
		double[] b = plusOneI (a);
		StdOut.format ("plusOne: %s\n", Arrays.toString (b));
		reverseI (a);
		StdOut.format ("reverse: %s\n", Arrays.toString (a));
	}
	public static void main (String[] args) {
		double[] a = {-25 };
		double x = max2(null);
		// test(new double[] { 10, 20, 60, 40, 50 });
		// test(new double[] { 60, 20, 30, 40 });
		// test(new double[] { 10, 20, 60 });
		// test(new double[] { 10, 20 });
		// test(new double[] { 10 });
		// test(new double[] { });
	}	
}
