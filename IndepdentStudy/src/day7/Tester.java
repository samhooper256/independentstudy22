package day7;

import java.util.*;
import java.util.stream.Collectors;

public class Tester {

	public static void main(String[] args) {
//		List<String> list = List.of("a", "bay", "hi");
//		
//		List<Integer> lenghts = list.stream().map(String::length)
//				.collect(Collectors.toCollection(ArrayList::new));
		
		List<Integer> nums = List.of(1, 2, 3, 4, 5, -2, 8, 4);
		
		int sum = nums.stream().reduce((a, b) -> a + b).get();
		
		System.out.println(sum);
		
		int max = nums.stream().reduce(Math::max).get();
		
		System.out.println(max);
		
		List<Complex> com = List.of(new Complex(3, 4), new Complex(-4, -8),
				new Complex(0, 9));
		
		Complex csum = com.stream().reduce(Complex::add).get();
		
		System.out.println(csum);
		
		List<String> strs = List.of("axe", "diamond", "leaf", "stone");
		
		String concat = strs.stream().map(s -> s.substring(0, 1))
				.reduce((a, b) -> a + ", " + b).get();
		
		System.out.println(concat);
		
		System.out.println(
			strs.stream().map(x -> x.length()).reduce((a, b) -> a + b).get()
		);
		
		int sum2 = nums.stream().reduce(0, Integer::sum);
		
	}
	
}
