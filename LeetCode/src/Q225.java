import java.util.LinkedList;
import java.util.Queue;

public class Q225 {
  /*Implement a last-in-first-out (LIFO) stack using only two queues. The implemented stack should support all the functions of a normal stack (push, top, pop, and empty).

  Implement the MyStack class:

  void push(int x) Pushes element x to the top of the stack.
  int pop() Removes the element on the top of the stack and returns it.
  int top() Returns the element on the top of the stack.
  boolean empty() Returns true if the stack is empty, false otherwise.
          Notes:

  You must use only standard operations of a queue, which means that only push to back, peek/pop from front, size and is empty operations are valid.
  Depending on your language, the queue may not be supported natively. You may simulate a queue using a list or deque (double-ended queue) as long as you use only a queue's standard operations.
  https://leetcode.cn/problems/implement-stack-using-queues*/

  public static void main(String[] args) {
    MyStack obj = new MyStack();
    obj.push(1);
    obj.push(2);
    System.out.println(obj.top());
    System.out.println(obj.pop());
    System.out.println(obj.empty());
  }

  static class MyStack {
    Queue<Integer> q1;
    Queue<Integer> q2;

    public MyStack() {
      q1 = new LinkedList<>();
      q2 = new LinkedList<>();
    }

    public void push(int x) {
      if (!q2.isEmpty()) {
        q2.add(x);
      } else {
        q1.add(x);
      }
    }

    public int pop() {
      if (!q1.isEmpty()) {
        while (q1.size() > 1) {
          q2.add(q1.poll());
        }
        return q1.poll();
      } else {
        while (q2.size() > 1) {
          q1.add(q2.poll());
        }
        return q2.poll();
      }
    }

    public int top() {
      if (!q1.isEmpty()) {
        while (q1.size() > 1) {
          q2.add(q1.poll());
        }
        int ans = q1.poll();
        q2.add(ans);
        return ans;
      } else {
        while (q2.size() > 1) {
          q1.add(q2.poll());
        }
        int ans = q2.poll();
        q1.add(ans);
        return ans;
      }
    }

    public boolean empty() {
      return q1.isEmpty() && q2.isEmpty();
    }
  }
}
