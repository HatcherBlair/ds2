package algs11;
import stdlib.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Hello {
static class List {
	public int value;
	public List next;
	public List (int value, List next) {
		this.value = value;
		this.next = next;
	}
}



// Precond: Forall i, lists[i] is sorted
// Return: A sorted list containing all the elements of lists
// Postcond: arguments are unchanged
public static List merge (ArrayList<List> lists) {
	List result = null;
	for (List list : lists) {
		result = merge (result, list);
	}
	return result;
}



public static List mergeRecursive(ArrayList<List> lists) {
	if (lists.size() == 0) return null;
	if (lists.size() == 1) return lists.get(0);
	int mid = lists.size() / 2;

	return merge (mergeRecursive(new ArrayList<List> (lists.subList(0, mid))),
	 mergeRecursive(new ArrayList<List> (lists.subList(mid+1, lists.size()))));
}
	

// Precond: list1,list2 is sorted
// Return: A sorted list containing all the elements of both lists
// Postcond: arguments are unchanged

public static List merge (List list1,List list2){ // |list1 +list2|
	List result = null;
	while (list1 != null && list2 != null) {
		if (list1.value < list2.value) {
			result = new List (list1.value, result);
			list1 = list1.next;
		} else {
			result = new List (list2.value, result);
			list2 = list2.next;
		}
	}
	while (list1 != null) {
		result = new List (list1.value, result);
		list1 = list1.next;
	}
	while (list2 != null) {
		result = new List (list2.value, result);
		list2 = list2.next;
	}
	return result;
}

public static int sum (List list) {
	if (list == null) return 0;
	return list.value + sum (list.next);
}

public static int sumIterative (List list) {
	int sum = 0;
	while (list != null) {
		sum += list.value;
		list = list.next;
	}
	return sum;
}
public static void f(int x){
	x = x+1;
}
	public static void main2 (String[] args) throws FileNotFoundException {
		File f = new File ("data/words3.txt");
		Scanner file = new Scanner (f);
		String s = file.next();
		List list = new List (1, new List (2, new List (3, null)));
		
		StdOut.println ( s);
	}

	public static void main(String[] arg){
		String s = "Hello";
		for (int i = 0; i < s.length(); i++) {
			StdOut.println (s.charAt(i));
		}

	}
}