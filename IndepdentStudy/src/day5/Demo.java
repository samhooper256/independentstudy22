package day5;

import java.util.*;
import java.util.stream.*;

public class Demo {

	/*
	 * Creating streams
	 * - list.stream()
	 * - Arrays.stream(arr)
	 * - Arrays.stream(arr, int, int)
	 * 
	 * Intermediate operations:
	 * - map (mapToInt, mapToDouble)
	 * - filter
	 * - distinct
	 * - sorted
	 * 
	 * Terminal operations:
	 * - forEachOrdered (forEach)
	 * - toArray
	 * 
	 */
	public static void main(String[] args) {
		String[] strs = {"a", "b", "c", "apple"};
		
		//intermediate operations: return a stream.
		//terminal operations: don't return a stream.
		Arrays.stream(strs)
			.map(s -> s + s)
			.filter(s -> s.startsWith("a"))
			.forEachOrdered(System.out::println);
		
		List<Integer> list = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
		
		String[] arr = list.stream().map(i -> i + "e").toArray(String[]::new);
		
		System.out.println(Arrays.toString(arr));
		
		int[] ints = {4, -2, 6, 9, 6, 4};
		double[] doubles = {1.2, 3, 0, -.5, 10.5};
		
		IntStream istream = Arrays.stream(ints);
		DoubleStream dstream = Arrays.stream(doubles);
		
		List<String> words = List.of("hat", "bear", "car", "peanut", "bottle",
				"hat", "bear", "hat", "bottle");
		
		words.stream()
			.mapToInt(String::length)
			.forEachOrdered(System.out::println);
		
		words.stream().forEach(System.out::println);
		
		String[] uniques = words.stream().distinct().toArray(String[]::new);
		
		System.out.println(Arrays.toString(uniques));
		
	}
	
}
