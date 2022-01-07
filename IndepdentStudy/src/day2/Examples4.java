package day2;

import java.util.*;

public class Examples4 {

	public static void main(String[] args) {
		List<Integer> list = new ArrayList<>();
		Collections.addAll(list, 3, 4, -5, 6, -7, 8);
		removeNegatives(list);
		System.out.println(list);
	}
	
	static void removeNegatives(Collection<? extends Number> nums) {
		//Statement Expression 
//		for(Iterator<? extends Number> itr = nums.iterator(); itr.hasNext() ; ) {
//			Number next = itr.next();
//			if(next.doubleValue() < 0)
//				itr.remove();
//		}
		
		Iterator<? extends Number> itr = nums.iterator();
		while(itr.hasNext()) {
			Number next = itr.next();
			if(next.doubleValue() < 0)
				itr.remove();
		}
	}
	
}
