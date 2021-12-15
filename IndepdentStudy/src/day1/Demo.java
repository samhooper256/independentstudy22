package day1;

import java.util.*;

public class Demo {

	public static void main(String[] args) {
		List<String> list = List.of("Adi", "Sam", "Nawaf", "Dhruv", "Aditi", "Yasmeen", "Akshee", "Andy", "Angie",
				"Angali", "Betsegaw", "Julia", "Linh", "Maddie", "Reagan", "Vaibhav", "Victoria");
		
		list.stream()
		.map(String::strip)
		.distinct()
		.sorted(Comparator.comparing(String::length).thenComparing(Comparator.naturalOrder()))
		.forEachOrdered(System.out::println);
		
		
	}
	
}
