package day1demoscomplete;

import java.util.*;

public class Demo1 {

	public static void main(String[] args) {
		/* Given this int[], add 5 to every value then convert it to a List<Integer>.*/
		int[] nums = {1, 6, 23, 5, 0, 18};
		
		List<Integer> list = Arrays.stream(nums).map(x -> x + 5).boxed().toList();
		
		System.out.println(list);
		
		list.forEach(System.out::println);
	}
	
}
