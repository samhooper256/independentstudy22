package day7;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.*;

/** @author Sam Hooper */
public class Tester3 {
	
private static class TestResult {
		
		private static final TestResult SUCCESS = new TestResult(true), FAILURE = new TestResult(false);
		
		public static TestResult build(boolean... caseResults) {
			if(caseResults.length == 0)
				throw new IllegalArgumentException("caseResults.length == 0");
			boolean result = caseResults[0];
			for(boolean cr : caseResults)
				result &= cr;
			return result ? SUCCESS : FAILURE;
		}
		
		public static TestResult exception(Exception e) {
			return new TestResult(false, "exception: " + e.getClass().getSimpleName());
		}
		
		private final boolean success;
		private final String parenthesizedMessage;
		
		public TestResult(boolean success) {
			this(success, null);
		}
		
		public TestResult(boolean success, String parenthesizedMessage) {
			this.success = success;
			this.parenthesizedMessage = parenthesizedMessage;
		}
		
		public boolean isSuccess() {
			return success;
		}
		
		@Override
		public String toString() {
			return isSuccess() ? "success" : parenthesizedMessage == null ? "FAILURE" :
				String.format("FAILURE (%s)", parenthesizedMessage);
		}
		
	}

	public static final City houston = new City("Houston", 2_300_000);
	public static final City sanAntonio = new City("San Antonio", 1_400_000);
	public static final City dallas = new City("Dallas", 1_300_000);
	public static final City austin = new City("Austin", 960_000);
	public static final City fortWorth = new City("Fort Worth", 920_000);
	public static final City elPaso = new City("El Paso", 690_000);
	public static final City arlington = new City("Arlington", 400_000);
	public static final City corpusChristi = new City("Corpus Christi", 330_000);
	public static final City plano = new City("Plano", 290_000);
	public static final City laredo = new City("Laredo", 270_000);
	public static final State texas = new State("Texas", 29_000_000, dallas, austin, fortWorth, houston, sanAntonio,
			elPaso, corpusChristi, plano, laredo, arlington);
	
	public static final City losAngeles = new City("Los Angeles", 3_900_000);
	public static final City sanDiego = new City("San Diego", 1_400_000);
	public static final City sanJose = new City("San Jose", 1_000_000);
	public static final City sanFrancisco = new City("San Francisco", 870_000);
	public static final City fresno = new City("Fresno", 540_000);
	public static final State california = new State("California", 39_500_000, sanFrancisco, losAngeles, fresno, sanJose, sanDiego);
	
	public static final City jacksonville = new City("Jacksonville", 930_000);
	public static final City miami = new City("Miami", 480_000);
	public static final City tampa = new City("Tampa", 400_000);
	public static final City orlando = new City("Orlando", 290_000);
	public static final City stPetersburg = new City("St. Petersburg", 270_000);
	public static final State florida = new State("Florida", 21_500_000, miami, tampa, orlando, jacksonville, stPetersburg);
	
	public static final City nyc = new City("New York City", 8_200_000);
	public static final City buffalo = new City("Buffalo", 250_000);
	public static final City rochester = new City("Rochester", 200_000);
	public static final City yonkers = new City("Yonkers", 200_000);
	public static final City syracuse = new City("Syracuse", 140_000);
	public static final State newYork = new State("New York", 20_000_000, nyc, rochester, yonkers, buffalo, syracuse);
	
	public static final City philadelphia = new City("Philadelphia", 1_500_000);
	public static final City pittsburgh = new City("Pittsburgh", 300_000);
	public static final City allentown = new City("Allentown", 120_000);
	public static final City erie = new City("Erie", 94_000);
	public static final City reading = new City("Reading", 88_000);
	public static final State pennsylvania = new State("Pennsylvania", 13_000_000, erie, reading, philadelphia, allentown, pittsburgh);
	
	public static final List<State> STATES =
			Collections.unmodifiableList(Arrays.asList(newYork, florida, texas, california, pennsylvania));
	public static final List<City> CITIES =
			Collections.unmodifiableList(STATES.stream().flatMap(s -> s.getCities().stream()).collect(Collectors.toList()));
	
