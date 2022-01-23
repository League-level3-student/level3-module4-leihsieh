package _01_TestMatchingBrackets;

import java.util.Stack;

public class TestMatchingBrackets {
	/*
	 * Use a Stack to complete the method for checking if every opening bracket
	 * has a matching closing bracket
	 */
	public static boolean doBracketsMatch(String b) {
		Stack<Character> stack = new Stack<Character>();
		boolean matching = false;
		for(int i = 0; i < b.length(); i++) {
			if(b.charAt(i) == '{') {
				stack.push(b.charAt(i));
				continue;
			}
			if(i == 0 && stack.isEmpty() == true) {
				return matching;
			}
			else if (b.charAt(i) == '}') {
				stack.pop();
				continue;
			}
		}
		System.out.println(stack.isEmpty());
		if(stack.isEmpty() == true) {
			matching = true;
		}

		return matching;
	}
}