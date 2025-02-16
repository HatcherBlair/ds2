package hw7;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Stream;

import algs41.BreadthFirstPaths;
import algs41.Graph;

public class WordLadder {
    private ArrayList<String> words;
    private BucketMethod bucketMethod;
    private LengthMethod lengthMethod;

    WordLadder(String path) {
        // Read in the words
        String fileName = "src/hw7/" + path;
        File file = new File(fileName);
        words = new ArrayList<String>();

        try (Stream<String> lines = Files.lines(file.toPath())) {
            lines.forEach(line -> {
                words.add(line.toLowerCase());
            });
        } catch (Exception e) {
            System.out.println(e);
        }

        // Initialize the two methods
        bucketMethod = new BucketMethod(words);
        lengthMethod = new LengthMethod(words);
    }

    public void printStats() {
        System.out.printf("Bucket method: E: %d, V: %d\n", bucketMethod.graph.E(), bucketMethod.graph.V());
        System.out.printf("Length method: E: %d, V: %d\n", lengthMethod.graph.E(), lengthMethod.graph.V());
    }

    public void buildBucketGraph() {
        bucketMethod.buildGraph();
    }

    public void buildLengthGraph() {
        lengthMethod.buildGraph();
    }

    // Grabs 2 random words from the list and runs the bucket ladder on it.
    public void randomBucketLadder() {
        Random rand = new Random();
        bucketMethod.makeLadder(words.get(rand.nextInt(words.size())),
                words.get(rand.nextInt(words.size())));
    }

    public void randomLengthLadder() {
        Random rand = new Random();
        lengthMethod.makeLadder(words.get(rand.nextInt(words.size())), words.get(rand.nextInt(words.size())));
    }

    public void bucketLadder(String from, String to) {
        bucketMethod.makeLadder(from.toLowerCase(), to.toLowerCase());
    }

    public void lengthLadder(String from, String to) {
        lengthMethod.makeLadder(from.toLowerCase(), to.toLowerCase());
    }

    // Helper class containing the length implementation
    private class LengthMethod {
        // 0 indexed. Words of length 1 are at index 0
        private ArrayList<ArrayList<Word>> sortedWords;
        private ArrayList<String> words;
        public Graph graph;

        private class Word implements Comparable<Word> {
            final String original;
            String shifted;

            Word(String word) {
                this.original = word;
                this.shifted = word;
            }

            @Override
            public int compareTo(Word other) {
                return this.shifted.compareTo(other.shifted);
            }
        }

        LengthMethod(Collection<String> words) {
            this.words = new ArrayList<>(words);
        }

        private void makeLadder(String from, String to) {
            if (words.indexOf(from) == -1 || words.indexOf(to) == -1) {
                System.out.println("No Ladder Found :(");
                return;
            }
            BreadthFirstPaths bfs = new BreadthFirstPaths(graph, words.indexOf(from));
            if (bfs.hasPathTo(words.indexOf(to))) {
                System.out.printf("Found Ladder of length: %d\n", bfs.distTo(words.indexOf(to)) + 1);
                for (int v : bfs.pathTo(words.indexOf(to))) {
                    System.out.println(words.get(v));
                }
                System.out.println();
            } else {
                System.out.println("No Ladder Found :(");
            }
        }

