package algs15.perc;

//import stdlib.*;
//import algs15.*;

// Uncomment the import statements above.

// You can test this using InteractivePercolationVisualizer and PercolationVisualizer
// All methods should make at most a constant number of calls to a UF data structure.
// You can use more than one UF data structure.
// You can assume that N>1
//
// Note that you can print out a UF structure using its toString method.
// This may be useful for debugging.
public class Percolation {
	int N;
	boolean[] open;
	// TODO: more fields to add here
	public Percolation(int N) {
		if (N < 2) throw new IllegalArgumentException();
		this.N = N;
		this.open = new boolean[N*N];
		// TODO: more to do here
	}
	// open site (row i, column j) if it is not already
	public void open(int i, int j) {
		int x = i*N+j;
		if (!open[x]) {
			open[x] = true;
			// TODO: more to do here.
		}
	}
	// is site (row i, column j) open?
	public boolean isOpen(int i, int j) {
		return open[i*N+j];
	}
	// is site (row i, column j) full?
	public boolean isFull(int i, int j) {
		// TODO
		return false;
	}
	// does the system percolate?
	public boolean percolates() {
		// TODO
		return false;
	}
}