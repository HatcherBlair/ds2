package algs11;
import stdlib.*;
/* ***********************************************************************
 *  Compilation:  javac ZRightTriangle.java
 *  Execution:    java ZRightTriangle
 *
 *  Draws a right triangle and a circumscribing circle.
 *
 *************************************************************************/

public class XDrawRightTriangle {

	public static void main(String[] args) {
		StdDraw.square(.5, .5, .5);
		StdDraw.setPenColor(StdDraw.BLUE);
		StdDraw.line(.5, .5, .9, .5);
		StdDraw.line(.9, .5, .5, .8);
		StdDraw.line(.5, .8, .5, .5);
		StdDraw.circle(.7, .65, .25);
	}
}
