package hw6;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import algs41.Graph;

public class KnightTour {
    private int size;
    private Graph board;
    private boolean[] marked;
    private List<Integer> path;

    // Helper class for dealing with coordinates
    public static class Coord {
        public int x;
        public int y;

        Coord(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    KnightTour(int size) {
        this.size = size;
        board = new Graph(size * size);
        marked = new boolean[size * size];
        path = new ArrayList<>();

        // Fill the board with chess moves
        for (int i = 0; i < board.V(); i++) {
            List<Coord> moves = generateMoves(idxToCoords(i));
            for (Coord move : moves) {
                board.addEdge(i, coordsToIdx(move));
            }
        }
    }

    // Finds the tour by taking the first edge
    public List<Integer> dumbTour(Coord start) {
        // Ensure the path and the marked array are empty
        path = new ArrayList<>();
        marked = new boolean[size * size];
        marked[coordsToIdx(start)] = true;
        dumbTour(start, start, 0);

        return path.size() == size * size - 1 ? path : new ArrayList<>();
    }

    private boolean dumbTour(Coord tile, Coord start, int numMoves) {
        // Base case for valid path
        if (numMoves == (size * size) - 1) {
            return true;
        }

        // Keep finding path
        for (Coord move : generateMoves(tile)) {
            int index = coordsToIdx(move);
            // Already visited this tile, not a valid move
            if (marked[index]) {
                continue;
            }

            marked[index] = true;
            path.add(index);
            // Found a tour
            if (dumbTour(move, start, numMoves + 1)) {
                return true;
            }

            // Didn't find a tour, remove moves from path
            marked[index] = false;
            if (path.size() > 0) {
                path.remove(path.size() - 1);
            }
        }

        return false;
    }

    // Finds the tour by taking the lowest order edge
    public List<Integer> smartTour(Coord start) {
        // Ensure the path and the marked array are empty
        path = new ArrayList<>();
        marked = new boolean[size * size];

        marked[coordsToIdx(start)] = true;
        smartTour(start, start, 0);

        return path.size() == size * size - 1 ? path : new ArrayList<>();
    }

    private boolean smartTour(Coord tile, Coord start, int numMoves) {
        // Base case for valid path
        if (numMoves == (size * size) - 1) {
            return true;
        }

        // Sort the moves before looping over them
        List<Coord> moves = generateMoves(tile);
        moves.sort(Comparator.comparingInt(move -> board.degree(coordsToIdx(move))));
        for (Coord move : moves) {
            int index = coordsToIdx(move);
            // Already visited
            if (marked[index]) {
                continue;
            }

            marked[index] = true;
            path.add(index);
            // Found a tour
            if (smartTour(move, start, numMoves + 1)) {
                return true;
            }

            // Didn't find tour, remove moves from path
            marked[index] = false;
            if (path.size() > 0) {
                path.remove(path.size() - 1);
            }
        }

        return false;
    }

    // Generates the moves from a given x and y
    private List<Coord> generateMoves(Coord startingPoint) {
        List<Coord> moves = new ArrayList<>();

        // +2 x, +/- 1 y
        Coord temp = new Coord(startingPoint.x + 2, startingPoint.y + 1);
        if (valid(temp)) {
            moves.add(new Coord(temp.x, temp.y));
        }
        temp.y = startingPoint.y - 1;
        if (valid(temp)) {
            moves.add(new Coord(temp.x, temp.y));
        }

        // -2 x, +/- 1 y
        temp.x = startingPoint.x - 2;
        if (valid(temp)) {
            moves.add(new Coord(temp.x, temp.y));
        }
        temp.y = startingPoint.y + 1;
        if (valid(temp)) {
            moves.add(new Coord(temp.x, temp.y));
        }

        // +/- 1 x, + 2 y
        temp.x = startingPoint.x + 1;
        temp.y = startingPoint.y + 2;
        if (valid(temp)) {
            moves.add(new Coord(temp.x, temp.y));
        }
        temp.x = startingPoint.x - 1;
        if (valid(temp)) {
            moves.add(new Coord(temp.x, temp.y));
        }

        // +/- 1 x, - 2 y
        temp.y = startingPoint.y - 2;
        if (valid(temp)) {
            moves.add(new Coord(temp.x, temp.y));
        }
        temp.x = startingPoint.x + 1;
        if (valid(temp)) {
            moves.add(new Coord(temp.x, temp.y));
        }
        return moves;
    }

    // Helper function to check moves
    private boolean valid(Coord point) {
        if (point.x < 0)
            return false;
        if (point.y < 0)
            return false;
        if (point.x >= size)
            return false;
        if (point.y >= size)
            return false;

        return true;
    }

    // Convert from x,y to index
    private int coordsToIdx(Coord coord) {
        return coord.y * size + coord.x;
    }

    // Convert from index to x,y
    private Coord idxToCoords(int idx) {
        return new Coord(idx % size, idx / size);
    }

    // Helper function to run and print smart tour
    private static void runSmartTour(int size) {
        KnightTour kt = new KnightTour(size);
        List<Integer> path;
        long start, finish;

        System.out.printf("Smart tour for size %d\n", size);
        start = System.currentTimeMillis();
        path = kt.smartTour(new Coord(0, 0));
        finish = System.currentTimeMillis();
        System.out.printf("Finished in %dms.\n", finish - start);
        System.out.printf("Size is %d and path size is %d\n", size, path.size());
        System.out.println("Path taken:");
        for (Integer tile : path) {
            System.out.printf("Index: %d, x: %d, y: %d\n",
                    tile,
                    kt.idxToCoords(tile).x,
                    kt.idxToCoords(tile).y);
        }
        System.out.println();
    }

    // Helper function to run and print dumb tour
    private static void runDumbTour(int size) {
        KnightTour kt = new KnightTour(size);
        List<Integer> path;
        long start, finish;

        System.out.printf("Dumb tour for size %d\n", size);
        start = System.currentTimeMillis();
        path = kt.dumbTour(new Coord(0, 0));
        finish = System.currentTimeMillis();
        System.out.printf("Finished in %dms.\n", finish - start);
        System.out.printf("Size is %d and path size is %d\n", size, path.size());
        System.out.println("Path taken:");
        for (Integer tile : path) {
            System.out.printf("Index: %d, x: %d, y: %d\n",
                    tile,
                    kt.idxToCoords(tile).x,
                    kt.idxToCoords(tile).y);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        System.out.println("Running tests for Knights Tour");

        runDumbTour(1);
        runDumbTour(2);
        runDumbTour(3);
        runDumbTour(4);
        runDumbTour(5);
        runDumbTour(6);

        runSmartTour(1);
        runSmartTour(2);
        runSmartTour(3);
        runSmartTour(4);
        runSmartTour(5);
        runSmartTour(6);
        runSmartTour(7);
        runSmartTour(8);
        runSmartTour(9);
        runSmartTour(10);
        runSmartTour(11);
    }
}
