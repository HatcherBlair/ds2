package algs11;

public class NumberPlusIndex implements Comparable<NumberPlusIndex> {
    
    private final int value;
    private final int index;
    NumberPlusIndex(int v,int i) {
        value = v;
        index = i;
    }

    public int compareTo(NumberPlusIndex that) {
        if (this.value < that.value) return -1;
        if (this.value > that.value) return +1;
        return 0;
    }

    static NumberPlusIndex[] createArray(int[] a) {
        NumberPlusIndex[] b = new NumberPlusIndex[a.length];
        for (int i = 0; i < a.length; i++)
            b[i] = new NumberPlusIndex(a[i],i);
        return b;
    }

}
