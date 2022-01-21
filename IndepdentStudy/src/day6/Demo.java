package day6;

import java.util.*;
import java.util.stream.*;

public class Demo {

	public static void main(String[] args) {
//		List<String> list = List.of("a", "bat", "carrot");
//		List<Integer> lengths = list.stream().map(String::length)
//				.collect(Collectors.toList());
//		System.out.println(lengths);
		
//		Optional<String> opt = Optional.empty();
//		
//		System.out.println(opt.isPresent());
//		System.out.println(opt.get());
//		System.out.println(opt.orElse("bye"));
//		
//		OptionalInt optInt = OptionalInt.of(7);
//		System.out.println(optInt.getAsInt());
		
//		List<Integer> nums = List.of(1, 3, 7, 2, 9);
//		
//		Optional<Integer> opt = nums.stream().filter(x -> x % 2 == 0)
//			.findFirst();
//		
//		System.out.println(opt.isPresent());
//		System.out.println(opt.orElse(-1));
//		System.out.println(opt.get());
		
//		List<String> words = List.of("cat", "koala", "dog");
//		
//		//true if empty
//		boolean result = words.stream().allMatch(s -> s.length() >= 4);
//		
//		System.out.println(result);
//		
//		//true if empty
//		System.out.println(words.stream().noneMatch(s -> s.endsWith("y")));
//		
//		//false if empty
//		System.out.println(words.stream().anyMatch("cat"::equals));
//		
//		long count = words.stream().count();
//		
//		int asInt = (int) count;
		
		
		List<String> strs = List.of("a", "b", "c", "d", "e", "f", "g");
		
//		strs.stream().skip(2).forEachOrdered(System.out::println);
//		strs.stream().skip(10).forEachOrdered(System.out::println);
		
//		strs.stream().limit(4).forEachOrdered(System.out::println);
//		strs.stream().limit(100).forEachOrdered(System.out::println);
		
		//[[a, b], [c], [d, e, f]]
//		List<List<String>> nested = List.of(List.of("a", "b"), List.of("c"),
//				List.of("d", "e", "f"));
//		String[] arr =
//				nested.stream().flatMap(l -> l.stream()).toArray(String[]::new);
//		
//		System.out.println(Arrays.toString(arr));
		
//		List<String> lines = List.of("a good cat", "nawaf is here",
//				"tree yes no");
//		
//		List<String> fileWords = lines.stream()
//				.flatMap(line -> Arrays.stream(line.split(" ")))
//				.collect(Collectors.toList());
//		
//		System.out.println(fileWords);
		
		List<Integer> numbers = List.of(-1, -6, -7, 8, 21, -3, 34, 4);
		
//		numbers.stream().dropWhile(x -> x < 0)
//			.forEachOrdered(System.out::println);
		
		numbers.stream().takeWhile(x -> x < 0)
			.forEachOrdered(System.out::println);
		
	}
	
}

