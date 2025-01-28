package algs11;

class Node<T extends Comparable<T>> {
    Node<T> left, right;
    T value;
    public Node(T value){
        this.value = value;
    }
    public Node(T value, Node<T> left, Node<T> right){
        this.value = value;
        this.left = left;
        this.right = right;
    }

}

public class TreeComparable<T extends Comparable<T>> {
    
    Node<T> root;
    public void insert(T v){

        // TODO
    }

    public boolean lookup(T v){
        return lookup(root, v);

    }

    public static <T extends Comparable<T>> boolean lookup(Node<T> node, T v){
        if (node == null) return false;
        if (v.compareTo(node.value) == 0) return true;
        if (v.compareTo(node.value) < 0) return lookup(node.left, v);
        return lookup(node.right, v);
    }

    public T min(){
        return min(root);
    }

    private static <T extends Comparable<T>> T min(Node<T> node){
        if (node.left == null) return node.value;
        return min(node.left);
    }

    private static <T extends Comparable<T>> T min2(Node<T> node){
        Node<T> current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current.value;
    }


    public T max(){
        // TODO
        return null;
    }

    public static boolean isBST1(TreeComparable<Integer> node){
        // TODO
        return false;
        // ALL VALUES IN LEFT < n.value < ALL VALUES IN RIGHT    
        // isBST(n.left) && isBST(n.right)
    }

    public static boolean  isBST2(TreeComparable<Integer> node){
        // TODO
        return false;
    }
    
}
