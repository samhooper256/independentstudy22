package day5;

import java.util.*;

public class Demo2 {

	public static void main(String[] args) {
		List<String> list = List.of("wreck", "it", "ralph", "marker", "table", "tea", "by",
				"eat", "looooooooong");
		
		String[] arr = list.stream().sorted(
					Comparator.comparing(String::length)
					.thenComparing(Comparator.naturalOrder())
					.reversed()
				)
				.toArray(String[]::new);
			
//		list.stream().sorted()
		
//		System.out.println(Arrays.toString(arr));
		
		List<Scanner> scanners = List.of(new Scanner("hello"), new Scanner("goodbye"));
		
		scanners.stream().sorted().forEachOrdered(System.out::println);
		
	}
	
}
