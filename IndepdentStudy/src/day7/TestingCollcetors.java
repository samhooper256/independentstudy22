package day7;

import java.util.*;
import java.util.stream.*;

public class TestingCollcetors {

	public static void main(String[] args) {
//		Collector<String, ArrayList<String>, ArrayList<String>> c =
//				Collector.of(ArrayList::new, (list, x) -> list.add(x), 
//						(list1, list2) -> {
//							list1.addAll(list2);
//							return list1;
//						});
//		
//		String[] strs = {"a", "j", "hi"};
//		ArrayList<String> list = Arrays.stream(strs).collect(c);
//		System.out.println(list);
		
//		List<String> list = List.of("underwater", "pineapple", "sponge");
//		String concat = list.stream().collect(Collectors.joining(", ", "[", "]"));
//		System.out.println(concat);
//		
//		System.out.println(list.stream().collect(Collectors.counting()));
//		
//		List<Integer> nums = List.of(6, -2, 3, 4, 5);
////		
//		System.out.println(nums.stream()
//			.collect(Collectors.mapping(Object::toString, Collectors.joining())));
////		
//		System.out.println(nums);
		List<State> states = Tester3.STATES;
//		
		List<City> cities = states.stream().flatMap(s -> s.getCities().stream())
				.collect(Collectors.toList());
//		
//		List<City> cities2 = states.stream().collect(
//			Collectors.flatMapping(
//				s -> s.getCities().stream(),
//				Collectors.toList()
//			)
//		);
//		
//		City largest = cities.stream().collect(Collectors.maxBy(
//				Comparator.comparing(City::getPopulation))).get();
//		City smallest = cities.stream().collect(Collectors.minBy(
//				Comparator.comparing(City::getPopulation))).get();
//		System.out.println(largest);
//		System.out.println(smallest);
//		
//		int numCities = cities.stream().collect(Collectors.collectingAndThen(
//				Collectors.toList(), l -> l.size()));
//		
		List<String> cityNames = cities.stream().map(City::getName)
			.collect(Collectors.collectingAndThen(
				Collectors.toList(),
				l -> Collections.unmodifiableList(l))
			);
//		
//		System.out.println(numCities);
//		
//		Map<State, String> stateMap = states.stream().collect(
//			Collectors.toMap(s -> s, s -> s.getName())
//		);
		
		Set<Person> ppl = Tester3.generatePeople(100);
		
		Map<City, String> map = ppl.stream().collect(
			Collectors.groupingBy(p -> p.getCity(),
				Collectors.mapping(Person::getFirstName,
					Collectors.collectingAndThen(
						Collectors.minBy(Comparator.naturalOrder()),
						Optional::get
					)
				)
			)
		);
		
//		map.forEach((k, v) -> System.out.println(k + " : " + v));
		
//		List<String> words = List.of("apple", "x-ray", "xylophone", "test");
//		
//		Map<Boolean, Set<String>> partition = words.stream().collect(
//			Collectors.partitioningBy(s -> s.contains("x"),
//					Collectors.toSet())
//		);
//		
//		System.out.println(partition);
		
	}
	
}