        private void buildGraph() {
            // Stores all words, used for creating edges for increasing length.
            // ie bird -> birds
            HashSet<String> oneSmaller = new HashSet<>();
            sortedWords = new ArrayList<>();
            graph = new Graph(words.size());

            for (String word : words) {
                // Ensure there is an array to put the word in to
                while (sortedWords.size() < word.length()) {
                    sortedWords.add(new ArrayList<>());
                }

                sortedWords.get(word.length() - 1).add(new Word(word));
                oneSmaller.add(word);
            }

            // Loop over the sizes
            for (int i = 0; i < sortedWords.size(); i++) {
                int wordLen = i + 1;
                ArrayList<Word> wordsByLength = sortedWords.get(i);

                // Shift the words wordLen-1 times
                for (int j = 0; j < wordLen; j++) {
                    // No words of this length
                    if (wordsByLength.size() == 0) {
                        continue;
                    }

                    // Check first word here in case only element in array and because while loop
                    // skips first word
                    String first = wordsByLength.get(0).original;
                    if (j == 0 && oneSmaller.contains(first.substring(0, wordLen - 1))) {
                        graph.addEdge(words.indexOf(first),
                                words.indexOf(first.substring(0, wordLen - 1)));
                    }

                    // No other words in array
                    if (wordsByLength.size() == 1) {
                        continue;
                    }

                    // Index of words to compare
                    int word = 0;
                    int cmp = 1;

                    while (word <= wordsByLength.size() - 2) {
                        // If the words haven't been shifted yet, also check the HashSet
                        if (j == 0 && oneSmaller.contains(wordsByLength.get(cmp).original.substring(0, wordLen - 1))) {
                            graph.addEdge(words.indexOf(wordsByLength.get(cmp).original),
                                    words.indexOf(wordsByLength.get(cmp).original.substring(0, wordLen - 1)));
                        }
                        if (wordsByLength.get(word).shifted.substring(0, wordLen - 1)
                                .compareTo(wordsByLength.get(cmp).shifted
                                        .substring(0,
                                                wordLen - 1)) == 0) {
                            // Words are the same, add edge and increment compare
                            graph.addEdge(words.indexOf(wordsByLength.get(word).original),
                                    words.indexOf(wordsByLength.get(cmp).original));
                            cmp++;

                            // End of list, increment word and reset
                            if (cmp == wordsByLength.size()) {
                                word++;
                                cmp = word + 1;
                            }
                        } else {
                            // Words differ
                            word++;
                            cmp = word + 1;
                        }
                    }

                    // Shift the words
                    shift(wordsByLength);
                    // Sort the array again
                    wordsByLength.sort(null);
                }
            }
        }

        // Shifts each string in array to the right by one character
        private void shift(ArrayList<Word> words) {
            for (int i = 0; i < words.size(); i++) {
                Word curr = words.get(i);
                // One character words are already shifted
                if (curr.original.length() == 1) {
                    return;
                }
                curr.shifted = curr.shifted.substring(1) + curr.shifted.substring(0, 1);
                words.set(i, curr);
            }
        }
    }

    // Helper class containing the bucket implementation
    private class BucketMethod {
        // Stores all of the buckets for creating the graph
        private HashMap<String, HashSet<String>> buckets;
        private ArrayList<String> words;
        public Graph graph;

        BucketMethod(Collection<String> words) {
            this.words = new ArrayList<>(words);
        }

        private void buildGraph() {
            buckets = new HashMap<>();
            // Populate the buckets
            for (String word : words) {
                addWord(word);
            }

            // Use the buckets to create the graph
            graph = new Graph(words.size());
            for (String key : buckets.keySet()) {
                // Every item in buckets.get(key) needs to have an edge to every other item
                Queue<String> bucket = new LinkedList<String>(buckets.get(key));
                while (!bucket.isEmpty()) {
                    String curr = bucket.poll();
                    bucket.forEach(word -> graph.addEdge(words.indexOf(word), words.indexOf(curr)));
                }
            }

        }

        private void makeLadder(String from, String to) {
            if (words.indexOf(from) == -1 || words.indexOf(to) == -1) {
                System.out.println("No Ladder Found :(");
                return;
            }
            BreadthFirstPaths bfs = new BreadthFirstPaths(graph, words.indexOf(from));
            if (bfs.hasPathTo(words.indexOf(to))) {
                System.out.printf("Found Ladder of length: %d\n", bfs.distTo(words.indexOf(to)) + 1);
                for (int v : bfs.pathTo(words.indexOf(to))) {
                    System.out.println(words.get(v));
                }
                System.out.println();
            } else {
                System.out.println("No Ladder Found :(");
            }
        }

