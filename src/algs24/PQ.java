package algs24;

// This is a toy interface for Priority Queues
// You are not allowed to insert 0.0
public interface PQ {
	public int size();
	public boolean isEmpty();
	public void insert(double x);
	public double min();
	public double delMin();
	public void toGraphviz();
}
