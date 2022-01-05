package day1;

public class Box<T> {	
	
	private T item;
	
	public Box(T itemArg) {
		item = itemArg;
	}
	
	public T getItem() {
		return item;
	}
	
	public void setItem(T newItem) {
		item = newItem;
	}
	
}
