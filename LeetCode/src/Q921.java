import java.util.Stack;

public class Q921 {
  /*A parentheses string is valid if and only if:

  It is the empty string,
  It can be written as AB (A concatenated with B), where A and B are valid strings, or
  It can be written as (A), where A is a valid string.
  You are given a parentheses string s. In one move, you can insert a parenthesis at any position of the string.

  For example, if s = "()))", you can insert an opening parenthesis to be "(()))" or a closing parenthesis to be "())))".
  Return the minimum number of moves required to make s valid.

  https://leetcode.cn/problems/minimum-add-to-make-parentheses-valid*/

  public static void main(String[] args) {
    String s = "())";
    System.out.println(minAddToMakeValid(s));
  }

  public static int minAddToMakeValid(String s) {
    if (s.length() == 0) {
      return 0;
    }
    Stack<Character> stack = new Stack<>();
    char[] chars = s.toCharArray();
    int count = 0;
    for (char c : chars) {
      if (c == '(') {
        stack.push(')');
      } else if (c == '[') {
        stack.push(']');
      } else if (c == '{') {
        stack.push('}');
      } else {
        if (stack.isEmpty() || c != stack.peek()) {
          // if empty means you need a opening bracket
          // if not equal,means you need a corresponding close bracket
          // but remains the wrong one in stack for later use
          count++;
          continue;
        }
        stack.pop();
      }
    }
    return count + stack.size();
  }
}
