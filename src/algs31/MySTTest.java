package algs31;

import stdlib.*;

public class MySTTest {

	private static void assertEquals(Object expected, Object actual) {
		if (expected == null) {
			if (actual != null) throw new AssertionError();
		} else {
			if (! expected.equals (actual)) throw new AssertionError();
		}
	}

	public static void testMin() {
		// This is a fully random test.
		// These tests are better if you can come up with one.
		for (int numTrials = 100; numTrials > 0; numTrials--) {
			BinarySearchST<Integer,Integer> st = new BinarySearchST<>();
			assertEquals (null, st.min ());
			int min = Integer.MAX_VALUE;
			for (int numAdded = 100; numAdded > 0; numAdded--) {
				int next = StdRandom.uniform (1000);
				st.put (next, next*10);
				min = Math.min (min, next);
				assertEquals (min, st.min ());
			}
		}
	}

	public static void testMax() {
		// This is a test with specific values
		// Not as good as a random test.
		BinarySearchST<Integer,Integer> st = new BinarySearchST<>();
		assertEquals (null, st.max()); // empty
		st.put (5, 50);
		assertEquals (5, st.max());  // one
		st.put (2, 20);
		assertEquals (5, st.max());  // does not change
		st.put (7, 70);
		assertEquals (7, st.max());  // changes
		st.put (1, 10);
		assertEquals (7, st.max());  // does not change
		st.put (8, 80);
		assertEquals (8, st.max());  // changes

		// symmetrically
		st = new BinarySearchST<>();
		assertEquals (null, st.max()); // empty
		st.put (5, 50);
		assertEquals (5, st.max());  // one
		st.put (7, 70);
		assertEquals (7, st.max());  // changes
		st.put (2, 20);
		assertEquals (7, st.max());  // does not change
		st.put (8, 80);
		assertEquals (8, st.max());  // changes
		st.put (1, 10);
		assertEquals (8, st.max());  // does not change
	}

	public static void testFloor() {
		// TODO: Write a test.  Try to write a random one, if you can.
	}

	public static void testCeiling() {
		// TODO: Write a test.  Try to write a random one, if you can.
	}

	public static void testSelect() {
		// TODO: Write a test.
	}

	public static void testRank() {
		// TODO: Write a test.
	}

	public static void testDeleteMin() {
		// TODO: Write a test.
	}

	public static void testDeleteMax() {
		// TODO: Write a test.
	}

	public static void testKeys() {
		// TODO: Write a test.
	}

	public static void main (String[] args) {
		testMin();
		testMax();
		testFloor();
		testCeiling();
		testSelect();
		testRank();
		testDeleteMin();
		testDeleteMax();
		testKeys();
	}
}
