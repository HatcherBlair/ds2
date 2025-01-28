package algs34;

import java.util.Arrays;
import java.util.Objects;

public class XBuiltInHashcodes {
	public static void main (String[] args) {
		System.out.format ("int\n");
		System.out.format ("%x ", Integer.hashCode (0));
		System.out.format ("%x ", Integer.hashCode (1));
		System.out.format ("%x ", Integer.hashCode (2));
		System.out.format ("%x ", Integer.hashCode (3));
		System.out.format ("%x ", Integer.hashCode (Integer.MAX_VALUE));
		System.out.format ("%x ", Integer.hashCode (-0));
		System.out.format ("%x ", Integer.hashCode (-1));
		System.out.format ("%x ", Integer.hashCode (-2));
		System.out.format ("%x ", Integer.hashCode (Integer.MIN_VALUE));
		System.out.println ();

		System.out.format ("\nlong\n");
		System.out.format ("%x ", Long.hashCode (0));
		System.out.format ("%x ", Long.hashCode (1));
		System.out.format ("%x ", Long.hashCode (2));
		System.out.format ("%x ", Long.hashCode (Long.MAX_VALUE));
		System.out.format ("%x ", Long.hashCode (-0));
		System.out.format ("%x ", Long.hashCode (-1));
		System.out.format ("%x ", Long.hashCode (-2));
		System.out.format ("%x ", Long.hashCode (Long.MIN_VALUE));
		System.out.println ();

		System.out.format ("\nfloat\n");
		System.out.format ("%x ", Float.hashCode (0));
		System.out.format ("%x ", Float.hashCode (1));
		System.out.format ("%x ", Float.hashCode (2));
		System.out.format ("%x ", Float.hashCode (Float.MAX_VALUE));
		System.out.format ("%x ", Float.hashCode (-0));
		System.out.format ("%x ", Float.hashCode (-1));
		System.out.format ("%x ", Float.hashCode (-2));
		System.out.format ("%x ", Float.hashCode (Float.MIN_VALUE));
		System.out.println ();

		System.out.format ("\ndouble\n");
		System.out.format ("%x ", Double.hashCode (0));
		System.out.format ("%x ", Double.hashCode (1));
		System.out.format ("%x ", Double.hashCode (2));
		System.out.format ("%x ", Double.hashCode (Double.MAX_VALUE));
		System.out.format ("%x ", Double.hashCode (-0));
		System.out.format ("%x ", Double.hashCode (-1));
		System.out.format ("%x ", Double.hashCode (-2));
		System.out.format ("%x ", Double.hashCode (Double.MIN_VALUE));
		System.out.println ();

		System.out.format ("\nchar\n");
		System.out.format ("%x ", Character.hashCode ('0'));
		System.out.format ("%x ", Character.hashCode ('1'));
		System.out.format ("%x ", Character.hashCode ('2'));
		System.out.println ();

		System.out.format ("\nstring\n");
		System.out.format ("%x ", Objects.hashCode ("0"));
		System.out.format ("%x ", Objects.hashCode ("1"));
		System.out.format ("%x ", Objects.hashCode ("2"));
		System.out.println ();

		System.out.format ("\nstring decimal\n");
		System.out.format ("%d ", Objects.hashCode ("0"));
		System.out.format ("%d ", Objects.hashCode ("1"));
		System.out.format ("%d ", Objects.hashCode ("2"));
		System.out.println ();

		System.out.format ("%d (=%d*31 + %d) ", Objects.hashCode ("00"), (int)'0', (int)'0');
		System.out.format ("%d (=%d*31 + %d) ", Objects.hashCode ("01"), (int)'0', (int)'1');
		System.out.format ("%d (=%d*31 + %d) ", Objects.hashCode ("02"), (int)'0', (int)'2');
		System.out.println ();

		System.out.format ("\nstring\n");
		System.out.format ("%d ", Objects.hashCode ("0"));
		System.out.format ("%d ", Objects.hashCode ("00"));
		System.out.format ("%d ", Objects.hashCode ("000"));
		System.out.format ("%d ", Objects.hashCode ("0000"));
		System.out.format ("%d ", Objects.hashCode ("00000"));
		System.out.format ("%d ", Objects.hashCode ("000000"));
		System.out.format ("%d ", Objects.hashCode ("0000000"));
		System.out.format ("%d ", Objects.hashCode ("00000000"));
		System.out.format ("%d ", Objects.hashCode ("000000000"));
		System.out.println ();

		{
			System.out.format ("\nfloat 0's\n");
			float x = Float.intBitsToFloat (0);
			float y = Float.intBitsToFloat (0x80000000);
			System.out.format ("(x==y)=%b Float.compare(x,y)=%d\n", x==y, Float.compare (x, y));
			System.out.format ("x=%s Float.hashCode(x)=%x\n", x, Float.hashCode (x));
			System.out.format ("y=%s Float.hashCode(y)=%x\n", y, Float.hashCode (y));
		}
		{
			System.out.format ("\nfloat NaN's\n");
			float x = Float.NaN;
			float y = Float.NaN;
			System.out.format ("(x==y)=%b Float.compare(x,y)=%d\n", x==y, Float.compare (x, y));
			System.out.format ("x=%s Float.hashCode(x)=%x\n", x, Float.hashCode (x));
			System.out.format ("y=%s Float.hashCode(y)=%x\n", y, Float.hashCode (y));
		}
		{
			System.out.format ("\ndouble 0's\n");
			double x = Double.longBitsToDouble (0L);
			double y = Double.longBitsToDouble (0x8000000000000000L);
			System.out.format ("(x==y)=%b Double.compare(x,y)=%d\n", x==y, Double.compare (x, y));
			System.out.format ("x=%s Double.hashCode(x)=%x\n", x, Double.hashCode (x));
			System.out.format ("y=%s Double.hashCode(y)=%x\n", y, Double.hashCode (y));
		}
		{
			System.out.format ("\ndouble NaN's\n");
			double x = Double.NaN;
			double y = Double.NaN;
			System.out.format ("(x==y)=%b Double.compare(x,y)=%d\n", x==y, Double.compare (x, y));
			System.out.format ("x=%s Double.hashCode(x)=%x\n", x, Double.hashCode (x));
			System.out.format ("y=%s Double.hashCode(y)=%x\n", y, Double.hashCode (y));
		}

		/*
		 * Objects and Arrays act like this:
		 *
		 * Objects.equals(a, b) { return (a == b) || (a != null && a.equals(b)); }
		 *   if (a==b) return true;
		 *   if (a==null || b==null || (! a.equals(b)) return false;
		 *   return true;
		 * }
		 * Objects.hashCode(a)  {
		 *   if (a==null) return 0;
		 *   return a.hashCode();
		 * }
		 *
		 * Arrays.equals(a, b) {
		 *   if (a==b) return true;
		 *   if (a==null || b==null || (a.length != b.length)) return false;
		 *   for (int i=0; i<a.length; i++) if (! Objects.equals(a[i],b[i])) return false;
		 *   return true;
		 * }
		 * Arrays.hashCode(a) {
		 *   if (a == null) return 0;
		 *   int result = 1;
		 *   for (Object element : a) result = 31 * result + Objects.hashCode(element);
		 *   return result;
		 * }
		 *
		 * Arrays.deepEquals is similar, but recursively calls Arrays.equals(a[i],b[i]) for nested arrays
		 * Arrays.deepHashCode is similar, but recursively calls Arrays.hashCode(element) for nested arrays
		 */
		{
			System.out.format ("\nObject\n");
			Object x = new Object ();
			Object y = new Object ();
			System.out.format ("(x==y)=%b Objects.equals(x,y)=%b\n", x==y, Objects.equals (x,y));
			System.out.format ("x=%s Objects.hashCode(x)=%x\n", x, Objects.hashCode (x));
			System.out.format ("y=%s Objects.hashCode(y)=%x\n", y, Objects.hashCode (y));
		}
		{
			System.out.format ("\nObject null\n");
			Object x = null;
			Object y = null;
			System.out.format ("(x==y)=%b Objects.equals(x,y)=%b\n", x==y, Objects.equals (x,y));
			System.out.format ("x=%s Objects.hashCode(x)=%x\n", x, Objects.hashCode (x));
			System.out.format ("y=%s Objects.hashCode(y)=%x\n", y, Objects.hashCode (y));
		}
		{
			System.out.format ("\nint[]\n");
			int a[] = new int[4];
			int b[] = new int[4];
			System.out.format ("(a==b)=%b Objects.equals(a,b)=%b Arrays.equals(a,b)=%b\n", a==b, Objects.equals (a,b), Arrays.equals(a,b));
			System.out.format ("a=%s Objects.hashCode(a)=%x Arrays.hashCode(a)=%x\n", a, Objects.hashCode (a), Arrays.hashCode (a));
			System.out.format ("b=%s Objects.hashCode(b)=%x Arrays.hashCode(b)=%x\n", b, Objects.hashCode (b), Arrays.hashCode (b));
		}
		{
			System.out.format ("\nint[] null\n");
			int a[] = null;
			int b[] = null;
			System.out.format ("(a==b)=%b Objects.equals(a,b)=%b Arrays.equals(a,b)=%b\n", a==b, Objects.equals (a,b), Arrays.equals(a,b));
			System.out.format ("a=%s Objects.hashCode(a)=%x Arrays.hashCode(a)=%x\n", a, Objects.hashCode (a), Arrays.hashCode (a));
			System.out.format ("b=%s Objects.hashCode(b)=%x Arrays.hashCode(b)=%x\n", b, Objects.hashCode (b), Arrays.hashCode (b));
		}
		{
			System.out.format ("\nObject[] (for most object arrays, Arrays.equals is enough)\n");
			String a[] = new String[] { new String("hi") };
			String b[] = new String[] { new String("hi") };
			System.out.format ("(a==b)=%b Objects.equals(a,b)=%b Arrays.equals(a,b)=%b Arrays.deepEquals(a,b)=%b\n", a==b, Objects.equals (a,b), Arrays.equals(a,b), Arrays.deepEquals(a,b));
			System.out.format ("a=%s Objects.hashCode(a)=%x Arrays.hashCode(a)=%x Arrays.deepHashCode(a)=%x\n", a, Objects.hashCode (a), Arrays.hashCode (a), Arrays.deepHashCode (a));
			System.out.format ("b=%s Objects.hashCode(b)=%x Arrays.hashCode(b)=%x Arrays.deepHashCode(b)=%x\n", b, Objects.hashCode (b), Arrays.hashCode (b), Arrays.deepHashCode (b));
		}
		{
			System.out.format ("\nint[][] (multidimensional arrays need Arrays.deepEquals)\n");
			int a[][] = new int[4][4];
			int b[][] = new int[4][4];
			System.out.format ("(a==b)=%b Objects.equals(a,b)=%b Arrays.equals(a,b)=%b Arrays.deepEquals(a,b)=%b\n", a==b, Objects.equals (a,b), Arrays.equals(a,b), Arrays.deepEquals(a,b));
			System.out.format ("a=%s Objects.hashCode(a)=%x Arrays.hashCode(a)=%x Arrays.deepHashCode(a)=%x\n", a, Objects.hashCode (a), Arrays.hashCode (a), Arrays.deepHashCode (a));
			System.out.format ("b=%s Objects.hashCode(b)=%x Arrays.hashCode(b)=%x Arrays.deepHashCode(b)=%x\n", b, Objects.hashCode (b), Arrays.hashCode (b), Arrays.deepHashCode (b));
		}
	}
}