	//most popular first names in the U.S. from 1921-2020; taken from here:
	//https://www.ssa.gov/oact/babynames/decades/century.html
	public static final List<String> FIRST_NAMES =
			Collections.unmodifiableList(Arrays.asList(
				"James",
				"Mary",
				"Robert",
				"Patricia",
				"John",
				"Jennifer",
				"Michael",
				"Linda",
				"William",
				"Elizabeth",
				"David",
				"Barbara",
				"Richard",
				"Susan",
				"Joseph",
				"Jessica",
				"Thomas",
				"Sarah",
				"Charles",
				"Karen"
			));
	
	//most common last names in the U.S.; taken from here: 
	//https://babynames.com/blogs/names/1000-most-popular-last-names-in-the-u-s/
	public static final List<String> LAST_NAMES =
			Collections.unmodifiableList(Arrays.asList(
				"Smith",
				"Johnson",
				"Williams",
				"Brown",
				"Jones",
				"Garcia",
				"Miller",
				"Davis",
				"Rodriguez",
				"Martinez",
				"Hernandez",
				"Lopez",
				"Gonzalez",
				"Wilson",
				"Anderson",
				"Thomas",
				"Taylor",
				"Moore",
				"Jackson",
				"Martin"
			));
	
	private static final Random RANDOM = new Random();
	
	private static final Pattern TOKENIZER = Pattern.compile("(?=[-,])|(?<=[-,])");
	
	private static Set<Integer> toTest;
	private static Scanner in;
	
	public static void main(String[] args) {
		in = new Scanner(System.in);
		toTest = getInput();
		Map<Integer, TestResult> map = new LinkedHashMap<>();
		for(int i : toTest)
			map.put(i, test(i));
		for(Map.Entry<Integer, TestResult> e : map.entrySet())
			System.out.printf("%2d %s %s%n", e.getKey(), e.getValue().isSuccess() ? "-" : ">>>", e.getValue());
	}
	
	private static Set<Integer> getInput() {
		System.out.println("Which example(s) you would like to test? Press Enter to test all.");
		System.out.println("To test specific cases, enter comma-separated numbers and/or a range, e.g. 1,3-6,8");
		while(true) {
			String line = in.nextLine().trim().toLowerCase();
			input:
			if(line.trim().isEmpty())
				return IntStream.rangeClosed(1, TEST_COUNT).boxed()
						.collect(Collectors.toCollection(LinkedHashSet::new));
			else {
				try {
					Set<Integer> set = parseTestNumbers(tokenize(line));
					if(set.isEmpty())
						break input;
					return set;
				}
				catch(Exception e) {}
			}
			System.out.println("Please try again. Make sure all numbers are in the range 1-20 (inclusive).");
		}
	}
	
	/** Assumes the given tokens are valid. */
	private static Set<Integer> parseTestNumbers(List<String> tokens) {
		Set<Integer> set = new HashSet<>();
		for(int i = 0; i < tokens.size(); i += 2) {
			//Assumes the token at index i is a number.
			int start = Integer.parseInt(tokens.get(i));
			if(start < 1 || start > 20)
				throw new IllegalArgumentException();
			if(i < tokens.size() - 1 && tokens.get(i + 1).equals("-")) {
				int end = Integer.parseInt(tokens.get(i + 2));
				if(end < 1 || end > 20)
					throw new IllegalArgumentException();
				for(int ex = start; ex <= end; ex++)
					set.add(ex);
				i += 2;
			}
			else { //next token is a comma
				set.add(start);
			}
		}
		return set;
	}
	
	//Static helper methods

	/** unmodifiable. */
	private static Set<Person> generatePeople(int n) {
		if(n > .1 * FIRST_NAMES.size() * LAST_NAMES.size() * CITIES.size())
			throw new IllegalArgumentException("Too many people");
		Set<Person> people = new HashSet<>();
		while(people.size() < n)
			people.add(generatePerson());
		return Collections.unmodifiableSet(people);
	}
	
	private static Person generatePerson() {
		return new Person(random(FIRST_NAMES), random(LAST_NAMES), random(CITIES));
	}
	
	private static <T> T random(List<T> list) {
		return list.get(RANDOM.nextInt(list.size()));
	}
	
