import java.util.Stack;

public class Q20 {
  /*Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

  An input string is valid if:

  Open brackets must be closed by the same type of brackets.
  Open brackets must be closed in the correct order.
  Every close bracket has a corresponding open bracket of the same type.
  https://leetcode.cn/problems/valid-parentheses*/
  public static void main(String[] args) {
    String s = "()[]{}";
    System.out.println(isValid(s));
  }

  public static boolean isValid(String s) {
    char[] chars = s.toCharArray();
    Stack<Character> stack = new Stack<>();
    for (char c : chars) {
      if (c == '(') {
        stack.push(')');
      } else if (c == '[') {
        stack.push(']');
      } else if (c == '{') {
        stack.push('}');
      } else {
        if (stack.isEmpty() || c != stack.pop()) {
          return false;
        }
      }
    }
    return stack.isEmpty();
  }
}
