package day7;

import java.util.*;
import java.util.stream.*;

public class CollectorsReview {

	public static void main(String[] args) {
		List<String> list = List.of("a", "b", "c");
		
		System.out.println(list.stream().collect(Collectors.joining(", ")));
		
		System.out.println(list.stream().collect(Collectors.counting()));
		
		System.out.println(list.stream().collect(
				Collectors.mapping(s -> s + s, Collectors.joining())));
		
		List<List<String>> nest = List.of(list, list, list);
		
		System.out.println(nest.stream().collect(
				Collectors.flatMapping(List::stream, Collectors.counting())));
		
		List<String> words = List.of("cat", "yeah", "nawaf", "yellow",
				"color");
		
		System.out.println(words.stream().collect(
				Collectors.maxBy(Comparator.comparing(String::length))));
		
		int size = words.stream().collect(
				Collectors.collectingAndThen(Collectors.counting(), l -> l.intValue()));
		
		Map<String, Integer> lenMap = words.stream().collect(
			Collectors.toMap(
				s -> s,
				s -> s.length()
			)
		);
		
		System.out.println(lenMap);
		
		Map<Boolean, List<String>> partition = words.stream().collect(
			Collectors.partitioningBy(s -> s.length() > 3)
		);
		
		System.out.println(partition);
		
		Map<String, Set<String>> grouped = words.stream().collect(
			Collectors.groupingBy(s -> s.substring(0, 1),
					Collectors.toSet())
		);
		
		System.out.println(grouped);
		
	}
	
}
