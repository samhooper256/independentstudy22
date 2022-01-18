package day5;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.*;

public class Examples {
	
	/* NOTES:
	 * - All examples should be done in one statement (that is, one semicolon).
	 * - No arguments passed to your methods will be null.
	 * - For all methods, if you are given a collection or array, DO NOT MODIFY the collection or array.
	 * - You can assume the given collection/array does not contain any null elements unless explicitly stated
	 * otherwise.
	 * - Examples with an (H) indicate that there are one or more hints for that example at the bottom of this file.
	 */
	
	/* Example 1: Given a Collection<T>, return a Stream<T> containing the same elements. */
	public static <T> Stream<T> streamFrom(Collection<T> coll) {
		return null;
	}
	
	/* Example 2: Given a T[], return a Stream<T> containing the same elements. */
	public static <T> Stream<T> streamFrom(T[] arr) {
		return null;
	}
	
	/* Example 3: Given a T[], return a Stream<T> containing only its elements from index startInclusive to the end of
	 * the array. Assume the index is valid (specifically, that 0 <= startInclusive <= arr.length). */
	public static <T> Stream<T> streamFrom(T[] arr, int startInclusive) {
		return null;
	}
	
	/* Example 4: Given an int[], return an IntStream containing only its elements from index startInclusive to
	 * index endExclusive.
	 * Assume the indices are valid (specifically, that 0 <= startInclusive <= endExclusive <= arr.length). */
	public static IntStream streamFrom(int[] arr, int startInclusive, int endExclusive) {
		return null;
	}
	
	/* Example 5 (H): Given a List<T>, return a Stream<T> containing only its elements from index startInclusive to
	 * index endExclusive.
	 * Assume the indices are valid (specifically, that 0 <= startInclusive <= endExclusive <= arr.length).*/
	public static <T> Stream<T> streamFrom(List<T> list, int startInclusive, int endExclusive) {
		return null;
	}
	
	/* Example 6: Given a List<?>, print out all of its elements, each on its own line, in the order they appear in the
	 * list. */
	public static void printAll(List<?> list) {
		return;
	}
	
	/* Example 7: Given a List<Integer>, print out each integer times two, each on its own line, in the order they
	 * appear in the list. For example, if given the list [1, 2, 3], output the following:
	 * 2
	 * 4
	 * 6
	 * */
	public static void printDoubled(List<Integer> list) {
		return;
	}
	
	/* Example 8: Given a List<Integer>, return an int[] containing the same numbers in the same order. */
	public static int[] toPrimitives(List<Integer> list) {
		return null;
	}
	
	/* Example 9: Given a List<String>, return a String[] containing the same elements as the list but with each string
	 * "doubled" (appended to itself). For example, if given the list [a, b, c], return the array [aa, bb, cc]. */
	public static String[] doubleStrings(List<String> list) {
		return null;
	}
	
	/* Example 10: Given a List<String>, return an int[] containing the lengths of the strings in the order they
	 * appear. For example, if given the list [rug, heart, ok], return the array [3, 5, 2]. */
	public static int[] getLengths(List<String> list) {
		return null;
	}
	
	/* Example 11 (H): Given an int[], return a double[] containing the same numbers, but halved. For example, if given
	 * [1, 2, 3], return [0.5, 1.0, 1.5]. */
	public static double[] half(int[] nums) {
		return null;
	}
	
	/* Example 12: Given a List<String>, return a String[] containing only the strings of even length from the list in
	 * the order they appear. */
	public static String[] evensOnly(List<String> list) {
		return null;
	}
	
	/* Example 13: Given a List<String>, return an int[] containing only the lengths of the even-lengthed strings from
	 * the list in the order they appear. For example, if given [okay, not, bamboozled], return [4, 10]. */
	public static int[] evenLengths(List<String> list) {
		return null;
	}
	
	/* Example 14: Given a String[], return a new String[] containing only the unique elements. The order of the strings
	 * in the returned array does not matter. */
	public static String[] getUniques(String[] strs) {
		return null;
	}
	
	/* Example 15 (H): Given a Set<Double>, return a double[] containing only the square roots of the non-negative
	 * numbers in the set. The order of the doubles in the returned array does not matter. */
	public static double[] squareRoots(Set<Double> set) {
		return null;
	}
	
	/* Example 16: Given a List<String>, return a String[] containing the same elements but in sorted order and omitting
	 * any nulls. The given list may contain nulls. For example, if given [hi, (null), act, bear, (null)],
	 * return [act, bear, hi]. */
	public static String[] sortedWithoutNulls(List<String> list) {
		return null;
	}
	
	/* Example 17: Given a String[], return a String[] containing the same strings but sorted by length and with all the
	 * strings with less than 3 characters removed. Assume that the given strings have unique lengths.
	 * For example, if given [s, antithetical, tear, pit, or, cackle], return [pit, tear, cackle, antithetical]. */
	public static String[] sortedByLength(String[] strs) {
		return null;
	}
	
	/* Example 18: Given a List<Integer>, return an int[] containing the distinct even elements sorted in descending
	 * order. All numbers will be non-negative. For example, if given [3, 4, 8, 4, 2, 1, 2, 2], return [8, 4, 2]. */
	public static int[] evensDescending(List<Integer> list) {
		return null;
	}
	
	/* Example 19: Given a List<Integer>, return an int[] containing the elements in the list that are between min
	 * (inclusive) and max (inclusive) AND satisfy the given predicate. The numbers in the returned array should be
	 * in the same order as in the list. Assume min <= max. */
	public static int[] inRangeSatisfying(List<Integer> list, int min, int max, Predicate<? super Integer> condition) {
		return null;
	}
	
	/* Example 20 (H): Given a List<String>, return the String[] that would be created if you kept only the strings that
	 * were null or had less than 6 characters and then sorted the strings in reverse order by length with any null
	 * strings appearing at the front of the returned array. Strings with the same length should be using the natural
	 * order of the String class (that is, lexicographically). The given list may contain nulls. For example, if given
	 * [hi, (null), longstring, same, sam3, cooked, paint, taint, party, crashing, parrot, (null)], return
	 * [(null), (null), paint, party, taint, sam3, same, hi]. */
	public static String[] finalBoss(List<String> strs) {
		return null;
	}
	
	
	
	
	
	//HINTS:
	
	//Example 5: use the subList(int, int) instance method from the List interface.
	
	//Example 11: 1. Note that the primitive streams (e.g. IntStream) have methods to map to other kinds of primitive
	//streams (e.g. mapToDouble). 2. Don't do int division.
	
	//Example 15: Note that "non-negative numbers" means positive numbers AND zero.
	
	//Example 20: Look at the Comparator API. Note that thenComparing is overloaded, and remember the
	//Comparator.naturalOrder() method. Also, see if any of the static methods have to do with nulls...
	
}