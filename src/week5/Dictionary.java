package week5;

import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.TreeSet;

public class Dictionary {
    // Helper to define the type of dictionary
    public enum StorageType {
        LinkedList, ArrayList, TreeSet, HashSet;
    }

    // Helper containing the characters in the alphabet
    private char[] ALPHABAET = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q',
            'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };

    private Collection<String> dictionary;

    public Dictionary(StorageType storageType) {
        switch (storageType) {
            case LinkedList:
                dictionary = new LinkedList<String>();
                break;
            case ArrayList:
                dictionary = new ArrayList<String>();
                break;
            case TreeSet:
                dictionary = new TreeSet<String>();
                break;
            case HashSet:
                dictionary = new HashSet<String>();
                break;
        }

        // Import the dictionary
        try (Scanner scanner = new Scanner(new File("src/week5/dictionary.txt"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                line = line.toLowerCase();
                dictionary.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // The only public method in this class
    // Checks for the word and similar words in the dictionary
    public TreeSet<String> iSpell(String word) {
        TreeSet<String> returnSet = new TreeSet<>();
        if (dictGet(word) != null) {
            returnSet.add("OK");
            return returnSet;
        }

        returnSet.addAll(deleteOne(word));
        returnSet.addAll(insertOne(word));
        returnSet.addAll(swapNeighbors(word));
        returnSet.addAll(replaceOne(word));
        returnSet.addAll(insertSpace(word));

        if (returnSet.isEmpty()) {
            returnSet.add("Not Found");
        }

        return returnSet;
    }

    protected String dictGet(String word) {
        if (dictionary.contains(word)) {
            return word;
        }
        return null;
    }

    // output of ok means word in dict
    // output of not found means no near misses
    // list the near misses in alphabetical order

    // Return all the strings in dict from deleting one letter
    protected TreeSet<String> deleteOne(String word) {
        TreeSet<String> returnSet = new TreeSet<>();
        for (int i = 0; i < word.length(); i++) {
            String newString = dictGet(word.substring(0, i) + word.substring(i + 1));
            if (newString != null) {
                returnSet.add(newString);
            }
        }

        return returnSet;
    }

    // Return all the strings in dict from inserting one letter
    protected TreeSet<String> insertOne(String word) {
        TreeSet<String> returnSet = new TreeSet<>();
        for (int i = 0; i < word.length() + 1; i++) {
            for (char letter : ALPHABAET) {
                StringBuilder newWord = new StringBuilder(word);
                newWord.insert(i, letter);
                String newString = dictGet(newWord.toString());
                if (newString != null) {
                    returnSet.add(newString);
                }
            }
        }

        return returnSet;
    }

    // Return all the strings in dict from swapping neighbording characters
    protected TreeSet<String> swapNeighbors(String word) {
        TreeSet<String> returnSet = new TreeSet<>();
        for (int i = 1; i < word.length(); i++) {
            char[] wordArr = word.toCharArray();
            char tmp = wordArr[i];
            wordArr[i] = wordArr[i - 1];
            wordArr[i - 1] = tmp;
            String newString = dictGet(new String(wordArr));
            if (newString != null) {
                returnSet.add(newString);
            }
        }

        return returnSet;
    }

    // Return all the strings in dict from replacing one letter
    protected TreeSet<String> replaceOne(String word) {
        TreeSet<String> returnSet = new TreeSet<>();
        for (int i = 0; i < word.length(); i++) {
            for (char letter : ALPHABAET) {
                StringBuilder newWord = new StringBuilder(word);
                newWord.setCharAt(i, letter);
                String newString = dictGet(newWord.toString());
                if (newString != null) {
                    returnSet.add(newString);
                }
            }
        }

        return returnSet;
    }

    // Return all the strings in dict from inserting a space and checking resulting
    // two words
    protected TreeSet<String> insertSpace(String word) {
        TreeSet<String> returnSet = new TreeSet<>();
        for (int i = 0; i < word.length(); i++) {
            String[] newStrings = { word.substring(0, i), word.substring(i, word.length() - 1) };
            String newString = dictGet(newStrings[0]);
            if (newString != null) {
                returnSet.add(newString);
            }
            newString = dictGet(newStrings[1]);
            if (newString != null) {
                returnSet.add(newString);
            }
        }
        return returnSet;
    }
}
