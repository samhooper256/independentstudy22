package day1;

import java.util.Scanner;

public class FPExample {

	public static void main(String[] args) {
		printStars(getInput(new Scanner(System.in)));
	}
	
	static int getInput(Scanner in) {
		return in.nextInt();
	}
	
	static void printStars(int n) {
		System.out.println(increasing(n) + decreasing(n - 1));
	}
	
	static String increasing(int n) {
		return n <= 0 ? "" : increasing(n - 1) + "\n" + " ".repeat(n - 1) + "*";
	}
	
	static String decreasing(int n) {
		return n <= 0 ? "" : "\n" + " ".repeat(n - 1) + "*" + decreasing(n - 1);
	}
	
}
