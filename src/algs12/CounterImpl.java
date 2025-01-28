package algs12;

public class CounterImpl implements CounterInterface {
    private int count;

    public CounterImpl(int x){
        this.count = x;
    }

    public CounterImpl(){
        this.count = 0;
    }
    
    public void increment(){
        this.count++;
    }
    
    public int get(){
        return this.count;
    }
    
    public void set(int x){
        this.count = x;
    }
    
}