	private static int intInclusive(int lowInclusive, int highInclusive) {
		return lowInclusive + RANDOM.nextInt(highInclusive - lowInclusive + 1);
	}
	
	/** a randomly generated String of the given length containing only visible ASCII characters.*/
	private static String visibleASCIIString(int length) {
		char[] chars = new char[length];
		for(int i = 0; i < chars.length; i++)
			chars[i] = (char) intInclusive(33, 126);
		return String.valueOf(chars);
	}
	
	/** unmodifiable. Uses {@link #visibleASCIIString(int)} to generate strings. Length range is inclusive. */
	private static List<String> randomStringList(int size, int minLength, int maxLength) {
		List<String> strs = new ArrayList<>(size);
		for(int i = 0; i < size; i++)
			strs.add(visibleASCIIString(intInclusive(minLength, maxLength)));
		return strs;
	}
	
	
	/** Returns an unmodifiable list containing the same elements but without the given items. */
	@SafeVarargs
	private static <T> List<T> without(List<T> list, T... items) {
		ArrayList<T> copy = new ArrayList<>(list);
		for(T item : items)
			copy.remove(item);
		return Collections.unmodifiableList(copy);
	}
	
	private static List<String> tokenize(String line) {
		return listOf(TOKENIZER.split(line.trim().replace(" ", "")));
	}
	
	@SafeVarargs
	private static <T> List<T> listOf(T... items) {
		return Collections.unmodifiableList(Arrays.asList(items));
	}
	
	@SafeVarargs
	private static <T> Set<T> setOf(T... items) {
		HashSet<T> hs = new HashSet<>(Arrays.asList(items));
		if(hs.size() != items.length)
			throw new IllegalArgumentException("Duplicates");
		return Collections.unmodifiableSet(hs);
	}
	
	/** @throws IllegalArgumentException if {@code (items.length % 2 != 0)}. 
	 * @throws ClassCastException if the items at even indices cannot all be cast to K or the items at odd indices
	 * cannot all be cast to V. */
	@SuppressWarnings("unchecked")
	private static <K, V> Map<K, V> mapOf(Object... items) {
		if(items.length % 2 != 0)
			throw new IllegalArgumentException("Not the same number of keys as values");
		Map<K, V> map = new HashMap<>();
		for(int i = 0; i < items.length; i += 2)
			map.put((K) items[i], (V) items[i + 1]);
		return map;
	}
	
	private static final int TEST_COUNT = 12;
	
	//Tester methods
	private static TestResult test(int ex) {
		try {
			switch(ex) {
				case 1: return test1();
				case 2: return test2();
				case 3: return test3();
				case 4: return test4();
				case 5: return test5();
				case 6: return test6();
				case 7: return test7();
				case 8: return test8();
				case 9: return test9();
				case 10: return test10();
				case 11: return test11();
				case 12: return test12();
			}
		}
		catch(Exception e) {
			return TestResult.exception(e);
		}
		if(ex >= 1 && ex <= TEST_COUNT)
			throw new UnsupportedOperationException(String.format("Unfinished test: %d%n", ex));
		throw new IllegalArgumentException(String.format("Not a valid example number: %d", ex));
	}
	
	private static TestResult test1() {
		return TestResult.build(
			Examples3.product(listOf(1d)) == 1,
			Examples3.product(listOf(1d, 2d)) == 2,
			Examples3.product(listOf(1d, 2d, 3d)) == 6,
			Examples3.product(listOf(1d, 2d, 3d, 4d)) == 24,
			Examples3.product(listOf(1d, 2d, 3d, 4d, 5d)) == 120,
			Examples3.product(listOf(-1.5, 9.75, 8.125)) == -118.828125
		);
	}
	
	private static TestResult test2() {
		for(int tc = 0; tc < 10; tc++) {
			int n = RANDOM.nextInt(99) + 1;
			Person[] arr = generatePeople(n).toArray(new Person[0]);
			Person l = arr[0];
			for(Person p : arr)
				if(p.getLastName().length() > l.getLastName().length())
					l = p;
			if(!l.equals(Examples3.longestLastName(arr)))
				return TestResult.FAILURE;
		}
		return TestResult.SUCCESS;
	}
	
