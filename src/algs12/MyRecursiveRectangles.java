package algs12;

import java.awt.Color;
import stdlib.*;

/**
 * For this assignment, you must do two things:
 *
 * 1. Complete the MyRectangle class at the bottom of this file. It is not a
 * public class, but don't worry about that. I've put two classes in one file so
 * that you need only hand in a single file.
 *
 * In particular, you should get the testEquals method to work without throwing
 * any exceptions.
 *
 * 2. Modify the draw method of MyRecursiveRectangles so that it behaves as
 * demonstrated here:
 *
 * <pre>
 * http://fpl.cs.depaul.edu/jriely/ds1/extras/RecursiveRectangles.mp4
 * </pre>
 *
 * To do this only requires very minor modifications to the function.
 *
 * It is not necessary, but if you want to learn more about the drawing classes,
 * see here:
 *
 * <pre>
 * http://fpl.cs.depaul.edu/jriely/ds1/code/doc-public/stdlib/StdDraw.html
 * http://introcs.cs.princeton.edu/java/15inout/
 * </pre>
 */

public class MyRecursiveRectangles {
	public static Color randomColor () {
		int r = StdRandom.uniform (256);
		int g = StdRandom.uniform (256);
		int b = StdRandom.uniform (256);
		return new Color (r, g, b);
	}
	public static final int VARIANCE = 60;
	public static int randomByteAbove (int i) {
		return StdRandom.uniform (Math.max (0, i - 1), Math.min (i + VARIANCE, 256));
	}
	public static int randomByteBelow (int i) {
		return StdRandom.uniform (Math.max (0, i - VARIANCE), Math.min (i + 1, 256));
	}
	public static Color randomColorLight (Color c) {
		int r = randomByteAbove (c.getRed ());
		int g = randomByteAbove (c.getGreen ());
		int b = randomByteAbove (c.getBlue ());
		return new Color (r, g, b);
	}
	public static Color randomColorDark (Color c) {
		int r = randomByteBelow (c.getRed ());
		int g = randomByteBelow (c.getGreen ());
		int b = randomByteBelow (c.getBlue ());
		return new Color (r, g, b);
	}
	public static void draw (MyRect rect, int level, Color c) {
		// TODO:
		// edit this function so that it recursively draws levels from here down to level 0,
		//   decrementing by 1 each time
		// Optionally, you can also stop recursing if the area of the rectangle is less than 0.1

		// The following line might help while debugging:
		//StdOut.format ("rect=%s, height=%f width=%f area=%f xmid=%f ymid=%f\n", rect, rect.height (), rect.width (), rect.area (), rect.xmid (), rect.ymid ());
		StdDraw.setPenColor (c);
		rect.drawEllipse ();
		StdDraw.show (50);
		if (level % 2 == 0) {
			double xmid = StdRandom.uniform (rect.xmin (), rect.xmax ());
			StdDraw.setPenColor (StdDraw.BLACK);
			StdDraw.line (xmid, rect.ymin (), xmid, rect.ymax ());
			MyRect left = new MyRect (rect.xmin (), rect.ymin (), xmid, rect.ymax ());
			MyRect right = new MyRect (xmid, rect.ymin (), rect.xmax (), rect.ymax ());
			StdDraw.setPenColor (randomColorLight (c));
			left.drawEllipse ();
			StdDraw.setPenColor (randomColorDark (c));
			right.drawEllipse ();
		} else {
			double ymid = StdRandom.uniform (rect.ymin (), rect.ymax ());
			StdDraw.setPenColor (StdDraw.BLACK);
			StdDraw.line (rect.xmin (), ymid, rect.xmax (), ymid);
			MyRect below = new MyRect (rect.xmin (), rect.ymin (), rect.xmax (), ymid);
			MyRect above = new MyRect (rect.xmin (), ymid, rect.xmax (), rect.ymax ());
			StdDraw.setPenColor (randomColorLight (c));
			below.drawEllipse ();
			StdDraw.setPenColor (randomColorDark (c));
			above.drawEllipse ();
		}
	}
	public static void testEquals () {
		// this method should not throw any exceptions!
		MyRect r1 = new MyRect (1, 1, 2, 2);
		if (!r1.equals (r1)) {
			throw new Error ();
		}
		if (!r1.equals (new MyRect (1, 1, 2, 2))) {
			throw new Error ();
		}
		if (r1.equals (null)) {
			throw new Error ();
		}
		if (r1.equals (new Object ())) {
			throw new Error ();
		}
		if (r1.equals (new MyRect (0, 1, 2, 2))) {
			throw new Error ();
		}
		if (r1.equals (new MyRect (1, 0, 2, 2))) {
			throw new Error ();
		}
		if (r1.equals (new MyRect (1, 1, 3, 2))) {
			throw new Error ();
		}
		if (r1.equals (new MyRect (1, 1, 2, 3))) {
			throw new Error ();
		}
	}

	public static final double DIM = 1.0;
	public static void main (String[] args) {
		testEquals();

		StdDraw.setCanvasSize (800, 800); // in pixels
		StdDraw.setXscale (-DIM, DIM);
		StdDraw.setYscale (-DIM, DIM);
		MyRect rect = new MyRect (-DIM, -DIM, DIM, DIM);

		//StdRandom.setSeed (0); // uncomment this if you want the same behavior every time you run the program
		Color initialColor = randomColor (); //new Color(127,127,127)
		//long seed = StdRandom.getSeed ();
		for (int numLevels = 1; numLevels < 8; numLevels += 1) {
			//StdRandom.setSeed (seed);
			StdDraw.clear ();
			draw (rect, numLevels, initialColor);
			StdDraw.show (1000);
		}
	}
}

// An axis aligned rectangle
class MyRect {
	// TODO: add fields

	// construct the axis-aligned rectangle "[xmin, xmax] x [ymin, ymax]"
	public MyRect (double xmin, double ymin, double xmax, double ymax) {
		if (xmax < xmin || ymax < ymin) {
			throw new IllegalArgumentException ("Invalid rectangle");
		}
		// TODO: set the fields
	}

	// accessor methods for 4 coordinates
	public double xmin () {
		return /* TODO */0;
	}
	public double ymin () {
		return /* TODO */0;
	}
	public double xmax () {
		return /* TODO */0;
	}
	public double ymax () {
		return /* TODO */0;
	}

	// width, height, area of rectangle
	public double width () {
		return /* TODO */0;
	}
	public double height () {
		return /* TODO */0;
	}
	public double area () {
		return /* TODO */0;
	}

	// halfway between xmin and xmax
	public double xmid () {
		return /* TODO */0;
	}
	// halfway between ymin and ymax
	public double ymid () {
		return /* TODO */0;
	}

	// follow the recipe given in the textbook (pp 102-103) to implement equals
	public boolean equals (Object y) {
		return /* TODO */false;
	}

	public void drawEllipse () {
		StdDraw.filledEllipse (xmid (), ymid (), width () / 2, height () / 2);
	}
	public void drawRectangle () {
		StdDraw.filledRectangle (xmid (), ymid (), width () / 2, height () / 2);
	}
	public String toString () {
		return "[" + xmin () + ", " + xmax () + "] x [" + ymin () + ", " + ymax () + "]";
	}
}
