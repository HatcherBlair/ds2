package hw5;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

public class HW5Test {

	@Rule
	public Timeout globalTimeout = Timeout.seconds(10);

	Price p101 = new Price(1, 1);
	Price p202 = new Price(2, 2);
	Price p303 = new Price(3, 3);
	Price p404 = new Price(4, 4);
	Price p505 = new Price(5, 5);

	@Test
	public void test05Exception() {
		PriceQueue pq = new PriceQueue();
		try {
			pq.dequeue();
			fail();
		} catch (NoSuchElementException e) {
		}
		assertTrue(pq.enqueue(p101));
		assertEquals(p101, pq.dequeue());
		try {
			pq.dequeue();
			fail();
		} catch (NoSuchElementException e) {
		}
		pq = new PriceQueue();
		assertTrue(pq.enqueue(p101));
		assertTrue(pq.delete(p101));
		try {
			pq.dequeue();
			fail();
		} catch (NoSuchElementException e) {
		}
	}

	@Test
	public void test05EnqDeqDel() {
		PriceQueue pq = new PriceQueue();
		assertTrue(pq.enqueue(p101));
		assertFalse(pq.isEmpty());
		assertEquals(1, pq.size());
		assertEquals(p101, pq.dequeue());
		assertTrue(pq.isEmpty());
		assertEquals(0, pq.size());
		assertFalse(pq.delete(p101));
		assertFalse(pq.delete(p202));
	}

	@Test
	public void test05EnqDelDeq() {
		PriceQueue pq = new PriceQueue();
		assertTrue(pq.enqueue(p101));
		assertFalse(pq.isEmpty());
		assertEquals(1, pq.size());
		assertFalse(pq.delete(p202));
		assertTrue(pq.delete(p101));
		assertTrue(pq.isEmpty());
		assertEquals(0, pq.size());
		try {
			pq.dequeue();
			fail();
		} catch (NoSuchElementException e) {
		}
		assertFalse(pq.delete(p101));
	}

	@Test
	public void test05EnqEnqDel2Deq() {
		PriceQueue pq = new PriceQueue();
		assertTrue(pq.enqueue(p101));
		assertTrue(pq.enqueue(p202));
		assertFalse(pq.isEmpty());
		assertEquals(2, pq.size());
		assertTrue(pq.delete(p202));
		assertFalse(pq.isEmpty());
		assertEquals(1, pq.size());
		assertEquals(p101, pq.dequeue());
		assertEquals(0, pq.size());
		assertFalse(pq.delete(p101));
		assertFalse(pq.delete(p202));

		assertTrue(pq.enqueue(p202));
		assertTrue(pq.enqueue(p101));
		assertFalse(pq.isEmpty());
		assertEquals(2, pq.size());
		assertTrue(pq.delete(p202));
		assertFalse(pq.isEmpty());
		assertEquals(1, pq.size());
		assertEquals(p101, pq.dequeue());
		assertEquals(0, pq.size());
		assertFalse(pq.delete(p101));
		assertFalse(pq.delete(p202));
	}

	@Test
	public void test05EnqEnqDel1Deq() {
		PriceQueue pq = new PriceQueue();
		assertTrue(pq.enqueue(p101));
		assertTrue(pq.enqueue(p202));
		assertFalse(pq.isEmpty());
		assertEquals(2, pq.size());
		assertTrue(pq.delete(p101));
		assertFalse(pq.isEmpty());
		assertEquals(1, pq.size());
		assertEquals(p202, pq.dequeue());
		assertEquals(0, pq.size());
		assertFalse(pq.delete(p101));
		assertFalse(pq.delete(p202));

		assertTrue(pq.enqueue(p202));
		assertTrue(pq.enqueue(p101));
		assertFalse(pq.isEmpty());
		assertEquals(2, pq.size());
		assertTrue(pq.delete(p101));
		assertFalse(pq.isEmpty());
		assertEquals(1, pq.size());
		assertEquals(p202, pq.dequeue());
		assertEquals(0, pq.size());
		assertFalse(pq.delete(p101));
		assertFalse(pq.delete(p202));
	}

	@Test
	public void test05EnqDeqEnqDeq() {
		PriceQueue pq = new PriceQueue();
		assertTrue(pq.enqueue(p101));
		assertEquals(p101, pq.dequeue());
		assertTrue(pq.enqueue(p202));
		assertEquals(p202, pq.dequeue());

		assertTrue(pq.enqueue(p202));
		assertEquals(p202, pq.dequeue());
		assertTrue(pq.enqueue(p101));
		assertEquals(p101, pq.dequeue());
	}

