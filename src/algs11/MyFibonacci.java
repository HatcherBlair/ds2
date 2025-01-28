package algs11;
import stdlib.*;

public class MyFibonacci {
	/*
  Assignment1:

  -----------------------------------------------------------------------
  1.1.16 Give the value of exR1(6):

    public static String exR1(int n)
    {
       if (n <= 0) return "";
       return exR1(n-3) + n + exR1(n-2) + n;
    }

  ANSWER:

  -----------------------------------------------------------------------
  1.1.18 Consider the following recursive function:

    public static int mystery(int a, int b)
    {
       if (b == 0)     return 0;
       if (b % 2 == 0) return mystery(a+a, b/2);
       return mystery(a+a, b/2) + a;
    }

  What are the values of mystery(2, 25) and mystery(3, 11)?

  ANSWER:

  Given positive integers a and b,describe what value mystery(a, b) computes.

  ANSWER:

  Answer the same question, but replace + with * and replace return 0 with return 1.

  ANSWER:

  -----------------------------------------------------------------------
  1.1.19 Run the function runF, below, on your computer.  Let it run for one hour.
  You will get the printout for the first few values of N very quickly, but it
  will slow down after a while.  What is the largest value of N that is printed
  after one hour?

  ANSWER:

  Develop a better implementation of F(N) that saves computed values in an array of type "long[]".

  ANSWER THIS PROBLEM BY COMPLETING THE FUNCTION myF, BELOW.

	 */

	public static long F(int N) {
		if (N == 0) return 0;
		if (N == 1) return 1;
		return F(N-1) + F(N-2);
	}
	public static void runF() {
		for (int N = 0; N < 100; N++)
			StdOut.println(N + " " + F(N));
	}

	public static long myF(int N) {
		// TODO
		return 0;
	}
	public static void runMyF() {
		for (int N = 0; N < 100; N++)
			StdOut.println(N + " " + myF(N));
	}

	// to run a single function, just comment out the others using two slashes
	public static void main(String[] args) {
		runF ();
		// runMyF ();
	}
}

