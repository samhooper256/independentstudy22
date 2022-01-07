package day2;

import java.util.*;

public class Examples6 {

	public static void main(String[] args) {
		List<List<String>> list = List.of(List.of("a", "b", "c"), List.of("d", "e"), List.of("f", "g", "h"));
		System.out.println(list);
		List<Object> obj = flatten(list);
		System.out.println(obj);
	}

	static <T> List<T> flatten(List<? extends List<? extends T>> list) {
		List<T> result = new ArrayList<>();
		for(List<? extends T> nestedList : list)
			result.addAll(nestedList);
		return result;
	}
	
}