	@Test
	public void test05EnqDeqEnqDel() {
		PriceQueue pq = new PriceQueue();
		assertTrue(pq.enqueue(p101));
		assertEquals(p101, pq.dequeue());
		assertTrue(pq.enqueue(p202));
		assertTrue(pq.delete(p202));

		assertTrue(pq.enqueue(p202));
		assertEquals(p202, pq.dequeue());
		assertTrue(pq.enqueue(p101));
		assertTrue(pq.delete(p101));
	}

	@Test
	public void test05EnqDelEnqDel() {
		PriceQueue pq = new PriceQueue();
		assertTrue(pq.enqueue(p101));
		assertTrue(pq.delete(p101));
		assertTrue(pq.enqueue(p202));
		assertTrue(pq.delete(p202));

		assertTrue(pq.enqueue(p202));
		assertTrue(pq.delete(p202));
		assertTrue(pq.enqueue(p101));
		assertTrue(pq.delete(p101));
	}

	@Test
	public void test05EnqDelEnqDeq() {
		PriceQueue pq = new PriceQueue();
		assertTrue(pq.enqueue(p101));
		assertTrue(pq.delete(p101));
		assertTrue(pq.enqueue(p202));
		assertEquals(p202, pq.dequeue());

		assertTrue(pq.enqueue(p202));
		assertTrue(pq.delete(p202));
		assertTrue(pq.enqueue(p101));
		assertEquals(p101, pq.dequeue());
	}

	@Test
	public void test05DeqThenDeleteInOrder() {
		PriceQueue pq = new PriceQueue();
		assertTrue(pq.enqueue(p101));
		assertTrue(pq.enqueue(p303));
		assertTrue(pq.enqueue(p202));
		assertTrue(pq.enqueue(p505));
		assertTrue(pq.enqueue(p404));
		assertEquals(5, pq.size());
		assertEquals(p101, pq.dequeue());
		assertEquals(p303, pq.dequeue());
		assertEquals(p202, pq.dequeue());
		assertEquals(p505, pq.dequeue());
		assertEquals(p404, pq.dequeue());
		assertEquals(0, pq.size());
		assertTrue(pq.enqueue(p505));
		assertTrue(pq.enqueue(p101));
		assertTrue(pq.enqueue(p303));
		assertTrue(pq.enqueue(p404));
		assertTrue(pq.enqueue(p202));
		assertEquals(5, pq.size());
		assertTrue(pq.delete(p505));
		assertTrue(pq.delete(p101));
		assertTrue(pq.delete(p303));
		assertTrue(pq.delete(p404));
		assertTrue(pq.delete(p202));
		assertEquals(0, pq.size());
	}

	@Test
	public void test05DeqThenDeleteReverseOrder() {
		PriceQueue pq = new PriceQueue();
		assertTrue(pq.enqueue(p101));
		assertTrue(pq.enqueue(p404));
		assertTrue(pq.enqueue(p202));
		assertTrue(pq.enqueue(p303));
		assertTrue(pq.enqueue(p505));
		assertEquals(5, pq.size());
		assertEquals(p101, pq.dequeue());
		assertEquals(p404, pq.dequeue());
		assertEquals(p202, pq.dequeue());
		assertEquals(p303, pq.dequeue());
		assertEquals(p505, pq.dequeue());
		assertEquals(0, pq.size());
		assertTrue(pq.enqueue(p101));
		assertTrue(pq.enqueue(p202));
		assertTrue(pq.enqueue(p404));
		assertTrue(pq.enqueue(p505));
		assertTrue(pq.enqueue(p303));
		assertEquals(5, pq.size());
		assertTrue(pq.delete(p303));
		assertTrue(pq.delete(p505));
		assertTrue(pq.delete(p404));
		assertTrue(pq.delete(p202));
		assertTrue(pq.delete(p101));
		assertEquals(0, pq.size());
	}

