package algs12;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CounterImplTest {

    @Test
    public void testIncrement() {
        CounterImpl c = new CounterImpl();
        c.increment();
        c.increment();
        assertEquals(c.get(),2);

    }

    @Test
    public void testSet() {
        CounterImpl c = new CounterImpl();
        c.set(5);
        assertEquals(c.get(),4);

    }
}
