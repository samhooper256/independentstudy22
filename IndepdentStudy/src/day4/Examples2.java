package day4;

import java.util.*;
import java.util.function.*;

public class Examples2 {

	public static void main(String[] args) {
		List<String> list = List.of("a", "b", "c");
//		list.forEach(x -> System.out.println(x));
		//Consumer<? super String>
		// void accept(String)
		// void println(String)
//		list.forEach(System.out::println);
		
//		List<Double> doubles = new ArrayList<>(List.of(4.0, 9.0, 16.0, 25.0));
//		doubles.replaceAll(x -> Math.sqrt(x));
//		doubles.replaceAll(Math::sqrt);
//		System.out.println(doubles);
		
//		int[] arr = {3, -1, 9, -11, 34, 4};
//		map(arr, x -> Math.abs(x));
//		map(arr, Math::abs);
//		System.out.println(Arrays.toString(arr));
		
		int[] arr2 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		
		System.out.println(count(arr2, Examples2::isPrime));
		
	}
	
	static int count(int[] arr, IntPredicate condition) {
		int count = 0;
		for(int item : arr)
			if(condition.test(item))
				count++;
		return count;
	}
	
	static boolean isPrime(int n) {
		if(n <= 1)
			return false;
		if(n == 2)
			return true;
		int sqrt = (int) Math.sqrt(n);
		for(int i = 2; i <= sqrt; i++)
			if(n % i == 0)
				return false;
		return true;
	}
	
	static void map(int[] arr, IntUnaryOperator function) {
		for(int i = 0; i < arr.length; i++)
			arr[i] = function.applyAsInt(arr[i]);
	}
	
}
