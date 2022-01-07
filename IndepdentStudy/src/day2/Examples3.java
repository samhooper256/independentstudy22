package day2;

import java.util.*;

public class Examples3 {

	public static void main(String[] args) {
		Map<String, Integer> map = Map.of("a", 3, "b", 7, "carrot", 12);
		printMap(map);
		List<Object> objs = new ArrayList<>();
		objs.add("zzz");
		List<String> strs = List.of("a", "b", "c");
		addContents(objs, strs);
		System.out.println(objs);
	}
	
	static <T> void addContents(Collection<? super T> coll1, Collection<T> coll2) {
		coll1.addAll(coll2);
	}
	
	static void printMap(Map<?, ?> map) {
		for(Object key : map.keySet())
			System.out.println(key + " : " + map.get(key));
//		for(Map.Entry<?, ?> entry : map.entrySet()) {
//			System.out.println(entry);
//			System.out.println(entry.getKey() + " : " + entry.getValue());
//		}
	}
	
	
}
