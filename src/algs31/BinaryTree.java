package algs31;

public class BinaryTree {

    // Root node pointer. Will be null for an empty tree.
  private Node root;
 

  /*
   --Node--
   The binary tree is built using this nested node class.
   Each node stores one data element, and has left and right
   sub-tree pointer which may be null.
   The node is a "dumb" nested class -- we just use it for
   storage; it does not have any methods.
  */
  private static class Node {
    Node left;
    Node right;
    int data;

    Node(int newData) {
      left = null;
      right = null;
      data = newData;
    }
  }

  /**
   Creates an empty binary tree -- a null root pointer.
  */
  public  BinaryTree() {
    root = null;
  }
 

  /**
   Returns true if the given target is in the binary tree.
   Uses a recursive helper.
  */
  public boolean lookup(int data) {
    return(lookup(root, data));
  }
 

  /**
   Recursive lookup  -- given a node, recur
   down searching for the given data.
  */
  private static boolean lookup(Node node, int data) {
    if (node==null) {
      return(false);
    }

    if (data==node.data) {
      return(true);
    }
    else if (data<node.data) {
      return(lookup(node.left, data));
    }
    else {
      return(lookup(node.right, data));
    }
  }
 

  /**
   Inserts the given data into the binary tree.
   Uses a recursive helper.
  */
  public void insert(int data) {
    root = insert(root, data);
  }
 

  /**
   Recursive insert -- given a node pointer, recur down and
   insert the given data into the tree. Returns the new
   node pointer (the standard way to communicate
   a changed pointer back to the caller).
  */
  private static Node insert(Node node, int data) {
    if (node==null) {
      node = new Node(data);
    }
    else {
      if (data <= node.data) {
        node.left = insert(node.left, data);
      }
      else {
        node.right = insert(node.right, data);
      }
    }

    return(node); // in any case, return the new pointer to the caller
  }

  int ht(Node node) {
    if (node == null) return 0;
    return 1 + Math.max(ht(node.left), ht(node.right));
  } 

  int diameter(Node n)


  public static void main(String[] args){
    BinaryTree tree = new BinaryTree();
    tree.insert(5);
    tree.insert(3);
    tree.insert(7);
    tree.insert(1);
    tree.insert(4);
    tree.insert(6);
    tree.insert(8);
    System.out.println(tree.lookup(5));
    System.out.println(tree.lookup(3));
    System.out.println(tree.lookup(7));
    System.out.println(tree.lookup(1));
    System.out.println(tree.lookup(4));
    System.out.println(tree.lookup(6));
    System.out.println(tree.lookup(8));
    System.out.println(tree.lookup(9));
  }
    
}
