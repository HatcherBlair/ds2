package algs35;
import stdlib.*;
/* ***********************************************************************
 *  Compilation:  javac SparseMatrix.java
 *  Execution:    java SparseMatrix
 *
 *  A sparse, square matrix, implementing using two arrays of sparse
 *  vectors, one representation for the rows and one for the columns.
 *
 *  For matrix-matrix product, we might also want to store the
 *  column representation.
 *
 *************************************************************************/

public class XSparseMatrix {
	private final int N;                 // N-by-N matrix
	private final SparseVector[] rows;   // the rows, each row is a sparse vector

	// initialize an N-by-N matrix of all 0s
	public XSparseMatrix(int N) {
		this.N  = N;
		rows = new SparseVector[N];
		for (int i = 0; i < N; i++) rows[i] = new SparseVector(N);
	}

	// put A[i][j] = value
	public void put(int i, int j, double value) {
		if (i < 0 || i >= N) throw new Error("Illegal index");
		if (j < 0 || j >= N) throw new Error("Illegal index");
		rows[i].put(j, value);
	}

	// return A[i][j]
	public double get(int i, int j) {
		if (i < 0 || i >= N) throw new Error("Illegal index");
		if (j < 0 || j >= N) throw new Error("Illegal index");
		return rows[i].get(j);
	}

	// return the number of nonzero entries (not the most efficient implementation)
	public int nnz() {
		int sum = 0;
		for (int i = 0; i < N; i++)
			sum += rows[i].nnz();
		return sum;
	}

	// return the matrix-vector product b = Ax
	public SparseVector times(SparseVector x) {
		if (N != x.size()) throw new Error("Dimensions disagree");
		SparseVector b = new SparseVector(N);
		for (int i = 0; i < N; i++)
			b.put(i, rows[i].dot(x));
		return b;
	}

	// return C = A + B
	public XSparseMatrix plus(XSparseMatrix B) {
		XSparseMatrix A = this;
		if (A.N != B.N) throw new Error("Dimensions disagree");
		XSparseMatrix C = new XSparseMatrix(N);
		for (int i = 0; i < N; i++)
			C.rows[i] = A.rows[i].plus(B.rows[i]);
		return C;
	}


	// return a string representation
	public String toString() {
		String s = "N = " + N + ", nonzeros = " + nnz() + "\n";
		for (int i = 0; i < N; i++) {
			s += i + ": " + rows[i] + "\n";
		}
		return s;
	}


	// test client
	public static void main(String[] args) {
		XSparseMatrix A = new XSparseMatrix(5);
		SparseVector x = new SparseVector(5);
		A.put(0, 0, 1.0);
		A.put(1, 1, 1.0);
		A.put(2, 2, 1.0);
		A.put(3, 3, 1.0);
		A.put(4, 4, 1.0);
		A.put(2, 4, 0.3);
		x.put(0, 0.75);
		x.put(2, 0.11);
		StdOut.println("x     : " + x);
		StdOut.println("A     : " + A);
		StdOut.println("Ax    : " + A.times(x));
		StdOut.println("A + A : " + A.plus(A));
	}

}
