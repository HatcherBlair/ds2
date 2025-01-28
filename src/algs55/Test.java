package algs55;

import java.util.BitSet;
import java.util.PriorityQueue;
import java.io.File;
import java.io.FileInputStream;

public class Test {

    
    
    public static void main(String[] args){

       

        String s = "Hel";
        File f = new File(s);
        BitStream bs;
        try {
         bs = new BitStream(f, "w");
         BitSet b = new BitSet(3);
         b.set(1,true);  //  10000000, 01000000
         bs.writeBits(b,3);
         bs.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }

        try {
            bs = new BitStream(f, "r");
            while (bs.hasMoreBits()) {
                System.out.println(bs.readBits(1));
            }
              bs.close();
            }
           catch (Exception e) {
               System.out.println(e);
           }
   

        System.out.println("SEPARATOR");
        File inputFile = new File("sample.txt");
        try {
            FileInputStream inStream = new FileInputStream(inputFile);
            int a = inStream.read();
            System.out.println(a);
            int b = inStream.read();
            System.out.println(b);
        }
        catch (Exception e) {
            System.out.println(e);
        }

        PriorityQueue<Node> pq = new PriorityQueue<Node>();
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(-1);

        pq.add(n1);
        pq.add(n2);
        pq.add(n3);
        System.out.println(pq.poll().key);
        System.out.println(pq.poll().key);

        
    }
    
}
