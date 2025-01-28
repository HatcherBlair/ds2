package hw3b;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

public class JUnitTiming {
	private static final int SIZE = 10000;
	private static final long DIFF = 200;

	private static SequentialSearchST<String, Integer> putSmall;
	private static SequentialSearchST<String, Integer> putLarge;
	private static SequentialSearchST<String, Integer> getSmall;
	private static SequentialSearchST<String, Integer> getLarge;
	private static SequentialSearchST<String, Integer> delSmall;
	private static SequentialSearchST<String, Integer> delLarge;

	@Rule
	public Timeout globalTimeout = Timeout.seconds(2);

	@BeforeClass
	public static void setupNumbers() {
		putSmall = new SequentialSearchST<String, Integer>();
		putLarge = new SequentialSearchST<String, Integer>();
		getSmall = new SequentialSearchST<String, Integer>();
		getLarge = new SequentialSearchST<String, Integer>();
		delSmall = new SequentialSearchST<String, Integer>();
		delLarge = new SequentialSearchST<String, Integer>();
		putSmall.put("0", 0);
		putSmall.put("1", 1);
		getSmall.put("0", 0);
		getSmall.put("1", 1);
		delSmall.put("0", 0);
		delSmall.put("1", 1);
		for (int i = 0; i < SIZE; i++) {
			putLarge.put("" + i, i);
			getLarge.put("" + i, i);
			delLarge.put("" + i, i);
		}
	}

	@Test
	public void testGet() {
		long start;
		long finish;

		start = System.currentTimeMillis();
		for (int i = 0; i < SIZE; i++)
			getSmall.get("00");
		finish = System.currentTimeMillis();
		long getControl = finish - start;

		start = System.currentTimeMillis();
		for (int i = 0; i < SIZE; i++)
			getLarge.get("00");
		finish = System.currentTimeMillis();
		long getExperiment = finish - start;
		if (getExperiment >= getControl + DIFF)
			System.out.printf("[timing]:  getSmall %d \t getLarge %d %n", getControl, getExperiment);
		assertTrue(getExperiment < getControl + DIFF);

	}

	@Test
	public void testDelete() {
		long start;
		long finish;

		start = System.currentTimeMillis();
		for (int i = 0; i < SIZE; i++)
			delSmall.delete("00");
		finish = System.currentTimeMillis();
		long deleteControl = finish - start;

		start = System.currentTimeMillis();
		for (int i = 0; i < SIZE; i++)
			delLarge.delete("00");
		finish = System.currentTimeMillis();
		long deleteExperiment = finish - start;
		if (deleteExperiment >= deleteControl + DIFF)
			System.out.printf("[timing]:  delSmall %d \t delLarge %d %n", deleteControl, deleteExperiment);
		assertTrue(deleteExperiment < deleteControl + DIFF);

	}

	@Test
	public void testPut() {
		long start;
		long finish;

		start = System.currentTimeMillis();
		int bigNum = 99999999;
		for (int i = bigNum; i > bigNum - SIZE; i--)
			putSmall.put("-" + i, i);
		finish = System.currentTimeMillis();
		long putControl = finish - start;

		start = System.currentTimeMillis();
		for (int i = bigNum; i > bigNum - SIZE; i--)
			putLarge.put("-" + i, i);
		finish = System.currentTimeMillis();
		long putExperiment = finish - start;
		if (putExperiment >= putControl + DIFF)
			System.out.printf("[timing]:  putSmall %d \t putLarge %d %n", putControl, putExperiment);
		assertTrue(putExperiment < putControl + DIFF);

	}
}