        /*
         * Adds a word to buckets
         * Bucket names are words with one char replaced with _, matching all words that
         * differ by one letter
         * NOTE: This also appends an _, making connections with words that are one
         * character longer
         */
        private void addWord(String word) {
            for (int i = 0; i <= word.length(); i++) {
                StringBuilder sbWord = new StringBuilder(word);
                // Cannot use setChar to append, even if stringBuilder has capacity
                if (i == word.length()) {
                    sbWord.append('a');
                }
                sbWord.setCharAt(i, '_');
                HashSet<String> bucket = buckets.get(sbWord.toString());
                if (bucket != null) {
                    // Found a bucket with the i'th character being _
                    bucket.add(word);
                } else {
                    // Didn't find a bucket, make a new one and add it to the hashmap
                    bucket = new HashSet<String>();
                    bucket.add(word);
                    buckets.put(sbWord.toString(), bucket);
                }
            }
        }
    }

    private static void printMenu() {
        System.out.println("1) Change the words file(default words.txt)");
        System.out.println("2) Time and Build the ladder using the Bucket Method");
        System.out.println("3) Time and build the ladder using the Length Method");
        System.out.println("4) Build a ladder from %{string1} to %{string2} using Bucket Graph");
        System.out.println("5) Build a ladder from %{string1} to %{string2} using Length Graph");
        System.out.println("6) Print stats");
        System.out.println("7) Exit");
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean done = false;
        String fileName = "words.txt";
        WordLadder wl = new WordLadder(fileName);
        while (!done) {
            printMenu();
            System.out.print("-> ");
            switch (input.nextInt()) {
                case 1:
                    input.nextLine(); // Flush the buffer from nextInt
                    System.out.println();
                    System.out.print("Enter new filename /src/hw7/");
                    fileName = input.nextLine();
                    System.out.printf("Current path set to /src/hw7/%s\n", fileName);
                    wl = new WordLadder(fileName);
                    break;
                case 2:
                    // bucket method
                    System.out.printf("Starting bucket ladder build for %s\n", fileName);
                    Long start = System.currentTimeMillis();
                    wl.buildBucketGraph();
                    Long finish = System.currentTimeMillis();
                    System.out.printf("Finished building bucket ladder for %s\n", fileName);
                    System.out.printf("Constructed in %dms\n\n", finish - start);
                    break;
                case 3:
                    // Length method
                    System.out.printf("Starting length ladder build for %s\n", fileName);
                    start = System.currentTimeMillis();
                    wl.buildLengthGraph();
                    finish = System.currentTimeMillis();
                    System.out.printf("Finished building length ladder for %s\n", fileName);
                    System.out.printf("Constructed in %dms\n\n", finish - start);
                    break;
                case 4:
                    input.nextLine(); // Flush the buffer from nextInt
                    // DFS to find ladder bucket method
                    System.out.println();
                    System.out.printf("Starting string: ");
                    String from = input.nextLine();
                    System.out.printf("Ending string: ");
                    String to = input.nextLine();
                    System.out.printf("Building a ladder from %s to %s\n\n", from, to);
                    wl.bucketLadder(from, to);
                    break;
                case 5:
                    input.nextLine(); // Flush the buffer from nextInt
                    // DFS to find ladder length method
                    System.out.println();
                    System.out.printf("Starting string: ");
                    from = input.nextLine();
                    System.out.printf("Ending string: ");
                    to = input.nextLine();
                    System.out.printf("Building a ladder from %s to %s\n\n", from, to);
                    wl.lengthLadder(from, to);
                    break;
                case 6:
                    wl.printStats();
                    break;
                case 7:
                    done = true;
                    break;
                default:
                    break;
            }
        }
        input.close();
    }

    // This main runs the bucket with random words to find good ladders.
    public static void main2(String[] args) {
        String fileName = "words.txt";
        WordLadder wl = new WordLadder(fileName);
        wl.buildBucketGraph();
        for (int i = 0; i < 1000; i++) {
            wl.randomBucketLadder();
        }
    }
}
