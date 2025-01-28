package algs55; // section 5.5
import stdlib.*;
import java.awt.Color;
/* ***********************************************************************
 *  Compilation:  javac PictureDump.java
 *  Execution:    java PictureDump width height < file
 *  Dependencies: BinaryIn.java Picture.java
 *  Data file:    http://introcs.cs.princeton.edu/stdlib/abra.txt
 *
 *  Reads in a binary file and writes out the bits as w-by-h picture,
 *  with the 1 bits in black and the 0 bits in white.
 *
 *  % more abra.txt
 *  ABRACADABRA!
 *
 *  % java PictureDump 16 6 < abra.txt
 *
 *************************************************************************/

public class PictureDump {
	private static BinaryIn binaryIn;

	public static void main(String[] args) {
		binaryIn = new BinaryIn("data/mobydick.txt");
		//binaryIn = new BinaryIn("lib/Jama.jar");
		args = new String[] { "500", "500" };

		int width = Integer.parseInt(args[0]);
		int height = Integer.parseInt(args[1]);
		Picture pic = new Picture(width, height);
		int count = 0;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				pic.set(j, i, Color.RED);
				if (!binaryIn.isEmpty()) {
					count++;
					boolean bit = binaryIn.readBoolean();
					if (bit) pic.set(j, i, Color.BLACK);
					else     pic.set(j, i, Color.WHITE);
				}
			}
		}
		pic.show();
		StdOut.println(count + " bits");
	}
}
