package algs11;
import stdlib.*;

public class Immut {

    private final int i;
    private final Immut f;
    private int[] a;

    public Immut(int i, Immut f) {
        this.i = i;
        this.f = f;
        this.a = new int[10];
    }

    public int getI() {
        return i;
    }

    public Immut getF() {
        return f;
    }

    public void set(int i, int value) {
        a[i] = value;
    }

    public static void main(String[] args) {
        String s = new String("hello");
        String t = new String("hello");
        StdOut.println(s == t); // false
        StdOut.println(s.equals(t)); // true
    }


    
}
