package day1;

import java.util.*;

public class Examples {

	public static void main(String[] args) {
		//sum, fuse, removeAllOccurrences, reverse
//		List<String> list = new ArrayList<>();
//		list.add("a");
//		list.add("b");
//		list.add("c");
//		
//		reverse(list);
//		
//		System.out.println(list);
//		
//		List<Integer> integers = new ArrayList<>();
//		integers.add(1);
//		integers.add(2);
//		integers.add(3);
//		integers.add(4);
//		
//		reverse(integers);
//		
//		System.out.println(integers);
		
//		List<String> strs = new ArrayList<>();
//		Collections.addAll(strs, "a", "b", "c", "a", "b", "d", "e", "a", "b");
//		
//		System.out.println(strs);
//		removeAllOccurrences(strs, "a");
//		System.out.println(strs);
	}
	
	public static <T> void reverse(List<T> list) {
		for(int i = 0; i < list.size() / 2; i++) {
			T temp = list.get(i);
			list.set(i, list.get(list.size() - i - 1));
			list.set(list.size() - i - 1, temp);
		}
	}
	
	public static <T> void removeAllOccurrences(List<T> list, T item) {
		for(int i = list.size() - 1; i >= 0; i--)
			if(list.get(i).equals(item))
				list.remove(i);
	}
	
}
