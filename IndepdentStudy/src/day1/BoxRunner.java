package day1;

public class BoxRunner {
	
	public static void main(String[] args) {
		
		Box<Integer> box = new Box<>(6);
		
		Integer item = box.getItem();
		
		System.out.println(item);
		
		Box<String> strBox = new Box<>("hi");
		strBox.setItem("bye");
		System.out.println(strBox.getItem());
		
		String strItem = strBox.getItem();
		
		Box<Object> objBox = new Box<>(null);
		
	}
	
}
