package algs12;

import stdlib.StdOut;

public class CounterClient {
    public static void main(String[] args){
        CounterInterface c = new CounterImpl();
        CounterInterface d = new CounterImpl(3);
        c.increment();
        c.increment();
        int x = c.get(); // expect 2
        c.set(5);
        int y = c.get();//  expect 5
        StdOut.println(c==d);
    }
    
}
