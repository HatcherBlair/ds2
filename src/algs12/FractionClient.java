package algs12;

import stdlib.StdOut;

public class FractionClient {
    public static void main(String[] args){
    Fraction f1 = new Fraction(1, 2);
    Fraction f2 = new Fraction(1, 2);
    StdOut.println(f1==f2); // false
    StdOut.println(f1.equals(f2)); // true
    }
}
