package day6;

import java.util.*;
import java.util.stream.*;

public class Examples2 {

	/* NOTES:
	 * - All examples should be done in one statement (that is, one semicolon).
	 * - No arguments passed to your methods will be null.
	 * - For all methods, if you are given a collection or array, DO NOT MODIFY the collection or array.
	 * - You can assume the given collection/array does not contain any null elements unless explicitly stated
	 * otherwise.
	 * - Examples with an (H) indicate that there are one or more hints for that example at the bottom of this file.
	 */
	
	/* Example 1: Given a T[], return a mutable ArrayList<T> containing the same elements. */
	public static <T> ArrayList<T> toList(T[] arr) {
		return null;
	}
	
	/* Example 2: Given a List<String>, return true iff every string ends in "y", false otherwise. Return true if the
	 * list is empty. The strings in the given list will all have at least one character.  */
	public static boolean endsY(List<String> list) {
		return false;
	}

	/* Example 3: Given a List<Double>, return true iff there are any negative numbers in the list, false otherwise.
	 * Return false if the list is empty. */
	public static boolean anyNegatives(List<Double> list) {
		return false;
	}
	
	/* Example 4: Given an int[], return true iff the array contains no multiplies of three, false otherwise. */
	public static boolean noThrees(int[] arr) {
		return false;
	}
	
	/* Example 5: Given a List<List<T>>, "flatten" it into a one-dimensional List<T>. For example, if given the list
	 * [[1, 2], [3, 4, 5], [6]], return [1, 2, 3, 4, 5, 6]. */
	public static <T> List<T> flatten(List<List<T>> lists) {
		return null;
	}
	
	/* Example 6: Given a collection of collections, return the number of collections that are empty (Not including the
	 * "outer" collection, of course - return 0 if coll is empty). */
	public static int emptyCount(Collection<? extends Collection<?>> colls) {
		return 0;
	}
	
	/* Example 7: Given an int[], return the first positive (and non-zero) number in the list, or return -1 if
	 * there are no positive numbers. */
	public static int firstPositive(int[] arr) {
		return 0;
	}
	
	/* Example 8: Given an int[], return true iff all even numbers are also multiples of four, false otherwise. Return
	 * true if there are no even numbers in the array. */
	public static boolean even4(int[] arr) {
		return false;
	}
	
	/* Example 9: Given two int[], return true iff they have the same number of 2's, false otherwise. */
	public static boolean sameTwos(int[] arr1, int[] arr2) {
		return false;
	}
	
	/* Example 10: Given a Set<Integer>, return a List<Integer> containing the numbers in descending order, omitting the
	 * three largest numbers. If the set contains three or fewer elements, return an empty list. For example, if given
	 * [3, 9, 2, 4, 10, 11, 8, 12], return [9, 8, 4, 3, 2]. */
	public static List<Integer> descending(Set<Integer> set) {
		return null;
	}
	
	/* Example 11: Given a Set<Person>, return a List<Person> containing the same people, but sorted alphabetically by
	 * last name. No two people will have the same last name. */
	public static List<Person> sortedByLastName(Set<Person> set) {
		return null;
	}
	
	/* Example 12: Given a List<String>, return the first string in the second half of the list that contains an 'x'
	 * character but not a 'y' character. If the list has odd size, consider the "second half" to be the larger half.
	 * Return null if there is no qualifying string in the second half of the list. */
	public static String why(List<String> list) {
		return null;
	}
	
	/* Example 13 (H): Given a finite, ordered Stream<T>, return the "substream" of its elements from "index"
	 * startInclusive to endExclusive. Assume the indices are valid (specifically, 0 <= startInclusive <= endExclusive,
	 * and endExclusive is less than or equal to the number of elements in the stream). */
	public static <T> Stream<T> substream(Stream<T> stream, int startInclusive, int endExclusive) {
		return null;
	}
	
	/* Example 14 (H): Given a collection of maps with keys of the same type, return a set containing all of the
	 * keys from all of the maps. */
	public static <K> Set<K> allKeys(Collection<? extends Map<K, ?>> maps) {
		return null;
	}
	
	/* Example 15 (H): Given a Set<Person>, return a LinkedList<String> of the first names of people whose first names
	 * are in the second half of the alphabet (N to Z, inclusive). The names should be sorted alphabetically. You can
	 * assume every person has a non-null first name that starts with a capital letter. */
	public static LinkedList<String> secondHalfNames(Set<Person> set) {
		return null;
	}
	
	/* Example 16 (H): Given a List<String>, return a List<String> containing the five longest distinct strings, ordered
	 * from longest to shortest. Strings of the same length should be sorted lexicographically (that is, by the natural
	 * order of strings). The given list will have at least five strings. For example, if given
	 * [a, biscuit, philanthropy, biscuit, congenial, metaphysics, ontology, philanthropy, nutate, maximize], return
	 * [philanthropy, metaphysics, congenial, maximize, ontology]
	 */
	public static List<String> longestStrings(List<String> list) {
		return null;
	}
	
	
	
	
	
	//HINTS
	
	//Example 13: Use skip(int) and limit(int). Yes, I know you can just convert it to a list and then back to a stream,
	//but there's a better (and shorter) way.
	
	//Example 14: Use the flatMap method on Streams as well as the keySet method on Maps.
	
	//Example 15: The following boolean expression will determine if a char c is in the second half of the alphabet:
	//				'N' <= c && c <= 'Z'
	//Note that you can get the char at a specific index in a string by using the charAt(int) method on Strings.
	
	//Example 16: Be careful when creating the comparator. If you're using reversed(), be sure not to reverse the
	//lexicographic order, only the length order.
	
}