	@Test
	public void test05DelInOrderThenDeq() {
		PriceQueue pq = new PriceQueue();
		assertTrue(pq.enqueue(p101));
		assertTrue(pq.enqueue(p404));
		assertTrue(pq.enqueue(p505));
		assertTrue(pq.enqueue(p303));
		assertTrue(pq.enqueue(p202));
		assertEquals(5, pq.size());
		assertTrue(pq.delete(p101));
		assertTrue(pq.delete(p404));
		assertTrue(pq.delete(p505));
		assertTrue(pq.delete(p303));
		assertTrue(pq.delete(p202));
		assertEquals(0, pq.size());
		assertTrue(pq.enqueue(p404));
		assertTrue(pq.enqueue(p101));
		assertTrue(pq.enqueue(p303));
		assertTrue(pq.enqueue(p505));
		assertTrue(pq.enqueue(p202));
		assertEquals(5, pq.size());
		assertEquals(p404, pq.dequeue());
		assertEquals(p101, pq.dequeue());
		assertEquals(p303, pq.dequeue());
		assertEquals(p505, pq.dequeue());
		assertEquals(p202, pq.dequeue());
		assertEquals(0, pq.size());
	}

	@Test
	public void test05DelReverseOrderThenDeq() {
		PriceQueue pq = new PriceQueue();
		assertTrue(pq.enqueue(p101));
		assertTrue(pq.enqueue(p303));
		assertTrue(pq.enqueue(p404));
		assertTrue(pq.enqueue(p505));
		assertTrue(pq.enqueue(p202));
		assertEquals(5, pq.size());
		assertTrue(pq.delete(p202));
		assertTrue(pq.delete(p505));
		assertTrue(pq.delete(p404));
		assertTrue(pq.delete(p303));
		assertTrue(pq.delete(p101));
		assertEquals(0, pq.size());
		assertTrue(pq.enqueue(p202));
		assertTrue(pq.enqueue(p303));
		assertTrue(pq.enqueue(p404));
		assertTrue(pq.enqueue(p505));
		assertTrue(pq.enqueue(p101));
		assertEquals(5, pq.size());
		assertEquals(p202, pq.dequeue());
		assertEquals(p303, pq.dequeue());
		assertEquals(p404, pq.dequeue());
		assertEquals(p505, pq.dequeue());
		assertEquals(p101, pq.dequeue());
		assertEquals(0, pq.size());
	}

	@Test
	public void test05DeleteMiddleInOrder() {
		PriceQueue pq = new PriceQueue();
		assertTrue(pq.enqueue(p303));
		assertTrue(pq.enqueue(p101));
		assertTrue(pq.enqueue(p404));
		assertTrue(pq.enqueue(p505));
		assertTrue(pq.enqueue(p202));
		assertTrue(pq.delete(p202));
		assertTrue(pq.delete(p303));
		assertTrue(pq.delete(p404));
		assertEquals(p101, pq.dequeue());
		assertEquals(p505, pq.dequeue());

		pq = new PriceQueue();
		assertTrue(pq.enqueue(p404));
		assertTrue(pq.enqueue(p101));
		assertTrue(pq.enqueue(p202));
		assertTrue(pq.enqueue(p505));
		assertTrue(pq.enqueue(p303));
		assertTrue(pq.delete(p202));
		assertTrue(pq.delete(p101));
		assertTrue(pq.delete(p505));
		assertTrue(pq.enqueue(p202));
		assertTrue(pq.enqueue(p505));
		assertEquals(p404, pq.dequeue());
		assertEquals(p303, pq.dequeue());
		assertEquals(p202, pq.dequeue());
		assertEquals(p505, pq.dequeue());
	}

	@Test
	public void test05DeleteMiddleRevOrder() {
		PriceQueue pq = new PriceQueue();
		assertTrue(pq.enqueue(p202));
		assertTrue(pq.enqueue(p101));
		assertTrue(pq.enqueue(p303));
		assertTrue(pq.enqueue(p505));
		assertTrue(pq.enqueue(p404));
		assertTrue(pq.delete(p505));
		assertTrue(pq.delete(p303));
		assertTrue(pq.delete(p101));
		assertEquals(p202, pq.dequeue());
		assertEquals(p404, pq.dequeue());

		pq = new PriceQueue();
		assertTrue(pq.enqueue(p505));
		assertTrue(pq.enqueue(p101));
		assertTrue(pq.enqueue(p202));
		assertTrue(pq.enqueue(p303));
		assertTrue(pq.enqueue(p404));
		assertTrue(pq.delete(p303));
		assertTrue(pq.delete(p202));
		assertTrue(pq.delete(p101));
		assertTrue(pq.enqueue(p202));
		assertTrue(pq.enqueue(p303));
		assertEquals(p505, pq.dequeue());
		assertEquals(p404, pq.dequeue());
		assertEquals(p202, pq.dequeue());
		assertEquals(p303, pq.dequeue());
	}

