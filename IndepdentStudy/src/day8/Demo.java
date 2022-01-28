package day8;

import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Demo {
	
	public static void main(String[] args) throws Throwable {
		String[] arr = {"a", "b", "c", "d", "e", "f", "g"};
		
//		IntStream.range(0, arr.length)
//		.filter(i -> i % 2 != 0)
//		.mapToObj(i -> arr[i])
//		.forEachOrdered(System.out::println);
		
		String str = "hello there";
		
//		str.chars()
//		.forEachOrdered(System.out::println);
		
		BufferedReader br = new BufferedReader(new FileReader("src/day8/input.txt"));
		
		List<String> list = br.lines().collect(Collectors.toList());
		
		System.out.println(list);
		
	}
	
}
