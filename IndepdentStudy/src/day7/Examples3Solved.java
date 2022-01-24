package day7;

import java.util.*;
import java.util.function.Function;
import java.util.stream.*;

public class Examples3Solved {

	/* NOTES (UPDATED):
	 * - NEW: Your methods can call other methods within Examples3.
	 * - All examples should be done in one statement (that is, one semicolon).
	 * - No arguments passed to your methods will be null.
	 * - For all methods, if given a collection or array, DO NOT MODIFY the collection or array.
	 * - You can assume the given collection/array does not contain any null elements.
	 * - Examples with an (H) indicate that there are one or more hints for that example at the bottom of this file.
	 */
	
	/* Example 0 (Optional - not in tester): Given a State, return a Stream<City> of its cities. This method may be
	 * useful for later examples :) */
	public static Stream<City> cities(State state) {
		return state.getCities().stream();
	}
	
	/* Example 1: Given a List<Double>, return the product of all the numbers. There will be at least 1 number in
	 * the list (if there is 1 number, return that number). */
	public static double product(List<Double> list) {
		return list.stream().reduce((a, b) -> a * b).get();
	}
	
	/* Example 2: Given a Person[], return the Person with the longest last name. There will be at least one Person in
	 * the array. */
	public static Person longestLastName(Person[] arr) {
		return Arrays.stream(arr).max(Comparator.comparing(p -> p.getLastName().length())).get();
	}
	
	/* Example 3: Given a List<State>, return the state with the smallest population. If there are no states in the
	 * list, return null.  */
	public static State smallestPopulation(List<State> states) {
		return states.stream().min(Comparator.comparingInt(State::getPopulation)).orElse(null);
	}
	
	/* Example 4: Given a collection, return a String containing the elements of that collection separated by spaces
	 * and enclosed in curly braces. In order to get the string representation of each element, you'll have to call
	 * toString(). For example, if given [1, 2, 3, 4, 5], return "{1 2 3 4 5}". */
	public static String formatted(Collection<?> coll) {
		return coll.stream().map(Object::toString).collect(Collectors.joining(" ", "{", "}"));
	}
	
	/* Example 5: Given a State, return the city in that state with the longest name. If multiple cities tie for longest
	 * name, return any one of them. The state will have at least 1 city. */
	public static City longestCity1(State state) {
		return cities(state).max(Comparator.comparing(c -> c.getName().length())).get();
	}
	
	/* Example 6 (H): Given a List<State>, consider all of the cities within all of the states. Return the city with the
	 * longest name. If multiple cities tie for longest name, return any one of them. There will be at least 1 state,
	 * and each state will have at least 1 city. */
	public static City longestCity2(Collection<State> states) {
		//Using longestCity1:
		return states.stream().map(Examples3Solved::longestCity1)
				.max(Comparator.comparing(c -> c.getName().length())).get();
		
		//Without longestCity1 (using flatMap):
//		return states.stream().flatMap(Examples3::cities)
//				.max(Comparator.comparing(c -> c.getName().length())).get();
		
		//All in one collect call:
//		return states.stream().collect(Collectors.flatMapping(s -> s.getCities().stream(),
//				Collectors.maxBy(Comparator.comparing(c -> c.getName().length())))).get();
	}
	
	/* Example 7: Given a Set<String>, return a Map<String, Integer> mapping each string to its length. */
	public static Map<String, Integer> lengthMap(Set<String> list) {
		return list.stream().collect(Collectors.toMap(
				s -> s,
				s -> s.length()
		));
	}
	
	/* Example 8 (H): Given a List<String>, return a HashMap<String, Integer> mapping each string to the number of times
	 * it occurs in the list. This method should run in O(n) where n is the number of strings. */
	public static HashMap<String, Integer> wordCount(List<String> list) {
		return list.stream().collect(Collectors.toMap(
				s -> s,
				s -> 1,
				Integer::sum, //or (a, b) -> a + b
				HashMap::new
		));
	}
	
	/* Example 9 (H): Given a List<Person>, return a Map<City, HashSet<String>> mapping each city represented to a HashSet
	 * of the first names of all the people in that city. */
	public static Map<City, HashSet<String>> getNames(List<Person> list) {
		return list.stream().collect(Collectors.groupingBy(
				Person::getCity,
				Collectors.mapping(Person::getFirstName, Collectors.toCollection(HashSet::new))
		));
	}
		
	/* Example 10 (H): Given a List<String> and a String sub, return a Map<Boolean, Integer> mapping true to the # of
	 * strings containing sub as a substring and false to the # of strings that don't contain sub as a substring. Both
	 * true and false should be present as keys, even if one or both of them map to 0.
	 * For example, the call subSplit([yelp, bout, hurry, yoke], "y") should return {true=3, false=1}. */
	public static Map<Boolean, Integer> subSplit(List<String> list, String sub) {
		return list.stream().collect(Collectors.partitioningBy(s -> s.contains(sub), 
				Collectors.collectingAndThen(Collectors.counting(), l -> l.intValue())));
	}
	
	/* Example 11 (H): Given a List<State>, return a Map<String, List<Integer>> mapping each state name to a list
	 * of its cities' population numbers sorted in descending order. The states will have unique names. */
	public static Map<String, List<Integer>> cityPops(List<State> list) {
		return list.stream().collect(Collectors.toMap(
				s -> s.getName(),
				s -> cities(s).map(City::getPopulation).sorted(Comparator.reverseOrder())
						.collect(Collectors.toList())
		));
	}
	
	/* Example 12: Given a List<State>, return a Map<State, Double> mapping each state to the sum of the populations of
	 * its 5 largest cities. Each state will have at least 5 cities. */
	public static Map<State, Integer> averageCityPop(List<State> list) {
		return list.stream().collect(Collectors.toMap(
				Function.identity(),
				s -> cities(s).map(City::getPopulation).sorted(Comparator.reverseOrder()).limit(5)
						.reduce(Integer::sum).get()
		));
	}
	
	
	
	
	
	/* HINTS:
	 * 
	 * Example 6:
	 * Hint #1: Remember, your method can call other methods (e.g. longestCity1). If you don't want to call
	 * longestCity1, you can use flatMap instead.
	 * 
	 * Example 8:
	 * Hint #1: Use the 4-parameter version of Collectors.toMap.
	 * Hint #2: Map each string to the value 1 by default. I'll let you figure out what the merge function should be.
	 * 
	 * Example 9:
	 * Hint #1: Use Collections.groupingBy.
	 * Hint #2: The downstream collector of groupingBy needs to 1) map each Person to their first name and 2) put the
	 * names into a HashSet.
	 * 
	 * Example 10:
	 * Hint #1: Use Collectors.partitioningBy.
	 * Hint #2: As the downstream Collector to partitioningBy, you'll want to use Collectors.counting(). But how do you
	 * convert that Long into an Integer...?
	 * Hint #3: Use Collectors.collectingAndThen to add a finishing transformation to Collectors.counting() that
	 * converts the Long to an Integer.
	 * Hint #4: Long extends Number, and all Numbers have a method called intValue().
	 * 
	 * Example 11:
	 * Hint #1: First, figure out how to get from a State to a List<Integer> of its population numbers in descending
	 * order.
	 * Hint #2: (cont. of Hint #1) Once you have that, use Collectors.toMap to map each State to that List<Integer>
	 * of population numbers.
	 * 
	 * Example 12:
	 * Hint #1: Like Example 11, first figure out how to get the sum of the populations of a state's 5 largest cities.
	 * Then use Collectors.toMap to map each state to that sum.
	 */
	
}
