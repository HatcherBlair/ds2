package algs11;
public class XAutoboxingValueOf {
	public static String concat(String s, String t) {
		return (s + t);
	}
	@SuppressWarnings("removal")
	public static void main (String[] args) {
		{
			System.out.print ("\nString Dog:    ");
			String x1 = "Dog";
			String x2 = "Dog";
			String x3 = new String ("Dog");
			String x4 = new String ("Dog");

			System.out.format ("%-5b ", x1 == x2);
			System.out.format ("%-5b ", x1 == x3);
			System.out.format ("%-5b ", x3 == x4);

			String x3i = x3.intern ();
			String x4i = x4.intern ();
			System.out.format ("%-5b ", x1 == x3i);
			System.out.format ("%-5b ", x3i == x4i);
		}
		{
			System.out.print ("\nString ...:    ");
			String x1 = "Antidisestablishmentarianism";
			String x2 = "Antidisestablishmentarianism";
			String x3 = new String ("Antidisestablishmentarianism");
			String x4 = new String ("Antidisestablishmentarianism");

			System.out.format ("%-5b ", x1 == x2);
			System.out.format ("%-5b ", x1 == x3);
			System.out.format ("%-5b ", x3 == x4);

			String x3i = x3.intern ();
			String x4i = x4.intern ();
			System.out.format ("%-5b ", x1 == x3i);
			System.out.format ("%-5b ", x3i == x4i);
		}
		{
			System.out.print ("\nString + :     ");
			String x1 = "hi" + "mom";
			String x2 = "hi" + "mom";
			String x3 = new String ("hi" + "mom");
			String x4 = new String ("hi" + "mom");

			System.out.format ("%-5b ", x1 == x2);
			System.out.format ("%-5b ", x1 == x3);
			System.out.format ("%-5b ", x3 == x4);

			String x3i = x3.intern ();
			String x4i = x4.intern ();
			System.out.format ("%-5b ", x1 == x3i);
			System.out.format ("%-5b ", x3i == x4i);
		}
		{
			System.out.print ("\nString concat: ");
			String x1 = concat ("hi", "mom");
			String x2 = concat ("hi", "mom");
			String x3 = new String (concat ("hi", "mom"));
			String x4 = new String (concat ("hi", "mom"));

			System.out.format ("%-5b ", x1 == x2);
			System.out.format ("%-5b ", x1 == x3);
			System.out.format ("%-5b ", x3 == x4);

			String x3i = x3.intern ();
			String x4i = x4.intern ();
			System.out.format ("%-5b ", x1 == x3i);
			System.out.format ("%-5b ", x3i == x4i);
		}
		{
			System.out.print ("\nCharacter a:   ");
			Character x1 = 'a';
			Character x2 = 'a';
			Character x3 = new Character ('a');
			Character x4 = new Character ('a');

			System.out.format ("%-5b ", x1 == x2);
			System.out.format ("%-5b ", x1 == x3);
			System.out.format ("%-5b ", x3 == x4);

			Character x3i = Character.valueOf (x3);
			Character x4i = Character.valueOf (x4);
			System.out.format ("%-5b ", x1 == x3i);
			System.out.format ("%-5b ", x3i == x4i);
		}
		{
			System.out.print ("\nCharacter \u13A7:   ");
			Character x1 = '\u13A7';
			Character x2 = '\u13A7';
			Character x3 = new Character ('\u13A7');
			Character x4 = new Character ('\u13A7');

			System.out.format ("%-5b ", x1 == x2);
			System.out.format ("%-5b ", x1 == x3);
			System.out.format ("%-5b ", x3 == x4);

			Character x3i = Character.valueOf (x3);
			Character x4i = Character.valueOf (x4);
			System.out.format ("%-5b ", x1 == x3i);
			System.out.format ("%-5b ", x3i == x4i);
		}
		{
			System.out.print ("\nInteger 0:     ");
			Integer x1 = 0;
			Integer x2 = 0;
			Integer x3 = new Integer (0);
			Integer x4 = new Integer (0);

			System.out.format ("%-5b ", x1 == x2);
			System.out.format ("%-5b ", x1 == x3);
			System.out.format ("%-5b ", x3 == x4);

			Integer x3i = Integer.valueOf (x3);
			Integer x4i = Integer.valueOf (x4);
			System.out.format ("%-5b ", x1 == x3i);
			System.out.format ("%-5b ", x3i == x4i);
		}
		{
			System.out.print ("\nInteger 12:    ");
			Integer x1 = Integer.valueOf(12);
			Integer x2 = Integer.valueOf(12);
			Integer x3 = new Integer (12);
			Integer x4 = new Integer (12);

			System.out.format ("%-5b ", x1 == x2);
			System.out.format ("%-5b ", x1 == x3);
			System.out.format ("%-5b ", x3 == x4);

			Integer x3i = Integer.valueOf (x3);
			Integer x4i = Integer.valueOf (x4);
			System.out.format ("%-5b ", x1 == x3i);
			System.out.format ("%-5b ", x3i == x4i);
		}
		{
			System.out.print ("\nInteger 13297: ");
			Integer x1 = 13297;
			Integer x2 = 13297;
			Integer x3 = new Integer (13297);
			Integer x4 = new Integer (13297);

			System.out.format ("%-5b ", x1 == x2);
			System.out.format ("%-5b ", x1 == x3);
			System.out.format ("%-5b ", x3 == x4);

			Integer x3i = Integer.valueOf (x3);
			Integer x4i = Integer.valueOf (x4);
			System.out.format ("%-5b ", x1 == x3i);
			System.out.format ("%-5b ", x3i == x4i);
		}
		{
			System.out.print ("\nDouble 0.0:    ");
			Double x1 = 0.0;
			Double x2 = 0.0;
			Double x3 = new Double (0);
			Double x4 = new Double (0);

			System.out.format ("%-5b ", x1 == x2);
			System.out.format ("%-5b ", x1 == x3);
			System.out.format ("%-5b ", x3 == x4);

			Double x3i = Double.valueOf (x3);
			Double x4i = Double.valueOf (x4);
			System.out.format ("%-5b ", x1 == x3i);
			System.out.format ("%-5b ", x3i == x4i);
		}
	}
}
