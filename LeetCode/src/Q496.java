import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

public class Q496 {
  /*The next greater element of some element x in an array is the first greater element that is to the right of x in the same array.

  You are given two distinct 0-indexed integer arrays nums1 and nums2, where nums1 is a subset of nums2.

  For each 0 <= i < nums1.length, find the index j such that nums1[i] == nums2[j] and determine the next greater element of nums2[j] in nums2. If there is no next greater element, then the answer for this query is -1.

  Return an array ans of length nums1.length such that ans[i] is the next greater element as described above.

  https://leetcode.cn/problems/next-greater-element-i*/
  public static void main(String[] args) {
    int[] nums1 = new int[] {4, 1, 2};
    int[] nums2 = new int[] {1, 3, 4, 2};
    System.out.println(Arrays.toString(nextGreaterElement(nums1, nums2)));
  }

  public static int[] nextGreaterElement(int[] nums1, int[] nums2) {
    int[] allAnswer = new int[nums2.length];
    Stack<Integer> stack = new Stack();
    // map records the element - index , only need one iteration of array
    HashMap<Integer, Integer> map = new HashMap<>();
    for (int i = nums2.length - 1; i >= 0; i--) {
      map.put(nums2[i], i);
      while (!stack.isEmpty()) {
        // peek is the smallest element in stack
        if (stack.peek() > nums2[i]) {
          // if bigger than nums2[i],record and push nums2[i] to stack and break the while loop
          allAnswer[i] = stack.peek();
          stack.push(nums2[i]);
          break;
        } else {
          // keep pop until find a element bigger than nums2[i]
          stack.pop();
        }
      }
      // if there is no element bigger than nums2[i], record -1, and put nums2[i] in stack
      if (stack.isEmpty()) {
        allAnswer[i] = -1;
        stack.push(nums2[i]);
      }
    }
    int[] answer = new int[nums1.length];
    for (int i = 0; i < nums1.length; i++) {
      answer[i] = allAnswer[map.get(nums1[i])];
    }
    return answer;
  }
}
