package day1;

import java.util.*;

public class Raw {

	public static void main(String[] args) {
		List<String> strings = new ArrayList<>();
		
		strings.add("a");
		strings.add("b");
		
		List raw = strings;
		
		List<Integer> integers = raw;
		
		integers.add(5);
		integers.add(6);
		
		System.out.println(raw);
		
		Integer first = integers.get(0);
		
	}
	
}
