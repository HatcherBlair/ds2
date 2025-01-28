package algs13;

import org.junit.Test;
import static org.junit.Assert.*;

public class MyLInkedTest {

    @Test
    public void test1() {
        MyLinked3 list = new MyLinked3();
        list.addB(1);
        assertEquals(list.first.item,1.0,0.1);
    }

    @Test
    public void test2() {
        MyLinked3 list = new MyLinked3();
        list.addB(1);
        list.addB(2);
        assertEquals(list.first.item,1.0,0.1);
        assertEquals(list.first.next.item,2.0,0.1);
    }
    
}
