package algs34;

import stdlib.*;

public class MyFBPerformanceTest {
	static int MAX_PEOPLE = 50000;
	static int NUM_SIZES = 6;
	static MyFB.Person[] person = new MyFB.Person[MAX_PEOPLE];
	public static void main(String[] args) {
		StdRandom.setSeed (0);
		StdOut.doNothing ();
		MyFB.DEBUG = false;
		for (int i=0; i<MAX_PEOPLE; i++) {
			person[i] = MyFB.makePerson (Integer.toString (i), 10);
		}
		addSpeedTest();
		querySpeedTest();
		lookupSpeedTest ();
	}
	public static void addSpeedTest() {
		int NUM_RELATIONS = 25000;
		for (int count=1; count<NUM_SIZES; count++) {
			NUM_RELATIONS += NUM_RELATIONS;
			int N = (int) (Math.sqrt (NUM_RELATIONS) * 3.0);
			MyFB fb = new MyFB ();
			Stopwatch sw1 = new Stopwatch ();
			for (int i=N-1; i>=0; i--)
				fb.addPerson (person[i]);
			for (int i=NUM_RELATIONS-1; i>=0; i--)
				fb.addFriendship (person[StdRandom.uniform (N)], person[StdRandom.uniform (N)]);
			double time1 = sw1.elapsedTime ();

			System.out.format ("%9d %9d: time=%f [add]\n", N, NUM_RELATIONS, time1);
		}
	}
	public static void querySpeedTest() {
		int NUM_RELATIONS = 25000;
		for (int count=1; count<NUM_SIZES; count++) {
			NUM_RELATIONS += NUM_RELATIONS;
			int N = (int) (Math.sqrt (NUM_RELATIONS) * 3.0);
			MyFB fb = new MyFB ();
			for (int i=N-1; i>=0; i--)
				fb.addPerson (person[i]);
			for (int i=NUM_RELATIONS-1; i>=0; i--)
				fb.addFriendship (person[StdRandom.uniform (N)], person[StdRandom.uniform (N)]);
			Stopwatch sw1 = new Stopwatch ();
			for (int i=NUM_RELATIONS-1; i>=0; i--)
				fb.queryFriendship (person[StdRandom.uniform (N)], person[StdRandom.uniform (N)]);
			double time1 = sw1.elapsedTime ();

			System.out.format ("%9d %9d: time=%f [query]\n", N, NUM_RELATIONS, time1);
		}
	}

	public static void lookupSpeedTest() {
		int NUM_RELATIONS = 25000;
		for (int count=1; count<NUM_SIZES; count++) {
			NUM_RELATIONS += NUM_RELATIONS;
			int N = (int) (Math.sqrt (NUM_RELATIONS) * 3.0);
			MyFB fb = new MyFB ();
			for (int i=N-1; i>=0; i--)
				fb.addPerson (person[i]);
			for (int i=NUM_RELATIONS-1; i>=0; i--)
				fb.addFriendship (person[StdRandom.uniform (N)], person[StdRandom.uniform (N)]);
			Stopwatch sw1 = new Stopwatch ();
			for (int i=NUM_RELATIONS-1; i>=0; i--)
				fb.lookupFriends (person[StdRandom.uniform (N)]);
			double time1 = sw1.elapsedTime ();

			System.out.format ("%9d %9d: time=%f [lookup]\n", N, NUM_RELATIONS, time1);
		}
	}
}