	private static TestResult test3() {
		return TestResult.build(
			Examples3.smallestPopulation(Collections.emptyList()) == null,
			Examples3.smallestPopulation(STATES).equals(pennsylvania),
			Examples3.smallestPopulation(without(STATES, pennsylvania)).equals(newYork),
			Examples3.smallestPopulation(without(STATES, pennsylvania, newYork)).equals(florida),
			Examples3.smallestPopulation(without(STATES, pennsylvania, newYork, florida)).equals(texas),
			Examples3.smallestPopulation(without(STATES, pennsylvania, newYork, florida, texas)).equals(california)
		);
	}
	
	private static TestResult test4() {
		return TestResult.build(
			Examples3.formatted(listOf()).equals("{}"),
			Examples3.formatted(listOf(1)).equals("{1}"),
			Examples3.formatted(listOf(1, 2)).equals("{1 2}"),
			Examples3.formatted(listOf(1, 2, -9623)).equals("{1 2 -9623}"),
			Examples3.formatted(listOf("apple", "", "____", " ")).equals("{apple  ____  }")
		);
	}
	
	private static TestResult test5() {
		return TestResult.build(
			Examples3.longestCity1(pennsylvania).equals(philadelphia),
			Examples3.longestCity1(newYork).equals(nyc),
			Examples3.longestCity1(florida).equals(stPetersburg),
			Examples3.longestCity1(texas).equals(corpusChristi),
			Examples3.longestCity1(california).equals(sanFrancisco)
		);
	}
	
	private static TestResult test6() {
		City result1 = Examples3.longestCity2(STATES);
		City result2 = Examples3.longestCity2(without(STATES, texas, florida));
		return TestResult.build(
			result1.equals(corpusChristi) || result1.equals(stPetersburg),
			Examples3.longestCity2(without(STATES, texas)).equals(stPetersburg),
			Examples3.longestCity2(without(STATES, florida)).equals(corpusChristi),
			result2.equals(nyc) || result2.equals(sanFrancisco),
			Examples3.longestCity2(without(STATES, texas, florida, newYork)).equals(sanFrancisco),
			Examples3.longestCity2(without(STATES, texas, florida, california)).equals(nyc),
			Examples3.longestCity2(without(STATES, texas, florida, newYork, california)).equals(philadelphia)
		);
	}

	private static TestResult test7() {
		if(!Examples3.lengthMap(setOf()).equals(mapOf()) || !Examples3.lengthMap(setOf("")).equals(mapOf("", 0)))
			return TestResult.FAILURE;
		for(int tc = 0; tc < 10; tc++) {
			Set<String> set = new HashSet<>();
			Map<String, Integer> map = new HashMap<>();
			int size = intInclusive(5, 100);
			for(int i = 0; i < size; i++) {
				String s = visibleASCIIString(intInclusive(0, 100));
				set.add(s);
				map.put(s, s.length());
			}
			if(!Examples3.lengthMap(set).equals(map))
				return TestResult.FAILURE;
		}
		return TestResult.SUCCESS;
	}
	
	private static TestResult test8() {
		if(!Examples3.wordCount(listOf()).equals(mapOf()) ||!Examples3.wordCount(listOf("")).equals(mapOf("", 1)))
			return TestResult.FAILURE;
		List<String> dict = IntStream.range(0, 99).mapToObj(i -> visibleASCIIString(intInclusive(1, 100)))
				.collect(Collectors.toCollection(ArrayList::new));
		dict.add("");
		for(int tc = 0; tc < 10; tc++) {
			List<String> list = Collections.unmodifiableList(IntStream.range(0, intInclusive(1000, 2000)).
					mapToObj(i -> random(dict)).collect(Collectors.toList()));
			HashMap<String, Integer> wc = new HashMap<>();
			for(String item : list)
				wc.merge(item, 1, Integer::sum);
			if(!Examples3.wordCount(list).equals(wc))
				return TestResult.FAILURE;
		}
		return TestResult.SUCCESS;
	}
	
