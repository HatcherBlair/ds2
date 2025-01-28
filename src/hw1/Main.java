package hw1;

import java.util.Queue;
import java.util.HashSet;
import java.util.LinkedList;

//import algs13.LinkedList;
import hw1.labyrinth.*;

public class Main {
    /* Change this constant to contain your name.
     *
     * WARNING: Once you've set set this constant and started exploring your maze,
     * do NOT edit the value of kYourName. Changing kYourName will change which
     * maze you get back, which might invalidate all your hard work!
     */
    private static final String YOUR_NAME = "Hatcher Blair";

    /* Change these constants to contain the paths out of your mazes. */
    private static final String PATH_OUT_OF_MAZE        = "EEESNWWWSSSEWNNEESSE";
    private static final String PATH_OUT_OF_TWISTY_MAZE = "NWSWSSESEWEEW";


    // Traverses the maze in "Level Order"
    // Based on ln 164 of: https://github.com/HatcherBlair/BalancedBST-TOP/blob/main/balancedBST.js
    public static void levelOrderTraversal(MazeCell startLocation) {
        Queue<MazeCell> queue = new LinkedList<>();
        HashSet<MazeCell> visited = new HashSet<>();
        queue.add(startLocation);

        // Counter bc I wanted to see how many iterations this loop had.
        int counter = 0;

        while (queue.peek() != null) {
            MazeCell currNode = queue.remove();
            visited.add(currNode);

            // Add the next level to the queue
            if(currNode.east != null && !visited.contains(currNode.east)) {
                queue.add(currNode.east);
            }
            if(currNode.north != null && !visited.contains(currNode.north)) {
                queue.add(currNode.north);
            }
            if(currNode.south != null && !visited.contains(currNode.south)) {
                queue.add(currNode.south);
            }
            if(currNode.west != null && !visited.contains(currNode.west)) {
                queue.add(currNode.west);
            }

            // Print out the current nodes info
            System.out.printf("%02d: currNode: %08x  whatsHere: %-10s N: %08x E: %08x S: %08x W: %08x%n", 
                counter, 
                System.identityHashCode(currNode),
                currNode.whatsHere,
                System.identityHashCode(currNode.north),
                System.identityHashCode(currNode.east),
                System.identityHashCode(currNode.south),
                System.identityHashCode(currNode.west));

            counter++;
        }
    }

    public static void main(String[] args) {
        MazeCell startLocation = MazeUtilities.mazeFor(YOUR_NAME);
       
        // Traverses and prints the maze for solving
        //levelOrderTraversal(startLocation);
        
        if (MazeUtilities.isPathToFreedom(startLocation, PATH_OUT_OF_MAZE)) {
            System.out.println("Congratulations! You've found a way out of your labyrinth.");
        } else {
            System.out.println("Sorry, but you're still stuck in your labyrinth.");
        }
        
        
         MazeCell twistyStartLocation = MazeUtilities.twistyMazeFor(YOUR_NAME);
        
        // Traverses and prints the maze for solving
        //levelOrderTraversal(twistyStartLocation);
        
        if (MazeUtilities.isPathToFreedom(twistyStartLocation, PATH_OUT_OF_TWISTY_MAZE)) {
            System.out.println("Congratulations! You've found a way out of your twisty labyrinth.");
        } else {
            System.out.println("Sorry, but you're still stuck in your twisty labyrinth.");
        }
    }
}
