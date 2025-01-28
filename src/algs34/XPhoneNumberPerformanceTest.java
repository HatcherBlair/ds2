package algs34;

import stdlib.*;

public class XPhoneNumberPerformanceTest {
	private static int NUM_SIZES = 8;
	private static int START_SIZE = 25000;
	public static void main(String[] args) {
		Object val = new Object ();
		int size = START_SIZE;
		for (int count=1; count<NUM_SIZES; count++) {
			size += size;
			//java.util.IdentityHashMap<XPhoneNumber,Object> set = new java.util.IdentityHashMap <>();
			java.util.HashMap<XPhoneNumber,Object> set = new java.util.HashMap <>();
			//SeparateChainingHashST<XPhoneNumber,Object> set = new SeparateChainingHashST <>();
			//LinearProbingHashST<XPhoneNumber,Object> set = new LinearProbingHashST <>();
			Stopwatch sw1 = new Stopwatch ();
			for (int i=size-1; i>=0; i--) {
				XPhoneNumber x = new XPhoneNumber
					(StdRandom.uniform (1000), StdRandom.uniform (1000), StdRandom.uniform (10000));
				set.put(x,val);
			}
			double time1 = sw1.elapsedTime ();
			Stopwatch sw2 = new Stopwatch ();
//			for (int i=size-1; i>=0; i--) {
//				XPhoneNumber x = new XPhoneNumber
//					(StdRandom.uniform (1000), StdRandom.uniform (1000), StdRandom.uniform (10000));
//				//set.containsKey (x);
//				set.contains (x);
//			}
			double time2 = sw1.elapsedTime ();
			System.out.format ("%9d: add=%f contains=%f\n", size, time1, time2);
		}
	}
}
