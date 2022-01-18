package day5;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.*;

/** @author Sam Hooper */
public class Tester {

	private static class TestOutputStream extends OutputStream {

		private final StringBuilder text;
		
		public TestOutputStream() {
			text = new StringBuilder();
		}
		
		@Override
		public void write(int b) throws IOException {
			text.append((char) b);
		}
		
		public StringBuilder builder() {
			return text;
		}
		
	}
	
	private static class TestPrintStream extends PrintStream {
		
		private final TestOutputStream tos;
		
		public TestPrintStream() {
			this(new TestOutputStream());
		}
		
		private TestPrintStream(TestOutputStream tos) {
			super(tos, true);
			this.tos = tos;
		}
		
		public String text() {
			return tos.builder().toString();
		}
		
		public void clearOutput() {
			tos.builder().delete(0, tos.builder().length());
		}
		
		public String scrape() {
			String text = text();
			clearOutput();
			return text;
		}
		
	}
	
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
	
	private static final Pattern LF = Pattern.compile("\n"), TOKENIZER = Pattern.compile("(?=[-,])|(?<=[-,])");
	
	private static TestPrintStream tps;
	private static PrintStream systemOut;
	private static Set<Integer> toTest;
	private static Scanner in;
	
	public static void main(String[] args) {
		in = new Scanner(System.in);
		toTest = getInput();
		tps = new TestPrintStream();
		systemOut = System.out;
		System.setOut(tps);
		Map<Integer, TestResult> map = new LinkedHashMap<>();
		for(int i : toTest)
			map.put(i, test(i));
		System.setOut(systemOut);
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
	
	@SafeVarargs
	private static <T> boolean matches(Stream<T> stream, T... items) {
		return Arrays.equals(stream.toArray(), items);
	}
	
	private static <T> boolean matches(Stream<T> stream, Collection<T> items) {
		return Arrays.equals(stream.toArray(), items.toArray());
	}
	
	private static <T> boolean matchesIgnoringOrder(Stream<T> stream, Set<T> items) {
		return items.equals(toSet(stream));
	}
	
	private static <T> boolean equalsIgnoringOrder(double[] a, double... b) {
		return Arrays.equals(Arrays.stream(a).sorted().toArray(), Arrays.stream(b).sorted().toArray());
	}
	
	private static <T> boolean matches(IntStream stream, int... items) {
		return Arrays.equals(stream.toArray(), items);
	}
	
	private static boolean outputMatches(String output, List<?> objects) {
		if(objects.isEmpty())
			return output.isEmpty();
		String[] split = LF.split(trimTrailing(output).replace("\r\n", "\n"));
		if(split.length != objects.size())
			return false;
		for(int i = 0; i < split.length; i++)
			if(!split[i].equals(String.valueOf(objects.get(i))))
				return false;
		return true;
	}
	
	private static boolean scrapeAndMatch(Stream<?> stream) {
		return scrapeAndMatch(stream.collect(Collectors.toList()));
	}
	
	private static boolean scrapeAndMatch(List<?> list) {
		return outputMatches(tps.scrape(), list);
	}
	
	private static String trimTrailing(String str) {
		for(int i = str.length() - 1; i >= 0; i--)
			if(str.charAt(i) > ' ')
				return str.substring(0, i + 1);
		return "";
	}
	
	private static <T> HashSet<T> toSet(Stream<T> stream) {
		return stream.collect(Collectors.toCollection(HashSet::new));
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
	
	private static final int TEST_COUNT = 20;
	
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
				case 17: return test17();
				case 18: return test18();
				case 19: return test19();
				case 20: return test20();
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
		for(List<String> l : listOf(listOf("a", "b", "c"), Tester.<String>listOf(), listOf("Xafaef", "aefaefea")))
			if(!matches(Examples.streamFrom(l), l))
				return TestResult.FAILURE;
		for(Set<Integer> s : listOf(setOf(1,65,2,34,-3,32,-4,-5), setOf(1,23,33,34,43,2), setOf(3)))
			if(!matchesIgnoringOrder(Examples.streamFrom(s), s))
				return TestResult.FAILURE;
		return TestResult.SUCCESS;
	}
	
	private static TestResult test2() {
		for(String[] arr : listOf(new String[] {"a", "b", "c"}, new String[] {}, new String[] {"Xafaef", "aefaefea"}))
			if(!matches(Examples.streamFrom(arr), arr))
				return TestResult.FAILURE;
		for(Integer[] arr : listOf(new Integer[] {1, 65, 2, 34, -3, 32, -4, -5}, new Integer[] {1, 23, 33, 34, 43, 2},
				new Integer[] {3}))
			if(!matches(Examples.streamFrom(arr), arr))
				return TestResult.FAILURE;
		return TestResult.SUCCESS;
	}
	
	private static TestResult test3() {
		return TestResult.build(
			matches(Examples.streamFrom(new String[] {}, 0)),
			matches(Examples.streamFrom(new String[] {"a"}, 0), "a"),
			matches(Examples.streamFrom(new String[] {"a"}, 1)),
			matches(Examples.streamFrom(new String[] {"a", "b", "c"}, 0), "a", "b", "c"),
			matches(Examples.streamFrom(new String[] {"a", "b", "c"}, 1), "b", "c"),
			matches(Examples.streamFrom(new String[] {"a", "b", "c"}, 2), "c"),
			matches(Examples.streamFrom(new String[] {"a", "b", "c"}, 3)),
			matches(Examples.streamFrom(new Integer[] {7, 8, 9}, 0), 7, 8, 9),
			matches(Examples.streamFrom(new Integer[] {7, 8, 9}, 1), 8, 9),
			matches(Examples.streamFrom(new Integer[] {7, 8, 9}, 2), 9),
			matches(Examples.streamFrom(new Integer[] {7, 8, 9}, 3))
		);
	}
	
	private static TestResult test4() {
		return TestResult.build(
			matches(Examples.streamFrom(new int[] {}, 0, 0)),
			matches(Examples.streamFrom(new int[] {9}, 0, 0)),
			matches(Examples.streamFrom(new int[] {9}, 0, 1), 9),
			matches(Examples.streamFrom(new int[] {9}, 1, 1)),
			matches(Examples.streamFrom(new int[] {8, 9}, 0, 0)),
			matches(Examples.streamFrom(new int[] {8, 9}, 0, 1), 8),
			matches(Examples.streamFrom(new int[] {8, 9}, 0, 2), 8, 9),
			matches(Examples.streamFrom(new int[] {8, 9}, 1, 1)),
			matches(Examples.streamFrom(new int[] {8, 9}, 1, 2), 9),
			matches(Examples.streamFrom(new int[] {8, 9}, 2, 2)),
			matches(Examples.streamFrom(new int[] {8, 9, 10, 11, 12, 13, 14, 15, 16}, 3, 7), 11, 12, 13, 14)
		);
	}
	
	private static TestResult test5() {
		return TestResult.build(
			matches(Examples.streamFrom(listOf(), 0, 0)),
			matches(Examples.streamFrom(listOf(9), 0, 0)),
			matches(Examples.streamFrom(listOf(9), 0, 1), 9),
			matches(Examples.streamFrom(listOf(9), 1, 1)),
			matches(Examples.streamFrom(listOf(8, 9), 0, 0)),
			matches(Examples.streamFrom(listOf(8, 9), 0, 1), 8),
			matches(Examples.streamFrom(listOf(8, 9), 0, 2), 8, 9),
			matches(Examples.streamFrom(listOf(8, 9), 1, 1)),
			matches(Examples.streamFrom(listOf(8, 9), 1, 2), 9),
			matches(Examples.streamFrom(listOf(8, 9), 2, 2)),
			matches(Examples.streamFrom(listOf(8, 9, 10, 11, 12, 13, 14, 15, 16), 3, 7), 11, 12, 13, 14)
		);
	}
	
	private static TestResult test6() {
		for(List<?> list : listOf(listOf("a", "b", "c"), listOf(1, 2, 3, 4), listOf(), listOf(1.2, 4.56))) {
			Examples.printAll(list);
			if(!scrapeAndMatch(list))
				return TestResult.FAILURE;
		}
		return TestResult.SUCCESS;
	}

	private static TestResult test7() {
		for(List<Integer> list : listOf(listOf(1, 2, 3), Tester.<Integer>listOf(), listOf(99, 123, 43, 21, 3),
				listOf(8))) {
			Examples.printDoubled(list);
			if(!scrapeAndMatch(list.stream().map(x -> x * 2)))
				return TestResult.FAILURE;
		}
		return TestResult.SUCCESS;
	}
	
	private static TestResult test8() {
		return TestResult.build(
			Arrays.equals(Examples.toPrimitives(listOf()), new int[] {}),
			Arrays.equals(Examples.toPrimitives(listOf(23)), new int[] {23}),
			Arrays.equals(Examples.toPrimitives(listOf(6, -9)), new int[] {6, -9}),
			Arrays.equals(Examples.toPrimitives(listOf(900, 82, 9)), new int[] {900, 82, 9}),
			Arrays.equals(Examples.toPrimitives(listOf(234, -3, 394, 9, 0, -10, -100, 3, 3434343, 3)),
					new int[] {234, -3, 394, 9, 0, -10, -100, 3, 3434343, 3})
		);
	}
	
	private static TestResult test9() {
		return TestResult.build(
			Arrays.equals(Examples.doubleStrings(listOf()), new String[] {}),
			Arrays.equals(Examples.doubleStrings(listOf("a")), new String[] {"aa"}),
			Arrays.equals(Examples.doubleStrings(listOf("coaxial")), new String[] {"coaxialcoaxial"}),
			Arrays.equals(Examples.doubleStrings(listOf("x", "y", "z", "ef")), new String[] {"xx", "yy", "zz", "efef"})
		);
	}
	
	private static TestResult test10() {
		return TestResult.build(
			Arrays.equals(Examples.getLengths(listOf()), new int[] {}),
			Arrays.equals(Examples.getLengths(listOf("cot")), new int[] {3}),
			Arrays.equals(Examples.getLengths(listOf("lemon", "")), new int[] {5, 0}),
			Arrays.equals(Examples.getLengths(listOf("rug", "heart", "ok")), new int[] {3, 5, 2}),
			Arrays.equals(Examples.getLengths(listOf("conjugate", "rapscallion", "kludge", "scrabble")),
					new int[] {9, 11, 6, 8}),
			Arrays.equals(Examples.getLengths(listOf("armadillo", "a", "can", "be", "bee", "be", "Kali", "punctual")),
					new int[] {9, 1, 3, 2, 3, 2, 4, 8})
		);
	}
	
	private static TestResult test11() {
		return TestResult.build(
			Arrays.equals(Examples.half(new int[] {}), new double[] {}),
			Arrays.equals(Examples.half(new int[] {7}), new double[] {3.5}),
			Arrays.equals(Examples.half(new int[] {99, 40}), new double[] {49.5, 20.0}),
			Arrays.equals(Examples.half(new int[] {1, 2, 3}), new double[] {0.5, 1.0, 1.5}),
			Arrays.equals(Examples.half(new int[] {6, 1, -3, 94, 0, 14}), new double[] {3.0, 0.5, -1.5, 47.0, 0.0, 7.0})
		);
	}
	
	private static TestResult test12() {
		return TestResult.build(
			Arrays.equals(Examples.evensOnly(listOf()), new String[] {}),
			Arrays.equals(Examples.evensOnly(listOf("")), new String[] {""}),
			Arrays.equals(Examples.evensOnly(listOf("no")), new String[] {"no"}),
			Arrays.equals(Examples.evensOnly(listOf("car", "wreck")), new String[] {}),
			Arrays.equals(Examples.evensOnly(listOf("jump", "bumble", "bungle", "beans", "oops")),
				new String[] {"jump", "bumble", "bungle", "oops"}),
			Arrays.equals(Examples.evensOnly(listOf("aardvark", "aardwolf", "aardwolves", "Aare", "Aargau",
				"aasvogel", "noteven")), new String[] {"aardvark", "aardwolf", "aardwolves", "Aare", "Aargau",
				"aasvogel"})
		);
	}
	
	private static TestResult test13() {
		return TestResult.build(
			Arrays.equals(Examples.evenLengths(listOf()), new int[] {}),
			Arrays.equals(Examples.evenLengths(listOf("")), new int[] {0}),
			Arrays.equals(Examples.evenLengths(listOf("no")), new int[] {2}),
			Arrays.equals(Examples.evenLengths(listOf("car", "wreck")), new int[] {}),
			Arrays.equals(Examples.evenLengths(listOf("okay", "not", "bamboozled")), new int[] {4, 10}),
			Arrays.equals(Examples.evenLengths(listOf("jump", "bumble", "bungle", "beans", "oops")),
				new int[] {4, 6, 6, 4}),
			Arrays.equals(Examples.evenLengths(listOf("aardvark", "aardwolf", "aardwolves", "Aare", "Aargau",
				"aasvogel", "noteven")), new int[] {8, 8, 10, 4, 6, 8})
		);
	}
	
	private static TestResult test14() {
		String[][] arrs = {
			{"a","b", "c", "b", "a"},
			{},
			{"x"},
			{"hog", "feeds", "hog"},
			{"that", "was", "dark"},
			{"testing", "testing", "testing", "1", "2", "3", "does", "your", "method", "work?"},
			{"I", "hope", "that", "i", "I"}
		};
		for(String[] arr : arrs)
			if(!new HashSet<>(Arrays.asList(Examples.getUniques(arr))).equals(new HashSet<>(Arrays.asList(arr))))
				return TestResult.FAILURE;
		return TestResult.SUCCESS;
	}
	
	private static TestResult test15() {
		return TestResult.build(
			equalsIgnoringOrder(Examples.squareRoots(setOf())),
			equalsIgnoringOrder(Examples.squareRoots(setOf(1d)), 1),
			equalsIgnoringOrder(Examples.squareRoots(setOf(1d, -1d)), 1),
			equalsIgnoringOrder(Examples.squareRoots(setOf(1.5, -2.34234, -3.4, -0.1, 7.0)),
				Math.sqrt(1.5), Math.sqrt(7.0)),
			equalsIgnoringOrder(Examples.squareRoots(setOf(4.0, 9.0, 16.0)), 2, 3, 4),
			equalsIgnoringOrder(Examples.squareRoots(setOf(2.0, 1.0, -1.0, -2.0, 3.0, -3.0)),
				1, Math.sqrt(2), Math.sqrt(3)),
			equalsIgnoringOrder(Examples.squareRoots(setOf(3.5, 8.25, -0.375, 9.875)),
				Math.sqrt(3.5), Math.sqrt(8.25), Math.sqrt(9.875))
		);
	}
	
	private static TestResult test16() {
		List<List<String>> lists = listOf(Tester.<String>listOf(), listOf("hi"), listOf((String) null),
				listOf("hi", "null"), listOf("hi", null, "act", "bear", null), listOf(null, null, null, "hack", null,
				"forest", "banana", null, "picnic", "jam", "jacked", "busted"));
		for(List<String> list : lists) {
			String[] out = Examples.sortedWithoutNulls(list);
			List<String> copy = new ArrayList<>(list);
			copy.removeIf(s -> s == null);
			Collections.sort(copy);
			if(!Arrays.equals(out, copy.toArray(new String[copy.size()])))
				return TestResult.FAILURE;
		}
		return TestResult.SUCCESS;
	}
	
	private static TestResult test17() {
		String[][] arrs = {
			{},
			{"apricot"},
			{"a", "b", "c"},
			{"can't", "1", "22", "333", "fungus", ""},
			{"gageteer", "gargoyle", "gainful", "garden", "gourd", "gash", "gym"},
			{"lol", "lemon", "lots", "lumber", "Lagrange", "lengthy"}
		};
		for(String[] arr : arrs) {
			List<String> list = new ArrayList<>(Arrays.asList(arr));
			list.removeIf(s -> s.length() < 3);
			Collections.sort(list, Comparator.comparingInt(String::length));
			if(!Arrays.equals(Examples.sortedByLength(arr), list.toArray(new String[list.size()])))
				return TestResult.FAILURE;
		}
		return TestResult.SUCCESS;
	}
	
	private static TestResult test18() {
		return TestResult.build(
			Arrays.equals(Examples.evensDescending(listOf()), new int[] {}),
			Arrays.equals(Examples.evensDescending(listOf(1)), new int[] {}),
			Arrays.equals(Examples.evensDescending(listOf(2)), new int[] {2}),
			Arrays.equals(Examples.evensDescending(listOf(9, 18)), new int[] {18}),
			Arrays.equals(Examples.evensDescending(listOf(16, 21)), new int[] {16}),
			Arrays.equals(Examples.evensDescending(listOf(3, 4, 8, 4, 2, 1, 2, 2)), new int[] {8, 4, 2}),
			Arrays.equals(Examples.evensDescending(listOf(9, 2, 0, 303, 404, 0, 2, 303, 99, 9, 999, 444, 404)),
					new int[] {444, 404, 2, 0})
		);
	}
	
	private static TestResult test19() {
		return TestResult.build(
			Arrays.equals(Examples.inRangeSatisfying(listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), 3, 7, x -> x % 2 == 0),
					new int[] {4, 6}),
			Arrays.equals(Examples.inRangeSatisfying(listOf(), Integer.MIN_VALUE, Integer.MAX_VALUE, x -> true),
					new int[] {}),
			Arrays.equals(Examples.inRangeSatisfying(listOf(7, 8, 9, 7), 7, 7, x -> true), new int[] {7, 7}),
			Arrays.equals(Examples.inRangeSatisfying(listOf(7, 8, 9, 7), 7, 9, x -> false), new int[] {}),
			Arrays.equals(Examples.inRangeSatisfying(listOf(23, 93, 42, 43, 23, 103, -1), 1, 100, x -> x % 10 == 3),
					new int[] {23, 93, 43, 23})
		);
	}
	
	private static TestResult test20() {
		return TestResult.build(
			Arrays.equals(Examples.finalBoss(listOf()), new String[] {}),
			Arrays.equals(Examples.finalBoss(listOf((String) null)), new String[] {null}),
			Arrays.equals(Examples.finalBoss(listOf("")), new String[] {""}),
			Arrays.equals(Examples.finalBoss(listOf("loooooooooong")), new String[] {}),
			Arrays.equals(Examples.finalBoss(listOf("hi", null, "longstring", "same", "sam3", "cooked", "paint",
			"taint", "party", "crashing", "parrot", null)), new String[] {null, null, "paint", "party", "taint",
			"sam3", "same", "hi"}),
			Arrays.equals(Examples.finalBoss(listOf("careen", null, "plead", "foot", "b", "a", "bee")),
			new String[] {null, "plead", "foot", "bee", "a", "b"}),
			Arrays.equals(Examples.finalBoss(listOf("chops", "chopped", "cot", "heat", "fax", "ban", "at")),
			new String[] {"chops", "heat", "ban", "cot", "fax", "at"})
		);
	}
}