	@Test
	public void test05DeleteFrontInOrder() {
		PriceQueue pq = new PriceQueue();
		assertTrue(pq.enqueue(p303));
		assertTrue(pq.enqueue(p101));
		assertTrue(pq.enqueue(p202));
		assertTrue(pq.enqueue(p404));
		assertTrue(pq.enqueue(p505));
		assertTrue(pq.delete(p303));
		assertTrue(pq.delete(p101));
		assertTrue(pq.delete(p202));
		assertEquals(p404, pq.dequeue());
		assertEquals(p505, pq.dequeue());

		pq = new PriceQueue();
		assertTrue(pq.enqueue(p404));
		assertTrue(pq.enqueue(p101));
		assertTrue(pq.enqueue(p202));
		assertTrue(pq.enqueue(p303));
		assertTrue(pq.enqueue(p505));
		assertTrue(pq.delete(p101));
		assertTrue(pq.delete(p202));
		assertTrue(pq.delete(p303));
		assertTrue(pq.enqueue(p101));
		assertTrue(pq.enqueue(p202));
		assertEquals(p404, pq.dequeue());
		assertEquals(p505, pq.dequeue());
		assertEquals(p101, pq.dequeue());
		assertEquals(p202, pq.dequeue());
	}

	@Test
	public void test05DeleteFrontRevOrder() {
		PriceQueue pq = new PriceQueue();
		assertTrue(pq.enqueue(p303));
		assertTrue(pq.enqueue(p101));
		assertTrue(pq.enqueue(p202));
		assertTrue(pq.enqueue(p404));
		assertTrue(pq.enqueue(p505));
		assertTrue(pq.delete(p202));
		assertTrue(pq.delete(p101));
		assertTrue(pq.delete(p303));
		assertEquals(p404, pq.dequeue());
		assertEquals(p505, pq.dequeue());

		pq = new PriceQueue();
		assertTrue(pq.enqueue(p303));
		assertTrue(pq.enqueue(p101));
		assertTrue(pq.enqueue(p202));
		assertTrue(pq.enqueue(p404));
		assertTrue(pq.enqueue(p505));
		assertTrue(pq.delete(p202));
		assertTrue(pq.delete(p101));
		assertTrue(pq.delete(p303));
		assertTrue(pq.enqueue(p101));
		assertTrue(pq.enqueue(p202));
		assertEquals(p404, pq.dequeue());
		assertEquals(p505, pq.dequeue());
		assertEquals(p101, pq.dequeue());
		assertEquals(p202, pq.dequeue());
	}

	@Test
	public void test05DeleteBackInOrder() {
		PriceQueue pq = new PriceQueue();
		assertTrue(pq.enqueue(p202));
		assertTrue(pq.enqueue(p101));
		assertTrue(pq.enqueue(p303));
		assertTrue(pq.enqueue(p505));
		assertTrue(pq.enqueue(p404));
		assertTrue(pq.delete(p303));
		assertTrue(pq.delete(p505));
		assertTrue(pq.delete(p404));
		assertEquals(p202, pq.dequeue());
		assertEquals(p101, pq.dequeue());

		pq = new PriceQueue();
		assertTrue(pq.enqueue(p404));
		assertTrue(pq.enqueue(p101));
		assertTrue(pq.enqueue(p303));
		assertTrue(pq.enqueue(p505));
		assertTrue(pq.enqueue(p202));
		assertTrue(pq.delete(p303));
		assertTrue(pq.delete(p505));
		assertTrue(pq.delete(p202));
		assertTrue(pq.enqueue(p303));
		assertTrue(pq.enqueue(p202));
		assertEquals(p404, pq.dequeue());
		assertEquals(p101, pq.dequeue());
		assertEquals(p303, pq.dequeue());
		assertEquals(p202, pq.dequeue());
	}

