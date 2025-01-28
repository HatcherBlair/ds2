package algs11;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.TreeSet;

import stdlib.StdOut;

public class Point2 implements Comparable<Point2> {
    final int x;
    final int y;

    Point2 (int x, int y) {
        this.x = x;
        this.y = y;
    }

    int getX() { return x; }
    int getY() { return y; }

    public int compareTo(Point2 that) {
        if (this.y < that.y) return -1;
        if (this.y > that.y) return +1;
        if (this.x < that.x) return -1;
        if (this.x > that.x) return +1;
        return 0;
    }

    public boolean equals(Object that) {
        if (this == that) return true;
        if (that == null) return false;
        if (this.getClass() != that.getClass()) return false;
        Point2 thatPoint = (Point2) that;
        
        return this.x == thatPoint.x && this.y == thatPoint.y;
    }

    public static void main(String[] args){
        Point2 p = new Point2 (1, 2);
        Point2 q = new Point2 (1, 2);

        ArrayList<Point2> list = new ArrayList<Point2>();
        list.add(p);
        StdOut.println(p.equals(q ));
        StdOut.println(list.contains(q)); 

        TreeSet<Point2> set = new TreeSet<Point2>();
        set.add(p);

    }
    
}
