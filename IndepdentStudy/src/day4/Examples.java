package day4;

import java.util.*;
import java.util.function.*;

public class Examples {
	
	public static void main(String[] args) {
//		DoubleUnaryOperator f = x -> Math.sqrt(x);
//		DoubleUnaryOperator f2 = Math::sqrt;
//		
//		System.out.println(f.applyAsDouble(49));
//		System.out.println(f2.applyAsDouble(49));
		
//		Predicate<String> p = "Hello"::equals;
		
//		System.out.println(p.test("pear"));
//		System.out.println(p.test("Hello"));
//		System.out.println(p.test("aoekfaoef"));
		
//		String str = "abcdefghijklmnopqrstuvwxyz";
		
//		Predicate<String> ill = str::contains;
		
//		System.out.println(ill.test("x"));
//		System.out.println(ill.test("hi"));
//		System.out.println(ill.test("B"));
		
		// double sqrt(double)
		// double applyAsDouble(double)
//		DoubleUnaryOperator sr = Math::sqrt;
		
		// int length()
		// int applyAsInt(String)
//		ToIntFunction<String> f = String::length;
		
//		System.out.println(f.applyAsInt("bye"));
		
//		Supplier<Object> r = Object::new;
		
//		IntFunction<ArrayList<String>> h = ArrayList<String>::new;
//		Supplier<ArrayList<String>> g = ArrayList<String>::new;
		Function<Collection<? extends String>, ArrayList<String>> j = ArrayList<String>::new;
		
		IntFunction<boolean[]> arrMaker = boolean[]::new;
		
		boolean[] ten = arrMaker.apply(10);
		
		List<String> strs = List.of("a","b","c");
		String[] strArr = strs.toArray(String[]::new);
		
	}
	
}
