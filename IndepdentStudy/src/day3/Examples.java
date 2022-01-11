package day3;

public class Examples {

	public static void main(String[] args) {
//		String s = "abcdef";
//		
//		CharMapper mapper = c -> {
//			return (char) (c + 1);
//		};
//		
//		System.out.println(mapChars(s, mapper));
//		System.out.println(mapChars(s, c -> Character.toUpperCase(c)));
		
		String x = "d34d84-8d34";
		
		CharPredicate p = c -> c >= 'a' && c <= 'z';
		
		System.out.println(contains(x, p));
		System.out.println(contains(x, c -> c == '0' || c == '1'));
	}
	
	public static boolean contains(String str, CharPredicate condition) {
		for(char c : str.toCharArray())
			if(condition.test(c))
				return true;
		return false;
	}
	
	public static String mapChars(String str, CharMapper mapper) {
		String result = "";
		for(char c : str.toCharArray())
			result += mapper.map(c);
		return result;
	}
	
}
