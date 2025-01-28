package algs35;
import stdlib.*;
/* ***********************************************************************
 *  Compilation:  javac Interpreter.java
 *  Execution:    java Interpreter
 *  Dependencies: In.java ST.java
 *
 *  Parses simple arithmetic expressions of the form z = x and
 *  z = x + y, where x and y can be symbolic variables or real
 *  numbers. Uses a symbol table to store the mapping between
 *  variable names and their values.
 *
 *
 *  % java Interpreter
 *  >> x := 34
 *  x = 34.0
 *
 *  >> y := 23 * x
 *  y := 782.0
 *
 *  >> z := x ^ y
 *  z := Infinity
 *
 *  >> z := y ^ 2
 *  z := 611524.0
 *
 *  >> x
 *  x := 34.0
 *
 *  >> x := sqrt 2
 *  x := 1.4142135623730951
 *
 *  >> <Ctrl-d>
 *
 *  Remarks
 *  -------
 *
 *   - Currently allows values on the LHS, e.g., 17 = x + y,
 *     and treats "17" as a variable name.
 *
 *
 *************************************************************************/

public class XInterpreter {

	public static void main(String[] args) {

		ST<String, Double> st = new ST<>();

		// read in one line at a time and parse
		String line;
		StdOut.print(">> ");
		while ((line = StdIn.readLine()) != null) {
			String[] tokens = line.split("\\s");

			// singe variable - just print out its value
			if (tokens.length == 1)  {
				String zvar = tokens[0];
				StdOut.println(zvar + " := " + st.get(zvar));
			}

			// z = x
			else if (tokens.length == 3) {
				String zvar = tokens[0];
				String eq   = tokens[1];
				String xvar = tokens[2];
				if (!eq.equals(":=")) throw new Error("Illegal assignment");
				Double x = st.get(xvar);
				if (x == null) x = Double.parseDouble(xvar);
				st.put(zvar, x);
				StdOut.println(zvar + " := " + st.get(zvar));
			}

			// z = function x
			else if (tokens.length == 4) {
				String zvar = tokens[0];
				String eq   = tokens[1];
				String func = tokens[2];
				String xvar = tokens[3];
				if (!eq.equals(":=")) throw new Error("Illegal assignment");
				Double x = st.get(xvar);
				if (x == null) x = Double.parseDouble(xvar);
				if      (func.equals("sin")) st.put(zvar, Math.sin(x));
				else if (func.equals("cos")) st.put(zvar, Math.cos(x));
				else if (func.equals("sqrt")) st.put(zvar, Math.sqrt(x));
				else if (func.equals("-")) st.put(zvar, -x);
				else throw new Error("Illegal function");
				StdOut.println(zvar + " := " + st.get(zvar));
			}

			// z = x + y
			else if (tokens.length == 5) {
				String zvar = tokens[0];
				String eq   = tokens[1];
				String xvar = tokens[2];
				String op   = tokens[3];
				String yvar = tokens[4];
				if (!eq.equals(":=")) throw new Error("Illegal assignment");
				Double x = st.get(xvar);
				Double y = st.get(yvar);
				if (x == null) x = Double.parseDouble(xvar);
				if (y == null) y = Double.parseDouble(yvar);
				if      (op.equals("+")) st.put(zvar, x + y);
				else if (op.equals("-")) st.put(zvar, x - y);
				else if (op.equals("*")) st.put(zvar, x * y);
				else if (op.equals("/")) st.put(zvar, x / y);
				else if (op.equals("^")) st.put(zvar, Math.pow(x, y));
				else throw new Error("Illegal operator");
				StdOut.println(zvar + " = " + st.get(zvar));
			}

			else throw new Error("Illegal expression");
			StdOut.println();
			StdOut.print(">> ");
		}

	}
}
