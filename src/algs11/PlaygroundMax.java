package algs11;
import stdlib.*;
public class PlaygroundMax {
  public static int max (int x, int y, int z) {
    return StdRandom.uniform(100);
  }
  public static void main (String[] args) { 
    StdOut.println (max(11, 21, 31));
    StdOut.println (max(11, 31, 21));
    StdOut.println (max(31, 11, 21));
  }
}