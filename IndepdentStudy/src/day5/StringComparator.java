package day5;

import java.util.Comparator;

public class StringComparator implements Comparator<String> {

	/* Returns...
	 *  - a negative # if o1 < o2
	 *  - 0 if o1 equals o2
	 *  - a positive # if o1 > o2
	 */
	@Override
	public int compare(String o1, String o2) {
		return o1.length() - o2.length();
	}

}
