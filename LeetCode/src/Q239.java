import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class Q239 {
  /*You are given an array of integers nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.

  Return the max sliding window.

  https://leetcode.cn/problems/sliding-window-maximum*/
  public static void main(String[] args) {
    int[] nums = new int[] {1, 3, -1, -3, 5, 3, 6, 7};
    System.out.println(Arrays.toString(maxSlidingWindow3(nums, 3)));
  }

  public static int[] maxSlidingWindow1(int[] nums, int k) {
    // priority queue is easy to understand but the performance is bad cannot pass all cases
    int n = nums.length;
    int[] answer = new int[n - k + 1];
    PriorityQueue<Integer> pq =
        new PriorityQueue(
            new Comparator<Integer>() {
              @Override
              public int compare(Integer o1, Integer o2) {
                return o2 - o1;
              }
            });
    for (int i = 0; i < k; i++) {
      pq.offer(nums[i]);
    }
    answer[0] = pq.peek();
    for (int i = 1; i < answer.length; i++) {
      pq.remove(nums[i - 1]);
      pq.add(nums[i + k - 1]);
      answer[i] = pq.peek();
    }
    return answer;
  }

  public static int[] maxSlidingWindow2(int[] nums, int k) {
    MonotonicQueue window = new MonotonicQueue();
    int[] answer = new int[nums.length - k + 1];
    for (int i = 0; i < nums.length; i++) {
      if (i < k - 1) {
        window.push(nums[i]);
      } else {
        window.push(nums[i]);
        answer[i - k + 1] = window.max();
        window.pop(nums[i - k + 1]);
      }
    }
    return answer;
  }

  public static int[] maxSlidingWindow3(int[] nums, int k) {
    // monotonic queue original
    LinkedList<Integer> mq = new LinkedList<>();
    int[] answer = new int[nums.length - k + 1];
    for (int i = 0; i < nums.length; i++) {
      if (i > k - 1 && mq.getFirst() == nums[i - k]) {
        // only i after k-1 needs to be polled and it has to be the first(biggest) element
        // otherwise it is not in the queue
        mq.pollFirst();
      }
      while (!mq.isEmpty() && mq.getLast() < nums[i]) {
        // any before&&smaller elements would be poll
        mq.pollLast();
      }
      // all before&&smaller elements has been polled, then add current element
      mq.addLast(nums[i]);
      if (i >= k - 1) {
        // get current biggest element and record it to answer
        answer[i - k + 1] = mq.getFirst();
      }
    }
    return answer;
  }

  static class MonotonicQueue {
    LinkedList<Integer> mq = new LinkedList<>();

    public void push(int n) {
      // keep remove smaller element and ensure all elements before are bigger
      while (!mq.isEmpty() && mq.getLast() < n) {
        mq.pollLast();
      }
      mq.addLast(n);
    }

    public int max() {
      // first element is the biggest element
      return mq.getFirst();
    }

    public void pop(int n) {
      // n is the oldest element and biggest element if it could be poped
      // if not equal, means n already gone
      if (n == mq.getFirst()) {
        mq.pollFirst();
      }
    }
  }
}