	private static TestResult test9() {
		int[] sizes = IntStream.concat(IntStream.of(0, 1, 2, 3, 4),
				IntStream.range(0, 15).map(i -> intInclusive(100, 500))).toArray();
		for(int n : sizes) {
			List<Person> list = Collections.unmodifiableList(new ArrayList<>(generatePeople(n)));
			if(!Examples3.getNames(list).equals(solve9(list)))
				return TestResult.FAILURE;	
		}
		return TestResult.SUCCESS;
	}
	
	private static Map<City, HashSet<String>> solve9(List<Person> list) {
		Map<City, HashSet<String>> map = new HashMap<>();
		for(Person p : list)
			if(!map.containsKey(p.getCity()))
				map.put(p.getCity(), new HashSet<>());
		for(Person p : list)
			map.get(p.getCity()).add(p.getFirstName());
		return map;
	}
	
	private static TestResult test10() {
		if(	!Examples3.subSplit(listOf(), "hi").equals(mapOf(true, 0, false, 0)) ||
			!Examples3.subSplit(listOf("hill"), "hi").equals(mapOf(true, 1, false, 0)) ||
			!Examples3.subSplit(listOf("hell"), "hi").equals(mapOf(true, 0, false, 1)))
			return TestResult.FAILURE;
		for(int tc = 0; tc < 20; tc++) {
			List<String> strs = randomStringList(intInclusive(500, 1000), 10, 100);
			String sub = visibleASCIIString(1);
			if(!Examples3.subSplit(strs, sub).equals(solve10(strs, sub)))
				return TestResult.FAILURE;
		}
		return TestResult.SUCCESS;
	}
	
	private static Map<Boolean, Integer> solve10(List<String> list, String sub) {
		int yes = 0, no = 0;
		for(String s : list)
			if(s.contains(sub))
				yes++;
			else
				no++;
		return mapOf(true, yes, false, no);
	}
	
	private static TestResult test11() {
		return TestResult.build(
			Examples3.cityPops(STATES).equals(solve11(STATES)),
			Examples3.cityPops(without(STATES, california)).equals(solve11(without(STATES, california))),
			Examples3.cityPops(without(STATES, texas)).equals(solve11(without(STATES, texas))),
			Examples3.cityPops(without(STATES, florida)).equals(solve11(without(STATES, florida))),
			Examples3.cityPops(without(STATES, newYork)).equals(solve11(without(STATES, newYork))),
			Examples3.cityPops(without(STATES, pennsylvania)).equals(solve11(without(STATES, pennsylvania)))
		);
	}
	
	private static Map<String, List<Integer>> solve11(List<State> list) {
		Map<String, List<Integer>> map = new HashMap<>();
		for(State s : list)
			map.put(s.getName(), popsDescending(s.getCities()));
		return map;
	}
	
	private static List<Integer> popsDescending(Set<City> cities) {
		List<Integer> list = new ArrayList<>(cities.size());
		for(City c : cities)
			list.add(c.getPopulation());
		Collections.sort(list, Comparator.reverseOrder());
		return list;
	}
	
	private static TestResult test12() {
		return TestResult.build(
			Examples3.averageCityPop(STATES).equals(solve12(STATES)),
			Examples3.averageCityPop(without(STATES, california)).equals(solve12(without(STATES, california))),
			Examples3.averageCityPop(without(STATES, texas)).equals(solve12(without(STATES, texas))),
			Examples3.averageCityPop(without(STATES, florida)).equals(solve12(without(STATES, florida))),
			Examples3.averageCityPop(without(STATES, newYork)).equals(solve12(without(STATES, newYork))),
			Examples3.averageCityPop(without(STATES, pennsylvania)).equals(solve12(without(STATES, pennsylvania)))
		);
	}
	
	private static Map<State, Integer> solve12(List<State> list) {
		Map<State, Integer> map = new HashMap<>();
		for(State s : list)
			map.put(s, sumOfTop5(s));
		return map;
	}
	
	private static int sumOfTop5(State s) {
		List<City> cities = new ArrayList<>(s.getCities());
		Collections.sort(cities, Comparator.comparing(City::getPopulation).reversed());
		return cities.subList(0, 5).stream().mapToInt(City::getPopulation).sum();
	}
	
}
