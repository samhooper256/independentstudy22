package day1;

import java.util.*;

public class Examples2 {

	public static void main(String[] args) {
//		Number n = 5;
//		double value = n.doubleValue();
//		
//		Collection<Number> coll = Set.of(2, 3, 4, 7, 9);
//		
//		System.out.println(sum(coll));
		
		List<Integer> list1 = List.of(2, 3, 4, 5);
		List<Double> list2 = List.of(2.3, 4.4, 6.6, 4.3);
		
		List<Number> combined = fuse(list1, list2);
		
		System.out.println(combined);
		
		List<String> list3 = List.of("a", "b", "c");
		
		List<Object> combined2 = fuse(list1, list3);
		
		for(Object o : combined2)
			System.out.println(o.getClass());
		
		System.out.println(combined2);
		
		System.out.println(fuse(combined, combined2));
		
	}
	
	
	public static <N extends Number> double sum(Collection<N> coll) {
		double sum = 0;
		for(Number n : coll)
			sum += n.doubleValue();
		return sum;
	}
	
	public static <R, A extends R, B extends R> List<R> fuse(List<A> a, List<B> b) {
		List<R> result = new ArrayList<>();
		for(R item : a)
			result.add(item);
		for(R item : b)
			result.add(item);
		return result;
	}
	
}
