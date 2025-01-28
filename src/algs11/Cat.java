package algs11;
import stdlib.*;
/* ***********************************************************************
 *  Compilation:  javac Cat.java
 *  Execution:    java Cat input0.txt input1.txt ... output.txt
 *  Dependencies: In.java Out.java
 *
 *  Reads in text files specified as the first command-line
 *  parameters, concatenates them, and writes the result to
 *  filename specified as the last command line parameter.
 *
 *  % more in1.txt
 *  This is
 *
 *  % more in2.txt
 *  a tiny
 *  test.
 *
 *  % java Cat in1.txt in2.txt out.txt
 *
 *  % more out.txt
 *  This is
 *  a tiny
 *  test.
 *
 *************************************************************************/

public class Cat {

	public static void main(String[] args) {
		//args = new String[] { "data/ex1.txt", "data/ex2.txt", "data/ex3.txt", "data/ex4.txt", "/tmp/out.txt" };
		args = new String[] { "data/in1.txt", "data/in2.txt", "/tmp/out.txt" };

		Out out = new Out(args[args.length - 1]);
		for (int i = 0; i < args.length - 1; i++) {
			In in = new In(args[i]);
			String s = in.readAll();
			out.println(s);
			in.close();
		}
		out.close();
		StdOut.format ("Output is in file \"%s\"", args[args.length - 1]);
	}

}
