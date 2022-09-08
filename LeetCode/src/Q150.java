import java.util.Stack;

public class Q150 {
  /*Evaluate the value of an arithmetic expression in Reverse Polish Notation.

  Valid operators are +, -, *, and /. Each operand may be an integer or another expression.

  Note that division between two integers should truncate toward zero.

  It is guaranteed that the given RPN expression is always valid.
  That means the expression would always evaluate to a result, and there will not be any division by zero operation.

  https://leetcode.cn/problems/evaluate-reverse-polish-notation*/
  public static void main(String[] args) {
    String[] tokens = new String[] {"4", "13", "5", "/", "+"};
    System.out.println(evalRPN(tokens));
  }

  public static int evalRPN(String[] tokens) {
    Stack<Integer> stack = new Stack<>();
    for (String s : tokens) {
      if (s.equals("+")) {
        stack.push(stack.pop() + stack.pop());
      } else if (s.equals("-")) {
        // firstly poped number would be the second number in fromula
        // it matters in - and / but not in + and *
        Integer sec = stack.pop();
        Integer fir = stack.pop();
        stack.push(fir - sec);
      } else if (s.equals("*")) {
        stack.push(stack.pop() * stack.pop());
      } else if (s.equals("/")) {
        Integer sec = stack.pop();
        Integer fir = stack.pop();
        stack.push(fir / sec);
      } else {
        stack.push(Integer.valueOf(s));
      }
    }
    return stack.pop();
  }
}
