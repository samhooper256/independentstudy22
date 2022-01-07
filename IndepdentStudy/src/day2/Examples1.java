package day2;

import java.util.*;

public class Examples1 {

	public static void main(String[] args) {
		List<Integer> list = List.of(2, 3, 4);
		System.out.println(sum(list));
		List<Integer> a = List.of(1, 2, 3);
		List<Double> b = List.of(1.1, 2.2, 3.3);
		List<Number> nums = fuse(a, b);
		System.out.println(nums);
	}
	
	static double sum(Collection<? extends Number> coll) {
		double sum = 0;
		for(Number n : coll)
			sum += n.doubleValue();
		return sum;
	}
	
	static <R> List<R> fuse(List<? extends R> a, List<? extends R> b) {
		List<R> result = new ArrayList<>();
		for(R item : a)
			result.add(item);
		for(R item : b)
			result.add(item);
		return result;
	}
	
}
