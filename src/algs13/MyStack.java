package algs13;

import java.lang.reflect.Array;
import java.util.ArrayList;

// OK to assume that Stack will not grow too big
public class MyStack<T> implements StackInterface {
    int[] data;
    int top; // size of the stack...
             // i.e the index of the next empty slot
             //data[0..top-1] are the elements on the stack

    public MyStack(int N) {
        top = 0;
        data = new int[N];

    }

    private void resize(int N){
        int[] temp = new int[N];
        for (int i = 0; i < data.length; i++) {
            temp[i] = data[i];
        data = temp;
        
        // what happens to the old data array?
        // it is garbage collected, because the program CANNOT
        // access it any more

        }
    }
    public void push(int item) {
        if (top==data.length){
            resize(2*data.length);
        }
        data[top++] = item;
    }

    public boolean isEmpty(){
        return top == 0;
    }

    public int pop(){
        top--;
        if (top < data.length/4){
            resize(data.length/2);
        }
        return data[top];
    }

    


        

    
}
