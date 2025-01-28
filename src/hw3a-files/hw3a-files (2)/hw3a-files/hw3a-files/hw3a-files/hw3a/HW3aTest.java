package hw3a;
import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

public class HW3aTest {
	
	@Rule
    public Timeout globalTimeout = Timeout.seconds(2);
	
	
	/* file1_1 frequencies
	 * 
	 * apple 1
	 */
	
	@Test
	public void test05fileOne() {
		WordFrequencyAnalyzer wf = new WordFrequencyAnalyzer("file1_1.txt");
		assertEquals(1, wf.getCount("apple"));
	}
	
	@Test
	public void test05fileOneMax() {
		WordFrequencyAnalyzer wf = new WordFrequencyAnalyzer("file1_1.txt");
		assertEquals(1, wf.maxCount());
	}

	
	/* file1_2 frequencies
	 * 
	 * apple 1
	 * zoo 1
	 */
	
	@Test
	public void test05fileTwo() {
		WordFrequencyAnalyzer wf = new WordFrequencyAnalyzer("file1_2.txt");
		assertEquals(1, wf.getCount("apple"));
		assertEquals(1, wf.getCount("zoo"));
	}
	
	@Test
	public void test05fileTwoMax() {
		WordFrequencyAnalyzer wf = new WordFrequencyAnalyzer("file1_1.txt");
		assertEquals(1, wf.maxCount());
	}
	
	/* file1_3 frequencies
	 * 
	 * apple 1
	 * fox 1
	 * zoo 1
	 */
	
	@Test
	public void test05fileThree() {
		WordFrequencyAnalyzer wf = new WordFrequencyAnalyzer("file1_3.txt");
		assertEquals(1, wf.getCount("apple"));
		assertEquals(1, wf.getCount("zoo"));
		assertEquals(1, wf.getCount("fox"));
	}
	
	@Test
	public void test05fileThreeMax() {
		WordFrequencyAnalyzer wf = new WordFrequencyAnalyzer("file1_1.txt");
		assertEquals(1, wf.maxCount());
	}
	

	/* file1_4 frequencies
	 * 
	 * apple 2
	 * fox 1
	 * zoo 4
	 */
	
	@Test
	public void test05fileFour() {
		WordFrequencyAnalyzer wf = new WordFrequencyAnalyzer("file1_4.txt");
		assertEquals(2, wf.getCount("apple"));
		assertEquals(4, wf.getCount("zoo"));
		assertEquals(1, wf.getCount("fox"));
	}
	
	@Test
	public void test05fileFourMax() {
		WordFrequencyAnalyzer wf = new WordFrequencyAnalyzer("file1_4.txt");
		assertEquals(4, wf.maxCount());
	}
	
	/* file1_5 frequencies
	 * 
	 * apple 2
	 * fox 5
	 * zoo 4
	 */
	
	@Test
	public void test05fileFive() {
		WordFrequencyAnalyzer wf = new WordFrequencyAnalyzer("file1_5.txt");
		assertEquals(2, wf.getCount("apple"));
		assertEquals(4, wf.getCount("zoo"));
		assertEquals(5, wf.getCount("fox"));
	}
	
	@Test
	public void test05fileFiveMax() {
		WordFrequencyAnalyzer wf = new WordFrequencyAnalyzer("file1_5.txt");
		assertEquals(5, wf.maxCount());
	}
	
	
	/* file1_6 frequencies
	 * 
	 * apple 2
	 * fox 5
	 * zoo 5
	 */
	
	@Test
	public void test05fileSix() {
		WordFrequencyAnalyzer wf = new WordFrequencyAnalyzer("file1_6.txt");
		assertEquals(2, wf.getCount("apple"));
		assertEquals(5, wf.getCount("zoo"));
		assertEquals(5, wf.getCount("fox"));
	}
	
	@Test
	public void test05fileSixMax() {
		WordFrequencyAnalyzer wf = new WordFrequencyAnalyzer("file1_6.txt");
		assertEquals(5, wf.maxCount());
	}
	
	
	
