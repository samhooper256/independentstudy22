package day2;

import java.util.*;

public class Examples2 {

	public static void main(String[] args) {
		Set<String> set = Set.of("a", "b", "c");
		printAll(set);
		System.out.println();
		List<Integer> list = List.of(1, 2, 3);
		printAll(list);
		System.out.println();
		List<String> letters = new ArrayList<>();
		Collections.addAll(letters, "a", "b", "x", "p", "k", "j", "h", "l", "m");
		removeRange(letters, 1, 4);
		System.out.println(letters);
	}
	
	static void removeRange(List<?> list, int start, int end) {
		for(int i = end - 1; i >= start; i--)
			list.remove(i);
//		list.subList(start, end).clear();
	}
	
	static void printAll(Collection<?> coll) {
		for(Object o : coll)
			System.out.println(o);
	}
	
}
