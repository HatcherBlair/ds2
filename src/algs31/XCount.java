package algs31;
import stdlib.*;
import algs35.ST;

public class XCount {

	public static void main(String[] args)
	{
		ST<String, Integer> st = new ST<>();
		for (int i = 0; !StdIn.isEmpty(); i++)
		{
			String key = StdIn.readString();
			st.put(key, i);
		}
		for (String s : st.keys())
			StdOut.println(s + " " + st.get(s));
	}
}
