import java.util.Arrays;

public class Q283 {
  /*Given an integer array nums, move all 0's to the end of it while maintaining the relative order of the non-zero elements.

  Note that you must do this in-place without making a copy of the array.

  https://leetcode.cn/problems/move-zeroes*/
  static int[] nums = new int[] {0, 1, 0, 3, 12};

  public static void main(String[] args) {
    moveZeroes(nums);
    System.out.println(Arrays.toString(nums));
  }

  public static void moveZeroes(int[] nums) {
    int slow = 0;
    int fast = 0;
    while (fast < nums.length) {
      if (nums[fast] != 0) {
        nums[slow] = nums[fast];
        slow++;
      }
      fast++;
    }
    // change all elements after slow TO 0
    for (int i = slow; i < nums.length; i++) {
      nums[i] = 0;
    }
  }
}
