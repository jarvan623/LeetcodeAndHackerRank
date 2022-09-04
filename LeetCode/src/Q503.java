import java.util.Arrays;
import java.util.Stack;

public class Q503 {
  /*Given a circular integer array nums (i.e., the next element of nums[nums.length - 1] is nums[0]), return the next greater number for every element in nums.

  The next greater number of a number x is the first greater number to its traversing-order next in the array, which means you could search circularly to find its next greater number. If it doesn't exist, return -1 for this number.

  https://leetcode.cn/problems/next-greater-element-ii*/
  public static void main(String[] args) {
    int[] nums = new int[] {1, 2, 3, 4, 3};
    System.out.println(Arrays.toString(nextGreaterElements(nums)));
  }

  public static int[] nextGreaterElements(int[] nums) {
    // circular array means 2 times iterations
    int n = nums.length;
    int[] answer = new int[n];
    Stack<Integer> stack = new Stack<>();
    for (int i = n - 1; i >= 0; i--) {
      // first iteration only update stack, no record
      while (!stack.isEmpty()) {
        if (stack.peek() > nums[i]) {
          stack.push(nums[i]);
          break;
        } else {
          stack.pop();
        }
      }
      if (stack.isEmpty()) {
        stack.push(nums[i]);
      }
    }
    for (int i = n - 1; i >= 0; i--) {
      // second iteration, from tail to head, record its next bigger element
      while (!stack.isEmpty()) {
        if (stack.peek() > nums[i]) {
          answer[i] = stack.peek();
          stack.push(nums[i]);
          break;
        } else {
          stack.pop();
        }
      }
      if (stack.isEmpty()) {
        stack.push(nums[i]);
        answer[i] = -1;
      }
    }
    return answer;
  }
}
