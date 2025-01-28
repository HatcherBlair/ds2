package algs11;
import stdlib.*;

public class Lecture1 {

    public static int[] create(int n) {
        return new int[n];

    }

    public static void print(int[] l){
        for (int a:l) {
            StdOut.println(a);
        }
    }

    public static void swap(int x, int y) {
        int t = x;
        x = y;
        y = t;
    }

    // PRECONDITION: l !=NULL
    // RETURN: sum
    // POSTCONDITION: l does not change
    public static int sum(int[] l) {
       int x = 0;
         for (int a:l ) {
              x +=a;
         }
         return x;
    }


    public static void main(String[] args) {
        int[] a = new int[0];
        sum(a);

    }
    
}
