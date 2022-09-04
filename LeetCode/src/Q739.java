import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Stack;

public class Q739 {
  /*Given an array of integers temperatures represents the daily temperatures, return an array answer
  such that answer[i] is the number of days you have to wait after the ith day to get a warmer temperature.
  If there is no future day for which this is possible, keep answer[i] == 0 instead.
  https://leetcode.cn/problems/daily-temperatures*/
  public static void main(String[] args) {
    int[] temperatures = new int[] {73, 74, 75, 71, 69, 72, 76, 73};
    System.out.println(Arrays.toString(dailyTemperatures1(temperatures)));
  }

  public static int[] dailyTemperatures1(int[] temperatures) {
    // the answer is the index-gap between elements and its nearest bigger
    // so we record index in the stack
    int n = temperatures.length;
    int[] answer = new int[n];
    Stack<Integer> stack = new Stack<>();
    for (int i = n - 1; i >= 0; i--) {
      while (!stack.isEmpty()) {
        if (temperatures[stack.peek()] > temperatures[i]) {
          answer[i] = stack.peek() - i;
          stack.push(i);
          break;
        } else {
          stack.pop();
        }
      }
      if (stack.isEmpty()) {
        answer[i] = 0;
        stack.push(i);
      }
    }
    return answer;
  }

  public static int[] dailyTemperatures2(int[] temperatures) {
    // use priority queue because PQ offers additional information INDEX
    PriorityQueue<int[]> pq =
        new PriorityQueue(
            new Comparator<int[]>() {
              @Override
              public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
              }
            });
    int n = temperatures.length;
    int[] answer = new int[n];
    for (int i = n - 1; i >= 0; i--) {
      while (!pq.isEmpty()) {
        int[] temp = pq.peek();
        if (temp[0] > temperatures[i]) {
          answer[i] = temp[1] - i;
          pq.offer(new int[] {temperatures[i], i});
          break;
        } else {
          pq.poll();
        }
      }
      if (pq.isEmpty()) {
        answer[i] = 0;
        pq.offer(new int[] {temperatures[i], i});
      }
    }
    return answer;
  }
}
