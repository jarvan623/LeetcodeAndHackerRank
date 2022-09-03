import java.util.Stack;

public class Q232 {
  /*Implement a first in first out (FIFO) queue using only two stacks. The implemented queue should support all the functions of a normal queue (push, peek, pop, and empty).

  Implement the MyQueue class:

  void push(int x) Pushes element x to the back of the queue.
  int pop() Removes the element from the front of the queue and returns it.
  int peek() Returns the element at the front of the queue.
  boolean empty() Returns true if the queue is empty, false otherwise.
          Notes:

  You must use only standard operations of a stack, which means only push to top, peek/pop from top, size, and is empty operations are valid.
  Depending on your language, the stack may not be supported natively. You may simulate a stack using a list or deque (double-ended queue) as long as you use only a stack's standard operations.
  https://leetcode.cn/problems/implement-queue-using-stacks*/
  public static void main(String[] args) {
    MyQueue obj = new MyQueue();
    obj.push(1);
    obj.push(2);
    System.out.println(obj.peek());
    System.out.println(obj.pop());
    System.out.println(obj.empty());
  }

  static class MyQueue {
    Stack<Integer> s1 = new Stack<>();
    Stack<Integer> s2 = new Stack<>();

    public MyQueue() {}

    public void push(int x) {
      s1.push(x);
    }

    public int pop() {
      if (!s2.isEmpty()) {
        return s2.pop();
      }
      while (!s1.isEmpty()) {
        s2.push(s1.pop());
      }
      return s2.pop();
    }

    public int peek() {
      if (!s2.isEmpty()) {
        return s2.peek();
      }
      while (!s1.isEmpty()) {
        s2.push(s1.pop());
      }
      return s2.peek();
    }

    public boolean empty() {
      return s1.isEmpty() && s2.isEmpty();
    }
  }
}
