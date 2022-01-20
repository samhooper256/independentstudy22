package day6;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.*;

/** @author Sam Hooper */
public class Tester2 {
	
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
		
		public boolean isFailure() {
			return !isSuccess();
		}
		
		@Override
		public String toString() {
			return isSuccess() ? "success" : parenthesizedMessage == null ? "FAILURE" :
				String.format("FAILURE (%s)", parenthesizedMessage);
		}
		
	}
	
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

	private static List<String> tokenize(String line) {
		return listOf(TOKENIZER.split(line.trim().replace(" ", "")));
	}
	
	private static <T> boolean matches(Stream<T> stream, List<T> items) {
		return Arrays.equals(stream.toArray(), items.toArray());
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
	
	private static final int TEST_COUNT = 16;
	
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
				case 13: return test13();
				case 14: return test14();
				case 15: return test15();
				case 16: return test16();
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
		ArrayList<String> list = Examples2.toList(new String[] {"hi", "bye", "there", "is", "pie"});
		TestResult result = TestResult.build(
			Examples2.toList(new String[] {}).equals(new ArrayList<String>()),
			Examples2.toList(new String[] {"bone"}).equals(new ArrayList<String>(Arrays.asList("bone"))),
			list.equals(new ArrayList<String>(Arrays.asList("hi", "bye", "there", "is", "pie")))
		);
		if(result.isFailure())
			return result;
		//testing mutability:
		list.remove(2);
		list.set(3, "oops");
		return TestResult.build(
			list.equals(listOf("hi", "bye", "is", "oops")),
			Examples2.toList(new Object[] {1.2, "hi", 37}).equals(new ArrayList<Object>(Arrays.asList(1.2, "hi", 37))),
			Examples2.toList(new Integer[] {3, 4}).equals(new ArrayList<Integer>(Arrays.asList(3, 4))),
			Examples2.toList(new Double[] {3.3, 4.4}).equals(new ArrayList<Double>(Arrays.asList(3.3, 4.4)))
		);
	}
	
	private static TestResult test2() {
		return TestResult.build(
			Examples2.endsY(listOf()),
			!Examples2.endsY(listOf("a")),
			Examples2.endsY(listOf("y")),
			Examples2.endsY(listOf("ay")),
			Examples2.endsY(listOf("ay", "nay", "bay", "tray")),
			!Examples2.endsY(listOf("ay", "nay", "bay", "traf")),
			!Examples2.endsY(listOf("not", "bot", "tot", "ummm"))
		);
	}
	
	private static TestResult test3() {
		return TestResult.build(
			!Examples2.anyNegatives(listOf()),
			!Examples2.anyNegatives(listOf(1.0)),
			Examples2.anyNegatives(listOf(-3.0)),
			Examples2.anyNegatives(listOf(3.4, -0.5, Math.PI)),
			Examples2.anyNegatives(listOf(-Math.PI, -Math.E, -Math.sqrt(3), -1.0, -0.1)),
			!Examples2.anyNegatives(listOf(1.1, 2.3, 9.8, 14342.343, 0.0))
		);
	}
	
	private static TestResult test4() {
		return TestResult.build(
			Examples2.noThrees(new int[] {}),
			Examples2.noThrees(new int[] {7}),
			!Examples2.noThrees(new int[] {3}),
			Examples2.noThrees(new int[] {934, 4, -9, 88}),
			!Examples2.noThrees(new int[] {9343, 3, 405025, 554, -34, -4, 33, 623}),
			!Examples2.noThrees(new int[] {3, 3, 3, 3, 3, 3}),
			!Examples2.noThrees(new int[] {6, 2, 7, 24, 6, 3, 66, 243, 5, 5, 2, 3}),
			Examples2.noThrees(new int[] {6, 2, 7, 24, 6, 31, 66, 243, 5, 5, 2, 31})
		);
	}
	
	private static TestResult test5() {
		boolean result = true;
		List<List<String>> list = new ArrayList<>();
		result &= Examples2.flatten(list).equals(new ArrayList<>());
		list.add(listOf("a"));
		result &= Examples2.flatten(list).equals(listOf("a"));
		list.add(listOf("b", "c"));
		result &= Examples2.flatten(list).equals(listOf("a", "b", "c"));
		list.add(listOf("d", "e", "f"));
		result &= Examples2.flatten(list).equals(listOf("a", "b", "c", "d", "e", "f"));
		List<List<Integer>> integers = listOf(listOf(1, 2), listOf(3), listOf(), listOf(4, 5, 6, 7));
		result &= Examples2.flatten(integers).equals(listOf(1, 2, 3, 4, 5, 6, 7));
		return TestResult.build(result);
	}
	
	private static TestResult test6() {
		return TestResult.build(
			Examples2.emptyCount(listOf()) == 0,
			Examples2.emptyCount(listOf(listOf())) == 1,
			Examples2.emptyCount(listOf(listOf("a"))) == 0,
			Examples2.emptyCount(listOf(listOf("a"), listOf())) == 1,
			Examples2.emptyCount(listOf(listOf("a"), listOf("a"))) == 0,
			Examples2.emptyCount(listOf(listOf("a"), listOf(), setOf())) == 2,
			Examples2.emptyCount(listOf(listOf("a"), listOf("a", "b", "c", "d"), setOf())) == 1,
			Examples2.emptyCount(listOf(listOf("a"), listOf("a", "b", "c", "d"), setOf("_"))) == 0
		);
	}

	private static TestResult test7() {
		return TestResult.build(
			Examples2.firstPositive(new int[] {}) == -1,
			Examples2.firstPositive(new int[] {3}) == 3,
			Examples2.firstPositive(new int[] {-3}) == -1,
			Examples2.firstPositive(new int[] {-3, -4, -1, 7, 8, 9}) == 7,
			Examples2.firstPositive(new int[] {-3, -9, 0, 0}) == -1,
			Examples2.firstPositive(new int[] {0}) == -1,
			Examples2.firstPositive(new int[] {8, -1, -1, -1, 9}) == 8,
			Examples2.firstPositive(new int[] {-5, 4, 8, -9, 9}) == 4
		);
	}
	
	private static TestResult test8() {
		return TestResult.build(
			Examples2.even4(new int[] {}),
			Examples2.even4(new int[] {3, 7, 9}),
			!Examples2.even4(new int[] {2, 7, 9}),
			!Examples2.even4(new int[] {2, 4, 6}),
			Examples2.even4(new int[] {12, 4, 64}),
			Examples2.even4(new int[] {3, 5, 4, 4, 8}),
			!Examples2.even4(new int[] {32, 5, 4, 4, 8, 10}),
			!Examples2.even4(new int[] {3, 5, 4, 4, 8, 10})
		);
	}
	
	private static TestResult test9() {
		return TestResult.build(
			Examples2.sameTwos(new int[] {}, new int[] {}),
			Examples2.sameTwos(new int[] {1}, new int[] {}),
			Examples2.sameTwos(new int[] {1}, new int[] {1}),
			Examples2.sameTwos(new int[] {}, new int[] {1}),
			Examples2.sameTwos(new int[] {1, 213}, new int[] {98, -1000000, 33}),
			!Examples2.sameTwos(new int[] {2}, new int[] {}),
			!Examples2.sameTwos(new int[] {}, new int[] {2}),
			Examples2.sameTwos(new int[] {2}, new int[] {2}),
			Examples2.sameTwos(new int[] {3, 2, 45, 98, 9, 9, 2}, new int[] {2, 34, 92, -33, -1000, 3, 3, 2}),
			!Examples2.sameTwos(new int[] {3, 2, 45, 98, 9, 9, 2}, new int[] {2, 34, 92, -33, -1000, 3, 3, 4})
		);
	}
	
	private static TestResult test10() {
		return TestResult.build(
			Examples2.descending(setOf()).equals(listOf()),
			Examples2.descending(setOf(3)).equals(listOf()),
			Examples2.descending(setOf(5, 2)).equals(listOf()),
			Examples2.descending(setOf(-9, 0, 5)).equals(listOf()),
			Examples2.descending(setOf(-9, 0, 5, 8)).equals(listOf(-9)),
			Examples2.descending(setOf(-9, 0, 5, 8, 17)).equals(listOf(0, -9)),
			Examples2.descending(setOf(-9, 0, 5, 8, 17, -100)).equals(listOf(0, -9, -100)),
			Examples2.descending(setOf(-9, 0, 5, 8, 88, 99, 101)).equals(listOf(8, 5, 0, -9)),
			Examples2.descending(setOf(3, 9, 2, 4, 10, 11, 8, 12)).equals(listOf(9, 8, 4, 3, 2))
		);
	}
	
	private static TestResult test11() {
		Set<Person> set1 = setOf(new Person("Sam", "Hooper"), new Person("Ayuj", "Verma"),
				new Person("Steve", "Stevenson"), new Person("The", "Nanda"), new Person("Mister", "E"),
				new Person("Curious", "George"), new Person("George", "Washington"), new Person("Okie", "Dokie"),
				new Person("That", "Bot"));
		List<Person> result1 = new ArrayList<>(set1);
		Set<Person> set2 = setOf(new Person("Oh", "No"), new Person("I", "Can't"),
				new Person("Think", "Of"), new Person("Any", "More"), new Person("Names", "Last"),
				new Person("Just", "Kidding"), new Person("I", "Can"), new Person("Because", "These"),
				new Person("Are", "Not"), new Person("Really", "Names"));
		List<Person> result2 = new ArrayList<>(set2);
		Collections.sort(result1, Comparator.comparing(Person::getLastName));
		Collections.sort(result2, Comparator.comparing(Person::getLastName));
		return TestResult.build(
			Examples2.sortedByLastName(set1).equals(result1),
			Examples2.sortedByLastName(set2).equals(result2)
		);
	}
	
	private static TestResult test12() {
		return TestResult.build(
			Objects.equals(Examples2.why(listOf()), null),
			Objects.equals(Examples2.why(listOf("hello")), null),
			Objects.equals(Examples2.why(listOf("x-ray")), null),
			Objects.equals(Examples2.why(listOf("xylophone")), null),
			Objects.equals(Examples2.why(listOf("xoxo")), "xoxo"),
			Objects.equals(Examples2.why(listOf("xoxo", "nah")), null),
			Objects.equals(Examples2.why(listOf("yep", "xoxo")), "xoxo"),
			Objects.equals(Examples2.why(listOf("yep", "exp", "roar")), "exp"),
			Objects.equals(Examples2.why(listOf("yep", "roar", "exp")), "exp"),
			Objects.equals(Examples2.why(listOf("yep", "roar", "lex", "flex")), "lex"),
			Objects.equals(Examples2.why(listOf("yep", "roar", "t-rex", "lex", "flex")), "t-rex"),
			Objects.equals(Examples2.why(listOf("chex", "roar", "t-rex", "lex", "flex")), "t-rex"),
			Objects.equals(Examples2.why(listOf("chex", "roar", "t-rexy", "lex", "flex")), "lex")
		);
	}
	
	private static TestResult test13() {
		List<String> list = listOf("I", "you", "me", "new", "red", "blue", "can", "you", "chew");
		return TestResult.build(
			matches(Examples2.substream(listOf(1, 2, 3).stream(), 1, 3), listOf(2, 3)),
			matches(Examples2.substream(listOf().stream(), 0, 0), listOf()),
			matches(Examples2.substream(listOf(3).stream(), 1, 1), listOf()),
			matches(Examples2.substream(list.stream(), 0, 0), list.subList(0, 0)),
			matches(Examples2.substream(list.stream(), 0, 1), list.subList(0, 1)),
			matches(Examples2.substream(list.stream(), 0, 2), list.subList(0, 2)),
			matches(Examples2.substream(list.stream(), 0, 7), list.subList(0, 7)),
			matches(Examples2.substream(list.stream(), 5, 7), list.subList(5, 7)),
			matches(Examples2.substream(list.stream(), 8, 9), list.subList(8, 9)),
			matches(Examples2.substream(list.stream(), 3, 6), list.subList(3, 6)),
			matches(Examples2.substream(list.stream(), 4, 8), list.subList(4, 8)),
			matches(Examples2.substream(list.stream(), 9, 9), list.subList(9, 9))
		);
	}
	
	private static TestResult test14() {
		List<Map<String, Integer>> list = new ArrayList<>();
		boolean result = true;
		result &= Examples2.allKeys(Collections.unmodifiableList(list)).equals(setOf());
		Map<String, Integer> map1 = new HashMap<>(); //unfortunately, Map.of does not exist in java 8.
		map1.put("a", 7);
		list.add(map1);
		result &= Examples2.allKeys(Collections.unmodifiableList(list)).equals(setOf("a"));
		map1.put("b", 9);
		result &= Examples2.allKeys(Collections.unmodifiableList(list)).equals(setOf("a", "b"));
		Map<String, Integer> map2 = new HashMap<>();
		list.add(map2);
		result &= Examples2.allKeys(Collections.unmodifiableList(list)).equals(setOf("a", "b"));
		map2.put("a", 19);
		result &= Examples2.allKeys(Collections.unmodifiableList(list)).equals(setOf("a", "b"));
		map2.put("c", 8);
		result &= Examples2.allKeys(Collections.unmodifiableList(list)).equals(setOf("a", "b", "c"));
		map2.put("d", 8);
		Map<String, Integer> map3 = new HashMap<>();
		map3.put("c", 109);
		map3.put("z", 908);
		list.add(map3);
		result &= Examples2.allKeys(Collections.unmodifiableList(list)).equals(setOf("a", "b", "c", "d", "z"));
		return TestResult.build(result);
	}
	
	private static TestResult test15() {
		Person sam = new Person("Sam", "Hooper");
		Person dhruv = new Person("Dhruv", "Nanda");
		Person maddie = new Person("Maddie", "Bramlett");
		Person nawaf = new Person("Nawaf", "Reaz");
		Person adi = new Person("Adi", "Behre");
		Person vaibhav = new Person("Vaibhav", "Shrivathsa");
		return TestResult.build(
			Examples2.secondHalfNames(setOf()).equals(new LinkedList<>()),
			Examples2.secondHalfNames(setOf(sam)).equals(listOf("Sam")),
			Examples2.secondHalfNames(setOf(dhruv)).equals(listOf()),
			Examples2.secondHalfNames(setOf(sam, dhruv)).equals(listOf("Sam")),
			Examples2.secondHalfNames(setOf(sam, dhruv, maddie)).equals(listOf("Sam")),
			Examples2.secondHalfNames(setOf(sam, dhruv, maddie, nawaf)).equals(listOf("Nawaf", "Sam")),
			Examples2.secondHalfNames(setOf(sam, dhruv, maddie, nawaf, adi, vaibhav))
				.equals(listOf("Nawaf", "Sam", "Vaibhav"))
		);
	}
	
	private static TestResult test16() {
		return TestResult.build(
			Examples2.longestStrings(listOf("a", "biscuit", "philanthropy", "biscuit", "congenial", "metaphysics",
				"ontology", "philanthropy", "nutate", "maximize")).equals(listOf("philanthropy", "metaphysics",
				"congenial", "maximize", "ontology")),
			Examples2.longestStrings(listOf("hmm", "let's", "see", "what", "words", "can", "Sam", "come", "up", "with",
				"today")).equals(listOf("let's", "today", "words", "come", "what")),
			Examples2.longestStrings(listOf("ready", "float", "heart", "crack", "poles", "horse", "cores"))
				.equals(listOf("cores", "crack", "float", "heart", "horse"))
		);
	}
	
}
