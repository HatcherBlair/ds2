package algs13;

public class CircularQArray {
    private  String[] a; // only final for not expanding version
    private int front, back;

    // back is the index of the next available slot
    // front is the index of the next item to be removed
    // in a non empty Q
    // front == back means the Q is empty

    public CircularQArray (int capacity) {
        a = new String[capacity];
        front = 0;
        back = 0;
    }

    public boolean isEmpty () {
        return front == back;
    }
    
    private void resize(int capacity) {
        String[] temp = new String[capacity];
        for (int i = 0; i < a.length; i++)
            temp[i] = a[i];
        a = temp;
    }
    public boolean isFull () {
        return (back + 1) % a.length == front;
    }
    public void enQ(String s){
        if (isFull()) resize(2*a.length);
        a[back] = s;
        back = (back +1) % a.length;   
    }

    public int size(){
        return (back - front + a.length) % a.length;
    }
    public String deQ(){
        if (isEmpty()) throw new Error("Q underflow error");
        if (size()<a.length/4) resize(a.length/2);

        String s = a[front];
        front = (front +1) % a.length;
        return s;
    }
}
