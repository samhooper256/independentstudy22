package day3;

import java.util.*;
import java.util.function.*;

public class Examples3 {

	public static void main(String[] args) {
		List<Integer> list = new ArrayList<>();
		Collections.addAll(list, 2, 3, 6, 1, 2, 4);
//		applyToAll(list, i -> i * i);
//		System.out.println(list);
		
//		list.replaceAll(i -> i - 5);
//		System.out.println(list);
//		
//		list.forEach(i -> System.out.println(i));
		
//		String[] strs = {"hi", "bye", "noon", "bear", "care"};
//		List<String> strList = toList(strs, () -> new ArrayList<>());
//		System.out.println(strList);
		
		List<Integer> nums = List.of(1, 7, 2, 3, 19, 12);
		Function<Integer, String> f = i -> String.valueOf((char) (i + 'A' - 1));
		System.out.println(map(nums, f));
	}
	
	public static <T, R> List<R> map(List<T> list, Function<T, R> function) {
		List<R> result = new ArrayList<>();
		for(T item : list)
			result.add(function.apply(item));
		return result;
	}
	
	public static <T> List<T> toList(T[] array, Supplier<List<T>> listFactory) {
		if(array == null)
			throw new IllegalArgumentException("The array is null");
		List<T> list = listFactory.get();
		for(T item : array)
			list.add(item);
		return list;
	}
	
	public static <T> void applyToAll(List<T> list, UnaryOperator<T> function) {
		for(int i = 0; i < list.size(); i++) {
			T current = list.get(i);
			T after = function.apply(current);
			list.set(i, after);
		}
	}
	
	
	
	
}
