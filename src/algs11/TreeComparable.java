package algs11;

import java.util.LinkedList;
import java.util.Queue;

class Node<T extends Comparable<T>> {
    Node<T> left, right;
    T value;

    public Node(T value) {
        this.value = value;
    }

    public Node(T value, Node<T> left, Node<T> right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }
}

public class TreeComparable<T extends Comparable<T>> {
    Node<T> root;

    public void insert(T v) {
        Node<T> currNode = root;
        while (true) {
            if (v.compareTo(currNode.value) == 0) {
                return;
            }
            if (v.compareTo(currNode.value) < 0) {
                if (currNode.left == null) {
                    Node<T> newNode = new Node<T>(v);
                    currNode.left = newNode;
                    return;
                }
                currNode = currNode.left;
            } else {
                if (currNode.right == null) {
                    Node<T> newNode = new Node<T>(v);
                    currNode.right = newNode;
                    return;
                }
                currNode = currNode.right;
            }
        }
    }

    public boolean lookup(T v) {
        return lookup(root, v);

    }

    public static <T extends Comparable<T>> boolean lookup(Node<T> node, T v) {
        if (node == null)
            return false;
        if (v.compareTo(node.value) == 0)
            return true;
        if (v.compareTo(node.value) < 0)
            return lookup(node.left, v);
        return lookup(node.right, v);
    }

    public T min() {
        return min(root);
    }

    private static <T extends Comparable<T>> T min(Node<T> node) {
        if (node.left == null)
            return node.value;
        return min(node.left);
    }

    private static <T extends Comparable<T>> T min2(Node<T> node) {
        Node<T> current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current.value;
    }

    public T max() {
        Node<T> curNode = root;
        while (true) {
            if (curNode.right == null) {
                return curNode.value;
            }
            curNode = curNode.right;
        }
    }

    public static boolean isBST1(TreeComparable<Integer> node) {
        Node<Integer> currNode = node.root;
        Queue<Node<Integer>> queue = new LinkedList<Node<Integer>>();
        queue.add(currNode.left);
        while (!queue.isEmpty()) {
            currNode = queue.remove();
            if (currNode.value >= node.root.value) {
                return false;
            }
            if (currNode.left != null) {
                queue.add(currNode.left);
            }
            if (currNode.right != null) {
                queue.add(currNode.right);
            }
        }
        queue = new LinkedList<Node<Integer>>();
        queue.add(node.root.right);
        while (!queue.isEmpty()) {
            currNode = queue.remove();
            if (currNode.value <= node.root.value) {
                return false;
            }
            if (currNode.left != null) {
                queue.add(currNode.left);
            }
            if (currNode.right != null) {
                queue.add(currNode.right);
            }
        }
        TreeComparable<Integer> left = null;
        TreeComparable<Integer> right = null;

        if (node.root.left != null) {
            left = new TreeComparable<>();
            left.root = node.root.left;
        }
        if (node.root.right != null) {
            right = new TreeComparable<>();
            right.root = node.root.left;
        }
        if (left != null && right != null) {
            return isBST1(left) && isBST1(right);
        }

        if (right == null) {
            return isBST1(left);
        }
        if (left == null) {
            return isBST1(right);
        }
        return true;
    }

    public static boolean isBST2(TreeComparable<Integer> node) {

        boolean leftSide = false;
        boolean rightSide = false;
        if (node.root.left != null) {
            TreeComparable<Integer> left = new TreeComparable<>();
            left.root = node.root.left;
            leftSide = isBST2(left, Integer.MIN_VALUE, node.root.value);
        }
        if (node.root.right != null) {
            TreeComparable<Integer> right = new TreeComparable<>();
            right.root = node.root.right;
            rightSide = isBST2(right, node.root.value, Integer.MAX_VALUE);
        }
        return rightSide & leftSide;
    }

    private static boolean isBST2(TreeComparable<Integer> node, Integer minVal, Integer maxVal) {
        if (minVal != null && maxVal != null) {
            if (node.root.value > maxVal || node.root.value < minVal) {
                return false;
            }
        }
        if (node.root.left == null && node.root.right == null) {
            return true;
        }
        boolean leftSide = false;
        boolean rightSide = false;
        if (node.root.left != null) {
            TreeComparable<Integer> left = new TreeComparable<>();
            left.root = node.root.left;
            leftSide = isBST2(left, minVal, node.root.value);
        }
        if (node.root.right != null) {
            TreeComparable<Integer> right = new TreeComparable<>();
            right.root = node.root.left;
            leftSide = isBST2(right, node.root.value, maxVal);
        }
        return rightSide & leftSide;
    }

}
