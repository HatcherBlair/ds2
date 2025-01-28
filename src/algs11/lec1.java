package algs11;


public class lec1 {
    static class Node {
        int value;
        Node next;
        public Node (int value, Node next){
            this.value = value;
            this.next = next;
        }
    }
    public static void main(String[] args){
        Node n1 = new Node(1, null);
       
        Node n3 = new Node(3,  new Node(2, n1));

        Node n4 = n3;
        // explore the object reference graphs here
        int i =1;
        System.out.println("out");
    }
}
