package algs55;

public class Node implements Comparable<Node>{
    int key;

    public Node(int key) {
        this.key = key;
    }

    public int compareTo(Node that) {
        return this.key - that.key; 
    }

    public String toString() {
        return "Node(" + key + ")";
    }
}
