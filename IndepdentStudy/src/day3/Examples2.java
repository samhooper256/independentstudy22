package day3;

import java.util.*;
import java.util.function.*;

public class Examples2 {

	public static void main(String[] args) {
//		List<String> strs = new ArrayList<>();
//		Collections.addAll(strs, "a", "a", "b");
//		doAction(strs, s -> System.out.println(s));
//		List<Integer> integers = List.of(2,5,3);
//		doAction(integers, i -> System.out.println(i * i));
		List<Integer> list = new ArrayList<>(List.of(1, 7, -3, 10, -8, 2));
		Predicate<Object> condition = obj -> obj == null;
		removeIf(list, condition);
		System.out.println(list);
		
		List<String> strs = new ArrayList<>(List.of("", "hi", "bye", "    ", "crab", "apple", ""));
		removeIf(strs, s -> s.isEmpty());
		System.out.println(strs);
		
		list.removeIf(i -> i % 2 == 0);
		System.out.println(list);
	}
	
	public static <T> void removeIf(Collection<T> coll, Predicate<? super T> condition) {
		Iterator<T> itr = coll.iterator();
		while(itr.hasNext()) {
			T next = itr.next();
			if(condition.test(next))
				itr.remove();
		}
	}
	
	public static <T> void doAction(Collection<T> coll, Consumer<T> action) {
		for(T obj : coll)
			action.accept(obj);
	}
	
}
