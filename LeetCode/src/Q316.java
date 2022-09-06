import java.util.Stack;

public class Q316 {
  /*Given a string s, remove duplicate letters so that every letter appears once and only once.
  You must make sure your result is the smallest in lexicographical order among all possible results.
  https://leetcode.cn/problems/remove-duplicate-letters*/
  public static void main(String[] args) {
    String s = "cdadabcc";
    System.out.println(removeDuplicateLetters(s)); // acdb
  }

  public static String removeDuplicateLetters(String s) {
    int[] count = new int[123];
    for (char c : s.toCharArray()) {
      // count the appearance of every letter
      count[c] += 1;
    }
    Stack<Character> stack = new Stack<>();
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      count[c] -= 1;
      if (stack.contains(c)) {
        // if already contians,go to next letter
        continue;
      }
      while (!stack.isEmpty() && stack.peek() > c) {
        if (count[stack.peek()] == 0) {
          // no more peek letter, so cannot pop,break and push current letter into stack
          break;
        } else {
          stack.pop();
        }
      }
      // when while loop breaks, push current letter into stack
      stack.push(c);
    }
    StringBuilder sb = new StringBuilder();
    while (!stack.isEmpty()) {
      sb.append(stack.pop());
    }
    return sb.reverse().toString();
  }
}
