package algs11;

import java.util.Queue;
import java.util.Stack;
import java.util.LinkedList;

import stdlib.*;

class Node {
    Node left, right;
    int value;

    public Node(int value) {
        this.value = value;
    }

    public Node(int value, Node left, Node right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public Node(int value, Node left) {
        this.value = value;
        this.left = left;
    }

    public boolean equals(Object o) {
        if (o == null)
            return false;
        if (o == this)
            return true;
        if (!(o instanceof Node))
            return false;
        Node other = (Node) o;
        return this.value == other.value && this.left.equals(other.left) && this.right.equals(other.right);
    }

    public void insert(int value) {
        insert(this, value);
    }

    public Node insert(Node node, int value) {
        if (node == null)
            return new Node(value);
        if (value < node.value)
            insert(node.left, value);
        else
            node.right = insert(node.right, value);
        return node;
    }

    public int size() {
        return size(this);
    }

    private static int size(Node node) {
        if (node == null)
            return 0;
        return 1 + size(node.left) + size(node.right);
    }

    public int maxDepth() {
        return maxDepth(this);
    }

    private static int maxDepth(Node node) {
        if (node == null)
            return 0;
        int left = maxDepth(node.left);
        int right = maxDepth(node.right);
        return left >= right ? left + 1 : right + 1;
    }

    public String printInorder() {
        Stack<Node> inOrder = new Stack<>();
        Node curr = this;
        String val = "";

        while (curr != null || !inOrder.isEmpty()) {
            while (curr != null) {
                inOrder.push(curr);
                curr = curr.left;
            }
            curr = inOrder.pop();
            val += curr.value;
            curr = curr.right;
        }

        return val;
    }

    private static String printInorder(Node node) {
        if (node == null) {
            return "";
        }

        String val = "";
        val += printInorder(node.left);
        val += node.value;
        val += printInorder(node.right);
        return val;
    }

    public String printPostOrder() {
        Stack<Node> postOrder = new Stack<>();
        Node curr = this;
        Node prev = null;
        String val = "";
        while (curr != null || !postOrder.isEmpty()) {
            while (curr != null) {
                postOrder.push(curr);
                curr = curr.left;
            }
            curr = postOrder.peek();
            if (curr.right == null || curr.right == prev) {
                val += curr.value;
                postOrder.pop();
                prev = curr;
                curr = null;
            } else {
                curr = curr.right;
            }
        }
        return val;
    }

    private static String printPostOrder(Node node) {
        if (node == null) {
            return "";
        }

        String val = "";
        val += printPostOrder(node.left);
        val += printPostOrder(node.right);
        val += node.value;
        return val;
    }

    public String printPreOrder() {
        Stack<Node> preOrder = new Stack<>();
        preOrder.push(this);
        Node curr;
        String val = "";

        while (!preOrder.isEmpty()) {
            curr = preOrder.pop();
            val += curr.value;
            if (curr.right != null) {
                preOrder.push(curr.right);
            }
            if (curr.left != null) {
                preOrder.push(curr.left);
            }
        }

        return val;
    }

    private static String printPreOrder(Node node) {
        if (node == null) {
            return "";
        }

        String val = "";
        val += node.value;
        val += printPreOrder(node.left);
        val += printPreOrder(node.right);
        return val;
    }

    public String[] printPaths() {
        return printPaths(this, "");
    }

    private static String[] printPaths(Node node, String curPath) {
        if (node.left == null && node.right == null) {
            return new String[] { curPath + node.value };
        }
        if (node.left != null && node.right != null) {
            String[] left = printPaths(node.left, curPath + node.value);
            String[] right = printPaths(node.right, curPath + node.value);
            String[] result = new String[left.length + right.length];
            System.arraycopy(left, 0, result, 0, left.length);
            System.arraycopy(right, 0, result, left.length, right.length);
            return result;
        }
        if (node.left == null) {
            return printPaths(node.right, curPath + node.value);
        }
        return printPaths(node.left, curPath + node.value);
    }

    public String printLevelOrder() {
        String val = "";
        Queue<Node> order = new LinkedList<>();
        order.add(this);
        while (!order.isEmpty()) {
            Node curr = order.poll();
            if (curr.left != null) {
                order.add(curr.left);
            }
            if (curr.right != null) {
                order.add(curr.right);
            }
            val += curr.value;
        }

        return val;
    }

    public int countLeaves() {
        return countLeaves(this);
    }

    private static int countLeaves(Node node) {
        if (node == null) {
            return 0;
        }
        if ((node.left == null) && (node.right == null)) {
            return 1;
        }

        return countLeaves(node.left) + countLeaves(node.right);
    }

    public boolean rootoleaf(int v) {
        return rootoLeaf(this, v, 0);
    }

    private static boolean rootoLeaf(Node node, int v, int currVal) {
        if (node.left == null && node.right == null) {
            if (node.value + currVal == v) {
                return true;
            }
            return false;
        }

        return (node.left != null && rootoLeaf(node.left, v, currVal + node.value))
                | (node.right != null && rootoLeaf(node.right, v, currVal + node.value));

    }

    public void mirror() {
        Queue<Node> queue = new LinkedList<>();
        queue.add(this);

        while (!queue.isEmpty()) {
            Node curr = queue.remove();
            Node temp = curr.left;
            curr.left = curr.right;
            curr.right = temp;
            if (curr.left != null) {
                queue.add(curr.left);
            }
            if (curr.right != null) {
                queue.add(curr.right);
            }
        }
    }

    public void doubleTree() {
        Queue<Node> queue = new LinkedList<>();
        queue.add(this);

        while (!queue.isEmpty()) {
            Node cur = queue.remove();
            Node newNode = new Node(cur.value);
            if (cur.left != null) {
                newNode.left = cur.left;
            }
            cur.left = newNode;

            if (cur.right != null) {
                queue.add(cur.right);
            }
            if (cur.left.left != null) {
                queue.add(cur.left.left);
            }
        }
    }

    private static void mirror(Node node) {
        if (node != null) {

            mirror(node.left);
            mirror(node.right);
            Node temp = node.left;
            node.left = node.right;
            node.right = temp;
        }

    }

    public boolean sameTree(Node node) {
        if (node == null)
            return false;
        if (this.value == node.value) {
            return this.sameNode(node);
        }
        return false;
    }

    private boolean sameNode(Node node) {
        if (node == null) {
            return false;
        }
        if (this.right == null && node.right != null) {
            return false;
        }
        if (this.left == null && node.left != null) {
            return false;
        }
        if (this.value != node.value) {
            return false;
        }

        boolean left = this.left == null && node.left == null ? true : this.left.sameNode(node.left);
        boolean right = this.right == null && node.right == null ? true : this.right.sameNode(node.right);

        return left & right;
    }

    // DLL problem
    public static void join(Node a, Node b) {
        a.right = b;
        b.left = a;
    }

    public static Node append(Node a, Node b) {
        if (a == null)
            return b;
        if (b == null)
            return a;

        Node aLast = a.left;
        Node bLast = b.left;

        join(aLast, b);
        join(bLast, a);

        return (a);
    }

    public static Node treeToList(Node root) {
        if (root == null) {
            return null;
        }

        Node small = treeToList(root.left);
        Node big = treeToList(root.right);

        root.left = root;
        root.right = root;

        small = append(small, root);
        small = append(small, big);

        return small;
    }

    public boolean isBST() {
        return isBST(this);
    }

    public static boolean isBST(Node node) {
        if (node == null)
            return true;
        int x = 1;
        return isBST(node.left)
                && isBST(node.right)
                && (node.value >= gmax(node.left))
                && (node.value <= gmin(node.right));
    }

    public boolean isBST2() {
        return isBST2(this, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public static boolean isBST2(Node node, int min, int max) {
        if (node == null)
            return true;
        return isBST2(node.left, min, node.value) && isBST2(node.right, node.value, max) && (node.value >= min)
                && (node.value <= max);
    }

    public static int gmin(Node node) {
        if (node == null)
            return Integer.MAX_VALUE;
        return Math.min(node.value, Math.min(gmin(node.left), gmin(node.right)));
    }

    public static int gmax(Node node) {
        if (node == null)
            return Integer.MAX_VALUE;
        return Math.min(node.value, Math.max(gmax(node.left), gmax(node.right)));
    }

    public static Node create(int N) {
        // create a BST with N nodes, left slanted
        Node root = null;
        if (N != 0) {
            root = new Node(N);
            Node curr = root;
            for (int i = N - 1; i > 0; i--) {
                curr.left = new Node(i);
                curr = curr.left;
            }
        }
        return root;
    }

    public static double timeTrial(int N) {
        // measure the amount of time taken to check isBST for a BST with N nodes

        int T = 10; // number of tests
        double sum = 0;
        for (int t = 0; t < T; t++) {
            Node r = create(N);
            Stopwatch s = new Stopwatch();
            r.isBST();
            sum += s.elapsedTime();
        }
        return sum / T;
    }

}

public class Tree {

    private static final int MIN = 64;

    // private static final int MAX = 32768000;
    // private static final int MAX = 1024000;
    private static final int MAX = 64000;

    public static void main(String[] args) {

        Node r = Node.create(2);
        int x = Node.gmax(r);
        int y = Node.gmin(r);

        r.isBST();

        double prev = Node.timeTrial(MIN);
        for (int N = MIN * 2; N <= MAX; N += N) {
            double time = Node.timeTrial(N);
            StdOut.format("%,13d %10.3f %10.3f\n", N, time, time / prev);
            prev = time;
        }
    }

    public static void main1(String[] args) {
        Node root = new Node(5, new Node(3), new Node(7));
        root.printInorder();

    }

}
