import java.util.Stack;

public class Q1081 {
  /*Given a string s, return the lexicographically smallest subsequence of s
  that contains all the distinct characters of s exactly once.
  https://leetcode.cn/problems/smallest-subsequence-of-distinct-characters*/
  public static void main(String[] args) {
    String s = "cbacdcbc";
    System.out.println(smallestSubsequence(s));
  }

  public static String smallestSubsequence(String s) {
    int[] count = new int[123];
    for (char c : s.toCharArray()) {
      count[c] += 1;
    }
    Stack<Character> stack = new Stack<>();
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      count[c] -= 1;
      if (stack.contains(c)) {
        continue;
      }
      while (!stack.isEmpty() && stack.peek() > c) {
        if (count[stack.peek()] == 0) {
          break;
        } else {
          stack.pop();
        }
      }
      stack.push(c);
    }
    StringBuilder sb = new StringBuilder();
    while (!stack.isEmpty()) {
      sb.append(stack.pop());
    }
    return sb.reverse().toString();
  }
}