	/* file1_7 frequencies
	 * 
	 * apple 2
	 * fox 5
	 * house 201
	 * zoo 5
	 */
	
	@Test
	public void test05fileSeven() {
		WordFrequencyAnalyzer wf = new WordFrequencyAnalyzer("file1_7.txt");
		assertEquals(2, wf.getCount("apple"));
		assertEquals(201, wf.getCount("house"));
		assertEquals(5, wf.getCount("zoo"));
		assertEquals(5, wf.getCount("fox"));
	}
	
	@Test
	public void test05fileSevenMax() {
		WordFrequencyAnalyzer wf = new WordFrequencyAnalyzer("file1_7.txt");
		assertEquals(201, wf.maxCount());
	}
	
	
	
	/* file1_8 frequencies
	 * 
	 * apple 2
	 * band 1
	 * cat 3
	 * door 98
	 * elephant 42
	 * fox 5
	 * gate 77
	 * house 201
	 * ice 135
	 * jar 33
	 * kite 63
	 * lab 21
	 * mouse 8
	 * nail 10
	 * oval 43
	 * point 6
	 * rail 452
	 * sad 57
	 * top 13
	 * under 2
	 * vase 187
	 * water 1492
	 * xeno 64
	 * yam 89
	 * zoo 5
	 */
	
	@Test
	public void test05fileEight() {
		WordFrequencyAnalyzer wf = new WordFrequencyAnalyzer("file1_8.txt");
		assertEquals(2, wf.getCount("apple"));
		assertEquals(1, wf.getCount("band"));
		assertEquals(201, wf.getCount("house"));
		assertEquals(5, wf.getCount("zoo"));
		assertEquals(63, wf.getCount("kite"));
		assertEquals(42, wf.getCount("elephant"));
		assertEquals(63, wf.getCount("kite"));
		assertEquals(452, wf.getCount("rail"));
	}
	
	@Test
	public void test05fileEightMax() {
		WordFrequencyAnalyzer wf = new WordFrequencyAnalyzer("file1_8.txt");
		assertEquals(1492, wf.maxCount());
	}
	
	/* file1_9 frequencies
	 * 
	 * apple 2
	 * band 1
	 * cat 3
	 * door 98
	 * elephant 42
	 * fox 5
	 * gate 77
	 * house 201
	 * ice 135
	 * jar 33
	 * kite 63
	 * lab 2021
	 * mouse 8
	 * nail 10
	 * oval 43
	 * point 6
	 * rail 452
	 * sad 57
	 * top 13
	 * under 2
	 * vase 187
	 * water 1492
	 * xeno 64
	 * yam 89
	 * zoo 5
	 */
	
	@Test
	public void test05fileNine() {
		WordFrequencyAnalyzer wf = new WordFrequencyAnalyzer("file1_9.txt");
		assertEquals(2, wf.getCount("apple"));
		assertEquals(1, wf.getCount("band"));
		assertEquals(201, wf.getCount("house"));
		assertEquals(5, wf.getCount("zoo"));
		assertEquals(63, wf.getCount("kite"));
		assertEquals(42, wf.getCount("elephant"));
		assertEquals(63, wf.getCount("kite"));
		assertEquals(452, wf.getCount("rail"));
		assertEquals(1492, wf.getCount("water"));
	}
	
	@Test
	public void test05fileNineMax() {
		WordFrequencyAnalyzer wf = new WordFrequencyAnalyzer("file1_9.txt");
		assertEquals(2021, wf.maxCount());
	}
	
	
	@Test
	public void test10MissingWords() {
		WordFrequencyAnalyzer wf = new WordFrequencyAnalyzer("file1_1.txt");
		assertEquals(0, wf.getCount("zoo"));
		assertEquals(0, wf.getCount("water"));
		wf = new WordFrequencyAnalyzer("file1_9.txt");
		assertEquals(0, wf.getCount("box"));
		assertEquals(0, wf.getCount("ear"));
	}
}
