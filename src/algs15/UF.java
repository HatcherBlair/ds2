package algs15;
/**
 *  The {@code UF} interface represents a union-find data data structure.
 *  It supports the <em>union</em> and <em>find</em>
 *  operations, along with a method for determining the number of
 *  disjoint sets.
 */

public interface UF {
	public abstract int find(int p);
	public abstract int count();
	public abstract boolean connected(int p, int q);
	public abstract void union(int p, int q);
	public abstract void toGraphviz();
}
