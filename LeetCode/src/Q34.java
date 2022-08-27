import java.util.Arrays;

public class Q34 {
  /*  Given an array of integers nums sorted in non-decreasing order, find the starting and ending position of a given target value.

  If target is not found in the array, return [-1, -1].

  You must write an algorithm with O(log n) runtime complexity.

  https://leetcode.cn/problems/find-first-and-last-position-of-element-in-sorted-array*/

  public static void main(String[] args) {
    int[] nums = new int[] {5, 7, 7, 8, 8, 10};
    System.out.println(Arrays.toString(searchRange(nums, 8)));
  }

  public static int[] searchRange(int[] nums, int target) {
    // this question is finding a range not only one specific index
    int[] answer = new int[] {-1, -1};
    int high = nums.length - 1;
    int low = 0;
    while (low <= high) {
      int mid = low + (high - low) / 2;
      if (nums[mid] > target) {
        high = mid - 1;
      } else if (nums[mid] < target) {
        low = mid + 1;
      } else {
        if (nums[high] == target && nums[low] == target) {
          answer[0] = low;
          answer[1] = high;
          return answer;
        } else if (nums[high] == target) {
          low++;
        } else if (nums[low] == target) {
          high--;
        } else {
          low++;
          high--;
        }
      }
    }
    return answer;
  }
}
