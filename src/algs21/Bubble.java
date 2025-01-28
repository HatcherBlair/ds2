package algs21;

import stdlib.*;

public class Bubble {

    // generic method to exchange two elements in an array
    // works for any type T
   private static <T> void exch(T[] a, int i, int j) {
        T temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static <T extends Comparable<T>> boolean less(T v, T w) {
        return (v.compareTo(w) < 0);
    }
    public static <T extends Comparable<T>> void bubblesort(T[] a){
        int N = a.length;
        for (int i = 0; i < N; i++) {
            // bubble the largest element to the end
            for (int j = 1; j < N-i; j++) {
                if (less(a[j],a[j-1])) {
                    exch(a, j, j - 1);
                }
            }
        }
    }

    
    

    public static void main(String[] args){
        Integer[] a = {8,9,10,1,2,3};
        String[] b = {"a","b","c","d","e","f"};
        bubblesort(a);
        bubblesort(b);
        for (int x:a)
            StdOut.print(x+" ");

        Object[] c = {new Object(), new Object()};
        // bubblesort(c); 
    }


    
}
