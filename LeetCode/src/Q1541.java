import java.util.Stack;

public class Q1541 {
  /*Given a parentheses string s containing only the characters '(' and ')'. A parentheses string is balanced if:

  Any left parenthesis '(' must have a corresponding two consecutive right parenthesis '))'.
  Left parenthesis '(' must go before the corresponding two consecutive right parenthesis '))'.
  In other words, we treat '(' as an opening parenthesis and '))' as a closing parenthesis.

  For example, "())", "())(())))" and "(())())))" are balanced, ")()", "()))" and "(()))" are not balanced.
  You can insert the characters '(' and ')' at any position of the string to balance it if needed.

  Return the minimum number of insertions needed to make s balanced.

  https://leetcode.cn/problems/minimum-insertions-to-balance-a-parentheses-string*/
  public static void main(String[] args) {
    String s = ")()";
    System.out.println(minInsertions(s));
  }

  public static int minInsertions(String s) {
    if (s.length() == 0) {
      return 0;
    }
    char[] chars = s.toCharArray();
    Stack<Character> stack = new Stack<>();
    int count = 0;
    for (int i = 0; i < chars.length; i++) {
      char c = chars[i];
      if (c == '(') {
        // every open bracket needs two close brackets
        // stack means how many ) needed
        stack.push(')');
        stack.push(')');
        continue;
      }
      if (i + 1 == chars.length) {
        // if i is last element
        if (stack.isEmpty()) {
          // stack is empty, so need a '(' and a ')' to balance
          count += 2;
          return count;
        } else {
          // -1 represents the last )
          return count + stack.size() - 1;
        }
      } else {
        if (chars[i + 1] == ')') {
          if (stack.isEmpty()) {
            // if double ), but stack is empty, need ONE (
            count++;
            i++;
            continue;
          }
          // if not empty,pop two )
          i++;
          stack.pop();
          stack.pop();
          continue;
        }
        if (chars[i + 1] == '(') {
          if (stack.isEmpty()) {
            // 2 represents one ( and one )
            count += 2;
            continue;
          } else {
            // ) cannot alon so count++, make it double
            // don't forget to pop twice, because stack means how many ) needed
            count++;
            stack.pop();
            stack.pop();
          }
        }
      }
    }
    return count + stack.size();
  }
}
