package day1demoscomplete;

import java.util.*;
import java.util.stream.Collectors;

public class Demo3 {

	public static void main(String[] args) {
		/* Given a List<String>, create a Map<Integer, Set<String>> mapping each length of a string to the set of all
		 * strings in the list that have that length. The strings in the sets should be in sorted order.*/
		
		List<String> names = List.of("Dhruv", "Adi", "Sam", "Nawaf", "Aditi", "Yasmeen", "Akshee", "Andy", "Angie",
				"Angali", "Betsegaw", "Julia", "Linh", "Maddie", "Reagan", "Vaibhav", "Victoria");
		
		Map<Integer, Set<String>> map =
				names.stream().collect(Collectors.groupingBy(String::length, Collectors.toCollection(TreeSet::new)));
		
		map.forEach((length, set) -> System.out.printf("%d : %s%n", length, set));
		
	}
	
}
