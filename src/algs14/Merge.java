package algs14;

import java.util.Arrays;
import java.util.List;
import java.util.Iterator;

public class Merge {


    // Preconditions: l is a list of integer arrays, all sorted in increasing order
    // Postconditions: returns a new array containing all the elements of the arrays in l
    //                in increasing order

    static int[] mergeArrays1(List<int[]> l){
        int  sum =0;
        for (int[] a : l) sum += a.length;
        int[] result = new int[sum];
        int i = 0;
        for (int[] a : l) {
            for (int j = 0; j < a.length; j++) {
                result[i++] = a[j];
            }
        }
        Arrays.sort(result);
        return result;

    }

    static int[] mergeArray2(int[][] l){
        
        int[] result = l[0];
        for (int i = 1; i < l.length; i++) {
            result = merge(result, l[i]);
        }
        return result;

    }

    static int[] mergeArray3(int[][] l){
        return mergeArray3(l, 0, l.length-1);
    }

    static int[] mergeArray3(int[][] l, int lo, int hi){
        if (lo == hi) return l[lo];
        int mid = (lo + hi) / 2;
        int[] l1 = mergeArray3(l, lo, mid);
        int[] l2 = mergeArray3(l, mid+1, hi);
        return merge(l1, l2);
    }
    
// precondition: l and m are sorted in increasing order
    // postcondition: returns a new array containing all the elements of l and m
    //                in increasing order
 static int[] merge(int[] l, int[] m) {
        int[] result = new int[l.length + m.length];
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < l.length && j < m.length) {
            if (l[i] < m[j]) {
                result[k++] = l[i++];
            } else {
                result[k++] = m[j++];
            }
        }
        while (i < l.length) {
            result[k++] = l[i++];
        }
        while (j < m.length) {
            result[k++] = m[j++];
        }
        return result;
    }

    public static void main(String[] args) {
        int[] a = {1, 3, 5, 7, 9};
        int[] b = {2, 4, 6, 8, 10};
        int[] c = merge(a, b);
        for (int i = 0; i < c.length; i++) {
            System.out.println(c[i]);
        }
    }
    
}
