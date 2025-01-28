package algs11;
import stdlib.*;

public class L1 {

    static int add(int x, int y) {
        return x + y;
    }

    static void swap(int x,int y) {
        int tmp =x;
        x=y;
        y=tmp;
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
       int[] a; // a is null
         a = new int[5];
         for (int x:a){
            StdOut.println(x);
        }
        for (int i=0;i<a.length;i++){
            StdOut.println(a[i]);
        }
        int[] b = new int[5];
        for (int i=0; i< a.length; i++){
            b[i] = a[i];
        }
        int[] c = {0,3,8,9}; // int[] c; c = new int[4]; c[0]=0; c[1]=3; c[2]=8; c[3]=9;



    }
    
}
