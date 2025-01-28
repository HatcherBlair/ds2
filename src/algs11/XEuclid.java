package algs11;
import stdlib.*;

public class XEuclid {

	static int gcd (int p, int q) {
		if (q == 0) return p;
		int r = p % q;
		return gcd (q, r);
	}

	public static void main (String[] args) {
		StdOut.print (gcd (1231234, 459286792));
	}

}
