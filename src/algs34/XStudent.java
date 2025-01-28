package algs34;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import stdlib.*;

public class XStudent {
	int i;
	double d;
	int[] a;
	Object o;

	int hCode = -1;

	public boolean equals(Object y) {
		//Trace.draw ();
		if (y == this) return true;
		if (y == null) return false;
		if (y.getClass() != this.getClass()) return false;
		XStudent that = (XStudent) y;
		if (Integer.compare (this.i, that.i) != 0) return false;
		if (Double.compare (this.d, that.d) != 0) return false;
		if (! Arrays.equals (this.a, that.a)) return false;
		if (! Objects.equals (this.o, that.o)) return false;
		// don't use ((this.o).equals(that.o)) unless you know that (this.o!=null)
		return true;
	}
	public int hashCode() {
		if (hCode != -1) return hCode;
		hCode = 17;
		hCode = 31 * h + Integer.hashCode (i);
		hCode = 31 * h + Double.hashCode (d);
		hCode = 31 * h + Arrays.hashCode (a);
		hCode = 31 * h + Objects.hashCode (o);
		// don't use (o.hashCode()) unless you know that (o!=null)
		return hCode;
	}


	public XStudent (double d, int[] a, Object o) {
		this.d = d;
		this.a = a;
		this.o = o;
	}
	public static void main (String[] args) {
		//Trace.run ();
		Object p = new Object ();
		XStudent x = new XStudent (0.1, new int[] { 1,2,3 }, p);
		XStudent y = new XStudent (0.1, new int[] { 1,2,3 }, p);

		HashSet<XStudent> set = new HashSet<> ();
		set.add (x);
		x.i++;
		StdOut.println(set.contains(x));
		StdOut.println(set.contains(y));
		StdOut.format ("(x==y)=%b x.equals(y)=%b\n", x==y, Objects.equals(x,y));
		StdOut.format ("x=%s Objects.hashCode(x)=%x\n", x, Objects.hashCode (x));
		StdOut.format ("y=%s Objects.hashCode(y)=%x\n", y, Objects.hashCode (y));
	}
}


// recommendation: USE ONLY FINAL INSTANCE VARIABLES for calculating hashCode, EQUALS
// COMPARETO
// INSTANCE VARIABLES SHOULD BE FINAL UNLESS YOU HAVE A GOOD REASON TO CHANGE THEM