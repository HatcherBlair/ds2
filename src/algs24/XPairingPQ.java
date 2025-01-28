package algs24;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class XPairingPQ implements PQ {
	static class Node {
		Node (double x, LinkedList<Node> sh) { item = x; subheaps = sh; }
		double item;
		LinkedList<Node> subheaps;
		public String toString () {
			StringBuilder sb = new StringBuilder ();
			sb.append(item);
			sb.append(" [ ");
			for (Node x : subheaps) {
				sb.append(x.toString());
				sb.append(' ');
			}
			sb.append(']');
			return sb.toString();		
		}
	};
	XPairingPQ (int N) {}
	
	Node head = null;
	int _size = 0;
	
	public String toString () {
		if (head == null)
			return "";
		return head.toString();
	}
	public int size() {
		return _size;
	}
	public boolean isEmpty() {
		return _size == 0;
	}
	
	static private Node meld (Node h1, Node h2) {
		if (h1 == null) return h2;
		if (h2 == null) return h1;
		if (h1.item < h2.item) {
			h1.subheaps.add(h2);
			return h1;
		}
		h2.subheaps.add(h1);
		return h2;
	}
	public void insert(double x) {
		head = meld (head, new Node (x, new LinkedList <> ()));
	}
	public double min() { return head.item; }
	static private Node merge_pairs (List<Node> heaps) {
		if (heaps.isEmpty())
			return null;
		Node cur = null;
		Iterator<Node> it = heaps.iterator ();
		if (heaps.size() % 2 == 1)
			cur = it.next();
		while (it.hasNext())
			cur = meld (cur, meld (it.next(), it.next()));
		return cur;
	}
	public double delMin() { 
		double ret = head.item;
		head = merge_pairs (head.subheaps); 
		return ret; 
	}
	public void toGraphviz() {}
}
