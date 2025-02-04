package week5;

import org.junit.Test;
import org.junit.BeforeClass;

import week5.Dictionary.StorageType;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeSet;

public class DictionaryTest {

    // Create an array with all the words for testing
    private static ArrayList<String> words = new ArrayList<>();
    private static Random rand = new Random();

    @BeforeClass
    public static void init() {
        try (Scanner scanner = new Scanner(new File("src/week5/dictionary.txt"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                line = line.toLowerCase();
                words.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGet() {
        int temp = rand.nextInt(words.size());
        Dictionary dict = new Dictionary(StorageType.ArrayList);

        // Test ArrayList
        assertEquals(words.get(temp), dict.dictGet(words.get(temp)));
        temp = rand.nextInt(words.size());
        assertEquals(words.get(temp), dict.dictGet(words.get(temp)));
        temp = rand.nextInt(words.size());
        assertEquals(words.get(temp), dict.dictGet(words.get(temp)));
        temp = rand.nextInt(words.size());
        assertEquals(words.get(temp), dict.dictGet(words.get(temp)));
        temp = rand.nextInt(words.size());
        assertEquals(words.get(temp), dict.dictGet(words.get(temp)));
        temp = rand.nextInt(words.size());
        assertEquals(words.get(temp), dict.dictGet(words.get(temp)));

        // Test HashSet
        dict = new Dictionary(StorageType.HashSet);
        assertEquals(words.get(temp), dict.dictGet(words.get(temp)));
        temp = rand.nextInt(words.size());
        assertEquals(words.get(temp), dict.dictGet(words.get(temp)));
        temp = rand.nextInt(words.size());
        assertEquals(words.get(temp), dict.dictGet(words.get(temp)));
        temp = rand.nextInt(words.size());
        assertEquals(words.get(temp), dict.dictGet(words.get(temp)));
        temp = rand.nextInt(words.size());
        assertEquals(words.get(temp), dict.dictGet(words.get(temp)));
        temp = rand.nextInt(words.size());
        assertEquals(words.get(temp), dict.dictGet(words.get(temp)));

        // Test LinkedList
        dict = new Dictionary(StorageType.LinkedList);
        assertEquals(words.get(temp), dict.dictGet(words.get(temp)));
        temp = rand.nextInt(words.size());
        assertEquals(words.get(temp), dict.dictGet(words.get(temp)));
        temp = rand.nextInt(words.size());
        assertEquals(words.get(temp), dict.dictGet(words.get(temp)));
        temp = rand.nextInt(words.size());
        assertEquals(words.get(temp), dict.dictGet(words.get(temp)));
        temp = rand.nextInt(words.size());
        assertEquals(words.get(temp), dict.dictGet(words.get(temp)));
        temp = rand.nextInt(words.size());
        assertEquals(words.get(temp), dict.dictGet(words.get(temp)));

        // Test TreeSet
        dict = new Dictionary(StorageType.TreeSet);
        assertEquals(words.get(temp), dict.dictGet(words.get(temp)));
        temp = rand.nextInt(words.size());
        assertEquals(words.get(temp), dict.dictGet(words.get(temp)));
        temp = rand.nextInt(words.size());
        assertEquals(words.get(temp), dict.dictGet(words.get(temp)));
        temp = rand.nextInt(words.size());
        assertEquals(words.get(temp), dict.dictGet(words.get(temp)));
        temp = rand.nextInt(words.size());
        assertEquals(words.get(temp), dict.dictGet(words.get(temp)));
        temp = rand.nextInt(words.size());
        assertEquals(words.get(temp), dict.dictGet(words.get(temp)));
    }

    @Test
    public void testExtraLetter() {
        Dictionary dict = new Dictionary(StorageType.HashSet);
        TreeSet<String> response = dict.deleteOne("ablazes");
        assertTrue(response.contains("ablaze"));
        response = dict.deleteOne("abnormall");
        assertTrue(response.contains("abnormal"));
        response = dict.deleteOne("ibota");
        assertTrue(response.contains("iota"));
    }

    @Test
    public void testMissingLetter() {
        Dictionary dict = new Dictionary(StorageType.ArrayList);
        TreeSet<String> response = dict.insertOne("inden");
        assertTrue(response.contains("indent"));
        response = dict.insertOne("marshmalow");
        assertTrue(response.contains("marshmallow"));
        response = dict.insertOne("arx");
        assertTrue(response.contains("marx"));
    }

    @Test
    public void testSwapNeighbor() {
        Dictionary dict = new Dictionary(StorageType.ArrayList);
        TreeSet<String> response = dict.swapNeighbors("indetn");
        assertTrue(response.contains("indent"));
        response = dict.swapNeighbors("maxr");
        assertTrue(response.contains("marx"));
        response = dict.swapNeighbors("multpile");
        assertTrue(response.contains("multiple"));
        response = dict.swapNeighbors("ibota");
        assertTrue(response.contains("biota"));
    }

    @Test
    public void testReplaceLetter() {
        Dictionary dict = new Dictionary(StorageType.ArrayList);
        TreeSet<String> response = dict.replaceOne("indenf");
        assertTrue(response.contains("indent"));
        response = dict.replaceOne("multifly");
        assertTrue(response.contains("multiply"));
        response = dict.replaceOne("multiprocesp");
        assertTrue(response.contains("multiprocess"));
    }

    @Test
    public void testInsertSpace() {
        Dictionary dict = new Dictionary(StorageType.ArrayList);
        TreeSet<String> response = dict.insertSpace("indenting");
        assertTrue(response.contains("indent"));
        response = dict.insertSpace("multiplyspy");
        assertTrue(response.contains("multiply"));
        response = dict.insertSpace("pressurepress");
        assertTrue(response.contains("pressure"));
    }

    @Test
    public void testISpell() {
        Dictionary dict = new Dictionary(StorageType.LinkedList);
        TreeSet<String> response = dict.iSpell("indentin");
        assertTrue(response.contains("indent"));
        response = dict.iSpell("multiplyspy");
        assertTrue(response.contains("multiply"));
        response = dict.iSpell("pressurepress");
        assertTrue(response.contains("pressure"));

        response = dict.iSpell("indenf");
        assertTrue(response.contains("indent"));
        response = dict.iSpell("multifly");
        assertTrue(response.contains("multiply"));
        response = dict.iSpell("multiprocesp");
        assertTrue(response.contains("multiprocess"));

        response = dict.iSpell("indetn");
        assertTrue(response.contains("indent"));
        response = dict.iSpell("maxr");
        assertTrue(response.contains("marx"));
        response = dict.iSpell("multpile");
        assertTrue(response.contains("multiple"));
        response = dict.iSpell("ibota");
        assertTrue(response.contains("biota"));

        response = dict.iSpell("ablazes");
        assertTrue(response.contains("ablaze"));
        response = dict.iSpell("abnormall");
        assertTrue(response.contains("abnormal"));
        response = dict.iSpell("ibota");
        assertTrue(response.contains("iota"));
    }

    @Test
    public void testPositiveTiming() {
        final int NUM_RUNS = 1000;
        Random rand = new Random();
        System.out.println("Testing Linked List");
        long start = System.currentTimeMillis();
        Dictionary dict = new Dictionary(StorageType.LinkedList);
        for (int i = 0; i < NUM_RUNS; i++) {
            dict.iSpell(words.get(rand.nextInt(words.size())));
        }
        long finish = System.currentTimeMillis();
        System.out.printf("Linked List finished in: %d ms.\n", finish - start);

        System.out.println("Testing ArrayList");
        start = System.currentTimeMillis();
        dict = new Dictionary(StorageType.ArrayList);
        for (int i = 0; i < NUM_RUNS; i++) {
            dict.iSpell(words.get(rand.nextInt(words.size())));
        }
        finish = System.currentTimeMillis();
        System.out.printf("Array List finished in: %d ms.\n", finish - start);

        System.out.println("Testing TreeSet");
        start = System.currentTimeMillis();
        dict = new Dictionary(StorageType.ArrayList);
        for (int i = 0; i < NUM_RUNS; i++) {
            dict.iSpell(words.get(rand.nextInt(words.size())));
        }
        finish = System.currentTimeMillis();
        System.out.printf("TreeSet finished in: %d ms.\n", finish - start);

        System.out.println("Testing HashSet");
        start = System.currentTimeMillis();
        dict = new Dictionary(StorageType.ArrayList);
        for (int i = 0; i < NUM_RUNS; i++) {
            dict.iSpell(words.get(rand.nextInt(words.size())));
        }
        finish = System.currentTimeMillis();
        System.out.printf("Hash Set finished in: %d ms.\n", finish - start);
    }

    @Test
    public void testTiming() {
        final int NUM_RUNS = 1000;
        Random rand = new Random();
        System.out.println("Testing Linked List");
        long start = System.currentTimeMillis();
        Dictionary dict = new Dictionary(StorageType.LinkedList);
        for (int i = 0; i < NUM_RUNS; i++) {

            String word = words.get(rand.nextInt(words.size()));
            if (rand.nextInt(2) == 0) {
                dict.iSpell(word);
            } else {
                String newWord = mutateString(word);
                dict.iSpell(newWord);
            }
        }
        long finish = System.currentTimeMillis();
        System.out.printf("Linked List finished in: %d ms.\n", finish - start);

        System.out.println("Testing ArrayList");
        start = System.currentTimeMillis();
        dict = new Dictionary(StorageType.ArrayList);
        for (int i = 0; i < NUM_RUNS; i++) {
            String word = words.get(rand.nextInt(words.size()));
            if (rand.nextInt(2) == 0) {
                dict.iSpell(word);
            } else {
                dict.iSpell(mutateString(word));
            }
        }
        finish = System.currentTimeMillis();
        System.out.printf("Array List finished in: %d ms.\n", finish - start);

        System.out.println("Testing TreeSet");
        start = System.currentTimeMillis();
        dict = new Dictionary(StorageType.ArrayList);
        for (int i = 0; i < NUM_RUNS; i++) {
            String word = words.get(rand.nextInt(words.size()));
            if (rand.nextInt(2) == 0) {
                dict.iSpell(word);
            } else {
                dict.iSpell(mutateString(word));
            }
        }
        finish = System.currentTimeMillis();
        System.out.printf("TreeSet finished in: %d ms.\n", finish - start);

        System.out.println("Testing HashSet");
        start = System.currentTimeMillis();
        dict = new Dictionary(StorageType.ArrayList);
        for (int i = 0; i < NUM_RUNS; i++) {
            String word = words.get(rand.nextInt(words.size()));
            if (rand.nextInt(2) == 0) {
                dict.iSpell(word);
            } else {
                dict.iSpell(mutateString(word));
            }
        }
        finish = System.currentTimeMillis();
        System.out.printf("Hash Set finished in: %d ms.\n", finish - start);
    }

    private String mutateString(String word) {
        Random rand = new Random();
        switch (rand.nextInt(3)) {
            case 0:
                return word.substring(0, 1) + word.substring(2);
            case 1:
                return word + 'a';
            case 2:
                return word + words.get(rand.nextInt(words.size()));
            case 3:
                char[] wordArr = word.toCharArray();
                char tmp = wordArr[1];
                wordArr[1] = wordArr[0];
                wordArr[0] = tmp;
                return new String(wordArr);
        }
        return "";
    }
}
