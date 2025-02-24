package hw8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

record MyPair<T, U>(T x, U y) {
}

class PairSecond<T extends Comparable<T>> implements Comparator<MyPair<T, T>> {
    public int compare(MyPair<T, T> p1, MyPair<T, T> p2) {
        return p1.y().compareTo(p2.y());
    }
}

public class HWhelp {
    public static void main(String[] args) {

        MyPair<String, String> p1 = new MyPair<>("a", "b");
        MyPair<String, String> p2 = new MyPair<>("a", "c");
        MyPair<String, String> p3 = new MyPair<>("c", "a");
        MyPair<String, String> p4 = new MyPair<>("c", "d");

        ArrayList<MyPair<String, String>> list = new ArrayList<>();
        list.add(p1);
        list.add(p2);
        list.add(p3);
        list.add(p4);
        Collections.sort(list, new PairSecond<String>());
        System.out.println(list);
    }
}
