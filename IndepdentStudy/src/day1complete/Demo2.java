package day1complete;

import java.util.stream.IntStream;

public class Demo2 {

	public static void main(String[] args) {
		/** Print out the first 100 squares that are not a multiple of 5.*/
		IntStream.iterate(0, i -> i + 1)
			.map(i -> i * i)
			.filter(i -> i % 5 != 0)
			.limit(100)
			.forEach(System.out::println);
	}
	
}
