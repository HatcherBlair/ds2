package algs13;
import java.io.File;
import stdlib.*;
/* ***********************************************************************
 *  Compilation:  javac Directory.java
 *  Execution:    java Directory directory-name
 *  Dependencies: Queue.java
 *
 *  Prints out all of the files in the given directory and any
 *  subdirectories in level-order by using a queue. Also prints
 *  out their file sizes in bytes.
 *
 *  % java Directory .
 *
 *************************************************************************/

public class XDirectory {

	public static void main(String[] args) {
		Queue<File> q = new Queue<>();
		File root = new File(args[0]);     // root directory
		q.enqueue(root);
		while (!q.isEmpty()) {
			File directory = q.dequeue();
			File[] files = directory.listFiles();
			for (File file : files) {
				if (file.isDirectory()) q.enqueue(file);
				else StdOut.println(file.length() + ":\t" + file);
			}
		}
	}

}
