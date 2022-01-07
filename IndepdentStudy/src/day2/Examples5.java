package day2;

import java.util.*;

public class Examples5 {

	public static void main(String[] args) {
		Map<String, Integer> map = Map.of("hello", 5, "beans", 10, "Adi Behre", 6);
		System.out.println(concatenateKeys(map));
		
		Map<String, Integer> map2 = Map.of("a", 1, "b", 2, "c", 3, "d", 4);
		Map<Integer, String> reversed = reverseMap(map2);
		System.out.println(reversed);
	}
	
	static <K, V> Map<V, K> reverseMap(Map<K, V> map) {
		Map<V, K> reversed = new HashMap<>();
		for(K key : map.keySet())
			reversed.put(map.get(key), key);
		return reversed;
	}
	
	static String concatenateKeys(Map<String, ?> map) {
		String result = "";
		for(String key : map.keySet())
			result += key;
		return result;
	}
	
}