	@Test
	public void test05DeleteBackRevOrder() {
		PriceQueue pq = new PriceQueue();
		assertTrue(pq.enqueue(p303));
		assertTrue(pq.enqueue(p101));
		assertTrue(pq.enqueue(p404));
		assertTrue(pq.enqueue(p505));
		assertTrue(pq.enqueue(p202));
		assertTrue(pq.delete(p202));
		assertTrue(pq.delete(p505));
		assertTrue(pq.delete(p404));
		assertTrue(pq.enqueue(p202));
		assertEquals(p303, pq.dequeue());
		assertEquals(p101, pq.dequeue());

		pq = new PriceQueue();
		assertTrue(pq.enqueue(p505));
		assertTrue(pq.enqueue(p202));
		assertTrue(pq.enqueue(p303));
		assertTrue(pq.enqueue(p404));
		assertTrue(pq.enqueue(p101));
		assertTrue(pq.delete(p101));
		assertTrue(pq.delete(p404));
		assertTrue(pq.delete(p303));
		assertTrue(pq.enqueue(p303));
		assertTrue(pq.enqueue(p404));
		assertEquals(p505, pq.dequeue());
		assertEquals(p202, pq.dequeue());
		assertEquals(p303, pq.dequeue());
		assertEquals(p404, pq.dequeue());
	}

	@Test
	public void test05Duplicates() {
		PriceQueue pq = new PriceQueue();
		assertTrue(pq.enqueue(new Price(3, 53)));
		assertTrue(pq.enqueue(new Price(1, 5)));
		assertTrue(pq.enqueue(new Price(2, 3)));
		assertTrue(pq.enqueue(new Price(7, 13)));
		assertTrue(pq.enqueue(new Price(6, 53)));

		assertFalse(pq.enqueue(new Price(3, 53)));
		assertFalse(pq.enqueue(new Price(1, 5)));
		assertFalse(pq.enqueue(new Price(2, 3)));
		assertTrue(pq.enqueue(new Price(0, 1)));
		assertTrue(pq.enqueue(new Price(9, 22)));

		assertFalse(pq.enqueue(new Price(9, 22)));
		assertTrue(pq.enqueue(new Price(7, 23)));

		assertFalse(pq.enqueue(new Price(9, 22)));
		assertFalse(pq.enqueue(new Price(7, 23)));
		assertFalse(pq.enqueue(new Price(3, 53)));
		assertFalse(pq.enqueue(new Price(1, 5)));
		assertFalse(pq.enqueue(new Price(2, 3)));
		assertFalse(pq.enqueue(new Price(7, 13)));
		assertFalse(pq.enqueue(new Price(6, 53)));
		assertFalse(pq.enqueue(new Price(0, 1)));
		assertTrue(pq.enqueue(new Price(1, 55)));

		assertFalse(pq.enqueue(new Price(3, 53)));
		assertFalse(pq.enqueue(new Price(1, 5)));
		assertFalse(pq.enqueue(new Price(2, 3)));
		assertFalse(pq.enqueue(new Price(7, 13)));
		assertFalse(pq.enqueue(new Price(6, 53)));
		assertFalse(pq.enqueue(new Price(0, 1)));
		assertFalse(pq.enqueue(new Price(9, 22)));
		assertFalse(pq.enqueue(new Price(7, 23)));
		assertFalse(pq.enqueue(new Price(1, 55)));
		assertTrue(pq.enqueue(new Price(10, 10)));
	}

	@Test
	public void testTiming() {
		final int SIZE = 1000;
		Price[] prices = new Price[SIZE * 2];
		for (int i = 0; i < SIZE * 2; i++)
			prices[i] = new Price(i / 100, i % 100);
		PriceQueue pq = new PriceQueue();
		for (int i = 0; i < SIZE; i++)
			pq.enqueue(prices[i]);
		long start1 = System.currentTimeMillis();
		for (int i = SIZE / 2; i < SIZE; i++) {
			pq.delete(prices[i]);
			pq.enqueue(prices[i]);
		}
		long finish1 = System.currentTimeMillis();
		long time1 = finish1 - start1;
		System.out.println("time1 = " + time1);

		pq = new PriceQueue();
		for (int i = 0; i < 2 * SIZE; i++)
			pq.enqueue(prices[i]);
		long start2 = System.currentTimeMillis();
		for (int i = (3 * SIZE) / 2; i < 2 * SIZE; i++) {
			pq.delete(prices[i]);
			pq.enqueue(prices[i]);
		}
		long finish2 = System.currentTimeMillis();
		long time2 = finish2 - start2;
		assertFalse(time2 > time1 * 1.5);
		System.out.println("time2 = " + time2);
	}
}
