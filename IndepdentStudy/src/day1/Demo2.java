package day1;

import java.util.*;
import java.util.stream.*;

public class Demo2 {

	public static void main(String[] args) {
		/** Print out the first 100 squares that are not a multiple of 5. */
		
		IntStream.iterate(1, i -> i + 1)
			.map(i -> i * i)
			.filter(i -> i % 5 != 0)
			.limit(100)
			.forEach(System.out::println);
	}
	
}
